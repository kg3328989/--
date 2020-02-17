package cn.com.smer.dtoform;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

public class SalarySelectForm {
	
	private Long[] projectId;
	
	private Date importDate;
	
	private String[] projectName;

	private Long[] money;

	private Short[] addorsubtract;

	private Long departmentId;

	private String departmentName;

	private String employeeName;

	private Long employeeId;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date1;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date2;
	

	public Long[] getProjectId() {
		return projectId;
	}

	public void setProjectId(Long[] projectId) {
		this.projectId = projectId;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String[] getProjectName() {
		return projectName;
	}

	public void setProjectName(String[] projectName) {
		this.projectName = projectName;
	}

	public Long[] getMoney() {
		return money;
	}

	public void setMoney(Long[] money) {
		this.money = money;
	}

	public Short[] getAddorsubtract() {
		return addorsubtract;
	}

	public void setAddorsubtract(Short[] addorsubtract) {
		this.addorsubtract = addorsubtract;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	@Override
	public String toString() {
		return "SalarySelectForm [projectId=" + Arrays.toString(projectId) + ", importDate=" + importDate
				+ ", projectName=" + Arrays.toString(projectName) + ", money=" + Arrays.toString(money)
				+ ", addorsubtract=" + Arrays.toString(addorsubtract) + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", employeeName=" + employeeName + ", employeeId=" + employeeId
				+ ", date1=" + date1 + ", date2=" + date2 + "]";
	}
	
	

	
}
