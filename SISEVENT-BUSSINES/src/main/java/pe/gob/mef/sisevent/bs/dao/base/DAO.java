/**
 * 
 */
package pe.gob.mef.sisevent.bs.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * Interface BASE simple DAO que reemplaza a JpaDaoSupport que sale de
 * circulacion.
 * 
 * @author Carlos Aguilar
 * @version 2.0, 21/03/2012 06:28:21 PM
 * 
 *          /----------Nombre----------/----fecha----/-------------Motivo------
 *          --------/ Carlos Aguilar Chamochumbi 21/03/2012 06:28:21 Interface BASE de la persistencia
 *          de la clase
 * 
 */
public interface DAO<T, PK extends Serializable> {

	void save(T paramT);

	void update(T paramT);

	void delete(T paramT);

	T findById(PK paramObject);

	List<T> find(String paramString);

	List<T> find(String paramString, Object[] paramArrayOfObject);

	List<T> findNaviteQuery(String paramString);

	List<T> findNaviteQuery(String paramString, Object[] paramArrayOfObject);

	T findUniqueResult(String paramString);

	T findUniqueResult(String paramString, Object[] paramArrayOfObject);

	List<T> findLimitedResult(String paramString, int paramInt1, int paramInt2);

	List<T> findLimitedResult(String paramString, int paramInt1, int paramInt2,
			Object[] paramArrayOfObject);

	Class<T> getDomainClass();

	void removeById(PK id);

	void remove(T paramT);

	Object findUniqueResultObject(String paramString,
			Object[] paramArrayOfObject);

	Object findUniqueResultObject(String paramString);

	Object findNaviteQueryObject(String paramString);

	Object findUniqueNaviteQuery(String paramString, Object[] paramArrayOfObject);
	<b> List<b> findNaviteQueryEdtidad(String paramString, Object[] paramArrayOfObject, Class<b> e);
	<b> List<b> findNaviteQueryEdtidadSinP(String paramString, Class<b> e);
}
