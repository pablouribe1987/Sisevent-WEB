package pe.gob.mef.sisevent.web.controller.rs.data;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Client error information holder.
 */
@XmlRootElement
public class ClientErrorInformation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4771175978319032780L;
	private final String requestURI;
    private final String exception;
    private final String message;

    /**
     * Instantiates a new Client error information.
     *
     * @param exception  the exception
     * @param requestURI the request uri
     */
    public ClientErrorInformation(String exception, String requestURI) {
        this.exception = exception;
        this.requestURI = requestURI;
        this.message = exception;
    }

    /**
     * Gets request uri.
     *
     * @return the request uri
     */
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public String getException() {
        return exception;
    }

	public String getMessage() {
		return message;
	}

}