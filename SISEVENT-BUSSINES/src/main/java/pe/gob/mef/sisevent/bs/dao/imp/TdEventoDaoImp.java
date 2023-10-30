package pe.gob.mef.sisevent.bs.dao.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.TdEventoDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdEvento;

/**
 * TD_EVENTO REPOSITORIO: EVENTO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdEventoDaoImp extends
		AbstractJpaCRUDDao<TdEvento, Long> implements
		TdEventoDao {

	private static final Logger log = Logger.getLogger(TdEventoDaoImp.class.getName());

	public TdEventoDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdEventoDaoImp");
	}
	
	public TdEventoDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdEventoDaoImp");
	}
	
	@Transactional
	public void saveTdEvento(TdEvento param) {
		super.save(param);
	}

	@Transactional
	public void updateTdEvento(TdEvento param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdEvento(TdEvento param) {
		super.delete(param);
	}

	public List<TdEvento> getAllTdEvento() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdEvento getTdEvento(Long id) {
		return super.findById(id);
	}

	public List<TdEvento> getNativeSQLTdEvento(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdEvento> getDomainClass() {
		return TdEvento.class;
	}

	public List<TdEvento> getActivasTdEvento() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdEvento> getActivasTdEventoCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdEvento> getDesactivasTdEvento() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdEvento> getByIdTdEvento(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idevent = ? order by t.idevent asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdEvento> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idevent) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdEvento> getXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idevent != null) {
			sb.append("and t.idevent = ? ");
			hs.add(idevent);
		}
		if (titulo != null) {
			sb.append("and t.titulo = ? ");
			hs.add(titulo);
		}
		if (fechaSoliIni != null) {
			sb.append("and t.fechaSoliIni = ? ");
			hs.add(fechaSoliIni);
		}
		if (idcategorias != null) {
			sb.append("and t.idcategorias = ? ");
			hs.add(idcategorias);
		}
		if (modalidad != null) {
			sb.append("and t.modalidad = ? ");
			hs.add(modalidad);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idevent desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdEvento> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdEvento> getXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
				}
				if (titulo != null) {
					sb.append("and t.titulo = ? ");
					hs.add(titulo);
				}
				if (fechaSoliIni != null) {
					sb.append("and t.fechaSoliIni = ? ");
					hs.add(fechaSoliIni);
				}
				if (idcategorias != null) {
					sb.append("and t.idcategorias = ? ");
					hs.add(idcategorias);
				}
				if (modalidad != null) {
					sb.append("and t.modalidad = ? ");
					hs.add(modalidad);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idevent desc ");

		List<TdEvento> lista = null;
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
	public long getTotalXFiltro(Long idevent,String titulo,Timestamp fechaSoliIni,Long idcategorias,String modalidad,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idevent) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
				}
				if (titulo != null) {
					sb.append("and t.titulo = ? ");
					hs.add(titulo);
				}
				if (fechaSoliIni != null) {
					sb.append("and t.fechaSoliIni = ? ");
					hs.add(fechaSoliIni);
				}
				if (idcategorias != null) {
					sb.append("and t.idcategorias = ? ");
					hs.add(idcategorias);
				}
				if (modalidad != null) {
					sb.append("and t.modalidad = ? ");
					hs.add(modalidad);
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
 	public List<TdEvento> getListaIdevent() {
 		StringBuffer sb = new StringBuffer(100);
 		sb.append("SELECT tablaa FROM TdEvento tablaa ");
 		sb.append("WHERE tablaa.estado > 1 ");
 		sb.append("ORDER BY tablaa.idevent asc ");
 		return super.find(sb.toString());
 	}
}