package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsActividadesFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7452276101880671824L;
	private String idactividades = null;
	private String idtareas = null;
	private String actividad = null;
	
	private Integer estado = null;		
	
	public MsActividadesFiltro(String idactividades,String idtareas,String actividad,Integer estado) {
                this.idactividades = idactividades;
		this.idtareas = idtareas;
		this.actividad = actividad;		
		this.estado = estado;		
	}

	public String getIdactividades() {
		return this.idactividades;
	}
	public void setIdactividades(String idactividades) {
		this.idactividades = idactividades;
	}
	public String getIdtareas() {
		return this.idtareas;
	}
	public void setIdtareas(String idtareas) {
		this.idtareas = idtareas;
	}
	public String getActividad() {
		return this.actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	

	public boolean isActivo() {
                if(idactividades!=null && idactividades.trim().length()>0) return true;
		if(idactividades!=null && idactividades.trim().length()>0) return true;
		if(idtareas!=null && idtareas.trim().length()>0) return true;
		if(actividad!=null && actividad.trim().length()>0) return true;
		
		if(estado!=null && estado.intValue()>-1) return true;
		return false;
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
