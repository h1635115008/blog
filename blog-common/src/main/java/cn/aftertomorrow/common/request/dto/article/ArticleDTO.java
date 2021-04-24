package cn.aftertomorrow.common.request.dto.article;

import cn.aftertomorrow.common.request.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * 文章DTO
 *
 * @author huangming
 * @date 2021/04/13
 */
@Data
public class ArticleDTO extends BaseDTO {
    private Integer id;
    private String title;
    private String summary;
    private String text;
    private String tag;
    private String code;
    private String keywords;
    private Integer view;
    private String time;
    private String name;
    private String unicode;
    private Integer status;
}
