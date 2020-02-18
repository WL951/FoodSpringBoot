package com.wl.foodspringboot.JmsConsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommonConsumer {

    @JmsListener(destination = "com.foodspringboot.queue")
    public void receiveQueue(String text){
        System.out.println("收到Queue消息(点对点)："+text);
    }


    @JmsListener(destination = "com.foodspringboot.toptic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopticone(String text){
        System.out.println("收到Toptic消息(发布-订阅)1："+text);
    }

    @JmsListener(destination = "com.foodspringboot.toptic",containerFactory="jmsListenerContainerTopic")
    public void receiveToptictwo(String text){
        System.out.println("收到Toptic消息(发布-订阅)2："+text);
    }
}
