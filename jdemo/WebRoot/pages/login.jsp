<%@ page contentType="text/html; charset=GBK" language="java" %>
<html>
<head>
	<title>login</title>
</head>
<body>
  <!-- ϵͳ������ʾ -->
  <font color="red">
  	<%
  		if(request.getAttribute("err")!=null){
  		    out.print(request.getAttribute("err"));
  		}
  	%>
  </font><hr><br>
  �������û��������룺
  <!-- ��½�����ñ��ύ��һ��Servlet -->
  <form method=post action=login>
  	�û�����<input type=text name=username /><br>
  	��&nbsp;&nbsp�룺<input type=password name=pwd /><br>
  	<input type=submit value=�ύ />
  </form>
</body>
</html>