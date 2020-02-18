package com.wl.foodspringboot.Utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms  // 开启支持jms java消息服务
public class ActiveMQ {

    /**
     * 点对点模式 Queue
     * @return
     */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("com.foodspringboot.queue");
    }

    /**
     * 发布-订阅模式 Toptic
     * @return
     */
    @Bean
    public Topic topic()
    {
        return new ActiveMQTopic("com.foodspringboot.toptic");
    }


    //需要给topic定义独立的JmsListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
