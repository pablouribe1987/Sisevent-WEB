package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdGruposflujos;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;

/**
 * TD_GRUPOSFLUJOS SERVICIO AUDITORIA Y CAMBIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaTdGruposflujosMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdGruposflujosMng.class.getName());
	
	public static boolean auditarCambiosTdGruposflujos(TdGruposflujosBk tdGruposflujosBk, TdGruposflujos tdGruposflujos, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (tdGruposflujosBk.getIdtareas() != null
							&& tdGruposflujos.getIdtareas() != null) {
						if (!tdGruposflujosBk.getIdtareas().equals(
								tdGruposflujos.getIdtareas())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idtareas"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdtareas() + " :: "+ tdGruposflujosBk.getIdtareas());
								}
							cambios = true;
							tdGruposflujos.setIdtareas(tdGruposflujosBk.getIdtareas());
						}
					} else if (tdGruposflujosBk.getIdtareas() == null
							&& tdGruposflujos.getIdtareas() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idtareas"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdtareas() + " :: "+ tdGruposflujosBk.getIdtareas());
								}
							cambios = true;
							tdGruposflujos.setIdtareas(tdGruposflujosBk.getIdtareas());
						
					} else if (tdGruposflujosBk.getIdtareas() != null
							&& tdGruposflujos.getIdtareas() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idtareas"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdtareas() + " :: "+ tdGruposflujosBk.getIdtareas());
								}
							cambios = true;			
							tdGruposflujos.setIdtareas(tdGruposflujosBk.getIdtareas());
					}
				if (tdGruposflujosBk.getIdunidadDestino() != null
							&& tdGruposflujos.getIdunidadDestino() != null) {
						if (!tdGruposflujosBk.getIdunidadDestino().equals(
								tdGruposflujos.getIdunidadDestino())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IdunidadDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdunidadDestino() + " :: "+ tdGruposflujosBk.getIdunidadDestino());
								}
							cambios = true;
							tdGruposflujos.setIdunidadDestino(tdGruposflujosBk.getIdunidadDestino());
						}
					} else if (tdGruposflujosBk.getIdunidadDestino() == null
							&& tdGruposflujos.getIdunidadDestino() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IdunidadDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdunidadDestino() + " :: "+ tdGruposflujosBk.getIdunidadDestino());
								}
							cambios = true;
							tdGruposflujos.setIdunidadDestino(tdGruposflujosBk.getIdunidadDestino());
						
					} else if (tdGruposflujosBk.getIdunidadDestino() != null
							&& tdGruposflujos.getIdunidadDestino() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IdunidadDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdunidadDestino() + " :: "+ tdGruposflujosBk.getIdunidadDestino());
								}
							cambios = true;			
							tdGruposflujos.setIdunidadDestino(tdGruposflujosBk.getIdunidadDestino());
					}
				if (tdGruposflujosBk.getIduserDestino() != null
							&& tdGruposflujos.getIduserDestino() != null) {
						if (!tdGruposflujosBk.getIduserDestino().equals(
								tdGruposflujos.getIduserDestino())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IduserDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIduserDestino() + " :: "+ tdGruposflujosBk.getIduserDestino());
								}
							cambios = true;
							tdGruposflujos.setIduserDestino(tdGruposflujosBk.getIduserDestino());
						}
					} else if (tdGruposflujosBk.getIduserDestino() == null
							&& tdGruposflujos.getIduserDestino() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IduserDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIduserDestino() + " :: "+ tdGruposflujosBk.getIduserDestino());
								}
							cambios = true;
							tdGruposflujos.setIduserDestino(tdGruposflujosBk.getIduserDestino());
						
					} else if (tdGruposflujosBk.getIduserDestino() != null
							&& tdGruposflujos.getIduserDestino() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:IduserDestino"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIduserDestino() + " :: "+ tdGruposflujosBk.getIduserDestino());
								}
							cambios = true;			
							tdGruposflujos.setIduserDestino(tdGruposflujosBk.getIduserDestino());
					}
				if (tdGruposflujosBk.getObservacion() != null
							&& tdGruposflujos.getObservacion() != null) {
						if (!tdGruposflujosBk.getObservacion().equals(
								tdGruposflujos.getObservacion())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Observacion"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacion() + " :: "+ tdGruposflujosBk.getObservacion());
								}
							cambios = true;
							tdGruposflujos.setObservacion(tdGruposflujosBk.getObservacion());
						}
					} else if (tdGruposflujosBk.getObservacion() == null
							&& tdGruposflujos.getObservacion() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Observacion"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacion() + " :: "+ tdGruposflujosBk.getObservacion());
								}
							cambios = true;
							tdGruposflujos.setObservacion(tdGruposflujosBk.getObservacion());
						
					} else if (tdGruposflujosBk.getObservacion() != null
							&& tdGruposflujos.getObservacion() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Observacion"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacion() + " :: "+ tdGruposflujosBk.getObservacion());
								}
							cambios = true;			
							tdGruposflujos.setObservacion(tdGruposflujosBk.getObservacion());
					}
				if (tdGruposflujosBk.getObservacionHtml() != null
							&& tdGruposflujos.getObservacionHtml() != null) {
						if (!tdGruposflujosBk.getObservacionHtml().equals(
								tdGruposflujos.getObservacionHtml())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:ObservacionHtml"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacionHtml() + " :: "+ tdGruposflujosBk.getObservacionHtml());
								}
							cambios = true;
							tdGruposflujos.setObservacionHtml(tdGruposflujosBk.getObservacionHtml());
						}
					} else if (tdGruposflujosBk.getObservacionHtml() == null
							&& tdGruposflujos.getObservacionHtml() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:ObservacionHtml"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacionHtml() + " :: "+ tdGruposflujosBk.getObservacionHtml());
								}
							cambios = true;
							tdGruposflujos.setObservacionHtml(tdGruposflujosBk.getObservacionHtml());
						
					} else if (tdGruposflujosBk.getObservacionHtml() != null
							&& tdGruposflujos.getObservacionHtml() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:ObservacionHtml"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getObservacionHtml() + " :: "+ tdGruposflujosBk.getObservacionHtml());
								}
							cambios = true;			
							tdGruposflujos.setObservacionHtml(tdGruposflujosBk.getObservacionHtml());
					}
				if (tdGruposflujosBk.getTiempoestadia() != null
							&& tdGruposflujos.getTiempoestadia() != null) {
						if (!tdGruposflujosBk.getTiempoestadia().equals(
								tdGruposflujos.getTiempoestadia())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Tiempoestadia"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getTiempoestadia() + " :: "+ tdGruposflujosBk.getTiempoestadia());
								}
							cambios = true;
							tdGruposflujos.setTiempoestadia(tdGruposflujosBk.getTiempoestadia());
						}
					} else if (tdGruposflujosBk.getTiempoestadia() == null
							&& tdGruposflujos.getTiempoestadia() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Tiempoestadia"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getTiempoestadia() + " :: "+ tdGruposflujosBk.getTiempoestadia());
								}
							cambios = true;
							tdGruposflujos.setTiempoestadia(tdGruposflujosBk.getTiempoestadia());
						
					} else if (tdGruposflujosBk.getTiempoestadia() != null
							&& tdGruposflujos.getTiempoestadia() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Tiempoestadia"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getTiempoestadia() + " :: "+ tdGruposflujosBk.getTiempoestadia());
								}
							cambios = true;			
							tdGruposflujos.setTiempoestadia(tdGruposflujosBk.getTiempoestadia());
					}
				 
		            if (tdGruposflujosBk.getCorreosnotif() != null
						&& tdGruposflujos.getCorreosnotif() != null) {
					if (!tdGruposflujosBk.getCorreosnotif().equals(
						tdGruposflujos.getCorreosnotif())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Correosnotif"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getCorreosnotif() + " :: "+ tdGruposflujosBk.getCorreosnotif());								
						}
						cambios = true;
						tdGruposflujos.setCorreosnotif(tdGruposflujosBk.getCorreosnotif());
					}
				} else if (tdGruposflujosBk.getCorreosnotif() == null
						&& tdGruposflujos.getCorreosnotif() != null) {
					if (tdGruposflujos.getCorreosnotif().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Correosnotif"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getCorreosnotif() + " :: "+ tdGruposflujosBk.getCorreosnotif());
						}
						cambios = true;
						tdGruposflujos.setCorreosnotif(tdGruposflujosBk.getCorreosnotif());
					}
				} else if (tdGruposflujosBk.getCorreosnotif() != null
						&& tdGruposflujos.getCorreosnotif() == null) {
					if (tdGruposflujosBk.getCorreosnotif().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Correosnotif"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getCorreosnotif() + " :: "+ tdGruposflujosBk.getCorreosnotif());
						}
						cambios = true;
						tdGruposflujos.setCorreosnotif(tdGruposflujosBk.getCorreosnotif());
					}
				}
				if (tdGruposflujosBk.getIdgrupo() != null
							&& tdGruposflujos.getIdgrupo() != null) {
						if (!tdGruposflujosBk.getIdgrupo().equals(
								tdGruposflujos.getIdgrupo())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idgrupo"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdgrupo() + " :: "+ tdGruposflujosBk.getIdgrupo());
								}
							cambios = true;
							tdGruposflujos.setIdgrupo(tdGruposflujosBk.getIdgrupo());
						}
					} else if (tdGruposflujosBk.getIdgrupo() == null
							&& tdGruposflujos.getIdgrupo() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idgrupo"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdgrupo() + " :: "+ tdGruposflujosBk.getIdgrupo());
								}
							cambios = true;
							tdGruposflujos.setIdgrupo(tdGruposflujosBk.getIdgrupo());
						
					} else if (tdGruposflujosBk.getIdgrupo() != null
							&& tdGruposflujos.getIdgrupo() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGruposflujos:Idgrupo"+" :: "+tdGruposflujosBk.getIdgruposflujos().toString()+" :: "+ tdGruposflujos.getIdgrupo() + " :: "+ tdGruposflujosBk.getIdgrupo());
								}
							cambios = true;			
							tdGruposflujos.setIdgrupo(tdGruposflujosBk.getIdgrupo());
					}
				
			
			return cambios;
	}
	
}