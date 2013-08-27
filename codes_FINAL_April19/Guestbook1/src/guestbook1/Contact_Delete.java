package guestbook1;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.*;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.lang.*;

public class Contact_Delete extends HttpServlet {
    //private static final Logger log = Logger.getLogger(SignGuestbookServlet.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
    	
    	resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em2 = null;
       
        int j=0;
        String[] item_split=null;	 //Item_split is a string containing todo list items, to be split up into a list
        String[] state_split=null;	//state_split is a string containing null for unchecked todo list items and the item nam for checked todo list items, to be split up into a list 
        String[] state_split2=null;
        String[] item_num;
        
        if (syncCache.contains(user.getUserId())) {
            
            syncCache.delete(user.getUserId());
           
            }
      
        
        
        //List<String> split = new ArrayList<String>();
        //List<String> checkbox_id = new ArrayList<String>();
        String checkbox_id =req.getParameter("checkbox_item_id");
        String checkbox_state = req.getParameter("check_state");
        
        item_num = req.getParameterValues("check_state");
        /*
        if(item_num!=null)
        {
        	out.println("To-do Items to be deleted are");
        	for(int i=0; i<item_num.length; i++){
        	out.println(item_num[i]+",");
        	
        	
        	}
        }
        else
        	out.println("None Selected");
       */
        
       if (checkbox_id!=null && item_num!=null){
    	   item_split = checkbox_id.split(",");
           //state_split = checkbox_state.split(",");
            
           // out.println(checkbox_state);
           // out.println("checkbox_item_id" + checkbox_id);
            
          for(int i =0; i < item_split.length ; i++){
        	  item_split[i]= item_split[i].replace('[', '\0');
          	  item_split[i]= item_split[i].replace(']', '\0');
          	 // item_split[i]= item_split[i].replace(' ', '\0');
          	  out.println(item_split[i]);
          }
         //out.println(item_num.length);
          try{
        	  if(checkbox_id!=null && item_num!=null)
        	  {  
        		  for(int i=0; i<item_num.length; i++){
        			  int x= Integer.parseInt(item_num[i]);
        			  String delete_contact= item_split[x];
        			  String delete_contact2;
        			  
        			  if(x!=item_split.length-1)
        				  delete_contact2=delete_contact.substring(1);
        			  else
        				  delete_contact2=delete_contact.substring(1,delete_contact.length()-1);
        			  
        			  
        			  
        			  em2 = emf.createEntityManager();
        			  Contact contact = em2.find(Contact.class, delete_contact2);
        			  out.println(contact);
        			  em2.getTransaction().begin();
                  	  em2.remove(contact);
                  	  em2.getTransaction().commit();                  	  
        		  }
          }
          else
          	out.println("None Selected");      
          }
          finally {
        	  em2.clear();
          }
       }
     resp.sendRedirect("/BookQuery_Group12");
   }  
}
