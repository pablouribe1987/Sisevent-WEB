package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.PrtParametrosACL;

/**
 * PRT_PARAMETROS BAKING: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL SISTEMA PARÁMETROS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class PrtParametrosBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409547106022923588L;

	//ID
	private Long idparametro;
		
	//PROPIEDADES
	private Long idpadre = null;
	private String descripcion = null;
	private String descripcioncorta = null;
	private Long orden = null;
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
	private String idpadreTxt = null;
	
	private PrtParametrosACL prtParametrosACL = null;		
		
	public PrtParametrosBk() {
	}
	
	public Long getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(Long idparametro) {
		this.idparametro = idparametro;
	}
	
	public Long getIdpadre() {
						return this.idpadre;
					}

	public void setIdpadre(Long idpadre) {
						this.idpadre = idpadre;
					}
	
	public String getDescripcion() {
						return this.descripcion;
					}

	public void setDescripcion(String descripcion) {
						this.descripcion = descripcion;
					}
	
	public String getDescripcioncorta() {
						return this.descripcioncorta;
					}

	public void setDescripcioncorta(String descripcioncorta) {
						this.descripcioncorta = descripcioncorta;
					}
	
	public Long getOrden() {
						return this.orden;
					}

	public void setOrden(Long orden) {
						this.orden = orden;
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
		
	
	public PrtParametrosACL getPrtParametrosACL() {
		return prtParametrosACL;
	}

	public void setPrtParametrosACL(PrtParametrosACL prtParametrosACL) {
		this.prtParametrosACL = prtParametrosACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}

	public String getIdpadreTxt() {
		return idpadreTxt;
	}

	public void setIdpadreTxt(String idpadreTxt) {
		this.idpadreTxt = idpadreTxt;
	}
}
