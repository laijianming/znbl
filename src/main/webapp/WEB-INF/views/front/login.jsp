<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>${fns:getConfig('productName')}后台登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${frontStatic}/css/supersized.css">
<link rel="stylesheet" href="${frontStatic}/css/login.css">
<link href="${frontStatic}/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="${frontStatic}js/html5.js"></script>
<![endif]-->
<script>
if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
	alert('未登录或登录超时。请重新登录，谢谢！');
	top.location = "${ctx}";
}
</script>
</head>
<body>
<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="page-container">
		<div class="main_box">
			<div class="login_box">
				<div class="login_logo" style="text-align:left;font-size:30px">
						<img alt="" src="${ctxStatic}/images/logo.png" />&nbsp;&nbsp;
							<span style="color:#ee9e19;">${fns:getConfig('productName')}</span>
						<div class="header">
						<%-- <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
							<label id="loginError" class="error">${message}</label>
						</div> --%>
					 	</div>
				</div>

				<div class="login_form">
					<form action="${ctx}/login" id="loginForm" method="post">
						<div class="form-group">
							<input id="username" name="username" type="text"
								placeholder="用户名" class="form-control x319 in required"
								autocomplete="off">
						</div>
						<div class="form-group">
							<input id="password" name="password" type="password"
								placeholder="密码" class="password form-control x319 in required">
						</div>
						
						 <c:if test="${isValidateCodeLogin}"> 
						<div class="form-group">
							 <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0; height:34px;width:75px;" />
						</div>
						 </c:if> 

						<div class="form-group space">
							<label style="width: 15%;"></label>
							<input type="submit" id="submit_btn"
								class="btn btn-default btn-lg"
								style="background-color: #5fb0b0;color:white;" value=" 登 录 " />&nbsp;&nbsp;
							<label style="width: 5%;"></label> 
							<input type="reset" value=" 重 置 " class="btn btn-default btn-lg">
						</div>
					</form>
				</div>
				
				<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
							<label id="loginError" class="error">${message}</label>
				</div>
			</div>
			
			<!-- 	<div class="bottom">Copyright &copy; 2016 - 2016  </div> -->
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery/jquery-migrate-1.1.1.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${frontStatic}/js/supersized.3.2.7.min.js"></script>
	<script type="text/javascript">
	$(function() {
		$("#loginForm").validate({
			rules: {
				validateCode: {remote: "${ctx}/servlet/validateCodeServlet"}
			},
			messages: {
				username: {required: "请填写用户名."},password: {required: "请填写密码."},
				validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
			},
			submitHandler: function(form){
				form.submit();
			},
			errorLabelContainer: "#messageBox",
			errorPlacement: function(error, element) {
				error.appendTo($("#loginError").parent());
			} 
		});
	});
</script>
	<script type="text/javascript">
	jQuery(function($){
	    $.supersized({
	        slide_interval     : 4000, // Length between transitions
	        transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
	        transition_speed   : 1000,    // Speed of transition
	        performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
	        // Size & Position
	        min_width          : 0,    // Min width allowed (in pixels)
	        min_height         : 0,    // Min height allowed (in pixels)
	        vertical_center    : 1,    // Vertically center background
	        horizontal_center  : 1,    // Horizontally center background
	        fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
	        fit_portrait       : 1,    // Portrait images will not exceed browser height
	        fit_landscape      : 0,    // Landscape images will not exceed browser width
	        // Components
	        slide_links        : 'blank',// Individual links for each slide (Options: false, 'num', 'name', 'blank')
	        slides             : [    // Slideshow Images
	                                 {image : '${frontStatic}/image/backgrounds/0.jpg'},
					 				{image : '${frontStatic}/image/backgrounds/1.jpg'},
									{ image : '${frontStatic}/image/backgrounds/2.jpg'}
	                       ]
	    });

	});
	</script>
</body>
</html>
