package controller;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashedPassword = PasswordUtil.hashPassword(password);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, hashedPassword);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role
            switch (user.getRole()) {
                case "Admin":
                    response.sendRedirect("jsp/dashboard_admin.jsp");
                    break;
                case "HR":
                    response.sendRedirect("jsp/dashboard_hr.jsp");
                    break;
                case "Manager":
                    response.sendRedirect("jsp/dashboard_manager.jsp");
                    break;
                case "Employee":
                    response.sendRedirect("jsp/dashboard_employee.jsp");
                    break;
                default:
                    response.sendRedirect("jsp/login.jsp?error=Invalid Role");
            }
        } else {
            response.sendRedirect("jsp/login.jsp?error=Invalid Username or Password");
        }
    }
}
