package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsInstitucionesBk;

/**
 * MS_INSTITUCIONES SERVICIO VALIDACIÓN: INSTITUCIONES Y ENTIDADES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45                      / Creación de la clase             /
 * 
 */
public class ValidacionMsInstitucionesMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2713218524343245218L;
	public static final Logger log = Logger.getLogger(ValidacionMsInstitucionesMng.class.getName());
	
	public static void validarMsInstitucionesBk(MsInstitucionesBk msInstitucionesBk, Long PAIS_PERU)
	 throws Validador
	{
                //FORANEAS
        if(msInstitucionesBk.getCodpais()!=null && msInstitucionesBk.getCodpais().longValue()<=0){
			msInstitucionesBk.setCodpais(null);
		}
	   
        if(msInstitucionesBk.getCoddpto()!=null && msInstitucionesBk.getCoddpto().longValue()<=0){
			msInstitucionesBk.setCoddpto(null);
		}
//	        if(msInstitucionesBk.getCodprov()!=null && msInstitucionesBk.getCodprov().longValue()<=0){
//			msInstitucionesBk.setCodprov(null);
//		}
//	        if(msInstitucionesBk.getCoddist()!=null && msInstitucionesBk.getCoddist().longValue()<=0){
//			msInstitucionesBk.setCoddist(null);
//		}
        
		//VALIDANDO
		
		validarRazonSocial(msInstitucionesBk.getRazonSocial());
		if(msInstitucionesBk.getRazonSocial()!=null){
				if(msInstitucionesBk.getRazonSocial().trim().length()>255)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.razonSocial"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							255,
							Messages.getStringToKey("msInstituciones.articuloRazonSocial")
									));				
				msInstitucionesBk.setRazonSocial(msInstitucionesBk.getRazonSocial().toUpperCase());
				}

		
//		validarSiglas(msInstitucionesBk.getSiglas());
		if(msInstitucionesBk.getSiglas()!=null){
				if(msInstitucionesBk.getSiglas().trim().length()>50)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.siglas"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							50,
							Messages.getStringToKey("msInstituciones.articuloSiglas")
									));				
				msInstitucionesBk.setSiglas(msInstitucionesBk.getSiglas().toUpperCase());
				}

		validarRuc(msInstitucionesBk.getRuc());

		
//		validarCorreo(msInstitucionesBk.getCorreo());
		if(msInstitucionesBk.getCorreo()!=null){
				if(msInstitucionesBk.getCorreo().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.correo"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							150,
							Messages.getStringToKey("msInstituciones.articuloCorreo")
									));				
				//msInstitucionesBk.setCorreo(msInstitucionesBk.getCorreo().toUpperCase());
				}

		
//		validarWeb(msInstitucionesBk.getWeb());
		if(msInstitucionesBk.getWeb()!=null){
				if(msInstitucionesBk.getWeb().trim().length()>150)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.web"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							150,
							Messages.getStringToKey("msInstituciones.articuloWeb")
									));				
				//msInstitucionesBk.setWeb(msInstitucionesBk.getWeb().toUpperCase());
				}

		
//		validarTelefono(msInstitucionesBk.getTelefono());
		if(msInstitucionesBk.getTelefono()!=null){
				if(msInstitucionesBk.getTelefono().trim().length()>100)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.telefono"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							100,
							Messages.getStringToKey("msInstituciones.articuloTelefono")
									));				
				//msInstitucionesBk.setTelefono(msInstitucionesBk.getTelefono().toUpperCase());
				}

		
//		validarFax(msInstitucionesBk.getFax());
		if(msInstitucionesBk.getFax()!=null){
				if(msInstitucionesBk.getFax().trim().length()>100)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.fax"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							100,
							Messages.getStringToKey("msInstituciones.articuloFax")
									));				
				//msInstitucionesBk.setFax(msInstitucionesBk.getFax().toUpperCase());
				}

//		validarCodpais(msInstitucionesBk.getCodpais());

		if(msInstitucionesBk.getCodpais()!=null && PAIS_PERU!=null && PAIS_PERU.longValue() == msInstitucionesBk.getCodpais().longValue()){
			validarCoddpto(msInstitucionesBk.getCoddpto());
			validarCodprov(msInstitucionesBk.getCodprov());
		    validarCoddist(msInstitucionesBk.getCoddist());
		}
		
