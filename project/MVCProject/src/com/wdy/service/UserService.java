package com.wdy.service;

import com.wdy.com.wdy.exception.UserExistExcetion;
import com.wdy.domin.User;

/**
 * Created by wdy on 16/9/20.
 */
public interface UserService {

    /**
     * 添加用户信息
     * @param user
     * @throws Exception
     */
    public void register(User user) throws Exception;

    public User login(User user);

    public boolean findUserByName(String username) throws Exception;
}
