<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 导入项目管理</title>

<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<!-- Sweet Alert -->
<link
	href="${pageContext.request.contextPath}/static/css/plugins/sweetalert/sweetalert.css"
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
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">
							<div class="ibox-content">
								<form
									action="${pageContext.request.contextPath}/import_project/listTwo">
									<!--内容增加区域-->
									<div class=" control-group">
										<label class="col-sm-2 control-label">部门名称</label>
										<div class="col-sm-10">
											<select class="select2_demo_1 form-control "
												id="departmentId" name="departmentId">
												<option value="">请选择...</option>
												<c:forEach items="${requestScope.departmentsList}"
													var="departments">
													<option value="${departments.departmentId}">${departments.departmentName}</option>
												</c:forEach>
											</select> <br />
										</div>
									</div>
									<br />
									<div class="form-group" id="data_4">
										<label for="departmentName" class="col-sm-2 control-label">开始日期</label>
										<div class="input-group date">
											<span class="input-group-addon"> <i
												class="fa fa-calendar"></i>
											</span> <input type="text" class="form-control" readonly="readonly"
												name="date1" id="date1">
										</div>
									</div>
									<br />
									<div class="form-group" id="data_4">
										<label for="departmentName" class="col-sm-2 control-label">结束日期</label>
										<div class="input-group date">
											<span class="input-group-addon"> <i
												class="fa fa-calendar"></i>
											</span> <input type="text" class="form-control" readonly="readonly"
												name="date2" id="date2">
										</div>
									</div>
									<br />
									<div class="form-group">
										<label for="departmentName" class="col-sm-2 control-label">员工编号</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="employeeId"
												id="employeeId" placeholder="请填写"> <br />
										</div>
									</div>
									<br />
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10 ">
											<button type="button" id="sumbtn" class="btn btn-primary">查询</button>
											<button type="reset" class="btn btn-primary">重填</button>
										</div>
									</div>
									<br /> <br /> <br />
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>导入信息</h5>
							<div class="ibox-tools">
								<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
								</a> <a class="close-link"> <i class="fa fa-times"></i>
								</a>
							</div>
						</div>
						<div class="ibox-content">

							<table class="table table-striped table-bordered table-hover "
								id="editable">
								<thead>
									<tr>
										<th>导入表编号</th>
										<th>员工姓名</th>
										<th>部门名称</th>
										<th>项目名称</th>
										<th>导入信息</th>
										<th>导入日期</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.importProjectsList}"
										var="importProjects">
										<tr class="gradeX">
											<td>${importProjects.importProjectId }</td>
											<c:forEach items="${requestScope.employeesList}"
												var="employees">
												<c:if
													test="${importProjects.employeeId == employees.employeeId }">
													<td>${employees.employeeName }</td>
												</c:if>
											</c:forEach>
											<c:forEach items="${requestScope.departmentsList}"
												var="departments">
												<c:if
													test="${importProjects.departmentId == departments.departmentId }">
													<td>${departments.departmentName }</td>
												</c:if>
											</c:forEach>
											<c:forEach items="${requestScope.projectsList}"
												var="projects">
												<c:if
													test="${importProjects.projectId == projects.projectId }">
													<td>${projects.projectName }</td>
												</c:if>
											</c:forEach>
											<td>${importProjects.importInfo }</td>
											<td><fmt:formatDate
													value="${importProjects.importDate }" pattern="yyyy-MM-dd" /></td>
											<td>
												<a href="${pageContext.request.contextPath}/import_project/${importProjects.importProjectId }/update?form"><i
													class="fa fa-pencil"></i></a>&nbsp;&nbsp;
												<a id="delete" href="#" ><i
													class="fa fa-remove" onclick="check(${importProjects.importProjectId})"></i></a>
											</td>

										</tr>
									</c:forEach>
							</table>

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

	<!-- Data picker -->
	<script src="${pageContext.request.contextPath}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>

	<script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/datatables.min.js"></script>



	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

	<!-- Sweet alert -->
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script>
		$("#sumbtn").click(function() {
			var testTag = /^[1-9][0-9]{1,10}$|^[1-9]$/;
			if (!testTag.test($("#employeeId").val()) && $("#employeeId").val() != '') {
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
		$(document)
				.ready(
						function() {
							$('.dataTables-example')
									.DataTable(
											{
												dom : '<"html5buttons"B>lTfgitp',
												buttons : [
														{
															extend : 'copy'
														},
														{
															extend : 'csv'
														},
														{
															extend : 'excel',
															title : 'ExampleFile'
														},
														{
															extend : 'pdf',
															title : 'ExampleFile'
														},

														{
															extend : 'print',
															customize : function(
																	win) {
																$(
																		win.document.body)
																		.addClass(
																				'white-bg');
																$(
																		win.document.body)
																		.css(
																				'font-size',
																				'10px');

																$(
																		win.document.body)
																		.find(
																				'table')
																		.addClass(
																				'compact')
																		.css(
																				'font-size',
																				'inherit');
															}
														} ]

											});

							/* Init DataTables */
							var oTable = $('#editable').DataTable();

							/* Apply the jEditable handlers to the table */
							oTable
									.$('td')
									.editable(
											'../example_ajax.php',
											{
												"callback" : function(sValue, y) {
													var aPos = oTable
															.fnGetPosition(this);
													oTable.fnUpdate(sValue,
															aPos[0], aPos[1]);
												},
												"submitdata" : function(value,
														settings) {
													return {
														"row_id" : this.parentNode
																.getAttribute('id'),
														"column" : oTable
																.fnGetPosition(this)[2]
													};
												},

												"width" : "90%",
												"height" : "100%"
											});

						});
	</script>
	<script>
	function check(importProjectId) {
			swal({
				title: "你确定要删除吗?",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes!",
				closeOnConfirm: false
			}, function(isConfirm) {
				if(isConfirm){
					window.location.href="${pageContext.request.contextPath}/import_project/"+importProjectId+"/delete";
				}
				return;
			});
	}
	</script>
	<c:if test="${not empty updateImportProjectResult}">
    	<script type="text/javascript">
    		swal({
					title: "${requestScope.updateImportProjectResult}",
				});
    	</script>
    </c:if>
    <c:if test="${not empty deleteImportResult}">
    	<script type="text/javascript">
    		swal({
					title: "${requestScope.deleteImportResult}",
				});
    	</script>
    </c:if>

</body>

</html>