package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdUbicacionesACL;

/**
 * TD_UBICACIONES BAKING: UBICACIONES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdUbicacionesBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137009303006232743L;

	//ID
	private Long idubicaciones;
		
	//PROPIEDADES
	private Long idevent = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private Integer coddist = null;
	private String ubicacion = null;
	private Timestamp fechaActivIni = null;
	private Timestamp fechaActivFin = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	
	
	//ADICIONALES
	private String ideventTxt = null;
	private String codpaisTxt = null;
	private String coddptoTxt = null;
	private String codprovTxt = null;
	private String coddistTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private TdUbicacionesACL tdUbicacionesACL = null;		
		
	public TdUbicacionesBk() {
	}
	
	public Long getIdubicaciones() {
		return this.idubicaciones;
	}

	public void setIdubicaciones(Long idubicaciones) {
		this.idubicaciones = idubicaciones;
	}
	
	public Long getIdevent() {
						return this.idevent;
					}

	public void setIdevent(Long idevent) {
						this.idevent = idevent;
					}
	
	public Long getCodpais() {
						return this.codpais;
					}

	public void setCodpais(Long codpais) {
						this.codpais = codpais;
					}
	
	public Integer getCoddpto() {
						return this.coddpto;
					}

	public void setCoddpto(Integer coddpto) {
						this.coddpto = coddpto;
					}
	
	public Integer getCodprov() {
						return this.codprov;
					}

	public void setCodprov(Integer codprov) {
						this.codprov = codprov;
					}
	
	public Integer getCoddist() {
						return this.coddist;
					}

	public void setCoddist(Integer coddist) {
						this.coddist = coddist;
					}
	
	public String getUbicacion() {
						return this.ubicacion;
					}

	public void setUbicacion(String ubicacion) {
						this.ubicacion = ubicacion;
					}
	
	public Timestamp getFechaActivIni() {
						return this.fechaActivIni;
					}

	public void setFechaActivIni(Timestamp fechaActivIni) {
						this.fechaActivIni = fechaActivIni;
					}
	
	public Timestamp getFechaActivFin() {
						return this.fechaActivFin;
					}

	public void setFechaActivFin(Timestamp fechaActivFin) {
						this.fechaActivFin = fechaActivFin;
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
	
	
	
	public String getIdeventTxt() {
		return this.ideventTxt;
	}
	public void setIdeventTxt(String ideventTxt) {
		this.ideventTxt = ideventTxt;
	}
	public String getCodpaisTxt() {
		return this.codpaisTxt;
	}
	public void setCodpaisTxt(String codpaisTxt) {
		this.codpaisTxt = codpaisTxt;
	}
	public String getCoddptoTxt() {
		return this.coddptoTxt;
	}
	public void setCoddptoTxt(String coddptoTxt) {
		this.coddptoTxt = coddptoTxt;
	}
	public String getCodprovTxt() {
		return this.codprovTxt;
	}
	public void setCodprovTxt(String codprovTxt) {
		this.codprovTxt = codprovTxt;
	}
	public String getCoddistTxt() {
		return this.coddistTxt;
	}
	public void setCoddistTxt(String coddistTxt) {
		this.coddistTxt = coddistTxt;
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
		
	
	public TdUbicacionesACL getTdUbicacionesACL() {
		return tdUbicacionesACL;
	}

	public void setTdUbicacionesACL(TdUbicacionesACL tdUbicacionesACL) {
		this.tdUbicacionesACL = tdUbicacionesACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
}
