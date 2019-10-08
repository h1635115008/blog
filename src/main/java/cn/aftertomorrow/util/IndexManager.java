package cn.aftertomorrow.util;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexManager {
        private static String INDEX_PATH = "C:\\Users\\ideapad\\workspace\\lucene\\index";
        private static IndexWriter indexWriter;
        static {
            try {
                indexWriter = new IndexWriter(FSDirectory.open(new File(INDEX_PATH).toPath()), new IndexWriterConfig(new IKAnalyzer()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void createIndex() throws IOException {
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
//            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);
            Field fieldSize = new LongPoint("size", fileSize);
            Field fieldStore = new StoredField("size", fileSize);
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

    public static void addDocument() throws IOException {
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

    public static void deleteALLDocument() throws IOException {
        indexWriter.deleteAll();
        destory();
    }

    public static void deleteDocumentByTerm() throws IOException {
        indexWriter.deleteDocuments(new Term("name", "name"));
        destory();
    }

    public static void deleteDocumentByQuery() throws IOException {
//        indexWriter.deleteDocuments();
//        indexWriter.close();
    }

    public static void destory() throws IOException {
        indexWriter.close();
    }
}
