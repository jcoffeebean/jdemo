<!-- contentType用于设定生成网页的文件格式和编码方式，即MIME类型和页面字符集类型，
     默认的MIME类型为text/html，默认的页面字符集为ISO-8859-1 -->
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
	<title>welcome java_web开发</title>
</head>
<body>
	welcome.jsp
	<%java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  java.util.Date date = new java.util.Date();
	%>
	北京时间：<% out.println(sdf.format(date));%>
  <hr>
  登陆用户：<%=session.getAttribute("name")%>
  
</body>
</html>