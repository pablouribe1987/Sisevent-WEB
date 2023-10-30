package pe.gob.mef.sisevent.bs.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class KittyCache<K, V> implements KCache<K, V> {

	private static final Log log = LogFactory.getLog(KittyCache.class);
	
	private Map<K, CacheEntry<V>> cache;
	private Queue<K> queue;
	private int maxSize;
	
	private int printCada = 500;
	private int totalCargar = 0;

	private AtomicInteger cacheSize = new AtomicInteger();
	
	public KittyCache(int maxSize) {
		this.maxSize = maxSize;
		cache = new ConcurrentHashMap<K, CacheEntry<V>>(maxSize);
		queue = new ConcurrentLinkedQueue<K>();
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Invalid Key.");
		}

		CacheEntry<V> entry = cache.get(key);

		if (entry == null) {
			return null;
		}

		long timestamp = entry.getExpireBy();
		if (timestamp != -1 && System.currentTimeMillis() > timestamp) {
			int secondsToLive =entry.getSecondsToLive();
			remove(key);
			V value = getEntidad(key);	
			if(value!=null)
				put(key,value,secondsToLive);
			return value;
		}
		return entry.getEntry();
	}

	public V removeAndGet(K key) {

		if (key == null) {
			return null;
		}

		CacheEntry<V> entry = cache.get(key);
		if (entry != null) {
			cacheSize.decrementAndGet();
			return cache.remove(key).getEntry();
		}

		return null;
	}

	public void put(K key, V value, int secondsToLive) {
		if (key == null) {
			throw new IllegalArgumentException("Invalid Key.");
		}
		if (value == null) {
			throw new IllegalArgumentException("Invalid Value.");
		}

		long expireBy = secondsToLive != -1 ? System.currentTimeMillis() + (secondsToLive * 1000) : secondsToLive;
		boolean exists = cache.containsKey(key);
		if (!exists) {
			cacheSize.incrementAndGet();
			while (cacheSize.get() > maxSize) {
				remove(queue.poll());
			}
		}
		cache.put(key, new CacheEntry<V>(secondsToLive, expireBy, value));
		queue.add(key);
		
		if((size()%printCada)==0){
			log.info(getClase()+" TOTAL "+size()+" CARGADOS DE "+totalCargar);
		}
	}

	public boolean remove(K key) {
		return removeAndGet(key) != null;
	}

	public int size() {
		return cacheSize.get();
	}

	public Map<K, V> getAll(Collection<K> collection) {
		Map<K, V> ret = new HashMap<K, V>();
		for (K o : collection) {
			ret.put(o, get(o));
		}
		return ret;
	}
	
	public List<V> getAll() {
		List<V> targetList = new ArrayList<V>();
		for (K key: cache.keySet()) {
             V valor = get(key);
		    if(valor!=null){
		    	targetList.add(valor);
		    }
		}
		return targetList;
	}
	
	public void clear() {
		cache.clear();
	}

	public int mapSize() {
		return cache.size();
	}

	public int queueSize() {
		return queue.size();
	}

	@SuppressWarnings("hiding")
	private class CacheEntry<V> {
		private long expireBy;
		private V entry;
		private int secondsToLive;

		public CacheEntry(int secondsToLive, long expireBy, V entry) {
			super();
			this.expireBy = expireBy;
			this.entry = entry;
			this.secondsToLive = secondsToLive;
		}

		/**
		 * @return the expireBy
		 */
		public long getExpireBy() {
			return expireBy;
		}

		/**
		 * @return the entry
		 */
		public V getEntry() {
			return entry;
		}

		public int getSecondsToLive() {
			return secondsToLive;
		}
	
	}
	
	public int getPrintCada() {
		return printCada;
	}

	public void setPrintCada(int printCada) {
		this.printCada = printCada;
	}

	public int getTotalCargar() {
		return totalCargar;
	}

	public void setTotalCargar(int totalCargar) {
		this.totalCargar = totalCargar;
	}
	
	public int getMemorySize() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException{
		List<V> list = getAll(); 
		Field f = ArrayList.class.getDeclaredField("elementData");
		f.setAccessible(true);
		int capacity = ((Object[]) f.get(list)).length;
		System.out.format("List size de "+getClase()+": %d, Capacity: %d ", list.size(), capacity);
		return capacity;
	}

	public abstract String getClase();
	public abstract V getEntidad(K key);
}
