package com.hu.ssm.service;

import com.hu.ssm.entity.Seckill;
import com.hu.ssm.service.impl.SeckillServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huz on 2018/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml",
"classpath:spring-service.xml"})
public class ISeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISeckillService iSeckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = iSeckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void exportSeckillUrl() throws Exception {

    }

    @Test
    public void executeSeckill() throws Exception {

    }

}