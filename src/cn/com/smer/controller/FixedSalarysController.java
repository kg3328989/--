package cn.com.smer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.FixedSalarys;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dtoform.FixedSalarysForm;
import cn.com.smer.service.DepartmentsService;
import cn.com.smer.service.EmployeesService;
import cn.com.smer.service.FixedSalarysService;
import cn.com.smer.service.ProjectsService;
/*
 * 	�̶����ʹ���
 * 		1.ӵ�а��������Ʋ�ѯԱ���̶����ʾ������鹦��
 * 		2.ӵ�ж�ĳ����Ա������ĳһ��̶�������Ŀ���������޸ĵĹ���
 * 		3.ӵ�ж�ĳ����ĳһ��Ա���Ĺ̶����ʽ��е����޸ĵĹ���
 * 		4.ӵ�ж�ĳ����ĳһԱ���Ĺ̶���������鿴����
 */
@Controller
@RequestMapping(value = "/fixed")
public class FixedSalarysController {
	
	@Autowired//�Զ�ע��
	private FixedSalarysService fixedSalaryService;
	@Autowired//�Զ�ע��
	private EmployeesService employeesService;
	@Autowired//�Զ�ע��
	private DepartmentsService departmentsService;
	@Autowired//�Զ�ע��
	private ProjectsService projectsService;
	@Autowired
	private DepartmentsMapper departmentsMapper;
	// ��ת��ѯҳ��
	@RequestMapping(value = "/list", params = { "form" })
	public String listForm(Model model) {
		List<Departments> listDepartments = departmentsMapper.selectAll();
		model.addAttribute("listDepartments", listDepartments);
		return "/fixedSalaryManagement/fixed_salary_management_select";
	}
	
