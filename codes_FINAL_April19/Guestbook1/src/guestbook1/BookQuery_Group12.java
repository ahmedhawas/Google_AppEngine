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
import javax.persistence.Query;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.User;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceException;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@SuppressWarnings("serial")
public class BookQuery_Group12 extends HttpServlet {
	String contact_search =null;
	@SuppressWarnings("unchecked")
	//String contact_search =null;
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
        throws IOException {
        
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;
        
        contact_search =null;
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
        //////////request.getParameter("min_ratio")
        out.println("<html>");
        out.println("<head>");
        out.println("<link type='text/css' rel='stylesheet' href='templatemo_style.css' />");
        out.println("</head>");

        out.println("<body>");
        out.println("<div id='templatemo_header'>");
        out.println("<div id='site_title'><h1><a href='#' title='Group 12'><img src='images/bean.png' alt='image 2' /></a></h1></div>");
        out.println("</div>");
        
        out.println("<div id='templatemo_main'>");
        out.println("<div class='section section_with_padding' id='todo'>");
        
        out.println("<h2><font color='pink'>My Contact Book</font></h2>");
        
        
        
        
        /*
        out.println("<div id='contact_form'>");
        out.println("<form>");
        out.println("Enter Contact Name to Search for: <input type='text' name='contact_search' style='border:2px solid #6495ED'> <br>"); 
     	//out.println("<input type='submit' method ='get' formaction='BookQuery_Group12' value='Search'>");
     	out.println("<input type='submit' class='submit_btn float_l' method ='get' formaction='BookQuery_Group12' value='Search'>");
        out.println("</form>");
     	out.println("</form>");
     	out.println("<form action='/BookQuery_Group12' method='get'>");
   		out.println("<input type='submit' class='submit_btn float_l' name='submit' value='Display Full List of Contacts' />");
		out.println("</form>");
     	out.println("</div>");
     	*/
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th><font color='pink'>Contact Name</font></th>");
        out.println("<th><font color='pink'>Phone</font></th>");
        out.println("<th><font color='pink'>email</font></th>");
        out.println("<th><font color='pink'>Delete Contact</font></th>");
        out.println("</tr>");
        
        //////////////////////////
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        List<String> usercache = (List<String>) syncCache.get(user.getUserId()); 
        
        Query query = null;
        List<Contact> results = null;

        em = emf.createEntityManager();
        // Query for all entities of a kind
        
        //out.println("<p>Every contact:</p><ul>");
        //results = (List<Contact>) query.getResultList();
        usercache = new ArrayList<String>();
        
        try {
        	List<String> checkbox_item_id = new ArrayList<String>();
            em = emf.createEntityManager();
            
            
            
            if (syncCache.contains(user.getUserId()) && req.getParameter("contact_search")==null) {
            	int item_num=0;
            	usercache = (List<String>) syncCache.get(user.getUserId());
            	boolean found=false;
            	
            	
            	for (int i = 0; i < usercache.size(); i++) {
            		            		
            		String contact_content = (usercache.get(i+2));
            		
            		//if (req.getParameter("contact_search")!=null && usercache.get(i+2).toLowerCase().contains(req.getParameter("contact_search").toLowerCase()))
            	
            		//{
            		
            			found=true;
            			checkbox_item_id.add(contact_content);
                		out.println("<tr>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+2)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+1)));
                        out.println("</td>");
                        
                        
                        out.println("<td align='center'>");
                        out.println("<form action='/Contact_Delete' method='post'>");
                        
                        out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num +"' id='check_state'></span>"); 
                        
                     	out.println("<br>");
                     	//out.println("</font>");
                     	//out.println("</div>");
                    	
                        out.println("</td>");
                        out.println("</tr>");
                        //out.println("</table>");
                        
                        i=i+2;///increment to next user
                        
                        item_num = item_num +1;
            		//}
            		
            	//HEREEEE
            	//}
            	 //out.println("</table>");
            	// out.println("<div id='contact_form'>");	
                // out.println("<input type='submit' class='submit_btn float_l' name='submit' id='submit' value='Delete Selected Contacts' onclick='return confirm('Are you sure you want to delete these items?')'/><br>");
                // out.println("<input type='hidden' name='checkbox_item_id' value='"+checkbox_item_id+"'/>");
                // out.println("</div>");
                // out.println("</form>");
            		
                        /*else if(req.getParameter("contact_search")==null)
            		{
            			found=true;
            			checkbox_item_id.add(contact_content);
                		out.println("<tr>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+2)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+1)));
                        out.println("</td>");
                        
                        
                        out.println("<td align='center'>");
                        out.println("<form action='/Contact_Delete' method='post'>");
                        out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num +"' id='check_state'></span>"); 
                     
                     	out.println("<br>");
                     	//out.println("</font>");
                     	//out.println("</div>");
                    	
                        out.println("</td>");
                        out.println("</tr>");
                        //out.println("</table>");
                        
                        i=i+2;///increment to next user
                        
                        item_num = item_num +1;
            		}
            	else if (found==false && req.getParameter("contact_search")!=null)
            	{
            		//if(item_num>=(usercache.size()-1)){
            		//out.println("<h2>Search Does not Match Any Username! </h2>");
            		//}//for (int i = 0; i < usercache.size(); i++) {
            		//	String contact_content = (usercache.get(i+2));
            			checkbox_item_id.add(contact_content);
                		out.println("<tr>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+2)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i)));
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println((usercache.get(i+1)));
                        out.println("</td>");
                        
                        
                        out.println("<td align='center'>");
                        out.println("<form action='/Contact_Delete' method='post'>");
                        out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num +"' id='check_state'></span>"); 
                     
                     	out.println("<br>");
                     	//out.println("</font>");
                     	//out.println("</div>");
                    	
                        out.println("</td>");
                        out.println("</tr>");
                        //out.println("</table>");
                        
                        i=i+2;///increment to next user
                        
                        item_num = item_num +1;
            			
            		//}
                	
            	}
            	
            ///// HERE ADDED BELOW/////
            	}
            	
            	out.println("</table>");
                
                if(found==false){
                	out.println("<h2><font color='yellow'>Search Does not Match Any Username!</font></h2>");
                	
                }*/
                       
            	}
            	
           }
            else if(req.getParameter("contact_search")!=null){
            
            	
            //Query query = null;
            //List<Contact> results = null;

            // Query for all entities of a kind
            query = em.createQuery("SELECT b FROM Contact b");
            //out.println("<p>Every contact:</p><ul>");
            results = (List<Contact>) query.getResultList();
            usercache = new ArrayList<String>();
            
            int item_num=0;
            boolean found=false;
            
            for (Contact b : results) {
            	String contact_content = b.contactName.getName();
            	if (b.getUser().getNickname().equalsIgnoreCase(user.getNickname()))
            	{
            		 usercache.add(b.getPhoneNumber());
                     usercache.add(b.getEmailAddress());
                     usercache.add(b.contactName.getName());
                     syncCache.put(user.getUserId(), usercache);
            		
            		if (req.getParameter("contact_search")!=null && b.contactName.getName().toLowerCase().contains(req.getParameter("contact_search").toLowerCase()))
            		{
            			found=true;
            			checkbox_item_id.add(contact_content);
                		out.println("<tr>");
                        out.println("<td align='center'>");
                        out.println(b.contactName.getName());
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println(b.getPhoneNumber());
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println(b.getEmailAddress());
                        out.println("</td>");
                       
                        out.println("<td align='center'>");
                        out.println("<form action='/Contact_Delete' method='post'>");
                        
                        out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num+"' id='check_state'></span>"); 
                     	
                     	//out.println("<span class='tab'> ${fn:escapeXml(greeting_content)} </span>");
                     	out.println("<br>");
                     	//out.println("</font>");
                     	//out.println("</div>");
                    	
                        out.println("</td>");
                        out.println("</tr>");
                        
                        item_num = item_num + 1;
                        
                        //b.getUser().getNickname()
            		}
            		
            	}
                //out.println("<li><i>" + b.getContactID().toString() + "</i>, " +
            ///PUT TAG BELOW BACK IN	
            ////}
            	/*else if(req.getParameter("contact_search")==null)
        		{
        			found=true;
        			checkbox_item_id.add(contact_content);
            		out.println("<tr>");
                    out.println("<td align='center'>");
                    out.println(b.contactName.getName());
                    out.println("</td>");
                    out.println("<td align='center'>");
                    out.println(b.getPhoneNumber());
                    out.println("</td>");
                    out.println("<td align='center'>");
                    out.println(b.getEmailAddress());
                    out.println("</td>");
                   
                    out.println("<td align='center'>");
                    out.println("<form action='/Contact_Delete' method='post'>");
                    //out.println("<div class='box home_box3 color5'>");
                 	//out.println("<font size='4' color='white'>");
                 	out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num+"' id='check_state'></span>"); 
                 	//out.println("<span class='tab'> ${fn:escapeXml(greeting_content)} </span>");
                 	out.println("<br>");
                 	//out.println("</font>");
                 	//out.println("</div>");
                	
                    out.println("</td>");
                    out.println("</tr>");
                    
                    item_num = item_num + 1;
                    
                    //b.getUser().getNickname()
        		}*/	
            }
            if(found==false){
            	out.println("<h2><font color='yellow'>Search Does not Match Any Username!</font></h2>");
            	
            }
         	
            //out.println("</ul>");
            //out.println("</table>");
            
            //out.println("<div id='contact_form'>");	
            //out.println("<input type='submit' class='submit_btn float_l' name='submit' id='submit' value='Delete Selected Contacts' onclick='return confirm('Are you sure you want to delete these items?')'/><br>");
            //out.println("<input type='hidden' name='checkbox_item_id' value='"+checkbox_item_id+"'/>");
            //out.println("</div>");
            //out.println("</form>");
			
            }  
            
            else
            {
            	//if(item_num>=(results.size()-1)){
            	//out.println("<h2>Search Does not Match Any Username! </h2>");
            	//}//for (Contact b : results) {
                	//contact_content = b.contactName.getName();
            	query = em.createQuery("SELECT b FROM Contact b");
                //out.println("<p>Every contact:</p><ul>");
                results = (List<Contact>) query.getResultList();
                usercache = new ArrayList<String>();
                
                int item_num=0;
                //boolean found=false;
                
                for (Contact b : results) {
                	String contact_content = b.contactName.getName();
                	
                	if (b.getUser().getNickname().equalsIgnoreCase(user.getNickname()))
                	{
                		checkbox_item_id.add(contact_content);
                		out.println("<tr>");
                        out.println("<td align='center'>");
                        out.println(b.contactName.getName());
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println(b.getPhoneNumber());
                        out.println("</td>");
                        out.println("<td align='center'>");
                        out.println(b.getEmailAddress());
                        out.println("</td>");
                       
                        out.println("<td align='center'>");
                        out.println("<form action='/Contact_Delete' method='post'>");
                       
                     	out.println("<span class='tab'><input type='checkbox' name='check_state' value='"+item_num+"' id='check_state'></span>"); 
                     	
                     	out.println("<br>");
                     	//out.println("</font>");
                     	//out.println("</div>");
                    	
                        out.println("</td>");
                        out.println("</tr>");
                        
                        item_num = item_num + 1;
                	}
                	
            	}
            }
            
           
            out.println("</table>");
            
            out.println("<div id='contact_form'>");
            out.println("<input type='submit' class='submit_btn float_l' name='submit' id='submit' value='Delete Selected Contacts' onclick='return confirm('Are you sure you want to delete these items?')'/><br>");
            out.println("<input type='hidden' name='checkbox_item_id' value='"+checkbox_item_id+"'/>");
            out.println("</div>");
            out.println("</form>");
            
            
        }
        catch (Exception e) {
        	e.printStackTrace();
        	}
        finally {
            em.close();
           
        }
        
