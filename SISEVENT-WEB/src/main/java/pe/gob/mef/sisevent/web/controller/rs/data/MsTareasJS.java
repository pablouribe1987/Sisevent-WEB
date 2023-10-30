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
public class MsTareasJS implements java.io.Serializable {

	// ID
	private Long idtareas;

	// PROPIEDADES
	private String tarea = null;
	private String descripcion = null;
	private Long tiempo = null;
	
	
	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public MsTareasJS() {
	}

	public Long getIdtareas() {
		return this.idtareas;
	}

	public void setIdtareas(Long idtareas) {
		this.idtareas = idtareas;
	}
	
	public String getTarea() {
						return this.tarea;
					}

	public void setTarea(String tarea) {
						this.tarea = tarea;
					}
	public String getDescripcion() {
						return this.descripcion;
					}

	public void setDescripcion(String descripcion) {
						this.descripcion = descripcion;
					}
	public Long getTiempo() {
						return this.tiempo;
					}

	public void setTiempo(Long tiempo) {
						this.tiempo = tiempo;
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
