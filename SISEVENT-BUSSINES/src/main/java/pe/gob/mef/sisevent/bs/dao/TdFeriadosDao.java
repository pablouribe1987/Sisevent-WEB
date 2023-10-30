package pe.gob.mef.sisevent.bs.dao;

import java.sql.Timestamp;
import java.util.List;

import pe.gob.mef.sisevent.bs.domain.TdFeriados;

/**
*
* TD_FERIADOS REPOSITORIO INTERFACE: DÍAS NO LABORABLES
*
*
* @author  Carlos Aguilar
* @version 2.0, 11/01/2021 02:31
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 11/01/2021 02:31         / Creación de la clase  /
* 
*/
public interface TdFeriadosDao {
	void saveTdFeriados(TdFeriados param);
	void updateTdFeriados(TdFeriados param);
	void deleteTdFeriados(TdFeriados param);
	List<TdFeriados> getAllTdFeriados();
	TdFeriados getTdFeriados(Long idferiado);
	List<TdFeriados> getNativeSQLTdFeriados(String queryString, Object[] params);
	List<TdFeriados> getActivasTdFeriados();
	List<TdFeriados> getDesactivasTdFeriados();
	Long getMaxIdVal();
	
	List<TdFeriados> getXFiltro(Timestamp fechaFeriado,String descricion,Integer estado, int iniciar, int max);
	List<TdFeriados> getXFiltro(Timestamp fechaFeriado,String descricion,Integer estado);
	long getTotalXFiltro(Timestamp fechaFeriado,String descricion,Integer estado);
	List<TdFeriados> getActivasTdFeriadosCero();
	
}