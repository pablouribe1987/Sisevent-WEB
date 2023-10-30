package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsActividadesBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsActividadesData implements Serializable{
	
	private static final Logger log = Logger.getLogger(MsActividadesData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsActividadesData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsActividadesBk> getMsActividadesActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsActividadesBk> msActividadesBksss = null;
		String key = MsActividadesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msActividadesBksss = (List<MsActividadesBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsActividadesBk> msActividadesBkssss = servicio.getAllMsActividadesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msActividadesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msActividadesBksss = servicio.getAllMsActividadesActivosCero(kyUsuarioMod);
			entrada.setLista(msActividadesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msActividadesBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsActividadesBk msActividadesC){		
		List<MsActividadesBk> msActividadesBksss = null;
		String key = MsActividadesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msActividadesBksss = (List<MsActividadesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsActividadesBk> msActividadesBkssss = servicio.getAllMsActividadesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msActividadesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msActividadesBksss = servicio.getAllMsActividadesActivosCero(kyUsuarioMod);
			entrada.setLista(msActividadesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msActividadesBksss.contains(msActividadesC)) {
			msActividadesBksss.add(msActividadesC);
		} else {
			int itemIndex = msActividadesBksss.indexOf(msActividadesC);
			if (itemIndex != -1) {
				msActividadesBksss.set(itemIndex, msActividadesC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsActividadesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsActividadesBk> msActividadesBkssss = servicio.getAllMsActividadesActivosCero(kyUsuarioMod);
    				entrada.setLista(msActividadesBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
