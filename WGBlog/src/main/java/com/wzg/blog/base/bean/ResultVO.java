package com.wzg.blog.base.bean;

import lombok.Data;

@Data
public class ResultVO<T> {
    //验证码校验
    private String mess;//给客户端的消息
    private boolean isOk;//用户操作是否成功
    private T t;//返回对象数据
}
