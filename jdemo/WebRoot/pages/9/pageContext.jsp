<%@ page contentType="text/html;charset=gbk" language="java" %>
<html>
	<head><title>pageContext�������</title><head>
	<body>
	pageContext�������ҳ�������ģ�ʹ��pageContext�������ֱ�ӷ���request��session��application��Χ������<br>
	<%
	//ʹ��pageContext�������ԣ�������Ĭ����page��Χ��
	pageContext.setAttribute("page","hello");
	//ʹ��request�������ԣ�������Ĭ����request��Χ��
	request.setAttribute("request","hello");
	//ʹ��pageContext������������request��Χ��
	pageContext.setAttribute("request2","hello",pageContext.REQUEST_SCOPE);
	//ʹ��session������������session��Χ��
	session.setAttribute("session","hello");
	//pageContext������������session��Χ��
	pageContext.setAttribute("session2","hello",pageContext.SESSION_SCOPE);
	//application������������application��Χ��
	application.setAttribute("app","hello");
	//pageContext������������application��Χ��
	pageContext.setAttribute("app2","hello",pageContext.APPLICATION_SCOPE);
	
	%><br>
	page�������ڵķ�Χ��<%=pageContext.getAttributesScope("page")%><br>
	request�������ڵķ�Χ��<%=pageContext.getAttributesScope("request")%><br>
	request2�������ڵķ�Χ��<%=pageContext.getAttributesScope("request2")%><br>
	session�������ڵķ�Χ��<%=pageContext.getAttributesScope("session")%><br>
	session2�������ڵķ�Χ��<%=pageContext.getAttributesScope("session2")%><br>
	app�������ڵķ�Χ��<%=pageContext.getAttributesScope("app")%><br>
	app2�������ڵķ�Χ��<%=pageContext.getAttributesScope("app2")%><br>
	
	</body>
</html>
	