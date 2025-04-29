<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Signup - Employee Leave Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
        }
        input, select {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
        button:hover {
            background-color: #218838;
        }
        .login-link {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Employee Signup</h2>
    
    <c:if test="${not empty message}">
        <p style="color: green; text-align: center">${message}</p>
    </c:if>

    <form action="SignupServlet" method="post">
        <label>First Name</label>
        <input type="text" name="firstName" required>
        
        <label>Last Name</label>
        <input type="text" name="lastName" required>
        
        <label>Email</label>
        <input type="email" name="email" required>
        
        <label>Phone Number</label>
        <input type="text" name="phoneNumber" required>
        
        <label>Date of Birth</label>
        <input type="date" name="dob" required>
        
        <label>Gender</label>
        <select name="gender" required>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>
        
        <label>Job Title</label>
        <input type="text" name="jobTitle" required>
        
        <label>Department</label>
        <input type="text" name="department" required>
        
        <label>Employee Type</label>
        <select name="employeeType" required>
            <option value="Full-time">Full-time</option>
            <option value="Part-time">Part-time</option>
            <option value="Contract">Contract</option>
            <option value="Intern">Intern</option>
        </select>
        
        <label>Date of Joining</label>
        <input type="date" name="dateOfJoining" required>
        
        <label>Manager ID</label>
        <input type="number" name="managerId">
        
        <label>Username</label>
        <input type="text" name="username" required>
        
        <label>Password</label>
        <input type="password" name="password" required>
        
        <label>Role</label>
        <select name="role" required>
            <option value="Employee">Employee</option>
            <option value="Manager">Manager</option>
            <option value="HR">HR</option>
            <option value="Admin">Admin</option>
        </select>
        
        <button type="submit">Register</button>
    </form>

    <div class="login-link">
        <a href="login.jsp">Already have an account? Login</a>
    </div>
</div>

</body>
</html>
