package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.MsActividadesACL;

/**
 * MS_ACTIVIDADES BAKING: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class MsActividadesBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953432384206476756L;

	//ID
	private Long idactividades;
		
	//PROPIEDADES
	private Long idtareas = null;
	private String actividad = null;
	private String camposallenar = null;
	private Integer numerodigitales = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;
	
	
	//ADICIONALES
	private String idtareasTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private MsActividadesACL msActividadesACL = null;		
		
	public MsActividadesBk() {
	}
	
	public Long getIdactividades() {
		return this.idactividades;
	}

	public void setIdactividades(Long idactividades) {
		this.idactividades = idactividades;
	}
	
	public Long getIdtareas() {
						return this.idtareas;
					}

	public void setIdtareas(Long idtareas) {
						this.idtareas = idtareas;
					}
	
	public String getActividad() {
						return this.actividad;
					}

	public void setActividad(String actividad) {
						this.actividad = actividad;
					}
	
	public String getCamposallenar() {
						return this.camposallenar;
					}

	public void setCamposallenar(String camposallenar) {
						this.camposallenar = camposallenar;
					}
	
	public Integer getNumerodigitales() {
						return this.numerodigitales;
					}

	public void setNumerodigitales(Integer numerodigitales) {
						this.numerodigitales = numerodigitales;
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
	
	
	
	public String getIdtareasTxt() {
		return this.idtareasTxt;
	}
	public void setIdtareasTxt(String idtareasTxt) {
		this.idtareasTxt = idtareasTxt;
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
		
	
	public MsActividadesACL getMsActividadesACL() {
		return msActividadesACL;
	}

	public void setMsActividadesACL(MsActividadesACL msActividadesACL) {
		this.msActividadesACL = msActividadesACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
}
