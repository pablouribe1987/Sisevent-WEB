package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.TdGruposflujos;

/**
*
* TD_GRUPOSFLUJOS REPOSITORIO INTERFACE: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdGruposflujosDao {
	void saveTdGruposflujos(TdGruposflujos param);
	void updateTdGruposflujos(TdGruposflujos param);
	void deleteTdGruposflujos(TdGruposflujos param);
	List<TdGruposflujos> getAllTdGruposflujos();
	TdGruposflujos getTdGruposflujos(Long idgruposflujos);
	List<TdGruposflujos> getNativeSQLTdGruposflujos(String queryString, Object[] params);
	List<TdGruposflujos> getActivasTdGruposflujos();
	List<TdGruposflujos> getActivasTdGruposflujosCero();
	List<TdGruposflujos> getDesactivasTdGruposflujos();
	Long getMaxIdVal();
	
	List<TdGruposflujos> getXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idGrupo,int iniciar, int max);
	List<TdGruposflujos> getXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idGrupo);
	long getTotalXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idGrupo);
	
}