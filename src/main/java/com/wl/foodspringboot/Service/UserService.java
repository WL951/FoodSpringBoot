package com.wl.foodspringboot.Service;

import com.wl.foodspringboot.Domain.User;

public interface UserService {
    public boolean IsLogin(User user);

    public boolean selectUserByName(User user);
}
