<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ page import="java.sql.*"%>
<html>
	<head><title>application����</title><head>
	<body>
		application������javax.servlet.ServletContex��ʵ��������WebӦ�ñ�������WebӦ�ù���һ��application����
		<br>
		<!-- JSP���� -->
	  <%!
	  	int i;
	  %>
		<!-- ��i�ԼӺ����application�ı�������-->
		<% 
		   
		   application.setAttribute("counter",String.valueOf(++i));		   
		%>
		<%=i%><br>
		<%=application.getAttribute("counter")%>
		
		<br>
		<!-- application�����web.xml�ļ��л�ȡ���� ʹ��context-paramԪ������ -->
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
			//��������
			out.println("<tr>");
			//�����
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
	