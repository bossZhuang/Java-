package com.hu.ssm.api;

import com.hu.ssm.entity.SuccessKilled;

/**
 * Created by huz on 2018/1/9.
 */
public interface ISuccessKilledService {
    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(long seckillId,long userPhone);

    /**
     * 根据ID查询successKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
