<%@ page contentType="text/html; charset=GBK" language="java" isErrorPage="true" %>
<html>
<head>
	<title>java_web����</title>
</head>
<body>
ϵͳ�쳣��Ŷ!!!
�쳣��Ϣ��
<%
//LoginServlet.java����ת�����쳣ҳ��ʱ������ex����
	Exception ex = (Exception)request.getAttribute("ex");
	java.io.ByteArrayOutputStream ostr = new java.io.ByteArrayOutputStream();
  //�Ѵ����ջ���浽����
  if(ex!= null) {
  	ex.printStackTrace(new java.io.PrintStream(ostr));
  	//��ӡ....
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