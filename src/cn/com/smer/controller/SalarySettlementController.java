package cn.com.smer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dtoform.SalarySettlementDetailsForm;
import cn.com.smer.dtoform.SalarySettlementsForm;
import cn.com.smer.service.SalarySettlementDetailsService;
import cn.com.smer.service.SalarySettlementService;
/*
 * 	���ʽ���������
 * 		���ڴ���Ա����Ա����Ϣ�Ͷ�Ӧ�·ݵ�ʵ�ʹ���
 * 
 */
@Controller
@RequestMapping(value = "/salarysettlement")
public class SalarySettlementController {
	@Autowired
	private SalarySettlementService salarySettlementService;
	@Autowired
	private DepartmentsMapper departmentsMapper;
	@Autowired
	private SalarySettlementDetailsService salarySettlementDetailsService;
	@RequestMapping(value = "/list", params = { "form" })
	public String list(Model model) {
		List<Departments> listDepartments = departmentsMapper.selectAll();
		model.addAttribute("listDepartments", listDepartments);
		return "/salarySettlements/salary_settlement";
	}

	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public String listForm(Model model, SalarySettlementsForm form) {
		String departmentName = departmentsMapper.selectByPrimaryKey(form.getDepartmentId()).getDepartmentName();
		model.addAttribute("departmentName", departmentName);
		List<SalarySettlementsForm> listSalarySettlements = salarySettlementService.selctAll(form);
		List<Departments> listDepartments = departmentsMapper.selectAll();
		model.addAttribute("listDepartments", listDepartments);
		Long trueSalarySum = (long) 0;
		for (SalarySettlementsForm salarySettlementsForm : listSalarySettlements) {
			trueSalarySum += salarySettlementsForm.getTrueSalary();
			//System.out.println("salarySettlementsForm"+salarySettlementsForm);
		}

		model.addAttribute("listSalarySettlements", listSalarySettlements);
		model.addAttribute("trueSalarySum", trueSalarySum);
		model.addAttribute("departmentName", listSalarySettlements.get(0).getDepartmentName());
		model.addAttribute("stateName", listSalarySettlements.get(0).getStateName());
		return "/salarySettlements/salary_settlement";
	}

	@RequestMapping(value = "/{id}/update", params = { "form" }, method = RequestMethod.POST)
	public String update(@PathVariable("id") Long salarySettlementId, Long trueSalary) {
		if (salarySettlementService.updateByParmaryKey(salarySettlementId, trueSalary)) {
			return "redirect:/salarysettlement/" + salarySettlementId + "/update?complete";
		} else {
			return "/main";
		}
	}

	@RequestMapping(value = "/{id}/update", params = { "complete" })
	public String updateCmplete(@PathVariable("id") Long salarySettlementId,Model model) {
		List<SalarySettlementDetailsForm> listSalarySettlementDetails = salarySettlementDetailsService.selectBysalarySettlementId(salarySettlementId);
		Long salarySum = salarySettlementDetailsService.selectTureSalary(salarySettlementId);
		Long salaryState = salarySettlementDetailsService.selectSalaryState(salarySettlementId);
		model.addAttribute("salaryState",salaryState);
		model.addAttribute("salarySum", salarySum);
		model.addAttribute("salarySettlementId", salarySettlementId);
		model.addAttribute("listSalarySettlementDetails", listSalarySettlementDetails);
		return "/salarySettlements/salary_settlement_details";
	}
	@RequestMapping(value = "/updateAll")
	public @ResponseBody Map<String, String> updateAll(@RequestBody List<Long> arrSalarySettlementsId) {
		for(Long l : arrSalarySettlementsId) {
			//System.out.println(l);
		}
		if(salarySettlementService.updateAll(arrSalarySettlementsId)) {
			//System.out.println(12412424);
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "success");
			return map;
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "fail");
			return map;
		}
	}
}
