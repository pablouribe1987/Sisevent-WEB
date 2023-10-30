package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdItinerarioBk;

/**
 * TD_ITINERARIO SERVICIO VALIDACIÓN: ITINERARIO DE LOS VUELOS A REALIZARCE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionTdItinerarioMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionTdItinerarioMng.class.getName());
	
	public static void validarTdItinerarioBk(TdItinerarioBk tdItinerarioBk)
	 throws Validador
	{
                //FORANEAS
                if(tdItinerarioBk.getIdevent()!=null && tdItinerarioBk.getIdevent().longValue()<=0){
			tdItinerarioBk.setIdevent(null);
		}
	        if(tdItinerarioBk.getIduserCrea()!=null && tdItinerarioBk.getIduserCrea().longValue()<=0){
			tdItinerarioBk.setIduserCrea(null);
		}
	        if(tdItinerarioBk.getIduserModif()!=null && tdItinerarioBk.getIduserModif().longValue()<=0){
			tdItinerarioBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		//validarIdevent(tdItinerarioBk.getIdevent());

		//validarCodpais(tdItinerarioBk.getCodpais());

		//validarCoddpto(tdItinerarioBk.getCoddpto());

		//validarCodprov(tdItinerarioBk.getCodprov());

		
		validarOrigendestino(tdItinerarioBk.getOrigendestino());
		if(tdItinerarioBk.getOrigendestino()!=null){
				if(tdItinerarioBk.getOrigendestino().trim().length()>550)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdItinerario.noexceder"),
							Messages.getStringToKey("tdItinerario.origendestino"),
							Messages.getStringToKey("tdItinerario.titulotabla"),
							550,
							Messages.getStringToKey("tdItinerario.articuloOrigendestino")
							));				
				tdItinerarioBk.setOrigendestino(tdItinerarioBk.getOrigendestino().toUpperCase());
				}

		validarFechaSaliIni(tdItinerarioBk.getFechaSaliIni());

		validarFechaLlegFin(tdItinerarioBk.getFechaLlegFin());

		
		
		
		
		
		
		
				
	}
public static void validarIdevent(Long idevent)
			 throws Validador
				{				
								if(idevent==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
						Messages.getStringToKey("tdItinerario.idevent"),
						Messages.getStringToKey("tdItinerario.titulotabla"),
						Messages.getStringToKey("tdItinerario.articuloIdevent")));
					}
	
	public static void validarCodpais(Long codpais)
			 throws Validador
				{				
								if(codpais==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
						Messages.getStringToKey("tdItinerario.codpais"),
						Messages.getStringToKey("tdItinerario.titulotabla"),
						Messages.getStringToKey("tdItinerario.articuloCodpais")));
					}
	
	public static void validarCoddpto(Integer coddpto)
			 throws Validador
				{				
								if(coddpto==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
						Messages.getStringToKey("tdItinerario.coddpto"),
						Messages.getStringToKey("tdItinerario.titulotabla"),
						Messages.getStringToKey("tdItinerario.articuloCoddpto")));
					}
	
	public static void validarCodprov(Integer codprov)
			 throws Validador
				{				
								if(codprov==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
						Messages.getStringToKey("tdItinerario.codprov"),
						Messages.getStringToKey("tdItinerario.titulotabla"),
						Messages.getStringToKey("tdItinerario.articuloCodprov")));
					}
	
	
	public static void validarOrigendestino(String origendestino)
	 throws Validador
	{					
			if(origendestino==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.ingrese"),
			Messages.getStringToKey("tdItinerario.origendestino"),
			Messages.getStringToKey("tdItinerario.titulotabla"),
			Messages.getStringToKey("tdItinerario.articuloOrigendestino")));
			if(origendestino.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
			Messages.getStringToKey("tdItinerario.origendestino"),
			Messages.getStringToKey("tdItinerario.titulotabla"),
			Messages.getStringToKey("tdItinerario.articuloOrigendestino")));						
			if(origendestino!=null){
				if(origendestino.trim().length()>550)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.noexceder"),
			Messages.getStringToKey("tdItinerario.origendestino"),
			Messages.getStringToKey("tdItinerario.titulotabla"),550,
			Messages.getStringToKey("tdItinerario.articuloOrigendestino")));
				}
	}
	
	public static void validarFechaSaliIni(Timestamp fechaSaliIni)
	 throws Validador
	{				
					if(fechaSaliIni==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
			Messages.getStringToKey("tdItinerario.fechaSaliIni"),
			Messages.getStringToKey("tdItinerario.titulotabla"),
			Messages.getStringToKey("tdItinerario.articuloFechaSaliIni")));
		}
	
	public static void validarFechaLlegFin(Timestamp fechaLlegFin)
	 throws Validador
	{				
					if(fechaLlegFin==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdItinerario.invalidoingrese"),
			Messages.getStringToKey("tdItinerario.fechaLlegFin"),
			Messages.getStringToKey("tdItinerario.titulotabla"),
			Messages.getStringToKey("tdItinerario.articuloFechaLlegFin")));
		}
	
	
	
	
	
	
	
		
}