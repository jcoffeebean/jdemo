<%@ page contentType="text/html; charset=GBK" language="java" errorPage="errortest.jsp" %>
<% 
   String rootPath = (String) request.getContextPath();   
%>
<link type="text/css" href="<%=rootPath%>/css/sys.css " rel="stylesheet" />
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<%=rootPath%>/image/sys_icon.gif" />	
	<title>java_web¿ª·¢</title>	
	<META content="MSHTML 6.00.2800.1106" name=GENERATOR>
</head>	

<frameset rows="*,30" cols="*" framespacing="0" frameborder="NO" border="0">
  <frameset rows="91,*" cols="*" frameborder="NO" border="0" framespacing="0" name="main">
    <frame src="test.jsp" name="topFrame" scrolling="NO" noresize>
    <frameset id=left name="middle" cols="176,9,*"  frameborder="no" border="0" framespacing="0" style="" border="0" frameborder="no">
		      <frameset rows="45,*" frameborder="no" border="0" framespacing="0" style="" border="0" frameborder="no" id=setyou>
		      	<frame src="test.jsp" name="topFrame1" scrolling="no" noresize>
		      	<frame src="test.jsp" name="leftFrame" scrolling="auto" noresize>
	          <frame src="UntitledFrame-24">
	        </frameset>
		      <frame name="movePage" src="test.jsp" noResize scrolling=no border=0>
		      <frame src="test.jsp" name="rightFrame" scrolling="auto"  frameborder="NO" noresize >
		</frameset>
  	<frame src="UntitledFrame-5">
  </frameset>
  <frame src="test.jsp" scrolling="NO" noresize>
</frameset>
<noframes>
	<body></body>
</noframes>
</html>