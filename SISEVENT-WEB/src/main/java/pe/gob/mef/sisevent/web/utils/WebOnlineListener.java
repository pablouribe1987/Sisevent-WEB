package pe.gob.mef.sisevent.web.utils;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pe.gob.mef.sisevent.web.controller.rs.data.MsUsuariosPerfilJS;

public class WebOnlineListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {

	private ServletContext application = null; 
	
	public WebOnlineListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// Get a list of user names  
		  List<MsUsuariosPerfilJS> online = (List<MsUsuariosPerfilJS>) this.application.getAttribute("online"); 
		  // Gets the current user name  
		  MsUsuariosPerfilJS username = (MsUsuariosPerfilJS) arg0.getSession().getAttribute("MsUsuariosPerfilJS"); 
		  // Remove this user name from the list  
		  online.remove(username); 
		  // Reset the deleted list to application Properties of the  
		  this.application.setAttribute("online", online);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// Get a list of user names  
		  System.out.println("one attribute is added in User session "+arg0.getName()+" ID "+arg0.getSession().getId());
		  		
		  List<MsUsuariosPerfilJS> online = (List<MsUsuariosPerfilJS>) this.application.getAttribute("online"); 
		  if ("MsUsuariosPerfilJS".equals(arg0.getName())) { 
		   // Adds the current user name to the list  
			  online.add((MsUsuariosPerfilJS) arg0.getValue()); 
		  } 
		  // Reset the added list to application Properties of the  
		  this.application.setAttribute("online", online); 
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Initialize the 1 a application object  
		  this.application = arg0.getServletContext(); 
		  // Set up the 1 The list of properties used to save the user name in thought  
		  this.application.setAttribute("online", new LinkedList<MsUsuariosPerfilJS>()); 
	}

}
