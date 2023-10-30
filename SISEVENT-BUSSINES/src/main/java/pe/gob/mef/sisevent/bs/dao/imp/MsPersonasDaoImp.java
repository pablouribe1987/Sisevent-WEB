package pe.gob.mef.sisevent.bs.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.sisevent.bs.dao.MsPersonasDao;
import pe.gob.mef.sisevent.bs.dao.base.AbstractJpaCRUDDao;
import pe.gob.mef.sisevent.bs.domain.MsPersonas;

/**
 * MS_PERSONAS REPOSITORIO: PERSONAS NATURALES
 * 
 * @author Carlos Aguilar
 * @version 2.0, 22/12/2020 21:03
 * 
 *    /----------Nombre----------/---------fecha------/------------Motivo---------/
 *   /Carlos Aguilar Chamochumbi/ 22/12/2020 21:03   / Creación de la clase    /
 * 
 */
@Repository
public class MsPersonasDaoImp extends
		AbstractJpaCRUDDao<MsPersonas, Long> implements
		MsPersonasDao {

	private static final Logger log = Logger.getLogger(MsPersonasDaoImp.class.getName());

	public MsPersonasDaoImp() {
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsPersonasDaoImp");
	}
	
	public MsPersonasDaoImp(EntityManager entityManager) {
		super.setEntityManager(entityManager);
		log.log(Level.INFO,null,"INICIALIZANDO JPA TEMPLATE PARA MsPersonasDaoImp");
	}
	
	@Transactional
	public void saveMsPersonas(MsPersonas param) {
		super.save(param);
	}

	@Transactional
	public void updateMsPersonas(MsPersonas param) {
		super.update(param);
	}

	@Transactional
	public void deleteMsPersonas(MsPersonas param) {
		super.delete(param);
	}

	public List<MsPersonas> getAllMsPersonas() {
		return super.find("from " + getDomainClass().getName());
	}

	public MsPersonas getMsPersonas(Long id) {
		return super.findById(id);
	}

	public List<MsPersonas> getNativeSQLMsPersonas(String queryString,
			Object[] params) {
		return super.findNaviteQuery(queryString, params);
	}

	public Class<MsPersonas> getDomainClass() {
		return MsPersonas.class;
	}

	public List<MsPersonas> getActivasMsPersonas() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado > 0");
	}
	
	@Override
	public List<MsPersonas> getActivasMsPersonasCero() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado >= 0");
	}

	public List<MsPersonas> getDesactivasMsPersonas() {
		return super.find("from " + getDomainClass().getName()
				+ " t where t.estado <= 0");
	}

	public List<MsPersonas> getByIdMsPersonas(Long id) {
		StringBuffer sb = new StringBuffer(100);
		sb.append("select t from " + getDomainClass().getName()
				+ " t where t.idperson = ? order by t.idperson asc ");
		Object param[] = new Object[1];
		param[0] = id;
		List<MsPersonas> lista = super.find(sb.toString(), param);
		return lista;
	}
	
	public Long getMaxIdVal(){
		String queryString = "select max(t.idperson) as maximo from "+getDomainClass().getName()+" t ";
		Long sequence = (Long) super.findUniqueResultObject(queryString);
		if (sequence == null) {
			sequence = 0L;
		}
		sequence ++;
		return sequence;
	}
	
	@Override
	public List<MsPersonas> getXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,
			String nombres,String sexo,Long tipodocumento,String documentoNumero,
			String telefono,String celular,String direccion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
		if (idperson != null) {
			sb.append("and t.idperson = ? ");
			hs.add(idperson);
		}
		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			if(apellidoPaterno.length()<10){
				sb.append("and t.apellidoPaterno Like ? ");
				hs.add(apellidoPaterno.toUpperCase()+'%');
			}else{
				sb.append("and t.apellidoPaterno = ? ");
				hs.add(apellidoPaterno.toUpperCase());
		    }
		}
		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			if(apellidoMaterno.length()<10){
				sb.append("and t.apellidoMaterno Like ? ");
				hs.add(apellidoMaterno.toUpperCase()+'%');
			}else{
				sb.append("and t.apellidoMaterno = ? ");
				hs.add(apellidoMaterno.toUpperCase());
			}			
		}
		if (nombres != null && nombres.trim().length() > 0) {
			if(nombres.length()<10){
				sb.append("and t.nombres Like ? ");
				hs.add(nombres.toUpperCase()+'%');
			}else{
				sb.append("and t.nombres = ? ");
				hs.add(nombres.toUpperCase());
			}
		}
		if (sexo != null) {
			sb.append("and t.sexo = ? ");
			hs.add(sexo);
		}
		if (tipodocumento != null) {
			sb.append("and t.tipodocumento = ? ");
			hs.add(tipodocumento);
		}
		if (documentoNumero != null && documentoNumero.trim().length() > 0) {
			if(documentoNumero.length()<8){
				sb.append("and t.documentoNumero Like ? ");
				hs.add(documentoNumero+'%');
			}else{
				sb.append("and t.documentoNumero = ? ");
				hs.add(documentoNumero);
			}
		}
		
		if ((telefono != null && telefono.trim().length() > 0) && (celular != null && celular.trim().length() > 0)){
			sb.append("and (t.telefono Like ? or t.celular Like ?) ");
			hs.add(telefono+'%');
			hs.add(celular+'%');
		}else if (telefono != null && telefono.trim().length() > 0) {
			sb.append("and t.telefono Like ? ");
			hs.add(telefono+'%');
		}else if (celular != null && celular.trim().length() > 0) {
			sb.append("and t.celular Like ? ");
			hs.add(celular+'%');
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

		Object param[] = new Object[hs.size()];
		hs.toArray(param);
		List<MsPersonas> lista = super.find(sb.toString(), param);

		return lista;
	}

	@Override
	public List<MsPersonas> getXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String sexo,Long tipodocumento,String documentoNumero,String telefono,String celular,String direccion,Integer estado, int iniciar, int max) {
		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select t from " + getDomainClass().getName() + " t where  >= 1 ");

		
				if (idperson != null) {
					sb.append("and t.idperson = ? ");
					hs.add(idperson);
				}
				if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
					sb.append("and t.apellidoPaterno = ? ");
					hs.add(apellidoPaterno);
				}
				if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
					sb.append("and t.apellidoMaterno = ? ");
					hs.add(apellidoMaterno);
				}
				if (nombres != null && nombres.trim().length() > 0) {
					sb.append("and t.nombres = ? ");
					hs.add(nombres);
				}
				if (sexo != null) {
					sb.append("and t.sexo = ? ");
					hs.add(sexo);
				}
				if (tipodocumento != null) {
					sb.append("and t.tipodocumento = ? ");
					hs.add(tipodocumento);
				}
				if (documentoNumero != null && documentoNumero.trim().length() > 0) {
					sb.append("and t.documentoNumero = ? ");
					hs.add(documentoNumero);
				}
				if (telefono != null && telefono.trim().length() > 0) {
					sb.append("and t.telefono = ? ");
					hs.add(telefono);
				}
				if (celular != null && celular.trim().length() > 0) {
					sb.append("and t.celular = ? ");
					hs.add(celular);
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

		List<MsPersonas> lista = null;
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
	public long getTotalXFiltro(Long idperson,String apellidoPaterno,String apellidoMaterno,String nombres,String sexo,Long tipodocumento,String documentoNumero,String telefono,String celular,String direccion,Integer estado) {

		StringBuffer sb = new StringBuffer(100);
		List<Object> hs = new ArrayList<Object>();
		sb.append("select count(t.idperson) from " + getDomainClass().getName() + " t where t.estado >= 1 ");

		
				if (idperson != null) {
					sb.append("and t.idperson = ? ");
					hs.add(idperson);
				}
				if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
					sb.append("and t.apellidoPaterno = ? ");
					hs.add(apellidoPaterno);
				}
				if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
					sb.append("and t.apellidoMaterno = ? ");
					hs.add(apellidoMaterno);
				}
				if (nombres != null && nombres.trim().length() > 0) {
					sb.append("and t.nombres = ? ");
					hs.add(nombres);
				}
				if (sexo != null) {
					sb.append("and t.sexo = ? ");
					hs.add(sexo);
				}
				if (tipodocumento != null) {
					sb.append("and t.tipodocumento = ? ");
					hs.add(tipodocumento);
				}
				if (documentoNumero != null && documentoNumero.trim().length() > 0) {
					sb.append("and t.documentoNumero = ? ");
					hs.add(documentoNumero);
				}
				if (telefono != null && telefono.trim().length() > 0) {
					sb.append("and t.telefono = ? ");
					hs.add(telefono);
				}
				if (celular != null && celular.trim().length() > 0) {
					sb.append("and t.celular = ? ");
					hs.add(celular);
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
}