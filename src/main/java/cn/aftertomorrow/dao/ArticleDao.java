package cn.aftertomorrow.dao;

import java.util.List;

import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.Page;
import cn.aftertomorrow.po.SerachItem;

public interface ArticleDao {
    public int addArticle(Article article);

    public int editArticle(Article article);

    public int deleteArticle(Integer[] ids);

    public List<Article> listAll();

    public Article findArticleById(Integer id);

    public List<Article> findByPage(Page page);

    public List<Article> listAllWithTag();

    public List<Article> listAllWithStatus();

    public int size();

    public int addView(Integer id);

    public List<SerachItem> serach(String keywords);
}
