<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>增加Cookie</title></head>
	<body>
		<%
		//在浏览器访问http://localhost:8080/java_webDemo/9/response.jsp?username=taowei
		//获取请求参数
			String username = request.getParameter("username");
			//以获取的请求参数为值，创建一个Cookie对象
			Cookie c = new Cookie("username",username);
			//设置Cookie对象的生存期限
			c.setMaxAge(24*3600);
			//向客户端添加Cookie对象
			response.addCookie(c);
		%>

	</body>
</html>