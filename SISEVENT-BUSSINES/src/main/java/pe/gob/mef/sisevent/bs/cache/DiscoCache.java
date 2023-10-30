package pe.gob.mef.sisevent.bs.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

///C.A. 20171009
public abstract class DiscoCache<K, V> {

	private Map<K, DiskCacheEntry<V>> cache = null;
	private Queue<K> queue = null;

	private int maxSize = 1000;
	private int maxRegistros = 1000;

	private AtomicInteger cacheSize = new AtomicInteger();

	protected String dirKCH = null;

	protected String dicKCHConsultas = null;

	private List<V> targetList = new ArrayList<V>();

	private boolean usarMemoria = false;

	public DiscoCache(String ROOTKCH) {

		queue = new ConcurrentLinkedQueue<K>();
		dirKCH = ROOTKCH + System.getProperty("file.separator") + "DKCH" + getDomainClass().getSimpleName();
		File dirkch = new File(dirKCH);
		if (!dirkch.exists()) {
			dirkch.mkdirs();
		}

		dicKCHConsultas = dirKCH + System.getProperty("file.separator") + "CONSULTAS";
		File dirkchc = new File(dicKCHConsultas);
		if (!dirkchc.exists()) {
			dirkchc.mkdirs();
		}
		// getUltimafecha();

		Properties propiedades = getSistemaProperties();
		String sparametro = propiedades.getProperty("USAR_MEMORIA");
		if (sparametro != null) {
			try {
				usarMemoria = Boolean.parseBoolean(sparametro);
			} catch (Exception e) {
			}
		}

		sparametro = propiedades.getProperty("MAXIMO_REGISTROS");
		if (sparametro != null) {
			try {
				maxRegistros = Integer.parseInt(sparametro);
			} catch (Exception e) {
			}
		}

		sparametro = propiedades.getProperty("MAXIMO_EN_MEMORIA");
		if (sparametro != null) {
			try {
				maxSize = Integer.parseInt(sparametro);
			} catch (Exception e) {
			}
		}

		cache = new ConcurrentHashMap<K, DiskCacheEntry<V>>(maxSize);
	}

	public abstract Class<V> getDomainClass();

	@SuppressWarnings("hiding")
	private class DiskCacheEntry<V> {
		private long expireBy = -1;
		private V entry = null;

		public DiskCacheEntry(long expireBy, V entry) {
			this.expireBy = expireBy;
			this.entry = entry;
		}

		public long getExpireBy() {
			return expireBy;
		}

		public V getEntry() {
			return entry;
		}
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Invalid Key.");
		}

		DiskCacheEntry<V> entry = cache.get(key);

		if (entry == null) {
			return null;
		}

