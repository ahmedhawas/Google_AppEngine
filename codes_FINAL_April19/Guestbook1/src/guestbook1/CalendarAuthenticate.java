package guestbook1;

//import com.google.gdata.;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.URL;

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

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.ColorProperty;
import com.google.gdata.data.calendar.SelectedProperty;
import com.google.gdata.util.ServiceException;
import com.google.gdata.client.*;


public class CalendarAuthenticate extends HttpServlet {
	
	String userEmail = "";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        EntityManagerFactory emf = EMF.get();
        EntityManager em = null;
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userEmail = user.getEmail();
        
        //out.println(userEmail);
//		
//		CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
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
		
        //String nextUrl = "http://www.moe-organizer.appspot.com/CalendarInteract";
        String nextUrl = "http://www.bean-organized.appspot.com/CalendarInteract?User="+userEmail;
        //String nextUrl = "http://www.moe-organizer.appspot.com/CalendarInteract?User="+user.getNickname();
        //String nextUrl = "localhost:8888/CalendarInteract";
		//String scope = "http://www.google.com/calendar/feeds/"+userEmail;
        String scope = "http://www.google.com/calendar/feeds/";
		boolean secure = false;  // set secure=true to request secure AuthSub tokens
		boolean session = true;
		//String authSubUrl = AuthSubUtil.getRequestUrl(nextUrl, scope, secure, session);
		String authSubUrl = AuthSubUtil.getRequestUrl(nextUrl, scope, secure, session);
		   

		
		String authorizationUrl = 
		        "<p>Bean Organized needs access to your Google Calendar account to read your Calendar feed. " +
		        "To authorize Bean Organized to access your account, <a href=\"" + authSubUrl + "\">log in to your account</a>.</p>";
		
		out.println(authorizationUrl);
        
//        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
//        CalendarFeed resultFeed;
//		try {
//			resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
//			CalendarEntry calendar = resultFeed.getEntries().get(0);
//			calendar.setTitle(new PlainTextConstruct("New title"));
//			calendar.setColor(new ColorProperty("#A32929"));
//			calendar.setSelected(SelectedProperty.TRUE);
//			CalendarEntry returnedCalendar = calendar.update();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	
}