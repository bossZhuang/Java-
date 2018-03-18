package com.hu.ssm.exception;

/**
 * Created by huz on 2018/3/18.
 * 秒杀业务相关异常
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
