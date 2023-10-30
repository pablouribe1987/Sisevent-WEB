package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdEvento;
import pe.gob.mef.sisevent.bs.transfer.bk.TdEventoBk;

/**
 * TD_EVENTO SERVICIO AUDITORIA Y CAMBIO: EVENTO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:35
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:35  / Creación de la clase /
 * 
 */
public class AuditoriaTdEventoMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdEventoMng.class.getName());
	
	public static boolean auditarCambiosTdEvento(TdEventoBk tdEventoBk, TdEvento tdEvento, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (tdEventoBk.getTitulo() != null
							&& tdEvento.getTitulo() != null) {
						if (!tdEventoBk.getTitulo().equals(
								tdEvento.getTitulo())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Titulo"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTitulo() + " :: "+ tdEventoBk.getTitulo());
								}
							cambios = true;
							tdEvento.setTitulo(tdEventoBk.getTitulo());
						}
					} else if (tdEventoBk.getTitulo() == null
							&& tdEvento.getTitulo() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Titulo"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTitulo() + " :: "+ tdEventoBk.getTitulo());
								}
							cambios = true;
							tdEvento.setTitulo(tdEventoBk.getTitulo());
						
					} else if (tdEventoBk.getTitulo() != null
							&& tdEvento.getTitulo() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Titulo"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTitulo() + " :: "+ tdEventoBk.getTitulo());
								}
							cambios = true;			
							tdEvento.setTitulo(tdEventoBk.getTitulo());
					}
				if (tdEventoBk.getFechaSoliIni() != null
							&& tdEvento.getFechaSoliIni() != null) {
						if (!tdEventoBk.getFechaSoliIni().equals(
								tdEvento.getFechaSoliIni())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliIni"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliIni() + " :: "+ tdEventoBk.getFechaSoliIni());
								}
							cambios = true;
							tdEvento.setFechaSoliIni(tdEventoBk.getFechaSoliIni());
						}
					} else if (tdEventoBk.getFechaSoliIni() == null
							&& tdEvento.getFechaSoliIni() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliIni"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliIni() + " :: "+ tdEventoBk.getFechaSoliIni());
								}
							cambios = true;
							tdEvento.setFechaSoliIni(tdEventoBk.getFechaSoliIni());
						
					} else if (tdEventoBk.getFechaSoliIni() != null
							&& tdEvento.getFechaSoliIni() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliIni"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliIni() + " :: "+ tdEventoBk.getFechaSoliIni());
								}
							cambios = true;			
							tdEvento.setFechaSoliIni(tdEventoBk.getFechaSoliIni());
					}
				if (tdEventoBk.getFechaSoliFin() != null
							&& tdEvento.getFechaSoliFin() != null) {
						if (!tdEventoBk.getFechaSoliFin().equals(
								tdEvento.getFechaSoliFin())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliFin"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliFin() + " :: "+ tdEventoBk.getFechaSoliFin());
								}
							cambios = true;
							tdEvento.setFechaSoliFin(tdEventoBk.getFechaSoliFin());
						}
					} else if (tdEventoBk.getFechaSoliFin() == null
							&& tdEvento.getFechaSoliFin() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliFin"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliFin() + " :: "+ tdEventoBk.getFechaSoliFin());
								}
							cambios = true;
							tdEvento.setFechaSoliFin(tdEventoBk.getFechaSoliFin());
						
					} else if (tdEventoBk.getFechaSoliFin() != null
							&& tdEvento.getFechaSoliFin() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:FechaSoliFin"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getFechaSoliFin() + " :: "+ tdEventoBk.getFechaSoliFin());
								}
							cambios = true;			
							tdEvento.setFechaSoliFin(tdEventoBk.getFechaSoliFin());
					}
				if (tdEventoBk.getPorconfirmar() != null
							&& tdEvento.getPorconfirmar() != null) {
						if (!tdEventoBk.getPorconfirmar().equals(
								tdEvento.getPorconfirmar())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Porconfirmar"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getPorconfirmar() + " :: "+ tdEventoBk.getPorconfirmar());
								}
							cambios = true;
							tdEvento.setPorconfirmar(tdEventoBk.getPorconfirmar());
						}
					} else if (tdEventoBk.getPorconfirmar() == null
							&& tdEvento.getPorconfirmar() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Porconfirmar"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getPorconfirmar() + " :: "+ tdEventoBk.getPorconfirmar());
								}
							cambios = true;
							tdEvento.setPorconfirmar(tdEventoBk.getPorconfirmar());
						
					} else if (tdEventoBk.getPorconfirmar() != null
							&& tdEvento.getPorconfirmar() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Porconfirmar"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getPorconfirmar() + " :: "+ tdEventoBk.getPorconfirmar());
								}
							cambios = true;			
							tdEvento.setPorconfirmar(tdEventoBk.getPorconfirmar());
					}
				if (tdEventoBk.getIddoc() != null
							&& tdEvento.getIddoc() != null) {
						if (!tdEventoBk.getIddoc().equals(
								tdEvento.getIddoc())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Iddoc"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIddoc() + " :: "+ tdEventoBk.getIddoc());
								}
							cambios = true;
							tdEvento.setIddoc(tdEventoBk.getIddoc());
						}
					} else if (tdEventoBk.getIddoc() == null
							&& tdEvento.getIddoc() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Iddoc"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIddoc() + " :: "+ tdEventoBk.getIddoc());
								}
							cambios = true;
							tdEvento.setIddoc(tdEventoBk.getIddoc());
						
					} else if (tdEventoBk.getIddoc() != null
							&& tdEvento.getIddoc() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Iddoc"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIddoc() + " :: "+ tdEventoBk.getIddoc());
								}
							cambios = true;			
							tdEvento.setIddoc(tdEventoBk.getIddoc());
					}
				 
		            if (tdEventoBk.getNumeroSid() != null
						&& tdEvento.getNumeroSid() != null) {
					if (!tdEventoBk.getNumeroSid().equals(
						tdEvento.getNumeroSid())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroSid() + " :: "+ tdEventoBk.getNumeroSid());								
						}
						cambios = true;
						tdEvento.setNumeroSid(tdEventoBk.getNumeroSid());
					}
				} else if (tdEventoBk.getNumeroSid() == null
						&& tdEvento.getNumeroSid() != null) {
					if (tdEvento.getNumeroSid().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroSid() + " :: "+ tdEventoBk.getNumeroSid());
						}
						cambios = true;
						tdEvento.setNumeroSid(tdEventoBk.getNumeroSid());
					}
				} else if (tdEventoBk.getNumeroSid() != null
						&& tdEvento.getNumeroSid() == null) {
					if (tdEventoBk.getNumeroSid().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroSid() + " :: "+ tdEventoBk.getNumeroSid());
						}
						cambios = true;
						tdEvento.setNumeroSid(tdEventoBk.getNumeroSid());
					}
				}
				if (tdEventoBk.getNumeroAnioSid() != null
							&& tdEvento.getNumeroAnioSid() != null) {
						if (!tdEventoBk.getNumeroAnioSid().equals(
								tdEvento.getNumeroAnioSid())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroAnioSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroAnioSid() + " :: "+ tdEventoBk.getNumeroAnioSid());
								}
							cambios = true;
							tdEvento.setNumeroAnioSid(tdEventoBk.getNumeroAnioSid());
						}
					} else if (tdEventoBk.getNumeroAnioSid() == null
							&& tdEvento.getNumeroAnioSid() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroAnioSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroAnioSid() + " :: "+ tdEventoBk.getNumeroAnioSid());
								}
							cambios = true;
							tdEvento.setNumeroAnioSid(tdEventoBk.getNumeroAnioSid());
						
					} else if (tdEventoBk.getNumeroAnioSid() != null
							&& tdEvento.getNumeroAnioSid() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:NumeroAnioSid"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getNumeroAnioSid() + " :: "+ tdEventoBk.getNumeroAnioSid());
								}
							cambios = true;			
							tdEvento.setNumeroAnioSid(tdEventoBk.getNumeroAnioSid());
					}
				if (tdEventoBk.getAsuntohr() != null
							&& tdEvento.getAsuntohr() != null) {
						if (!tdEventoBk.getAsuntohr().equals(
								tdEvento.getAsuntohr())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Asuntohr"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getAsuntohr() + " :: "+ tdEventoBk.getAsuntohr());
								}
							cambios = true;
							tdEvento.setAsuntohr(tdEventoBk.getAsuntohr());
						}
					} else if (tdEventoBk.getAsuntohr() == null
							&& tdEvento.getAsuntohr() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Asuntohr"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getAsuntohr() + " :: "+ tdEventoBk.getAsuntohr());
								}
							cambios = true;
							tdEvento.setAsuntohr(tdEventoBk.getAsuntohr());
						
					} else if (tdEventoBk.getAsuntohr() != null
							&& tdEvento.getAsuntohr() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Asuntohr"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getAsuntohr() + " :: "+ tdEventoBk.getAsuntohr());
								}
							cambios = true;			
							tdEvento.setAsuntohr(tdEventoBk.getAsuntohr());
					}
				 
		            if (tdEventoBk.getRemiRazonSocial() != null
						&& tdEvento.getRemiRazonSocial() != null) {
					if (!tdEventoBk.getRemiRazonSocial().equals(
						tdEvento.getRemiRazonSocial())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiRazonSocial"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiRazonSocial() + " :: "+ tdEventoBk.getRemiRazonSocial());								
						}
						cambios = true;
						tdEvento.setRemiRazonSocial(tdEventoBk.getRemiRazonSocial());
					}
				} else if (tdEventoBk.getRemiRazonSocial() == null
						&& tdEvento.getRemiRazonSocial() != null) {
					if (tdEvento.getRemiRazonSocial().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiRazonSocial"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiRazonSocial() + " :: "+ tdEventoBk.getRemiRazonSocial());
						}
						cambios = true;
						tdEvento.setRemiRazonSocial(tdEventoBk.getRemiRazonSocial());
					}
				} else if (tdEventoBk.getRemiRazonSocial() != null
						&& tdEvento.getRemiRazonSocial() == null) {
					if (tdEventoBk.getRemiRazonSocial().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiRazonSocial"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiRazonSocial() + " :: "+ tdEventoBk.getRemiRazonSocial());
						}
						cambios = true;
						tdEvento.setRemiRazonSocial(tdEventoBk.getRemiRazonSocial());
					}
				}
				if (tdEventoBk.getRemiIdprovee() != null
							&& tdEvento.getRemiIdprovee() != null) {
						if (!tdEventoBk.getRemiIdprovee().equals(
								tdEvento.getRemiIdprovee())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdprovee"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdprovee() + " :: "+ tdEventoBk.getRemiIdprovee());
								}
							cambios = true;
							tdEvento.setRemiIdprovee(tdEventoBk.getRemiIdprovee());
						}
					} else if (tdEventoBk.getRemiIdprovee() == null
							&& tdEvento.getRemiIdprovee() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdprovee"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdprovee() + " :: "+ tdEventoBk.getRemiIdprovee());
								}
							cambios = true;
							tdEvento.setRemiIdprovee(tdEventoBk.getRemiIdprovee());
						
					} else if (tdEventoBk.getRemiIdprovee() != null
							&& tdEvento.getRemiIdprovee() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdprovee"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdprovee() + " :: "+ tdEventoBk.getRemiIdprovee());
								}
							cambios = true;			
							tdEvento.setRemiIdprovee(tdEventoBk.getRemiIdprovee());
					}
				if (tdEventoBk.getRemiNombresapellidos() != null
							&& tdEvento.getRemiNombresapellidos() != null) {
						if (!tdEventoBk.getRemiNombresapellidos().equals(
								tdEvento.getRemiNombresapellidos())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiNombresapellidos"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiNombresapellidos() + " :: "+ tdEventoBk.getRemiNombresapellidos());
								}
							cambios = true;
							tdEvento.setRemiNombresapellidos(tdEventoBk.getRemiNombresapellidos());
						}
					} else if (tdEventoBk.getRemiNombresapellidos() == null
							&& tdEvento.getRemiNombresapellidos() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiNombresapellidos"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiNombresapellidos() + " :: "+ tdEventoBk.getRemiNombresapellidos());
								}
							cambios = true;
							tdEvento.setRemiNombresapellidos(tdEventoBk.getRemiNombresapellidos());
						
					} else if (tdEventoBk.getRemiNombresapellidos() != null
							&& tdEvento.getRemiNombresapellidos() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiNombresapellidos"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiNombresapellidos() + " :: "+ tdEventoBk.getRemiNombresapellidos());
								}
							cambios = true;			
							tdEvento.setRemiNombresapellidos(tdEventoBk.getRemiNombresapellidos());
					}
				if (tdEventoBk.getRemiIdperson() != null
							&& tdEvento.getRemiIdperson() != null) {
						if (!tdEventoBk.getRemiIdperson().equals(
								tdEvento.getRemiIdperson())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdperson"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdperson() + " :: "+ tdEventoBk.getRemiIdperson());
								}
							cambios = true;
							tdEvento.setRemiIdperson(tdEventoBk.getRemiIdperson());
						}
					} else if (tdEventoBk.getRemiIdperson() == null
							&& tdEvento.getRemiIdperson() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdperson"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdperson() + " :: "+ tdEventoBk.getRemiIdperson());
								}
							cambios = true;
							tdEvento.setRemiIdperson(tdEventoBk.getRemiIdperson());
						
					} else if (tdEventoBk.getRemiIdperson() != null
							&& tdEvento.getRemiIdperson() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:RemiIdperson"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getRemiIdperson() + " :: "+ tdEventoBk.getRemiIdperson());
								}
							cambios = true;			
							tdEvento.setRemiIdperson(tdEventoBk.getRemiIdperson());
					}
				if (tdEventoBk.getTipoevento() != null
							&& tdEvento.getTipoevento() != null) {
						if (!tdEventoBk.getTipoevento().equals(
								tdEvento.getTipoevento())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Tipoevento"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTipoevento() + " :: "+ tdEventoBk.getTipoevento());
								}
							cambios = true;
							tdEvento.setTipoevento(tdEventoBk.getTipoevento());
						}
					} else if (tdEventoBk.getTipoevento() == null
							&& tdEvento.getTipoevento() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Tipoevento"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTipoevento() + " :: "+ tdEventoBk.getTipoevento());
								}
							cambios = true;
							tdEvento.setTipoevento(tdEventoBk.getTipoevento());
						
					} else if (tdEventoBk.getTipoevento() != null
							&& tdEvento.getTipoevento() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Tipoevento"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getTipoevento() + " :: "+ tdEventoBk.getTipoevento());
								}
							cambios = true;			
							tdEvento.setTipoevento(tdEventoBk.getTipoevento());
					}
				if (tdEventoBk.getIdcategorias() != null
							&& tdEvento.getIdcategorias() != null) {
						if (!tdEventoBk.getIdcategorias().equals(
								tdEvento.getIdcategorias())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Idcategorias"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIdcategorias() + " :: "+ tdEventoBk.getIdcategorias());
								}
							cambios = true;
							tdEvento.setIdcategorias(tdEventoBk.getIdcategorias());
						}
					} else if (tdEventoBk.getIdcategorias() == null
							&& tdEvento.getIdcategorias() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Idcategorias"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIdcategorias() + " :: "+ tdEventoBk.getIdcategorias());
								}
							cambios = true;
							tdEvento.setIdcategorias(tdEventoBk.getIdcategorias());
						
					} else if (tdEventoBk.getIdcategorias() != null
							&& tdEvento.getIdcategorias() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Idcategorias"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getIdcategorias() + " :: "+ tdEventoBk.getIdcategorias());
								}
							cambios = true;			
							tdEvento.setIdcategorias(tdEventoBk.getIdcategorias());
					}
				if (tdEventoBk.getModalidad() != null
							&& tdEvento.getModalidad() != null) {
						if (!tdEventoBk.getModalidad().equals(
								tdEvento.getModalidad())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Modalidad"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getModalidad() + " :: "+ tdEventoBk.getModalidad());
								}
							cambios = true;
							tdEvento.setModalidad(tdEventoBk.getModalidad());
						}
					} else if (tdEventoBk.getModalidad() == null
							&& tdEvento.getModalidad() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Modalidad"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getModalidad() + " :: "+ tdEventoBk.getModalidad());
								}
							cambios = true;
							tdEvento.setModalidad(tdEventoBk.getModalidad());
						
					} else if (tdEventoBk.getModalidad() != null
							&& tdEvento.getModalidad() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdEvento:Modalidad"+" :: "+tdEventoBk.getIdevent().toString()+" :: "+ tdEvento.getModalidad() + " :: "+ tdEventoBk.getModalidad());
								}
							cambios = true;			
							tdEvento.setModalidad(tdEventoBk.getModalidad());
					}
				
			
			return cambios;
	}
	
}