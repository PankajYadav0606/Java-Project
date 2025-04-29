import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/questionServlet")
public class QuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("question_text");
        String subject = request.getParameter("subject");
        int maxMarks = Integer.parseInt(request.getParameter("max_marks"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_db", "root", "root");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO questions (question_text, subject, max_marks) VALUES (?, ?, ?)");
            ps.setString(1, questionText);
            ps.setString(2, subject);
            ps.setInt(3, maxMarks);
            ps.executeUpdate();
            response.sendRedirect("admin_dashboard.html");
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
