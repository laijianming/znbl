<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看图册</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<table>
 <c:forEach items="${page.list}" var="sysPic" varStatus="status">
	<c:if test="${status.index % 2 eq 0}">
		<tr>
		<td><img alt="${sysPic.name}" src="${root}/${sysPic.picUrl}" style="width:300px;height:150px;"></td>
	</c:if>
	<c:if test="${status.index % 2 eq 1}">
		<td><img alt="${sysPic.name}" src="${root}/${sysPic.picUrl}" style="width:300px;height:150px;"></td>
		</tr>
	</c:if>
 </c:forEach>
</table>
<div class="pagination">${page}</div>
</body>
</html>