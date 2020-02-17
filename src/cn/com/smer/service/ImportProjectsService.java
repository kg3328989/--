package cn.com.smer.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dao.inter.EmployeesMapper;
import cn.com.smer.dao.inter.ImportProjectsMapper;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dtoform.ImportProjectsForm;

@Service
public class ImportProjectsService {
	@Autowired
	private ImportProjectsMapper importProjectsDao;
	@Autowired
	private EmployeesMapper employeesDao;
	@Autowired
	private ProjectsMapper projectsDao;
	@Autowired
	private DepartmentsMapper departmentsDao;
	//������Ŀ¼��
	public boolean insertImport(ImportProjectsForm ImportProjectsForm) {
		//���ݵĿ�����Ҫ��֤���������е�������һ��
		ImportProjects pro = new ImportProjects();
		BeanUtils.copyProperties(ImportProjectsForm, pro);
		int count = importProjectsDao.insertSelective(pro);
		if(count == 1){
			return true;
		}else {
			return false;
		}
	}
	//�鲿������û��¼���Ա��
	public boolean selectemployee(Long departmentId,Long employeeId) {
		Employees emp = employeesDao.selectEmployeeById(employeeId);
		if(emp.getDepartmentId()==departmentId) {
			return true;
		}else {
			return false;
		}
	}
	//�жϵ�����Ϣ�Ƿ��ظ�¼��
	public boolean selectImportProjects(Date importDate,Long employeeId,Long projectId) {
		ImportProjects importTest = new ImportProjects();
		importTest.setImportDate(importDate);
		importTest.setEmployeeId(employeeId);
		importTest.setProjectId(projectId);
		List<ImportProjects> List = importProjectsDao.selectImportText(importTest);
		if(List.size()==0) {
			return true;
		}else {
			return false;
		}
	}
	//ֱ�Ӳ�ѯ
	public List<ImportProjects> selectImportProjects(ImportProjectsForm importProjectsForm){
		ImportProjects pro = new ImportProjects();
		BeanUtils.copyProperties(importProjectsForm, pro);
		return importProjectsDao.selectByInfo(pro);
	}
	//��ʼ�������ڲ�
	public List<ImportProjects> selectImportProjectsTwo(ImportProjectsForm importProjectsForm){
		ImportProjects pro = new ImportProjects();
		BeanUtils.copyProperties(importProjectsForm, pro);
		return importProjectsDao.selectBySomething(pro);
	}
	
	public ImportProjects selectImportProjectsById(Long importProjectId){
		return importProjectsDao.selectByPrimaryKey(importProjectId);
	}
	public boolean updateImportProjects(ImportProjectsForm importProjectsForm) {
		ImportProjects importProjects = new ImportProjects();
		BeanUtils.copyProperties(importProjectsForm, importProjects);
		int count = importProjectsDao.updateByPrimaryKeySelective(importProjects);
		if(count == 1){
			return true;
		}else {
			return false;
		}
	}
	public boolean insertImportProjects(List<Map> listMap) {
		boolean result = false;
		int hcount = 0;
		int lcount = 0;
		List<Long> id = new ArrayList<Long>();
		int count = 0;
		for(Map l : listMap) {
			//System.out.println("��" + hcount + "����ѭ����");
			ImportProjects importProjects = new ImportProjects();
			if(hcount == 0) {
				int length = l.size();
				//System.out.println("**************" + l.size());
				for(lcount=4;lcount<length;lcount++) {
					String projectName = (String) l.get(lcount);
					//System.out.println("projectName" + projectName);
					List<Projects> projectid = projectsDao.selectByProjectName(projectName);
					for(Projects m : projectid) {
						//System.out.println("**************" + m.getProjectName());
						//System.out.println("**************" + m.getProjectId());
					}
					id.add(projectid.get(0).getProjectId());
				}
				lcount=0;
				hcount++;
				continue;
			}
			if(hcount!=0) {
				int length = l.size();
				for(lcount = 0 ; lcount< length ; lcount++) {
					if(lcount == 0) {
						String empId = (String) l.get(lcount);
						importProjects.setEmployeeId(Long.valueOf(empId));//Ա�����get��
					}
					if(lcount == 2) {
						Departments dept = departmentsDao.selectByName((String) l.get(lcount));
						importProjects.setDepartmentId(dept.getDepartmentId());
					}
					if(lcount == 3) {
						Date importDate = (Date) l.get(lcount);
						importProjects.setImportDate(importDate);
					}
					if(lcount >= 4) {
						importProjects.setProjectId(id.get(count));
						String info = (String) l.get(lcount);
						importProjects.setImportInfo(Long.valueOf(info));
						int i = importProjectsDao.insertSelective(importProjects);
						if(i == 1) {
							result = true;
						}else {
							//System.out.println("�������ݳ���");
							//System.out.println(importProjects.toString());
							return false;
						}
						count++;
					}
				}
			}
			lcount = 0;
			hcount++;
			count = 0;
		}
		return result;
	}
	
	public boolean deleteImportProjects(Long importProjectId) {
		int count = importProjectsDao.deleteByPrimaryKey(importProjectId);
		if(count == 1) {
			return true;
		}else {
			return false;
		}
	}

}
