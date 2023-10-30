package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.TdParticipantesBk;
import pe.gob.mef.sisevent.web.utils.Entrada;


public class TdParticipantesData implements Serializable{
	
	private static final Logger log = Logger.getLogger(TdParticipantesData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String,Entrada>();

	public TdParticipantesData() {
	}

	@SuppressWarnings("unchecked")
	public List<TdParticipantesBk> getTdParticipantesActivos(Servicio servicio, Long kyUsuarioMod){		
		List<TdParticipantesBk> tdParticipantesBksss = null;
		String key = TdParticipantesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdParticipantesBksss = (List<TdParticipantesBk>) entrada.getLista();
			long ultimoacceso = entrada.getUltimoacceso();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((ultimoacceso+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdParticipantesBk> tdParticipantesBkssss = servicio.getAllTdParticipantesActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdParticipantesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdParticipantesBksss = servicio.getAllTdParticipantesActivosCero(kyUsuarioMod);
			entrada.setLista(tdParticipantesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return tdParticipantesBksss;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, Long kyUsuarioMod, TdParticipantesBk tdParticipantesC){		
		List<TdParticipantesBk> tdParticipantesBksss = null;
		String key = TdParticipantesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
			tdParticipantesBksss = (List<TdParticipantesBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if((entrada.getUltimoacceso()+entrada.getTiempomuerto())<System.currentTimeMillis()){
				new Thread() {
		            public void run() {
		                try {
		                	List<TdParticipantesBk> tdParticipantesBkssss = servicio.getAllTdParticipantesActivosCero(kyUsuarioMod);
		    				entrada.setLista(tdParticipantesBkssss);
		    				entrada.setUltimoacceso(System.currentTimeMillis());
		                } catch (Exception ex) {
		                	log.info(ex.getMessage());
		                    
		                } 
		            }		 
		        }.start();				
			}
		}else{
			Entrada entrada = new Entrada();
			tdParticipantesBksss = servicio.getAllTdParticipantesActivosCero(kyUsuarioMod);
			entrada.setLista(tdParticipantesBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!tdParticipantesBksss.contains(tdParticipantesC)) {
			tdParticipantesBksss.add(tdParticipantesC);
		} else {
			int itemIndex = tdParticipantesBksss.indexOf(tdParticipantesC);
			if (itemIndex != -1) {
				tdParticipantesBksss.set(itemIndex, tdParticipantesC);
			}
		}		
	}
	
	public void refrescar(Servicio servicio, Long kyUsuarioMod){
		String key = TdParticipantesBk.class.getSimpleName();
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<TdParticipantesBk> tdParticipantesBkssss = servicio.getAllTdParticipantesActivosCero(kyUsuarioMod);
    				entrada.setLista(tdParticipantesBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}
}
