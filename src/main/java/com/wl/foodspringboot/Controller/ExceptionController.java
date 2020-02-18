package com.wl.foodspringboot.Controller;

import com.wl.foodspringboot.Domain.FoodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  //异常注解介绍 @ControllerAdvice 如果是返回json数据 则用 RestControllerAdvice,就可以不加 @ResponseBody
public class ExceptionController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    Map<String, Object> map = new HashMap<>();

    @ExceptionHandler(value = Exception.class)
    Object exception(Exception e, HttpServletRequest request) {
        map.clear();
        map.put("code", 100);
        logger.error("异常测试",e);
        return map;
    }


    @ExceptionHandler(value = FoodException.class)
    Object myException(FoodException e, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error.html");
        modelAndView.addObject("msg",e.getMsg());
        return modelAndView;
        //返回Json数据
//        map.clear();
//        map.put("code", e.getCode());
//        map.put("msg", e.getMsg());
//        return map;
    }

}
