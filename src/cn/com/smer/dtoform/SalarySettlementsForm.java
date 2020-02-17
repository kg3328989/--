package cn.com.smer.dtoform;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SalarySettlementsForm {
	private Long salarySettlementId;

	private Long employeeId;

	private String employeeName;
	
	private Long departmentId;
	
	private String departmentName;

	private Long state;
	
	private String stateName;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date importDate;

	private Date payDate;

	private Long trueSalary;
	
	
	
	public String getStateName() {
		return stateName;
	}



	public void setStateName(String stateName) {
		this.stateName = stateName;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public Long getSalarySettlementId() {
		return salarySettlementId;
	}



	public void setSalarySettlementId(Long salarySettlementId) {
		this.salarySettlementId = salarySettlementId;
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



	public Long getState() {
		return state;
	}



	public void setState(Long state) {
		this.state = state;
	}



	public Date getImportDate() {
		return importDate;
	}



	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}



	public Date getPayDate() {
		return payDate;
	}



	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}



	public Long getTrueSalary() {
		return trueSalary;
	}



	public void setTrueSalary(Long trueSalary) {
		this.trueSalary = trueSalary;
	}



	@Override
	public String toString() {
		return "SalarySettlementsForm [salarySettlementId=" + salarySettlementId + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", state=" + state + ", stateName=" + stateName + ", importDate=" + importDate
				+ ", payDate=" + payDate + ", trueSalary=" + trueSalary + "]";
	}

	

}
