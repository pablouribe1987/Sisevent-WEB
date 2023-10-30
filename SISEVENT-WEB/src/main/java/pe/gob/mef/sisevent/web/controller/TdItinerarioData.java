package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdItinerarioBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdItinerarioData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdItinerarioData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdItinerarioData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdItinerarioBk> getTdItinerarioActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdItinerarioBk> tdItinerarioBksss = null;
		String key = TdItinerarioBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdItinerarioBksss = (List<TdItinerarioBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdItinerarioBk> tdItinerarioBkssss = servicio.getAllTdItinerarioActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdItinerarioBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdItinerarioBksss = servicio.getAllTdItinerarioActivosCero(kyUsuarioMod);
			entrada.setLista(tdItinerarioBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdItinerarioBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdItinerarioBk tdItinerarioC){		
		List<TdItinerarioBk> tdItinerarioBksss = null;
		String key = TdItinerarioBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdItinerarioBksss = (List<TdItinerarioBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdItinerarioBk> tdItinerarioBkssss = servicio.getAllTdItinerarioActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdItinerarioBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdItinerarioBksss = servicio.getAllTdItinerarioActivosCero(kyUsuarioMod);
			entrada.setLista(tdItinerarioBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdItinerarioBksss.contains(tdItinerarioC)) {
			tdItinerarioBksss.add(tdItinerarioC);
		} else {
			int itemIndex = tdItinerarioBksss.indexOf(tdItinerarioC);
			if (itemIndex != -1) {
				tdItinerarioBksss.set(itemIndex, tdItinerarioC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdItinerarioBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdItinerarioBk> tdItinerarioBkssss = servicio.getAllTdItinerarioActivosCero(kyUsuarioMod);
    				entrada.setLista(tdItinerarioBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
