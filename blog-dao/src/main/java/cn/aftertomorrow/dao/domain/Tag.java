package cn.aftertomorrow.dao.domain;

import lombok.Data;

/**
 * 标签
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class Tag {
    private Integer id;
    private String name;
    private String kind;
    private String unicode;
}
