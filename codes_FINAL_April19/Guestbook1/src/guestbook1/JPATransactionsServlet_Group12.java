package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
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

import java.lang.Character;
import javax.cache.CacheFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@SuppressWarnings("serial")
public class JPATransactionsServlet_Group12 extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req,
                      HttpServletResponse resp)
        throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        //List<String> usercache = new ArrayList<String>();
        
        String contactbookName = req.getParameter("contactbookName");
        //Key guestbookKey = KeyFactory.createKey("Contactbook", contactbookName);
        String contactName = req.getParameter("contactName");
        String phoneNumber = req.getParameter("phoneNumber");
        String emailAddress = req.getParameter("emailAddress");
        
        ///////Added for Maps, Address
        String streetName = req.getParameter("streetName");
        String city = req.getParameter("city");
        String province = req.getParameter("province");
        String postal = req.getParameter("postal");
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;
        
        ////////////////// MEMCACHE
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        List <String> usercache = (List<String>) syncCache.get(user.getUserId());
        
        
        try {
        	//List<String> usercache = new ArrayList<String>();
        	//usercache = new ArrayList<String>(); 
        	
        	em = emf.createEntityManager();
        	
        	 Contact contact = new Contact(contactName);
             contact.setPhoneNumber(phoneNumber);
             contact.setEmailAddress(emailAddress);
             contact.setUser(user);
             em.persist(contact);
        	
             /////////////MEMCACHE
            
             usercache.add(phoneNumber);
             usercache.add(emailAddress);
             usercache.add(contactName);
             
             syncCache.put(user.getUserId(), usercache);//adds the user contact to memcache
        	
        } finally {
            em.close();
            resp.sendRedirect("/BookQuery_Group12");
        }
        

    }
}