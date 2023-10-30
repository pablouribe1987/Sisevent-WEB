package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdParticipantesACL;

/**
 * TD_PARTICIPANTES BAKING: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdParticipantesBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5664554209723952290L;

	//ID
	private Long idparticipantes;
		
	//PROPIEDADES
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
	
	
	//ADICIONALES
	private String idusuarioIdproveeIdpersonTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private TdParticipantesACL tdParticipantesACL = null;		
	private Long idevent = null;//MPINARES 01092023 - INICIO 
	
	public TdParticipantesBk() {
	}
	
	public Long getIdparticipantes() {
		return this.idparticipantes;
	}

	public void setIdparticipantes(Long idparticipantes) {
		this.idparticipantes = idparticipantes;
	}
	
	public Long getIdusuarioIdproveeIdperson() {
						return this.idusuarioIdproveeIdperson;
					}

	public void setIdusuarioIdproveeIdperson(Long idusuarioIdproveeIdperson) {
						this.idusuarioIdproveeIdperson = idusuarioIdproveeIdperson;
					}
	
	public String getNombresrazonsocial() {
						return this.nombresrazonsocial;
					}

	public void setNombresrazonsocial(String nombresrazonsocial) {
						this.nombresrazonsocial = nombresrazonsocial;
					}
	
	public Integer getTipo() {
						return this.tipo;
					}

	public void setTipo(Integer tipo) {
						this.tipo = tipo;
					}
	
	public Integer getFlagacompaniante() {
						return this.flagacompaniante;
					}

	public void setFlagacompaniante(Integer flagacompaniante) {
						this.flagacompaniante = flagacompaniante;
					}
	
	public Timestamp getFechaCrea() {
						return this.fechaCrea;
					}

	public void setFechaCrea(Timestamp fechaCrea) {
						this.fechaCrea = fechaCrea;
					}
	
	public Timestamp getFechaModif() {
						return this.fechaModif;
					}

	public void setFechaModif(Timestamp fechaModif) {
						this.fechaModif = fechaModif;
					}
	
	public Long getIduserCrea() {
						return this.iduserCrea;
					}

	public void setIduserCrea(Long iduserCrea) {
						this.iduserCrea = iduserCrea;
					}
	
	public Long getIduserModif() {
						return this.iduserModif;
					}

	public void setIduserModif(Long iduserModif) {
						this.iduserModif = iduserModif;
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
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	
	
	public String getIdusuarioIdproveeIdpersonTxt() {
		return this.idusuarioIdproveeIdpersonTxt;
	}
	public void setIdusuarioIdproveeIdpersonTxt(String idusuarioIdproveeIdpersonTxt) {
		this.idusuarioIdproveeIdpersonTxt = idusuarioIdproveeIdpersonTxt;
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
		
	
	public TdParticipantesACL getTdParticipantesACL() {
		return tdParticipantesACL;
	}

	public void setTdParticipantesACL(TdParticipantesACL tdParticipantesACL) {
		this.tdParticipantesACL = tdParticipantesACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
	
	//MPINARES 01092023 - INICIO 
		public Long getIdevent() {
			return idevent;
		}

		public void setIdevent(Long idevent) {
			this.idevent = idevent;
		}
		//MPINARES 01092023 - FIN 
}
