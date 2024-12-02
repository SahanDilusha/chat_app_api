package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.User;
import entity.UserStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonObject responseObject = new JsonObject();

        try {

            JsonObject reuestObject = (JsonObject) gson.fromJson(req.getReader(), JsonObject.class);

            if (session.createCriteria(User.class).add(Restrictions.eq("email", reuestObject.get("email").getAsString())).uniqueResult() == null) {

                UserStatus userStatus = (UserStatus) session.get(UserStatus.class, 1);

                User newUser = new User(reuestObject.get("name").getAsString(), reuestObject.get("email").getAsString(), "123456", reuestObject.get("password").getAsString(), userStatus);

                session.save(newUser);

                session.beginTransaction().commit();
                
                responseObject.addProperty("status", true);
                responseObject.add("content", gson.toJsonTree(newUser));
            } else {
                responseObject.addProperty("status", false);
            }

            resp.setContentType("application/json");
            resp.getWriter().write(gson.toJson(responseObject));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
