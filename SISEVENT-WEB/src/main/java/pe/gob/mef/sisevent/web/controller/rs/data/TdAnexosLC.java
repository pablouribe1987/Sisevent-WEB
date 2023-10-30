package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;

@XmlRootElement
public class TdAnexosLC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132711397505784033L;
	protected long contador = 0;
	public long tiempoenBD = 0;
	public long tiempoenproceso = 0;
	protected List<TdAnexosBk> data = null;

	public TdAnexosLC() {
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public List<TdAnexosBk> getData() {
		return data;
	}

	public void setData(List<TdAnexosBk> data) {
		this.data = data;
	}

	public long getTiempoenBD() {
		return tiempoenBD;
	}

	public void setTiempoenBD(long tiempoenBD) {
		this.tiempoenBD = tiempoenBD;
	}

	public long getTiempoenproceso() {
		return tiempoenproceso;
	}

	public void setTiempoenproceso(long tiempoenproceso) {
		this.tiempoenproceso = tiempoenproceso;
	}
}
