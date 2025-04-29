<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body>
    <h2>Welcome Admin: ${user.username}</h2>

    <ul>
        <li><a href="employee_list.jsp">Manage Employees</a></li>
        <li><a href="leave_list.jsp">Manage Leave Requests</a></li>
        <li><a href="../logout">Logout</a></li>
    </ul>
</body>
</html>
