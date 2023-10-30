package pe.gob.mef.sisevent.bs.domain;

// Generated 21/03/2012 11:09:32 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MS_UBIGEO ENTIDAD: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre------------------------/-------------fecha-----------/---------------Motivo------------/
 *          /Carlos Aguilar Chamochumbi/ 23/12/2020 11:52 / Creación de la clase /
 * 
 */

@Embeddable
public class MsUbigeoId implements java.io.Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 3156000311988835567L;
	private Integer coddist;
	private Integer coddpto;
	private Integer codprov;

	public MsUbigeoId() {
	}
	
	

	public MsUbigeoId(Integer coddpto, Integer codprov, Integer coddist) {
		super();
		this.coddist = coddist;
		this.coddpto = coddpto;
		this.codprov = codprov;
	}



	@Column(name = "CODDIST", nullable = false, precision = 2, scale = 0)
	public Integer getCoddist() {
		return this.coddist;
	}

	public void setCoddist(Integer coddist) {
		this.coddist = coddist;
	}

	@Column(name = "CODDPTO", nullable = false, precision = 2, scale = 0)
	public Integer getCoddpto() {
		return this.coddpto;
	}

	public void setCoddpto(Integer coddpto) {
		this.coddpto = coddpto;
	}

	@Column(name = "CODPROV", nullable = false, precision = 2, scale = 0)
	public Integer getCodprov() {
		return this.codprov;
	}

	public void setCodprov(Integer codprov) {
		this.codprov = codprov;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coddist == null) ? 0 : coddist.hashCode());
		result = prime * result + ((coddpto == null) ? 0 : coddpto.hashCode());
		result = prime * result + ((codprov == null) ? 0 : codprov.hashCode());
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
		MsUbigeoId other = (MsUbigeoId) obj;
		if (coddist == null) {
			if (other.coddist != null)
				return false;
		} else if (!coddist.equals(other.coddist))
			return false;
		if (coddpto == null) {
			if (other.coddpto != null)
				return false;
		} else if (!coddpto.equals(other.coddpto))
			return false;
		if (codprov == null) {
			if (other.codprov != null)
				return false;
		} else if (!codprov.equals(other.codprov))
			return false;
		return true;
	}

	/**
	 * CREAR TOSTRING equals y hashCode() toString
	 *
	 */
}