<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="renderer" content="webkit">
<title>智能辩论平台</title>
<link rel="stylesheet" href="${frontStatic}/index/pintuer.css" />
<link rel="stylesheet" href="${frontStatic}/index/stylesheet.css" />
<script src="${frontStatic}/js/jquery.min.js"></script>
<script src="${frontStatic}/js/pintuer.js"></script>
<script src="${frontStatic}/js/jquery.validate.js"></script>
<script src="${frontStatic}/js/messages_zh.js"></script>
<link rel="stylesheet" type="text/css" href="${frontStatic}/index/silme.css">
<script type="text/javascript" src="${frontStatic}/index/js/easeljs-0.8.2.min.js"></script>
<script type="text/javascript" src="${frontStatic}/index/js/silme.js"></script>
<script type="text/javascript" src="${ctxStatic}/algorithm/md5.js"></script>


<!-- 弹出框 -->
<script src="${ctxStatic}/alert/js/bootstrap/js/bootstrap.min.js"></script>
<link href="${ctxStatic}/alert/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" />
<script src="${ctxStatic}/alert/js/sh_alert.js"></script>

<!-- 页面js文件 -->
<script src="${ctxStatic}/page/js/comment.js"></script>
<script src="${ctxStatic}/page/js/userlogin.js"></script>

</head>
<script>
	
</script>
<body>
	<%@ include file="/WEB-INF/views/front/include/navTag.jsp"%>

	<div class="container padding-big register">
		<h2 class="text-center margin-big-bottom">登录</h2>
		<div class="main">
			<form method="post" class="form-x form-auto inputForm" action="${ctx}/userLogin/" id="loginForm">
				
				<!--  先前url -->
				<input type="hidden" value="${gourl}" name="gourl">

				<div class="form-group">
					<div class="label">
						<label for="username"> 登录名:</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="username" name="username"
							size="50" data-validate="required:必填" placeholder="请填写登录名" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="password"> 密码:</label>
					</div>
					<div class="field">
						<input type="password" class="input" id="password" name="password"
							size="50" data-validate="required:必填" placeholder="请输入密码" />
					</div>
				</div>
				<div class="validateCode_box">
					<div class="form-group">
						<sys:validateCode name="validateCode"
							inputCssStyle="margin-bottom:0; height:34px;width:75px;" />
					</div>
					<div id="messageBox" style="color: red;">
						<label id="loginErrorMessage" class="error"></label>
					</div>
				</div>

				<div class="form-button" align="center" style="margin-left: 0px;">
					<br>
					<button id="login" name="login" class="button" type="submit">
						登录</button>
					<button class="button" type="button"
						onclick="javascript:history.back(-1);">返回</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/front/include/silme.jsp"%>
	<%@ include file="/WEB-INF/views/front/include/footer.jsp"%>


	<!--需要添加的全局html-->
	<div id="dialogalert" title="系统提示：">
		<p class="msgcontent" style="margin: 10px auto; text-align: center;">

		</p>
	</div>

	<!--end需要添加的全局html-->

</body>
</html>