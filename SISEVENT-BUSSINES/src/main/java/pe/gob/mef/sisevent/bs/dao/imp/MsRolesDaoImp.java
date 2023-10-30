package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsRolesDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsRoles;

@Repository
public class MsRolesDaoImp extends AbstractJpaCRUDDao<MsRoles, Long> implements MsRolesDao {
	private static final Logger log = Logger.getLogger(MsRolesDaoImp.class.getName());

	public MsRolesDaoImp() {
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsRolesDaoImp");
	}

	public MsRolesDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsRolesDaoImp");
	}

	@Transactional
	public void saveMsRoles(MsRoles param) {
		super.save(param);
	}

	@Transactional
	public void updateMsRoles(MsRoles param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsRoles(MsRoles param) {
		super.delete(param);
	}

	public List<MsRoles> getAllMsRoles() {
		return super.find("from " + this.getDomainClass().getName());
	}

	public MsRoles getMsRoles(Long id) {
		return (MsRoles) super.findById(id);
	}

	public List<MsRoles> getNativeSQLMsRoles(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsRoles> getDomainClass() {
		return MsRoles.class;
	}

	public List<MsRoles> getActivasMsRoles() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado > 0");
	}

	public List<MsRoles> getDesactivasMsRoles() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<MsRoles> getByIdMsRoles(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.idrol = ? order by t.idrol asc ");
		Object[] param = new Object[]{id};
		List<MsRoles> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select max(t.idrol) as maximo from " + this.getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}

		sequence = sequence + 1L;
		return sequence;
	}

	public List<MsRoles> getXFiltro(String username) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		if (username != null && username.trim().length() > 0) {
			sb.append("and t.username = ?1 ");
			hs.add(username);
		}

		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<MsRoles> lista = super.find(sb.toString(), param);
		return lista;
	}

	public MsRoles getXUserRol(String username, String rol) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.username = ? and t.rol = ? ");
		hs.add(username);
		hs.add(rol);
		MsRoles msRoles = null;

		try {
			Object[] param = new Object[hs.size()];
			hs.toArray(param);
			msRoles = (MsRoles) super.findUniqueResultObject(sb.toString(), param);
		} catch (Exception var7) {
			;
		}

		return msRoles;
	}
}