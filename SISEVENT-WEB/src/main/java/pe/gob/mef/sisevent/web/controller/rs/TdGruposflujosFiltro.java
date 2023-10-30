package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdGruposflujosFiltro implements Serializable{


        /**
	 * 
	 */
	private static final long serialVersionUID = 5806881877018903301L;
	private String idtareas = null;
	private String idunidadDestino = null;
	private String iduserDestino = null;
	
	private Integer estado = null;		
	
	public TdGruposflujosFiltro(String idtareas,String idunidadDestino,String iduserDestino,Integer estado) {

                this.idtareas = idtareas;
		this.idunidadDestino = idunidadDestino;
		this.iduserDestino = iduserDestino;
		
		this.estado = estado;		
	}
	
	public String getIdtareas() {
		return this.idtareas;
	}
	public void setIdtareas(String idtareas) {
		this.idtareas = idtareas;
	}
	public String getIdunidadDestino() {
		return this.idunidadDestino;
	}
	public void setIdunidadDestino(String idunidadDestino) {
		this.idunidadDestino = idunidadDestino;
	}
	public String getIduserDestino() {
		return this.iduserDestino;
	}
	public void setIduserDestino(String iduserDestino) {
		this.iduserDestino = iduserDestino;
	}
	

	public boolean isActivo() {
		if(idtareas!=null && idtareas.trim().length()>0) return true;
		if(idunidadDestino!=null && idunidadDestino.trim().length()>0) return true;
		if(iduserDestino!=null && iduserDestino.trim().length()>0) return true;
		
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
