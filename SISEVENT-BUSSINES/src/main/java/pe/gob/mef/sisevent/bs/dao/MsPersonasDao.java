package pe.gob.mef.sisevent.bs.dao;

import java.util.List;

import pe.gob.mef.sisevent.bs.domain.MsPersonas;

/**
*
* MS_PERSONAS REPOSITORIO INTERFACE: PERSONAS NATURALES
*
*
* @author  Carlos Aguilar
* @version 2.0, 22/12/2020 17:45
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 22/12/2020 17:45         / Creación de la clase  /
* 
*/
public interface MsPersonasDao {
	void saveMsPersonas(MsPersonas param);
	void updateMsPersonas(MsPersonas param);
	void deleteMsPersonas(MsPersonas param);
	List<MsPersonas> getAllMsPersonas();
	MsPersonas getMsPersonas(Long idperson);
	List<MsPersonas> getNativeSQLMsPersonas(String queryString, Object[] params);
	List<MsPersonas> getActivasMsPersonas();
	List<MsPersonas> getDesactivasMsPersonas();
	Long getMaxIdVal();
	
	List<MsPersonas> getXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String sexo,Long tipodocumento,String documentoNumero,String telefono,String celular,String direccion,Integer estado, int iniciar, int max);
	List<MsPersonas> getXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String sexo,Long tipodocumento,String documentoNumero,String telefono,String celular,String direccion,Integer estado);
	long getTotalXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String sexo,Long tipodocumento,String documentoNumero,String telefono,String celular,String direccion,Integer estado);
	List<MsPersonas> getActivasMsPersonasCero();
	
}