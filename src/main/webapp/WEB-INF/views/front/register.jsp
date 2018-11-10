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
<link rel="stylesheet" type="text/css"
	href="${frontStatic}/index/silme.css">
<script type="text/javascript"
	src="${frontStatic}/index/js/easeljs-0.8.2.min.js"></script>
<script type="text/javascript" src="${frontStatic}/index/js/silme.js"></script>
<script type="text/javascript" src="${ctxStatic}/algorithm/md5.js"></script>

<!-- 弹出框 -->
<script src="${ctxStatic}/alert/js/bootstrap/js/bootstrap.min.js"></script>
<link href="${ctxStatic}/alert/js/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.css"
	rel="stylesheet" />
<script src="${ctxStatic}/alert/js/sh_alert.js"></script>

</head>
<script>
	$(function() {
		$("#phone-code-button").click(function(){
			var phone = $("#phone").val();
			if(phone!=''&&phone!=' '){
				$.ajax({
					type : "post",
					url : "${ctx}/send-message/",
					data : {
						"phone" : phone
					},
					dataType : "json",
					success : function(data) {
						if (data.code == "0") {
							
						} else {
							shalert("短信发送失败!");
						}
					}
				});
			}
			return false;
		});

		$("#register").click(function() {
			var username = $("#username").val();
			var phoneCode = $("#phone-code").val();
			var stop1 = false;
			var stop2 = false;
			//用户名检测
			$.ajax({
				url : "${ctx}/checkLoginName/" + username,
				dataType : "json",
				async: false,
				success : function(data) {
					if (data.check == "true") {
						shalert("1");
						stop1 = true;
					} else {
						shalert("用户名已存在!");
					}
				}
			});
			//手机验证码检测
			$.ajax({
				url : "${ctx}/check-phone-code/",
				type : "post",
				data : {
					"enterCode" : phoneCode
				},
				async: false,
				dataType : "json",
				success : function(data) {
					if (data.code == "0") {
						shalert("2");
						stop2 = true;
					} else {
						shalert("手机验证码错误!");
					}
				}
			});
			if(stop1==true&&stop2==true){
				var password = $("#password").val();
				$("#password").val($.md5(password));
				$("#register").submit();
				return true;
			}
			return false;
		});

		
		$("#registerForm").validate({
			rules : {
				validateCode : {
					remote : "${ctx}/servlet/validateCodeServlet"
				}
			},
			messages : {
				validateCode : {
					remote : "验证码不正确,再仔细看看？",
					required : "验证码是不是还没填写?"
				}
			},
			submitHandler : function(form) {
				form.submit();
			},
			errorLabelContainer : "#messageBox",
			errorPlacement : function(error, element) {
				error.appendTo($("#loginError").parent());
			}
		});
	});
</script>
<body>
	<%@ include file="/WEB-INF/views/front/include/navTag.jsp"%>

	<div class="container padding-big register">
		<h2 class="text-center margin-big-bottom">注册</h2>
		<div class="main">
			<form method="post" class="form-x form-auto inputForm"
				action="${ctx}/registe/" id="registerForm">
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
				<div class="form-group">
					<div class="label">
						<label for="email"> 邮箱:</label>
					</div>
					<div class="field">
						<input type="email" class="input" id="email" name="email"
							size="50" data-validate="required:必填" placeholder="请输入邮箱" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="phone"> 手机:</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="phone" name="phone"
							size="50" data-validate="required:必填" placeholder="请输入手机" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="phone-code"> 短信验证码:</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="phone-code" name="phone-code"
							size="50" data-validate="required:必填" placeholder="请输入验证码" style="width: 280px"/>
						<button id="phone-code-button" class="btn btn-default phone-code-button">获取验证码</button>
					</div>
				</div>

				<div class="validateCode_box">
					<div class="form-group">
						<sys:validateCode name="validateCode"
							inputCssStyle="margin-bottom:0; height:34px;width:75px;" />
					</div>
					<div id="messageBox" style="color: red;">
						<label id="loginError" class="error"></label>
					</div>
				</div>

				<div class="form-button" align="center" style="margin-left: 0px;">
					<br>
					<button id="register" name="register" class="button" type="submit">
						注册</button>
					<button class="button" type="button"
						onclick="javascript:history.back(-1);">返回</button>
				</div>
			</form>
		</div>
	</div>
	<br>
	<br>
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