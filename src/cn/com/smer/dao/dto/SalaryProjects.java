package cn.com.smer.dao.dto;

public class SalaryProjects {
    private Short salaryProjectId;

    private String salaryProjectName;

    public Short getSalaryProjectId() {
        return salaryProjectId;
    }

    public void setSalaryProjectId(Short salaryProjectId) {
        this.salaryProjectId = salaryProjectId;
    }

    public String getSalaryProjectName() {
        return salaryProjectName;
    }

    public void setSalaryProjectName(String salaryProjectName) {
        this.salaryProjectName = salaryProjectName == null ? null : salaryProjectName.trim();
    }
}