package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsUnidadesOrg;

/**
*
* MS_UNIDADES_ORG REPOSITORIO INTERFACE: UNIDADES ORGÁNICAS
*
*
* @author  Carlos Aguilar
* @version 2.0, 25/11/2020 23:37
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 25/11/2020 23:37         / Creación de la clase  /
* 
*/
public interface MsUnidadesOrgDao {
	void saveMsUnidadesOrg(MsUnidadesOrg param);
	void updateMsUnidadesOrg(MsUnidadesOrg param);
	void deleteMsUnidadesOrg(MsUnidadesOrg param);
	List<MsUnidadesOrg> getAllMsUnidadesOrg();
	MsUnidadesOrg getMsUnidadesOrg(Long idunidad);
	List<MsUnidadesOrg> getNativeSQLMsUnidadesOrg(String queryString, Object[] params);
	List<MsUnidadesOrg> getActivasMsUnidadesOrg();
	List<MsUnidadesOrg> getDesactivasMsUnidadesOrg();
	Long getMaxIdVal();
	List<MsUnidadesOrg> getByPadresMsUnidadesOrg();
	List<MsUnidadesOrg> getByIdPadreMsUnidadesOrg(Long idPadre);
	List<MsUnidadesOrg> getByNombreMsUnidadesOrg(String descripcion);
	List<MsUnidadesOrg> getActivasMsUnidadesOrgCero();
}