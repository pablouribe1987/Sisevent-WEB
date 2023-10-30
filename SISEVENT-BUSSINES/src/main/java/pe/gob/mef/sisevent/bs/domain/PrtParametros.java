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
 * PRT_PARAMETROS ENTIDAD: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL SISTEMA
 * PARÁMETROS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37 / Creación de la clase
 *          /
 * 
 */
@Entity
@Table(name = "PRT_PARAMETROS")
public class PrtParametros implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3570933083228068926L;

	// ID
	private Long idparametro = null;

	// PROPIEDADES
	private Long idpadre = null;
	private String descripcion = null;
	private String descripcioncorta = null;
	private Long orden = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;

	public PrtParametros() {
	}

	@SequenceGenerator(name = "G_PRT_PARAMETROS", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_PRT_PARAMETROS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_PRT_PARAMETROS")
	@Id
	@Column(name = "IDPARAMETRO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(Long idparametro) {
		this.idparametro = idparametro;
	}

	@Column(name = "IDPADRE", precision = 10, scale = 0)
	public Long getIdpadre() {
		return this.idpadre;
	}

	public void setIdpadre(Long idpadre) {
		this.idpadre = idpadre;
	}

	@Column(name = "DESCRIPCION", nullable = false, length = 150)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "DESCRIPCIONCORTA", length = 150)
	public String getDescripcioncorta() {
		return this.descripcioncorta;
	}

	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}

	@Column(name = "ORDEN", precision = 10, scale = 0)
	public Long getOrden() {
		return this.orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
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

}
