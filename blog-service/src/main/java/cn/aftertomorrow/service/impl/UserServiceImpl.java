package cn.aftertomorrow.service.impl;

import cn.aftertomorrow.common.request.dto.user.UserDTO;
import cn.aftertomorrow.common.util.JavaBeanUtils;
import cn.aftertomorrow.dao.domain.UserDO;
import cn.aftertomorrow.dao.mapper.UserMapper;
import cn.aftertomorrow.service.UserService;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务类实现类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findUserByName(String name) {
        UserDO user = userMapper.findUserByName(name);
        UserDTO userDTO = null;
        if (ObjectUtil.isNotEmpty(user)) {
            userDTO = JavaBeanUtils.copyPropertiesToObject(user, UserDTO.class);
        }
        return userDTO;
    }
}
