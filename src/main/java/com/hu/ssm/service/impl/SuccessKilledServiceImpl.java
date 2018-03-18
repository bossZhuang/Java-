package com.hu.ssm.service.impl;

import com.hu.ssm.entity.SuccessKilled;
import com.hu.ssm.service.ISuccessKilledService;
import org.springframework.stereotype.Service;

/**
 * Created by huz on 2018/1/9.
 */
@Service
public class SuccessKilledServiceImpl implements ISuccessKilledService {

    public int insertSuccessKilled(long seckillId, long userPhone) {
        return 0;
    }

    public SuccessKilled queryByIdWithSeckill(long seckillId) {
        return null;
    }
}
