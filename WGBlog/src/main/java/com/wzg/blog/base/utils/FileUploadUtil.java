package com.wzg.blog.base.utils;

import com.wzg.blog.back.bean.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUploadUtil {
    //上传Md的图片
    public static   Map<String,Object>fileUpload(@RequestParam(value = "editormd-image-file",required = false) MultipartFile img,
                                        HttpSession session){
        //准备上传图片保存在upload目录下，还有子目录upload/时间/用户名/很多图片
        String realPath = session.getServletContext().getRealPath("/upload");
        realPath+= File.separator+ DateTimeUtil.getDate();
        //获取用户登录
        User user = (User) session.getAttribute("user");
        realPath+=File.separator+user.getUsername();
        File file=new File(realPath);
        if(!file.exists()){
            //创建带层级的目录 mkdir：只能创建一级目录
            file.mkdirs();
        }
        //相同用户可能会上传相同文件名的图片
        //获取用户名
        String fileName = img.getOriginalFilename();
        fileName=System.currentTimeMillis()+fileName;
        //定义用于给Editormd返回的map数据
        Map<String,Object>map=new HashMap<>();
        //获取回调地址
        String url="http://localhost:8080/WGBlog/upload/"+
                DateTimeUtil.getDate()+File.separator+user.getUsername()+File.separator+fileName;
        try {
            img.transferTo(new File(realPath+File.separator+fileName));
            //返回success：1（数字）url：图片回调地址（图片在服务器存储路径）
            map.put("success",1);
            map.put("url",url);
            map.put("message","上传图片成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }
}
