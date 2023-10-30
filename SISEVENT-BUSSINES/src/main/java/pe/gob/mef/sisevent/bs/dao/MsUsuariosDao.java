package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsUsuarios;

/**
*
* MS_USUARIOS REPOSITORIO INTERFACE: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA USUARIOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 25/11/2020 23:37
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37         / Creación de la clase  /
* 
*/
public interface MsUsuariosDao {
	void saveMsUsuarios(MsUsuarios param);
	void updateMsUsuarios(MsUsuarios param);
	void deleteMsUsuarios(MsUsuarios param);
	List<MsUsuarios> getAllMsUsuarios();
	MsUsuarios getMsUsuarios(Long idusuario);
	List<MsUsuarios> getNativeSQLMsUsuarios(String queryString, Object[] params);
	List<MsUsuarios> getActivasMsUsuarios();
	List<MsUsuarios> getDesactivasMsUsuarios();
	Long getMaxIdVal();
	List<MsUsuarios> getByIdMsUsuarios(String username);
	List<MsUsuarios> getXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado, int iniciar, int max);
	List<MsUsuarios> getXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado);
	long getTotalXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado);
	List<MsUsuarios> getXRoles(String[] roles);
	List<MsUsuarios> getActivasMsUsuariosCero();
	List<MsUsuarios> getXRoles(String[] roles, Long idunidad);
	List<MsUsuarios> getListaIdusuario();
}