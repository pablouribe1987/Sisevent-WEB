package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;
import java.util.List;

import pe.gob.mef.sisevent.bs.transfer.TdAnexosDto;

/**
 * TD_FLUJO BAKING: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class TdFlujoBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235964220539088949L;

	// ID
	private Long idflujo;

	// PROPIEDADES
	private Long idflujopadre = null;
	private Long idevent = null;
	private Timestamp fechaDerivacion = null;
	private Long idunidadDeriva = null;
	private Long iduserDeriva = null;
	private Timestamp fechaRecepcion = null;
	private Long idunidadRecepciona = null;
	private Long iduserRecepciona = null;
	private Long idunidadDestino = null;
	private Long iduserDestino = null;
	private Long idunidadAtiende = null;
	private Long iduserAtiende = null;
	private Timestamp fechaAtencion = null;
	private String observacion = null;
	private Integer tiempoestadia = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private String correosnotif = null;
	private String observacionHtml = null;// MPINARES 27042022 - INICIO
	private Long idTarea=null;
	// ADICIONALES
	private int ordflujo = 0;
	private int ordflujopadre = 0;
//	private String idsaccTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;

	private String idunidadDerivaTxt = null;
	private String idunidadDerivaAcroTxt = null;
	private String iduserDerivaTxt = null;
	private String idunidadRecepcionaTxt = null;
	private String iduserRecepcionaTxt = null;
	private String idunidadDestinoTxt = null;
	private String iduserDestinoTxt = null;
	private String idunidadAtiendeTxt = null;
	private String iduserAtiendeTxt = null;
	private String estadoTxt = null;
	private String idunidadDestinoGTxt = null;

	private List<TdAnexosDto> tdAnexosBksss = null;
	
	//vbaldeon 08092023 INICIO
	private String idEventoTxt=null; //vbaldeon 25092023 
	private String idTareaTxt=null; //vbaldeon 25092023 
	//vbaldeon 08092023 fin
	private String  contador; //vbaldeon 25092023 
	private boolean  add; //vbaldeon 25092023 

	public TdFlujoBk() {
	}

	public Long getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(Long idflujo) {
		this.idflujo = idflujo;
	}

	public Long getIdflujopadre() {
		return this.idflujopadre;
	}

	public void setIdflujopadre(Long idflujopadre) {
		this.idflujopadre = idflujopadre;
	}

	public Long getIdevent() {
		return this.idevent;
	}

	public void setIdevent(Long idevent) {
		this.idevent = idevent;
	}

	public Timestamp getFechaDerivacion() {
		return this.fechaDerivacion;
	}

	public void setFechaDerivacion(Timestamp fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	public java.util.Date getFechaDerivacionJUD() {
		java.util.Date fechaDerivacionJUD = null;
		if (fechaDerivacion != null)
			fechaDerivacionJUD = new java.util.Date(fechaDerivacion.getTime());
		return fechaDerivacionJUD;
	}

	public void setFechaDerivacionJUD(java.util.Date fechaDerivacionJUD) {
		if (fechaDerivacionJUD != null)
			this.fechaDerivacion = new Timestamp(fechaDerivacionJUD.getTime());
		else
			this.fechaDerivacion = null;
	}

	public Long getIdunidadDeriva() {
		return this.idunidadDeriva;
	}

	public void setIdunidadDeriva(Long idunidadDeriva) {
		this.idunidadDeriva = idunidadDeriva;
	}

	public Long getIduserDeriva() {
		return this.iduserDeriva;
	}

	public void setIduserDeriva(Long iduserDeriva) {
		this.iduserDeriva = iduserDeriva;
	}

	public Timestamp getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public java.util.Date getFechaRecepcionJUD() {
		java.util.Date fechaRecepcionJUD = null;
		if (fechaRecepcion != null)
			fechaRecepcionJUD = new java.util.Date(fechaRecepcion.getTime());
		return fechaRecepcionJUD;
	}

	public void setFechaRecepcionJUD(java.util.Date fechaRecepcionJUD) {
		if (fechaRecepcionJUD != null)
			this.fechaRecepcion = new Timestamp(fechaRecepcionJUD.getTime());
		else
			this.fechaRecepcion = null;
	}

	public Long getIdunidadRecepciona() {
		return this.idunidadRecepciona;
	}

	public void setIdunidadRecepciona(Long idunidadRecepciona) {
		this.idunidadRecepciona = idunidadRecepciona;
	}

	public Long getIduserRecepciona() {
		return this.iduserRecepciona;
	}

	public void setIduserRecepciona(Long iduserRecepciona) {
		this.iduserRecepciona = iduserRecepciona;
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

	public Long getIdunidadAtiende() {
		return this.idunidadAtiende;
	}

	public void setIdunidadAtiende(Long idunidadAtiende) {
		this.idunidadAtiende = idunidadAtiende;
	}

	public Long getIduserAtiende() {
		return this.iduserAtiende;
	}

	public void setIduserAtiende(Long iduserAtiende) {
		this.iduserAtiende = iduserAtiende;
	}

	public Timestamp getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Timestamp fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public java.util.Date getFechaAtencionJUD() {
		java.util.Date fechaAtencionJUD = null;
		if (fechaAtencion != null)
			fechaAtencionJUD = new java.util.Date(fechaAtencion.getTime());
		return fechaAtencionJUD;
	}

	public void setFechaAtencionJUD(java.util.Date fechaAtencionJUD) {
		if (fechaAtencionJUD != null)
			this.fechaAtencion = new Timestamp(fechaAtencionJUD.getTime());
		else
			this.fechaAtencion = null;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getTiempoestadia() {
		return this.tiempoestadia;
	}

	public void setTiempoestadia(Integer tiempoestadia) {
		this.tiempoestadia = tiempoestadia;
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

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

//	public String getIdsaccTxt() {
//		return this.idsaccTxt;
//	}
//
//	public void setIdsaccTxt(String idsaccTxt) {
//		this.idsaccTxt = idsaccTxt;
//	}

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

	public String getIdunidadDerivaTxt() {
		return idunidadDerivaTxt;
	}

	public void setIdunidadDerivaTxt(String idunidadDerivaTxt) {
		this.idunidadDerivaTxt = idunidadDerivaTxt;
	}

	public String getIduserDerivaTxt() {
		return iduserDerivaTxt;
	}

	public void setIduserDerivaTxt(String iduserDerivaTxt) {
		this.iduserDerivaTxt = iduserDerivaTxt;
	}

	public String getIdunidadRecepcionaTxt() {
		return idunidadRecepcionaTxt;
	}

	public void setIdunidadRecepcionaTxt(String idunidadRecepcionaTxt) {
		this.idunidadRecepcionaTxt = idunidadRecepcionaTxt;
	}

	public String getIduserRecepcionaTxt() {
		return iduserRecepcionaTxt;
	}

	public void setIduserRecepcionaTxt(String iduserRecepcionaTxt) {
		this.iduserRecepcionaTxt = iduserRecepcionaTxt;
	}

	public String getIdunidadDestinoTxt() {
		return idunidadDestinoTxt;
	}

	public void setIdunidadDestinoTxt(String idunidadDestinoTxt) {
		this.idunidadDestinoTxt = idunidadDestinoTxt;
	}

	public String getIduserDestinoTxt() {
		return iduserDestinoTxt;
	}

	public void setIduserDestinoTxt(String iduserDestinoTxt) {
		this.iduserDestinoTxt = iduserDestinoTxt;
	}

	public String getIdunidadAtiendeTxt() {
		return idunidadAtiendeTxt;
	}

	public void setIdunidadAtiendeTxt(String idunidadAtiendeTxt) {
		this.idunidadAtiendeTxt = idunidadAtiendeTxt;
	}

	public String getIduserAtiendeTxt() {
		return iduserAtiendeTxt;
	}

	public void setIduserAtiendeTxt(String iduserAtiendeTxt) {
		this.iduserAtiendeTxt = iduserAtiendeTxt;
	}

	public String getEstadoTxt() {
		return estadoTxt;
	}

	public void setEstadoTxt(String estadoTxt) {
		this.estadoTxt = estadoTxt;
	}

	public int getOrdflujo() {
		return ordflujo;
	}

	public void setOrdflujo(int ordflujo) {
		this.ordflujo = ordflujo;
	}

	public int getOrdflujopadre() {
		return ordflujopadre;
	}

	public void setOrdflujopadre(int ordflujopadre) {
		this.ordflujopadre = ordflujopadre;
	}

	public List<TdAnexosDto> getTdAnexosBksss() {
		return tdAnexosBksss;
	}

	public void setTdAnexosBksss(List<TdAnexosDto> tdAnexosBksss) {
		this.tdAnexosBksss = tdAnexosBksss;
	}

	public String getIdunidadDestinoGTxt() {
		return idunidadDestinoGTxt;
	}

	public void setIdunidadDestinoGTxt(String idunidadDestinoGTxt) {
		this.idunidadDestinoGTxt = idunidadDestinoGTxt;
	}

	public String getIdunidadDerivaAcroTxt() {
		return idunidadDerivaAcroTxt;
	}

	public void setIdunidadDerivaAcroTxt(String idunidadDerivaAcroTxt) {
		this.idunidadDerivaAcroTxt = idunidadDerivaAcroTxt;
	}

	public String getCorreosnotif() {
		return correosnotif;
	}

	public void setCorreosnotif(String correosnotif) {
		this.correosnotif = correosnotif;
	}

	// MPINARES 27042022 - INICIO
	public String getObservacionHtml() {
		return observacionHtml;
	}

	public void setObservacionHtml(String observacionHtml) {
		this.observacionHtml = observacionHtml;
	}

	public String getObservacionView() {
		String observacionView = "";
		if (observacionHtml == null || observacionHtml.length() < 0) {
			observacionView = observacion;
		} else {
			observacionView = observacionHtml;
		}
		return observacionView;
	}
	// MPINARES 27042022 - FIN
	
	public String getIdEventoTxt() { //vbaldeon 25092023 
		return idEventoTxt;
	}

	public void setIdEventoTxt(String idEventoTxt) {//vbaldeon 25092023 
		this.idEventoTxt = idEventoTxt;
	}

	public String getIdTareaTxt() { //vbaldeon 25092023 
		return idTareaTxt;
	}

	public void setIdTareaTxt(String idTareaTxt) { //vbaldeon 25092023 
		this.idTareaTxt = idTareaTxt;
	}
	
	//vbaldeon 08092023 FIN
	
			//vbaldeon 25092023  inicio
			public String getContador() {
				return contador;
			}

			public void setContador(String contador) {
				this.contador = contador;
			}

			public boolean isAdd() {
				return add;
			}

			public void setAdd(boolean add) {
				this.add = add;
			}
			//vbaldeon 25092023  fin

			public Long getIdTarea() {
				return idTarea;
			}

			public void setIdTarea(Long idTarea) {
				this.idTarea = idTarea;
			}
}