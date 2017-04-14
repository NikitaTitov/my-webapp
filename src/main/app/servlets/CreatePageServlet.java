package servlets;


import jdbc.UsersDataSet;
import jdbc.dao.UserService;
import jdbc.dao.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreatePageServlet extends HttpServlet{
    private static final String INSERT_OR_EDIT = "/user.jsp";
    private static final String BASE_PAGE = "/";
    private UserService service;

    public CreatePageServlet() {
        super();
        this.service = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher(INSERT_OR_EDIT);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDataSet user;
        String name = req.getParameter("user_name");
        String secondName = req.getParameter("last_name");
        String password = req.getParameter("password");
        user = new UsersDataSet(name, secondName, password);

        service.insertUser(user);

        resp.sendRedirect(BASE_PAGE);
    }
}
