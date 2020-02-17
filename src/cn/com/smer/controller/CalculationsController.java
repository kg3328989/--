package cn.com.smer.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.smer.dao.dto.Calculations;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dtoform.CalculationsForm;
import cn.com.smer.service.CalculationsService;
import cn.com.smer.service.ProjectsService;
import cn.com.smer.service.SalaryProjectsService;
/*
 * 	������Ŀ
 * 		1.����
 * 		2.�޸�
 * 		3.��ѯ
 */
@Controller
@RequestMapping("/calculation")
public class CalculationsController {
	@Autowired
	private CalculationsService calculationsService;
	@Autowired
	private ProjectsService projectsService;
	// ��Ӳ�Ʒ ����ʾ
	@RequestMapping(value = "{id}/create", params = { "form" })
	public String createForm(@PathVariable("id") Long id, Model model) {
		Projects projects =  projectsService.selectByPrimaryKey(id);
		model.addAttribute("projects", projects);
		return "/calculation/calculationAdd";
	}

	// �½�
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(CalculationsForm form, Model model) {
		boolean result = calculationsService.insertSelective(form);
		if (result) {
			return "redirect:/projects/create?complete"; // �ض�����һ��ҵ����Ʒ���
		} else {
			model.addAttribute("AddprojectsResult", "�������Y�Ŀʧ��!");
		}
		return "/calculation/calculationAdd";
	}

	// �޸�Ա�� ����ʾ
	@RequestMapping(value = "/{id}/update", params = { "form" })
	public String updateForm(Model model, @PathVariable("id") Long Id) {
		Calculations calculations =  calculationsService.selectByPrimaryKey(Id);
		Projects projects = projectsService.selectByPrimaryKey(calculations.getProjectId());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1", "��");
		map.put("2", "��");
		map.put("3", "��");
		map.put("4", "��");
		model.addAttribute("operatorMap", map);
		model.addAttribute("projects", projects);
		model.addAttribute("calculations", calculations);
		return "/calculation/calculationUpdate";
	}

	// ����
	@RequestMapping(value = "/{id}/update", params = { "update" }, method = RequestMethod.POST)
	public String updateForm(CalculationsForm form, Model model, @PathVariable("id") Long departmentId) {
		boolean result = calculationsService.updateByPrimaryKeySelective(form);
		if (result) {
			return "redirect:/calculation/{id}/update?complete";
		} else {
			model.addAttribute("updateCalculations", "���ü��㷽��ʧ��!");
		}

		return "/projects/projectsSelect";
	}

	// ���³ɹ�
	@RequestMapping(value = "/{id}/update", params = { "complete" }, method = RequestMethod.GET)
	public String updateForm(Model model) {
		model.addAttribute("updateCalculations", "���ü��㷽���ɹ�!");
		return "/projects/projectsSelect";
	}
	
	

}
