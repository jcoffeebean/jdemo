<%@ page contentType="text/html;charset=gbk" language="java" %>
<html>
	<head><title>pageContext对象测试</title><head>
	<body>
	pageContext对象代表页面上下文，使用pageContext对象可以直接访问request，session，application范围的属性<br>
	<%
	//使用pageContext设置属性，该属性默认在page范围内
	pageContext.setAttribute("page","hello");
	//使用request设置属性，该属性默认在request范围内
	request.setAttribute("request","hello");
	//使用pageContext将属性设置在request范围内
	pageContext.setAttribute("request2","hello",pageContext.REQUEST_SCOPE);
	//使用session将属性设置在session范围内
	session.setAttribute("session","hello");
	//pageContext将属性设置在session范围内
	pageContext.setAttribute("session2","hello",pageContext.SESSION_SCOPE);
	//application将属性设置在application范围内
	application.setAttribute("app","hello");
	//pageContext将属性设置在application范围内
	pageContext.setAttribute("app2","hello",pageContext.APPLICATION_SCOPE);
	
	%><br>
	page属性所在的范围：<%=pageContext.getAttributesScope("page")%><br>
	request属性所在的范围：<%=pageContext.getAttributesScope("request")%><br>
	request2属性所在的范围：<%=pageContext.getAttributesScope("request2")%><br>
	session属性所在的范围：<%=pageContext.getAttributesScope("session")%><br>
	session2属性所在的范围：<%=pageContext.getAttributesScope("session2")%><br>
	app属性所在的范围：<%=pageContext.getAttributesScope("app")%><br>
	app2属性所在的范围：<%=pageContext.getAttributesScope("app2")%><br>
	
	</body>
</html>
	