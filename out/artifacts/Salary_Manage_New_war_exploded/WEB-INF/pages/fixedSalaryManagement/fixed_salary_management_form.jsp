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
<link
	href="${pageContext.request.contextPath}/static/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet">
<!-- Sweet Alert -->
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
			<!-- 超级大表单 -->
			<c:set var="temp"></c:set>
			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">
							<div class="row">
								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<form
											action="${pageContext.request.contextPath}/fixed/batchUpdate?batchUpdate"
											method="post" id="batchUpdate">
											<div class="ibox-title">
												<br />
												<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;XXX部门|员工固定工资表：</h3>
												<div class="ibox-tools">
													<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
													</a>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="ibox float-e-margins">
														<div class="ibox-content">
															<div class="table-responsive">
																<table
																	class="table table-striped table-bordered table-hover dataTables-example">
																	<thead>
																		<tr>
																			<th>选中</th>
																			<th>员工编号</th>
																			<th>员工姓名</th>
																			<th>部门名称</th>

																			<c:forEach items="${requestScope.pojsList }"
																				var="pojs">
																				<%-- <c:forEach items="${requestScope.fsList }" var="fs"> --%>
																				<c:if test="${pojs.projectType == 1}">
																					<th>${pojs.projectName }</th>
																				</c:if>
																				<%-- </c:forEach> --%>
																			</c:forEach>

																			<!--固定工资项目列  到时候考虑使用foreach看看能不能实现动态列插入显示-->
																			<th>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 修改
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 详情
																			</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:if test="${empty requestScope.fsList }">
																			<script type="text/javascript">
																				window
																						.alert("无查询结果！")
																			</script>
																		</c:if>
																		<c:forEach items="${requestScope.empList }" var="emp"
																			varStatus="ind">
																			<c:if
																				test="${emp.departmentId == requestScope.deptid}">
																				<tr>
																					<!-- 第一列 -->
																					<!-- 输出复选框 -->
																					<td><input type="checkbox"
																						value="${emp.employeeId }" name="checkbox" /></td>
																					<!-- 第二列 -->
																					<!-- 输出员工编号 -->
																					<td>${emp.employeeId }</td>
																					<!-- 第三列 -->
																					<!-- 输出员工姓名 -->
																					<td>${emp.employeeName }</td>
																					<!-- 第四列 -->
																					<!-- 输出部门名称 -->
																					<c:forEach items="${requestScope.deptList }"
																						var="dept">
																						<c:if test="${dept.departmentId == deptid}">
																							<td>${dept.departmentName }</td>
																						</c:if>
																					</c:forEach>
																					<!-- 第五列 -->
																					<!-- 输出对应项目的数值 -->
																					<c:forEach items="${requestScope.pojsList }"
																						var="pojs">
																						<%-- <c:set var="totalCount" value="0"></c:set> --%>
																						<c:if test="${pojs.projectType == 1}">
																							<c:forEach items="${requestScope.fsList }"
																								var="fs">
																								<c:if test="${fs.employeeId == emp.employeeId}">
																									<c:if test="${pojs.projectId == fs.projectId}">
																										<td>${fs.salary }</td>
																										<%-- <c:set var="totalCount" value="1"></c:set> --%>
																									</c:if>
																								</c:if>
																							</c:forEach>
																							<%-- <c:if test="${totalCount == 0}">
																								<td>0</td>
																							</c:if> --%>
																						</c:if>
																					</c:forEach>
																					<!-- 第七列 -->
																					<td>
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																						<a
																						href="${pageContext.request.contextPath}/fixed/${emp.employeeId}/${requestScope.deptid}/update?form"><i
																							class="fa fa-pencil"></i></a>
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																						<a
																						href="${pageContext.request.contextPath}/fixed/${emp.employeeId}/${requestScope.deptid}"><i
																							class="fa fa-list-ul"></i></a>
																					</td>
																				</tr>
																			</c:if>
																		</c:forEach>
																	</tbody>
																</table>
																<label>全选</label>
																<button type="button" id="selectAll">
																	<i class="fa fa-check-circle-o"></i>
																</button>
																<label>反选</label>
																<button type="button" id="changeSelect">
																	<i class="fa fa-check-circle"></i>
																</button>
																<label>全不选</label>
																<button type="button" id="selectNot">
																	<i class="fa fa-circle-o"></i>
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="ibox float-e-margins">
														<div class="ibox-content">
															<h3>员工固定工资批量修改：</h3>

															<div class="ibox-content">
																<label class="col-sm-2 control-label">工资类型名称</label>
																<div class="col-sm-10">
																	<select class="form-control m-b" name="projectId"
																		id="projectId">
																		<option value="">请选择...</option>
																		<c:forEach items="${requestScope.pojsList }"
																			var="pojs">
																			<option value="${pojs.projectId}">${pojs.projectName}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
															<div class="ibox-content">
																<label class="col-sm-2 control-label">统一设置金额</label>
																<div class="col-sm-10">
																	<input type="text" class="form-control" name="salary"
																		id="salary">
																</div>
															</div>
															<div class="hr-line-dashed"></div>
															<div class="form-group">
																<div class="col-sm-2 col-sm-offset-2">
																	<button class="btn btn-primary demo3"
																		id="sub_btn" type="button">提交</button>
																</div>
															</div>
														</div>
													</div>
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
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/dataTables/datatables.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

	<!-- Sweet alert -->
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script language="JavaScript">
		$(function() {
			$("#go_submit").click(function() {
				location.href = 'fixed_salary_management_form.html#';
			})
		})
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
		$(function() {
			$("#selectAll").click(function() {
				/*						window.alert("test");*/
				o = document.getElementsByName("checkbox")
				for (i = 0; i < o.length; i++) {
					o[i].checked = true
				}
			});
			$("#changeSelect").click(function() {
				/*window.alert("test");*/
				o = document.getElementsByName("checkbox")
				for (i = 0; i < o.length; i++) {
					if (o[i].checked == false) {
						o[i].checked = true
					} else {
						o[i].checked = false
					}
				}
			});
			$("#selectNot").click(function() {
				/*						window.alert("test");*/
				o = document.getElementsByName("checkbox")
				for (i = 0; i < o.length; i++) {
					o[i].checked = false
				}
			});
		});
		//				<!-- Page-Level Scripts -->
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

		function fnClickAddRow() {
			$('#editable').dataTable()
					.fnAddData(
							[ "Custom row", "New row", "New row", "New row",
									"New row" ]);

		}

		$(function() {
			$("#sub_btn").click(function() {
				var checkbox = document.getElementsByName("checkbox");
				var Checktemp = 0;
				for (var i = 0; i < checkbox.length; i++) {
					if (checkbox[i].checked == true) {
						Checktemp++;
					}
				}
				if (Checktemp == 0) {
					swal({
						title : "未选定员工!",
						text : "请选定员工",
						type : "warning"
					});
					return;
				}
				var projectId = document.getElementById("projectId");

				if (projectId.value == "") {
					swal({
						title : "未选定工资类型名称!",
						text : "请选定工资类型名称",
						type : "warning"
					});
					return;
				}
				var testTag = /^(0|[1-9][0-9]*)$/;
				
				if (!testTag.test($("#salary").val())) {
					swal({
						title : "警告非数字",
						text : "请输入正确的工资.",
						type : "warning"
					});
					$("#salary").focus();
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
				swal({
					title : "确定要修改么?",
					text : "请您务必检查好修改数据的正确",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "YES!",
					closeOnConfirm : false
				}, function() {
					//提交表单
					$('#batchUpdate').submit();
				});
			});
		});
	</script>

</body>

</html>