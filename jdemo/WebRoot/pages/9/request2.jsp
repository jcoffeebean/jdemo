<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head><title>测试request，封装地址栏参数(GET请求)</title></head>
	<body><hr>
		在浏览器中输入：
		http://localhost:8080/jdemo/pages/9/request2.jsp?name=taowei&sex=女girl<hr>
		<%
			//原始的请求参数值
			String rawName = request.getParameter("name");
			String sex = request.getParameter("sex");
			//将请求参数值用UTF-8分解成字节数组
			byte[] rawBytes = rawName.getBytes("UTF-8");//
			//将字节数组重新解码成字符串
			String name = new String(rawBytes,"UTF-8");
		%>
		rawName--><%=rawName%><br>
		name---><%=name%><br>
		sex--><%=sex%><br><hr>
		url串中包含中文的处理方法<hr>
		<%
			//获取请求的原始查询字符串
			String rawStr = request.getQueryString();
			out.println("1原始的查询串为："+rawStr+"<br>");
			//用URLDecoder解码
			String queryStr = java.net.URLDecoder.decode(rawStr,"UTF-8");
			out.println("2解码后的查询串为："+queryStr+"<br>");
			String[] strs = queryStr.split("&");
			for(String s: strs) {
				String[] temp = s.split("=");
				String pN = temp[0];
				String pV = temp[1];
				out.println(pN+"="+pV+"<br>");
			}
		%>
		
	</body>
</html>