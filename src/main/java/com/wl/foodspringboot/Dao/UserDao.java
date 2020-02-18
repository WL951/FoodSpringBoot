package com.wl.foodspringboot.Dao;

import com.wl.foodspringboot.Domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    public User selectUserByName(String username);  //通过application.properties配置的mybatis.mapper-locations属性查找对应mapper.xml.文件中id="selectUserByName",执行对应的sql
}
