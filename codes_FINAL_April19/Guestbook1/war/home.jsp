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


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Personal Organizer</title>
<meta name="keywords" content="CSS, HTML, servlets, JSPs, javascript" />
<meta name="description" content="This is our Google AppEngine personal organization app." />

	<link href="templatemo_style.css" type="text/css" rel="stylesheet" /> 
	<script type="text/javascript" src="js/jquery.min.js"></script> 
	<script type="text/javascript" src="js/jquery.scrollTo-min.js"></script> 
	<script type="text/javascript" src="js/jquery.localscroll-min.js"></script> 
	<script type="text/javascript" src="js/init.js"></script> 
    
    <link rel="stylesheet" href="css/slimbox2.css" type="text/css" media="screen" /> 
    <script type="text/JavaScript" src="js/slimbox2.js"></script> 

</head> 
<body> 
<div id="templatemo_header">
    <div id="site_title"><h1><a href="#" title="Group 12"><img src="images/bean.png" alt="image 2" /></a></h1></div>
</div>

			<%
			int item_num =0;
			List<String> checkbox_item_id = new ArrayList<String>();
    		EntityManagerFactory emf = guestbook1.EMF.get();
    		EntityManager em = null;
        
    		UserService userService = UserServiceFactory.getUserService();
    		User user = userService.getCurrentUser();
     		if (user != null) {	 
			%>
<div id="templatemo_main">
    <div id="content"> 
		<div id="home" class="section">
        	
        	
        	
        	
			<div id="home_about" class="box">
           	  <h2>Welcome to </h2>
           	  	<h3>BEAN Organized</h3>
                <!-- <p>This is a <a href="http://ehappy.sa.utoronto.ca">awesome personal</a> organizer <a href="http://ehappy.sa.utoronto.ca/"> for everyone</a> to use! We are making<a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>this</strong></a> <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>for</strong></a> our very time consuming cloud computing project.</p>
                <p>Although I must say it is pretty fun!</p>
                <p>So far you can create your own <a href="todo.jsp">to-do</a> list!</p> -->
                <p>BEAN Organized allows you to stay organized regardless of where you are or what you're doing!</p>
                <p>Making use of cloud computing technology you can access and edit your personal to-do list, contact book and calendar from anywhere.
                Click on the icons to the right to begin making use of these wonderful features and explore our app!</p>
            </div>
            
            <div id="home_gallery" class="box no_mr">
                <a href="/BookQuery_Group12"><img src="images/gallery/01-2.png" alt="image 1" /></a>
                <a href="home.jsp" ><img src="images/gallery/02-21.png" alt="image 2" /></a>
                <a href="home.jsp"  class="no_mr"><img src="images/gallery/03-1.png" alt="image 3" /></a>
                <a href="/CalendarAuthenticate"><img src="images/gallery/04-2.png" alt="image 4" /></a>
                <a href="home.jsp" ><img src="images/gallery/05-2.png" alt="image 5" /></a>
                <a href="todoEntry.jsp" class="no_mr"><img src="images/gallery/06-2.png" alt="image 6" /></a>
            </div>
           
           
               
        </div> 
        
        
                
            <a href="#home" class="slider_nav_btn home_btn">home</a> 
            <a href="#services" class="slider_nav_btn previous_btn">Previous</a>
            <a href="#contact" class="slider_nav_btn next_btn">Next</a> 
        </div> 
        	
        
        </div> 
         
    </div> 
</div>
</div>

<p><a href="<%=userService.createLogoutURL(request.getRequestURI()) %>"><img src="images/logout.png" alt="Logout" id="topright" /></a></p>
 <!-- <a href="logoutRedirect.jsp"><img src="images/logout.png" alt="Logout" id="topright" /></a></p> -->

<div id="templatemo_footer">
    <a href="#">ECE1779</a> | Designed by <a href="#" target="_parent">Group 12</a>
</div>
	<%
 		
     } else {
    		response.sendRedirect("loginRedirect.jsp");
    	}
	%>

</body> 
</html>