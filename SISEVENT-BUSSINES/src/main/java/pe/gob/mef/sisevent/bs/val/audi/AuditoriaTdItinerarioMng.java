package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdItinerario;
import pe.gob.mef.sisevent.bs.transfer.bk.TdItinerarioBk;

/**
 * TD_ITINERARIO SERVICIO AUDITORIA Y CAMBIO: ITINERARIO DE LOS VUELOS A REALIZARCE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaTdItinerarioMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdItinerarioMng.class.getName());
	
	public static boolean auditarCambiosTdItinerario(TdItinerarioBk tdItinerarioBk, TdItinerario tdItinerario, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (tdItinerarioBk.getIdevent() != null
							&& tdItinerario.getIdevent() != null) {
						if (!tdItinerarioBk.getIdevent().equals(
								tdItinerario.getIdevent())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Idevent"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getIdevent() + " :: "+ tdItinerarioBk.getIdevent());
								}
							cambios = true;
							tdItinerario.setIdevent(tdItinerarioBk.getIdevent());
						}
					} else if (tdItinerarioBk.getIdevent() == null
							&& tdItinerario.getIdevent() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Idevent"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getIdevent() + " :: "+ tdItinerarioBk.getIdevent());
								}
							cambios = true;
							tdItinerario.setIdevent(tdItinerarioBk.getIdevent());
						
					} else if (tdItinerarioBk.getIdevent() != null
							&& tdItinerario.getIdevent() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Idevent"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getIdevent() + " :: "+ tdItinerarioBk.getIdevent());
								}
							cambios = true;			
							tdItinerario.setIdevent(tdItinerarioBk.getIdevent());
					}
				if (tdItinerarioBk.getCodpais() != null
							&& tdItinerario.getCodpais() != null) {
						if (!tdItinerarioBk.getCodpais().equals(
								tdItinerario.getCodpais())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codpais"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodpais() + " :: "+ tdItinerarioBk.getCodpais());
								}
							cambios = true;
							tdItinerario.setCodpais(tdItinerarioBk.getCodpais());
						}
					} else if (tdItinerarioBk.getCodpais() == null
							&& tdItinerario.getCodpais() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codpais"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodpais() + " :: "+ tdItinerarioBk.getCodpais());
								}
							cambios = true;
							tdItinerario.setCodpais(tdItinerarioBk.getCodpais());
						
					} else if (tdItinerarioBk.getCodpais() != null
							&& tdItinerario.getCodpais() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codpais"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodpais() + " :: "+ tdItinerarioBk.getCodpais());
								}
							cambios = true;			
							tdItinerario.setCodpais(tdItinerarioBk.getCodpais());
					}
				if (tdItinerarioBk.getCoddpto() != null
							&& tdItinerario.getCoddpto() != null) {
						if (!tdItinerarioBk.getCoddpto().equals(
								tdItinerario.getCoddpto())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Coddpto"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCoddpto() + " :: "+ tdItinerarioBk.getCoddpto());
								}
							cambios = true;
							tdItinerario.setCoddpto(tdItinerarioBk.getCoddpto());
						}
					} else if (tdItinerarioBk.getCoddpto() == null
							&& tdItinerario.getCoddpto() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Coddpto"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCoddpto() + " :: "+ tdItinerarioBk.getCoddpto());
								}
							cambios = true;
							tdItinerario.setCoddpto(tdItinerarioBk.getCoddpto());
						
					} else if (tdItinerarioBk.getCoddpto() != null
							&& tdItinerario.getCoddpto() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Coddpto"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCoddpto() + " :: "+ tdItinerarioBk.getCoddpto());
								}
							cambios = true;			
							tdItinerario.setCoddpto(tdItinerarioBk.getCoddpto());
					}
				if (tdItinerarioBk.getCodprov() != null
							&& tdItinerario.getCodprov() != null) {
						if (!tdItinerarioBk.getCodprov().equals(
								tdItinerario.getCodprov())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codprov"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodprov() + " :: "+ tdItinerarioBk.getCodprov());
								}
							cambios = true;
							tdItinerario.setCodprov(tdItinerarioBk.getCodprov());
						}
					} else if (tdItinerarioBk.getCodprov() == null
							&& tdItinerario.getCodprov() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codprov"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodprov() + " :: "+ tdItinerarioBk.getCodprov());
								}
							cambios = true;
							tdItinerario.setCodprov(tdItinerarioBk.getCodprov());
						
					} else if (tdItinerarioBk.getCodprov() != null
							&& tdItinerario.getCodprov() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Codprov"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getCodprov() + " :: "+ tdItinerarioBk.getCodprov());
								}
							cambios = true;			
							tdItinerario.setCodprov(tdItinerarioBk.getCodprov());
					}
				if (tdItinerarioBk.getOrigendestino() != null
							&& tdItinerario.getOrigendestino() != null) {
						if (!tdItinerarioBk.getOrigendestino().equals(
								tdItinerario.getOrigendestino())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Origendestino"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getOrigendestino() + " :: "+ tdItinerarioBk.getOrigendestino());
								}
							cambios = true;
							tdItinerario.setOrigendestino(tdItinerarioBk.getOrigendestino());
						}
					} else if (tdItinerarioBk.getOrigendestino() == null
							&& tdItinerario.getOrigendestino() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Origendestino"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getOrigendestino() + " :: "+ tdItinerarioBk.getOrigendestino());
								}
							cambios = true;
							tdItinerario.setOrigendestino(tdItinerarioBk.getOrigendestino());
						
					} else if (tdItinerarioBk.getOrigendestino() != null
							&& tdItinerario.getOrigendestino() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:Origendestino"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getOrigendestino() + " :: "+ tdItinerarioBk.getOrigendestino());
								}
							cambios = true;			
							tdItinerario.setOrigendestino(tdItinerarioBk.getOrigendestino());
					}
				if (tdItinerarioBk.getFechaSaliIni() != null
							&& tdItinerario.getFechaSaliIni() != null) {
						if (!tdItinerarioBk.getFechaSaliIni().equals(
								tdItinerario.getFechaSaliIni())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaSaliIni"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaSaliIni() + " :: "+ tdItinerarioBk.getFechaSaliIni());
								}
							cambios = true;
							tdItinerario.setFechaSaliIni(tdItinerarioBk.getFechaSaliIni());
						}
					} else if (tdItinerarioBk.getFechaSaliIni() == null
							&& tdItinerario.getFechaSaliIni() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaSaliIni"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaSaliIni() + " :: "+ tdItinerarioBk.getFechaSaliIni());
								}
							cambios = true;
							tdItinerario.setFechaSaliIni(tdItinerarioBk.getFechaSaliIni());
						
					} else if (tdItinerarioBk.getFechaSaliIni() != null
							&& tdItinerario.getFechaSaliIni() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaSaliIni"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaSaliIni() + " :: "+ tdItinerarioBk.getFechaSaliIni());
								}
							cambios = true;			
							tdItinerario.setFechaSaliIni(tdItinerarioBk.getFechaSaliIni());
					}
				if (tdItinerarioBk.getFechaLlegFin() != null
							&& tdItinerario.getFechaLlegFin() != null) {
						if (!tdItinerarioBk.getFechaLlegFin().equals(
								tdItinerario.getFechaLlegFin())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaLlegFin"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaLlegFin() + " :: "+ tdItinerarioBk.getFechaLlegFin());
								}
							cambios = true;
							tdItinerario.setFechaLlegFin(tdItinerarioBk.getFechaLlegFin());
						}
					} else if (tdItinerarioBk.getFechaLlegFin() == null
							&& tdItinerario.getFechaLlegFin() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaLlegFin"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaLlegFin() + " :: "+ tdItinerarioBk.getFechaLlegFin());
								}
							cambios = true;
							tdItinerario.setFechaLlegFin(tdItinerarioBk.getFechaLlegFin());
						
					} else if (tdItinerarioBk.getFechaLlegFin() != null
							&& tdItinerario.getFechaLlegFin() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdItinerario:FechaLlegFin"+" :: "+tdItinerarioBk.getIditinerario().toString()+" :: "+ tdItinerario.getFechaLlegFin() + " :: "+ tdItinerarioBk.getFechaLlegFin());
								}
							cambios = true;			
							tdItinerario.setFechaLlegFin(tdItinerarioBk.getFechaLlegFin());
					}
				
			
			return cambios;
	}
	
}