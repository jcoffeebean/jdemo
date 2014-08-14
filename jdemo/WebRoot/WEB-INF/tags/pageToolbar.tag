<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="common.page.Page" description="Page.java" %>
<%@ attribute name="pageSizeSelectList" type="java.lang.Number[]" required="false"  %>
<%@ attribute name="isShowPageSizeList" type="java.lang.Boolean" required="false"  %>
<%@ attribute name="href" type="java.lang.String" required="false"  %>
<%@ attribute name="formId" type="java.lang.String" required="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
	Integer[] defaultPageSizes = new Integer[]{5,10,20,50};
	if(jspContext.getAttribute("pageSizeSelectList") == null) {
		jspContext.setAttribute("pageSizeSelectList",defaultPageSizes); 
	}
	
	if(jspContext.getAttribute("isShowPageSizeList") == null) {
		jspContext.setAttribute("isShowPageSizeList",true); 
	} 
%>

		<div class="paginationToolbar">
		
			<div  class="leftControls">
			</div>
			
			<div class="paginationControls">
				<span class="buttonLabel">${page.thisPageFirstElementNumber} - ${page.thisPageLastElementNumber} of ${page.totalCount}</span>
				
				<c:choose>
				<c:when test="${page.firstPage}"><span>首页</span></c:when>
				<c:otherwise>
					<span><a href="javascript:PaginationControl.togglePage(1);">首页</a></span>
				</c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.hasPreviousPage}">
					<span><a href="javascript:PaginationControl.togglePage(${page.previousPageNumber});">上一页</a></span>
				</c:when>
				<c:otherwise><span>上一页</span></c:otherwise>
				</c:choose>
				
				<c:forEach var="item" items="${page.linkPageNumbers}">
				<c:choose>
				<c:when test="${item == page.thisPageNumber}">[${item}]</c:when>
				<c:otherwise><span><a href="javascript:PaginationControl.togglePage(${item});">[${item}]</a></span></c:otherwise>
				</c:choose>
				</c:forEach>
				
				<c:choose>
				<c:when test="${page.hasNextPage}">
					<span><a href="javascript:PaginationControl.togglePage(${page.nextPageNumber});">下一页</a></span>
				</c:when>
				<c:otherwise><span>下一页</span></c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.lastPage}"><span>末页</span></c:when>
				<c:otherwise>
					<span><a href="javascript:PaginationControl.togglePage(${page.lastPageNumber});">末页</a></span>
				</c:otherwise>
				</c:choose>
				
				<c:if test="${isShowPageSizeList}">
				<span>
				<select onChange="PaginationControl.togglePageSize(this.value)" id="pageSizeSelect">
					<c:forEach var="item" items="${pageSizeSelectList}">
						<option value="${item}" ${page.pageSize == item ? 'selected' : '' }>${item}</option>
					</c:forEach> 
				</select>
				</span>
				</c:if>
			</div>
		</div>
			<script type="text/javascript">
			var PaginationControl = {
				doJump : function(pageNumber,pageSize,sortColumns) {
					pageNumber = pageNumber || 1;
					pageSize = pageSize || document.getElementById('pageSizeSelect').value;
					sortColumns = sortColumns || '';
					
					<c:choose>
					<c:when test="${not empty href}">
						var href = '${href}';
						window.location.href = href + (/\?/.test(href)?'&':'?') + 'pageNumber='+pageNumber+'&pageSize='+pageSize+'&sortColumns='+sortColumns;
					</c:when>
					<c:otherwise>
						var form = document.getElementById('${formId}') || document.forms[0];
						form.appendChild(this.createInput('pageNumber',pageNumber));
						form.appendChild(this.createInput('pageSize',pageSize));
						form.appendChild(this.createInput('sortColumns',sortColumns));
						form.submit();
					</c:otherwise>
					</c:choose>					
				},
				togglePage : function(pageNumber) {
					this.doJump(pageNumber,null,null);
				},
				togglePageSize : function(pageSize) {
					this.doJump(null,pageSize,null);
				},
				toggleSort : function(sortColumns) {
					this.doJump(null,null,sortColumns);
				},
				createInput:function(name,value){
					var input = document.createElement('input');
					input.type = 'hidden';
					input.name = name;
					input.value = value;
					return input;
				}
			}		
			</script>
		
