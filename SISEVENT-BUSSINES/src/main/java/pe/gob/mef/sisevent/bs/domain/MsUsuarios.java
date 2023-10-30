package pe.gob.mef.sisevent.bs.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pe.gob.mef.sisevent.bs.utils.PropertiesMg;

/**
 * MS_USUARIOS ENTIDAD: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "MS_USUARIOS")
public class MsUsuarios implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7264732001978896468L;

	// ID
	private Long idusuario = null;

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

	public MsUsuarios() {
	}

	@SequenceGenerator(name = "G_MS_USUARIOS", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_MS_USUARIOS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_USUARIOS")
	@Id
	@Column(name = "IDUSUARIO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "NOMBRES", nullable = false, length = 100)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "APELLIDO_PATERNO", nullable = false, length = 100)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "APELLIDO_MATERNO", length = 100)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "CONTRASENIA", nullable = false, length = 50)
	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Column(name = "IDUSER_CREA", nullable = false, precision = 10, scale = 0)
	public Long getIduserCrea() {
		return this.iduserCrea;
	}

	public void setIduserCrea(Long iduserCrea) {
		this.iduserCrea = iduserCrea;
	}

	@Column(name = "IDUSER_MODIF", nullable = false, precision = 10, scale = 0)
	public Long getIduserModif() {
		return this.iduserModif;
	}

	public void setIduserModif(Long iduserModif) {
		this.iduserModif = iduserModif;
	}

	@Column(name = "FECHA_CREA", nullable = false, length = 7)
	public Timestamp getFechaCrea() {
		return this.fechaCrea;
	}

	public void setFechaCrea(Timestamp fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	@Column(name = "FECHA_MODIF", nullable = false, length = 7)
	public Timestamp getFechaModif() {
		return this.fechaModif;
	}

	public void setFechaModif(Timestamp fechaModif) {
		this.fechaModif = fechaModif;
	}

	@Column(name = "RTMADDRESS", nullable = false, length = 50)
	public String getRtmaddress() {
		return this.rtmaddress;
	}

	public void setRtmaddress(String rtmaddress) {
		this.rtmaddress = rtmaddress;
	}

	@Column(name = "RTMADDRESSMODIF", nullable = false, length = 50)
	public String getRtmaddressmodif() {
		return this.rtmaddressmodif;
	}

	public void setRtmaddressmodif(String rtmaddressmodif) {
		this.rtmaddressmodif = rtmaddressmodif;
	}

	@Column(name = "ESTADO", nullable = false, precision = 1, scale = 0)
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name = "SEDE", length = 150)
	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	@Column(name = "IDUNIDAD", precision = 10, scale = 0)
	public Long getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}

	@Column(name = "ANEXO", length = 20)
	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	@Column(name = "CELULAR", length = 20)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "CORREO", length = 200)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
