/**
 * 
 */
package pe.gob.mef.sisevent.bs.cache.clases;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.gob.mef.sisevent.bs.cache.KittyCache;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;


/**
 * @author cafach
 *
 */
public class CacheMsUsuariosBk extends KittyCache<Long, MsUsuariosBk> {

	private static final Log log = LogFactory.getLog(CacheMsUsuariosBk.class);

	private transient Servicio servicio = null;
	
	
	public CacheMsUsuariosBk(int maxSize, Servicio servicio) {
		super(maxSize);
		log.info("CREANDO MsUsuariosBkCache " + maxSize);
		this.servicio = servicio;
	}

	@Override
	public String getClase() {
		return MsUsuariosBk.class.getSimpleName();
	}

	@Override
	public MsUsuariosBk getEntidad(Long key) {
		// TODO Auto-generated method stub
		return servicio.getMsUsuariosBkXid(key, null);
	}
}
