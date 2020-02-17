package cn.com.smer.dao.inter;

import java.util.List;
import cn.com.smer.dao.dto.Employees;

public interface EmployeesMapper {
    int deleteByPrimaryKey(Long employeeId);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(Long employeeId);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);
    
	/*<!-- sxbʹ�ã�ͨ�����ű�Ų�ѯȫ��Ա��������� -->*/
    List<Employees> selectByDepartmentId(Long deptid);
    /*<!-- sxbʹ�ã� -->*/
    List<Employees> selectBySelective(Employees employee);
	
	Long selectDepartmentIdByEmployeeId(Long employeeId);
	
	List<Employees> selectAll();
	//zxͨ��ID��ѯ����//sxb����
	Employees selectEmployeeById(Long employeeId);
	List<Employees> selectEmployees(Long employeeId);
	
}