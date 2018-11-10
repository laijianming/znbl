<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Kode is a Premium Bootstrap Admin Template, It's responsive, clean coded and mobile friendly">
<meta name="keywords"
	content="bootstrap, admin, dashboard, flat admin template, responsive," />
<title>动漫专区</title>

<!-- ========== Css And js Files ========== -->
<link href="${frontStatic}/list/common/css/root.css" rel="stylesheet">
<script type="text/javascript"
	src="${frontStatic}/list/common/js/jquery.min.js"></script>
<%-- <script src="${frontStatic}/list/common/js/bootstrap/bootstrap.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/plugins.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/bootstrap-select/bootstrap-select.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/easypiechart/easypiechart.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/easypiechart/easypiechart-plugin.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/sparkline/sparkline.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/sparkline/sparkline-plugin.js"></script>
<script type="text/javascript"
	src="${frontStatic}/list/common/js/common.js"></script>


<!-- Live2D Library -->
<script src="${ctxStatic}/live2d/src/live2d.min.js"></script>

<!-- Live2D Framework -->
<script src="${ctxStatic}/live2d/src/Live2DFramework.js"></script>

<!-- Live2D Script -->
<script src="${ctxStatic}/live2d/src/utils/MatrixStack.js"></script>
<script src="${ctxStatic}/live2d/src/utils/ModelSettingJson.js"></script>
<script src="${ctxStatic}/live2d/src/PlatformManager.js"></script>
<script src="${ctxStatic}/live2d/src/LAppDefine.js"></script>
<script src="${ctxStatic}/live2d/src/LAppModel.js"></script>
<script src="${ctxStatic}/live2d/src/LAppLive2DManager.js"></script>
<script src="${ctxStatic}/live2d/src/SampleApp1.js"></script>


<link href="${frontStatic}/list/common/css/common.css" rel="stylesheet">

<script>
	
