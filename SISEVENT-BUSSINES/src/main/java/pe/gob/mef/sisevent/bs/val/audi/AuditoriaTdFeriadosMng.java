package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdFeriados;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFeriadosBk;

/**
 * TD_FERIADOS SERVICIO AUDITORIA Y CAMBIO: DÍAS NO LABORABLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2021 02:31
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /11/01/2021 02:31 / Creación de la clase /
 * 
 */
public class AuditoriaTdFeriadosMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5668158232726405239L;
	public static final Logger log = Logger.getLogger(AuditoriaTdFeriadosMng.class.getName());

	public static boolean auditarCambiosTdFeriados(TdFeriadosBk tdFeriadosBk, TdFeriados tdFeriados, Long iduser,
			String user, String rmtaddress, int nivel) {
		boolean cambios = false;

		if (tdFeriadosBk.getFechaFeriado() != null && tdFeriados.getFechaFeriado() != null) {
			if (!tdFeriadosBk.getFechaFeriado().equals(tdFeriados.getFechaFeriado())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFeriados:FechaFeriado" + " :: " + tdFeriadosBk.getIdferiado().toString()
									+ " :: " + tdFeriados.getFechaFeriado() + " :: " + tdFeriadosBk.getFechaFeriado());
				}
				cambios = true;
				tdFeriados.setFechaFeriado(tdFeriadosBk.getFechaFeriado());
			}
		} else if (tdFeriadosBk.getFechaFeriado() == null && tdFeriados.getFechaFeriado() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFeriados:FechaFeriado"
								+ " :: " + tdFeriadosBk.getIdferiado().toString() + " :: "
								+ tdFeriados.getFechaFeriado() + " :: " + tdFeriadosBk.getFechaFeriado());
			}
			cambios = true;
			tdFeriados.setFechaFeriado(tdFeriadosBk.getFechaFeriado());

		} else if (tdFeriadosBk.getFechaFeriado() != null && tdFeriados.getFechaFeriado() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFeriados:FechaFeriado"
								+ " :: " + tdFeriadosBk.getIdferiado().toString() + " :: "
								+ tdFeriados.getFechaFeriado() + " :: " + tdFeriadosBk.getFechaFeriado());
			}
			cambios = true;
			tdFeriados.setFechaFeriado(tdFeriadosBk.getFechaFeriado());
		}
		if (tdFeriadosBk.getDescricion() != null && tdFeriados.getDescricion() != null) {
			if (!tdFeriadosBk.getDescricion().equals(tdFeriados.getDescricion())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFeriados:Descricion" + " :: " + tdFeriadosBk.getIdferiado().toString() + " :: "
									+ tdFeriados.getDescricion() + " :: " + tdFeriadosBk.getDescricion());
				}
				cambios = true;
				tdFeriados.setDescricion(tdFeriadosBk.getDescricion());
			}
		} else if (tdFeriadosBk.getDescricion() == null && tdFeriados.getDescricion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFeriados:Descricion"
								+ " :: " + tdFeriadosBk.getIdferiado().toString() + " :: " + tdFeriados.getDescricion()
								+ " :: " + tdFeriadosBk.getDescricion());
			}
			cambios = true;
			tdFeriados.setDescricion(tdFeriadosBk.getDescricion());

		} else if (tdFeriadosBk.getDescricion() != null && tdFeriados.getDescricion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFeriados:Descricion"
								+ " :: " + tdFeriadosBk.getIdferiado().toString() + " :: " + tdFeriados.getDescricion()
								+ " :: " + tdFeriadosBk.getDescricion());
			}
			cambios = true;
			tdFeriados.setDescricion(tdFeriadosBk.getDescricion());
		}

		return cambios;
	}

}