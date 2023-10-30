package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsUnidadesOrg;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;

/**
 * MS_UNIDADES_ORG SERVICIO AUDITORIA Y CAMBIO: UNIDADES ORGÁNICAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /25/11/2020 23:37  / Creación de la clase /
 * 
 */
public class AuditoriaMsUnidadesOrgMng implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3543635060731050312L;
	public static final Logger log = Logger.getLogger(AuditoriaMsUnidadesOrgMng.class.getName());
	
	public static boolean auditarCambiosMsUnidadesOrg(MsUnidadesOrgBk MsUnidadesOrgBk, MsUnidadesOrg MsUnidadesOrg, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (MsUnidadesOrgBk.getIdpadre() != null
							&& MsUnidadesOrg.getIdpadre() != null) {
						if (!MsUnidadesOrgBk.getIdpadre().equals(
								MsUnidadesOrg.getIdpadre())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Idpadre"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getIdpadre() + " :: "+ MsUnidadesOrgBk.getIdpadre());
								}
							cambios = true;
							MsUnidadesOrg.setIdpadre(MsUnidadesOrgBk.getIdpadre());
						}
					} else if (MsUnidadesOrgBk.getIdpadre() == null
							&& MsUnidadesOrg.getIdpadre() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Idpadre"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getIdpadre() + " :: "+ MsUnidadesOrgBk.getIdpadre());
								}
							cambios = true;
							MsUnidadesOrg.setIdpadre(MsUnidadesOrgBk.getIdpadre());
						
					} else if (MsUnidadesOrgBk.getIdpadre() != null
							&& MsUnidadesOrg.getIdpadre() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Idpadre"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getIdpadre() + " :: "+ MsUnidadesOrgBk.getIdpadre());
								}
							cambios = true;			
							MsUnidadesOrg.setIdpadre(MsUnidadesOrgBk.getIdpadre());
					}
				 
		            if (MsUnidadesOrgBk.getCodigo() != null
						&& MsUnidadesOrg.getCodigo() != null) {
					if (!MsUnidadesOrgBk.getCodigo().equals(
						MsUnidadesOrg.getCodigo())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Codigo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getCodigo() + " :: "+ MsUnidadesOrgBk.getCodigo());								
						}
						cambios = true;
						MsUnidadesOrg.setCodigo(MsUnidadesOrgBk.getCodigo());
					}
				} else if (MsUnidadesOrgBk.getCodigo() == null
						&& MsUnidadesOrg.getCodigo() != null) {
					if (MsUnidadesOrg.getCodigo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Codigo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getCodigo() + " :: "+ MsUnidadesOrgBk.getCodigo());
						}
						cambios = true;
						MsUnidadesOrg.setCodigo(MsUnidadesOrgBk.getCodigo());
					}
				} else if (MsUnidadesOrgBk.getCodigo() != null
						&& MsUnidadesOrg.getCodigo() == null) {
					if (MsUnidadesOrgBk.getCodigo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Codigo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getCodigo() + " :: "+ MsUnidadesOrgBk.getCodigo());
						}
						cambios = true;
						MsUnidadesOrg.setCodigo(MsUnidadesOrgBk.getCodigo());
					}
				}
				 
		            if (MsUnidadesOrgBk.getDescripcion() != null
						&& MsUnidadesOrg.getDescripcion() != null) {
					if (!MsUnidadesOrgBk.getDescripcion().equals(
						MsUnidadesOrg.getDescripcion())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Descripcion"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getDescripcion() + " :: "+ MsUnidadesOrgBk.getDescripcion());								
						}
						cambios = true;
						MsUnidadesOrg.setDescripcion(MsUnidadesOrgBk.getDescripcion());
					}
				} else if (MsUnidadesOrgBk.getDescripcion() == null
						&& MsUnidadesOrg.getDescripcion() != null) {
					if (MsUnidadesOrg.getDescripcion().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Descripcion"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getDescripcion() + " :: "+ MsUnidadesOrgBk.getDescripcion());
						}
						cambios = true;
						MsUnidadesOrg.setDescripcion(MsUnidadesOrgBk.getDescripcion());
					}
				} else if (MsUnidadesOrgBk.getDescripcion() != null
						&& MsUnidadesOrg.getDescripcion() == null) {
					if (MsUnidadesOrgBk.getDescripcion().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Descripcion"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getDescripcion() + " :: "+ MsUnidadesOrgBk.getDescripcion());
						}
						cambios = true;
						MsUnidadesOrg.setDescripcion(MsUnidadesOrgBk.getDescripcion());
					}
				}
				 
		            if (MsUnidadesOrgBk.getAcronimo() != null
						&& MsUnidadesOrg.getAcronimo() != null) {
					if (!MsUnidadesOrgBk.getAcronimo().equals(
						MsUnidadesOrg.getAcronimo())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Acronimo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getAcronimo() + " :: "+ MsUnidadesOrgBk.getAcronimo());								
						}
						cambios = true;
						MsUnidadesOrg.setAcronimo(MsUnidadesOrgBk.getAcronimo());
					}
				} else if (MsUnidadesOrgBk.getAcronimo() == null
						&& MsUnidadesOrg.getAcronimo() != null) {
					if (MsUnidadesOrg.getAcronimo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Acronimo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getAcronimo() + " :: "+ MsUnidadesOrgBk.getAcronimo());
						}
						cambios = true;
						MsUnidadesOrg.setAcronimo(MsUnidadesOrgBk.getAcronimo());
					}
				} else if (MsUnidadesOrgBk.getAcronimo() != null
						&& MsUnidadesOrg.getAcronimo() == null) {
					if (MsUnidadesOrgBk.getAcronimo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Acronimo"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getAcronimo() + " :: "+ MsUnidadesOrgBk.getAcronimo());
						}
						cambios = true;
						MsUnidadesOrg.setAcronimo(MsUnidadesOrgBk.getAcronimo());
					}
				}
				if (MsUnidadesOrgBk.getFlagofgeneral() != null
							&& MsUnidadesOrg.getFlagofgeneral() != null) {
						if (!MsUnidadesOrgBk.getFlagofgeneral().equals(
								MsUnidadesOrg.getFlagofgeneral())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Flagofgeneral"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getFlagofgeneral() + " :: "+ MsUnidadesOrgBk.getFlagofgeneral());
								}
							cambios = true;
							MsUnidadesOrg.setFlagofgeneral(MsUnidadesOrgBk.getFlagofgeneral());
						}
					} else if (MsUnidadesOrgBk.getFlagofgeneral() == null
							&& MsUnidadesOrg.getFlagofgeneral() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Flagofgeneral"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getFlagofgeneral() + " :: "+ MsUnidadesOrgBk.getFlagofgeneral());
								}
							cambios = true;
							MsUnidadesOrg.setFlagofgeneral(MsUnidadesOrgBk.getFlagofgeneral());
						
					} else if (MsUnidadesOrgBk.getFlagofgeneral() != null
							&& MsUnidadesOrg.getFlagofgeneral() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Flagofgeneral"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getFlagofgeneral() + " :: "+ MsUnidadesOrgBk.getFlagofgeneral());
								}
							cambios = true;			
							MsUnidadesOrg.setFlagofgeneral(MsUnidadesOrgBk.getFlagofgeneral());
					}
				if (MsUnidadesOrgBk.getEstado() != null
							&& MsUnidadesOrg.getEstado() != null) {
						if (!MsUnidadesOrgBk.getEstado().equals(
								MsUnidadesOrg.getEstado())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Estado"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getEstado() + " :: "+ MsUnidadesOrgBk.getEstado());
								}
							cambios = true;
							MsUnidadesOrg.setEstado(MsUnidadesOrgBk.getEstado());
						}
					} else if (MsUnidadesOrgBk.getEstado() == null
							&& MsUnidadesOrg.getEstado() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Estado"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getEstado() + " :: "+ MsUnidadesOrgBk.getEstado());
								}
							cambios = true;
							MsUnidadesOrg.setEstado(MsUnidadesOrgBk.getEstado());
						
					} else if (MsUnidadesOrgBk.getEstado() != null
							&& MsUnidadesOrg.getEstado() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"MsUnidadesOrg:Estado"+" :: "+MsUnidadesOrgBk.getIdunidad().toString()+" :: "+ MsUnidadesOrg.getEstado() + " :: "+ MsUnidadesOrgBk.getEstado());
								}
							cambios = true;			
							MsUnidadesOrg.setEstado(MsUnidadesOrgBk.getEstado());
					}
				
			
			return cambios;
	}
	
}