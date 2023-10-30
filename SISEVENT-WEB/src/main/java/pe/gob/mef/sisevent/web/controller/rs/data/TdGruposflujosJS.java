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
public class TdGruposflujosJS implements java.io.Serializable {

	// ID
	private Long idgruposflujos;

	// PROPIEDADES
	private Long idtareas = null;
	private Long idunidadDestino = null;
	private Long iduserDestino = null;
	private String observacion = null;
	private String observacionHtml = null;
	private Integer tiempoestadia = null;
	private String correosnotif = null;
	private Long idgrupo = null;
	
	
	// ADICIONALES
	private String idtareasTxt = null;
	private String idunidadDestinoTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private String idgrupoTxt = null;
	
	private Integer editopcion = 1;

	public TdGruposflujosJS() {
	}

	public Long getIdgruposflujos() {
		return this.idgruposflujos;
	}

	public void setIdgruposflujos(Long idgruposflujos) {
		this.idgruposflujos = idgruposflujos;
	}
	
	public Long getIdtareas() {
						return this.idtareas;
					}

	public void setIdtareas(Long idtareas) {
						this.idtareas = idtareas;
					}
	public Long getIdunidadDestino() {
						return this.idunidadDestino;
					}

	public void setIdunidadDestino(Long idunidadDestino) {
						this.idunidadDestino = idunidadDestino;
					}
	public Long getIduserDestino() {
						return this.iduserDestino;
					}

	public void setIduserDestino(Long iduserDestino) {
						this.iduserDestino = iduserDestino;
					}
	public String getObservacion() {
						return this.observacion;
					}

	public void setObservacion(String observacion) {
						this.observacion = observacion;
					}
	public String getObservacionHtml() {
						return this.observacionHtml;
					}

	public void setObservacionHtml(String observacionHtml) {
						this.observacionHtml = observacionHtml;
					}
	public Integer getTiempoestadia() {
						return this.tiempoestadia;
					}

	public void setTiempoestadia(Integer tiempoestadia) {
						this.tiempoestadia = tiempoestadia;
					}
	public String getCorreosnotif() {
						return this.correosnotif;
					}

	public void setCorreosnotif(String correosnotif) {
						this.correosnotif = correosnotif;
					}
	public Long getIdgrupo() {
						return this.idgrupo;
					}

	public void setIdgrupo(Long idgrupo) {
						this.idgrupo = idgrupo;
					}
	
	
	public String getIdtareasTxt() {
		return this.idtareasTxt;
	}
	public void setIdtareasTxt(String idtareasTxt) {
		this.idtareasTxt = idtareasTxt;
	}
	public String getIdunidadDestinoTxt() {
		return this.idunidadDestinoTxt;
	}
	public void setIdunidadDestinoTxt(String idunidadDestinoTxt) {
		this.idunidadDestinoTxt = idunidadDestinoTxt;
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
	public String getIdgrupoTxt() {
		return this.idgrupoTxt;
	}
	public void setIdgrupoTxt(String idgrupoTxt) {
		this.idgrupoTxt = idgrupoTxt;
	}
		

	public Integer getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(Integer editopcion) {
		this.editopcion = editopcion;
	}			
}
