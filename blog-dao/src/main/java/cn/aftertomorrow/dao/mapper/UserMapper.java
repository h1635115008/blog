package cn.aftertomorrow.dao.mapper;

import cn.aftertomorrow.dao.domain.UserDO;

/**
 * 用户mapper
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface UserMapper {
    /**
     * 通过用户名查找用户
     *
     * @param name
     * @return
     */
    UserDO findUserByName(String name);
}
