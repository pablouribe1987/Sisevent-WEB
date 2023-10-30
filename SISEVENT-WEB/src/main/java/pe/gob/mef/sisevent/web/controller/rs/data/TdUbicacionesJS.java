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
public class TdUbicacionesJS implements java.io.Serializable {

	// ID
	private Long idubicaciones;

	// PROPIEDADES
	private Long idevent = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private Integer coddist = null;
	private String ubicacion = null;
	private Timestamp fechaActivIni = null;
	private Timestamp fechaActivFin = null;
	private Long iduserModif = null;
	
	
	// ADICIONALES
	private String ideventTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public TdUbicacionesJS() {
	}

	public Long getIdubicaciones() {
		return this.idubicaciones;
	}

	public void setIdubicaciones(Long idubicaciones) {
		this.idubicaciones = idubicaciones;
	}
	
	public Long getIdevent() {
						return this.idevent;
					}

	public void setIdevent(Long idevent) {
						this.idevent = idevent;
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
	public String getUbicacion() {
						return this.ubicacion;
					}

	public void setUbicacion(String ubicacion) {
						this.ubicacion = ubicacion;
					}
	public Timestamp getFechaActivIni() {
						return this.fechaActivIni;
					}

	public void setFechaActivIni(Timestamp fechaActivIni) {
						this.fechaActivIni = fechaActivIni;
					}
	public Timestamp getFechaActivFin() {
						return this.fechaActivFin;
					}

	public void setFechaActivFin(Timestamp fechaActivFin) {
						this.fechaActivFin = fechaActivFin;
					}
	public Long getIduserModif() {
						return this.iduserModif;
					}

	public void setIduserModif(Long iduserModif) {
						this.iduserModif = iduserModif;
					}
	
	
	public String getIdeventTxt() {
		return this.ideventTxt;
	}
	public void setIdeventTxt(String ideventTxt) {
		this.ideventTxt = ideventTxt;
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
