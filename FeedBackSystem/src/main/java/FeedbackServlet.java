import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("user_login.html");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/feedback_db", "root", "root");

            String[] questionIds = request.getParameterValues("question_id");
            if (questionIds == null) {
                response.getWriter().println("No questions found.");
                return;
            }

            for (String questionId : questionIds) {
                int answer = Integer.parseInt(request.getParameter("q" + questionId));

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO feedback (user_id, question_id, answer) VALUES (?, ?, ?)");
                ps.setInt(1, userId);
                ps.setInt(2, Integer.parseInt(questionId));
                ps.setInt(3, answer);
                ps.executeUpdate();
            }

            // ✅ Redirect to feedback success page
            response.sendRedirect("feedbacksuccess.html");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
