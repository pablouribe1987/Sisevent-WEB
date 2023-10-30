package pe.gob.mef.sisevent.bs.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pe.gob.mef.sisevent.bs.utils.PropertiesMg;

/**
 * TD_FLUJO ENTIDAD: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 22/12/2020 17:45 / Creación de la clase /
 * 
 */
@Entity
@Table(name = "TD_FLUJO")
public class TdFlujo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ID
	private Long idflujo = null;

	// PROPIEDADES
	private Long idflujopadre = null;
	private Long idevent = null;//vbaldeon 25092023  eliminar todo CA
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
	
	//vbaldeon 08092023 INICIO
//			private Long idEvento=null;
	private Long idTarea=null;
	//vbaldeon 08092023 fin

	public TdFlujo() {
	}

	@SequenceGenerator(name = "G_TD_FLUJO", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_FLUJO", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_FLUJO")
	@Id
	@Column(name = "IDFLUJO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(Long idflujo) {
		this.idflujo = idflujo;
	}

	@Column(name = "IDFLUJOPADRE", precision = 10, scale = 0)
	public Long getIdflujopadre() {
		return this.idflujopadre;
	}

	public void setIdflujopadre(Long idflujopadre) {
		this.idflujopadre = idflujopadre;
	}

	//vbaldeon 25092023  eliminar todo inicio CA		
		@Column(name = "IDEVENT", nullable = false, precision = 10, scale = 0) 
		public Long getIdevent() {
			return this.idevent;
		}

		public void setIdevent(Long idevent) {
			this.idevent = idevent;
		}
		//vbaldeon 25092023  fin

	@Column(name = "FECHA_DERIVACION", nullable = false, length = 7)
	public Timestamp getFechaDerivacion() {
		return this.fechaDerivacion;
	}

	public void setFechaDerivacion(Timestamp fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	@Column(name = "IDUNIDAD_DERIVA", nullable = false, precision = 10, scale = 0)
	public Long getIdunidadDeriva() {
		return this.idunidadDeriva;
	}

	public void setIdunidadDeriva(Long idunidadDeriva) {
		this.idunidadDeriva = idunidadDeriva;
	}

	@Column(name = "IDUSER_DERIVA", nullable = false, precision = 10, scale = 0)
	public Long getIduserDeriva() {
		return this.iduserDeriva;
	}

	public void setIduserDeriva(Long iduserDeriva) {
		this.iduserDeriva = iduserDeriva;
	}

	@Column(name = "FECHA_RECEPCION", length = 7)
	public Timestamp getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column(name = "IDUNIDAD_RECEPCIONA", precision = 10, scale = 0)
	public Long getIdunidadRecepciona() {
		return this.idunidadRecepciona;
	}

	public void setIdunidadRecepciona(Long idunidadRecepciona) {
		this.idunidadRecepciona = idunidadRecepciona;
	}

	@Column(name = "IDUSER_RECEPCIONA", precision = 10, scale = 0)
	public Long getIduserRecepciona() {
		return this.iduserRecepciona;
	}

	public void setIduserRecepciona(Long iduserRecepciona) {
		this.iduserRecepciona = iduserRecepciona;
	}

	@Column(name = "IDUNIDAD_DESTINO", nullable = false, precision = 10, scale = 0)
	public Long getIdunidadDestino() {
		return this.idunidadDestino;
	}

	public void setIdunidadDestino(Long idunidadDestino) {
		this.idunidadDestino = idunidadDestino;
	}

	@Column(name = "IDUSER_DESTINO", nullable = false, precision = 10, scale = 0)
	public Long getIduserDestino() {
		return this.iduserDestino;
	}

	public void setIduserDestino(Long iduserDestino) {
		this.iduserDestino = iduserDestino;
	}

	@Column(name = "IDUNIDAD_ATIENDE", precision = 10, scale = 0)
	public Long getIdunidadAtiende() {
		return this.idunidadAtiende;
	}

	public void setIdunidadAtiende(Long idunidadAtiende) {
		this.idunidadAtiende = idunidadAtiende;
	}

	@Column(name = "IDUSER_ATIENDE", precision = 10, scale = 0)
	public Long getIduserAtiende() {
		return this.iduserAtiende;
	}

	public void setIduserAtiende(Long iduserAtiende) {
		this.iduserAtiende = iduserAtiende;
	}

	@Column(name = "FECHA_ATENCION", length = 7)
	public Timestamp getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Timestamp fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	@Column(name = "OBSERVACION", length = 4000)
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "TIEMPOESTADIA", precision = 4, scale = 0)
	public Integer getTiempoestadia() {
		return this.tiempoestadia;
	}

	public void setTiempoestadia(Integer tiempoestadia) {
		this.tiempoestadia = tiempoestadia;
	}

	@Column(name = "FECHA_CREA", nullable = false, length = 7)
	public Timestamp getFechaCrea() {
		return this.fechaCrea;
	}

	public void setFechaCrea(Timestamp fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	@Column(name = "FECHA_MODIF", nullable = false, length = 7)
	public Timestamp getFechaModif() {
		return this.fechaModif;
	}

	public void setFechaModif(Timestamp fechaModif) {
		this.fechaModif = fechaModif;
	}

	@Column(name = "IDUSER_CREA", nullable = false, precision = 10, scale = 0)
	public Long getIduserCrea() {
		return this.iduserCrea;
	}

	public void setIduserCrea(Long iduserCrea) {
		this.iduserCrea = iduserCrea;
	}

	@Column(name = "IDUSER_MODIF", nullable = false, precision = 10, scale = 0)
	public Long getIduserModif() {
		return this.iduserModif;
	}

	public void setIduserModif(Long iduserModif) {
		this.iduserModif = iduserModif;
	}

	@Column(name = "ESTADO", nullable = false, precision = 2, scale = 0)
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name = "RMTADDRESS", nullable = false, length = 50)
	public String getRmtaddress() {
		return this.rmtaddress;
	}

	public void setRmtaddress(String rmtaddress) {
		this.rmtaddress = rmtaddress;
	}

	@Column(name = "RMTADDRESSRST", nullable = false, length = 50)
	public String getRmtaddressrst() {
		return this.rmtaddressrst;
	}

	public void setRmtaddressrst(String rmtaddressrst) {
		this.rmtaddressrst = rmtaddressrst;
	}

	@Column(name = "CORREOSNOTIF", length = 250)
	public String getCorreosnotif() {
		return this.correosnotif;
	}

	public void setCorreosnotif(String correosnotif) {
		this.correosnotif = correosnotif;
	}

	// MPINARES 27042022 - INICIO
	@Column(name = "OBSERVACION_HTML", length = 4000)
	public String getObservacionHtml() {
		return this.observacionHtml;
	}

	public void setObservacionHtml(String observacionHtml) {
		this.observacionHtml = observacionHtml;
	}
	// MPINARES 27042022 - FIN
	//vbaldeon 08092023 INICIO
//		@Column(name = "IDEVENT", precision = 10, scale = 0)
//		public Long getIdEvento() {
//			return idEvento;
//		}
//
//		public void setIdEvento(Long idEvento) {
//			this.idEvento = idEvento;
//		}
		@Column(name = "IDTAREAS", precision = 10, scale = 0)
		public Long getIdTarea() {
			return idTarea;
		}

		public void setIdTarea(Long idTarea) {
			this.idTarea = idTarea;
		}	
		//vbaldeon 08092023 fin
}
