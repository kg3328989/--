<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<a class="close-canvas-menu"><i class="fa fa-times"></i></a>
		<!--右侧菜单开始-->
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element" align="center">
					<a data-toggle="dropdown" class="dropdown-toggle"
						href="off_canvas_menu.html#"> <span class="clear"> <span
							class="block m-t-xs"> <strong class="font-bold">项目分类</strong>
						</span></span>
					</a>
				</div>
			</li>
			<li><a href="#"><i class="fa fa-th-large"></i> <span
					class="nav-label">工资项目管理</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a
						href="${pageContext.request.contextPath}/projects/create?form">工资项目增加</a></li>
					<li><a
						href="${pageContext.request.contextPath}/projects/list?form">工资项目查询</a></li>

				</ul></li>
			<li><a href="${pageContext.request.contextPath}/fixed/list?form"><i
					class="fa fa-bar-chart-o"></i> <span class="nav-label">固定工资管理</span></a></li>

			<li><a href="#"><i class="fa fa-edit"></i> <span
					class="nav-label">导入项目管理</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a
						href="${pageContext.request.contextPath}/import_project/create?form">导入项目录入</a></li>
					<li><a
						href="${pageContext.request.contextPath}/import_project/listTwo?form">查询导入项目</a></li>
				</ul></li>

			<li><a
				href="${pageContext.request.contextPath }/salarysettlement/list?form"><i
					class="fa fa-flask"></i> <span class="nav-label">工资结算</span></a></li>
			<li><a href="#"><i class="fa fa-picture-o"></i> <span
					class="nav-label">报表</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a
						href="${pageContext.request.contextPath}/baobiao/list?form1">工资查询</a>
					</li>
					<li><a href="#">工资统计<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">

							<li><a
								href="${pageContext.request.contextPath}/baobiao/list?form2">公司</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/baobiao/list?form3">部门</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/baobiao/list?form4">员工</a>
							</li>
						</ul>
				</ul></li>

		</ul>
		<!--右侧菜单开结束-->
	</div>
</nav>