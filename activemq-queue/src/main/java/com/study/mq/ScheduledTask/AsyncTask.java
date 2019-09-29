package com.study.mq.ScheduledTask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * <br>
 * 标题: 自定义异步执行任务<br>
 * 描述: 异步方法需使用 @Async 注解<br>
 * 判断任务是否完成，必须返回 Future 接口
 * @author zc
 * @date 2018/04/26
 */
@Component
public class AsyncTask {

    @Async
    public Future<Boolean> doTask11() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时:" + (end - start) + "毫秒");
        return new AsyncResult<Boolean>(true);
    }

    @Async
    public Future<Boolean> doTask22() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(700);
        long end = System.currentTimeMillis();
        System.out.println("任务2耗时:" + (end - start) + "毫秒");
        return new AsyncResult<Boolean>(true);
    }

    @Async
    public Future<Boolean> doTask33() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();
        System.out.println("任务3耗时:" + (end - start) + "毫秒");
        return new AsyncResult<Boolean>(true);
    }

}
