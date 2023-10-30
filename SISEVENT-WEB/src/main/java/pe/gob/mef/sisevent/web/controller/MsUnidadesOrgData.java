package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsUnidadesOrgData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8821887266439353217L;

	private static final Logger log = Logger.getLogger(MsUnidadesOrgData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsUnidadesOrgData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsUnidadesOrgBk> getMsUnidadesOrgActivos(Servicio servicio,Long kyUsuarioMod){		
		List<MsUnidadesOrgBk> msUnidadesOrgBksss = null;
		String key = MsUnidadesOrgBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msUnidadesOrgBksss = (List<MsUnidadesOrgBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsUnidadesOrgBk> msUnidadesOrgBkssss = servicio.getAllMsUnidadesOrgActivosCero(kyUsuarioMod);
		    				entrada.setLista(msUnidadesOrgBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msUnidadesOrgBksss = servicio.getAllMsUnidadesOrgActivosCero(kyUsuarioMod);
			entrada.setLista(msUnidadesOrgBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msUnidadesOrgBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsUnidadesOrgBk msUnidadesOrgC){		
		List<MsUnidadesOrgBk> msUnidadesOrgBksss = null;
		String key = MsUnidadesOrgBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msUnidadesOrgBksss = (List<MsUnidadesOrgBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsUnidadesOrgBk> msUnidadesOrgBkssss = servicio.getAllMsUnidadesOrgActivosCero(kyUsuarioMod);
		    				entrada.setLista(msUnidadesOrgBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msUnidadesOrgBksss = servicio.getAllMsUnidadesOrgActivosCero(kyUsuarioMod);
			entrada.setLista(msUnidadesOrgBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msUnidadesOrgBksss.contains(msUnidadesOrgC)) {
			msUnidadesOrgBksss.add(msUnidadesOrgC);
		} else {
			int itemIndex = msUnidadesOrgBksss.indexOf(msUnidadesOrgC);
			if (itemIndex != -1) {
				msUnidadesOrgBksss.set(itemIndex, msUnidadesOrgC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsUnidadesOrgBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsUnidadesOrgBk> msUnidadesOrgBkssss = servicio.getAllMsUnidadesOrgActivosCero(kyUsuarioMod);
    				entrada.setLista(msUnidadesOrgBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
