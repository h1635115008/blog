package cn.aftertomorrow.dao.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文章
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class Article {
    private Integer id;
    private String title;
    private String summary;
    private String text;
    private String tag;
    private String code;
    private String keywords;
    private Integer view;
    private Date time;
    private String name;
    private String unicode;
    private Integer status;
}
