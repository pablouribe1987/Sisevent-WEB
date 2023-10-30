package pe.gob.mef.sisevent.bs.dao.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.TdFeriadosDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdFeriados;

/**
 * TD_FERIADOS REPOSITORIO: DÍAS NO LABORABLES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2021 02:31
 * 
 *          /----------Nombre----------/---------fecha------/------------Motivo---------/ /Carlos Aguilar Chamochumbi/
 *          11/01/2021 02:31 / Creación de la clase /
 * 
 */
@Repository
public class TdFeriadosDaoImp extends AbstractJpaCRUDDao<TdFeriados, Long> implements TdFeriadosDao {

	private static final Logger log = Logger.getLogger(TdFeriadosDaoImp.class.getName());

	public TdFeriadosDaoImp() {
		log.log(Level.INFO, null, "INICIALIZANDO JPA TEMPLATE PARA TdFeriadosDaoImp");
	}

	public TdFeriadosDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, null, "INICIALIZANDO JPA TEMPLATE PARA TdFeriadosDaoImp");
	}

	@Transactional
	public void saveTdFeriados(TdFeriados param) {
		super.save(param);
	}

	@Transactional
	public void updateTdFeriados(TdFeriados param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdFeriados(TdFeriados param) {
		super.delete(param);
	}

	public List<TdFeriados> getAllTdFeriados() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdFeriados getTdFeriados(Long id) {
		return super.findById(id);
	}

	public List<TdFeriados> getNativeSQLTdFeriados(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdFeriados> getDomainClass() {
		return TdFeriados.class;
	}

	public List<TdFeriados> getActivasTdFeriados() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado > 0");
	}
	
	@Override
	public List<TdFeriados> getActivasTdFeriadosCero() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado >= 0");
	}

	public List<TdFeriados> getDesactivasTdFeriados() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<TdFeriados> getByIdTdFeriados(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName() + " t where t.idferiado = ? order by t.idferiado asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdFeriados> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select max(t.idferiado) as maximo from " + getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence++;
		return sequence;
	}

	@Override
	public List<TdFeriados> getXFiltro(Timestamp fechaFeriado, String descricion, Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		if (fechaFeriado != null) {
			sb.append("and t.fechaFeriado = ? ");
			hs.add(fechaFeriado);
		}
		if (descricion != null) {
			sb.append("and t.descricion = ? ");
			hs.add(descricion);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}
		// sb.append("order by t.idusuario desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdFeriados> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdFeriados> getXFiltro(Timestamp fechaFeriado, String descricion, Integer estado, int iniciar,
			int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		if (fechaFeriado != null) {
			sb.append("and t.fechaFeriado = ? ");
			hs.add(fechaFeriado);
		}
		if (descricion != null) {
			sb.append("and t.descricion = ? ");
			hs.add(descricion);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}
		// sb.append("order by t.idusuario desc ");

		List<TdFeriados> lista = null;
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
	public long getTotalXFiltro(Timestamp fechaFeriado, String descricion, Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idferiado) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		if (fechaFeriado != null) {
			sb.append("and t.fechaFeriado = ? ");
			hs.add(fechaFeriado);
		}
		if (descricion != null) {
			sb.append("and t.descricion = ? ");
			hs.add(descricion);
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