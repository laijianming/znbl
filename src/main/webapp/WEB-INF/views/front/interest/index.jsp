<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兴趣资讯</title>
<link rel="stylesheet" href="${frontStatic}/index/pintuer.css" />
<link rel="stylesheet" href="${frontStatic}/index/stylesheet.css" />
<script src="${frontStatic}/js/jquery.min.js"></script>
<script
	src="${frontStatic}/interest/front/assets/js/jquery.scrolly.min.js"></script>
<%-- <script src="${frontStatic}/interest/front/assets/js/jquery.poptrox.min.js"></script> --%>
<script src="${frontStatic}/interest/front/assets/js/skel.min.js"></script>
<script src="${frontStatic}/interest/front/assets/js/util.js"></script>
<script src="${frontStatic}/interest/front/assets/js/main.js"></script>
<link rel="stylesheet"
	href="${frontStatic}/interest/front/assets/css/main.css" />

<!-- 页面js文件 -->
<script src="${ctxStatic}/page/js/comment.js"></script>
<script src="${ctxStatic}/page/js/interest-index.js"></script>
</head>
<body style="position: relative;">
	<%@ include file="/WEB-INF/views/front/include/navTag.jsp"%>

	<!-- Banner -->
	<section id="banner">
		<div class="inner">
			<header>
				<h1>兴趣专区选择</h1>
				<br />
				<p>请选择您喜欢的话题</p>
			</header>
			<a href="#main" class="more">Learn More</a>
		</div>
	</section>

	<!-- Main -->
	<div id="main">
		<div class="inner">

			<!-- Boxes -->
			<div class="thumbnails">

				<div class="box">
					<a class="image fit"><img src="${frontStatic}/interest/front/images/pic01.jpg" alt="" /></a>
					<div class="inner">
						<h3>动漫</h3>
						<p>最新最热的动漫话题，请谈谈你的看法</p>
						<form action="${ctx}/interest/comic/signuplist/" method="post">
							<input name="token" class="token" type="hidden">
							<input name="userId" class="userId" type="hidden">
							<button id="comic" class="button fit" type="submit">进入</button>
						</form>
					</div>
				</div>

				<div class="box">
					<a class="image fit"><img
						src="${frontStatic}/interest/front/images/pic02.jpg" alt="" /></a>
					<div class="inner">
						<h3>电影</h3>
						<p>最新最热的电影话题，请谈谈你的看法</p>
						<a href="" class="button style2 fit">进入</a>
					</div>
				</div>

				<div class="box">
					<a class="image fit"><img
						src="${frontStatic}/interest/front/images/pic03.jpeg" alt="" /></a>
					<div class="inner">
						<h3>游戏</h3>
						<p>最新最热的游戏话题，请谈谈你的看法</p>
						<a href="" class="button style3 fit">进入</a>
					</div>
				</div>

				<div class="box">
					<a class="image fit"><img
						src="${frontStatic}/interest/front/images/pic04.jpg" alt="" /></a>
					<div class="inner">
						<h3>科技</h3>
						<p>最新最热的科技话题，请谈谈你的看法</p>
						<a href="" class="button style2 fit">进入</a>
					</div>
				</div>

				<div class="box">
					<a class="image fit"><img
						src="${frontStatic}/interest/front/images/pic05.jpg" alt="" /></a>
					<div class="inner">
						<h3>教育</h3>
						<p>最新最热的教育话题，请谈谈你的看法</p>
						<a href="" class="button style3 fit">进入</a>
					</div>
				</div>

				<div class="box">
					<a class="image fit"><img
						src="${frontStatic}/interest/front/images/pic06.jpg" alt="" /></a>
					<div class="inner">
						<h3>其它</h3>
						<p>最新最热的其它话题，请谈谈你的看法</p>
						<a href="" class="button fit">进入</a>
					</div>
				</div>

				<div class="box">
					<div class="inner">
						<a href="${ctx}/" class="button fit">返回主页</a>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/front/include/footer.jsp"%>
</body>
</html>