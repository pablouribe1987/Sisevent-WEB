package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.sisevent.bs.dao.TdParticipantesDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.TdParticipantes;

/**
 * TD_PARTICIPANTES REPOSITORIO: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:40
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 29/04/2023 22:40   / Creación de la clase    /
 * 
 */
@Repository
public class TdParticipantesDaoImp extends
		AbstractJpaCRUDDao<TdParticipantes, Long> implements
		TdParticipantesDao {

	private static final Logger log = Logger.getLogger(TdParticipantesDaoImp.class.getName());

	public TdParticipantesDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdParticipantesDaoImp");
	}
	
	public TdParticipantesDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA TdParticipantesDaoImp");
	}
	
	@Transactional
	public void saveTdParticipantes(TdParticipantes param) {
		super.save(param);
	}

	@Transactional
	public void updateTdParticipantes(TdParticipantes param) {
		super.update(param);
	}

	@Transactional
	public void deleteTdParticipantes(TdParticipantes param) {
		super.delete(param);
	}

	public List<TdParticipantes> getAllTdParticipantes() {
		return super.find("from " + getDomainClass().getName());
	}

	public TdParticipantes getTdParticipantes(Long id) {
		return super.findById(id);
	}

	public List<TdParticipantes> getNativeSQLTdParticipantes(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<TdParticipantes> getDomainClass() {
		return TdParticipantes.class;
	}

	public List<TdParticipantes> getActivasTdParticipantes() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	public List<TdParticipantes> getActivasTdParticipantesCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<TdParticipantes> getDesactivasTdParticipantes() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<TdParticipantes> getByIdTdParticipantes(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idparticipantes = ? order by t.idparticipantes asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<TdParticipantes> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idparticipantes) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<TdParticipantes> getXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idparticipantes != null) {
			sb.append("and t.idparticipantes = ? ");
			hs.add(idparticipantes);
		}
		if (idusuarioIdproveeIdperson != null) {
			sb.append("and t.idusuarioIdproveeIdperson = ? ");
			hs.add(idusuarioIdproveeIdperson);
		}
		if (nombresrazonsocial != null) {
			sb.append("and t.nombresrazonsocial = ? ");
			hs.add(nombresrazonsocial);
		}
		if (tipo != null) {
			sb.append("and t.tipo = ? ");
			hs.add(tipo);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		// sb.append("order by t.idparticipantes desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<TdParticipantes> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<TdParticipantes> getXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idparticipantes != null) {
					sb.append("and t.idparticipantes = ? ");
					hs.add(idparticipantes);
				}
				if (idusuarioIdproveeIdperson != null) {
					sb.append("and t.idusuarioIdproveeIdperson = ? ");
					hs.add(idusuarioIdproveeIdperson);
				}
				if (nombresrazonsocial != null) {
					sb.append("and t.nombresrazonsocial = ? ");
					hs.add(nombresrazonsocial);
				}
				if (tipo != null) {
					sb.append("and t.tipo = ? ");
					hs.add(tipo);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idparticipantes desc ");

		List<TdParticipantes> lista = null;
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
	public long getTotalXFiltro(Long idparticipantes,Long idusuarioIdproveeIdperson,String nombresrazonsocial,Integer tipo,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idparticipantes) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idparticipantes != null) {
					sb.append("and t.idparticipantes = ? ");
					hs.add(idparticipantes);
				}
				if (idusuarioIdproveeIdperson != null) {
					sb.append("and t.idusuarioIdproveeIdperson = ? ");
					hs.add(idusuarioIdproveeIdperson);
				}
				if (nombresrazonsocial != null) {
					sb.append("and t.nombresrazonsocial = ? ");
					hs.add(nombresrazonsocial);
				}
				if (tipo != null) {
					sb.append("and t.tipo = ? ");
					hs.add(tipo);
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
	
	//MPINARES 01092023 - INICIO 
		@Override
		public List<TdParticipantes> getXFiltro(Long idevent) {

			StringBuffer sb = new StringBuffer(100);
			List<Object> hs = new ArrayList<Object>();
			sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

			
			if (idevent != null) {
				sb.append("and t.idevent = ? ");
				hs.add(idevent);
			}
				
			// sb.append("order by t.idparticipantes desc ");

			Object param[] = new Object[hs.size()];
			hs.toArray(param);
			List<TdParticipantes> lista = super.find(sb.toString(), param);

			return lista;
		}
		//MPINARES 01092023 - FIN
}