/**
 * 
 */
package pe.gob.mef.sisevent.bs.transfer;

import java.io.Serializable;

/**
 * @author CARLOS
 *
 */
public class IDsValorDto implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -511024562387419140L;
	private String id = null;
	private String valor = null;

	public IDsValorDto(String id, String valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDsValorDto other = (IDsValorDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