        //out.println("<div class='clear h20'></div>");
        //out.println("<a href='contactEntry.jsp' class='submit_btn float_l' home_btn'>Add a Contact</a>"); 
        //out.println("<a href='home.html' class='slider_nav_btn previous_btn'>Home</a>");
        out.println("<form action='contactEntry.jsp' method='post'>");
        out.println("<input type='submit' class='submit_btn float_l' name='submit' value='Add a Contact'/>");
        out.println("</form>");
        
        
        out.println("<div class='half right'>");
        out.println("<a href='home.jsp' class='slider_nav_btn home_btn'>home</a> ");
        out.println("<a href='home.jsp' class='slider_nav_btn previous_btn'>Previous</a>");
        out.println("</div>");
        
        
        //out.println("<div id='search_button'");
        out.println("<div id='contact_form'>");
        out.println("<form>");
        out.println("Contact Name Search: <input type='text' name='contact_search' style='border:2px solid #6495ED'> <br>"); 
     	//out.println("<input type='submit' method ='get' formaction='BookQuery_Group12' value='Search'>");
     	out.println("<input type='submit' class='submit_btn float_l' method ='get' formaction='BookQuery_Group12' value='Search'>");
        out.println("</form>");
     	out.println("</form>");
     	out.println("<form action='/BookQuery_Group12' method='get'>");
   		out.println("<input type='submit' class='submit_btn float_l' name='submit' value='Display Full List of Contacts' />");
		out.println("</form>");
     	out.println("</div>");
        //out.println("</div>");
        
        out.println("</div>");
        out.println("</div>");
        out.println("<p><a href='"+userService.createLogoutURL(req.getRequestURI())+"'><img src='images/logout.png' alt='Logout' id='topright' /></a></p>");
        
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
//        fmt.setTimeZone(new SimpleTimeZone(0, ""));
//        out.println("<p>The time is: " + fmt.format(new Date()) + "</p>");
        
        } else {
    		resp.sendRedirect("/loginRedirect.jsp");
    	}
        
        out.println("</body>");
        out.println("</html>");
   }
    
}
