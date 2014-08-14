<!-- contentType用于设定生成网页的文件格式和编码方式，即MIME类型和页面字符集类型，
     默认的MIME类型为text/html，默认的页面字符集为ISO-8859-1 -->
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="errorPage.jsp" %>
<% 
   String rootPath = (String) request.getContextPath();   
%>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<%=rootPath%>/image/sys_icon.gif" />
	<title>java_web开发</title>	
</head>
<body>
	<%java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  java.util.Date date = new java.util.Date();
	  String cT = request.getContentType();
	%>
	北京时间：<% out.println(sdf.format(date));%><br>
	contentType: <%=cT%>
	
	<br><br>
	
	
	声明测试：
	<!-- 下面是JSP声明部分 -->
	<%!
	  //声明一个整型变量
	  public int count;
	  //声明一个方法
	  public String info() {
	  	return "Hello Java Web!";
	  }
	  %>
	<%
	  //将count的值输出后再加1
		out.println(count++);
	%>
	<br>
	<%
	  //输出info()方法的返回值
		out.println(info());
	%>
	<br><br>
	<!-- 使用表达式输出变量值 -->
	count：<%=count%><br>
	
	<!-- 使用表达式输出方法返回值 -->
	info：<%=info()%>
	<br><br><br>
	
	<table bgcolor="9999dd" border="l">
	<!-- 这些脚本对HTML标签产生作用 -->
  <%
  for(int i=0; i<10; i++){
  %>
  <tr><td>循环值：</td><td><%=i%></td><tr/>  	
  <%
  }
  %> 
  </table> 
  
</body>
</html>