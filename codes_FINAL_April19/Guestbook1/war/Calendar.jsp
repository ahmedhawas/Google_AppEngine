<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>


<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>


<html>
  <head>
    <link type="text/css" rel="stylesheet" href="templatemo_style.css" />
  </head>

  <body>
	<div id="templatemo_header">
    <div id="site_title"><h1><a href="#" title="Group 12"><img src="images/bean.png" alt="image 2" /></a></h1></div>
	</div>



<div id="todo"> 
	   
            
  <script type="text/javascript">

/***********************************************
* Drop Down Date select script- by JavaScriptKit.com
* This notice MUST stay intact for use
* Visit JavaScript Kit at http://www.javascriptkit.com/ for this script and more
***********************************************/

var monthtext=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];

function populatedropdownDate(dayfield, monthfield, yearfield){
var today=new Date()
var dayfield=document.getElementById(dayfield)
var monthfield=document.getElementById(monthfield)
var yearfield=document.getElementById(yearfield)
for (var i=1; i<32; i++)
dayfield.options[i]=new Option(i, i+1)
dayfield.options[today.getDate()]=new Option(today.getDate(), today.getDate(), true, true) //select today's day
for (var m=0; m<12; m++)
monthfield.options[m]=new Option(monthtext[m], monthtext[m])
monthfield.options[today.getMonth()]=new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true) //select today's month
var thisyear=today.getFullYear()
for (var y=0; y<20; y++){
yearfield.options[y]=new Option(thisyear, thisyear)
thisyear+=1
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) //select today's year
}


function populatedropdownTime(hourfield, minutefield){
	var today=new Date()
	var hourfield=document.getElementById(hourfield)
	var minutefield=document.getElementById(minutefield)
	for (var i=0; i<24; i++)
	hourfield.options[i]=new Option(i, i+1)
/* 	dayfield.options[today.getDate()]=new Option(today.getDate(), today.getDate(), true, true) //select today's day */
	for (var m=0; m<60; m++)
	minutefield.options[m]=new Option(m, m+1)
	/* monthfield.options[today.getMonth()]=new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true) //select today's month */
	/* var thisyear=today.getFullYear()
	for (var y=0; y<20; y++){
	yearfield.options[y]=new Option(thisyear, thisyear)
	thisyear+=1
	}
	yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) //select today's year */
	}

</script>
  
  </head>
  
  
  
  
  <body>
  <!-- <div id="templatemo_header">
    <div id="site_title"><h1><a href="#" title="Group 12"><img src="images/bean.png" alt="image 2" /></a></h1></div>
  </div> -->
  
