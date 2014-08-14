<%@ page contentType="text/html;charset=gbk" language="java"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<html>
 <head>
 	<base href="<%=basePath%>"> 
 	 
 	<title>调用JavaBean</title></head>
 <body>
   <!-- 实例化JavaBean实例，实现类为tydic.Person. 该实例的实例名为p1-->
 	 <jsp:useBean id="p1" class="tydic.Person" scope="page"/>
 	 
 	 <jsp:setProperty name="p1" property="p_name1" value="Eric"/>
 	 <jsp:setProperty name="p1" property="age" value="85"/>
 	 
 	 <jsp:getProperty name="p1" property="p_name1"/>
 	 <jsp:getProperty name="p1" property="age"/>
 	 <br>
 	 application.getAttribute("counter")=<%=application.getAttribute("counter")%>
 	 
 </body>
</html>