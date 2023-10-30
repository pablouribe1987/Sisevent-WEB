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
 * TD_ANEXOS ENTIDAD: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 07/01/2021 06:00
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 07/01/2021 06:00 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "TD_ANEXOS")
public class TdAnexos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5452932695891728089L;

	// ID
	private Long idanexo = null;

	// PROPIEDADES
	private Long idsacc = null;
	private String filename = null;
	private String filenameoriginal = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Long lastmodified = null;
	private Long tamanio = null;
	private String tipo = null;
	private Long idflujo = null;

	public TdAnexos() {
	}

	@SequenceGenerator(name = "G_TD_ANEXOS", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_ANEXOS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_ANEXOS")
	@Id
	@Column(name = "IDANEXO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdanexo() {
		return this.idanexo;
	}

	public void setIdanexo(Long idanexo) {
		this.idanexo = idanexo;
	}

	@Column(name = "IDSACC", precision = 10, scale = 0)
	public Long getIdsacc() {
		return this.idsacc;
	}

	public void setIdsacc(Long idsacc) {
		this.idsacc = idsacc;
	}

	@Column(name = "FILENAME", nullable = false, length = 255)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILENAMEORIGINAL", length = 255)
	public String getFilenameoriginal() {
		return this.filenameoriginal;
	}

	public void setFilenameoriginal(String filenameoriginal) {
		this.filenameoriginal = filenameoriginal;
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

	@Column(name = "LASTMODIFIED", precision = 16, scale = 0)
	public Long getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(Long lastmodified) {
		this.lastmodified = lastmodified;
	}

	@Column(name = "TAMANIO", precision = 10, scale = 0)
	public Long getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	@Column(name = "TIPO", length = 100)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "IDFLUJO", nullable = false, precision = 10, scale = 0)
	public Long getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(Long idflujo) {
		this.idflujo = idflujo;
	}

}
