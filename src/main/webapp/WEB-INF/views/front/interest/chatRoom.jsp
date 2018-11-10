<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
<link href="${frontStatic}/list/chat-room/css/chatRoom.css" rel="stylesheet">
</head>
<body>
<div id="wrap">
		<div id="statusBar">
			<div id="whatTime"></div>
			<div id="wireless"></div>
			<div id="battery"></div>
		</div>
		<div id="main">
			<div id="toolBar">
				<img id="gobackBtn" src="${frontStatic}/list/chat-room/img/goback.jpg">
				<h4 id="userName"></h4>
				<img id="personBtn" class="topRightBtn" src="${frontStatic}/list/chat-room/img/person.jpg">
			</div>
			

			<div class="dialogLeft">
				<img src="${frontStatic}/list/chat-room/img/aniang.jpg">
				<span></span>
				<p>天气好晴朗1~</p>
			</div>

			<div class="dialogRight">
				<p>天气好晴朗2~</p>
				<span></span>
				<img src="${frontStatic}/list/chat-room/img/aniang.jpg">
			</div>

			<div class="dialogLeft">
				<img src="${frontStatic}/list/chat-room/img/aniang.jpg">
				<span></span>
				<p>天气好晴朗1~</p>
			</div>

			<div class="dialogRight">
				<p>天气好晴朗2~</p>
				<span></span>
				<img src="${frontStatic}/list/chat-room/img/aniang.jpg">
			</div>


		</div>
		<div id="inputBar">
			<a href="#" id="voiceBtn">
				<img src="${frontStatic}/list/chat-room/img/voiceBtn.jpg" alt="语音">
			</a>
			<input type="text">
			<a href="#" id="gengduo">
				<img src="${frontStatic}/list/chat-room/img/gengduo.jpg" alt="更多">
			</a>
			<a href="#" id="biaoqing">
				<img src="${frontStatic}/list/chat-room/img/biaoqing.jpg" alt="表情">
			</a>
		</div>
	</div>

</body>
</html>