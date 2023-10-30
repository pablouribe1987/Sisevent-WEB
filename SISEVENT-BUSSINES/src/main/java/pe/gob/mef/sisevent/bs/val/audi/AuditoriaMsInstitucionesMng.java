package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsInstituciones;
import pe.gob.mef.sisevent.bs.transfer.bk.MsInstitucionesBk;

/**
 * MS_INSTITUCIONES SERVICIO AUDITORIA Y CAMBIO: INSTITUCIONES Y ENTIDADES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class AuditoriaMsInstitucionesMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1179392492056427171L;
	public static final Logger log = Logger.getLogger(AuditoriaMsInstitucionesMng.class.getName());

	public static boolean auditarCambiosMsInstituciones(MsInstitucionesBk msInstitucionesBk,
			MsInstituciones msInstituciones, Long iduser, String user, String rmtaddress, int nivel) {
		boolean cambios = false;

		if (msInstitucionesBk.getRazonSocial() != null && msInstituciones.getRazonSocial() != null) {
			if (!msInstitucionesBk.getRazonSocial().equals(msInstituciones.getRazonSocial())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:RazonSocial" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getRazonSocial() + " :: " + msInstitucionesBk.getRazonSocial());
				}
				cambios = true;
				msInstituciones.setRazonSocial(msInstitucionesBk.getRazonSocial());
			}
		} else if (msInstitucionesBk.getRazonSocial() == null && msInstituciones.getRazonSocial() != null) {
			if (msInstituciones.getRazonSocial().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:RazonSocial" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getRazonSocial() + " :: " + msInstitucionesBk.getRazonSocial());
				}
				cambios = true;
				msInstituciones.setRazonSocial(msInstitucionesBk.getRazonSocial());
			}
		} else if (msInstitucionesBk.getRazonSocial() != null && msInstituciones.getRazonSocial() == null) {
			if (msInstitucionesBk.getRazonSocial().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:RazonSocial" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getRazonSocial() + " :: " + msInstitucionesBk.getRazonSocial());
				}
				cambios = true;
				msInstituciones.setRazonSocial(msInstitucionesBk.getRazonSocial());
			}
		}

		if (msInstitucionesBk.getSiglas() != null && msInstituciones.getSiglas() != null) {
			if (!msInstitucionesBk.getSiglas().equals(msInstituciones.getSiglas())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Siglas" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getSiglas() + " :: " + msInstitucionesBk.getSiglas());
				}
				cambios = true;
				msInstituciones.setSiglas(msInstitucionesBk.getSiglas());
			}
		} else if (msInstitucionesBk.getSiglas() == null && msInstituciones.getSiglas() != null) {
			if (msInstituciones.getSiglas().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Siglas" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getSiglas() + " :: " + msInstitucionesBk.getSiglas());
				}
				cambios = true;
				msInstituciones.setSiglas(msInstitucionesBk.getSiglas());
			}
		} else if (msInstitucionesBk.getSiglas() != null && msInstituciones.getSiglas() == null) {
			if (msInstitucionesBk.getSiglas().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Siglas" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getSiglas() + " :: " + msInstitucionesBk.getSiglas());
				}
				cambios = true;
				msInstituciones.setSiglas(msInstitucionesBk.getSiglas());
			}
		}
		if (msInstitucionesBk.getRuc() != null && msInstituciones.getRuc() != null) {
			if (!msInstitucionesBk.getRuc().equals(msInstituciones.getRuc())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Ruc"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getRuc() + " :: " + msInstitucionesBk.getRuc());
				}
				cambios = true;
				msInstituciones.setRuc(msInstitucionesBk.getRuc());
			}
		} else if (msInstitucionesBk.getRuc() == null && msInstituciones.getRuc() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Ruc"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getRuc() + " :: " + msInstitucionesBk.getRuc());
			}
			cambios = true;
			msInstituciones.setRuc(msInstitucionesBk.getRuc());

		} else if (msInstitucionesBk.getRuc() != null && msInstituciones.getRuc() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Ruc"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getRuc() + " :: " + msInstitucionesBk.getRuc());
			}
			cambios = true;
			msInstituciones.setRuc(msInstitucionesBk.getRuc());
		}

		if (msInstitucionesBk.getCorreo() != null && msInstituciones.getCorreo() != null) {
			if (!msInstitucionesBk.getCorreo().equals(msInstituciones.getCorreo())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Correo" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCorreo() + " :: " + msInstitucionesBk.getCorreo());
				}
				cambios = true;
				msInstituciones.setCorreo(msInstitucionesBk.getCorreo());
			}
		} else if (msInstitucionesBk.getCorreo() == null && msInstituciones.getCorreo() != null) {
			if (msInstituciones.getCorreo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Correo" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCorreo() + " :: " + msInstitucionesBk.getCorreo());
				}
				cambios = true;
				msInstituciones.setCorreo(msInstitucionesBk.getCorreo());
			}
		} else if (msInstitucionesBk.getCorreo() != null && msInstituciones.getCorreo() == null) {
			if (msInstitucionesBk.getCorreo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Correo" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCorreo() + " :: " + msInstitucionesBk.getCorreo());
				}
				cambios = true;
				msInstituciones.setCorreo(msInstitucionesBk.getCorreo());
			}
		}

		if (msInstitucionesBk.getWeb() != null && msInstituciones.getWeb() != null) {
			if (!msInstitucionesBk.getWeb().equals(msInstituciones.getWeb())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Web"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getWeb() + " :: " + msInstitucionesBk.getWeb());
				}
				cambios = true;
				msInstituciones.setWeb(msInstitucionesBk.getWeb());
			}
		} else if (msInstitucionesBk.getWeb() == null && msInstituciones.getWeb() != null) {
			if (msInstituciones.getWeb().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Web"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getWeb() + " :: " + msInstitucionesBk.getWeb());
				}
				cambios = true;
				msInstituciones.setWeb(msInstitucionesBk.getWeb());
			}
		} else if (msInstitucionesBk.getWeb() != null && msInstituciones.getWeb() == null) {
			if (msInstitucionesBk.getWeb().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Web"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getWeb() + " :: " + msInstitucionesBk.getWeb());
				}
				cambios = true;
				msInstituciones.setWeb(msInstitucionesBk.getWeb());
			}
		}

		if (msInstitucionesBk.getTelefono() != null && msInstituciones.getTelefono() != null) {
			if (!msInstitucionesBk.getTelefono().equals(msInstituciones.getTelefono())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Telefono" + " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
							+ msInstituciones.getTelefono() + " :: " + msInstitucionesBk.getTelefono());
				}
				cambios = true;
				msInstituciones.setTelefono(msInstitucionesBk.getTelefono());
			}
		} else if (msInstitucionesBk.getTelefono() == null && msInstituciones.getTelefono() != null) {
			if (msInstituciones.getTelefono().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Telefono" + " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
							+ msInstituciones.getTelefono() + " :: " + msInstitucionesBk.getTelefono());
				}
				cambios = true;
				msInstituciones.setTelefono(msInstitucionesBk.getTelefono());
			}
		} else if (msInstitucionesBk.getTelefono() != null && msInstituciones.getTelefono() == null) {
			if (msInstitucionesBk.getTelefono().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Telefono" + " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
							+ msInstituciones.getTelefono() + " :: " + msInstitucionesBk.getTelefono());
				}
				cambios = true;
				msInstituciones.setTelefono(msInstitucionesBk.getTelefono());
			}
		}

		if (msInstitucionesBk.getFax() != null && msInstituciones.getFax() != null) {
			if (!msInstitucionesBk.getFax().equals(msInstituciones.getFax())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Fax"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getFax() + " :: " + msInstitucionesBk.getFax());
				}
				cambios = true;
				msInstituciones.setFax(msInstitucionesBk.getFax());
			}
		} else if (msInstitucionesBk.getFax() == null && msInstituciones.getFax() != null) {
			if (msInstituciones.getFax().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Fax"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getFax() + " :: " + msInstitucionesBk.getFax());
				}
				cambios = true;
				msInstituciones.setFax(msInstitucionesBk.getFax());
			}
		} else if (msInstitucionesBk.getFax() != null && msInstituciones.getFax() == null) {
			if (msInstitucionesBk.getFax().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Fax"
									+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
									+ msInstituciones.getFax() + " :: " + msInstitucionesBk.getFax());
				}
				cambios = true;
				msInstituciones.setFax(msInstitucionesBk.getFax());
			}
		}
		if (msInstitucionesBk.getCodpais() != null && msInstituciones.getCodpais() != null) {
			if (!msInstitucionesBk.getCodpais().equals(msInstituciones.getCodpais())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Codpais" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCodpais() + " :: " + msInstitucionesBk.getCodpais());
				}
				cambios = true;
				msInstituciones.setCodpais(msInstitucionesBk.getCodpais());
			}
		} else if (msInstitucionesBk.getCodpais() == null && msInstituciones.getCodpais() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Codpais"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCodpais() + " :: " + msInstitucionesBk.getCodpais());
			}
			cambios = true;
			msInstituciones.setCodpais(msInstitucionesBk.getCodpais());

		} else if (msInstitucionesBk.getCodpais() != null && msInstituciones.getCodpais() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Codpais"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCodpais() + " :: " + msInstitucionesBk.getCodpais());
			}
			cambios = true;
			msInstituciones.setCodpais(msInstitucionesBk.getCodpais());
		}
		if (msInstitucionesBk.getCoddpto() != null && msInstituciones.getCoddpto() != null) {
			if (!msInstitucionesBk.getCoddpto().equals(msInstituciones.getCoddpto())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Coddpto" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCoddpto() + " :: " + msInstitucionesBk.getCoddpto());
				}
				cambios = true;
				msInstituciones.setCoddpto(msInstitucionesBk.getCoddpto());
			}
		} else if (msInstitucionesBk.getCoddpto() == null && msInstituciones.getCoddpto() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Coddpto"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCoddpto() + " :: " + msInstitucionesBk.getCoddpto());
			}
			cambios = true;
			msInstituciones.setCoddpto(msInstitucionesBk.getCoddpto());

		} else if (msInstitucionesBk.getCoddpto() != null && msInstituciones.getCoddpto() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Coddpto"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCoddpto() + " :: " + msInstitucionesBk.getCoddpto());
			}
			cambios = true;
			msInstituciones.setCoddpto(msInstitucionesBk.getCoddpto());
		}
		if (msInstitucionesBk.getCodprov() != null && msInstituciones.getCodprov() != null) {
			if (!msInstitucionesBk.getCodprov().equals(msInstituciones.getCodprov())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Codprov" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCodprov() + " :: " + msInstitucionesBk.getCodprov());
				}
				cambios = true;
				msInstituciones.setCodprov(msInstitucionesBk.getCodprov());
			}
		} else if (msInstitucionesBk.getCodprov() == null && msInstituciones.getCodprov() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Codprov"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCodprov() + " :: " + msInstitucionesBk.getCodprov());
			}
			cambios = true;
			msInstituciones.setCodprov(msInstitucionesBk.getCodprov());

		} else if (msInstitucionesBk.getCodprov() != null && msInstituciones.getCodprov() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Codprov"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCodprov() + " :: " + msInstitucionesBk.getCodprov());
			}
			cambios = true;
			msInstituciones.setCodprov(msInstitucionesBk.getCodprov());
		}
		if (msInstitucionesBk.getCoddist() != null && msInstituciones.getCoddist() != null) {
			if (!msInstitucionesBk.getCoddist().equals(msInstituciones.getCoddist())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "msInstituciones:Coddist" + " :: " + msInstitucionesBk.getIdprovee().toString()
									+ " :: " + msInstituciones.getCoddist() + " :: " + msInstitucionesBk.getCoddist());
				}
				cambios = true;
				msInstituciones.setCoddist(msInstitucionesBk.getCoddist());
			}
		} else if (msInstitucionesBk.getCoddist() == null && msInstituciones.getCoddist() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Coddist"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCoddist() + " :: " + msInstitucionesBk.getCoddist());
			}
			cambios = true;
			msInstituciones.setCoddist(msInstitucionesBk.getCoddist());

		} else if (msInstitucionesBk.getCoddist() != null && msInstituciones.getCoddist() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "msInstituciones:Coddist"
								+ " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
								+ msInstituciones.getCoddist() + " :: " + msInstitucionesBk.getCoddist());
			}
			cambios = true;
			msInstituciones.setCoddist(msInstitucionesBk.getCoddist());
		}
		if (msInstitucionesBk.getDireccion() != null && msInstituciones.getDireccion() != null) {
			if (!msInstitucionesBk.getDireccion().equals(msInstituciones.getDireccion())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Direccion" + " :: " + msInstitucionesBk.getIdprovee().toString() + " :: "
							+ msInstituciones.getDireccion() + " :: " + msInstitucionesBk.getDireccion());
				}
				cambios = true;
				msInstituciones.setDireccion(msInstitucionesBk.getDireccion());
			}
		} else if (msInstitucionesBk.getDireccion() == null && msInstituciones.getDireccion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "msInstituciones:Direccion" + " :: " + msInstitucionesBk.getIdprovee().toString()
								+ " :: " + msInstituciones.getDireccion() + " :: " + msInstitucionesBk.getDireccion());
			}
			cambios = true;
			msInstituciones.setDireccion(msInstitucionesBk.getDireccion());

		} else if (msInstitucionesBk.getDireccion() != null && msInstituciones.getDireccion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "msInstituciones:Direccion" + " :: " + msInstitucionesBk.getIdprovee().toString()
								+ " :: " + msInstituciones.getDireccion() + " :: " + msInstitucionesBk.getDireccion());
			}
			cambios = true;
			msInstituciones.setDireccion(msInstitucionesBk.getDireccion());
		}

		if (msInstitucionesBk.getTipoentidad() != null && msInstituciones.getTipoentidad() != null) {
			if (!msInstitucionesBk.getTipoentidad().equals(msInstituciones.getTipoentidad())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Tipoentidad" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getTipoentidad() + " :: " + msInstitucionesBk.getTipoentidad());
				}
				cambios = true;
				msInstituciones.setTipoentidad(msInstitucionesBk.getTipoentidad());
			}
		} else if (msInstitucionesBk.getTipoentidad() == null && msInstituciones.getTipoentidad() != null) {
			if (msInstituciones.getTipoentidad().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Tipoentidad" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getTipoentidad() + " :: " + msInstitucionesBk.getTipoentidad());
				}
				cambios = true;
				msInstituciones.setTipoentidad(msInstitucionesBk.getTipoentidad());
			}
		} else if (msInstitucionesBk.getTipoentidad() != null && msInstituciones.getTipoentidad() == null) {
			if (msInstitucionesBk.getTipoentidad().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "msInstituciones:Tipoentidad" + " :: " + msInstitucionesBk.getIdprovee().toString()
							+ " :: " + msInstituciones.getTipoentidad() + " :: " + msInstitucionesBk.getTipoentidad());
				}
				cambios = true;
				msInstituciones.setTipoentidad(msInstitucionesBk.getTipoentidad());
			}
		}
		return cambios;
	}

}