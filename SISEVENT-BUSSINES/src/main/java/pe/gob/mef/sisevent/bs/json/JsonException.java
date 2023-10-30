/**
 * 
 */
package pe.gob.mef.sisevent.bs.json;

/**
 * Clase para presentar los errores de validacion
 * 
 * @author  Carlos Aguilar
 * @version 2.0, 11/01/2012  
 * 
 * 
 *  /----------Nombre----------/----fecha----/-------------Motivo--------------/
 *   Carlos Aguilar Chamochumbi	11/01/2012	  Creación de la clase
 *  
 *  
 */
public class JsonException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4903924374495789768L;
	private int severidad = 2; //0 info, 1 warning, 2 error, 3 fatal 
	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	public static final int FATAL = 3;
	
	private int cod = 0;
	

	public JsonException(int severidad, int cod,String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.severidad = severidad;
		this.cod = cod;
	}
	
	/**
	 * @param arg0
	 */
	public JsonException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public JsonException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JsonException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public int getSeveridad() {
		return severidad;
	}

	public void setSeveridad(int severidad) {
		this.severidad = severidad;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}	
	
}
