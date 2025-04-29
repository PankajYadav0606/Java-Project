<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || (!"Admin".equals(user.getRole()) && !"HR".equals(user.getRole()))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Add New Employee</title>
    <link rel="stylesheet" href="../css/style.css">
    
</head>
<body>
    <h2>Add New Employee</h2>

    <form action="../employee" method="post">
        <input type="hidden" name="action" value="add"/>

        <label>First Name:</label><br/>
        <input type="text" name="firstName" required/><br/><br/>
        
        <label>Last Name:</label><br/>
        <input type="text" name="lastName" required/><br/><br/>

        <label>Email:</label><br/>
        <input type="email" name="email" required/><br/><br/>

        <label>Phone Number:</label><br/>
        <input type="text" name="phoneNumber" required/><br/><br/>

        <label>Date of Birth:</label><br/>
        <input type="date" name="dob" required/><br/><br/>

        <label>Gender:</label><br/>
        <select name="gender" required>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select><br/><br/>

        <label>Job Title:</label><br/>
        <input type="text" name="jobTitle" required/><br/><br/>

        <label>Department:</label><br/>
        <input type="text" name="department" required/><br/><br/>

        <label>Employee Type:</label><br/>
        <select name="employeeType" required>
            <option value="Full-time">Full-time</option>
            <option value="Part-time">Part-time</option>
            <option value="Contract">Contract</option>
            <option value="Intern">Intern</option>
        </select><br/><br/>

        <label>Date of Joining:</label><br/>
        <input type="date" name="dateOfJoining" required/><br/><br/>

        <label>Username:</label><br/>
        <input type="text" name="username" required/><br/><br/>

        <label>Password:</label><br/>
        <input type="password" name="password" required/><br/><br/>

        <label>Role:</label><br/>
        <select name="role" required>
            <option value="Employee">Employee</option>
            <option value="Manager">Manager</option>
            <option value="HR">HR</option>
            <option value="Admin">Admin</option>
        </select><br/><br/>

        <button type="submit">Add Employee</button>
    </form>

    <a href="employee_list.jsp">Back to Employee List</a>
</body>
</html>
