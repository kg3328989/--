package cn.com.smer.dao.dto;


public class FixedSalarys {
	private Long fixedSalaryId;

	private Long projectId;

	private Long employeeId;

	private Long departmentId;

	private Long salary;
	

	public Long getFixedSalaryId() {
		return fixedSalaryId;
	}

	public void setFixedSalaryId(Long fixedSalaryId) {
		this.fixedSalaryId = fixedSalaryId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "FixedSalarys [fixedSalaryId=" + fixedSalaryId + ", projectId=" + projectId + ", employeeId="
				+ employeeId + ", departmentId=" + departmentId + ", salary=" + salary + "]";
	}

	
}