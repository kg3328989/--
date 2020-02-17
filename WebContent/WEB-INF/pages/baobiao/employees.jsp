<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 报表管理</title>

<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
</head>

<body>

	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<a class="close-canvas-menu"><i class="fa fa-times"></i></a>
				<!--右侧菜单开始-->
				<%@include file="../inc/menu.jsp"%>
				<!--右侧菜单开结束-->
			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<!--上侧导航条开始-->
				<%@include file="../inc/header.jsp"%>
				<!--上侧导航条结束-->
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<h2>DHC工资管理系统</h2>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/login/list?form">Home</a></li>
						<li class="active"><strong>/员工查询</strong></li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-20 col-lg-offset-1">
						<div class="ibox">

							<div class="col-md-10">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">员工工资统计</h3>
									</div>
									<!-- 按员工编号查询 -->
									<div class="panel-body">
										<form class="form-horizontal"
											action="${pageContext.request.contextPath}/baobiao/list4">
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-1 control-label">员工编号</label>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="employeeId" name="employeeId">
												</div>
												<button type="button" id="sumbtn" class="btn btn-default">查询</button>
											</div>
										</form>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">查询结果</h3>
										</div>
										<div class="panel-body">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>发放年月</th>
														<th>员工编号</th>
														<th>员工姓名</th>

														<c:forEach items="${requestScope.pojsList }" var="pojs">
															<th>${pojs.projectName }</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${requestScope.ssList }" var="ss">
														<tr>
															<!-- 发放年月 -->
															<td><fmt:formatDate value="${ss.payDate}"
																	pattern="yyyy-MM-dd hh:mm:ss" /></td>
															<!-- 编号 -->
															<td>${requestScope.empId }</td>
															<!-- 姓名 -->
															<td>${requestScope.empName }</td>
															<!-- 对应工资值 -->
															<c:forEach items="${requestScope.pojsList }" var="pojs">
																<c:set var="temp" value="0"></c:set>
																<c:forEach items="${requestScope.ssdList }" var="ssd">
																	<c:if
																		test="${ss.salarySettlementId == ssd.salarySettlementId}">
																		<c:if test="${pojs.projectId == ssd.projectId}">
																			<c:set var="temp" value="1"></c:set>
																			<td>${ssd.money}</td>
																		</c:if>
																	</c:if>
																</c:forEach>
																<%-- <td>${temp == 0}</td> --%>
																<c:if test="${temp == 0}">
																	<td>0</td>
																</c:if>
															</c:forEach>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

									</div>
								</div>

							</div>
						</div>
					</div>

				</div>


			</div>
			<!--底部显示-->
			<div class="footer">
				<div class="pull-right">
					<strong>大连华信培训中心定制二班</strong>第五组
				</div>
				<div>
					<strong>Web</strong> for DHC &copy; 2019-2020
				</div>
			</div>
		</div>
	</div>
	<!-- Mainly scripts -->
	<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script>
	
	
	
		$("#sumbtn").click(function() {
			var testTag = /^[1-9][0-9]{1,10}$|^[1-9]$/;
			if (!testTag.test($("#employeeId").val()) || $("#employeeId").val() == '') {
				swal({
					title : "警示",
					text : "请输入正确的员工编号.",
					type : "warning"
				});
				$("#employeeId").focus();
				return;
			}
			$('form').submit();
		});
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
		$(document).ready(function() {
			$('#data_4 .input-group.date').datepicker({
				minViewMode : 1,
				keyboardNavigation : false,
				forceParse : false,
				autoclose : true,
				todayHighlight : true
			});
		});
	</script>

</body>

</html>