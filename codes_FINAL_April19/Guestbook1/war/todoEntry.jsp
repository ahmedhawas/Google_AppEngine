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
<div class="section section_with_padding" id="todo"> 
            <h2>Welcome to-<font color="orange">DO</font></h2> 
            <div class="half left">
                <h4>Current Items on List...</h4>

	<%
	int item_num =0;
	List<String> checkbox_item_id = new ArrayList<String>();
    EntityManagerFactory emf = guestbook1.EMF.get();
    EntityManager em = null;
        
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
     if (user != null) {	 
	%>
	
	
	
	
       
	  <!-- <div id="contact_form"> -->
	  <!-- <form action="/JPATransactionsServlet_Group12" method="post"> -->
	  <!--  <div class="left"> -->
      <%
      try {
          em = emf.createEntityManager();
         
          
          Query query = null;
          List<ToDo> results = null;

          // Query for all entities of a kind
          query = em.createQuery("SELECT b FROM ToDo b");
          //out.println("<div id='templatemo_main'>");
          //out.println("<div class='section section_with_padding' id='todo'>");
          //out.println("<h2>Things to do...</h2>");
         
          //out.println("<p>Every contact:</p><ul>");
          results = (List<ToDo>) query.getResultList();
          
          
         int num_todos = 0;
          
          for (ToDo b : results) {
        	
          	if (b.getUser().getNickname().equalsIgnoreCase(user.getNickname()))
          	{
          		checkbox_item_id.add(b.content.getName());
          		%>
          		<form action="/ToDo_Delete" method="post">
        			<div class="box home_box3 color5">
        			<font size="4" color="white">
        			<span class="tab"><input type="checkbox" name="check_state" value="<%=item_num%>" id="check_state"></span> 
    				<span class="tab"> <%=b.content.getName() %>  </span>
    				<br>
        			</font>
        			</div>
          		<!--out.println("<li><i>" + b.content.getName() + "</li>"); -->
                <%  
                item_num = item_num + 1;
          	}
          	num_todos++;
              
          	request.setAttribute("Content", b.content.getName());
          	request.setAttribute("Num", num_todos);
          
          }
          %>
          <div id="contact_form">	
          <input type="submit" class="submit_btn float_l" name="submit" id="submit" value="Completed Tasks!" onclick="return confirm('Are you sure you want to delete these items?')"/><br>
          <input type="hidden" name="checkbox_item_id" value="<%=checkbox_item_id%>"/>
          <!-- <label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0"></textarea> -->
          </div>
    	 </form>
          <% 
          
          //out.println("</ul>");
			
      } finally {
          em.close();
         
      }
      %>
      <!-- </div>  -->
      <!--  </form>-->
 	   
		<!--  </div> -->
	</div>
		
	 <div class="half right">
                	<h4>Add New Item...</h4>
                 	<div id="contact_form">
                   		<form action="/JPATransactions_ToDo" method="post">
                 		<div class="left">
                            <label for="author"></label> <input name="content" type="text" class="input_field" id="submit" placeholder="Add new item..." maxlength="40" required/>
                       		<input type="submit" class="submit_btn float_l" name="submit" value="+" />
                       		<input type="hidden" name="todoListEntry" value="${fn:escapeXml(guestbookName)}"/>
                        </div>
                		</form>
                	</div>
                	<br>
                	<br />
                	<br />
          		
                
				<a href="home.jsp" class="slider_nav_btn home_btn">home</a> 
           		<a href="home.jsp" class="slider_nav_btn previous_btn">Previous</a>
             
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