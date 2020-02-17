package cn.com.smer.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.dto.SalaryProjects;
import cn.com.smer.dtoform.ProjectsForm;
import cn.com.smer.service.ProjectsService;
import cn.com.smer.service.SalaryProjectsService;
/*
 * 	������Ŀ����
 * 		1.���ӹ�����Ŀ
 * 		2.�޸Ĺ�����Ŀ
 * 		3.ɾ��������Ŀ
 * 		4.��ѯ������Ŀ
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private SalaryProjectsService salaryProjectsService;

	// ��ӹ�����Ŀ ����ʾ
	@RequestMapping(value = "/create", params = { "form" })
	public String createForm() {
		return "/projects/projectsAdd";
	}

	// �½�
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(ProjectsForm form, Model model) {
		//System.out.println(form);
		if(form.getProjectType() == 4) {
			if(projectsService.selectByProjectTypeIs() == false) {
				model.addAttribute("AddprojectsResult", "�������Y�Ŀʧ�ܣ�ʵ����Ŀ�Ѵ��ڣ�����");
				return "/projects/projectsAdd";
			}
		}
		boolean projectIs = projectsService.selectByProjectName(form.getProjectName());
		if(projectIs == false) {
			model.addAttribute("AddprojectsResult", "�������Y�Ŀʧ�ܣ���Ŀ�����Ѵ��ڣ�����");
			return "/projects/projectsAdd";
		}
		boolean result = projectsService.insertSelective(form);
		Long id = form.getProjectId();
		model.addAttribute("id", id);
		if (result) {
			if (form.getProjectType() == 2 || form.getProjectType() == 3) {
				return "redirect:/calculation/{id}/create?form"; // �ض�����һ��ҵ����Ʒ���
			} else {
				return "redirect:/projects/create?complete";
			}

		} else {
			model.addAttribute("AddprojectsResult", "�������Y�Ŀʧ��!");
		}
		return "/projects/projectsAdd";
	}

	// �½����
	@RequestMapping(value = "/create", method = RequestMethod.GET, params = { "complete" })
	public String createComplete(Model model) {
		model.addAttribute("AddprojectsResult", "�������Y�Ŀ�ɹ�!");
		return "/projects/projectsAdd";
	}

	// ��ѯ������Ŀ ��ѯ����ʾ
	@RequestMapping(value = "/list", params = { "form" })
	public String listForm(Model model) {

		return "/projects/projectsSelect";
	}

	// �б���ʾ
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(ProjectsForm form, Model model) {
		List<SalaryProjects> salaryProjectsList = salaryProjectsService.selectAll();
		List<Projects> projectsList = projectsService.selectBySelective(form);
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("salaryProjectsList", salaryProjectsList);
		return "/projects/projectsSelect";
	}

	// �޸Ĺ�����Ŀ ����ʾ
	@RequestMapping(value = "/{id}/update", params = { "form" })
	public String updateForm(Model model, @PathVariable("id") Long projectId) {
		Projects projects = projectsService.selectByPrimaryKey(projectId);
		List<SalaryProjects> salaryProjectsList = salaryProjectsService.selectAll();
		HashMap<Integer, String> addorsubtractMap = new HashMap<Integer, String>();
		addorsubtractMap.put(0, "����");
		addorsubtractMap.put(1, "����");
		addorsubtractMap.put(2, "����");
		model.addAttribute("addorsubtractMap", addorsubtractMap);
		model.addAttribute("projects", projects);
		model.addAttribute("salaryProjectsList", salaryProjectsList);
		return "/projects/projectsUpdate";
	}

	// ����
	@RequestMapping(value = "/{id}/update", params = { "update" }, method = RequestMethod.POST)
	public String updateForm(ProjectsForm form, Model model, @PathVariable("id") Long projectId) {
		boolean result = projectsService.updateByPrimaryKeySelective(form);
		if (result) {
			return "redirect:/projects/{id}/update?complete";
		} else {
			model.addAttribute("updateProjects", "���湤����Ŀʧ��!");
		}
		return "/projects/projectsSelect";
	}

	// ���³ɹ�
	@RequestMapping(value = "/{id}/update", params = { "complete" }, method = RequestMethod.GET)
	public String updateForm(Model model) {
		model.addAttribute("updateProjects", "���湤����Ŀ�ɹ�!");
		return "/projects/projectsSelect";
	}

	// ɾ����Ʒ ɾ��
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") Long projectId) {
		Projects projects = projectsService.selectByPrimaryKey(projectId);
		int n = projects.getAddorsubtract()+3;
		projects.setAddorsubtract(new Short(String.valueOf(n)));
		//System.out.println(projects);
		ProjectsForm form = new ProjectsForm();
		BeanUtils.copyProperties(projects, form);
		boolean result = projectsService.updateByPrimaryKeySelective(form);
		if (result) {
			return "redirect:/projects/{id}/delete?complete";
		} else {
			model.addAttribute("DeleteProjectsResult", "ɾ��������Ŀʧ�ܣ�");
		}
		return "/projects/projectsSelect";
	}

	// ɾ��������Ŀ�ɹ�
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET, params = { "complete" })
	public String recovery(Model model) {
		model.addAttribute("RecoveryProjectsResult", "ɾ��������Ŀ�ɹ���");
		return "/projects/projectsSelect";
	}
	//�ָ�������Ŀ
	@RequestMapping(value = "/{id}/recovery", method = RequestMethod.GET)
	public String recovery(Model model, @PathVariable("id") Long projectId) {
		Projects projects = projectsService.selectByPrimaryKey(projectId);
		//System.out.println(projects);
		int n = projects.getAddorsubtract()-3;
		projects.setAddorsubtract(new Short(String.valueOf(n)));
		//System.out.println(projects);
		ProjectsForm form = new ProjectsForm();
		BeanUtils.copyProperties(projects, form);
		boolean result = projectsService.updateByPrimaryKeySelective(form);
		if (result) {
			return "redirect:/projects/{id}/recovery?complete";
		} else {
			model.addAttribute("RecoveryProjectsResult", "�ָ�������Ŀʧ�ܣ�");
		}
		return "/projects/projectsSelect";
	}


	// �ָ�������Ŀ�ɹ�
	@RequestMapping(value = "/{id}/recovery", method = RequestMethod.GET, params = { "complete" })
	public String delete(Model model) {
		model.addAttribute("DeleteProjectsResult", "�ָ�������Ŀ�ɹ���");
		return "/projects/projectsSelect";
	}
}
