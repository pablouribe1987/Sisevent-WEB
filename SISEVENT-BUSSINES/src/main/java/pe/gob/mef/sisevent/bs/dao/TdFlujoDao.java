package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.TdFlujo;

/**
*
* TD_FLUJO REPOSITORIO INTERFACE: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
*
*
* @author  Carlos Aguilar
* @version 2.0, 22/12/2020 17:45
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45         / Creación de la clase  /
* 
*/
public interface TdFlujoDao {
	void saveTdFlujo(TdFlujo param);
	void updateTdFlujo(TdFlujo param);
	void deleteTdFlujo(TdFlujo param);
	List<TdFlujo> getAllTdFlujo();
	TdFlujo getTdFlujo(Long idflujo);
	List<TdFlujo> getNativeSQLTdFlujo(String queryString, Object[] params);
	List<TdFlujo> getActivasTdFlujo();
	List<TdFlujo> getDesactivasTdFlujo();
	Long getMaxIdVal();
	
	List<TdFlujo> getXFiltro(Long idflujo,Long idflujopadre,Long idsacc,Integer estado, int iniciar, int max);
	List<TdFlujo> getXFiltro(Long idflujo,Long idflujopadre,Long idsacc,Integer estado);
	long getTotalXFiltro(Long idflujo,Long idflujopadre,Long idsacc,Integer estado);
	List<TdFlujo> getXFiltro(Long idsacc);
	
}