//		validarDireccion(msInstitucionesBk.getDireccion());
		if(msInstitucionesBk.getDireccion()!=null){
				if(msInstitucionesBk.getDireccion().trim().length()>550)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("msInstituciones.noexceder"),
							Messages.getStringToKey("msInstituciones.direccion"),
							Messages.getStringToKey("msInstituciones.titulotabla"),
							550,
							Messages.getStringToKey("msInstituciones.articuloDireccion")
							));				
				msInstitucionesBk.setDireccion(msInstitucionesBk.getDireccion().toUpperCase());
				}

	}

	public static void validarRazonSocial(String razonSocial)
	 throws Validador
	{					
			if(razonSocial==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.razonSocial"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloRazonSocial")));
			if(razonSocial.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.razonSocial"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(razonSocial!=null){
				if(razonSocial.trim().length()>255)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.razonSocial"),
			Messages.getStringToKey("msInstituciones.titulotabla"),255,
			Messages.getStringToKey("msInstituciones.articuloRazonSocial")));
				}
	}
	
	public static void validarSiglas(String siglas)
	 throws Validador
	{					
			if(siglas==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.siglas"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloSiglas")));
			if(siglas.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.siglas"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(siglas!=null){
				if(siglas.trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.siglas"),
			Messages.getStringToKey("msInstituciones.titulotabla"),50,
			Messages.getStringToKey("msInstituciones.articuloSiglas")));
				}
	}
	public static void validarRuc(String ruc)
			 throws Validador
				{				
								if(ruc==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
						Messages.getStringToKey("msInstituciones.ruc"),
						Messages.getStringToKey("msInstituciones.titulotabla"),
						Messages.getStringToKey("msInstituciones.articuloRuc")));
					}
	
	
	public static void validarCorreo(String correo)
	 throws Validador
	{					
			if(correo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.correo"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloCorreo")));
			if(correo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.correo"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(correo!=null){
				if(correo.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.correo"),
			Messages.getStringToKey("msInstituciones.titulotabla"),150,
			Messages.getStringToKey("msInstituciones.articuloCorreo")));
				}
	}
	
	public static void validarWeb(String web)
	 throws Validador
	{					
			if(web==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.web"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloWeb")));
			if(web.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.web"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(web!=null){
				if(web.trim().length()>150)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.web"),
			Messages.getStringToKey("msInstituciones.titulotabla"),150,
			Messages.getStringToKey("msInstituciones.articuloWeb")));
				}
	}
	
	public static void validarTelefono(String telefono)
	 throws Validador
	{					
			if(telefono==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.telefono"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloTelefono")));
			if(telefono.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.telefono"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(telefono!=null){
				if(telefono.trim().length()>100)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.telefono"),
			Messages.getStringToKey("msInstituciones.titulotabla"),100,
			Messages.getStringToKey("msInstituciones.articuloTelefono")));
				}
	}
	
	public static void validarFax(String fax)
	 throws Validador
	{					
			if(fax==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.fax"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloFax")));
			if(fax.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.fax"),
			Messages.getStringToKey("msInstituciones.titulotabla")));						
			if(fax!=null){
				if(fax.trim().length()>100)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.fax"),
			Messages.getStringToKey("msInstituciones.titulotabla"),100,
			Messages.getStringToKey("msInstituciones.articuloFax")));
				}
	}
	
	public static void validarCodpais(Long codpais)
	 throws Validador
	{				
					if(codpais==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.seleccione"),
			Messages.getStringToKey("msInstituciones.codpais"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloCodpais")));
			if(codpais.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoseleccione"),
			Messages.getStringToKey("msInstituciones.codpais"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloCodpais")));			
	}
	public static void validarCoddpto(Integer coddpto)
			 throws Validador
				{				
								if(coddpto==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
						Messages.getStringToKey("msInstituciones.coddpto"),
						Messages.getStringToKey("msInstituciones.titulotabla"),
						Messages.getStringToKey("msInstituciones.articuloCoddpto")));
					}
	
	public static void validarCodprov(Integer codprov)
			 throws Validador
				{				
								if(codprov==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
						Messages.getStringToKey("msInstituciones.codprov"),
						Messages.getStringToKey("msInstituciones.titulotabla"),
						Messages.getStringToKey("msInstituciones.articuloCodprov")));
					}
	
	public static void validarCoddist(Integer coddist)
			 throws Validador
				{				
								if(coddist==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
						Messages.getStringToKey("msInstituciones.coddist"),
						Messages.getStringToKey("msInstituciones.titulotabla"),
						Messages.getStringToKey("msInstituciones.articuloCoddist")));
					}
	
	
	public static void validarDireccion(String direccion)
	 throws Validador
	{					
			if(direccion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.ingrese"),
			Messages.getStringToKey("msInstituciones.direccion"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloDireccion")));
			if(direccion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.invalidoingrese"),
			Messages.getStringToKey("msInstituciones.direccion"),
			Messages.getStringToKey("msInstituciones.titulotabla"),
			Messages.getStringToKey("msInstituciones.articuloDireccion")));						
			if(direccion!=null){
				if(direccion.trim().length()>550)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("msInstituciones.noexceder"),
			Messages.getStringToKey("msInstituciones.direccion"),
			Messages.getStringToKey("msInstituciones.titulotabla"),550,
			Messages.getStringToKey("msInstituciones.articuloDireccion")));
				}
	}
	
	
	
	
	
	
	
		
}