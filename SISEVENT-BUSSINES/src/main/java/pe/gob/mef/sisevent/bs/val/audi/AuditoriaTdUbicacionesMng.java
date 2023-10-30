package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdUbicaciones;
import pe.gob.mef.sisevent.bs.transfer.bk.TdUbicacionesBk;

/**
 * TD_UBICACIONES SERVICIO AUDITORIA Y CAMBIO: UBICACIONES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaTdUbicacionesMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdUbicacionesMng.class.getName());
	
	public static boolean auditarCambiosTdUbicaciones(TdUbicacionesBk tdUbicacionesBk, TdUbicaciones tdUbicaciones, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (tdUbicacionesBk.getIdevent() != null
							&& tdUbicaciones.getIdevent() != null) {
						if (!tdUbicacionesBk.getIdevent().equals(
								tdUbicaciones.getIdevent())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Idevent"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIdevent() + " :: "+ tdUbicacionesBk.getIdevent());
								}
							cambios = true;
							tdUbicaciones.setIdevent(tdUbicacionesBk.getIdevent());
						}
					} else if (tdUbicacionesBk.getIdevent() == null
							&& tdUbicaciones.getIdevent() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Idevent"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIdevent() + " :: "+ tdUbicacionesBk.getIdevent());
								}
							cambios = true;
							tdUbicaciones.setIdevent(tdUbicacionesBk.getIdevent());
						
					} else if (tdUbicacionesBk.getIdevent() != null
							&& tdUbicaciones.getIdevent() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Idevent"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIdevent() + " :: "+ tdUbicacionesBk.getIdevent());
								}
							cambios = true;			
							tdUbicaciones.setIdevent(tdUbicacionesBk.getIdevent());
					}
				if (tdUbicacionesBk.getCodpais() != null
							&& tdUbicaciones.getCodpais() != null) {
						if (!tdUbicacionesBk.getCodpais().equals(
								tdUbicaciones.getCodpais())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codpais"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodpais() + " :: "+ tdUbicacionesBk.getCodpais());
								}
							cambios = true;
							tdUbicaciones.setCodpais(tdUbicacionesBk.getCodpais());
						}
					} else if (tdUbicacionesBk.getCodpais() == null
							&& tdUbicaciones.getCodpais() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codpais"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodpais() + " :: "+ tdUbicacionesBk.getCodpais());
								}
							cambios = true;
							tdUbicaciones.setCodpais(tdUbicacionesBk.getCodpais());
						
					} else if (tdUbicacionesBk.getCodpais() != null
							&& tdUbicaciones.getCodpais() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codpais"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodpais() + " :: "+ tdUbicacionesBk.getCodpais());
								}
							cambios = true;			
							tdUbicaciones.setCodpais(tdUbicacionesBk.getCodpais());
					}
				if (tdUbicacionesBk.getCoddpto() != null
							&& tdUbicaciones.getCoddpto() != null) {
						if (!tdUbicacionesBk.getCoddpto().equals(
								tdUbicaciones.getCoddpto())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddpto"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddpto() + " :: "+ tdUbicacionesBk.getCoddpto());
								}
							cambios = true;
							tdUbicaciones.setCoddpto(tdUbicacionesBk.getCoddpto());
						}
					} else if (tdUbicacionesBk.getCoddpto() == null
							&& tdUbicaciones.getCoddpto() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddpto"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddpto() + " :: "+ tdUbicacionesBk.getCoddpto());
								}
							cambios = true;
							tdUbicaciones.setCoddpto(tdUbicacionesBk.getCoddpto());
						
					} else if (tdUbicacionesBk.getCoddpto() != null
							&& tdUbicaciones.getCoddpto() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddpto"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddpto() + " :: "+ tdUbicacionesBk.getCoddpto());
								}
							cambios = true;			
							tdUbicaciones.setCoddpto(tdUbicacionesBk.getCoddpto());
					}
				if (tdUbicacionesBk.getCodprov() != null
							&& tdUbicaciones.getCodprov() != null) {
						if (!tdUbicacionesBk.getCodprov().equals(
								tdUbicaciones.getCodprov())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codprov"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodprov() + " :: "+ tdUbicacionesBk.getCodprov());
								}
							cambios = true;
							tdUbicaciones.setCodprov(tdUbicacionesBk.getCodprov());
						}
					} else if (tdUbicacionesBk.getCodprov() == null
							&& tdUbicaciones.getCodprov() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codprov"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodprov() + " :: "+ tdUbicacionesBk.getCodprov());
								}
							cambios = true;
							tdUbicaciones.setCodprov(tdUbicacionesBk.getCodprov());
						
					} else if (tdUbicacionesBk.getCodprov() != null
							&& tdUbicaciones.getCodprov() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Codprov"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCodprov() + " :: "+ tdUbicacionesBk.getCodprov());
								}
							cambios = true;			
							tdUbicaciones.setCodprov(tdUbicacionesBk.getCodprov());
					}
				if (tdUbicacionesBk.getCoddist() != null
							&& tdUbicaciones.getCoddist() != null) {
						if (!tdUbicacionesBk.getCoddist().equals(
								tdUbicaciones.getCoddist())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddist"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddist() + " :: "+ tdUbicacionesBk.getCoddist());
								}
							cambios = true;
							tdUbicaciones.setCoddist(tdUbicacionesBk.getCoddist());
						}
					} else if (tdUbicacionesBk.getCoddist() == null
							&& tdUbicaciones.getCoddist() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddist"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddist() + " :: "+ tdUbicacionesBk.getCoddist());
								}
							cambios = true;
							tdUbicaciones.setCoddist(tdUbicacionesBk.getCoddist());
						
					} else if (tdUbicacionesBk.getCoddist() != null
							&& tdUbicaciones.getCoddist() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Coddist"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getCoddist() + " :: "+ tdUbicacionesBk.getCoddist());
								}
							cambios = true;			
							tdUbicaciones.setCoddist(tdUbicacionesBk.getCoddist());
					}
				if (tdUbicacionesBk.getUbicacion() != null
							&& tdUbicaciones.getUbicacion() != null) {
						if (!tdUbicacionesBk.getUbicacion().equals(
								tdUbicaciones.getUbicacion())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Ubicacion"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getUbicacion() + " :: "+ tdUbicacionesBk.getUbicacion());
								}
							cambios = true;
							tdUbicaciones.setUbicacion(tdUbicacionesBk.getUbicacion());
						}
					} else if (tdUbicacionesBk.getUbicacion() == null
							&& tdUbicaciones.getUbicacion() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Ubicacion"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getUbicacion() + " :: "+ tdUbicacionesBk.getUbicacion());
								}
							cambios = true;
							tdUbicaciones.setUbicacion(tdUbicacionesBk.getUbicacion());
						
					} else if (tdUbicacionesBk.getUbicacion() != null
							&& tdUbicaciones.getUbicacion() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:Ubicacion"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getUbicacion() + " :: "+ tdUbicacionesBk.getUbicacion());
								}
							cambios = true;			
							tdUbicaciones.setUbicacion(tdUbicacionesBk.getUbicacion());
					}
				if (tdUbicacionesBk.getFechaActivIni() != null
							&& tdUbicaciones.getFechaActivIni() != null) {
						if (!tdUbicacionesBk.getFechaActivIni().equals(
								tdUbicaciones.getFechaActivIni())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivIni"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivIni() + " :: "+ tdUbicacionesBk.getFechaActivIni());
								}
							cambios = true;
							tdUbicaciones.setFechaActivIni(tdUbicacionesBk.getFechaActivIni());
						}
					} else if (tdUbicacionesBk.getFechaActivIni() == null
							&& tdUbicaciones.getFechaActivIni() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivIni"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivIni() + " :: "+ tdUbicacionesBk.getFechaActivIni());
								}
							cambios = true;
							tdUbicaciones.setFechaActivIni(tdUbicacionesBk.getFechaActivIni());
						
					} else if (tdUbicacionesBk.getFechaActivIni() != null
							&& tdUbicaciones.getFechaActivIni() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivIni"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivIni() + " :: "+ tdUbicacionesBk.getFechaActivIni());
								}
							cambios = true;			
							tdUbicaciones.setFechaActivIni(tdUbicacionesBk.getFechaActivIni());
					}
				if (tdUbicacionesBk.getFechaActivFin() != null
							&& tdUbicaciones.getFechaActivFin() != null) {
						if (!tdUbicacionesBk.getFechaActivFin().equals(
								tdUbicaciones.getFechaActivFin())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivFin"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivFin() + " :: "+ tdUbicacionesBk.getFechaActivFin());
								}
							cambios = true;
							tdUbicaciones.setFechaActivFin(tdUbicacionesBk.getFechaActivFin());
						}
					} else if (tdUbicacionesBk.getFechaActivFin() == null
							&& tdUbicaciones.getFechaActivFin() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivFin"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivFin() + " :: "+ tdUbicacionesBk.getFechaActivFin());
								}
							cambios = true;
							tdUbicaciones.setFechaActivFin(tdUbicacionesBk.getFechaActivFin());
						
					} else if (tdUbicacionesBk.getFechaActivFin() != null
							&& tdUbicaciones.getFechaActivFin() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:FechaActivFin"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getFechaActivFin() + " :: "+ tdUbicacionesBk.getFechaActivFin());
								}
							cambios = true;			
							tdUbicaciones.setFechaActivFin(tdUbicacionesBk.getFechaActivFin());
					}
				if (tdUbicacionesBk.getIduserModif() != null
							&& tdUbicaciones.getIduserModif() != null) {
						if (!tdUbicacionesBk.getIduserModif().equals(
								tdUbicaciones.getIduserModif())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:IduserModif"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIduserModif() + " :: "+ tdUbicacionesBk.getIduserModif());
								}
							cambios = true;
							tdUbicaciones.setIduserModif(tdUbicacionesBk.getIduserModif());
						}
					} else if (tdUbicacionesBk.getIduserModif() == null
							&& tdUbicaciones.getIduserModif() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:IduserModif"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIduserModif() + " :: "+ tdUbicacionesBk.getIduserModif());
								}
							cambios = true;
							tdUbicaciones.setIduserModif(tdUbicacionesBk.getIduserModif());
						
					} else if (tdUbicacionesBk.getIduserModif() != null
							&& tdUbicaciones.getIduserModif() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdUbicaciones:IduserModif"+" :: "+tdUbicacionesBk.getIdubicaciones().toString()+" :: "+ tdUbicaciones.getIduserModif() + " :: "+ tdUbicacionesBk.getIduserModif());
								}
							cambios = true;			
							tdUbicaciones.setIduserModif(tdUbicacionesBk.getIduserModif());
					}
				
			
			return cambios;
	}
	
}