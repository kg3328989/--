package cn.com.smer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.inter.DepartmentsMapper;

@Service
public class DepartmentsService {
	@Autowired
	private DepartmentsMapper departmentsDao;
	
	public List<Departments> getAllDepartments(){
		return departmentsDao.selectAll();
	}
	
	public Departments selectByName(String deptName) {
		return departmentsDao.selectByName(deptName);
	}
}
