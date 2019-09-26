package cn.aftertomorrow.service;

import java.util.*;

import cn.aftertomorrow.po.SerachItem;
import cn.aftertomorrow.util.Util;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.aftertomorrow.dao.ArticleDao;
import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.Page;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    CacheManager cacheManager;

    public List<Article> listAll() {
        // TODO Auto-generated method stub
        return this.articleDao.listAll();
    }

    public Article findArticleById(Integer id) {
        // TODO Auto-generated method stub
        articleDao.addView(id);
        return this.articleDao.findArticleById(id);
    }

    public List<Article> findByPage(Page page) {
        // TODO Auto-generated method stub
        return this.articleDao.findByPage(page);
    }

    public int addArticle(Article article) {
        // TODO Auto-generated method stub
        if (Util.notEmpty(article.getTag(), article.getSummary(), article.getTitle())) {
            article.setTime(new Date());
            return articleDao.addArticle(article);
        } else {
            return 0;
        }
    }

    @Override
    public int editArticle(Article article) {
        return articleDao.editArticle(article);
    }

    @Override
    public int deleteArticle(Integer[] ids) {
        return articleDao.deleteArticle(ids);
    }


    @Override
    @Cacheable(value = "result", key = "#root.methodName")
    public int size() {
        System.out.println("size() is invoked");
        return articleDao.size();
    }

    @Override
    @Cacheable(value = "result")
    public List<SerachItem> serach(String keywords) {
        System.out.println("serach() is invoked");
        return articleDao.serach(keywords);
    }

    @Override
//    @Cacheable(value = "result", key = "#root.methodName")
    public Map<String, List<Article>> getAticleOrderByYears() {
        System.out.println("getAticleOrderByYears() is invoked");
        Map<String, List<Article>> aticleOrderByYears = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        List<Article> articles = articleDao.listAllWithStatus();
        for (Article article : articles) {
            String year = String.valueOf(article.getTime().getYear() + 1900);
            Util.order(aticleOrderByYears, year, new ArrayList(), article);
        }
        return aticleOrderByYears;
    }

    @Override
//    @Cacheable(value = "result", key = "#root.methodName")
    public Map<String, List<Article>> getAticleOrderByTags() {
        System.out.println("getAticleOrderByTags() is invoked");
        Map<String, List<Article>> aticleOrderByTags = new HashMap<>();
        List<Article> articles = articleDao.listAllWithTag();
        for (Article article : articles) {
            String tagName = String.valueOf(article.getName());
            Util.order(aticleOrderByTags, tagName, new ArrayList(), article);
        }
        return aticleOrderByTags;
    }
}
