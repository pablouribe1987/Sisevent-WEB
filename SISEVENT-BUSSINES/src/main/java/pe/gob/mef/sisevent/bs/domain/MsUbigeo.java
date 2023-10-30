package pe.gob.mef.sisevent.bs.domain;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MS_UBIGEO ENTIDAD: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 23/12/2020 11:52 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "MS_UBIGEO")
public class MsUbigeo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8969440550894769020L;

	// ID
	private MsUbigeoId id = null;

	// PROPIEDADES
	private String nombre = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;

	public MsUbigeo() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "coddist", column = @Column(name = "CODDIST", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "coddpto", column = @Column(name = "CODDPTO", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "codprov", column = @Column(name = "CODPROV", nullable = false, precision = 2, scale = 0)) })
	public MsUbigeoId getId() {
		return this.id;
	}

	public void setId(MsUbigeoId id) {
		this.id = id;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
