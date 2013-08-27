package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ContactDeletion extends HttpServlet {
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
        throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
 
        EntityManagerFactory emf = EMF.get();
        EntityManager em2 = null;
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        try {
            em2 = emf.createEntityManager();
            
            
            
            Contact contact = em2.find(Contact.class, "hello");
            
            out.println(contact.contactName);
            
            
            if (contact == null)
            {
            	resp.sendRedirect("BookQuery_Group12");
            }else {
                out.println(contact.getEmailAddress());
                em2.getTransaction().begin();
            	em2.remove(contact);
            	em2.getTransaction().commit();
            	resp.sendRedirect("BookQuery_Group12");
            }
        
    						}
        finally {
        	em2.clear();
        }
    }
    
}