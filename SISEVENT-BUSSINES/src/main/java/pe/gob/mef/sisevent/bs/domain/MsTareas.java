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
 * MS_TAREAS ENTIDAD: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS DERIVACIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "MS_TAREAS")
public class MsTareas implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2747887283845096802L;

	// ID
	private Long idtareas = null;

	// PROPIEDADES
	private String tarea = null;
	private String descripcion = null;
	private Long tiempo = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private String rtmaddress = null;
	private String rtmaddressmodif = null;
	private Integer estado = null;
	

	public MsTareas() {
	}

	@SequenceGenerator(name = "G_MS_TAREAS", sequenceName = PropertiesMg.ESQUEMA + "SQ_MS_TAREAS", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_MS_TAREAS")
@Id
@Column(name = "IDTAREAS", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdtareas()
{
		return this.idtareas;
}

public void setIdtareas(Long idtareas)
{
		this.idtareas = idtareas;
}

@Column(name = "TAREA", nullable = false, length = 150)
public String  getTarea()
{
		return this.tarea;
}

public void setTarea(String tarea)
{
		this.tarea = tarea;
}

@Column(name = "DESCRIPCION", length = 500)
public String  getDescripcion()
{
		return this.descripcion;
}

public void setDescripcion(String descripcion)
{
		this.descripcion = descripcion;
}

@Column(name = "TIEMPO", precision = 10, scale = 0)
public Long  getTiempo()
{
		return this.tiempo;
}

public void setTiempo(Long tiempo)
{
		this.tiempo = tiempo;
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
