package model;

import java.util.Date;

public class LeaveRequest {
    private int leaveId;
    private int employeeId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private int leaveDays;
    private String leaveStatus;
    private Date appliedOn;
    private Integer approvedBy;
    private Date approvalDate;

    // Getters and Setters
    public int getLeaveId() { return leaveId; }
    public void setLeaveId(int leaveId) { this.leaveId = leaveId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public int getLeaveDays() { return leaveDays; }
    public void setLeaveDays(int leaveDays) { this.leaveDays = leaveDays; }

    public String getLeaveStatus() { return leaveStatus; }
    public void setLeaveStatus(String leaveStatus) { this.leaveStatus = leaveStatus; }

    public Date getAppliedOn() { return appliedOn; }
    public void setAppliedOn(Date appliedOn) { this.appliedOn = appliedOn; }

    public Integer getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Integer approvedBy) { this.approvedBy = approvedBy; }

    public Date getApprovalDate() { return approvalDate; }
    public void setApprovalDate(Date approvalDate) { this.approvalDate = approvalDate; }
}
