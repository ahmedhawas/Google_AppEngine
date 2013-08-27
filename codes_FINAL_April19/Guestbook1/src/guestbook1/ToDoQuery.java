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
import javax.persistence.Query;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
public class ToDoQuery extends HttpServlet {
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
        throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
 
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<link type='text/css' rel='stylesheet' href='templatemo_style.css' />");
        out.println("</head>");

        out.println("<body>");
        out.println("<div id='templatemo_header'>");
        out.println("<div id='site_title'><h1><a href='#' title='Group 12'><img src='images/bean.png' alt='image 2' /></a></h1></div>");
        out.println("</div>");
        
        

        try {
            em = emf.createEntityManager();
           
            
            Query query = null;
            List<ToDo> results = null;

            // Query for all entities of a kind
            query = em.createQuery("SELECT b FROM ToDo b");
            out.println("<div id='templatemo_main'>");
            out.println("<div class='section section_with_padding' id='todo'>");
            out.println("<h2>Things to do...</h2>");
           
            out.println("<p>Every contact:</p><ul>");
            results = (List<ToDo>) query.getResultList();
            
           int num_todos = 0;
            
            for (ToDo b : results) {
            	if (b.getUser().getNickname().equalsIgnoreCase(user.getNickname()))
            	{
            		out.println("<li><i>" + b.content.getName() + "</li>");
                            
            	}
            	num_todos++;
                
            	req.setAttribute("Content", b.content.getName());
            	req.setAttribute("Num", num_todos);
            }
            out.println("</ul>");
			
        } finally {
            em.close();
           
        }
       
   }
    
}