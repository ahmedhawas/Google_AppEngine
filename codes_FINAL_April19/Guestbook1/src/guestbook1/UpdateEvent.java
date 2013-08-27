package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.Query;
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Feed;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.ServiceException;

public class UpdateEvent extends HttpServlet {
	
	String userEmail = "";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
		
		  UserService userService = UserServiceFactory.getUserService();
		  User user = userService.getCurrentUser();
		  
		  String emailAddress = "";
		  String modifiedEmail = "";
		  
		  emailAddress = user.getEmail();
		  
		  String sessionToken = req.getParameter("sessionToken");
		  String eventToUpdate = req.getParameter("EventToUpdate");
		  String newEvent = req.getParameter("NewEvent");

		  
		  String startDay = req.getParameter("daydropdown3");
	      String startMonth = req.getParameter("monthdropdown3");
	      String startYear = req.getParameter("yeardropdown3");
	        
	      String endDay = req.getParameter("daydropdown4");
	      String endMonth = req.getParameter("monthdropdown4");
	      String endYear = req.getParameter("yeardropdown4");
	      
	      int startDayInt = Integer.parseInt(startDay);
	      int endDayInt = Integer.parseInt(endDay);
	      
	      TimeZone tz = TimeZone.getTimeZone("EST");
	        //Calendar cal = Calendar.getInstance();
	        Calendar cal = Calendar.getInstance(tz);
	        int currentHour = cal.get(Calendar.HOUR_OF_DAY)+1;
	        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
	        int currentMonth = cal.get(cal.MONTH)+1; //zero-based 	
	        int currentYear = cal.get(cal.YEAR);  
	      
	        startMonth = AddEvent.ConvertMonthNameToNum(startMonth);
	        endMonth = AddEvent.ConvertMonthNameToNum(endMonth);
	        
	        out.println(startMonth);
	        out.println(endMonth);
	        
	        int startMonthInt = Integer.parseInt(startMonth);
	        int startYearInt = Integer.parseInt(startYear);
	        
	        if (startDayInt!=currentDay || startMonthInt!=currentMonth || startYearInt!=currentYear)
	        {
	        	out.println("we are not in the current date");
	        	startDayInt = startDayInt-1;
	        	endDayInt = endDayInt-1;
	        }
	      
	      

	        startMonth = Integer.toString(new Integer(startMonthInt));
	        startYear = Integer.toString(new Integer(startYearInt));
	      
	        startDay = Integer.toString(new Integer(startDayInt));
	        endDay = Integer.toString(new Integer(endDayInt));
	      
	        if (0<=Integer.parseInt(startDay) && Integer.parseInt(startDay) <10)
	        	startDay = "0"+startDay;
	        if (0<=Integer.parseInt(endDay) && Integer.parseInt(endDay) <10)
	        	endDay = "0"+endDay;
	        if (0<=Integer.parseInt(startMonth) && Integer.parseInt(startMonth) <10)
	        	startMonth = "0"+startMonth;
	        

	       
	       out.println(sessionToken);
	        out.println(startDay);
	        out.println(startMonth);
	        out.println(startYear);
	        out.println(endDay);
	        out.println(endMonth);
	        out.println(endYear);
	       
		  
		  URL feedUrl = new URL("https://www.google.com/calendar/feeds/"+emailAddress+"/private/full");
		  CalendarQuery myQuery = new CalendarQuery(feedUrl);
		  

		  
		  myQuery.setMinimumStartTime(DateTime.parseDateTime(startYear+"-"+startMonth+"-"+startDay+"T00:00:00"));
		  myQuery.setMaximumStartTime(DateTime.parseDateTime(endYear+"-"+endMonth+"-"+endDay+"T23:59:59"));
		  myQuery.setFullTextQuery(eventToUpdate);
		  
		  
		  CalendarService myService = new CalendarService("google-ExampleApp-v1.0");
		  myService.setAuthSubToken(sessionToken, null);
		  
		  CalendarEventFeed resultFeed = new CalendarEventFeed();
		  // Send the request and receive the response:
		  try {
			 resultFeed = myService.query(myQuery, CalendarEventFeed.class);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  
		  
		 
		CalendarEventFeed myResultsFeed;
		try {
			
			if (resultFeed.getEntries().size() > 0) {
				CalendarEventEntry firstMatchEntry = (CalendarEventEntry)
						resultFeed.getEntries().get(0);
				firstMatchEntry.setTitle(new PlainTextConstruct(newEvent));
				URL editUrl = new URL(firstMatchEntry.getEditLink().getHref());
				CalendarEventEntry updatedEntry = (CalendarEventEntry)myService.update(editUrl, firstMatchEntry);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("Calendar.jsp?sessionToken=" + sessionToken);

	}
}