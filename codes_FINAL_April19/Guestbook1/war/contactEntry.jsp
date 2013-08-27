<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.io.IOException"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.SimpleTimeZone"%>
<%@ page import="javax.persistence.EntityManagerFactory"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="javax.persistence.Query"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="guestbook1.ToDo"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory.Builder"%>


<html>
  <head>
    <link type="text/css" rel="stylesheet" href="templatemo_style.css" />
  </head>

  <body>
	<div id="templatemo_header">
    <div id="site_title"><h1><a href="#" title="Group 12"><img src="images/bean.png" alt="image 2" /></a></h1></div>
	</div>

<div id="templatemo_main">

<%
//    String guestbookName = request.getParameter("contactbookName");
   /*  if (contactbookName == null) {
        contactbookName = "default";
    } */
    /* pageContext.setAttribute("contactbookName", contactbookName); */
    EntityManagerFactory emf = guestbook1.EMF.get();
    EntityManager em = null;
    
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
     if (user != null) {
    //  pageContext.setAttribute("user", user); 
    	 
	%>
	 
	
	<div class="section section_with_padding" id="todo"> 
            <h2><font color="pink">My Contact Book</font></h2> 
            
	  <div class="half left">
      	<h4>Enter a New Contact...</h4>
                
	  <div id="contact_form">
	  <form action="/JPATransactionsServlet_Group12" method="post">
	  <div class="left">
      <!-- <div><textarea name="content" rows="3" cols="60"></textarea></div> -->
      <table>
      <tr>
      <td>Name</td><td><input type = "text" class="input_field" name = "contactName" required></td>
      </tr>
      <tr>
      <td>Phone Number</td><td><input type = 'tel' pattern='[0-9]{10}' class="input_field" name = "phoneNumber"></td>
      </tr>
      <tr>
      <td>Email Address</td><td><input type = "email" class="input_field" name = "emailAddress"></td>
      </tr>
      </table>
      <input type="submit" class="submit_btn float_l" value="Add contact" />
      <input type="hidden" name="contactbookName" value="${fn:escapeXml(contactbookName)}"/>
      
 	  </div>  
      </form>
      
    	<form action="/BookQuery_Group12" method="get">
    	<input type="submit" class="submit_btn float_l" value="Go to my Contact Book" />
    	</form>
    	
		</div>
		
	</div>
		
		 <%/* String LatLng = request.getParameter("LatLgn");
		String[] LatLng_split = LatLng.split(",");
		double lat = Double.parseDouble(LatLng_split[0]);	
        double lng = Double.parseDouble(LatLng_split[1]);*/
		%>
		<!--  <script type="text/javascript">
    		var lat_script = "";
    		var lng_script = "";
		</script>-->
		
	<div class="half right">
	
			<a href="home.jsp" class="slider_nav_btn home_btn">home</a> 
          	<a href="home.jsp" class="slider_nav_btn previous_btn">Previous</a>
		<!-- 		
		 <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">
          html { height: 100% }
          body { height: 70%; margin: 0; padding: 10px }
          #map-canvas { height: 65% }
        </style>
        <script type="text/javascript"
          src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDix3P9I2Wry9Ggos01ANz215sZ0INHgyQ&sensor=false">
        </script>
        <script type="text/javascript">
          function initialize() {
        	  
        	  var mapOptions = {
              
        	  
              //double lat = Double.parseDouble(LatLng_split[0]);	
              //double lng = Double.parseDouble(LatLng_split[1]);
              center: new google.maps.LatLng(lat_script, lng_script),
              zoom: 8,
              mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);
            
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(43.66935450, -79.38773770),
                map: map,
                title:"Hello World!"
            });
            
          }
          google.maps.event.addDomListener(window, 'load', initialize);
        </script>
			<div id="map-canvas"/>	
				-->
			
             
	</div>	
		
 	</div>
 </div> 
 
	<p><a href="<%=userService.createLogoutURL(request.getRequestURI()) %>"><img src="images/logout.png" alt="Logout" id="topright" /></a></p>
 	<%
 		
     } else {
    		response.sendRedirect("loginRedirect.jsp");
    	}
	%>

  </body>
</html>