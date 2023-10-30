package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsCategoriasBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsCategoriasData implements Serializable{
	
	private static final Logger log = Logger.getLogger(MsCategoriasData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsCategoriasData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsCategoriasBk> getMsCategoriasActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsCategoriasBk> msCategoriasBksss = null;
		String key = MsCategoriasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msCategoriasBksss = (List<MsCategoriasBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsCategoriasBk> msCategoriasBkssss = servicio.getAllMsCategoriasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msCategoriasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msCategoriasBksss = servicio.getAllMsCategoriasActivosCero(kyUsuarioMod);
			entrada.setLista(msCategoriasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msCategoriasBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsCategoriasBk msCategoriasC){		
		List<MsCategoriasBk> msCategoriasBksss = null;
		String key = MsCategoriasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msCategoriasBksss = (List<MsCategoriasBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsCategoriasBk> msCategoriasBkssss = servicio.getAllMsCategoriasActivosCero(kyUsuarioMod);
		    				entrada.setLista(msCategoriasBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msCategoriasBksss = servicio.getAllMsCategoriasActivosCero(kyUsuarioMod);
			entrada.setLista(msCategoriasBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msCategoriasBksss.contains(msCategoriasC)) {
			msCategoriasBksss.add(msCategoriasC);
		} else {
			int itemIndex = msCategoriasBksss.indexOf(msCategoriasC);
			if (itemIndex != -1) {
				msCategoriasBksss.set(itemIndex, msCategoriasC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsCategoriasBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsCategoriasBk> msCategoriasBkssss = servicio.getAllMsCategoriasActivosCero(kyUsuarioMod);
    				entrada.setLista(msCategoriasBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
