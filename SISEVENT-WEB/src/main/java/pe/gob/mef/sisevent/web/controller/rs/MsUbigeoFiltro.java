package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsUbigeoFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8750756927284349126L;
	private String ids = null;
	private String coddistTxt=null;
	private String coddptoTxt=null;
	private String codprovTxt=null;
	private Integer estado = null;
	
	
	public MsUbigeoFiltro(String ids, String coddptoTxt,String codprovTxt,String coddistTxt,Integer estado) {
		this.coddptoTxt = coddptoTxt;
		this.codprovTxt = codprovTxt;
		this.coddistTxt = coddistTxt;
		this.estado = estado;	
		this.ids=ids;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		if(coddptoTxt!=null && coddptoTxt.trim().length()>0) return true;
		if(codprovTxt!=null && codprovTxt.trim().length()>0) return true;
		if(coddistTxt!=null && coddistTxt.trim().length()>0) return true;
		if(ids!=null && ids.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;
		return false;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCoddistTxt() {
		return coddistTxt;
	}

	public void setCoddistTxt(String coddistTxt) {
		this.coddistTxt = coddistTxt;
	}

	public String getCoddptoTxt() {
		return coddptoTxt;
	}

	public void setCoddptoTxt(String coddptoTxt) {
		this.coddptoTxt = coddptoTxt;
	}

	public String getCodprovTxt() {
		return codprovTxt;
	}

	public void setCodprovTxt(String codprovTxt) {
		this.codprovTxt = codprovTxt;
	}
		
}
