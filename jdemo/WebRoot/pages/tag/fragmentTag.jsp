<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ taglib uri="/taowei/mytaglib" prefix="my" %>
<%@ taglib uri="/tags/test.tld" prefix="mytag" %>
<html>
	<head><title>��ҳ��Ƭ����Ϊ����</title></head>
	<body>
		<h3>��ҳ��Ƭ����Ϊ����</h3>
		<my:f>
			<!-- ʹ��jsp:attribute��ǩ����fragment���� -->
			<jsp:attribute name="fragment">
				<!-- �����Ƕ�̬��JSPҳ��Ƭ�� -->
				<mytag:Hi2 />
			</jsp:attribute>
		</my:f>
		
		<my:f>
			<!-- ʹ��jsp:attribute��ǩ����fragment���� -->
			<jsp:attribute name="fragment">
				<!-- �����Ƕ�̬��JSPҳ��Ƭ�� -->
				${pageContext.request.remoteAddr}
			</jsp:attribute>
		</my:f>
	</body>
</html>