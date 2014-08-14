<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
	<title>jsp-forward.jsp</title>
</head>
<body>
	<%
		out.println("jsp:forward测试");
		request.setAttribute("viewName","jsp-forward.jsp");
		request.setAttribute("key_out",out.toString());
	%>
	${viewName}<br>
	request.getAttribute("username") = <%=request.getAttribute("username") %><br>
	request.getParameter("username") = <%=request.getParameter("username") %><br>
	
	out = <%= out.toString() %> 	
	<!-- jsp:forward: 执行页面转向，将请求的处理转发到下一个页面。-->
	<jsp:forward page="forward-result.jsp?name=aric">
		<jsp:param value="999" name="age"/>
	</jsp:forward>
	
	<!-- jsp:forward指令下面的内容不再执行 -->
	<form id="login" method="post" action="/jdemo/f">
		<input type="text" name="username"/>
		<input type="submit" value="登录"/>
  	</form>

	
</body>
</html>
