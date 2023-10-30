package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import pe.gob.mef.sisevent.bs.ctlracceso.MsUbigeoACL;
import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;

/**
 * MS_UBIGEO BAKING: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 23/12/2020 11:52 / Creación de la clase /
 * 
 */
public class MsUbigeoBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -453109263928309447L;

	// ID
	private MsUbigeoId id;

	// PROPIEDADES
	private String nombre = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private Integer estado = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;

	// ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;

	private String coddistTxt=null;
	private String coddptoTxt=null;
	private String codprovTxt=null;
	
	private MsUbigeoACL msUbigeoACL = null;
	
	private Integer tipo = null;

	public MsUbigeoBk() {
	}

	public MsUbigeoId getId() {
		return this.id;
	}

	public void setId(MsUbigeoId id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public MsUbigeoACL getMsUbigeoACL() {
		return msUbigeoACL;
	}

	public void setMsUbigeoACL(MsUbigeoACL msUbigeoACL) {
		this.msUbigeoACL = msUbigeoACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
	public void setCclase(String cclase) {
	}

	public String getCoddistTxt() {
		return coddistTxt;
	}

	public void setCoddistTxt(String coddistTxt) {
		this.coddistTxt = coddistTxt;
	}

	public String getCoddptoTxt() {
		return coddptoTxt;
	}

	public void setCoddptoTxt(String coddptoTxt) {
		this.coddptoTxt = coddptoTxt;
	}

	public String getCodprovTxt() {
		return codprovTxt;
	}

	public void setCodprovTxt(String codprovTxt) {
		this.codprovTxt = codprovTxt;
	}
	
	public String getIds(){
		DecimalFormat df = new DecimalFormat("00");
		StringBuffer sb = new StringBuffer();
		if(id!=null){
			if(id.getCoddpto()!=null){
				sb.append(df.format(id.getCoddpto().intValue()));
			}else{
				sb.append("00");
			}
			if(id.getCodprov()!=null){
				sb.append(df.format(id.getCodprov().intValue()));
			}else{
				sb.append("00");
			}
			if(id.getCoddist()!=null){
				sb.append(df.format(id.getCoddist().intValue()));
			}else{
				sb.append("00");
			}
		}else{
			sb.append("000000");
		}
		
		return sb.toString();
	}
	
	public void setIds(String s){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MsUbigeoBk other = (MsUbigeoBk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Integer getTipo() {
		if(tipo==null){
			if(id!=null){
				if(id.getCoddist()!=null && id.getCoddist().intValue()>0){
					tipo=3;
				}else if(id.getCodprov()!=null && id.getCodprov().intValue()>0){
					tipo=2;
				}else if(id.getCoddpto()!=null && id.getCoddpto().intValue()>0){
					tipo=1;
				}
			}
		}
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}	
}