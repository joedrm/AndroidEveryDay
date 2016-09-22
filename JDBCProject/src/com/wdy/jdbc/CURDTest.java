package com.wdy.jdbc;

import com.wdy.jdbc.com.wdy.entity.User;
import com.wdy.jdbc.com.wdy.util.DBUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wdy on 16/9/21.
 */
public class CURDTest {

    /**
     * 插入数据
     */
    @Test
    public void insert(){
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.getConnection();
            statement = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?)");
            statement.setInt(1,8);
            statement.setString(2,"tom");
            statement.setString(3,"333");
            statement.setString(4,"tom@163.com");
            statement.setString(5,"2013-02-22");

            int i = statement.executeUpdate();
//            while (i>0) {
//                System.out.println("sucess");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, conn, statement);
        }
    }

    /**
     * 更新
     * @throws Exception
     */
    @Test
    public void update(){
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DBUtils.getConnection();
            statement = conn.createStatement();
            int set = statement.executeUpdate("UPDATE users SET NAME ='jerry',password='333',email='jerry@163.com' WHERE id=4");
            while (set>0) {
                System.out.println("sucess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, conn, statement);
        }
    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void delete(){
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DBUtils.getConnection();
            statement = conn.createStatement();
            int set = statement.executeUpdate("DELETE FROM users WHERE id=4");
            while (set>0) {
                System.out.println("sucess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, conn, statement);
        }
    }

    /**
     * 查询
     */
    @Test
    public void select(){
        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            conn = DBUtils.getConnection();
            statement = conn.createStatement();
            set = statement.executeQuery("SELECT password,email,birthday,id,name FROM users");
            List<User> list = new ArrayList<>();
            while (set.next()) {
                User user = new User();
                user.setId(set.getInt("id"));
                user.setName(set.getString("name"));
                user.setPassword(set.getString("password"));
                user.setEmail(set.getString("email"));
                user.getBirthday(set.getDate("birthday"));
                list.add(user);
            }
            for (User user : list) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(set, conn, statement);
        }
    }

}
