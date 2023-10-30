/**
 * 
 */
package pe.gob.mef.sisevent.bs.cache;

import java.util.Collection;
import java.util.Map;

/**
 * @author cafach
 *
 */
public interface KCache<K, V> {
	
	void put(K key, V value, int secondsToLive);
	V get(K key);
	Map<K, V> getAll(Collection<K> collection);
	void clear();
	boolean remove(K key);
	V removeAndGet(K key);
	int size();
	String getClase();
	V getEntidad(K key);
}
