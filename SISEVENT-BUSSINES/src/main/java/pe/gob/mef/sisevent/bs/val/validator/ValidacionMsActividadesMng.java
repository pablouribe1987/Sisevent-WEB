package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsActividadesBk;

/**
 * MS_ACTIVIDADES SERVICIO VALIDACIÓN: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionMsActividadesMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionMsActividadesMng.class.getName());
	
	public static void validarMsActividadesBk(MsActividadesBk msActividadesBk)
	 throws Validador
	{
                //FORANEAS
                if(msActividadesBk.getIdtareas()!=null && msActividadesBk.getIdtareas().longValue()<=0){
			msActividadesBk.setIdtareas(null);
		}
	        if(msActividadesBk.getIduserCrea()!=null && msActividadesBk.getIduserCrea().longValue()<=0){
			msActividadesBk.setIduserCrea(null);
		}
	        if(msActividadesBk.getIduserModif()!=null && msActividadesBk.getIduserModif().longValue()<=0){
			msActividadesBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		validarIdtareas(msActividadesBk.getIdtareas());

		
		validarActividad(msActividadesBk.getActividad());
		if(msActividadesBk.getActividad()!=null){
				if(msActividadesBk.getActividad().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msActividades.noexceder"),
							Messages.getStringToKey("msActividades.actividad"),
							Messages.getStringToKey("msActividades.titulotabla"),
							150,
							Messages.getStringToKey("msActividades.articuloActividad")
									));				
				msActividadesBk.setActividad(msActividadesBk.getActividad().toUpperCase());
				}

		
		//validarCamposallenar(msActividadesBk.getCamposallenar());
		if(msActividadesBk.getCamposallenar()!=null){
				if(msActividadesBk.getCamposallenar().trim().length()>500)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msActividades.noexceder"),
							Messages.getStringToKey("msActividades.camposallenar"),
							Messages.getStringToKey("msActividades.titulotabla"),
							500,
							Messages.getStringToKey("msActividades.articuloCamposallenar")
									));				
				msActividadesBk.setCamposallenar(msActividadesBk.getCamposallenar().toUpperCase());
				}

		//validarNumerodigitales(msActividadesBk.getNumerodigitales());

		
		
		
		
		
		
		
				
	}

	public static void validarIdtareas(Long idtareas)
	 throws Validador
	{				
					if(idtareas==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.seleccione"),
			Messages.getStringToKey("msActividades.idtareas"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloIdtareas")));
			if(idtareas.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.invalidoseleccione"),
			Messages.getStringToKey("msActividades.idtareas"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloIdtareas")));			
	}
	
	public static void validarActividad(String actividad)
	 throws Validador
	{					
			if(actividad==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.ingrese"),
			Messages.getStringToKey("msActividades.actividad"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloActividad")));
			if(actividad.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.invalidoingrese"),
			Messages.getStringToKey("msActividades.actividad"),
			Messages.getStringToKey("msActividades.titulotabla")));						
			if(actividad!=null){
				if(actividad.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.noexceder"),
			Messages.getStringToKey("msActividades.actividad"),
			Messages.getStringToKey("msActividades.titulotabla"),150,
			Messages.getStringToKey("msActividades.articuloActividad")));
				}
	}
	
	public static void validarCamposallenar(String camposallenar)
	 throws Validador
	{					
			if(camposallenar==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.ingrese"),
			Messages.getStringToKey("msActividades.camposallenar"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloCamposallenar")));
			if(camposallenar.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.invalidoingrese"),
			Messages.getStringToKey("msActividades.camposallenar"),
			Messages.getStringToKey("msActividades.titulotabla")));						
			if(camposallenar!=null){
				if(camposallenar.trim().length()>500)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.noexceder"),
			Messages.getStringToKey("msActividades.camposallenar"),
			Messages.getStringToKey("msActividades.titulotabla"),500,
			Messages.getStringToKey("msActividades.articuloCamposallenar")));
				}
	}
	
	public static void validarNumerodigitales(Integer numerodigitales)
	 throws Validador
	{				
					if(numerodigitales==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.seleccione"),
			Messages.getStringToKey("msActividades.numerodigitales"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloNumerodigitales")));
			if(numerodigitales.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msActividades.invalidoseleccione"),
			Messages.getStringToKey("msActividades.numerodigitales"),
			Messages.getStringToKey("msActividades.titulotabla"),
			Messages.getStringToKey("msActividades.articuloNumerodigitales")));			
	}
	
	
	
	
	
	
	
		
}