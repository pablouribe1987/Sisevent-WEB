package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUbigeoBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsUbigeoData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2113791653574537575L;

	private static final Logger log = Logger.getLogger(MsUbigeoData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsUbigeoData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsUbigeoBk> getMsUbigeoActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsUbigeoBk> msUbigeoBksss = null;
		String key = MsUbigeoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msUbigeoBksss = (List<MsUbigeoBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsUbigeoBk> msUbigeoBkssss = servicio.getAllMsUbigeoActivosCero(kyUsuarioMod);
		    				entrada.setLista(msUbigeoBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msUbigeoBksss = servicio.getAllMsUbigeoActivosCero(kyUsuarioMod);
			entrada.setLista(msUbigeoBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msUbigeoBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsUbigeoBk msUbigeoC){		
		List<MsUbigeoBk> msUbigeoBksss = null;
		String key = MsUbigeoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msUbigeoBksss = (List<MsUbigeoBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsUbigeoBk> msUbigeoBkssss = servicio.getAllMsUbigeoActivosCero(kyUsuarioMod);
		    				entrada.setLista(msUbigeoBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msUbigeoBksss = servicio.getAllMsUbigeoActivosCero(kyUsuarioMod);
			entrada.setLista(msUbigeoBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msUbigeoBksss.contains(msUbigeoC)) {
			msUbigeoBksss.add(msUbigeoC);
		} else {
			int itemIndex = msUbigeoBksss.indexOf(msUbigeoC);
			if (itemIndex != -1) {
				msUbigeoBksss.set(itemIndex, msUbigeoC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsUbigeoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsUbigeoBk> msUbigeoBkssss = servicio.getAllMsUbigeoActivosCero(kyUsuarioMod);
    				entrada.setLista(msUbigeoBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
