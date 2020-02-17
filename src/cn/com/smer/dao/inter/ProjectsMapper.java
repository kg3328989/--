package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dtoform.ProjectsForm;

public interface ProjectsMapper {
    int deleteByPrimaryKey(Long projectId);

    int insert(Projects record);

    int insertSelective(Projects record);

    Projects selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Projects record);

    int updateByPrimaryKey(Projects record);

	List<Projects> selectBySelective(ProjectsForm form);
	
	List<Long> selectProjectIdByOne();
	
	List<Projects> selectByProjectType();
	
	List<Projects> selectByProjectTypeIs();
	
	List<Projects> getAllOneProjects();
	
	List<Projects> getAllProjects();
	
	List<Projects> getAllOkProjects();

	List<Projects> selectByProjectName(String projectName);

}