package filtres;


import jdbc.UsersDataSet;
import servlets.session.HttpUserSessions;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class AuthFilter implements Filter {
    private HttpUserSessions successSessions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        successSessions = HttpUserSessions.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String sessionId = req.getSession().getId();
        String requestURI = req.getRequestURI();

        if (successSessions.hasSession(sessionId)) {
            RequestDispatcher rd;
            UsersDataSet user = successSessions.getSessionData(sessionId);
            if (user.getUserRight().equals("admin")) {
                rd = request.getRequestDispatcher(requestURI);
                rd.forward(request, response);
                return;
            } else {
                rd = request.getRequestDispatcher("/user/helloPage");
                rd.forward(request, response);
                return;
            }
        } else {
            res.getWriter().println("Unauthorized access request");
        }
        //chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
