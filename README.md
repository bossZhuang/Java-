# 关于开发Java高并发秒杀项目
*业务对象依赖图*

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/1.png)

##后台
*service层和dao层总结*

为了提高秒杀时候的安全性，秒杀接口是只有在规定时间内才能暴露给用户,
秒杀商品在数据库中有两个特殊字段，秒杀开启时间和结束时间，除此之外为了安全还有使用MD5加密字符串作为正确用户验证，自己封装秒杀执行后结果

##### 关于MD5验证原理

在秒杀接口方法中有一个MD5加密验证，在用户秒杀一件商品时，为了安全起见秒杀时除了秒杀id和用户电话还需要一个MD5，这个MD5是后台根据自己的一个加密串-盐结合秒杀id生成的，在暴露秒杀接口时将这个生成的MD5传到前台，在秒杀时更加秒杀id和本地的生成MD5方法生成一个相同的MD5，如果两者匹配那么证明就是安全的秒杀，如果不匹配甚至转过来的MD5为空那么就证明，这个访问非法，抛出异常。

**部分代码如下：**

暴露接口方法中：
```
String md5 = getMD5(seckillId);
```


秒杀接口方法中：
```
getMD5(seckillId).equals(md5)
```


getMD5方法：

```
private String getMD5(long seckillId){
    String base = seckillId + "/" +slat;
    String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
    return md5;
}
```
##前台

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/2.png)

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/3.png)

*Restful接口设计*

比如：GET /seckill/list

Post /seckill/execute/{seckillId}这是反例

Post /seckill/{seckillId}/execution这是正确的

URL设计
/模块/资源/{标示}/集合1/...

## 秒杀学习

1.前后台交互设计

2.resetApi接口设计

3.springmvc更多使用技巧

4.bootstrop和js使用

### 秒杀URL设计

GET /seckill/list 秒杀列表

GET /seckill/{id}/detail 详情页

Get /seckill/time/now 系统时间

POST /seckill/{id}/exposer 暴露秒杀

POST /seckill/{id}/{md5}/execution 执行秒杀

## 项目总结并发优化

系统存在可优化环节

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/6.png)

优化思路是把客户端逻辑放到MySQL服务端，避免网络延迟和GC影响
两种解决方案

定制SQL方案：

1.修改MySQL源码，淘宝就有过历史

2.使用存储过程使整个事物在MySQL端完成

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/7.png)

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/8.png)

这样做的原因是如果插入失败就没必要更新了。

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/9.png)

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/11.png)

虽然在本示例中使用MySQL中的事务能够有效节约系统系统秒杀事件，但并不适合所有系统，只是本事务较为简单。

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/12.png)

集群化部署示例有如下方式:

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/13.png)

## 总结

做完示例后学到的有

**秒杀系统的基本理念:**

1.数据库，实体构建

2.接口构建

3.安全保证

