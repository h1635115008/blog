package cn.aftertomorrow.common.request.dto.user;

import lombok.Data;

/**
 * 用户DTO
 *
 * @author huangming
 * @date 2021/04/13
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
}
