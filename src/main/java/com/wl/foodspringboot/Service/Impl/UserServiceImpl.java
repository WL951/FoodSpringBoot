package com.wl.foodspringboot.Service.Impl;

import com.wl.foodspringboot.Dao.UserDao;
import com.wl.foodspringboot.Domain.User;
import com.wl.foodspringboot.Mapper.UserMapper;
import com.wl.foodspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    UserDao userDao;

    @Autowired
    ApplicationEventPublisher publisher;//事务监听

    @Transactional
    @Override
    public boolean IsLogin(User user) {
        User oldUser = userMapper.selectUserByName(user);
        publisher.publishEvent("测试");//触发事件
        if (oldUser != null) {
            if (oldUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean selectUserByName(User user) {
        User oldUser = userDao.selectUserByName(user.getUsername());
        if (oldUser != null) {
            if (oldUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
