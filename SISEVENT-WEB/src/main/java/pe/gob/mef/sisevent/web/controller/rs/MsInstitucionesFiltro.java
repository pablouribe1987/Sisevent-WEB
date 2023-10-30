package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsInstitucionesFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371648742362051441L;
	private String idprovee = null;
	private String razonSocial = null;
	private String siglas = null;
	private String ruc = null;
	private String telefono = null;
	private String direccion = null;
	private Integer estado = null;
	
	
	public MsInstitucionesFiltro(String idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado) {
		this.idprovee = idprovee;
		this.razonSocial = razonSocial;
		this.siglas = siglas;
		this.ruc = ruc;
		this.telefono = telefono;
		this.direccion = direccion;
		this.estado = estado;		
	}

	public String getIdprovee() {
		return this.idprovee;
	}
	public void setIdprovee(String idprovee) {
		this.idprovee = idprovee;
	}
	public String getRazonSocial() {
		return this.razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getSiglas() {
		return this.siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getRuc() {
		return this.ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return this.direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		if(idprovee!=null && idprovee.trim().length()>0) return true;
		if(razonSocial!=null && razonSocial.trim().length()>0) return true;
		if(siglas!=null && siglas.trim().length()>0) return true;
		if(ruc!=null && ruc.trim().length()>0) return true;
		if(telefono!=null && telefono.trim().length()>0) return true;
		if(direccion!=null && direccion.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;		
		return false;
	}
		
}
