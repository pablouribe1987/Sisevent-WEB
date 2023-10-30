package pe.gob.mef.sisevent.bs.transfer.bk;

import java.sql.Timestamp;

import pe.gob.mef.sisevent.bs.ctlracceso.TdItinerarioACL;

/**
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/05/2023 17:25
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 26/05/2023 17:25   / Creación de la clase    /
 * 
 */
public class TdCategEventoBk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 196627381486376007L;

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
	
	
		public TdCategEventoBk() {
		}


		public Long getIdcategevento() {
			return idcategevento;
		}


		public void setIdcategevento(Long idcategevento) {
			this.idcategevento = idcategevento;
		}


		public Long getIdevent() {
			return idevent;
		}


		public void setIdevent(Long idevent) {
			this.idevent = idevent;
		}


		public Long getIdcategorias() {
			return idcategorias;
		}


		public void setIdcategorias(Long idcategorias) {
			this.idcategorias = idcategorias;
		}


		public Timestamp getFechaCrea() {
			return fechaCrea;
		}


		public void setFechaCrea(Timestamp fechaCrea) {
			this.fechaCrea = fechaCrea;
		}


		public Timestamp getFechaModif() {
			return fechaModif;
		}


		public void setFechaModif(Timestamp fechaModif) {
			this.fechaModif = fechaModif;
		}


		public Long getIduserCrea() {
			return iduserCrea;
		}


		public void setIduserCrea(Long iduserCrea) {
			this.iduserCrea = iduserCrea;
		}


		public Long getIduserModif() {
			return iduserModif;
		}


		public void setIduserModif(Long iduserModif) {
			this.iduserModif = iduserModif;
		}


		public String getRmtaddress() {
			return rmtaddress;
		}


		public void setRmtaddress(String rmtaddress) {
			this.rmtaddress = rmtaddress;
		}


		public String getRmtaddressrst() {
			return rmtaddressrst;
		}


		public void setRmtaddressrst(String rmtaddressrst) {
			this.rmtaddressrst = rmtaddressrst;
		}


		public Integer getEstado() {
			return estado;
		}


		public void setEstado(Integer estado) {
			this.estado = estado;
		}
		
		
		
	
}
