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
 * MS_CATEGORIAS ENTIDAD: CATEGORÍAS ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11 / Creación de la clase
 *          /
 * 
 */
@Entity
@Table(name = "MS_CATEGORIAS")
public class MsCategorias implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6025227837779544158L;

	// ID
	private Long idcategorias = null;

	// PROPIEDADES
	private String categoria = null;
	private String arraycamposocultos = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;

	public MsCategorias() {
	}

	@SequenceGenerator(name = "G_MS_CATEGORIAS", sequenceName = PropertiesMg.ESQUEMA
			+ "SQ_MS_CATEGORIAS", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_CATEGORIAS")
	@Id
	@Column(name = "IDCATEGORIAS", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdcategorias() {
		return this.idcategorias;
	}

	public void setIdcategorias(Long idcategorias) {
		this.idcategorias = idcategorias;
	}

	@Column(name = "CATEGORIA", nullable = false, length = 150)
	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column(name = "ARRAYCAMPOSOCULTOS", nullable = false, length = 500)
	public String getArraycamposocultos() {
		return this.arraycamposocultos;
	}

	public void setArraycamposocultos(String arraycamposocultos) {
		this.arraycamposocultos = arraycamposocultos;
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

	@Column(name = "ESTADO", nullable = false, precision = 1, scale = 0)
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
