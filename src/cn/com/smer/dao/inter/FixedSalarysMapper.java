package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.FixedSalarys;

public interface FixedSalarysMapper {
	int deleteByPrimaryKey(Long fixedSalaryId);

    int insert(FixedSalarys record);

    int insertSelective(FixedSalarys record);

    FixedSalarys selectByPrimaryKey(Long fixedSalaryId);
    /*��ʹ��*/
    int updateByPrimaryKeySelective(FixedSalarys record);
    
    int updateByPrimaryKey(FixedSalarys record);
    
	/*�½���ѯ����*/
    /*<!-- ���������ű�Ų�ѯ -->*/List<FixedSalarys> selectByDeptIdSelective(Long deptId);
  
    /*<!-- ������Ա����Ų�ѯ -->*/List<FixedSalarys> selectByEmpIdSelective(Long empId);
    
    /*<!-- ������ѯȫ��������Ŀ��� -->*/List<Long> selectProjectIdByDepartmentId(Long deptId);
    
	/*<!-- ͨ��Ա����ź���Ŀ����޸Ĺ���ֵ -->*/int batchUpdate(FixedSalarys record);
	
	/*<!-- �鿴ĳ��Ա���Ĺ̶������Ƿ���� -->*/FixedSalarys selectBySelective(FixedSalarys record);
    
}