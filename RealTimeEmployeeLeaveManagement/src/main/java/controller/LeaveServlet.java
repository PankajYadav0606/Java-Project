package controller;

import dao.LeaveDAO;
import model.LeaveRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/leave")
public class LeaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LeaveDAO dao = new LeaveDAO();

        try {
            if ("apply".equals(action)) {
                LeaveRequest leave = new LeaveRequest();
                leave.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                leave.setLeaveType(request.getParameter("leaveType"));
                leave.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
                leave.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
                leave.setLeaveDays(Integer.parseInt(request.getParameter("leaveDays")));

                dao.applyLeave(leave);
                response.sendRedirect("jsp/leave_list.jsp");
            } else if ("updateStatus".equals(action)) {
                int leaveId = Integer.parseInt(request.getParameter("leaveId"));
                String status = request.getParameter("status");
                int approvedBy = Integer.parseInt(request.getParameter("approvedBy"));

                dao.updateLeaveStatus(leaveId, status, approvedBy);
                response.sendRedirect("jsp/leave_list.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("jsp/error.jsp");
        }
    }
}
