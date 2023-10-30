package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.TdFlujo;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;

/**
 * TD_FLUJO SERVICIO AUDITORIA Y CAMBIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 17:45
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi
 *          /22/12/2020 17:45 / Creación de la clase /
 * 
 */
public class AuditoriaTdFlujoMng implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8540085911723288488L;
	public static final Logger log = Logger.getLogger(AuditoriaTdFlujoMng.class.getName());

	public static boolean auditarCambiosTdFlujo(TdFlujoBk tdFlujoBk, TdFlujo tdFlujo, Long iduser, String user,
			String rmtaddress, int nivel) {
		boolean cambios = false;

		if (tdFlujoBk.getIdflujopadre() != null && tdFlujo.getIdflujopadre() != null) {
			if (!tdFlujoBk.getIdflujopadre().equals(tdFlujo.getIdflujopadre())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:Idflujopadre" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIdflujopadre() + " :: " + tdFlujoBk.getIdflujopadre());
				}
				cambios = true;
				tdFlujo.setIdflujopadre(tdFlujoBk.getIdflujopadre());
			}
		} else if (tdFlujoBk.getIdflujopadre() == null && tdFlujo.getIdflujopadre() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Idflujopadre"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdflujopadre()
								+ " :: " + tdFlujoBk.getIdflujopadre());
			}
			cambios = true;
			tdFlujo.setIdflujopadre(tdFlujoBk.getIdflujopadre());

		} else if (tdFlujoBk.getIdflujopadre() != null && tdFlujo.getIdflujopadre() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Idflujopadre"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdflujopadre()
								+ " :: " + tdFlujoBk.getIdflujopadre());
			}
			cambios = true;
			tdFlujo.setIdflujopadre(tdFlujoBk.getIdflujopadre());
		}
