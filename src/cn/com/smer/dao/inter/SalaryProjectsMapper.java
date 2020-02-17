package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.SalaryProjects;

public interface SalaryProjectsMapper {
    int deleteByPrimaryKey(Short salaryProjectId);

    int insert(SalaryProjects record);

    int insertSelective(SalaryProjects record);

    SalaryProjects selectByPrimaryKey(Short salaryProjectId);

    int updateByPrimaryKeySelective(SalaryProjects record);

    int updateByPrimaryKey(SalaryProjects record);

	List<SalaryProjects> selectAll();
}