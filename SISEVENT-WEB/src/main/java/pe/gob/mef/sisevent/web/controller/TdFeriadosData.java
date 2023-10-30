package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFeriadosBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdFeriadosData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5542870311174170642L;

	private static final Logger log = Logger.getLogger(TdFeriadosData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdFeriadosData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdFeriadosBk> getTdFeriadosActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdFeriadosBk> tdFeriadosBksss = null;
		String key = TdFeriadosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdFeriadosBksss = (List<TdFeriadosBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdFeriadosBk> tdFeriadosBkssss = servicio.getAllTdFeriadosActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdFeriadosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdFeriadosBksss = servicio.getAllTdFeriadosActivosCero(kyUsuarioMod);
			entrada.setLista(tdFeriadosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdFeriadosBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdFeriadosBk tdFeriadosC){		
		List<TdFeriadosBk> tdFeriadosBksss = null;
		String key = TdFeriadosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdFeriadosBksss = (List<TdFeriadosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdFeriadosBk> tdFeriadosBkssss = servicio.getAllTdFeriadosActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdFeriadosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdFeriadosBksss = servicio.getAllTdFeriadosActivosCero(kyUsuarioMod);
			entrada.setLista(tdFeriadosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdFeriadosBksss.contains(tdFeriadosC)) {
			tdFeriadosBksss.add(tdFeriadosC);
		} else {
			int itemIndex = tdFeriadosBksss.indexOf(tdFeriadosC);
			if (itemIndex != -1) {
				tdFeriadosBksss.set(itemIndex, tdFeriadosC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdFeriadosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdFeriadosBk> tdFeriadosBkssss = servicio.getAllTdFeriadosActivosCero(kyUsuarioMod);
    				entrada.setLista(tdFeriadosBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
