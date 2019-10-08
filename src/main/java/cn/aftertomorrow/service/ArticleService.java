package cn.aftertomorrow.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.Page;
import cn.aftertomorrow.po.SearchItem;
import org.apache.lucene.queryparser.classic.ParseException;

public interface ArticleService {
    public int addArticle(Article article);

    public int editArticle(Article article);

    public int deleteArticle(Integer[] ids);

    public List<Article> listAll();

    public Article findArticleById(Integer id);

    public List<Article> findByPage(Page page);

    public int size();

    public List<SearchItem> search(String keywords) throws Exception;


    public Map<String, List<Article>> getArticleOrderByYears();

    public Map<String, List<Article>> getArticleOrderByTags();

    public void initIndex() throws IOException;
}
