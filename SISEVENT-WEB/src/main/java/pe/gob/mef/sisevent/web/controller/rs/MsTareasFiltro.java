package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsTareasFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3341900189180368369L;
	private String idtareas = null;
	private String tarea = null;
	private String tiempo = null;
	private String sestado = null;
	
	private Integer estado = null;		
	
	public MsTareasFiltro(String idtareas,String tarea,String tiempo,String sestado,Integer estado) {
                this.idtareas = idtareas;
		this.tarea = tarea;
		this.tiempo = tiempo;
		this.sestado = sestado;
		
		this.estado = estado;		
	}

	public String getIdtareas() {
		return this.idtareas;
	}
	public void setIdtareas(String idtareas) {
		this.idtareas = idtareas;
	}
	public String getTarea() {
		return this.tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getTiempo() {
		return this.tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getSestado() {
		return this.sestado;
	}
	public void setEstado(String sestado) {
		this.sestado = sestado;
	}
	

	public boolean isActivo() {
                if(idtareas!=null && idtareas.trim().length()>0) return true;
		if(idtareas!=null && idtareas.trim().length()>0) return true;
		if(tarea!=null && tarea.trim().length()>0) return true;
		if(tiempo!=null && tiempo.trim().length()>0) return true;
		if(sestado!=null && sestado.trim().length()>0) return true;
		
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
