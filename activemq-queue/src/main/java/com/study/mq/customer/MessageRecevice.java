package com.study.mq.customer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class MessageRecevice {

    //使用JmsListener配置消费者监听的队列
    @JmsListener(destination = "queue")//目的地
    public void receiveQueueObj(String txtMsg) {
        switch (txtMsg){
            case "1":
                System.out.println("执行A系统任务");
                break;
            case "2":
                System.out.println("执行B系统任务");
                break;
            default:
                System.out.println("不执行");
                break;
        }
    }
}