//		if (tdFlujoBk.getIdsacc() != null && tdFlujo.getIdsacc() != null) {
//			if (!tdFlujoBk.getIdsacc().equals(tdFlujo.getIdsacc())) {
//				if (nivel > 0) {
//					log.log(Level.INFO,
//							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Idsacc"
//									+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdsacc() + " :: "
//									+ tdFlujoBk.getIdsacc());
//				}
//				cambios = true;
//				tdFlujo.setIdsacc(tdFlujoBk.getIdsacc());
//			}
//		} else if (tdFlujoBk.getIdsacc() == null && tdFlujo.getIdsacc() != null) {
//			if (nivel > 0) {
//				log.log(Level.INFO,
//						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Idsacc" + " :: "
//								+ tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdsacc() + " :: "
//								+ tdFlujoBk.getIdsacc());
//			}
//			cambios = true;
//			tdFlujo.setIdsacc(tdFlujoBk.getIdsacc());
//
//		} else if (tdFlujoBk.getIdsacc() != null && tdFlujo.getIdsacc() == null) {
//			if (nivel > 0) {
//				log.log(Level.INFO,
//						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Idsacc" + " :: "
//								+ tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdsacc() + " :: "
//								+ tdFlujoBk.getIdsacc());
//			}
//			cambios = true;
//			tdFlujo.setIdsacc(tdFlujoBk.getIdsacc());
//		}
		if (tdFlujoBk.getFechaDerivacion() != null && tdFlujo.getFechaDerivacion() != null) {
			if (!tdFlujoBk.getFechaDerivacion().equals(tdFlujo.getFechaDerivacion())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:FechaDerivacion" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getFechaDerivacion() + " :: " + tdFlujoBk.getFechaDerivacion());
				}
				cambios = true;
				tdFlujo.setFechaDerivacion(tdFlujoBk.getFechaDerivacion());
			}
		} else if (tdFlujoBk.getFechaDerivacion() == null && tdFlujo.getFechaDerivacion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaDerivacion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaDerivacion()
								+ " :: " + tdFlujoBk.getFechaDerivacion());
			}
			cambios = true;
			tdFlujo.setFechaDerivacion(tdFlujoBk.getFechaDerivacion());

		} else if (tdFlujoBk.getFechaDerivacion() != null && tdFlujo.getFechaDerivacion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaDerivacion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaDerivacion()
								+ " :: " + tdFlujoBk.getFechaDerivacion());
			}
			cambios = true;
			tdFlujo.setFechaDerivacion(tdFlujoBk.getFechaDerivacion());
		}

		if (tdFlujoBk.getIdunidadDeriva() != null && tdFlujo.getIdunidadDeriva() != null) {
			if (!tdFlujoBk.getIdunidadDeriva().equals(tdFlujo.getIdunidadDeriva())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IdunidadDeriva" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIdunidadDeriva() + " :: " + tdFlujoBk.getIdunidadDeriva());
				}
				cambios = true;
				tdFlujo.setIdunidadDeriva(tdFlujoBk.getIdunidadDeriva());
			}
		} else if (tdFlujoBk.getIdunidadDeriva() == null && tdFlujo.getIdunidadDeriva() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadDeriva"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadDeriva()
								+ " :: " + tdFlujoBk.getIdunidadDeriva());
			}
			cambios = true;
			tdFlujo.setIdunidadDeriva(tdFlujoBk.getIdunidadDeriva());

		} else if (tdFlujoBk.getIdunidadDeriva() != null && tdFlujo.getIdunidadDeriva() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadDeriva"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadDeriva()
								+ " :: " + tdFlujoBk.getIdunidadDeriva());
			}
			cambios = true;
			tdFlujo.setIdunidadDeriva(tdFlujoBk.getIdunidadDeriva());
		}

		if (tdFlujoBk.getIduserDeriva() != null && tdFlujo.getIduserDeriva() != null) {
			if (!tdFlujoBk.getIduserDeriva().equals(tdFlujo.getIduserDeriva())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IduserDeriva" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIduserDeriva() + " :: " + tdFlujoBk.getIduserDeriva());
				}
				cambios = true;
				tdFlujo.setIduserDeriva(tdFlujoBk.getIduserDeriva());
			}
		} else if (tdFlujoBk.getIduserDeriva() == null && tdFlujo.getIduserDeriva() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserDeriva"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserDeriva()
								+ " :: " + tdFlujoBk.getIduserDeriva());
			}
			cambios = true;
			tdFlujo.setIduserDeriva(tdFlujoBk.getIduserDeriva());

		} else if (tdFlujoBk.getIduserDeriva() != null && tdFlujo.getIduserDeriva() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserDeriva"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserDeriva()
								+ " :: " + tdFlujoBk.getIduserDeriva());
			}
			cambios = true;
			tdFlujo.setIduserDeriva(tdFlujoBk.getIduserDeriva());
		}

		if (tdFlujoBk.getFechaRecepcion() != null && tdFlujo.getFechaRecepcion() != null) {
			if (!tdFlujoBk.getFechaRecepcion().equals(tdFlujo.getFechaRecepcion())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:FechaRecepcion" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getFechaRecepcion() + " :: " + tdFlujoBk.getFechaRecepcion());
				}
				cambios = true;
				tdFlujo.setFechaRecepcion(tdFlujoBk.getFechaRecepcion());
			}
		} else if (tdFlujoBk.getFechaRecepcion() == null && tdFlujo.getFechaRecepcion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaRecepcion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaRecepcion()
								+ " :: " + tdFlujoBk.getFechaRecepcion());
			}
			cambios = true;
			tdFlujo.setFechaRecepcion(tdFlujoBk.getFechaRecepcion());

		} else if (tdFlujoBk.getFechaRecepcion() != null && tdFlujo.getFechaRecepcion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaRecepcion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaRecepcion()
								+ " :: " + tdFlujoBk.getFechaRecepcion());
			}
			cambios = true;
			tdFlujo.setFechaRecepcion(tdFlujoBk.getFechaRecepcion());
		}

		if (tdFlujoBk.getIdunidadRecepciona() != null && tdFlujo.getIdunidadRecepciona() != null) {
			if (!tdFlujoBk.getIdunidadRecepciona().equals(tdFlujo.getIdunidadRecepciona())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IdunidadRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIdunidadRecepciona() + " :: " + tdFlujoBk.getIdunidadRecepciona());
				}
				cambios = true;
				tdFlujo.setIdunidadRecepciona(tdFlujoBk.getIdunidadRecepciona());
			}
		} else if (tdFlujoBk.getIdunidadRecepciona() == null && tdFlujo.getIdunidadRecepciona() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "tdFlujo:IdunidadRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
								+ tdFlujo.getIdunidadRecepciona() + " :: " + tdFlujoBk.getIdunidadRecepciona());
			}
			cambios = true;
			tdFlujo.setIdunidadRecepciona(tdFlujoBk.getIdunidadRecepciona());

		} else if (tdFlujoBk.getIdunidadRecepciona() != null && tdFlujo.getIdunidadRecepciona() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "tdFlujo:IdunidadRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
								+ tdFlujo.getIdunidadRecepciona() + " :: " + tdFlujoBk.getIdunidadRecepciona());
			}
			cambios = true;
			tdFlujo.setIdunidadRecepciona(tdFlujoBk.getIdunidadRecepciona());
		}

		if (tdFlujoBk.getIduserRecepciona() != null && tdFlujo.getIduserRecepciona() != null) {
			if (!tdFlujoBk.getIduserRecepciona().equals(tdFlujo.getIduserRecepciona())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IduserRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIduserRecepciona() + " :: " + tdFlujoBk.getIduserRecepciona());
				}
				cambios = true;
				tdFlujo.setIduserRecepciona(tdFlujoBk.getIduserRecepciona());
			}
		} else if (tdFlujoBk.getIduserRecepciona() == null && tdFlujo.getIduserRecepciona() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "tdFlujo:IduserRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
								+ tdFlujo.getIduserRecepciona() + " :: " + tdFlujoBk.getIduserRecepciona());
			}
			cambios = true;
			tdFlujo.setIduserRecepciona(tdFlujoBk.getIduserRecepciona());

		} else if (tdFlujoBk.getIduserRecepciona() != null && tdFlujo.getIduserRecepciona() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
								+ "tdFlujo:IduserRecepciona" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
								+ tdFlujo.getIduserRecepciona() + " :: " + tdFlujoBk.getIduserRecepciona());
			}
			cambios = true;
			tdFlujo.setIduserRecepciona(tdFlujoBk.getIduserRecepciona());
		}

		if (tdFlujoBk.getIdunidadDestino() != null && tdFlujo.getIdunidadDestino() != null) {
			if (!tdFlujoBk.getIdunidadDestino().equals(tdFlujo.getIdunidadDestino())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IdunidadDestino" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIdunidadDestino() + " :: " + tdFlujoBk.getIdunidadDestino());
				}
				cambios = true;
				tdFlujo.setIdunidadDestino(tdFlujoBk.getIdunidadDestino());
			}
		} else if (tdFlujoBk.getIdunidadDestino() == null && tdFlujo.getIdunidadDestino() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadDestino"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadDestino()
								+ " :: " + tdFlujoBk.getIdunidadDestino());
			}
			cambios = true;
			tdFlujo.setIdunidadDestino(tdFlujoBk.getIdunidadDestino());

		} else if (tdFlujoBk.getIdunidadDestino() != null && tdFlujo.getIdunidadDestino() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadDestino"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadDestino()
								+ " :: " + tdFlujoBk.getIdunidadDestino());
			}
			cambios = true;
			tdFlujo.setIdunidadDestino(tdFlujoBk.getIdunidadDestino());
		}

		if (tdFlujoBk.getIduserDestino() != null && tdFlujo.getIduserDestino() != null) {
			if (!tdFlujoBk.getIduserDestino().equals(tdFlujo.getIduserDestino())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IduserDestino" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIduserDestino() + " :: " + tdFlujoBk.getIduserDestino());
				}
				cambios = true;
				tdFlujo.setIduserDestino(tdFlujoBk.getIduserDestino());
			}
		} else if (tdFlujoBk.getIduserDestino() == null && tdFlujo.getIduserDestino() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserDestino"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserDestino()
								+ " :: " + tdFlujoBk.getIduserDestino());
			}
			cambios = true;
			tdFlujo.setIduserDestino(tdFlujoBk.getIduserDestino());

		} else if (tdFlujoBk.getIduserDestino() != null && tdFlujo.getIduserDestino() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserDestino"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserDestino()
								+ " :: " + tdFlujoBk.getIduserDestino());
			}
			cambios = true;
			tdFlujo.setIduserDestino(tdFlujoBk.getIduserDestino());
		}

		if (tdFlujoBk.getIdunidadAtiende() != null && tdFlujo.getIdunidadAtiende() != null) {
			if (!tdFlujoBk.getIdunidadAtiende().equals(tdFlujo.getIdunidadAtiende())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IdunidadAtiende" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIdunidadAtiende() + " :: " + tdFlujoBk.getIdunidadAtiende());
				}
				cambios = true;
				tdFlujo.setIdunidadAtiende(tdFlujoBk.getIdunidadAtiende());
			}
		} else if (tdFlujoBk.getIdunidadAtiende() == null && tdFlujo.getIdunidadAtiende() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadAtiende"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadAtiende()
								+ " :: " + tdFlujoBk.getIdunidadAtiende());
			}
			cambios = true;
			tdFlujo.setIdunidadAtiende(tdFlujoBk.getIdunidadAtiende());

		} else if (tdFlujoBk.getIdunidadAtiende() != null && tdFlujo.getIdunidadAtiende() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IdunidadAtiende"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIdunidadAtiende()
								+ " :: " + tdFlujoBk.getIdunidadAtiende());
			}
			cambios = true;
			tdFlujo.setIdunidadAtiende(tdFlujoBk.getIdunidadAtiende());
		}

		if (tdFlujoBk.getIduserAtiende() != null && tdFlujo.getIduserAtiende() != null) {
			if (!tdFlujoBk.getIduserAtiende().equals(tdFlujo.getIduserAtiende())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:IduserAtiende" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getIduserAtiende() + " :: " + tdFlujoBk.getIduserAtiende());
				}
				cambios = true;
				tdFlujo.setIduserAtiende(tdFlujoBk.getIduserAtiende());
			}
		} else if (tdFlujoBk.getIduserAtiende() == null && tdFlujo.getIduserAtiende() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserAtiende"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserAtiende()
								+ " :: " + tdFlujoBk.getIduserAtiende());
			}
			cambios = true;
			tdFlujo.setIduserAtiende(tdFlujoBk.getIduserAtiende());

		} else if (tdFlujoBk.getIduserAtiende() != null && tdFlujo.getIduserAtiende() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:IduserAtiende"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getIduserAtiende()
								+ " :: " + tdFlujoBk.getIduserAtiende());
			}
			cambios = true;
			tdFlujo.setIduserAtiende(tdFlujoBk.getIduserAtiende());
		}

		if (tdFlujoBk.getFechaAtencion() != null && tdFlujo.getFechaAtencion() != null) {
			if (!tdFlujoBk.getFechaAtencion().equals(tdFlujo.getFechaAtencion())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:FechaAtencion" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getFechaAtencion() + " :: " + tdFlujoBk.getFechaAtencion());
				}
				cambios = true;
				tdFlujo.setFechaAtencion(tdFlujoBk.getFechaAtencion());
			}
		} else if (tdFlujoBk.getFechaAtencion() == null && tdFlujo.getFechaAtencion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaAtencion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaAtencion()
								+ " :: " + tdFlujoBk.getFechaAtencion());
			}
			cambios = true;
			tdFlujo.setFechaAtencion(tdFlujoBk.getFechaAtencion());

		} else if (tdFlujoBk.getFechaAtencion() != null && tdFlujo.getFechaAtencion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:FechaAtencion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getFechaAtencion()
								+ " :: " + tdFlujoBk.getFechaAtencion());
			}
			cambios = true;
			tdFlujo.setFechaAtencion(tdFlujoBk.getFechaAtencion());
		}
		if (tdFlujoBk.getObservacion() != null && tdFlujo.getObservacion() != null) {
			if (!tdFlujoBk.getObservacion().equals(tdFlujo.getObservacion())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Observacion"
									+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacion()
									+ " :: " + tdFlujoBk.getObservacion());
				}
				cambios = true;
				tdFlujo.setObservacion(tdFlujoBk.getObservacion());
			}
		} else if (tdFlujoBk.getObservacion() == null && tdFlujo.getObservacion() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Observacion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacion()
								+ " :: " + tdFlujoBk.getObservacion());
			}
			cambios = true;
			tdFlujo.setObservacion(tdFlujoBk.getObservacion());

		} else if (tdFlujoBk.getObservacion() != null && tdFlujo.getObservacion() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Observacion"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacion()
								+ " :: " + tdFlujoBk.getObservacion());
			}
			cambios = true;
			tdFlujo.setObservacion(tdFlujoBk.getObservacion());
		}
		//MPINARES 27042022 - INICIO
				if (tdFlujoBk.getObservacionHtml() != null && tdFlujo.getObservacionHtml() != null) {
					if (!tdFlujoBk.getObservacionHtml().equals(tdFlujo.getObservacionHtml())) {
						if (nivel > 0) {
							log.log(Level.INFO,
									"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:ObservacionHtml"
											+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacionHtml()
											+ " :: " + tdFlujoBk.getObservacionHtml());
						}
						cambios = true;
						tdFlujo.setObservacionHtml(tdFlujoBk.getObservacionHtml());
					}
				} else if (tdFlujoBk.getObservacionHtml() == null && tdFlujo.getObservacionHtml() != null) {
					if (nivel > 0) {
						log.log(Level.INFO,
								"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:ObservacionHtml"
										+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacionHtml()
										+ " :: " + tdFlujoBk.getObservacionHtml());
					}
					cambios = true;
					tdFlujo.setObservacionHtml(tdFlujoBk.getObservacionHtml());

				} else if (tdFlujoBk.getObservacionHtml() != null && tdFlujo.getObservacionHtml() == null) {
					if (nivel > 0) {
						log.log(Level.INFO,
								"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:ObservacionHtml"
										+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getObservacionHtml()
										+ " :: " + tdFlujoBk.getObservacionHtml());
					}
					cambios = true;
					tdFlujo.setObservacionHtml(tdFlujoBk.getObservacionHtml());
				}
				//MPINARES 27042022 - FIN
		if (tdFlujoBk.getTiempoestadia() != null && tdFlujo.getTiempoestadia() != null) {
			if (!tdFlujoBk.getTiempoestadia().equals(tdFlujo.getTiempoestadia())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:Tiempoestadia" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getTiempoestadia() + " :: " + tdFlujoBk.getTiempoestadia());
				}
				cambios = true;
				tdFlujo.setTiempoestadia(tdFlujoBk.getTiempoestadia());
			}
		} else if (tdFlujoBk.getTiempoestadia() == null && tdFlujo.getTiempoestadia() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Tiempoestadia"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getTiempoestadia()
								+ " :: " + tdFlujoBk.getTiempoestadia());
			}
			cambios = true;
			tdFlujo.setTiempoestadia(tdFlujoBk.getTiempoestadia());

		} else if (tdFlujoBk.getTiempoestadia() != null && tdFlujo.getTiempoestadia() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Tiempoestadia"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getTiempoestadia()
								+ " :: " + tdFlujoBk.getTiempoestadia());
			}
			cambios = true;
			tdFlujo.setTiempoestadia(tdFlujoBk.getTiempoestadia());
		}
		if (tdFlujoBk.getEstado() != null && tdFlujo.getEstado() != null) {
			if (!tdFlujoBk.getEstado().equals(tdFlujo.getEstado())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Estado"
									+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getEstado() + " :: "
									+ tdFlujoBk.getEstado());
				}
				cambios = true;
				tdFlujo.setEstado(tdFlujoBk.getEstado());
			}
		} else if (tdFlujoBk.getEstado() == null && tdFlujo.getEstado() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Estado" + " :: "
								+ tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getEstado() + " :: "
								+ tdFlujoBk.getEstado());
			}
			cambios = true;
			tdFlujo.setEstado(tdFlujoBk.getEstado());

		} else if (tdFlujoBk.getEstado() != null && tdFlujo.getEstado() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Estado" + " :: "
								+ tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getEstado() + " :: "
								+ tdFlujoBk.getEstado());
			}
			cambios = true;
			tdFlujo.setEstado(tdFlujoBk.getEstado());
		}
		if (tdFlujoBk.getCorreosnotif() != null && tdFlujo.getCorreosnotif() != null) {
			if (!tdFlujoBk.getCorreosnotif().equals(tdFlujo.getCorreosnotif())) {
				if (nivel > 0) {
					log.log(Level.INFO,
							"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: "
									+ "tdFlujo:Correosnotif" + " :: " + tdFlujoBk.getIdflujo().toString() + " :: "
									+ tdFlujo.getCorreosnotif() + " :: " + tdFlujoBk.getCorreosnotif());
				}
				cambios = true;
				tdFlujo.setCorreosnotif(tdFlujoBk.getCorreosnotif());
			}
		} else if (tdFlujoBk.getCorreosnotif() == null && tdFlujo.getCorreosnotif() != null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Correosnotif"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getCorreosnotif()
								+ " :: " + tdFlujoBk.getCorreosnotif());
			}
			cambios = true;
			tdFlujo.setCorreosnotif(tdFlujoBk.getCorreosnotif());

		} else if (tdFlujoBk.getCorreosnotif() != null && tdFlujo.getCorreosnotif() == null) {
			if (nivel > 0) {
				log.log(Level.INFO,
						"CAMBIO :: " + iduser + " :: " + user + " :: " + rmtaddress + " :: " + "tdFlujo:Correosnotif"
								+ " :: " + tdFlujoBk.getIdflujo().toString() + " :: " + tdFlujo.getCorreosnotif()
								+ " :: " + tdFlujoBk.getCorreosnotif());
			}
			cambios = true;
			tdFlujo.setCorreosnotif(tdFlujoBk.getCorreosnotif());
		}
		return cambios;
	}

}