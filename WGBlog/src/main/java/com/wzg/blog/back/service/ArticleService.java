package com.wzg.blog.back.service;

import com.wzg.blog.back.bean.Article;
import com.wzg.blog.back.bean.Category;
import com.wzg.blog.back.bean.Tag;

import java.util.List;

public interface ArticleService {

    List<Article> list(String uid, String title);

    void changeIsOpen(Article article);
    List<Category> queryByCategory();

    List<Tag> queryTages(String cid);

    Article  saveOrUpdate(Article article);

    Article queryById(String id);

    void deleteById(String id);
}
