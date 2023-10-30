package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsUsuarios;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;

/**
 * MS_USUARIOS SERVICIO AUDITORIA Y CAMBIO: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /25/11/2020 23:37 / Creación de la clase /
 * 
 */
public class AuditoriaMsUsuariosMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5301064633508544571L;
	public static final Logger log = Logger.getLogger(AuditoriaMsUsuariosMng.class.getName());

	public static boolean auditarCambiosMsUsuarios(MsUsuariosBk MsUsuariosBk, MsUsuarios MsUsuarios, Long iduser,
			String user, String rmtaddress, int nivel) {
		boolean cambios = false;

		if (MsUsuariosBk.getUsername() != null && MsUsuarios.getUsername() != null) {
			if (!MsUsuariosBk.getUsername().equals(MsUsuarios.getUsername())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Username"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
									+ MsUsuarios.getUsername() + " :: " + MsUsuariosBk.getUsername());
				}
				cambios = true;
				MsUsuarios.setUsername(MsUsuariosBk.getUsername());
			}
		} else if (MsUsuariosBk.getUsername() == null && MsUsuarios.getUsername() != null) {
			if (MsUsuarios.getUsername().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Username"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
									+ MsUsuarios.getUsername() + " :: " + MsUsuariosBk.getUsername());
				}
				cambios = true;
				MsUsuarios.setUsername(MsUsuariosBk.getUsername());
			}
		} else if (MsUsuariosBk.getUsername() != null && MsUsuarios.getUsername() == null) {
			if (MsUsuariosBk.getUsername().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Username"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
									+ MsUsuarios.getUsername() + " :: " + MsUsuariosBk.getUsername());
				}
				cambios = true;
				MsUsuarios.setUsername(MsUsuariosBk.getUsername());
			}
		}

		if (MsUsuariosBk.getNombres() != null && MsUsuarios.getNombres() != null) {
			if (!MsUsuariosBk.getNombres().equals(MsUsuarios.getNombres())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Nombres"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getNombres()
									+ " :: " + MsUsuariosBk.getNombres());
				}
				cambios = true;
				MsUsuarios.setNombres(MsUsuariosBk.getNombres());
			}
		} else if (MsUsuariosBk.getNombres() == null && MsUsuarios.getNombres() != null) {
			if (MsUsuarios.getNombres().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Nombres"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getNombres()
									+ " :: " + MsUsuariosBk.getNombres());
				}
				cambios = true;
				MsUsuarios.setNombres(MsUsuariosBk.getNombres());
			}
		} else if (MsUsuariosBk.getNombres() != null && MsUsuarios.getNombres() == null) {
			if (MsUsuariosBk.getNombres().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Nombres"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getNombres()
									+ " :: " + MsUsuariosBk.getNombres());
				}
				cambios = true;
				MsUsuarios.setNombres(MsUsuariosBk.getNombres());
			}
		}

		if (MsUsuariosBk.getApellidoPaterno() != null && MsUsuarios.getApellidoPaterno() != null) {
			if (!MsUsuariosBk.getApellidoPaterno().equals(MsUsuarios.getApellidoPaterno())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoPaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoPaterno() + " :: " + MsUsuariosBk.getApellidoPaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoPaterno(MsUsuariosBk.getApellidoPaterno());
			}
		} else if (MsUsuariosBk.getApellidoPaterno() == null && MsUsuarios.getApellidoPaterno() != null) {
			if (MsUsuarios.getApellidoPaterno().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoPaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoPaterno() + " :: " + MsUsuariosBk.getApellidoPaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoPaterno(MsUsuariosBk.getApellidoPaterno());
			}
		} else if (MsUsuariosBk.getApellidoPaterno() != null && MsUsuarios.getApellidoPaterno() == null) {
			if (MsUsuariosBk.getApellidoPaterno().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoPaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoPaterno() + " :: " + MsUsuariosBk.getApellidoPaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoPaterno(MsUsuariosBk.getApellidoPaterno());
			}
		}

		if (MsUsuariosBk.getApellidoMaterno() != null && MsUsuarios.getApellidoMaterno() != null) {
			if (!MsUsuariosBk.getApellidoMaterno().equals(MsUsuarios.getApellidoMaterno())) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoMaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoMaterno() + " :: " + MsUsuariosBk.getApellidoMaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoMaterno(MsUsuariosBk.getApellidoMaterno());
			}
		} else if (MsUsuariosBk.getApellidoMaterno() == null && MsUsuarios.getApellidoMaterno() != null) {
			if (MsUsuarios.getApellidoMaterno().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoMaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoMaterno() + " :: " + MsUsuariosBk.getApellidoMaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoMaterno(MsUsuariosBk.getApellidoMaterno());
			}
		} else if (MsUsuariosBk.getApellidoMaterno() != null && MsUsuarios.getApellidoMaterno() == null) {
			if (MsUsuariosBk.getApellidoMaterno().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
							+ "MsUsuarios:ApellidoMaterno" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
							+ MsUsuarios.getApellidoMaterno() + " :: " + MsUsuariosBk.getApellidoMaterno());
				}
				cambios = true;
				MsUsuarios.setApellidoMaterno(MsUsuariosBk.getApellidoMaterno());
			}
		}

		if (MsUsuariosBk.getContrasenia() != null && MsUsuariosBk.getContrasenia().length() > 1) {
			if (MsUsuariosBk.getContrasenia() != null && MsUsuarios.getContrasenia() != null) {
				if (!MsUsuariosBk.getContrasenia().equals(MsUsuarios.getContrasenia())) {
					if (nivel > 0) {
						log.log(Level.INFO, "CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "MsUsuarios:Contrasenia" + " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
								+ MsUsuarios.getContrasenia() + " :: " + MsUsuariosBk.getContrasenia());
					}
					cambios = true;
					MsUsuarios.setContrasenia(MsUsuariosBk.getContrasenia());
				}
			} else if (MsUsuariosBk.getContrasenia() == null && MsUsuarios.getContrasenia() != null) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "MsUsuarios:Contrasenia" + " :: " + MsUsuariosBk.getIdusuario().toString()
									+ " :: " + MsUsuarios.getContrasenia() + " :: " + MsUsuariosBk.getContrasenia());
				}
				cambios = true;
				MsUsuarios.setContrasenia(MsUsuariosBk.getContrasenia());

			} else if (MsUsuariosBk.getContrasenia() != null && MsUsuarios.getContrasenia() == null) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "MsUsuarios:Contrasenia" + " :: " + MsUsuariosBk.getIdusuario().toString()
									+ " :: " + MsUsuarios.getContrasenia() + " :: " + MsUsuariosBk.getContrasenia());
				}
				cambios = true;
				MsUsuarios.setContrasenia(MsUsuariosBk.getContrasenia());
			}
		}

		if (MsUsuariosBk.getEstado() != null && MsUsuarios.getEstado() != null) {
			if (!MsUsuariosBk.getEstado().equals(MsUsuarios.getEstado())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Estado"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getEstado()
									+ " :: " + MsUsuariosBk.getEstado());
				}
				cambios = true;
				MsUsuarios.setEstado(MsUsuariosBk.getEstado());
			}
		} else if (MsUsuariosBk.getEstado() == null && MsUsuarios.getEstado() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Estado"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getEstado()
								+ " :: " + MsUsuariosBk.getEstado());
			}
			cambios = true;
			MsUsuarios.setEstado(MsUsuariosBk.getEstado());

		} else if (MsUsuariosBk.getEstado() != null && MsUsuarios.getEstado() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Estado"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getEstado()
								+ " :: " + MsUsuariosBk.getEstado());
			}
			cambios = true;
			MsUsuarios.setEstado(MsUsuariosBk.getEstado());
		}
		if (MsUsuariosBk.getSede() != null && MsUsuarios.getSede() != null) {
			if (!MsUsuariosBk.getSede().equals(MsUsuarios.getSede())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Sede"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getSede()
									+ " :: " + MsUsuariosBk.getSede());
				}
				cambios = true;
				MsUsuarios.setSede(MsUsuariosBk.getSede());
			}
		} else if (MsUsuariosBk.getSede() == null && MsUsuarios.getSede() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Sede"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getSede()
								+ " :: " + MsUsuariosBk.getSede());
			}
			cambios = true;
			MsUsuarios.setSede(MsUsuariosBk.getSede());

		} else if (MsUsuariosBk.getSede() != null && MsUsuarios.getSede() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Sede"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getSede()
								+ " :: " + MsUsuariosBk.getSede());
			}
			cambios = true;
			MsUsuarios.setSede(MsUsuariosBk.getSede());
		}
		if (MsUsuariosBk.getIdunidad() != null && MsUsuarios.getIdunidad() != null) {
			if (!MsUsuariosBk.getIdunidad().equals(MsUsuarios.getIdunidad())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Idunidad"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: "
									+ MsUsuarios.getIdunidad() + " :: " + MsUsuariosBk.getIdunidad());
				}
				cambios = true;
				MsUsuarios.setIdunidad(MsUsuariosBk.getIdunidad());
			}
		} else if (MsUsuariosBk.getIdunidad() == null && MsUsuarios.getIdunidad() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Idunidad"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getIdunidad()
								+ " :: " + MsUsuariosBk.getIdunidad());
			}
			cambios = true;
			MsUsuarios.setIdunidad(MsUsuariosBk.getIdunidad());

		} else if (MsUsuariosBk.getIdunidad() != null && MsUsuarios.getIdunidad() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Idunidad"
								+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getIdunidad()
								+ " :: " + MsUsuariosBk.getIdunidad());
			}
			cambios = true;
			MsUsuarios.setIdunidad(MsUsuariosBk.getIdunidad());
		}

		if (MsUsuariosBk.getAnexo() != null && MsUsuarios.getAnexo() != null) {
			if (!MsUsuariosBk.getAnexo().equals(MsUsuarios.getAnexo())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Anexo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getAnexo()
									+ " :: " + MsUsuariosBk.getAnexo());
				}
				cambios = true;
				MsUsuarios.setAnexo(MsUsuariosBk.getAnexo());
			}
		} else if (MsUsuariosBk.getAnexo() == null && MsUsuarios.getAnexo() != null) {
			if (MsUsuarios.getAnexo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Anexo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getAnexo()
									+ " :: " + MsUsuariosBk.getAnexo());
				}
				cambios = true;
				MsUsuarios.setAnexo(MsUsuariosBk.getAnexo());
			}
		} else if (MsUsuariosBk.getAnexo() != null && MsUsuarios.getAnexo() == null) {
			if (MsUsuariosBk.getAnexo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Anexo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getAnexo()
									+ " :: " + MsUsuariosBk.getAnexo());
				}
				cambios = true;
				MsUsuarios.setAnexo(MsUsuariosBk.getAnexo());
			}
		}

		if (MsUsuariosBk.getCelular() != null && MsUsuarios.getCelular() != null) {
			if (!MsUsuariosBk.getCelular().equals(MsUsuarios.getCelular())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Celular"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCelular()
									+ " :: " + MsUsuariosBk.getCelular());
				}
				cambios = true;
				MsUsuarios.setCelular(MsUsuariosBk.getCelular());
			}
		} else if (MsUsuariosBk.getCelular() == null && MsUsuarios.getCelular() != null) {
			if (MsUsuarios.getCelular().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Celular"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCelular()
									+ " :: " + MsUsuariosBk.getCelular());
				}
				cambios = true;
				MsUsuarios.setCelular(MsUsuariosBk.getCelular());
			}
		} else if (MsUsuariosBk.getCelular() != null && MsUsuarios.getCelular() == null) {
			if (MsUsuariosBk.getCelular().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Celular"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCelular()
									+ " :: " + MsUsuariosBk.getCelular());
				}
				cambios = true;
				MsUsuarios.setCelular(MsUsuariosBk.getCelular());
			}
		}

		if (MsUsuariosBk.getCorreo() != null && MsUsuarios.getCorreo() != null) {
			if (!MsUsuariosBk.getCorreo().equals(MsUsuarios.getCorreo())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Correo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCorreo()
									+ " :: " + MsUsuariosBk.getCorreo());
				}
				cambios = true;
				MsUsuarios.setCorreo(MsUsuariosBk.getCorreo());
			}
		} else if (MsUsuariosBk.getCorreo() == null && MsUsuarios.getCorreo() != null) {
			if (MsUsuarios.getCorreo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Correo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCorreo()
									+ " :: " + MsUsuariosBk.getCorreo());
				}
				cambios = true;
				MsUsuarios.setCorreo(MsUsuariosBk.getCorreo());
			}
		} else if (MsUsuariosBk.getCorreo() != null && MsUsuarios.getCorreo() == null) {
			if (MsUsuariosBk.getCorreo().trim().length() > 0) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "MsUsuarios:Correo"
									+ " :: " + MsUsuariosBk.getIdusuario().toString() + " :: " + MsUsuarios.getCorreo()
									+ " :: " + MsUsuariosBk.getCorreo());
				}
				cambios = true;
				MsUsuarios.setCorreo(MsUsuariosBk.getCorreo());
			}
		}
		return cambios;
	}

}