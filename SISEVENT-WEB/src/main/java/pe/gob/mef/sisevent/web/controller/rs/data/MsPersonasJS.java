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
public class MsPersonasJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804374006678395910L;

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
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	
	
	// ADICIONALES
	private String tipodocumentoTxt = null;
	private String codpaisTxt = null;
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private Integer editopcion = 1;

	public MsPersonasJS() {
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
