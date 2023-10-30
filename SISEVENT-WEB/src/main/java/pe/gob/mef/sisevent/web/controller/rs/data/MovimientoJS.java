/**
 * MovimientoWSDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.web.ws.sisevent.TdExpAnexosDto;

@XmlRootElement
public class MovimientoJS  implements java.io.Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8447073155101922671L;

	private java.lang.String estado = null;

    private java.lang.String fechadeestado = null;

    private java.lang.String remitente = null;

    private java.lang.String unidaddestino = null;
    
  //MPINARES 27042022 - INICIO
    private java.lang.String observacion;
    private java.lang.String observacionAtencion;
  //MPINARES 27042022 - FIN

    private pe.gob.mef.sisevent.web.ws.sisevent.TdExpAnexosDto[] anexos;//MPINARES 01092023 - INICIO
    
    public MovimientoJS() {
    }

    public MovimientoJS(
           java.lang.String estado,
           java.lang.String fechadeestado,
           java.lang.String remitente,
           java.lang.String unidaddestino) {
           this.estado = estado;
           this.fechadeestado = fechadeestado;
           this.remitente = remitente;
           this.unidaddestino = unidaddestino;
    }

  //MPINARES 27042022 - INICIO
    public MovimientoJS(String estado, String fechadeestado, String remitente, String unidaddestino, String observacion,
			String observacionAtencion) {
		super();
		this.estado = estado;
		this.fechadeestado = fechadeestado;
		this.remitente = remitente;
		this.unidaddestino = unidaddestino;
		this.observacion = observacion;
		this.observacionAtencion = observacionAtencion;
	}
  //MPINARES 27042022 - FIN

    //MPINARES 01092023 - INICIO
    public MovimientoJS(String estado, String fechadeestado, String remitente, String unidaddestino, String observacion,
		String observacionAtencion, TdExpAnexosDto[] anexos) {
	super();
	this.estado = estado;
	this.fechadeestado = fechadeestado;
	this.remitente = remitente;
	this.unidaddestino = unidaddestino;
	this.observacion = observacion;
	this.observacionAtencion = observacionAtencion;
	this.anexos = anexos;
}
  //MPINARES 01092023 - FIN
    
    /**
     * Gets the estado value for this MovimientoWSDto.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this MovimientoWSDto.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechadeestado value for this MovimientoWSDto.
     * 
     * @return fechadeestado
     */
    public java.lang.String getFechadeestado() {
        return fechadeestado;
    }


    /**
     * Sets the fechadeestado value for this MovimientoWSDto.
     * 
     * @param fechadeestado
     */
    public void setFechadeestado(java.lang.String fechadeestado) {
        this.fechadeestado = fechadeestado;
    }


    /**
     * Gets the remitente value for this MovimientoWSDto.
     * 
     * @return remitente
     */
    public java.lang.String getRemitente() {
        return remitente;
    }


    /**
     * Sets the remitente value for this MovimientoWSDto.
     * 
     * @param remitente
     */
    public void setRemitente(java.lang.String remitente) {
        this.remitente = remitente;
    }


    /**
     * Gets the unidaddestino value for this MovimientoWSDto.
     * 
     * @return unidaddestino
     */
    public java.lang.String getUnidaddestino() {
        return unidaddestino;
    }


    /**
     * Sets the unidaddestino value for this MovimientoWSDto.
     * 
     * @param unidaddestino
     */
    public void setUnidaddestino(java.lang.String unidaddestino) {
        this.unidaddestino = unidaddestino;
    }
    
  //MPINARES 27042022 - INICIO
  	public java.lang.String getObservacion() {
  		return observacion;
  	}

  	public void setObservacion(java.lang.String observacion) {
  		this.observacion = observacion;
  	}

  	public java.lang.String getObservacionAtencion() {
  		return observacionAtencion;
  	}

  	public void setObservacionAtencion(java.lang.String observacionAtencion) {
  		this.observacionAtencion = observacionAtencion;
  	}
      
    //MPINARES 27042022 - FIN
  	
  //MPINARES 01092023 - INICIO
  	public pe.gob.mef.sisevent.web.ws.sisevent.TdExpAnexosDto[] getAnexos() {
  		return anexos;
  	}

  	public void setAnexos(pe.gob.mef.sisevent.web.ws.sisevent.TdExpAnexosDto[] anexos) {
  		this.anexos = anexos;
  	}
  	//MPINARES 01092023 - FIN
}
