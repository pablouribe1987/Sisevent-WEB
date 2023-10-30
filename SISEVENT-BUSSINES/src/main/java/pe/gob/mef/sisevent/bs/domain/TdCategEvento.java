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
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_CATEG_EVENTO")
public class TdCategEvento implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 445316411927275431L;

	// ID
	private Long idcategevento = null;

	// PROPIEDADES
	private Long idevent = null;
	private Long idcategorias = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	

	public TdCategEvento() {
	}

	@SequenceGenerator(name = "G_TD_CATEG_EVENTO", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_CATEG_EVENTO", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_CATEG_EVENTO")
@Id
@Column(name = "IDCATEGEVENTO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getIdcategevento() {
		return this.idcategevento;
	}

	public void setIdcategevento(Long idcategevento) {
		this.idcategevento = idcategevento;
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

@Column(name = "IDCATEGORIAS", precision = 10, scale = 0)
public Long getIdcategorias() {
	return this.idcategorias;
}

public void setIdcategorias(Long idcategorias) {
	this.idcategorias = idcategorias;
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
