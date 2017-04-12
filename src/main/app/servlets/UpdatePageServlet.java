package servlets;


import jdbc.DBConnectService;
import jdbc.DBService;
import jdbc.UsersDataSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdatePageServlet extends MainPageServlet {
    private static final String INSERT_OR_EDIT = "/user.jsp";
    private static final String BASE_PAGE = "/";
    private DBService service;

    public UpdatePageServlet() {
        super();
        this.service = new DBService(DBConnectService.getMysqlConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDataSet user;
        String userId = req.getParameter("id");

        user = service.getUser(Long.parseLong(userId));

        req.setAttribute("user", user);
        RequestDispatcher view = req.getRequestDispatcher(INSERT_OR_EDIT);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDataSet user;
        String userId = req.getParameter("id");
        String name = req.getParameter("user_name");
        String secondName = req.getParameter("last_name");
        String password = req.getParameter("password");
        user = new UsersDataSet(name, secondName, password);

        user.setId(Long.parseLong(userId));
        service.updateUser(user);

        resp.sendRedirect(BASE_PAGE);
    }
}