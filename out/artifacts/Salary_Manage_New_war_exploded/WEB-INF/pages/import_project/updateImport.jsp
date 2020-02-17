<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 导入项目管理</title>

<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/static/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
</head>


<body>
	<div id="wrapper">

		<%@include file="../inc/menu.jsp"%>

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
						<li class="active"><strong>工资项目管理</strong>
						</li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal"
									action="${pageContext.request.contextPath}/import_project/${requestScope.importProjectsList.importProjectId}/update?update">
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">员工编号</label>
										<div class="col-sm-10">
											<input type="text" class="form-control"
												value="${requestScope.importProjectsList.employeeId }"
												readonly="readonly"> <br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">员工姓名</label>
										<div class="col-sm-10">
											<c:forEach items="${requestScope.employeesList}"
												var="employees">
												<c:if
													test="${requestScope.importProjectsList.employeeId == employees.employeeId }">
													<input type="text" class="form-control"
														value="${employees.employeeName }" readonly="readonly">
												</c:if>
											</c:forEach>
											<br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">部门编号</label>
										<div class="col-sm-10">
											<input type="text" class="form-control"
												value="${requestScope.importProjectsList.departmentId }"
												readonly="readonly"> <br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">导入项目</label>
										<div class="col-sm-10">
											<c:forEach items="${requestScope.projectsList}"
												var="projects">
												<c:if
													test="${requestScope.importProjectsList.projectId == projects.projectId }">
													<input type="text" class="form-control"
														value="${projects.projectName }" readonly="readonly">
												</c:if>
											</c:forEach>
											<br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">导入信息</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="importInfo"
												id="importInfo"
												value="${requestScope.importProjectsList.importInfo }">
											<br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">备注</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="remark" id="remark"
												value="${requestScope.importProjectsList.remark }">
											<br />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10 ">
											<button type="button" id="sumbtn" class="btn btn-primary">修改</button>
											<button type="reset" class="btn btn-primary">重填</button>
										</div>
									</div>
								</form>
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
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Data picker -->
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>


	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script>
		$("#sumbtn").click(function() {
			var testTag = /^[1-9][0-9]{1,10}$|^[1-9]$/;
			var testRemark = /^.{0,255}$/;
			if (!testTag.test($("#importInfo").val())) {
				swal({
					title : "警示",
					text : "请输入正确的导入信息.",
					type : "warning"
				});
				$("#importInfo").focus();
				return;
			}
			if (!testRemark.test($("#remark").val())) {
				swal({
					title : "警示",
					text : "请输入255个字符以内的备注.",
					type : "warning"
				});
				$("#remark").focus();
				return;
			} 
			$('form').submit();
		});
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
	</script>


</body>
</html>