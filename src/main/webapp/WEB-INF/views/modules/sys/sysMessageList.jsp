<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台留言管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysMessage/">平台留言列表</a></li>
		<%-- <shiro:hasPermission name="sys:sysMessage:edit"><li><a href="${ctx}/sys/sysMessage/form">添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="sysMessage" action="${ctx}/sys/sysMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select id="type" path="type" class="input-medium">
					<form:option value="">请选择</form:option>
					<form:options items="${fns:getDictList('message_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>留言者</th>
				<th>类型</th>
				<th>留言时间</th>
				<th>是否已处理</th>
				<shiro:hasPermission name="sys:sysMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysMessage">
			<tr>
				<td>${sysMessage.sendUserName}</td>
				<td>${fns:getDictLabel(sysMessage.type, "message_type", sysMessage.type)}</td>
				<td> 
					<fmt:formatDate value="${sysMessage.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${fns:getDictLabel(sysMessage.status, "yes_no", sysMessage.status)}</td>
				<shiro:hasPermission name="sys:sysMessage:edit"><td>
    				<a href="">详情</a>
    				<a href="">回复</a>
					<a href="${ctx}/sys/sysMessage/delete?id=${sysMessage.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>