<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图册创建</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		function showPic(){
			
		};
		function addPic(){
			
		};
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysPic/formDialog?id=${sysPic.id}&parent.id=${sysPicparent.id}"><shiro:hasPermission name="sys:sysPic:edit">${not empty sysPic.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysPic:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysPic" action="${ctx}/sys/sysPic/saveDialog" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input id="bId" name="bId" type="hidden" value="${bId}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="125" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${sysPic.parent.id}" labelName="parent.name" labelValue="${sysPic.parent.name}"
					title="父类编号" url="/sys/sysPic/treeData" extId="${sysPic.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图库：</label>
			<div class="controls">
				<%-- <form:input path="picUrl" htmlEscape="false" maxlength="2000" class="input-xlarge "/> --%>
				<input type="button" value="查看"  onclick="showPic()"/>
				<!-- <input type="button" value="添加"  onclick="addPic()"/> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" htmlEscape="false" maxlength="10" class="input-xlarge ">
					<form:option value="图册">图册</form:option>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">0是被使用 1在图册中：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
				<span style="color:red;">Tip:越小越前</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:sysPic:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="javascript:window.parent.closeDialog('#dialog_1');"/>
		</div>
	</form:form>
</body>
</html>