package tydic.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
//import javax.servlet.jsp.PageContext;
import java.io.IOException;
import javax.servlet.http.HttpServlet;

public class GetAppServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//过滤器Filter放行请求/getcounter后的目的地: GetAppServlet.service()方法
		System.out.println("过滤器Filter放行请求/getcounter后的目的地: GetAppServlet.service()方法");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>");
		out.println("测试application");
		out.println("</title></head><body>");
		ServletContext sc = getServletConfig().getServletContext();
		out.print ("application中当前的counter值为:" );
		out.println(sc.getAttribute("counter"));
		out.println("</body></html>");
	}

}
