<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图册管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
			
			//动态为图册加入<a>
		$("#treeTable tr:gt(0)").each(function(i){
			    var index=$(this).children("td").eq(2).text().replace(/\t|\n/ig,"");
			    if(index =="图册"){
			    	var rowId=$(this).children("td").eq(1).text();
			    	var td_4=$(this).children("td").eq(5);
			    	var append=td_4.html();
			    	var temp="<a onclick=\"openDialog('"+rowId+"','1')\">查看图册</a>&nbsp;<a onclick=\"openDialog('"+rowId+"','2')\">添加图片</a>";
			    	append+=temp;
			    	td_4.html(append);
			    }
			}); 
			
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		};
		function openDialog(id,type){
			//alert(id+"--"+ type);
			var title="";
	    	if(type==1){//查看
	    		$("#iframe").attr("src","${ctx}/sys/sysPic/showPic?id=" + id); 
	    		title="查看";
	    	}else if(type==2){//添加
	    		$("#iframe").attr("src","${ctx}/sys/sysPic/addPic?id=" + id); 
	    		title="添加";
	    	}
			$("#dialog").dialog({
			bgiframe: true,
	        resizable: true, //是否可以重置大小
	        height: 500, //高度
	        width: 626, //宽度
	        draggable: false, //是否可以拖动。
	        title: title,
	        modal: true,
	        show : {
				effect : 'drop',
				direction : 'up'
			}
			}); 
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysPic/">图册列表</a></li>
		<shiro:hasPermission name="sys:sysPic:edit"><li><a href="${ctx}/sys/sysPic/form">添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysPic" action="${ctx}/sys/sysPic/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="125" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>类型</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:sysPic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/sysPic/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td style="display:none">{{row.id}}</td>
			<td>
				{{row.type}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<shiro:hasPermission name="sys:sysPic:edit"><td>
   				<a href="${ctx}/sys/sysPic/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/sysPic/delete?id={{row.id}}" onclick="return confirmx('确认要删除该树结构及所有子树结构吗？', this.href)">删除</a>
				<a href="${ctx}/sys/sysPic/form?parent.id={{row.id}}">添加下级树结构</a>

			</td></shiro:hasPermission>
		</tr>
	</script>
	
	
	<div style="display:none;overflow:hidden;padding:3px" id="dialog">
		<iframe frameborder="no" border="0" marginwidth="0" marginheight="0"
			id="iframe" scrolling="auto" width="100%" height="100%"></iframe>
	</div>
	
	
</body>
</html>