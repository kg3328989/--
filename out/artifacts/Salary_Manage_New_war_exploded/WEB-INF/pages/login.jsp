<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>登录</title>

<link
	href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath }/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/backgroundstyle.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">

</head>

<body class="gray-bg">


	<div class="middle-box text-center loginscreen animated fadeInDown">
		<div>
			<div>

				<h1 class="logo-name">&nbsp;</h1>

			</div>
			<p style="font-family: '仿宋'; font-size: 30px; color: #E7EAEC;"
				align="center"; >欢迎使用本系统</p>
			<hr />
			<small style="color: #E7E7E7;">请检查好您的账号和密码是否输入正确哦</small>

			<form class="m-t" role="form"
				action="${pageContext.request.contextPath }/login/list"
				method="post">
				<div class="form-group">
					<input type="hidden" value="${requestScope.flag }" id="flag">
					<input type="text" class="form-control" placeholder="请输入用户名"
						name="userName">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="请输入密码"
						 name="userPassword">
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登录</button>

				<!-- <a href="login.html#"><small>忘记密码？</small></a> -->
			</form>

			<p class="m-t">
				<small>@dhee 第五组 制作; 2019</small>
			</p>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script
		src="${pageContext.request.contextPath }/static/js/jquery-2.1.1.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#flag").val() == "flag"){
				swal({
					title: "警示",
					text: "用户名或者密码错误.",
					type: "warning"
				});
				$("#userName").focus();
			}
		});
	</script>
</body>

</html>
