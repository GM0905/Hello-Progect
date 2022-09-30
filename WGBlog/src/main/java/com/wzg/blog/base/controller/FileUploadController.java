package com.wzg.blog.base.controller;

import com.wzg.blog.back.bean.User;
import com.wzg.blog.base.utils.DateTimeUtil;
import com.wzg.blog.base.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {
    //解决Editermd文件上传
    @RequestMapping("/editorUpload")
    @ResponseBody
    public Map<String,Object> editorUpload(@RequestParam(value = "editormd-image-file",required = false)MultipartFile img,
                                           HttpSession session){
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile img,HttpSession session){
        //img必须要跟页面上的name一样，不然会找不到数据
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }
}
