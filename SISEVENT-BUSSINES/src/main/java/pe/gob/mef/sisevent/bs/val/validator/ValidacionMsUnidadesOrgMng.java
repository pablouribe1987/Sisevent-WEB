package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;

/**
 * MS_UNIDADES_ORG SERVICIO VALIDACIÓN: UNIDADES ORGÁNICAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37                      / Creación de la clase             /
 * 
 */
public class ValidacionMsUnidadesOrgMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8962484866725004191L;
	public static final Logger log = Logger.getLogger(ValidacionMsUnidadesOrgMng.class.getName());
	
	public static void validarMsUnidadesOrgBk(MsUnidadesOrgBk MsUnidadesOrgBk)
	 throws Validador
	{
                //FORANEAS
        if(MsUnidadesOrgBk.getIdpadre()!=null && MsUnidadesOrgBk.getIdpadre().longValue()<=0){
			MsUnidadesOrgBk.setIdpadre(null);
		} 
		//VALIDANDO
//		validarIdpadre(MsUnidadesOrgBk.getIdpadre());

		
//		validarCodigo(MsUnidadesOrgBk.getCodigo());
		if(MsUnidadesOrgBk.getCodigo()!=null){
				if(MsUnidadesOrgBk.getCodigo().trim().length()>60)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.codigo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),60));				
				MsUnidadesOrgBk.setCodigo(MsUnidadesOrgBk.getCodigo().toUpperCase());
				}

		
		validarDescripcion(MsUnidadesOrgBk.getDescripcion());
		if(MsUnidadesOrgBk.getDescripcion()!=null){
				if(MsUnidadesOrgBk.getDescripcion().trim().length()>255)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.descripcion"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),255));				
				MsUnidadesOrgBk.setDescripcion(MsUnidadesOrgBk.getDescripcion().toUpperCase());
				}

		
		validarAcronimo(MsUnidadesOrgBk.getAcronimo());
		if(MsUnidadesOrgBk.getAcronimo()!=null){
				if(MsUnidadesOrgBk.getAcronimo().trim().length()>25)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.acronimo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),25));				
				MsUnidadesOrgBk.setAcronimo(MsUnidadesOrgBk.getAcronimo().toUpperCase());
				}

//		validarFlagofgeneral(MsUnidadesOrgBk.getFlagofgeneral());	
		
		
		validarEstado(MsUnidadesOrgBk.getEstado());				
	}
	
public static void validarIdpadre(Long idpadre)
			 throws Validador
				{				
								if(idpadre==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoingrese"),
						Messages.getStringToKey("MsUnidadesOrg.idpadre"),
						Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
					}
	
	
	public static void validarCodigo(String codigo)
	 throws Validador
	{					
			if(codigo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.ingrese"),
			Messages.getStringToKey("MsUnidadesOrg.codigo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
			if(codigo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoingrese"),
			Messages.getStringToKey("MsUnidadesOrg.codigo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));						
			if(codigo!=null){
				if(codigo.trim().length()>60)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.codigo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),60));
				}
	}
	
	public static void validarDescripcion(String descripcion)
	 throws Validador
	{					
			if(descripcion==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.ingrese"),
			Messages.getStringToKey("MsUnidadesOrg.descripcion"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
			if(descripcion.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoingrese"),
			Messages.getStringToKey("MsUnidadesOrg.descripcion"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));						
			if(descripcion!=null){
				if(descripcion.trim().length()>255)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.descripcion"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),255));
				}
	}
	
	public static void validarAcronimo(String acronimo)
	 throws Validador
	{					
			if(acronimo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.ingrese"),
			Messages.getStringToKey("MsUnidadesOrg.acronimo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
			if(acronimo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoingrese"),
			Messages.getStringToKey("MsUnidadesOrg.acronimo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));						
			if(acronimo!=null){
				if(acronimo.trim().length()>25)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.noexceder"),
			Messages.getStringToKey("MsUnidadesOrg.acronimo"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla"),25));
				}
	}
	
	public static void validarFlagofgeneral(Integer flagofgeneral)
	 throws Validador
	{				
					if(flagofgeneral==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoingrese"),
			Messages.getStringToKey("MsUnidadesOrg.flagofgeneral"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
	}	
	
	
	
	
	
	
	public static void validarEstado(Integer estado)
	 throws Validador
	{				
					if(estado==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.seleccione"),
			Messages.getStringToKey("MsUnidadesOrg.estado"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
			if(estado.intValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUnidadesOrg.invalidoseleccione"),
			Messages.getStringToKey("MsUnidadesOrg.estado"),
			Messages.getStringToKey("MsUnidadesOrg.titulotabla")));
	}
	
	
		
}