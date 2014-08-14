<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/pages/commons/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/resource/css/table.css"></link>
	<script type="text/javascript">
	function doClear(){
		$(document.forms[0]).find('input:text').val('');
	}
	</script>	
  </head>
    
  <body>
  	<div style="padding-right: 3px;_height: 300px;min-height: 300px;">
  		<form action="${ctx}/qq/li" method="post">
  		<div style="padding: 10px;clear: both;margin-bottom: 10px;">	
		    	<table border="0" align="left" width="100%">
		    		<tr>
		    			<td class="td_lab">姓名：</td>
		    			<td><input type="text" name="xm" class="input-list-query" value="${qqBean.xm}"/></td>
		    			<td class="td_lab">身份证号：</td>
		    			<td><input type="text" name="sfzh" class="input-list-query" value="${qqBean.sfzh}"/></td>
		    			<td class="td_lab">QQ号：</td>
		    			<td><input type="text" name="qq" class="input-list-query" value="${qqBean.qq}"/></td>	  		    				    		
		    		</tr>
		    		<tr> 
		    			<td colspan="6" align="right" style="padding: 10px 0;">
		    				<input type="submit" class="button2" value="查询" />
		    				<input type="button" class="button" value="清空" onclick="doClear()"/>
						</td>    			
		    		</tr>  
		    	</table>    		
  		</div>
  		<div style="clear: both;">
  			<table class="TabList" width="100%" align="center">
  				<thead>
  				<tr class="title">
  					<th width="150">姓名</th>
  					<th>身份证号</th>
  					<th>QQ号</th>
  				</tr>
  				</thead>
  				<tbody>
  			<c:if test="${empty page.result }">
  				<td colspan="4" class="first last" style="text-align: center;height: 30px;">
  				没有数据!
  				</td>
  			</c:if>	
  			<c:forEach items="${page.result }" var="item" varStatus="status">
  				<tr class="${status.index%2==0?'even':'odd' }">
  					<td class="first">${item.xm }</td>
  					<td>${item.sfzh }</td>
  					<td>${item.qq }</td>
  				</tr>
  			</c:forEach>
  				</tbody>
  			</table>
  		</div>
  		<div>
  			<pagination:pageToolbar page="${page}"></pagination:pageToolbar>
  		</div>
  		</form>
  	</div>
  </body>
</html>
