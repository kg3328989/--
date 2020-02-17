package cn.com.smer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.dto.SalarySettlementDetails;
import cn.com.smer.dao.dto.SalarySettlements;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dao.inter.EmployeesMapper;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dao.inter.SalarySettlementDetailsMapper;
import cn.com.smer.dao.inter.SalarySettlementsMapper;
import cn.com.smer.dtoform.ProjectsForm;
import cn.com.smer.dtoform.SalarySelectForm;
import cn.com.smer.dtoform.SalarySettlementsForm;

@Service
public class BaoBiaoService {
	@Autowired
	private SalarySettlementsMapper salarySettlementsDao;
	@Autowired
	private DepartmentsMapper departmentsMapper;
	@Autowired
	private EmployeesMapper employeesMapper;
	@Autowired
	private SalarySettlementDetailsMapper salarySettlementDetailsMapper;
	@Autowired
	private ProjectsMapper projectsMapper;

	public List<SalarySettlements> selectAll() {
		return salarySettlementsDao.selectAll();
	}

	public List<SalarySettlements> selectByDate(SalarySettlementsForm form) {
		SalarySettlements salarySettlements = new SalarySettlements();
		BeanUtils.copyProperties(form, salarySettlements);
		return salarySettlementsDao.selectBySelective(salarySettlements);
	}

	public List<SalarySettlements> selectDistinctDept() {
		return salarySettlementsDao.selectDistinctDept();
	}

	public List<SalarySettlements> selectBySelective(SalarySettlementsForm form) {
		SalarySettlements salarySettlements = new SalarySettlements();
		BeanUtils.copyProperties(form, salarySettlements);
		return salarySettlementsDao.selectBySelective(salarySettlements);
	}

	public List<SalarySettlements> selectAllDate() {

		return salarySettlementsDao.selectAllDate();
	}

