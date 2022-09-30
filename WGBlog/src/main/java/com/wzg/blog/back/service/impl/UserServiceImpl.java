package com.wzg.blog.back.service.impl;

import com.wzg.blog.back.bean.User;
import com.wzg.blog.back.mapper.UserMapper;
import com.wzg.blog.back.service.UserService;
import com.wzg.blog.base.Exception.BlogEnum;
import com.wzg.blog.base.Exception.BlogException;
import com.wzg.blog.base.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //验证码校验
    @Autowired
    private UserMapper userMapper;

  /*  public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;   == @Autowired
    }*/
    @Override
    public User login(User user, String code,String rightCode) {
        if(!rightCode.equals(code)){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
        //校验用户名和密码是否正确
        String password=user.getPassword();
        //MD5加密
        password=MD5Util.getMD5(password);
        user.setPassword(password);
        List<User> users = userMapper.select(user);
        if(users.size()==0){
            throw new BlogException(BlogEnum.USER_LOGIN_ACCOUNT);
        }
        return users.get(0);
    }

    @Override
    public void verifyOldPwd(String oldPwd, User user) {
        oldPwd=MD5Util.getMD5(oldPwd);
        String password=user.getPassword();
        if(!password.equals(oldPwd)){
            throw new BlogException(BlogEnum.USER_VERIFY_PASS);
        }
    }

    @Override
    public void updateUser(User user) {
        //用户新密码加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        int count=userMapper.updateByPrimaryKey(user);
        if(count==0){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
    }
}
