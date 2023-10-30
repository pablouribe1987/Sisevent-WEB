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
 * MS_PERSONAS ENTIDAD: PERSONAS NATURALES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "MS_PERSONAS")
public class MsPersonas implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -138247840941242984L;

	// ID
	private Long idperson = null;

	// PROPIEDADES
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String nombres = null;
	private String sexo = null;
	private Long tipodocumento = null;
	private String documentoNumero = null;
	private String correo = null;
	private String telefono = null;
	private String celular = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private Integer coddist = null;
	private String direccion = null;
	private String tienefoto = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;

	public MsPersonas() {
	}

	@SequenceGenerator(name = "G_MS_PERSONAS", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_MS_PERSONAS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_PERSONAS")
	@Id
	@Column(name = "IDPERSON", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdperson() {
		return this.idperson;
	}

	public void setIdperson(Long idperson) {
		this.idperson = idperson;
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

	@Column(name = "NOMBRES", nullable = false, length = 150)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "SEXO", length = 1)
	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(name = "TIPODOCUMENTO", precision = 10, scale = 0)
	public Long getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Long tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	@Column(name = "DOCUMENTO_NUMERO", nullable = false, length = 25)
	public String getDocumentoNumero() {
		return this.documentoNumero;
	}

	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
	}

	@Column(name = "CORREO", length = 50)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "TELEFONO", length = 15)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "CELULAR", length = 15)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "CODPAIS", nullable = false, precision = 10, scale = 0)
	public Long getCodpais() {
		return this.codpais;
	}

	public void setCodpais(Long codpais) {
		this.codpais = codpais;
	}

	@Column(name = "CODDPTO", nullable = false, precision = 2, scale = 0)
	public Integer getCoddpto() {
		return this.coddpto;
	}

	public void setCoddpto(Integer coddpto) {
		this.coddpto = coddpto;
	}

	@Column(name = "CODPROV", nullable = false, precision = 2, scale = 0)
	public Integer getCodprov() {
		return this.codprov;
	}

	public void setCodprov(Integer codprov) {
		this.codprov = codprov;
	}

	@Column(name = "CODDIST", nullable = false, precision = 2, scale = 0)
	public Integer getCoddist() {
		return this.coddist;
	}

	public void setCoddist(Integer coddist) {
		this.coddist = coddist;
	}

	@Column(name = "DIRECCION", nullable = false, length = 255)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "TIENEFOTO", length = 2)
	public String getTienefoto() {
		return this.tienefoto;
	}

	public void setTienefoto(String tienefoto) {
		this.tienefoto = tienefoto;
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

	@Column(name = "ESTADO", nullable = false, precision = 1, scale = 0)
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name = "RMTADDRESS", nullable = false, length = 50)
	public String getRmtaddress() {
		return this.rmtaddress;
	}

	public void setRmtaddress(String rmtaddress) {
		this.rmtaddress = rmtaddress;
	}

	@Column(name = "RMTADDRESSRST", nullable = false, length = 50)
	public String getRmtaddressrst() {
		return this.rmtaddressrst;
	}

	public void setRmtaddressrst(String rmtaddressrst) {
		this.rmtaddressrst = rmtaddressrst;
	}

}
