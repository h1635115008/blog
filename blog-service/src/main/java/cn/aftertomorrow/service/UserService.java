package cn.aftertomorrow.service;

import cn.aftertomorrow.common.request.dto.user.UserDTO;

/**
 * 用户服务类接口
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface UserService {
    /**
     * 通过姓名查找用户
     *
     * @param name
     * @return
     */
    UserDTO findUserByName(String name);
}
