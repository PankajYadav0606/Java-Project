package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.LeaveRequest;
import util.DBConnection;

public class LeaveDAO {

    // Submit Leave
    public boolean applyLeave(LeaveRequest leave) {
        String sql = "INSERT INTO LeaveRequests (employee_id, leave_type, start_date, end_date, leave_days) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setDate(3, new java.sql.Date(leave.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(leave.getEndDate().getTime()));
            ps.setInt(5, leave.getLeaveDays());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // View All Leaves
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaves = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequests";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LeaveRequest leave = new LeaveRequest();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmployeeId(rs.getInt("employee_id"));
                leave.setLeaveType(rs.getString("leave_type"));
                leave.setStartDate(rs.getDate("start_date"));
                leave.setEndDate(rs.getDate("end_date"));
                leave.setLeaveDays(rs.getInt("leave_days"));
                leave.setLeaveStatus(rs.getString("leave_status"));
                leave.setAppliedOn(rs.getTimestamp("applied_on"));
                leave.setApprovedBy(rs.getInt("approved_by"));
                leave.setApprovalDate(rs.getTimestamp("approval_date"));
                leaves.add(leave);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return leaves;
    }

    // Approve/Reject Leave
    public boolean updateLeaveStatus(int leaveId, String status, int approvedBy) {
        String sql = "UPDATE LeaveRequests SET leave_status=?, approved_by=?, approval_date=NOW() WHERE leave_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, approvedBy);
            ps.setInt(3, leaveId);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
