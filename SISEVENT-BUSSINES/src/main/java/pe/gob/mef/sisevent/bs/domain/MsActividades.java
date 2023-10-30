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
 * MS_ACTIVIDADES ENTIDAD: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:35
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:35   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "MS_ACTIVIDADES")
public class MsActividades implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3287456194808687003L;

	// ID
	private Long idactividades = null;

	// PROPIEDADES
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
	

	public MsActividades() {
	}

	@SequenceGenerator(name = "G_MS_ACTIVIDADES", sequenceName = PropertiesMg.ESQUEMA + "SQ_MS_ACTIVIDADES", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_ACTIVIDADES")
@Id
@Column(name = "IDACTIVIDADES", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdactividades()
{
		return this.idactividades;
}

public void setIdactividades(Long idactividades)
{
		this.idactividades = idactividades;
}

@Column(name = "IDTAREAS", nullable = false, precision = 10, scale = 0)
public Long  getIdtareas()
{
		return this.idtareas;
}

public void setIdtareas(Long idtareas)
{
		this.idtareas = idtareas;
}

@Column(name = "ACTIVIDAD", nullable = false, length = 150)
public String  getActividad()
{
		return this.actividad;
}

public void setActividad(String actividad)
{
		this.actividad = actividad;
}

@Column(name = "CAMPOSALLENAR", length = 500)
public String  getCamposallenar()
{
		return this.camposallenar;
}

public void setCamposallenar(String camposallenar)
{
		this.camposallenar = camposallenar;
}

@Column(name = "NUMERODIGITALES", precision = 4, scale = 0)
public Integer  getNumerodigitales()
{
		return this.numerodigitales;
}

public void setNumerodigitales(Integer numerodigitales)
{
		this.numerodigitales = numerodigitales;
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

@Column(name = "RTMADDRESS", nullable = false, length = 50)
public String  getRtmaddress()
{
		return this.rtmaddress;
}

public void setRtmaddress(String rtmaddress)
{
		this.rtmaddress = rtmaddress;
}

@Column(name = "RTMADDRESSMODIF", nullable = false, length = 50)
public String  getRtmaddressmodif()
{
		return this.rtmaddressmodif;
}

public void setRtmaddressmodif(String rtmaddressmodif)
{
		this.rtmaddressmodif = rtmaddressmodif;
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
