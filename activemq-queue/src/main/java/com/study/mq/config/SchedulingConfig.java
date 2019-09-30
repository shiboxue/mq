package com.study.mq.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @描述: 多线程执行定时任务
 * @日期 2019年5月27日
 */
@Configuration
public class SchedulingConfig implements SchedulingConfigurer {
    /**
     * @描述: 所有的定时任务都放在一个线程池中,定时任务启动时使用不同的线程
     * @param taskRegistrar
     * @日期 2019年5月27日
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
    }
}
