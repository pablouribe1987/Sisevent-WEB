package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.TdParticipantes;

/**
*
* TD_PARTICIPANTES REPOSITORIO INTERFACE: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface TdParticipantesDao {
	void saveTdParticipantes(TdParticipantes param);
	void updateTdParticipantes(TdParticipantes param);
	void deleteTdParticipantes(TdParticipantes param);
	List<TdParticipantes> getAllTdParticipantes();
	TdParticipantes getTdParticipantes(Long idparticipantes);
	List<TdParticipantes> getNativeSQLTdParticipantes(String queryString, Object[] params);
	List<TdParticipantes> getActivasTdParticipantes();
	List<TdParticipantes> getActivasTdParticipantesCero();
	List<TdParticipantes> getDesactivasTdParticipantes();
	Long getMaxIdVal();
	
	List<TdParticipantes> getXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado, int iniciar, int max);
	List<TdParticipantes> getXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado);
	long getTotalXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado);
	List<TdParticipantes> getXFiltro(Long idevent); //MPINARES 01092023 - INICIO
}