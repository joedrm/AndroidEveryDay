package com.wdy.warper;

/**
 * Created by wdy on 16/9/23.
 */
import com.wdy.warper.MyConnectionWarper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * 适配器模式
 */
public class MyConnection extends MyConnectionWarper {
    private Connection oldConn;
    private LinkedList<Connection> pool;
    public MyConnection(Connection oldConn,LinkedList<Connection> pool) {
        super(oldConn);
        this.oldConn = oldConn;
        this.pool = pool;
    }
    @Override
    public void close() throws SQLException {
        pool.addLast(oldConn);
    }
}

