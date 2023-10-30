package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.bs.transfer.bk.MsTareasBk;

@XmlRootElement
public class MsTareasLC implements Serializable  {

	protected long contador = 0;
	private long tiempoenBD = 0;
	private long tiempoenproceso = 0;
	private boolean creamodifica = false;
	protected List<MsTareasBk> data = null;
	
	public MsTareasLC() {
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public List<MsTareasBk> getData() {
		return data;
	}

	public void setData(List<MsTareasBk> data) {
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
