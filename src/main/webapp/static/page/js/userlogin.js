$(function() {
	//验证码bug设置模拟点击
	setTimeout(function() {
		$("a.mid.validateCodeRefresh").click();
	}, 500);

	$("#loginForm").validate({
		rules : {
			validateCode : {
				remote : variable.hostName + "servlet/validateCodeServlet"
			}
		},
		messages : {
			validateCode : {
				remote : "验证码不正确,换一张试试？",
				required : "验证码是不是还没填写?"
			}
		},
		submitHandler : function(form) {
			form.submit();
		},
		errorPlacement : function(error, element) {
			error.appendTo($("#loginErrorMessage").parent());
		}
	});

	$("#login").click(
			function() {
				var username = $("#username").val();
				var password = $("#password").val();
				password = $.md5(password);
				$.ajax({
					url : variable.hostName + "checkUserInfo/" + username + "/"+ password,
					dataType : "json",
					cache : false,
					success : function(data) {
						if (data.code == 1) {
							stop = false;
							var localStorage = window.localStorage;
							//写入信息
							localStorage.setItem("token", data.token);
							localStorage.setItem("username", username);
							localStorage.setItem("userId",data.userId);
							$("#password").val(password);
							$("#login").submit();
						}
						if (data.code == 0) {
							shalert("账号或密码有误!");
							stop = true;
						}
					}
				})
				if (stop) {
					return false;
				}
			});
});