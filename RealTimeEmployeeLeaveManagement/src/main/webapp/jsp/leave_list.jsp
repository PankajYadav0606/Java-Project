<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="dao.LeaveDAO, model.LeaveRequest, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    model.User user = (model.User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    LeaveDAO dao = new LeaveDAO();
    List<LeaveRequest> leaveList = dao.getAllLeaveRequests();
    request.setAttribute("leaveList", leaveList);
%>

<html>
<head>
    <title>Leave Requests</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body>
    <h2>Leave Requests</h2>

    <c:if test="${user.role eq 'Employee'}">
        <a href="apply_leave.jsp">Apply for Leave</a> | 
    </c:if>
    <a href="../logout">Logout</a>

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>Leave ID</th><th>Employee ID</th><th>Leave Type</th><th>Start Date</th><th>End Date</th><th>Status</th><th>Actions</th>
        </tr>
        <c:forEach var="leave" items="${leaveList}">
            <tr>
                <td>${leave.leaveId}</td>
                <td>${leave.employeeId}</td>
                <td>${leave.leaveType}</td>
                <td>${leave.startDate}</td>
                <td>${leave.endDate}</td>
                <td>${leave.leaveStatus}</td>
                <td>
                    <c:if test="${user.role eq 'Manager' || user.role eq 'HR' || user.role eq 'Admin'}">
                        <form action="../leave" method="post" style="display:inline;">
                            <input type="hidden" name="leaveId" value="${leave.leaveId}" />
                            <input type="hidden" name="approvedBy" value="${user.employeeId}" />
                            <button type="submit" name="action" value="updateStatus" onclick="this.form.status.value='Approved'">Approve</button>
                            <button type="submit" name="action" value="updateStatus" onclick="this.form.status.value='Rejected'">Reject</button>
                            <input type="hidden" name="status" />
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
