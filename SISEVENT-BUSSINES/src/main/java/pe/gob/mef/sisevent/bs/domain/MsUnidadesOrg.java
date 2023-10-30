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
 * MS_UNIDADES_ORG ENTIDAD: UNIDADES ORGÁNICAS
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
@Table(name = "MS_UNIDADES_ORG")
public class MsUnidadesOrg implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5946723006919705613L;

	// ID
	private Long idunidad = null;

	// PROPIEDADES
	private Long idpadre = null;
	private String codigo = null;
	private String descripcion = null;
	private String acronimo = null;
	private Integer flagofgeneral = null;
	private Long iduserCrea = null;
	private Timestamp fechaCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;

	public MsUnidadesOrg() {
	}

	@SequenceGenerator(name = "G_MS_UNIDADES_ORG", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_MS_UNIDADES_ORG", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_UNIDADES_ORG")
	@Id
	@Column(name = "IDUNIDAD", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}

	@Column(name = "IDPADRE", precision = 10, scale = 0)
	public Long getIdpadre() {
		return this.idpadre;
	}

	public void setIdpadre(Long idpadre) {
		this.idpadre = idpadre;
	}

	@Column(name = "CODIGO", unique = true, length = 60)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "DESCRIPCION", nullable = false, length = 255)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "ACRONIMO", length = 25)
	public String getAcronimo() {
		return this.acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	@Column(name = "FLAGOFGENERAL", precision = 1, scale = 0)
	public Integer getFlagofgeneral() {
		return this.flagofgeneral;
	}

	public void setFlagofgeneral(Integer flagofgeneral) {
		this.flagofgeneral = flagofgeneral;
	}

	@Column(name = "IDUSER_CREA", nullable = false, precision = 10, scale = 0)
	public Long getIduserCrea() {
		return this.iduserCrea;
	}

	public void setIduserCrea(Long iduserCrea) {
		this.iduserCrea = iduserCrea;
	}

	@Column(name = "FECHA_CREA", nullable = false, length = 7)
	public Timestamp getFechaCrea() {
		return this.fechaCrea;
	}

	public void setFechaCrea(Timestamp fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	@Column(name = "IDUSER_MODIF", nullable = false, precision = 10, scale = 0)
	public Long getIduserModif() {
		return this.iduserModif;
	}

	public void setIduserModif(Long iduserModif) {
		this.iduserModif = iduserModif;
	}

	@Column(name = "FECHA_MODIF", nullable = false, length = 7)
	public Timestamp getFechaModif() {
		return this.fechaModif;
	}

	public void setFechaModif(Timestamp fechaModif) {
		this.fechaModif = fechaModif;
	}

	@Column(name = "ESTADO", nullable = false, precision = 1, scale = 0)
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name = "RMTADDRESS", length = 50)
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

}
