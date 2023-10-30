package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPersonasBk;

/**
 * MS_PERSONAS SERVICIO VALIDACIÓN: PERSONAS NATURALES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45                      / Creación de la clase             /
 * 
 */
public class ValidacionMsPersonasMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4141851099777266232L;
	public static final Logger log = Logger.getLogger(ValidacionMsPersonasMng.class.getName());
	
	public static void validarMsPersonasBk(MsPersonasBk msPersonasBk, Long PAIS_PERU)
	 throws Validador
	{
                //FORANEAS
        if(msPersonasBk.getTipodocumento()!=null && msPersonasBk.getTipodocumento().longValue()<=0){
			msPersonasBk.setTipodocumento(null);
		}
	        if(msPersonasBk.getCodpais()!=null && msPersonasBk.getCodpais().longValue()<=0){
			msPersonasBk.setCodpais(null);
		}
	        if(msPersonasBk.getCoddpto()!=null && msPersonasBk.getCoddpto().longValue()<=0){
			msPersonasBk.setCoddpto(null);
		}
	        if(msPersonasBk.getCodprov()!=null && msPersonasBk.getCodprov().longValue()<=0){
			msPersonasBk.setCodprov(null);
		}
	        if(msPersonasBk.getCoddist()!=null && msPersonasBk.getCoddist().longValue()<=0){
			msPersonasBk.setCoddist(null);
		}
	    
		//VALIDANDO
		
		validarApellidoPaterno(msPersonasBk.getApellidoPaterno());
		if(msPersonasBk.getApellidoPaterno()!=null){
				if(msPersonasBk.getApellidoPaterno().trim().length()>100)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.apellidoPaterno"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							100,
							Messages.getStringToKey("msPersonas.articuloApellidoPaterno")
									));				
				msPersonasBk.setApellidoPaterno(msPersonasBk.getApellidoPaterno().toUpperCase());
				}

		
//		validarApellidoMaterno(msPersonasBk.getApellidoMaterno());
		if(msPersonasBk.getApellidoMaterno()!=null){
				if(msPersonasBk.getApellidoMaterno().trim().length()>100)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.apellidoMaterno"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							100,
							Messages.getStringToKey("msPersonas.articuloApellidoMaterno")
									));				
				msPersonasBk.setApellidoMaterno(msPersonasBk.getApellidoMaterno().toUpperCase());
				}

		
		validarNombres(msPersonasBk.getNombres());
		if(msPersonasBk.getNombres()!=null){
				if(msPersonasBk.getNombres().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.nombres"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							150,
							Messages.getStringToKey("msPersonas.articuloNombres")
									));				
				msPersonasBk.setNombres(msPersonasBk.getNombres().toUpperCase());
				}

//		validarSexo(msPersonasBk.getSexo());

//		validarTipodocumento(msPersonasBk.getTipodocumento());

		
		validarDocumentoNumero(msPersonasBk.getDocumentoNumero());
		if(msPersonasBk.getDocumentoNumero()!=null){
				if(msPersonasBk.getDocumentoNumero().trim().length()>25)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.documentoNumero"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							25,
							Messages.getStringToKey("msPersonas.articuloDocumentoNumero")
									));				
		//		msPersonasBk.setDocumentoNumero(msPersonasBk.getDocumentoNumero().toUpperCase());
				}

		
//		validarCorreo(msPersonasBk.getCorreo());
		if(msPersonasBk.getCorreo()!=null){
				if(msPersonasBk.getCorreo().trim().length()>50)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.correo"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							50,
							Messages.getStringToKey("msPersonas.articuloCorreo")
									));				
			//	msPersonasBk.setCorreo(msPersonasBk.getCorreo().toUpperCase());
				}

		
//		validarTelefono(msPersonasBk.getTelefono());
		if(msPersonasBk.getTelefono()!=null){
				if(msPersonasBk.getTelefono().trim().length()>15)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.telefono"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							15,
							Messages.getStringToKey("msPersonas.articuloTelefono")
									));				
				//msPersonasBk.setTelefono(msPersonasBk.getTelefono().toUpperCase());
				}

		
//		validarCelular(msPersonasBk.getCelular());
		if(msPersonasBk.getCelular()!=null){
				if(msPersonasBk.getCelular().trim().length()>15)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.celular"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							15,
							Messages.getStringToKey("msPersonas.articuloCelular")
									));				
				//msPersonasBk.setCelular(msPersonasBk.getCelular().toUpperCase());
				}

//		validarCodpais(msPersonasBk.getCodpais());

		if(msPersonasBk.getCodpais()!=null && PAIS_PERU!=null && PAIS_PERU.longValue() == msPersonasBk.getCodpais().longValue()){
			validarCoddpto(msPersonasBk.getCoddpto());
            validarCodprov(msPersonasBk.getCodprov());
			validarCoddist(msPersonasBk.getCoddist());
		}

		
