package cn.aftertomorrow.dao.mapper;

import cn.aftertomorrow.dao.domain.ArticleDO;

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
    int addArticle(ArticleDO article);

    /**
     * 编辑文章
     *
     * @param article
     * @return
     */
    int editArticle(ArticleDO article);

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
    List<ArticleDO> listAll();

    /**
     * 通过文章id获取文章
     *
     * @param id
     * @return
     */
    ArticleDO findArticleById(Integer id);

    /**
     * 通过标签获取文章
     *
     * @return
     */
    List<ArticleDO> listAllWithTag();

    /**
     * 通过状态获取文章
     *
     * @return
     */
    List<ArticleDO> listAllWithStatus();

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
    List<ArticleDO> searchByKeywords(String keywords);
}
