package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class AddEvent extends HttpServlet {
		
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
	        
	        String sessionToken = req.getParameter("sessionToken");
	        
	        String startDay = req.getParameter("daydropdown1");
	        String startMonth = req.getParameter("monthdropdown1");
	        String startYear = req.getParameter("yeardropdown1");
	        
	        String endDay = req.getParameter("daydropdown2");
	        String endMonth = req.getParameter("monthdropdown2");
	        String endYear = req.getParameter("yeardropdown2");
	        
	        String startHour = req.getParameter("hourdropdown1");
	        String startMinute = req.getParameter("minutedropdown1");
	        
	        String endHour = req.getParameter("hourdropdown2");
	        String endMinute = req.getParameter("minutedropdown2");
	        
	        String EventTitle = req.getParameter("EventTitle");
	        String EventDetails = req.getParameter("EventDetails");
	        
	        int startDayInt = Integer.parseInt(startDay);
	        int endDayInt = Integer.parseInt(endDay);
	        int startHourInt = Integer.parseInt(startHour);
	        int endHourInt = Integer.parseInt(endHour);
	        int startMinuteInt = Integer.parseInt(startMinute);
	        int endMinuteInt = Integer.parseInt(endMinute);
	        
	        out.println(startHourInt);
	        out.println(endHourInt);
	        
//	        if (startHourInt == 1)
//	        	startHourInt = startHourInt+1;
//	        if (endHourInt == 1)
//	        	endHourInt = endHourInt+1;
	        
//	        out.println(startHourInt);
//	        out.println(endHourInt);
	 	   
	        TimeZone tz = TimeZone.getTimeZone("EST");
	        //Calendar cal = Calendar.getInstance();
	        Calendar cal = Calendar.getInstance(tz);
	        int currentHour = cal.get(Calendar.HOUR_OF_DAY)+1;
	        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
	        int currentMonth = cal.get(cal.MONTH)+1; //zero-based 	
	        int currentYear = cal.get(cal.YEAR);  
	        
	        
	        startMonth = ConvertMonthNameToNum(startMonth);
	        endMonth = ConvertMonthNameToNum(endMonth);
	        
	        int startMonthInt = Integer.parseInt(startMonth);
	        int startYearInt = Integer.parseInt(startYear);
	        
	        
	       

//	        out.println(currentHour);
//	        out.println(currentDay);
//	        out.println(currentMonth);
//	        out.println(currentYear);
//	        
//	        out.println(startDayInt);
//	        out.println(startMonthInt);
//	        out.println(startYearInt);
	        	        
	        
	        if (startDayInt!=currentDay || startMonthInt!=currentMonth || startYearInt!=currentYear)
	        {
	        	out.println("we are not in the current date");
	        	startDayInt = startDayInt-1;
	        	endDayInt = endDayInt-1;
	        }
	        
	
	        //startDayInt = startDayInt-1;
	        
	        startHourInt = startHourInt-1;
	        endHourInt = endHourInt-1;
	        startMinuteInt = startMinuteInt-1;
	        endMinuteInt = endMinuteInt-1;
	        
	        out.println(startHourInt);
	        out.println(endHourInt);
	        
	        
	        if (startHourInt==-1)
	        	startHourInt=0;
	        if (endHourInt==-1)
	        	endHourInt=0;
//	        
	        
	        
	        
//	        if ((startDayInt+1)==currentDay && startMonthInt==currentMonth && startYearInt==currentYear)
//	        {
//	        	//out.println("we are in the current date");
//	        	startDayInt = startDayInt+1;
//	        }
	        
//	        out.println(startDayInt);
//	        out.println(startMonthInt);
//	        out.println(startYearInt);
	        
	        
	        
	        startDay = Integer.toString(new Integer(startDayInt));
	        endDay = Integer.toString(new Integer(endDayInt));
	        startHour = Integer.toString(new Integer(startHourInt));
	        endHour = Integer.toString(new Integer(endHourInt));
	        startMinute = Integer.toString(new Integer(startMinuteInt));
	        endMinute = Integer.toString(new Integer(endMinuteInt));
	        
	        startMonth = Integer.toString(new Integer(startMonthInt));
	        startYear = Integer.toString(new Integer(startYearInt));
	        
//	        out.println(sessionToken);
//	        out.println(startDay);
//	        out.println(startMonth);
//	        out.println(startYear);
//	        out.println(endDay);
//	        out.println(endMonth);
//	        out.println(endYear);
//	        out.println(startHour);
//	        out.println(startMinute);
//	        out.println(endHour);
//	        out.println(endMinute);
//	        out.println(EventTitle);
//	        out.println(EventDetails);
//	        
	        if (0<=Integer.parseInt(startDay) && Integer.parseInt(startDay) <10)
	        	startDay = "0"+startDay;
	        if (0<=Integer.parseInt(endDay) && Integer.parseInt(endDay) <10)
	        	endDay = "0"+endDay;
	        if (0<=Integer.parseInt(startHour) && Integer.parseInt(startHour) <10)
	        	startHour = "0"+startHour;
	        if (0<=Integer.parseInt(endHour) && Integer.parseInt(endHour) <10)
	        	endHour = "0"+endHour;
	        if (0<=Integer.parseInt(startMinute) && Integer.parseInt(startMinute) <10)
	        	startMinute = "0"+startMinute;
	        if (0<=Integer.parseInt(endMinute) && Integer.parseInt(endMinute) <10)
	        	endMinute = "0"+endMinute;
	        if (0<=Integer.parseInt(startMonth) && Integer.parseInt(startMonth) <10)
	        	startMonth = "0"+startMonth;
	        
//	        startMonth = ConvertMonthNameToNum(startMonth);
//	        endMonth = ConvertMonthNameToNum(endMonth);
//	        
//	        out.println(sessionToken);
//	        out.println(startDay);
//	        out.println(startMonth);
//	        out.println(startYear);
//	        out.println(endDay);
//	        out.println(endMonth);
//	        out.println(endYear);
	        out.println(startHour);
//	        out.println(startMinute);
	        out.println(endHour);
//	        out.println(endMinute);
//	        out.println(EventTitle);
//	        out.println(EventDetails);
	        
	        CalendarService myService = new CalendarService("google-ExampleApp-v1.0");
			myService.setAuthSubToken(sessionToken, null);
	    
			URL postUrl = new URL("https://www.google.com/calendar/feeds/" + userEmail +"/private/full");
			  //new URL("https://www.google.com/calendar/feeds/jo@gmail.com/private/full");
			CalendarEventEntry myEntry = new CalendarEventEntry();

			myEntry.setTitle(new PlainTextConstruct(EventTitle));
			myEntry.setContent(new PlainTextConstruct(EventDetails));

			//DateTime startTime = DateTime.parseDateTime("2013-04-12T15:00:00-05:00");
			DateTime startTime = DateTime.parseDateTime(startYear+"-"+startMonth+"-"+startDay+"T"+startHour+":"+startMinute+":00-04:00");
			//DateTime endTime = DateTime.parseDateTime("2013-04-12T17:00:00-05:00");
			DateTime endTime = DateTime.parseDateTime(endYear+"-"+endMonth+"-"+endDay+"T"+endHour+":"+endMinute+":00-04:00");

			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEntry.addTime(eventTimes);

			// Send the request and receive the response:
			try {
				CalendarEventEntry insertedEntry = myService.insert(postUrl, myEntry);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect("Calendar.jsp?sessionToken=" + sessionToken);
		}
		
		static String ConvertMonthNameToNum(String month)
		{
			String numericMonth="";
			if (month.equals("Jan")) 
                numericMonth = "01";
			else if (month.equals("Feb")) 
                numericMonth = "02";
			else if (month.equals("Mar")) 
                numericMonth = "03";
			else if (month.equals("Apr")) 
                numericMonth = "04";
			else if (month.equals("May")) 
                numericMonth = "05";
			else if (month.equals("Jun")) 
                numericMonth = "06";
			else if (month.equals("Jul")) 
                numericMonth = "07";
			else if (month.equals("Aug")) 
                numericMonth = "08";
			else if (month.equals("Sept")) 
                numericMonth = "09";
			else if (month.equals("Oct")) 
                numericMonth = "10";
			else if (month.equals("Nov")) 
                numericMonth = "11";
			else if (month.equals("Dec")) 
                numericMonth = "12";
//            case "Feb":
//            	numericMonth = "02";
//                break;
//            case "Mar":
//            	numericMonth = "03";
//                break;
//            case "Apr":
//            	numericMonth = "04";
//                break;
//            case "May":
//            	numericMonth = "05";
//                break;
//            case "Jun":
//            	numericMonth = "06";
//                break;
//            case "Jul":
//            	numericMonth = "07";
//                break;
//            case "Aug":
//            	numericMonth = "08";
//                break;
//            case "Sept":
//            	numericMonth = "09";
//                break;
//            case "Oct":
//            	numericMonth = "10";
//                break;
//            case "Nov":
//            	numericMonth = "11";
//                break;
//            case "Dec":
//            	numericMonth = "12";
//                break;
//            default: 
//            	numericMonth = "01";
//                break;
			
			return numericMonth;
			
		}
}