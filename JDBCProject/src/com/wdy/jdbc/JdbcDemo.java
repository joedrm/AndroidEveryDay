package com.wdy.jdbc;

import com.wdy.jdbc.com.wdy.entity.User;
import com.wdy.jdbc.com.wdy.util.DBUtils;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by wdy on 16/9/21.
 */
public class JdbcDemo {

    @Test
    public void test1() throws Exception {
        /** 步骤:
         * 1、注册驱动
         * 2、创建连接
         * 3、得到执行sql语句的Statement对象
         * 4、执行sql语句，并返回结果
         * 5、处理结果
         * 6、关闭资源
         */

        /**
         * DriverManager.registerDriver(new com.mysql.jdbc.Driver());不建议使用
         * 原因有2个：
         *      导致驱动被注册2次。
         *      强烈依赖数据库的驱动jar
         * 解决办法：
         *      Class.forName("com.mysql.jdbc.Driver");
         */
        Class.forName("com.mysql.jdbc.Driver");
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        // JDBC连接的三种方式
        // 第一种方式
        // jdbc是协议, mysql表示是mysql数据库, localhost:IP地址 3306数据库端口, root用户名, root密码
        // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdy2","root","root");

        // 第二种方式
        /*
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdy2",info);
        */

        // 第三种方式
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdy2?user=root&password=root");

        Statement statement = conn.createStatement();

        /** java.sql.Statement接口: 操作sql语句，并返回相应结果的对象
         *  常用的三个方法:
         *  ResultSet executeQuery(String sql) 根据查询语句返回结果集。只能执行select语句。
         *  int executeUpdate(String sql) 根据执行的DML（insert update delete）语句，返回受影响的行数。
         *  boolean execute(String sql)  此方法可以执行任意sql语句。返回boolean值，表示是否返回ResultSet结果集。
         *      仅当执行select语句，且有返回结果时返回true, 其它语句都返回false.
         */

        // ResultSet结果集
        ResultSet set = statement.executeQuery("SELECT * FROM users");
        // 处理结果
        while (set.next()) {
            System.out.println(set.getObject("id"));
            System.out.println(set.getObject("name"));
            System.out.println(set.getObject("password"));
            System.out.println(set.getObject("email"));
            System.out.println(set.getObject("birthday"));
            System.out.println("-----------------------");
        }
        set.close();
        conn.close();
        statement.close();
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdy2?user=root&password=root");
            statement = conn.createStatement();
            set = statement.executeQuery("SELECT * FROM users");
            while (set.next()) {
                System.out.println(set.getObject("id"));
                System.out.println(set.getObject("name"));
                System.out.println(set.getObject("password"));
                System.out.println(set.getObject("email"));
                System.out.println(set.getObject("birthday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test3() {
        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            conn = DBUtils.getConnection();
            statement = conn.createStatement();
            set = statement.executeQuery("SELECT * FROM users");
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