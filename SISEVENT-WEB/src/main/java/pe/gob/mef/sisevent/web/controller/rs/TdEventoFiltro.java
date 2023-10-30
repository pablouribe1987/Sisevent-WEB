package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdEventoFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4358872734181888323L;
	private String idevent = null;
	private String titulo = null;
	private String fechaSoliIni = null;
	private String idcategorias = null;
	private String modalidad = null;
	private String sestado = null;
	
	private Integer estado = null;		
	
	public TdEventoFiltro(String idevent,String titulo,String fechaSoliIni,String idcategorias,String modalidad,String sestado,Integer estado) {
                this.idevent = idevent;
		this.titulo = titulo;
		this.fechaSoliIni = fechaSoliIni;
		this.idcategorias = idcategorias;
		this.modalidad = modalidad;
		this.sestado = sestado;
		
		this.estado = estado;		
	}

	public String getIdevent() {
		return this.idevent;
	}
	public void setIdevent(String idevent) {
		this.idevent = idevent;
	}
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFechaSoliIni() {
		return this.fechaSoliIni;
	}
	public void setFechaSoliIni(String fechaSoliIni) {
		this.fechaSoliIni = fechaSoliIni;
	}
	public String getIdcategorias() {
		return this.idcategorias;
	}
	public void setIdcategorias(String idcategorias) {
		this.idcategorias = idcategorias;
	}
	public String getModalidad() {
		return this.modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getSestado() {
		return this.sestado;
	}
	public void setSestado(String sestado) {
		this.sestado = sestado;
	}
	

	public boolean isActivo() {
                if(idevent!=null && idevent.trim().length()>0) return true;
		if(idevent!=null && idevent.trim().length()>0) return true;
		if(titulo!=null && titulo.trim().length()>0) return true;
		if(fechaSoliIni!=null && fechaSoliIni.trim().length()>0) return true;
		if(idcategorias!=null && idcategorias.trim().length()>0) return true;
		if(modalidad!=null && modalidad.trim().length()>0) return true;
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
