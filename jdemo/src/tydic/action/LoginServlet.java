package tydic.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tydic.model.DbDao;
import java.sql.ResultSet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;



public class LoginServlet extends HttpServlet {
	//响应客户端请求
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		//Servlet本身并不输出响应到客户端，因此必须将请求转发
		RequestDispatcher rd;
		
		String errMsg = "";
		//获取请求参数
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		if(username!=null && username.equals("SFA")) {
			//OK
			//getServletContext().getRequestDispatcher("/test.jsp").forward(request,response);	
			
			//获取tomcat容器下面其他的APP呢？可行否？
			request.getSession().getServletContext().getContext("/SFA_APP").getRequestDispatcher("/SFA_APP").forward(request,response);	
			return;
		}
		
		try {
			//Servlet本身并不执行任何业务逻辑处理，它调用JavaBean处理用户请求
			
			//MySQL数据库
			//DbDao dd = DbDao.instance("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/db","root","123456");
			//Oracle数据库
			DbDao dd = DbDao.instance("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@HAWK:orcl","emcd","emcd");
			//查询结果集
			ResultSet rs = dd.query("select pwd from test where username='"+username+"'");
			if(rs.next()) {
				//用户名和密码匹配
				if(rs.getString("pwd").equals(pwd)) {
					//获取session对象
					HttpSession session = request.getSession(true);
					//设置session属性，跟踪用户会话状态
					session.setAttribute("name",username);
					session.setAttribute("user",username);
					//获取转发对象
					rd = request.getRequestDispatcher("/welcome.jsp");
					//转发请求
					rd.forward(request,response);
					
				} else {
						errMsg = "您的用户名和密码不相符，请重新输入";
				}
				
			} else {
					errMsg = "您的用户名不存在，请先注册";
			}
			
		} catch(Exception e) {
				rd = request.getRequestDispatcher("/errorPage.jsp");
				System.out.println("LoginServlet：登录异常了...跳转到/errorPage.jsp");
				request.setAttribute("ex",e);
				rd.forward(request,response);
		}
		
		if(errMsg!=null && !errMsg.equals("")) {
			rd = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("err",errMsg);
			rd.forward(request,response);
		}
		//request.getServletContext()，Since Servlet3.0，而tomcat6只支持到Servlet2.5
		//旧版本需要先用request拿到HttpSession或者通过Servlet自身拿到ServletConfig之后再获取ServletContext

		System.out.println("getRealPath(\"/\"): "+request.getSession().getServletContext().getRealPath("/"));
	}
}
