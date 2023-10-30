package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsTareasBk;

/**
 * MS_TAREAS SERVICIO VALIDACIÓN: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS DERIVACIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionMsTareasMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionMsTareasMng.class.getName());
	
	public static void validarMsTareasBk(MsTareasBk msTareasBk)
	 throws Validador
	{
                //FORANEAS
                if(msTareasBk.getIduserCrea()!=null && msTareasBk.getIduserCrea().longValue()<=0){
			msTareasBk.setIduserCrea(null);
		}
	        if(msTareasBk.getIduserModif()!=null && msTareasBk.getIduserModif().longValue()<=0){
			msTareasBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		
		validarTarea(msTareasBk.getTarea());
		if(msTareasBk.getTarea()!=null){
				if(msTareasBk.getTarea().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msTareas.noexceder"),
							Messages.getStringToKey("msTareas.tarea"),
							Messages.getStringToKey("msTareas.titulotabla"),
							150,
							Messages.getStringToKey("msTareas.articuloTarea")
									));				
				msTareasBk.setTarea(msTareasBk.getTarea().toUpperCase());
				}

		
		//validarDescripcion(msTareasBk.getDescripcion());
		if(msTareasBk.getDescripcion()!=null){
				if(msTareasBk.getDescripcion().trim().length()>500)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msTareas.noexceder"),
							Messages.getStringToKey("msTareas.descripcion"),
							Messages.getStringToKey("msTareas.titulotabla"),
							500,
							Messages.getStringToKey("msTareas.articuloDescripcion")
							));				
				msTareasBk.setDescripcion(msTareasBk.getDescripcion().toUpperCase());
				}

		//validarTiempo(msTareasBk.getTiempo());

		
		
		
		
		
		
		
				
	}

	public static void validarTarea(String tarea)
	 throws Validador
	{					
			if(tarea==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.ingrese"),
			Messages.getStringToKey("msTareas.tarea"),
			Messages.getStringToKey("msTareas.titulotabla"),
			Messages.getStringToKey("msTareas.articuloTarea")));
			if(tarea.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.invalidoingrese"),
			Messages.getStringToKey("msTareas.tarea"),
			Messages.getStringToKey("msTareas.titulotabla")));						
			if(tarea!=null){
				if(tarea.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.noexceder"),
			Messages.getStringToKey("msTareas.tarea"),
			Messages.getStringToKey("msTareas.titulotabla"),150,
			Messages.getStringToKey("msTareas.articuloTarea")));
				}
	}
	
	public static void validarDescripcion(String descripcion)
	 throws Validador
	{					
			if(descripcion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.ingrese"),
			Messages.getStringToKey("msTareas.descripcion"),
			Messages.getStringToKey("msTareas.titulotabla"),
			Messages.getStringToKey("msTareas.articuloDescripcion")));
			if(descripcion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.invalidoingrese"),
			Messages.getStringToKey("msTareas.descripcion"),
			Messages.getStringToKey("msTareas.titulotabla"),
			Messages.getStringToKey("msTareas.articuloDescripcion")));						
			if(descripcion!=null){
				if(descripcion.trim().length()>500)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.noexceder"),
			Messages.getStringToKey("msTareas.descripcion"),
			Messages.getStringToKey("msTareas.titulotabla"),500,
			Messages.getStringToKey("msTareas.articuloDescripcion")));
				}
	}
	
	public static void validarTiempo(Long tiempo)
	 throws Validador
	{				
					if(tiempo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.seleccione"),
			Messages.getStringToKey("msTareas.tiempo"),
			Messages.getStringToKey("msTareas.titulotabla"),
			Messages.getStringToKey("msTareas.articuloTiempo")));
			if(tiempo.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msTareas.invalidoseleccione"),
			Messages.getStringToKey("msTareas.tiempo"),
			Messages.getStringToKey("msTareas.titulotabla"),
			Messages.getStringToKey("msTareas.articuloTiempo")));			
	}
	
	
	
	
	
	
	
		
}