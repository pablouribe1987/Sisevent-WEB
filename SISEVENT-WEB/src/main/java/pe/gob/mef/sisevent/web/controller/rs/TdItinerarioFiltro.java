package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdItinerarioFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4169639842700228069L;
	private String iditinerario = null;
	private String idevent = null;
	private String codpais = null;
	private String coddpto = null;
	private String codprov = null;
	
	private Integer estado = null;		
	
	public TdItinerarioFiltro(String iditinerario,String idevent,String codpais,String coddpto,String codprov,Integer estado) {
                this.iditinerario = iditinerario;
		this.idevent = idevent;
		this.codpais = codpais;
		this.coddpto = coddpto;
		this.codprov = codprov;
		
		this.estado = estado;		
	}

	public String getIditinerario() {
		return this.iditinerario;
	}
	public void setIditinerario(String iditinerario) {
		this.iditinerario = iditinerario;
	}
	public String getIdevent() {
		return this.idevent;
	}
	public void setIdevent(String idevent) {
		this.idevent = idevent;
	}
	public String getCodpais() {
		return this.codpais;
	}
	public void setCodpais(String codpais) {
		this.codpais = codpais;
	}
	public String getCoddpto() {
		return this.coddpto;
	}
	public void setCoddpto(String coddpto) {
		this.coddpto = coddpto;
	}
	public String getCodprov() {
		return this.codprov;
	}
	public void setCodprov(String codprov) {
		this.codprov = codprov;
	}
	

	public boolean isActivo() {
                if(iditinerario!=null && iditinerario.trim().length()>0) return true;
		if(iditinerario!=null && iditinerario.trim().length()>0) return true;
		if(idevent!=null && idevent.trim().length()>0) return true;
		if(codpais!=null && codpais.trim().length()>0) return true;
		if(coddpto!=null && coddpto.trim().length()>0) return true;
		if(codprov!=null && codprov.trim().length()>0) return true;
		
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
