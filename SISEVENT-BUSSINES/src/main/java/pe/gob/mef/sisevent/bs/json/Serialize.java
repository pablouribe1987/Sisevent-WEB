package pe.gob.mef.sisevent.bs.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

import pe.gob.mef.sisevent.bs.utils.PropertiesMg;

public abstract class Serialize<K, V> {

	private static final Log log = LogFactory.getLog(Serialize.class);
	
	private String sarchivokch = null;
	
	public Serialize() {
	}
	
	public String getUltimoDirectorio(){
		if(sarchivokch==null){
		Properties systema = PropertiesMg.getSistemaProperties();
		String ROOTKCH = PropertiesMg.getRootFolder() + System.getProperty("file.separator") + systema.getProperty("KCH", "KCH");
		File archivodir = new File(ROOTKCH);
		if (!archivodir.exists()) {
			archivodir.mkdirs();
		}
		String ULTIMO = systema.getProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase());
		if(ULTIMO==null){
			Date hoy = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			systema.setProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase(), sdf.format(hoy));
			PropertiesMg.saveSistemaProperties(systema);
			ULTIMO = systema.getProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase());
		}
		String IMAGEN = ROOTKCH + System.getProperty("file.separator") +"IMAGEN_"+ULTIMO+ System.getProperty("file.separator") +getClase().toUpperCase();
		File archivokch = new File(IMAGEN);
		if (!archivokch.exists()) {
			if(!archivokch.mkdirs()){
				log.info("NO SE PUDO CREAR EL DIRECTORIO"+IMAGEN);
				return null;
			}
			log.info("DIRECTORIO CREADO "+archivokch.getAbsolutePath());
		}
		
		if(archivokch.exists()){
			sarchivokch = archivokch.getAbsolutePath();
		}
		}
		return sarchivokch;
	}
	
	public void clearDir(){
		sarchivokch = null;
	}
	
	public Timestamp getUltimaFecha() throws Exception{
		Properties systema = PropertiesMg.getSistemaProperties();
		String ULTIMO = systema.getProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase());
		Timestamp retorno = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(ULTIMO==null){
			Date hoy = new Date(System.currentTimeMillis());
			systema.setProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase(), sdf.format(hoy));
			PropertiesMg.saveSistemaProperties(systema);
			ULTIMO = systema.getProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase());
		}
		
		
		Date dt = sdf.parse(ULTIMO);
		retorno = new Timestamp(dt.getTime());
		
		return retorno;
	}
	
	public boolean isExistFecha() throws Exception{
		Properties systema = PropertiesMg.getSistemaProperties();
		String ULTIMO = systema.getProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase());
		boolean retorno = false;
		if(ULTIMO!=null){
			retorno = true;
		}
		return retorno;
	}
	
	public void clearFecha(){
		Properties systema = PropertiesMg.getSistemaProperties();
		systema.setProperty("KCH_ULTIMA_IMAGEN_"+getClase().toUpperCase(),"");
		PropertiesMg.saveSistemaProperties(systema);
	}
	
	public abstract String getClase();
	public abstract Type getTipoClase();
	
	public void add(K key, V objeto){
		String ids = key.toString();
		String rootArchivo = getUltimoDirectorio();
		if(rootArchivo==null) return;
		String archivo = rootArchivo+System.getProperty("file.separator")+ids+".json";
		Mapper mapper = new MapperBuilder().build();
		 
		try {
			mapper.writeObject(objeto, new FileOutputStream(archivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public V get(K key){
		String ids = key.toString();
		String rootArchivo = getUltimoDirectorio();
		if(rootArchivo==null) return null;
		String archivo = rootArchivo+System.getProperty("file.separator")+ids+".json";
		Mapper mapper = new MapperBuilder().build();
		File archvof = new File(archivo);
		if(!archvof.exists()) return null;
		try {
			FileInputStream in = new FileInputStream(archvof);
			V objeto = mapper.readObject(in, getTipoClase());
			return objeto;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
	}
}
