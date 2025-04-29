<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register - Employee Leave Management</title>
    <link rel="stylesheet" href="../css/style.css">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mt-5 shadow">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Register</h3>

                    <form action="../register" method="post">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label>First Name</label>
                                <input type="text" class="form-control" name="firstName" required/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label>Last Name</label>
                                <input type="text" class="form-control" name="lastName" required/>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email" required/>
                        </div>

                        <div class="mb-3">
                            <label>Phone Number</label>
                            <input type="text" class="form-control" name="phoneNumber" required/>
                        </div>

                        <div class="mb-3">
                            <label>Date of Birth</label>
                            <input type="date" class="form-control" name="dob" required/>
                        </div>

                        <div class="mb-3">
                            <label>Gender</label>
                            <select class="form-control" name="gender" required>
                                <option value="">Select</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label>Job Title</label>
                            <input type="text" class="form-control" name="jobTitle" required/>
                        </div>

                        <div class="mb-3">
                            <label>Department</label>
                            <input type="text" class="form-control" name="department" required/>
                        </div>

                        <div class="mb-3">
                            <label>Employee Type</label>
                            <select class="form-control" name="employeeType" required>
                                <option value="">Select</option>
                                <option value="Full-time">Full-time</option>
                                <option value="Part-time">Part-time</option>
                                <option value="Contract">Contract</option>
                                <option value="Intern">Intern</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label>Date of Joining</label>
                            <input type="date" class="form-control" name="dateOfJoining" required/>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label>Username</label>
                                <input type="text" class="form-control" name="username" required/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label>Password</label>
                                <input type="password" class="form-control" name="password" required/>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label>Role</label>
                            <select class="form-control" name="role" required>
                                <option value="">Select Role</option>
                                <option value="Employee">Employee</option>
                                <option value="Manager">Manager</option>
                                <option value="HR">HR</option>
                                <option value="Admin">Admin</option>
                            </select>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger mt-3">${param.error}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
