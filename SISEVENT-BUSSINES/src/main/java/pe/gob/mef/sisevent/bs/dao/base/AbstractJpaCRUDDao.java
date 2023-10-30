/**
 * 
 */
package pe.gob.mef.sisevent.bs.dao.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Interface BASE simple DAO que reemplaza a JpaDaoSupport que sale de
 * circulacion.
 * 
 * @author Carlos Aguilar
 * @version 2.0, 21/03/2012 06:28:21 PM
 * 
 *          /----------Nombre----------/----fecha----/-------------Motivo------
 *          --------/ Carlos Aguilar Chamochumbi 21/03/2012 06:28:21 Implementación BASE de la persistencia
 *          de la clase
 * 
 */
public abstract class AbstractJpaCRUDDao<T, PK extends Serializable> implements
		DAO<T, PK> {

	@PersistenceContext(unitName="entityManagerFactory")
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(T paramT) {
		// TODO Auto-generated method stub
		entityManager.persist(paramT);
	}

	public void update(T paramT) {
		// TODO Auto-generated method stub		
		entityManager.merge(paramT);
		entityManager.flush();
	}

	public void delete(T paramT) {
		// TODO Auto-generated method stub
		// entityManager.remove(paramT);
	}

	public T findById(PK paramObject) {
		// TODO Auto-generated method stub
		return entityManager.find(getDomainClass(), paramObject);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String paramString) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String paramString, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findNaviteQuery(String paramString) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(paramString,
				getDomainClass());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findNaviteQuery(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(paramString,
				getDomainClass());
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueResult(String paramString) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		return (T) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueResult(String paramString, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		return (T) query.getSingleResult();
	}

	public Object findUniqueResultObject(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		//query.setParameter(paramArrayOfObject.length+1, new Integer(1));
		//query.setFirstResult(1);
		return query.getSingleResult();
	}   

	public Object findUniqueResultObject(String paramString) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);		
		return query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findLimitedResult(String paramString, int paramInt1,
			int paramInt2) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		query.setFirstResult(paramInt1);
		query.setMaxResults(paramInt2);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findLimitedResult(String paramString, int paramInt1,
			int paramInt2, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(paramString);
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		query.setFirstResult(paramInt1);
		query.setMaxResults(paramInt2);
		return query.getResultList();
	}

	public void removeById(PK id) {
		// TODO Auto-generated method stub
		T entity = findById(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}

	public void remove(T paramT) {
		// TODO Auto-generated method stub
		entityManager.remove(paramT);
	}

	public abstract Class<T> getDomainClass();

	public Object findNaviteQueryObject(String paramString) {
          Query query = entityManager.createNativeQuery(paramString);
          return query.getSingleResult();
    }
		
	@SuppressWarnings("unchecked")
	public <b> List<b> findNaviteQueryEdtidad(String paramString,
			Object[] paramArrayOfObject, Class<b> e) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(paramString,e);
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}	
		List<b> fromSql = (List<b>) query.getResultList(); 
		return fromSql;
	}
	
	@SuppressWarnings("unchecked")
	public <b> List<b> findNaviteQueryEdtidadSinP(String paramString, Class<b> e) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(paramString,e);			
		List<b> fromSql = (List<b>) query.getResultList(); 
		return fromSql;
	}

	public Object findUniqueNaviteQuery(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(paramString,
				getDomainClass());
		for (int i = 0; i < paramArrayOfObject.length; ++i) {
			query.setParameter((i + 1), paramArrayOfObject[i]);
		}
		return query.getSingleResult();
	}
	
	//VBALDEONH SPRINT2 INICIO 
		@SuppressWarnings({ "unchecked", "hiding" })
		public <T> List<T> findNative(String select, Object[] params){	
				Query query = entityManager.createNativeQuery(select);
				if(params!=null) setParemeter(query, params);
				return query.getResultList();		
		}
		private void setParemeter(Query query, Object[] params) {
			if (params != null && params.length > 0)
				for (int i = 0; i < params.length; i++ ){
					query.setParameter(i + 1, params[i]);
				}
		}
		//SPRINT2 FIN
}
