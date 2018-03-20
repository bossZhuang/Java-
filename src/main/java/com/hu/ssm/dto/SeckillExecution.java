package com.hu.ssm.dto;

import com.hu.ssm.entity.SuccessKilled;
import com.hu.ssm.enums.SeckillStatEnum;

/**
 * Created by huz on 2018/3/17.
 * 封装秒杀执行后结果
 */
public class SeckillExecution {
    //订单ID
    private long seckillId;
    //秒杀执行结果状态
    private int status;
    //状态表示
    private String statusInfo;
    //秒杀成功对象
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = seckillStatEnum.getState();
        this.statusInfo = seckillStatEnum.getStateinfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, String statusInfo) {
        this.seckillId = seckillId;
        this.status = seckillStatEnum.getState();
        this.statusInfo = seckillStatEnum.getStateinfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
