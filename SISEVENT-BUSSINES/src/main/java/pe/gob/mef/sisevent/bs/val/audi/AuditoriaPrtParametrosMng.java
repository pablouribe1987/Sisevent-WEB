package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.PrtParametros;
import pe.gob.mef.sisevent.bs.transfer.bk.PrtParametrosBk;

/**
 * PRT_PARAMETROS SERVICIO AUDITORIA Y CAMBIO: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL SISTEMA PARÁMETROS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /25/11/2020 23:37  / Creación de la clase /
 * 
 */
public class AuditoriaPrtParametrosMng implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 64222878509700895L;
	public static final Logger log = Logger.getLogger(AuditoriaPrtParametrosMng.class.getName());
	
	public static boolean auditarCambiosPrtParametros(PrtParametrosBk PrtParametrosBk, PrtParametros PrtParametros, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (PrtParametrosBk.getIdpadre() != null
							&& PrtParametros.getIdpadre() != null) {
						if (!PrtParametrosBk.getIdpadre().equals(
								PrtParametros.getIdpadre())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Idpadre"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getIdpadre() + " :: "+ PrtParametrosBk.getIdpadre());
								}
							cambios = true;
							PrtParametros.setIdpadre(PrtParametrosBk.getIdpadre());
						}
					} else if (PrtParametrosBk.getIdpadre() == null
							&& PrtParametros.getIdpadre() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Idpadre"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getIdpadre() + " :: "+ PrtParametrosBk.getIdpadre());
								}
							cambios = true;
							PrtParametros.setIdpadre(PrtParametrosBk.getIdpadre());
						
					} else if (PrtParametrosBk.getIdpadre() != null
							&& PrtParametros.getIdpadre() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Idpadre"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getIdpadre() + " :: "+ PrtParametrosBk.getIdpadre());
								}
							cambios = true;			
							PrtParametros.setIdpadre(PrtParametrosBk.getIdpadre());
					}
				 
		            if (PrtParametrosBk.getDescripcion() != null
						&& PrtParametros.getDescripcion() != null) {
					if (!PrtParametrosBk.getDescripcion().equals(
						PrtParametros.getDescripcion())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcion"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcion() + " :: "+ PrtParametrosBk.getDescripcion());								
						}
						cambios = true;
						PrtParametros.setDescripcion(PrtParametrosBk.getDescripcion());
					}
				} else if (PrtParametrosBk.getDescripcion() == null
						&& PrtParametros.getDescripcion() != null) {
					if (PrtParametros.getDescripcion().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcion"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcion() + " :: "+ PrtParametrosBk.getDescripcion());
						}
						cambios = true;
						PrtParametros.setDescripcion(PrtParametrosBk.getDescripcion());
					}
				} else if (PrtParametrosBk.getDescripcion() != null
						&& PrtParametros.getDescripcion() == null) {
					if (PrtParametrosBk.getDescripcion().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcion"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcion() + " :: "+ PrtParametrosBk.getDescripcion());
						}
						cambios = true;
						PrtParametros.setDescripcion(PrtParametrosBk.getDescripcion());
					}
				}
				 
		            if (PrtParametrosBk.getDescripcioncorta() != null
						&& PrtParametros.getDescripcioncorta() != null) {
					if (!PrtParametrosBk.getDescripcioncorta().equals(
						PrtParametros.getDescripcioncorta())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcioncorta"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcioncorta() + " :: "+ PrtParametrosBk.getDescripcioncorta());								
						}
						cambios = true;
						PrtParametros.setDescripcioncorta(PrtParametrosBk.getDescripcioncorta());
					}
				} else if (PrtParametrosBk.getDescripcioncorta() == null
						&& PrtParametros.getDescripcioncorta() != null) {
					if (PrtParametros.getDescripcioncorta().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcioncorta"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcioncorta() + " :: "+ PrtParametrosBk.getDescripcioncorta());
						}
						cambios = true;
						PrtParametros.setDescripcioncorta(PrtParametrosBk.getDescripcioncorta());
					}
				} else if (PrtParametrosBk.getDescripcioncorta() != null
						&& PrtParametros.getDescripcioncorta() == null) {
					if (PrtParametrosBk.getDescripcioncorta().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Descripcioncorta"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getDescripcioncorta() + " :: "+ PrtParametrosBk.getDescripcioncorta());
						}
						cambios = true;
						PrtParametros.setDescripcioncorta(PrtParametrosBk.getDescripcioncorta());
					}
				}
				if (PrtParametrosBk.getOrden() != null
							&& PrtParametros.getOrden() != null) {
						if (!PrtParametrosBk.getOrden().equals(
								PrtParametros.getOrden())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Orden"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getOrden() + " :: "+ PrtParametrosBk.getOrden());
								}
							cambios = true;
							PrtParametros.setOrden(PrtParametrosBk.getOrden());
						}
					} else if (PrtParametrosBk.getOrden() == null
							&& PrtParametros.getOrden() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Orden"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getOrden() + " :: "+ PrtParametrosBk.getOrden());
								}
							cambios = true;
							PrtParametros.setOrden(PrtParametrosBk.getOrden());
						
					} else if (PrtParametrosBk.getOrden() != null
							&& PrtParametros.getOrden() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Orden"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getOrden() + " :: "+ PrtParametrosBk.getOrden());
								}
							cambios = true;			
							PrtParametros.setOrden(PrtParametrosBk.getOrden());
					}
				if (PrtParametrosBk.getEstado() != null
							&& PrtParametros.getEstado() != null) {
						if (!PrtParametrosBk.getEstado().equals(
								PrtParametros.getEstado())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Estado"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getEstado() + " :: "+ PrtParametrosBk.getEstado());
								}
							cambios = true;
							PrtParametros.setEstado(PrtParametrosBk.getEstado());
						}
					} else if (PrtParametrosBk.getEstado() == null
							&& PrtParametros.getEstado() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Estado"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getEstado() + " :: "+ PrtParametrosBk.getEstado());
								}
							cambios = true;
							PrtParametros.setEstado(PrtParametrosBk.getEstado());
						
					} else if (PrtParametrosBk.getEstado() != null
							&& PrtParametros.getEstado() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"PrtParametros:Estado"+" :: "+PrtParametrosBk.getIdparametro().toString()+" :: "+ PrtParametros.getEstado() + " :: "+ PrtParametrosBk.getEstado());
								}
							cambios = true;			
							PrtParametros.setEstado(PrtParametrosBk.getEstado());
					}
				
			
			return cambios;
	}
	
}