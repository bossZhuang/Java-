package com.hu.ssm.service;

import com.hu.ssm.api.ISuccessKilledService;
import com.hu.ssm.entity.SuccessKilled;
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
