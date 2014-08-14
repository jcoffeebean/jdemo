<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ taglib uri="/tags/test.tld" prefix="mytag"%>
<%@ taglib uri="/tags/test.tld" prefix="mtag"%>
<%@ taglib uri="/test.tld" prefix="hello"%>
<html>
	<head><title>自定义标签QueryTag</title></head>
	<body>
		自定义标签QueryTag<hr>
	<mytag:query url="jdbc:oracle:thin:@192.168.0.104:1521:oradb" driver="oracle.jdbc.driver.OracleDriver" 
						username="pzk" passwd="pzk" sql="select * from user_info" />					
	<mtag:Hi />
	<hello:Hi />
	</body>
		
</html>