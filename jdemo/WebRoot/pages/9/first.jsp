<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head><title>form页</title></head>
	<body>
		<%
			String balance = request.getParameter("balance");
			
			double bal = Double.parseDouble(balance);
			
			if(bal<500){
				out.println("取钱："+bal);
				
		  } else {
				
				//创建一个List对象
				java.util.List<String> info = new java.util.ArrayList<String>();
				info.add("1111");
				info.add("2222");
				info.add("3333");
				//向request中封装一个请求属性info
				request.setAttribute("info",info);
		%>
		
		<!-- forward实现转发，请求参数和请求属性都不会丢失，即在second.jsp页面可以获取请求参数和请求属性的值 -->
		<jsp:forward page="second.jsp"/>
			
		<%
			}
		%>
	</body>
</html>