package cn.com.smer.dtoform;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ImportProjectsForm {
    private Long importProjectId;

    private Long employeeId;

    private Long projectId;

    private Long departmentId;
    private Date importDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date1;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date2;

    private Long importInfo;

    private String remark;

    public Long getImportProjectId() {
        return importProjectId;
    }

    public void setImportProjectId(Long importProjectId) {
        this.importProjectId = importProjectId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Long getImportInfo() {
        return importInfo;
    }

    public void setImportInfo(Long importInfo) {
        this.importInfo = importInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
    
}