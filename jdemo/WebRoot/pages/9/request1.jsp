<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head>
		<title>����request��ʹ��</title>
	</head>
	<body>
		<%//��ȡ��������ͷ������
			java.util.Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String value = request.getHeader(headerName);
				out.println(headerName+": "+value+"<br>");
			}
			
		  //���ý��뷽ʽ������������GBK���룬����������ʾ����
		  request.setCharacterEncoding("GBK");
		  //�������λ�ȡ�����ֵ
		  String username = request.getParameter("username");
		  String gender = request.getParameter("gender");
		  String[] color = request.getParameterValues("color");
		  String country = request.getParameter("country");
		%><hr>
		<!-- ������������ֵ -->
		������<%=username%><hr>
		�Ա�<%=gender%><hr>
		ϲ������ɫ��<% for(String c : color) {out.print(c);}%><hr>
		���ң�<%=country%><hr>
		ContentType: <%=request.getContentType()%><br>
		
		<%//��ȡ��������
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