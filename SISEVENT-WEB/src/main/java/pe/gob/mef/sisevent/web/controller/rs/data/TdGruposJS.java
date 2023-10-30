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
public class TdGruposJS implements java.io.Serializable {

	// ID
	private Long idgrupo;

	// PROPIEDADES
	private String grupo = null;
	
	
	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public TdGruposJS() {
	}

	public Long getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(Long idgrupo) {
		this.idgrupo = idgrupo;
	}
	
	public String getGrupo() {
						return this.grupo;
					}

	public void setGrupo(String grupo) {
						this.grupo = grupo;
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
