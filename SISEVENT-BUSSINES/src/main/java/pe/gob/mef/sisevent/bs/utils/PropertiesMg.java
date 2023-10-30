/**
 * 
 */
package pe.gob.mef.sisevent.bs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase para el manejo de las constantes del Sistema.
 * 
 * @author Carlos Aguilar
 * @version 2.0, 11/01/2012
 * 
 * 
 *          /----------Nombre----------/----fecha----/-------------Motivo------
 *          --------/ Carlos Aguilar Chamochumbi 11/01/2012 Creación de la
 *          clase
 * 
 * 
 */
public class PropertiesMg implements Serializable {

	private static final long serialVersionUID = -123125970677914710L;

	private static final Log log = LogFactory.getLog(PropertiesMg.class);
	
	private static final String archivo = "pe.gob.mef.sisevent.bs.resources.Rutas";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(archivo);
	
	public static final String ESQUEMA = "SISEVENT."; 
	
	private static final String SISTEM_PROPERTIES = "sistema";
	private static final String ALARMA_PROPERTIES = "alarma";
	private static File rootDir = null;
	private static boolean imprimirrootsistema = true;
	private static final String PROPERTIES_DIR = "propiedades-sisevent";
    private static final String AUXILIARES_DIR = "auxiliares";
	
    //////////////////////////////////////////////////////////////////
    public static final String L_DOMINIO = "DOMINIO";
    public static final String D_DOMINIO = "http://localhost:8080/sisevent";
    
	public static final String KEY_SMTP_HOST_NAME = "SMTP_HOST";
	public static final String KEY_SMTP_PORT = "SMTP_PORT";
	public static final String KEY_SMTP_FROM_ADDRESS = "SMTP_FROM_ADDRESS";
	public static final String KEY_SMTP_FROM_PASSWORD = "SMTP_FROM_PASSWORD";
	public static final String KEY_SMTP_TO_ADDRESS = "SMTP_TO_ADDRESS";
	public static final String KEY_SMTP_SECURITY = "KEY_SMTP_SECURITY";
	public static final String KEY_SMTP_DEBUG = "KEY_SMTP_DEBUG";
	public static final String KEY_FILES = "FILES_TRANSFERENCIA";
	public static final String KEY_ESTADOS_REGISTROS_NUEVO = "ESTADOS_REGISTROS_NUEVO";
	public static final String KEY_ESTADOS_REGISTROS_ELIMINADO = "ESTADOS_REGISTROS_ELIMINADO";

	public static final String DEFOULT_SMTP_HOST_NAME = "192.168.1.2";
	public static final String DEFOULT_SMTP_PORT = "25";
	public static final String DEFOULT_SMTP_FROM_ADDRESS = "cafach@hotmail.com";
	public static final String DEFOULT_SMTP_FROM_PASSWORD = "password";
	public static final String DEFOULT_SMTP_TO_ADDRESS = "cafach@hotmail.com";
	public static final String DEFOULT_SMTP_SECURITY = "0";
	public static final String DEFOULT_SMTP_DEBUG = "false";
	
	public static final String D_ALARMA_REPRESENTANTE_PERSONA = "SISTEMA DE TRÁMITE DOCUMENTARIO";
	public static final String L_ALARMA_REPRESENTANTE_PERSONA = "ALARMA_REPRESENTANTE_PERSONA";
	public static final String D_ALARMA_REPRESENTANTE_CORREO = "stramite@mef.gob.pe";
	public static final String L_ALARMA_REPRESENTANTE_CORREO = "ALARMA_REPRESENTANTE_CORREO";
	public static final String D_ALARMA_REPRESENTANTE_ANEXO = "7426";
	public static final String L_ALARMA_REPRESENTANTE_ANEXO = "ALARMA_REPRESENTANTE_CORREO";
	public static final String D_ALARMA_REPRESENTANTE_AREA = "Oficina General de Tecnolog&iacute;as de la Informaci&oacute;n";
	public static final String L_ALARMA_REPRESENTANTE_AREA = "ALARMA_REPRESENTANTE_AREA ";
	

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static boolean containsKey(String key) {
		return RESOURCE_BUNDLE.containsKey(key);
	}

