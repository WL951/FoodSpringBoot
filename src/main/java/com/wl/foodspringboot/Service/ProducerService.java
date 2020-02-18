package com.wl.foodspringboot.Service;

import javax.jms.Destination;

/**
 * 消息生产者
 */
public interface ProducerService {

    /**
     * 点对点模式
     * @param destination 目的队列
     * @param message 消息内容
     */
    public void sendMessageQueue(Destination destination,final String message);


    /**
     * 发布-订阅模式
     * @param destination 目的队列
     * @param message 消息内容
     */
    public void sendMessageToptic(Destination destination,final String message);

}
