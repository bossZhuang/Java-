-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
CREATE TABLE seckill(
  `seckill_id` bigint not null AUTO_INCREMENT COMMENT '商品库存ID',
  `name` VARCHAR(120) not null COMMENT '商品名称',
  `number` INT not null COMMENT '库存数量',
  `create_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `start_time` timestamp not null COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP not null COMMENT '秒杀结束时间',
  PRIMARY key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=`utf8` COMMENT='描述库存表';

-- 初始化数据
INSERT INTO seckill(name,number,start_time,end_time)
value
  ('1000元秒杀iPhone',100,'2017-11-01 00:00:00','2017-11-02 00:00:00'),
  ('5000元秒杀iPhone8',200,'2017-11-01 00:00:00','2017-11-02 00:00:00'),
  ('100元秒杀xiaomi',300,'2017-11-01 00:00:00','2017-11-02 00:00:00'),
  ('400元秒杀hongmi',200,'2017-11-01 00:00:00','2017-11-02 00:00:00');
-- 秒杀成功信息表
-- 用户登录认证相关信息
CREATE TABLE success_killed(
  `seckill_id` bigint not null COMMENT '秒杀商品ID',
  `user_phone` bigint not null COMMENT '用户手机号',
  `state` tinyint not null DEFAULT -1 COMMENT '状态标识:-1:无效 0：成功 1：已付款',
  `create_time` TIMESTAMP not null COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),
  key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=`utf8` COMMENT='秒杀成功信息表';
