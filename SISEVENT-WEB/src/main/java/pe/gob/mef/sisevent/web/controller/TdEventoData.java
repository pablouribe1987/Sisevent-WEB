package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdEventoBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdEventoData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdEventoData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdEventoData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdEventoBk> getTdEventoActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdEventoBk> tdEventoBksss = null;
		String key = TdEventoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdEventoBksss = (List<TdEventoBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdEventoBk> tdEventoBkssss = servicio.getAllTdEventoActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdEventoBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdEventoBksss = servicio.getAllTdEventoActivosCero(kyUsuarioMod);
			entrada.setLista(tdEventoBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdEventoBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdEventoBk tdEventoC){		
		List<TdEventoBk> tdEventoBksss = null;
		String key = TdEventoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdEventoBksss = (List<TdEventoBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdEventoBk> tdEventoBkssss = servicio.getAllTdEventoActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdEventoBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdEventoBksss = servicio.getAllTdEventoActivosCero(kyUsuarioMod);
			entrada.setLista(tdEventoBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdEventoBksss.contains(tdEventoC)) {
			tdEventoBksss.add(tdEventoC);
		} else {
			int itemIndex = tdEventoBksss.indexOf(tdEventoC);
			if (itemIndex != -1) {
				tdEventoBksss.set(itemIndex, tdEventoC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdEventoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdEventoBk> tdEventoBkssss = servicio.getAllTdEventoActivosCero(kyUsuarioMod);
    				entrada.setLista(tdEventoBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
