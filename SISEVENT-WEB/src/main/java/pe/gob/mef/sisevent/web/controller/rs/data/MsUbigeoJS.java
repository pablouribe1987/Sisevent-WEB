package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;

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
public class MsUbigeoJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948859215358189165L;

	// ID
	private MsUbigeoId id;

	// PROPIEDADES
	private String nombre = null;
//	private Timestamp fechaCrea = null;
//	private Timestamp fechaModif = null;
//	private Long iduserCrea = null;
//	private Long iduserModif = null;
	private Integer estado = null;
//	private String rmtaddress = null;
//	private String rmtaddressrst = null;
	
	
	// ADICIONALES
//	private String iduserCreaTxt = null;
//	private String iduserModifTxt = null;
	private Integer editopcion = 1;
	
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;
	
	public MsUbigeoJS() {
	}

	public MsUbigeoId getId() {
		return this.id;
	}

	public void setId(MsUbigeoId id) {
		this.id = id;
	}
	
	public String getNombre() {
						return this.nombre;
					}

	public void setNombre(String nombre) {
						this.nombre = nombre;
					}
	

	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	public Integer getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(Integer editopcion) {
		this.editopcion = editopcion;
	}

	public String getCoddptoTxt() {
		return coddptoTxt;
	}

	public void setCoddptoTxt(String coddptoTxt) {
		this.coddptoTxt = coddptoTxt;
	}

	public String getCodprovTxt() {
		return codprovTxt;
	}

	public void setCodprovTxt(String codprovTxt) {
		this.codprovTxt = codprovTxt;
	}

	public String getCoddistTxt() {
		return coddistTxt;
	}

	public void setCoddistTxt(String coddistTxt) {
		this.coddistTxt = coddistTxt;
	}
	
	
}
