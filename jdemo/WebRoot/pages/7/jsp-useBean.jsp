<%@ page contentType="text/html;charset=gbk" language="java"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<html>
 <head>
 	<base href="<%=basePath%>"> 
 	 
 	<title>����JavaBean</title></head>
 <body>
   <!-- ʵ����JavaBeanʵ����ʵ����Ϊtydic.Person. ��ʵ����ʵ����Ϊp1-->
 	 <jsp:useBean id="p1" class="tydic.Person" scope="page"/>
 	 
 	 <jsp:setProperty name="p1" property="p_name1" value="Eric"/>
 	 <jsp:setProperty name="p1" property="age" value="85"/>
 	 
 	 <jsp:getProperty name="p1" property="p_name1"/>
 	 <jsp:getProperty name="p1" property="age"/>
 	 <br>
 	 application.getAttribute("counter")=<%=application.getAttribute("counter")%>
 	 
 </body>
</html>