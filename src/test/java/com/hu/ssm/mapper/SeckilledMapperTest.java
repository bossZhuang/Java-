package com.hu.ssm.mapper;

import com.hu.ssm.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置Spring和Junit整合，Junit启动时加载SpringIOC容器
 * Created by huz on 2018/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉JunitSpring的配置文件
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class SeckilledMapperTest {

    //注入mapper实现依赖
    @Resource
    private SeckilledMapper seckilledMapper;
    @Test
    public void reduceNumber() throws Exception {
        int update = seckilledMapper.reduceNumber(1000L,new Date());
        System.out.println(update);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckilledMapper.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
            List<Seckill> seckillList = seckilledMapper.queryAll(0,100);
            for(Seckill seckill:seckillList){
                System.out.println(seckill);
            }
    }

}