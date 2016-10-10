package com.wdy.pool;

import com.wdy.util.DBUtils;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by wdy on 16/9/23.
 * 模拟数据库连接池的实现
 */
public class SimpleConnectionPool {

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

    // 得到链接,从pool中拿到coon
    public Connection getConnectionFromPool(){
        Connection coon = null;
        if (pool.size()>0){
            coon = pool.removeFirst();
        }else {
            /**
             * 此时解决方案: 等待 或者 重新创建一个
             */
            throw new RuntimeException("服务器忙");
        }
        return coon;
    }

    // 释放链接,添加到pool中
    public void release(Connection coon){
        pool.addLast(coon);
    }
}
