package pe.gob.mef.sisevent.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.PrtParametrosBk;
import pe.gob.mef.sisevent.web.utils.Entrada;

public class PrtParametrosData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 415901462492305906L;

	private static final Logger log = Logger.getLogger(PrtParametrosData.class.getName());

	private Map<String, Entrada> dataCache = new HashMap<String, Entrada>();

	public PrtParametrosData() {
	}

	@SuppressWarnings("unchecked")
	public List<PrtParametrosBk> getPrtParametrosActivos(Servicio servicio, Long kyUsuarioMod, String keyt) {
		List<PrtParametrosBk> prtParametrosBksss = null;
		String key = PrtParametrosBk.class.getSimpleName()+(keyt==null?"":keyt);
		if (dataCache.containsKey(key)) {
			Entrada entrada = dataCache.get(key);
			prtParametrosBksss = (List<PrtParametrosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if ((entrada.getUltimoacceso() + entrada.getTiempomuerto()) < System.currentTimeMillis()) {
				new Thread() {
					public void run() {
						try {
							List<PrtParametrosBk> prtParametrosBkssss = servicio
									.getAllPrtParametrosActivosCero(kyUsuarioMod,keyt);
							entrada.setLista(prtParametrosBkssss);
							entrada.setUltimoacceso(System.currentTimeMillis());
						} catch (Exception ex) {
							log.info(ex.getMessage());

						}
					}
				}.start();
			}
		} else {
			Entrada entrada = new Entrada();
			prtParametrosBksss = servicio.getAllPrtParametrosActivosCero(kyUsuarioMod,keyt);
			entrada.setLista(prtParametrosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		return prtParametrosBksss;
	}

	@SuppressWarnings("unchecked")
	public void add(Servicio servicio, PrtParametrosBk prtParametrosBk, Long kyUsuarioMod, String keyt) {
		List<PrtParametrosBk> prtParametrosBksss = null;
		String key = PrtParametrosBk.class.getSimpleName()+(keyt==null?"":keyt);
		if (dataCache.containsKey(key)) {
			Entrada entrada = dataCache.get(key);
			prtParametrosBksss = (List<PrtParametrosBk>) entrada.getLista();
			entrada.setUltimoacceso(System.currentTimeMillis());
			if ((entrada.getUltimoacceso() + entrada.getTiempomuerto()) < System.currentTimeMillis()) {
				new Thread() {
					public void run() {
						try {
							List<PrtParametrosBk> prtParametrosBkssss = servicio
									.getAllPrtParametrosActivosCero(kyUsuarioMod,keyt);
							entrada.setLista(prtParametrosBkssss);
							entrada.setUltimoacceso(System.currentTimeMillis());
						} catch (Exception ex) {
							log.info(ex.getMessage());
						}
					}
				}.start();
			}
		} else {
			Entrada entrada = new Entrada();
			prtParametrosBksss = servicio.getAllPrtParametrosActivosCero(kyUsuarioMod,keyt);
			entrada.setLista(prtParametrosBksss);
			entrada.setTiempomuerto(60000);
			entrada.setUltimoacceso(System.currentTimeMillis());
			dataCache.put(key, entrada);
		}
		if (!prtParametrosBksss.contains(prtParametrosBk)) {
			prtParametrosBksss.add(prtParametrosBk);
		} else {
			int itemIndex = prtParametrosBksss.indexOf(prtParametrosBk);
			if (itemIndex != -1) {
				prtParametrosBksss.set(itemIndex, prtParametrosBk);
			}
		}

	}

	public void refrescar(Servicio servicio, Long kyUsuarioMod,String keyt){
		String key = PrtParametrosBk.class.getSimpleName()+(keyt==null?"":keyt);
		if(dataCache.containsKey(key)){
			Entrada entrada = dataCache.get(key);
		new Thread() {
            public void run() {
                try {
                	List<PrtParametrosBk> prtParametrosBkssss = servicio.getAllPrtParametrosActivosCero(kyUsuarioMod,keyt);
    				entrada.setLista(prtParametrosBkssss);
    				entrada.setUltimoacceso(System.currentTimeMillis());
                } catch (Exception ex) {
                	log.info(ex.getMessage());
                    
                } 
            }		 
        }.start();
		}
	}

}
