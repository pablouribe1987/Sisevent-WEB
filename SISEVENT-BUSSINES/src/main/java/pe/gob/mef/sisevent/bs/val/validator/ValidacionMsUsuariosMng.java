package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;

/**
 * MS_USUARIOS SERVICIO VALIDACIÓN: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          25/11/2020 23:37 / Creación de la clase /
 * 
 */
public class ValidacionMsUsuariosMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7363085739317886925L;
	public static final Logger log = Logger.getLogger(ValidacionMsUsuariosMng.class.getName());

	public static void validarMsUsuariosBk(MsUsuariosBk MsUsuariosBk, boolean solocontrasenia) throws Validador {
		// FORANEAS
		if (MsUsuariosBk.getIduserCrea() != null && MsUsuariosBk.getIduserCrea().longValue() <= 0) {
			MsUsuariosBk.setIduserCrea(null);
		}
		if (MsUsuariosBk.getIduserModif() != null && MsUsuariosBk.getIduserModif().longValue() <= 0) {
			MsUsuariosBk.setIduserModif(null);
		}

		if (MsUsuariosBk.getIdunidad() != null && MsUsuariosBk.getIdunidad().longValue() <= 0) {
			MsUsuariosBk.setIdunidad(null);
		}

		// VALIDANDO
		if(!solocontrasenia){
		validarUsername(MsUsuariosBk.getUsername());
		if (MsUsuariosBk.getUsername() != null) {
			if (MsUsuariosBk.getUsername().trim().length() > 50)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.username"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 50));
			// MsUsuariosBk.setUsername(MsUsuariosBk.getUsername().toUpperCase());
		}
		validarNombres(MsUsuariosBk.getNombres());
		if (MsUsuariosBk.getNombres() != null) {
			if (MsUsuariosBk.getNombres().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.nombres"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
			MsUsuariosBk.setNombres(MsUsuariosBk.getNombres().toUpperCase());
		}

		validarApellidoPaterno(MsUsuariosBk.getApellidoPaterno());
		if (MsUsuariosBk.getApellidoPaterno() != null) {
			if (MsUsuariosBk.getApellidoPaterno().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.apellidoPaterno"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
			MsUsuariosBk.setApellidoPaterno(MsUsuariosBk.getApellidoPaterno().toUpperCase());
		}

		validarApellidoMaterno(MsUsuariosBk.getApellidoMaterno());
		if (MsUsuariosBk.getApellidoMaterno() != null) {
			if (MsUsuariosBk.getApellidoMaterno().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.apellidoMaterno"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
			MsUsuariosBk.setApellidoMaterno(MsUsuariosBk.getApellidoMaterno().toUpperCase());
		}

		validarEstado(MsUsuariosBk.getEstado());

		validarSede(MsUsuariosBk.getSede());

		validarIdunidad(MsUsuariosBk.getIdunidad());
		}
		
		boolean validarcontrasenia = false;
		if (MsUsuariosBk.getIdusuario() == null) {
			validarcontrasenia = true;
		} else if (MsUsuariosBk.getIdusuario().longValue() <= 0) {
			validarcontrasenia = true;
		} else if (MsUsuariosBk.getContrasenia() != null && MsUsuariosBk.getContrasenia().length() > 0) {
			validarcontrasenia = true;
		}

		if (validarcontrasenia) {
			validarContrasenia(MsUsuariosBk.getContrasenia());
			if (MsUsuariosBk.getContrasenia() != null) {
				if (MsUsuariosBk.getContrasenia().trim().length() > 50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
							Messages.getStringToKey("MsUsuarios.contrasenia"),
							Messages.getStringToKey("MsUsuarios.titulotabla"), 50));
				// MsUsuariosBk.setContrasenia(MsUsuariosBk.getContrasenia().toUpperCase());
			}

			/***
			 * ((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16}) (?=.*[a-z]) : This matches the presence of at least
			 * one lowercase letter. (?=.*d) : This matches the presence of at least one digit i.e. 0-9. (?=.*[@#$%]) :
			 * This matches the presence of at least one special character. ((?=.*[A-Z]) : This matches the presence of
			 * at least one capital letter. {6,16} : This limits the length of password from minimum 6 letters to
			 * maximum 16 letters.
			 */

			if (MsUsuariosBk.getContrasenia().trim().length() <= 5) {
				throw new Validador(Messages.getStringToKey("MsUsuariosRegistro.contrasenia_6_caracteres"));
			}
			if (MsUsuariosBk.getContraseniaConfir() == null) {
				throw new Validador(Messages.getStringToKey("MsUsuariosRegistro.contrasenia_debe_reingresar"));
			} else if (MsUsuariosBk.getContraseniaConfir().trim().length() <= 5) {
				throw new Validador(Messages.getStringToKey("MsUsuariosRegistro.contrasenia_reingrese"));
			} else if (!MsUsuariosBk.getContraseniaConfir().equals(MsUsuariosBk.getContrasenia())) {
				throw new Validador(Messages.getStringToKey("MsUsuariosRegistro.contrasenia_no_coincide"));
			}

			String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,50})"; // "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})"
			Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
			Matcher matcher = pattern.matcher(MsUsuariosBk.getContrasenia());
			boolean resultado = matcher.matches();
			if (!resultado) {
				throw new Validador(
						"LA CONTRASEÑA DEBE AL MENOS CONTENER UN DÍGITO DE 0-9,  DEBE CONTENER AL MENOS UNA LETRA EN MINÚSCULA, DEBE CONTENER AL MENOS UNA LETRA MAYÚSCULA Y DEBE TENER UNA LONGITUD DE AL MENOS 6 CARACTERES Y MÁXIMO DE 50 CARACTERES."); // DEBE
																																																															// CONTENER
																																																												// $%\"
			}
		}
		
		//validarAnexo(MsUsuariosBk.getAnexo());
				if(MsUsuariosBk.getAnexo()!=null){
						if(MsUsuariosBk.getAnexo().trim().length()>20)
							throw new Validador(MessageFormat.format(
									Messages.getStringToKey("MsUsuarios.noexceder"),
									Messages.getStringToKey("MsUsuarios.anexo"),
									Messages.getStringToKey("MsUsuarios.titulotabla"),
									20,
									Messages.getStringToKey("MsUsuarios.articuloAnexo")
											));				
//						MsUsuariosBk.setAnexo(MsUsuariosBk.getAnexo().toUpperCase());
						}

				
				//validarCelular(MsUsuariosBk.getCelular());
				if(MsUsuariosBk.getCelular()!=null){
						if(MsUsuariosBk.getCelular().trim().length()>20)
							throw new Validador(MessageFormat.format(
									Messages.getStringToKey("MsUsuarios.noexceder"),
									Messages.getStringToKey("MsUsuarios.celular"),
									Messages.getStringToKey("MsUsuarios.titulotabla"),
									20,
									Messages.getStringToKey("MsUsuarios.articuloCelular")
											));				
//						MsUsuariosBk.setCelular(MsUsuariosBk.getCelular().toUpperCase());
						}

		if(!solocontrasenia){		
				validarCorreo(MsUsuariosBk.getCorreo());
				if(MsUsuariosBk.getCorreo()!=null){
						if(MsUsuariosBk.getCorreo().trim().length()>200)
							throw new Validador(MessageFormat.format(
									Messages.getStringToKey("MsUsuarios.noexceder"),
									Messages.getStringToKey("MsUsuarios.correo"),
									Messages.getStringToKey("MsUsuarios.titulotabla"),
									200,
									Messages.getStringToKey("MsUsuarios.articuloCorreo")
											));				
//						MsUsuariosBk.setCorreo(MsUsuariosBk.getCorreo().toUpperCase());
						}
		}

	}

	public static void validarUsername(String username) throws Validador {
		if (username == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.username"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (username.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.username"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (username != null) {
			if (username.trim().length() > 50)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.username"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 50));
		}
	}

	public static void validarNombres(String nombres) throws Validador {
		if (nombres == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.nombres"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (nombres.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.nombres"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (nombres != null) {
			if (nombres.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.nombres"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
		}
	}

	public static void validarApellidoPaterno(String apellidoPaterno) throws Validador {
		if (apellidoPaterno == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.apellidoPaterno"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (apellidoPaterno.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.apellidoPaterno"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (apellidoPaterno != null) {
			if (apellidoPaterno.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.apellidoPaterno"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
		}
	}

	public static void validarApellidoMaterno(String apellidoMaterno) throws Validador {
		if (apellidoMaterno == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.apellidoMaterno"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (apellidoMaterno.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.apellidoMaterno"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (apellidoMaterno != null) {
			if (apellidoMaterno.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
						Messages.getStringToKey("MsUsuarios.apellidoMaterno"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 100));
		}
	}

	public static void validarEstado(Integer estado) throws Validador {
		if (estado == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.seleccione"),
					Messages.getStringToKey("MsUsuarios.estado"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (estado.intValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoseleccione"),
					Messages.getStringToKey("MsUsuarios.estado"), Messages.getStringToKey("MsUsuarios.titulotabla")));
	}

	public static void validarSede(String sede) throws Validador {
		if (sede == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingreseL"),
					Messages.getStringToKey("MsUsuarios.sede"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (sede.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingreseL"),
					Messages.getStringToKey("MsUsuarios.sede"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (sede != null) {
			if (sede.trim().length() > 150)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexcederL"),
						Messages.getStringToKey("MsUsuarios.sede"), Messages.getStringToKey("MsUsuarios.titulotabla"),
						150));
		}
	}

	public static void validarIdunidad(Long idunidad) throws Validador {
		if (idunidad == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.seleccioneL"),
					Messages.getStringToKey("MsUsuarios.idunidad"), Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (idunidad.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoseleccioneL"),
					Messages.getStringToKey("MsUsuarios.idunidad"), Messages.getStringToKey("MsUsuarios.titulotabla")));
	}

	public static void validarContrasenia(String contrasenia) throws Validador {
		if (contrasenia == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingreseL"),
					Messages.getStringToKey("MsUsuarios.contrasenia"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (contrasenia.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingreseL"),
					Messages.getStringToKey("MsUsuarios.contrasenia"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));
		if (contrasenia != null) {
			if (contrasenia.trim().length() > 50)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexcederL"),
						Messages.getStringToKey("MsUsuarios.contrasenia"),
						Messages.getStringToKey("MsUsuarios.titulotabla"), 50));
		}
	}
	
	public static void validarAnexo(String anexo)
			 throws Validador
			{					
					if(anexo==null)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.anexo"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),
					Messages.getStringToKey("MsUsuarios.articuloAnexo")));
					if(anexo.trim().length()<=0)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.anexo"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));						
					if(anexo!=null){
						if(anexo.trim().length()>20)
							throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
					Messages.getStringToKey("MsUsuarios.anexo"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),20,
					Messages.getStringToKey("MsUsuarios.articuloAnexo")));
						}
			}
			
			public static void validarCelular(String celular)
			 throws Validador
			{					
					if(celular==null)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.celular"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),
					Messages.getStringToKey("MsUsuarios.articuloCelular")));
					if(celular.trim().length()<=0)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.celular"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));						
					if(celular!=null){
						if(celular.trim().length()>20)
							throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
					Messages.getStringToKey("MsUsuarios.celular"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),20,
					Messages.getStringToKey("MsUsuarios.articuloCelular")));
						}
			}
			
			public static void validarCorreo(String correo)
			 throws Validador
			{					
					if(correo==null)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.ingrese"),
					Messages.getStringToKey("MsUsuarios.correo"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),
					Messages.getStringToKey("MsUsuarios.articuloCorreo")));
					if(correo.trim().length()<=0)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.invalidoingrese"),
					Messages.getStringToKey("MsUsuarios.correo"),
					Messages.getStringToKey("MsUsuarios.titulotabla")));						
					if(correo!=null){
						if(correo.trim().length()>200)
							throw new Validador(MessageFormat.format(Messages.getStringToKey("MsUsuarios.noexceder"),
					Messages.getStringToKey("MsUsuarios.correo"),
					Messages.getStringToKey("MsUsuarios.titulotabla"),200,
					Messages.getStringToKey("MsUsuarios.articuloCorreo")));
						}
			}
}