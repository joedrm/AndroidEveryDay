package com.wdy.dao;

import com.wdy.domin.User;

/**
 * Created by wdy on 16/9/20.
 */
public interface UserDao {

    /**
     * 添加用户信息
     * @param user
     * @throws Exception
     */
    public void addUser(User user) throws Exception;

    /**
     * 登录
     * @param user
     * @throws Exception
     */
    public User findUser(User user) throws Exception;

    /**
     * 根据用户名查找用户是否存在
     * @param username
     * @return
     * @throws Exception
     */
    public Boolean findUserByName(String username) throws Exception;
}
