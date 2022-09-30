package com.wzg.blog.base.Exception;

public class BlogException extends RuntimeException{
    //验证码校验
    private BlogEnum blogEnum;

    public BlogException(BlogEnum blogEnum) {
        super(blogEnum.getMassage());
        this.blogEnum = blogEnum;
    }
}
