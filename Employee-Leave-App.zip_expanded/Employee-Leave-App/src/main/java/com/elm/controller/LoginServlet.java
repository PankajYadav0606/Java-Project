package com.elm.controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.elm.dao.EmployeeDAO;
import com.elm.model.Employee;
import com.elm.util.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO(conn);
            Employee emp = employeeDAO.validateUser(email, password);

            // If employee not found, redirect to error page
            if (emp == null) {
                response.sendRedirect("404.html");
                return;
            }

            // Create session and store user details
            HttpSession session = request.getSession();
            session.setAttribute("username", emp.getFirstName()); // Store name in session
            session.setAttribute("role", emp.getRole()); // Store role for further access control

            // Redirect based on role
            if ("Admin".equalsIgnoreCase(emp.getRole())) {
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.sendRedirect("user_dashboard.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database connection error!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}