package guestbook1;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.http.AuthSubUtil;
import com.google.gdata.util.AuthenticationException;

public class CalendarLogout extends HttpServlet {
		
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
	        
	        try {
	        	out.println("Token revoked!");
	        	 //userService.createLogoutURL(req.getRequestURI());
				AuthSubUtil.revokeToken(sessionToken, null);
				userService.createLogoutURL(req.getRequestURI());
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}