	public synchronized static Properties getSistemaProperties() {
		Properties propertieSistema = null;
		File filepropiedadesdir = getPropiedadesDir();
		if (filepropiedadesdir == null)
			return null;

		String propiedades = filepropiedadesdir.getAbsolutePath() + System.getProperty("file.separator")
				+ SISTEM_PROPERTIES;
		File filepropiedades = new File(propiedades + ".properties");
		if (filepropiedades.exists()) {
			FileInputStream input = null;
			propertieSistema = new Properties();
			try {
				input = new FileInputStream(filepropiedades);
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
			// AGREGAR PROPIEDADES POR DEFECTO
			saveSistemaProperties(propertieSistema);
		}
		return propertieSistema;
	}

	public synchronized static void saveSistemaProperties(Properties p) {

		File filepropiedadesdir = getPropiedadesDir();
		if (filepropiedadesdir == null)
			return;

		String propiedades = filepropiedadesdir.getAbsolutePath() + System.getProperty("file.separator")
				+ SISTEM_PROPERTIES;
		File sistemaproriedadesFile = new File(propiedades + ".properties");
		FileOutputStream ouput = null;
		try {
			ouput = new FileOutputStream(sistemaproriedadesFile);
			p.store(ouput, "PARAMETROS DEL SISTEMA");
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

	public synchronized static Properties getAlarmaProperties() {
		Properties propertieSistema = null;

		File filepropiedadesdir = getPropiedadesDir();
		if (filepropiedadesdir == null)
			return null;

		String propiedades = filepropiedadesdir.getAbsolutePath() + System.getProperty("file.separator")
				+ ALARMA_PROPERTIES;
		File filepropiedades = new File(propiedades + ".properties");
		if (filepropiedades.exists()) {
			FileInputStream input = null;
			propertieSistema = new Properties();
			try {
				input = new FileInputStream(filepropiedades);
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

			saveAlarmaProperties(propertieSistema);
		}

		return propertieSistema;
	}

	public synchronized static void saveAlarmaProperties(Properties p) {

		File filepropiedadesdir = getPropiedadesDir();
		if (filepropiedadesdir == null)
			return;

		String propiedades = filepropiedadesdir.getAbsolutePath() + System.getProperty("file.separator")
				+ ALARMA_PROPERTIES;
		File sistemaproriedadesFile = new File(propiedades + ".properties");
		FileOutputStream ouput = null;
		try {
			ouput = new FileOutputStream(sistemaproriedadesFile);
			p.store(ouput, "PARAMETROS DE LAS ALARMAS DEL SISTEMA");
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

	public static File getRootFolder() {
		if (rootDir == null) {
			String home = System.getProperty("user.home", ".");
			String os = System.getProperty("os.name").toLowerCase();
			String dirfilename = "/";
			if (os.contains("solaris") || os.contains("sunos") || os.contains("linux") || os.contains("unix")) {
				dirfilename = home + File.separator;
			} else if (os.contains("win")) {
				String appdata = System.getenv("APPDATA");
				dirfilename = (appdata != null ? (appdata + File.separator) : (home + File.separator));
			} else if (os.contains("mac")) {
				dirfilename = (home + "Library/Application Support/minecraft" + File.separator);
			}

			File f = new File(dirfilename);

			String sRAIZ = getString("RAIZ");
			if (f.exists() && sRAIZ != null) {
				String rutaconraiz = f.getAbsolutePath() + File.separator + sRAIZ;
				f = new File(rutaconraiz);
				if (!f.exists()) {
					f.mkdirs();
				}
			}

			if (f.exists()) {
				if (imprimirrootsistema) {
					System.out.println("INICIANDO PROPIEDADES EN DIRECTORIO: " + f.getAbsolutePath());
					imprimirrootsistema = false;
					rootDir = f;
				}
			} else {
				System.out.println("INICIANDO PROPIEDADES EN DIRECTORIO: NO SE ENCUENTRA EL DIRECTORIO" + dirfilename);
			}
		}
		return rootDir;
	}

	private static File getPropiedadesDir() {
		File filesistemroot = getRootFolder();
		if (filesistemroot == null)
			return null;
		String propiedadesdir = filesistemroot.getAbsolutePath() + System.getProperty("file.separator")
				+ PROPERTIES_DIR;
		File filepropiedadesdir = new File(propiedadesdir);
		if (!filepropiedadesdir.exists()) {
			log.info("CREANDO EL DIRECTORIO DE PROPIEDADES " + propiedadesdir);
			if (filepropiedadesdir.mkdir()) {
				log.info("SE CREO EL DIRECTORIO DE PROPIEDADES EN " + propiedadesdir + " CON EXITO...");
			} else {
				log.info("ERROR FATAL CREANDO EL DIRECTORIO DE PROPIEDADES EN " + propiedadesdir + "...");
				return null;
			}
		}

		return filepropiedadesdir;
	}

	public static String getROOTCONTADOR() {
		String contador = null;
		try {
			File filesistemroot = getRootFolder();
			if (filesistemroot == null)
				return null;
			String contadores = getSistemString("RUTA_CONTADORES", "counters");
			contador = filesistemroot.getAbsolutePath() + System.getProperty("file.separator") + contadores;
			File f = new File(contador);
			if(!f.exists())
				f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contador;
	}

	public static String getROOTDOCS() {
		String digitales = null;
		try {
			File filesistemroot = getRootFolder();
			if (filesistemroot == null)
				return null;
			String ruta = getSistemString("RUTA_DOCS", "documents");
			digitales = filesistemroot.getAbsolutePath() + System.getProperty("file.separator") + ruta;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digitales;
	}

	public static String getSistemString(String key, String defoult) {
		String retorno = null;
		try {
			Properties sistemapropiedades = getSistemaProperties();
			if (sistemapropiedades == null)
				return defoult;

			if (defoult != null && defoult.trim().length() > 0) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, defoult);
					saveSistemaProperties(sistemapropiedades);
					retorno = defoult;
				} else {
					retorno = sistemapropiedades.getProperty(key, defoult);
				}
			} else {
				retorno = sistemapropiedades.getProperty(key);
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Boolean getSistemBoolean(String key, Boolean defoult) {
		Boolean retorno = null;
		try {
			Properties sistemapropiedades = getSistemaProperties();
			if (sistemapropiedades == null)
				return defoult;

			if (defoult != null) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, defoult.toString());
					saveSistemaProperties(sistemapropiedades);
					retorno = defoult;
				} else {
					try {
						retorno = Boolean.parseBoolean(sistemapropiedades.getProperty(key));
					} catch (NumberFormatException e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR BOOLEAN "
								+ sistemapropiedades.getProperty(key));
					}
				}
			} else {
				try {
					retorno = Boolean.parseBoolean(sistemapropiedades.getProperty(key));
				} catch (NumberFormatException e) {
					log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR BOOLEAN "
							+ sistemapropiedades.getProperty(key));
				}
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Long getSistemLong(String key, Long defoult) {
		Long retorno = null;
		try {
			Properties sistemapropiedades = getSistemaProperties();
			if (sistemapropiedades == null)
				return defoult;

			if (defoult != null) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, defoult.toString());
					saveSistemaProperties(sistemapropiedades);
					retorno = defoult;
				} else {
					try {
						retorno = Long.parseLong(sistemapropiedades.getProperty(key));
					} catch (NumberFormatException e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
								+ sistemapropiedades.getProperty(key));
					}
				}
			} else {
				try {
					retorno = Long.parseLong(sistemapropiedades.getProperty(key));
				} catch (NumberFormatException e) {
					log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
							+ sistemapropiedades.getProperty(key));
				}
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Long getSistemLong(String key, String defoult) {
		Long retorno = null;
		try {

			Properties sistemapropiedades = getSistemaProperties();
			retorno = Long.parseLong(defoult);
			if (sistemapropiedades == null)
				return retorno;

			if (defoult != null) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, defoult);
					saveSistemaProperties(sistemapropiedades);
				} else {
					try {
						retorno = Long.parseLong(sistemapropiedades.getProperty(key));
					} catch (NumberFormatException e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
								+ sistemapropiedades.getProperty(key));
					}
				}
			} else {
				try {
					retorno = Long.parseLong(sistemapropiedades.getProperty(key));
				} catch (NumberFormatException e) {
					log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
							+ sistemapropiedades.getProperty(key));
				}
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Integer getSistemInteger(String key, Integer defoult) {
		Integer retorno = null;
		try {
			Properties sistemapropiedades = getSistemaProperties();
			if (sistemapropiedades == null)
				return defoult;

			if (defoult != null) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, defoult.toString());
					saveSistemaProperties(sistemapropiedades);
					retorno = defoult;
				} else {
					try {
						retorno = Integer.parseInt(sistemapropiedades.getProperty(key));
					} catch (NumberFormatException e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
								+ sistemapropiedades.getProperty(key));
					}
				}
			} else {
				try {
					retorno = Integer.parseInt(sistemapropiedades.getProperty(key));
				} catch (NumberFormatException e) {
					log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR NO NUMERICO "
							+ sistemapropiedades.getProperty(key));
				}
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Date getSistemInteger(String key, Date defoult) {
		Date retorno = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Properties sistemapropiedades = getSistemaProperties();
			if (sistemapropiedades == null)
				return defoult;

			if (defoult != null) {
				if (sistemapropiedades.getProperty(key) == null) {
					sistemapropiedades.setProperty(key, sdf.format(defoult));
					saveSistemaProperties(sistemapropiedades);
					try {
						retorno = sdf.parse(sistemapropiedades.getProperty(key));
					} catch (Exception e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR FECHA "
								+ sistemapropiedades.getProperty(key));
					}
				} else {
					try {
						retorno = sdf.parse(sistemapropiedades.getProperty(key));
					} catch (Exception e) {
						log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR FECHA "
								+ sistemapropiedades.getProperty(key));
					}
				}
			} else {
				try {
					retorno = sdf.parse(sistemapropiedades.getProperty(key));
				} catch (Exception e) {
					log.info("ERROR OBTENIENDO LA PROPIEDAD DEL SISTEMA " + key + " VALOR FECHA "
							+ sistemapropiedades.getProperty(key));
				}
			}
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static Properties getUsuarioProperties(String usuario) {
		Properties propertieUsuario = null;
		if (propertieUsuario == null) {
			if (getPropiedadesDir() != null) {
				String PROPERTIE_USUARIO = usuario + ".properties";
				File propertiesfile = new File(getPropiedadesDir().getAbsolutePath()
						+ System.getProperty("file.separator") + PROPERTIE_USUARIO);
				if (propertiesfile.exists()) {
					FileInputStream input = null;
					propertieUsuario = new Properties();
					try {
						input = new FileInputStream(propertiesfile);
						propertieUsuario.load(input);
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
					propertieUsuario = new Properties();
					// AGREGAR PROPIEDADES POR DEFECTO
					saveUsuarioProperties(propertieUsuario, usuario);
				}
			}
		}
		return propertieUsuario;
	}

	public static void saveUsuarioProperties(Properties p, String usuario) {
		String PROPERTIE_USUARIO = usuario + ".properties";
		File propertiesfile = new File(
				getPropiedadesDir().getAbsolutePath() + System.getProperty("file.separator") + PROPERTIE_USUARIO);
		FileOutputStream ouput = null;
		try {
			System.out.println("CREANDO PREFERENCIAS EN: " + propertiesfile.getAbsolutePath());
			ouput = new FileOutputStream(propertiesfile);
			p.store(ouput, "PREFERENCIAS DEL USUARIO " + usuario);
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

	public static long getDEFECTO_PAIS_PERU() {
		long DEFECTO_PAIS_PERU = 173L;
		Properties sistema = getSistemaProperties();
		try {
			DEFECTO_PAIS_PERU = Long.parseLong(sistema.getProperty("DEFECTO_PAIS_PERU", "173"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DEFECTO_PAIS_PERU;
	}

	public static int getDEFECTO_DEPARTAMENTO() {
		int DEFECTO_DEPARTAMENTO = 15;
		Properties sistema = getSistemaProperties();
		try {
			DEFECTO_DEPARTAMENTO = Integer.parseInt(sistema.getProperty("DEFECTO_DEPARTAMENTO", "15"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DEFECTO_DEPARTAMENTO;
	}

	public static int getDEFECTO_PROVINCIA() {
		int DEFECTO_PROVINCIA = 1;
		Properties sistema = getSistemaProperties();
		try {
			DEFECTO_PROVINCIA = Integer.parseInt(sistema.getProperty("DEFECTO_PROVINCIA", "1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DEFECTO_PROVINCIA;
	}

	public static int getDEFECTO_DISTRITO() {
		int DEFECTO_DISTRITO = 1;
		Properties sistema = getSistemaProperties();
		try {
			DEFECTO_DISTRITO = Integer.parseInt(sistema.getProperty("DEFECTO_DISTRITO", "1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DEFECTO_DISTRITO;
	}

	public static long getDEFECTO_USUARIO_UO() {
		long DEFECTO_USUARIO_UO = 589L;
		Properties sistema = getSistemaProperties();
		try {
			DEFECTO_USUARIO_UO = Long.parseLong(sistema.getProperty("DEFECTO_USUARIO_UO", "589"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DEFECTO_USUARIO_UO;
	}

	public static List<String> getListaDefaultsClass(Class<?> a) {
		List<String> resultados = new ArrayList<String>();
		try {
			if (a != null) {
				String dirfilename = getRootFolder() + File.separator + AUXILIARES_DIR;
				File retorno = new File(dirfilename);
				if (!retorno.exists()) {
					retorno.mkdirs();
					System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
				}

				String nameclass = a.getName();
				String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
				File retorno2 = new File(dirfilenameclass);
				if (!retorno2.exists()) {
					retorno2.mkdirs();
					System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
				}

				if (retorno2.exists() && retorno2.isDirectory()) {
					File[] files = retorno2.listFiles();
					if (files != null && files.length > 0) {
						for (int i = 0; i < files.length; i++) {
							if (files[i].isFile()) {
								String namefile = files[i].getName();
								resultados.add(namefile);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
		return resultados;
	}

	public static List<String> getListaPerfiles() {
		List<String> resultados = new ArrayList<String>();
		try {
			String dirfilename = getRootFolder() + File.separator + AUXILIARES_DIR;
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = "PERFILES";
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}

			if (retorno2.exists() && retorno2.isDirectory()) {
				File[] files = retorno2.listFiles();
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							String namefile = files[i].getName();
							resultados.add(namefile);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
		return resultados;
	}

	public static void saveDefoultClass(String username, String plantilla, Object a) {
		try {
			if (a == null)
				return;

			String dirfilename = getRootFolder() + File.separator + username + ".class";
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = a.getClass().getName();
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}
			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(dirFileName));
			salida.writeObject(a);
			salida.close();
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void savePerfil(String plantilla, Object a) {
		try {
			if (a == null)
				return;

			String dirfilename = getRootFolder() + File.separator + AUXILIARES_DIR;
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = "PERFILES";
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}
			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(dirFileName));
			salida.writeObject(a);
			salida.close();
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void removerDefoultClass(String username, String plantilla, Class<?> a) {
		try {
			if (a == null)
				return;

			String dirfilename = getRootFolder() + File.separator + username + ".class";
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = a.getName();
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}

			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			File f = new File(dirFileName);
			if (!f.exists())
				return;

			f.delete();

		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void removerPerfil(String plantilla) {
		try {
			String dirfilename = getRootFolder() + File.separator + AUXILIARES_DIR;
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = "PERFILES";
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}

			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			File f = new File(dirFileName);
			if (!f.exists())
				return;

			f.delete();

		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDefoultClass(String username, String plantilla, Class<T> a) {
		T b = null;
		try {
			if (a == null)
				return null;

			String dirfilename = getRootFolder() + File.separator + username + ".class";
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = a.getName();
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}

			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			File f = new File(dirFileName);
			if (!f.exists())
				return null;

			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(dirFileName));
			b = (T) entrada.readObject();
			entrada.close();

		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getPerfil(String plantilla) {
		T b = null;
		try {
			String dirfilename = getRootFolder() + File.separator + AUXILIARES_DIR;
			File retorno = new File(dirfilename);
			if (!retorno.exists()) {
				retorno.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno.getAbsolutePath());
			}

			String nameclass = "PERFILES";
			String dirfilenameclass = retorno.getAbsolutePath() + File.separator + nameclass;
			File retorno2 = new File(dirfilenameclass);
			if (!retorno2.exists()) {
				retorno2.mkdirs();
				System.out.println("CREANDO EL DIRECTORIO DE CLASES: " + retorno2.getAbsolutePath());
			}

			String dirFileName = retorno2.getAbsolutePath() + System.getProperty("file.separator") + plantilla;

			File f = new File(dirFileName);
			if (!f.exists())
				return null;

			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(dirFileName));
			b = (T) entrada.readObject();
			entrada.close();

		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}
}
