package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsCategoriasFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6664877660924882741L;
	private String idcategorias = null;
	private String categoria = null;
	private String arraycamposocultos = null;
	
	private Integer estado = null;		
	
	public MsCategoriasFiltro(String idcategorias,String categoria,String arraycamposocultos,Integer estado) {
                this.idcategorias = idcategorias;
		this.categoria = categoria;
		this.arraycamposocultos = arraycamposocultos;
		
		this.estado = estado;		
	}

	public String getIdcategorias() {
		return this.idcategorias;
	}
	public void setIdcategorias(String idcategorias) {
		this.idcategorias = idcategorias;
	}
	public String getCategoria() {
		return this.categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getArraycamposocultos() {
		return this.arraycamposocultos;
	}
	public void setArraycamposocultos(String arraycamposocultos) {
		this.arraycamposocultos = arraycamposocultos;
	}
	

	public boolean isActivo() {
                if(idcategorias!=null && idcategorias.trim().length()>0) return true;
		if(idcategorias!=null && idcategorias.trim().length()>0) return true;
		if(categoria!=null && categoria.trim().length()>0) return true;
		if(arraycamposocultos!=null && arraycamposocultos.trim().length()>0) return true;
		
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
