package com.wl.foodspringboot.Utils;

import com.wl.foodspringboot.RedisMessageConsumer.ReceiverRedisMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@Configuration
public class RedisListenerConfig {

    /**
     * 注：同时配置了监听所有的db键的过期事件和指定db键过期监听事件：同时起作用，但如果是指定外的db键失效就只有监听所有的db键的过期事件起作用
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter,MessageListenerAdapter listenerAdapterTest2) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //指定：__keyevent@0__:expired，监听指定的db键的过期事件，且只对定义类RedisListener有作用
        container.addMessageListener(new RedisListener(), new PatternTopic("__keyevent@1__:expired"));

        //发布订阅：监听对应键的事件
        container.addMessageListener(listenerAdapter,new PatternTopic("redisPubSub1"));
        container.addMessageListener(listenerAdapterTest2,new PatternTopic("redisPubSub2"));

        return container;
    }
    /**
     * 监听所有的db键的过期事件
     *  @param connectionFactory
     *      * @return
     */
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        return container;
//    }


    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage  receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");//使用类ReceiverRedisMessage的receiveMessage方法接受消息
    }
    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapterTest2(ReceiverRedisMessage  receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage2");//使用类ReceiverRedisMessage的receiveMessage2方法接受消息
    }
    /**
     * 注册订阅者
     * @param latch
     * @return
     */
    @Bean
    ReceiverRedisMessage receiver(CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }


    /**
     * 计数器，用来控制线程
     * @return
     */
    @Bean
    public CountDownLatch latch(){
        return new CountDownLatch(1);//指定了计数的次数 1
    }


}
