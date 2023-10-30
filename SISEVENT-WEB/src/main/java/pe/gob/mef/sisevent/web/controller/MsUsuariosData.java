package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.service.imp.ServicioImp;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.web.utils.Entrada;

public class MsUsuariosData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1331108546349165027L;

	private static final Logger log = Logger.getLogger(ServicioImp.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String, Entrada>();

	public MsUsuariosData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsUsuariosBk> getMsUsuariosActivos(String user, Servicio servicio) {
		List<MsUsuariosBk> msUsuariosBksss = null;
		String key = MsUsuariosBk.class.getSimpleName();
		if (dataCache.containsKey(key)) {
			Entrada entrada = dataCache.get(key);
			msUsuariosBksss = (List<MsUsuariosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if ((entrada.getUltimoacceso() + entrada.getTiempomuerto()) < System.currentTimeMillis()) {
				new Thread() {
					public void run() {
						try {
							List<MsUsuariosBk> msUsuariosBkssss = servicio.getAllMsUsuariosActivosCero(user);
							entrada.setLista(msUsuariosBkssss);
							entrada.setUltimoacceso(System.currentTimeMillis());
						} catch (Exception ex) {
							log.info(ex.getMessage());

						}
					}
				}.start();
			}
		} else {
			Entrada entrada = new Entrada();
			msUsuariosBksss = servicio.getAllMsUsuariosActivosCero(user);
			entrada.setLista(msUsuariosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msUsuariosBksss;
	}

	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, String user, MsUsuariosBk msUsuariosC){		
		List<MsUsuariosBk> msUsuariosBksss = null;
		String key = MsUsuariosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msUsuariosBksss = (List<MsUsuariosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsUsuariosBk> msUsuariosBkssss = servicio.getAllMsUsuariosActivosCero(user);
		    				entrada.setLista(msUsuariosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msUsuariosBksss = servicio.getAllMsUsuariosActivosCero(user);
			entrada.setLista(msUsuariosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msUsuariosBksss.contains(msUsuariosC)) {
			msUsuariosBksss.add(msUsuariosC);
		} else {
			int itemIndex = msUsuariosBksss.indexOf(msUsuariosC);
			if (itemIndex != -1) {
				msUsuariosBksss.set(itemIndex, msUsuariosC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, String user){
		String key = MsUsuariosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsUsuariosBk> msUsuariosBkssss = servicio.getAllMsUsuariosActivosCero(user);
    				entrada.setLista(msUsuariosBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
