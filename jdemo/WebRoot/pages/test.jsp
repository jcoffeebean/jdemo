<!-- contentType�����趨������ҳ���ļ���ʽ�ͱ��뷽ʽ����MIME���ͺ�ҳ���ַ������ͣ�
     Ĭ�ϵ�MIME����Ϊtext/html��Ĭ�ϵ�ҳ���ַ���ΪISO-8859-1 -->
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="errorPage.jsp" %>
<% 
   String rootPath = (String) request.getContextPath();   
%>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<%=rootPath%>/image/sys_icon.gif" />
	<title>java_web����</title>	
</head>
<body>
	<%java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  java.util.Date date = new java.util.Date();
	  String cT = request.getContentType();
	%>
	����ʱ�䣺<% out.println(sdf.format(date));%><br>
	contentType: <%=cT%>
	
	<br><br>
	
	
	�������ԣ�
	<!-- ������JSP�������� -->
	<%!
	  //����һ�����ͱ���
	  public int count;
	  //����һ������
	  public String info() {
	  	return "Hello Java Web!";
	  }
	  %>
	<%
	  //��count��ֵ������ټ�1
		out.println(count++);
	%>
	<br>
	<%
	  //���info()�����ķ���ֵ
		out.println(info());
	%>
	<br><br>
	<!-- ʹ�ñ��ʽ�������ֵ -->
	count��<%=count%><br>
	
	<!-- ʹ�ñ��ʽ�����������ֵ -->
	info��<%=info()%>
	<br><br><br>
	
	<table bgcolor="9999dd" border="l">
	<!-- ��Щ�ű���HTML��ǩ�������� -->
  <%
  for(int i=0; i<10; i++){
  %>
  <tr><td>ѭ��ֵ��</td><td><%=i%></td><tr/>  	
  <%
  }
  %> 
  </table> 
  
</body>
</html>