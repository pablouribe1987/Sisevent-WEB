package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;

/**
 * TD_FLUJO SERVICIO VALIDACIÓN: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 23:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          22/12/2020 23:11 / Creación de la clase /
 * 
 */
public class ValidacionTdFlujoMng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6484642957433137972L;
	public static final Logger log = Logger.getLogger(ValidacionTdFlujoMng.class.getName());

	public static void validarTdFlujoBk(TdFlujoBk tdFlujoBk) throws Validador {
		// FORANEAS
		//vbaldeon 25092023 inicio
		/*if (tdFlujoBk.getIdsacc() != null && tdFlujoBk.getIdsacc().longValue() <= 0) {
			tdFlujoBk.setIdsacc(null);
		}*/
		//vbaldeon 25092023 inicio
		if (tdFlujoBk.getIduserCrea() != null && tdFlujoBk.getIduserCrea().longValue() <= 0) {
			tdFlujoBk.setIduserCrea(null);
		}
		if (tdFlujoBk.getIduserModif() != null && tdFlujoBk.getIduserModif().longValue() <= 0) {
			tdFlujoBk.setIduserModif(null);
		}

		// VALIDANDO
		// validarIdflujopadre(tdFlujoBk.getIdflujopadre());

		//validarIdsacc(tdFlujoBk.getIdsacc());//vbaldeon 25092023

		validarFechaDerivacion(tdFlujoBk.getFechaDerivacion());

		validarIdunidadDeriva(tdFlujoBk.getIdunidadDeriva());

		validarIduserDeriva(tdFlujoBk.getIduserDeriva());

		// validarFechaRecepcion(tdFlujoBk.getFechaRecepcion());

		// validarIdunidadRecepciona(tdFlujoBk.getIdunidadRecepciona());

		// validarIduserRecepciona(tdFlujoBk.getIduserRecepciona());

		// validarIdunidadDestino(tdFlujoBk.getIdunidadDestino());

		// validarIduserDestino(tdFlujoBk.getIduserDestino());

		// validarIdunidadAtiende(tdFlujoBk.getIdunidadAtiende());

		// validarIduserAtiende(tdFlujoBk.getIduserAtiende());

		// validarFechaAtencion(tdFlujoBk.getFechaAtencion());

		// validarObservacion(tdFlujoBk.getObservacion());
		if (tdFlujoBk.getObservacion() != null) {
			if (tdFlujoBk.getObservacion().trim().length() > 4000)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.noexceder"),
						Messages.getStringToKey("tdFlujo.observacion"), Messages.getStringToKey("tdFlujo.titulotabla"),
						4000, Messages.getStringToKey("tdFlujo.articuloObservacion")));
			tdFlujoBk.setObservacion(tdFlujoBk.getObservacion().toUpperCase());
		}

		validarTiempoestadia(tdFlujoBk.getTiempoestadia());

		validarEstado(tdFlujoBk.getEstado());
	}

	public static void validarIdflujopadre(Long idflujopadre) throws Validador {
		if (idflujopadre == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idflujopadre"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdflujopadre")));
		if (idflujopadre.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idflujopadre"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdflujopadre")));
	}

	public static void validarIdsacc(Long idsacc) throws Validador {
		if (idsacc == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idsacc"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdsacc")));
		if (idsacc.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idsacc"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdsacc")));
	}

	public static void validarFechaDerivacion(Timestamp fechaDerivacion) throws Validador {
		if (fechaDerivacion == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoingrese"),
					Messages.getStringToKey("tdFlujo.fechaDerivacion"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloFechaDerivacion")));
	}

	public static void validarIdunidadDeriva(Long idunidadDeriva) throws Validador {
		if (idunidadDeriva == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idunidadDeriva"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadDeriva")));
		if (idunidadDeriva.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idunidadDeriva"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadDeriva")));
	}

	public static void validarIduserDeriva(Long iduserDeriva) throws Validador {
		if (iduserDeriva == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.iduserDeriva"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserDeriva")));
		if (iduserDeriva.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.iduserDeriva"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserDeriva")));
	}

	public static void validarFechaRecepcion(Timestamp fechaRecepcion) throws Validador {
		if (fechaRecepcion == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoingrese"),
					Messages.getStringToKey("tdFlujo.fechaRecepcion"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloFechaRecepcion")));
	}

	public static void validarIdunidadRecepciona(Long idunidadRecepciona) throws Validador {
		if (idunidadRecepciona == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idunidadRecepciona"),
					Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadRecepciona")));
		if (idunidadRecepciona.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idunidadRecepciona"),
					Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadRecepciona")));
	}

	public static void validarIduserRecepciona(Long iduserRecepciona) throws Validador {
		if (iduserRecepciona == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.iduserRecepciona"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserRecepciona")));
		if (iduserRecepciona.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.iduserRecepciona"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserRecepciona")));
	}

	public static void validarIdunidadDestino(Long idunidadDestino) throws Validador {
		if (idunidadDestino == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idunidadDestino"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadDestino")));
		if (idunidadDestino.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idunidadDestino"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadDestino")));
	}

	public static void validarIduserDestino(Long iduserDestino) throws Validador {
		if (iduserDestino == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.iduserDestino"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserDestino")));
		if (iduserDestino.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.iduserDestino"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserDestino")));
	}

	public static void validarIdunidadAtiende(Long idunidadAtiende) throws Validador {
		if (idunidadAtiende == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.idunidadAtiende"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadAtiende")));
		if (idunidadAtiende.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.idunidadAtiende"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIdunidadAtiende")));
	}

	public static void validarIduserAtiende(Long iduserAtiende) throws Validador {
		if (iduserAtiende == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.iduserAtiende"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserAtiende")));
		if (iduserAtiende.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.iduserAtiende"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloIduserAtiende")));
	}

	public static void validarFechaAtencion(Timestamp fechaAtencion) throws Validador {
		if (fechaAtencion == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoingrese"),
					Messages.getStringToKey("tdFlujo.fechaAtencion"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloFechaAtencion")));
	}

	public static void validarObservacion(String observacion) throws Validador {
		if (observacion == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.ingrese"),
					Messages.getStringToKey("tdFlujo.observacion"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloObservacion")));
		if (observacion.trim().length() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoingrese"),
					Messages.getStringToKey("tdFlujo.observacion"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloObservacion")));
		if (observacion != null) {
			if (observacion.trim().length() > 4000)
				throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.noexceder"),
						Messages.getStringToKey("tdFlujo.observacion"), Messages.getStringToKey("tdFlujo.titulotabla"),
						4000, Messages.getStringToKey("tdFlujo.articuloObservacion")));
		}
	}

	public static void validarTiempoestadia(Integer tiempoestadia) throws Validador {
		if (tiempoestadia == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.tiempoestadia"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloTiempoestadia")));
		if (tiempoestadia.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.tiempoestadia"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloTiempoestadia")));
	}

	public static void validarEstado(Integer estado) throws Validador {
		if (estado == null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.seleccione"),
					Messages.getStringToKey("tdFlujo.estado"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloEstado")));
		if (estado.longValue() <= 0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdFlujo.invalidoseleccione"),
					Messages.getStringToKey("tdFlujo.estado"), Messages.getStringToKey("tdFlujo.titulotabla"),
					Messages.getStringToKey("tdFlujo.articuloEstado")));
	}

}