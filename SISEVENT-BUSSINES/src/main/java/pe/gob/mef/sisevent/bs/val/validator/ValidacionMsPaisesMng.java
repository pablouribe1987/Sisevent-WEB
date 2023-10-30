package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPaisesBk;

/**
 * MS_PAISES SERVICIO VALIDACIÓN: PAISES SEGÚN ISO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class ValidacionMsPaisesMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -98883403248409057L;
	public static final Logger log = Logger.getLogger(ValidacionMsPaisesMng.class.getName());

	public static void validarMsPaisesBk(MsPaisesBk msPaisesBk) throws Validador {
		// FORANEAS
		
		// VALIDANDO
//		validarPaiIsonum(msPaisesBk.getPaiIsonum());

//		validarPaiIso2(msPaisesBk.getPaiIso2());
		if (msPaisesBk.getPaiIso2() != null) {
			if (msPaisesBk.getPaiIso2().trim().length() > 5)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiIso2"), Messages.getStringToKey("msPaises.titulotabla"), 5,
						Messages.getStringToKey("msPaises.articuloPaiIso2")));
			msPaisesBk.setPaiIso2(msPaisesBk.getPaiIso2().toUpperCase());
		}

//		validarPaiIso3(msPaisesBk.getPaiIso3());
		if (msPaisesBk.getPaiIso3() != null) {
			if (msPaisesBk.getPaiIso3().trim().length() > 5)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiIso3"), Messages.getStringToKey("msPaises.titulotabla"), 5,
						Messages.getStringToKey("msPaises.articuloPaiIso3")));
			msPaisesBk.setPaiIso3(msPaisesBk.getPaiIso3().toUpperCase());
		}

		validarPaiNombre(msPaisesBk.getPaiNombre());
		if (msPaisesBk.getPaiNombre() != null) {
			if (msPaisesBk.getPaiNombre().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiNombre"), Messages.getStringToKey("msPaises.titulotabla"),
						100, Messages.getStringToKey("msPaises.articuloPaiNombre")));
			msPaisesBk.setPaiNombre(msPaisesBk.getPaiNombre().toUpperCase());
		}

	}

	public static void validarPaiIsonum(Long paiIsonum) throws Validador {
		if (paiIsonum == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.seleccione"),
					Messages.getStringToKey("msPaises.paiIsonum"), Messages.getStringToKey("msPaises.titulotabla"),
					Messages.getStringToKey("msPaises.articuloPaiIsonum")));
		if (paiIsonum.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.invalidoseleccione"),
					Messages.getStringToKey("msPaises.paiIsonum"), Messages.getStringToKey("msPaises.titulotabla"),
					Messages.getStringToKey("msPaises.articuloPaiIsonum")));
	}

	public static void validarPaiIso2(String paiIso2) throws Validador {
		if (paiIso2 == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.ingrese"),
					Messages.getStringToKey("msPaises.paiIso2"), Messages.getStringToKey("msPaises.titulotabla"),
					Messages.getStringToKey("msPaises.articuloPaiIso2")));
		if (paiIso2.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.invalidoingrese"),
					Messages.getStringToKey("msPaises.paiIso2"), Messages.getStringToKey("msPaises.titulotabla")));
		if (paiIso2 != null) {
			if (paiIso2.trim().length() > 5)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiIso2"), Messages.getStringToKey("msPaises.titulotabla"), 5,
						Messages.getStringToKey("msPaises.articuloPaiIso2")));
		}
	}

	public static void validarPaiIso3(String paiIso3) throws Validador {
		if (paiIso3 == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.ingrese"),
					Messages.getStringToKey("msPaises.paiIso3"), Messages.getStringToKey("msPaises.titulotabla"),
					Messages.getStringToKey("msPaises.articuloPaiIso3")));
		if (paiIso3.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.invalidoingrese"),
					Messages.getStringToKey("msPaises.paiIso3"), Messages.getStringToKey("msPaises.titulotabla")));
		if (paiIso3 != null) {
			if (paiIso3.trim().length() > 5)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiIso3"), Messages.getStringToKey("msPaises.titulotabla"), 5,
						Messages.getStringToKey("msPaises.articuloPaiIso3")));
		}
	}

	public static void validarPaiNombre(String paiNombre) throws Validador {
		if (paiNombre == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.ingrese"),
					Messages.getStringToKey("msPaises.paiNombre"), Messages.getStringToKey("msPaises.titulotabla"),
					Messages.getStringToKey("msPaises.articuloPaiNombre")));
		if (paiNombre.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.invalidoingrese"),
					Messages.getStringToKey("msPaises.paiNombre"), Messages.getStringToKey("msPaises.titulotabla")));
		if (paiNombre != null) {
			if (paiNombre.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("msPaises.noexceder"),
						Messages.getStringToKey("msPaises.paiNombre"), Messages.getStringToKey("msPaises.titulotabla"),
						100, Messages.getStringToKey("msPaises.articuloPaiNombre")));
		}
	}

}