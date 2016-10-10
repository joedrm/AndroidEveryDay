package com.wdy.jdbc;

import com.wdy.entity.User;
import com.wdy.util.C3P0Util;
import com.wdy.util.DBCPUtil;
import com.wdy.util.DBUtils;
import com.wdy.datasource.MyDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * DBUtils的使用
     */
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
//                User user = new User();
//                user.setId(set.getInt("id"));
//                user.setName(set.getString("name"));
//                user.setPassword(set.getString("password"));
//                user.setEmail(set.getString("email"));
//                user.getBirthday(set.getDate("birthday"));
//                list.add(user);
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

    /**
     * 未使用事务之前
     */
    @Test
    public void test4(){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("UPDATE users SET password=999 WHERE name='wdy'");
            ps.executeUpdate();

            int i= 10/0; // 模拟程序执行中断后出现数据错误

            ps = conn.prepareStatement("UPDATE users SET password=999 WHERE name='tom'");
            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(null,conn,ps);
        }

    }

    /**
     * 使用事务之后,
     */
    @Test
    public void test5(){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            // TRANSACTION_REPEATABLE_READ  mysql的默认级别
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            conn.setAutoCommit(false);// 开启事务
            ps = conn.prepareStatement("UPDATE users SET password=666 WHERE name='wdy'");
            ps.executeUpdate();

            int i= 10/0; // 模拟程序执行中断后,数据都没有改变

            ps = conn.prepareStatement("UPDATE users SET password=666 WHERE name='tom'");
            ps.executeUpdate();

            conn.commit();// 提交事务
        } catch (Exception e) {
            try {
                conn.rollback();// 回滚事务
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(null,conn,ps);
        }
    }
    /**
     * 数据库连接池的使用, 装饰设计模式的使用
     */
    public void test6(){
        DataSource dataSource = new MyDataSource();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 此时这里不能直接关闭, 应该放入数据库连接池(pool),
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * DBCP实践
     */
    public void test7(){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBCPUtil.getConnection();
            ps = conn.prepareStatement("...");

//			...
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBCPUtil.release(conn, ps, null);
        }
    }

    /**
     * C3P0的使用
     */
    @Test
    public void test8(){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet set = ps.executeQuery();
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
        }finally{
            C3P0Util.release(conn, ps, null);
        }
        System.out.println(conn.getClass().getName());
    }
}