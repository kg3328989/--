package cn.com.smer.dao.dto;

public class SalarySettlementDetailsKey {
    private Long salarySettlementId;

    private Long projectId;

    public Long getSalarySettlementId() {
        return salarySettlementId;
    }

    public void setSalarySettlementId(Long salarySettlementId) {
        this.salarySettlementId = salarySettlementId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}