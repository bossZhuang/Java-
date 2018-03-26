package com.hu.ssm.service;

import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
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
        long id = 1000;
        Exposer exportSeckillUrl = iSeckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exportSeckillUrl);
        //exposed=true,
        // md5='c70d673a0e8614a74c1a4a9124801473',
        // seckillId=1000,
        // now=0,
        // start=0,
        // end=0
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000;
        long phone = 13502181181L;
        String md5 = "c70d673a0e8614a74c1a4a9124801473";
        SeckillExecution seckillExecution = iSeckillService.executeSeckill(id,phone,md5);
        logger.info("result={}",seckillExecution);
    }

}