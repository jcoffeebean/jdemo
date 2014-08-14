package tydic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class GetConnectionListener implements ServletContextListener {

	//应用启动时该方法被调用
	public void contextInitialized(ServletContextEvent sce) {
		try {
			//取得应用的ServletContext实例
			ServletContext application = sce.getServletContext();
			String driver = application.getInitParameter("driver");
			String url = application.getInitParameter("url");
			String user = application.getInitParameter("user");
			String pwd = application.getInitParameter("pwd");
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pwd);
			//将Connection设置为application范围的属性
			application.setAttribute("conn",conn);
			
		} catch(Exception e) {
				System.out.println("Listener中获取连接异常："+e.getMessage());
		}
	}	
	
	//应用关闭时，该方法被调用
	public void contextDestroyed(ServletContextEvent sce) {
		//
		ServletContext application = sce.getServletContext();
		Connection conn = (Connection)application.getAttribute("conn");	
		//关闭数据库连接
		if(conn!=null) {
			try {
				conn.close();
			} catch(SQLException ex) {
					ex.printStackTrace();
			}
		}
	}
	
}
