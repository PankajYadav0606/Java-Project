package com.elm.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elm.dao.EmployeeDAO;
import com.elm.model.Employee;
import com.elm.util.DBConnection;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extracting user input from form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String jobTitle = request.getParameter("jobTitle");
        String department = request.getParameter("department");
        String employeeType = request.getParameter("employeeType");
        String dateOfJoining = request.getParameter("dateOfJoining");
        String managerIdParam = request.getParameter("managerId");
        int managerId = (managerIdParam != null && !managerIdParam.isEmpty()) ? Integer.parseInt(managerIdParam) : 0;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create Employee object
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, dob, gender, jobTitle, department,
                employeeType, dateOfJoining, managerId, username, password, role);

        try (Connection conn = DBConnection.getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO(conn);
            boolean isInserted = employeeDAO.registerEmployee(employee);
            System.out.println("Inserted: "+isInserted);
            if (isInserted) {
                request.setAttribute("message", "Registration successful! Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Registration failed. Email or Username may already exist.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Database error! Please try again later.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
