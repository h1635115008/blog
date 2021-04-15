package cn.aftertomorrow.service;

import java.util.List;
import java.util.Map;

import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import com.github.pagehelper.PageInfo;

/**
 * 文章服务类接口
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface ArticleService {
    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    int addArticle(ArticleDTO article);

    /**
     * 编辑文章
     *
     * @param article
     * @return
     */
    int editArticle(ArticleDTO article);

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
    List<ArticleDTO> listAll();

    /**
     * 通过文章id获取文章
     *
     * @param id
     * @return
     */
    ArticleDTO findArticleById(Integer id);

    /**
     * 分页查找
     *
     * @param pageNum
     * @param size
     * @return
     */
    PageInfo<ArticleDTO> findByPage(Integer pageNum, Integer size);

    /**
     * 获取文章数量
     *
     * @return
     */
    int getArticleSize();

    /**
     * 通过关键字搜索文章
     *
     * @param keywords
     * @return
     * @throws Exception
     */
    List<ArticleDTO> searchByKeywords(String keywords) throws Exception;

    /**
     * 通过年份对文章分组
     *
     * @return
     */
    Map<String, List<ArticleDTO>> getArticleCollectionByYear();

    /**
     * 通过标签对文章分组
     *
     * @return
     */
    Map<String, List<ArticleDTO>> getArticleCollectionByTag();
}
