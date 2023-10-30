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
public class MsInstitucionesJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6974178890217252754L;

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
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private String tipoentidad = null;
	
	// ADICIONALES
	private String codpaisTxt = null;
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private Integer editopcion = 1;

	public MsInstitucionesJS() {
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

	public String getTipoentidad() {
		return tipoentidad;
	}

	public void setTipoentidad(String tipoentidad) {
		this.tipoentidad = tipoentidad;
	}	
	
}
