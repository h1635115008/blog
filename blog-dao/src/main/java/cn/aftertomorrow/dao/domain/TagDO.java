package cn.aftertomorrow.dao.domain;

import lombok.Data;

/**
 * 标签
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class TagDO extends BaseDO {
    private String name;
    private String kind;
    private String unicode;
}
