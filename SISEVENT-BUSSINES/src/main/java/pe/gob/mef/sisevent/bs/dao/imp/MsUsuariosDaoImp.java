package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsUsuariosDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsUsuarios;

@Repository
public class MsUsuariosDaoImp extends AbstractJpaCRUDDao<MsUsuarios, Long> implements MsUsuariosDao {
	private static final Logger log = Logger.getLogger(MsUsuariosDaoImp.class.getName());

	public MsUsuariosDaoImp() {
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsUsuariosDaoImp");
	}

	public MsUsuariosDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsUsuariosDaoImp");
	}

	@Transactional
	public void saveMsUsuarios(MsUsuarios param) {
		super.save(param);
	}

	@Transactional
	public void updateMsUsuarios(MsUsuarios param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsUsuarios(MsUsuarios param) {
		super.delete(param);
	}

	public List<MsUsuarios> getAllMsUsuarios() {
		return super.find("from " + this.getDomainClass().getName());
	}

	public MsUsuarios getMsUsuarios(Long id) {
		return (MsUsuarios) super.findById(id);
	}

	public List<MsUsuarios> getNativeSQLMsUsuarios(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsUsuarios> getDomainClass() {
		return MsUsuarios.class;
	}

	public List<MsUsuarios> getActivasMsUsuarios() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado > 0");
	}
	
	@Override
	public List<MsUsuarios> getActivasMsUsuariosCero() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado >= 0");
	}

	public List<MsUsuarios> getDesactivasMsUsuarios() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<MsUsuarios> getByIdMsUsuarios(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.idusuario = ? order by t.idusuario asc ");
		Object[] param = new Object[]{id};
		List<MsUsuarios> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select max(t.idusuario) as maximo from " + this.getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}

		sequence = sequence + 1L;
		return sequence;
	}

	public List<MsUsuarios> getByIdMsUsuarios(String username) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		List<Object> hs = new ArrayList<Object>();
		if (username != null) {
			sb.append("and t.username = ? ");
			hs.add(username);
		}

		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<MsUsuarios> lista = super.find(sb.toString(), param);
		return lista;
	}

	public List<MsUsuarios> getXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		if (username != null && username.trim().length() > 0) {
			sb.append("and t.username = ? ");
			hs.add(username);
		}

		if (nombres != null && nombres.trim().length() > 0) {
			sb.append("and t.nombres = ? ");
			hs.add(nombres);
		}

		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sb.append("and t.apellidoPaterno = ? ");
			hs.add(apellidoPaterno);
		}

		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			sb.append("and t.apellidoMaterno = ? ");
			hs.add(apellidoMaterno);
		}

		if (iduserModif != null) {
			sb.append("and t.iduserModif = ? ");
			hs.add(iduserModif);
		}

		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}

		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<MsUsuarios> lista = super.find(sb.toString(), param);
		return lista;
	}

	public List<MsUsuarios> getXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where  >= 1 ");
		if (username != null && username.trim().length() > 0) {
			sb.append("and t.username = ? ");
			hs.add(username);
		}

		if (nombres != null && nombres.trim().length() > 0) {
			sb.append("and t.nombres = ? ");
			hs.add(nombres);
		}

		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sb.append("and t.apellidoPaterno = ? ");
			hs.add(apellidoPaterno);
		}

		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			sb.append("and t.apellidoMaterno = ? ");
			hs.add(apellidoMaterno);
		}

		if (iduserModif != null) {
			sb.append("and t.iduserModif = ? ");
			hs.add(iduserModif);
		}

		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}

		List<MsUsuarios> lista = null;
		if (hs.size() > 0) {
			Object[] param = new Object[hs.size()];
			hs.toArray(param);
			lista = super.findLimitedResult(sb.toString(), iniciar, max + 1, param);
			return lista;
		} else {
			lista = super.findLimitedResult(sb.toString(), iniciar, max + 1);
			return lista;
		}
	}

	public long getTotalXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idusuario) from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		if (username != null && username.trim().length() > 0) {
			sb.append("and t.username = ? ");
			hs.add(username);
		}

		if (nombres != null && nombres.trim().length() > 0) {
			sb.append("and t.nombres = ? ");
			hs.add(nombres);
		}

		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sb.append("and t.apellidoPaterno = ? ");
			hs.add(apellidoPaterno);
		}

		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			sb.append("and t.apellidoMaterno = ? ");
			hs.add(apellidoMaterno);
		}

		if (iduserModif != null) {
			sb.append("and t.iduserModif = ? ");
			hs.add(iduserModif);
		}

		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}

		if (hs.size() > 0) {
			Object[] param = new Object[hs.size()];
			hs.toArray(param);
			Object o = super.findUniqueResultObject(sb.toString(), param);
			long retorno = 0L;
			if (o instanceof Long) {
				retorno = (Long) o;
			}

			return retorno;
		} else {
			Object o = super.findUniqueResultObject(sb.toString());
			long retorno = 0L;
			if (o instanceof Long) {
				retorno = (Long) o;
			}

			return retorno;
		}
	}
	
	@Override
	public List<MsUsuarios> getXRoles(String roles[]) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select distinct t from " + this.getDomainClass().getName() + " t, MsRoles r where t.estado >= 1 "
				+ "and r.estado >0 and t.username=r.username ");
		
		if (roles!=null && roles.length>0) {
			sb.append("and ( ");
			for (int i = 0; i < roles.length; i++) {
				if(i>0)
					sb.append(" or ");
				sb.append("r.rol = ? ");
				hs.add(roles[i]);
			}
			sb.append(") ");			
		}
		
		sb.append("order by t.apellidoPaterno || t.apellidoMaterno || t.nombres ");
		
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<MsUsuarios> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<MsUsuarios> getXRoles(String roles[], Long idunidad) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select distinct t from " + this.getDomainClass().getName() + " t, MsRoles r where t.estado >= 1 "
				+ "and r.estado >0 and t.username=r.username ");
		
		if (roles!=null && roles.length>0) {
			sb.append("and ( ");
			for (int i = 0; i < roles.length; i++) {
				if(i>0)
					sb.append(" or ");
				sb.append("r.rol = ? ");
				hs.add(roles[i]);
			}
			sb.append(") ");			
		}
		
		if(idunidad!=null && idunidad.longValue()>0){
			sb.append("and t.idunidad=? ");
			hs.add(idunidad);
		}		
		sb.append("order by t.apellidoPaterno || t.apellidoMaterno || t.nombres ");
		
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<MsUsuarios> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<MsUsuarios> getListaIdusuario() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("SELECT tablaa FROM MsUsuarios tablaa ");
		sb.append("WHERE tablaa.estado > 1 ");
		sb.append("ORDER BY tablaa.idusuario asc ");
		return super.find(sb.toString());
	}
	
}