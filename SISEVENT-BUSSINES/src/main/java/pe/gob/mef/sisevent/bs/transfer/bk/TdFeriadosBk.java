package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdFeriadosACL;

/**
 * TD_FERIADOS BAKING: DÍAS NO LABORABLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdFeriadosBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1313739514580041163L;

	//ID
	private Long idferiado;
		
	//PROPIEDADES
	private Timestamp fechaFeriado = null;
	private String descricion = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrs = null;
	private Integer estado = null;
	
	
	//ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	private TdFeriadosACL tdFeriadosACL = null;		
		
	public TdFeriadosBk() {
	}
	
	public Long getIdferiado() {
		return this.idferiado;
	}

	public void setIdferiado(Long idferiado) {
		this.idferiado = idferiado;
	}
	
	public Timestamp getFechaFeriado() {
						return this.fechaFeriado;
					}

	public void setFechaFeriado(Timestamp fechaFeriado) {
						this.fechaFeriado = fechaFeriado;
					}
	
	public String getDescricion() {
						return this.descricion;
					}

	public void setDescricion(String descricion) {
						this.descricion = descricion;
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
	
	public String getRmtaddressrs() {
						return this.rmtaddressrs;
					}

	public void setRmtaddressrs(String rmtaddressrs) {
						this.rmtaddressrs = rmtaddressrs;
					}
	
	public Integer getEstado() {
						return this.estado;
					}

	public void setEstado(Integer estado) {
						this.estado = estado;
					}
	
	public TdFeriadosACL getTdFeriadosACL() {
		return tdFeriadosACL;
	}

	public void setTdFeriadosACL(TdFeriadosACL tdFeriadosACL) {
		this.tdFeriadosACL = tdFeriadosACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}

	public String getIduserCreaTxt() {
		return iduserCreaTxt;
	}

	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}

	public String getIduserModifTxt() {
		return iduserModifTxt;
	}

	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}
}
