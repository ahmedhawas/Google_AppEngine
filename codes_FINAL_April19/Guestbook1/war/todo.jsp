<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
  <head>
    <link type="text/css" rel="stylesheet" href="templatemo_style.css" />
  </head>

  <body>
	<div id="templatemo_header">
    	<div id="site_title"><h1><a href="#" title="Group 12"><img src="images/bean.png" alt="image 2" /></a></h1></div>
	</div>
 <%
    List<String> checkbox_item_id = new ArrayList<String>();
 	
    String guestbookName = request.getParameter("guestbookName");
    
    if (guestbookName == null) {
        guestbookName = "default";
    }
    pageContext.setAttribute("guestbookName", guestbookName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      pageContext.setAttribute("user", user);
      
      %>
<%
	int item_num=0;
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
   
    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
    Query query = new Query("ToDoList", guestbookKey).addSort("date", Query.SortDirection.DESCENDING);
   
    List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
    
    
%> 	
<div id="templatemo_main">
<div class="section section_with_padding" id="todo"> 
            <h2>Welcome to-<font color="orange">DO</font></h2> 
            <div class="half left">
                <h4>Current Items on List...</h4>
                
 			<%         
    		if (greetings.isEmpty()) {
       		
    		} else {
       		 %>
     		 <%
        	for (Entity greeting : greetings) {
        		
        	String todo_content = greeting.getProperty("content").toString();
        	String user_name1=greeting.getProperty("user").toString();
        	String user_name2= user.toString();			
            
        	pageContext.setAttribute("greeting_content",
                                     greeting.getProperty("content"));
            if (greeting.getProperty("user") == null) {
                %>
                <!--<p>An anonymous person wrote:</p>-->
                <%
            } else {
                pageContext.setAttribute("greeting_user",
                                         greeting.getProperty("user"));
                %>
                <!-- <p><b>${fn:escapeXml(greeting_user.nickname)}</b> wrote:</p>-->
                <%
            }
            %>
            	<%
            	//String todo_content = greeting.getProperty("content").toString();
        		//String user_name1=greeting.getProperty("user").toString();
      			//String user_name2= user.toString();
        		if(user_name1.compareTo(user_name2)==0 ){
        		checkbox_item_id.add(todo_content);	
        		%>
        		
        		
        		<form action="/ToDo_Delete" method="post">
            <div class="box home_box3 color5">
            	<font size="4" color="white">
            	<span class="tab"><input type="checkbox" name="check_state" value="<%=item_num%>" id="check_state"></span> 
        		<span class="tab"> ${fn:escapeXml(greeting_content)} </span>
        		<br>
            	</font>
            </div>
            
            <br>
          
            <% 
            item_num = item_num + 1;
            }
        	%>
            <!--  <blockquote>${fn:escapeXml(greeting_content)}</blockquote>-->
            <%
        }
    }
%>	 
 				<div id="contact_form">	
                <input type="submit" class="submit_btn float_l" name="submit" id="submit" value="Completed Tasks!" onclick="return confirm('Are you sure you want to delete these items?')"/><br>
                <input type="hidden" name="checkbox_item_id" value="<%=checkbox_item_id %>"/>
                <!-- <label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0"></textarea> -->
                </div>
           </form>
           
           
           
                        
            </div> 
                
            <div class="half right">
                	<h4>Add New Item...</h4>
                 	<div id="contact_form">
                   		<form action="/sign" method="post">
                 		<div class="left">
                            <label for="author"></label> <input name="content" type="text" class="input_field" id="submit" placeholder="Add new item..." maxlength="40" />
                       		<input type="submit" class="submit_btn float_l" name="submit" value="+" />
                       		<input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
                        </div>
                		</form>
                	</div>
                	<br>
                	<br />
                	<br />
          		<strong>Email: appengine.group12[ at ]gmail[ dot ]com</strong><br />
                
                <div class="clear h20"></div>
				<a href="home.html" class="slider_nav_btn home_btn">home</a> 
           		<a href="#testimonial" class="slider_nav_btn previous_btn">Previous</a>
             
        	</div> 
 	
 </div>
 </div>  
 	
<%! 
private void tasks_complete()
{
	//if(checkbox_id.checked == 1){
	//alert("ALERTING");
	//}
	//out.println("HELLO");
	
}
 %>
	<!--  <p>Hello, ${fn:escapeXml(user.nickname)}! (You can-->
	 
	 <p><a href="<%=userService.createLogoutURL(request.getRequestURI()) %>"><img src="images/logout.png" alt="Logout" id="topright" /></a></p>
	
	<%
    	} else {
    		response.sendRedirect("loginRedirect.jsp");
    		
    	}
	%>
	
  </body>
</html>