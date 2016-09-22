package com.wdy.service.impl;

import com.wdy.com.wdy.exception.UserExistExcetion;
import com.wdy.dao.UserDao;
import com.wdy.dao.impl.UserDaoImpl;
import com.wdy.domin.User;
import com.wdy.service.UserService;

/**
 * Created by wdy on 16/9/20.
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();
    @Override
    public void register(User user) throws Exception {
        userDao.addUser(user);
    }

    @Override
    public User login(User user) {
        User u = null;
        try {
            u = userDao.findUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean findUserByName(String username) throws Exception {
        Boolean isExist = userDao.findUserByName(username);
        if (isExist){
            throw new UserExistExcetion("用户已存在");
        }
        return isExist;
    }
}
