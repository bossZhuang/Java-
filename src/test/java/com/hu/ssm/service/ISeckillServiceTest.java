package com.hu.ssm.service;

import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
import com.hu.ssm.entity.Seckill;
import com.hu.ssm.exception.RepeatKillException;
import com.hu.ssm.exception.SeckillCloseException;
import com.hu.ssm.exception.SeckillException;
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

    //测试代码完整逻辑,注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = iSeckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 13502171183L;
            String md5 = "c70d673a0e8614a74c1a4a9124801473";
            try {
                SeckillExecution seckillExecution = iSeckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }

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


        /**
         * 21:02:42.688 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
         21:02:42.696 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.710 {main} DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@654c1a54] will be managed by Spring
         21:02:42.717 {main} DEBUG c.h.s.m.SeckilledMapper.reduceNumber - ==>  Preparing: update seckill SET number = number-1 where seckill_id = ? and start_time <= ? and end_time >= ? and number > 0;
         21:02:42.751 {main} DEBUG c.h.s.m.SeckilledMapper.reduceNumber - ==> Parameters: 1000(Long), 2018-03-27 21:02:42.681(Timestamp), 2018-03-27 21:02:42.681(Timestamp)
         21:02:42.751 {main} DEBUG c.h.s.m.SeckilledMapper.reduceNumber - <==    Updates: 1
         21:02:42.752 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.752 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900] from current transaction
         21:02:42.752 {main} DEBUG c.h.s.m.S.insertSuccessKilled - ==>  Preparing: INSERT ignore INTO success_killed(seckill_id,user_phone,state) VALUES (?,?,0)
         21:02:42.753 {main} DEBUG c.h.s.m.S.insertSuccessKilled - ==> Parameters: 1000(Long), 13502171181(Long)
         21:02:42.754 {main} DEBUG c.h.s.m.S.insertSuccessKilled - <==    Updates: 1
         21:02:42.769 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.770 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900] from current transaction
         21:02:42.772 {main} DEBUG c.h.s.m.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id = s.seckill_id where sk.seckill_id = ? AND sk.user_phone=?
         21:02:42.772 {main} DEBUG c.h.s.m.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 13502171181(Long)
         21:02:42.792 {main} DEBUG c.h.s.m.S.queryByIdWithSeckill - <==      Total: 1
         21:02:42.799 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.818 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.818 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.819 {main} DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4604b900]
         21:02:42.876 {main} INFO  c.hu.ssm.service.ISeckillServiceTest - result=com.hu.ssm.dto.SeckillExecution@779dfe55
         */
        /**
         * result=SeckillExecution{
         * seckillId=1000,
         * status=1,
         * statusInfo='秒杀成功',
         * successKilled=
         * SuccessKilled{
         * seckillId=1000,
         * userPhone=13502171183,
         * state=0,
         * createTime=Tue Mar 27 21:05:33 CST 2018,
         * seckill=Seckill{
         * seckillId=1000,
         * name='1000元秒杀iPhone',
         * startTime=Wed Nov 01 00:00:00 CST 2017,
         * endTime=Fri Mar 30 00:00:00 CST 2018,
         * createTime=Tue Jan 09 21:22:03 CST 2018}}}
         */
    }

}