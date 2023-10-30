package pe.gob.mef.sisevent.bs.ctlracceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import pe.gob.mef.sisevent.bs.transfer.IDsValorDto;

/*
 * CLASE PARA EL MANEJO DE LOS ROLES DE LOS USUARIOS
 * 
 * @author  Carlos Aguilar
 * @version 2.0, 21/03/2012 11:09:32 AM
 *
 * 
 *  /----------Nombre----------/----fecha----/-------------Motivo--------------/
 *  Carlos Aguilar Chamochumbi	21/03/2012 11:09:32 AM	  Creación de la clase
 *
 */
public class Roles implements Serializable {

	private static final long serialVersionUID = 873421700604638878L;

	public static final String ADMINISTRADOR = "SISEVENT_ADMINISTRADOR";
	public static final String VISUALIZADOR = "SISEVENT_VISUALIZADOR";
	public static final String REGISTRADOR = "SISEVENT_REGISTRADOR";
	public static final String RESPONSABLE = "SISEVENT_RESPONSABLE";
	public static final String ASISTENTE = "SISEVENT_ASISTENTE";
	public static final String REASIGNA_TAREAS = "SISEVENT_REASIGNA_TAREAS";

	//// ROLES USUARIOS
	public static final String CREA_REPORTES = "SISEVENT_CREA_REPORTES";
	public static final String ADMINISTRADOR_USUARIOS = "SISEVENT_ADMIN_MSUSUARIOS";

	//// ROLES USUARIOS
	public static final String MSUNIDADESORG_CREA = "SISEVENT_MSUNIDADESORG_CREA";
	public static final String MSUNIDADESORG_VE = "SISEVENT_MSUNIDADESORG_VE";

	public static final String PRTPARAMETROS_CREA = "SISEVENT_PRTPARAMETROS_CREA";
	public static final String PRTPARAMETROS_VE = "SISEVENT_PRTPARAMETROS_VE";
	
	//// ROLES USUARIOS
	public static final String MSINSTITUCIONES_CREA = "SISEVENT_MSINSTITUCIONES_CREA";
	public static final String MSINSTITUCIONES_VE = "SISEVENT_MSINSTITUCIONES_VE";

	//// ROLES MSUBIGEO
	public static final String MSUBIGEO_CREA = "SISEVENT_MSUBIGEO_CREA";
	public static final String MSUBIGEO_VE = "SISEVENT_MSUBIGEO_VE";
	//// ROLES USUARIOS
	public static final String MSPAISES_CREA = "SISEVENT_MSPAISES_CREA";
	public static final String MSPAISES_VE = "SISEVENT_MSPAISES_VE";

	//// ROLES USUARIOS
	public static final String MSPERSONAS_CREA = "SISEVENT_MSPERSONAS_CREA";
	public static final String MSPERSONAS_VE = "SISEVENT_MSPERSONAS_VE";
	
	//// ROLES USUARIOS
	public static final String TDANEXOS_CREA = "SISEVENT_TDANEXOS_CREA";
	public static final String TDANEXOS_VE = "SISEVENT_TDANEXOS_VE";

	//// ROLES USUARIOS
	public static final String TDFLUJO_CREA = "SISEVENT_TDFLUJO_CREA";
	public static final String TDFLUJO_VE = "SISEVENT_TDFLUJO_VE";

////ROLES TDFERIADOS
	public static final String TDFERIADOS_CREA = "SISEVENT_TDFERIADOS_CREA";
	public static final String TDFERIADOS_VE = "SISEVENT_TDFERIADOS_VE";

////ROLES MSACTIVIDADES
	public static final String MSACTIVIDADES_CREA = "SISEVENT_MSACTIVIDADES_CREA";
	public static final String MSACTIVIDADES_VE = "SISEVENT_MSACTIVIDADES_VE";
////ROLES MSCATEGORIAS
	public static final String MSCATEGORIAS_CREA = "SISEVENT_MSCATEGORIAS_CREA";
	public static final String MSCATEGORIAS_VE = "SISEVENT_MSCATEGORIAS_VE";	
////ROLES MSTAREAS
	public static final String MSTAREAS_CREA = "SISEVENT_MSTAREAS_CREA";
	public static final String MSTAREAS_VE = "SISEVENT_MSTAREAS_VE";	
////ROLES TDEVENTO
	public static final String TDEVENTO_CREA = "SISEVENT_TDEVENTO_CREA";
	public static final String TDEVENTO_VE = "SISEVENT_TDEVENTO_VE";	
////ROLES TDGRUPOSFLUJOS
	public static final String TDGRUPOSFLUJOS_CREA = "SISEVENT_TDGRUPOSFLUJOS_CREA";
	public static final String TDGRUPOSFLUJOS_VE = "SISEVENT_TDGRUPOSFLUJOS_VE";
////ROLES TDGRUPOS
	public static final String TDGRUPOS_CREA = "SISEVENT_TDGRUPOS_CREA";
	public static final String TDGRUPOS_VE = "SISEVENT_TDGRUPOS_VE";
////ROLES TDITINERARIO
	public static final String TDITINERARIO_CREA = "SISEVENT_TDITINERARIO_CREA";
	public static final String TDITINERARIO_VE = "SISEVENT_TDITINERARIO_VE";
////ROLES TDPARTICIPANTES
	public static final String TDPARTICIPANTES_CREA = "SISEVENT_TDPARTICIPANTES_CREA";
	public static final String TDPARTICIPANTES_VE = "SISEVENT_TDPARTICIPANTES_VE";
////ROLES TDUBICACIONES
	public static final String TDUBICACIONES_CREA = "SISEVENT_TDUBICACIONES_CREA";
	public static final String TDUBICACIONES_VE = "SISEVENT_TDUBICACIONES_VE";
	
