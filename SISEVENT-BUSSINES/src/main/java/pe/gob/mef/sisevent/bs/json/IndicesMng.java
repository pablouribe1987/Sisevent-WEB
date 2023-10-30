/**
 * 
 */
package pe.gob.mef.sisevent.bs.json;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.springframework.scheduling.annotation.Async;

import pe.gob.mef.sisevent.bs.utils.PropertiesMg;

public abstract class IndicesMng<K, V> {

	private static final Log log = LogFactory.getLog(IndicesMng.class);

	private int contador = 0;
	private long total = 1;
	private static final int NUMERO_MAXIMO_REGISTROS = 100;
	private static final String DIRINDEX = "INDEXSL";
	private static final String KEY_ULTIMA_EJECUCION = "ULTIMA_EJECUCION_";
	private static final String KEY_HORA_EJECUCION = "HORA_EJECUCION_";
	private static final int MAXIMO_RESULTADOS_BUSQUEDA = 25000;

	private long totalAindexar = 0;
	private static int indexados = 0;
	private boolean deternerReindexado = false;
	private boolean reindexarDeDondeSeQuedo = false;

	private static final String FROM = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇçÄï§¢°";
	private static final String TO = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunnccAiScto";

	private static String SPANISH_STOP_WORDS[] = { "un", "una", "unas", "unos", "uno", "sobre", "todo", "también", "tras", "otro", "algún", "alguno", "alguna",

	"algunos", "algunas", "ser", "es", "soy", "eres", "somos", "sois", "estoy", "esta", "estamos", "estais",

	"estan", "en", "para", "atras", "porque", "por qué", "estado", "estaba", "ante", "antes", "siendo",

	"ambos", "pero", "por", "poder", "puede", "puedo", "podemos", "podeis", "pueden", "fui", "fue", "fuimos",

	"fueron", "hacer", "hago", "hace", "hacemos", "haceis", "hacen", "cada", "fin", "incluso", "primero",

	"desde", "conseguir", "consigo", "consigue", "consigues", "conseguimos", "consiguen", "ir", "voy", "va",

	"vamos", "vais", "van", "vaya", "bueno", "ha", "tener", "tengo", "tiene", "tenemos", "teneis", "tienen",

	"el", "la", "lo", "las", "los", "su", "aqui", "mio", "tuyo", "ellos", "ellas", "nos", "nosotros", "vosotros",

	"vosotras", "si", "dentro", "solo", "solamente", "saber", "sabes", "sabe", "sabemos", "sabeis", "saben",

	"ultimo", "largo", "bastante", "haces", "muchos", "aquellos", "aquellas", "sus", "entonces", "tiempo",

	"verdad", "verdadero", "verdadera", "cierto", "ciertos", "cierta", "ciertas", "intentar", "intento",

	"intenta", "intentas", "intentamos", "intentais", "intentan", "dos", "bajo", "arriba", "encima", "usar",

	"uso", "usas", "usa", "usamos", "usais", "usan", "emplear", "empleo", "empleas", "emplean", "ampleamos",

	"empleais", "valor", "muy", "era", "eras", "eramos", "eran", "modo", "bien", "cual", "cuando", "donde",

	"mientras", "quien", "con", "entre", "sin", "trabajo", "trabajar", "trabajas", "trabaja", "trabajamos",

	"trabajais", "trabajan", "podria", "podrias", "podriamos", "podrian", "podriais", "yo", "aquel", "mi",

	"de", "a", "e", "i", "o", "u" };

	public IndicesMng() {
	}

	public void updateIndex() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp ULTIMA_EJECUCION = null;
		Integer HORA_EJECUCION = null;

		Properties systema = PropertiesMg.getSistemaProperties();

		log.info("--INICIANDO UPDATE INDEX " + getClase().toUpperCase() + "--");

