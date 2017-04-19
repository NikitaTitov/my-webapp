package servlets;


import servlets.session.HttpUserSessions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/helloPage")
public class LogoutPageServlet extends HttpServlet{
    private HttpUserSessions successSessions;

    public LogoutPageServlet() {
        super();
        successSessions = HttpUserSessions.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/successLogin.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();

        if (successSessions.hasSession(sessionId)) {
            successSessions.deleteSession(sessionId);
            resp.sendRedirect("/");
        }
    }
}
