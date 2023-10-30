package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdParticipantesFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8054101727098907049L;
	private String idparticipantes = null;
	private String idusuarioIdproveeIdperson = null;
	private String nombresrazonsocial = null;
	private String tipo = null;
	private String sestado = null;
	
	private Integer estado = null;		
	
	public TdParticipantesFiltro(String idparticipantes,String idusuarioIdproveeIdperson,String nombresrazonsocial,String tipo,String sestado ,Integer estado) {
                this.idparticipantes = idparticipantes;
		this.idusuarioIdproveeIdperson = idusuarioIdproveeIdperson;
		this.nombresrazonsocial = nombresrazonsocial;
		this.tipo = tipo;
		this.sestado = sestado;
		
		this.estado = estado;		
	}

	public String getIdparticipantes() {
		return this.idparticipantes;
	}
	public void setIdparticipantes(String idparticipantes) {
		this.idparticipantes = idparticipantes;
	}
	public String getIdusuarioIdproveeIdperson() {
		return this.idusuarioIdproveeIdperson;
	}
	public void setIdusuarioIdproveeIdperson(String idusuarioIdproveeIdperson) {
		this.idusuarioIdproveeIdperson = idusuarioIdproveeIdperson;
	}
	public String getNombresrazonsocial() {
		return this.nombresrazonsocial;
	}
	public void setNombresrazonsocial(String nombresrazonsocial) {
		this.nombresrazonsocial = nombresrazonsocial;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSestado() {
		return this.sestado;
	}
	public void setSestado(String sestado) {
		this.sestado = sestado;
	}
	

	public boolean isActivo() {
                if(idparticipantes!=null && idparticipantes.trim().length()>0) return true;
		if(idparticipantes!=null && idparticipantes.trim().length()>0) return true;
		if(idusuarioIdproveeIdperson!=null && idusuarioIdproveeIdperson.trim().length()>0) return true;
		if(nombresrazonsocial!=null && nombresrazonsocial.trim().length()>0) return true;
		if(tipo!=null && tipo.trim().length()>0) return true;
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
