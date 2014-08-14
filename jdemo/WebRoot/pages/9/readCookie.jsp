<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>访问Cookie</title></head>
	<body>
		<%
		//获取本站在客户机的所有Cookie
		Cookie[] cookies = request.getCookies();
		//变量客户端上的每一个Cookie
		for(Cookie c: cookies) {
		  if(c.getName().equals("username"))
		  	out.print(c.getValue()+"<br>");
		  else 
		  	out.print(c.getValue()+"--");
		}
		%>

	</body>
</html>