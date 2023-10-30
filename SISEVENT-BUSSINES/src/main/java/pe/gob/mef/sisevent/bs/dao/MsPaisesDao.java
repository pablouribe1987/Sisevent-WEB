package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsPaises;

/**
*
* MS_PAISES REPOSITORIO INTERFACE: PAISES SEGÚN ISO
*
*
* @author  Carlos Aguilar
* @version 2.0, 22/12/2020 17:45
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45         / Creación de la clase  /
* 
*/
public interface MsPaisesDao {
	void saveMsPaises(MsPaises param);
	void updateMsPaises(MsPaises param);
	void deleteMsPaises(MsPaises param);
	List<MsPaises> getAllMsPaises();
	MsPaises getMsPaises(Long paiPk);
	List<MsPaises> getNativeSQLMsPaises(String queryString, Object[] params);
	List<MsPaises> getActivasMsPaises();
	List<MsPaises> getDesactivasMsPaises();
	Long getMaxIdVal();
	
	List<MsPaises> getXFiltro(Long paiPk,Long paiIsonum,String paiIso2,String paiIso3,String paiNombre,Integer estado, int iniciar, int max);
	List<MsPaises> getXFiltro(Long paiPk,Long paiIsonum,String paiIso2,String paiIso3,String paiNombre,Integer estado);
	long getTotalXFiltro(Long paiPk,Long paiIsonum,String paiIso2,String paiIso3,String paiNombre,Integer estado);
	List<MsPaises> getActivasMsPaisesCero();
	MsPaises getByNombreMsPaises(String nombre);
	List<MsPaises> getListaPaiPk();
	
}