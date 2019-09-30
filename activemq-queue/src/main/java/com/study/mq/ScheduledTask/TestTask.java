package com.study.mq.ScheduledTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger log = LoggerFactory.getLogger(TestTask.class);
    @Value("${scheduled.enable}")//定时任务开关
    private String scheduledEnable;
    /**
     * 定义每过3秒执行任务
     * 支持使用 @Scheduled(cron = "4-40 * * * * ?") cron表达式
     */
    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        if(!Boolean.parseBoolean(scheduledEnable)){
            log.info("定时任务已关闭");
            return;
        }
        log.info("现在时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/15 * * * * ? ")
    private void sayHello(){
        String dateTime = dateFormat.format(new Date());
        log.info("{} 向宇宙发出了一声问候: Hello World!", dateTime);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/16 * * * * ? ")
    private void sayHello2(){
        String dateTime = dateFormat.format(new Date());
        log.info("{} 向宇宙发出了一声问候: 你好,世界", dateTime);
    }

}
