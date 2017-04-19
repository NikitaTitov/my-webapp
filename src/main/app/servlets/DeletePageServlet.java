package servlets;


import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeletePageServlet extends HttpServlet {
    private static final String BASE_PAGE = "/admin/listUsers";
    private UserService service;

    public DeletePageServlet() {
        super();
        this.service = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        service.deleteUser(Long.parseLong(userId));

        resp.sendRedirect(BASE_PAGE);
    }
}


