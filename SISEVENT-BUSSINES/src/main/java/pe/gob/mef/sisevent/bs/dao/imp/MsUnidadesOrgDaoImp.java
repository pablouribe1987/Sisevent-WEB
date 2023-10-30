package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsUnidadesOrgDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsUnidadesOrg;

@Repository
public class MsUnidadesOrgDaoImp extends AbstractJpaCRUDDao<MsUnidadesOrg, Long> implements MsUnidadesOrgDao {
	private static final Logger log = Logger.getLogger(MsUnidadesOrgDaoImp.class.getName());

	public MsUnidadesOrgDaoImp() {
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsUnidadesOrgDaoImp");
	}

	public MsUnidadesOrgDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA MsUnidadesOrgDaoImp");
	}

	@Transactional
	public void saveMsUnidadesOrg(MsUnidadesOrg param) {
		super.save(param);
	}

	@Transactional
	public void updateMsUnidadesOrg(MsUnidadesOrg param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsUnidadesOrg(MsUnidadesOrg param) {
		super.delete(param);
	}

	public List<MsUnidadesOrg> getAllMsUnidadesOrg() {
		return super.find("from " + this.getDomainClass().getName());
	}

	public MsUnidadesOrg getMsUnidadesOrg(Long id) {
		return (MsUnidadesOrg) super.findById(id);
	}

	public List<MsUnidadesOrg> getNativeSQLMsUnidadesOrg(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsUnidadesOrg> getDomainClass() {
		return MsUnidadesOrg.class;
	}

	public List<MsUnidadesOrg> getActivasMsUnidadesOrg() {
		return super.find(
				"from " + this.getDomainClass().getName() + " t where t.estado > 0 order by t.descripcion asc ");
	}
	
	@Override
	public List<MsUnidadesOrg> getActivasMsUnidadesOrgCero() {
		return super.find(
				"from " + this.getDomainClass().getName() + " t where t.estado >= 0 ");
	}

	public List<MsUnidadesOrg> getDesactivasMsUnidadesOrg() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<MsUnidadesOrg> getByIdMsUnidadesOrg(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.idunidad = ? order by t.idunidad asc ");
		Object[] param = new Object[]{id};
		List<MsUnidadesOrg> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select max(t.idunidad) as maximo from " + this.getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}

		sequence = sequence + 1L;
		return sequence;
	}

	public List<MsUnidadesOrg> getByPadresMsUnidadesOrg() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.idpadre <= 0 or t.idpadre is NULL order by t.descripcion asc ");
		List<MsUnidadesOrg> lista = super.find(sb.toString());
		return lista;
	}

	public List<MsUnidadesOrg> getByIdPadreMsUnidadesOrg(Long idPadre) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.idpadre = ? order by t.descripcion asc ");
		Object[] param = new Object[]{idPadre};
		List<MsUnidadesOrg> lista = super.find(sb.toString(), param);
		return lista;
	}

	public List<MsUnidadesOrg> getByNombreMsUnidadesOrg(String descripcion) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.descripcion = ? order by t.descripcion asc ");
		Object[] param = new Object[]{descripcion};
		List<MsUnidadesOrg> lista = super.find(sb.toString(), param);
		return lista;
	}
}