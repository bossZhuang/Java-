# 关于开发Java高并发秒杀项目
*业务对象依赖图*

![image 图片](https://github.com/bossZhuang/Java-/blob/master/src/main/webapp/images/1.png)

*service层和dao层总结*

为了提高秒杀时候的安全性，秒杀接口是只有在规定时间内才能暴露给用户,
秒杀商品在数据库中有两个特殊字段，秒杀开启时间和结束时间，除此之外为了安全还有使用MD5加密字符串作为正确用户验证，自己封装秒杀执行后结果
