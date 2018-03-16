package com.hu.ssm.api;

import com.hu.ssm.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by huz on 2018/1/9.
 */
public interface ISeckillService {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId,Date killTime);

    /**
     * 根据ID查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offet,int limit);
}
