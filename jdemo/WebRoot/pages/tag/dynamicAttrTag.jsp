<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ taglib uri="/tags/test.tld" prefix="mytag"%>

<html>
	<head><title>支持动态属性的标签</title></head>
	<body>
		支持动态属性的标签<hr>		

		<mytag:dynamic bean="a" item="object" /><hr>
		<mytag:dynamic 书名="疯狂java讲义" 价格="45元" 出版社="博文视点" /><hr>
						
		<br>${pageContext.request.remoteAddr}
	</body>

</html>