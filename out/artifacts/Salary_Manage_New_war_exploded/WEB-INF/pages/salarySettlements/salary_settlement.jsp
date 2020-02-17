<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 工资结算</title>

<link
	href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/chosen/chosen.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/css/plugins/dataTables/datatables.min.css"
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
						<li><a
							href="${pageContext.request.contextPath }/login/list?main">Home</a></li>
						<li class="active"><a
							href="${pageContext.request.contextPath }/salarysettlement/list?form"><strong>工资结算管理</strong></a>
						</li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">
							<!--内容增加区域-->
							<div class="ibox-title">
								<h5>
									工资结算 <small>(选择部门及月份)</small>
								</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<form
									action="${pageContext.request.contextPath }/salarysettlement/list"
									id="selectsalarysettlement" method="post">
									<div class="form-group col-sm-11 col-sm-offset-1">
										<label class="font-noraml col-sm-10">部门</label>
										<div class="col-sm-10">
											<select class="form-control" name="departmentId"
												id="departmentId">
												<option value="">请选择</option>
												<c:forEach items="${requestScope.listDepartments }"
													var="department">
													<option value="${department.departmentId }">${department.departmentName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-sm-11 col-sm-offset-1" id="data_1">
										<label class="font-noraml col-sm-10">月份</label>
										<div class="input-group date col-sm-10">
											<span class="input-group-addon"><i
												class="fa fa-calendar"></i></span><input type="text"
												class="form-control" value="" name="importDate"
												id="importDate">
										</div>
									</div>

									<div class="form-group col-sm-offset-9">
										<button type="button" class="btn btn-primary" id="selectAll">结算</button>
									</div>
								</form>
							</div>
							<!--内容增加区域-->
						</div>
						<div class="ibox float-e-margins">
							<div class="ibox-title  back-change">
								<h5>
									工资结算详细<small></small>
								</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
									<!--<a class="dropdown-toggle" data-toggle="dropdown" href="form_advanced.html#">
											<i class="fa fa-wrench"></i>
										</a>
										<ul class="dropdown-menu dropdown-user">
											<li><a href="form_advanced.html#">Config option 1</a>
											</li>
											<li><a href="form_advanced.html#">Config option 2</a>
											</li>
										</ul>
										<a class="close-link">
											<i class="fa fa-times"></i>
										</a>-->
								</div>
							</div>
							<div class="ibox-content">
								<div class="row">
									<!--  -->
									<div class="table-responsive">
										<table
											class="table table-striped table-bordered table-hover dataTables-example">
											<thead>
												<tr>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">结算编号</th>

													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">员工编号</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">员工姓名</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">部门名称</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">导入日期</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">结算状态</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">实发工资</th>
													<th class="sorting_asc" tabindex="0"
														aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Rendering engine: activate to sort column descending"
														style="height: 50px; line-height: calc(3.5); text-align: center;">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ requestScope.listSalarySettlements}"
													var="salarySettlement">
													<tr>
														<td>${salarySettlement.salarySettlementId }<input
															type="hidden"
															value="${salarySettlement.salarySettlementId }"
															name="salarySettlementId"></td>
														<td>${salarySettlement.employeeId }</td>
														<td>${salarySettlement.employeeName }</td>
														<td>${salarySettlement.departmentName }</td>
														<td><fmt:formatDate pattern="yyyy-MM"
																value="${salarySettlement.importDate }" /></td>
														<td>${salarySettlement.stateName }</td>
														<td>${salarySettlement.trueSalary }</td>
														<td><a><i class='fa fa-list-ul'
																onclick='details(${salarySettlement.salarySettlementId})'></i></a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
									<!--  -->

								</div>
								<c:if test="${not empty requestScope.departmentName }">
									<div class="row">
										<div class="col-md-3">
											<label class="font-noraml"><strong>部门:</strong></label> <span
												id="departmentName" class="form-control">${requestScope.departmentName}</span>
										</div>
										<div class="col-md-3">
											<label class="font-noraml"><strong>工资总额:</strong></label> <span
												id="tureSalarySum" class="form-control">${requestScope.trueSalarySum}</span>
										</div>
										<div class="col-md-3">
											<label class="font-noraml"><strong>工资状态:</strong></label> <span
												id="stateName" class="form-control">${requestScope.stateName}</span>
										</div>
										<div class="col-md-1 col-md-offset-1">
											<label class="font-noraml"><strong>&nbsp</strong></label>
											<c:if test="${requestScope.stateName eq '未处理' }">
												<button type="button" class="btn btn-primary" id="updateAll">发放</button>
											</c:if>
											<c:if test="${requestScope.stateName ne '未处理' }">
												<button type="button" class="btn btn-primary" id="updateAll"
													disabled="disabled">发放</button>
											</c:if>
										</div>
									</div>
								</c:if>
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
		src="${pageContext.request.contextPath }/static/js/jquery-2.1.1.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath }/static/js/inspinia.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/pace/pace.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/chosen/chosen.jquery.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/jeditable/jquery.jeditable.js "></script>

	<script
		src="${pageContext.request.contextPath }/static/js/plugins/dataTables/datatables.min.js "></script>
	<script>
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});

		$(document)
				.ready(
						function() {
							$('#data_1 .input-group.date').datepicker({
								minViewMode : 1,
								keyboardNavigation : false,
								forceParse : false,
								autoclose : true,
								todayHighlight : true
							});

							$('.dataTables-example')
									.DataTable(
											{
												dom : '<"html5buttons "B>lTfgitp',
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
							oTable.$('td').editable(
									'../example_ajax.php',
									{
										"callback " : function(sValue, y) {
											var aPos = oTable
													.fnGetPosition(this);
											oTable.fnUpdate(sValue, aPos[0],
													aPos[1]);
										},
										"submitdata " : function(value,
												settings) {
											return {
												"row_id " : this.parentNode
														.getAttribute('id'),
												"column " : oTable
														.fnGetPosition(this)[2]
											};
										},

										"width " : "90% ",
										"height " : "100% "
									});

						});
		function fnClickAddRow() {
			$('#editable').dataTable().fnAddData(
					[ "Custom row ", "New row ", "New row ", "New row ",
							"New row ", "New row ", "New row ", "New row ",
							"New row " ]);

		}
		//结算工资
		$("#selectAll").click(function() {
			if ($("#departmentId").val() == '') {
				swal({
					title : "警示",
					text : "请选择部门.",
					type : "warning"
				});
				$("#departmentId").focus();
				return;
			}
			if ($("#importDate").val() == '') {
				swal({
					title : "警示",
					text : "所选择日期在今日之后.",
					type : "warning"
				});
				$("#importDate").focus();
				return;
			}
			$('form').submit();
		});
		
		//详情按钮调用
		function details(salarySettlementId) {
			window.location.href = "${pageContext.request.contextPath }/salarySettlementDetails/"
					+ salarySettlementId;
		}
		//发放工资
		$("#updateAll")
				.click(
						function() {
							var arrSalarySettlementsId = new Array();
							$('body').find("input[name='salarySettlementId']").each(function(ind,ele){
								arrSalarySettlementsId.push($(this).val());
							});
							swal(
									{
										title : "确认发放工资吗?",
										text : "发放本部门所有员工工资，该操作不可修改!",
										type : "warning",
										showCancelButton : true,
										confirmButtonColor : "#DD6B55",
										confirmButtonText : "Yes, do it!",
										closeOnConfirm : false
									},
									function() {
										$.ajax({
													type : "post",
													url : "http://localhost:8888/${pageContext.request.contextPath }/salarysettlement/updateAll",
													dataType : "json",
													contentType : "application/json",
													data : JSON
															.stringify(arrSalarySettlementsId),
													success : function(data) {
														if (data.result == 'success') {
															swal(
																	{
																		title : "提示",
																		text : "工资已结算.",
																		type : "success",
																		confirmButtonColor : "#DD6B55",
																		confirmButtonText : "OK!",
																		closeOnConfirm : true
																	},
																	function(
																			isConfirm) {
																		if (isConfirm) {
																			window.location.href = "${pageContext.request.contextPath }/salarysettlement/list?form";
																		}
																	});
														} else {
															swal({
																title : "提示",
																text : "工资结算失败."
															});
														}
													}
												});
									});
						});
	</script>

</body>

</html>