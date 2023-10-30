package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdFlujoFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676331144350319039L;
	private String idflujo = null;
	private String idflujopadre = null;
	private String idsacc = null;
	private String estado = null;
	
	
	public TdFlujoFiltro(String idflujo,String idflujopadre,String idsacc,String estado) {
		this.idflujo = idflujo;
		this.idflujopadre = idflujopadre;
		this.idsacc = idsacc;
		this.estado = estado;
		
	}

	public String getIdflujo() {
		return this.idflujo;
	}
	public void setIdflujo(String idflujo) {
		this.idflujo = idflujo;
	}
	public String getIdflujopadre() {
		return this.idflujopadre;
	}
	public void setIdflujopadre(String idflujopadre) {
		this.idflujopadre = idflujopadre;
	}
	public String getIdsacc() {
		return this.idsacc;
	}
	public void setIdsacc(String idsacc) {
		this.idsacc = idsacc;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public boolean isActivo() {
		if(idflujo!=null && idflujo.trim().length()>0) return true;
		if(idflujopadre!=null && idflujopadre.trim().length()>0) return true;
		if(idsacc!=null && idsacc.trim().length()>0) return true;
		if(estado!=null && estado.trim().length()>0) return true;
		
		return false;
	}
		
}