	// ��ʾ��ѯ���
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(String selective,Model model) {
		/*�ж��ǲ������ƻ��ǲ��ű��*/
		boolean temp = true;
		Long deptid = null;
		for(int i = 0;i < selective.length();i++) {
			char o = selective.charAt(i);
			if(o < 48 || o > 57) {
				temp = false;
				break;
			}
		}
		if(temp == true) {
			//System.out.println("���в��ű�Ų�ѯ");
			//�ַ���ת������
			deptid = Long.valueOf(selective);
		}
		if(temp == false) {
			//System.out.println("���в������Ʋ�ѯ");
			Departments dept = departmentsService.selectByName(selective);
			deptid = dept.getDepartmentId();
		}
		//����ò�����Ϣδ���룬����д˳�ʼ������
		fixedSalaryService.insertByDepartmentId(deptid);
		//��ʼ��
		fixedSalaryService.initialize(deptid);
		
		/*System.out.println("���ű��ע�룡" + deptid);*/
		model.addAttribute("deptid",deptid);
		
		List<Employees> empAll = employeesService.selectByDepartmentId(deptid);
		model.addAttribute("empList", empAll);
		//���ò����Ƿ��������ְԱ�������г�ʼ��
		fixedSalaryService.checkNewEmployee(deptid,empAll);
		
		List<FixedSalarys> fs = fixedSalaryService.selectByDeptId(deptid);
		model.addAttribute("fsList", fs);
		
		List<Departments> deptAll = departmentsService.getAllDepartments();
		model.addAttribute("deptList", deptAll);
		
		/*for(Departments pjA : deptAll) {
			System.out.println("DepartmentId=" + pjA.getDepartmentId());
			System.out.println("DepartmentName=" + pjA.getDepartmentName());
			System.out.println();
		}*/
		List<Projects> pojsAll = projectsService.getAllOneProjects();
		model.addAttribute("pojsList", pojsAll);
		
		/*for(Projects pjA : pojsAll) {
			System.out.println("fixedSalaryId=" + pjA.getProjectName());
			System.out.println("projectId=" + pjA.getProjectId());
			System.out.println();
		}*/
		
		return "/fixedSalaryManagement/fixed_salary_management_form";
	}
	//��ʾ����Ա���̶����ʱ�
	@RequestMapping(value="/{empId}/{deptId}/update", params= {"form"})
	public String updateForm(@PathVariable("empId") Long employeeId , @PathVariable("deptId") Long departmentId, Model model) {
		
		model.addAttribute("empId", employeeId);
		model.addAttribute("deptId", departmentId);
		
		List<FixedSalarys> fsListForEmp = fixedSalaryService.selectByEmpId(employeeId);
		/*for(FixedSalarys fs : fsListForEmp) {
			System.out.println("fixedSalaryId=" + fs.getFixedSalaryId());
			System.out.println("projectId=" + fs.getProjectId());
			System.out.println("employeeId=" + fs.getEmployeeId());
			System.out.println("departmentId=" + fs.getDepartmentId());
			System.out.println("salary=" + fs.getSalary());
			System.out.println();
		}*/
		model.addAttribute("fsList", fsListForEmp);
		
		
		List<Employees> empAll = employeesService.selectByDepartmentId(departmentId);
		model.addAttribute("empList", empAll);
		
		List<Departments> deptAll = departmentsService.getAllDepartments();
		model.addAttribute("deptList", deptAll);
		
		List<Projects> pojsAll = projectsService.getAllOneProjects();
		model.addAttribute("pojsList", pojsAll);
		/*for(Projects pojsA : pojsAll) {
			System.out.println("ProjectName=" + pojsA.getProjectName());
			System.out.println("ProjectId=" + pojsA.getProjectId());
			System.out.println();
		}*/
		return "/fixedSalaryManagement/fixed_salary_management_update";
	}
	//���²���(ʧ��)
	@RequestMapping(value="/{id}/update",params= {"update"},method=RequestMethod.POST)
	public String update(@PathVariable("id") Long employeeId ,FixedSalarysForm form, Model model) {
		//System.out.println(form.getFixedSalaryId());
		boolean result = fixedSalaryService.updateByPrimaryKeySelective(form);
		
		if(result) {
			return "redirect:/fixed/" + employeeId + "/update?complete";
		}else {
			model.addAttribute("updateFsResult", "0");
			return "/fixedSalaryManagement/fixed_salary_management_select";
		}
	}
	//���³ɹ�
	@RequestMapping(value="/{id}/update",params= {"complete"},method=RequestMethod.GET)
	public String updateComplete(Model model) {
		model.addAttribute("updateFsResult", "1");
		return "/fixedSalaryManagement/fixed_salary_management_select";
	}
	//�������
	@RequestMapping(value="/{empId}/{deptId}",method=RequestMethod.GET)
	public String detail(@PathVariable("empId") Long employeeId , @PathVariable("deptId") Long departmentId, Model model) {
		model.addAttribute("empId", employeeId);
		model.addAttribute("deptId", departmentId);
		
		List<FixedSalarys> fsListForEmp = fixedSalaryService.selectByEmpId(employeeId);
		model.addAttribute("fsList", fsListForEmp);
		
		List<Employees> empAll = employeesService.selectByDepartmentId(departmentId);
		model.addAttribute("empList", empAll);
		
		List<Departments> deptAll = departmentsService.getAllDepartments();
		model.addAttribute("deptList", deptAll);
		
		List<Projects> pojsAll = projectsService.getAllOneProjects();
		model.addAttribute("pojsList", pojsAll);
		
		return "/fixedSalaryManagement/fixed_salary_management_detail";
	}
	//�������²���
	@RequestMapping(value="/batchUpdate",params= {"batchUpdate"},method=RequestMethod.POST)
	public String batchUpdate(FixedSalarysForm form, Long DataTables_Table_0_length, Model model) {
		//System.out.println(form);
		boolean result = fixedSalaryService.batchUpdate(form);
		if(result) {
			return "redirect:/fixed/" + DataTables_Table_0_length + "/batchUpdate?complete";
		}else {
			model.addAttribute("batchUpdateResult", "0");
			return "/fixedSalaryManagement/fixed_salary_management_select";
		}
	}
	//���³ɹ�
	@RequestMapping(value="/{id}/batchUpdate",params= {"complete"},method=RequestMethod.GET)
	public String batchUpdateComplete(Model model) {
		List<Departments> listDepartments = departmentsMapper.selectAll();
		model.addAttribute("listDepartments", listDepartments);
		model.addAttribute("batchUpdateResult", "1");
		return "/fixedSalaryManagement/fixed_salary_management_select";
	}
	
}
