package pe.gob.mef.sisevent.bs.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NodosDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4677107352658446408L;
	
	private Long id = null;
	private String name = null;
	private List<NodosDto> children = new ArrayList<NodosDto>();
	
	public NodosDto() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NodosDto> getChildren() {
		return children;
	}
	public void setChildren(List<NodosDto> children) {
		this.children = children;
	}
	public void addChildren(NodosDto children) {
		this.children.add(children);
	}	
}
