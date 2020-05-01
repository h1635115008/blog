package cn.aftertomorrow.service;

import java.io.IOException;
import java.util.*;

import cn.aftertomorrow.exception.InsertException;
import cn.aftertomorrow.po.SearchItem;
import cn.aftertomorrow.util.Util;
import net.sf.ehcache.CacheManager;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.aftertomorrow.dao.ArticleDao;
import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.Page;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    CacheManager cacheManager;
    IndexReader indexReader;
    IndexSearcher indexSearcher;

    public List<Article> listAll() {
        return this.articleDao.listAll();
    }

    public Article findArticleById(Integer id) {
        articleDao.addView(id);
        return this.articleDao.findArticleById(id);
    }

    public List<Article> findByPage(Page page) {
        return this.articleDao.findByPage(page);
    }

    public int addArticle(Article article) {
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
        logger.info("size() is invoked");
        return articleDao.size();
    }

    @Override
    @Cacheable(value = "result")
    public List<SearchItem> search(String keywords) throws ParseException, IOException, InvalidTokenOffsetsException {
        logger.info("search() is invoked");
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        Query query = queryParser.parse(QueryParser.escape(keywords));
        List<SearchItem> searchItems = Util.search(query, indexSearcher);
        return searchItems;
    }

    @Override
    public Map<String, List<Article>> getArticleOrderByYears() {
        logger.info("getArticleOrderByYears() is invoked");
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
    public Map<String, List<Article>> getArticleOrderByTags() {
        logger.info("getArticleOrderByTags() is invoked");
        Map<String, List<Article>> aticleOrderByTags = new HashMap<>();
        List<Article> articles = articleDao.listAllWithTag();
        for (Article article : articles) {
            String tagName = String.valueOf(article.getName());
            Util.order(aticleOrderByTags, tagName, new ArrayList(), article);
        }
        return aticleOrderByTags;
    }

    @Override
    @PostConstruct
    public void initIndex() throws IOException {
        IndexWriter indexWriter = new IndexWriter(Util.articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        List<Article> articles = articleDao.listAll();
        for (Article article : articles) {
            Document document = new Document();
            Field fieldId = new StoredField("id", article.getId());
            Field fieldTitle = new TextField("title", article.getTitle(), Field.Store.YES);
            document.add(fieldId);
            document.add(fieldTitle);
            indexWriter.addDocument(document);
        }
        indexWriter.close();
        indexReader = DirectoryReader.open(Util.articleIndexDirectory());
        indexSearcher = new IndexSearcher(indexReader);
    }

    @PreDestroy
    public void destroy() throws IOException {
        IndexWriter indexWriter = new IndexWriter(Util.articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteAll();
        indexWriter.close();
        indexReader.close();
    }
}
