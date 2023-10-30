package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsRolesBk;

/**
 * MS_ROLES SERVICIO VALIDACIÓN: ROLES DEL SISTEMA, ROLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37                      / Creación de la clase             /
 * 
 */
public class ValidacionMsRolesMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6740146745859771605L;
	public static final Logger log = Logger.getLogger(ValidacionMsRolesMng.class.getName());
	
	public static void validarMsRolesBk(MsRolesBk MsRolesBk)
	 throws Validador
	{
                //FORANEAS	        
		//VALIDANDO
		
		validarUsername(MsRolesBk.getUsername());
		if(MsRolesBk.getUsername()!=null){
				if(MsRolesBk.getUsername().trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.noexceder"),
			Messages.getStringToKey("MsRoles.username"),
			Messages.getStringToKey("MsRoles.titulotabla"),50));				
//				MsRolesBk.setUsername(MsRolesBk.getUsername().toUpperCase());
				}

//		validarRol(MsRolesBk.getRol());
//		validarEstado(MsRolesBk.getEstado());		
	}

	public static void validarUsername(String username)
	 throws Validador
	{					
			if(username==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.ingrese"),
			Messages.getStringToKey("MsRoles.username"),
			Messages.getStringToKey("MsRoles.titulotabla")));
			if(username.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.invalidoingrese"),
			Messages.getStringToKey("MsRoles.username"),
			Messages.getStringToKey("MsRoles.titulotabla")));						
			if(username!=null){
				if(username.trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.noexceder"),
			Messages.getStringToKey("MsRoles.username"),
			Messages.getStringToKey("MsRoles.titulotabla"),50));
				}
	}
	
	public static void validarRol(String rol)
	 throws Validador
	{				
					if(rol==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.ingrese"),
								Messages.getStringToKey("MsRoles.rol"),
								Messages.getStringToKey("MsRoles.titulotabla")));
								if(rol.trim().length()<=0)
								throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.invalidoingrese"),
								Messages.getStringToKey("MsRoles.rol"),
								Messages.getStringToKey("MsRoles.titulotabla")));						
								if(rol!=null){
									if(rol.trim().length()>50)
										throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.noexceder"),
								Messages.getStringToKey("MsRoles.rol"),
								Messages.getStringToKey("MsRoles.titulotabla"),50));
									}
								
	}
		
	public static void validarEstado(Integer estado)
	 throws Validador
	{				
					if(estado==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.seleccione"),
			Messages.getStringToKey("MsRoles.estado"),
			Messages.getStringToKey("MsRoles.titulotabla")));
//			if(estado.intValue()<=0)
//			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsRoles.invalidoseleccione"),
//			Messages.getStringToKey("MsRoles.estado"),
//			Messages.getStringToKey("MsRoles.titulotabla")));
	}
		
}