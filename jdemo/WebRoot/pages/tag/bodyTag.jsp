<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ taglib uri="/tags/test.tld" prefix="mytag"%>
<%
	java.util.List<String> list = new java.util.ArrayList<String>();
	list.add("Hello");
	list.add("Java");
	list.add("World!!");
	pageContext.setAttribute("a",list);
%>	
<html>
	<head><title>����ǩ��ı�ǩ--������</title></head>
	<body>
		����ǩ��ı�ǩ--������<hr>		
	<table border='1' bgcolor="#aaaaadd" width="300">
		<!-- ʹ�õ�������ǩ�Լ��Ͻ��е������ -->
		<mytag:iterator bean="a" item="object">
			<tr>
				<td>${pageScope.object}</td>
			</tr>
		</mytag:iterator>
	</table>				

	</body>

</html>