package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdParticipantesBk;

/**
 * TD_PARTICIPANTES SERVICIO VALIDACIÓN: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionTdParticipantesMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionTdParticipantesMng.class.getName());
	
	public static void validarTdParticipantesBk(TdParticipantesBk tdParticipantesBk)
	 throws Validador
	{
                //FORANEAS
                if(tdParticipantesBk.getIdusuarioIdproveeIdperson()!=null && tdParticipantesBk.getIdusuarioIdproveeIdperson().longValue()<=0){
			tdParticipantesBk.setIdusuarioIdproveeIdperson(null);
		}
	        if(tdParticipantesBk.getIduserCrea()!=null && tdParticipantesBk.getIduserCrea().longValue()<=0){
			tdParticipantesBk.setIduserCrea(null);
		}
	        if(tdParticipantesBk.getIduserModif()!=null && tdParticipantesBk.getIduserModif().longValue()<=0){
			tdParticipantesBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		validarIdusuarioIdproveeIdperson(tdParticipantesBk.getIdusuarioIdproveeIdperson());

		
		validarNombresrazonsocial(tdParticipantesBk.getNombresrazonsocial());
		if(tdParticipantesBk.getNombresrazonsocial()!=null){
				if(tdParticipantesBk.getNombresrazonsocial().trim().length()>600)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdParticipantes.noexceder"),
							Messages.getStringToKey("tdParticipantes.nombresrazonsocial"),
							Messages.getStringToKey("tdParticipantes.titulotabla"),
							600,
							Messages.getStringToKey("tdParticipantes.articuloNombresrazonsocial")
							));				
				tdParticipantesBk.setNombresrazonsocial(tdParticipantesBk.getNombresrazonsocial().toUpperCase());
				}

		validarTipo(tdParticipantesBk.getTipo());

		validarFlagacompaniante(tdParticipantesBk.getFlagacompaniante());

		
		
		
		
		
		
		
				
	}
public static void validarIdusuarioIdproveeIdperson(Long idusuarioIdproveeIdperson)
			 throws Validador
				{				
								if(idusuarioIdproveeIdperson==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.invalidoingrese"),
						Messages.getStringToKey("tdParticipantes.idusuarioIdproveeIdperson"),
						Messages.getStringToKey("tdParticipantes.titulotabla"),
						Messages.getStringToKey("tdParticipantes.articuloIdusuarioIdproveeIdperson")));
					}
	
	
	public static void validarNombresrazonsocial(String nombresrazonsocial)
	 throws Validador
	{					
			if(nombresrazonsocial==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.ingrese"),
			Messages.getStringToKey("tdParticipantes.nombresrazonsocial"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloNombresrazonsocial")));
			if(nombresrazonsocial.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.invalidoingrese"),
			Messages.getStringToKey("tdParticipantes.nombresrazonsocial"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloNombresrazonsocial")));						
			if(nombresrazonsocial!=null){
				if(nombresrazonsocial.trim().length()>600)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.noexceder"),
			Messages.getStringToKey("tdParticipantes.nombresrazonsocial"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),600,
			Messages.getStringToKey("tdParticipantes.articuloNombresrazonsocial")));
				}
	}
	
	public static void validarTipo(Integer tipo)
	 throws Validador
	{				
					if(tipo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.seleccione"),
			Messages.getStringToKey("tdParticipantes.tipo"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloTipo")));
			if(tipo.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.invalidoseleccione"),
			Messages.getStringToKey("tdParticipantes.tipo"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloTipo")));			
	}
	
	public static void validarFlagacompaniante(Integer flagacompaniante)
	 throws Validador
	{				
					if(flagacompaniante==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.seleccione"),
			Messages.getStringToKey("tdParticipantes.flagacompaniante"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloFlagacompaniante")));
			if(flagacompaniante.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdParticipantes.invalidoseleccione"),
			Messages.getStringToKey("tdParticipantes.flagacompaniante"),
			Messages.getStringToKey("tdParticipantes.titulotabla"),
			Messages.getStringToKey("tdParticipantes.articuloFlagacompaniante")));			
	}
	
	
	
	
	
	
	
		
}