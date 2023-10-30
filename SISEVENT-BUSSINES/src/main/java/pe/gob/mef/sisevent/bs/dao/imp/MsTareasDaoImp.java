package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.MsTareasDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsTareas;

/**
 * MS_TAREAS REPOSITORIO: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS DERIVACIONES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class MsTareasDaoImp extends
		AbstractJpaCRUDDao<MsTareas, Long> implements
		MsTareasDao {

	private static final Logger log = Logger.getLogger(MsTareasDaoImp.class.getName());

	public MsTareasDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsTareasDaoImp");
	}
	
	public MsTareasDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsTareasDaoImp");
	}
	
	@Transactional
	public void saveMsTareas(MsTareas param) {
		super.save(param);
	}

	@Transactional
	public void updateMsTareas(MsTareas param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsTareas(MsTareas param) {
		super.delete(param);
	}

	public List<MsTareas> getAllMsTareas() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsTareas getMsTareas(Long id) {
		return super.findById(id);
	}

	public List<MsTareas> getNativeSQLMsTareas(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsTareas> getDomainClass() {
		return MsTareas.class;
	}

	public List<MsTareas> getActivasMsTareas() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<MsTareas> getActivasMsTareasCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<MsTareas> getDesactivasMsTareas() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<MsTareas> getByIdMsTareas(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idtareas = ? order by t.idtareas asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsTareas> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idtareas) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<MsTareas> getXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idtareas != null) {
			sb.append("and t.idtareas = ? ");
			hs.add(idtareas);
		}
		if (tarea != null && tarea.trim().length() > 0) {
			sb.append("and t.tarea = ? ");
			hs.add(tarea);
		}
		if (tiempo != null) {
			sb.append("and t.tiempo = ? ");
			hs.add(tiempo);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idtareas desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsTareas> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsTareas> getXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (tarea != null && tarea.trim().length() > 0) {
					sb.append("and t.tarea = ? ");
					hs.add(tarea);
				}
				if (tiempo != null) {
					sb.append("and t.tiempo = ? ");
					hs.add(tiempo);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idtareas desc ");

		List<MsTareas> lista = null;
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
	public long getTotalXFiltro(Long idtareas,String tarea,Long tiempo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idtareas) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (tarea != null && tarea.trim().length() > 0) {
					sb.append("and t.tarea = ? ");
					hs.add(tarea);
				}
				if (tiempo != null) {
					sb.append("and t.tiempo = ? ");
					hs.add(tiempo);
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