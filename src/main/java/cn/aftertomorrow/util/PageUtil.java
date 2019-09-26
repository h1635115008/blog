package cn.aftertomorrow.util;

import cn.aftertomorrow.po.Article;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    public static List<Article> getArticle(List<Article> allArticle, int start, int size) {
        List<Article> article = new ArrayList<>();
        for (int i = start; i < start + size && i < allArticle.size(); i++) {
            article.add(allArticle.get(i));
        }
        return article;
    }
}
