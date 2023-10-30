package pe.gob.mef.sisevent.bs.dao;

import java.sql.Timestamp;
import java.util.List;

import pe.gob.mef.sisevent.bs.domain.TdEvento;

/**
*
* TD_EVENTO REPOSITORIO INTERFACE: EVENTO
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdEventoDao {
	void saveTdEvento(TdEvento param);
	void updateTdEvento(TdEvento param);
	void deleteTdEvento(TdEvento param);
	List<TdEvento> getAllTdEvento();
	TdEvento getTdEvento(Long idevent);
	List<TdEvento> getNativeSQLTdEvento(String queryString, Object[] params);
	List<TdEvento> getActivasTdEvento();
	List<TdEvento> getActivasTdEventoCero();
	List<TdEvento> getDesactivasTdEvento();
	Long getMaxIdVal();
	
	List<TdEvento> getXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado, int iniciar, int max);
	List<TdEvento> getXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado);
	long getTotalXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado);
	List<TdEvento> getListaIdevent();
	
}