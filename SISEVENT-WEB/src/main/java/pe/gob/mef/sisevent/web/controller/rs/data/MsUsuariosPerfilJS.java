package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * MSUSUARIOS BAKING: USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 09/05/2020 02:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ 
 *          /Carlos Aguilar Chamochumbi /09/05/2020 02:37  / Creación de la clase /
 * 
 */
@XmlRootElement
public class MsUsuariosPerfilJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159960599424027052L;

	// ID
	private Long idusuario;

	// PROPIEDADES
	private String username = null;
	private String nombrecompleto = null;
	private String contrasenia = null;
	private String sede = null;
	private Long idunidad = null;
	
	// ADICIONALES
	private String idunidadTxt = null;
	private String contraseniaConfir = null;
	
	public MsUsuariosPerfilJS() {
	}

	public Long getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContraseniaConfir() {
		return contraseniaConfir;
	}

	public void setContraseniaConfir(String contraseniaConfir) {
		this.contraseniaConfir = contraseniaConfir;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Long getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}

	public String getIdunidadTxt() {
		return idunidadTxt;
	}

	public void setIdunidadTxt(String idunidadTxt) {
		this.idunidadTxt = idunidadTxt;
	}		
}
