<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Employee".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Apply for Leave</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body>
    <h2>Apply for Leave</h2>

    <form action="../leave" method="post">
        <input type="hidden" name="action" value="apply"/>
        <input type="hidden" name="employeeId" value="${user.employeeId}"/>

        <label>Leave Type:</label><br/>
        <select name="leaveType" required>
            <option value="Sick Leave">Sick Leave</option>
            <option value="Casual Leave">Casual Leave</option>
            <option value="Annual Leave">Annual Leave</option>
            <option value="Maternity Leave">Maternity Leave</option>
            <option value="Unpaid Leave">Unpaid Leave</option>
        </select><br/><br/>

        <label>Start Date:</label><br/>
        <input type="date" name="startDate" required/><br/><br/>

        <label>End Date:</label><br/>
        <input type="date" name="endDate" required/><br/><br/>

        <label>Number of Days:</label><br/>
        <input type="number" name="leaveDays" required/><br/><br/>

        <button type="submit">Apply</button>
    </form>

    <a href="leave_list.jsp">Back to Leave List</a>
</body>
</html>
