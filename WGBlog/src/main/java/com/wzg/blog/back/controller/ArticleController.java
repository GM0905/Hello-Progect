package com.wzg.blog.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.webkit.graphics.WCImageDecoder;
import com.wzg.blog.back.bean.Article;
import com.wzg.blog.back.bean.Category;
import com.wzg.blog.back.bean.Tag;
import com.wzg.blog.back.bean.User;
import com.wzg.blog.back.service.ArticleService;
import com.wzg.blog.base.Exception.BlogException;
import com.wzg.blog.base.bean.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/Article/list")
    @ResponseBody
    public PageInfo<Article>list(int page, int pageSize, String title,HttpSession session){
        //获取当前登录用户
      User user= (User) session.getAttribute("user");
        //参数一：当前页码  //参数2：每页记录数
        PageHelper.startPage(page,pageSize);
      List<Article> articles= articleService.list(user.getUid(),title);
        PageInfo<Article> objectPageInfo =new PageInfo<>(articles);

        return objectPageInfo;
    }
    @RequestMapping("/Article/changeIsOpen")
    @ResponseBody
    //异步修改是否公开
    public ResultVO changeIsOpem(Article article){
        ResultVO resultVO=new ResultVO();
        try{
            articleService.changeIsOpen(article);
            resultVO.setOk(true);
            if(article.getIsOpen().equals("0")){
                resultVO.setMess("文章已经私密");
            }else {
                resultVO.setMess("文章已经公开");
            }
        }catch (BlogException e){
            resultVO.setMess(e.getMessage());
        }
        return resultVO;
    }

    //异步查询所有栏目
    @RequestMapping("/Article/queryCategory")
    @ResponseBody
public List<Category>queryByCategory(){
 List<Category>categories= articleService.queryByCategory();
 return categories;
}

    @RequestMapping("/Article/queryTags")
    @ResponseBody
    public List<Tag>queryTags(String cid){
       List<Tag> tags=articleService.queryTages(cid);
       return tags;
    }
    @RequestMapping("/Article/saveOrUpdate")
    @ResponseBody
    //异步修改是否公开
    public ResultVO saveOrUpdate(Article article, HttpSession session){
        ResultVO resultVO=new ResultVO();
        try{
            User user=(User) session.getAttribute("user");
            article.setUid(user.getUid());
              article=articleService.saveOrUpdate(article);
            resultVO.setOk(true);
            if(article.getAid()==null){
                resultVO.setMess("发布成功");
            }else {
                resultVO.setMess("修改成功");
            }
            resultVO.setT(article);

        }catch (BlogException e){
            resultVO.setMess(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/Article/queryById")
    @ResponseBody
    public Article queryById(String id){
        Article article = articleService.queryById(id);
        return article;
    }

    //异步删除文章
    @RequestMapping("/Article/deleteArticle")
    @ResponseBody
    public ResultVO deleteArticle(String id){
        ResultVO resultVo = new ResultVO();
        try {
            articleService.deleteById(id);
            resultVo.setOk(true);
            resultVo.setMess("删除文章成功");
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }
}
