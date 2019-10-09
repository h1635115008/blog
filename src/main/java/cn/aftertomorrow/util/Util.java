package cn.aftertomorrow.util;

import cn.aftertomorrow.po.SearchItem;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Util {
    public static String getYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sdf.format(date);
    }

    public static String getYMDHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return sdf.format(date);
    }

    public static String getYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }

    public static void order(Map sthOrderByitems, String item, List sthOrderByitem, Object sth) {
        if (sthOrderByitems.get(item) == null) {
            sthOrderByitem.add(sth);
            sthOrderByitems.put(item, sthOrderByitem);
        } else {
            ((List) sthOrderByitems.get(item)).add(sth);
        }
    }

    public static boolean notEmpty(String... args) {
        for (String arg : args) {
            if (arg == null || arg.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String XSSFilter(String string) {
        Whitelist whitelist = Whitelist.relaxed();
        return Jsoup.clean(string, whitelist);
    }

    public static List<SearchItem> search(Query query, IndexSearcher indexSearcher) throws IOException, InvalidTokenOffsetsException {
        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.ScoreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer scorer = new QueryScorer(query);
        // 准备高亮工具
        Highlighter highlighter = new Highlighter(formatter, scorer);
        List<SearchItem> searchItems = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document document = indexSearcher.doc(docID);
            SearchItem searchItem = new SearchItem();
            System.out.println("id: " + document.get("id"));
            searchItem.setId(Integer.valueOf(document.get("id")));
            System.out.println("title: " + document.get("title"));
            // 用高亮工具处理普通的查询结果,参数：分词器，要高亮的字段的名称，高亮字段的原始值
            String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
            searchItem.setTitle(hTitle);
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
            searchItems.add(searchItem);
        }
        return searchItems;
    }

    public static Directory articleIndexDirectory() throws IOException {
        return FSDirectory.open(new File(Util.class.getClassLoader().getResource("").getPath() + "/index").toPath());
    }
}
