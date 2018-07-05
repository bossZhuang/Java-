-- 秒杀执行存储过程
DELIMITER $$ -- console;转换为 $$
-- 定义存储过程
-- 参数定义:in 输入参数；out 输出参数
-- count_count();返回上一条修改类型sql(insert，delete，update)的影响行数
-- row_count; 0;未修改数据；>0表示修改行数；<0；sql错误/未执行修改SQL
CREATE PROCEDURE `seckill`.`execute_seckill`(in v_seckill_id bitint,in v_phone bigint,in v_kill_time timestamp,out r_result int)
  BEGIN
    DECLARE insert_count int default 0;
    START TRANSACTION ;
    INSERT IGNORE INTO success_killed
    (seckill_id,user_phone,create_time)
        VALUES (v_seckill_id,v_phone,v_kill_time)
    SELECT row_count() INTO insert_count;
    IF (insert_count = 0) then
      ROLLBACK;
      SET r_result = -1;
    ElSEIF(insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -1;
    ElSE
      UPDATE seckill
        SET number = number-1
        WHERE seckill_id = v_seckill_id
          AND end_time > v_kill_time
          AND start_time < v_kill_time
          AND number > 0;
        SELECT row_count() into insert_count;
      IF(insert_count = 0) THEN
        ROLLBACK;
        set r_result = 0;
      ElSEIF (insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ElSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$
-- 存储过程定义结束

DELIMITER ;

set @r_result=-3;
-- 执行存储过程
call execute_seckill(1003,13502178891,now(),@r_result);
-- 获取结果
SELECT @r_result;