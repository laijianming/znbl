// JavaScript Document
//支持Enter键登录
document.onkeydown = function(e) {
	if ($(".bac").length == 0) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			var obtnLogin = document.getElementById("submit_btn")
			obtnLogin.focus();
		}
	}
}

$(function() {
	// 提交表单
	$('#submit_btn').click(function() {
		show_loading();
		if ($('#loginName').val() == '') {
			show_err_msg('请填写用户名');
			$('#loginName').focus();
		} else if ($('#password').val() == '') {
			show_err_msg('请填写密码！');
			$('#password').focus();
		} else if ($('#yanzheng').val() == '') {
			show_err_msg('请填写验证码');
			$('#yanzheng').focus();
		} else {
			// ajax提交表单，#login_form为表单的ID。 如：
			$('#login_form').ajaxSubmit(function(data) {
			alert("A")});
			show_msg('正在为您登陆，请稍后...', '/');
		}
	});
});