package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.Departments;

public interface DepartmentsMapper {
    int deleteByPrimaryKey(Long departmentId);

    int insert(Departments record);

    int insertSelective(Departments record);

    Departments selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);
    /*<!-- sxbʹ�ã�������ѯȫ�����ű�� -->*/
    List<Departments> selectAll();
    /*<!-- sxbʹ�ã�ͨ���������Ʋ�ѯ���ű�� -->*/
    Departments selectByName(String deptName);
}