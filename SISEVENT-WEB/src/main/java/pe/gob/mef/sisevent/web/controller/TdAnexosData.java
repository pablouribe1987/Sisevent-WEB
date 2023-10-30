package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdAnexosData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdAnexosData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdAnexosData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdAnexosBk> getTdAnexosActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdAnexosBk> tdAnexosBksss = null;
		String key = TdAnexosBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdAnexosBksss = (List<TdAnexosBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdAnexosBk> tdAnexosBkssss = servicio.getAllTdAnexosActivos();
		    				entrada.setLista(tdAnexosBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdAnexosBksss = servicio.getAllTdAnexosActivos();
			entrada.setLista(tdAnexosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdAnexosBksss;		
	}
	
}
