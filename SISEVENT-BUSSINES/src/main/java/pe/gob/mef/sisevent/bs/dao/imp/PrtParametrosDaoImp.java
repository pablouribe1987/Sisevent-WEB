package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.PrtParametrosDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.PrtParametros;

@Repository
public class PrtParametrosDaoImp extends AbstractJpaCRUDDao<PrtParametros, Long> implements PrtParametrosDao {
	private static final Logger log = Logger.getLogger(PrtParametrosDaoImp.class.getName());

	public PrtParametrosDaoImp() {
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA PrtParametrosDaoImp");
	}

	public PrtParametrosDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, (String) null, "INICIALIZANDO JPA TEMPLATE PARA PrtParametrosDaoImp");
	}

	@Transactional
	public void savePrtParametros(PrtParametros param) {
		super.save(param);
	}

	@Transactional
	public void updatePrtParametros(PrtParametros param) {
		super.update(param);
	}

	@Transactional
	public void deletePrtParametros(PrtParametros param) {
		super.delete(param);
	}

	public List<PrtParametros> getAllPrtParametros() {
		return super.find("from " + this.getDomainClass().getName());
	}

	public PrtParametros getPrtParametros(Long id) {
		return (PrtParametros) super.findById(id);
	}

	public List<PrtParametros> getNativeSQLPrtParametros(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<PrtParametros> getDomainClass() {
		return PrtParametros.class;
	}

	public List<PrtParametros> getActivasPrtParametros() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado > 0");
	}

	public List<PrtParametros> getDesactivasPrtParametros() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<PrtParametros> getByIdPrtParametros(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + this.getDomainClass().getName()
				+ " t where t.idparametro = ? order by t.idparametro asc ");
		Object[] param = new Object[]{id};
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select max(t.idparametro) as maximo from " + this.getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}

		sequence = sequence + 1L;
		return sequence;
	}

	public List<PrtParametros> getXFiltro(Long idpadre, String descripcion) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		if (idpadre != null && idpadre > 0L) {
			sb.append("and t.idpadre = ? ");
			hs.add(idpadre);
		}

		if (descripcion != null && descripcion.trim().length() > 0) {
			sb.append("and t.descripcion = ? ");
			hs.add(descripcion);
		}

		sb.append("order by t.idparametro desc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}

	public List<PrtParametros> getXDescripcion(String descripcionpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		sb.append("and t.idpadre in (select g.idparametro from " + this.getDomainClass().getName()
				+ " g where g.descripcion = ?) ");
		hs.add(descripcionpadre);
		sb.append("order by t.orden asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public PrtParametros getDescripcion(String descripcionpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 and t.descripcion = ? and (t.idpadre is null or t.idpadre=0) ");
		hs.add(descripcionpadre);
		sb.append("order by t.orden asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		if(lista!=null && !lista.isEmpty())
		return lista.get(0);
		else
			return null;
	}
	
	@Override
	public List<PrtParametros> getXPadre(Long idpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 1 ");
		if (idpadre != null && idpadre > 0L) {
			sb.append("and (t.idpadre=? or t.idpadre is null) ");
			hs.add(idpadre);
		}
		sb.append("order by t.descripcion asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<PrtParametros> getXDescripcionCero(String descripcionpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 0 ");
		sb.append("and t.idpadre in (select g.idparametro from " + this.getDomainClass().getName()
				+ " g where g.descripcion = ?) ");
		hs.add(descripcionpadre);
		sb.append("order by t.orden asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<PrtParametros> getXDescripcionCeroNo0(String descripcionpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado > 0 ");
		sb.append("and t.idpadre in (select g.idparametro from " + this.getDomainClass().getName()
				+ " g where g.descripcion = ?) ");
		hs.add(descripcionpadre);
		sb.append("order by t.orden asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<PrtParametros> getActivasPrtParametrosCero() {
		return super.find("from " + this.getDomainClass().getName() + " t where t.estado >= 0");
	}
	
	@Override
	public List<PrtParametros> getXDescripcionHijos(String descripcionpadre) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + this.getDomainClass().getName() + " t where t.estado >= 0 ");
		sb.append("and t.idpadre in (select h.idparametro from " + this.getDomainClass().getName() + " h where h.estado >= 0 ");
		sb.append("and h.idpadre in (select g.idparametro from " + this.getDomainClass().getName()
				+ " g where g.descripcion = ?)) ");
		hs.add(descripcionpadre);
		sb.append("order by t.orden asc ");
		Object[] param = new Object[hs.size()];
		hs.toArray(param);
		List<PrtParametros> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	@Override
	public List<PrtParametros> getListaIdparametro() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("SELECT tablaa FROM PrtParametros tablaa ");
		sb.append("WHERE tablaa.estado > 1 ");
		sb.append("ORDER BY tablaa.idparametro asc ");
		return super.find(sb.toString());
	}	
}