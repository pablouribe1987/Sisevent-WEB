package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposBk;

/**
 * TD_GRUPOS SERVICIO VALIDACIÓN: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionTdGruposMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionTdGruposMng.class.getName());
	
	public static void validarTdGruposBk(TdGruposBk tdGruposBk)
	 throws Validador
	{
                //FORANEAS
                if(tdGruposBk.getIduserCrea()!=null && tdGruposBk.getIduserCrea().longValue()<=0){
			tdGruposBk.setIduserCrea(null);
		}
	        if(tdGruposBk.getIduserModif()!=null && tdGruposBk.getIduserModif().longValue()<=0){
			tdGruposBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		
		validarGrupo(tdGruposBk.getGrupo());
		if(tdGruposBk.getGrupo()!=null){
				if(tdGruposBk.getGrupo().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdGrupos.noexceder"),
							Messages.getStringToKey("tdGrupos.grupo"),
							Messages.getStringToKey("tdGrupos.titulotabla"),
							150,
							Messages.getStringToKey("tdGrupos.articuloGrupo")
									));				
				tdGruposBk.setGrupo(tdGruposBk.getGrupo().toUpperCase());
				}

		
		
		
		
		
		
		
				
	}

	public static void validarGrupo(String grupo)
	 throws Validador
	{					
			if(grupo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGrupos.ingrese"),
			Messages.getStringToKey("tdGrupos.grupo"),
			Messages.getStringToKey("tdGrupos.titulotabla"),
			Messages.getStringToKey("tdGrupos.articuloGrupo")));
			if(grupo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGrupos.invalidoingrese"),
			Messages.getStringToKey("tdGrupos.grupo"),
			Messages.getStringToKey("tdGrupos.titulotabla")));						
			if(grupo!=null){
				if(grupo.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGrupos.noexceder"),
			Messages.getStringToKey("tdGrupos.grupo"),
			Messages.getStringToKey("tdGrupos.titulotabla"),150,
			Messages.getStringToKey("tdGrupos.articuloGrupo")));
				}
	}
	
	
	
	
	
	
	
		
}