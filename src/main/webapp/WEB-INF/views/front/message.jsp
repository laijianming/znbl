<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>操作-提示</title>
<link href="${frontStatic}/css/mess.css" rel="stylesheet" />

</head>
<body class="hold-transition login-page">

	<div id="login-container">
		<div id="login-header" style="color: white">用户操作提示</div>
		<div id="login-content" class="clearfix">
		 ${message}
			<br />
			<c:if test="${not empty redirectUrl }">
			 <span id="showTimer"></span><br />
			</c:if>
		    <a href="javascript:history.back();" style="color:red;">返回上页</a>
			<br />
			<a href="${ctx}/" style="color:red;">返回首页</a>
		</div>
		<div id="login-extra">
		</div>
	</div>
</body>
<script>

var InterValObj; //timer变量，控制时间
var curCount =5;

function setOperation(){
	if (curCount <= 0) {
		window.clearInterval(InterValObj);//停止计时器
		InterValObj = null;
		window.location.href="${ctx}/user/info?redirectUrl=${redirectUrl}";
	} else {
		
		curCount--;
		$("#showTimer").html("在" + curCount + "秒内将自动跳转");
	}
}

$(function(){
	var url = "${redirectUrl}";
	if(url){
		$("#showTimer").html("在" + curCount + "秒内将自动跳转");
		InterValObj = window.setInterval(setOperation, 1000); 
	}
	
});

</script>
</html>
