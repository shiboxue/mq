package com.study.mq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * 消息产生者
 */
@Controller
public class PriverController {
    private final static Logger log = LoggerFactory.getLogger(PriverController.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String test1(String msg){
        jmsMessagingTemplate.convertAndSend("queue",msg);
        log.info("消息已经发送");
        return "b";
    }
}
