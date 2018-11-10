<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到智能辩论平台</title>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<link rel="stylesheet" href="${frontStatic}/index/index.css"
	media="screen">
<script type="text/javascript"
	src="${frontStatic}/index/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${frontStatic}/index/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="${frontStatic}/index/js/index.js"></script>

<!-- 弹出框 -->
<script src="${ctxStatic}/alert/js/bootstrap/js/bootstrap.min.js"></script>
<link href="${ctxStatic}/alert/js/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.css"
	rel="stylesheet" />
<script src="${ctxStatic}/alert/js/sh_alert.js"></script>

<script type="text/javascript">
	$(function() {
		var message = $("#message").text();
		if (message != '') {
			shalert(message);
		}
		checkLogin();
		$("#login-out").click(function(){
			var localStorage=window.localStorage;
			localStorage.clear();
		});       
	})
	function checkLogin() {
		var localStorage = window.localStorage;
		var username = localStorage.getItem("username");
		if(username!=null&&username!=''&&username!=' '){
			var loginform = $('.navigation');
			var logo = "<img src=\"${frontStatic}/index/images/logo.png\"></img>";
			var login_name = "<span class=\"float-right username\"><a href=\"\">&or;</a><ul class=\"\"><li><a href=\"#\">消息中心</a></li><li><a href=\"#\">设置</a></li></ul></span>";
			var login_exit = "<span><a id=\"login-out\" class=\"float-right\" href=\"${ctx}/login-out/\">退出登录</a></span>";
			loginform.html(logo + login_exit + login_name);
			$(".navigation .username>a").html(username);
		}
	}
</script>
</head>
<body>
	<div class="navigation">
		<img src="${frontStatic}/index/images/logo.png"></img> <span><a
			id="a_login" href="${ctx}/front/login/">登录</a><font>|</font><a
			id="a_register" href="${ctx}/register/">注册</a></span>
	</div>
	<div class="welcomePage">
		<div class="arrowDown"></div>
		<video src="${ctxStatic}/front/video/01.webm" type="video/webm"
			autoplay>
		</video>
	</div>
	<div class="introduction">
		<div class="introPage introPage3">
			<h2>亮点三:可创建属于自己的辩论话题</h2>
		</div>
		<div class="introPage introPage2">
			<h2>亮点二:更公平的发言评判功能</h2>
		</div>
		<div class="introPage introPage1">
			<h2>亮点一:更强、更严谨的语言管理监督功能</h2>
		</div>
	</div>
	<div class="selectMenu">
		<div class="menuContainer">
			<div class="menu" id="menu1" position="left">
				<figure class="ball">
					<div class="menuContent">
						<span>国家资讯</span> <img src="${frontStatic}/index/images/33.png"></img>
						<span>关注身边国家事</span>
					</div>
					<span class="ball-shadow"></span>
				</figure>
			</div>
			<div class="menu" id="menu2" position="front">
				<figure class="ball focusBall">
					<div class="menuContent">
						<span>校园资讯</span> <img src="${frontStatic}/index/images/11.png"></img>
						<span>高校最新消息</span>
					</div>
					<span class="ball-shadow"></span>
				</figure>
			</div>
			<div class="menu" id="menu3" position="right">
				<figure class="ball">
					<div class="menuContent">
						<span>兴趣专栏</span> <img src="${frontStatic}/index/images/44.png"></img>
						<span>推送最感兴趣消息</span>
					</div>
					<span class="ball-shadow"></span>
				</figure>
			</div>
		</div>
		<div class="arrowDown"></div>
	</div>
	<div class="aboutUs">
		<h1>关于我们</h1>
	</div>
	<div class="siderBar">
		<div class="option">
			<div class="circle"></div>
		</div>
		<div class="option">
			<div class="circle"></div>
		</div>
		<div class="option">
			<div class="circle"></div>
		</div>
		<div class="option">
			<div class="circle"></div>
		</div>
	</div>

	<c:if test="${!empty message}">
		<div id="message">${message}</div>
	</c:if>

	<div id="dialogalert" title="系统提示：">
		<p class="msgcontent" style="margin: 10px auto; text-align: center;">

		</p>
	</div>
</body>
</html>