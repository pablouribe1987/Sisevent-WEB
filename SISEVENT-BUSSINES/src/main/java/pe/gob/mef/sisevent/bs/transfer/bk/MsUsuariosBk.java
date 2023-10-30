package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;
import java.util.List;

import pe.gob.mef.sisevent.bs.ctlracceso.MsUsuariosACL;

/**
 * MS_USUARIOS BAKING: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37 / Creación de la clase /
 * 
 */
public class MsUsuariosBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8657058221870969062L;

	// ID
	private Long idusuario;

	// PROPIEDADES
	private String username = null;
	private String nombres = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String contrasenia = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;
	private String sede = null;
	private Long idunidad = null;
	private String anexo = null;
	private String celular = null;
	private String correo = null;

	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private String idunidadTxt = null;
	private String idunidadAcroTxt = null;
	private String contraseniaConfir = null;
	private List<String> rolesSistema = null;
	private List<String> rolesSistemaDes = null;
	private MsUsuariosACL msUsuariosACL = null;
	//MPINARES 01092023 - INICIO 
		private String nombreCompleto = null;
		private String cclase = null;
		private String roles = null;
		//MPINARES 01092023 - FIN
	public MsUsuariosBk() {
	}

	public MsUsuariosBk(MsUsuariosBk hijo) {
		super();
		this.idusuario = hijo.getIdusuario();
		this.username = hijo.getUsername();
		this.nombres = hijo.getNombres();
		this.apellidoPaterno = hijo.getApellidoPaterno();
		this.apellidoMaterno = hijo.getApellidoMaterno();
		this.contrasenia = hijo.getContrasenia();
		this.iduserCrea = hijo.getIduserCrea();
		this.iduserModif = hijo.getIduserModif();
		this.fechaCrea = hijo.getFechaCrea();
		this.fechaModif = hijo.getFechaModif();
		this.rtmaddress = hijo.getRtmaddress();
		this.rtmaddressmodif = hijo.getRtmaddressmodif();
		this.estado = hijo.getEstado();
		this.sede = hijo.getSede();
		this.idunidad = hijo.getIdunidad();
		this.iduserCreaTxt = hijo.getIduserCreaTxt();
		this.iduserModifTxt = hijo.getIduserModifTxt();
		this.idunidadTxt = hijo.getIdunidadTxt();
		this.contraseniaConfir = hijo.getContraseniaConfir();
		this.rolesSistema = hijo.getRolesSistema();
		this.msUsuariosACL = hijo.getMsUsuariosACL();
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

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Long getIduserCrea() {
		return this.iduserCrea;
	}

	public void setIduserCrea(Long iduserCrea) {
		this.iduserCrea = iduserCrea;
	}

	public Long getIduserModif() {
		return this.iduserModif;
	}

	public void setIduserModif(Long iduserModif) {
		this.iduserModif = iduserModif;
	}

	public Timestamp getFechaCrea() {
		return this.fechaCrea;
	}

	public void setFechaCrea(Timestamp fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Timestamp getFechaModif() {
		return this.fechaModif;
	}

	public void setFechaModif(Timestamp fechaModif) {
		this.fechaModif = fechaModif;
	}

	public String getRtmaddress() {
		return this.rtmaddress;
	}

	public void setRtmaddress(String rtmaddress) {
		this.rtmaddress = rtmaddress;
	}

	public String getRtmaddressmodif() {
		return this.rtmaddressmodif;
	}

	public void setRtmaddressmodif(String rtmaddressmodif) {
		this.rtmaddressmodif = rtmaddressmodif;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Long getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}

	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getIduserCreaTxt() {
		return this.iduserCreaTxt;
	}

	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}

	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}

	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}

	public String getIdunidadTxt() {
		return this.idunidadTxt;
	}

	public void setIdunidadTxt(String idunidadTxt) {
		this.idunidadTxt = idunidadTxt;
	}

	public String getContraseniaConfir() {
		return contraseniaConfir;
	}

	public void setContraseniaConfir(String contraseniaConfir) {
		this.contraseniaConfir = contraseniaConfir;
	}

	public List<String> getRolesSistema() {
		return rolesSistema;
	}

	public void setRolesSistema(List<String> rolesSistema) {
		this.rolesSistema = rolesSistema;
	}

	public MsUsuariosACL getMsUsuariosACL() {
		return msUsuariosACL;
	}

	public void setMsUsuariosACL(MsUsuariosACL msUsuariosACL) {
		this.msUsuariosACL = msUsuariosACL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MsUsuariosBk other = (MsUsuariosBk) obj;
		if (idusuario == null) {
			if (other.idusuario != null)
				return false;
		} else if (!idusuario.equals(other.idusuario))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public List<String> getRolesSistemaDes() {
		return rolesSistemaDes;
	}

	public void setRolesSistemaDes(List<String> rolesSistemaDes) {
		this.rolesSistemaDes = rolesSistemaDes;
	}

	public String getRoles() {
		StringBuffer s = new StringBuffer();
		if (rolesSistemaDes != null) {
			for (String rol : rolesSistemaDes) {
				s.append(rol + " \n");
			}
		}
		roles= s.toString();
		return roles;
	}

	public String getCclase() {
		if (estado != null && estado.intValue() > 0) {
			cclase= "cverde";
		} else {

		}
		cclase= "camarillo";
		return cclase;
	}

	public void setCclase(String cclase) {
	}

	public String getIdunidadAcroTxt() {
		return idunidadAcroTxt;
	}

	public void setIdunidadAcroTxt(String idunidadAcroTxt) {
		this.idunidadAcroTxt = idunidadAcroTxt;
	}

	public String geNombreCompleto() {
		return ((getNombres() == null ? "" : (getNombres() + " "))
				+ (getApellidoPaterno() == null ? "" : (getApellidoPaterno() + " "))
				+ (getApellidoMaterno() == null ? "" : (getApellidoMaterno() + " "))).trim()
						.toUpperCase();
	}
	
	public String getNombreCompleto() {
		nombreCompleto=((getNombres() == null ? "" : (getNombres() + " "))
				+ (getApellidoPaterno() == null ? "" : (getApellidoPaterno() + " "))
				+ (getApellidoMaterno() == null ? "" : (getApellidoMaterno() + " "))).trim()
						.toUpperCase();
		
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}