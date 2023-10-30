package pe.gob.mef.sisevent.web.controller.rs.data;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * MSUSUARIOS BAKING: USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 09/05/2020 02:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ 
 *          /Carlos Aguilar Chamochumbi /09/05/2020 02:37  / Creación de la clase /
 * 
 */
@XmlRootElement
public class MsPaisesJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3231437965797741886L;

	// ID
	private Long paiPk;

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
	
	
	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private Integer editopcion = 1;

	public MsPaisesJS() {
	}

	public Long getPaiPk() {
		return this.paiPk;
	}

	public void setPaiPk(Long paiPk) {
		this.paiPk = paiPk;
	}
	
	public Long getPaiIsonum() {
						return this.paiIsonum;
					}

	public void setPaiIsonum(Long paiIsonum) {
						this.paiIsonum = paiIsonum;
					}
	
	public String getPaiIso2() {
						return this.paiIso2;
					}

	public void setPaiIso2(String paiIso2) {
						this.paiIso2 = paiIso2;
					}
	
	public String getPaiIso3() {
						return this.paiIso3;
					}

	public void setPaiIso3(String paiIso3) {
						this.paiIso3 = paiIso3;
					}
	
	public String getPaiNombre() {
						return this.paiNombre;
					}

	public void setPaiNombre(String paiNombre) {
						this.paiNombre = paiNombre;
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

	public Integer getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(Integer editopcion) {
		this.editopcion = editopcion;
	}		
}
