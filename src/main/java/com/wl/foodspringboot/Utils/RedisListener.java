package com.wl.foodspringboot.Utils;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 实现MessageListener接口，自定义Redis过期监听事件。指定：__keyevent@0__:expired，监听指定的db键的过期事件
 */
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 建议使用: valueSerializer
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        //Redis数据的键
        String redisId = new String(body);

        System.out.println("onMessage >> " );
        System.out.println(String.format("channel: %s \n body: %s \n bytes: %s"
                ,new String(channel), new String(body), new String(bytes)));

    }
}