		try {
			ULTIMA_EJECUCION = new Timestamp((sdf.parse((systema.getProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase()) == null ? "01/01/2000" : systema
					.getProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase())))).getTime());
		} catch (ParseException e) {
			log.error("NO SE HA CONFIGURADO LA ULTIMA FECHA DE EJECUCIÓN DEL INDEX " + KEY_ULTIMA_EJECUCION + getClase().toUpperCase());
			// e.printStackTrace();
			return;
		}

		try {
			int hora_del_dia = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if (systema.getProperty(KEY_HORA_EJECUCION + getClase().toUpperCase()) != null) {
				if (systema.getProperty(KEY_HORA_EJECUCION + getClase().toUpperCase()).indexOf(";") > 0) {
					StringTokenizer sk = new StringTokenizer(systema.getProperty(KEY_HORA_EJECUCION + getClase().toUpperCase()), ";");
					if (sk.hasMoreTokens()) {
						HORA_EJECUCION = Integer.parseInt(sk.nextToken());
						while (sk.hasMoreTokens()) {
							if (HORA_EJECUCION != null && HORA_EJECUCION.intValue() == hora_del_dia) {
								updateIndexExpediente(ULTIMA_EJECUCION);
							}
							HORA_EJECUCION = Integer.parseInt(sk.nextToken());
						}
					}
				} else {
					HORA_EJECUCION = Integer.parseInt(systema.getProperty(KEY_HORA_EJECUCION + getClase().toUpperCase()));
					if (HORA_EJECUCION != null && HORA_EJECUCION.intValue() == hora_del_dia) {
						updateIndexExpediente(ULTIMA_EJECUCION);
					}
				}
			}
		} catch (NumberFormatException ne) {
			log.info("001 NO SE ESPECIFICADO HA LA HORA DE INDEXACIÓN " + KEY_HORA_EJECUCION + getClase().toUpperCase());
		} catch (Exception e) {
			log.info("003 NO SE ESPECIFICADO HA LA HORA DE INDEXACIÓN " + KEY_HORA_EJECUCION + getClase().toUpperCase());
		}
	}

	@Async
	public void reindexar() {

		if (indexados > 0) {
			log.info("SALIENDO DE REINDEXACION DE EXPEDIENTE OTRO PROCESO EN CURSO");
			return;
		}

		Properties systema = PropertiesMg.getSistemaProperties();
		String ROOTKCH = PropertiesMg.getRootFolder() + System.getProperty("file.separator") + DIRINDEX + getClase().toUpperCase();
		File archivodir = new File(ROOTKCH);
		if (!archivodir.exists()) {
			if (!archivodir.mkdirs()) {
				return;
			} else {
				log.info("CREANDO EL DIRECTORIO INDESs: " + ROOTKCH);
			}
		}

		try {
			Directory directory = FSDirectory.open(archivodir);
			if (IndexWriter.isLocked(directory)) {
				IndexWriter.unlock(directory);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File[] archivos = archivodir.listFiles();
		if (archivos != null && archivos.length > 0) {
			for (int i = 0; i < archivos.length; i++) {
				archivos[i].delete();
			}
		}

		Timestamp NEW_ULTIMA_EJECUCION = new Timestamp(System.currentTimeMillis());
		// Timestamp ULTIMA_EJECUCIONREAL = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		boolean salir = false;
		boolean calculartotal = true;
		contador = 0;
		indexados = 1;

		while (!salir) {
			List<V> rcvLinea100V2Bksss = getXMayoDeID(contador, 0L, NUMERO_MAXIMO_REGISTROS);
			if (rcvLinea100V2Bksss != null) {
				if (rcvLinea100V2Bksss.size() > NUMERO_MAXIMO_REGISTROS) {
					if (calculartotal) {
						calculartotal = false;
						total = getTotalXMayoDeID(0L);
					}
				} else {
					salir = true;
					if (calculartotal) {
						calculartotal = false;
						total = rcvLinea100V2Bksss.size();
					}
				}
				try {
					IndexWriter idexWrite = getIndexWriter();
					for (V rcvLinea100V2Bk : rcvLinea100V2Bksss) {
						try {
							Document doc = getDataStringToObjec(rcvLinea100V2Bk);
							idexWrite.addDocument(doc);
							idexWrite.commit();
							// listadedocs.add(doc);
							contador++;
						} catch (Exception e) {
							e.printStackTrace();
						}
						indexados++;

						// if (getFecRegistroAud(rcvLinea100V2Bk) != null) {
						// if (ULTIMA_EJECUCIONREAL != null) {
						// if (getFecRegistroAud(rcvLinea100V2Bk).after(ULTIMA_EJECUCIONREAL)) {
						// ULTIMA_EJECUCIONREAL = getFecRegistroAud(rcvLinea100V2Bk);
						// }
						// } else {
						// ULTIMA_EJECUCIONREAL = getFecRegistroAud(rcvLinea100V2Bk);
						// }
						// }
					}

					if (indexados >= total)
						indexados = (int) total;

					idexWrite.close(true);
					log.info("INDEXANDO REGISTRO " + contador + " DE " + total);
				} catch (LockObtainFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (deternerReindexado) {
					deternerReindexado = false;
					break;
				}
			} else {
				break;
			}
		}

		// if (ULTIMA_EJECUCIONREAL != null) {
		if (systema != null) {
			systema.setProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase(), sdf.format(NEW_ULTIMA_EJECUCION));
			PropertiesMg.saveSistemaProperties(systema);
		}
		// }

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contador = 0;
		total = -1;
		indexados = 0;
	}

	@Async
	public void updateIndexExpediente(Timestamp ULTIMA_EJECUCION) {

		log.info("EJECUTANDOSE INDEX FULL TEXT: " + (new Date(System.currentTimeMillis())) + " PARA " + getClase().toUpperCase());

		if (indexados > 1) {
			log.info("SALIENDO DE INDEXACIoN HAY OTRO PROCESO EN CURSO");
			return;
		}

		Properties systema = PropertiesMg.getSistemaProperties();

		Timestamp NEW_ULTIMA_EJECUCION = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Timestamp ULTIMA_EJECUCIONREAL = ULTIMA_EJECUCION;

		IndexWriter writer = null;
		try {
			writer = getIndexWriter();
		} catch (LockObtainFailedException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		int iniciodesde = 0;
		boolean continuar = true;
		long total = 0;
		indexados = 1;

		if (writer != null) {
			try {
				while (continuar) {

					List<V> rcvLinea100V2Bksss = getXFechaModificacion(ULTIMA_EJECUCION, iniciodesde, NUMERO_MAXIMO_REGISTROS);

					iniciodesde += rcvLinea100V2Bksss.size();
					if (rcvLinea100V2Bksss.size() > NUMERO_MAXIMO_REGISTROS) {
						if (total == 0) {
							total = getTotalXFechaModificacion(ULTIMA_EJECUCION);
							log.info("TOTAL REAL DE EXPEDIENTES " + total);
							totalAindexar = total;
						}
					} else {
						if (total == 0) {
							total = rcvLinea100V2Bksss.size();
							log.info("TOTAL REAL DE EXPEDIENTES " + total);
							totalAindexar = total;
						}
						continuar = false;
					}

					for (V rcvLinea100V2Bk : rcvLinea100V2Bksss) {
						String iddoc = getIdToString(rcvLinea100V2Bk);
						Term term = new Term(getIdName(), iddoc);
						Query query = new TermQuery(term);

						boolean remplazar = false;

						try {
							IndexSearcher searcher = getIndexSearcher();
							IndexReader indexReader = searcher.getIndexReader();
							TopDocs docs = searcher.search(query, 1);
							if (docs.totalHits >= 1) {
								// writer.deleteDocuments(query);
								// writer.commit();
								remplazar = true;
							}
							searcher.close();
							indexReader.close();
						} catch (LockObtainFailedException e) {
							log.error("INDICE READER BLOKEADO...");
							e.printStackTrace();
						} catch (IOException e) {
							log.error("INDICE READER ERROR IO...");
							e.printStackTrace();
						}

						try {
							Document doc = getDataStringToObjec(rcvLinea100V2Bk);
							if (remplazar) {
								writer.updateDocument(term, doc);
							} else {
								writer.addDocument(doc);
							}
						} catch (CorruptIndexException e) {
							log.error("INDICE ERROR CORRUPTO...EN" + iddoc);
							e.printStackTrace();
						} catch (IOException e) {
							log.error("INDICE READER ERROR IO...EN" + iddoc);
							e.printStackTrace();
						}

						indexados++;

						// if (getFecRegistroAud(rcvLinea100V2Bk) != null) {
						// if (getFecRegistroAud(rcvLinea100V2Bk).after(ULTIMA_EJECUCIONREAL)) {
						// ULTIMA_EJECUCIONREAL = getFecRegistroAud(rcvLinea100V2Bk);
						// }
						// }
					}

					try {
						writer.commit();
					} catch (CorruptIndexException e) {
						log.error("INDICE ERROR CORRUPTO AL COMMIT...");
						e.printStackTrace();
					} catch (IOException e) {
						log.error("INDICE ERROR CORRUPTO AL COMMIT...");
						e.printStackTrace();
					}
					if (systema != null) {
						systema.setProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase(), sdf.format(NEW_ULTIMA_EJECUCION));
						PropertiesMg.saveSistemaProperties(systema);
					}
					if (deternerReindexado) {
						deternerReindexado = false;
						break;
					}
				}
			} finally {
				try {
					writer.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		indexados = 0;
	}

	public void updateIndexExpedienteDesedeUltiaFecha() {
		Timestamp ULTIMA_EJECUCION = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Properties systema = PropertiesMg.getSistemaProperties();

		try {
			ULTIMA_EJECUCION = new Timestamp((sdf.parse((systema.getProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase()) == null ? "01/01/2000" : systema
					.getProperty(KEY_ULTIMA_EJECUCION + getClase().toUpperCase())))).getTime());
		} catch (ParseException e) {
			log.error("NO SE HA CONFIGURADO LA ULTIMA FECHA DE EJECUCIÓN DEL INDEX " + getClase().toUpperCase());
			return;
		}

		if (ULTIMA_EJECUCION != null) {
			updateIndexExpediente(ULTIMA_EJECUCION);
		}
	}

	@Async
	public void insertIndexExpediente(V rcvLinea100V2Bk) {

		String iddoc = getIdToString(rcvLinea100V2Bk);
		log.info(getClase().toUpperCase() + " EJECUTANDOSE INDEX PARA: " + iddoc);

		if (indexados > 1) {
			log.info(getClase().toUpperCase() + " SALIENDO DE INDEXACION HAY OTRO PROCESO EN CURSO");
			return;
		}

		indexados = 1;
		IndexWriter writer = null;
		try {
			writer = getIndexWriter();
		} catch (LockObtainFailedException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		if (writer != null) {
			try {
				Term term = new Term(getIdName(), iddoc);
				Query query = new TermQuery(term);

				boolean remplazar = false;

				try {
					IndexSearcher searcher = getIndexSearcher();
					IndexReader indexReader = searcher.getIndexReader();
					TopDocs docs = searcher.search(query, 1);
					if (docs.totalHits >= 1) {
						// writer.deleteDocuments(query);
						// writer.commit();
						remplazar = true;
					}
					searcher.close();
					indexReader.close();
				} catch (LockObtainFailedException e) {
					log.error("INDICE READER BLOKEADO...");
					e.printStackTrace();
				} catch (IOException e) {
					log.error("INDICE READER ERROR IO...");
					e.printStackTrace();
				}

				try {
					Document doc = getDataStringToObjec(rcvLinea100V2Bk);
					if (remplazar) {
						writer.updateDocument(term, doc);
					} else {
						writer.addDocument(doc);
					}
				} catch (CorruptIndexException e) {
					log.error("INDICE ERROR CORRUPTO...EN" + iddoc);
					e.printStackTrace();
				} catch (IOException e) {
					log.error("INDICE READER ERROR IO...EN" + iddoc);
					e.printStackTrace();
				}

			} finally {
				try {
					writer.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		indexados = 0;
	}

	public IndexWriter getIndexWriter() throws IOException, LockObtainFailedException {

		String ROOTKCH = PropertiesMg.getRootFolder() + System.getProperty("file.separator") + DIRINDEX + getClase().toUpperCase();
		File archivodir = new File(ROOTKCH);
		if (!archivodir.exists()) {
			if (!archivodir.mkdirs()) {
				return null;
			} else {
				log.info("CREANDO EL DIRECTORIO INDESs: " + ROOTKCH);
			}
		}

		Directory directory = FSDirectory.open(archivodir);
		if (IndexWriter.isLocked(directory)) {
			IndexWriter.unlock(directory);
		}

		Analyzer analizer = getAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_34, analizer);
		conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter indexWriter = new IndexWriter(directory, conf);
		return indexWriter;
	}

	public static Analyzer getAnalyzer() {
		Set<String> exclusionSet = new HashSet<String>();
		for (int i = 0; i < SPANISH_STOP_WORDS.length; i++) {
			exclusionSet.add(SPANISH_STOP_WORDS[i]);
		}
		Analyzer analizer = new SpanishAnalyzer(Version.LUCENE_34, SpanishAnalyzer.getDefaultStopSet(), exclusionSet);
		return analizer;
	}

	public IndexSearcher getIndexSearcher() throws IOException, LockObtainFailedException {
		String ROOTKCH = PropertiesMg.getRootFolder() + System.getProperty("file.separator") + DIRINDEX + getClase().toUpperCase();
		File archivodir = new File(ROOTKCH);
		if (!archivodir.exists()) {
			if (!archivodir.mkdirs()) {
				return null;
			} else {
				log.info("CREANDO EL DIRECTORIO INDESs: " + ROOTKCH);
			}
		}
		Directory directory = FSDirectory.open(archivodir);
		IndexReader indexreader = IndexReader.open(directory, true);
		IndexSearcher searcher = new IndexSearcher(indexreader);
		return searcher;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalAindexar() {
		return totalAindexar;
	}

	public void setTotalAindexar(long totalAindexar) {
		this.totalAindexar = totalAindexar;
	}

	public int getIndexados() {
		return indexados;
	}

	@SuppressWarnings("static-access")
	public void setIndexados(int indexados) {
		this.indexados = indexados;
	}

	public boolean isDeternerReindexado() {
		return deternerReindexado;
	}

	public void setDeternerReindexado(boolean deternerReindexado) {
		this.deternerReindexado = deternerReindexado;
	}

	public boolean isReindexarDeDondeSeQuedo() {
		return reindexarDeDondeSeQuedo;
	}

	public void setReindexarDeDondeSeQuedo(boolean reindexarDeDondeSeQuedo) {
		this.reindexarDeDondeSeQuedo = reindexarDeDondeSeQuedo;
	}

	public abstract String getClase();

	public abstract Class<?> getTipoClase();

	public abstract List<V> getXMayoDeID(int contador, long id, int NUMERO_MAXIMO_REGISTROS);

	public abstract Long getTotalXMayoDeID(long id);

	public abstract String getIdName();

	public abstract Timestamp getFecRegistroAud(Object o);

	public abstract List<V> getXFechaModificacion(Timestamp ULTIMA_EJECUCION, int iniciodesde, int NUMERO_MAXIMO_REGISTROS);

	public abstract Long getTotalXFechaModificacion(Timestamp ULTIMA_EJECUCION);

	public abstract String getIdToString(V objeto);

	public abstract Map<String, String> getCAMPOS();

	public abstract K convertToStringToID(String s);

	public Document getDataStringToObjec(Object b) {
		try {
			if (b == null)
				return null;

			Document doc = new Document();
			java.lang.reflect.Field[] camposdea = b.getClass().getDeclaredFields();
			for (int i = 0; i < camposdea.length; i++) {
				String camponame = camposdea[i].getName();
				String sgetMetod = "get" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
				Class<?>[] types = new Class[] {};
				try {
					Method method = b.getClass().getMethod(sgetMetod, types);
					Object valuecampoa = method.invoke(b, new Object[0]);

					if (valuecampoa != null) {
						Fieldable field = null;
						if (valuecampoa instanceof String) {
							String valorstring = reconocerTexto((String) valuecampoa);
							if (camponame.equals(getIdName())) {
								field = new Field(camponame, valorstring, Field.Store.YES, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							} else {
								field = new Field(camponame, valorstring, Field.Store.NO, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							}
						} else if (valuecampoa instanceof Number) {
							Number number = (Number) valuecampoa;
							NumericField numberField = null;
							if (camponame.equals(getIdName())) {
								numberField = new NumericField(camponame, Field.Store.YES, true);
							} else {
								numberField = new NumericField(camponame, Field.Store.NO, true);
							}
							if (valuecampoa instanceof Long) {
								numberField.setLongValue(number.longValue());
							} else if (valuecampoa instanceof Float) {
								numberField.setFloatValue(number.floatValue());
							} else if (valuecampoa instanceof Double) {
								numberField.setDoubleValue(number.doubleValue());
							} else {
								numberField.setIntValue(number.intValue());
							}
							field = numberField;
						} else if (valuecampoa instanceof Date) {
							Date datevalue = (Date) valuecampoa;
							long datelong = datevalue.getTime();
							NumericField numberField = null;
							if (camponame.equals(getIdName())) {
								numberField = new NumericField(camponame, Field.Store.YES, true);
							} else {
								numberField = new NumericField(camponame, Field.Store.NO, true);
							}
							numberField.setLongValue(datelong);
							field = numberField;
						} else if (valuecampoa instanceof List) {
							String valorstring = reconocerTexto(valuecampoa.toString());
							if (camponame.equals(getIdName())) {
								field = new Field(camponame, valorstring, Field.Store.YES, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							} else {
								field = new Field(camponame, valorstring, Field.Store.NO, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							}
						} else {
							String valorstring = valuecampoa.toString().toLowerCase();
							if (camponame.equals(getIdName())) {
								field = new Field(camponame, valorstring, Field.Store.YES, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							} else {
								field = new Field(camponame, valorstring, Field.Store.NO, Field.Index.ANALYZED);
								field = new Field(camponame + "_O", valorstring, Field.Store.NO, Field.Index.NOT_ANALYZED);
							}
						}
						doc.add(field);
					}
				} catch (NoSuchMethodException exception) {
					// System.out.println("Error NoSuchMethodException: " +
					// exception.getMessage());
				}
			}
			return doc;
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private String tratarnumerodocumento(String numero) {
		if (numero == null)
			return null;
		if (numero.length() <= 0)
			return numero;

		if (numero.trim().indexOf(" ") > -1) {
			StringTokenizer stk = new StringTokenizer(numero.trim(), " ");
			StringBuffer sb = new StringBuffer();
			int total = 0;
			while (stk.hasMoreElements()) {
				String s = tratarnumerodocumento(stk.nextToken());
				sb.append(s);
				total++;
			}
			if (total == 2) {
				stk = new StringTokenizer(numero.trim(), " ");
				String s1 = stk.nextToken();
				String s2 = stk.nextToken();
				long numero1 = getSoloNumero(s1);
				long numero2 = getSoloNumero(s2);
				if (numero1 > 0 && numero2 > 0) {
					if (numero2 > (numero1 + 1)) {
						StringBuffer sb2 = new StringBuffer();
						String snum = getContinuacionDeNumero(s1);
						for (long n = (numero1 + 1); n < numero2; n++) {
							String s = tratarnumerodocumento(n + snum);
							sb2.append(s);
						}
						sb.append(sb2.toString());
						return sb.toString();
					}
				}
			} else {
				return sb.toString();
			}
		}
		if (isIniciaConNumero(numero)) {
			String numerotmp = numero;
			String numerofinal1 = (numerotmp.startsWith("0") ? numerotmp.substring("0".length()) : "");
			String numerofinal2 = (numerotmp.startsWith("00") ? numerotmp.substring("00".length()) : "");
			String numerofinal3 = (numerotmp.startsWith("000") ? numerotmp.substring("000".length()) : "");
			String numerofinal4 = (numerotmp.startsWith("0000") ? numerotmp.substring("0000".length()) : "");
			String numerofinal5 = (numerotmp.startsWith("00000") ? numerotmp.substring("00000".length()) : "");
			String numerofinal6 = (numerotmp.startsWith("000000") ? numerotmp.substring("000000".length()) : "");
			String numerofinal7 = (numerotmp.startsWith("0000000") ? numerotmp.substring("0000000".length()) : "");
			String numerofinal = (numerofinal7 + " " + numerofinal6 + " " + numerofinal5 + " " + numerofinal4 + " " + numerofinal3 + " " + numerofinal2 + " "
					+ numerofinal1 + " " + numero).trim().toLowerCase();
			return limpiarCaracteres(numerofinal + " 0" + numerofinal + " 00" + numerofinal + " 000" + numerofinal + " 0000" + numerofinal + " 00000"
					+ numerofinal + " 000000" + numerofinal + " 0000000" + numerofinal);
		} else {
			return limpiarCaracteres(numero).toLowerCase();
		}
	}

	private boolean isIniciaConNumero(String s) {
		boolean retorno = false;
		int coincidencias = 0;
		if (s != null && s.trim().length() > 1) {
			for (int i = 0; i < s.length(); i++) {
				char a = s.charAt(i);
				if (Character.isDigit(a)) {
					coincidencias++;
				}
				if (i > 0) {
					if (coincidencias == 2) {
						retorno = true;
					}
					break;
				}
			}
		}
		return retorno;
	}

	private long getSoloNumero(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			if (Character.isDigit(a)) {
				sb.append(a);
			} else {
				break;
			}
		}
		long retorno = 0;
		String digitos = sb.toString();
		if (digitos != null && digitos.length() > 0) {
			try {
				retorno = Long.parseLong(digitos);
			} catch (Exception e) {
			}
		}
		return retorno;
	}

	private String getContinuacionDeNumero(String s) {
		StringBuffer sb = new StringBuffer();
		boolean yapasonumero = false;
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			if (!Character.isDigit(a) && !yapasonumero) {
				yapasonumero = true;
			} else {
				sb.append(a);
			}
		}
		return sb.toString();
	}

	private boolean isEsSoloNumero(String s) {
		boolean yapasonumero = true;
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			if (!Character.isDigit(a)) {
				yapasonumero = false;
				break;
			}
		}
		return yapasonumero;
	}

	public List<K> findInIndexesArchivo(String texto) throws JsonException {

		List<K> resultado = new ArrayList<K>();

		if (texto == null)
			throw new JsonException(
					"DEBE INGRESAR EL TEXTO DE BUSQUEDA....\nPUEDE ESPECIFICAR UN CAMPO DETERMINADO UTILIZANDO \"=\", UTILICE EL \";\" PARA SEPARAR DIFERENTES CAMPOS, PUEDE UTILIZAR LAS PALABRAS CLAVE \"AND, OR, ( )\" PARA SEPARAR VALORES. ");
		if (texto.trim().length() < 4)
			throw new JsonException(
					"DEBE INGRESAR EL TEXTO DE BUSQUEDA MAYOR A TRES CARACTERES...\nPUEDE ESPECIFICAR UN CAMPO DETERMINADO UTILIZANDO \"=\", UTILICE EL \";\" PARA SEPARAR DIFERENTES CAMPOS, PUEDE UTILIZAR LAS PALABRAS CLAVE \"AND, OR, ( )\" PARA SEPARAR VALORES. ");

		java.lang.reflect.Field[] camposdea = getTipoClase().getClass().getDeclaredFields();

		BooleanQuery combined = new BooleanQuery();

		if (texto.indexOf(":") > 0) {
			if (texto.indexOf(';') > 0) {
				StringTokenizer stk = new StringTokenizer(texto, ";");
				BooleanQuery combinarcamposvalor = new BooleanQuery();
				while (stk.hasMoreElements()) {
					String frase = (String) stk.nextElement();
					if (frase.indexOf(":") > 0) {
						StringTokenizer stkIg = new StringTokenizer(frase, ":");
						while (stkIg.hasMoreElements()) {
							String campo = (String) stkIg.nextElement();
							if (getCAMPOS().containsKey(campo.toUpperCase())) {
								campo = getCAMPOS().get(campo.toUpperCase());
							}
							if (stkIg.hasMoreElements()) {
								String valor = (String) stkIg.nextElement();
								Query querycampo = getQry(valor.trim(), campo);
								combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
							} else {
								BooleanQuery combinarcamposconocidos = new BooleanQuery();
								for (int i = 0; i < camposdea.length; i++) {
									String camponame = camposdea[i].getName();
									Query qidcausa = getQry(campo, camponame);
									combinarcamposconocidos.add(qidcausa, BooleanClause.Occur.SHOULD);
								}
							}
						}
					} else {
						BooleanQuery combinarcamposconocidos = new BooleanQuery();
						for (int i = 0; i < camposdea.length; i++) {
							String camponame = camposdea[i].getName();
							Query qidcausa = getQry(frase, camponame);
							combinarcamposconocidos.add(qidcausa, BooleanClause.Occur.SHOULD);
						}
						combinarcamposvalor.add(combinarcamposconocidos, BooleanClause.Occur.MUST);
					}
				}
				combined.add(combinarcamposvalor, BooleanClause.Occur.SHOULD);
			} else {
				StringTokenizer stkIg = new StringTokenizer(texto, "=");
				while (stkIg.hasMoreElements()) {
					String campo = (String) stkIg.nextElement();
					if (getCAMPOS().containsKey(campo.toUpperCase())) {
						campo = getCAMPOS().get(campo.toUpperCase());
					}
					if (stkIg.hasMoreElements()) {
						String valor = (String) stkIg.nextElement();
						Query querycampo = getQry(valor, campo);
						combined.add(querycampo, BooleanClause.Occur.SHOULD);
					}
				}
			}
		} else {
			for (int i = 0; i < camposdea.length; i++) {
				String camponame = camposdea[i].getName();
				Query qidcausa = getQry(texto, camponame);
				combined.add(qidcausa, BooleanClause.Occur.SHOULD);
			}
		}

		IndexSearcher searcher = null;
		try {
			searcher = getIndexSearcher();
		} catch (LockObtainFailedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		long start = System.currentTimeMillis();

		try {
			if (searcher != null) {
				TopDocs docs = searcher.search(combined, MAXIMO_RESULTADOS_BUSQUEDA);
				long end = System.currentTimeMillis();
				log.info("RESULTADO EN: " + (end - start) + " TOTAL DE HITS " + docs.totalHits);

				ScoreDoc[] scoreDocs = docs.scoreDocs;
				for (ScoreDoc scoreDoc : scoreDocs) {
					try {
						int docid = scoreDoc.doc;
						// float score = scoreDoc.score;
						Document doc = searcher.doc(docid);

						String iddoctxt = doc.get(getIdName());

						K id = convertToStringToID(iddoctxt);

						if (!resultado.contains(id))
							resultado.add(id);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				searcher.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new JsonException(e1.getMessage());
		}

		return resultado;
	}

	private String limpiarCaracteres(String source) {
		HashMap<Character, Character> hmap = new HashMap<Character, Character>();
		for (int i = 0; i < FROM.length(); i++) {
			Character from = FROM.charAt(i);
			Character to = TO.charAt(i);
			hmap.put(from, to);
		}
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < source.length(); j++) {
			Character s = source.charAt(j);
			if (hmap.containsKey(s)) {
				sb.append(hmap.get(s).charValue());
			} else {
				sb.append(s.charValue());
			}
		}
		return sb.toString();
	}

	private String reconocerTexto(String s) {
		StringBuffer sb = new StringBuffer();
		if (s.trim().indexOf(" ") > -1) {
			StringTokenizer stk = new StringTokenizer(s.trim(), " ");
			while (stk.hasMoreElements()) {
				String ss = stk.nextToken();
				if (isIniciaConNumero(ss)) {
					sb.append(tratarnumerodocumento(ss.toLowerCase()));
				} else {
					sb.append(limpiarCaracteres(ss.toLowerCase()));
				}
			}
		} else {
			if (isIniciaConNumero(s)) {
				sb.append(tratarnumerodocumento(s.toLowerCase()));
			} else {
				sb.append(limpiarCaracteres(s.toLowerCase()));
			}
		}
		return sb.toString();
	}

	private Query getQry(String s, String tipodoc) {
		String ssearch = null;
		if (isEsSoloNumero(s)) {
			ssearch = s;
		} else {
			ssearch = reconocerTexto(s);
		}

		QueryParser parser = new QueryParser(Version.LUCENE_34, tipodoc, getAnalyzer());
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q = null;
		try {
			q = parser.parse(ssearch);
		} catch (org.apache.lucene.queryParser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}

	// /---------------------------

	public Query rangeQuery(String key, Number from, Number to, boolean includeFrom, boolean includeTo) {
		if (from instanceof Long || to instanceof Long) {
			return NumericRangeQuery.newLongRange(key, from != null ? from.longValue() : 0, to != null ? to.longValue() : Long.MAX_VALUE, includeFrom,
					includeTo);
		} else if (from instanceof Double || to instanceof Double) {
			return NumericRangeQuery.newDoubleRange(key, from != null ? from.doubleValue() : 0, to != null ? to.doubleValue() : Double.MAX_VALUE, includeFrom,
					includeTo);
		} else if (from instanceof Float || to instanceof Float) {
			return NumericRangeQuery.newFloatRange(key, from != null ? from.floatValue() : 0, to != null ? to.floatValue() : Float.MAX_VALUE, includeFrom,
					includeTo);
		} else {
			return NumericRangeQuery.newIntRange(key, from != null ? from.intValue() : 0, to != null ? to.intValue() : Integer.MAX_VALUE, includeFrom,
					includeTo);
		}
	}

	public List<K> findInIndexesArchivo(String query, String sortField, OrdenLista orden) throws JsonException {

		List<K> resultado = new ArrayList<K>();

		if (query == null)
			throw new JsonException(
					"DEBE INGRESAR EL TEXTO DE BUSQUEDA....\nPUEDE ESPECIFICAR UN CAMPO DETERMINADO UTILIZANDO \"=\", UTILICE EL \";\" PARA SEPARAR DIFERENTES CAMPOS, PUEDE UTILIZAR LAS PALABRAS CLAVE \"AND, OR, ( )\" PARA SEPARAR VALORES. ");
		if (query.trim().length() < 4)
			throw new JsonException(
					"DEBE INGRESAR EL TEXTO DE BUSQUEDA MAYOR A TRES CARACTERES...\nPUEDE ESPECIFICAR UN CAMPO DETERMINADO UTILIZANDO \"=\", UTILICE EL \";\" PARA SEPARAR DIFERENTES CAMPOS, PUEDE UTILIZAR LAS PALABRAS CLAVE \"AND, OR, ( )\" PARA SEPARAR VALORES. ");
		BooleanQuery combinarcamposvalor = new BooleanQuery();
		if (query.indexOf(';') > -1) {
			StringTokenizer stk = new StringTokenizer(query, ";");
			while (stk.hasMoreElements()) {
				String frase = (String) stk.nextElement();
				if (frase.indexOf(":") > 0) {
					StringTokenizer stkIg = new StringTokenizer(frase, ":");
					while (stkIg.hasMoreElements()) {
						String campo = (String) stkIg.nextElement();
						if (stkIg.hasMoreElements()) {
							String valor = (String) stkIg.nextElement();
							java.lang.reflect.Field camposdea = null;
							try {
								camposdea = getTipoClase().getDeclaredField(campo);
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							}
							if (camposdea != null) {
								Class<?> clazz = camposdea.getType();
								if (clazz != null) {

									Query querycampo = null;

									if (clazz == String.class) {
										querycampo = getQry(valor.trim(), campo);
										combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
									} else if (clazz == Long.class) {
										// querycampo = getQry(valor.trim(), campo);
										Long valorL = null;
										try {
											valorL = Long.parseLong(valor);
										} catch (Exception e) {
											e.printStackTrace();
										}
										if (valorL != null && valorL.longValue() > 0) {
											querycampo = NumericRangeQuery.newLongRange(campo, valorL, valorL, true, true);
											combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
										}
									} else if (clazz == Float.class) {
										// querycampo = getQry(valor.trim(), campo);
										Float valorF = null;
										try {
											valorF = Float.parseFloat(valor);
										} catch (Exception e) {
											e.printStackTrace();
										}
										if (valorF != null && valorF.floatValue() > 0) {
											querycampo = NumericRangeQuery.newFloatRange(campo, valorF, valorF, true, true);
											combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
										}
									} else if (clazz == Double.class) {
										// querycampo = getQry(valor.trim(), campo);
										Double valorD = null;
										try {
											valorD = Double.parseDouble(valor);
										} catch (Exception e) {
											e.printStackTrace();
										}
										if (valorD != null && valorD.doubleValue() > 0) {
											querycampo = NumericRangeQuery.newDoubleRange(campo, valorD, valorD, true, true);
											combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
										}
									} else if (clazz == Integer.class) {
										// querycampo = getQry(valor.trim(), campo);
										Integer valorI = null;
										try {
											valorI = Integer.parseInt(valor);
										} catch (Exception e) {
											e.printStackTrace();
										}
										if (valorI != null && valorI.intValue() > 0) {
											querycampo = NumericRangeQuery.newIntRange(campo, valorI, valorI, true, true);
											combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
										}
									} else if (clazz == Date.class) {
										// querycampo = getQry(valor.trim(), campo);
										Long valorL = null;
										try {
											valorL = Long.parseLong(valor);
										} catch (Exception e) {
											e.printStackTrace();
										}
										if (valorL != null && valorL.longValue() > 0) {
											querycampo = NumericRangeQuery.newLongRange(campo, valorL, valorL, true, true);
											combinarcamposvalor.add(querycampo, BooleanClause.Occur.MUST);
										}
									} else if (clazz == List.class) {
										querycampo = getQry(valor.trim(), campo);
									} else {
										querycampo = getQry(valor.trim(), campo);
									}
								}
							}
						}
					}
				}
			}
		}

		Sort sort = null;
		if (sortField != null && sortField.trim().length() > 0 && orden != null && orden != OrdenLista.UNSORTED) {
			boolean ascedente = true;
			if (orden != OrdenLista.DESCENDING) {
				ascedente = false;
			}
			java.lang.reflect.Field camposdea = null;
			try {
				camposdea = getTipoClase().getDeclaredField(sortField);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			if (camposdea != null) {
				Class<?> clazz = camposdea.getType();
				if (clazz != null) {
					int fieldType = SortField.INT;
					if (clazz == String.class) {
						sort = new Sort(new SortField(sortField + "_O", SortField.STRING, ascedente));
					} else if (clazz == Long.class) {
						fieldType = SortField.LONG;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == Float.class) {
						fieldType = SortField.FLOAT;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == Double.class) {
						fieldType = SortField.DOUBLE;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == Integer.class) {
						fieldType = SortField.INT;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == Date.class) {
						fieldType = SortField.LONG;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == java.sql.Date.class) {
						fieldType = SortField.LONG;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == java.sql.Timestamp.class) {
						fieldType = SortField.LONG;
						sort = new Sort(new SortField(sortField, fieldType, ascedente));
					} else if (clazz == List.class) {
						sort = new Sort(new SortField(sortField + "_O", SortField.STRING, ascedente));
					} else if (clazz == ArrayList.class) {
						sort = new Sort(new SortField(sortField + "_O", SortField.STRING, ascedente));
					} else {
						sort = new Sort(new SortField(sortField + "_O", SortField.STRING, ascedente));
					}
				}
			}
		}

		IndexSearcher searcher = null;
		try {
			searcher = getIndexSearcher();
		} catch (LockObtainFailedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		long start = System.currentTimeMillis();

		try {
			if (searcher != null) {
				TopDocs docs = null;
				if (sort != null) {
					docs = searcher.search(combinarcamposvalor, MAXIMO_RESULTADOS_BUSQUEDA, sort);
				} else {
					docs = searcher.search(combinarcamposvalor, MAXIMO_RESULTADOS_BUSQUEDA);
				}
				long end = System.currentTimeMillis();
				log.info("RESULTADO EN: " + (end - start) + " TOTAL DE HITS " + docs.totalHits);

				ScoreDoc[] scoreDocs = docs.scoreDocs;
				for (ScoreDoc scoreDoc : scoreDocs) {
					try {
						int docid = scoreDoc.doc;
						// float score = scoreDoc.score;
						Document doc = searcher.doc(docid);

						String iddoctxt = doc.get(getIdName());

						K id = convertToStringToID(iddoctxt);

						if (!resultado.contains(id))
							resultado.add(id);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				searcher.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new JsonException(e1.getMessage());
		}

		return resultado;
	}

}
