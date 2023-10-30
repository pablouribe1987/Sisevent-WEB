package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.MsTareas;

/**
*
* MS_TAREAS REPOSITORIO INTERFACE: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS DERIVACIONES
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface MsTareasDao {
	void saveMsTareas(MsTareas param);
	void updateMsTareas(MsTareas param);
	void deleteMsTareas(MsTareas param);
	List<MsTareas> getAllMsTareas();
	MsTareas getMsTareas(Long idtareas);
	List<MsTareas> getNativeSQLMsTareas(String queryString, Object[] params);
	List<MsTareas> getActivasMsTareas();
	List<MsTareas> getActivasMsTareasCero();
	List<MsTareas> getDesactivasMsTareas();
	Long getMaxIdVal();
	
	List<MsTareas> getXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado, int iniciar, int max);
	List<MsTareas> getXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado);
	long getTotalXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado);
	
}