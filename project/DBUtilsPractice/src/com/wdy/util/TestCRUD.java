package com.wdy.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

public class TestCRUD {

	/**
	 * 查询
	 * @throws SQLException
	 */
	@Test
	public void testSelect() throws SQLException{
		QueryRunner rn = new QueryRunner(C3P0Util.getDataSource());
		List<User> list= rn.query("SELECT *FROM users WHERE id=?",
				new BeanListHandler<User>(User.class),4);
		for (User user:list){
			System.out.println(user);
		}
	}

	/**
	 * 插入操作
	 */
	@Test
	public void testInsert() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into users(name,password,email,birthday) values(?,?,?,?)",
				"wooper","222","c10@163.com",new Date());
	}

	/**
	 * 更新操作
	 * @throws SQLException
     */
	@Test
	public void testUpdate() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update users set name=?,password=? where id=?", "lattry","333",15);
	}

	/**
	 * 删除操作
	 * @throws SQLException
     */
	@Test
	public void testDelete() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("delete from users where id=?", 15);
	}

	/**
	 * 批量执行(批处理)
	 * 只能执行相同的sql语句
	 * @throws SQLException
     */
	@Test
	public void testBatch() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[][] params = new Object[10][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{"lattry"+i,"123","c10@163.com",new Date()};
		}
		qr.batch("insert into users(name,password,email,birthday) values(?,?,?,?)", params );
	}

	/**
	 * ArrayHandler:适合取一条记录,
	 * 把该记录的每列值封装到一个数组中,Object[] arr
	 * @throws SQLException
     */
	@Test
	public void arrHandlerTest() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[] arr = qr.query("SELECT *FROM users WHERE id=?", new ArrayHandler(), 1);
		for(Object obj : arr){
			System.out.println(obj);
		}
	}

	/**
	 * ArrayListHandler:适合取多条记录。把每条记录的每列值封装到一个数组中Object[]，把数组封装到一个List中
	 * @throws SQLException
     */
	@Test
	public void arrListHandlerTest() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Object[]> query = qr.query("SELECT * FROM users", new ArrayListHandler());

		for (Object[] os : query) {
			for (Object o : os) {
				System.out.println(o);
			}
			System.out.println("--------------");
		}
	}

	/**
	 * ColumnListHandler:取某一列的数据。封装到List中。
	 * @throws SQLException
	 */
	@Test
	public void columnListHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Object> query = qr.query("SELECT name, password FROM users", new ColumnListHandler(1));

		for (Object obj : query) {
			System.out.println(obj);
			System.out.println("--------------");
		}
	}

	/**
	 * KeyedHandler:取多条记录，每一条记录封装到一个Map中，再把这个Map封装到另外一个Map中，key为指定的字段值。
	 * @throws SQLException
	 */
	@Test
	public void keyedHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Map<Object, Map<String, Object>> map = qr.query("SELECT * FROM users", new KeyedHandler(1));

		for (Map.Entry<Object, Map<String,Object>> m : map.entrySet()) {
			System.out.println(m.getKey());//大Map中key值就是id列的值
			for (Map.Entry<String, Object> mm : m.getValue().entrySet()) {
				System.out.println(mm.getKey()+"\t"+mm.getValue());//取出小Map中的列名和列值
			}
			System.out.println("---------------------");
		}
	}

	/**
	 * mapHandler:适合取1条记录。把当前记录的列名和列值放到一个Map中
	 * @throws SQLException
	 */
	@Test
	public void mapHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Map<String, Object> map = qr.query("SELECT * FROM users WHERE id=?", new MapHandler(), 6);

		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
	}

	/**
	 * MapListHandler:适合取多条记录。把每条记录封装到一个Map中，再把Map封装到List中
	 * @throws SQLException
	 */
	@Test
	public void mapListHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Map<String, Object>> list = qr.query("SELECT * FROM users", new MapListHandler());

		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+"\t"+m.getValue());
			}
			System.out.println("---------------");
		}
	}

	/**
	 * ScalarHandler:适合取单行单列数据
	 * @throws SQLException
	 */
	@Test
	public void scalarHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object obj = qr.query("SELECT COUNT(*) FROM users", new ScalarHandler(1));

		System.out.println(obj);
		System.out.println("---------------");

	}

	/**
	 * BeanHandler:适合取单行数据
	 * @throws SQLException
     */
	@Test
	public void beanHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		User user = qr.query("select * from users where id=?", new BeanHandler<User>(User.class),1);
		System.out.println(user);
	}


	@Test //BeanListHandler
	public void beanListHandler() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list = qr.query("select * from users where id=?", new BeanListHandler<User>(User.class),1);

		System.out.println(list.size());
	}

}