		long timestamp = entry.getExpireBy();
		if (timestamp != -1 && System.currentTimeMillis() > timestamp) {
			remove(key);
			return null;
		}
		return entry.getEntry();
	}

	public V removeAndGet(K key) {

		if (key == null) {
			return null;
		}

		DiskCacheEntry<V> entry = cache.get(key);
		if (entry != null) {
			cacheSize.decrementAndGet();
			targetList.clear();
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
		cache.put(key, new DiskCacheEntry<V>(expireBy, value));
		queue.add(key);
		targetList.add(value);
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
		if (targetList == null || targetList.isEmpty()) {
			for (K key : cache.keySet()) {
				V valor = get(key);
				if (valor != null) {
					targetList.add(valor);
				}
			}
		}
		return targetList;
	}

	public void clear() {
		cache.clear();
		targetList.clear();
		cacheSize.lazySet(0);
	}

	public int mapSize() {
		return cache.size();
	}

	public int queueSize() {
		return queue.size();
	}

	private Properties getSistemaProperties() {
		Properties propertieSistema = null;
		if (propertieSistema == null) {
			String propertiesFile = dirKCH + System.getProperty("file.separator") + getDomainClass().getSimpleName();
			File sistemaproriedadesFile = new File(propertiesFile);
			if (sistemaproriedadesFile.exists()) {
				FileInputStream input = null;
				propertieSistema = new Properties();
				try {
					input = new FileInputStream(sistemaproriedadesFile);
					propertieSistema.load(input);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (null != input)
						try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			} else {
				propertieSistema = new Properties();
				propertieSistema.put("FECHADEACTUALIZACION", "20010101");
				propertieSistema.put("ACTIVO", "false");
				propertieSistema.put("RECREAR_AL_INICIAR", "true");
				propertieSistema.put("USAR_MEMORIA", "false");
				propertieSistema.put("MAXIMO_REGISTROS", "1000");
				propertieSistema.put("MAXIMO_EN_MEMORIA", "1000");
				saveProperties(propertieSistema);
			}
		}
		return propertieSistema;
	}

	private void saveProperties(Properties p) {
		String propertiesFile = dirKCH + System.getProperty("file.separator") + getDomainClass().getSimpleName();
		File sistemaproriedadesFile = new File(propertiesFile);
		FileOutputStream ouput = null;
		try {
			ouput = new FileOutputStream(sistemaproriedadesFile);
			p.store(ouput, "PARAMETROS DE LA CLASE " + getDomainClass().getSimpleName() + " CACHE DISCO");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != ouput)
				try {
					ouput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void clearDisco() {
		File directorio = new File(dirKCH);
		if (directorio.exists() && directorio.isDirectory()) {
			for (File fileAct : directorio.listFiles()) {
				if (fileAct.isFile() && fileAct.getName().endsWith(".ser")) {
					if (!fileAct.delete()) {
						System.out.println("NO SE PUEDE ELIMINAR EL " + fileAct.getAbsolutePath());
					}
				}
			}
		}

		directorio = new File(dicKCHConsultas);
		if (directorio.exists() && directorio.isDirectory()) {
			for (File fileAct : directorio.listFiles()) {
				if (!fileAct.delete()) {
					System.out.println("NO SE PUEDE ELIMINAR EL " + fileAct.getAbsolutePath());
				}
			}
		}

	}

	public Date getUltimafecha() {
		Properties propiedades = getSistemaProperties();
		String sfecha = propiedades.getProperty("FECHADEACTUALIZACION");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date ultimafecha = null;
		if (sfecha != null) {
			try {
				ultimafecha = sdf.parse(sfecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (ultimafecha == null) {
				ultimafecha = new Date(0L);
				propiedades.setProperty("FECHADEACTUALIZACION", sdf.format(ultimafecha));
				saveProperties(propiedades);
			}
		} else {
			ultimafecha = new Date(0L);
			propiedades.setProperty("FECHADEACTUALIZACION", sdf.format(ultimafecha));
			saveProperties(propiedades);
		}
		return ultimafecha;
	}

	public void setUltimafecha(Date ultimafecha) {
		Properties propiedades = getSistemaProperties();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		propiedades.setProperty("FECHADEACTUALIZACION", sdf.format(ultimafecha));
		saveProperties(propiedades);
	}

	public boolean isActivo() {
		Properties propiedades = getSistemaProperties();
		String sactivo = propiedades.getProperty("ACTIVO");
		boolean retorno = false;
		if (sactivo != null) {
			try {
				retorno = Boolean.parseBoolean(sactivo);
			} catch (Exception e) {
			}
		}
		return retorno;
	}

	public boolean isRecrearAlInicio() {
		Properties propiedades = getSistemaProperties();
		String sactivo = propiedades.getProperty("RECREAR_AL_INICIAR");
		boolean retorno = true;
		if (sactivo != null) {
			try {
				retorno = Boolean.parseBoolean(sactivo);
			} catch (Exception e) {
			}
		}
		return retorno;
	}

	public int getMaxRegistros() {
		return maxRegistros;
	}

	public void setMaxRegistros(int maxRegistros) {
		this.maxRegistros = maxRegistros;
	}

	public boolean isUsarMemoria() {
		return usarMemoria;
	}

	public void setUsarMemoria(boolean usarMemoria) {
		this.usarMemoria = usarMemoria;
	}

}
