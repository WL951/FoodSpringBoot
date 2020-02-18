package com.wl.foodspringboot;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@SpringBootApplication
//@MapperScan("com.wl.foodspringboot.Dao") 如果Mapper类添加了注解@Mapper，就不用这句了
@ServletComponentScan
//@EnableJms  //开启支持jms java消息服务
public class FoodSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodSpringBootApplication.class, args);
    }

//    @Bean  //交给Spring进行管理，方便后续进行注入  (注释掉，把它放在工具包下的ActiveMQ类加载)
//    public Queue queue(){
//        return new ActiveMQQueue("com.foodspringboot.queue");
//    }


}
