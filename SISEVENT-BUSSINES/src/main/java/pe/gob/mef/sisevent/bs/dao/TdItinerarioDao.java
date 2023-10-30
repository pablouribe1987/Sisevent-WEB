package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.TdItinerario;

/**
*
* TD_ITINERARIO REPOSITORIO INTERFACE: ITINERARIO DE LOS VUELOS A REALIZARCE
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdItinerarioDao {
	void saveTdItinerario(TdItinerario param);
	void updateTdItinerario(TdItinerario param);
	void deleteTdItinerario(TdItinerario param);
	List<TdItinerario> getAllTdItinerario();
	TdItinerario getTdItinerario(Long iditinerario);
	List<TdItinerario> getNativeSQLTdItinerario(String queryString, Object[] params);
	List<TdItinerario> getActivasTdItinerario();
	List<TdItinerario> getActivasTdItinerarioCero();
	List<TdItinerario> getDesactivasTdItinerario();
	Long getMaxIdVal();
	
	List<TdItinerario> getXFiltro(Long iditinerario,Long idevent,Long codpais,Integer coddpto,Integer codprov, int iniciar, int max);
	List<TdItinerario> getXFiltro(Long iditinerario,Long idevent,Long codpais,Integer coddpto,Integer codprov);
	long getTotalXFiltro(Long iditinerario,Long idevent,Long codpais,Integer coddpto,Integer codprov);
	
}