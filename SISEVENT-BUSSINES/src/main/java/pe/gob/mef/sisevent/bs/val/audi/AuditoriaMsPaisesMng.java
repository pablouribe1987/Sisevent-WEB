package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsPaises;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPaisesBk;

/**
 * MS_PAISES SERVICIO AUDITORIA Y CAMBIO: PAISES SEGÚN ISO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /22/12/2020 17:45  / Creación de la clase /
 * 
 */
public class AuditoriaMsPaisesMng implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8914407634212446412L;
	public static final Logger log = Logger.getLogger(AuditoriaMsPaisesMng.class.getName());
	
	public static boolean auditarCambiosMsPaises(MsPaisesBk msPaisesBk, MsPaises msPaises, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		if (msPaisesBk.getPaiIsonum() != null
							&& msPaises.getPaiIsonum() != null) {
						if (!msPaisesBk.getPaiIsonum().equals(
								msPaises.getPaiIsonum())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIsonum"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIsonum() + " :: "+ msPaisesBk.getPaiIsonum());
								}
							cambios = true;
							msPaises.setPaiIsonum(msPaisesBk.getPaiIsonum());
						}
					} else if (msPaisesBk.getPaiIsonum() == null
							&& msPaises.getPaiIsonum() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIsonum"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIsonum() + " :: "+ msPaisesBk.getPaiIsonum());
								}
							cambios = true;
							msPaises.setPaiIsonum(msPaisesBk.getPaiIsonum());
						
					} else if (msPaisesBk.getPaiIsonum() != null
							&& msPaises.getPaiIsonum() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIsonum"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIsonum() + " :: "+ msPaisesBk.getPaiIsonum());
								}
							cambios = true;			
							msPaises.setPaiIsonum(msPaisesBk.getPaiIsonum());
					}
				 
		            if (msPaisesBk.getPaiIso2() != null
						&& msPaises.getPaiIso2() != null) {
					if (!msPaisesBk.getPaiIso2().equals(
						msPaises.getPaiIso2())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso2"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso2() + " :: "+ msPaisesBk.getPaiIso2());								
						}
						cambios = true;
						msPaises.setPaiIso2(msPaisesBk.getPaiIso2());
					}
				} else if (msPaisesBk.getPaiIso2() == null
						&& msPaises.getPaiIso2() != null) {
					if (msPaises.getPaiIso2().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso2"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso2() + " :: "+ msPaisesBk.getPaiIso2());
						}
						cambios = true;
						msPaises.setPaiIso2(msPaisesBk.getPaiIso2());
					}
				} else if (msPaisesBk.getPaiIso2() != null
						&& msPaises.getPaiIso2() == null) {
					if (msPaisesBk.getPaiIso2().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso2"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso2() + " :: "+ msPaisesBk.getPaiIso2());
						}
						cambios = true;
						msPaises.setPaiIso2(msPaisesBk.getPaiIso2());
					}
				}
				 
		            if (msPaisesBk.getPaiIso3() != null
						&& msPaises.getPaiIso3() != null) {
					if (!msPaisesBk.getPaiIso3().equals(
						msPaises.getPaiIso3())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso3"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso3() + " :: "+ msPaisesBk.getPaiIso3());								
						}
						cambios = true;
						msPaises.setPaiIso3(msPaisesBk.getPaiIso3());
					}
				} else if (msPaisesBk.getPaiIso3() == null
						&& msPaises.getPaiIso3() != null) {
					if (msPaises.getPaiIso3().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso3"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso3() + " :: "+ msPaisesBk.getPaiIso3());
						}
						cambios = true;
						msPaises.setPaiIso3(msPaisesBk.getPaiIso3());
					}
				} else if (msPaisesBk.getPaiIso3() != null
						&& msPaises.getPaiIso3() == null) {
					if (msPaisesBk.getPaiIso3().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiIso3"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiIso3() + " :: "+ msPaisesBk.getPaiIso3());
						}
						cambios = true;
						msPaises.setPaiIso3(msPaisesBk.getPaiIso3());
					}
				}
				 
		            if (msPaisesBk.getPaiNombre() != null
						&& msPaises.getPaiNombre() != null) {
					if (!msPaisesBk.getPaiNombre().equals(
						msPaises.getPaiNombre())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiNombre"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiNombre() + " :: "+ msPaisesBk.getPaiNombre());								
						}
						cambios = true;
						msPaises.setPaiNombre(msPaisesBk.getPaiNombre());
					}
				} else if (msPaisesBk.getPaiNombre() == null
						&& msPaises.getPaiNombre() != null) {
					if (msPaises.getPaiNombre().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiNombre"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiNombre() + " :: "+ msPaisesBk.getPaiNombre());
						}
						cambios = true;
						msPaises.setPaiNombre(msPaisesBk.getPaiNombre());
					}
				} else if (msPaisesBk.getPaiNombre() != null
						&& msPaises.getPaiNombre() == null) {
					if (msPaisesBk.getPaiNombre().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msPaises:PaiNombre"+" :: "+msPaisesBk.getPaiPk().toString()+" :: "+ msPaises.getPaiNombre() + " :: "+ msPaisesBk.getPaiNombre());
						}
						cambios = true;
						msPaises.setPaiNombre(msPaisesBk.getPaiNombre());
					}
				}
				
			
			return cambios;
	}
	
}