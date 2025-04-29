<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login - Employee Leave Management</title>
    <link rel="stylesheet" href="../css/style.css">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5 shadow">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Login</h3>
                    
                    <form action="../login" method="post">
                        <div class="mb-3">
                            <label>Username</label>
                            <input type="text" class="form-control" name="username" required/>
                        </div>
                        
                        <div class="mb-3">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" required/>
                        </div>
                        
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>

                    <div class="mt-3 text-center">
                        <p>Don't have an account? <a href="register.jsp">Register Here</a></p>
                    </div>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger mt-3">${param.error}</div>
                    </c:if>

                    <c:if test="${param.msg != null}">
                        <div class="alert alert-success mt-3">${param.msg}</div>
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
