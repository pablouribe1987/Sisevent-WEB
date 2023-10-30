package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdParticipantes;
import pe.gob.mef.sisevent.bs.transfer.bk.TdParticipantesBk;

/**
 * TD_PARTICIPANTES SERVICIO AUDITORIA Y CAMBIO: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaTdParticipantesMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdParticipantesMng.class.getName());
	
	public static boolean auditarCambiosTdParticipantes(TdParticipantesBk tdParticipantesBk, TdParticipantes tdParticipantes, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (tdParticipantesBk.getIdusuarioIdproveeIdperson() != null
							&& tdParticipantes.getIdusuarioIdproveeIdperson() != null) {
						if (!tdParticipantesBk.getIdusuarioIdproveeIdperson().equals(
								tdParticipantes.getIdusuarioIdproveeIdperson())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:IdusuarioIdproveeIdperson"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getIdusuarioIdproveeIdperson() + " :: "+ tdParticipantesBk.getIdusuarioIdproveeIdperson());
								}
							cambios = true;
							tdParticipantes.setIdusuarioIdproveeIdperson(tdParticipantesBk.getIdusuarioIdproveeIdperson());
						}
					} else if (tdParticipantesBk.getIdusuarioIdproveeIdperson() == null
							&& tdParticipantes.getIdusuarioIdproveeIdperson() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:IdusuarioIdproveeIdperson"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getIdusuarioIdproveeIdperson() + " :: "+ tdParticipantesBk.getIdusuarioIdproveeIdperson());
								}
							cambios = true;
							tdParticipantes.setIdusuarioIdproveeIdperson(tdParticipantesBk.getIdusuarioIdproveeIdperson());
						
					} else if (tdParticipantesBk.getIdusuarioIdproveeIdperson() != null
							&& tdParticipantes.getIdusuarioIdproveeIdperson() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:IdusuarioIdproveeIdperson"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getIdusuarioIdproveeIdperson() + " :: "+ tdParticipantesBk.getIdusuarioIdproveeIdperson());
								}
							cambios = true;			
							tdParticipantes.setIdusuarioIdproveeIdperson(tdParticipantesBk.getIdusuarioIdproveeIdperson());
					}
				if (tdParticipantesBk.getNombresrazonsocial() != null
							&& tdParticipantes.getNombresrazonsocial() != null) {
						if (!tdParticipantesBk.getNombresrazonsocial().equals(
								tdParticipantes.getNombresrazonsocial())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Nombresrazonsocial"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getNombresrazonsocial() + " :: "+ tdParticipantesBk.getNombresrazonsocial());
								}
							cambios = true;
							tdParticipantes.setNombresrazonsocial(tdParticipantesBk.getNombresrazonsocial());
						}
					} else if (tdParticipantesBk.getNombresrazonsocial() == null
							&& tdParticipantes.getNombresrazonsocial() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Nombresrazonsocial"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getNombresrazonsocial() + " :: "+ tdParticipantesBk.getNombresrazonsocial());
								}
							cambios = true;
							tdParticipantes.setNombresrazonsocial(tdParticipantesBk.getNombresrazonsocial());
						
					} else if (tdParticipantesBk.getNombresrazonsocial() != null
							&& tdParticipantes.getNombresrazonsocial() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Nombresrazonsocial"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getNombresrazonsocial() + " :: "+ tdParticipantesBk.getNombresrazonsocial());
								}
							cambios = true;			
							tdParticipantes.setNombresrazonsocial(tdParticipantesBk.getNombresrazonsocial());
					}
				if (tdParticipantesBk.getTipo() != null
							&& tdParticipantes.getTipo() != null) {
						if (!tdParticipantesBk.getTipo().equals(
								tdParticipantes.getTipo())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Tipo"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getTipo() + " :: "+ tdParticipantesBk.getTipo());
								}
							cambios = true;
							tdParticipantes.setTipo(tdParticipantesBk.getTipo());
						}
					} else if (tdParticipantesBk.getTipo() == null
							&& tdParticipantes.getTipo() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Tipo"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getTipo() + " :: "+ tdParticipantesBk.getTipo());
								}
							cambios = true;
							tdParticipantes.setTipo(tdParticipantesBk.getTipo());
						
					} else if (tdParticipantesBk.getTipo() != null
							&& tdParticipantes.getTipo() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Tipo"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getTipo() + " :: "+ tdParticipantesBk.getTipo());
								}
							cambios = true;			
							tdParticipantes.setTipo(tdParticipantesBk.getTipo());
					}
				if (tdParticipantesBk.getFlagacompaniante() != null
							&& tdParticipantes.getFlagacompaniante() != null) {
						if (!tdParticipantesBk.getFlagacompaniante().equals(
								tdParticipantes.getFlagacompaniante())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Flagacompaniante"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getFlagacompaniante() + " :: "+ tdParticipantesBk.getFlagacompaniante());
								}
							cambios = true;
							tdParticipantes.setFlagacompaniante(tdParticipantesBk.getFlagacompaniante());
						}
					} else if (tdParticipantesBk.getFlagacompaniante() == null
							&& tdParticipantes.getFlagacompaniante() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Flagacompaniante"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getFlagacompaniante() + " :: "+ tdParticipantesBk.getFlagacompaniante());
								}
							cambios = true;
							tdParticipantes.setFlagacompaniante(tdParticipantesBk.getFlagacompaniante());
						
					} else if (tdParticipantesBk.getFlagacompaniante() != null
							&& tdParticipantes.getFlagacompaniante() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdParticipantes:Flagacompaniante"+" :: "+tdParticipantesBk.getIdparticipantes().toString()+" :: "+ tdParticipantes.getFlagacompaniante() + " :: "+ tdParticipantesBk.getFlagacompaniante());
								}
							cambios = true;			
							tdParticipantes.setFlagacompaniante(tdParticipantesBk.getFlagacompaniante());
					}
				
			
			return cambios;
	}
	
}