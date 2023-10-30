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
 * TD_UBICACIONES ENTIDAD: UBICACIONES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_UBICACIONES")
public class TdUbicaciones implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4661622198438668883L;

	// ID
	private Long idubicaciones = null;

	// PROPIEDADES
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
	

	public TdUbicaciones() {
	}

	@SequenceGenerator(name = "G_TD_UBICACIONES", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_UBICACIONES", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_UBICACIONES")
@Id
@Column(name = "IDUBICACIONES", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdubicaciones()
{
		return this.idubicaciones;
}

public void setIdubicaciones(Long idubicaciones)
{
		this.idubicaciones = idubicaciones;
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

@Column(name = "CODDIST", precision = 2, scale = 0)
public Integer  getCoddist()
{
		return this.coddist;
}

public void setCoddist(Integer coddist)
{
		this.coddist = coddist;
}

@Column(name = "UBICACION", nullable = false, length = 550)
public String  getUbicacion()
{
		return this.ubicacion;
}

public void setUbicacion(String ubicacion)
{
		this.ubicacion = ubicacion;
}

@Column(name = "FECHA_ACTIV_INI", nullable = false, length = 7)
public Timestamp  getFechaActivIni()
{
		return this.fechaActivIni;
}

public void setFechaActivIni(Timestamp fechaActivIni)
{
		this.fechaActivIni = fechaActivIni;
}

@Column(name = "FECHA_ACTIV_FIN", nullable = false, length = 7)
public Timestamp  getFechaActivFin()
{
		return this.fechaActivFin;
}

public void setFechaActivFin(Timestamp fechaActivFin)
{
		this.fechaActivFin = fechaActivFin;
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


			
}
