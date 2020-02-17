package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dao.dto.Projects;

public interface ImportProjectsMapper {
    int deleteByPrimaryKey(Long importProjectId);

    int insert(ImportProjects record);

    int insertSelective(ImportProjects pro);

    ImportProjects selectByPrimaryKey(Long importProjectId);
    
    List<ImportProjects> selectByInfo(ImportProjects pro);

    int updateByPrimaryKeySelective(ImportProjects record);

    int updateByPrimaryKey(ImportProjects record);
    
    List<ImportProjects> selectBySomething(ImportProjects pro);
  //��ѯ������Ϣ�Ƿ��ظ�¼��
    List<ImportProjects> selectImportText(ImportProjects record);
}