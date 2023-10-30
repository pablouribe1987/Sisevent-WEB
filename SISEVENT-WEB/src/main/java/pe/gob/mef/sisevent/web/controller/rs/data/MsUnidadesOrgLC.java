package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;

@XmlRootElement
public class MsUnidadesOrgLC implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -779184516651392089L;
	protected long contador = 0;
	public long tiempoenBD = 0;
	public long tiempoenproceso = 0;
	protected List<MsUnidadesOrgBk> data = null;
	public boolean creamodifica = false;
	
	public MsUnidadesOrgLC() {
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public List<MsUnidadesOrgBk> getData() {
		return data;
	}

	public void setData(List<MsUnidadesOrgBk> data) {
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
