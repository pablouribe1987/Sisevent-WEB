/**
 * 
 */
package pe.gob.mef.sisevent.web.utils;

import java.io.Serializable;

/**
 * @author caguilar
 *
 */
public class ColumnModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1344358909855787745L;
	private String header;  
    private String property;  
    private int tipo =0;
    private String filterMatchMode = "startsWith"; //"startsWith"(default), "endsWith", "contains" and "exact".
    private String width = "50px";
    private String idColumn = null;
    private String leyenda = null;
 
	public ColumnModel(String header, String property) {  
        this.header = header;  
        this.property = property;  
    }  
	
	public ColumnModel(String header, String property, int tipo) {  
        this.header = header;  
        this.property = property;  
        this.tipo = tipo;
    }  
	
	public ColumnModel(String header, String property, int tipo, String width) {  
        this.header = header;  
        this.property = property;  
        this.tipo = tipo;
        this.width = width;
    }  

    public String getHeader() {  
        return header;  
    }  

    public String getProperty() {  
        return property;  
    }

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFilterMatchMode() {
		return filterMatchMode;
	}

	public void setFilterMatchMode(String filterMatchMode) {
		this.filterMatchMode = filterMatchMode;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setProperty(String property) {
		this.property = property;
	}	
	
	
}
