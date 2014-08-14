<%@ page contentType="text/html; charset=GBK" language="java" %>
<html>
<head>
	<title>login</title>
</head>
<body>
  <!-- 系统出错提示 -->
  <font color="red">
  	<%
  		if(request.getAttribute("err")!=null){
  		    out.print(request.getAttribute("err"));
  		}
  	%>
  </font><hr><br>
  请输入用户名和密码：
  <!-- 登陆表单，该表单提交到一个Servlet -->
  <form method=post action=login>
  	用户名：<input type=text name=username /><br>
  	密&nbsp;&nbsp码：<input type=password name=pwd /><br>
  	<input type=submit value=提交 />
  </form>
</body>
</html>