<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	//从session对象中取出
	java.util.Map<String,Integer> itemMap = (java.util.HashMap<String,Integer>)session.getAttribute("itemMap");
	
	if(itemMap == null) {
		itemMap = new java.util.HashMap<String,Integer>();
		itemMap.put("书籍",0);
		itemMap.put("电脑",0);
		itemMap.put("汽车",0);	
	}
	//获取上个页面的请求参数
	String[] buys = request.getParameterValues("item");
	//遍历各数组元素
	for(String item: buys) {
		if(item.equals("book")){
			int num = itemMap.get("书籍").intValue();
			//将书籍key对应的数量加1
			itemMap.put("书籍",num+1);
		}else if(item.equals("computer")){
			int num = itemMap.get("电脑").intValue();
			//将电脑key对应的数量加1
			itemMap.put("电脑",num+1);
		}else if(item.equals("car")){
			int num = itemMap.get("汽车").intValue();
			//将汽车key对应的数量加1
			itemMap.put("汽车",num+1);
		}		
	}
	
	//将itemMap对象放入session中
	session.setAttribute("itemMap",itemMap);
%>
<html>
	<head><title>购买的物品列表</title></head>
	<body>
		你所购买的物品：<hr><br>
		书籍：<%=itemMap.get("书籍")%>本<br>
		电脑：<%=itemMap.get("电脑")%>台<br>
		汽车：<%=itemMap.get("汽车")%>辆<br>
		<a href=shopping.jsp>再次购买</a>
	</body>
</html>