package com.wl.foodspringboot.Mapper;

import com.wl.foodspringboot.Domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 访问数据库的接口
 */
@Mapper
@Repository
public interface UserMapper {

    //推荐使用#{}取值，不要用${}取值，因为存在注入的风险
    @Select("select * from food_user where username=#{username}")
    User selectUserByName(User user);
}
