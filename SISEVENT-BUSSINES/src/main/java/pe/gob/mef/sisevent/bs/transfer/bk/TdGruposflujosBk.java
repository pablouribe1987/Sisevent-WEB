package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdGruposflujosACL;

/**
 * TD_GRUPOSFLUJOS BAKING: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdGruposflujosBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6406841069915204396L;

	//ID
	private Long idgruposflujos;
		
	//PROPIEDADES
	private Long idtareas = null;
	private Long idunidadDestino = null;
	private Long iduserDestino = null;
	private String observacion = null;
	private String observacionHtml = null;
	private Integer tiempoestadia = null;
	private String correosnotif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	private Long idgrupo = null;
	
	
	//ADICIONALES
	private String idtareasTxt = null;
	private String idunidadDestinoTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private String idgrupoTxt = null;
	private String iduserDestinoTxt = null; 
	
	private TdGruposflujosACL tdGruposflujosACL = null;		
		
	public TdGruposflujosBk() {
	}
	
	public Long getIdgruposflujos() {
		return this.idgruposflujos;
	}

	public void setIdgruposflujos(Long idgruposflujos) {
		this.idgruposflujos = idgruposflujos;
	}
	
	public Long getIdtareas() {
						return this.idtareas;
					}

	public void setIdtareas(Long idtareas) {
						this.idtareas = idtareas;
					}
	
	public Long getIdunidadDestino() {
						return this.idunidadDestino;
					}

	public void setIdunidadDestino(Long idunidadDestino) {
						this.idunidadDestino = idunidadDestino;
					}
	
	public Long getIduserDestino() {
						return this.iduserDestino;
					}

	public void setIduserDestino(Long iduserDestino) {
						this.iduserDestino = iduserDestino;
					}
	
	public String getObservacion() {
						return this.observacion;
					}

	public void setObservacion(String observacion) {
						this.observacion = observacion;
					}
	
	public String getObservacionHtml() {
						return this.observacionHtml;
					}

	public void setObservacionHtml(String observacionHtml) {
						this.observacionHtml = observacionHtml;
					}
	
	public Integer getTiempoestadia() {
						return this.tiempoestadia;
					}

	public void setTiempoestadia(Integer tiempoestadia) {
						this.tiempoestadia = tiempoestadia;
					}
	
	public String getCorreosnotif() {
						return this.correosnotif;
					}

	public void setCorreosnotif(String correosnotif) {
						this.correosnotif = correosnotif;
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
	
	public String getRmtaddress() {
						return this.rmtaddress;
					}

	public void setRmtaddress(String rmtaddress) {
						this.rmtaddress = rmtaddress;
					}
	
	public String getRmtaddressrst() {
						return this.rmtaddressrst;
					}

	public void setRmtaddressrst(String rmtaddressrst) {
						this.rmtaddressrst = rmtaddressrst;
					}
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	public Long getIdgrupo() {
						return this.idgrupo;
					}

	public void setIdgrupo(Long idgrupo) {
						this.idgrupo = idgrupo;
					}
	
	
	
	public String getIdtareasTxt() {
		return this.idtareasTxt;
	}
	public void setIdtareasTxt(String idtareasTxt) {
		this.idtareasTxt = idtareasTxt;
	}
	public String getIdunidadDestinoTxt() {
		return this.idunidadDestinoTxt;
	}
	public void setIdunidadDestinoTxt(String idunidadDestinoTxt) {
		this.idunidadDestinoTxt = idunidadDestinoTxt;
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
	public String getIdgrupoTxt() {
		return this.idgrupoTxt;
	}
	public void setIdgrupoTxt(String idgrupoTxt) {
		this.idgrupoTxt = idgrupoTxt;
	}
		
	
	public TdGruposflujosACL getTdGruposflujosACL() {
		return tdGruposflujosACL;
	}

	public void setTdGruposflujosACL(TdGruposflujosACL tdGruposflujosACL) {
		this.tdGruposflujosACL = tdGruposflujosACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}

	public String getIduserDestinoTxt() {
		return iduserDestinoTxt;
	}

	public void setIduserDestinoTxt(String iduserDestinoTxt) {
		this.iduserDestinoTxt = iduserDestinoTxt;
	}
}
