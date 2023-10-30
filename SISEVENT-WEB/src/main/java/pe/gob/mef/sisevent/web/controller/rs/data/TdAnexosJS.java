package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * MSUSUARIOS BAKING: USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 09/05/2020 02:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /09/05/2020 02:37 / Creación de la clase /
 * 
 */
@XmlRootElement
public class TdAnexosJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4251879429204405926L;

	// ID
	private Long idanexo;

	// PROPIEDADES
	private Long idsacc = null;
	private String filename = null;
	private String filenameoriginal = null;
	private Long lastmodified = null;
	private Long tamanio = null;
	private String tipo = null;
	private Long idflujo = null;

	// ADICIONALES
	private String idsaccTxt = null;
	private String iduserModifTxt = null;
	private String data = null;
    private String letter = null;
    
	public TdAnexosJS() {
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
		return this.idsaccTxt;
	}

	public void setIdsaccTxt(String idsaccTxt) {
		this.idsaccTxt = idsaccTxt;
	}

	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}

	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

}
