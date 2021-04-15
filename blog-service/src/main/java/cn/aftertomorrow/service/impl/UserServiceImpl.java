package cn.aftertomorrow.service.impl;

import cn.aftertomorrow.common.request.dto.user.UserDTO;
import cn.aftertomorrow.dao.domain.User;
import cn.aftertomorrow.dao.mapper.UserMapper;
import cn.aftertomorrow.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findUserByName(String name) {
        UserDTO userDTO = new UserDTO();
        User user = userMapper.findUserByName(name);
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
