<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关联参赛者</title>
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
	<form:form id="searchForm" modelAttribute="sysApply" action="${ctx}/sys/sysApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<%-- 		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select id="type" path="type" class="input-medium">
					<form:option value="">请选择</form:option>
					<form:options items="${fns:getDictList('match_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>报名人</th>
				<th>手机号码</th>
				<th>类型</th>
				<th>报名时间</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysApply">
			<tr>
				<td>${sysApply.user.name}</td>
				<td>${sysApply.user.mobile}</td>
				<td>${fns:getDictLabel(sysApply.type, "match_type", sysApply.type)}</td>
				<td>
					<fmt:formatDate value="${sysApply.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>${fns:getDictLabel(sysApply.status, "match_status", sysApply.status)}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>