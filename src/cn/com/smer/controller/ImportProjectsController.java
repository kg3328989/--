package cn.com.smer.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dtoform.ImportProjectsForm;
import cn.com.smer.service.DepartmentsService;
import cn.com.smer.service.EmployeesService;
import cn.com.smer.service.ImportProjectsService;
import cn.com.smer.service.ProjectsService;
/*
 * 	 ������Ŀ����¼��
 *		1.������Ŀ����ά������ĳ������ĳ���µĵ������͵Ĺ�����Ŀ������ֵ����ά�����翼����Ϣ�Ͳ������ʵ�����¼������޸ġ�
 *		2.��ѯ������Ŀ����:�Ե������͵Ĺ�����Ŀ������ֵ���в�ѯ�����ѯĳ������ĳ���µĿ�����Ϣ�Ͳ������ʵȡ�
 *		
 */


@Controller
@RequestMapping(value = "/import_project")
public class ImportProjectsController {
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private ImportProjectsService importProjectsService;
	@Autowired
	private DepartmentsService departmentsService;
	@Autowired
	private EmployeesService employeesService;
	//������Ŀ¼��
	@RequestMapping(value="/create", params = {"form"})
	public String createForm(Model model) {
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		return "/import_project/insertImport";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(ImportProjectsForm form,Model model) {
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		Long departmentId = form.getDepartmentId();
		Long employeeId = form.getEmployeeId();
		Date importDate = form.getImportDate();
		Long projectId = form.getProjectId();
		if(employeesService.selectEmployee(employeeId)) {
			model.addAttribute("selectEmployeeResultTwo", "�޴�Ա��!");
			return "/import_project/insertImport";
		}
		//��ѯ�������Ƿ���¼����Ϣ�е�Ա��
		boolean resultTwo = importProjectsService.selectemployee(departmentId, employeeId);
		if(resultTwo) {
			//�ж�¼����Ϣ�Ƿ��Ѿ�����
			boolean resultThree = importProjectsService.selectImportProjects(importDate, employeeId,projectId);
			if(resultThree) {
				//�Ƿ����ɹ�
				boolean result = importProjectsService.insertImport(form);
				if(result) {
					return "redirect:/import_project/create?complete";
				}else {
					model.addAttribute("importProjectsResult", "������Ŀʧ�ܣ�");
					return "/import_project/insertImport";
				}	
			}else {
				model.addAttribute("selectImportsResult", "����Ϣ�Ѵ��ڣ�");
				return "/import_project/insertImport";
			}
			
		}else {
			model.addAttribute("selectEmployeeResult", "����û�и�Ա��");
			return "/import_project/insertImport";
		}
	}
	@RequestMapping(value="/create", method=RequestMethod.GET,params= {"complete"})
	public String createComplete(Model model) {
		model.addAttribute("importProjectsResult","������Ŀ�ɹ���");
		return "/import_project/insertImport";
	}
	
	
	//��ѯ������Ŀ
	@RequestMapping(value="/listTwo", params= {"form"})
	public String listTwoForm(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		return "/import_project/selectImport";
	}
	@RequestMapping(value="/listTwo")
	public String listTwo(Model model,ImportProjectsForm form) {
		List<ImportProjects> importProjectsList = importProjectsService.selectImportProjectsTwo(form);
		model.addAttribute("importProjectsList", importProjectsList);
		List<Employees> employeesList = employeesService.getAll();
		model.addAttribute("employeesList",employeesList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		return "/import_project/selectImport";
	}
	
	
	//�޸ĵ�����Ŀ
	@RequestMapping(value="/{id}/update", params= {"form"})
	public String updateForm(@PathVariable("id") Long importProjectId,Model model) {
		ImportProjects importProjectsList = importProjectsService.selectImportProjectsById(importProjectId);
		model.addAttribute("importProjectsList", importProjectsList);
		List<Employees> employeesList = employeesService.getAll();
		model.addAttribute("employeesList",employeesList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		return "/import_project/updateImport";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST, params= {"update"})
	public String update(@PathVariable("id") Long importProjectId , ImportProjectsForm improtProjectsForm , Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		improtProjectsForm.setImportProjectId(importProjectId);
		boolean result = importProjectsService.updateImportProjects(improtProjectsForm);
		if(result) {
			return "redirect:/import_project/" + importProjectId + "/update?complete";
		}else {
			model.addAttribute("updateImportProjectResult", "���ĵ�����Ϣʧ�ܣ�");
			return "/import_project/selectImport";
		}
	}
	@RequestMapping(value="/{id}/update", method=RequestMethod.GET, params= {"complete"})
	public String updateComplete(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("updateImportProjectResult", "���µ�����Ϣ�ɹ�!");
		return "/import_project/selectImport";
	}
	//ɾ��
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long importProjectId,Model model) {
		boolean result = importProjectsService.deleteImportProjects(importProjectId);
		if(result) {
			return "redirect:/import_project/" + importProjectId + "/delete?complete";
		}else {
			List<Departments> departmentsList = departmentsService.getAllDepartments();
			model.addAttribute("departmentsList", departmentsList);
			model.addAttribute("deleteImportResult", "ɾ��������Ϣʧ�ܣ�");
			return "/import_project/selectImport";
		}
	}
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET, params= {"complete"})
	public String deleteComplete(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("deleteImportResult", "ɾ��������Ϣ�ɹ�!");
		return "/import_project/selectImport";
	}

}
