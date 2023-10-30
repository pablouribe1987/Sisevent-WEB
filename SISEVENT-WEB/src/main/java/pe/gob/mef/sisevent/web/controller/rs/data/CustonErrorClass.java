package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustonErrorClass extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539648493765088802L;
	private String message;
	
	public CustonErrorClass() {
		// TODO Auto-generated constructor stub
	}

	public CustonErrorClass(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	public CustonErrorClass(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CustonErrorClass(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustonErrorClass(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
