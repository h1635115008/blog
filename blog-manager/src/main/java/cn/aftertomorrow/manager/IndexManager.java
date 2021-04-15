package cn.aftertomorrow.manager;

import cn.aftertomorrow.dao.domain.Article;
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

    private static String INDEX_PATH = "C:\\Users\\ideapad\\workspace\\lucene\\index";

    private static IndexWriter indexWriter;

    static {
        try {
            indexWriter = new IndexWriter(FSDirectory.open(new File(INDEX_PATH).toPath()), new IndexWriterConfig(new IKAnalyzer()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createIndex() throws IOException {
        //1、创建一个Directory对象，指定索引保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirec tory();
        //把索引库保存在磁盘
//        Directory directory = FSDirectory.open(new File(INDEX_PATH).toPath());
        //2、 基于Directory对象创建一 个Indexwriter对象
//        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()));
        //3、读取数据库，对应每条记录创建一个文档对象。
        File dir = new File("C:\\Users\\ideapad\\workspace\\lucene\\documents");
        File[] files = dir.listFiles();
        for (File file : files) {
            String filePath = file.getPath();
            String fileName = file.getName();
            String fileContent = FileUtils.readFileToString(file, "UTF-8");
            long fileSize = FileUtils.sizeOf(file);
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
//            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldPath = new StoredField("path", filePath);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
//            Field fieldSize = new TextField("getArticleSize", fileSize + "", Field.Store.YES);
            Field fieldSize = new LongPoint("getArticleSize", fileSize);
            Field fieldStore = new StoredField("getArticleSize", fileSize);
            //4、向文档对象中添加域
            Document document = new Document();
            document.add(fieldContent);
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldSize);
            document.add(fieldStore);
            //5、把文档对象写入索引库
            indexWriter.addDocument(document);
        }
        //6、关闭indexWriter对象
        destory();
    }

    public void addDocument() throws IOException {
        //创建-个Indexwriter对象，需要使用IKAnalyzer作为分析器
//        IndexWriter indexWriter =
//                new IndexWriter(FSDirectory.open(new File(INDEX_PATH).toPath()),
//                        new IndexWriterConfig(new IKAnalyzer()));
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
        document.add(new TextField("name", "新添加的文件 ", Field.Store.YES));
        document.add(new TextField("content ", "新添加的文件内容", Field.Store.NO));
        document.add(new StoredField("path", ""));
        //把文档写入索引库
        indexWriter.addDocument(document);
        //关闭索引库
        destory();

    }

    public void deleteALLDocument() throws IOException {
        indexWriter.deleteAll();
        destory();
    }

    public void deleteDocumentByTerm() throws IOException {
        indexWriter.deleteDocuments(new Term("name", "name"));
        destory();
    }

    public void deleteDocumentByQuery() throws IOException {
//        indexWriter.deleteDocuments();
//        indexWriter.close();
    }

    public static void destory() throws IOException {
        indexWriter.close();
    }

    public List<Article> search(Query query) throws IOException, InvalidTokenOffsetsException {
        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
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
        List<Article> articles = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document document = indexSearcher.doc(docID);
            Article article = new Article();
            System.out.println();
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

    public Directory articleIndexDirectory() throws IOException {
        return FSDirectory.open(new File(IndexManager.class.getClassLoader().getResource("").getPath() + "/index").toPath());
    }

    @PostConstruct
    public void initIndex() throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        List<Article> articles = articleMapper.listAll();
        for (Article article : articles) {
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
    }

    @PreDestroy
    public void destroy() throws IOException {
        IndexWriter indexWriter = new IndexWriter(articleIndexDirectory(), new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteAll();
        indexWriter.close();
        indexReader.close();
    }
}
