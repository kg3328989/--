package cn.com.smer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.SalarySettlementDetails;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dao.inter.SalarySettlementDetailsMapper;
import cn.com.smer.dao.inter.SalarySettlementsMapper;
import cn.com.smer.dtoform.SalarySettlementDetailsForm;

@Service
public class SalarySettlementDetailsService {
	@Autowired
	private ProjectsMapper projectsMapper;
	@Autowired
	private SalarySettlementDetailsMapper salarySettlementDetailsMapper;
	@Autowired
	private SalarySettlementsMapper salarySettlementMapper;

	public List<SalarySettlementDetailsForm> selectBysalarySettlementId(Long salarySettlementId) {
		List<SalarySettlementDetails> listSalarySettlementDetails = salarySettlementDetailsMapper
				.selectBySalarySettlementId(salarySettlementId);
		List<SalarySettlementDetailsForm> listSalarySettlementDetailsForm = new ArrayList<SalarySettlementDetailsForm>();
		for (SalarySettlementDetails salarySettlementDetails : listSalarySettlementDetails) {
			SalarySettlementDetailsForm form = new SalarySettlementDetailsForm();
			form.setSalarySettlementId(salarySettlementDetails.getSalarySettlementId());
			form.setProjectId(salarySettlementDetails.getProjectId());
			form.setProjectName(projectsMapper.selectByPrimaryKey(salarySettlementDetails.getProjectId()).getProjectName());
			form.setMoney(salarySettlementDetails.getMoney());
			form.setAddorsubtract(salarySettlementDetails.getAddorsubtract());
			listSalarySettlementDetailsForm.add(form);
		}

		return listSalarySettlementDetailsForm;
	}

	public Long selectTureSalary(Long salarySettlementId) {
		return salarySettlementMapper.selectByPrimaryKey(salarySettlementId).getTrueSalary();
	}

	public Long selectSalaryState(Long salarySettlementId) {
		return salarySettlementMapper.selectByPrimaryKey(salarySettlementId).getState();
	}

}
