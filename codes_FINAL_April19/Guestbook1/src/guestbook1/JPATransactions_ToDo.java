package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
public class JPATransactions_ToDo extends HttpServlet {
    public void doPost(HttpServletRequest req,
                      HttpServletResponse resp)
        throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        //String todoEntry_new = req.getParameter("todoListName");
        String content_todo = req.getParameter("content");
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            ToDo content = new ToDo(content_todo);
            //todoEntry.setToDoContent(content);
            content.setUser(user);
           
            em.persist(content);
        } finally {
            em.close();
            resp.sendRedirect("/todoEntry.jsp");
        }
        

    }
}