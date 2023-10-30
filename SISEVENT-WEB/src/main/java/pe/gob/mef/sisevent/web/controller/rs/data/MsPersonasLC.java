package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.bs.transfer.bk.MsPersonasBk;

@XmlRootElement
public class MsPersonasLC implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497153185122387601L;
	protected long contador = 0;
	public long tiempoenBD = 0;
	public long tiempoenproceso = 0;
	protected List<MsPersonasBk> data = null;
	public boolean creamodifica = false;
	
	public MsPersonasLC() {
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public List<MsPersonasBk> getData() {
		return data;
	}

	public void setData(List<MsPersonasBk> data) {
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
	
	public boolean isCreamodifica() {
		return creamodifica;
	}

	public void setCreamodifica(boolean creamodifica) {
		this.creamodifica = creamodifica;
	}
}
