package pe.gob.mef.sisevent.bs.transfer;

import java.io.Serializable;

public class Progreso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1700279109402842681L;
	private String iniLabel = null;
	private int iniTotal = 0;
	private int iniProgreso = 0;
	
	public Progreso() {
		// TODO Auto-generated constructor stub
	}

	public String getIniLabel() {
		return iniLabel;
	}

	public void setIniLabel(String iniLabel) {
		this.iniLabel = iniLabel;
	}

	public int getIniTotal() {
		return iniTotal;
	}

	public void setIniTotal(int iniTotal) {
		this.iniTotal = iniTotal;
	}

	public int getIniProgreso() {
		return iniProgreso;
	}

	public void setIniProgreso(int iniProgreso) {
		this.iniProgreso = iniProgreso;
	}
	
	public int getIniProgresoPorc() {
		int retorno = 0;
		if(iniTotal<=0)
			return 100;
		float d = (iniProgreso*100)/iniTotal;
		retorno = Math.round(d);
		if(retorno>100) retorno=100;
		return retorno;
	}

	public void setIniProgresoPorc(int iniProgreso) {
	}
}
