<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>测试</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<style type="text/css">
		p {background:pink;color: blue;font-size: large;font-weight: bold;text-align: center;}
	</style>
  </head>
  
  <body>
    <p>
    Hello 宏伟！！！以下是后台服务返回的数据哦.</p><br>
    <div>
    <ul>
    	<li>${msg}</li>
    	<li>${viewName}</li>
    	<li>${nameList}</li>
    	<li>${nameList2}</li>
    	<li>${stringList}</li>
    	<li>${nameList4}</li>
    	<li>${nameList5}</li>
    </ul>
    </div>
  </body>
</html>
