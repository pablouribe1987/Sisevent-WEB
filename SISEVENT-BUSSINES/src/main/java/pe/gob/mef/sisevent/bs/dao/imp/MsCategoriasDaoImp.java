package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.MsCategoriasDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsCategorias;

/**
 * MS_CATEGORIAS REPOSITORIO: CATEGORÍAS ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class MsCategoriasDaoImp extends
		AbstractJpaCRUDDao<MsCategorias, Long> implements
		MsCategoriasDao {

	private static final Logger log = Logger.getLogger(MsCategoriasDaoImp.class.getName());

	public MsCategoriasDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsCategoriasDaoImp");
	}
	
	public MsCategoriasDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsCategoriasDaoImp");
	}
	
	@Transactional
	public void saveMsCategorias(MsCategorias param) {
		super.save(param);
	}

	@Transactional
	public void updateMsCategorias(MsCategorias param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsCategorias(MsCategorias param) {
		super.delete(param);
	}

	public List<MsCategorias> getAllMsCategorias() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsCategorias getMsCategorias(Long id) {
		return super.findById(id);
	}

	public List<MsCategorias> getNativeSQLMsCategorias(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsCategorias> getDomainClass() {
		return MsCategorias.class;
	}

	public List<MsCategorias> getActivasMsCategorias() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<MsCategorias> getActivasMsCategoriasCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<MsCategorias> getDesactivasMsCategorias() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<MsCategorias> getByIdMsCategorias(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idcategorias = ? order by t.idcategorias asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsCategorias> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idcategorias) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<MsCategorias> getXFiltro(Long idcategorias,String categoria,String arraycamposocultos) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idcategorias != null) {
			sb.append("and t.idcategorias = ? ");
			hs.add(idcategorias);
		}
		if (categoria != null && categoria.trim().length() > 0) {
			sb.append("and t.categoria = ? ");
			hs.add(categoria);
		}
		if (arraycamposocultos != null) {
			sb.append("and t.arraycamposocultos = ? ");
			hs.add(arraycamposocultos);
		}		
		// sb.append("order by t.idcategorias desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsCategorias> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsCategorias> getXFiltro(Long idcategorias,String categoria,String arraycamposocultos, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idcategorias != null) {
					sb.append("and t.idcategorias = ? ");
					hs.add(idcategorias);
				}
				if (categoria != null && categoria.trim().length() > 0) {
					sb.append("and t.categoria = ? ");
					hs.add(categoria);
				}
				if (arraycamposocultos != null) {
					sb.append("and t.arraycamposocultos = ? ");
					hs.add(arraycamposocultos);
				}
		// sb.append("order by t.idcategorias desc ");

		List<MsCategorias> lista = null;
		if (hs.size() > 0) {
			Object param[] = new Object[hs.size()];
			hs.toArray(param);
			lista = super.findLimitedResult(sb.toString(), iniciar, (max + 1), param);
			return lista;
		} else {
			lista = super.findLimitedResult(sb.toString(), iniciar, (max + 1));
			return lista;
		}
	}

	@Override
	public long getTotalXFiltro(Long idcategorias,String categoria,String arraycamposocultos) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idcategorias) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idcategorias != null) {
					sb.append("and t.idcategorias = ? ");
					hs.add(idcategorias);
				}
				if (categoria != null && categoria.trim().length() > 0) {
					sb.append("and t.categoria = ? ");
					hs.add(categoria);
				}
				if (arraycamposocultos != null) {
					sb.append("and t.arraycamposocultos = ? ");
					hs.add(arraycamposocultos);
				}
						
		if (hs.size() > 0) {
			Object param[] = new Object[hs.size()];
			hs.toArray(param);
			Object o = super.findUniqueResultObject(sb.toString(), param);
			long retorno = 0;
			if (o instanceof java.lang.Long) {
				retorno = ((Long) o).longValue();
			}
			return retorno;
		} else {
			Object o = super.findUniqueResultObject(sb.toString());
			long retorno = 0;
			if (o instanceof java.lang.Long) {
				retorno = ((Long) o).longValue();
			}
			return retorno;
		}
	}
	
	@Override
	public List<MsCategorias> getListaIdcategorias() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("SELECT tablaa FROM MsCategorias tablaa ");
		sb.append("WHERE tablaa.estado > 1 ");
		sb.append("ORDER BY tablaa.idcategorias asc ");
		return super.find(sb.toString());
	}
}