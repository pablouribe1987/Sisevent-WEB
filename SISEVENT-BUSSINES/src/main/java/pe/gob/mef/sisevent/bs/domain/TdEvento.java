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
 * TD_EVENTO ENTIDAD: EVENTO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:35
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:35   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_EVENTO")
public class TdEvento implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2544887474600728073L;

	// ID
	private Long idevent = null;

	// PROPIEDADES
	private String titulo = null;
	private Timestamp fechaSoliIni = null;
	private Timestamp fechaSoliFin = null;
	private Integer porconfirmar = null;
	private Long iddoc = null;
	private String numeroSid = null;
	private Integer numeroAnioSid = null;
	private String asuntohr = null;
	private String remiRazonSocial = null;
	private Long remiIdprovee = null;
	private String remiNombresapellidos = null;
	private Long remiIdperson = null;
	private String tipoevento = null;
	private Long idcategorias = null;
	private String modalidad = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	

	public TdEvento() {
	}

	@SequenceGenerator(name = "G_TD_EVENTO", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_EVENTO", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_EVENTO")
@Id
@Column(name = "IDEVENT", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdevent()
{
		return this.idevent;
}

public void setIdevent(Long idevent)
{
		this.idevent = idevent;
}

@Column(name = "TITULO", nullable = false, length = 600)
public String  getTitulo()
{
		return this.titulo;
}

public void setTitulo(String titulo)
{
		this.titulo = titulo;
}

@Column(name = "FECHA_SOLI_INI", nullable = false, length = 7)
public Timestamp  getFechaSoliIni()
{
		return this.fechaSoliIni;
}

public void setFechaSoliIni(Timestamp fechaSoliIni)
{
		this.fechaSoliIni = fechaSoliIni;
}

@Column(name = "FECHA_SOLI_FIN", nullable = false, length = 7)
public Timestamp  getFechaSoliFin()
{
		return this.fechaSoliFin;
}

public void setFechaSoliFin(Timestamp fechaSoliFin)
{
		this.fechaSoliFin = fechaSoliFin;
}

@Column(name = "PORCONFIRMAR", nullable = false, precision = 1, scale = 0)
public Integer  getPorconfirmar()
{
		return this.porconfirmar;
}

public void setPorconfirmar(Integer porconfirmar)
{
		this.porconfirmar = porconfirmar;
}

@Column(name = "IDDOC", precision = 10, scale = 0)
public Long  getIddoc()
{
		return this.iddoc;
}

public void setIddoc(Long iddoc)
{
		this.iddoc = iddoc;
}

@Column(name = "NUMERO_SID", length = 50)
public String  getNumeroSid()
{
		return this.numeroSid;
}

public void setNumeroSid(String numeroSid)
{
		this.numeroSid = numeroSid;
}

@Column(name = "NUMERO_ANIO_SID", precision = 4, scale = 0)
public Integer  getNumeroAnioSid()
{
		return this.numeroAnioSid;
}

public void setNumeroAnioSid(Integer numeroAnioSid)
{
		this.numeroAnioSid = numeroAnioSid;
}

@Column(name = "ASUNTOHR", nullable = false, length = 600)
public String  getAsuntohr()
{
		return this.asuntohr;
}

public void setAsuntohr(String asuntohr)
{
		this.asuntohr = asuntohr;
}

@Column(name = "REMI_RAZON_SOCIAL", length = 255)
public String  getRemiRazonSocial()
{
		return this.remiRazonSocial;
}

public void setRemiRazonSocial(String remiRazonSocial)
{
		this.remiRazonSocial = remiRazonSocial;
}

@Column(name = "REMI_IDPROVEE", precision = 10, scale = 0)
public Long  getRemiIdprovee()
{
		return this.remiIdprovee;
}

public void setRemiIdprovee(Long remiIdprovee)
{
		this.remiIdprovee = remiIdprovee;
}

@Column(name = "REMI_NOMBRESAPELLIDOS", length = 350)
public String  getRemiNombresapellidos()
{
		return this.remiNombresapellidos;
}

public void setRemiNombresapellidos(String remiNombresapellidos)
{
		this.remiNombresapellidos = remiNombresapellidos;
}

@Column(name = "REMI_IDPERSON", precision = 10, scale = 0)
public Long  getRemiIdperson()
{
		return this.remiIdperson;
}

public void setRemiIdperson(Long remiIdperson)
{
		this.remiIdperson = remiIdperson;
}

@Column(name = "TIPOEVENTO", length = 150)
public String  getTipoevento()
{
		return this.tipoevento;
}

public void setTipoevento(String tipoevento)
{
		this.tipoevento = tipoevento;
}

@Column(name = "IDCATEGORIAS", nullable = false, precision = 10, scale = 0)
public Long  getIdcategorias()
{
		return this.idcategorias;
}

public void setIdcategorias(Long idcategorias)
{
		this.idcategorias = idcategorias;
}

@Column(name = "MODALIDAD", length = 150)
public String  getModalidad()
{
		return this.modalidad;
}

public void setModalidad(String modalidad)
{
		this.modalidad = modalidad;
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
