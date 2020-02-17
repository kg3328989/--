<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="${pageContext.request.contextPath}/static/css/plugins/select2/select2.min.css"
	rel="stylesheet">
<!-- Sweet Alert -->
<link href="${pageContext.request.contextPath}/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">

</head>


<body>
	<div id="wrapper">

		<%@include file="../inc/menu.jsp"%>

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
						<li><a href="${pageContext.request.contextPath }/login/list?form">Home</a></li>
						<li class="active"><strong>工资项目管理</strong>
						</li>
					</ol>
				</div>
			</div>
			<div>
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								Excel导入项目 <small></small>
							</h5>
							<div class="ibox-tools">
								<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="ibox-content">
							<input type="file" value="" class="form-control" id="fileName"
											placeholder="选择文件">
							<br/>
							<button type="button" class="btn btn-primary"
											onclick="submitFile();">从Excel读取数据</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								导入项目录用 <small>(请填写相应信息)</small>
							</h5>
							<div class="ibox-tools">
								<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="ibox-content">

							<form method="post"
								action="${pageContext.request.contextPath}/import_project/create"
								class="form-horizontal" id="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">部门名称</label>
									<div class="col-sm-10">
										<select class="select2_demo_1 form-control " id="departmentId"
											name="departmentId">
											<option value="">请选择...</option>
											<c:forEach items="${requestScope.departmentsList}"
												var="departments">
												<option value="${departments.departmentId}">${departments.departmentName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">员工编号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="employeeId"
											name="employeeId" placeholder="请填写"> <br />
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-sm-2 control-label">导入项目名称</label>
									<div class="col-sm-10">
										<select class="select2_demo_1 form-control " id="projectId"
											name="projectId">
											<option value="">请选择...</option>
											<c:forEach items="${requestScope.projectsList}"
												var="projects">
												<option value="${projects.projectId}">${projects.projectName}</option>
											</c:forEach>
										</select> <br />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label for="departmentName" class="col-sm-2 control-label">信息</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="importInfo"
											name="importInfo" placeholder="请填写正确信息（例：10）"> <br />
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group" id="data_4">
									<label for="departmentName" class="col-sm-2 control-label">导入日期</label>
									<div class="input-group date">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span> <input type="text" id="importDate" name="importDate"
											class="form-control" readonly="readonly">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label for="departmentName" class="col-sm-2 control-label">备注</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="remark"
											name="remark" placeholder="请填写"> <br />
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10 ">
										<button type="button" class="btn btn-primary"
											onclick="check();">录入</button>
										<button type="reset" class="btn btn-primary">重填</button>
									</div>
								</div>
							</form>
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

	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

	<!-- Select2 -->
	<script src="${pageContext.request.contextPath}/static/js/plugins/select2/select2.full.min.js"></script>
	<!-- 弹出对话框 -->
	<script src="${pageContext.request.contextPath}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script>
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
		function check() {
			var departmentId = document.getElementById("departmentId").value;
			var employeeId = document.getElementById("employeeId").value;
			var projectId = document.getElementById("projectId").value;
			var importInfo = document.getElementById("importInfo").value;
			var importDate = document.getElementById("importDate").value;
			if (departmentId == "") {
				swal({
					title : "填写错误!",
					text : "请重新填写部门名称!",
					type : "warning"
				});
				return;
			}
			var testTag = /^[1-9][0-9]{1,10}$|^[1-9]$/;
			if (!testTag.test(employeeId)) {
				swal({
					title : "警示",
					text : "请输入正确的员工编号.",
					type : "warning"
				});
				$("#employeeId").focus();
				return;
			}
			if (projectId == "") {
				swal({
					title : "填写错误!",
					text : "请重新填写导入项目名称!",
					type : "warning"
				});
				return;
			}
			var testTag1 = /^[1-9][0-9]{1,10}$|^[0-9]$/;
			if (!testTag1.test(importInfo)) {
				swal({
					title : "警示",
					text : "请输入正确的信息.",
					type : "warning"
				});
				$("#importInfo").focus();
				return;
			}
			if (importDate == "") {
				swal({
					title : "填写错误!",
					text : "请重新填写导入日期!",
					type : "warning"
				});
				return;
			}
			var testRemark = /^.{0,255}$/;
			if (!testRemark.test($("#remark").val())) {
				swal({
					title : "警示",
					text : "请输入255个字符以内的备注.",
					type : "warning"
				});
				$("#remark").focus();
				return;
			} 
			swal({
				title : "确定要提交吗",
				type : "warning",
				showCancelButton : true,
				closeOnConfirm : false
			}, function() {
				$('#form-horizontal').submit();
			});
			return;

		}
		function submitFile() {
			var formData = new FormData();
			var name = $("#fileName").val();
			if ($("#fileName").val() == '') {
				swal({
					title : "警示",
					text : "请选择文件.",
					type : "warning"
				});
				$("#fileName").focus();
				return;
			}
			formData.append("file", $("#fileName")[0].files[0]);
			formData.append("name", name);
			$.ajax({
				url : "http://localhost:8888/${pageContext.request.contextPath }/excel/importProjects",
				type : "POST",
				async : false,
				data : formData,
				processData : false,
				contentType : false,
				beforeSend : function() {
					console.log("正在进行，请稍候");
				},
				success : function(e) {
					if (e == "01") {
						swal({
							title : "导入成功!",
							type : "success"
						});
					} else {
						swal({
							title : "导入失败!",
							type : "warning"
						});
					}
				}
			});
		}
	</script>
	<c:if test="${not empty importProjectsResult}">
    	<script type="text/javascript">
    		swal({
					title: "${requestScope.importProjectsResult}",
				});
    	</script>
    </c:if>
    <c:if test="${not empty selectEmployeeResult}">
	    <script type="text/javascript">
	    	swal({
					title: "${requestScope.selectEmployeeResult}",
				});
	    </script>
	</c:if>
	<c:if test="${not empty selectImportsResult}">
	    <script type="text/javascript">
	    	swal({
					title: "${requestScope.selectImportsResult}",
				});
	    </script>
	</c:if>
	<c:if test="${not empty selectEmployeeResultTwo}">
	    <script type="text/javascript">
	    	swal({
					title: "${requestScope.selectEmployeeResultTwo}",
				});
	    </script>
	</c:if>

</body>
</html>