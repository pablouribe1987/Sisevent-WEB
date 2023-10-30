package pe.gob.mef.sisevent.web.controller.rs.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class MantenimientoInicial implements Serializable  {

	private Integer totalDeUduariosConectados = null;
	private Long maxMemory = null;
	private Long totalMemory = null;
	private Long freeMemory = null;
	private String resultadoLiberarmemoria = null;
	
	private static final long serialVersionUID = -735326918886750456L;

	public MantenimientoInicial() {
	
	}

	public Integer getTotalDeUduariosConectados() {
		return totalDeUduariosConectados;
	}

	public void setTotalDeUduariosConectados(Integer totalDeUduariosConectados) {
		this.totalDeUduariosConectados = totalDeUduariosConectados;
	}

	
	public Long getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(Long maxMemory) {
		this.maxMemory = maxMemory;
	}

	public Long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(Long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public void setFreeMemory(Long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public String getResultadoLiberarmemoria() {
		return resultadoLiberarmemoria;
	}

	public void setResultadoLiberarmemoria(String resultadoLiberarmemoria) {
		this.resultadoLiberarmemoria = resultadoLiberarmemoria;
	}

	public String getMaximoHeapInBytes() {
		return maxMemory==null?"":maxMemory.longValue() + "";
	}

	public String getMaximoHeapInKB() {
		return maxMemory==null?"":(maxMemory.longValue() / 1024) + "";
	}

	public String getMaximoHeapInMB() {
		return maxMemory==null?"":(maxMemory.longValue() / (1024 * 1000)) + "";
	}

	public String getCurrentHeapInBytes() {
		return totalMemory==null?"":totalMemory.longValue() + "";
	}

	public String getCurrentHeapInKB() {
		return totalMemory==null?"":(totalMemory.longValue() / 1024) + "";
	}

	public String getCurrentHeapInMB() {
		return totalMemory==null?"":(totalMemory.longValue() / (1024 * 1000)) + "";
	}

	public String getFreeUnusedHeapInBytes() {
		return freeMemory==null?"":freeMemory.longValue() + "";
	}

	public String getFreeUnusedHeapInKB() {
		return freeMemory==null?"":(freeMemory.longValue() / 1024) + "";
	}

	public String getFreeUnusedHeapInMB() {
		return freeMemory==null?"":(freeMemory.longValue() / (1024 * 1000)) + "";
	}
}
