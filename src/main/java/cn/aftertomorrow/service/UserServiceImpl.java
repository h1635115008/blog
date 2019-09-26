package cn.aftertomorrow.service;

import cn.aftertomorrow.dao.UserDao;
import cn.aftertomorrow.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }
}
