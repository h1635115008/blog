package cn.aftertomorrow.dao.domain;

import lombok.Data;

/**
 * @author huangming
 * @className ArticleComment
 * @description
 * @date 2021/4/20
 */
@Data
public class ArticleCommentDO extends BaseDO {
    private String name;
    private String email;
    private String words;
    private Integer articleId;
    private Integer parentId;
}
