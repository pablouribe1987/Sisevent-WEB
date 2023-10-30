package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdGrupos;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposBk;

/**
 * TD_GRUPOS SERVICIO AUDITORIA Y CAMBIO: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaTdGruposMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaTdGruposMng.class.getName());
	
	public static boolean auditarCambiosTdGrupos(TdGruposBk tdGruposBk, TdGrupos tdGrupos, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		 
		            if (tdGruposBk.getGrupo() != null
						&& tdGrupos.getGrupo() != null) {
					if (!tdGruposBk.getGrupo().equals(
						tdGrupos.getGrupo())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGrupos:Grupo"+" :: "+tdGruposBk.getIdgrupo().toString()+" :: "+ tdGrupos.getGrupo() + " :: "+ tdGruposBk.getGrupo());								
						}
						cambios = true;
						tdGrupos.setGrupo(tdGruposBk.getGrupo());
					}
				} else if (tdGruposBk.getGrupo() == null
						&& tdGrupos.getGrupo() != null) {
					if (tdGrupos.getGrupo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGrupos:Grupo"+" :: "+tdGruposBk.getIdgrupo().toString()+" :: "+ tdGrupos.getGrupo() + " :: "+ tdGruposBk.getGrupo());
						}
						cambios = true;
						tdGrupos.setGrupo(tdGruposBk.getGrupo());
					}
				} else if (tdGruposBk.getGrupo() != null
						&& tdGrupos.getGrupo() == null) {
					if (tdGruposBk.getGrupo().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"tdGrupos:Grupo"+" :: "+tdGruposBk.getIdgrupo().toString()+" :: "+ tdGrupos.getGrupo() + " :: "+ tdGruposBk.getGrupo());
						}
						cambios = true;
						tdGrupos.setGrupo(tdGruposBk.getGrupo());
					}
				}
				
			
			return cambios;
	}
	
}