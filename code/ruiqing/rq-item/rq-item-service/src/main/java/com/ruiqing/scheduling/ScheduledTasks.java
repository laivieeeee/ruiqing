package com.ruiqing.scheduling;

import com.ruiqing.common.utils.RedisLockUtil;
import com.ruiqing.common.utils.RedisUtil;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/5/27 15:28
 */
@Component
public class ScheduledTasks {
    @Scheduled(cron = "0 0/55 * * * ?")
    public void taskCustomReports() {
        String name = Thread.currentThread().getName();
        if (RedisLockUtil.lock(name,10000)){
            System.out.println("定时任务");
            RedisLockUtil.unlock(name,10000);
        }

    }
}
