package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.TdAnexosDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdAnexos;

/**
 * TD_ANEXOS REPOSITORIO: ANEXOS DE LOS MOVIMIENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 21:03
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 22/12/2020 21:03   / Creación de la clase    /
 * 
 */
@Repository
public class TdAnexosDaoImp extends
		AbstractJpaCRUDDao<TdAnexos, Long> implements
		TdAnexosDao {

	private static final Logger log = Logger.getLogger(TdAnexosDaoImp.class.getName());

	public TdAnexosDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdAnexosDaoImp");
	}
	
	public TdAnexosDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdAnexosDaoImp");
	}
	
	@Transactional
	public void saveTdAnexos(TdAnexos param) {
		super.save(param);
	}

	@Transactional
	public void updateTdAnexos(TdAnexos param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdAnexos(TdAnexos param) {
		super.delete(param);
	}

	public List<TdAnexos> getAllTdAnexos() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdAnexos getTdAnexos(Long id) {
		return super.findById(id);
	}

	public List<TdAnexos> getNativeSQLTdAnexos(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdAnexos> getDomainClass() {
		return TdAnexos.class;
	}

	public List<TdAnexos> getActivasTdAnexos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}

	public List<TdAnexos> getDesactivasTdAnexos() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdAnexos> getByIdTdAnexos(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idanexo = ? order by t.idanexo asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdAnexos> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idanexo) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdAnexos> getXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idsacc != null) {
			sb.append("and t.idsacc = ? ");
			hs.add(idsacc);
		}
		if (filename != null && filename.trim().length() > 0) {
			sb.append("and t.filename = ? ");
			hs.add(filename);
		}
		if (filenameoriginal != null && filenameoriginal.trim().length() > 0) {
			sb.append("and t.filenameoriginal = ? ");
			hs.add(filenameoriginal);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idusuario desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdAnexos> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdAnexos> getXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		
				if (idsacc != null) {
					sb.append("and t.idsacc = ? ");
					hs.add(idsacc);
				}
				if (filename != null && filename.trim().length() > 0) {
					sb.append("and t.filename = ? ");
					hs.add(filename);
				}
				if (filenameoriginal != null && filenameoriginal.trim().length() > 0) {
					sb.append("and t.filenameoriginal = ? ");
					hs.add(filenameoriginal);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idusuario desc ");

		List<TdAnexos> lista = null;
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
	public long getTotalXFiltro(Long idsacc,String filename,String filenameoriginal,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idanexo) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idsacc != null) {
					sb.append("and t.idsacc = ? ");
					hs.add(idsacc);
				}
				if (filename != null && filename.trim().length() > 0) {
					sb.append("and t.filename = ? ");
					hs.add(filename);
				}
				if (filenameoriginal != null && filenameoriginal.trim().length() > 0) {
					sb.append("and t.filenameoriginal = ? ");
					hs.add(filenameoriginal);
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
	public List<TdAnexos> getXFiltroXFlujoYAtencio(Long idsacc,Long idflujo) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idsacc != null) {
			sb.append("and t.idsacc = ? ");
			hs.add(idsacc);
		}
		
		if (idflujo != null) {
			sb.append("and t.idflujo = ? ");
			hs.add(idflujo);
		}
	   
		sb.append("order by t.idanexo asc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdAnexos> lista = super.find(sb.toString(), param);

		return lista;
	}
}