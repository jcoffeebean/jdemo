<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head><title>second.jsp</title></head>
	<body>
		<%
			String balance = request.getParameter("balance");
			
			double bal = Double.parseDouble(balance);
			java.util.List<String> info = (java.util.ArrayList<String>)request.getAttribute("info");
			
			out.print("qian="+bal+"<br><hr>");			
			
			for(String tmp: info) {
				out.print(tmp+"<br>");
			}
		%>

	</body>
</html>