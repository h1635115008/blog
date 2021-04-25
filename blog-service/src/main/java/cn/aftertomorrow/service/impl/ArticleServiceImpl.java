package cn.aftertomorrow.service.impl;

import java.io.IOException;
import java.util.*;

import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import cn.aftertomorrow.common.transfer.FieldRule;
import cn.aftertomorrow.common.transfer.ValueParser;
import cn.aftertomorrow.common.util.JavaBeanUtils;
import cn.aftertomorrow.common.util.StringUtils;
import cn.aftertomorrow.dao.domain.ArticleDO;
import cn.aftertomorrow.dao.mapper.ArticleMapper;
import cn.aftertomorrow.manager.IndexManager;
import cn.aftertomorrow.service.ArticleService;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
        List<ArticleDO> articleList = articleMapper.listAll();

        List<FieldRule> fieldRules = Lists.newArrayList(
                FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_YMD)
        );
        return JavaBeanUtils.copyPropertiesToList(articleList, ArticleDTO.class, fieldRules);
    }

    @Override
    public ArticleDTO findArticleById(Integer id, Boolean isAddView) {
        if (isAddView) {
            articleMapper.addArticleViewCount(id);
        }

        ArticleDO article = articleMapper.findArticleById(id);

        List<FieldRule> fieldRules = Lists.newArrayList(
                FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_YMD)
        );
        return JavaBeanUtils.copyPropertiesToObject(article, ArticleDTO.class, fieldRules);
    }

    @Override
    public PageInfo<ArticleDTO> findByPage(Integer pageNum, Integer size) {
        Page<ArticleDTO> page = PageHelper.startPage(pageNum, size);

        List<ArticleDO> articles = articleMapper.listAllWithStatus();

        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(page);
        List<FieldRule> fieldRules = Lists.newArrayList(
                FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_YMD)
        );
        pageInfo.setList(JavaBeanUtils.copyPropertiesToList(articles, ArticleDTO.class, fieldRules));
        return pageInfo;
    }

    @Override
    public int addArticle(ArticleDTO article) {
        if (StringUtils.notEmpty(article.getTag(), article.getSummary(), article.getTitle())) {
            return articleMapper.addArticle(JavaBeanUtils.copyPropertiesToObject(article, ArticleDO.class));
        } else {
            return 0;
        }
    }

    @Override
    public int editArticle(ArticleDTO article) {
        return articleMapper.editArticle(JavaBeanUtils.copyPropertiesToObject(article, ArticleDO.class));
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
        return indexManager.searchArticle(query);
    }

    @Override
    public Map<String, List<ArticleDTO>> getArticleCollectionByYear() {
        Map<String, List<ArticleDTO>> articleCollection = new TreeMap<>(Comparator.reverseOrder());
        List<ArticleDO> articles = articleMapper.listAllWithStatus();
        articles.forEach(e -> {
            List<FieldRule> fieldRules = Lists.newArrayList(
                    FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_MD)
            );
            List<ArticleDTO> articleList;
            String year = String.valueOf(DateUtil.parseDate(e.getGmtCreate()).getYear() + 1900);
            if (null == articleCollection.get(year)) {
                articleList = new ArrayList<>();
                articleList.add(JavaBeanUtils.copyPropertiesToObject(e, ArticleDTO.class, fieldRules));
                articleCollection.put(year, articleList);
            } else {
                articleList = articleCollection.get(year);
                articleList.add(JavaBeanUtils.copyPropertiesToObject(e, ArticleDTO.class, fieldRules));
            }
        });
        return articleCollection;
    }

    @Override
    public Map<String, List<ArticleDTO>> getArticleCollectionByTag() {
        Map<String, List<ArticleDTO>> articleCollection = new HashMap<>();
        List<ArticleDO> articles = articleMapper.listAllWithTag();
        articles.forEach(e -> {
            List<ArticleDTO> articleList;
            List<FieldRule> fieldRules = Lists.newArrayList(
                    FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_YMD)
            );
            if (null == articleCollection.get(e.getName())) {
                articleList = new ArrayList<>();
                articleList.add(JavaBeanUtils.copyPropertiesToObject(e, ArticleDTO.class, fieldRules));
                articleCollection.put(e.getName(), articleList);
            } else {
                articleList = articleCollection.get(e.getName());
                articleList.add(JavaBeanUtils.copyPropertiesToObject(e, ArticleDTO.class, fieldRules));
            }
        });
        return articleCollection;
    }

    @Override
    public List<ArticleDTO> listAllWithStatus() {
        return JavaBeanUtils.copyPropertiesToList(articleMapper.listAllWithStatus(), ArticleDTO.class);
    }
}
