package com.study.mq.services.impl;

import com.study.mq.services.IThreadTestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @classNameThreadServiceImpl
 * @Description TODO
 * @Auhtorshiboxue
 * @Date2019/9/3015:54
 * @Version 1.0
 **/
@Service("threadTestService")
public class ThreadServiceImpl implements IThreadTestService {
    @Async
    @Override
    public void test(int i) {
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }
}
