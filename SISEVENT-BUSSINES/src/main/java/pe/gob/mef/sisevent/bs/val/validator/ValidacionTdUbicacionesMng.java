package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdUbicacionesBk;

/**
 * TD_UBICACIONES SERVICIO VALIDACIÓN: UBICACIONES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionTdUbicacionesMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionTdUbicacionesMng.class.getName());
	
	public static void validarTdUbicacionesBk(TdUbicacionesBk tdUbicacionesBk)
	 throws Validador
	{
                //FORANEAS
                if(tdUbicacionesBk.getIdevent()!=null && tdUbicacionesBk.getIdevent().longValue()<=0){
			tdUbicacionesBk.setIdevent(null);
		}
	        if(tdUbicacionesBk.getIduserCrea()!=null && tdUbicacionesBk.getIduserCrea().longValue()<=0){
			tdUbicacionesBk.setIduserCrea(null);
		}
	        if(tdUbicacionesBk.getIduserModif()!=null && tdUbicacionesBk.getIduserModif().longValue()<=0){
			tdUbicacionesBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		//validarIdevent(tdUbicacionesBk.getIdevent());

		//validarCodpais(tdUbicacionesBk.getCodpais());

		//validarCoddpto(tdUbicacionesBk.getCoddpto());

		//validarCodprov(tdUbicacionesBk.getCodprov());

		//validarCoddist(tdUbicacionesBk.getCoddist());

		
		validarUbicacion(tdUbicacionesBk.getUbicacion());
		if(tdUbicacionesBk.getUbicacion()!=null){
				if(tdUbicacionesBk.getUbicacion().trim().length()>550)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdUbicaciones.noexceder"),
							Messages.getStringToKey("tdUbicaciones.ubicacion"),
							Messages.getStringToKey("tdUbicaciones.titulotabla"),
							550,
							Messages.getStringToKey("tdUbicaciones.articuloUbicacion")
							));				
				tdUbicacionesBk.setUbicacion(tdUbicacionesBk.getUbicacion().toUpperCase());
				}

		validarFechaActivIni(tdUbicacionesBk.getFechaActivIni());

		validarFechaActivFin(tdUbicacionesBk.getFechaActivFin());

		
		
		
		validarIduserModif(tdUbicacionesBk.getIduserModif());

		
		
		
				
	}

	public static void validarIdevent(Long idevent)
	 throws Validador
	{				
					if(idevent==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.seleccione"),
			Messages.getStringToKey("tdUbicaciones.idevent"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloIdevent")));
			if(idevent.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoseleccione"),
			Messages.getStringToKey("tdUbicaciones.idevent"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloIdevent")));			
	}
	public static void validarCodpais(Long codpais)
			 throws Validador
				{				
								if(codpais==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
						Messages.getStringToKey("tdUbicaciones.codpais"),
						Messages.getStringToKey("tdUbicaciones.titulotabla"),
						Messages.getStringToKey("tdUbicaciones.articuloCodpais")));
					}
	
	public static void validarCoddpto(Integer coddpto)
			 throws Validador
				{				
								if(coddpto==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
						Messages.getStringToKey("tdUbicaciones.coddpto"),
						Messages.getStringToKey("tdUbicaciones.titulotabla"),
						Messages.getStringToKey("tdUbicaciones.articuloCoddpto")));
					}
	
	public static void validarCodprov(Integer codprov)
			 throws Validador
				{				
								if(codprov==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
						Messages.getStringToKey("tdUbicaciones.codprov"),
						Messages.getStringToKey("tdUbicaciones.titulotabla"),
						Messages.getStringToKey("tdUbicaciones.articuloCodprov")));
					}
	
	public static void validarCoddist(Integer coddist)
			 throws Validador
				{				
								if(coddist==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
						Messages.getStringToKey("tdUbicaciones.coddist"),
						Messages.getStringToKey("tdUbicaciones.titulotabla"),
						Messages.getStringToKey("tdUbicaciones.articuloCoddist")));
					}
	
	
	public static void validarUbicacion(String ubicacion)
	 throws Validador
	{					
			if(ubicacion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.ingrese"),
			Messages.getStringToKey("tdUbicaciones.ubicacion"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloUbicacion")));
			if(ubicacion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
			Messages.getStringToKey("tdUbicaciones.ubicacion"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloUbicacion")));						
			if(ubicacion!=null){
				if(ubicacion.trim().length()>550)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.noexceder"),
			Messages.getStringToKey("tdUbicaciones.ubicacion"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),550,
			Messages.getStringToKey("tdUbicaciones.articuloUbicacion")));
				}
	}
	
	public static void validarFechaActivIni(Timestamp fechaActivIni)
	 throws Validador
	{				
					if(fechaActivIni==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
			Messages.getStringToKey("tdUbicaciones.fechaActivIni"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloFechaActivIni")));
		}
	
	public static void validarFechaActivFin(Timestamp fechaActivFin)
	 throws Validador
	{				
					if(fechaActivFin==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
			Messages.getStringToKey("tdUbicaciones.fechaActivFin"),
			Messages.getStringToKey("tdUbicaciones.titulotabla"),
			Messages.getStringToKey("tdUbicaciones.articuloFechaActivFin")));
		}
	
	
	
	public static void validarIduserModif(Long iduserModif)
			 throws Validador
				{				
								if(iduserModif==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdUbicaciones.invalidoingrese"),
						Messages.getStringToKey("tdUbicaciones.iduserModif"),
						Messages.getStringToKey("tdUbicaciones.titulotabla"),
						Messages.getStringToKey("tdUbicaciones.articuloIduserModif")));
					}
	
	
	
	
		
}