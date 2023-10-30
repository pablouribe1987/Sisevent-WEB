package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.MsActividadesDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsActividades;

/**
 * MS_ACTIVIDADES REPOSITORIO: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS TAREAS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class MsActividadesDaoImp extends
		AbstractJpaCRUDDao<MsActividades, Long> implements
		MsActividadesDao {

	private static final Logger log = Logger.getLogger(MsActividadesDaoImp.class.getName());

	public MsActividadesDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsActividadesDaoImp");
	}
	
	public MsActividadesDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsActividadesDaoImp");
	}
	
	@Transactional
	public void saveMsActividades(MsActividades param) {
		super.save(param);
	}

	@Transactional
	public void updateMsActividades(MsActividades param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsActividades(MsActividades param) {
		super.delete(param);
	}

	public List<MsActividades> getAllMsActividades() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsActividades getMsActividades(Long id) {
		return super.findById(id);
	}

	public List<MsActividades> getNativeSQLMsActividades(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsActividades> getDomainClass() {
		return MsActividades.class;
	}

	public List<MsActividades> getActivasMsActividades() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<MsActividades> getActivasMsActividadesCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<MsActividades> getDesactivasMsActividades() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<MsActividades> getByIdMsActividades(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idactividades = ? order by t.idactividades asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsActividades> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idactividades) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<MsActividades> getXFiltro(Long idactividades,Long idtareas,String actividad) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idactividades != null) {
			sb.append("and t.idactividades = ? ");
			hs.add(idactividades);
		}
		if (idtareas != null) {
			sb.append("and t.idtareas = ? ");
			hs.add(idtareas);
		}
		if (actividad != null && actividad.trim().length() > 0) {
			sb.append("and t.actividad = ? ");
			hs.add(actividad);
		}		
		// sb.append("order by t.idactividades desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsActividades> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsActividades> getXFiltro(Long idactividades,Long idtareas,String actividad, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idactividades != null) {
					sb.append("and t.idactividades = ? ");
					hs.add(idactividades);
				}
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (actividad != null && actividad.trim().length() > 0) {
					sb.append("and t.actividad = ? ");
					hs.add(actividad);
				}
		// sb.append("order by t.idactividades desc ");

		List<MsActividades> lista = null;
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
	public long getTotalXFiltro(Long idactividades,Long idtareas,String actividad) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idactividades) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idactividades != null) {
					sb.append("and t.idactividades = ? ");
					hs.add(idactividades);
				}
				if (idtareas != null) {
					sb.append("and t.idtareas = ? ");
					hs.add(idtareas);
				}
				if (actividad != null && actividad.trim().length() > 0) {
					sb.append("and t.actividad = ? ");
					hs.add(actividad);
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