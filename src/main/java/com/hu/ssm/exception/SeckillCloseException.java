package com.hu.ssm.exception;

/**
 * Created by huz on 2018/3/18.
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
