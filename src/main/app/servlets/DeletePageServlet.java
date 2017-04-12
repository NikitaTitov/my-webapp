package servlets;


import jdbc.DBConnectService;
import jdbc.DBService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeletePageServlet extends HttpServlet {
    private static final String BASE_PAGE = "/";
    private DBService service;

    public DeletePageServlet() {
        super();
        this.service = new DBService(DBConnectService.getMysqlConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        service.deleteUser(Long.parseLong(userId));

        req.setAttribute("users", service.getAllUsers());
        RequestDispatcher view = req.getRequestDispatcher(BASE_PAGE);
        view.forward(req, resp);
    }
}


