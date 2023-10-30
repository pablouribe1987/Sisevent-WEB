package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;

/**
 * TD_ANEXOS SERVICIO VALIDACIÓN: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 07/01/2021 06:00
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          07/01/2021 06:00 / Creación de la clase /
 * 
 */
public class ValidacionTdAnexosMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1649066081153222221L;
	public static final Logger log = Logger.getLogger(ValidacionTdAnexosMng.class.getName());

	public static void validarTdAnexosBk(TdAnexosBk tdAnexosBk) throws Validador {
		// FORANEAS
		if (tdAnexosBk.getIdsacc() != null && tdAnexosBk.getIdsacc().longValue() <= 0) {
			tdAnexosBk.setIdsacc(null);
		}
		if (tdAnexosBk.getIduserCrea() != null && tdAnexosBk.getIduserCrea().longValue() <= 0) {
			tdAnexosBk.setIduserCrea(null);
		}
		if (tdAnexosBk.getIduserModif() != null && tdAnexosBk.getIduserModif().longValue() <= 0) {
			tdAnexosBk.setIduserModif(null);
		}

		// VALIDANDO
		validarIdsacc(tdAnexosBk.getIdsacc());

		validarFilename(tdAnexosBk.getFilename());
		if (tdAnexosBk.getFilename() != null) {
			if (tdAnexosBk.getFilename().trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.filename"), Messages.getStringToKey("tdAnexos.titulotabla"),
						255, Messages.getStringToKey("tdAnexos.articuloFilename")));
//			tdAnexosBk.setFilename(tdAnexosBk.getFilename().toUpperCase());
		}

		validarFilenameoriginal(tdAnexosBk.getFilenameoriginal());
		if (tdAnexosBk.getFilenameoriginal() != null) {
			if (tdAnexosBk.getFilenameoriginal().trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.filenameoriginal"),
						Messages.getStringToKey("tdAnexos.titulotabla"), 255,
						Messages.getStringToKey("tdAnexos.articuloFilenameoriginal")));
//			tdAnexosBk.setFilenameoriginal(tdAnexosBk.getFilenameoriginal().toUpperCase());
		}

		validarLastmodified(tdAnexosBk.getLastmodified());

		validarTamanio(tdAnexosBk.getTamanio());

		// validarTipo(tdAnexosBk.getTipo());
		if (tdAnexosBk.getTipo() != null) {
			if (tdAnexosBk.getTipo().trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.tipo"), Messages.getStringToKey("tdAnexos.titulotabla"), 100,
						Messages.getStringToKey("tdAnexos.articuloTipo")));
//			tdAnexosBk.setTipo(tdAnexosBk.getTipo().toUpperCase());
		}

		validarIdflujo(tdAnexosBk.getIdflujo());

	}

	public static void validarIdsacc(Long idsacc) throws Validador {
		if (idsacc == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.seleccione"),
					Messages.getStringToKey("tdAnexos.idsacc"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloIdsacc")));
		if (idsacc.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoseleccione"),
					Messages.getStringToKey("tdAnexos.idsacc"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloIdsacc")));
	}

	public static void validarFilename(String filename) throws Validador {
		if (filename == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.ingrese"),
					Messages.getStringToKey("tdAnexos.filename"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloFilename")));
		if (filename.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoingrese"),
					Messages.getStringToKey("tdAnexos.filename"), Messages.getStringToKey("tdAnexos.titulotabla")));
		if (filename != null) {
			if (filename.trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.filename"), Messages.getStringToKey("tdAnexos.titulotabla"),
						255, Messages.getStringToKey("tdAnexos.articuloFilename")));
		}
	}

	public static void validarFilenameoriginal(String filenameoriginal) throws Validador {
		if (filenameoriginal == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.ingrese"),
					Messages.getStringToKey("tdAnexos.filenameoriginal"),
					Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloFilenameoriginal")));
		if (filenameoriginal.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoingrese"),
					Messages.getStringToKey("tdAnexos.filenameoriginal"),
					Messages.getStringToKey("tdAnexos.titulotabla")));
		if (filenameoriginal != null) {
			if (filenameoriginal.trim().length() > 255)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.filenameoriginal"),
						Messages.getStringToKey("tdAnexos.titulotabla"), 255,
						Messages.getStringToKey("tdAnexos.articuloFilenameoriginal")));
		}
	}

	public static void validarLastmodified(Long lastmodified) throws Validador {
		if (lastmodified == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.seleccione"),
					Messages.getStringToKey("tdAnexos.lastmodified"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloLastmodified")));
		if (lastmodified.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoseleccione"),
					Messages.getStringToKey("tdAnexos.lastmodified"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloLastmodified")));
	}

	public static void validarTamanio(Long tamanio) throws Validador {
		if (tamanio == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.seleccione"),
					Messages.getStringToKey("tdAnexos.tamanio"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloTamanio")));
		if (tamanio.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoseleccione"),
					Messages.getStringToKey("tdAnexos.tamanio"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloTamanio")));
	}

	public static void validarTipo(String tipo) throws Validador {
		if (tipo == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.ingrese"),
					Messages.getStringToKey("tdAnexos.tipo"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloTipo")));
		if (tipo.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoingrese"),
					Messages.getStringToKey("tdAnexos.tipo"), Messages.getStringToKey("tdAnexos.titulotabla")));
		if (tipo != null) {
			if (tipo.trim().length() > 100)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.noexceder"),
						Messages.getStringToKey("tdAnexos.tipo"), Messages.getStringToKey("tdAnexos.titulotabla"), 100,
						Messages.getStringToKey("tdAnexos.articuloTipo")));
		}
	}

	public static void validarIdflujo(Long idflujo) throws Validador {
		if (idflujo == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.seleccione"),
					Messages.getStringToKey("tdAnexos.idflujo"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloIdflujo")));
		if (idflujo.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdAnexos.invalidoseleccione"),
					Messages.getStringToKey("tdAnexos.idflujo"), Messages.getStringToKey("tdAnexos.titulotabla"),
					Messages.getStringToKey("tdAnexos.articuloIdflujo")));
	}

}