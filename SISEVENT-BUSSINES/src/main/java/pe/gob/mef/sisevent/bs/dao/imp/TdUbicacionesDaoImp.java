package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.TdUbicacionesDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdUbicaciones;

/**
 * TD_UBICACIONES REPOSITORIO: UBICACIONES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdUbicacionesDaoImp extends
		AbstractJpaCRUDDao<TdUbicaciones, Long> implements
		TdUbicacionesDao {

	private static final Logger log = Logger.getLogger(TdUbicacionesDaoImp.class.getName());

	public TdUbicacionesDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdUbicacionesDaoImp");
	}
	
	public TdUbicacionesDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdUbicacionesDaoImp");
	}
	
	@Transactional
	public void saveTdUbicaciones(TdUbicaciones param) {
		super.save(param);
	}

	@Transactional
	public void updateTdUbicaciones(TdUbicaciones param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdUbicaciones(TdUbicaciones param) {
		super.delete(param);
	}

	public List<TdUbicaciones> getAllTdUbicaciones() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdUbicaciones getTdUbicaciones(Long id) {
		return super.findById(id);
	}

	public List<TdUbicaciones> getNativeSQLTdUbicaciones(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdUbicaciones> getDomainClass() {
		return TdUbicaciones.class;
	}

	public List<TdUbicaciones> getActivasTdUbicaciones() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdUbicaciones> getActivasTdUbicacionesCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdUbicaciones> getDesactivasTdUbicaciones() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdUbicaciones> getByIdTdUbicaciones(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idubicaciones = ? order by t.idubicaciones asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdUbicaciones> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idubicaciones) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdUbicaciones> getXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idubicaciones != null) {
			sb.append("and t.idubicaciones = ? ");
			hs.add(idubicaciones);
		}
		if (idevent != null) {
			sb.append("and t.idevent = ? ");
			hs.add(idevent);
		}
		if (ubicacion != null) {
			sb.append("and t.ubicacion = ? ");
			hs.add(ubicacion);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idubicaciones desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdUbicaciones> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdUbicaciones> getXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idubicaciones != null) {
					sb.append("and t.idubicaciones = ? ");
					hs.add(idubicaciones);
				}
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
				}
				if (ubicacion != null) {
					sb.append("and t.ubicacion = ? ");
					hs.add(ubicacion);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idubicaciones desc ");

		List<TdUbicaciones> lista = null;
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
	public long getTotalXFiltro(Long idubicaciones,Long idevent,String ubicacion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idubicaciones) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idubicaciones != null) {
					sb.append("and t.idubicaciones = ? ");
					hs.add(idubicaciones);
				}
				if (idevent != null) {
					sb.append("and t.idevent = ? ");
					hs.add(idevent);
				}
				if (ubicacion != null) {
					sb.append("and t.ubicacion = ? ");
					hs.add(ubicacion);
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