package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

/**
 * MS_ROLES BAKING: ROLES DEL SISTEMA, ROLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37   / Creación de la clase    /
 * 
 */
public class MsRolesBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4372992312714931830L;

	//ID
	private Long idrol;
		
	//PROPIEDADES
	private String username = null;
	private String rol = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;
	
	
	//ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
		
	public MsRolesBk() {
	}
	
	public Long getIdrol() {
		return this.idrol;
	}

	public void setIdrol(Long idrol) {
		this.idrol = idrol;
	}
	
	public String getUsername() {
						return this.username;
					}

	public void setUsername(String username) {
						this.username = username;
					}
	
	public String getRol() {
						return this.rol;
					}

	public void setRol(String rol) {
						this.rol = rol;
					}
	
	public Long getIduserCrea() {
						return this.iduserCrea;
					}

	public void setIduserCrea(Long iduserCrea) {
						this.iduserCrea = iduserCrea;
					}
	
	public Long getIduserModif() {
						return this.iduserModif;
					}

	public void setIduserModif(Long iduserModif) {
						this.iduserModif = iduserModif;
					}
	
	public Timestamp getFechaCrea() {
						return this.fechaCrea;
					}

	public void setFechaCrea(Timestamp fechaCrea) {
						this.fechaCrea = fechaCrea;
					}
	
	public Timestamp getFechaModif() {
						return this.fechaModif;
					}

	public void setFechaModif(Timestamp fechaModif) {
						this.fechaModif = fechaModif;
					}
	
	public String getRtmaddress() {
						return this.rtmaddress;
					}

	public void setRtmaddress(String rtmaddress) {
						this.rtmaddress = rtmaddress;
					}
	
	public String getRtmaddressmodif() {
						return this.rtmaddressmodif;
					}

	public void setRtmaddressmodif(String rtmaddressmodif) {
						this.rtmaddressmodif = rtmaddressmodif;
					}
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	
	
	public String getIduserCreaTxt() {
		return this.iduserCreaTxt;
	}
	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}
	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}
	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}
		
}