package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUbigeoBk;

/**
 * MS_UBIGEO SERVICIO VALIDACIÓN: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          23/12/2020 11:52 / Creación de la clase /
 * 
 */
public class ValidacionMsUbigeoMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8127618658985807023L;
	public static final Logger log = Logger.getLogger(ValidacionMsUbigeoMng.class.getName());

	public static void validarMsUbigeoBk(MsUbigeoBk msUbigeoBk) throws Validador {
		// FORANEAS
		
		if (msUbigeoBk.getIduserCrea() != null && msUbigeoBk.getIduserCrea().longValue() <= 0) {
			msUbigeoBk.setIduserCrea(null);
		}
		if (msUbigeoBk.getIduserModif() != null && msUbigeoBk.getIduserModif().longValue() <= 0) {
			msUbigeoBk.setIduserModif(null);
		}
		
		if(msUbigeoBk.getId()!=null){
			if(msUbigeoBk.getId().getCoddist()==null){
				msUbigeoBk.getId().setCoddist(0);
			}
			if(msUbigeoBk.getId().getCodprov()==null){
				msUbigeoBk.getId().setCodprov(0);
			}
			if(msUbigeoBk.getId().getCoddpto()==null){
				msUbigeoBk.getId().setCoddpto(0);
			}				
		}		

		// VALIDANDO

		validarNombre(msUbigeoBk.getNombre());
		if (msUbigeoBk.getNombre() != null) {
			if (msUbigeoBk.getNombre().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msUbigeo.noexceder"),
						Messages.getStringToKey("msUbigeo.nombre"), Messages.getStringToKey("msUbigeo.titulotabla"),
						100, Messages.getStringToKey("msUbigeo.articuloNombre")));
			msUbigeoBk.setNombre(msUbigeoBk.getNombre().toUpperCase());
		}

	}

	public static void validarNombre(String nombre) throws Validador {
		if (nombre == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msUbigeo.ingrese"),
					Messages.getStringToKey("msUbigeo.nombre"), Messages.getStringToKey("msUbigeo.titulotabla"),
					Messages.getStringToKey("msUbigeo.articuloNombre")));
		if (nombre.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msUbigeo.invalidoingrese"),
					Messages.getStringToKey("msUbigeo.nombre"), Messages.getStringToKey("msUbigeo.titulotabla")));
		if (nombre != null) {
			if (nombre.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msUbigeo.noexceder"),
						Messages.getStringToKey("msUbigeo.nombre"), Messages.getStringToKey("msUbigeo.titulotabla"),
						100, Messages.getStringToKey("msUbigeo.articuloNombre")));
		}
	}

}