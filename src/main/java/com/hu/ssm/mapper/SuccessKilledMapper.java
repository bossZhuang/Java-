package com.hu.ssm.mapper;

import com.hu.ssm.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by huz on 2018/3/13.
 */
public interface SuccessKilledMapper {

    /**
     * 插入购买明细，可重复过滤
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
