package com.wl.foodspringboot.Controller;

import com.wl.foodspringboot.Domain.FoodException;
import com.wl.foodspringboot.Domain.User;
import com.wl.foodspringboot.Service.ProducerService;
import com.wl.foodspringboot.Utils.JsonUtils;
import com.wl.foodspringboot.Utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/test")
@PropertySource({"classpath:resource.properties"}) //加载配置资源文件
public class TestController {

    /**
     * 文件上传
     */
    @Value("${file.uploadFolder}")
    private String filePath;

    private Map<String,Object> map=new HashMap<>();

    //文件上传
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadTest(@RequestParam("head_img") MultipartFile file, HttpServletRequest request)
    {
        //file.isEmpty(); 判断图片是否为空
        //file.getSize(); 图片大小进行判断

        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名,比如图片的jpeg,png
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName;

        try {
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "false";

    }

    /**
     * 单元测试
     * @return
     */
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Map<String,Object>  test() {
        map.clear();
        map.put("flag", "true");
        return map;
    }

    /**
     * 自定义异常
     * @return
     */

    @RequestMapping(value = "/tsetException",method = RequestMethod.GET)
    public String tsetException() {
        try {
            int i = 1 / 0;
        }catch (Exception e){
            throw new FoodException("500",e.getMessage());
        }
        return "true";
    }

    /**
     *日志打印
     */
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/testlog",method = RequestMethod.GET)
    public String tsetlog() {
        logger.debug("this debug level log");
        logger.info("this debug info log");
        logger.warn("this debug warn log");
        logger.error("this debug error log");
        return "true";
    }


    /**
     * 测试异常日志打印 若用ExceptionController里的exception方法（全局异常）捕获异常,则不会显示error级别对应的错误日志，只会有warn级别对应的日志
     * @return
     */
    @RequestMapping(value = "/testlog2",method = RequestMethod.GET)
    public String testlog2() {
        int i=1/0;
        return "true";
    }


    /**
     * Redis
     * @return
     */
    @Autowired
    private RedisClient redisClient; //jdbcTemplate
    //缓存和读取数据
    @RequestMapping(value = "/testRedis",method = RequestMethod.GET)
    public User testRedis(User user) {
        redisClient.set("FoodSpringBoot:user:"+String.valueOf(user.getUserid()), JsonUtils.objToString(user));
        System.out.println(redisClient.get("FoodSpringBoot:user:"+String.valueOf(user.getUserid())));
        return user;
    }
    //redis实现发布订阅功能
    @RequestMapping(value = "/redisPubSub",method = RequestMethod.GET)
    public User redisPubSub(User user) {
        redisClient.convertAndSend("redisPubSub1",JsonUtils.objToString(user));
        redisClient.convertAndSend("redisPubSub2",JsonUtils.objToString(user));
        return user;
    }

    /**
     * ActiveMQ
     */
    @Autowired
    ProducerService producerService;
    //生产者消费者：一对一
    @RequestMapping(value = "/testActiveMQQueue",method = RequestMethod.GET)
    public String testActiveMQQueue(String msg){
        producerService.sendMessageQueue(null,msg);
        return "true";
    }
    //发布订阅
    @RequestMapping(value = "/testActiveMQToptic",method = RequestMethod.GET)
    public String testActiveMQToptic(String msg){
        producerService.sendMessageToptic(null,msg);
        return "true";
    }
}
