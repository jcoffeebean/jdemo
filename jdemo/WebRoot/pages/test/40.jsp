<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--link rel="stylesheet" type="text/css" href="${ctx}/resource/css/test.css" /-->
		<title>表单模块的实现方案二</title>
	</head>
	<body>
		<form action="" method="">
			<fieldset>
				<legend>登录表单</legend>
				<p><label for="name">账号：</label><input type="text" id="name" /></p>
				<p><label for="pwd">密码：</label><input type="password" id="pwd" /></p>
				<input type="submit" value="登录" class="subBtn" />
			</fieldset>	
		</form>	
	</body>
</html>