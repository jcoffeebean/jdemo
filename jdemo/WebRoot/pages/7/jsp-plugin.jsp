<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>ʹ��jsp:plugin</title></head>
	<body>
		<!-- ʹ��Applet ע��HiApplet.java���ܼ�package�������Ҳ���������HiApplet.class-->
		<jsp:plugin type="applet" code="/WEB-INF/classes/applet/HiApplet.class" codebase="/WEB-INF/classes/applet">
		
			<jsp:params>
				<jsp:param name="Hi" value="Java������"/>
			</jsp:params>
			
			<jsp:fallback>
				<p>��������jre���</p>
			</jsp:fallback>
		</jsp:plugin>
	</body>
</html>