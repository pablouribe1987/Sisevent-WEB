package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsTareasBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsTareasData implements Serializable{
	
	private static final Logger log = Logger.getLogger(MsTareasData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsTareasData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsTareasBk> getMsTareasActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsTareasBk> msTareasBksss = null;
		String key = MsTareasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msTareasBksss = (List<MsTareasBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsTareasBk> msTareasBkssss = servicio.getAllMsTareasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msTareasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msTareasBksss = servicio.getAllMsTareasActivosCero(kyUsuarioMod);
			entrada.setLista(msTareasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msTareasBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsTareasBk msTareasC){		
		List<MsTareasBk> msTareasBksss = null;
		String key = MsTareasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msTareasBksss = (List<MsTareasBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsTareasBk> msTareasBkssss = servicio.getAllMsTareasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msTareasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msTareasBksss = servicio.getAllMsTareasActivosCero(kyUsuarioMod);
			entrada.setLista(msTareasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msTareasBksss.contains(msTareasC)) {
			msTareasBksss.add(msTareasC);
		} else {
			int itemIndex = msTareasBksss.indexOf(msTareasC);
			if (itemIndex != -1) {
				msTareasBksss.set(itemIndex, msTareasC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsTareasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsTareasBk> msTareasBkssss = servicio.getAllMsTareasActivosCero(kyUsuarioMod);
    				entrada.setLista(msTareasBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
