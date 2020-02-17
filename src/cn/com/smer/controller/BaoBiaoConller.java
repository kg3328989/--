package cn.com.smer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.dto.SalarySettlementDetails;
import cn.com.smer.dao.dto.SalarySettlements;
import cn.com.smer.dtoform.DateDepartmentsForm;
import cn.com.smer.dtoform.SalarySelectForm;
import cn.com.smer.dtoform.SalarySettlementDetailsForm;
import cn.com.smer.dtoform.SalarySettlementsForm;
import cn.com.smer.service.BaoBiaoService;
import cn.com.smer.service.DepartmentsService;
import cn.com.smer.service.EmployeesService;
import cn.com.smer.service.ProjectsService;

/*
 * 	报表管理
 * 		1.工资查询报表
 * 		2.打印工资条
 * 		3.工资统计报表
 */
@Controller
@RequestMapping("/baobiao")
public class BaoBiaoConller {
	@Autowired
	private BaoBiaoService baoBiaoService;
	@Autowired
	private DepartmentsService departmentsService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private ProjectsService projectsService;

	// 工资查询
	@RequestMapping(value = "/list", params = { "form1" })
	public String listForm1(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		List<Employees> listEmployees = employeesService.getAll();
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("deptList", departmentsList);
		return "/baobiao/salary_select";

	}

