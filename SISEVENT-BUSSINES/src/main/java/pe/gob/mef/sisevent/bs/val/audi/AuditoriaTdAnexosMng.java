package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdAnexos;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;

/**
 * TD_ANEXOS SERVICIO AUDITORIA Y CAMBIO: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 07/01/2021 06:00
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /07/01/2021 06:00 / Creación de la clase /
 * 
 */
public class AuditoriaTdAnexosMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3217197517864947092L;
	public static final Logger log = Logger.getLogger(AuditoriaTdAnexosMng.class.getName());

	public static boolean auditarCambiosTdAnexos(TdAnexosBk tdAnexosBk, TdAnexos tdAnexos, Long iduser, String user,
			String rmtaddress, int nivel) {
		boolean cambios = false;

		if (tdAnexosBk.getIdsacc() != null && tdAnexos.getIdsacc() != null) {
			if (!tdAnexosBk.getIdsacc().equals(tdAnexos.getIdsacc())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idsacc"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdsacc()
									+ " :: " + tdAnexosBk.getIdsacc());
				}
				cambios = true;
				tdAnexos.setIdsacc(tdAnexosBk.getIdsacc());
			}
		} else if (tdAnexosBk.getIdsacc() == null && tdAnexos.getIdsacc() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idsacc"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdsacc() + " :: "
								+ tdAnexosBk.getIdsacc());
			}
			cambios = true;
			tdAnexos.setIdsacc(tdAnexosBk.getIdsacc());

		} else if (tdAnexosBk.getIdsacc() != null && tdAnexos.getIdsacc() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idsacc"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdsacc() + " :: "
								+ tdAnexosBk.getIdsacc());
			}
			cambios = true;
			tdAnexos.setIdsacc(tdAnexosBk.getIdsacc());
		}

		if (tdAnexosBk.getFilename() != null && tdAnexos.getFilename() != null) {
			if (!tdAnexosBk.getFilename().equals(tdAnexos.getFilename())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Filename"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getFilename()
									+ " :: " + tdAnexosBk.getFilename());
				}
				cambios = true;
				tdAnexos.setFilename(tdAnexosBk.getFilename());
			}
		} else if (tdAnexosBk.getFilename() == null && tdAnexos.getFilename() != null) {
			if (tdAnexos.getFilename().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Filename"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getFilename()
									+ " :: " + tdAnexosBk.getFilename());
				}
				cambios = true;
				tdAnexos.setFilename(tdAnexosBk.getFilename());
			}
		} else if (tdAnexosBk.getFilename() != null && tdAnexos.getFilename() == null) {
			if (tdAnexosBk.getFilename().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Filename"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getFilename()
									+ " :: " + tdAnexosBk.getFilename());
				}
				cambios = true;
				tdAnexos.setFilename(tdAnexosBk.getFilename());
			}
		}

		if (tdAnexosBk.getFilenameoriginal() != null && tdAnexos.getFilenameoriginal() != null) {
			if (!tdAnexosBk.getFilenameoriginal().equals(tdAnexos.getFilenameoriginal())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdAnexos:Filenameoriginal" + " :: " + tdAnexosBk.getIdanexo().toString() + " :: "
									+ tdAnexos.getFilenameoriginal() + " :: " + tdAnexosBk.getFilenameoriginal());
				}
				cambios = true;
				tdAnexos.setFilenameoriginal(tdAnexosBk.getFilenameoriginal());
			}
		} else if (tdAnexosBk.getFilenameoriginal() == null && tdAnexos.getFilenameoriginal() != null) {
			if (tdAnexos.getFilenameoriginal().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdAnexos:Filenameoriginal" + " :: " + tdAnexosBk.getIdanexo().toString() + " :: "
									+ tdAnexos.getFilenameoriginal() + " :: " + tdAnexosBk.getFilenameoriginal());
				}
				cambios = true;
				tdAnexos.setFilenameoriginal(tdAnexosBk.getFilenameoriginal());
			}
		} else if (tdAnexosBk.getFilenameoriginal() != null && tdAnexos.getFilenameoriginal() == null) {
			if (tdAnexosBk.getFilenameoriginal().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdAnexos:Filenameoriginal" + " :: " + tdAnexosBk.getIdanexo().toString() + " :: "
									+ tdAnexos.getFilenameoriginal() + " :: " + tdAnexosBk.getFilenameoriginal());
				}
				cambios = true;
				tdAnexos.setFilenameoriginal(tdAnexosBk.getFilenameoriginal());
			}
		}
		if (tdAnexosBk.getLastmodified() != null && tdAnexos.getLastmodified() != null) {
			if (!tdAnexosBk.getLastmodified().equals(tdAnexos.getLastmodified())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdAnexos:Lastmodified" + " :: " + tdAnexosBk.getIdanexo().toString() + " :: "
									+ tdAnexos.getLastmodified() + " :: " + tdAnexosBk.getLastmodified());
				}
				cambios = true;
				tdAnexos.setLastmodified(tdAnexosBk.getLastmodified());
			}
		} else if (tdAnexosBk.getLastmodified() == null && tdAnexos.getLastmodified() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Lastmodified"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getLastmodified()
								+ " :: " + tdAnexosBk.getLastmodified());
			}
			cambios = true;
			tdAnexos.setLastmodified(tdAnexosBk.getLastmodified());

		} else if (tdAnexosBk.getLastmodified() != null && tdAnexos.getLastmodified() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Lastmodified"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getLastmodified()
								+ " :: " + tdAnexosBk.getLastmodified());
			}
			cambios = true;
			tdAnexos.setLastmodified(tdAnexosBk.getLastmodified());
		}
		if (tdAnexosBk.getTamanio() != null && tdAnexos.getTamanio() != null) {
			if (!tdAnexosBk.getTamanio().equals(tdAnexos.getTamanio())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tamanio"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTamanio()
									+ " :: " + tdAnexosBk.getTamanio());
				}
				cambios = true;
				tdAnexos.setTamanio(tdAnexosBk.getTamanio());
			}
		} else if (tdAnexosBk.getTamanio() == null && tdAnexos.getTamanio() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tamanio"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTamanio() + " :: "
								+ tdAnexosBk.getTamanio());
			}
			cambios = true;
			tdAnexos.setTamanio(tdAnexosBk.getTamanio());

		} else if (tdAnexosBk.getTamanio() != null && tdAnexos.getTamanio() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tamanio"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTamanio() + " :: "
								+ tdAnexosBk.getTamanio());
			}
			cambios = true;
			tdAnexos.setTamanio(tdAnexosBk.getTamanio());
		}

		if (tdAnexosBk.getTipo() != null && tdAnexos.getTipo() != null) {
			if (!tdAnexosBk.getTipo().equals(tdAnexos.getTipo())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tipo"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTipo() + " :: "
									+ tdAnexosBk.getTipo());
				}
				cambios = true;
				tdAnexos.setTipo(tdAnexosBk.getTipo());
			}
		} else if (tdAnexosBk.getTipo() == null && tdAnexos.getTipo() != null) {
			if (tdAnexos.getTipo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tipo"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTipo() + " :: "
									+ tdAnexosBk.getTipo());
				}
				cambios = true;
				tdAnexos.setTipo(tdAnexosBk.getTipo());
			}
		} else if (tdAnexosBk.getTipo() != null && tdAnexos.getTipo() == null) {
			if (tdAnexosBk.getTipo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Tipo"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getTipo() + " :: "
									+ tdAnexosBk.getTipo());
				}
				cambios = true;
				tdAnexos.setTipo(tdAnexosBk.getTipo());
			}
		}
		if (tdAnexosBk.getIdflujo() != null && tdAnexos.getIdflujo() != null) {
			if (!tdAnexosBk.getIdflujo().equals(tdAnexos.getIdflujo())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idflujo"
									+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdflujo()
									+ " :: " + tdAnexosBk.getIdflujo());
				}
				cambios = true;
				tdAnexos.setIdflujo(tdAnexosBk.getIdflujo());
			}
		} else if (tdAnexosBk.getIdflujo() == null && tdAnexos.getIdflujo() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idflujo"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdflujo() + " :: "
								+ tdAnexosBk.getIdflujo());
			}
			cambios = true;
			tdAnexos.setIdflujo(tdAnexosBk.getIdflujo());

		} else if (tdAnexosBk.getIdflujo() != null && tdAnexos.getIdflujo() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdAnexos:Idflujo"
								+ " :: " + tdAnexosBk.getIdanexo().toString() + " :: " + tdAnexos.getIdflujo() + " :: "
								+ tdAnexosBk.getIdflujo());
			}
			cambios = true;
			tdAnexos.setIdflujo(tdAnexosBk.getIdflujo());
		}

		return cambios;
	}

}