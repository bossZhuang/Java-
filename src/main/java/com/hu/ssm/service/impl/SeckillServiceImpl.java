package com.hu.ssm.service.impl;

import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
import com.hu.ssm.entity.Seckill;
import com.hu.ssm.entity.SuccessKilled;
import com.hu.ssm.enums.SeckillStatEnum;
import com.hu.ssm.exception.RepeatKillException;
import com.hu.ssm.exception.SeckillCloseException;
import com.hu.ssm.exception.SeckillException;
import com.hu.ssm.mapper.SeckilledMapper;
import com.hu.ssm.mapper.SuccessKilledMapper;
import com.hu.ssm.service.ISeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口
 * 三个方面:方法定义粒度，转入参数，返回类型(return 类型和异常)
 * Created by huz on 2018/1/9.
 */
@Service
public class SeckillServiceImpl implements ISeckillService {

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckilledMapper seckilledMapper;
    @Autowired
    private SuccessKilledMapper successKilledMapper;

    private final String slat = "sdfsdfsdf8979ADSF";

    public List<Seckill> getSeckillList() {
        return seckilledMapper.queryAll(0,4);
    }

    public Seckill getById(long seckillId) {
        return seckilledMapper.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = getById(seckillId);
        if(seckill==null){
            return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //当前系统时间
        Date nowTime = new Date();
        if(nowTime.getTime()<startTime.getTime()
                ||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);//TODO
        System.out.println("md5是:"+md5);
        return new Exposer(true,md5,seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" +slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     *使用注解控制事务方法的优点
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能的短，不要穿插其他网络操作RPC/HTTP请求或剥离到事务方法的外部
     * 3.不是所有方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        if(md5 == null||!getMD5(seckillId).equals(md5)){
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑:减库存+购买记录
        Date nowDate = new Date();
        try {
            //减库存
            int updateCount = seckilledMapper.reduceNumber(seckillId,nowDate);
            if (updateCount <= 0){
                //没有更新记录秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledMapper.insertSuccessKilled(seckillId,userPhone);
                if (insertCount <= 0){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS ,successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            //所有编译期异常转化成运行时异常-有助于Spring声明式事务进行回滚操作
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }
}
