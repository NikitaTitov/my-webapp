package servlets;

import jdbc.UsersDataSet;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;
import servlets.session.HttpUserSessions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginPageServlet extends HttpServlet {
    private UserService service;
    private HttpUserSessions successSessions;

    public LoginPageServlet() {
        super();
        service = UserServiceImpl.getInstance();
        successSessions = HttpUserSessions.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        if(successSessions.hasSession(sessionId)){
            resp.sendRedirect("/user/helloPage");
        } else {
            RequestDispatcher view = req.getRequestDispatcher("/loginStartPage.jsp");
            view.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("user_name");
        String password = req.getParameter("password");

        UsersDataSet user = service.getUser(name, password);
        if (user != null || !user.getName().isEmpty()) {
            successSessions.addSession(req.getSession().getId(), user);
            resp.sendRedirect("/admin/listUsers");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            resp.getWriter().println("<font color=red>User name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}
