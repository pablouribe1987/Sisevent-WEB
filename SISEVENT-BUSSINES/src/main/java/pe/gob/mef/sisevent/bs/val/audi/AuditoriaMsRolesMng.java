package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsRoles;
import pe.gob.mef.sisevent.bs.transfer.bk.MsRolesBk;

/**
 * MS_ROLES SERVICIO AUDITORIA Y CAMBIO: ROLES DEL SISTEMA, ROLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /25/11/2020 23:37  / Creación de la clase /
 * 
 */
public class AuditoriaMsRolesMng implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 752561722163522401L;
	public static final Logger log = Logger.getLogger(AuditoriaMsRolesMng.class.getName());
	
	public static boolean auditarCambiosMsRoles(MsRolesBk MsRolesBk, MsRoles MsRoles, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		 
		            if (MsRolesBk.getUsername() != null
						&& MsRoles.getUsername() != null) {
					if (!MsRolesBk.getUsername().equals(
						MsRoles.getUsername())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Username"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getUsername() + " :: "+ MsRolesBk.getUsername());								
						}
						cambios = true;
						MsRoles.setUsername(MsRolesBk.getUsername());
					}
				} else if (MsRolesBk.getUsername() == null
						&& MsRoles.getUsername() != null) {
					if (MsRoles.getUsername().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Username"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getUsername() + " :: "+ MsRolesBk.getUsername());
						}
						cambios = true;
						MsRoles.setUsername(MsRolesBk.getUsername());
					}
				} else if (MsRolesBk.getUsername() != null
						&& MsRoles.getUsername() == null) {
					if (MsRolesBk.getUsername().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Username"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getUsername() + " :: "+ MsRolesBk.getUsername());
						}
						cambios = true;
						MsRoles.setUsername(MsRolesBk.getUsername());
					}
				}
				if (MsRolesBk.getRol() != null
							&& MsRoles.getRol() != null) {
						if (!MsRolesBk.getRol().equals(
								MsRoles.getRol())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Rol"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getRol() + " :: "+ MsRolesBk.getRol());
								}
							cambios = true;
							MsRoles.setRol(MsRolesBk.getRol());
						}
					} else if (MsRolesBk.getRol() == null
							&& MsRoles.getRol() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Rol"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getRol() + " :: "+ MsRolesBk.getRol());
								}
							cambios = true;
							MsRoles.setRol(MsRolesBk.getRol());
						
					} else if (MsRolesBk.getRol() != null
							&& MsRoles.getRol() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Rol"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getRol() + " :: "+ MsRolesBk.getRol());
								}
							cambios = true;			
							MsRoles.setRol(MsRolesBk.getRol());
					}
				if (MsRolesBk.getEstado() != null
							&& MsRoles.getEstado() != null) {
						if (!MsRolesBk.getEstado().equals(
								MsRoles.getEstado())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Estado"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getEstado() + " :: "+ MsRolesBk.getEstado());
								}
							cambios = true;
							MsRoles.setEstado(MsRolesBk.getEstado());
						}
					} else if (MsRolesBk.getEstado() == null
							&& MsRoles.getEstado() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Estado"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getEstado() + " :: "+ MsRolesBk.getEstado());
								}
							cambios = true;
							MsRoles.setEstado(MsRolesBk.getEstado());
						
					} else if (MsRolesBk.getEstado() != null
							&& MsRoles.getEstado() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsRoles:Estado"+" :: "+MsRolesBk.getIdrol().toString()+" :: "+ MsRoles.getEstado() + " :: "+ MsRolesBk.getEstado());
								}
							cambios = true;			
							MsRoles.setEstado(MsRolesBk.getEstado());
					}
				
			
			return cambios;
	}
	
}