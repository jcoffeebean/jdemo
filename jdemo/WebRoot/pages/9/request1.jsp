<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head>
		<title>测试request的使用</title>
	</head>
	<body>
		<%//获取所有请求头的名称
			java.util.Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String value = request.getHeader(headerName);
				out.println(headerName+": "+value+"<br>");
			}
			
		  //设置解码方式，对于中文用GBK编码，否则中文显示乱码
		  request.setCharacterEncoding("GBK");
		  //下面依次获取表单域的值
		  String username = request.getParameter("username");
		  String gender = request.getParameter("gender");
		  String[] color = request.getParameterValues("color");
		  String country = request.getParameter("country");
		%><hr>
		<!-- 下面输出表单域的值 -->
		姓名：<%=username%><hr>
		性别：<%=gender%><hr>
		喜欢的颜色：<% for(String c : color) {out.print(c);}%><hr>
		国家：<%=country%><hr>
		ContentType: <%=request.getContentType()%><br>
		
		<%//获取表单参数名
			java.util.Enumeration enu = (java.util.Enumeration)request.getParameterNames();
			while(enu.hasMoreElements()){
				Object obj = enu.nextElement();
				String[] parms = request.getParameterValues(obj.toString());
				if(parms!=null && parms.length>1 ) {
				   String sr="";
					 for(String s: parms){sr = sr+s+" ";}
					 out.print(obj.toString()+": "+sr);
			  } else{
					out.print(obj.toString()+": "+request.getParameter(obj.toString()));
				}
				out.print("<br>");
			}
		%>
		
	</body>
</html>