package com.hu.ssm.exception;

/**
 * Created by huz on 2018/3/18.
 * 重复秒杀异常（运行时异常）
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
