package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsInstitucionesBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsInstitucionesData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8068599873721387880L;

	private static final Logger log = Logger.getLogger(MsInstitucionesData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsInstitucionesData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsInstitucionesBk> getMsInstitucionesActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsInstitucionesBk> msInstitucionesBksss = null;
		String key = MsInstitucionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){ 
			Entrada entrada = dataCache.get(key);
			msInstitucionesBksss = (List<MsInstitucionesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsInstitucionesBk> msInstitucionesBkssss = servicio.getAllMsInstitucionesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msInstitucionesBkssss); 
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msInstitucionesBksss = servicio.getAllMsInstitucionesActivosCero(kyUsuarioMod);
			entrada.setLista(msInstitucionesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msInstitucionesBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsInstitucionesBk msInstitucionesC){		
		List<MsInstitucionesBk> msInstitucionesBksss = null;
		String key = MsInstitucionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msInstitucionesBksss = (List<MsInstitucionesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsInstitucionesBk> msInstitucionesBkssss = servicio.getAllMsInstitucionesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msInstitucionesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msInstitucionesBksss = servicio.getAllMsInstitucionesActivosCero(kyUsuarioMod);
			entrada.setLista(msInstitucionesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msInstitucionesBksss.contains(msInstitucionesC)) {
			msInstitucionesBksss.add(msInstitucionesC);
		} else {
			int itemIndex = msInstitucionesBksss.indexOf(msInstitucionesC);
			if (itemIndex != -1) {
				msInstitucionesBksss.set(itemIndex, msInstitucionesC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsInstitucionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsInstitucionesBk> msInstitucionesBkssss = servicio.getAllMsInstitucionesActivosCero(kyUsuarioMod);
    				entrada.setLista(msInstitucionesBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
	
}
