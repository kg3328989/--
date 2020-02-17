package cn.com.smer.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.inter.CalculationsMapper;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dtoform.ProjectsForm;

@Service
public class ProjectsService {
	@Autowired
	private ProjectsMapper projectsDao;
	@Autowired
	private CalculationsMapper calculationsDao;

	public boolean insertSelective(ProjectsForm form) {
		Projects projects = new Projects();
		BeanUtils.copyProperties(form, projects);
		//System.out.println(projects);
		int count = projectsDao.insertSelective(projects);
		BeanUtils.copyProperties(projects, form);
		if (count == 1) {
			return true;
		} else {
			return false;
		}

	}

	public List<Projects> selectBySelective(ProjectsForm form) {
		return projectsDao.selectBySelective(form);
	}

	public Projects selectByPrimaryKey(Long projectId) {
		return projectsDao.selectByPrimaryKey(projectId);
	}

	public boolean updateByPrimaryKeySelective(ProjectsForm form) {
		Projects projects = new Projects();
		BeanUtils.copyProperties(form, projects);
		//System.out.println(projects);
		int count = projectsDao.updateByPrimaryKeySelective(projects);
		BeanUtils.copyProperties(projects, form);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteByPrimaryKey(Long id) {
		Projects projects = projectsDao.selectByPrimaryKey(id);
		if (projects.getProjectType() != 1) {
			Long calculationId = projects.getCalculationId();
			if (projectsDao.deleteByPrimaryKey(id) == 1) {
				if (calculationsDao.deleteByPrimaryKey(calculationId) == 1) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else {
			int count = projectsDao.deleteByPrimaryKey(id);
			if (count == 1) {
				return true;
			} else {
				return false;
			}
		}
	}

	public List<Projects> getAllProjects() {
		return projectsDao.selectByProjectType();
	}

	public List<Projects> getAllProjectsTwo() {
		return projectsDao.getAllOkProjects();
	}

	public List<Projects> getAllOneProjects() {
		return projectsDao.getAllOneProjects();
	}

	public boolean selectByProjectName(String projectName) {
		List<Projects> projectsList = projectsDao.selectByProjectName(projectName);
		//System.out.println(projectsList);
		if (projectsList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean selectByProjectTypeIs() {
		List<Projects> projectsList = projectsDao.selectByProjectTypeIs();
		//System.out.println(projectsList);
		if (projectsList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
