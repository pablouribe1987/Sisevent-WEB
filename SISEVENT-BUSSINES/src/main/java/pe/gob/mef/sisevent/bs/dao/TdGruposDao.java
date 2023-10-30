package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.TdGrupos;

/**
*
* TD_GRUPOS REPOSITORIO INTERFACE: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdGruposDao {
	void saveTdGrupos(TdGrupos param);
	void updateTdGrupos(TdGrupos param);
	void deleteTdGrupos(TdGrupos param);
	List<TdGrupos> getAllTdGrupos();
	TdGrupos getTdGrupos(Long idgrupo);
	List<TdGrupos> getNativeSQLTdGrupos(String queryString, Object[] params);
	List<TdGrupos> getActivasTdGrupos();
	List<TdGrupos> getActivasTdGruposCero();
	List<TdGrupos> getDesactivasTdGrupos();
	Long getMaxIdVal();
	
	List<TdGrupos> getXFiltro(Long idgrupo,String grupo,Integer estado, int iniciar, int max);
	List<TdGrupos> getXFiltro(Long idgrupo,String grupo,Integer estado);
	long getTotalXFiltro(Long idgrupo,String grupo,Integer estado);
	
}