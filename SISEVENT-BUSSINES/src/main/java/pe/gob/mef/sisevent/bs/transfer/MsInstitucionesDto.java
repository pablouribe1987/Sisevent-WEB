package pe.gob.mef.sisevent.bs.transfer;

/**
 * MS_INSTITUCIONES BAKING: INSTITUCIONES Y ENTIDADES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class MsInstitucionesDto implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8528702670272160978L;

	// ID
	private Long idprovee;

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
	private String tipoentidad = null;

	// ADICIONALES
	private String codpaisTxt = null;
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;
	
	public MsInstitucionesDto() {
	}

	public Long getIdprovee() {
		return this.idprovee;
	}

	public void setIdprovee(Long idprovee) {
		this.idprovee = idprovee;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getCodpais() {
		return this.codpais;
	}

	public void setCodpais(Long codpais) {
		this.codpais = codpais;
	}

	public Integer getCoddpto() {
		return this.coddpto;
	}

	public void setCoddpto(Integer coddpto) {
		this.coddpto = coddpto;
	}

	public Integer getCodprov() {
		return this.codprov;
	}

	public void setCodprov(Integer codprov) {
		this.codprov = codprov;
	}

	public Integer getCoddist() {
		return this.coddist;
	}

	public void setCoddist(Integer coddist) {
		this.coddist = coddist;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodpaisTxt() {
		return this.codpaisTxt;
	}

	public void setCodpaisTxt(String codpaisTxt) {
		this.codpaisTxt = codpaisTxt;
	}

	public String getCoddptoTxt() {
		return this.coddptoTxt;
	}

	public void setCoddptoTxt(String coddptoTxt) {
		this.coddptoTxt = coddptoTxt;
	}

	public String getCodprovTxt() {
		return this.codprovTxt;
	}

	public void setCodprovTxt(String codprovTxt) {
		this.codprovTxt = codprovTxt;
	}

	public String getCoddistTxt() {
		return this.coddistTxt;
	}

	public void setCoddistTxt(String coddistTxt) {
		this.coddistTxt = coddistTxt;
	}

	public String getTipoentidad() {
		return tipoentidad;
	}

	public void setTipoentidad(String tipoentidad) {
		this.tipoentidad = tipoentidad;
	}

}