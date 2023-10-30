package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.TdCategEvento;

/**
*
* //MPINARES 01092023 - INICIO - NUEVA CLASE
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdCategEventoDao {
	void saveTdCategEvento(TdCategEvento param);
	void updateTdCategEvento(TdCategEvento param);
	void deleteTdCategEvento(TdCategEvento param);
	List<TdCategEvento> getAllTdCategEvento();
	TdCategEvento getTdCategEvento(Long idcategevento);
	List<TdCategEvento> getNativeSQLTdCategEvento(String queryString, Object[] params);
	List<TdCategEvento> getActivasTdCategEvento();
	List<TdCategEvento> getActivasTdCategEventoCero();
	List<TdCategEvento> getDesactivasTdCategEvento();
	Long getMaxIdVal();
	
//	List<MsCategEvento> getXFiltro(Long idcategevento,Long idevent,Long codpais,Integer coddpto,Integer codprov, int iniciar, int max);
	List<TdCategEvento> getXFiltro(Long idcategevento,Long idevent,Long idcategorias);
//	long getTotalXFiltro(Long idcategevento,Long idevent,Long codpais,Integer coddpto,Integer codprov);
	
}