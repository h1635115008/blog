package cn.aftertomorrow.service;

import cn.aftertomorrow.common.request.dto.user.UserDTO;

public interface UserService {
    UserDTO findUserByName(String name);
}
