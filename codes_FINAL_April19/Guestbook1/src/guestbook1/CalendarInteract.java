package guestbook1;

//import com.google.gdata.;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.client.http.AuthSubUtil;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.ColorProperty;
import com.google.gdata.data.calendar.SelectedProperty;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import com.google.gdata.client.*;


public class CalendarInteract extends HttpServlet {
	
	String userEmail = "";
	
	private static final Logger log = Logger.getLogger(CalendarInteract.class.getName());
	
	boolean updateToken = true; 
	
	String singleUseToken="";
	String sessionToken="";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;
        
        
//        if(!updateToken)
//        	singleUseToken = req.getParameter("token");
//        else
//        {
//        	 singleUseToken = AuthSubUtil.getTokenFromReply(req.getQueryString());
//        	
//        }
        
        if(updateToken)
        {
        	try {
    			String singleUseToken = AuthSubUtil.getTokenFromReply(req.getQueryString());
    			
    			sessionToken = AuthSubUtil.exchangeForSessionToken(singleUseToken, null);
    		} catch (AuthenticationException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		} catch (GeneralSecurityException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
        	//singleUseToken = AuthSubUtil.getTokenFromReply(req.getQueryString());
        }
        
        
        //try{
        
        UserService userService = UserServiceFactory.getUserService();
        boolean loggedIn = userService.isUserLoggedIn();
        
        out.println(loggedIn);
        
        if(!loggedIn)
        {
        	updateToken = false;
        	//resp.sendRedirect("loginRedirect2.jsp?token="+singleUseToken);
        	resp.sendRedirect("loginRedirect2.jsp");
        }
        
        out.println(updateToken);
        out.println(singleUseToken);
        out.println("aiwa");
        out.println(sessionToken);
        
        
        User user = userService.getCurrentUser();        	
//        if (user == null)
//        	log.log(Level.SEVERE, "User is null");
//        
        
//        String userEmail = user.getEmail();
//        
//        
//        //String userEmail = req.getParameter("User");
//        
////        out.println(user);
//        out.println(userEmail);
//        out.println(loggedIn);
////        } catch(NullPointerException e)
////        {
////        	out.println("the user is null!");
////        }
////        
//        
//
//        
//		
//		String sessionToken = "";
//
//		try {
//			//String singleUseToken = AuthSubUtil.getTokenFromReply(req.getQueryString());
//			
//			sessionToken = AuthSubUtil.exchangeForSessionToken(singleUseToken, null);
//		} catch (AuthenticationException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		} catch (GeneralSecurityException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		//sessionToken = singleUseToken;
//		
//		
//		
		CalendarService myService = new CalendarService("google-ExampleApp-v1.0");
		myService.setAuthSubToken(sessionToken, null);
		
		try {
			Map<String, String> tokenInfo = AuthSubUtil.getTokenInfo(sessionToken, null);
		} catch (AuthenticationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		//resp.sendRedirect("Calendar.jsp?User="+userEmail+"&"+"sessionToken=" + sessionToken);
		resp.sendRedirect("Calendar.jsp?sessionToken=" + sessionToken);
		
		
		
		//resp.sendRedirect("Calendar.jsp?User="+userEmail+"&"+ sessionToken+"=sessionToken");

        
		//CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
//		try {
//			myService.setUserCredentials("jo@gmail.com", "mypassword");
//		} catch (AuthenticationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// Send the request and print the response
//		URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
//		CalendarFeed resultFeed;
//		try {
//			resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
//			out.println("Your calendars:");
//			out.println();
//			for (int i = 0; i<resultFeed.getEntries().size(); i++) {
//				CalendarEntry entry = resultFeed.getEntries().get(i);
//				out.println("\t" + entry.getTitle().getPlainText());
//			}
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		
        
//        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
//        CalendarFeed resultFeed;
//		try {
//			resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
//			CalendarEntry calendar = resultFeed.getEntries().get(0);
//			calendar.setTitle(new PlainTextConstruct(userEmail));
//			calendar.setColor(new ColorProperty("#A32929"));
//			calendar.setSelected(SelectedProperty.TRUE);
//			CalendarEntry returnedCalendar = calendar.update();
//			
//			
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		
//		URL postUrl = new URL("https://www.google.com/calendar/feeds/" + userEmail +"/private/full");
//				  //new URL("https://www.google.com/calendar/feeds/jo@gmail.com/private/full");
//				CalendarEventEntry myEntry = new CalendarEventEntry();
//
//				myEntry.setTitle(new PlainTextConstruct("Tennis with Beth"));
//				myEntry.setContent(new PlainTextConstruct("Meet for a quick lesson."));
//
//				DateTime startTime = DateTime.parseDateTime("2013-04-12T15:00:00-05:00");
//				DateTime endTime = DateTime.parseDateTime("2013-04-12T17:00:00-05:00");
//				When eventTimes = new When();
//				eventTimes.setStartTime(startTime);
//				eventTimes.setEndTime(endTime);
//				myEntry.addTime(eventTimes);
//
//				// Send the request and receive the response:
//				try {
//					CalendarEventEntry insertedEntry = myService.insert(postUrl, myEntry);
//				} catch (ServiceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		
				//resp.sendRedirect("Calendar" + sessionToken);

	}
	
	
}