	public static Map<String, String> ROLES_DESCRIPCION = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1730490346280234674L;

		{
			put(ADMINISTRADOR, "ADMINISTRADOR DEL SISTEMA."); // 1
			put(VISUALIZADOR, "PERMITE VISUALIZAR A MODO DE CONSULTA LOS EVENTOS Y TAREAS.");
			put(REGISTRADOR, "PERMITE REGISTRAR/ANULAR/EDITAR EVENTOS Y TAREAS.");
			put(RESPONSABLE, "ATIENDE SUS TAREAS ASISNADAS DE ALGÚN EVENTO.");
			put(ASISTENTE, "PRESTA ASISTENCIA AL RESPONSABLE PARA ATENDER TAREAS.");
			put(REASIGNA_TAREAS, "REASIGNA TAREAS ASIGNADAS A ALGÚN ESPECIALISTA EN LOS EVENTOS.");
			
			/*
			 put(MSACTIVIDADES_CREA, "CREA "+Messages.getStringToKey("msActividades.comentariotabla"));
			 put(MSACTIVIDADES_VE, "VISUALIZA " +Messages.getStringToKey("msActividades.comentariotabla"));
			 put(MSCATEGORIAS_CREA, "CREA "+Messages.getStringToKey("msCategorias.comentariotabla"));
			 put(MSCATEGORIAS_VE, "VISUALIZA " +Messages.getStringToKey("msCategorias.comentariotabla"));
			put(MSTAREAS_CREA, "CREA "+Messages.getStringToKey("msTareas.comentariotabla"));
			put(MSTAREAS_VE, "VISUALIZA " +Messages.getStringToKey("msTareas.comentariotabla"));
			put(TDEVENTO_CREA, "CREA "+Messages.getStringToKey("tdEvento.comentariotabla"));
			put(TDEVENTO_VE, "VISUALIZA " +Messages.getStringToKey("tdEvento.comentariotabla"));
			put(TDGRUPOSFLUJOS_CREA, "CREA "+Messages.getStringToKey("tdGruposflujos.comentariotabla"));
			put(TDGRUPOSFLUJOS_VE, "VISUALIZA " +Messages.getStringToKey("tdGruposflujos.comentariotabla"));
			put(TDGRUPOS_CREA, "CREA "+Messages.getStringToKey("tdGrupos.comentariotabla"));
			put(TDGRUPOS_VE, "VISUALIZA " +Messages.getStringToKey("tdGrupos.comentariotabla"));
			put(TDITINERARIO_CREA, "CREA "+Messages.getStringToKey("tdItinerario.comentariotabla"));
			put(TDITINERARIO_VE, "VISUALIZA " +Messages.getStringToKey("tdItinerario.comentariotabla"));
			put(TDPARTICIPANTES_CREA, "CREA "+Messages.getStringToKey("tdParticipantes.comentariotabla"));
			put(TDPARTICIPANTES_VE, "VISUALIZA " +Messages.getStringToKey("tdParticipantes.comentariotabla"));
			put(TDUBICACIONES_CREA, "CREA "+Messages.getStringToKey("tdUbicaciones.comentariotabla"));
			put(TDUBICACIONES_VE, "VISUALIZA " +Messages.getStringToKey("tdUbicaciones.comentariotabla"));
			 */
		}
	};

	public static List<IDsValorDto> getRoles(int opcion) {

		List<IDsValorDto> rolesLista = new ArrayList<IDsValorDto>();
		Map<String, String> rolesMapa = null;
		switch (opcion) {
		case 0: {
			rolesMapa = ROLES_DESCRIPCION;
		}
			break;
		case 1: {

		}
			break;
		case 2: {

		}
			break;
		case 3: {

		}
			break;
		}
		Set<String> roles = rolesMapa.keySet();
		for (String rol : roles) {
			String codigo = rol;
			String descripcion = rolesMapa.get(rol);
			IDsValorDto rolesItemDto = new IDsValorDto(codigo,descripcion);
			rolesLista.add(rolesItemDto);
		}

		if (rolesLista != null && rolesLista.size() > 0) {
			Collections.sort(rolesLista, new Comparator<IDsValorDto>() {
				public int compare(IDsValorDto e1, IDsValorDto e2) {
					return e1.getValor().compareToIgnoreCase(e2.getValor());
				}
			});
		}
		return rolesLista;
	}

	@SuppressWarnings({"rawtypes", "unchecked" })
	public static HashMap sortValues(Map map) {
		List list = new LinkedList(map.entrySet());
		// Custom Comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		// copying the sorted list in HashMap to preserve the iteration order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public static TreeMap sortKeys(Map map) {
		TreeMap tm=new  TreeMap(map);
		return tm;
	}
	
	public static String getDescripcion(String key){
		if(ROLES_DESCRIPCION.containsKey(key)){
			return ROLES_DESCRIPCION.get(key);
		}else return key;
	}
	
}
