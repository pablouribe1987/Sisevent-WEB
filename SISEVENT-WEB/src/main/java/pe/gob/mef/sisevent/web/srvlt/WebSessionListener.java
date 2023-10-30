package pe.gob.mef.sisevent.web.srvlt;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class WebSessionListener implements HttpSessionListener {

	public WebSessionListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// System.out.println("Session creada");
		ServletContext contexto = arg0.getSession().getServletContext();
		synchronized (contexto) {
			Integer usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
			if (usuarioConectados == null) {
				usuarioConectados = new Integer(0);
			}
			usuarioConectados += 1;
			contexto.setAttribute("usuariosConectados", usuarioConectados);
			
			Calendar now = GregorianCalendar.getInstance();
			int dayNumber = now.get(Calendar.DAY_OF_MONTH);
			@SuppressWarnings("unchecked")
			HashMap<Integer,Integer> delmes =  (HashMap<Integer, Integer>) contexto.getAttribute("MapaMaxUsuariosConectados");
			if(delmes==null){
				delmes = new HashMap<Integer,Integer>();
			}
			int maximodehoy = 0;
			if(delmes.containsKey(dayNumber)){
				maximodehoy = delmes.get(dayNumber);
			}
			if(usuarioConectados>maximodehoy){
				delmes.put(dayNumber,usuarioConectados);
			}
			contexto.setAttribute("MapaMaxUsuariosConectados", delmes);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// System.out.println("Session destruida");
		ServletContext contexto = arg0.getSession().getServletContext();
		synchronized (contexto) {
			Integer usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
			if (usuarioConectados == null) {
				usuarioConectados = new Integer(0);
			}
			usuarioConectados -= 1;
			contexto.setAttribute("usuariosConectados", usuarioConectados);
		}
	}

}
