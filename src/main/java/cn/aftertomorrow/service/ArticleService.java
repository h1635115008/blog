package cn.aftertomorrow.service;

import java.util.List;
import java.util.Map;

import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.Page;
import cn.aftertomorrow.po.SerachItem;

public interface ArticleService {
    public int addArticle(Article article);

    public int editArticle(Article article);

    public int deleteArticle(Integer[] ids);

    public List<Article> listAll();

    public Article findArticleById(Integer id);

    public List<Article> findByPage(Page page);

    public int size();

    public List<SerachItem> serach(String keywords);


    public Map<String, List<Article>> getAticleOrderByYears();

    public Map<String, List<Article>> getAticleOrderByTags();

}
