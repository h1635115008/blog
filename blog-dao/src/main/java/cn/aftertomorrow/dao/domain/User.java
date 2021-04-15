package cn.aftertomorrow.dao.domain;

import lombok.Data;

/**
 * 用户
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
}
