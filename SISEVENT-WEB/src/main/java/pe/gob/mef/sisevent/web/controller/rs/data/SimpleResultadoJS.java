package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleResultadoJS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5505890240906810014L;
	private String resultado = null;
	
	public SimpleResultadoJS(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}	
}
