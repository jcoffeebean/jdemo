<%@ page contentType="text/html; charset=gbk" language="java" errorPage="errorPage.jsp"%>
<html>
<head>
<title>�������ݿ�</title>
</head>
<body>
<%
//��ȡͨ��������ע���application��Χ�ڵ�Connection
java.sql.Connection conn = (java.sql.Connection)application.getAttribute("conn");
//����Statement
java.sql.Statement stmt = conn.createStatement();
//ִ�в�ѯ
java.sql.ResultSet rs = stmt.executeQuery("select * from all_objs where rownum <6");
%>
<table bgcolor="9999dd" border="1">
<%
//�������
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
����
</body>
</html>