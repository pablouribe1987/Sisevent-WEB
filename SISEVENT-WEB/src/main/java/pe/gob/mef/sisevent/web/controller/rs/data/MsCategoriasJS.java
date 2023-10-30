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
public class MsCategoriasJS implements java.io.Serializable {

	// ID
	private Long idcategorias;

	// PROPIEDADES
	private String categoria = null;
	private String arraycamposocultos = null;
	
	
	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public MsCategoriasJS() {
	}

	public Long getIdcategorias() {
		return this.idcategorias;
	}

	public void setIdcategorias(Long idcategorias) {
		this.idcategorias = idcategorias;
	}
	
	public String getCategoria() {
						return this.categoria;
					}

	public void setCategoria(String categoria) {
						this.categoria = categoria;
					}
	public String getArraycamposocultos() {
						return this.arraycamposocultos;
					}

	public void setArraycamposocultos(String arraycamposocultos) {
						this.arraycamposocultos = arraycamposocultos;
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
