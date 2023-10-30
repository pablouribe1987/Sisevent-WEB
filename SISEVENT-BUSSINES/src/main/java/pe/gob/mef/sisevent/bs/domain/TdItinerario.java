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
 * TD_ITINERARIO ENTIDAD: ITINERARIO DE LOS VUELOS A REALIZARCE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_ITINERARIO")
public class TdItinerario implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 445316411927275431L;

	// ID
	private Long iditinerario = null;

	// PROPIEDADES
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
	private String tipovuelo = null;//MPINARES 01092023 - INICIO 	

	public TdItinerario() {
	}

	@SequenceGenerator(name = "G_TD_ITINERARIO", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_ITINERARIO", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_ITINERARIO")
@Id
@Column(name = "IDITINERARIO", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIditinerario()
{
		return this.iditinerario;
}

public void setIditinerario(Long iditinerario)
{
		this.iditinerario = iditinerario;
}

@Column(name = "IDEVENT", precision = 10, scale = 0)
public Long  getIdevent()
{
		return this.idevent;
}

public void setIdevent(Long idevent)
{
		this.idevent = idevent;
}

@Column(name = "CODPAIS", precision = 10, scale = 0)
public Long  getCodpais()
{
		return this.codpais;
}

public void setCodpais(Long codpais)
{
		this.codpais = codpais;
}

@Column(name = "CODDPTO", precision = 2, scale = 0)
public Integer  getCoddpto()
{
		return this.coddpto;
}

public void setCoddpto(Integer coddpto)
{
		this.coddpto = coddpto;
}

@Column(name = "CODPROV", precision = 2, scale = 0)
public Integer  getCodprov()
{
		return this.codprov;
}

public void setCodprov(Integer codprov)
{
		this.codprov = codprov;
}

@Column(name = "ORIGENDESTINO", nullable = false, length = 550)
public String  getOrigendestino()
{
		return this.origendestino;
}

public void setOrigendestino(String origendestino)
{
		this.origendestino = origendestino;
}

@Column(name = "FECHA_SALI_INI", nullable = false, length = 7)
public Timestamp  getFechaSaliIni()
{
		return this.fechaSaliIni;
}

public void setFechaSaliIni(Timestamp fechaSaliIni)
{
		this.fechaSaliIni = fechaSaliIni;
}

@Column(name = "FECHA_LLEG_FIN", nullable = false, length = 7)
public Timestamp  getFechaLlegFin()
{
		return this.fechaLlegFin;
}

public void setFechaLlegFin(Timestamp fechaLlegFin)
{
		this.fechaLlegFin = fechaLlegFin;
}

@Column(name = "FECHA_CREA", nullable = false, length = 7)
public Timestamp  getFechaCrea()
{
		return this.fechaCrea;
}

public void setFechaCrea(Timestamp fechaCrea)
{
		this.fechaCrea = fechaCrea;
}

@Column(name = "FECHA_MODIF", nullable = false, length = 7)
public Timestamp  getFechaModif()
{
		return this.fechaModif;
}

public void setFechaModif(Timestamp fechaModif)
{
		this.fechaModif = fechaModif;
}

@Column(name = "IDUSER_CREA", nullable = false, precision = 10, scale = 0)
public Long  getIduserCrea()
{
		return this.iduserCrea;
}

public void setIduserCrea(Long iduserCrea)
{
		this.iduserCrea = iduserCrea;
}

@Column(name = "IDUSER_MODIF", nullable = false, precision = 10, scale = 0)
public Long  getIduserModif()
{
		return this.iduserModif;
}

public void setIduserModif(Long iduserModif)
{
		this.iduserModif = iduserModif;
}

@Column(name = "RMTADDRESS", nullable = false, length = 50)
public String  getRmtaddress()
{
		return this.rmtaddress;
}

public void setRmtaddress(String rmtaddress)
{
		this.rmtaddress = rmtaddress;
}

@Column(name = "RMTADDRESSRST", nullable = false, length = 50)
public String  getRmtaddressrst()
{
		return this.rmtaddressrst;
}

public void setRmtaddressrst(String rmtaddressrst)
{
		this.rmtaddressrst = rmtaddressrst;
}

@Column(name = "ESTADO", nullable = false, precision = 1, scale = 0)
public Integer  getEstado()
{
		return this.estado;
}

public void setEstado(Integer estado)
{
		this.estado = estado;
}

//MPINARES 01092023 - INICIO 
@Column(name = "TIPOVUELO", length = 150)
public String getTipovuelo() {
	return this.tipovuelo;
}

public void setTipovuelo(String tipovuelo) {
	this.tipovuelo = tipovuelo;
}
//MPINARES 01092023 - FIN 
			
}
