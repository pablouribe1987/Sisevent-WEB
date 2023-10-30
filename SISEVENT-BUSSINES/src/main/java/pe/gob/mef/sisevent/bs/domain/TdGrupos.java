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
 * TD_GRUPOS ENTIDAD: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_GRUPOS")
public class TdGrupos implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8268452995085904397L;

	// ID
	private Long idgrupo = null;

	// PROPIEDADES
	private String grupo = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	

	public TdGrupos() {
	}

	@SequenceGenerator(name = "G_TD_GRUPOS", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_GRUPOS", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_GRUPOS")
@Id
@Column(name = "IDGRUPO", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdgrupo()
{
		return this.idgrupo;
}

public void setIdgrupo(Long idgrupo)
{
		this.idgrupo = idgrupo;
}

@Column(name = "GRUPO", nullable = false, length = 150)
public String  getGrupo()
{
		return this.grupo;
}

public void setGrupo(String grupo)
{
		this.grupo = grupo;
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
