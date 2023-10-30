/**
 * 
 */
package pe.gob.mef.sisevent.bs.transfer;

import java.io.Serializable;

/**
 * @author CARLOS
 *
 */
public class IIDValorDto implements Serializable {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 7815294556279978279L;
	private Integer id = null;
	private String valor = null;
	
	/**
	 * 
	 */
	public IIDValorDto() {
		// TODO Auto-generated constructor stub
	}
	
	public IIDValorDto(Integer id, String valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
}
