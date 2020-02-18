package com.wl.foodspringboot.Service.Impl;

import com.wl.foodspringboot.Service.ProducerService;
import com.wl.foodspringboot.Utils.ActiveMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;  //用来发送消息到broker的的对象

    @Autowired
    private Queue queue;//默认目的队列  在工具包下面的ActiveMQ类

    /**
     * Queue 发送消息
     * @param destination 目的队列
     * @param message 待发送的消息内容
     */
    @Override
    public void sendMessageQueue(Destination destination, String message) {
        if(destination==null) {
            jmsMessagingTemplate.convertAndSend(this.queue, message);
        }else {
            jmsMessagingTemplate.convertAndSend(destination, message);
        }

    }


    @Autowired
    private Topic topic;//默认目的队列  在工具包下面的ActiveMQ类
    /**
     * Toptic 发送消息
     * @param destination 目的队列
     * @param message 待发送的消息内容
     */
    @Override
    public void sendMessageToptic(Destination destination, String message) {
        if(destination==null) {
            jmsMessagingTemplate.convertAndSend(this.topic, message);
        }else {
            jmsMessagingTemplate.convertAndSend(destination, message);
        }

    }
}
