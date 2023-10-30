package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.TdUbicaciones;

/**
*
* TD_UBICACIONES REPOSITORIO INTERFACE: UBICACIONES ASIGADOS A LOS EVENTOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdUbicacionesDao {
	void saveTdUbicaciones(TdUbicaciones param);
	void updateTdUbicaciones(TdUbicaciones param);
	void deleteTdUbicaciones(TdUbicaciones param);
	List<TdUbicaciones> getAllTdUbicaciones();
	TdUbicaciones getTdUbicaciones(Long idubicaciones);
	List<TdUbicaciones> getNativeSQLTdUbicaciones(String queryString, Object[] params);
	List<TdUbicaciones> getActivasTdUbicaciones();
	List<TdUbicaciones> getActivasTdUbicacionesCero();
	List<TdUbicaciones> getDesactivasTdUbicaciones();
	Long getMaxIdVal();
	
	List<TdUbicaciones> getXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado, int iniciar, int max);
	List<TdUbicaciones> getXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado);
	long getTotalXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado);
	
}