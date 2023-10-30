package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.TdGruposflujosDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdGruposflujos;

/**
 * TD_GRUPOSFLUJOS REPOSITORIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdGruposflujosDaoImp extends
		AbstractJpaCRUDDao<TdGruposflujos, Long> implements
		TdGruposflujosDao {

	private static final Logger log = Logger.getLogger(TdGruposflujosDaoImp.class.getName());

	public TdGruposflujosDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdGruposflujosDaoImp");
	}
	
	public TdGruposflujosDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdGruposflujosDaoImp");
	}
	
	@Transactional
	public void saveTdGruposflujos(TdGruposflujos param) {
		super.save(param);
	}

	@Transactional
	public void updateTdGruposflujos(TdGruposflujos param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdGruposflujos(TdGruposflujos param) {
		super.delete(param);
	}

	public List<TdGruposflujos> getAllTdGruposflujos() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdGruposflujos getTdGruposflujos(Long id) {
		return super.findById(id);
	}

	public List<TdGruposflujos> getNativeSQLTdGruposflujos(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdGruposflujos> getDomainClass() {
		return TdGruposflujos.class;
	}

	public List<TdGruposflujos> getActivasTdGruposflujos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdGruposflujos> getActivasTdGruposflujosCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdGruposflujos> getDesactivasTdGruposflujos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdGruposflujos> getByIdTdGruposflujos(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idgruposflujos = ? order by t.idgruposflujos asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdGruposflujos> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idgruposflujos) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdGruposflujos> getXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idgrupo) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idtareas != null) {
			sb.append("and t.idtareas = ? ");
			hs.add(idtareas);
		}
		if (idgrupo != null) {
			sb.append("and t.idgrupo = ? ");
			hs.add(idgrupo);
		}
		if (idunidadDestino != null) {
			sb.append("and t.idunidadDestino = ? ");
			hs.add(idunidadDestino);
		}
		if (iduserDestino != null) {
			sb.append("and t.iduserDestino = ? ");
			hs.add(iduserDestino);
		}		
		// sb.append("order by t.idgruposflujos desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdGruposflujos> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdGruposflujos> getXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idgrupo, 
			int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (idgrupo != null) {
					sb.append("and t.idgrupo = ? ");
					hs.add(idgrupo);
				}
				if (idunidadDestino != null) {
					sb.append("and t.idunidadDestino = ? ");
					hs.add(idunidadDestino);
				}
				if (iduserDestino != null) {
					sb.append("and t.iduserDestino = ? ");
					hs.add(iduserDestino);
				}
		// sb.append("order by t.idgruposflujos desc ");

		List<TdGruposflujos> lista = null;
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
	public long getTotalXFiltro(Long idtareas,Long idunidadDestino,Long iduserDestino, Long idgrupo) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idgruposflujos) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (idgrupo != null) {
					sb.append("and t.idgrupo = ? ");
					hs.add(idgrupo);
				}
				if (idunidadDestino != null) {
					sb.append("and t.idunidadDestino = ? ");
					hs.add(idunidadDestino);
				}
				if (iduserDestino != null) {
					sb.append("and t.iduserDestino = ? ");
					hs.add(iduserDestino);
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