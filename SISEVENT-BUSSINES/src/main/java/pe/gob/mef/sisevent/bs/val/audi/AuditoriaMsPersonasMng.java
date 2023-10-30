package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsPersonas;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPersonasBk;

/**
 * MS_PERSONAS SERVICIO AUDITORIA Y CAMBIO: PERSONAS NATURALES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /22/12/2020 17:45  / Creación de la clase /
 * 
 */
public class AuditoriaMsPersonasMng implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5485695707040975814L;
	public static final Logger log = Logger.getLogger(AuditoriaMsPersonasMng.class.getName());
	
	public static boolean auditarCambiosMsPersonas(MsPersonasBk msPersonasBk, MsPersonas msPersonas, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		 
		            if (msPersonasBk.getApellidoPaterno() != null
						&& msPersonas.getApellidoPaterno() != null) {
					if (!msPersonasBk.getApellidoPaterno().equals(
						msPersonas.getApellidoPaterno())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoPaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoPaterno() + " :: "+ msPersonasBk.getApellidoPaterno());								
						}
						cambios = true;
						msPersonas.setApellidoPaterno(msPersonasBk.getApellidoPaterno());
					}
				} else if (msPersonasBk.getApellidoPaterno() == null
						&& msPersonas.getApellidoPaterno() != null) {
					if (msPersonas.getApellidoPaterno().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoPaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoPaterno() + " :: "+ msPersonasBk.getApellidoPaterno());
						}
						cambios = true;
						msPersonas.setApellidoPaterno(msPersonasBk.getApellidoPaterno());
					}
				} else if (msPersonasBk.getApellidoPaterno() != null
						&& msPersonas.getApellidoPaterno() == null) {
					if (msPersonasBk.getApellidoPaterno().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoPaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoPaterno() + " :: "+ msPersonasBk.getApellidoPaterno());
						}
						cambios = true;
						msPersonas.setApellidoPaterno(msPersonasBk.getApellidoPaterno());
					}
				}
				 
		            if (msPersonasBk.getApellidoMaterno() != null
						&& msPersonas.getApellidoMaterno() != null) {
					if (!msPersonasBk.getApellidoMaterno().equals(
						msPersonas.getApellidoMaterno())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoMaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoMaterno() + " :: "+ msPersonasBk.getApellidoMaterno());								
						}
						cambios = true;
						msPersonas.setApellidoMaterno(msPersonasBk.getApellidoMaterno());
					}
				} else if (msPersonasBk.getApellidoMaterno() == null
						&& msPersonas.getApellidoMaterno() != null) {
					if (msPersonas.getApellidoMaterno().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoMaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoMaterno() + " :: "+ msPersonasBk.getApellidoMaterno());
						}
						cambios = true;
						msPersonas.setApellidoMaterno(msPersonasBk.getApellidoMaterno());
					}
				} else if (msPersonasBk.getApellidoMaterno() != null
						&& msPersonas.getApellidoMaterno() == null) {
					if (msPersonasBk.getApellidoMaterno().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:ApellidoMaterno"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getApellidoMaterno() + " :: "+ msPersonasBk.getApellidoMaterno());
						}
						cambios = true;
						msPersonas.setApellidoMaterno(msPersonasBk.getApellidoMaterno());
					}
				}
				 
		            if (msPersonasBk.getNombres() != null
						&& msPersonas.getNombres() != null) {
					if (!msPersonasBk.getNombres().equals(
						msPersonas.getNombres())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Nombres"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getNombres() + " :: "+ msPersonasBk.getNombres());								
						}
						cambios = true;
						msPersonas.setNombres(msPersonasBk.getNombres());
					}
				} else if (msPersonasBk.getNombres() == null
						&& msPersonas.getNombres() != null) {
					if (msPersonas.getNombres().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Nombres"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getNombres() + " :: "+ msPersonasBk.getNombres());
						}
						cambios = true;
						msPersonas.setNombres(msPersonasBk.getNombres());
					}
				} else if (msPersonasBk.getNombres() != null
						&& msPersonas.getNombres() == null) {
					if (msPersonasBk.getNombres().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Nombres"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getNombres() + " :: "+ msPersonasBk.getNombres());
						}
						cambios = true;
						msPersonas.setNombres(msPersonasBk.getNombres());
					}
				}
				if (msPersonasBk.getSexo() != null
							&& msPersonas.getSexo() != null) {
						if (!msPersonasBk.getSexo().equals(
								msPersonas.getSexo())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Sexo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getSexo() + " :: "+ msPersonasBk.getSexo());
								}
							cambios = true;
							msPersonas.setSexo(msPersonasBk.getSexo());
						}
					} else if (msPersonasBk.getSexo() == null
							&& msPersonas.getSexo() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Sexo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getSexo() + " :: "+ msPersonasBk.getSexo());
								}
							cambios = true;
							msPersonas.setSexo(msPersonasBk.getSexo());
						
					} else if (msPersonasBk.getSexo() != null
							&& msPersonas.getSexo() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Sexo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getSexo() + " :: "+ msPersonasBk.getSexo());
								}
							cambios = true;			
							msPersonas.setSexo(msPersonasBk.getSexo());
					}
				if (msPersonasBk.getTipodocumento() != null
							&& msPersonas.getTipodocumento() != null) {
						if (!msPersonasBk.getTipodocumento().equals(
								msPersonas.getTipodocumento())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Tipodocumento"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTipodocumento() + " :: "+ msPersonasBk.getTipodocumento());
								}
							cambios = true;
							msPersonas.setTipodocumento(msPersonasBk.getTipodocumento());
						}
					} else if (msPersonasBk.getTipodocumento() == null
							&& msPersonas.getTipodocumento() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Tipodocumento"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTipodocumento() + " :: "+ msPersonasBk.getTipodocumento());
								}
							cambios = true;
							msPersonas.setTipodocumento(msPersonasBk.getTipodocumento());
						
					} else if (msPersonasBk.getTipodocumento() != null
							&& msPersonas.getTipodocumento() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Tipodocumento"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTipodocumento() + " :: "+ msPersonasBk.getTipodocumento());
								}
							cambios = true;			
							msPersonas.setTipodocumento(msPersonasBk.getTipodocumento());
					}
				 
		            if (msPersonasBk.getDocumentoNumero() != null
						&& msPersonas.getDocumentoNumero() != null) {
					if (!msPersonasBk.getDocumentoNumero().equals(
						msPersonas.getDocumentoNumero())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:DocumentoNumero"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDocumentoNumero() + " :: "+ msPersonasBk.getDocumentoNumero());								
						}
						cambios = true;
						msPersonas.setDocumentoNumero(msPersonasBk.getDocumentoNumero());
					}
				} else if (msPersonasBk.getDocumentoNumero() == null
						&& msPersonas.getDocumentoNumero() != null) {
					if (msPersonas.getDocumentoNumero().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:DocumentoNumero"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDocumentoNumero() + " :: "+ msPersonasBk.getDocumentoNumero());
						}
						cambios = true;
						msPersonas.setDocumentoNumero(msPersonasBk.getDocumentoNumero());
					}
				} else if (msPersonasBk.getDocumentoNumero() != null
						&& msPersonas.getDocumentoNumero() == null) {
					if (msPersonasBk.getDocumentoNumero().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:DocumentoNumero"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDocumentoNumero() + " :: "+ msPersonasBk.getDocumentoNumero());
						}
						cambios = true;
						msPersonas.setDocumentoNumero(msPersonasBk.getDocumentoNumero());
					}
				}
				 
		            if (msPersonasBk.getCorreo() != null
						&& msPersonas.getCorreo() != null) {
					if (!msPersonasBk.getCorreo().equals(
						msPersonas.getCorreo())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Correo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCorreo() + " :: "+ msPersonasBk.getCorreo());								
						}
						cambios = true;
						msPersonas.setCorreo(msPersonasBk.getCorreo());
					}
				} else if (msPersonasBk.getCorreo() == null
						&& msPersonas.getCorreo() != null) {
					if (msPersonas.getCorreo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Correo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCorreo() + " :: "+ msPersonasBk.getCorreo());
						}
						cambios = true;
						msPersonas.setCorreo(msPersonasBk.getCorreo());
					}
				} else if (msPersonasBk.getCorreo() != null
						&& msPersonas.getCorreo() == null) {
					if (msPersonasBk.getCorreo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Correo"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCorreo() + " :: "+ msPersonasBk.getCorreo());
						}
						cambios = true;
						msPersonas.setCorreo(msPersonasBk.getCorreo());
					}
				}
				 
		            if (msPersonasBk.getTelefono() != null
						&& msPersonas.getTelefono() != null) {
					if (!msPersonasBk.getTelefono().equals(
						msPersonas.getTelefono())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Telefono"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTelefono() + " :: "+ msPersonasBk.getTelefono());								
						}
						cambios = true;
						msPersonas.setTelefono(msPersonasBk.getTelefono());
					}
				} else if (msPersonasBk.getTelefono() == null
						&& msPersonas.getTelefono() != null) {
					if (msPersonas.getTelefono().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Telefono"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTelefono() + " :: "+ msPersonasBk.getTelefono());
						}
						cambios = true;
						msPersonas.setTelefono(msPersonasBk.getTelefono());
					}
				} else if (msPersonasBk.getTelefono() != null
						&& msPersonas.getTelefono() == null) {
					if (msPersonasBk.getTelefono().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Telefono"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getTelefono() + " :: "+ msPersonasBk.getTelefono());
						}
						cambios = true;
						msPersonas.setTelefono(msPersonasBk.getTelefono());
					}
				}
				 
		            if (msPersonasBk.getCelular() != null
						&& msPersonas.getCelular() != null) {
					if (!msPersonasBk.getCelular().equals(
						msPersonas.getCelular())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Celular"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCelular() + " :: "+ msPersonasBk.getCelular());								
						}
						cambios = true;
						msPersonas.setCelular(msPersonasBk.getCelular());
					}
				} else if (msPersonasBk.getCelular() == null
						&& msPersonas.getCelular() != null) {
					if (msPersonas.getCelular().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Celular"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCelular() + " :: "+ msPersonasBk.getCelular());
						}
						cambios = true;
						msPersonas.setCelular(msPersonasBk.getCelular());
					}
				} else if (msPersonasBk.getCelular() != null
						&& msPersonas.getCelular() == null) {
					if (msPersonasBk.getCelular().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Celular"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCelular() + " :: "+ msPersonasBk.getCelular());
						}
						cambios = true;
						msPersonas.setCelular(msPersonasBk.getCelular());
					}
				}
				if (msPersonasBk.getCodpais() != null
							&& msPersonas.getCodpais() != null) {
						if (!msPersonasBk.getCodpais().equals(
								msPersonas.getCodpais())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codpais"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodpais() + " :: "+ msPersonasBk.getCodpais());
								}
							cambios = true;
							msPersonas.setCodpais(msPersonasBk.getCodpais());
						}
					} else if (msPersonasBk.getCodpais() == null
							&& msPersonas.getCodpais() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codpais"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodpais() + " :: "+ msPersonasBk.getCodpais());
								}
							cambios = true;
							msPersonas.setCodpais(msPersonasBk.getCodpais());
						
					} else if (msPersonasBk.getCodpais() != null
							&& msPersonas.getCodpais() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codpais"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodpais() + " :: "+ msPersonasBk.getCodpais());
								}
							cambios = true;			
							msPersonas.setCodpais(msPersonasBk.getCodpais());
					}
				if (msPersonasBk.getCoddpto() != null
							&& msPersonas.getCoddpto() != null) {
						if (!msPersonasBk.getCoddpto().equals(
								msPersonas.getCoddpto())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddpto"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddpto() + " :: "+ msPersonasBk.getCoddpto());
								}
							cambios = true;
							msPersonas.setCoddpto(msPersonasBk.getCoddpto());
						}
					} else if (msPersonasBk.getCoddpto() == null
							&& msPersonas.getCoddpto() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddpto"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddpto() + " :: "+ msPersonasBk.getCoddpto());
								}
							cambios = true;
							msPersonas.setCoddpto(msPersonasBk.getCoddpto());
						
					} else if (msPersonasBk.getCoddpto() != null
							&& msPersonas.getCoddpto() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddpto"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddpto() + " :: "+ msPersonasBk.getCoddpto());
								}
							cambios = true;			
							msPersonas.setCoddpto(msPersonasBk.getCoddpto());
					}
				if (msPersonasBk.getCodprov() != null
							&& msPersonas.getCodprov() != null) {
						if (!msPersonasBk.getCodprov().equals(
								msPersonas.getCodprov())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codprov"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodprov() + " :: "+ msPersonasBk.getCodprov());
								}
							cambios = true;
							msPersonas.setCodprov(msPersonasBk.getCodprov());
						}
					} else if (msPersonasBk.getCodprov() == null
							&& msPersonas.getCodprov() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codprov"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodprov() + " :: "+ msPersonasBk.getCodprov());
								}
							cambios = true;
							msPersonas.setCodprov(msPersonasBk.getCodprov());
						
					} else if (msPersonasBk.getCodprov() != null
							&& msPersonas.getCodprov() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Codprov"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCodprov() + " :: "+ msPersonasBk.getCodprov());
								}
							cambios = true;			
							msPersonas.setCodprov(msPersonasBk.getCodprov());
					}
				if (msPersonasBk.getCoddist() != null
							&& msPersonas.getCoddist() != null) {
						if (!msPersonasBk.getCoddist().equals(
								msPersonas.getCoddist())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddist"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddist() + " :: "+ msPersonasBk.getCoddist());
								}
							cambios = true;
							msPersonas.setCoddist(msPersonasBk.getCoddist());
						}
					} else if (msPersonasBk.getCoddist() == null
							&& msPersonas.getCoddist() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddist"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddist() + " :: "+ msPersonasBk.getCoddist());
								}
							cambios = true;
							msPersonas.setCoddist(msPersonasBk.getCoddist());
						
					} else if (msPersonasBk.getCoddist() != null
							&& msPersonas.getCoddist() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Coddist"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getCoddist() + " :: "+ msPersonasBk.getCoddist());
								}
							cambios = true;			
							msPersonas.setCoddist(msPersonasBk.getCoddist());
					}
				if (msPersonasBk.getDireccion() != null
							&& msPersonas.getDireccion() != null) {
						if (!msPersonasBk.getDireccion().equals(
								msPersonas.getDireccion())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Direccion"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDireccion() + " :: "+ msPersonasBk.getDireccion());
								}
							cambios = true;
							msPersonas.setDireccion(msPersonasBk.getDireccion());
						}
					} else if (msPersonasBk.getDireccion() == null
							&& msPersonas.getDireccion() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Direccion"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDireccion() + " :: "+ msPersonasBk.getDireccion());
								}
							cambios = true;
							msPersonas.setDireccion(msPersonasBk.getDireccion());
						
					} else if (msPersonasBk.getDireccion() != null
							&& msPersonas.getDireccion() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPersonas:Direccion"+" :: "+msPersonasBk.getIdperson().toString()+" :: "+ msPersonas.getDireccion() + " :: "+ msPersonasBk.getDireccion());
								}
							cambios = true;			
							msPersonas.setDireccion(msPersonasBk.getDireccion());
					}
				
			
			return cambios;
	}
	
}