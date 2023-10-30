package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPaisesBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class MsPaisesData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8706159293802106745L;

	private static final Logger log = Logger.getLogger(MsPaisesData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public MsPaisesData() {
	}

	@SuppressWarnings("unchecked")
	public List<MsPaisesBk> getMsPaisesActivos(Servicio servicio, Long kyUsuarioMod){		
		List<MsPaisesBk> msPaisesBksss = null;
		String key = MsPaisesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msPaisesBksss = (List<MsPaisesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsPaisesBk> msPaisesBkssss = servicio.getAllMsPaisesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msPaisesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msPaisesBksss = servicio.getAllMsPaisesActivosCero(kyUsuarioMod);
			entrada.setLista(msPaisesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return msPaisesBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, MsPaisesBk msPaisesC){		
		List<MsPaisesBk> msPaisesBksss = null;
		String key = MsPaisesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			msPaisesBksss = (List<MsPaisesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<MsPaisesBk> msPaisesBkssss = servicio.getAllMsPaisesActivosCero(kyUsuarioMod);
		    				entrada.setLista(msPaisesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			msPaisesBksss = servicio.getAllMsPaisesActivosCero(kyUsuarioMod);
			entrada.setLista(msPaisesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!msPaisesBksss.contains(msPaisesC)) {
			msPaisesBksss.add(msPaisesC);
		} else {
			int itemIndex = msPaisesBksss.indexOf(msPaisesC);
			if (itemIndex != -1) {
				msPaisesBksss.set(itemIndex, msPaisesC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = MsPaisesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<MsPaisesBk> msPaisesBkssss = servicio.getAllMsPaisesActivosCero(kyUsuarioMod);
    				entrada.setLista(msPaisesBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
