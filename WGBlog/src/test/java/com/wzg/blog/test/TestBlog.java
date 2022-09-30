package com.wzg.blog.test;

import com.wzg.blog.back.bean.User;
import com.wzg.blog.base.Exception.BlogEnum;
import com.wzg.blog.base.Exception.BlogException;
import org.junit.Test;
import com.wzg.blog.back.mapper.UserMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class TestBlog {
    @Test
    public void test01(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserMapper userMapper =(UserMapper) beanFactory.getBean("userMapper");
        User user=new User();
        user.setUsername("333");
        user.setPassword("123");
        userMapper.insert(user);
    }

    @Test
    public void test02(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserMapper userMapper =(UserMapper) beanFactory.getBean("userMapper");
        User user=new User();
        user.setUid("1");
        user.setPassword("admin");
        //根据主键添加：updateByPrimaryKeySelective
        userMapper.updateByPrimaryKeySelective(user);
    }
    //测试tkmapper的删除
    @Test
    public void test03(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserMapper userMapper =(UserMapper) beanFactory.getBean("userMapper");
        //根据主键删除：deleteByPrimaryKey
     // userMapper.deleteByPrimaryKey(2);
        /*只能进行等值的拼接删除，做不了模糊删除*/
       /*User user=new User();
       user.setUid("5");
       userMapper.delete(user);*/
        Example example=new Example(User.class);
        //大于或者等于andGreaterThanOrEqualTo
        example.createCriteria().andGreaterThanOrEqualTo("uid",3);
       userMapper.deleteByExample(example);
    }
    //查询操作
    @Test
    public void test04(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserMapper userMapper =(UserMapper) beanFactory.getBean("userMapper");
        User user=new User();
        user.setUid("1");
       // userMapper.selectOne(user);
      /*  List<User> users = userMapper.selectAll();
        System.out.println(users);*/
        Example example=new Example(User.class);
        example.createCriteria().andLike("password","123");
        userMapper.selectByExample(example);
    }

    @Test
    public void test05(){
      int a=0;
      try{
          if(a==0){
              throw new BlogException(BlogEnum.USER_LOGIN_CODE);
          }
      }catch(BlogException e){
          System.out.println(e.getMessage());
      }

    }
}