<!--   <div id="templatemo_main">
 -->  
  <% 
  
  UserService userService = UserServiceFactory.getUserService();
  User user = userService.getCurrentUser();
  
  
  if (user != null) {	 
  
  String emailAddress = "";
  String modifiedEmail = "";
  
  emailAddress = user.getEmail();
 
  
  
  //String[] tokens = emailAddress.split("@");
  
  modifiedEmail = emailAddress.replaceAll("@", "%40");
  
  
  %>
  
     <% String sessionToken = request.getParameter("sessionToken"); %> 
  
  
<!--   <div id="container"> -->
  
 
  
  
  <div class="left" style="margin:50" >
   
  
            <h2><font color="orange">Calendar</font></h2> 
            
        
  
 <!--  <div id="content"  class="half right" > -->
  	<!-- <iframe src="https://www.google.com/calendar/embed?src=appengine.group12%40gmail.com&ctz=America/Toronto" style="border: 0" width="800" height="600" frameborder="0" scrolling="no"> -->
  	<iframe src="https://www.google.com/calendar/embed?src=<%=modifiedEmail%>&ctz=America/Toronto" style="border: 0" width="800" height="600" frameborder="0" scrolling="no">
  	
  	</iframe>
  <!-- </div>	 -->
  	
    <!-- <form action="/CalendarAuthenticate" method="post">
  			<input type="submit" class="submit_btn float_l" value="Authenticate with calendar" />
  	</form> -->	 
  	
 </div> 	

<!-- <div class="half right"> -->

<div class="half right" style="width:350px; height:600px; overflow: auto;">


<a href="home.jsp" class="slider_nav_btn home_btn">home</a> 
<a href="home.jsp" class="slider_nav_btn previous_btn">Previous</a>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>




<h3><font color="orange">Add an event:</font></h3>
  	
  	<form action="/AddEvent"  method="get" name="form1">

start date:
  	
<select name="daydropdown1" id="daydropdown1">
</select> 
<select name="monthdropdown1" id="monthdropdown1">
</select> 
<select name="yeardropdown1" id="yeardropdown1">
</select> 

<br>

end date:
<select name="daydropdown2" id="daydropdown2">
</select> 
<select name="monthdropdown2" id="monthdropdown2">
</select> 
<select name="yeardropdown2" id="yeardropdown2">
</select> 

<br>

start time:
<select name="hourdropdown1" id="hourdropdown1">
</select> 
<select name="minutedropdown1" id="minutedropdown1">
</select> 

<br>


end time:
<select name="hourdropdown2" id="hourdropdown2">
</select> 
<select name="minutedropdown2" id="minutedropdown2">
</select> 

<input type = "hidden" name = "sessionToken" value="<%=sessionToken %>">

<br>
Event title: <input type='text' name='EventTitle' style='border:2px solid #6495ED'> <br>
Event details: <input type='text' name='EventDetails' style='border:2px solid #6495ED'> <br>
<input type = "submit" value='Add Event!'>

<!--         	out.println("<input type=hidden name= 'key1' value='"+rs.getString(1)+"'>"); -->
</form>

<!-- </div> -->

<form  action="UpdateEvent"  method="get" name="form2">
<h3><font color="orange">Update an event:</font></h3>

When is your event?
<br>  	
Start date:
<select name="daydropdown3" id="daydropdown3">
</select> 
<select name="monthdropdown3" id="monthdropdown3">
</select> 
<select name="yeardropdown3" id="yeardropdown3">
</select> 

<br>

End date:
<select name="daydropdown4" id="daydropdown4">
</select> 
<select name="monthdropdown4" id="monthdropdown4">
</select> 
<select name="yeardropdown4" id="yeardropdown4">
</select> 

<br>

Title of event to modify: <input type='text' name='EventToUpdate' style='border:2px solid #6495ED'> <br>
Title of the new event: <input type='text' name='NewEvent' style='border:2px solid #6495ED'> <br>
<input type = "hidden" name = "sessionToken" value="<%=sessionToken %>">
<input type = "submit" value='Update Event!'>

</form>



<form  action="DeleteEvent"  method="get" name="form3">
<h3><font color="orange">Delete an event:</font></h3>

When is your event?
<br>  	
Start date:
<select name="daydropdown5" id="daydropdown5">
</select> 
<select name="monthdropdown5" id="monthdropdown5">
</select> 
<select name="yeardropdown5" id="yeardropdown5">
</select> 

<br>

End date:
<select name="daydropdown6" id="daydropdown6">
</select> 
<select name="monthdropdown6" id="monthdropdown6">
</select> 
<select name="yeardropdown6" id="yeardropdown6">
</select> 

<br>

Title of event to delete: <input type='text' name='EventToDelete' style='border:2px solid #6495ED'> <br>
<input type = "hidden" name = "sessionToken" value="<%=sessionToken %>">
<input type = "submit" value='Delete Event!'>

</form>




</div>



<script type="text/javascript">

//populatedropdown(id_of_day_select, id_of_month_select, id_of_year_select)
/* window.onload=function(){
populatedropdown("daydropdown", "monthdropdown", "yeardropdown")
} */

populatedropdownDate("daydropdown1", "monthdropdown1", "yeardropdown1")
populatedropdownDate("daydropdown2", "monthdropdown2", "yeardropdown2")
populatedropdownTime("hourdropdown1", "minutedropdown1")
populatedropdownTime("hourdropdown2", "minutedropdown2")

populatedropdownDate("daydropdown3", "monthdropdown3", "yeardropdown3")
populatedropdownDate("daydropdown4", "monthdropdown4", "yeardropdown4")

populatedropdownDate("daydropdown5", "monthdropdown5", "yeardropdown5")
populatedropdownDate("daydropdown6", "monthdropdown6", "yeardropdown6")


</script>

<%-- <form>
<!-- <p><a href="/CalendarLogout"><img src="images/logout.png" alt="Logout" id="bottom" onclick="logoutFunction()"/></a></p> -->
<p><a><img src="images/logout.png" alt="Logout" id="bottom" onclick="logoutFunction(<%=sessionToken%>)" /></a></p>
<input type = "hidden" name = "sessionToken" value="<%=sessionToken %>">

</form>>  --%>	


<script>

//function logoutFunction(token)
//{
//	userService.createLogoutURL(req.getRequestURI());
//	AuthSubUtil.revokeToken(sessionToken, null);
	//}

</script>
		
			
		</div>
	<!-- </div> -->
	
	<p><a href="<%=userService.createLogoutURL(request.getRequestURI()) %>"><img src="images/logout.png" alt="Logout" id="topright" /></a></p>
	<%  	
     } else {
    		response.sendRedirect("loginRedirect.jsp");
    	}
	%>
	
  </body>
  
</html>