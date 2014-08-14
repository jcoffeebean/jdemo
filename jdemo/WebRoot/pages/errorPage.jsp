<%@ page contentType="text/html; charset=GBK" language="java" isErrorPage="true" %>
<html>
<head>
	<title>java_web开发</title>
</head>
<body>
系统异常了哦!!!
异常信息：
<%
//LoginServlet.java里面转发到异常页面时设置了ex属性
	Exception ex = (Exception)request.getAttribute("ex");
	java.io.ByteArrayOutputStream ostr = new java.io.ByteArrayOutputStream();
  //把错误堆栈储存到流中
  if(ex!= null) {
  	ex.printStackTrace(new java.io.PrintStream(ostr));
  	//打印....
  	out.println(ostr.toString());
  }
%><hr>
<%if(exception != null) {
%>
<%=exception.getClass()%><br>
<%=exception.getMessage()%>
<%}%>
</body>
</html>