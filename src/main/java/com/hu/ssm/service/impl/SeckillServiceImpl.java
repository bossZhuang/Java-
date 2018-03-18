package com.hu.ssm.service.impl;

import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
import com.hu.ssm.entity.Seckill;
import com.hu.ssm.exception.RepeatKillException;
import com.hu.ssm.exception.SeckillCloseException;
import com.hu.ssm.exception.SeckillException;
import com.hu.ssm.mapper.SeckilledMapper;
import com.hu.ssm.mapper.SuccessKilledMapper;
import com.hu.ssm.service.ISeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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

    private SeckilledMapper seckilledMapper;

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
        return new Exposer(true,md5,seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" +slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        return null;
    }
}
