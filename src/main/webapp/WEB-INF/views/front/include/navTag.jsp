<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div class="navigation">
		<img src="${frontStatic}/index/images/logo.png"></img> <span><a id="a_login"
			href="${ctx}/front/login/">登录</a>|<a id="a_register" href="${ctx}/register/">注册</a></span>
	</div>

<script>
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

$(function() {
	$("#login-out").click(function(){
		var localStorage=window.localStorage;
		localStorage.clear();
	});  
})
</script>
