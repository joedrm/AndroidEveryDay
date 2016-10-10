package com.wdy.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by wdy on 16/9/22.
 */
public class DBUtils {

    public static String driverClass;
    public static String url;
    public static String username;
    public static String password;

    static{
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dbinfo");
        driverClass = resourceBundle.getString("driverClass");
        url = resourceBundle.getString("url");
        username = resourceBundle.getString("username");
        password = resourceBundle.getString("password");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到Connection对象
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/wdy2","root","root");
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 关闭资源
     */
    public static void closeAll(ResultSet set, Connection conn, Statement st){
        if (set !=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
