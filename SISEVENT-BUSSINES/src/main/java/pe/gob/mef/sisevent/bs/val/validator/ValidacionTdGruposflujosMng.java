package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;

/**
 * TD_GRUPOSFLUJOS SERVICIO VALIDACIÓN: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionTdGruposflujosMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionTdGruposflujosMng.class.getName());
	
	public static void validarTdGruposflujosBk(TdGruposflujosBk tdGruposflujosBk)
	 throws Validador
	{
                //FORANEAS
                if(tdGruposflujosBk.getIdtareas()!=null && tdGruposflujosBk.getIdtareas().longValue()<=0){
			tdGruposflujosBk.setIdtareas(null);
		}
	        if(tdGruposflujosBk.getIdunidadDestino()!=null && tdGruposflujosBk.getIdunidadDestino().longValue()<=0){
			tdGruposflujosBk.setIdunidadDestino(null);
		}
	        if(tdGruposflujosBk.getIduserCrea()!=null && tdGruposflujosBk.getIduserCrea().longValue()<=0){
			tdGruposflujosBk.setIduserCrea(null);
		}
	        if(tdGruposflujosBk.getIduserModif()!=null && tdGruposflujosBk.getIduserModif().longValue()<=0){
			tdGruposflujosBk.setIduserModif(null);
		}
	        if(tdGruposflujosBk.getIdgrupo()!=null && tdGruposflujosBk.getIdgrupo().longValue()<=0){
			tdGruposflujosBk.setIdgrupo(null);
		}
	        
		//VALIDANDO
		validarIdtareas(tdGruposflujosBk.getIdtareas());

		validarIdunidadDestino(tdGruposflujosBk.getIdunidadDestino());

		validarIduserDestino(tdGruposflujosBk.getIduserDestino());

		
		//validarObservacion(tdGruposflujosBk.getObservacion());
		if(tdGruposflujosBk.getObservacion()!=null){
				if(tdGruposflujosBk.getObservacion().trim().length()>4000)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdGruposflujos.noexceder"),
							Messages.getStringToKey("tdGruposflujos.observacion"),
							Messages.getStringToKey("tdGruposflujos.titulotabla"),
							4000,
							Messages.getStringToKey("tdGruposflujos.articuloObservacion")
							));				
				tdGruposflujosBk.setObservacion(tdGruposflujosBk.getObservacion().toUpperCase());
				}

		
		//validarObservacionHtml(tdGruposflujosBk.getObservacionHtml());
		if(tdGruposflujosBk.getObservacionHtml()!=null){
				if(tdGruposflujosBk.getObservacionHtml().trim().length()>4000)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdGruposflujos.noexceder"),
							Messages.getStringToKey("tdGruposflujos.observacionHtml"),
							Messages.getStringToKey("tdGruposflujos.titulotabla"),
							4000,
							Messages.getStringToKey("tdGruposflujos.articuloObservacionHtml")
							));				
				tdGruposflujosBk.setObservacionHtml(tdGruposflujosBk.getObservacionHtml().toUpperCase());
				}

		//validarTiempoestadia(tdGruposflujosBk.getTiempoestadia());

		
		//validarCorreosnotif(tdGruposflujosBk.getCorreosnotif());
		if(tdGruposflujosBk.getCorreosnotif()!=null){
				if(tdGruposflujosBk.getCorreosnotif().trim().length()>250)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdGruposflujos.noexceder"),
							Messages.getStringToKey("tdGruposflujos.correosnotif"),
							Messages.getStringToKey("tdGruposflujos.titulotabla"),
							250,
							Messages.getStringToKey("tdGruposflujos.articuloCorreosnotif")
									));				
				tdGruposflujosBk.setCorreosnotif(tdGruposflujosBk.getCorreosnotif().toUpperCase());
				}

		
		
		
		
		
		
		
		validarIdgrupo(tdGruposflujosBk.getIdgrupo());

				
	}

	public static void validarIdtareas(Long idtareas)
	 throws Validador
	{				
					if(idtareas==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.seleccione"),
			Messages.getStringToKey("tdGruposflujos.idtareas"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdtareas")));
			if(idtareas.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoseleccione"),
			Messages.getStringToKey("tdGruposflujos.idtareas"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdtareas")));			
	}
	
	public static void validarIdunidadDestino(Long idunidadDestino)
	 throws Validador
	{				
					if(idunidadDestino==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.seleccione"),
			Messages.getStringToKey("tdGruposflujos.idunidadDestino"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdunidadDestino")));
			if(idunidadDestino.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoseleccione"),
			Messages.getStringToKey("tdGruposflujos.idunidadDestino"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdunidadDestino")));			
	}
	
	public static void validarIduserDestino(Long iduserDestino)
	 throws Validador
	{				
					if(iduserDestino==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.seleccione"),
			Messages.getStringToKey("tdGruposflujos.iduserDestino"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIduserDestino")));
			if(iduserDestino.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoseleccione"),
			Messages.getStringToKey("tdGruposflujos.iduserDestino"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIduserDestino")));			
	}
	
	public static void validarObservacion(String observacion)
	 throws Validador
	{					
			if(observacion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.ingrese"),
			Messages.getStringToKey("tdGruposflujos.observacion"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloObservacion")));
			if(observacion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoingrese"),
			Messages.getStringToKey("tdGruposflujos.observacion"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloObservacion")));						
			if(observacion!=null){
				if(observacion.trim().length()>4000)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.noexceder"),
			Messages.getStringToKey("tdGruposflujos.observacion"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),4000,
			Messages.getStringToKey("tdGruposflujos.articuloObservacion")));
				}
	}
	
	public static void validarObservacionHtml(String observacionHtml)
	 throws Validador
	{					
			if(observacionHtml==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.ingrese"),
			Messages.getStringToKey("tdGruposflujos.observacionHtml"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloObservacionHtml")));
			if(observacionHtml.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoingrese"),
			Messages.getStringToKey("tdGruposflujos.observacionHtml"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloObservacionHtml")));						
			if(observacionHtml!=null){
				if(observacionHtml.trim().length()>4000)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.noexceder"),
			Messages.getStringToKey("tdGruposflujos.observacionHtml"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),4000,
			Messages.getStringToKey("tdGruposflujos.articuloObservacionHtml")));
				}
	}
	
	public static void validarTiempoestadia(Integer tiempoestadia)
	 throws Validador
	{				
					if(tiempoestadia==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.seleccione"),
			Messages.getStringToKey("tdGruposflujos.tiempoestadia"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloTiempoestadia")));
			if(tiempoestadia.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoseleccione"),
			Messages.getStringToKey("tdGruposflujos.tiempoestadia"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloTiempoestadia")));			
	}
	
	public static void validarCorreosnotif(String correosnotif)
	 throws Validador
	{					
			if(correosnotif==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.ingrese"),
			Messages.getStringToKey("tdGruposflujos.correosnotif"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloCorreosnotif")));
			if(correosnotif.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoingrese"),
			Messages.getStringToKey("tdGruposflujos.correosnotif"),
			Messages.getStringToKey("tdGruposflujos.titulotabla")));						
			if(correosnotif!=null){
				if(correosnotif.trim().length()>250)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.noexceder"),
			Messages.getStringToKey("tdGruposflujos.correosnotif"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),250,
			Messages.getStringToKey("tdGruposflujos.articuloCorreosnotif")));
				}
	}
	
	
	
	
	
	
	
	
	public static void validarIdgrupo(Long idgrupo)
	 throws Validador
	{				
					if(idgrupo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.seleccione"),
			Messages.getStringToKey("tdGruposflujos.idgrupo"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdgrupo")));
			if(idgrupo.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdGruposflujos.invalidoseleccione"),
			Messages.getStringToKey("tdGruposflujos.idgrupo"),
			Messages.getStringToKey("tdGruposflujos.titulotabla"),
			Messages.getStringToKey("tdGruposflujos.articuloIdgrupo")));			
	}
		
}