package cn.aftertomorrow.dao.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author huangming
 * @className BaseDO
 * @description
 * @date 2021/4/20
 */
@Data
public class BaseDO {
    private Integer id;
    private String gmtCreate;
    private String gmtModified;
}
