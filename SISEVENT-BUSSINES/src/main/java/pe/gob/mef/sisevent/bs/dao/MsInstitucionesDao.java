package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsInstituciones;

/**
*
* MS_INSTITUCIONES REPOSITORIO INTERFACE: INSTITUCIONES Y ENTIDADES
*
*
* @author  Carlos Aguilar
* @version 2.0, 22/12/2020 17:45
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45         / Creación de la clase  /
* 
*/
public interface MsInstitucionesDao {
	void saveMsInstituciones(MsInstituciones param);
	void updateMsInstituciones(MsInstituciones param);
	void deleteMsInstituciones(MsInstituciones param);
	List<MsInstituciones> getAllMsInstituciones();
	MsInstituciones getMsInstituciones(Long idprovee);
	List<MsInstituciones> getNativeSQLMsInstituciones(String queryString, Object[] params);
	List<MsInstituciones> getActivasMsInstituciones();
	List<MsInstituciones> getDesactivasMsInstituciones();
	Long getMaxIdVal();
	
	List<MsInstituciones> getXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado, int iniciar, int max);
	List<MsInstituciones> getXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado);
	long getTotalXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado);
	List<MsInstituciones> getActivasMsInstitucionesCero();
	List<MsInstituciones> getXFiltroTipoentidad(String tipoentidad, String razonSocial);
	
}