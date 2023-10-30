package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsUbigeoDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsUbigeo;
import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;

/**
 * MS_UBIGEO REPOSITORIO: UBIGEO DATOS OTORGADOS POR EL INEI
 * 
 * @author Carlos Aguilar
 * @version 2.0, 23/12/2020 11:52
 * 
 *          /----------Nombre----------/---------fecha------/------------Motivo---------/ /Carlos Aguilar Chamochumbi/
 *          23/12/2020 11:52 / Creación de la clase /
 * 
 */
@Repository
public class MsUbigeoDaoImp extends AbstractJpaCRUDDao<MsUbigeo, MsUbigeoId> implements MsUbigeoDao {

	private static final Logger log = Logger.getLogger(MsUbigeoDaoImp.class.getName());

	public MsUbigeoDaoImp() {
		log.log(Level.INFO, null, "INICIALIZANDO JPA TEMPLATE PARA MsUbigeoDaoImp");
	}

	public MsUbigeoDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO, null, "INICIALIZANDO JPA TEMPLATE PARA MsUbigeoDaoImp");
	}

	@Transactional
	public void saveMsUbigeo(MsUbigeo param) {
		super.save(param);
	}

	@Transactional
	public void updateMsUbigeo(MsUbigeo param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsUbigeo(MsUbigeo param) {
		super.delete(param);
	}

	public List<MsUbigeo> getAllMsUbigeo() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsUbigeo getMsUbigeo(MsUbigeoId id) {
		return super.findById(id);
	}

	public List<MsUbigeo> getNativeSQLMsUbigeo(String queryString, Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsUbigeo> getDomainClass() {
		return MsUbigeo.class;
	}

	public List<MsUbigeo> getActivasMsUbigeo() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado > 0");
	}
	
	@Override
	public List<MsUbigeo> getActivasMsUbigeoCero() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado >= 0");
	}

	public List<MsUbigeo> getDesactivasMsUbigeo() {
		return super.find("from " + getDomainClass().getName() + " t where t.estado <= 0");
	}

	public List<MsUbigeo> getByIdMsUbigeo(MsUbigeoId id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName() + " t where t.id = ? order by t.id asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsUbigeo> lista = super.find(sb.toString(), param);
		return lista;
	}

	public Long getMaxIdVal() {
		String queryString = "select count(*) as maximo from " + getDomainClass().getName() + " t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence++;
		return sequence;
	}

	@Override
	public List<MsUbigeo> getXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre, Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		if (coddpto != null) {
			sb.append("and t.id.coddpto = ? ");
			hs.add(coddpto);
		}
		if (codprov != null) {
			sb.append("and t.id.codprov = ? ");
			hs.add(codprov);
		}
		if (coddist != null) {
			sb.append("and t.id.coddist = ? ");
			hs.add(coddist);
		}
		if (nombre != null && nombre.trim().length() > 0) {
			sb.append("and t.nombre = ? ");
			hs.add(nombre);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}
		// sb.append("order by t.idusuario desc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsUbigeo> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsUbigeo> getXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre, Integer estado,
			int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		if (coddpto != null) {
			sb.append("and t.id.coddpto = ? ");
			hs.add(coddpto);
		}
		if (codprov != null) {
			sb.append("and t.id.codprov = ? ");
			hs.add(codprov);
		}
		if (coddist != null) {
			sb.append("and t.id.coddist = ? ");
			hs.add(coddist);
		}
		if (nombre != null && nombre.trim().length() > 0) {
			sb.append("and t.nombre = ? ");
			hs.add(nombre);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}
		// sb.append("order by t.idusuario desc ");

		List<MsUbigeo> lista = null;
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
	public long getTotalXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre, Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.id) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		if (coddpto != null) {
			sb.append("and t.id.coddpto = ? ");
			hs.add(coddpto);
		}
		if (codprov != null) {
			sb.append("and t.id.codprov = ? ");
			hs.add(codprov);
		}
		if (coddist != null) {
			sb.append("and t.id.coddist = ? ");
			hs.add(coddist);
		}
		if (nombre != null && nombre.trim().length() > 0) {
			sb.append("and t.nombre = ? ");
			hs.add(nombre);
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
	public List<MsUbigeo> getDepartamentos() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.estado > 0 and tablaa.id.codprov = 0 AND ");
		sb.append("tablaa.id.coddist = 0 ");
		sb.append("ORDER BY tablaa.nombre");
		return super.find(sb.toString());
	}

	@Override
	public List<MsUbigeo> getProvincias(Integer id_dpto) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.estado > 0 and tablaa.id.coddpto = ? AND ");
		sb.append("tablaa.id.coddist = 0 AND ");
		sb.append("tablaa.id.codprov <> 0 ");
		sb.append("ORDER BY tablaa.nombre");
		Object[] params = new Object[1];
		params[0] = id_dpto;
		return super.find(sb.toString(), params);
	}

	@Override
	public List<MsUbigeo> getDistritos(Integer id_dpto, Integer id_prov) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.estado > 0 and tablaa.id.coddpto = ? AND ");
		sb.append("tablaa.id.codprov = ? AND ");
		sb.append("tablaa.id.coddist <> 0 ");
		sb.append("ORDER BY tablaa.nombre");
		Object[] params = new Object[2];
		params[0] = id_dpto;
		params[1] = id_prov;
		return super.find(sb.toString(), params);
	}
	
	@Override
	public MsUbigeo getDepartamentosXNombre(String nombre) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.id.codprov = 0 ");
		sb.append("AND tablaa.id.coddist = 0 ");
		sb.append("AND tablaa.nombre=? ");
		Object[] params = new Object[1];
		MsUbigeo msUbigeo = null;
		params[0] = nombre;
		try{
		List<MsUbigeo> lista = super.find(sb.toString(), params);
		if(!lista.isEmpty())
			msUbigeo = lista.get(0);
		}catch(Exception e){}
		return msUbigeo;
	}

	@Override
	public MsUbigeo getProvinciasXNombre(Integer id_dpto, String nombre) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.id.coddpto = ? ");
		sb.append("AND tablaa.id.codprov>0 AND tablaa.id.coddist = 0 ");
		sb.append("AND tablaa.nombre=? ");		
		Object[] params = new Object[2];
		params[0] = id_dpto;
		MsUbigeo msUbigeo = null;
		params[1] = nombre;
		try{
		List<MsUbigeo> lista = super.find(sb.toString(), params);
		if(!lista.isEmpty())
			msUbigeo = lista.get(0);
		}catch(Exception e){}
		return msUbigeo;
	}

	@Override
	public MsUbigeo getDistritosXNombre(Integer id_dpto, Integer id_prov, String nombre) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("FROM MsUbigeo tablaa ");
		sb.append("WHERE tablaa.id.coddpto = ? ");
		sb.append("AND tablaa.id.codprov = ? AND tablaa.id.coddist>0 ");
		sb.append("AND tablaa.nombre=? ");
		Object[] params = new Object[3];
		params[0] = id_dpto;
		params[1] = id_prov;
		MsUbigeo msUbigeo = null;
		params[2] = nombre;
		try{
		List<MsUbigeo> lista = super.find(sb.toString(), params);
		if(!lista.isEmpty())
			msUbigeo = lista.get(0);
		}catch(Exception e){}
		return msUbigeo;
	}
}