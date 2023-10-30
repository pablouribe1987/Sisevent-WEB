package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFeriadosBk;

/**
 * TD_FERIADOS SERVICIO VALIDACIÓN: DÍAS NO LABORABLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2021 02:31
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          11/01/2021 02:31 / Creación de la clase /
 * 
 */
public class ValidacionTdFeriadosMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3497878032196754615L;
	public static final Logger log = Logger.getLogger(ValidacionTdFeriadosMng.class.getName());

	public static void validarTdFeriadosBk(TdFeriadosBk tdFeriadosBk) throws Validador {
		// FORANEAS
		if (tdFeriadosBk.getIduserCrea() != null && tdFeriadosBk.getIduserCrea().longValue() <= 0) {
			tdFeriadosBk.setIduserCrea(null);
		}
		if (tdFeriadosBk.getIduserModif() != null && tdFeriadosBk.getIduserModif().longValue() <= 0) {
			tdFeriadosBk.setIduserModif(null);
		}

		// VALIDANDO
		validarFechaFeriado(tdFeriadosBk.getFechaFeriado());

		// validarDescricion(tdFeriadosBk.getDescricion());
		if (tdFeriadosBk.getDescricion() != null) {
			if (tdFeriadosBk.getDescricion().trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFeriados.noexceder"),
						Messages.getStringToKey("tdFeriados.descricion"),
						Messages.getStringToKey("tdFeriados.titulotabla"), 255,
						Messages.getStringToKey("tdFeriados.articuloDescricion")));
			tdFeriadosBk.setDescricion(tdFeriadosBk.getDescricion().toUpperCase());
		}

	}

	public static void validarFechaFeriado(Timestamp fechaFeriado) throws Validador {
		if (fechaFeriado == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFeriados.invalidoingrese"),
					Messages.getStringToKey("tdFeriados.fechaFeriado"),
					Messages.getStringToKey("tdFeriados.titulotabla"),
					Messages.getStringToKey("tdFeriados.articuloFechaFeriado")));
	}

	public static void validarDescricion(String descricion) throws Validador {
		if (descricion == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFeriados.ingrese"),
					Messages.getStringToKey("tdFeriados.descricion"), Messages.getStringToKey("tdFeriados.titulotabla"),
					Messages.getStringToKey("tdFeriados.articuloDescricion")));
		if (descricion.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFeriados.invalidoingrese"),
					Messages.getStringToKey("tdFeriados.descricion"), Messages.getStringToKey("tdFeriados.titulotabla"),
					Messages.getStringToKey("tdFeriados.articuloDescricion")));
		if (descricion != null) {
			if (descricion.trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFeriados.noexceder"),
						Messages.getStringToKey("tdFeriados.descricion"),
						Messages.getStringToKey("tdFeriados.titulotabla"), 255,
						Messages.getStringToKey("tdFeriados.articuloDescricion")));
		}
	}

}