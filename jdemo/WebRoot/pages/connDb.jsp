<%@ page contentType="text/html; charset=gbk" language="java" errorPage="errorPage.jsp"%>
<html>
<head>
<title>�������ݿ�</title>
</head>
<body>
<%
//ע�����ݿ�����
Class.forName ("oracle.jdbc.driver.OracleDriver");
//��ȡ���ݿ�����
java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@HAWK:1521:orcl" , "video" , "video");
//����Statement
java.sql.Statement stmt = conn.createStatement();
//ִ�в�ѯ
java.sql.ResultSet rs = stmt.executeQuery("select * from jg where rownum<6");
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
conn.close();
%>
</table>
����
</body>
</html>