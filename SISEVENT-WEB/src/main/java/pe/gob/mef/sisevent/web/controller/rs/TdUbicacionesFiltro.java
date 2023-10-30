package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdUbicacionesFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7129251600530673713L;
	private String idubicaciones = null;
	private String idevent = null;
	private String ubicacion = null;
	private String sestado = null;
	
	private Integer estado = null;		
	
	public TdUbicacionesFiltro(String idubicaciones,String idevent,String ubicacion,String sestado,Integer estado) {
                this.idubicaciones = idubicaciones;
		this.idevent = idevent;
		this.ubicacion = ubicacion;
		this.sestado = sestado;
		
		this.estado = estado;		
	}

	public String getIdubicaciones() {
		return this.idubicaciones;
	}
	public void setIdubicaciones(String idubicaciones) {
		this.idubicaciones = idubicaciones;
	}
	public String getIdevent() {
		return this.idevent;
	}
	public void setIdevent(String idevent) {
		this.idevent = idevent;
	}
	public String getUbicacion() {
		return this.ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getSestado() {
		return this.sestado;
	}
	public void setSestado(String sestado) {
		this.sestado = sestado;
	}
	

	public boolean isActivo() {
                if(idubicaciones!=null && idubicaciones.trim().length()>0) return true;
		if(idubicaciones!=null && idubicaciones.trim().length()>0) return true;
		if(idevent!=null && idevent.trim().length()>0) return true;
		if(ubicacion!=null && ubicacion.trim().length()>0) return true;
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
