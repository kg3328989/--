package cn.com.smer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.FixedSalarys;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.dto.SalarySettlements;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dao.inter.EmployeesMapper;
import cn.com.smer.dao.inter.FixedSalarysMapper;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dtoform.FixedSalarysForm;

@Service
public class FixedSalarysService {

	@Autowired
	private FixedSalarysMapper fixedSalarysDao;
	@Autowired
	private ProjectsMapper projectsDao;
	@Autowired
	private EmployeesMapper employeesDao;
	@Autowired
	private DepartmentsMapper departmentsDao;

	// ����������Ա���̶�������Ϣ���²��ų�ʼ����
	public void insertByDepartmentId(Long deptId) {
		if (fixedSalarysDao.selectByDeptIdSelective(deptId).size() == 0) {
			List<Employees> empe = employeesDao.selectByDepartmentId(deptId);
			List<Long> ppd = projectsDao.selectProjectIdByOne();
			for (Employees emp : empe) {
				for (Long pp : ppd) {
					FixedSalarys a = new FixedSalarys();
					a.setDepartmentId(deptId);
					a.setEmployeeId(emp.getEmployeeId());
					a.setProjectId(pp);
					Long b = Long.valueOf(0);
					a.setSalary(b);
					int c = fixedSalarysDao.insertSelective(a);
					if (c == 0) {
						//System.out.println("�²���Ա���̶�����������Ϣ����");
					}
				}
			}
		}
	}

	// ������Ա���̶�������Ϣ��ʼ����Ա����ʼ����
	public void checkNewEmployee(Long deptId, List<Employees> allEmp) {
		List<Long> ppd = projectsDao.selectProjectIdByOne();
		for (Employees emp : allEmp) {
			if (fixedSalarysDao.selectByEmpIdSelective(emp.getEmployeeId()).size() == 0) {
				for (Long pp : ppd) {
					FixedSalarys a = new FixedSalarys();
					a.setDepartmentId(deptId);
					a.setEmployeeId(emp.getEmployeeId());
					a.setProjectId(pp);
					Long b = Long.valueOf(0);
					a.setSalary(b);
					int c = fixedSalarysDao.insertSelective(a);
					if (c == 0) {
						//System.out.println("��Ա���̶�����������Ϣ����");
					}
				}
			}
		}
	}

	// ��ʼ������
	public void initialize(Long deptId) {
		//System.out.println("�_ʼ��ʼ����" + deptId);
		List<Long> fspd = fixedSalarysDao.selectProjectIdByDepartmentId(deptId);
		//System.out.println("��ǵ�1");
		List<Long> ppd = projectsDao.selectProjectIdByOne();
		//System.out.println("��ǵ�2");
		List<Employees> empe = employeesDao.selectByDepartmentId(deptId);
		/*for(Employees a : empe) {
			System.out.println("Ա��Id=" + a.getEmployeeId());
			System.out.println("Ա������=" + a.getEmployeeName());
			System.out.println();
		}*/
		//System.out.println("��ǵ�3");
		for (Long pp : ppd) {

			if (!fspd.contains(pp)) {
				//System.out.println("��ǵ�4");
				// ��������Ա��
				for (Employees emp : empe) {
					FixedSalarys a = new FixedSalarys();
					a.setDepartmentId(emp.getDepartmentId());
					a.setEmployeeId(emp.getEmployeeId());
					a.setProjectId(pp);
					Long b = Long.valueOf(0);
					a.setSalary(b);
					int c = fixedSalarysDao.insertSelective(a);
					if (c == 0) {
						//System.out.println("��ʼ������");
					}
				}
				//System.out.println("��ǵ�5");
			}

		}
	}

	// ͨ������Id��ѯ�̶�������ϸ
	public List<FixedSalarys> selectByDeptId(Long deptid) {
		List<FixedSalarys> fixedSalarys = fixedSalarysDao.selectByDeptIdSelective(deptid);
		return fixedSalarys;
	}

	// ͨ��Ա��Id��ѯ�̶�������ϸ
	public List<FixedSalarys> selectByEmpId(Long empId) {
		List<FixedSalarys> fixedSalarys = fixedSalarysDao.selectByEmpIdSelective(empId);
		return fixedSalarys;
	}

