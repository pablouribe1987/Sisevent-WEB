package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.MsCategoriasACL;

/**
 * MS_CATEGORIAS BAKING: CATEGORÍAS ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class MsCategoriasBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8357810427204092475L;

	//ID
	private Long idcategorias;
		
	//PROPIEDADES
	private String categoria = null;
	private String arraycamposocultos = null;
	private Timestamp fechaCrea = null;
	private Timestamp fechaModif = null;
	private Long iduserCrea = null;
	private Long iduserModif = null;
	private String rmtaddress = null;
	private String rmtaddressrst = null;
	private Integer estado = null;
	
	
	//ADICIONALES
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private MsCategoriasACL msCategoriasACL = null;		
		
	public MsCategoriasBk() {
	}
	
	public Long getIdcategorias() {
		return this.idcategorias;
	}

	public void setIdcategorias(Long idcategorias) {
		this.idcategorias = idcategorias;
	}
	
	public String getCategoria() {
						return this.categoria;
					}

	public void setCategoria(String categoria) {
						this.categoria = categoria;
					}
	
	public String getArraycamposocultos() {
						return this.arraycamposocultos;
					}

	public void setArraycamposocultos(String arraycamposocultos) {
						this.arraycamposocultos = arraycamposocultos;
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
		
	
	public MsCategoriasACL getMsCategoriasACL() {
		return msCategoriasACL;
	}

	public void setMsCategoriasACL(MsCategoriasACL msCategoriasACL) {
		this.msCategoriasACL = msCategoriasACL;
	}
	
	public String getCclase(){
		if(estado!=null && estado.intValue()>0){
			return "cverde";
		}else{
			
		}return "camarillo";
	}
}
