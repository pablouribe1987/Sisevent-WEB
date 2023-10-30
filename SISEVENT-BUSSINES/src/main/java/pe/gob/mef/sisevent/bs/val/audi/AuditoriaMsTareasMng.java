package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsTareas;
import pe.gob.mef.sisevent.bs.transfer.bk.MsTareasBk;

/**
 * MS_TAREAS SERVICIO AUDITORIA Y CAMBIO: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS DERIVACIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaMsTareasMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaMsTareasMng.class.getName());
	
	public static boolean auditarCambiosMsTareas(MsTareasBk msTareasBk, MsTareas msTareas, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		 
		            if (msTareasBk.getTarea() != null
						&& msTareas.getTarea() != null) {
					if (!msTareasBk.getTarea().equals(
						msTareas.getTarea())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tarea"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTarea() + " :: "+ msTareasBk.getTarea());								
						}
						cambios = true;
						msTareas.setTarea(msTareasBk.getTarea());
					}
				} else if (msTareasBk.getTarea() == null
						&& msTareas.getTarea() != null) {
					if (msTareas.getTarea().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tarea"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTarea() + " :: "+ msTareasBk.getTarea());
						}
						cambios = true;
						msTareas.setTarea(msTareasBk.getTarea());
					}
				} else if (msTareasBk.getTarea() != null
						&& msTareas.getTarea() == null) {
					if (msTareasBk.getTarea().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tarea"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTarea() + " :: "+ msTareasBk.getTarea());
						}
						cambios = true;
						msTareas.setTarea(msTareasBk.getTarea());
					}
				}
				if (msTareasBk.getDescripcion() != null
							&& msTareas.getDescripcion() != null) {
						if (!msTareasBk.getDescripcion().equals(
								msTareas.getDescripcion())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Descripcion"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getDescripcion() + " :: "+ msTareasBk.getDescripcion());
								}
							cambios = true;
							msTareas.setDescripcion(msTareasBk.getDescripcion());
						}
					} else if (msTareasBk.getDescripcion() == null
							&& msTareas.getDescripcion() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Descripcion"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getDescripcion() + " :: "+ msTareasBk.getDescripcion());
								}
							cambios = true;
							msTareas.setDescripcion(msTareasBk.getDescripcion());
						
					} else if (msTareasBk.getDescripcion() != null
							&& msTareas.getDescripcion() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Descripcion"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getDescripcion() + " :: "+ msTareasBk.getDescripcion());
								}
							cambios = true;			
							msTareas.setDescripcion(msTareasBk.getDescripcion());
					}
				if (msTareasBk.getTiempo() != null
							&& msTareas.getTiempo() != null) {
						if (!msTareasBk.getTiempo().equals(
								msTareas.getTiempo())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tiempo"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTiempo() + " :: "+ msTareasBk.getTiempo());
								}
							cambios = true;
							msTareas.setTiempo(msTareasBk.getTiempo());
						}
					} else if (msTareasBk.getTiempo() == null
							&& msTareas.getTiempo() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tiempo"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTiempo() + " :: "+ msTareasBk.getTiempo());
								}
							cambios = true;
							msTareas.setTiempo(msTareasBk.getTiempo());
						
					} else if (msTareasBk.getTiempo() != null
							&& msTareas.getTiempo() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msTareas:Tiempo"+" :: "+msTareasBk.getIdtareas().toString()+" :: "+ msTareas.getTiempo() + " :: "+ msTareasBk.getTiempo());
								}
							cambios = true;			
							msTareas.setTiempo(msTareasBk.getTiempo());
					}
				
			
			return cambios;
	}
	
}