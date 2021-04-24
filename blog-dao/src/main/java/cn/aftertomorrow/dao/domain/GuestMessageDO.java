package cn.aftertomorrow.dao.domain;

import lombok.Data;

/**
 * 留言
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class GuestMessageDO extends BaseDO {
    private String name;
    private String email;
    private String words;
    private Integer parentId;
}
