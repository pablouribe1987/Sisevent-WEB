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
public class TdFeriadosJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4286155896214117434L;

	// ID
	private Long idferiado;

	// PROPIEDADES
	private Timestamp fechaFeriado = null;	
	private String descricion = null;
		
	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private Integer editopcion = 1;
	

	public TdFeriadosJS() {
	}

	public Long getIdferiado() {
		return this.idferiado;
	}

	public void setIdferiado(Long idferiado) {
		this.idferiado = idferiado;
	}
	
	public Timestamp getFechaFeriado() {
		return this.fechaFeriado;
	}
	public void setFechaFeriado(Timestamp fechaFeriado) {
		this.fechaFeriado = fechaFeriado;
	}
	public java.util.Date getFechaFeriadoJUD() {
		java.util.Date fechaFeriadoJUD = null;
		if (fechaFeriado != null)
			fechaFeriadoJUD = new java.util.Date(fechaFeriado.getTime());
		return fechaFeriadoJUD;
	}
	public void setFechaFeriadoJUD(java.util.Date fechaFeriadoJUD) {
		if (fechaFeriadoJUD != null)
			this.fechaFeriado = new Timestamp(fechaFeriadoJUD.getTime());
		else
			this.fechaFeriado = null;
	}	
	
	public String getDescricion() {
						return this.descricion;
					}

	public void setDescricion(String descricion) {
						this.descricion = descricion;
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
