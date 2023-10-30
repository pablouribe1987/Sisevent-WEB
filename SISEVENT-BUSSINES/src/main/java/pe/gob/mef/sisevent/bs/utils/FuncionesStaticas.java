/**
 * 
 */
package pe.gob.mef.sisevent.bs.utils;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import pe.gob.mef.sisevent.bs.domain.MsPersonas;
import pe.gob.mef.sisevent.bs.domain.MsUsuarios;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;

/**
 * CLASE CON FUNCIONES ESTATICAS DE MAYOR USO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 09/05/2020 02:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ /Carlos Aguilar Chamochumbi /
 *          09/05/2020 02:37 / Creación de la clase /
 * 
 */
public class FuncionesStaticas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3675243726536427783L;

	private static final int BUFFER_SIZE = 4096;
	private static final String FROM = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇçÄï§¢:°.-+";
	private static final String TO = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunnccAiSct o   ";

	public static Long PAIS_PERU = 150L;
	public static Integer PAIS_DTO_LIMA = 15;
	public static Integer PAIS_PROV_LIMA = 1;
	public static Integer PAIS_DIST_LIMA = 1;

	public FuncionesStaticas() {
	}

	public static String geNombreCompletoDeTtUsuariosSistema(MsUsuarios nombre) {
		if (nombre == null)
			return null;
		return ((nombre.getNombres() == null ? "" : (nombre.getNombres() + " "))
				+ (nombre.getApellidoPaterno() == null ? "" : (nombre.getApellidoPaterno() + " "))
				+ (nombre.getApellidoMaterno() == null ? "" : (nombre.getApellidoMaterno() + " "))).trim()
						.toUpperCase();
	}

	public static void copyPropertiesObject(Object a, Object b) {
		try {
			if (a == null || b == null)
				return;
			Field camposdea[] = b.getClass().getDeclaredFields();
			for (int i = 0; i < camposdea.length; i++) {
				String camponame = camposdea[i].getName();
				String sgetMetod = "get" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
				String ssetMetod = "set" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
				Class<?>[] types = new Class[] {};
				try {
					Method method = b.getClass().getMethod(sgetMetod, types);
					Object valuecampoa = method.invoke(b, new Object[0]);
					Class<?> types2 = camposdea[i].getType();
					Method method2 = a.getClass().getMethod(ssetMetod, types2);
					method2.invoke(a, valuecampoa);
				} catch (NoSuchMethodException exception) {
					// System.out.println("Error NoSuchMethodException: " +
					// exception.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void copyPropertiesObjectStringOptimice(Object a, Object b) {
		try {
			if (a == null || b == null)
				return;
			Field camposdea[] = b.getClass().getDeclaredFields();
			for (int i = 0; i < camposdea.length; i++) {
				String camponame = camposdea[i].getName();
				String sgetMetod = "get" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
				String ssetMetod = "set" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
				Class<?>[] types = new Class[] {};
				try {
					Method method = b.getClass().getMethod(sgetMetod, types);
					Object valuecampoa = method.invoke(b, new Object[0]);
					Class<?> types2 = camposdea[i].getType();
					Method method2 = a.getClass().getMethod(ssetMetod, types2);

					if (valuecampoa instanceof String) {
						method2.invoke(a, ((String) valuecampoa).intern());
					} else {
						method2.invoke(a, valuecampoa);
					}

				} catch (NoSuchMethodException exception) {
					// System.out.println("Error NoSuchMethodException: " +
					// exception.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String getMimeName(String name, Integer firmado) {
		String mime = "";
		if (name == null)
			return mime;

		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1).toLowerCase();
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
			} else if ("jpg".equals(extension)) {
				mime = "image/jpeg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
			} else if ("docx".equals(extension)) {
				mime = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			} else if ("xlsx".equals(extension)) {
				mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			} else if ("xls".equals(extension)) {
				mime = "application/vnd.ms-excel";
			} else if ("xml".equals(extension)) {
				mime = "application/xml";
			} else if ("pdf".equals(extension)) {
				mime = "application/pdf";
			} else if ("doc".equals(extension)) {
				mime = "application/msword";
			} else if ("pptx".equals(extension)) {
				mime = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
			} else if ("ppt".equals(extension)) {
				mime = "application/vnd.ms-powerpoint";
			} else if ("msg".equals(extension)) {
				mime = "application/x-msg";
			} else {
				mime = "image/unknown";
			}
		}
		if (firmado != null && firmado.intValue() >= 1) {
			mime = "firmado/" + mime;
		}
		return mime;
	}

	public static String getFileNameSistema(Long idsacc, Long idflujo, Long idanexo, Long idusuario, Long idarea) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		DecimalFormat df = new DecimalFormat("000000");
		Timestamp diahoy = new Timestamp(System.currentTimeMillis());
		String filename = df.format(idsacc) + df.format(idflujo) + df.format(idanexo)+df.format(idusuario) + df.format(idarea)
				+ df.format(idflujo) + sdf.format(diahoy);
		return filename;
	}

	public static String getFileNameTempSistema(Long idusuario, Long idarea) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		DecimalFormat df = new DecimalFormat("000000");
		Timestamp diahoy = new Timestamp(System.currentTimeMillis());
		String filename = "TEMP_" + df.format(idusuario) + df.format(idarea) + sdf.format(diahoy)+UUID.randomUUID();
		return filename;
	}

	public static String getFileNameRutaSistema(String filename) {
		File RUTAROOT = PropertiesMg.getRootFolder();
		String anioactual = Calendar.getInstance().get(Calendar.YEAR) + "_"
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1);
		File F_RUTAROOT = new File(RUTAROOT, anioactual);
		if (!F_RUTAROOT.exists()) {
			if (!F_RUTAROOT.mkdirs()) {
				return null;
			}
		}
		String RUTA = F_RUTAROOT.getAbsolutePath();
		return RUTA + System.getProperty("file.separator") + filename;
	}

	public static File getFileSistemaCompletoSearch(String solofilename, Timestamp fechaModifi) {
		File RUTAROOT = PropertiesMg.getRootFolder();
		GregorianCalendar fechaModi = new GregorianCalendar();
		if(fechaModifi==null)
			fechaModifi = new Timestamp(System.currentTimeMillis());
		
		fechaModi.setTimeInMillis(fechaModifi.getTime());
		int anio = fechaModi.get(Calendar.YEAR);
		int mes = fechaModi.get(Calendar.MONTH) + 1;
		File F_RUTAROOT = new File(RUTAROOT, anio + "_" + mes);
		if (!F_RUTAROOT.exists()) {
			F_RUTAROOT.mkdirs();
		}
		
		String RUTA = F_RUTAROOT.getAbsolutePath() + System.getProperty("file.separator") + solofilename;
		File fileRetorno = new File(RUTA);
		if (fileRetorno.exists()) {
			return fileRetorno;
		}
		
		String anioactual = Calendar.getInstance().get(Calendar.YEAR) + "";
		F_RUTAROOT = new File(RUTAROOT, anioactual);
		if (!F_RUTAROOT.exists()) {
			F_RUTAROOT.mkdirs();
		}
		RUTA = F_RUTAROOT.getAbsolutePath() + System.getProperty("file.separator") + solofilename;
		fileRetorno = new File(RUTA);
		if (fileRetorno.exists()) {
			return fileRetorno;
		} else {
			int b123 = 0;
			while (true) {
				fileRetorno = null;
				anioactual = (Calendar.getInstance().get(Calendar.YEAR) - b123) + "";
				if (anioactual.equals("2020")) {
					break;
				}
				for (int i = 1; i <= 12; i++) {
					String anioo = anioactual + "_" + i;
					F_RUTAROOT = new File(RUTAROOT, anioo);
					if (F_RUTAROOT.exists()) {
						RUTA = F_RUTAROOT.getAbsolutePath() + System.getProperty("file.separator") + solofilename;
						fileRetorno = new File(RUTA);
						if (fileRetorno.exists()) {
							break;
						}
					}
				}
				if (fileRetorno != null && fileRetorno.exists()) {
					break;
				}
				b123++;
			}
		}
		return fileRetorno;
	}

	public static String getfechaLargaFormateada(Timestamp fec) {
		if (fec == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int S = 0, M = 0, A = 0, D = 0;
		Date fechax = new Date(fec.getTime());
		Calendar f = Calendar.getInstance();
		f.setTime(fechax);
		String di[] = { "", "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" };
		String me[] = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
				"OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
		S = f.get(Calendar.DAY_OF_WEEK);
		M = f.get(Calendar.MONTH);
		D = f.get(Calendar.DAY_OF_MONTH);
		A = f.get(Calendar.YEAR);
		return di[S] + " " + D + " DE " + me[M] + " DEL " + A + " A LAS " + sdf.format(fec) + "mm HORAS.";
	}

	public static String getSolofechaLargaFormateada(Timestamp fec) {
		if (fec == null)
			return "";
		int S = 0, M = 0, A = 0, D = 0;
		Date fechax = new Date(fec.getTime());
		Calendar f = Calendar.getInstance();
		f.setTime(fechax);
		String di[] = { "", "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" };
		String me[] = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
				"OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
		S = f.get(Calendar.DAY_OF_WEEK);
		M = f.get(Calendar.MONTH);
		D = f.get(Calendar.DAY_OF_MONTH);
		A = f.get(Calendar.YEAR);
		return di[S] + " " + D + " DE " + me[M] + " DEL " + A;
	}

	// ASANCHEZ
	public static String formatearCadena(String cadena) {
		if (cadena == null)
			return "";

		if (cadena.trim().equals(""))
			return "";

		String resultado = "";
		StringTokenizer st = new StringTokenizer(cadena);
		while (st.hasMoreTokens()) {
			resultado = resultado + st.nextToken() + " ";
		}

		return resultado.trim();
	}

	public static Timestamp getDiaMasUno(Timestamp diafin) {
		if (diafin == null)
			return null;
		GregorianCalendar calFin = new GregorianCalendar();
		// long unDia = 1000 * 60 * 60 * 24;
		calFin.setTimeInMillis(diafin.getTime());
		calFin.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calFin.set(Calendar.MILLISECOND, 0);
		calFin.add(Calendar.DAY_OF_MONTH, 1);
		Timestamp diafinfinal = new Timestamp(calFin.getTimeInMillis());
		return diafinfinal;
	}

	public static Timestamp getInicioDelDia(Timestamp diafin) {
		if (diafin == null)
			return null;
		GregorianCalendar calFin = new GregorianCalendar();
		// long unDia = 1000 * 60 * 60 * 24;
		calFin.setTimeInMillis(diafin.getTime());
		calFin.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calFin.set(Calendar.MILLISECOND, 0);
		Timestamp diafinfinal = new Timestamp(calFin.getTimeInMillis());
		return diafinfinal;
	}

	public static boolean copyFile(File destFile,File sourceFile) {
		InputStream in = null;
		OutputStream out = null;
		boolean resultado = false;
		try {
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			resultado = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return resultado;
	}

	public static void copyFile(InputStream in, File destFile) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	/**
	 * Extracts a zip file specified by the zipFilePath to a directory specified by destDirectory (will be created if
	 * does not exists)
	 * 
	 * @param zipFilePath
	 * @param destDirectory
	 * @throws IOException
	 */
	public static void unzip(InputStream zipFilePath, String destDirectory) throws IOException {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(zipFilePath);
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	/**
	 * Extracts a zip entry (file entry)
	 * 
	 * @param zipIn
	 * @param filePath
	 * @throws IOException
	 */
	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	public static String getSFechaLargaFormateada(Timestamp fec) {
		if (fec == null)
			return "";
		int S = 0, M = 0, A = 0, D = 0;
		Date fechax = new Date(fec.getTime());
		Calendar f = Calendar.getInstance();
		f.setTime(fechax);
		String di[] = { "", "domingo", "lunes", "martes", "mi�rcoles", "jueves", "viernes", "s�bado" };
		String me[] = { "enero", "febrero", "maro", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
				"octubre", "noviembre", "diciembre" };
		S = f.get(Calendar.DAY_OF_WEEK);
		M = f.get(Calendar.MONTH);
		D = f.get(Calendar.DAY_OF_MONTH);
		A = f.get(Calendar.YEAR);
		return di[S] + " " + D + " de " + me[M] + " del " + A;
	}

	public static String getDireccionLarga(String direccion, String codDistrito, String codProvincia,
			String codDepartamento, String codPais) {
		if (direccion == null)
			return "";
		StringBuffer sb = new StringBuffer();
		sb.append(direccion);
		if (codDistrito != null && codDistrito.trim().length() > 0)
			sb.append(" ").append(codDistrito);
		if (codProvincia != null && codProvincia.trim().length() > 0)
			sb.append(" ").append(codProvincia);
		if (codDepartamento != null && codDepartamento.trim().length() > 0)
			sb.append(", ").append(codDepartamento);
		if (codPais != null && codPais.trim().length() > 0)
			sb.append(" / ").append(codPais);

		return sb.toString();
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		// Get the size of the file
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IOException("File is too large!");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;

		InputStream is = new FileInputStream(file);
		try {
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} finally {
			is.close();
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		return bytes;
	}

	public static boolean moveTo(File archivo, String nuevaRuta) {
		boolean respuesta = false;
		File archivodestino = new File(nuevaRuta);
		respuesta = archivo.renameTo(archivodestino);
		// if(respuesta){
		// respuesta = archivo.delete();
		// }
		return respuesta;
	}

	public static boolean moveTo(String sarchivo, File archivodestino) {
		boolean respuesta = false;
		File archivo = new File(sarchivo);
		if (archivodestino.exists()) {
			archivodestino.delete();
		}
		if (archivo.exists()) {
			respuesta = archivo.renameTo(archivodestino);
		}
		return respuesta;
	}

	public static void addToZipFile(String fileName, String ruta, ZipOutputStream zos)
			throws FileNotFoundException, IOException {
		File file = new File(ruta);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}

	public static String limpiarCaracteres(String source) {
		if (source == null)
			return null;
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

	// NOMBRE COMPLETO
	public static String getNombreCompleto(MsUsuarios msUsuarios) {
		return (msUsuarios.getApellidoPaterno() != null ? msUsuarios.getApellidoPaterno() : "")
				+ (msUsuarios.getApellidoMaterno() != null ? " " + msUsuarios.getApellidoMaterno() : "")
				+ (msUsuarios.getNombres() != null ? " " + msUsuarios.getNombres() : "");
	}

	public static String getNombreCompleto(MsUsuariosBk msUsuarios) {
		return (msUsuarios.getApellidoPaterno() != null ? msUsuarios.getApellidoPaterno() : "")
				+ (msUsuarios.getApellidoMaterno() != null ? " " + msUsuarios.getApellidoMaterno() : "")
				+ (msUsuarios.getNombres() != null ? " " + msUsuarios.getNombres() : "");
	}

	public static String getNombreCompletoINF(MsUsuariosBk msUsuarios) {
		return (msUsuarios.getNombres() != null ? msUsuarios.getNombres() : "")
				+ (msUsuarios.getApellidoPaterno() != null ? " " + msUsuarios.getApellidoPaterno() : "")
				+ (msUsuarios.getApellidoMaterno() != null ? " " + msUsuarios.getApellidoMaterno() : "");
	}

	public static String getNombreCompletoPersona(MsPersonas msPersonas) {
		return (msPersonas.getApellidoPaterno() != null ? msPersonas.getApellidoPaterno() : "")
				+ (msPersonas.getApellidoMaterno() != null ? " " + msPersonas.getApellidoMaterno() : "")
				+ (msPersonas.getNombres() != null ? " " + msPersonas.getNombres() : "");
	}
	public static void setValorInItem(Object a, String camponame, Object valuecampoa) {
		try {
			if (a == null)
				return;
			String ssetMetod = "set" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
			try {
				Class<?> types2 = valuecampoa.getClass();
				Method method2 = a.getClass().getMethod(ssetMetod, types2);
				method2.invoke(a, valuecampoa);
			} catch (NoSuchMethodException exception) {
				// System.out.println("Error NoSuchMethodException: " +
				// exception.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Error exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// /----------------CALCULO DE DIAS----------------
	public static long getDiasHabilesTranscurridos(Timestamp diaini, Timestamp diafin, Timestamp supdiaini,
			Timestamp supdiafin, List<Timestamp> diasFeriados, int estado) {

		GregorianCalendar calIni = new GregorianCalendar();
		GregorianCalendar calFin = new GregorianCalendar();
		GregorianCalendar calTemp = new GregorianCalendar();

		if (diaini == null)
			diaini = new Timestamp(System.currentTimeMillis());
		if (diafin == null) {
			if (estado == 6 || estado == 7) {
				return 0L;
			} else {
				diafin = new Timestamp(System.currentTimeMillis());
			}
		}

		calIni.setTimeInMillis(diaini.getTime());
		calFin.setTimeInMillis(diafin.getTime());

		if (diaini.after(diafin)) {
			calTemp.setTimeInMillis(diaini.getTime());
			calIni.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calFin.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH), 0,
					0, 0);
		} else {
			calFin.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calIni.set(calIni.get(Calendar.YEAR), calIni.get(Calendar.MONTH), calIni.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
		}

		calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calTemp.add(Calendar.DAY_OF_MONTH, -1);

		if (calTemp.equals(calIni)) {
			return 0;
		}

		long diastranscurridos = getDiasHabilesTranscurridos(calIni, calFin, diasFeriados, estado);

		// ****SUSPENCION
		if (supdiaini != null) {
			if (supdiafin == null)
				supdiafin = new Timestamp(System.currentTimeMillis());

			long diasdesuspencion = 0;

			GregorianCalendar supcalIni = new GregorianCalendar();
			GregorianCalendar supcalFin = new GregorianCalendar();
			GregorianCalendar supcalTemp = new GregorianCalendar();

			supcalIni.setTimeInMillis(supdiaini.getTime());
			supcalFin.setTimeInMillis(supdiafin.getTime());

			if (supdiaini.after(supdiafin)) {
				supcalTemp.setTimeInMillis(supdiaini.getTime());
				supcalIni.set(supcalFin.get(Calendar.YEAR), supcalFin.get(Calendar.MONTH),
						supcalFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				supcalFin.set(supcalTemp.get(Calendar.YEAR), supcalTemp.get(Calendar.MONTH),
						supcalTemp.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			} else {
				supcalFin.set(supcalFin.get(Calendar.YEAR), supcalFin.get(Calendar.MONTH),
						supcalFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				supcalIni.set(supcalIni.get(Calendar.YEAR), supcalIni.get(Calendar.MONTH),
						supcalIni.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
			supcalTemp.set(supcalFin.get(Calendar.YEAR), supcalFin.get(Calendar.MONTH),
					supcalFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			supcalTemp.add(Calendar.DAY_OF_MONTH, -1);

			if (!calTemp.equals(calIni)) {
				if ((supcalIni.equals(calIni) || supcalIni.after(calIni)) && supcalIni.before(calFin)
						&& (supcalFin.equals(calFin) || supcalFin.before(calFin))) { // &&
																						// supcalFin.after(calIni)
					diasdesuspencion = getDiasHabilesTranscurridos(supcalIni, supcalFin, diasFeriados, estado);
				} else if (supcalIni.before(calIni) && supcalFin.after(calIni)
						&& (supcalFin.before(calFin) || supcalFin.equals(calFin))) { // &&
																						// supcalIni.before(calFin)
					diasdesuspencion = getDiasHabilesTranscurridos(calIni, supcalFin, diasFeriados, estado);
				} else if ((supcalIni.equals(calIni) || supcalIni.after(calIni)) && supcalIni.before(calFin)
						&& supcalFin.after(calFin)) { // &&
														// supcalFin.after(calIni)
					diasdesuspencion = getDiasHabilesTranscurridos(supcalIni, calFin, diasFeriados, estado);
				} else if ((supcalIni.equals(calIni) || supcalIni.before(calIni))
						&& (supcalFin.equals(calFin) || supcalFin.after(calFin))) { // &&
																					// supcalIni.before(calFin)
																					// &&
																					// supcalFin.after(calIni)
					diasdesuspencion = getDiasHabilesTranscurridos(calIni, calFin, diasFeriados, estado);
				}
			}
			diastranscurridos -= diasdesuspencion;
		}

		if (diastranscurridos <= 0)
			diastranscurridos = 0;
		return diastranscurridos;
	}

	public static long getDiasHabilesTranscurridos(GregorianCalendar calIni, GregorianCalendar calFin,
			List<Timestamp> diasFeriados, int estado) {

		if (calFin == null) {
			if (estado == 6 || estado == 7) {
				return 0;
			} else {
				calFin = new GregorianCalendar();
			}
		}

		GregorianCalendar calTemp = new GregorianCalendar();
		calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calTemp.add(Calendar.DAY_OF_MONTH, -1);

		if (calTemp.equals(calIni)) {
			return 0;
		}

		long unDia = 1000 * 60 * 60 * 24;
		long ldiafin = calFin.getTimeInMillis();
		long ldiaini = calIni.getTimeInMillis();
		long restaEnDias = ((long) ((ldiafin - ldiaini) / unDia));

		long diasDeSamana = (long) Math.floor(restaEnDias / 7);
		long diasNoIncluidos = restaEnDias - (diasDeSamana * 7);
		long diastranscurridos = restaEnDias - (diasDeSamana * 2);

		for (int j = 0; j < (int) diasNoIncluidos; j++) {
			calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calTemp.add(Calendar.DAY_OF_MONTH, (-1 * j));
			if (calTemp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| calTemp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				diastranscurridos--;
			}
		}
		if (diasFeriados != null) {
			for (int i = 0; i < diasFeriados.size(); i++) {
				Timestamp diaferiado = (Timestamp) diasFeriados.get(i);
				calTemp.setTimeInMillis(diaferiado.getTime());
				calTemp.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH),
						0, 0, 0);
				if ((calTemp.after(calIni) && calTemp.before(calFin)) || calTemp.equals(calIni)
						|| calTemp.equals(calFin)) {
					if (calTemp.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
							&& calTemp.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
						diastranscurridos--;
					}
				}
			}
		}

		return diastranscurridos;
	}

	public static Timestamp getDiaHabilfin(Timestamp diaini, long dias, Timestamp supdiaini, Timestamp supdiafin,
			List<Timestamp> diasFeriados, int estado) {
		long unDia = 1000 * 60 * 60 * 24;
		Timestamp diaFin = new Timestamp(diaini.getTime() + (unDia * dias));
		long diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, supdiaini, supdiafin, diasFeriados, estado);
		if (diahastaahora < dias) {
			while (diahastaahora < dias) {
				diaFin = new Timestamp(diaFin.getTime() + ((dias - diahastaahora) * unDia));
				diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, supdiaini, supdiafin, diasFeriados, estado);
			}
			// CORECCION A 1
			if (diahastaahora > dias) {
				diaFin = new Timestamp(diaFin.getTime() - unDia);
				diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, supdiaini, supdiafin, diasFeriados, estado);
			}
		}
		return diaFin;
	}

	public long getDiasCalendarioTranscurridos(GregorianCalendar calIni, GregorianCalendar calFin, int estado) {

		long unDia = 1000 * 60 * 60 * 24;

		if (calFin == null) {
			if (estado == 6 || estado == 7) {
				return 0;
			} else {
				calFin = new GregorianCalendar();
			}
		}

		GregorianCalendar calTemp = new GregorianCalendar();
		calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calTemp.add(Calendar.DAY_OF_MONTH, -1);

		if (calTemp.equals(calIni)) {
			return 0;
		}

		long ldiafin = calFin.getTimeInMillis();
		long ldiaini = calIni.getTimeInMillis();
		long restaEnDias = (ldiafin - ldiaini) / unDia;

		return restaEnDias;
	}

	public static synchronized String getNextNumero() {
		String numeroretorno = null;
		DecimalFormat sdf = new DecimalFormat("000000");
		long numero = getNextNumber();
		int contadordeintentos = 0;
		while (numero <= 0 && contadordeintentos < 5) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			numero = getNextNumber();
			contadordeintentos++;
		}
		if (numero > 0) {
			numeroretorno = sdf.format(numero);
		}
		return numeroretorno;
	}

	private static synchronized long getNextNumber() {
		Calendar c = Calendar.getInstance();
		File f = null;
		RandomAccessFile raf = null;
		String sFileN = PropertiesMg.getROOTCONTADOR() + System.getProperty("file.separator") + "ATENCIONES"
				+ c.get(Calendar.YEAR);
		long ret = -1;
		try {
			f = new File(sFileN);
			if (!f.exists()) {
				raf = new RandomAccessFile(f, "rw");
				raf.writeLong(0L);
				raf.close();
				f = new File(sFileN);
			}
			raf = new RandomAccessFile(f, "rw");
			long l = raf.readLong();
			if (l >= 0) {
				ret = l + 1;
				raf.seek(0);
				raf.writeLong(ret);
			}
			raf.close();
		} catch (NullPointerException ne) {
			System.out.println("Error en el contador 01 ");
			ne.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Error en el contador 02 ");
			nfe.printStackTrace();
		} catch (FileNotFoundException fne) {
			System.out.println("Error en el contador 03 ");
			fne.printStackTrace();
		} catch (EOFException ee) {
			System.out.println("Error en el contador 04 ");
			ee.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error en el contador 05 ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error en el contador 06 ");
			e.printStackTrace();
		}
		return ret;
	}

	// /----------------CALCULO DE DIAS----------------
	public static long getDiasHabilesTranscurridos(Timestamp diaini, Timestamp diafin, List<Timestamp> diasFeriados) {
		GregorianCalendar calIni = new GregorianCalendar();
		GregorianCalendar calFin = new GregorianCalendar();
		GregorianCalendar calTemp = new GregorianCalendar();

		if (diaini == null)
			diaini = new Timestamp(System.currentTimeMillis());
		if (diafin == null) {
			diafin = new Timestamp(System.currentTimeMillis());
		}

		calIni.setTimeInMillis(diaini.getTime());
		calFin.setTimeInMillis(diafin.getTime());

		if (diaini.after(diafin)) {
			calTemp.setTimeInMillis(diaini.getTime());
			calIni.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calFin.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH), 0,
					0, 0);
		} else {
			calFin.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calIni.set(calIni.get(Calendar.YEAR), calIni.get(Calendar.MONTH), calIni.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
		}

		calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calTemp.add(Calendar.DAY_OF_MONTH, -1);

		if (calTemp.equals(calIni)) {
			return 0;
		}

		long unDia = 1000 * 60 * 60 * 24;
		long ldiafin = calFin.getTimeInMillis();
		long ldiaini = calIni.getTimeInMillis();
		long restaEnDias = ((long) ((ldiafin - ldiaini) / unDia));

		long diasDeSamana = (long) Math.floor(restaEnDias / 7);
		long diasNoIncluidos = restaEnDias - (diasDeSamana * 7);
		long diastranscurridos = restaEnDias - (diasDeSamana * 2);

		for (int j = 0; j < (int) diasNoIncluidos; j++) {
			calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calTemp.add(Calendar.DAY_OF_MONTH, (-1 * j));
			if (calTemp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| calTemp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				diastranscurridos--;
			}
		}

		if (diasFeriados != null) {
			for (int i = 0; i < diasFeriados.size(); i++) {
				Timestamp diaferiado = (Timestamp) diasFeriados.get(i);
				calTemp.setTimeInMillis(diaferiado.getTime());
				calTemp.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH),
						0, 0, 0);
				if ((calTemp.after(calIni) && calTemp.before(calFin)) || calTemp.equals(calIni)
						|| calTemp.equals(calFin)) {
					if (calTemp.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
							&& calTemp.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
						diastranscurridos--;
					}
				}
			}
		}
		if (diastranscurridos <= 0)
			diastranscurridos = 0;
		return diastranscurridos;
	}

	public static Timestamp getDiaHabilfin(Timestamp diaini, long dias, List<Timestamp> diasFeriados) {
		long unDia = 1000 * 60 * 60 * 24;
		Timestamp diaFin = new Timestamp(diaini.getTime() + (unDia * dias));
		long diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, diasFeriados);
		if (diahastaahora < dias) {
			while (diahastaahora < dias) {
				diaFin = new Timestamp(diaFin.getTime() + ((dias - diahastaahora) * unDia));
				diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, diasFeriados);
			}
			// CORECCION A 1
			if (diahastaahora > dias) {
				diaFin = new Timestamp(diaFin.getTime() - unDia);
				diahastaahora = getDiasHabilesTranscurridos(diaini, diaFin, diasFeriados);
			}
		}
		return diaFin;
	}

	public long getDiasCalendarioTranscurridos(Timestamp diaini, Timestamp diafin, List<Timestamp> diasFeriados) {
		GregorianCalendar calIni = new GregorianCalendar();
		GregorianCalendar calFin = new GregorianCalendar();
		GregorianCalendar calTemp = new GregorianCalendar();

		if (diaini == null)
			diaini = new Timestamp(System.currentTimeMillis());
		if (diafin == null) {
			diafin = new Timestamp(System.currentTimeMillis());
		}

		calIni.setTimeInMillis(diaini.getTime());
		calFin.setTimeInMillis(diafin.getTime());

		if (diaini.after(diafin)) {
			calTemp.setTimeInMillis(diaini.getTime());
			calIni.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calFin.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH), 0,
					0, 0);
		} else {
			calFin.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
			calIni.set(calIni.get(Calendar.YEAR), calIni.get(Calendar.MONTH), calIni.get(Calendar.DAY_OF_MONTH), 0, 0,
					0);
		}

		calTemp.set(calFin.get(Calendar.YEAR), calFin.get(Calendar.MONTH), calFin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calTemp.add(Calendar.DAY_OF_MONTH, -1);

		if (calTemp.equals(calIni)) {
			return 0;
		}

		long unDia = 1000 * 60 * 60 * 24;
		long ldiafin = calFin.getTimeInMillis();
		long ldiaini = calIni.getTimeInMillis();
		long restaEnDias = (ldiafin - ldiaini) / unDia;

		return restaEnDias;
	}
	
	public static String getAcro(String name){
		StringBuffer sb = new StringBuffer();
		boolean primero = true;
		for(int i=0;i<name.length();i++){
			if(primero){
				primero = false;
				sb.append(name.charAt(i));
			}
			if(name.charAt(i)==' '){
				primero = true;
			}
		}
		String acro = sb.toString();
		if(acro.length()<=1 && name.length()>3){
			acro = name.substring(0,3);
		}
		return acro;
	}
	

}