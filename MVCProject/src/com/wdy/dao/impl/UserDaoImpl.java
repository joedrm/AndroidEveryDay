package com.wdy.dao.impl;

import com.wdy.dao.UserDao;
import com.wdy.domin.User;
import com.wdy.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * Created by wdy on 16/9/20.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("INSERT INTO users(name,password,email,brithday) VALUES(?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleFormatter.format(user.getBirthday());
            ps.setString(4, dateStr);

            int i = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }finally {
            DBUtils.closeAll(null,conn,ps);
        }
    }

    @Override
    public User findUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        User user1 = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT *FROM users WHERE name=? AND password=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            set = ps.executeQuery();
            if (set.next()){
                user1 = new User();
                user1.setId(set.getInt("id"));
                user1.setUsername(set.getNString("name"));
                user1.setPassword(set.getNString("password"));
                user1.setEmail(set.getNString("email"));
                user1.setBirthday(set.getDate("birthday"));

                System.out.println(user1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(set,conn,ps);
        }
        return user1;
    }

    @Override
    public Boolean findUserByName(String username) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT *FROM users WHERE name=?");
            ps.setString(1, username);

            set = ps.executeQuery();
            if (set.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(set,conn,ps);
        }
        return false;
    }
}
