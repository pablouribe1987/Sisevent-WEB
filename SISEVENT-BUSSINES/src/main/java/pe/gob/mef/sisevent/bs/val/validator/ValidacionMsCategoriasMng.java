package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsCategoriasBk;

/**
 * MS_CATEGORIAS SERVICIO VALIDACIÓN: CATEGORÍAS ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:11                      / Creación de la clase             /
 * 
 */
public class ValidacionMsCategoriasMng implements Serializable{
	public static final Logger log = Logger.getLogger(ValidacionMsCategoriasMng.class.getName());
	
	public static void validarMsCategoriasBk(MsCategoriasBk msCategoriasBk)
	 throws Validador
	{
                //FORANEAS
                if(msCategoriasBk.getIduserCrea()!=null && msCategoriasBk.getIduserCrea().longValue()<=0){
			msCategoriasBk.setIduserCrea(null);
		}
	        if(msCategoriasBk.getIduserModif()!=null && msCategoriasBk.getIduserModif().longValue()<=0){
			msCategoriasBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		
		validarCategoria(msCategoriasBk.getCategoria());
		if(msCategoriasBk.getCategoria()!=null){
				if(msCategoriasBk.getCategoria().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msCategorias.noexceder"),
							Messages.getStringToKey("msCategorias.categoria"),
							Messages.getStringToKey("msCategorias.titulotabla"),
							150,
							Messages.getStringToKey("msCategorias.articuloCategoria")
									));				
				msCategoriasBk.setCategoria(msCategoriasBk.getCategoria().toUpperCase());
				}

		
		validarArraycamposocultos(msCategoriasBk.getArraycamposocultos());
		if(msCategoriasBk.getArraycamposocultos()!=null){
				if(msCategoriasBk.getArraycamposocultos().trim().length()>500)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msCategorias.noexceder"),
							Messages.getStringToKey("msCategorias.arraycamposocultos"),
							Messages.getStringToKey("msCategorias.titulotabla"),
							500,
							Messages.getStringToKey("msCategorias.articuloArraycamposocultos")
							));				
				msCategoriasBk.setArraycamposocultos(msCategoriasBk.getArraycamposocultos().toUpperCase());
				}

		
		
		
		
		
		
		
				
	}

	public static void validarCategoria(String categoria)
	 throws Validador
	{					
			if(categoria==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.ingrese"),
			Messages.getStringToKey("msCategorias.categoria"),
			Messages.getStringToKey("msCategorias.titulotabla"),
			Messages.getStringToKey("msCategorias.articuloCategoria")));
			if(categoria.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.invalidoingrese"),
			Messages.getStringToKey("msCategorias.categoria"),
			Messages.getStringToKey("msCategorias.titulotabla")));						
			if(categoria!=null){
				if(categoria.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.noexceder"),
			Messages.getStringToKey("msCategorias.categoria"),
			Messages.getStringToKey("msCategorias.titulotabla"),150,
			Messages.getStringToKey("msCategorias.articuloCategoria")));
				}
	}
	
	public static void validarArraycamposocultos(String arraycamposocultos)
	 throws Validador
	{					
			if(arraycamposocultos==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.ingrese"),
			Messages.getStringToKey("msCategorias.arraycamposocultos"),
			Messages.getStringToKey("msCategorias.titulotabla"),
			Messages.getStringToKey("msCategorias.articuloArraycamposocultos")));
			if(arraycamposocultos.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.invalidoingrese"),
			Messages.getStringToKey("msCategorias.arraycamposocultos"),
			Messages.getStringToKey("msCategorias.titulotabla"),
			Messages.getStringToKey("msCategorias.articuloArraycamposocultos")));						
			if(arraycamposocultos!=null){
				if(arraycamposocultos.trim().length()>500)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msCategorias.noexceder"),
			Messages.getStringToKey("msCategorias.arraycamposocultos"),
			Messages.getStringToKey("msCategorias.titulotabla"),500,
			Messages.getStringToKey("msCategorias.articuloArraycamposocultos")));
				}
	}
	
	
	
	
	
	
	
		
}