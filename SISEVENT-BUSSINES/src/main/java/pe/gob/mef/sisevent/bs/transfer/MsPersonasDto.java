package pe.gob.mef.sisevent.bs.transfer;

/**
 * MS_PERSONAS BAKING: PERSONAS NATURALES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class MsPersonasDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419527198075072594L;

	// ID
	private Long idperson;

	// PROPIEDADES
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String nombres = null;
	private String sexo = null;
	private Long tipodocumento = null;
	private String documentoNumero = null;
	private String correo = null;
	private String telefono = null;
	private String celular = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private Integer coddist = null;
	private String direccion = null;
	private String tienefoto = null;

	// ADICIONALES
	private String tipodocumentoTxt = null;
	private String codpaisTxt = null;
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;

	public MsPersonasDto() {
	}

	public Long getIdperson() {
		return this.idperson;
	}

	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Long tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getDocumentoNumero() {
		return this.documentoNumero;
	}

	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public String getTienefoto() {
		return this.tienefoto;
	}

	public void setTienefoto(String tienefoto) {
		this.tienefoto = tienefoto;
	}

	public String getTipodocumentoTxt() {
		return this.tipodocumentoTxt;
	}

	public void setTipodocumentoTxt(String tipodocumentoTxt) {
		this.tipodocumentoTxt = tipodocumentoTxt;
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

}