/**
 * ExpedienteWSDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.controller.rs.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpedienteJS implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820872753460951976L;
	private java.lang.String anexos = null;
	private java.lang.String asunto = null;
	private java.lang.String fechahoradeiniciodetramite = null;
	private java.lang.String fechahoradeldocumento = null;
	private java.lang.String fechahorarecepcion = null;
	private List<MovimientoJS> movimientoss = null;
	private java.lang.String noexpediente = null;
	private java.lang.String numerodedocumento = null;
	private java.lang.String numerodefolios = null;
	private java.lang.String situacionactual = null;
	private java.lang.String tipodedocumento = null;
	// MPINARES 27042022 - INICIO
	private java.lang.String fechahorafindocumento = null;
	private java.lang.String observacionfindocumento = null;
	private java.lang.String expedienteiddoc = null;
	// MPINARES 27042022 - FIN

	// MPINARES 01092023 - INICIO
	private java.lang.String remitente;
	private java.lang.String idperson;
	private java.lang.String idprovee;
	private java.lang.String numeroSid;
	private java.lang.String numeroAnioSid;
	// MPINARES 01092023 - FIN

	public ExpedienteJS() {
	}

	public ExpedienteJS(java.lang.String anexos, java.lang.String asunto, java.lang.String fechahoradeiniciodetramite,
			java.lang.String fechahoradeldocumento, java.lang.String fechahorarecepcion,
			List<MovimientoJS> movimientoss, java.lang.String noexpediente, java.lang.String numerodedocumento,
			java.lang.String numerodefolios, java.lang.String situacionactual, java.lang.String tipodedocumento) {
		this.anexos = anexos;
		this.asunto = asunto;
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
		this.fechahoradeldocumento = fechahoradeldocumento;
		this.fechahorarecepcion = fechahorarecepcion;
		this.movimientoss = movimientoss;
		this.noexpediente = noexpediente;
		this.numerodedocumento = numerodedocumento;
		this.numerodefolios = numerodefolios;
		this.situacionactual = situacionactual;
		this.tipodedocumento = tipodedocumento;
	}

	// MPINARES 27042022 - INICIO
	public ExpedienteJS(String anexos, String asunto, String fechahoradeiniciodetramite, String fechahoradeldocumento,
			String fechahorarecepcion, List<MovimientoJS> movimientoss, String noexpediente, String numerodedocumento,
			String numerodefolios, String situacionactual, String tipodedocumento, String fechahorafindocumento,
			String observacionfindocumento) {
		super();
		this.anexos = anexos;
		this.asunto = asunto;
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
		this.fechahoradeldocumento = fechahoradeldocumento;
		this.fechahorarecepcion = fechahorarecepcion;
		this.movimientoss = movimientoss;
		this.noexpediente = noexpediente;
		this.numerodedocumento = numerodedocumento;
		this.numerodefolios = numerodefolios;
		this.situacionactual = situacionactual;
		this.tipodedocumento = tipodedocumento;
		this.fechahorafindocumento = fechahorafindocumento;
		this.observacionfindocumento = observacionfindocumento;
	}
	// MPINARES 27042022 - FIN

	// MPINARES 01092023 - INICIO
	public ExpedienteJS(String anexos, String asunto, String fechahoradeiniciodetramite, String fechahoradeldocumento,
			String fechahorarecepcion, List<MovimientoJS> movimientoss, String noexpediente, String numerodedocumento,
			String numerodefolios, String situacionactual, String tipodedocumento, String fechahorafindocumento,
			String observacionfindocumento, String expedienteiddoc, String remitente) {
		super();
		this.anexos = anexos;
		this.asunto = asunto;
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
		this.fechahoradeldocumento = fechahoradeldocumento;
		this.fechahorarecepcion = fechahorarecepcion;
		this.movimientoss = movimientoss;
		this.noexpediente = noexpediente;
		this.numerodedocumento = numerodedocumento;
		this.numerodefolios = numerodefolios;
		this.situacionactual = situacionactual;
		this.tipodedocumento = tipodedocumento;
		this.fechahorafindocumento = fechahorafindocumento;
		this.observacionfindocumento = observacionfindocumento;
		this.expedienteiddoc = expedienteiddoc;
		this.remitente = remitente;
	}

	public ExpedienteJS(String anexos, String asunto, String fechahoradeiniciodetramite, String fechahoradeldocumento,
			String fechahorarecepcion, List<MovimientoJS> movimientoss, String noexpediente, String numerodedocumento,
			String numerodefolios, String situacionactual, String tipodedocumento, String fechahorafindocumento,
			String observacionfindocumento, String expedienteiddoc, String remitente, String idperson,
			String idprovee) {
		super();
		this.anexos = anexos;
		this.asunto = asunto;
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
		this.fechahoradeldocumento = fechahoradeldocumento;
		this.fechahorarecepcion = fechahorarecepcion;
		this.movimientoss = movimientoss;
		this.noexpediente = noexpediente;
		this.numerodedocumento = numerodedocumento;
		this.numerodefolios = numerodefolios;
		this.situacionactual = situacionactual;
		this.tipodedocumento = tipodedocumento;
		this.fechahorafindocumento = fechahorafindocumento;
		this.observacionfindocumento = observacionfindocumento;
		this.expedienteiddoc = expedienteiddoc;
		this.remitente = remitente;
		this.idperson = idperson;
		this.idprovee = idprovee;
	}

	public ExpedienteJS(String anexos, String asunto, String fechahoradeiniciodetramite, String fechahoradeldocumento,
			String fechahorarecepcion, List<MovimientoJS> movimientoss, String noexpediente, String numerodedocumento,
			String numerodefolios, String situacionactual, String tipodedocumento, String fechahorafindocumento,
			String observacionfindocumento, String expedienteiddoc, String remitente, String idperson, String idprovee,
			String numeroSid, String numeroAnioSid) {
		super();
		this.anexos = anexos;
		this.asunto = asunto;
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
		this.fechahoradeldocumento = fechahoradeldocumento;
		this.fechahorarecepcion = fechahorarecepcion;
		this.movimientoss = movimientoss;
		this.noexpediente = noexpediente;
		this.numerodedocumento = numerodedocumento;
		this.numerodefolios = numerodefolios;
		this.situacionactual = situacionactual;
		this.tipodedocumento = tipodedocumento;
		this.fechahorafindocumento = fechahorafindocumento;
		this.observacionfindocumento = observacionfindocumento;
		this.expedienteiddoc = expedienteiddoc;
		this.remitente = remitente;
		this.idperson = idperson;
		this.idprovee = idprovee;
		this.numeroSid = numeroSid;
		this.numeroAnioSid = numeroAnioSid;
	}

	// MPINARES 01092023 - FIN

	/**
	 * Gets the anexos value for this ExpedienteWSDto.
	 * 
	 * @return anexos
	 */
	public java.lang.String getAnexos() {
		return anexos;
	}

	/**
	 * Sets the anexos value for this ExpedienteWSDto.
	 * 
	 * @param anexos
	 */
	public void setAnexos(java.lang.String anexos) {
		this.anexos = anexos;
	}

	/**
	 * Gets the asunto value for this ExpedienteWSDto.
	 * 
	 * @return asunto
	 */
	public java.lang.String getAsunto() {
		return asunto;
	}

	/**
	 * Sets the asunto value for this ExpedienteWSDto.
	 * 
	 * @param asunto
	 */
	public void setAsunto(java.lang.String asunto) {
		this.asunto = asunto;
	}

	/**
	 * Gets the fechahoradeiniciodetramite value for this ExpedienteWSDto.
	 * 
	 * @return fechahoradeiniciodetramite
	 */
	public java.lang.String getFechahoradeiniciodetramite() {
		return fechahoradeiniciodetramite;
	}

	/**
	 * Sets the fechahoradeiniciodetramite value for this ExpedienteWSDto.
	 * 
	 * @param fechahoradeiniciodetramite
	 */
	public void setFechahoradeiniciodetramite(java.lang.String fechahoradeiniciodetramite) {
		this.fechahoradeiniciodetramite = fechahoradeiniciodetramite;
	}

	/**
	 * Gets the fechahoradeldocumento value for this ExpedienteWSDto.
	 * 
	 * @return fechahoradeldocumento
	 */
	public java.lang.String getFechahoradeldocumento() {
		return fechahoradeldocumento;
	}

	/**
	 * Sets the fechahoradeldocumento value for this ExpedienteWSDto.
	 * 
	 * @param fechahoradeldocumento
	 */
	public void setFechahoradeldocumento(java.lang.String fechahoradeldocumento) {
		this.fechahoradeldocumento = fechahoradeldocumento;
	}

	/**
	 * Gets the fechahorarecepcion value for this ExpedienteWSDto.
	 * 
	 * @return fechahorarecepcion
	 */
	public java.lang.String getFechahorarecepcion() {
		return fechahorarecepcion;
	}

	/**
	 * Sets the fechahorarecepcion value for this ExpedienteWSDto.
	 * 
	 * @param fechahorarecepcion
	 */
	public void setFechahorarecepcion(java.lang.String fechahorarecepcion) {
		this.fechahorarecepcion = fechahorarecepcion;
	}

	/**
	 * Gets the noexpediente value for this ExpedienteWSDto.
	 * 
	 * @return noexpediente
	 */
	public java.lang.String getNoexpediente() {
		return noexpediente;
	}

	/**
	 * Sets the noexpediente value for this ExpedienteWSDto.
	 * 
	 * @param noexpediente
	 */
	public void setNoexpediente(java.lang.String noexpediente) {
		this.noexpediente = noexpediente;
	}

	/**
	 * Gets the numerodedocumento value for this ExpedienteWSDto.
	 * 
	 * @return numerodedocumento
	 */
	public java.lang.String getNumerodedocumento() {
		return numerodedocumento;
	}

	/**
	 * Sets the numerodedocumento value for this ExpedienteWSDto.
	 * 
	 * @param numerodedocumento
	 */
	public void setNumerodedocumento(java.lang.String numerodedocumento) {
		this.numerodedocumento = numerodedocumento;
	}

	/**
	 * Gets the numerodefolios value for this ExpedienteWSDto.
	 * 
	 * @return numerodefolios
	 */
	public java.lang.String getNumerodefolios() {
		return numerodefolios;
	}

	/**
	 * Sets the numerodefolios value for this ExpedienteWSDto.
	 * 
	 * @param numerodefolios
	 */
	public void setNumerodefolios(java.lang.String numerodefolios) {
		this.numerodefolios = numerodefolios;
	}

	/**
	 * Gets the situacionactual value for this ExpedienteWSDto.
	 * 
	 * @return situacionactual
	 */
	public java.lang.String getSituacionactual() {
		return situacionactual;
	}

	/**
	 * Sets the situacionactual value for this ExpedienteWSDto.
	 * 
	 * @param situacionactual
	 */
	public void setSituacionactual(java.lang.String situacionactual) {
		this.situacionactual = situacionactual;
	}

	/**
	 * Gets the tipodedocumento value for this ExpedienteWSDto.
	 * 
	 * @return tipodedocumento
	 */
	public java.lang.String getTipodedocumento() {
		return tipodedocumento;
	}

	/**
	 * Sets the tipodedocumento value for this ExpedienteWSDto.
	 * 
	 * @param tipodedocumento
	 */
	public void setTipodedocumento(java.lang.String tipodedocumento) {
		this.tipodedocumento = tipodedocumento;
	}

	public List<MovimientoJS> getMovimientoss() {
		return movimientoss;
	}

	public void setMovimientoss(List<MovimientoJS> movimientoss) {
		this.movimientoss = movimientoss;
	}

	// MPINARES 27042022 - INICIO
	public java.lang.String getFechahorafindocumento() {
		return fechahorafindocumento;
	}

	public void setFechahorafindocumento(java.lang.String fechahorafindocumento) {
		this.fechahorafindocumento = fechahorafindocumento;
	}

	public java.lang.String getObservacionfindocumento() {
		return observacionfindocumento;
	}

	public void setObservacionfindocumento(java.lang.String observacionfindocumento) {
		this.observacionfindocumento = observacionfindocumento;
	}

	public java.lang.String getExpedienteiddoc() {
		return expedienteiddoc;
	}

	public void setExpedienteiddoc(java.lang.String expedienteiddoc) {
		this.expedienteiddoc = expedienteiddoc;
	}
	// MPINARES 27042022 - FIN

	// MPINARES 01092023 - INICIO
	public java.lang.String getRemitente() {
		return remitente;
	}

	public void setRemitente(java.lang.String remitente) {
		this.remitente = remitente;
	}

	public java.lang.String getIdperson() {
		return idperson;
	}

	public void setIdperson(java.lang.String idperson) {
		this.idperson = idperson;
	}

	public java.lang.String getIdprovee() {
		return idprovee;
	}

	public void setIdprovee(java.lang.String idprovee) {
		this.idprovee = idprovee;
	}

	public java.lang.String getNumeroSid() {
		return numeroSid;
	}

	public void setNumeroSid(java.lang.String numeroSid) {
		this.numeroSid = numeroSid;
	}

	public java.lang.String getNumeroAnioSid() {
		return numeroAnioSid;
	}

	public void setNumeroAnioSid(java.lang.String numeroAnioSid) {
		this.numeroAnioSid = numeroAnioSid;
	}
	// MPINARES 01092023 - FIN
}
