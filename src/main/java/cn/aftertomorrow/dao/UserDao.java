package cn.aftertomorrow.dao;

import cn.aftertomorrow.po.User;

public interface UserDao {
    public User findUserById(String id);
}
