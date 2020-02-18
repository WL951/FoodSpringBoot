package com.wl.foodspringboot.Controller;

import com.wl.foodspringboot.Domain.User;
import com.wl.foodspringboot.Service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class UserController {

    @Resource
    UserService userService;

    private Map<String,Object> map=new HashMap<>();

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Map<String,Object> login(User user) {
        map.clear();
        boolean flag = userService.IsLogin(user);
        boolean flagxml = userService.selectUserByName(user);
        map.put("flag", flag);
        map.put("flagxml", flag);
        return map;
    }
}
