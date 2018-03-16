package com.hu.ssm.mapper;

import com.hu.ssm.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by huz on 2018/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉JunitSpring的配置文件
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class SuccessKilledMapperTest {

    @Resource
    private SuccessKilledMapper successKilledMapper;
    @Test
    public void insertSuccessKilled() throws Exception {
            long id = 1000L;
            long phone = 13502181181L;
            int insertCount = successKilledMapper.insertSuccessKilled(id,phone);
            System.out.println("insert="+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long phone = 13502181181L;
        SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(id,phone);
        System.out.println("insert="+successKilled);
        System.out.println(successKilled.getSeckill());
    }

}