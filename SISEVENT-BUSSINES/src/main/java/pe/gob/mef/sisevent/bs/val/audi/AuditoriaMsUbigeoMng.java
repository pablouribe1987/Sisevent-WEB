package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsUbigeo;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUbigeoBk;

/**
 * MS_UBIGEO SERVICIO AUDITORIA Y CAMBIO: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /23/12/2020 11:52 / Creación de la clase /
 * 
 */
public class AuditoriaMsUbigeoMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4336584531952245210L;
	public static final Logger log = Logger.getLogger(AuditoriaMsUbigeoMng.class.getName());

	public static boolean auditarCambiosMsUbigeo(MsUbigeoBk msUbigeoBk, MsUbigeo msUbigeo, Long iduser, String user,
			String rmtaddress, int nivel) {
		boolean cambios = false;

		if (msUbigeoBk.getNombre() != null && msUbigeo.getNombre() != null) {
			if (!msUbigeoBk.getNombre().equals(msUbigeo.getNombre())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msUbigeo:Nombre"
									+ " :: " + msUbigeoBk.getId().toString() + " :: " + msUbigeo.getNombre() + " :: "
									+ msUbigeoBk.getNombre());
				}
				cambios = true;
				msUbigeo.setNombre(msUbigeoBk.getNombre());
			}
		} else if (msUbigeoBk.getNombre() == null && msUbigeo.getNombre() != null) {
			if (msUbigeo.getNombre().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msUbigeo:Nombre"
									+ " :: " + msUbigeoBk.getId().toString() + " :: " + msUbigeo.getNombre() + " :: "
									+ msUbigeoBk.getNombre());
				}
				cambios = true;
				msUbigeo.setNombre(msUbigeoBk.getNombre());
			}
		} else if (msUbigeoBk.getNombre() != null && msUbigeo.getNombre() == null) {
			if (msUbigeoBk.getNombre().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msUbigeo:Nombre"
									+ " :: " + msUbigeoBk.getId().toString() + " :: " + msUbigeo.getNombre() + " :: "
									+ msUbigeoBk.getNombre());
				}
				cambios = true;
				msUbigeo.setNombre(msUbigeoBk.getNombre());
			}
		}

		return cambios;
	}

}