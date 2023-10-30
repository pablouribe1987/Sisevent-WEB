package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.PrtParametros;

/**
*
* PRT_PARAMETROS REPOSITORIO INTERFACE: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL SISTEMA PARÁMETROS
*
*
* @author  Carlos Aguilar
* @version 2.0, 25/11/2020 23:37
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37         / Creación de la clase  /
* 
*/
public interface PrtParametrosDao {
	void savePrtParametros(PrtParametros param);
	void updatePrtParametros(PrtParametros param);
	void deletePrtParametros(PrtParametros param);
	List<PrtParametros> getAllPrtParametros();
	PrtParametros getPrtParametros(Long idparametro);
	List<PrtParametros> getNativeSQLPrtParametros(String queryString, Object[] params);
	List<PrtParametros> getActivasPrtParametros();
	List<PrtParametros> getDesactivasPrtParametros();
	Long getMaxIdVal();
	List<PrtParametros> getXFiltro(Long idpadre, String descripcion);
	List<PrtParametros> getXDescripcion(String descripcionpadre);
	PrtParametros getDescripcion(String descripcionpadre);
	List<PrtParametros> getXPadre(Long idpadre);
	List<PrtParametros> getXDescripcionCero(String descripcionpadre);
	List<PrtParametros> getActivasPrtParametrosCero();
	List<PrtParametros> getXDescripcionHijos(String descripcionpadre);
	List<PrtParametros> getXDescripcionCeroNo0(String descripcionpadre);
	List<PrtParametros> getListaIdparametro();
}