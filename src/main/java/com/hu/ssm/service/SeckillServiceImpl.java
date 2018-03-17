package com.hu.ssm.service;

import com.hu.ssm.api.ISeckillService;
import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
import com.hu.ssm.entity.Seckill;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口
 * 三个方面:方法定义粒度，转入参数，返回类型(return 类型和异常)
 * Created by huz on 2018/1/9.
 */
@Service
public class SeckillServiceImpl implements ISeckillService {


    public List<Seckill> getSeckillList() {
        return null;
    }

    public Seckill getById(long seckillId) {
        return null;
    }

    public Exposer exportSeckillUrl(long seckillId) {
        return null;
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) {
        return null;
    }
}
