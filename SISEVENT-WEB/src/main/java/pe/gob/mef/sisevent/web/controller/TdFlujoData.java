package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdFlujoData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6700012254847008946L;

	private static final Logger log = Logger.getLogger(TdFlujoData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdFlujoData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdFlujoBk> getTdFlujoActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdFlujoBk> tdFlujoBksss = null;
		String key = TdFlujoBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdFlujoBksss = (List<TdFlujoBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdFlujoBk> tdFlujoBkssss = servicio.getAllTdFlujoActivos();
		    				entrada.setLista(tdFlujoBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());		                    
		                } 
		            }		 
		        }.start();
			}
		}else{
			Entrada entrada = new Entrada();
			tdFlujoBksss = servicio.getAllTdFlujoActivos();
			entrada.setLista(tdFlujoBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdFlujoBksss;		
	}
	
}
