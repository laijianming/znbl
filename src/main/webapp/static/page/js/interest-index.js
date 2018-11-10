$(function() {
	checkLogin();
	$(".footer").css("position","static");
	var localStorage = window.localStorage;
	var token = localStorage.getItem("token");
	var userId = localStorage.getItem("userId");
	$(".token").val(token);
	$(".userId").val(userId);
})