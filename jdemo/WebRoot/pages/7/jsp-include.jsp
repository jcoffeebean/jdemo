<%@ page contentType="text/html;charset=gbk" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head><title>²âÊÔjsp:include¶¯×÷Ö¸Áî-</title></head>
	<body>
	<%
		String uri = "/connDb.jsp";
		out.println("uri="+uri);
		String uri2 = basePath + "connDb.jsp";
		out.println("uri2="+uri2);
	%>
	<jsp:include page="<%=uri%>" />
	<!--  jsp:include page="<%=uri2%>" /-->

  	</body>
</html>