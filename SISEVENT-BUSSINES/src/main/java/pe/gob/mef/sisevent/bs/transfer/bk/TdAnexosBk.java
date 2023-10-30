package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

/**
 * TD_ANEXOS BAKING: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 07/01/2021 06:00
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 07/01/2021 06:00   / Creación de la clase    /
 * 
 */
public class TdAnexosBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8750051990310097446L;

	//ID
	private Long idanexo;
		
	//PROPIEDADES
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
	
	
	//ADICIONALES
	private String idsaccTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
			
	public TdAnexosBk() {
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
	
	public String getFilename() {
						return this.filename;
					}

	public void setFilename(String filename) {
						this.filename = filename;
					}
	
	public String getFilenameoriginal() {
						return this.filenameoriginal;
					}

	public void setFilenameoriginal(String filenameoriginal) {
						this.filenameoriginal = filenameoriginal;
					}
	
	public Timestamp getFechaCrea() {
						return this.fechaCrea;
					}

	public void setFechaCrea(Timestamp fechaCrea) {
						this.fechaCrea = fechaCrea;
					}
	
	public Timestamp getFechaModif() {
						return this.fechaModif;
					}

	public void setFechaModif(Timestamp fechaModif) {
						this.fechaModif = fechaModif;
					}
	
	public Long getIduserCrea() {
						return this.iduserCrea;
					}

	public void setIduserCrea(Long iduserCrea) {
						this.iduserCrea = iduserCrea;
					}
	
	public Long getIduserModif() {
						return this.iduserModif;
					}

	public void setIduserModif(Long iduserModif) {
						this.iduserModif = iduserModif;
					}
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	public String getRmtaddress() {
						return this.rmtaddress;
					}

	public void setRmtaddress(String rmtaddress) {
						this.rmtaddress = rmtaddress;
					}
	
	public String getRmtaddressrst() {
						return this.rmtaddressrst;
					}

	public void setRmtaddressrst(String rmtaddressrst) {
						this.rmtaddressrst = rmtaddressrst;
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
	public String getIduserCreaTxt() {
		return this.iduserCreaTxt;
	}
	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}
	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}
	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}
		
}