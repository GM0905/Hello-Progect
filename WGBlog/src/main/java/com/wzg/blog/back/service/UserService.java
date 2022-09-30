package com.wzg.blog.back.service;

import com.wzg.blog.back.bean.User;

public interface UserService {
    //验证码校验
    User login(User user, String code,String rightCode);

    void verifyOldPwd(String oldPwd,User user);
    void updateUser(User user);
}
