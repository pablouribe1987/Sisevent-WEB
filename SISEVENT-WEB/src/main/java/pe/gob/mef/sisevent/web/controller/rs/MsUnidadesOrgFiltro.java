package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsUnidadesOrgFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4174985418560459856L;
	private String idunidad = null;
	private String idpadreTxt = null;
	private String codigo = null;
	private String descripcion = null;
	private String acronimo = null;
	private Integer estado = null;
	
	public MsUnidadesOrgFiltro(String idunidad,String idpadreTxt,String codigo,String descripcion,String acronimo, Integer estado) {
		this.idunidad = idunidad;
		this.idpadreTxt = idpadreTxt;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.acronimo = acronimo;		
		this.estado = estado;
	}

	public String getIdunidad() {
		return this.idunidad;
	}
	public void setIdunidad(String idunidad) {
		this.idunidad = idunidad;
	}
	public String getIdpadreTxt() {
		return this.idpadreTxt;
	}
	public void setIdpadreTxt(String idpadreTxt) {
		this.idpadreTxt = idpadreTxt;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAcronimo() {
		return this.acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		if(idunidad!=null && idunidad.trim().length()>0) return true;
		if(idpadreTxt!=null && idpadreTxt.trim().length()>0) return true;
		if(codigo!=null && codigo.trim().length()>0) return true;
		if(descripcion!=null && descripcion.trim().length()>0) return true;
		if(acronimo!=null && acronimo.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;
		return false;
	}
		
}