	@RequestMapping(value = "listSelect", method = RequestMethod.POST)
	public String list1(Model model, SalarySelectForm form) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		List<Employees> listEmployees = employeesService.getAll();
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("deptList", departmentsList);
		List<SalarySelectForm> listSalarySelectForm = baoBiaoService.SelectSalarySettlementDetailsForm(form);
		for (SalarySelectForm salarySelectForm : listSalarySelectForm) {
			//System.out.println(salarySelectForm);
		}
		model.addAttribute("listSalarySelectForm", listSalarySelectForm);
		List<Projects> listProjects = baoBiaoService.selectAllProjects();
		for (Projects project : listProjects) {
			//System.out.println(project);
		}
		model.addAttribute("listProjects", listProjects);
		return "/baobiao/salary_select";
	}

	// 公司
	@RequestMapping(value = "/list", params = { "form2" })
	public String listForm2() {
		return "/baobiao/company";

	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listDepartments1(SalarySettlementsForm form, Model model) {

		// Long maxSalary = (long) salarySettlementsList.get(0).getTrueSalary();
		// Long minSalary = (long) salarySettlementsList.get(0).getTrueSalary();
		// 结算信息结算主表
		List<SalarySettlements> salarySettlementsList = baoBiaoService.selectBySelective(form);
		// 返回的计算对象结果
		List<DateDepartmentsForm> listDateDepartmentsForm = new ArrayList<DateDepartmentsForm>();
		// 设置第一个月份
		DateDepartmentsForm dateDepartment0 = new DateDepartmentsForm();
		if (salarySettlementsList.size() > 0) {
			dateDepartment0.setImportDate(salarySettlementsList.get(0).getImportDate());
			dateDepartment0.setMaxSalary(salarySettlementsList.get(0).getTrueSalary());
			dateDepartment0.setMinSalary(salarySettlementsList.get(0).getTrueSalary());
			dateDepartment0.setAvgSalary((double) 0);
			listDateDepartmentsForm.add(dateDepartment0);
		}

		for (SalarySettlements salarySettlements : salarySettlementsList) {

			for (int i = 0; i < listDateDepartmentsForm.size(); i++) {
				DateDepartmentsForm dateDepartment = listDateDepartmentsForm.get(i);
				if (dateDepartment.getImportDate().getTime() == salarySettlements.getImportDate().getTime()) {
					dateDepartment.setSumSalary(dateDepartment.getSumSalary() + salarySettlements.getTrueSalary());
					if (salarySettlements.getTrueSalary() > dateDepartment.getSumSalary()) {
						dateDepartment.setMaxSalary(salarySettlements.getTrueSalary());
					}

					if (salarySettlements.getTrueSalary() < dateDepartment.getSumSalary()) {
						dateDepartment.setMinSalary(salarySettlements.getTrueSalary());
					}

					dateDepartment.setAvgSalary(dateDepartment.getAvgSalary() + 1);
					break;
				} else if (dateDepartment.getImportDate().getTime() != salarySettlements.getImportDate().getTime()
						&& i == (listDateDepartmentsForm.size() - 1)) {
					DateDepartmentsForm temp = new DateDepartmentsForm();
					temp.setImportDate(salarySettlements.getImportDate());
					temp.setSumSalary(salarySettlements.getTrueSalary());
					temp.setMaxSalary(salarySettlements.getTrueSalary());
					temp.setMinSalary(salarySettlements.getTrueSalary());
					temp.setAvgSalary((double) 0);
					listDateDepartmentsForm.add(temp);
				}
			}
		}
		for (DateDepartmentsForm dateDepartments : listDateDepartmentsForm) {
			//System.out.println(dateDepartments);
			dateDepartments.setAvgSalary(
					((int) (dateDepartments.getSumSalary() / dateDepartments.getAvgSalary() * 100)) / 100.0);
		}
		model.addAttribute("listDateDepartmentsForm", listDateDepartmentsForm);
		return "/baobiao/company";
	}

	@RequestMapping(value = "/listDepartments", method = RequestMethod.POST)
	public String listDepartments(SalarySettlementsForm form, Model model) {
		List<SalarySettlements> salarySettlementsList = baoBiaoService.selectBySelective(form);
		List<SalarySettlements> salarySettlementsDateList = baoBiaoService.selectAllDate();
		List<DateDepartmentsForm> DateDepartmentsList = new ArrayList<DateDepartmentsForm>();
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		for (Departments departments : departmentsList) {
			for (SalarySettlements salarySettlements : salarySettlementsDateList) {
				DateDepartmentsForm dd = new DateDepartmentsForm();
				dd.setDepartmentId(departments.getDepartmentId());
				dd.setImportDate(salarySettlements.getImportDate());
				DateDepartmentsList.add(dd);
			}
			for (SalarySettlements salarySettlements : salarySettlementsList) {
				for (DateDepartmentsForm dd : DateDepartmentsList) {
					if (salarySettlements.getDepartmentId().equals(dd.getDepartmentId())
							&& salarySettlements.getImportDate().equals(dd.getImportDate())) {
						dd.setAvgSalary(dd.getAvgSalary() + 1);
						dd.setSumSalary(dd.getSumSalary() + salarySettlements.getTrueSalary());
						if (salarySettlements.getTrueSalary() > dd.getMaxSalary()) {
							dd.setMaxSalary(salarySettlements.getTrueSalary());
						}
						if (salarySettlements.getTrueSalary() < dd.getMinSalary()) {
							dd.setMinSalary(salarySettlements.getTrueSalary());
						}
					}
				}
			}
		}
		model.addAttribute("deptList", departmentsList);
		model.addAttribute("salarySettlementsDateList", salarySettlementsDateList);
		model.addAttribute("salarySettlementsList", salarySettlementsList);
		model.addAttribute("DateDepartmentsList", DateDepartmentsList);
		return "/baobiao/departments";
	}

	// 部门
	@RequestMapping(value = "/list", params = { "form3" })
	// 部门列表显示
	public String listForm3(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("deptList", departmentsList);
		//System.out.println(departmentsList);
		return "/baobiao/departments";

	}

	// sxb 通过员工编号查询工资查询
	@RequestMapping(value = "/list", params = { "form4" })
	public String listForm4() {

		return "/baobiao/employees";

	}

	// sxb 显示查询结果
	@RequestMapping(value = "/list4", method = RequestMethod.GET)
	public String list(Long employeeId, Model model) {
		SalarySettlementsForm a = new SalarySettlementsForm();
		a.setEmployeeId(employeeId);
		model.addAttribute("empId", employeeId);
		// 注入工资结算主表
		List<SalarySettlements> ss = baoBiaoService.selectBySelective(a);
		model.addAttribute("ssList", ss);

		// 拿ss去找报表编号，然后去查询项目编号和对性工资值
		List<SalarySettlementDetails> ssd = baoBiaoService.selectSSD(ss);

		// 注入工资结算详细表
		model.addAttribute("ssdList", ssd);
		// 注入项目名称
		List<Projects> pojsAll = projectsService.getAllProjectsTwo();
		for (Projects b : pojsAll) {
			//System.out.println(b.getProjectName());
		}
		model.addAttribute("pojsList", pojsAll);

		String empName = employeesService.selectNameByEmployeeId(employeeId);
		model.addAttribute("empName", empName);

		return "/baobiao/employees";
	}
}
