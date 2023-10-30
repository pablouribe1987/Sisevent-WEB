package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.TdCategEventoDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdCategEvento;

/**
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdCategEventoDaoImp extends
		AbstractJpaCRUDDao<TdCategEvento, Long> implements
		TdCategEventoDao {

	private static final Logger log = Logger.getLogger(TdCategEventoDaoImp.class.getName());

	public TdCategEventoDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdCategEventoDaoImp");
	}
	
	public TdCategEventoDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdCategEventoDaoImp");
	}
	
	@Transactional
	public void saveTdCategEvento(TdCategEvento param) {
		super.save(param);
	}

	@Transactional
	public void updateTdCategEvento(TdCategEvento param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdCategEvento(TdCategEvento param) {
		super.delete(param);
	}

	public List<TdCategEvento> getAllTdCategEvento() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdCategEvento getTdCategEvento(Long id) {
		return super.findById(id);
	}

	public List<TdCategEvento> getNativeSQLTdCategEvento(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdCategEvento> getDomainClass() {
		return TdCategEvento.class;
	}

	public List<TdCategEvento> getActivasTdCategEvento() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdCategEvento> getActivasTdCategEventoCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdCategEvento> getDesactivasTdCategEvento() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdCategEvento> getByIdTdCategEvento(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.iditinerario = ? order by t.iditinerario asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdCategEvento> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.iditinerario) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdCategEvento> getXFiltro(Long idcategevento,Long idevent,Long idcategorias) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idcategevento != null) {
			sb.append("and t.idcategevento = ? ");
			hs.add(idcategevento);
		}
		if (idevent != null) {
			sb.append("and t.idevent = ? ");
			hs.add(idevent);
		}
		if (idcategorias != null) {
			sb.append("and t.idcategorias = ? ");
			hs.add(idcategorias);
		}
			
		// sb.append("order by t.iditinerario desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdCategEvento> lista = super.find(sb.toString(), param);

		return lista;
	}

//	@Override
//	public List<MsCategEvento> getXFiltro(Long iditinerario,Long idevent,Long codpais,Integer coddpto,Integer codprov, int iniciar, int max) {
//		StringBuffer sb = new StringBuffer(100);
//		List<Object> hs = new ArrayList<Object>();
//		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");
//
//		
//				if (iditinerario != null) {
//					sb.append("and t.iditinerario = ? ");
//					hs.add(iditinerario);
//				}
//				if (idevent != null) {
//					sb.append("and t.idevent = ? ");
//					hs.add(idevent);
//				}
//				if (codpais != null) {
//					sb.append("and t.codpais = ? ");
//					hs.add(codpais);
//				}
//				if (coddpto != null) {
//					sb.append("and t.coddpto = ? ");
//					hs.add(coddpto);
//				}
//				if (codprov != null) {
//					sb.append("and t.codprov = ? ");
//					hs.add(codprov);
//				}
//		// sb.append("order by t.iditinerario desc ");
//
//		List<MsCategEvento> lista = null;
//		if (hs.size() > 0) {
//			Object param[] = new Object[hs.size()];
//			hs.toArray(param);
//			lista = super.findLimitedResult(sb.toString(), iniciar, (max + 1), param);
//			return lista;
//		} else {
//			lista = super.findLimitedResult(sb.toString(), iniciar, (max + 1));
//			return lista;
//		}
//	}

//	@Override
//	public long getTotalXFiltro(Long iditinerario,Long idevent,Long codpais,Integer coddpto,Integer codprov) {
//
//		StringBuffer sb = new StringBuffer(100);
//		List<Object> hs = new ArrayList<Object>();
//		sb.append("select count(t.iditinerario) from " + getDomainClass().getName() + " t where t.estado >= 1 ");
//
//		
//				if (iditinerario != null) {
//					sb.append("and t.iditinerario = ? ");
//					hs.add(iditinerario);
//				}
//				if (idevent != null) {
//					sb.append("and t.idevent = ? ");
//					hs.add(idevent);
//				}
//				if (codpais != null) {
//					sb.append("and t.codpais = ? ");
//					hs.add(codpais);
//				}
//				if (coddpto != null) {
//					sb.append("and t.coddpto = ? ");
//					hs.add(coddpto);
//				}
//				if (codprov != null) {
//					sb.append("and t.codprov = ? ");
//					hs.add(codprov);
//				}
//						
//		if (hs.size() > 0) {
//			Object param[] = new Object[hs.size()];
//			hs.toArray(param);
//			Object o = super.findUniqueResultObject(sb.toString(), param);
//			long retorno = 0;
//			if (o instanceof java.lang.Long) {
//				retorno = ((Long) o).longValue();
//			}
//			return retorno;
//		} else {
//			Object o = super.findUniqueResultObject(sb.toString());
//			long retorno = 0;
//			if (o instanceof java.lang.Long) {
//				retorno = ((Long) o).longValue();
//			}
//			return retorno;
//		}
//	}
}