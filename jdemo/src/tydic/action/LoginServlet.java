package tydic.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tydic.model.DbDao;

import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;



public class LoginServlet extends HttpServlet {
	//响应客户端请求
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		//Servlet转发请求
		RequestDispatcher rd;
		
		String errMsg = "";
		//获取请求参数
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		if(username != null && username.equals("ftwwpm")) {
			//OK
			//getServletContext().getRequestDispatcher("/test.jsp").forward(request,response);	
			
			//获取tomcat容器下面其他的APP呢？可行否？
			ServletContext otherContext = request.getSession().getServletContext().getContext("/ftwwpm");
			otherContext.getRequestDispatcher("/ftwwpm").forward(request,response);	
			return;
		}
		
		try {
			//Servlet本身并不执行任何业务逻辑处理，它调用JavaBean处理用户请求
			
			//MySQL数据库
			//DbDao dd = DbDao.instance("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/db","root","123456");
			//Oracle数据库
			DbDao dd = DbDao.instance("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.42.11.34:1521:orcl","cidmon","cidmon");
			//查询结果集
			ResultSet rs = dd.query("select PASSWORD from t_sys_user where username='"+username+"'");
			if(rs.next()) {
				String _pwd = rs.getString("password");
				//用户名和密码匹配
				if(_pwd != null && _pwd.equals(pwd)) {
					//获取session对象
					HttpSession session = request.getSession(true);
					//设置session属性，跟踪用户会话状态
					session.setAttribute("name",username);
					session.setAttribute("user",username);
					
					//第一种：通过request转发请求，服务器内部跳转，浏览器的URL地址不变化
//					rd = request.getRequestDispatcher("/pages/welcome.jsp");
//					rd.forward(request,response);
					
					//第二种：通过response.sendRedirect，二次请求，浏览器的URL地址会变化（即：既可以重新转发请求到当前应用，也可以转发请求到其他web应用）
					response.sendRedirect(request.getContextPath() + "/pages/welcome.jsp");
					
					//第三种方式：直接向浏览器返回输出流
//					PrintStream out = new PrintStream(response.getOutputStream());
//					out.print("<html>");
//					out.print("<head>");
//					out.print("<title>Servlet直接输出页面流</title>");
//					out.print("</head>");
//					out.print("<body>");
//					out.print("<div><h1>ccc</h1></div>");
//					out.print("</body>");
//					out.print("</html>");
					
				} else {
						errMsg = "您的用户名和密码不相符，请重新输入";
				}
				
			} else {
					errMsg = "您的用户名不存在，请先注册";
			}
			
		} catch(Exception e) {
				e.printStackTrace();
				rd = request.getRequestDispatcher("/pages/errorPage.jsp");
				System.out.println("LoginServlet：登录异常了...跳转到/errorPage.jsp");
				request.setAttribute("ex",e);
				rd.forward(request,response);
		}
		
		if(errMsg!=null && !errMsg.equals("")) {
			rd = request.getRequestDispatcher("/pages/login.jsp");
			request.setAttribute("err",errMsg);
			rd.forward(request,response);
		}
		//request.getServletContext()，Since Servlet3.0，而tomcat6只支持到Servlet2.5
		//旧版本需要先用request拿到HttpSession或者通过Servlet自身拿到ServletConfig之后再获取ServletContext

		System.out.println("getRealPath(\"/\"): "+request.getSession().getServletContext().getRealPath("/"));
		System.out.println("=====================");
	}
}
