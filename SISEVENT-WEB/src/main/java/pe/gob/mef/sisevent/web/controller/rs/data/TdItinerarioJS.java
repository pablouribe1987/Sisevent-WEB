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
public class TdItinerarioJS implements java.io.Serializable {

	// ID
	private Long iditinerario;

	// PROPIEDADES
	private Long idevent = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private String origendestino = null;
	private Timestamp fechaSaliIni = null;
	private Timestamp fechaLlegFin = null;
	
	
	// ADICIONALES
	private String ideventTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public TdItinerarioJS() {
	}

	public Long getIditinerario() {
		return this.iditinerario;
	}

	public void setIditinerario(Long iditinerario) {
		this.iditinerario = iditinerario;
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
	public String getOrigendestino() {
						return this.origendestino;
					}

	public void setOrigendestino(String origendestino) {
						this.origendestino = origendestino;
					}
	public Timestamp getFechaSaliIni() {
						return this.fechaSaliIni;
					}

	public void setFechaSaliIni(Timestamp fechaSaliIni) {
						this.fechaSaliIni = fechaSaliIni;
					}
	public Timestamp getFechaLlegFin() {
						return this.fechaLlegFin;
					}

	public void setFechaLlegFin(Timestamp fechaLlegFin) {
						this.fechaLlegFin = fechaLlegFin;
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
