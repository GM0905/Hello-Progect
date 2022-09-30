package com.wzg.blog.base.Exception;

import com.wzg.blog.back.bean.Article;
import org.aspectj.weaver.AbstractReferenceTypeDelegate;

public enum BlogEnum {
    //验证码校验
    //第一个001是用户登录模块，第二个001是登陆中验证码错误情况
    USER_LOGIN_CODE("001-001","验证码错误"),
    USER_LOGIN_ACCOUNT("001-002","用户名或密码错误"),
    USER_VERIFY_PASS("001-003","旧密码错误") ,
    USER_VERIFY_UPDATE("001-004","密码修改错误"),
    ARTICLE_ISOPEN("002-001","修改文章状态失败"),
    ARTICLE_PUNISH("002-002","发布文章失败"),
    ARTICLE_UPDATE("002-003","修改文章失败");
    private String typecode;
    private String massage;

    BlogEnum( String typecode,String massage ) {
        this.typecode = typecode;
        this.massage = massage;
    }
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }
}
