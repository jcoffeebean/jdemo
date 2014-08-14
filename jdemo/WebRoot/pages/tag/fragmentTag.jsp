<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ taglib uri="/taowei/mytaglib" prefix="my" %>
<%@ taglib uri="/tags/test.tld" prefix="mytag" %>
<html>
	<head><title>以页面片段作为属性</title></head>
	<body>
		<h3>以页面片段作为属性</h3>
		<my:f>
			<!-- 使用jsp:attribute标签传入fragment参数 -->
			<jsp:attribute name="fragment">
				<!-- 下面是动态的JSP页面片段 -->
				<mytag:Hi2 />
			</jsp:attribute>
		</my:f>
		
		<my:f>
			<!-- 使用jsp:attribute标签传入fragment参数 -->
			<jsp:attribute name="fragment">
				<!-- 下面是动态的JSP页面片段 -->
				${pageContext.request.remoteAddr}
			</jsp:attribute>
		</my:f>
	</body>
</html>