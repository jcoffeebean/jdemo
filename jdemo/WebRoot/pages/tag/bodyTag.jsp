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
	<head><title>带标签体的标签--迭代器</title></head>
	<body>
		带标签体的标签--迭代器<hr>		
	<table border='1' bgcolor="#aaaaadd" width="300">
		<!-- 使用迭代器标签对集合进行迭代输出 -->
		<mytag:iterator bean="a" item="object">
			<tr>
				<td>${pageScope.object}</td>
			</tr>
		</mytag:iterator>
	</table>				

	</body>

</html>