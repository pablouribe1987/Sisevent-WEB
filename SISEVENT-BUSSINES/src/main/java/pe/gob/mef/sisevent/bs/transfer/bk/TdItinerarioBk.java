package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdItinerarioACL;

/**
 * TD_ITINERARIO BAKING: ITINERARIO DE LOS VUELOS A REALIZARCE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdItinerarioBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 196627381486376007L;

	//ID
	private Long iditinerario;
		
	//PROPIEDADES
	private Long idevent = null;
	private Long codpais = null;
	private Integer coddpto = null;
	private Integer codprov = null;
	private String origendestino = null;
	private Timestamp fechaSaliIni = null;
	private Timestamp fechaLlegFin = null;
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
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private TdItinerarioACL tdItinerarioACL = null;		
	
	//MPINARES 01092023 - INICIO
		private Long contador = null; 
		private String tipovuelo = null;
		private String idtipoVueloTxt = null;
		//MPINARES 01092023 - FIN
	
	public TdItinerarioBk() {
	}
	
	public Long getIditinerario() {
		return this.iditinerario;
	}

	public void setIditinerario(Long iditinerario) {
		this.iditinerario = iditinerario;
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
	
	public String getOrigendestino() {
						return this.origendestino;
					}

	public void setOrigendestino(String origendestino) {
						this.origendestino = origendestino;
					}
	
	public Timestamp getFechaSaliIni() {
						return this.fechaSaliIni;
					}

	public void setFechaSaliIni(Timestamp fechaSaliIni) {
						this.fechaSaliIni = fechaSaliIni;
					}
	
	public Timestamp getFechaLlegFin() {
						return this.fechaLlegFin;
					}

	public void setFechaLlegFin(Timestamp fechaLlegFin) {
						this.fechaLlegFin = fechaLlegFin;
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
		
	
	public TdItinerarioACL getTdItinerarioACL() {
		return tdItinerarioACL;
	}

	public void setTdItinerarioACL(TdItinerarioACL tdItinerarioACL) {
		this.tdItinerarioACL = tdItinerarioACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
	
	//MPINARES 01092023 - INICIO 
		public Long getContador() {
			return contador;
		}

		public void setContador(Long contador) {
			this.contador = contador;
		}



		public String getTipovuelo() {
			return tipovuelo;
		}

		public void setTipovuelo(String tipovuelo) {
			this.tipovuelo = tipovuelo;
		}

		public String getIdtipoVueloTxt() {
			return idtipoVueloTxt;
		}

		public void setIdtipoVueloTxt(String idtipoVueloTxt) {
			this.idtipoVueloTxt = idtipoVueloTxt;
		}
		
		
		//MPINARES 01092023 - FIN 
}
