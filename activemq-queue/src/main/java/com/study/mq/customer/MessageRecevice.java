package com.study.mq.customer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 接受activeMQ 频道为queue的消息
 */
@Component
public class MessageRecevice {

    //使用JmsListener配置消费者监听的队列
    @JmsListener(destination = "queue")//目的地
    public void receiveQueueObj(String txtMsg) {
        //模拟系统执行方式
        switch (txtMsg){//判别条件
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
