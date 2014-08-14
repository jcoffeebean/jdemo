<%@ page contentType="text/html; charset=gbk" language="java" errorPage="errorPage.jsp"%>
<html>
<head>
<title>连接数据库</title>
</head>
<body>
<%
//注册数据库驱动
Class.forName ("oracle.jdbc.driver.OracleDriver");
//获取数据库连接
java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@HAWK:1521:orcl" , "video" , "video");
//创建Statement
java.sql.Statement stmt = conn.createStatement();
//执行查询
java.sql.ResultSet rs = stmt.executeQuery("select * from jg where rownum<6");
%>
<table bgcolor="9999dd" border="1">
<%
//历结果集
while(rs.next()){
%>
<tr>
<td><%=rs.getString(1)%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(4)%></td>
</tr>
<br>
<%
}
rs.close();
stmt.close();
conn.close();
%>
</table>
测试
</body>
</html>