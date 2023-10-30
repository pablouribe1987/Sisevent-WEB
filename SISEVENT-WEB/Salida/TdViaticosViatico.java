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
 * TD_VIATICOS_VIATICO ENTIDAD: 
 * 
 * @author Carlos Aguilar
 * @version 2.0, 28/05/2023 17:09
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 28/05/2023 17:09   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_VIATICOS_VIATICO")
public class TdViaticosViatico implements java.io.Serializable {


	// ID
	private Long idviatico = null;

	// PROPIEDADES
	private String servicio = null;
	private String centroCosto = null;
	private Long idsede = null;
	private Long idpersonal = null;
	private String banco = null;
	private String numeroCuenta = null;
	private Timestamp fechaInicio = null;
	private Timestamp fechaTermino = null;
	private String tipoComision = null;
	private String itinerario = null;
	private String motivoComision = null;
	private String logroEsperado = null;
	private String indicador = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;
	

	public TdViaticosViatico() {
	}

	@SequenceGenerator(name = "G_TD_VIATICOS_VIATICO", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_VIATICOS_VIATICO", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_VIATICOS_VIATICO")
@Id
@Column(name = "IDVIATICO", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdviatico()
{
		return this.idviatico;
}

public void setIdviatico(Long idviatico)
{
		this.idviatico = idviatico;
}

@Column(name = "SERVICIO", length = 256)
public String  getServicio()
{
		return this.servicio;
}

public void setServicio(String servicio)
{
		this.servicio = servicio;
}

@Column(name = "CENTRO_COSTO", length = 256)
public String  getCentroCosto()
{
		return this.centroCosto;
}

public void setCentroCosto(String centroCosto)
{
		this.centroCosto = centroCosto;
}

@Column(name = "IDSEDE", precision = 10, scale = 0)
public Long  getIdsede()
{
		return this.idsede;
}

public void setIdsede(Long idsede)
{
		this.idsede = idsede;
}

@Column(name = "IDPERSONAL", precision = 10, scale = 0)
public Long  getIdpersonal()
{
		return this.idpersonal;
}

public void setIdpersonal(Long idpersonal)
{
		this.idpersonal = idpersonal;
}

@Column(name = "BANCO", length = 256)
public String  getBanco()
{
		return this.banco;
}

public void setBanco(String banco)
{
		this.banco = banco;
}

@Column(name = "NUMERO_CUENTA", length = 256)
public String  getNumeroCuenta()
{
		return this.numeroCuenta;
}

public void setNumeroCuenta(String numeroCuenta)
{
		this.numeroCuenta = numeroCuenta;
}

@Column(name = "FECHA_INICIO", length = 7)
public Timestamp  getFechaInicio()
{
		return this.fechaInicio;
}

public void setFechaInicio(Timestamp fechaInicio)
{
		this.fechaInicio = fechaInicio;
}

@Column(name = "FECHA_TERMINO", length = 7)
public Timestamp  getFechaTermino()
{
		return this.fechaTermino;
}

public void setFechaTermino(Timestamp fechaTermino)
{
		this.fechaTermino = fechaTermino;
}

@Column(name = "TIPO_COMISION", length = 256)
public String  getTipoComision()
{
		return this.tipoComision;
}

public void setTipoComision(String tipoComision)
{
		this.tipoComision = tipoComision;
}

@Column(name = "ITINERARIO", length = 256)
public String  getItinerario()
{
		return this.itinerario;
}

public void setItinerario(String itinerario)
{
		this.itinerario = itinerario;
}

@Column(name = "MOTIVO_COMISION", length = 256)
public String  getMotivoComision()
{
		return this.motivoComision;
}

public void setMotivoComision(String motivoComision)
{
		this.motivoComision = motivoComision;
}

@Column(name = "LOGRO_ESPERADO", length = 256)
public String  getLogroEsperado()
{
		return this.logroEsperado;
}

public void setLogroEsperado(String logroEsperado)
{
		this.logroEsperado = logroEsperado;
}

@Column(name = "INDICADOR", length = 256)
public String  getIndicador()
{
		return this.indicador;
}

public void setIndicador(String indicador)
{
		this.indicador = indicador;
}

@Column(name = "IDUSER_CREA", precision = 10, scale = 0)
public Long  getIduserCrea()
{
		return this.iduserCrea;
}

public void setIduserCrea(Long iduserCrea)
{
		this.iduserCrea = iduserCrea;
}

@Column(name = "IDUSER_MODIF", precision = 10, scale = 0)
public Long  getIduserModif()
{
		return this.iduserModif;
}

public void setIduserModif(Long iduserModif)
{
		this.iduserModif = iduserModif;
}

@Column(name = "FECHA_CREA", length = 7)
public Timestamp  getFechaCrea()
{
		return this.fechaCrea;
}

public void setFechaCrea(Timestamp fechaCrea)
{
		this.fechaCrea = fechaCrea;
}

@Column(name = "FECHA_MODIF", length = 7)
public Timestamp  getFechaModif()
{
		return this.fechaModif;
}

public void setFechaModif(Timestamp fechaModif)
{
		this.fechaModif = fechaModif;
}

@Column(name = "RTMADDRESS", length = 256)
public String  getRtmaddress()
{
		return this.rtmaddress;
}

public void setRtmaddress(String rtmaddress)
{
		this.rtmaddress = rtmaddress;
}

@Column(name = "RTMADDRESSMODIF", length = 256)
public String  getRtmaddressmodif()
{
		return this.rtmaddressmodif;
}

public void setRtmaddressmodif(String rtmaddressmodif)
{
		this.rtmaddressmodif = rtmaddressmodif;
}

@Column(name = "ESTADO", precision = 1, scale = 0)
public Integer  getEstado()
{
		return this.estado;
}

public void setEstado(Integer estado)
{
		this.estado = estado;
}


			
}
