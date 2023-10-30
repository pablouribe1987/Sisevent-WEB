package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPersonasBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsPersonasData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8792775744238248434L;

	private static final Logger log = Logger.getLogger(MsPersonasData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsPersonasData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsPersonasBk> getMsPersonasActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsPersonasBk> msPersonasBksss = null;
		String key = MsPersonasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msPersonasBksss = (List<MsPersonasBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsPersonasBk> msPersonasBkssss = servicio.getAllMsPersonasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msPersonasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msPersonasBksss = servicio.getAllMsPersonasActivosCero(kyUsuarioMod);
			entrada.setLista(msPersonasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msPersonasBksss;		
	}

	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsPersonasBk msPersonasC){		
		List<MsPersonasBk> msPersonasBksss = null;
		String key = MsPersonasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msPersonasBksss = (List<MsPersonasBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsPersonasBk> msPersonasBkssss = servicio.getAllMsPersonasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msPersonasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msPersonasBksss = servicio.getAllMsPersonasActivosCero(kyUsuarioMod);
			entrada.setLista(msPersonasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msPersonasBksss.contains(msPersonasC)) {
			msPersonasBksss.add(msPersonasC);
		} else {
			int itemIndex = msPersonasBksss.indexOf(msPersonasC);
			if (itemIndex != -1) {
				msPersonasBksss.set(itemIndex, msPersonasC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsPersonasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsPersonasBk> msPersonasBkssss = servicio.getAllMsPersonasActivosCero(kyUsuarioMod);
    				entrada.setLista(msPersonasBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
