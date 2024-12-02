package controller;

import com.google.gson.Gson;
import entity.UserStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;


@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<UserStatus> statusList = (List<UserStatus>) session.createCriteria(UserStatus.class).list();
        
        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(statusList));

    }

 

}
