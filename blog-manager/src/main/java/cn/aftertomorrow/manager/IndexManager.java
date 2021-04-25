package cn.aftertomorrow.manager;

import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import cn.aftertomorrow.dao.domain.ArticleDO;
import cn.aftertomorrow.dao.mapper.ArticleMapper;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 索引管理
 *
 * @author huangming
 * @date 2019/09/26
 */
@Component
public class IndexManager {
    private final Logger logger = LoggerFactory.getLogger(IndexManager.class);
    @Autowired
    private ArticleMapper articleMapper;

    private IndexReader indexReader;

    private IndexSearcher indexSearcher;

    /**
     * 添加索引文档
     *
     * @param article
     * @throws IOException
     */
    public void addArticleDocument(ArticleDTO article) throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        Document document = new Document();
        // 向document对象中添加域
        document.add(new StoredField("id", article.getId()));
        document.add(new TextField("title", article.getTitle(), Field.Store.YES));
        //把文档写入索引库
        indexWriter.addDocument(document);
        //关闭索引库
        indexWriter.close();

    }

    /**
     * 删除所有文档
     *
     * @throws IOException
     */
    public void deleteALLDocument() throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteAll();
        indexWriter.close();
    }

    /**
     * 通过分词项删除文档
     *
     * @param field
     * @param text
     * @throws IOException
     */
    public void deleteDocumentByTerm(String field, String text) throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteDocuments(new Term(field, text));
        indexWriter.close();
    }

    /**
     * 搜索文章
     *
     * @param query
     * @return
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     */
    public List<ArticleDTO> searchArticle(Query query) throws IOException, InvalidTokenOffsetsException {
        /*搜索数据,两个参数：查询条件对象要查询的最大结果条数
        返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。*/
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 获取总条数
        long totalHits = topDocs.totalHits.value;
        logger.info("本次搜索共找到{}条数据", totalHits);
        // 获取得分文档对象（ScoreDoc）数组.ScoreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer scorer = new QueryScorer(query);
        // 准备高亮工具
        Highlighter highlighter = new Highlighter(formatter, scorer);
        List<ArticleDTO> articles = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document document = indexSearcher.doc(docID);
            ArticleDTO article = new ArticleDTO();
            logger.info("id: {}", document.get("id"));
            article.setId(Integer.valueOf(document.get("id")));
            logger.info("title: {}", document.get("title"));
            // 用高亮工具处理普通的查询结果,参数：分词器，要高亮的字段的名称，高亮字段的原始值
            String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
            article.setTitle(hTitle);
            // 取出文档得分
            logger.info("得分：{}", scoreDoc.score);
            articles.add(article);
        }
        return articles;
    }

    /**
     * 打开文件
     *
     * @return
     * @throws IOException
     */
    public Directory articleIndexDirectory() throws IOException {
        return FSDirectory.open(new File(IndexManager.class.getClassLoader().getResource("").getPath() + "/index/article").toPath());
    }

    /**
     * 初始化文章索引
     *
     * @throws IOException
     */
    @PostConstruct
    public void initArticleIndex() throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        List<ArticleDO> articles = articleMapper.listAll();
        for (ArticleDO article : articles) {
            Document document = new Document();
            Field fieldId = new StoredField("id", article.getId());
            Field fieldTitle = new TextField("title", article.getTitle(), Field.Store.YES);
            document.add(fieldId);
            document.add(fieldTitle);
            indexWriter.addDocument(document);
        }
        indexWriter.close();
        indexReader = DirectoryReader.open(articleIndexDirectory());
        indexSearcher = new IndexSearcher(indexReader);
        logger.info("lucene article index is init.");
    }

    /**
     * 销毁文章索引
     *
     * @throws IOException
     */
    @PreDestroy
    public void destroyArticleIndex() throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteAll();
        indexWriter.close();
        indexReader.close();
        logger.info("lucene article index is destroy.");
    }
}
