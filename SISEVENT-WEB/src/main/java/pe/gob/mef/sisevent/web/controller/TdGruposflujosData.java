package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdGruposflujosData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdGruposflujosData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdGruposflujosData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdGruposflujosBk> getTdGruposflujosActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdGruposflujosBk> tdGruposflujosBksss = null;
		String key = TdGruposflujosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdGruposflujosBksss = (List<TdGruposflujosBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdGruposflujosBk> tdGruposflujosBkssss = servicio.getAllTdGruposflujosActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdGruposflujosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdGruposflujosBksss = servicio.getAllTdGruposflujosActivosCero(kyUsuarioMod);
			entrada.setLista(tdGruposflujosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdGruposflujosBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdGruposflujosBk tdGruposflujosC){		
		List<TdGruposflujosBk> tdGruposflujosBksss = null;
		String key = TdGruposflujosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdGruposflujosBksss = (List<TdGruposflujosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdGruposflujosBk> tdGruposflujosBkssss = servicio.getAllTdGruposflujosActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdGruposflujosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdGruposflujosBksss = servicio.getAllTdGruposflujosActivosCero(kyUsuarioMod);
			entrada.setLista(tdGruposflujosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdGruposflujosBksss.contains(tdGruposflujosC)) {
			tdGruposflujosBksss.add(tdGruposflujosC);
		} else {
			int itemIndex = tdGruposflujosBksss.indexOf(tdGruposflujosC);
			if (itemIndex != -1) {
				tdGruposflujosBksss.set(itemIndex, tdGruposflujosC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdGruposflujosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdGruposflujosBk> tdGruposflujosBkssss = servicio.getAllTdGruposflujosActivosCero(kyUsuarioMod);
    				entrada.setLista(tdGruposflujosBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
