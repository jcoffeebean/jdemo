<%@ page contentType="text/html;charset=GBK" language="java"%>
<!-- 导入标签库，指定前缀为mtag的标签由/tags/test.tld的标签库处理 -->
<%@ taglib uri="/tags/test.tld" prefix="mtag" %>
<html>
	<head><title>使用自定义标签</title></head>
	<body bgcolor="#ffffc0">
		<h2>下面是自定义标签显示的内容<hr>
			<!-- 使用标签，其中mtag为标签前缀，根据taglib编译指令，mtag前缀的标签将由URI为/tags/test.tld的标签库处理 -->
			<mtag:Hi /><hr>
			<mtag:Hi></mtag:Hi><hr>	SKIP_BODY
			<mtag:Hi2 /><hr>
			------------------------	
	</body>
</html>