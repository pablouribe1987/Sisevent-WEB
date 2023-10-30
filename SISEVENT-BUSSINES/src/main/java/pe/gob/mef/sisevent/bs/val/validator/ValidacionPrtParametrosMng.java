package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.PrtParametrosBk;

/**
 * PRT_PARAMETROS SERVICIO VALIDACIÓN: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL SISTEMA PARÁMETROS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37                      / Creación de la clase             /
 * 
 */
public class ValidacionPrtParametrosMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3522688193665432229L;
	public static final Logger log = Logger.getLogger(ValidacionPrtParametrosMng.class.getName());
	
	public static void validarPrtParametrosBk(PrtParametrosBk PrtParametrosBk)
	 throws Validador
	{
                //FORANEAS
                if(PrtParametrosBk.getIdpadre()!=null && PrtParametrosBk.getIdpadre().longValue()<=0){
			PrtParametrosBk.setIdpadre(null);
		}
	        if(PrtParametrosBk.getIduserCrea()!=null && PrtParametrosBk.getIduserCrea().longValue()<=0){
			PrtParametrosBk.setIduserCrea(null);
		}
	        if(PrtParametrosBk.getIduserModif()!=null && PrtParametrosBk.getIduserModif().longValue()<=0){
			PrtParametrosBk.setIduserModif(null);
		}
	        
		//VALIDANDO
//		validarIdpadre(PrtParametrosBk.getIdpadre());

		
		validarDescripcion(PrtParametrosBk.getDescripcion());
		if(PrtParametrosBk.getDescripcion()!=null){
				if(PrtParametrosBk.getDescripcion().trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.noexceder"),
			Messages.getStringToKey("PrtParametros.descripcion"),
			Messages.getStringToKey("PrtParametros.titulotabla"),150));				
				PrtParametrosBk.setDescripcion(PrtParametrosBk.getDescripcion().toUpperCase());
				}

		
//		validarDescripcioncorta(PrtParametrosBk.getDescripcioncorta());
		if(PrtParametrosBk.getDescripcioncorta()!=null){
				if(PrtParametrosBk.getDescripcioncorta().trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.noexceder"),
			Messages.getStringToKey("PrtParametros.descripcioncorta"),
			Messages.getStringToKey("PrtParametros.titulotabla"),150));				
//				PrtParametrosBk.setDescripcioncorta(PrtParametrosBk.getDescripcioncorta().toUpperCase());
				}

//		validarOrden(PrtParametrosBk.getOrden());		
		validarEstado(PrtParametrosBk.getEstado());			
	}

	public static void validarIdpadre(Long idpadre)
	 throws Validador
	{				
					if(idpadre==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.seleccione"),
			Messages.getStringToKey("PrtParametros.idpadre"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
			if(idpadre.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.invalidoseleccione"),
			Messages.getStringToKey("PrtParametros.idpadre"),
			Messages.getStringToKey("PrtParametros.titulotabla")));			
	}
	
	public static void validarDescripcion(String descripcion)
	 throws Validador
	{					
			if(descripcion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.ingrese"),
			Messages.getStringToKey("PrtParametros.descripcion"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
			if(descripcion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.invalidoingrese"),
			Messages.getStringToKey("PrtParametros.descripcion"),
			Messages.getStringToKey("PrtParametros.titulotabla")));						
			if(descripcion!=null){
				if(descripcion.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.noexceder"),
			Messages.getStringToKey("PrtParametros.descripcion"),
			Messages.getStringToKey("PrtParametros.titulotabla"),150));
				}
	}
	
	public static void validarDescripcioncorta(String descripcioncorta)
	 throws Validador
	{					
			if(descripcioncorta==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.ingrese"),
			Messages.getStringToKey("PrtParametros.descripcioncorta"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
			if(descripcioncorta.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.invalidoingrese"),
			Messages.getStringToKey("PrtParametros.descripcioncorta"),
			Messages.getStringToKey("PrtParametros.titulotabla")));						
			if(descripcioncorta!=null){
				if(descripcioncorta.trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.noexceder"),
			Messages.getStringToKey("PrtParametros.descripcioncorta"),
			Messages.getStringToKey("PrtParametros.titulotabla"),50));
				}
	}
	
	public static void validarOrden(Integer orden)
	 throws Validador
	{				
					if(orden==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.seleccione"),
			Messages.getStringToKey("PrtParametros.orden"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
			if(orden.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.invalidoseleccione"),
			Messages.getStringToKey("PrtParametros.orden"),
			Messages.getStringToKey("PrtParametros.titulotabla")));			
	}
	
	
	
	
	
	
	
	public static void validarEstado(Integer estado)
	 throws Validador
	{				
					if(estado==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.seleccione"),
			Messages.getStringToKey("PrtParametros.estado"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
			if(estado.intValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("PrtParametros.invalidoseleccione"),
			Messages.getStringToKey("PrtParametros.estado"),
			Messages.getStringToKey("PrtParametros.titulotabla")));
	}
		
}