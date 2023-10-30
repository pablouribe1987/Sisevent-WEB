package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsInstitucionesDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsInstituciones;

/**
 * MS_INSTITUCIONES REPOSITORIO: INSTITUCIONES Y ENTIDADES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 21:03
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 22/12/2020 21:03   / Creación de la clase    /
 * 
 */
@Repository
public class MsInstitucionesDaoImp extends
		AbstractJpaCRUDDao<MsInstituciones, Long> implements
		MsInstitucionesDao {

	private static final Logger log = Logger.getLogger(MsInstitucionesDaoImp.class.getName());

	public MsInstitucionesDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsInstitucionesDaoImp");
	}
	
	public MsInstitucionesDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsInstitucionesDaoImp");
	}
	
	@Transactional
	public void saveMsInstituciones(MsInstituciones param) {
		super.save(param);
	}

	@Transactional
	public void updateMsInstituciones(MsInstituciones param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsInstituciones(MsInstituciones param) {
		super.delete(param);
	}

	public List<MsInstituciones> getAllMsInstituciones() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsInstituciones getMsInstituciones(Long id) {
		return super.findById(id);
	}

	public List<MsInstituciones> getNativeSQLMsInstituciones(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsInstituciones> getDomainClass() {
		return MsInstituciones.class;
	}

	public List<MsInstituciones> getActivasMsInstituciones() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}

	public List<MsInstituciones> getDesactivasMsInstituciones() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<MsInstituciones> getByIdMsInstituciones(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idprovee = ? order by t.idprovee asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsInstituciones> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idprovee) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<MsInstituciones> getXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idprovee != null) {
			sb.append("and t.idprovee = ? ");
			hs.add(idprovee);
		}
		if (razonSocial != null && razonSocial.trim().length() > 0) {
			sb.append("and t.razonSocial LIKE ? ");
			hs.add('%'+razonSocial.toUpperCase()+'%');
		}
		if (siglas != null && siglas.trim().length() > 0) {
			sb.append("and t.siglas = ? ");
			hs.add(siglas);
		}
		if (ruc != null) {
			if(ruc.length()<11){
				sb.append("and t.ruc LIKE ? ");
				hs.add(ruc+'%');
			}else{
				if(ruc.length()>11) ruc = ruc.substring(0,11);
				sb.append("and t.ruc = ? ");
				hs.add(ruc);
			}			
		}
		if (telefono != null && telefono.trim().length() > 0) {
			sb.append("and t.telefono = ? ");
			hs.add(telefono);
		}
		if (direccion != null) {
			sb.append("and t.direccion = ? ");
			hs.add(direccion);
		}
		if (estado != null) {
			sb.append("and t.estado = ? ");
			hs.add(estado);
		}		
		sb.append("order by t.razonSocial asc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsInstituciones> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsInstituciones> getXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		
				if (idprovee != null) {
					sb.append("and t.idprovee = ? ");
					hs.add(idprovee);
				}
				if (razonSocial != null && razonSocial.trim().length() > 0) {
					sb.append("and t.razonSocial = ? ");
					hs.add(razonSocial);
				}
				if (siglas != null && siglas.trim().length() > 0) {
					sb.append("and t.siglas = ? ");
					hs.add(siglas);
				}
				if (ruc != null) {
					sb.append("and t.ruc = ? ");
					hs.add(ruc);
				}
				if (telefono != null && telefono.trim().length() > 0) {
					sb.append("and t.telefono = ? ");
					hs.add(telefono);
				}
				if (direccion != null) {
					sb.append("and t.direccion = ? ");
					hs.add(direccion);
				}
				if (estado != null) {
					sb.append("and t.estado = ? ");
					hs.add(estado);
				}
		// sb.append("order by t.idusuario desc ");

		List<MsInstituciones> lista = null;
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
	public long getTotalXFiltro(Long idprovee,String razonSocial,String siglas,String ruc,String telefono,String direccion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idprovee) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idprovee != null) {
					sb.append("and t.idprovee = ? ");
					hs.add(idprovee);
				}
				if (razonSocial != null && razonSocial.trim().length() > 0) {
					sb.append("and t.razonSocial = ? ");
					hs.add(razonSocial);
				}
				if (siglas != null && siglas.trim().length() > 0) {
					sb.append("and t.siglas = ? ");
					hs.add(siglas);
				}
				if (ruc != null) {
					sb.append("and t.ruc = ? ");
					hs.add(ruc);
				}
				if (telefono != null && telefono.trim().length() > 0) {
					sb.append("and t.telefono = ? ");
					hs.add(telefono);
				}
				if (direccion != null) {
					sb.append("and t.direccion = ? ");
					hs.add(direccion);
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
	public List<MsInstituciones> getActivasMsInstitucionesCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}
	
	@Override
	public List<MsInstituciones> getXFiltroTipoentidad(String tipoentidad,String razonSocial) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (tipoentidad != null) {
			sb.append("and t.tipoentidad = ? ");
			hs.add(tipoentidad);
		}
		if (razonSocial != null && razonSocial.trim().length() > 0) {
			sb.append("and t.razonSocial LIKE ? ");
			hs.add('%'+razonSocial.toUpperCase()+'%');
		}
				
		sb.append("order by t.razonSocial asc ");

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsInstituciones> lista = super.find(sb.toString(), param);

		return lista;
	}
}