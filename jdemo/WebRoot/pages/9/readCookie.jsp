<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>����Cookie</title></head>
	<body>
		<%
		//��ȡ��վ�ڿͻ���������Cookie
		Cookie[] cookies = request.getCookies();
		//�����ͻ����ϵ�ÿһ��Cookie
		for(Cookie c: cookies) {
		  if(c.getName().equals("username"))
		  	out.print(c.getValue()+"<br>");
		  else 
		  	out.print(c.getValue()+"--");
		}
		%>

	</body>
</html>