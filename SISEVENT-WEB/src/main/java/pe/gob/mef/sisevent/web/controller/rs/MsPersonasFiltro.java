package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsPersonasFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7452150999442612914L;
	private String idperson = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String nombres = null;
	private String documentoNumero = null;
	private String direccion = null;
	private Integer estado = null;
	
	
	public MsPersonasFiltro(String idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String documentoNumero,String direccion,Integer estado) {
		this.idperson = idperson;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombres = nombres;
		this.documentoNumero = documentoNumero;
		this.direccion = direccion;
		this.estado = estado;
		
	}

	public String getIdperson() {
		return this.idperson;
	}
	public void setIdperson(String idperson) {
		this.idperson = idperson;
	}
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombres() {
		return this.nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getDocumentoNumero() {
		return this.documentoNumero;
	}
	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
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
		if(idperson!=null && idperson.trim().length()>0) return true;
		if(apellidoPaterno!=null && apellidoPaterno.trim().length()>0) return true;
		if(apellidoMaterno!=null && apellidoMaterno.trim().length()>0) return true;
		if(nombres!=null && nombres.trim().length()>0) return true;
		if(documentoNumero!=null && documentoNumero.trim().length()>0) return true;
		if(direccion!=null && direccion.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;
		
		return false;
	}
		
}
