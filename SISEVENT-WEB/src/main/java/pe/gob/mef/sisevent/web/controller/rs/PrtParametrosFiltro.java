package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class PrtParametrosFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8098133169956253035L;
	private String idparametro = null;
	private String idpadreTxt = null;
	private String descripcion = null;
	private String descripcioncorta = null;
	private String orden = null;
	private Integer estado = null;
	
	
	public PrtParametrosFiltro(String idparametro,String idpadreTxt,String descripcion,String descripcioncorta,String orden,Integer estado) {
		this.idparametro = idparametro;
		this.idpadreTxt = idpadreTxt;
		this.descripcion = descripcion;
		this.descripcioncorta = descripcioncorta;
		this.orden = orden;
		this.estado = estado;
	}

	public String getIdparametro() {
		return this.idparametro;
	}
	public void setIdparametro(String idparametro) {
		this.idparametro = idparametro;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcioncorta() {
		return this.descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getOrden() {
		return this.orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	

	public String getIdpadreTxt() {
		return idpadreTxt;
	}

	public void setIdpadreTxt(String idpadreTxt) {
		this.idpadreTxt = idpadreTxt;
	}

	public boolean isActivo() {
		if(idparametro!=null && idparametro.trim().length()>0) return true;
		if(idpadreTxt!=null && idpadreTxt.trim().length()>0) return true;
		if(descripcion!=null && descripcion.trim().length()>0) return true;
		if(descripcioncorta!=null && descripcioncorta.trim().length()>0) return true;
		if(orden!=null && orden.trim().length()>0) return true;
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
