package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsRoles;

/**
*
* MS_ROLES REPOSITORIO INTERFACE: ROLES DEL SISTEMA, ROLES
*
*
* @author  Carlos Aguilar
* @version 2.0, 25/11/2020 23:37
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37         / Creación de la clase  /
* 
*/
public interface MsRolesDao {
	void saveMsRoles(MsRoles param);
	void updateMsRoles(MsRoles param);
	void deleteMsRoles(MsRoles param);
	List<MsRoles> getAllMsRoles();
	MsRoles getMsRoles(Long idrol);
	List<MsRoles> getNativeSQLMsRoles(String queryString, Object[] params);
	List<MsRoles> getActivasMsRoles();
	List<MsRoles> getDesactivasMsRoles();
	Long getMaxIdVal();
	MsRoles getXUserRol(String username, String rol);
	List<MsRoles> getXFiltro(String username);
}