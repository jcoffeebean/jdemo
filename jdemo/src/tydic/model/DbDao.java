package tydic.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class DbDao {
	
	//将JavaBean作成单例模式
	private static DbDao dd;
	private Connection conn;
	private String username;
	private String pwd;
	private String url;
	private String driver;
	
	//私有构造器
	private DbDao() {
	}
	
		//私有构造器
	private DbDao(String driver,String url,String username,String pwd) throws Exception {
		this.url = url;
		this.driver = driver;
		this.username = username;
		this.pwd = pwd;
		Class.forName(driver);
		conn = DriverManager.getConnection(url,username,pwd);
	
	}
	//实例化Bean的入口
	public static DbDao instance(String driver,String url,String username,String pwd) throws Exception {
		if(dd == null) {
			dd = new DbDao(driver,url,username,pwd);
		}
		return dd;
	}

	//获取连接
	public void getConnection() throws Exception {
		if(conn == null) {
			Class.forName(this.driver);
			DriverManager.getConnection(this.driver,this.username,this.pwd);
		}
		
	}
	
	//执行查询
	public ResultSet query(String sql) throws Exception {
		getConnection();
		Statement st = conn.createStatement();
		return st.executeQuery(sql); 	
	}
	//执行删除
	public void delete(String sql) throws Exception {
		getConnection();
		Statement st = conn.createStatement();
		st.executeUpdate(sql);
	}
	
	//各个成员的getter和setter属性
	
}
