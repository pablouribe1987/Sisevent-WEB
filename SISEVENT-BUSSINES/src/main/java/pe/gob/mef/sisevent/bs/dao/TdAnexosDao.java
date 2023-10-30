package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.TdAnexos;

/**
*
* TD_ANEXOS REPOSITORIO INTERFACE: ANEXOS DE LOS MOVIMIENTOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 22/12/2020 17:45
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45         / Creación de la clase  /
* 
*/
public interface TdAnexosDao {
	void saveTdAnexos(TdAnexos param);
	void updateTdAnexos(TdAnexos param);
	void deleteTdAnexos(TdAnexos param);
	List<TdAnexos> getAllTdAnexos();
	TdAnexos getTdAnexos(Long idanexo);
	List<TdAnexos> getNativeSQLTdAnexos(String queryString, Object[] params);
	List<TdAnexos> getActivasTdAnexos();
	List<TdAnexos> getDesactivasTdAnexos();
	Long getMaxIdVal();
	
	List<TdAnexos> getXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado, int iniciar, int max);
	List<TdAnexos> getXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado);
	long getTotalXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado);
	List<TdAnexos> getXFiltroXFlujoYAtencio(Long idsacc, Long idflujo);
	
}