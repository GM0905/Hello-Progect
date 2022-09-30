package com.wzg.blog.back.service.impl;

import com.wzg.blog.back.bean.Article;
import com.wzg.blog.back.bean.Category;
import com.wzg.blog.back.bean.Tag;
import com.wzg.blog.back.mapper.ArticleMapper;
import com.wzg.blog.back.mapper.CategoryMapper;
import com.wzg.blog.back.mapper.TagMapper;
import com.wzg.blog.back.service.ArticleService;
import com.wzg.blog.base.Exception.BlogEnum;
import com.wzg.blog.base.Exception.BlogException;
import com.wzg.blog.base.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
  private ArticleMapper articleMapper;
@Autowired
private CategoryMapper categoryMapper;
@Autowired
private TagMapper tagMapper;
    @Override
    //模糊查询
    public List<Article> list(String uid, String title) {
        Example example=new Example(Article.class);
        Example.Criteria criteria=example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);
        if (title!=null && !title.equals("")){
           criteria.andLike("title","%"+title+"%");
        }

        List<Article> articles = articleMapper.selectByExample(example);
        for (Article article:articles){
            String cid=article.getCid();
            Category category = categoryMapper.selectByPrimaryKey(cid);
            article.setCid(category.getCname());
        }
        return articles;
    }

    @Override
    public void changeIsOpen(Article article) {
       int count= articleMapper.updateByPrimaryKeySelective(article);
        if(count==0){
            throw new BlogException(BlogEnum.ARTICLE_ISOPEN);
        }
    }

    @Override
    public List<Category> queryByCategory() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Tag> queryTages(String cid) {
        Tag tag=new Tag();
        tag.setCid(cid);
        return tagMapper.select(tag);
    }

    @Override
    public Article saveOrUpdate(Article article) {
        if(article.getAid()==null){
            //点赞数
            article.setThumbsUp("0");
            //是否热门
            article.setIs_hot("0");
            //访问量
            article.setVisit_count("0");
            //发布时间
            article.setCreate_time(DateTimeUtil.getSysTime());
            //是否被评论
            article.setIsCommented("0");
            int count = articleMapper.insertSelective(article);
            if (count== 0){
                throw new BlogException(BlogEnum.ARTICLE_PUNISH);
            }
        }else{
            article.setCreate_time(DateTimeUtil.getSysTime());
    int count= articleMapper.updateByPrimaryKeySelective(article);
    if(count==0){
        //修改失败
        throw new BlogException(BlogEnum.ARTICLE_UPDATE);
    }
        }

        return article;
    }

    @Override
    public Article queryById(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

        @Override
        public void deleteById(String id) {
            int count = articleMapper.deleteByPrimaryKey(id);
            if(count == 0){
                throw new BlogException(BlogEnum.ARTICLE_PUNISH);
            }
        }

}
