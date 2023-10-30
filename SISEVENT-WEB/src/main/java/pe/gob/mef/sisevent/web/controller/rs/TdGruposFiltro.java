package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdGruposFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8049414431904260957L;
	private String idgrupo = null;
	private String grupo = null;
	private String sestado = null;
	
	private Integer estado = null;		
	
	public TdGruposFiltro(String idgrupo,String grupo,String sestado,Integer estado) {
                this.idgrupo = idgrupo;
		this.grupo = grupo;
		this.sestado = sestado;
		
		this.estado = estado;		
	}

	public String getIdgrupo() {
		return this.idgrupo;
	}
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}
	public String getGrupo() {
		return this.grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getSestado() {
		return this.sestado;
	}
	public void setSestado(String sestado) {
		this.sestado = sestado;
	}
	

	public boolean isActivo() {
                if(idgrupo!=null && idgrupo.trim().length()>0) return true;
		if(idgrupo!=null && idgrupo.trim().length()>0) return true;
		if(grupo!=null && grupo.trim().length()>0) return true;
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
