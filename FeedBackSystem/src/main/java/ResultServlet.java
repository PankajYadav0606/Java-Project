	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Feedback Results</title>");
        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head><body>");
        out.println("<h2>Feedback Results</h2>");
        out.println("<table class='result-table'>");
        out.println("<tr><th>User ID</th><th>Question</th><th>Answer</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_db", "root", "root");

            String query = "SELECT users.id, users.name, questions.question_text, feedback.answer " +
                           "FROM feedback " +
                           "JOIN users ON feedback.user_id = users.id " +
                           "JOIN questions ON feedback.question_id = questions.id";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + " (" + rs.getString("name") + ")</td>");
                out.println("<td>" + rs.getString("question_text") + "</td>");
                out.println("<td>" + rs.getInt("answer") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='admin_dashboard.html' class='back-button'>Back to Admin Dashboard</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<p class='error'>Error: " + e.getMessage() + "</p>");
        }
    }
}