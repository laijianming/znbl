$(function() {
	checkLogin();
	var localStorage = window.localStorage;
	var token = localStorage.getItem("token");
	var userId = localStorage.getItem("userId");
	$(".token").val(token);
	$(".userId").val(userId);
	$("#login-out").click(function(){
		var localStorage=window.localStorage;
		localStorage.clear();
	}); 
	addPageCss();
	haruthink();
});

/* ================================================
info
================================================ */
var curWwwPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
var localhostPaht=curWwwPath.substring(0,pos);
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var realPath=localhostPaht+projectName;

var page = function(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchform").submit();
	return false;
};

var checkLogin = function(){
	var localStorage = window.localStorage;
	var username = localStorage.getItem("username");
	if(username!=null&&username!=''&&username!=' '){
		$(".user-name").text(username);
	}
}

var addPageCss = function(){
	$(".pagination li").css({
		display: "inline"
	});
	$(".pagination li a").css({
		color: "black",
    	float: "left",
    	padding: "8px 16px",
    	textDecoration: "none",
        border: "1px solid #ddd"
	});
	$(".pagination").find(".controls").find("a").css({
		color: "black",
    	float: "left",
    	padding: "8px 16px",
    	textDecoration: "none",
    	border: "0px solid #ddd"
	});
	$(".pagination").find(".controls").find("a").find("input").css({
		height: "auto",
		width: "40px"
	});
}

/* ================================================
haruthink text
================================================ */
var haruthink = function(){
    setInterval(function(){
        initText();
    },5000);
}

var initText = function() {
    var thought = $(".thought");
    var num = Math.floor((Math.random() * 10) + 1);
    var text = "";
    switch (num) {
        case 1:
            text = "欢迎━(*｀∀´*)ノ亻!";
            break;
        case 2:
            text = "是时候休息一下了";
            break;
        case 3:
            text = "今天该做些什么呢？";
            break;
        case 4:
            text = "(⊙o⊙)…";
            break;
        case 5:
            text = "应该说些什么？";
            break;
        case 6:
            text = "你好，我是Haru";
            break;
        case 7:
            text = "想睡觉了";
            break;
        case 8:
            text = "该打招呼了...";
            break;
        case 9:
            text = "我在哪 (°□°；) ";
            break;
        case 10:
            text = "噗噗噗";
            break;
    }
    thought.text(text);
}

