package servlets;

import jdbc.DBConnectService;
import jdbc.DBService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private static final String LIST_USER = "/listUser.jsp";
    private DBService service;

    public MainPageServlet() {
        super();
        this.service = new DBService(DBConnectService.getMysqlConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = LIST_USER;
        req.setAttribute("users", service.getAllUsers());
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

}

}
