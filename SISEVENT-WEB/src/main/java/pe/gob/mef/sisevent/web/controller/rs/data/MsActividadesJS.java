package pe.gob.mef.sisevent.web.controller.rs.data;

import java.sql.Timestamp;
import java.util.List;

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
public class MsActividadesJS implements java.io.Serializable {

	// ID
	private Long idactividades;

	// PROPIEDADES
	private Long idtareas = null;
	private String actividad = null;
	private String camposallenar = null;
	private Integer numerodigitales = null;
	
	
	// ADICIONALES
	private String idtareasTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public MsActividadesJS() {
	}

	public Long getIdactividades() {
		return this.idactividades;
	}

	public void setIdactividades(Long idactividades) {
		this.idactividades = idactividades;
	}
	
	public Long getIdtareas() {
						return this.idtareas;
					}

	public void setIdtareas(Long idtareas) {
						this.idtareas = idtareas;
					}
	public String getActividad() {
						return this.actividad;
					}

	public void setActividad(String actividad) {
						this.actividad = actividad;
					}
	public String getCamposallenar() {
						return this.camposallenar;
					}

	public void setCamposallenar(String camposallenar) {
						this.camposallenar = camposallenar;
					}
	public Integer getNumerodigitales() {
						return this.numerodigitales;
					}

	public void setNumerodigitales(Integer numerodigitales) {
						this.numerodigitales = numerodigitales;
					}
	
	
	public String getIdtareasTxt() {
		return this.idtareasTxt;
	}
	public void setIdtareasTxt(String idtareasTxt) {
		this.idtareasTxt = idtareasTxt;
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
