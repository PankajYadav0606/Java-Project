<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.io.IOException" %>

<%
    // Get the session object
    HttpSession sessionObj = request.getSession(false);
    String username = (sessionObj != null) ? (String) sessionObj.getAttribute("username") : null;

    // Redirect to login if user is not logged in
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>User Dashboard - Employee Leave Management</title>

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap CSS -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Custom Styles -->
    <style>
        
    html, body {
        height: 100%;
        margin: 0;
        display: flex;
        flex-direction: column;
    }

    body {
        font-family: 'Open Sans', sans-serif;
        background-color: #f8f9fa;
    }

    main {
        flex: 1;
    }

    .container {
        max-width: 800px;
        margin-top: 100px;
        background-color: #fff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .dashboard-links {
        margin-top: 20px;
    }

    .dashboard-links a {
        display: block;
        padding: 10px;
        background: #28a745;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .dashboard-links a:hover {
        background: #218838;
    }

    .logout-btn {
        display: inline-block;
        padding: 10px 20px;
        background: red;
        color: white;
        border-radius: 5px;
        text-decoration: none;
        font-weight: bold;
    }

    .logout-btn:hover {
        background: darkred;
    }
</style>

</head>

<body>

<header id="header" class="header d-flex align-items-center fixed-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center">
        <a href="index.html" class="logo d-flex align-items-center me-auto">
            <h1 class="sitename">Employee Leave Management Application</h1>
        </a>
    </div>
</header>

<main>
    <div class="container">
        <h2>Welcome, <%= username %>! 🎉</h2>
        <p>You are now logged into your dashboard.</p>

        <div class="dashboard-links">
            <a href="apply_leave.jsp">Apply for Leave</a>
            <a href="view_leaves.jsp">View Leave Status</a>
            <a href="profile.jsp">View Profile</a>
        </div>

        <a href="index.html" class="logout-btn">Logout</a>
    </div>
</main>

<footer class="footer text-center py-3 mt-4 bg-dark text-white">
    <p>© 2025 Employee Leave Management. All Rights Reserved.</p>
</footer>

<!-- Bootstrap & JS -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>