package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.TdGruposDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdGrupos;

/**
 * TD_GRUPOS REPOSITORIO: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdGruposDaoImp extends
		AbstractJpaCRUDDao<TdGrupos, Long> implements
		TdGruposDao {

	private static final Logger log = Logger.getLogger(TdGruposDaoImp.class.getName());

	public TdGruposDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdGruposDaoImp");
	}
	
	public TdGruposDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdGruposDaoImp");
	}
	
	@Transactional
	public void saveTdGrupos(TdGrupos param) {
		super.save(param);
	}

	@Transactional
	public void updateTdGrupos(TdGrupos param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdGrupos(TdGrupos param) {
		super.delete(param);
	}

	public List<TdGrupos> getAllTdGrupos() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdGrupos getTdGrupos(Long id) {
		return super.findById(id);
	}

	public List<TdGrupos> getNativeSQLTdGrupos(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdGrupos> getDomainClass() {
		return TdGrupos.class;
	}

	public List<TdGrupos> getActivasTdGrupos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdGrupos> getActivasTdGruposCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdGrupos> getDesactivasTdGrupos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdGrupos> getByIdTdGrupos(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idgrupo = ? order by t.idgrupo asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdGrupos> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idgrupo) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdGrupos> getXFiltro(Long idgrupo,String grupo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idgrupo != null) {
			sb.append("and t.idgrupo = ? ");
			hs.add(idgrupo);
		}
		if (grupo != null && grupo.trim().length() > 0) {
			sb.append("and t.grupo = ? ");
			hs.add(grupo);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idgrupo desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdGrupos> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdGrupos> getXFiltro(Long idgrupo,String grupo,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idgrupo != null) {
					sb.append("and t.idgrupo = ? ");
					hs.add(idgrupo);
				}
				if (grupo != null && grupo.trim().length() > 0) {
					sb.append("and t.grupo = ? ");
					hs.add(grupo);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idgrupo desc ");

		List<TdGrupos> lista = null;
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
	public long getTotalXFiltro(Long idgrupo,String grupo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idgrupo) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idgrupo != null) {
					sb.append("and t.idgrupo = ? ");
					hs.add(idgrupo);
				}
				if (grupo != null && grupo.trim().length() > 0) {
					sb.append("and t.grupo = ? ");
					hs.add(grupo);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
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
}