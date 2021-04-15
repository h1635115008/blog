package cn.aftertomorrow.dao.mapper;

import cn.aftertomorrow.dao.domain.Article;

import java.util.List;

/**
 * 文章mapper
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface ArticleMapper {
    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    int addArticle(Article article);

    /**
     * 编辑文章
     *
     * @param article
     * @return
     */
    int editArticle(Article article);

    /**
     * 删除文章
     *
     * @param ids
     * @return
     */
    int deleteArticle(Integer[] ids);

    /**
     * 获取所有文章
     *
     * @return
     */
    List<Article> listAll();

    /**
     * 通过文章id获取文章
     *
     * @param id
     * @return
     */
    Article findArticleById(Integer id);

    /**
     * 通过标签获取文章
     *
     * @return
     */
    List<Article> listAllWithTag();

    /**
     * 通过状态获取文章
     *
     * @return
     */
    List<Article> listAllWithStatus();

    /**
     * 获取文章数量
     *
     * @return
     */
    int getArticleSize();

    /**
     * 增加文章阅读量
     *
     * @param id
     * @return
     */
    int addArticleViewCount(Integer id);

    /**
     * 通过关键字查找文章
     *
     * @param keywords
     * @return
     */
    List<Article> searchByKeywords(String keywords);
}
