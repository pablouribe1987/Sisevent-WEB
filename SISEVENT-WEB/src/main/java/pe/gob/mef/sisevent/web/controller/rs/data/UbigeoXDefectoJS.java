package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;

public class UbigeoXDefectoJS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7763819024775509718L;
	private Long xDefectoCodpais = null;
	private Integer xDefectoCoddpto = null;
	private Integer xDefectoCodprov = null;
	private Integer xDefectoCoddist = null;
	
	public UbigeoXDefectoJS() {
		// TODO Auto-generated constructor stub
	}
	
	

	public UbigeoXDefectoJS(Long xDefectoCodpais, Integer xDefectoCoddpto, Integer xDefectoCodprov,
			Integer xDefectoCoddist) {
		super();
		this.xDefectoCodpais = xDefectoCodpais;
		this.xDefectoCoddpto = xDefectoCoddpto;
		this.xDefectoCodprov = xDefectoCodprov;
		this.xDefectoCoddist = xDefectoCoddist;
	}



	public Long getxDefectoCodpais() {
		return xDefectoCodpais;
	}

	public void setxDefectoCodpais(Long xDefectoCodpais) {
		this.xDefectoCodpais = xDefectoCodpais;
	}

	public Integer getxDefectoCoddpto() {
		return xDefectoCoddpto;
	}

	public void setxDefectoCoddpto(Integer xDefectoCoddpto) {
		this.xDefectoCoddpto = xDefectoCoddpto;
	}

	public Integer getxDefectoCodprov() {
		return xDefectoCodprov;
	}

	public void setxDefectoCodprov(Integer xDefectoCodprov) {
		this.xDefectoCodprov = xDefectoCodprov;
	}

	public Integer getxDefectoCoddist() {
		return xDefectoCoddist;
	}

	public void setxDefectoCoddist(Integer xDefectoCoddist) {
		this.xDefectoCoddist = xDefectoCoddist;
	}

}