//		validarDireccion(msPersonasBk.getDireccion());
		if(msPersonasBk.getDireccion()!=null){
				if(msPersonasBk.getDireccion().trim().length()>255)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msPersonas.noexceder"),
							Messages.getStringToKey("msPersonas.direccion"),
							Messages.getStringToKey("msPersonas.titulotabla"),
							255,
							Messages.getStringToKey("msPersonas.articuloDireccion")
							));				
				msPersonasBk.setDireccion(msPersonasBk.getDireccion().toUpperCase());
				}

	}

	public static void validarApellidoPaterno(String apellidoPaterno)
	 throws Validador
	{					
			if(apellidoPaterno==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.apellidoPaterno"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloApellidoPaterno")));
			if(apellidoPaterno.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.apellidoPaterno"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(apellidoPaterno!=null){
				if(apellidoPaterno.trim().length()>100)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.apellidoPaterno"),
			Messages.getStringToKey("msPersonas.titulotabla"),100,
			Messages.getStringToKey("msPersonas.articuloApellidoPaterno")));
				}
	}
	
	public static void validarApellidoMaterno(String apellidoMaterno)
	 throws Validador
	{					
			if(apellidoMaterno==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.apellidoMaterno"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloApellidoMaterno")));
			if(apellidoMaterno.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.apellidoMaterno"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(apellidoMaterno!=null){
				if(apellidoMaterno.trim().length()>100)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.apellidoMaterno"),
			Messages.getStringToKey("msPersonas.titulotabla"),100,
			Messages.getStringToKey("msPersonas.articuloApellidoMaterno")));
				}
	}
	
	public static void validarNombres(String nombres)
	 throws Validador
	{					
			if(nombres==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.nombres"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloNombres")));
			if(nombres.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.nombres"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(nombres!=null){
				if(nombres.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.nombres"),
			Messages.getStringToKey("msPersonas.titulotabla"),150,
			Messages.getStringToKey("msPersonas.articuloNombres")));
				}
	}
	
	public static void validarSexo(String sexo)
	 throws Validador
	{				
		if(sexo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.sexo"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloNombres")));
			if(sexo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.sexo"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(sexo!=null){
				if(sexo.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.sexo"),
			Messages.getStringToKey("msPersonas.titulotabla"),150,
			Messages.getStringToKey("msPersonas.articuloNombres")));
				}
						
	}
	
	public static void validarTipodocumento(Long tipodocumento)
	 throws Validador
	{				
					if(tipodocumento==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.seleccione"),
			Messages.getStringToKey("msPersonas.tipodocumento"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloTipodocumento")));
			if(tipodocumento.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoseleccione"),
			Messages.getStringToKey("msPersonas.tipodocumento"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloTipodocumento")));			
	}
	
	public static void validarDocumentoNumero(String documentoNumero)
	 throws Validador
	{					
			if(documentoNumero==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.documentoNumero"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloDocumentoNumero")));
			if(documentoNumero.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.documentoNumero"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(documentoNumero!=null){
				if(documentoNumero.trim().length()>25)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.documentoNumero"),
			Messages.getStringToKey("msPersonas.titulotabla"),25,
			Messages.getStringToKey("msPersonas.articuloDocumentoNumero")));
				}
	}
	
	public static void validarCorreo(String correo)
	 throws Validador
	{					
			if(correo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.correo"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCorreo")));
			if(correo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.correo"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(correo!=null){
				if(correo.trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.correo"),
			Messages.getStringToKey("msPersonas.titulotabla"),50,
			Messages.getStringToKey("msPersonas.articuloCorreo")));
				}
	}
	
	public static void validarTelefono(String telefono)
	 throws Validador
	{					
			if(telefono==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.telefono"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloTelefono")));
			if(telefono.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.telefono"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(telefono!=null){
				if(telefono.trim().length()>15)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.telefono"),
			Messages.getStringToKey("msPersonas.titulotabla"),15,
			Messages.getStringToKey("msPersonas.articuloTelefono")));
				}
	}
	
	public static void validarCelular(String celular)
	 throws Validador
	{					
			if(celular==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.celular"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCelular")));
			if(celular.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.celular"),
			Messages.getStringToKey("msPersonas.titulotabla")));						
			if(celular!=null){
				if(celular.trim().length()>15)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.celular"),
			Messages.getStringToKey("msPersonas.titulotabla"),15,
			Messages.getStringToKey("msPersonas.articuloCelular")));
				}
	}
	
	public static void validarCodpais(Long codpais)
	 throws Validador
	{				
					if(codpais==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.seleccione"),
			Messages.getStringToKey("msPersonas.codpais"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCodpais")));
			if(codpais.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoseleccione"),
			Messages.getStringToKey("msPersonas.codpais"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCodpais")));			
	}
	
	public static void validarCoddpto(Integer coddpto)
	 throws Validador
	{				
					if(coddpto==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.seleccione"),
			Messages.getStringToKey("msPersonas.coddpto"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCoddpto")));
			if(coddpto.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoseleccione"),
			Messages.getStringToKey("msPersonas.coddpto"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCoddpto")));			
	}
	
	public static void validarCodprov(Integer codprov)
	 throws Validador
	{				
					if(codprov==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.seleccione"),
			Messages.getStringToKey("msPersonas.codprov"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCodprov")));
			if(codprov.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoseleccione"),
			Messages.getStringToKey("msPersonas.codprov"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCodprov")));			
	}
	
	public static void validarCoddist(Integer coddist)
	 throws Validador
	{				
					if(coddist==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.seleccione"),
			Messages.getStringToKey("msPersonas.coddist"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCoddist")));
			if(coddist.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoseleccione"),
			Messages.getStringToKey("msPersonas.coddist"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloCoddist")));			
	}
	
	public static void validarDireccion(String direccion)
	 throws Validador
	{					
			if(direccion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.ingrese"),
			Messages.getStringToKey("msPersonas.direccion"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloDireccion")));
			if(direccion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.invalidoingrese"),
			Messages.getStringToKey("msPersonas.direccion"),
			Messages.getStringToKey("msPersonas.titulotabla"),
			Messages.getStringToKey("msPersonas.articuloDireccion")));						
			if(direccion!=null){
				if(direccion.trim().length()>255)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msPersonas.noexceder"),
			Messages.getStringToKey("msPersonas.direccion"),
			Messages.getStringToKey("msPersonas.titulotabla"),255,
			Messages.getStringToKey("msPersonas.articuloDireccion")));
				}
	}
	
	
	
	
	
	
	
	
		
}