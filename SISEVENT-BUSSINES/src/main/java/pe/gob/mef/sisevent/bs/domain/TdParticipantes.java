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
 * TD_PARTICIPANTES ENTIDAD: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *   /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:11   / Creación de la clase    /
 * 
 */
@Entity
@Table(name = "TD_PARTICIPANTES")
public class TdParticipantes implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7870516836631419987L;

	// ID
	private Long idparticipantes = null;

	// PROPIEDADES
	private Long idusuarioIdproveeIdperson = null;
	private String nombresrazonsocial = null;
	private Integer tipo = null;
	private Integer flagacompaniante = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	private Long idevent = null;//MPINARES 01092023 - INICIO 
	

	public TdParticipantes() {
	}

	@SequenceGenerator(name = "G_TD_PARTICIPANTES", sequenceName = PropertiesMg.ESQUEMA + "SQ_TD_PARTICIPANTES", allocationSize = 1)
@GeneratedValue(strategy = SEQUENCE, generator = "G_TD_PARTICIPANTES")
@Id
@Column(name = "IDPARTICIPANTES", unique = true, nullable = false, precision = 10, scale = 0)
public Long  getIdparticipantes()
{
		return this.idparticipantes;
}

public void setIdparticipantes(Long idparticipantes)
{
		this.idparticipantes = idparticipantes;
}

@Column(name = "IDUSUARIO_IDPROVEE_IDPERSON", nullable = false, precision = 10, scale = 0)
public Long  getIdusuarioIdproveeIdperson()
{
		return this.idusuarioIdproveeIdperson;
}

public void setIdusuarioIdproveeIdperson(Long idusuarioIdproveeIdperson)
{
		this.idusuarioIdproveeIdperson = idusuarioIdproveeIdperson;
}

@Column(name = "NOMBRESRAZONSOCIAL", nullable = false, length = 600)
public String  getNombresrazonsocial()
{
		return this.nombresrazonsocial;
}

public void setNombresrazonsocial(String nombresrazonsocial)
{
		this.nombresrazonsocial = nombresrazonsocial;
}

@Column(name = "TIPO", nullable = false, precision = 1, scale = 0)
public Integer  getTipo()
{
		return this.tipo;
}

public void setTipo(Integer tipo)
{
		this.tipo = tipo;
}

@Column(name = "FLAGACOMPANIANTE", nullable = false, precision = 1, scale = 0)
public Integer  getFlagacompaniante()
{
		return this.flagacompaniante;
}

public void setFlagacompaniante(Integer flagacompaniante)
{
		this.flagacompaniante = flagacompaniante;
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
@Column(name = "IDEVENT", precision = 10, scale = 0)
public Long  getIdevent()
{
		return this.idevent;
}

public void setIdevent(Long idevent)
{
		this.idevent = idevent;
}
//MPINARES 01092023 - FIN 
			
}
