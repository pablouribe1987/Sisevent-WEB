package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdGruposData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdGruposData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdGruposData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdGruposBk> getTdGruposActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdGruposBk> tdGruposBksss = null;
		String key = TdGruposBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdGruposBksss = (List<TdGruposBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdGruposBk> tdGruposBkssss = servicio.getAllTdGruposActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdGruposBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdGruposBksss = servicio.getAllTdGruposActivosCero(kyUsuarioMod);
			entrada.setLista(tdGruposBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdGruposBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdGruposBk tdGruposC){		
		List<TdGruposBk> tdGruposBksss = null;
		String key = TdGruposBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdGruposBksss = (List<TdGruposBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdGruposBk> tdGruposBkssss = servicio.getAllTdGruposActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdGruposBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdGruposBksss = servicio.getAllTdGruposActivosCero(kyUsuarioMod);
			entrada.setLista(tdGruposBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdGruposBksss.contains(tdGruposC)) {
			tdGruposBksss.add(tdGruposC);
		} else {
			int itemIndex = tdGruposBksss.indexOf(tdGruposC);
			if (itemIndex != -1) {
				tdGruposBksss.set(itemIndex, tdGruposC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdGruposBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdGruposBk> tdGruposBkssss = servicio.getAllTdGruposActivosCero(kyUsuarioMod);
    				entrada.setLista(tdGruposBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
