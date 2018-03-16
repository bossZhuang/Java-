package com.hu.ssm.service;

import com.hu.ssm.api.ISeckillService;
import com.hu.ssm.entity.Seckill;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huz on 2018/1/9.
 */
@Service
public class SeckillServiceImpl implements ISeckillService {

    public int reduceNumber(long seckillId, Date killTime) {
        return 0;
    }

    public Seckill queryById(long seckillId) {
        return null;
    }

    public List<Seckill> queryAll(int offet, int limit) {
        return null;
    }
}
