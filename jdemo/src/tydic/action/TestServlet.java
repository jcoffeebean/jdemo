package tydic.action;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.PrintStream;

public class TestServlet extends HttpServlet {
	//重写init方法
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	//处理请求
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		try {
			//获取ServletConfig对象
			ServletConfig config = getServletConfig();
			//从Servlet的配置参数中读取参数值
			String driver = config.getInitParameter("driver");
			String url = config.getInitParameter("url");
			String username = config.getInitParameter("username");
			String pwd = config.getInitParameter("pwd");
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,pwd);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select * from user_info");
			
			//获取页面输出流
			PrintStream out = new PrintStream(response.getOutputStream());
			
			out.print("<html>");
			out.print("<head>");
			out.print("<title>Servlet测试 读取配置参数，注意与application读取配置参数的区别</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("Servlet访问配置参数的方式非常类似于application方式。只是application的配置参数对整个Web应用有效，而Servlet配置参数仅对该Servlet有效");
			out.print("<table bgcolor=\"9999dd\" border=1 align=center >");
			while(rs.next()) {
				out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
			}
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
