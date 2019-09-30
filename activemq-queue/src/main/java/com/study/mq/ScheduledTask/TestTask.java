package com.study.mq.ScheduledTask;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>
 * 标题: 测试定时任务<br>
 * 描述: 自定义定时任务<br>
 *
 * @author zc
 * @date 2018/04/26
 */
@Component
public class TestTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Value("${scheduled.enable}")//定时任务开关
    private String scheduledEnable;
    /**
     * 定义每过3秒执行任务
     * 支持使用 @Scheduled(cron = "4-40 * * * * ?") cron表达式
     */
    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        if(!Boolean.parseBoolean(scheduledEnable)){
            System.out.println("定时任务已关闭");
            return;
        }
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

}
