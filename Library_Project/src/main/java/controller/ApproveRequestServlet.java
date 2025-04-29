// controller/ApproveRequestServlet.java

package controller;

import dao.TransactionDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ApproveRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int txnId = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action"); // approve or reject

        TransactionDAO dao = new TransactionDAO();
        String status = "approve".equals(action) ? "approved" : "overdue";
        boolean result = dao.updateTransactionStatus(txnId, status);

        if (result) {
            response.sendRedirect("PendingRequestsServlet?msg=" + status);
        } else {
            response.sendRedirect("PendingRequestsServlet?error=1");
        }
    }
}
