package controller;

import dao.EmployeeDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EmployeeDAO dao = new EmployeeDAO();

        try {
            if ("add".equals(action)) {
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
                emp.setPasswordHash(util.PasswordUtil.hashPassword(request.getParameter("password")));
                emp.setRole(request.getParameter("role"));

                dao.addEmployee(emp);
                response.sendRedirect("jsp/employee_list.jsp");
            } else if ("update".equals(action)) {
                Employee emp = new Employee();
                emp.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
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
                emp.setEmployeeStatus(request.getParameter("employeeStatus"));
                emp.setRole(request.getParameter("role"));

                dao.updateEmployee(emp);
                response.sendRedirect("jsp/employee_list.jsp");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("employeeId"));
                dao.deleteEmployee(id);
                response.sendRedirect("jsp/employee_list.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("jsp/error.jsp");
        }
    }
}
