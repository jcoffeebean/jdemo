<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>使用jsp:plugin</title></head>
	<body>
		<!-- 使用Applet 注：HiApplet.java不能加package，否则找不到编译后的HiApplet.class-->
		<jsp:plugin type="applet" code="/WEB-INF/classes/applet/HiApplet.class" codebase="/WEB-INF/classes/applet">
		
			<jsp:params>
				<jsp:param name="Hi" value="Java的世界"/>
			</jsp:params>
			
			<jsp:fallback>
				<p>不能下载jre插件</p>
			</jsp:fallback>
		</jsp:plugin>
	</body>
</html>