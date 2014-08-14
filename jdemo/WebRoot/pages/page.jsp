<!-- JSP的三个编译指令Page，includes，taglib -->
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page info="This a JSP page" %>

<html>
	<head>
		<title>Page instruction</title>
	</head>
	<body>
	Test Page</br></br><!-- </br>换行 -->
		<%=getServletInfo()%>
	</body>
</html>