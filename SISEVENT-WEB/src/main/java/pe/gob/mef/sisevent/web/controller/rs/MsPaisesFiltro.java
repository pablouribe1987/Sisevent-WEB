package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsPaisesFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9121482181666556547L;
	private String paiPk = null;
	private String paiNombre = null;
	private Integer estado = null;
	
	
	public MsPaisesFiltro(String paiPk,String paiNombre,Integer estado) {
		this.paiPk = paiPk;
		this.paiNombre = paiNombre;
		this.estado = estado;
		
	}

	public String getPaiPk() {
		return this.paiPk;
	}
	public void setPaiPk(String paiPk) {
		this.paiPk = paiPk;
	}
	public String getPaiNombre() {
		return this.paiNombre;
	}
	public void setPaiNombre(String paiNombre) {
		this.paiNombre = paiNombre;
	}
	

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		if(paiPk!=null && paiPk.trim().length()>0) return true;
		if(paiNombre!=null && paiNombre.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;
		return false;
	}
		
}
