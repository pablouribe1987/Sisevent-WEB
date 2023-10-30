package pe.gob.mef.sisevent.bs.transfer;

/**
 * MS_UNIDADES_ORG BAKING: UNIDADES ORGÁNICAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37 / Creación de la clase /
 * 
 */
public class MsUnidadesOrgDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5741045184803328449L;

	// ID
	private Long idunidad;

	// PROPIEDADES
	private Long idpadre = null;
	private String codigo = null;
	private String descripcion = null;
	private String acronimo = null;
	private Integer flagofgeneral = null;

	public MsUnidadesOrgDto() {
	}

	public Long getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}

	public Long getIdpadre() {
		return this.idpadre;
	}

	public void setIdpadre(Long idpadre) {
		this.idpadre = idpadre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAcronimo() {
		return this.acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public Integer getFlagofgeneral() {
		return this.flagofgeneral;
	}

	public void setFlagofgeneral(Integer flagofgeneral) {
		this.flagofgeneral = flagofgeneral;
	}

}