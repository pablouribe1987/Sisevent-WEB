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
 * TD_FERIADOS ENTIDAD: DÍAS NO LABORABLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2021 02:31
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 11/01/2021 02:31 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "TD_FERIADOS")
public class TdFeriados implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 823339039557307172L;

	// ID
	private Long idferiado = null;

	// PROPIEDADES
	private Timestamp fechaFeriado = null;
	private String descricion = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrs = null;

	public TdFeriados() {
	}

	@SequenceGenerator(name = "G_TD_FERIADOS", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_TD_FERIADOS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_FERIADOS")
	@Id
	@Column(name = "IDFERIADO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdferiado() {
		return this.idferiado;
	}

	public void setIdferiado(Long idferiado) {
		this.idferiado = idferiado;
	}

	@Column(name = "FECHA_FERIADO", unique = true, nullable = false, length = 7)
	public Timestamp getFechaFeriado() {
		return this.fechaFeriado;
	}

	public void setFechaFeriado(Timestamp fechaFeriado) {
		this.fechaFeriado = fechaFeriado;
	}

	@Column(name = "DESCRICION", length = 255)
	public String getDescricion() {
		return this.descricion;
	}

	public void setDescricion(String descricion) {
		this.descricion = descricion;
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

	@Column(name = "RMTADDRESSRS", nullable = false, length = 50)
	public String getRmtaddressrs() {
		return this.rmtaddressrs;
	}

	public void setRmtaddressrs(String rmtaddressrs) {
		this.rmtaddressrs = rmtaddressrs;
	}

}
