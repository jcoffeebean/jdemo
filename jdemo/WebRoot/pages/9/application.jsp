<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ page import="java.sql.*"%>
<html>
	<head><title>application测试</title><head>
	<body>
		application对象是javax.servlet.ServletContex的实例；代表Web应用本身，整个Web应用共享一个application对象
		<br>
		<!-- JSP声明 -->
	  <%!
	  	int i;
	  %>
		<!-- 将i自加后放入application的变量里面-->
		<% 
		   
		   application.setAttribute("counter",String.valueOf(++i));		   
		%>
		<%=i%><br>
		<%=application.getAttribute("counter")%>
		
		<br>
		<!-- application对象从web.xml文件中获取参数 使用context-param元素配置 -->
		<% 
		String driver = application.getInitParameter("driver");
		String url = application.getInitParameter("url");
		String user = application.getInitParameter("user");
		String pwd = application.getInitParameter("pwd");
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,pwd);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select sysdate from dual");
		%>
		<table bgcolor="9999dd" border="1" align="center">
		<%
		while(rs.next()){
			//输出表格行
			out.println("<tr>");
			//输出列
			out.write("<td>");
			out.print(rs.getDate(1)+" "+rs.getTime(1));
			out.print("</td>");
			out.write("</tr>");
		}
		
		rs.close();
		stmt.close();
		conn.close();
		%>
		</table>
	</body>
</html>
	