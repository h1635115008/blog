package cn.aftertomorrow.service.impl;

import java.io.IOException;
import java.util.*;

import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import cn.aftertomorrow.common.util.POJOUtils;
import cn.aftertomorrow.common.util.StringUtils;
import cn.aftertomorrow.dao.domain.Article;
import cn.aftertomorrow.dao.mapper.ArticleMapper;
import cn.aftertomorrow.manager.IndexManager;
import cn.aftertomorrow.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.ehcache.CacheManager;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 文章服务类实现类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    IndexManager indexManager;

    @Override
    public List<ArticleDTO> listAll() {
        List<Article> articleList = articleMapper.listAll();
        return POJOUtils.copyPropertiesToList(articleList, ArticleDTO.class);
    }

    @Override
    public ArticleDTO findArticleById(Integer id, Boolean isAddView) {
        if (isAddView) {
            articleMapper.addArticleViewCount(id);
        }
        Article article = articleMapper.findArticleById(id);
        return POJOUtils.copyPropertiesToObject(article, ArticleDTO.class);
    }

    @Override
    public PageInfo<ArticleDTO> findByPage(Integer pageNum, Integer size) {
        Page<ArticleDTO> page = PageHelper.startPage(pageNum, size);

        articleMapper.listAllWithStatus();

        return new PageInfo<>(page);
    }

    @Override
    public int addArticle(ArticleDTO article) {
        if (StringUtils.notEmpty(article.getTag(), article.getSummary(), article.getTitle())) {
            article.setTime(new Date());
            return articleMapper.addArticle(POJOUtils.copyPropertiesToObject(article, Article.class));
        } else {
            return 0;
        }
    }

    @Override
    public int editArticle(ArticleDTO article) {
        return articleMapper.editArticle(POJOUtils.copyPropertiesToObject(article, Article.class));
    }

    @Override
    public int deleteArticle(Integer[] ids) {
        return articleMapper.deleteArticle(ids);
    }


    @Override
    @Cacheable(value = "result", key = "#root.methodName")
    public int getArticleSize() {

        return articleMapper.getArticleSize();
    }

    @Override
    @Cacheable(value = "result")
    public List<ArticleDTO> searchByKeywords(String keywords) throws ParseException, IOException, InvalidTokenOffsetsException {
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        Query query = queryParser.parse(QueryParser.escape(keywords));
        return POJOUtils.copyPropertiesToList(indexManager.search(query), ArticleDTO.class);
    }

    @Override
    public Map<String, List<ArticleDTO>> getArticleCollectionByYear() {
        Map<String, List<ArticleDTO>> articleCollection = new TreeMap<>(Comparator.reverseOrder());
        List<Article> articles = articleMapper.listAllWithStatus();
        articles.forEach(e -> {
            List<ArticleDTO> articleList;
            if (null == articleCollection.get(String.valueOf(e.getTime().getYear() + 1900))) {
                articleList = new ArrayList<>();
                articleList.add(POJOUtils.copyPropertiesToObject(e, ArticleDTO.class));
                articleCollection.put(e.getName(), articleList);
            } else {
                articleList = articleCollection.get(String.valueOf(e.getTime().getYear() + 1900));
                articleList.add(POJOUtils.copyPropertiesToObject(e, ArticleDTO.class));
            }
        });
        return articleCollection;
    }

    @Override
    public Map<String, List<ArticleDTO>> getArticleCollectionByTag() {
        Map<String, List<ArticleDTO>> articleCollection = new HashMap<>();
        List<Article> articles = articleMapper.listAllWithTag();
        articles.forEach(e -> {
            List<ArticleDTO> articleList;
            if (null == articleCollection.get(e.getName())) {
                articleList = new ArrayList<>();
                articleList.add(POJOUtils.copyPropertiesToObject(e, ArticleDTO.class));
                articleCollection.put(e.getName(), articleList);
            } else {
                articleList = articleCollection.get(e.getName());
                articleList.add(POJOUtils.copyPropertiesToObject(e, ArticleDTO.class));
            }
        });
        return articleCollection;
    }

    @Override
    public List<ArticleDTO> listAllWithStatus() {
        return POJOUtils.copyPropertiesToList(articleMapper.listAllWithStatus(), ArticleDTO.class);
    }
}
