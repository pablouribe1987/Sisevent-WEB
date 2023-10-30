package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class MsUsuariosFiltro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8222519511967901485L;
	private String idusuario = null;
	private String username = null;
	private String nombres = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String roles = null;
	private String sede  = null;
	private String idunidadTxt = null;
	private Integer estado = null;
	
	private boolean activo = false;
	
	public MsUsuariosFiltro(String idusuario, String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, String sede, String roles, Integer estado) {
		super();
		this.idusuario = idusuario;
		this.username = username;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.sede = sede;
		this.roles = roles;
		this.estado = estado;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public boolean isActivo() {
		if(idusuario!=null && idusuario.trim().length()>0) return true;
		if(username!=null && username.trim().length()>0) return true; 
		if(nombres != null && nombres.trim().length()>0) return true; 
		if(apellidoPaterno != null && apellidoPaterno.trim().length()>0) return true; 
		if(apellidoMaterno != null && apellidoMaterno.trim().length()>0) return true;
		if(roles != null && roles.trim().length()>0) return true;
		if(sede  != null && sede.trim().length()>0) return true;
		if(idunidadTxt != null && idunidadTxt.trim().length()>0) return true;
		if(estado!=null && estado.intValue()>-1) return true;
		return activo;
	}

	public String getIdunidadTxt() {
		return idunidadTxt;
	}
	public void setIdunidadTxt(String idunidadTxt) {
		this.idunidadTxt = idunidadTxt;
	}

	public String getRoles() {
		if(roles!=null && !roles.startsWith("*")){
			roles = "*"+roles;
		}
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}	
}
