package pe.gob.mef.sisevent.bs.transfer;

/**
 * TD_ANEXOS BAKING: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 07/01/2021 06:00
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 07/01/2021 06:00 / Creación de la clase /
 * 
 */
public class TdAnexosDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8966873535783463695L;

	// ID
	private Long idanexo;

	// PROPIEDADES
	private Long idsacc = null;
	private String filenameoriginal = null;
	private Long lastmodified = null;
	private Long tamanio = null;
	private String tipo = null;
	private Long idflujo = null;

	private String idsaccTxt = null;
	private String iduserModifTxt = null;
	private String data = null;

	public TdAnexosDto() {
	}

	public Long getIdanexo() {
		return this.idanexo;
	}

	public void setIdanexo(Long idanexo) {
		this.idanexo = idanexo;
	}

	public Long getIdsacc() {
		return this.idsacc;
	}

	public void setIdsacc(Long idsacc) {
		this.idsacc = idsacc;
	}

	public String getFilenameoriginal() {
		return this.filenameoriginal;
	}

	public void setFilenameoriginal(String filenameoriginal) {
		this.filenameoriginal = filenameoriginal;
	}

	public Long getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(Long lastmodified) {
		this.lastmodified = lastmodified;
	}

	public Long getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(Long idflujo) {
		this.idflujo = idflujo;
	}

	public String getIdsaccTxt() {
		return idsaccTxt;
	}

	public void setIdsaccTxt(String idsaccTxt) {
		this.idsaccTxt = idsaccTxt;
	}

	public String getIduserModifTxt() {
		return iduserModifTxt;
	}

	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}