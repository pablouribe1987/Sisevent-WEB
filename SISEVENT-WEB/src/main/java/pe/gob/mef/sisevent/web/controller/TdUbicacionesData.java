package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdUbicacionesBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdUbicacionesData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdUbicacionesData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdUbicacionesData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdUbicacionesBk> getTdUbicacionesActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdUbicacionesBk> tdUbicacionesBksss = null;
		String key = TdUbicacionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdUbicacionesBksss = (List<TdUbicacionesBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdUbicacionesBk> tdUbicacionesBkssss = servicio.getAllTdUbicacionesActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdUbicacionesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdUbicacionesBksss = servicio.getAllTdUbicacionesActivosCero(kyUsuarioMod);
			entrada.setLista(tdUbicacionesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdUbicacionesBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdUbicacionesBk tdUbicacionesC){		
		List<TdUbicacionesBk> tdUbicacionesBksss = null;
		String key = TdUbicacionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdUbicacionesBksss = (List<TdUbicacionesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdUbicacionesBk> tdUbicacionesBkssss = servicio.getAllTdUbicacionesActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdUbicacionesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdUbicacionesBksss = servicio.getAllTdUbicacionesActivosCero(kyUsuarioMod);
			entrada.setLista(tdUbicacionesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdUbicacionesBksss.contains(tdUbicacionesC)) {
			tdUbicacionesBksss.add(tdUbicacionesC);
		} else {
			int itemIndex = tdUbicacionesBksss.indexOf(tdUbicacionesC);
			if (itemIndex != -1) {
				tdUbicacionesBksss.set(itemIndex, tdUbicacionesC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdUbicacionesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdUbicacionesBk> tdUbicacionesBkssss = servicio.getAllTdUbicacionesActivosCero(kyUsuarioMod);
    				entrada.setLista(tdUbicacionesBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
