package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsActividades;
import pe.gob.mef.sisevent.bs.transfer.bk.MsActividadesBk;

/**
 * MS_ACTIVIDADES SERVICIO AUDITORIA Y CAMBIO: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaMsActividadesMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaMsActividadesMng.class.getName());
	
	public static boolean auditarCambiosMsActividades(MsActividadesBk msActividadesBk, MsActividades msActividades, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (msActividadesBk.getIdtareas() != null
							&& msActividades.getIdtareas() != null) {
						if (!msActividadesBk.getIdtareas().equals(
								msActividades.getIdtareas())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Idtareas"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getIdtareas() + " :: "+ msActividadesBk.getIdtareas());
								}
							cambios = true;
							msActividades.setIdtareas(msActividadesBk.getIdtareas());
						}
					} else if (msActividadesBk.getIdtareas() == null
							&& msActividades.getIdtareas() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Idtareas"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getIdtareas() + " :: "+ msActividadesBk.getIdtareas());
								}
							cambios = true;
							msActividades.setIdtareas(msActividadesBk.getIdtareas());
						
					} else if (msActividadesBk.getIdtareas() != null
							&& msActividades.getIdtareas() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Idtareas"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getIdtareas() + " :: "+ msActividadesBk.getIdtareas());
								}
							cambios = true;			
							msActividades.setIdtareas(msActividadesBk.getIdtareas());
					}
				 
		            if (msActividadesBk.getActividad() != null
						&& msActividades.getActividad() != null) {
					if (!msActividadesBk.getActividad().equals(
						msActividades.getActividad())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Actividad"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getActividad() + " :: "+ msActividadesBk.getActividad());								
						}
						cambios = true;
						msActividades.setActividad(msActividadesBk.getActividad());
					}
				} else if (msActividadesBk.getActividad() == null
						&& msActividades.getActividad() != null) {
					if (msActividades.getActividad().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Actividad"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getActividad() + " :: "+ msActividadesBk.getActividad());
						}
						cambios = true;
						msActividades.setActividad(msActividadesBk.getActividad());
					}
				} else if (msActividadesBk.getActividad() != null
						&& msActividades.getActividad() == null) {
					if (msActividadesBk.getActividad().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Actividad"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getActividad() + " :: "+ msActividadesBk.getActividad());
						}
						cambios = true;
						msActividades.setActividad(msActividadesBk.getActividad());
					}
				}
				 
		            if (msActividadesBk.getCamposallenar() != null
						&& msActividades.getCamposallenar() != null) {
					if (!msActividadesBk.getCamposallenar().equals(
						msActividades.getCamposallenar())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Camposallenar"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getCamposallenar() + " :: "+ msActividadesBk.getCamposallenar());								
						}
						cambios = true;
						msActividades.setCamposallenar(msActividadesBk.getCamposallenar());
					}
				} else if (msActividadesBk.getCamposallenar() == null
						&& msActividades.getCamposallenar() != null) {
					if (msActividades.getCamposallenar().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Camposallenar"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getCamposallenar() + " :: "+ msActividadesBk.getCamposallenar());
						}
						cambios = true;
						msActividades.setCamposallenar(msActividadesBk.getCamposallenar());
					}
				} else if (msActividadesBk.getCamposallenar() != null
						&& msActividades.getCamposallenar() == null) {
					if (msActividadesBk.getCamposallenar().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Camposallenar"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getCamposallenar() + " :: "+ msActividadesBk.getCamposallenar());
						}
						cambios = true;
						msActividades.setCamposallenar(msActividadesBk.getCamposallenar());
					}
				}
				if (msActividadesBk.getNumerodigitales() != null
							&& msActividades.getNumerodigitales() != null) {
						if (!msActividadesBk.getNumerodigitales().equals(
								msActividades.getNumerodigitales())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Numerodigitales"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getNumerodigitales() + " :: "+ msActividadesBk.getNumerodigitales());
								}
							cambios = true;
							msActividades.setNumerodigitales(msActividadesBk.getNumerodigitales());
						}
					} else if (msActividadesBk.getNumerodigitales() == null
							&& msActividades.getNumerodigitales() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Numerodigitales"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getNumerodigitales() + " :: "+ msActividadesBk.getNumerodigitales());
								}
							cambios = true;
							msActividades.setNumerodigitales(msActividadesBk.getNumerodigitales());
						
					} else if (msActividadesBk.getNumerodigitales() != null
							&& msActividades.getNumerodigitales() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msActividades:Numerodigitales"+" :: "+msActividadesBk.getIdactividades().toString()+" :: "+ msActividades.getNumerodigitales() + " :: "+ msActividadesBk.getNumerodigitales());
								}
							cambios = true;			
							msActividades.setNumerodigitales(msActividadesBk.getNumerodigitales());
					}
				
			
			return cambios;
	}
	
}