	// ͨ���̶���Ŀ����޸Ĺ̶���Ŀ
	public boolean updateByPrimaryKeySelective(FixedSalarysForm fsForm) {
		// ���ݵĿ���,Ҫ��֤���������е�������һ�¡�
		FixedSalarys fs = new FixedSalarys();
		BeanUtils.copyProperties(fsForm, fs);
		int count = fixedSalarysDao.updateByPrimaryKeySelective(fs);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	// ͨ��Ա����ź���Ŀ����޸Ĺ���ֵ
	public boolean batchUpdate(FixedSalarysForm fsForm) {
		// ���ݵĿ���,Ҫ��֤���������е�������һ�¡�
		FixedSalarys fs = new FixedSalarys();
		BeanUtils.copyProperties(fsForm, fs);

		List<Long> employees = fsForm.getCheckbox();

		int temp = 1;
		for (Long employee : employees) {
			fs.setEmployeeId(employee);
			int count = fixedSalarysDao.batchUpdate(fs);
			if (count != 1) {
				temp = 0;
				break;
			}
		}
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}

	// Excal�����Ϣ¼��
	public boolean updateByExcelAll(List<Map> arrFixedSalaryId) {
		boolean result = true;
		int conth = 0;//�м���
		int contl = 0;//�м���
		List<Long> id = new ArrayList<Long>();//ת��id list
		int count = 0;//�̶�������Ŀ������
		//��ѭ��
		for (Map l : arrFixedSalaryId) {
			//System.out.println("��" + conth + "����ѭ����");
			FixedSalarys fx = new FixedSalarys();
			//�ж��ǲ��ǹ̶�����������һ��
			if(conth == 0) {
				int length = l.size();
				//System.out.println("**************" + l.size());
				//�������� ��ѭ�� ����
				for(contl = 3 ; contl< length ; contl++) {
					String projectName = (String) l.get(contl);
					//System.out.println("projectName" + projectName);
					List<Projects> projectid = projectsDao.selectByProjectName(projectName);
					
					//��������*********************************************************
					for(Projects m : projectid) {
						//System.out.println("**************" + m.getProjectName());
						//System.out.println("**************" + m.getProjectId());
					}
					//��������*********************************************************
					
					id.add(projectid.get(0).getProjectId());//��Ž���
				}
				contl = 0;//��ָ���ʼ��
				conth++;//��ָ�����
				continue;
			}
			
			if(conth != 0) {
				int length = l.size();
				//System.out.println("l.size()" + l.size());
				//��ѭ��
				for(contl = 0 ; contl< length ; contl++) {
					if(contl == 0) {
						String empId = (String) l.get(contl);
						fx.setEmployeeId(Long.valueOf(empId));//Ա�����get��
					}
					if(contl == 2) {
						Departments dept = departmentsDao.selectByName((String) l.get(contl));
						fx.setDepartmentId(dept.getDepartmentId());//���ű��get��
					}
					if(contl >= 3) {
						
						fx.setProjectId(id.get(count));//��Ŀ���get!
						String slry = (String) l.get(contl);
						
						//���в���֮ǰ��Ҫ����salary�ж�
						FixedSalarys fxtest = fixedSalarysDao.selectBySelective(fx);
						if(fxtest != null) {
							//˵�����ݴ��ڣ����������޸�
							fxtest.setSalary(Long.valueOf(slry));//��Ӧ����get!
							int i = fixedSalarysDao.updateByPrimaryKeySelective(fxtest);
							if(i == 1) {
								result = true;
							}else {
								//System.out.println("�޸����ݳ���");
								//System.out.println(fxtest.toString());
								return false;
							}
						}else {
							//˵�����ݲ����ڣ�������������
							fx.setSalary(Long.valueOf(slry));//��Ӧ����get!
							int i = fixedSalarysDao.insertSelective(fx);
							if(i == 1) {
								result = true;
							}else {
								//System.out.println("�������ݳ���");
								//System.out.println(fx.toString());
								return false;
							}
						}
						count++;
					}
				}
			}
			contl = 0;//��ָ�����
			count = 0;//�̶�������Ŀ����������
			conth++;//��ָ�����
		}
		return result;
	}
}
