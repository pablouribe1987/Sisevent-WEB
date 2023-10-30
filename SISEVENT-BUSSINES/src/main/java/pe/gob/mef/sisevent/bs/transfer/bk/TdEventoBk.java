package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;
import java.util.List;

import pe.gob.mef.sisevent.bs.ctlracceso.TdEventoACL;

/**
 * TD_EVENTO BAKING: EVENTO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdEventoBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1419964255926451622L;

	//ID
	private Long idevent;
		
	//PROPIEDADES
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
	
	
	//ADICIONALES
	private String remiRazonSocialTxt = null;
	private String remiIdproveeTxt = null;
	private String remiNombresapellidosTxt = null;
	private String remiIdpersonTxt = null;
	private String tipoeventoTxt = null;
	private String idcategoriasTxt = null;
	private String modalidadTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private TdEventoACL tdEventoACL = null;		
	//MPINARES 01092023 - INICIO  
		private List<TdItinerarioBk> tdItinerarioJss = null;
		private List<MsCategoriasBk> msCategoriasJss = null;
		private List<MsUsuariosBk> msUsuariosJss= null;
		//MPINARES 01092023 - FIN
		private String idTipoTarea =null; //vbaldeon 08092023
		private String idTipoTareaTxt=null;// vbaldeon 08092023
		private String  idGrupo=null;//vbaldeon 08092023 
		private String 	idGrupoTxt=null;//vbaldeon 08092023 
		
		private List<TdFlujoBk> tdFlujoBks = null;//vbaldeon 08092023
	public TdEventoBk() {
	}
	
	public Long getIdevent() {
		return this.idevent;
	}

	public void setIdevent(Long idevent) {
		this.idevent = idevent;
	}
	
	public String getTitulo() {
						return this.titulo;
					}

	public void setTitulo(String titulo) {
						this.titulo = titulo;
					}
	
	public Timestamp getFechaSoliIni() {
						return this.fechaSoliIni;
					}

	public void setFechaSoliIni(Timestamp fechaSoliIni) {
						this.fechaSoliIni = fechaSoliIni;
					}
	
	public Timestamp getFechaSoliFin() {
						return this.fechaSoliFin;
					}

	public void setFechaSoliFin(Timestamp fechaSoliFin) {
						this.fechaSoliFin = fechaSoliFin;
					}
	
	public Integer getPorconfirmar() {
						return this.porconfirmar;
					}

	public void setPorconfirmar(Integer porconfirmar) {
						this.porconfirmar = porconfirmar;
					}
	
	public Long getIddoc() {
						return this.iddoc;
					}

	public void setIddoc(Long iddoc) {
						this.iddoc = iddoc;
					}
	
	public String getNumeroSid() {
						return this.numeroSid;
					}

	public void setNumeroSid(String numeroSid) {
						this.numeroSid = numeroSid;
					}
	
	public Integer getNumeroAnioSid() {
						return this.numeroAnioSid;
					}

	public void setNumeroAnioSid(Integer numeroAnioSid) {
						this.numeroAnioSid = numeroAnioSid;
					}
	
	public String getAsuntohr() {
						return this.asuntohr;
					}

	public void setAsuntohr(String asuntohr) {
						this.asuntohr = asuntohr;
					}
	
	public String getRemiRazonSocial() {
						return this.remiRazonSocial;
					}

	public void setRemiRazonSocial(String remiRazonSocial) {
						this.remiRazonSocial = remiRazonSocial;
					}
	
	public Long getRemiIdprovee() {
						return this.remiIdprovee;
					}

	public void setRemiIdprovee(Long remiIdprovee) {
						this.remiIdprovee = remiIdprovee;
					}
	
	public String getRemiNombresapellidos() {
						return this.remiNombresapellidos;
					}

	public void setRemiNombresapellidos(String remiNombresapellidos) {
						this.remiNombresapellidos = remiNombresapellidos;
					}
	
	public Long getRemiIdperson() {
						return this.remiIdperson;
					}

	public void setRemiIdperson(Long remiIdperson) {
						this.remiIdperson = remiIdperson;
					}
	
	public String getTipoevento() {
						return this.tipoevento;
					}

	public void setTipoevento(String tipoevento) {
						this.tipoevento = tipoevento;
					}
	
	public Long getIdcategorias() {
						return this.idcategorias;
					}

	public void setIdcategorias(Long idcategorias) {
						this.idcategorias = idcategorias;
					}
	
	public String getModalidad() {
						return this.modalidad;
					}

	public void setModalidad(String modalidad) {
						this.modalidad = modalidad;
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
	
	
	
	public String getRemiRazonSocialTxt() {
		return this.remiRazonSocialTxt;
	}
	public void setRemiRazonSocialTxt(String remiRazonSocialTxt) {
		this.remiRazonSocialTxt = remiRazonSocialTxt;
	}
	public String getRemiIdproveeTxt() {
		return this.remiIdproveeTxt;
	}
	public void setRemiIdproveeTxt(String remiIdproveeTxt) {
		this.remiIdproveeTxt = remiIdproveeTxt;
	}
	public String getRemiNombresapellidosTxt() {
		return this.remiNombresapellidosTxt;
	}
	public void setRemiNombresapellidosTxt(String remiNombresapellidosTxt) {
		this.remiNombresapellidosTxt = remiNombresapellidosTxt;
	}
	public String getRemiIdpersonTxt() {
		return this.remiIdpersonTxt;
	}
	public void setRemiIdpersonTxt(String remiIdpersonTxt) {
		this.remiIdpersonTxt = remiIdpersonTxt;
	}
	public String getTipoeventoTxt() {
		return this.tipoeventoTxt;
	}
	public void setTipoeventoTxt(String tipoeventoTxt) {
		this.tipoeventoTxt = tipoeventoTxt;
	}
	public String getIdcategoriasTxt() {
		return this.idcategoriasTxt;
	}
	public void setIdcategoriasTxt(String idcategoriasTxt) {
		this.idcategoriasTxt = idcategoriasTxt;
	}
	public String getModalidadTxt() {
		return this.modalidadTxt;
	}
	public void setModalidadTxt(String modalidadTxt) {
		this.modalidadTxt = modalidadTxt;
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
		
	
	public TdEventoACL getTdEventoACL() {
		return tdEventoACL;
	}

	public void setTdEventoACL(TdEventoACL tdEventoACL) {
		this.tdEventoACL = tdEventoACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
	//MPINARES 01092023 - INICIO 
			public java.util.Date getFechaSoliIniJUD() {
				java.util.Date fechaSoliIniJUD = null;
				if (fechaSoliIni != null)
					fechaSoliIniJUD = new java.util.Date(fechaSoliIni.getTime());
				return fechaSoliIniJUD;
			}
			public void setFechaSoliIniJUD(java.util.Date fechaSoliIniJUD) {
				if (fechaSoliIniJUD != null)
					this.fechaSoliIni = new Timestamp(fechaSoliIniJUD.getTime());
				else
					this.fechaSoliIni = null;
			}
			
			public java.util.Date getFechaSoliFinJUD() {
				java.util.Date fechaSoliFinJUD = null;
				if (fechaSoliFin != null)
					fechaSoliFinJUD = new java.util.Date(fechaSoliFin.getTime());
				return fechaSoliFinJUD;
			}
			public void setFechaSoliFinJUD(java.util.Date fechaSoliFinJUD) {
				if (fechaSoliFinJUD != null)
					this.fechaSoliFin = new Timestamp(fechaSoliFinJUD.getTime());
				else
					this.fechaSoliFin = null;
			}
			
			public List<TdItinerarioBk> getTdItinerarioJss() {
				return tdItinerarioJss;
			}

			public void setTdItinerarioJss(List<TdItinerarioBk> tdItinerarioJss) {
				this.tdItinerarioJss = tdItinerarioJss;
			}

			public List<MsCategoriasBk> getMsCategoriasJss() {
				return msCategoriasJss;
			}

			public void setMsCategoriasJss(List<MsCategoriasBk> msCategoriasJss) {
				this.msCategoriasJss = msCategoriasJss;
			}

			public List<MsUsuariosBk> getMsUsuariosJss() {
				return msUsuariosJss;
			}

			public void setMsUsuariosJss(List<MsUsuariosBk> msUsuariosJss) {
				this.msUsuariosJss = msUsuariosJss;
			}			
			//MPINARES 01092023 - FIN
			//vbaldeon 08092023 inicio
			
			public String getIdTipoTarea() {
				return idTipoTarea;
			}

			public void setIdTipoTarea(String idTipoTarea) {
				this.idTipoTarea = idTipoTarea;
			}

			public String getIdTipoTareaTxt() {
				return idTipoTareaTxt;
			}

			public void setIdTipoTareaTxt(String idTipoTareaTxt) {
				this.idTipoTareaTxt = idTipoTareaTxt;
			}

			public String getIdGrupo() {
				return idGrupo;
			}

			public void setIdGrupo(String idGrupo) {
				this.idGrupo = idGrupo;
			}

			public String getIdGrupoTxt() {
				return idGrupoTxt;
			}

			public void setIdGrupoTxt(String idGrupoTxt) {
				this.idGrupoTxt = idGrupoTxt;
			}

			public List<TdFlujoBk> getTdFlujoBks() {
				return tdFlujoBks;
			}

			public void setTdFlujoBks(List<TdFlujoBk> tdFlujoBks) {
				this.tdFlujoBks = tdFlujoBks;
			}
}
