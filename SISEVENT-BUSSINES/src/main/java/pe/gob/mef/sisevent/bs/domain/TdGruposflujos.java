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
 * TD_GRUPOSFLUJOS ENTIDAD: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_GRUPOSFLUJOS")
public class TdGruposflujos implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1241836505931529993L;

	// ID
	private Long idgruposflujos = null;

	// PROPIEDADES
	private Long idtareas = null;
	private Long idunidadDestino = null;
	private Long iduserDestino = null;
	private String observacion = null;
	private String observacionHtml = null;
	private Integer tiempoestadia = null;
	private String correosnotif = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	private Long idgrupo = null;
	

	public TdGruposflujos() {
	}

	@SequenceGenerator(name = "G_TD_GRUPOSFLUJOS", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_GRUPOSFLUJOS", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_GRUPOSFLUJOS")
@Id
@Column(name = "IDGRUPOSFLUJOS", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdgruposflujos()
{
		return this.idgruposflujos;
}

public void setIdgruposflujos(Long idgruposflujos)
{
		this.idgruposflujos = idgruposflujos;
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

@Column(name = "IDUNIDAD_DESTINO", nullable = false, precision = 10, scale = 0)
public Long  getIdunidadDestino()
{
		return this.idunidadDestino;
}

public void setIdunidadDestino(Long idunidadDestino)
{
		this.idunidadDestino = idunidadDestino;
}

@Column(name = "IDUSER_DESTINO", nullable = false, precision = 10, scale = 0)
public Long  getIduserDestino()
{
		return this.iduserDestino;
}

public void setIduserDestino(Long iduserDestino)
{
		this.iduserDestino = iduserDestino;
}

@Column(name = "OBSERVACION", length = 4000)
public String  getObservacion()
{
		return this.observacion;
}

public void setObservacion(String observacion)
{
		this.observacion = observacion;
}

@Column(name = "OBSERVACION_HTML", length = 4000)
public String  getObservacionHtml()
{
		return this.observacionHtml;
}

public void setObservacionHtml(String observacionHtml)
{
		this.observacionHtml = observacionHtml;
}

@Column(name = "TIEMPOESTADIA", precision = 4, scale = 0)
public Integer  getTiempoestadia()
{
		return this.tiempoestadia;
}

public void setTiempoestadia(Integer tiempoestadia)
{
		this.tiempoestadia = tiempoestadia;
}

@Column(name = "CORREOSNOTIF", length = 250)
public String  getCorreosnotif()
{
		return this.correosnotif;
}

public void setCorreosnotif(String correosnotif)
{
		this.correosnotif = correosnotif;
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

@Column(name = "IDGRUPO", nullable = false, precision = 10, scale = 0)
public Long  getIdgrupo()
{
		return this.idgrupo;
}

public void setIdgrupo(Long idgrupo)
{
		this.idgrupo = idgrupo;
}


			
}
