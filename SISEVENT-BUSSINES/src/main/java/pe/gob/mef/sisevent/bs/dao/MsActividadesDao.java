package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.MsActividades;

/**
*
* MS_ACTIVIDADES REPOSITORIO INTERFACE: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface MsActividadesDao {
	void saveMsActividades(MsActividades param);
	void updateMsActividades(MsActividades param);
	void deleteMsActividades(MsActividades param);
	List<MsActividades> getAllMsActividades();
	MsActividades getMsActividades(Long idactividades);
	List<MsActividades> getNativeSQLMsActividades(String queryString, Object[] params);
	List<MsActividades> getActivasMsActividades();
	List<MsActividades> getActivasMsActividadesCero();
	List<MsActividades> getDesactivasMsActividades();
	Long getMaxIdVal();
	
	List<MsActividades> getXFiltro(Long idactividades,Long idtareas,String actividad, int iniciar, int max);
	List<MsActividades> getXFiltro(Long idactividades,Long idtareas,String actividad);
	long getTotalXFiltro(Long idactividades,Long idtareas,String actividad);
	
}