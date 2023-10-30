package pe.gob.mef.sisevent.web.controller.rs.data;

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
public class PrtParametrosJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7786760927334226460L;

	// ID
	private Long idparametro;

	// PROPIEDADES
	private Long idpadre = null;
	private String descripcion = null;
	private String descripcioncorta = null;
	private Long orden = null;
	
	// ADICIONALES
	private Integer editopcion = 1;
	private String idpadreTxt = null;

	public PrtParametrosJS() {
	}

	public Long getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(Long idparametro) {
		this.idparametro = idparametro;
	}
	
	public Long getIdpadre() {
						return this.idpadre;
					}

	public void setIdpadre(Long idpadre) {
						this.idpadre = idpadre;
					}
	
	public String getDescripcion() {
						return this.descripcion;
					}

	public void setDescripcion(String descripcion) {
						this.descripcion = descripcion;
					}
	
	public String getDescripcioncorta() {
						return this.descripcioncorta;
					}

	public void setDescripcioncorta(String descripcioncorta) {
						this.descripcioncorta = descripcioncorta;
					}
	
	public Integer getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(Integer editopcion) {
		this.editopcion = editopcion;
	}

	public String getIdpadreTxt() {
		return idpadreTxt;
	}

	public void setIdpadreTxt(String idpadreTxt) {
		this.idpadreTxt = idpadreTxt;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

}
