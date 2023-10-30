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
 * MS_INSTITUCIONES ENTIDAD: INSTITUCIONES Y ENTIDADES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "MS_INSTITUCIONES")
public class MsInstituciones implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ID
	private Long idprovee = null;

	// PROPIEDADES
	private String razonSocial = null;
	private String siglas = null;
	private String ruc = null;
	private String correo = null;
	private String web = null;
	private String telefono = null;
	private String fax = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private Integer coddist = null;
	private String direccion = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private String tipoentidad = null;
	
	public MsInstituciones() {
	}

	@SequenceGenerator(name = "G_MS_INSTITUCIONES", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_MS_INSTITUCIONES", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_INSTITUCIONES")
	@Id
	@Column(name = "IDPROVEE", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdprovee() {
		return this.idprovee;
	}

	public void setIdprovee(Long idprovee) {
		this.idprovee = idprovee;
	}

	@Column(name = "RAZON_SOCIAL", nullable = false, length = 255)
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "SIGLAS", length = 50)
	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@Column(name = "RUC", length = 20)
	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@Column(name = "CORREO", length = 150)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "WEB", length = 150)
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "TELEFONO", length = 100)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "FAX", length = 100)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "CODPAIS", precision = 10, scale = 0)
	public Long getCodpais() {
		return this.codpais;
	}

	public void setCodpais(Long codpais) {
		this.codpais = codpais;
	}

	@Column(name = "CODDPTO", precision = 2, scale = 0)
	public Integer getCoddpto() {
		return this.coddpto;
	}

	public void setCoddpto(Integer coddpto) {
		this.coddpto = coddpto;
	}

	@Column(name = "CODPROV", precision = 2, scale = 0)
	public Integer getCodprov() {
		return this.codprov;
	}

	public void setCodprov(Integer codprov) {
		this.codprov = codprov;
	}

	@Column(name = "CODDIST", precision = 2, scale = 0)
	public Integer getCoddist() {
		return this.coddist;
	}

	public void setCoddist(Integer coddist) {
		this.coddist = coddist;
	}

	@Column(name = "DIRECCION", length = 550)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	@Column(name = "RMTADDRESSRST", length = 50)
	public String getRmtaddressrst() {
		return this.rmtaddressrst;
	}

	public void setRmtaddressrst(String rmtaddressrst) {
		this.rmtaddressrst = rmtaddressrst;
	}
	
	@Column(name = "TIPOENTIDAD", length = 150)
	public String  getTipoentidad()
	{
			return this.tipoentidad;
	}

	public void setTipoentidad(String tipoentidad)
	{
			this.tipoentidad = tipoentidad;
	}
}
