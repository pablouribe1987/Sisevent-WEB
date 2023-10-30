package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsUbigeo;
import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;

/**
*
* MS_UBIGEO REPOSITORIO INTERFACE: UBIGEO DATOS OTORGADOS POR EL INEI
*
*
* @author  Carlos Aguilar
* @version 2.0, 23/12/2020 11:52
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 23/12/2020 11:52         / Creación de la clase  /
* 
*/
public interface MsUbigeoDao {
	void saveMsUbigeo(MsUbigeo param);
	void updateMsUbigeo(MsUbigeo param);
	void deleteMsUbigeo(MsUbigeo param);
	List<MsUbigeo> getAllMsUbigeo();
	MsUbigeo getMsUbigeo(MsUbigeoId id);
	List<MsUbigeo> getNativeSQLMsUbigeo(String queryString, Object[] params);
	List<MsUbigeo> getActivasMsUbigeo();
	List<MsUbigeo> getDesactivasMsUbigeo();
	Long getMaxIdVal();
	
	List<MsUbigeo> getXFiltro(Integer coddpto,Integer codprov,Integer coddist,String nombre,Integer estado, int iniciar, int max);
	List<MsUbigeo> getXFiltro(Integer coddpto,Integer codprov,Integer coddist,String nombre,Integer estado);
	long getTotalXFiltro(Integer coddpto,Integer codprov,Integer coddist,String nombre,Integer estado);
	List<MsUbigeo> getActivasMsUbigeoCero();
	List<MsUbigeo> getDistritos(Integer id_dpto, Integer id_prov);
	List<MsUbigeo> getProvincias(Integer id_dpto);
	List<MsUbigeo> getDepartamentos();
	MsUbigeo getDistritosXNombre(Integer id_dpto, Integer id_prov, String nombre);
	MsUbigeo getProvinciasXNombre(Integer id_dpto, String nombre);
	MsUbigeo getDepartamentosXNombre(String nombre);
	
}