package com.wdy.datasource;

import com.wdy.util.DBUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by wdy on 16/9/23.
 */
public class MyDataSource implements DataSource {

    private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());

    static{
        for (int i=0; i<10; i++){

            Connection coon = null;

            try {

                coon = DBUtils.getConnection();
                pool.add(coon);
            } catch (Exception e) {
                throw new ExceptionInInitializerError("初始化数据库连接失败, 请检查配置文件是否正确!");
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection coon = null;
        if (pool.size()>0){
            coon = pool.removeFirst();
            Connection myCoon = new MyConnection(coon, pool);
            return myCoon;
        }else {
            /**
             * 此时解决方案: 等待 或者 重新创建一个
             */
            throw new RuntimeException("服务器忙");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