	public List<SalarySelectForm> SelectSalarySettlementDetailsForm(SalarySelectForm form) {
		SalarySettlements salarySettlementsSelect = new SalarySettlements();
		if (form.getDepartmentId() != null)
			salarySettlementsSelect.setDepartmentId(form.getDepartmentId());
		if (form.getDepartmentId() != null)
			salarySettlementsSelect.setEmployeeId(form.getEmployeeId());
		//System.out.println(salarySettlementsSelect);
		List<SalarySettlements> listSalarySettlement = salarySettlementsDao.selectBySelective(salarySettlementsSelect);
		//System.out.println(listSalarySettlement.size() + "===================================================");
		List<SalarySelectForm> listsalarySelectForm = new ArrayList<SalarySelectForm>();
		for (SalarySettlements salarySettlements : listSalarySettlement) {
			if (form.getDate1() != null && form.getDate2() != null) {
				if (salarySettlements.getImportDate().getTime() >= form.getDate1().getTime()
						&& salarySettlements.getImportDate().getTime() <= form.getDate2().getTime()) {
					SalarySelectForm salarySelectForm = new SalarySelectForm();
					salarySelectForm.setEmployeeId(salarySettlements.getEmployeeId());
					salarySelectForm.setEmployeeName(
							employeesMapper.selectByPrimaryKey(salarySettlements.getEmployeeId()).getEmployeeName());
					salarySelectForm.setDepartmentId(salarySelectForm.getDepartmentId());
					salarySelectForm.setDepartmentName(departmentsMapper
							.selectByPrimaryKey(salarySettlements.getDepartmentId()).getDepartmentName());
					List<SalarySettlementDetails> listSalarySettlementDetails = salarySettlementDetailsMapper
							.selectBySalarySettlementId(salarySettlements.getSalarySettlementId());
					int count = 0;
					String[] projectName = new String[listSalarySettlementDetails.size()];
					Long[] money = new Long[listSalarySettlementDetails.size()];
					Long[] projectId = new Long[listSalarySettlementDetails.size()];
					Short[] addorsubtract = new Short[listSalarySettlementDetails.size()];
					for (SalarySettlementDetails salarySettlementDetails : listSalarySettlementDetails) {
						projectName[count] = projectsMapper.selectByPrimaryKey(salarySettlementDetails.getProjectId())
								.getProjectName();
						money[count] = salarySettlementDetails.getMoney();
						addorsubtract[count] = salarySettlementDetails.getAddorsubtract();
						projectId[count] = salarySettlementDetails.getProjectId();
						count++;

					}
					salarySelectForm.setProjectId(projectId);
					salarySelectForm.setImportDate(salarySettlements.getImportDate());
					salarySelectForm.setProjectName(projectName);
					salarySelectForm.setMoney(money);
					salarySelectForm.setAddorsubtract(addorsubtract);
					listsalarySelectForm.add(salarySelectForm);
				}
			} else if (form.getDate1() != null && form.getDate2() == null) {
				if (salarySettlements.getImportDate().getTime() >= form.getDate1().getTime()) {
					SalarySelectForm salarySelectForm = new SalarySelectForm();
					salarySelectForm.setEmployeeId(salarySettlements.getEmployeeId());
					salarySelectForm.setEmployeeName(
							employeesMapper.selectByPrimaryKey(salarySettlements.getEmployeeId()).getEmployeeName());
					salarySelectForm.setDepartmentId(salarySelectForm.getDepartmentId());
					salarySelectForm.setDepartmentName(departmentsMapper
							.selectByPrimaryKey(salarySettlements.getDepartmentId()).getDepartmentName());
					List<SalarySettlementDetails> listSalarySettlementDetails = salarySettlementDetailsMapper
							.selectBySalarySettlementId(salarySettlements.getSalarySettlementId());
					int count = 0;
					String[] projectName = new String[listSalarySettlementDetails.size()];

					Long[] money = new Long[listSalarySettlementDetails.size()];

					Short[] addorsubtract = new Short[listSalarySettlementDetails.size()];

					Long[] projectId = new Long[listSalarySettlementDetails.size()];
					for (SalarySettlementDetails salarySettlementDetails : listSalarySettlementDetails) {
						projectName[count] = projectsMapper.selectByPrimaryKey(salarySettlementDetails.getProjectId())
								.getProjectName();
						money[count] = salarySettlementDetails.getMoney();
						addorsubtract[count] = salarySettlementDetails.getAddorsubtract();
						projectId[count] = salarySettlementDetails.getProjectId();
						count++;

					}
					salarySelectForm.setProjectId(projectId);
					salarySelectForm.setImportDate(salarySettlements.getImportDate());
					salarySelectForm.setProjectName(projectName);
					salarySelectForm.setMoney(money);
					salarySelectForm.setAddorsubtract(addorsubtract);
					listsalarySelectForm.add(salarySelectForm);
				}
			} else if (form.getDate1() == null && form.getDate2() != null) {
				if (salarySettlements.getImportDate().getTime() <= form.getDate2().getTime()) {
					SalarySelectForm salarySelectForm = new SalarySelectForm();
					salarySelectForm.setEmployeeId(salarySettlements.getEmployeeId());
					salarySelectForm.setEmployeeName(
							employeesMapper.selectByPrimaryKey(salarySettlements.getEmployeeId()).getEmployeeName());
					salarySelectForm.setDepartmentId(salarySelectForm.getDepartmentId());
					salarySelectForm.setDepartmentName(departmentsMapper
							.selectByPrimaryKey(salarySettlements.getDepartmentId()).getDepartmentName());
					List<SalarySettlementDetails> listSalarySettlementDetails = salarySettlementDetailsMapper
							.selectBySalarySettlementId(salarySettlements.getSalarySettlementId());
					int count = 0;
					String[] projectName = new String[listSalarySettlementDetails.size()];

					Long[] money = new Long[listSalarySettlementDetails.size()];

					Short[] addorsubtract = new Short[listSalarySettlementDetails.size()];
					Long[] projectId = new Long[listSalarySettlementDetails.size()];
					for (SalarySettlementDetails salarySettlementDetails : listSalarySettlementDetails) {
						projectName[count] = projectsMapper.selectByPrimaryKey(salarySettlementDetails.getProjectId())
								.getProjectName();
						money[count] = salarySettlementDetails.getMoney();
						addorsubtract[count] = salarySettlementDetails.getAddorsubtract();
						projectId[count] = salarySettlementDetails.getProjectId();
						count++;

					}
					salarySelectForm.setProjectId(projectId);
					salarySelectForm.setImportDate(salarySettlements.getImportDate());
					salarySelectForm.setProjectName(projectName);
					salarySelectForm.setMoney(money);
					salarySelectForm.setAddorsubtract(addorsubtract);
					listsalarySelectForm.add(salarySelectForm);
				}
			} else {
				SalarySelectForm salarySelectForm = new SalarySelectForm();
				salarySelectForm.setEmployeeId(salarySettlements.getEmployeeId());
				salarySelectForm.setEmployeeName(
						employeesMapper.selectByPrimaryKey(salarySettlements.getEmployeeId()).getEmployeeName());
				salarySelectForm.setDepartmentId(salarySelectForm.getDepartmentId());
				salarySelectForm.setDepartmentName(
						departmentsMapper.selectByPrimaryKey(salarySettlements.getDepartmentId()).getDepartmentName());
				List<SalarySettlementDetails> listSalarySettlementDetails = salarySettlementDetailsMapper
						.selectBySalarySettlementId(salarySettlements.getSalarySettlementId());
				int count = 0;
				String[] projectName = new String[listSalarySettlementDetails.size()];

				Long[] money = new Long[listSalarySettlementDetails.size()];

				Short[] addorsubtract = new Short[listSalarySettlementDetails.size()];
				Long[] projectId = new Long[listSalarySettlementDetails.size()];
				for (SalarySettlementDetails salarySettlementDetails : listSalarySettlementDetails) {
					projectName[count] = projectsMapper.selectByPrimaryKey(salarySettlementDetails.getProjectId())
							.getProjectName();
					money[count] = salarySettlementDetails.getMoney();
					addorsubtract[count] = salarySettlementDetails.getAddorsubtract();
					projectId[count] = salarySettlementDetails.getProjectId();
					count++;

				}
				salarySelectForm.setProjectId(projectId);
				salarySelectForm.setImportDate(salarySettlements.getImportDate());
				salarySelectForm.setProjectName(projectName);
				salarySelectForm.setMoney(money);
				salarySelectForm.setAddorsubtract(addorsubtract);
				listsalarySelectForm.add(salarySelectForm);
			}
		}
		return listsalarySelectForm;
	}

	public List<Projects> selectAllProjects() {
		return projectsMapper.selectBySelective(new ProjectsForm());
	}

	/* sxb�������� */
	public List<SalarySettlementDetails> selectSSD(List<SalarySettlements> allss) {
		List<SalarySettlementDetails> allSSD = new ArrayList<SalarySettlementDetails>();
		//System.out.println("��ǵ�2");

		// ��ȡ������
		for (SalarySettlements ss : allss) {
			//System.out.println("��ǵ�3");
			Long c = ss.getSalarySettlementId();
			//System.out.println(c);

			List<SalarySettlementDetails> SSDtemp = salarySettlementDetailsMapper.selectBySSId(c);
			for (SalarySettlementDetails temp : SSDtemp) {
				allSSD.add(temp);
			}
		}
		return allSSD;
	}
}
