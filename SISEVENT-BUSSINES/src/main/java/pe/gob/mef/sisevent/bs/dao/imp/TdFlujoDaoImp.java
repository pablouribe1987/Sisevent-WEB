package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.TdFlujoDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdFlujo;

/**
 * TD_FLUJO REPOSITORIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 21:03
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 22/12/2020 21:03   / Creación de la clase    /
 * 
 */
@Repository
public class TdFlujoDaoImp extends
		AbstractJpaCRUDDao<TdFlujo, Long> implements
		TdFlujoDao {

	private static final Logger log = Logger.getLogger(TdFlujoDaoImp.class.getName());

	public TdFlujoDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdFlujoDaoImp");
	}
	
	public TdFlujoDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdFlujoDaoImp");
	}
	
	@Transactional
	public void saveTdFlujo(TdFlujo param) {
		super.save(param);
	}

	@Transactional
	public void updateTdFlujo(TdFlujo param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdFlujo(TdFlujo param) {
		super.delete(param);
	}

	public List<TdFlujo> getAllTdFlujo() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdFlujo getTdFlujo(Long id) {
		return super.findById(id);
	}

	public List<TdFlujo> getNativeSQLTdFlujo(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdFlujo> getDomainClass() {
		return TdFlujo.class;
	}

	public List<TdFlujo> getActivasTdFlujo() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}

	public List<TdFlujo> getDesactivasTdFlujo() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdFlujo> getByIdTdFlujo(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idflujo = ? order by t.idflujo asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdFlujo> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idflujo) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdFlujo> getXFiltro(Long idflujo,Long idflujopadre,Long idevent,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idflujo != null) {
			sb.append("and t.idflujo = ? ");
			hs.add(idflujo);
		}
		if (idflujopadre != null) {
			sb.append("and t.idflujopadre = ? ");
			hs.add(idflujopadre);
		}
		if (idevent != null) {
			sb.append("and t.idevent = ? ");
			hs.add(idevent);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idusuario desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdFlujo> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdFlujo> getXFiltro(Long idflujo,Long idflujopadre,Long idevent,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		
				if (idflujo != null) {
					sb.append("and t.idflujo = ? ");
					hs.add(idflujo);
				}
				if (idflujopadre != null) {
					sb.append("and t.idflujopadre = ? ");
					hs.add(idflujopadre);
				}
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idusuario desc ");

		List<TdFlujo> lista = null;
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
	public long getTotalXFiltro(Long idflujo,Long idflujopadre,Long idevent,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idflujo) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idflujo != null) {
					sb.append("and t.idflujo = ? ");
					hs.add(idflujo);
				}
				if (idflujopadre != null) {
					sb.append("and t.idflujopadre = ? ");
					hs.add(idflujopadre);
				}
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
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
	
	@Override
	public List<TdFlujo> getXFiltro(Long idevent) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		if (idevent != null) {
			sb.append("and t.idevent = ? ");
			hs.add(idevent);
		}
		
		sb.append("order by t.idflujo asc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdFlujo> lista = super.find(sb.toString(), param);

		return lista;
	}
}