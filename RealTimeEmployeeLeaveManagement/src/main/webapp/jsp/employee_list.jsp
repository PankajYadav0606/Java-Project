<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="dao.EmployeeDAO, model.Employee, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    model.User user = (model.User) session.getAttribute("user");
    if (user == null || (!"Admin".equals(user.getRole()) && !"HR".equals(user.getRole()))) {
        response.sendRedirect("login.jsp");
        return;
    }

    EmployeeDAO dao = new EmployeeDAO();
    List<Employee> employeeList = dao.getAllEmployees();
    request.setAttribute("employeeList", employeeList);
%>

<html>
<head>
    <title>Manage Employees</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body>
    <h2>Employee List</h2>

    <a href="add_employee.jsp">Add New Employee</a> | 
    <a href="../logout">Logout</a>

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Department</th><th>Job Title</th><th>Status</th><th>Actions</th>
        </tr>
        <c:forEach var="emp" items="${employeeList}">
            <tr>
                <td>${emp.employeeId}</td>
                <td>${emp.firstName} ${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.phoneNumber}</td>
                <td>${emp.department}</td>
                <td>${emp.jobTitle}</td>
                <td>${emp.employeeStatus}</td>
                <td>
                    <form action="../employee" method="post" style="display:inline;">
                        <input type="hidden" name="employeeId" value="${emp.employeeId}" />
                        <button type="submit" name="action" value="delete" onclick="return confirm('Are you sure to delete?')">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
