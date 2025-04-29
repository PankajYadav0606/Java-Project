package controller;

import dao.EmployeeDAO;
import model.Employee;
import util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Employee emp = new Employee();

            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setEmail(request.getParameter("email"));
            emp.setPhoneNumber(request.getParameter("phoneNumber"));
            emp.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob")));
            emp.setGender(request.getParameter("gender"));
            emp.setJobTitle(request.getParameter("jobTitle"));
            emp.setDepartment(request.getParameter("department"));
            emp.setEmployeeType(request.getParameter("employeeType"));
            emp.setDateOfJoining(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateOfJoining")));
            emp.setUsername(request.getParameter("username"));
            emp.setPasswordHash(PasswordUtil.hashPassword(request.getParameter("password")));

            // ✅ Important: Save selected Role also
            emp.setRole(request.getParameter("role")); 

            EmployeeDAO employeeDAO = new EmployeeDAO();
            boolean success = employeeDAO.addEmployee(emp);

            if (success) {
                response.sendRedirect("jsp/login.jsp?msg=Registration successful. Please login.");
            } else {
                response.sendRedirect("jsp/register.jsp?error=Registration Failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("jsp/register.jsp?error=Error Occurred");
        }
    }
}
