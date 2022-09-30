package com.wzg.blog.back.controller;

import com.wzg.blog.back.bean.User;
import com.wzg.blog.back.service.UserService;
import com.wzg.blog.base.Exception.BlogException;
import com.wzg.blog.base.bean.ResultVO;
import com.wzg.blog.base.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired

    private UserService userService;
    @RequestMapping("/back/user/login")
    @ResponseBody
    //验证码校验
    public ResultVO login(User user, String code, HttpSession session){
        ResultVO resultVO=new ResultVO();
   String rightCode=(String) session.getAttribute("code");
   try{
     user = userService.login(user,code,rightCode);
      resultVO.setOk(true);
       session.setAttribute("user",user);
   }catch (BlogException e){
       resultVO.setMess(e.getMessage());
   }
return resultVO;
    }
    @RequestMapping("/view/index")
    public String toindex(){
        return "/view/index";
    }

    @RequestMapping("/user/loginOut")
     public String loginOut(HttpSession session){
        //清除session，重定向到登录页面
        session.removeAttribute("user");
         return "redirect:/login.jsp";
     }

     //异步校验旧密码
    @RequestMapping("/user/verifyOldPwd")
    @ResponseBody
    public ResultVO verifyOldPwd(String oldPwd ,HttpSession session ){
        ResultVO resultVO=new ResultVO();
            User user =(User) session.getAttribute("user");
        try{
           userService.verifyOldPwd(oldPwd,user);
            resultVO.setOk(true);
            session.setAttribute("user",user);
        }catch (BlogException e){
            resultVO.setMess(e.getMessage());
        }
        return resultVO;
    }
    @RequestMapping("/user/updateUser")
    @ResponseBody
    public ResultVO updateUser(User user ){
        ResultVO resultVO=new ResultVO();

        try{
          userService.updateUser(user);
            resultVO.setOk(true);
            resultVO.setMess("修改用户信息成功");
        }catch (BlogException e){
            resultVO.setMess(e.getMessage());
        }
        return resultVO;
    }
}
