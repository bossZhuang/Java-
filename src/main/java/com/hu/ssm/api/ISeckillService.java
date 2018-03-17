package com.hu.ssm.api;

import com.hu.ssm.dto.Exposer;
import com.hu.ssm.dto.SeckillExecution;
import com.hu.ssm.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口
 * 三个方面:方法定义粒度，转入参数，返回类型(return 类型和异常)
 * Created by huz on 2018/1/9.
 */
public interface ISeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口
     * 否则输出系统时间和秒杀时间
     * 防止秒杀前被用户拼出秒杀接口进行秒杀
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * md5判读用户是否正确安全性
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5);
}
