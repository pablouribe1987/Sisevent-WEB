package pe.gob.mef.sisevent.bs.resources; 
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase para el manejo los messages mapeados en el properties
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2012
 * 
 * 
 *          /----------Nombre-----------/----fecha----/-------------Motivo------
 *           Carlos Aguilar Chamochumbi   11/01/2012       Manejo de textos
 * 
 * 
 */

public class Messages extends ResourceBundle{
	// VARIABLES DE ERROR
	private static final String msg = "pe.gob.mef.sisevent.bs.resources.Message";
	private static ResourceBundle RESOURCE_BUNDLE = null;
	
	
	public static ResourceBundle getRESOURCE_BUNDLE() {
		if(RESOURCE_BUNDLE==null){
			RESOURCE_BUNDLE = ResourceBundle.getBundle(msg);
		}
		return RESOURCE_BUNDLE;
	}
	
	public static void setRESOURCE_BUNDLE(ResourceBundle RESOURCE_BUNDLE) {
		Messages.RESOURCE_BUNDLE = RESOURCE_BUNDLE;
	}
	
	@Override
	protected Object handleGetObject(String key) {
		return getStringToKey(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		try {			
			if(RESOURCE_BUNDLE==null){
				RESOURCE_BUNDLE = ResourceBundle.getBundle(msg);
			}
			return RESOURCE_BUNDLE.getKeys();
		} catch (Exception e) {
			return parent.getKeys();
		}		
	}
	
	public static String getStringToKey(String key) {
		try {			
			if(RESOURCE_BUNDLE==null){
				RESOURCE_BUNDLE = ResourceBundle.getBundle(msg);
			}
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
}
