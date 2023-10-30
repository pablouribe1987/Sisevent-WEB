package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdFeriadosFiltro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1509978409203338691L;
	private String fechaFeriado = null;
	private String descricion = null;
	private Integer estado = null;

	public TdFeriadosFiltro(String fechaFeriado, String descricion, Integer estado) {
		this.fechaFeriado = fechaFeriado;
		this.descricion = descricion;
		this.estado = estado;
	}

	public String getFechaFeriado() {
		return this.fechaFeriado;
	}

	public void setFechaFeriado(String fechaFeriado) {
		this.fechaFeriado = fechaFeriado;
	}

	public String getDescricion() {
		return this.descricion;
	}

	public void setDescricion(String descricion) {
		this.descricion = descricion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		if (fechaFeriado != null && fechaFeriado.trim().length() > 0)
			return true;
		if (descricion != null && descricion.trim().length() > 0)
			return true;
		if(estado!=null && estado.intValue()>-1) return true;

		return false;
	}

}
