package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.MsUnidadesOrgACL;

/**
 * MS_UNIDADES_ORG BAKING: UNIDADES ORGÁNICAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 23:37
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 25/11/2020 23:37   / Creación de la clase    /
 * 
 */
public class MsUnidadesOrgBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5741045184803328449L;

	//ID
	private Long idunidad;
		
	//PROPIEDADES
	private Long idpadre = null;
	private String codigo = null;
	private String descripcion = null;
	private String acronimo = null;
	private Integer flagofgeneral = null;
	private Long iduserCrea = null;
	private Timestamp fechaCrea = null;
	private Long iduserModif = null;
	private Timestamp fechaModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	
	
	//ADICIONALES
	private String idpadreTxt = null;
	
	private Long idsppadre = null;
	private String idsppadreTxt = null;
	
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private MsUnidadesOrgACL msUnidadesOrgACL = null;
	private String idsppadreTxtAcro	 = null;//MPINARES 27042022 - INICIO
		
	public MsUnidadesOrgBk() {
	}
	
	public Long getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Long idunidad) {
		this.idunidad = idunidad;
	}
	
	public Long getIdpadre() {
						return this.idpadre;
					}

	public void setIdpadre(Long idpadre) {
						this.idpadre = idpadre;
					}
	
	public String getCodigo() {
						return this.codigo;
					}

	public void setCodigo(String codigo) {
						this.codigo = codigo;
					}
	
	public String getDescripcion() {
						return this.descripcion;
					}

	public void setDescripcion(String descripcion) {
						this.descripcion = descripcion;
					}
	
	public String getAcronimo() {
						return this.acronimo;
					}

	public void setAcronimo(String acronimo) {
						this.acronimo = acronimo;
					}
	
	public Integer getFlagofgeneral() {
						return this.flagofgeneral;
					}

	public void setFlagofgeneral(Integer flagofgeneral) {
						this.flagofgeneral = flagofgeneral;
					}
	
	public Long getIduserCrea() {
						return this.iduserCrea;
					}

	public void setIduserCrea(Long iduserCrea) {
						this.iduserCrea = iduserCrea;
					}
	
	public Timestamp getFechaCrea() {
						return this.fechaCrea;
					}

	public void setFechaCrea(Timestamp fechaCrea) {
						this.fechaCrea = fechaCrea;
					}
	
	public Long getIduserModif() {
						return this.iduserModif;
					}

	public void setIduserModif(Long iduserModif) {
						this.iduserModif = iduserModif;
					}
	
	public Timestamp getFechaModif() {
						return this.fechaModif;
					}

	public void setFechaModif(Timestamp fechaModif) {
						this.fechaModif = fechaModif;
					}
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	public String getRmtaddress() {
						return this.rmtaddress;
					}

	public void setRmtaddress(String rmtaddress) {
						this.rmtaddress = rmtaddress;
					}
	
	public String getRmtaddressrst() {
						return this.rmtaddressrst;
					}

	public void setRmtaddressrst(String rmtaddressrst) {
						this.rmtaddressrst = rmtaddressrst;
					}
	
	
	
	public String getIdpadreTxt() {
		return this.idpadreTxt;
	}
	public void setIdpadreTxt(String idpadreTxt) {
		this.idpadreTxt = idpadreTxt;
	}
	public String getIduserCreaTxt() {
		return this.iduserCreaTxt;
	}
	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}
	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}
	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}

	public MsUnidadesOrgACL getMsUnidadesOrgACL() {
		return msUnidadesOrgACL;
	}

	public void setMsUnidadesOrgACL(MsUnidadesOrgACL msUnidadesOrgACL) {
		this.msUnidadesOrgACL = msUnidadesOrgACL;
	}
		
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
	public void setCclase(String cclase) {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idunidad == null) ? 0 : idunidad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MsUnidadesOrgBk other = (MsUnidadesOrgBk) obj;
		if (idunidad == null) {
			if (other.idunidad != null)
				return false;
		} else if (!idunidad.equals(other.idunidad))
			return false;
		return true;
	}

	public Long getIdsppadre() {
		return idsppadre;
	}

	public void setIdsppadre(Long idsppadre) {
		this.idsppadre = idsppadre;
	}

	public String getIdsppadreTxt() {
		return idsppadreTxt;
	}

	public void setIdsppadreTxt(String idsppadreTxt) {
		this.idsppadreTxt = idsppadreTxt;
	}	
	
	//MPINARES 27042022 - INICIO
		public String getIdsppadreTxtAcro() {
			return idsppadreTxtAcro;
		}

		public void setIdsppadreTxtAcro(String idsppadreTxtAcro) {
			this.idsppadreTxtAcro = idsppadreTxtAcro;
		}	
		//MPINARES 27042022 - FIN
}