<%@ page contentType="text/html; charset=gbk" language="java" errorPage="errorPage.jsp"%>
<html>
<head>
<title>连接数据库</title>
</head>
<body>
<%
//获取通过监听器注册的application范围内的Connection
java.sql.Connection conn = (java.sql.Connection)application.getAttribute("conn");
//创建Statement
java.sql.Statement stmt = conn.createStatement();
//执行查询
java.sql.ResultSet rs = stmt.executeQuery("select * from all_objs where rownum <6");
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
%>
</table>
测试
</body>
</html>