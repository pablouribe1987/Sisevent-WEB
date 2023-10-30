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
 * MS_PAISES ENTIDAD: PAISES SEGÚN ISO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "MS_PAISES")
public class MsPaises implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ID
	private Long paiPk = null;

	// PROPIEDADES
	private Long paiIsonum = null;
	private String paiIso2 = null;
	private String paiIso3 = null;
	private String paiNombre = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;

	public MsPaises() {
	}

	@SequenceGenerator(name = "G_MS_PAISES", sequenceName = PropertiesMg.ESQUEMA + "SQ_MS_PAISES", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_PAISES")
	@Id
	@Column(name = "PAI_PK", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPaiPk() {
		return this.paiPk;
	}

	public void setPaiPk(Long paiPk) {
		this.paiPk = paiPk;
	}

	@Column(name = "PAI_ISONUM", unique = true, precision = 10, scale = 0)
	public Long getPaiIsonum() {
		return this.paiIsonum;
	}

	public void setPaiIsonum(Long paiIsonum) {
		this.paiIsonum = paiIsonum;
	}

	@Column(name = "PAI_ISO2", length = 5)
	public String getPaiIso2() {
		return this.paiIso2;
	}

	public void setPaiIso2(String paiIso2) {
		this.paiIso2 = paiIso2;
	}

	@Column(name = "PAI_ISO3", length = 5)
	public String getPaiIso3() {
		return this.paiIso3;
	}

	public void setPaiIso3(String paiIso3) {
		this.paiIso3 = paiIso3;
	}

	@Column(name = "PAI_NOMBRE", nullable = false, length = 100)
	public String getPaiNombre() {
		return this.paiNombre;
	}

	public void setPaiNombre(String paiNombre) {
		this.paiNombre = paiNombre;
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
