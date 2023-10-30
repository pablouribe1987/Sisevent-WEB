package pe.gob.mef.sisevent.web.controller.rs;

import java.io.Serializable;

public class TdAnexosFiltro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4845592088273638145L;
	private String idsacc = null;
	private String filename = null;
	private String idflujo = null;

	public TdAnexosFiltro(String idsacc, String filename, String idflujo) {
		this.idsacc = idsacc;
		this.filename = filename;
		this.idflujo = idflujo;

	}

	public String getIdsacc() {
		return this.idsacc;
	}

	public void setIdsacc(String idsacc) {
		this.idsacc = idsacc;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(String idflujo) {
		this.idflujo = idflujo;
	}

	public boolean isActivo() {
		if (idsacc != null && idsacc.trim().length() > 0)
			return true;
		if (filename != null && filename.trim().length() > 0)
			return true;
		if (idflujo != null && idflujo.trim().length() > 0)
			return true;

		return false;
	}

}
