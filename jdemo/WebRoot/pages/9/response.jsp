<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>����Cookie</title></head>
	<body>
		<%
		//�����������http://localhost:8080/java_webDemo/9/response.jsp?username=taowei
		//��ȡ�������
			String username = request.getParameter("username");
			//�Ի�ȡ���������Ϊֵ������һ��Cookie����
			Cookie c = new Cookie("username",username);
			//����Cookie�������������
			c.setMaxAge(24*3600);
			//��ͻ������Cookie����
			response.addCookie(c);
		%>

	</body>
</html>