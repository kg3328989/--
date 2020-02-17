<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 固定项目管理</title>

<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/plugins/sweetalert/sweetalert.css"
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
						<li><a
							href="${pageContext.request.contextPath }/login/list?main">Home</a></li>
						<li class="active"><strong>工资项目管理</strong></li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">
							<div class="col-lg-12">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h3 align="center">员工固定工资修改</h3>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
									<div class="ibox-content">
										<form
											action="${pageContext.request.contextPath}/fixed/${requestScope.empId }/update?update"
											method="post" class="form-horizontal" id="Update">
											<div class="form-group">
												<label class="col-lg-2 control-label">员工编号</label>
												<div class="col-lg-10">
													<input type="text" disabled=""
														placeholder="${requestScope.empId }" class="form-control">
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<div class="form-group">
												<label class="col-sm-2 control-label">员工姓名</label>
												<div class="col-sm-10">
													<c:forEach items="${requestScope.empList }" var="emp">
														<c:if test="${emp.employeeId == requestScope.empId}">
															<input type="text" disabled=""
																placeholder="${emp.employeeName}" class="form-control">

														</c:if>
													</c:forEach>
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<div class="form-group">
												<label class="col-sm-2 control-label">部门名称</label>
												<div class="col-sm-10">
													<c:forEach items="${requestScope.deptList }" var="dept">
														<c:if test="${dept.departmentId == requestScope.deptId}">
															<input type="text" disabled=""
																placeholder="${dept.departmentName }"
																class="form-control">
														</c:if>
													</c:forEach>
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<div class="form-group">
												<label class="col-sm-2 control-label">工资类型名称</label>
												<div class="col-sm-10">
													<select class="form-control m-b" id="fSId" onchange="fun()">
														<option value="">请选择...</option>
														<c:forEach items="${requestScope.fsList }" var="fs">
															<c:forEach items="${requestScope.pojsList }" var="pojs">
																<c:if test="${pojs.projectId == fs.projectId}">
																	<option value="${fs.fixedSalaryId},${fs.salary}">${pojs.projectName}</option>
																</c:if>
															</c:forEach>
														</c:forEach>
													</select>
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">工资数值修改为</label>
												<div class="col-sm-10">
													<input type="hidden" name="fixedSalaryId"
														id="fixedSalaryId" value="" class="form-control">
													<input type="text" name="salary" id="salary" value=""
														class="form-control">
												</div>
											</div>
											<div class="hr-line-dashed"></div>
											<div class="form-group">
												<div class="col-sm-3 col-sm-offset-2">
													<button class="btn btn-primary demo3" id="save_btn"
														type="button">保存</button>
												</div>
											</div>
										</form>
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
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
	<!-- Sweet alert -->
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script>
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
		function fun() {
			var fs = document.getElementById("fSId");
			/* alert("你选择了：" + fs.value);
			var name = 'abc,def,ghi';
			var names = name.split(",");
			for(var j = 0 ; j< names.length;j++){
				alert(j + names[j]);
			} */
			var result = fs.value.split(",");
			for (var i = 0; i < result.length; i++) {
				if (i == 0) {
					/* alert("你选择了：" + fs.value + result[i]); */
					var fsidtext = document.getElementById("fixedSalaryId");
					fsidtext.value = result[i];
				}
				if (i == 1) {
					/* alert("你选择了：" + fs.value + result[i]); */
					var fstext = document.getElementById("salary");
					fstext.value = result[i];
				}
			}
		}
		$(function() {
			$("#save_btn").click(
					function() {

						var projectId = document.getElementById("fSId");

						if (projectId.value == "") {
							swal({
								title : "未选定工资类型名称!",
								text : "请选定工资类型名称",
								type : "warning"
							});
							return;
						}
						var testTag = /^(0|[1-9][0-9]){0,11}$/;
						if (!testTag.test($("#fixedSalaryId").val())
								|| $("#fixedSalaryId").val() == '') {
							swal({
								title : "警示",
								text : "请输入正确的工资.",
								type : "warning"
							});
							$("#fixedSalaryId").focus();
							return;
						}
						//提交表单
						$("#Update").submit();
					});
		});
	</script>

</body>

</html>