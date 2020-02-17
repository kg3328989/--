<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DHC工资管理系统 | 工资项目管理</title>

<link
	href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath }/static/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath }/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/style.css"
	rel="stylesheet">

<!-- Sweet Alert -->
<link
	href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">


</head>

<body>

	<div id="wrapper">

		<!--右侧菜单开始-->
		<%@ include file="../inc/menu.jsp"%>
		<!--右侧菜单开结束-->

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<!--上侧导航条开始-->
				<%@ include file="../inc/header.jsp"%>
				<!--上侧导航条结束-->
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<h2>DHC工资管理系统</h2>
					<ol class="breadcrumb">
						<li><a
							href="${pageContext.request.contextPath }/login/list?main">Home</a></li>
						<li class="active"><strong>工资项目查询</strong></li>
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
									工资项目查询 <small>(请填写相应信息)</small>
								</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<form action="${pageContext.request.contextPath}/projects/list"
									method="post" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-1 control-label">项目名称</label>

										<div class="col-sm-4">
											<input type="text" name="projectName" id="projectName"
												class="form-control">
										</div>
										<label class="col-sm-2 control-label">项目类型</label>
										<div class="col-sm-4">
											<select class="form-control m-b" name="projectType"
												id="projectType">
												<option value="">请选择...</option>
												<option value="1">固定项目</option>
												<option value="2">计算项目</option>
												<option value="3">导入项目</option>
												<option value="4">实发项目</option>
											</select> <span class="help-block m-b-none"></span>
										</div>

									</div>
									<div class="hr-line-dashed"></div>

									<div class="form-group">
										<label class="col-sm-1 control-label">增减项</label>
										<div class="col-sm-4">
											<select class="form-control m-b" name="addorsubtract"
												id="addorsubtract">
												<option value="">请选择...</option>
												<option value="0">增加</option>
												<option value="1">减少</option>
												<option value="2">不变</option>
											</select> <span class="help-block m-b-none"></span>
										</div>
									</div>

									<div class="hr-line-dashed"></div>

									<div class="form-group">
										<label class="col-sm-5 control-label"></label>
										<div class="col-sm-5">
											<button class="btn btn-primary" type="reset">重置</button>
											<button class="btn btn-primary" type="submit">查询</button>
										</div>
									</div>

								</form>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="ibox">
								<div class="row">
									<div class="col-lg-12">
										<div class="ibox float-e-margins">
											<div class="ibox-title">
												<h5>查询结果</h5>
												<div class="ibox-tools">
													<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
													</a> <a class="close-link"> <i class="fa fa-times"></i>
													</a>
												</div>
											</div>

											<div class="ibox-content">

												<div class="table-responsive">
													<table
														class="table table-striped table-bordered table-hover dataTables-example">
														<thead>
															<tr>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">序号</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">项目编号</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">项目名称</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">项目类型</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">是否在工资条显示</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">计算公式</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">增减项</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">备注</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="DataTables_Table_0" rowspan="1"
																	colspan="1" aria-sort="ascending"
																	aria-label="Rendering engine: activate to sort column descending"
																	style="height: 50px; line-height: calc(3.5); text-align: center;">操作</th>
															</tr>
														</thead>
														<tbody>
															<c:set var="n" value="0"></c:set>
															<c:forEach items="${requestScope.projectsList}"
																var="projects" varStatus="ind">
																<tr class="gradeA odd" role="row"
																	style="text-align: center;">
																	<td style="line-height: 35px">${ind.count}</td>
																	<td style="line-height: 35px">${projects.projectId}</td>
																	<td style="line-height: 35px">${projects.projectName}</td>
																	<td style="line-height: 35px"><c:forEach
																			items="${requestScope.salaryProjectsList}"
																			var="salaryProjects">
																			<c:if
																				test="${projects.projectType eq salaryProjects.salaryProjectId}">
																			${salaryProjects.salaryProjectName}
																			</c:if>
																		</c:forEach></td>
																	<td style="line-height: 35px"><c:if
																			test="${projects.show == 1}">
																			显示
																			</c:if> <c:if test="${projects.show == 0}">
																			不显示
																			</c:if></td>
																	<td style="line-height: 35px"><c:if
																			test="${not empty projects.calculationId}">
																			<a
																				href="${pageContext.request.contextPath}/calculation/${projects.calculationId}/update?form"><i
																				class="fa fa-wrench"></i></a>
																		</c:if></td>
																	<td style="line-height: 35px"><c:if
																			test="${projects.addorsubtract == 0}">
																			增加
																			</c:if> <c:if test="${projects.addorsubtract == 1}">
																			减少
																			</c:if> <c:if test="${projects.addorsubtract == 2}">
																			不变
																			</c:if></td>
																	<td style="line-height: 35px">${projects.remark}</td>

																	<td><c:if test="${projects.projectName != '标准工资'}">
																			<c:if test="${ projects.addorsubtract <= 3}">
																				<a
																					href="${pageContext.request.contextPath}/projects/${projects.projectId}/update?form"><i
																					class="fa fa-pencil"></i></a>
																			&nbsp&nbsp&nbsp&nbsp
																			<a onclick="one(${projects.projectId})"><i
																					class="fa fa-remove"></i></a>
																			</c:if>
																			<c:if test="${ projects.addorsubtract > 3}">
																			<a onclick="two(${projects.projectId})"><i
																					class="fa fa-repeat" ></i></a>
																			</c:if>
																			</c:if></td>

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
				</div>
			</div>

			<!--底部显示-->
			<div class="footer ">
				<div class="pull-right ">
					<strong>大连华信培训中心定制二班</strong>第五组
				</div>
				<div>
					<strong>Web</strong> for DHC &copy; 2019-2020
				</div>
			</div>
		</div>
	</div>


	<!-- projectsAddAddAddly scripts -->
	<script
		src="${pageContext.request.contextPath }/static/js/jquery-2.1.1.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/bootstrap.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/metisMenu/jquery.metisMenu.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/slimscroll/jquery.slimscroll.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/jeditable/jquery.jeditable.js "></script>

	<script
		src="${pageContext.request.contextPath }/static/js/plugins/dataTables/datatables.min.js "></script>

	<!-- Custom and plugin javascript -->
	<script
		src="${pageContext.request.contextPath }/static/js/inspinia.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/pace/pace.min.js "></script>


	<!-- Sweet alert -->
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
	
        function one(id) {
        	swal(
        			{
        				title : "您确定要删除么?",
        				text : "此操作不可改变!",
        				type : "warning",
        				showCancelButton : true,
        				confirmButtonColor : "#DD6B55",
        				confirmButtonText : "Yes, delete it!",
        				cancelButtonText : "No, cancel plx!",
        				closeOnConfirm : false,
        				closeOnCancel : false
        			},
        			function(
        					isConfirm) {
        				if (isConfirm) {
        
        					window.location.href = "${pageContext.request.contextPath}/projects/"+id+"/delete";
        				} else {
        					swal(
        							" 您没有删除此项目",
        							"error");
        				}
        			});
        }
        function two(id) {
        	swal(
        			{
        				title : "您确定要恢复此项目么?",
        				text : "此操作不可改变!",
        				type : "warning",
        				showCancelButton : true,
        				confirmButtonColor : "#DD6B55",
        				confirmButtonText : "Yes, delete it!",
        				cancelButtonText : "No, cancel plx!",
        				closeOnConfirm : false,
        				closeOnCancel : false
        			},
        			function(
        					isConfirm) {
        				if (isConfirm) {
        
        					window.location.href = "${pageContext.request.contextPath}/projects/"+id+"/recovery";
        				} else {
        					swal(
        							" 您没有恢复此项目",
        							"error");
        				}
        			});
        }
    </script>
	<script>
		$(document)
				.ready(
						function() {
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
	</script>
	<%--	保存工资项目结果提示框		--%>
	<c:if test="${not empty requestScope.updateProjects}">
		<script type="text/javascript">
                 swal({
						title: "${requestScope.updateProjects}!",
						text: "谢谢!",
						type: "success"
					}); 
		</script>
	</c:if>
	<%--	保存工资项目结果提示框		--%>
	<%--	修改计算公式结果提示框		--%>
	<c:if test="${not empty requestScope.updateCalculations}">
		<script type="text/javascript">
		swal({
				title: "${requestScope.updateCalculations}!",
				text: "谢谢!",
				type: "success"
			});
		</script>
	</c:if>
	<%--	修改计算公式结果提示框		--%>



	<%--	删除工资项目结果提示框		--%>
	<c:if test="${not empty requestScope.DeleteProjectsResult}">
		<script type="text/javascript">
			swal({
				title: "${requestScope.DeleteProjectsResult}!",
				text: "谢谢!",
				type: "success"
			}); 
		</script>
	</c:if>
	<%--	删除工资项目结果提示框		--%>
	<%--	恢复工资项目结果提示框		--%>
	<c:if test="${not empty requestScope.RecoveryProjectsResult}">
		<script type="text/javascript">
			swal({
				title: "${requestScope.RecoveryProjectsResult}!",
				text: "谢谢!",
				type: "success"
			}); 
		</script>
	</c:if>
	<%--	恢复工资项目结果提示框		--%>


</body>

</html>