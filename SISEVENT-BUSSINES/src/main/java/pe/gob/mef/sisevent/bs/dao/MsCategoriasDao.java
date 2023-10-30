package pe.gob.mef.sisevent.bs.dao;

import java.util.List;
import pe.gob.mef.sisevent.bs.domain.MsCategorias;

/**
*
* MS_CATEGORIAS REPOSITORIO INTERFACE: CATEGORÍAS ASIGADOS A LOS EVENTOS
*
*
* @author  Carlos Aguilar
* @version 2.0, 29/04/2023 22:40
* 
*          /----------Nombre------------------------/---------fecha------/----------Motivo---------/
*          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:40         / Creación de la clase  /
* 
*/
public interface MsCategoriasDao {
	void saveMsCategorias(MsCategorias param);
	void updateMsCategorias(MsCategorias param);
	void deleteMsCategorias(MsCategorias param);
	List<MsCategorias> getAllMsCategorias();
	MsCategorias getMsCategorias(Long idcategorias);
	List<MsCategorias> getNativeSQLMsCategorias(String queryString, Object[] params);
	List<MsCategorias> getActivasMsCategorias();
	List<MsCategorias> getActivasMsCategoriasCero();
	List<MsCategorias> getDesactivasMsCategorias();
	Long getMaxIdVal();
	
	List<MsCategorias> getXFiltro(Long idcategorias,String categoria,String arraycamposocultos, int iniciar, int max);
	List<MsCategorias> getXFiltro(Long idcategorias,String categoria,String arraycamposocultos);
	long getTotalXFiltro(Long idcategorias,String categoria,String arraycamposocultos);
	List<MsCategorias> getListaIdcategorias();
	
}