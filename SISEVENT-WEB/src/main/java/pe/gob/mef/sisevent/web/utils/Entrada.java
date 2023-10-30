package pe.gob.mef.sisevent.web.utils;

import java.io.Serializable;
import java.util.List;

public class Entrada implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7874526030440729806L;
	private List<?> lista = null; 
	private long tiempomuerto = -1;
	private long ultimoacceso = -1;
	
	public Entrada() {
	}

	public long getTiempomuerto() {
		return tiempomuerto;
	}

	public void setTiempomuerto(long tiempomuerto) {
		this.tiempomuerto = tiempomuerto;
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	public long getUltimoacceso() {
		return ultimoacceso;
	}

	public void setUltimoacceso(long ultimoacceso) {
		this.ultimoacceso = ultimoacceso;
	}	

}
