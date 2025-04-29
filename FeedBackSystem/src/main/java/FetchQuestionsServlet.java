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

@WebServlet("/fetchQuestions")
public class FetchQuestionsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        StringBuilder formFields = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_db", "root", "root");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int questionId = rs.getInt("id");
                String questionText = rs.getString("question_text");

                formFields.append("<p>").append(questionText).append(" (1-5):</p>");
                formFields.append("<input type='hidden' name='question_id' value='").append(questionId).append("'>");
                formFields.append("<input type='number' name='q").append(questionId).append("' min='1' max='5' required><br>");
            }

            response.getWriter().println(formFields.toString());
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
