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
        };
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">平台留言列表</a></li>
		<shiro:hasPermission name="interest:comic:edit"><li><a href="">添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comic" action="${ctx}/interest/comic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="createByName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>点击数</th>
				<th>正方人数</th>
				<th>反方人数</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>生效时间</th>
				<th>失效时间</th>
				<shiro:hasPermission name="interest:comic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comic">
			<tr>
				<td>${comic.title}</td>
				<td>${comic.hits}</td>
				<td>${comic.claimNumber}</td>
				<td>${comic.counterclaimNumber}</td>
				<td>${comic.createByName}</td>
				<td> 
					<fmt:formatDate value="${comic.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td> 
					<fmt:formatDate value="${comic.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td> 
					<fmt:formatDate value="${comic.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="interest:comic:edit"><td>
    				<a href="">详情</a>
					<a href="" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>