</script>
</head>
<body onload="sampleApp1()">
	<!-- Start Page Loading -->
	<div class="loading">
		<img src="${frontStatic}/list/common/img/loading.gif" alt="loading-img">
	</div>
	<!-- End Page Loading -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START TOP -->
	<div id="top" class="clearfix">

		<!-- Start App Logo -->
		<div class="applogo">
			<a href="index.html" class="logo">智能辩论</a>
		</div>
		<!-- End App Logo -->

		<!-- Start Sidebar Show Hide Button -->
		<a href="#" class="sidebar-open-button"><i class="fa fa-bars"></i></a>
		<a href="#" class="sidebar-open-button-mobile"><i
			class="fa fa-bars"></i></a>
		<!-- End Sidebar Show Hide Button -->

		<!-- Start Searchbox -->
		<form:form id="searchform" modelAttribute="comic"
			action="${ctx}/interest/comic/signuplist/" method="post"
			class="searchform">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<input name="token" class="token" type="hidden">
			<input name="userId" class="userId" type="hidden">
			<form:input class="searchbox" id="searchtext" path="title"
				htmlEscape="false" maxlength="50"  placeholder="搜索话题"/>
			<span class="searchbutton"><i class="fa fa-search"></i></span>
		</form:form>


		<!-- Start Top Right -->
		<ul class="top-right">

			<li class="link"><a href="#" class="notifications">6</a></li>

			<li class="dropdown link"><a href="#" data-toggle="dropdown"
				class="dropdown-toggle profilebox"><img src="${frontStatic}/list/common/img/profileimg.png"
					alt="img"><b class="user-name">Jonathan Doe</b><span class="caret"></span></a>
				<ul class="dropdown-menu dropdown-menu-list dropdown-menu-right">
					<li role="presentation" class="dropdown-header">个人菜单</li>
					<li><a href="#"><i class="fa falist fa-inbox"></i>Inbox<span
							class="badge label-danger">4</span></a></li>
					<li><a href="#"><i class="fa falist fa-file-o"></i>Files</a></li>
					<li><a href="#"><i class="fa falist fa-wrench"></i>Settings</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa falist fa-lock"></i>
							Lockscreen</a></li>
					<li><a id="login-out" href="${ctx}/login-out/"><i class="fa falist fa-power-off"></i>
							登出</a></li>
				</ul></li>

		</ul>
		<!-- End Top Right -->

	</div>
	<!-- END TOP -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->


	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START SIDEBAR -->
	<div class="sidebar clearfix">

		<ul class="sidebar-panel nav">
			<li class="sidetitle">主菜单</li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-list-ul" aria-hidden="true"></i></span>模块选择<span class="caret"></span></a>
				<ul>
					<li><a href="icons.html">动漫<span
							class="label label-default">2</span></a></li>
					<li><a href="tabs.html">电影</a></li>
					<li><a href="buttons.html">游戏</a></li>
					<li><a href="panels.html">科技</a></li>
					<li><a href="notifications.html">教育</a></li>
					<li><a href="modal-boxes.html">其它</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-thumbs-o-up" aria-hidden="true"></i></span>评论区<span class="label label-danger">NEW</span><span
					class="caret"></span></a>
				<ul>
					<li><a href="icons.html">待评论区<span
							class="label label-default">2</span></a></li>
					<li><a href="tabs.html">已评论区</a></li>
				</ul></li>
			<li><a href="charts.html"><span class="icon color8"><i
						class="fa fa-bar-chart"></i></span>信息统计</a></li>
			<li><a href="charts.html"><span class="icon color8"><i
						class="fa fa-th-large" aria-hidden="true"></i></span>创建话题</a></li>
			<li><a href="widgets.html"><span class="icon color11"><i
						class="fa fa-cog" aria-hidden="true"></i></span>话题管理</a></li>
		</ul>

		<ul class="sidebar-panel nav">
			<li class="sidetitle">更多</li>
			<li><a href="grid.html"><span class="icon color15"><i
						class="fa fa-columns"></i></span>使用说明</a></li>
		</ul>

		<!-- <div class="sidebar-plan">
			Pro Plan<a href="#" class="link">Upgrade</a>
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="60"
					aria-valuemin="0" aria-valuemax="100" style="width: 60%;"></div>
			</div>
			<span class="space">42 GB / 100 GB</span>
		</div> -->

	</div>
	<!-- END SIDEBAR -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->


	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START CONTENT -->
	<div class="content">

		<!-- Start Page Header -->
		<div class="page-header">
			<h1 class="title">动漫专区</h1>
			<ol class="breadcrumb">
				<li class="active">我是一个经常笑的人，可我不是经常开心的人。 ——面码《未闻花名》</li>
			</ol>

			<!-- Start Page Header Right Div -->
			<div class="right">
				<div class="btn-group" role="group" aria-label="...">
					<a href="index.html" class="btn btn-light"></a> <a
						href="#" class="btn btn-light"><i class="fa fa-refresh"></i></a> <a
						href="#" class="btn btn-light"><i class="fa fa-search"></i></a> <a
						href="#" class="btn btn-light" id="topstats"><i
						class="fa fa-line-chart"></i></a>
				</div>
			</div>
			<!-- End Page Header Right Div -->

		</div>
		<!-- End Page Header -->


		<!-- //////////////////////////////////////////////////////////////////////////// -->
		<!-- START CONTAINER -->
		<div class="container-widget">

			<!-- Start First Row -->
			<div class="row">

				<!-- 判断是否有元素 -->
				<c:if test="${empty page.list}">
					<div class="col-md-12 col-lg-9">
					<h1 class="no-message">没有信息</h1>
					</div>
				</c:if>
				<c:if test="${!empty page.list}">
					<c:forEach items="${page.list}" var="comic">
						<!-- Start Orders -->
						<div class="col-md-12 col-lg-9">
							<div class="panel panel-widget">
								<div class="panel-title">
									${comic.title} <span class="label label-warning">196</span>
									<ul class="panel-tools">
										<li><a class="icon minimise-tool"><i
												class="fa fa-minus"></i></a></li>
										<li><a class="icon expand-tool"><i
												class="fa fa-expand"></i></a></li>
										<li><a class="icon closed-tool"><i
												class="fa fa-times"></i></a></li>
									</ul>
								</div>

								<div class="panel-body table-responsive">

									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<td colspan=2><img class="img-thumbnail img-responsive"
													src="${frontStatic}/list/common/img/profileimg.png" alt="img"></td>
												<td colspan=4 style="font-size: 13px">${comic.introduction}</td>
											</tr>
											<tr>
												<td colspan=2 style="font-size: 13px"><span class="glyphicon glyphicon-user"
													aria-hidden="true"></span>${comic.createByName}</td>
												<td style="font-size: 13px"><span class="glyphicon glyphicon-eye-open"
													aria-hidden="true"></span>23</td>
												<td style="font-size: 13px"><span class="fa fa-folder-open" aria-hidden="true"></span>${fns:getDictLabel(comic.type, 'topic_type',6)}</td>
												<td style="font-size: 13px"><span class="glyphicon glyphicon-time"
													aria-hidden="true"></span>
												<fmt:formatDate value="${comic.createDate}"
														pattern="yyyy-MM-dd" /></td>
												<td><a href="#" class="go-talk-page btn btn-default btn-sm">进入</a>
												</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div>
						<!-- End Orders -->
					</c:forEach>
				</c:if>
				<!-- Start live2d -->
				<div class="col-md-12 col-lg-3">
					 <p class="thought">今天该做些什么呢？</p>
					<p>
						<button id="btnChange" class="active" style="display: none;">Change
							Model</button>
					</p>

					<div>
						<canvas id="glcanvas" width="250" height="300">
            </canvas>
					</div>
				</div>
				<!-- End live2d -->

			</div>
			<!-- End First Row -->

			<!-- Start Second Row -->
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<!-- Start Page -->
					<nav aria-label="Page navigation">
					<div class="pagination">${page}</div>
					</nav>

					<!-- End Page -->
				</div>
			</div>
			<!-- End Second Row -->


		</div>
		<!-- END CONTAINER -->
		<!-- //////////////////////////////////////////////////////////////////////////// -->

		<!-- Start Footer -->
		<div class="row footer">
			<div class="col-md-12 text-center">
				版权所有 &copy; 2017-2018 大学生智能辩论平台</a>
			</div>
		</div>
		<!-- End Footer -->

	</div>
	<!-- End Content -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->

</body>
<script type="text/javascript">
$(".go-talk-page").click(function(){
	var storage = window.localStorage;
	var token = storage.token;
	var userId = storage.userId;
	$(".go-talk-page").attr("href", realPath+'/interest/comic/talk/?token='+token+'&userId='+userId);
})
</script>
</html>