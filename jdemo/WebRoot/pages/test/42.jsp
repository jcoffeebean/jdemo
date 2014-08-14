<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--link rel="stylesheet" type="text/css" href="${ctx}/resource/css/test.css" /-->
		<title>表格模块的实现方案二</title>
	</head>
	<body>
		<table border="1">
			<caption>几种页面实现的比较</caption>
			<thead>
				<tr><th>实现方式</th><th>代码量</th><th>搜索引擎友好</th><th>特殊终端兼容</th></tr>
			</thead>
			<tbody>
				<tr><th>table布局</th><th>多</th><th>差</th><th>一般</th></tr>
				<tr><th>乱用标签的CSS布局</th><th>少</th><th>一般</th><th>差</th></tr>
				<tr><th>标签语义良好的CSS布局</th><th>少</th><th>好</th><th>好</th></tr>
			</tbody>
		</table>
	</body>
</html>