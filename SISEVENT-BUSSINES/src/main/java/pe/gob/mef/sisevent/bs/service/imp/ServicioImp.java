package pe.gob.mef.sisevent.bs.service.imp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jxl.read.biff.BiffException;
import pe.gob.mef.sisevent.bs.cache.clases.CacheMsUsuariosBk;
import pe.gob.mef.sisevent.bs.ctlracceso.MsActividadesACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsCategoriasACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsInstitucionesACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsPaisesACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsPersonasACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsTareasACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsUbigeoACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsUnidadesOrgACL;
import pe.gob.mef.sisevent.bs.ctlracceso.MsUsuariosACL;
import pe.gob.mef.sisevent.bs.ctlracceso.PrtParametrosACL;
import pe.gob.mef.sisevent.bs.ctlracceso.Roles;
import pe.gob.mef.sisevent.bs.ctlracceso.TdEventoACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdFeriadosACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdGruposACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdGruposflujosACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdItinerarioACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdParticipantesACL;
import pe.gob.mef.sisevent.bs.ctlracceso.TdUbicacionesACL;
import pe.gob.mef.sisevent.bs.dao.MsActividadesDao;
import pe.gob.mef.sisevent.bs.dao.MsCategoriasDao;
import pe.gob.mef.sisevent.bs.dao.MsInstitucionesDao;
import pe.gob.mef.sisevent.bs.dao.MsPaisesDao;
import pe.gob.mef.sisevent.bs.dao.MsPersonasDao;
import pe.gob.mef.sisevent.bs.dao.MsRolesDao;
import pe.gob.mef.sisevent.bs.dao.MsTareasDao;
import pe.gob.mef.sisevent.bs.dao.MsUbigeoDao;
import pe.gob.mef.sisevent.bs.dao.MsUnidadesOrgDao;
import pe.gob.mef.sisevent.bs.dao.MsUsuariosDao;
import pe.gob.mef.sisevent.bs.dao.PrtParametrosDao;
import pe.gob.mef.sisevent.bs.dao.TdAnexosDao;
import pe.gob.mef.sisevent.bs.dao.TdCategEventoDao;
import pe.gob.mef.sisevent.bs.dao.TdEventoDao;
import pe.gob.mef.sisevent.bs.dao.TdFeriadosDao;
import pe.gob.mef.sisevent.bs.dao.TdFlujoDao;
import pe.gob.mef.sisevent.bs.dao.TdGruposDao;
import pe.gob.mef.sisevent.bs.dao.TdGruposflujosDao;
import pe.gob.mef.sisevent.bs.dao.TdItinerarioDao;
import pe.gob.mef.sisevent.bs.dao.TdParticipantesDao;
import pe.gob.mef.sisevent.bs.dao.TdUbicacionesDao;
import pe.gob.mef.sisevent.bs.domain.MsActividades;
import pe.gob.mef.sisevent.bs.domain.MsCategorias;
import pe.gob.mef.sisevent.bs.domain.MsInstituciones;
import pe.gob.mef.sisevent.bs.domain.MsPaises;
import pe.gob.mef.sisevent.bs.domain.MsPersonas;
import pe.gob.mef.sisevent.bs.domain.MsRoles;
import pe.gob.mef.sisevent.bs.domain.MsTareas;
import pe.gob.mef.sisevent.bs.domain.MsUbigeo;
import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;
import pe.gob.mef.sisevent.bs.domain.MsUnidadesOrg;
import pe.gob.mef.sisevent.bs.domain.MsUsuarios;
import pe.gob.mef.sisevent.bs.domain.PrtParametros;
import pe.gob.mef.sisevent.bs.domain.TdAnexos;
import pe.gob.mef.sisevent.bs.domain.TdCategEvento;
import pe.gob.mef.sisevent.bs.domain.TdEvento;
import pe.gob.mef.sisevent.bs.domain.TdFeriados;
import pe.gob.mef.sisevent.bs.domain.TdFlujo;
import pe.gob.mef.sisevent.bs.domain.TdGrupos;
import pe.gob.mef.sisevent.bs.domain.TdGruposflujos;
import pe.gob.mef.sisevent.bs.domain.TdItinerario;
import pe.gob.mef.sisevent.bs.domain.TdParticipantes;
import pe.gob.mef.sisevent.bs.domain.TdUbicaciones;
import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.IDValorDto;
import pe.gob.mef.sisevent.bs.transfer.IDsValorDto;
import pe.gob.mef.sisevent.bs.transfer.IIDValorDto;
import pe.gob.mef.sisevent.bs.transfer.MsInstitucionesDto;
import pe.gob.mef.sisevent.bs.transfer.MsPersonasDto;
import pe.gob.mef.sisevent.bs.transfer.MsUnidadesOrgDto;
import pe.gob.mef.sisevent.bs.transfer.NodosDto;
import pe.gob.mef.sisevent.bs.transfer.Progreso;
import pe.gob.mef.sisevent.bs.transfer.TdAnexosDto;
import pe.gob.mef.sisevent.bs.transfer.bk.MsActividadesBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsCategoriasBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsInstitucionesBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPaisesBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsPersonasBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsRolesBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsTareasBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUbigeoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.PrtParametrosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdCategEventoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdEventoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFeriadosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdItinerarioBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdParticipantesBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdUbicacionesBk;
import pe.gob.mef.sisevent.bs.utils.Celdas;
import pe.gob.mef.sisevent.bs.utils.EMailUtil;
import pe.gob.mef.sisevent.bs.utils.Encriptar;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.bs.utils.LeerCeldasExcel;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsActividadesMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsCategoriasMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsInstitucionesMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsPaisesMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsPersonasMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsRolesMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsTareasMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsUbigeoMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsUnidadesOrgMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaMsUsuariosMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaPrtParametrosMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdAnexosMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdEventoMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdFeriadosMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdFlujoMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdGruposMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdGruposflujosMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdItinerarioMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdParticipantesMng;
import pe.gob.mef.sisevent.bs.val.audi.AuditoriaTdUbicacionesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsActividadesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsCategoriasMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsInstitucionesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsPaisesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsPersonasMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsRolesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsTareasMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsUbigeoMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsUnidadesOrgMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionMsUsuariosMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionPrtParametrosMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdAnexosMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdEventoMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdFeriadosMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdFlujoMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdGruposMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdGruposflujosMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdItinerarioMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdParticipantesMng;
import pe.gob.mef.sisevent.bs.val.validator.ValidacionTdUbicacionesMng;

/**
 * SERVICIO: SERVICIO PRINCIPAL DE FACADE PATTERN
 * 
 * @author Carlos Aguilar
 * @version 2.0, 26/11/2020 00:02
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/
 *          /Carlos Aguilar Chamochumbi / 26/11/2020 00:02 / Creación de la
 *          clase /
 * 
 */
@Service
public class ServicioImp implements Servicio, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3525833212613563737L;

	private static final Logger log = Logger.getLogger(ServicioImp.class.getName());

	@Autowired
	private MsUsuariosDao msUsuariosDao = null;
	@Autowired
	private PrtParametrosDao prtParametrosDao = null;
	@Autowired
	private MsUnidadesOrgDao msUnidadesOrgDao = null;
	@Autowired
	private MsRolesDao msRolesDao = null;

	@Autowired
	private MsInstitucionesDao msInstitucionesDao = null;
	@Autowired
	private MsUbigeoDao msUbigeoDao = null;
	@Autowired
	private MsPaisesDao msPaisesDao = null;
	@Autowired
	private MsPersonasDao msPersonasDao = null;
	@Autowired
	private TdAnexosDao tdAnexosDao = null;

	@Autowired
	private TdFlujoDao tdFlujoDao = null;
	@Autowired
	private TdFeriadosDao tdFeriadosDao = null;

	private List<String> sedesCache = null;
	private List<NodosDto> uoTreeCache = null;
	private List<MsUnidadesOrgDto> uoListaCache = null;
	private Map<Long, MsUsuariosBk> msUsuariosBkCache = null;
	private Map<String, MsUsuariosBk> msUsuariosBkXUseranameCache = null;

	private CacheMsUsuariosBk cacheMsUsuariosBkActivos = null;

	private Map<Integer, String> estadosdeflujo = null;
	private Map<Long, MsUnidadesOrgBk> msUnidadesOrgBkCache = null;
	private Map<String, Long> tiemposestados = null;
	private List<Timestamp> diasFeriados = null;
	private Map<Integer, String> estadosdeatencion = null;
	private List<MsUsuariosBk> usuariosquecreanatenciones = null;
	private List<MsUsuariosBk> usuariosdearea = null;
	private Map<String, String> emailservice = null;
	private boolean debugmail = false;

	private Long xDefectoCodpais = null;
	private Integer xDefectoCoddpto = null;
	private Integer xDefectoCodprov = null;
	private Integer xDefectoCoddist = null;

	private String endpointVentanilla = null;

	private Progreso progreso = null;

	// -----
	@Autowired
	private TdUbicacionesDao tdUbicacionesDao = null;
	@Autowired
	private TdEventoDao tdEventoDao = null;

	// -----
	@Autowired
	private TdParticipantesDao tdParticipantesDao = null;

	// -----
	@Autowired
	private TdItinerarioDao tdItinerarioDao = null;

	// -----
	@Autowired
	private TdGruposDao tdGruposDao = null;

	// -----
	@Autowired
	private TdGruposflujosDao tdGruposflujosDao = null;
	@Autowired
	private MsTareasDao msTareasDao = null;
	@Autowired
	private MsCategoriasDao msCategoriasDao = null;
	@Autowired
	private MsActividadesDao msActividadesDao = null;
	// MPINARES 01092023 - INICIO
	@Autowired
	private TdCategEventoDao tdCategEventoDao = null;
	// MPINARES 01092023 - FIN
	// -----

	private List<IDValorDto> tdEventoIdeventListaCache = null;
	private List<IDValorDto> msUsuariosIdusuarioListaCache = null;
	private List<IDValorDto> msPaisesPaiPkListaCache = null;

	private List<MsInstitucionesDto> msInstitucionesListaCache = null;
	private List<MsPersonasDto> msPersonasListaCache = null;
	private List<IDValorDto> prtParametrosIdparametroListaCache = null;
	private List<MsCategoriasBk> msCategoriasIdcategoriasListaCache = null;

	private List<IDValorDto> prtParametrosContratoTipoTarea = null; // vbaldeon
																	// 08092023

	private List<IDsValorDto> prtParametrosContratoTipoEventoo = null;// MPINARES
																		// 01092023
																		// -
																		// INICIO

	public ServicioImp() {
		super();
	}

	/**
	 */
	@PostConstruct
	private void inicialice() {
		log.info("Inicilizando el servicio msUsuariosService Implementación");
		// /-------INICIALIZANDO PRIMER USUARIO ADMINISTRADOR SI LA TABLA ESTA
		// VACIA------
		// Long idmax = msUsuariosDao.getMaxIdVal();
		List<MsUsuarios> msUsuarios = msUsuariosDao.getByIdMsUsuarios("AdminSID-D");
		if (msUsuarios == null || msUsuarios.size() <= 0) {
			MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
			List<String> roles = new ArrayList<String>();
			roles.add(Roles.ADMINISTRADOR);
			msUsuariosBk.setUsername("AdminSID-D");
			msUsuariosBk.setNombres("ADMINISTRADOR");
			msUsuariosBk.setApellidoPaterno("DEL");
			msUsuariosBk.setApellidoMaterno("SISTEMA");
			msUsuariosBk.setCorreo("stramite@mef.gob.pe");
			msUsuariosBk.setContrasenia("S1s3v3nt");
			msUsuariosBk.setContraseniaConfir("S1s3v3nt");
			msUsuariosBk.setRolesSistema(roles);
			msUsuariosBk.setSede("PRINCIPAL");
			msUsuariosBk.setEstado(1);
			msUsuariosBk.setIdunidad(1L);
			try {
				saveorupdateMsUsuariosBk(msUsuariosBk, "AdminSID-D", 1L, 1L, 1L, "127.0.0.1", false);
			} catch (Validador e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// inicialiceData();

		/// CACHES///
		inicializeCaches();
		///////////
	}

	@Override
	@Async
	public void inicialiceData() {
		progreso = new Progreso();
		progreso.setIniLabel("--INICIO--");
		progreso.setIniTotal(100);
		progreso.setIniProgreso(0);
		// /-------INICIANLIZANDO PARAMETROS INICIALES DEL SYSTEMA------
		Long idmaxP = prtParametrosDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			progreso.setIniLabel("INICIANLIZANDO PARAMETROS INICIALES DEL SYSTEMA");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("PARAMETROS.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PARAMETROS.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 0, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				Map<String, List<String>> mapa = new HashMap<String, List<String>>();
				for (Celdas celda : celdass) {
					String categoria = celda.getCelda0();
					String valor = celda.getCelda1();
					List<String> valores = null;
					if (mapa.containsKey(categoria)) {
						valores = mapa.get(categoria);
					} else {
						valores = new ArrayList<String>();
						mapa.put(categoria, valores);
					}
					valores.add(valor);
				}
				int contador = 0;
				for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
					String key = entry.getKey();
					List<String> valores = entry.getValue();
					PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
					prtParametrosBkPadre.setDescripcion(key);
					prtParametrosBkPadre.setIdpadre(0L);
					prtParametrosBkPadre.setEstado(1);
					try {
						prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L,
								null, "127.0.0.1", null);
						if (prtParametrosBkPadre.getIdparametro() != null
								&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
							long orden = 1;
							for (String valor : valores) {
								PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
								prtParametrosBkEliminado.setDescripcion(valor);
								prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
								prtParametrosBkEliminado.setOrden(orden);
								prtParametrosBkEliminado.setEstado(1);
								prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado,
										"AdminSID-D", 1L, 1L, null, "127.0.0.1", null);
								orden++;
								contador++;
								progreso.setIniProgreso(contador);
							}
						}
					} catch (Validador e) {
						e.printStackTrace();
					}
				}
			}
		}

		// INICIALIZANDO UO
		idmaxP = msUnidadesOrgDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			progreso.setIniLabel("INICIALIZANDO UO");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("UNIDADES.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("UNIDADES.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				Map<Long, MsUnidadesOrgBk> mapa = new HashMap<Long, MsUnidadesOrgBk>();
				for (Celdas celda : celdass) {
					try {
						Long IDUNIDAD = Long.parseLong(celda.getCelda0());
						Long IDPADRE = Long.parseLong(celda.getCelda1());
						String CODIGO = celda.getCelda2();
						String DESCRIPCION = celda.getCelda3();
						String ACRONIMO = celda.getCelda12();
						Integer FLAGOFGENERAL = Integer
								.parseInt(celda.getCelda19() == null || celda.getCelda19().trim().length() <= 0 ? "0"
										: celda.getCelda19());

						MsUnidadesOrgBk msUnidadesOrgBk = new MsUnidadesOrgBk();
						msUnidadesOrgBk.setAcronimo(ACRONIMO);
						msUnidadesOrgBk.setCodigo(CODIGO);
						msUnidadesOrgBk.setDescripcion(DESCRIPCION);
						msUnidadesOrgBk.setFlagofgeneral(FLAGOFGENERAL);
						msUnidadesOrgBk.setIdpadre(IDPADRE);
						msUnidadesOrgBk.setEstado(1);

						mapa.put(IDUNIDAD, msUnidadesOrgBk);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				int contador = 0;
				for (Map.Entry<Long, MsUnidadesOrgBk> entry : mapa.entrySet()) {
					Long key = entry.getKey();
					MsUnidadesOrgBk msUnidadesOrgBk = entry.getValue();
					if (msUnidadesOrgBk.getIdunidad() == null) {
						try {
							msUnidadesOrgBk = saveorupdateMsUnidadesOrgBk(msUnidadesOrgBk, "AdminSID-D", 1L, 1L, 1L,
									"127.0.0.1"); // saveUORecursivo(mapa,
													// msUnidadesOrgBk);
							mapa.put(key, msUnidadesOrgBk);
							contador++;
							progreso.setIniProgreso(contador);
						} catch (Validador e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				progreso.setIniLabel("ACTUALIZANDO UO PADRES");
				contador = 0;
				for (Map.Entry<Long, MsUnidadesOrgBk> entry : mapa.entrySet()) {
					Long key = entry.getKey();
					MsUnidadesOrgBk msUnidadesOrgBk = entry.getValue();
					if (msUnidadesOrgBk.getIdpadre() != null && msUnidadesOrgBk.getIdpadre().longValue() > 0) {
						MsUnidadesOrgBk msUnidadesOrgBkP = mapa.get(msUnidadesOrgBk.getIdpadre());
						if (msUnidadesOrgBkP != null) {
							msUnidadesOrgBk.setIdpadre(msUnidadesOrgBkP.getIdunidad());
						} else {
							msUnidadesOrgBk.setIdpadre(0L);
						}
					} else {
						msUnidadesOrgBk.setIdpadre(0L);
					}
					try {
						msUnidadesOrgBk = saveorupdateMsUnidadesOrgBk(msUnidadesOrgBk, "AdminSID-D", 1L, 1L, 1L,
								"127.0.0.1"); // saveUORecursivo(mapa,
												// msUnidadesOrgBk);
						mapa.put(key, msUnidadesOrgBk);
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					contador++;
					progreso.setIniProgreso(contador);
				}
			}
		}

		/// INICIANLIZANDO USUARIOS
		idmaxP = msUsuariosDao.getMaxIdVal();
		if (idmaxP.longValue() <= 3) {
			progreso.setIniLabel("INICIANLIZANDO USUARIOS");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);

			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("UsuariosF.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("UsuariosF.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {
					MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
					List<String> roles = new ArrayList<String>();
					roles.add(Roles.RESPONSABLE);
					msUsuariosBk.setUsername(celda.getCelda0());
					msUsuariosBk.setNombres(celda.getCelda1());
					msUsuariosBk.setApellidoPaterno(celda.getCelda2());
					msUsuariosBk.setApellidoMaterno(celda.getCelda3());
					msUsuariosBk.setContrasenia(
							celda.getCelda0().substring(0, 1).toUpperCase() + celda.getCelda0().substring(1) + "2021");
					msUsuariosBk.setContraseniaConfir(
							celda.getCelda0().substring(0, 1).toUpperCase() + celda.getCelda0().substring(1) + "2021");
					msUsuariosBk.setRolesSistema(roles);
					msUsuariosBk.setSede(celda.getCelda4());

					msUsuariosBk.setIdunidad(1L);
					try {
						List<MsUnidadesOrg> msUnidadesOrgss = msUnidadesOrgDao
								.getByNombreMsUnidadesOrg(celda.getCelda6());
						if (!msUnidadesOrgss.isEmpty()) {
							MsUnidadesOrg msUnidadesOrg = msUnidadesOrgss.get(0);
							msUsuariosBk.setIdunidad(msUnidadesOrg.getIdunidad());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					msUsuariosBk.setEstado(1);

					try {
						saveorupdateMsUsuariosBk(msUsuariosBk, "AdminSID-D", 1L, 1L, 1L, "127.0.0.1", false);
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					contador++;
					progreso.setIniProgreso(contador);
				}
			}
		}
		///

		// INICIALIZANDO PAISES
		idmaxP = msPaisesDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			// System.out.println("CARGANDO PAISES....");
			progreso.setIniLabel("INICIANLIZANDO PAISES");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("PAISES.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PAISES.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				// System.out.println("LEYENDO EXCEL....");
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
				// System.out.println("LEYENDO EXCEL FIN....");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Timestamp hoy = new Timestamp(System.currentTimeMillis());
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {
					MsPaisesBk msPaisesBk = new MsPaisesBk();
					msPaisesBk.setEstado(1);
					msPaisesBk.setFechaCrea(hoy);
					msPaisesBk.setFechaModif(hoy);
					msPaisesBk.setIduserCrea(1L);
					msPaisesBk.setIduserModif(1L);
					msPaisesBk.setPaiIso2(null);
					msPaisesBk.setPaiIso3(celda.getCelda1());
					msPaisesBk.setPaiIsonum(null);
					msPaisesBk.setPaiNombre(celda.getCelda2());
					msPaisesBk.setPaiPk(null);
					msPaisesBk.setRmtaddress("127.0.0.1");
					msPaisesBk.setRmtaddressrst("127.0.0.1");

					try {
						saveorupdateMsPaisesBk(msPaisesBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}
		}

		// INICIALIZANDO UBIGEO
		idmaxP = msUbigeoDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			progreso.setIniLabel("INICIANLIZANDO UBIGEO");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("UBIGEO.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("UBIGEO.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp hoy = new Timestamp(System.currentTimeMillis());
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					MsUbigeoId msUbigeoId = new MsUbigeoId();
					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda0());
						codprov = Integer.parseInt(celda.getCelda1());
						coddist = Integer.parseInt(celda.getCelda2());
					} catch (Exception e) {
						continue;
					}
					if (coddpto.intValue() == 7) {
						System.out.println("AQUI");
					}
					msUbigeoId.setCoddpto(coddpto);
					msUbigeoId.setCodprov(codprov);
					msUbigeoId.setCoddist(coddist);

					MsUbigeoBk msUbigeoBk = new MsUbigeoBk();
					msUbigeoBk.setEstado(null);
					msUbigeoBk.setFechaCrea(hoy);
					msUbigeoBk.setFechaModif(hoy);
					msUbigeoBk.setId(msUbigeoId);
					msUbigeoBk.setIduserCrea(1L);
					msUbigeoBk.setIduserModif(1L);
					msUbigeoBk.setNombre(celda.getCelda3());
					msUbigeoBk.setRmtaddress("127.0.0.1");
					msUbigeoBk.setRmtaddressrst("127.0.0.1");

					try {
						saveorupdateMsUbigeoBk(msUbigeoBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}
		}

		// INICIALIZANDO INSTITUCIONES
		idmaxP = msInstitucionesDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			progreso.setIniLabel("INICIANLIZANDO INSTITUCIONES");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("INSTITUCIONES.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("INSTITUCIONES.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp hoy = new Timestamp(System.currentTimeMillis());
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {
					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;

					try {
						coddpto = Integer.parseInt(celda.getCelda13());
						codprov = Integer.parseInt(celda.getCelda14());
						coddist = Integer.parseInt(celda.getCelda15());
						codpais = Long.parseLong(celda.getCelda12());
						if (codpais.longValue() == 173L) {
							codpais = 150L;
						}
					} catch (Exception e) {
						continue;
					}

					MsInstitucionesBk msInstitucionesBk = new MsInstitucionesBk();
					msInstitucionesBk.setCoddist(coddist);
					msInstitucionesBk.setCoddpto(coddpto);
					msInstitucionesBk.setCodpais(codpais);
					msInstitucionesBk.setCodprov(codprov);
					msInstitucionesBk.setCorreo(celda.getCelda5());
					msInstitucionesBk.setDireccion(celda.getCelda17());
					msInstitucionesBk.setEstado(1);
					msInstitucionesBk.setFax(celda.getCelda9());
					msInstitucionesBk.setFechaCrea(hoy);
					msInstitucionesBk.setFechaModif(hoy);
					msInstitucionesBk.setIdprovee(null);
					msInstitucionesBk.setIduserCrea(1L);
					msInstitucionesBk.setIduserModif(1L);
					msInstitucionesBk.setRazonSocial(celda.getCelda2());
					msInstitucionesBk.setRmtaddress("127.0.0.1");
					msInstitucionesBk.setRmtaddressrst("127.0.0.1");
					msInstitucionesBk.setRuc(celda.getCelda4());
					msInstitucionesBk.setSiglas(celda.getCelda3());
					msInstitucionesBk.setTelefono(celda.getCelda7());
					msInstitucionesBk.setWeb(celda.getCelda6() == null ? celda.getCelda6().toLowerCase() : null);

					try {
						saveorupdateMsInstitucionesBk(msInstitucionesBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					contador++;
					progreso.setIniProgreso(contador);
				}
			}
		}

		// INICIALIZANDO PERSONAS
		idmaxP = msPersonasDao.getMaxIdVal();
		if (idmaxP.longValue() <= 1) {
			///
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 1");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);

			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource("PERSONA1.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA1.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Celdas> celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp hoy = new Timestamp(System.currentTimeMillis());
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 2
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 2");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA2.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA2.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 3
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 3");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA3.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA3.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 4
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 4");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA4.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA4.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 5
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 5");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA5.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA5.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 6
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 6");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA6.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA6.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}

			/// 7
			progreso.setIniLabel("INICIANLIZANDO PERSONAS MOD 7");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			configFile = new File(classLoader.getResource("PERSONA7.xls").getFile());
			if (!configFile.exists()) {
				Resource resource = new ClassPathResource("PERSONA7.xls");
				try {
					configFile = resource.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			celdass = null;
			try {
				celdass = LeerCeldasExcel.readExcel(configFile, 1, 1, "ISO-8859-9");
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (celdass != null && celdass.size() > 0) {
				progreso.setIniTotal(celdass.size());
				int contador = 0;
				for (Celdas celda : celdass) {

					Integer coddpto = null;
					Integer codprov = null;
					Integer coddist = null;
					Long codpais = null;
					Long tipodoc = null;
					try {
						coddpto = Integer.parseInt(celda.getCelda15());
						codprov = Integer.parseInt(celda.getCelda16());
						coddist = Integer.parseInt(celda.getCelda17());
						codpais = Long.parseLong(celda.getCelda14());
						tipodoc = Long.parseLong(celda.getCelda27() == null ? "0" : celda.getCelda27());
					} catch (Exception e) {
						continue;
					}

					MsPersonasBk msPersonasBk = new MsPersonasBk();
					msPersonasBk.setApellidoMaterno(celda.getCelda2());
					msPersonasBk.setApellidoPaterno(celda.getCelda1());
					msPersonasBk.setCelular(celda.getCelda13());
					msPersonasBk.setCoddist(coddist);
					msPersonasBk.setCoddpto(coddpto);
					msPersonasBk.setCodpais(codpais);
					msPersonasBk.setCodprov(codprov);
					msPersonasBk.setCorreo(celda.getCelda9());
					msPersonasBk.setDireccion(celda.getCelda19());
					msPersonasBk.setDocumentoNumero(celda.getCelda7());
					msPersonasBk.setEstado(1);
					msPersonasBk.setFechaCrea(hoy);
					msPersonasBk.setFechaModif(hoy);
					msPersonasBk.setIduserCrea(1L);
					msPersonasBk.setIduserModif(1L);
					msPersonasBk.setNombres(celda.getCelda3());
					msPersonasBk.setRmtaddress("127.0.0.1");
					msPersonasBk.setRmtaddressrst("127.0.0.1");
					msPersonasBk.setSexo(celda.getCelda4());
					msPersonasBk.setTelefono(celda.getCelda10());
					msPersonasBk.setTienefoto(celda.getCelda26());
					msPersonasBk.setTipodocumento(tipodoc);

					try {
						saveorupdateMsPersonasBk(msPersonasBk, "AdminSID-D", 1L, 1L, "127.0.0.1");
					} catch (Validador e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					contador++;
					progreso.setIniProgreso(contador);
				}
			}
			//////
		}

		//// ESTADOS DEL FLUJO
		PrtParametros p = prtParametrosDao.getDescripcion("ESTADOS DE LOS MOVIMIENTOS");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO ESTADOS DE LOS MOVIMIENTOS");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);

			String key = "ESTADOS DE LOS MOVIMIENTOS";
			List<IDValorDto> valores = new ArrayList<IDValorDto>();
			valores.add(new IDValorDto(1L, "INICIO"));
			valores.add(new IDValorDto(2L, "ASIGNADO"));
			valores.add(new IDValorDto(3L, "DERIVADO"));
			valores.add(new IDValorDto(4L, "DEVUELTO"));
			valores.add(new IDValorDto(5L, "POR FINALIZAR"));
			valores.add(new IDValorDto(6L, "NOTIFICADO"));
			valores.add(new IDValorDto(7L, "FINALIZADO"));
			valores.add(new IDValorDto(8L, "ENTIDAD NOTIFICADA"));
			valores.add(new IDValorDto(9L, "ANULADO"));
			valores.add(new IDValorDto(10L, "LLAMADA DERIVADA"));

			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(valores.size());
					int contador = 0;
					for (IDValorDto valor : valores) {
						PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
						prtParametrosBkEliminado.setDescripcion(valor.getValor());
						prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
						prtParametrosBkEliminado.setOrden(valor.getId());
						prtParametrosBkEliminado.setEstado(1);
						prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado, "AdminSID-D",
								1L, 1L, null, "127.0.0.1", null);

						contador++;
						progreso.setIniProgreso(contador);
					}
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		//// TIEMPO DE ESTADIA
		p = prtParametrosDao.getDescripcion("TIEMPO DE ESTADIA");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO TIEMPO DE ESTADIA");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			String key = "TIEMPO DE ESTADIA";
			List<IDValorDto> valores = new ArrayList<IDValorDto>();
			valores.add(new IDValorDto(8L, "VERDE"));
			valores.add(new IDValorDto(10L, "AMARILLO"));
			valores.add(new IDValorDto(10L, "ROJO"));

			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(valores.size());
					int contador = 0;
					for (IDValorDto valor : valores) {
						PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
						prtParametrosBkEliminado.setDescripcion(valor.getValor());
						prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
						prtParametrosBkEliminado.setOrden(valor.getId());
						prtParametrosBkEliminado.setEstado(1);
						prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado, "AdminSID-D",
								1L, 1L, null, "127.0.0.1", null);

						contador++;
						progreso.setIniProgreso(contador);
					}
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		//// ESTADOS DE LAS ATENCIONES
		p = prtParametrosDao.getDescripcion("ESTADOS DE LAS ATENCIONES");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO ESTADOS DE LAS ATENCIONES");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			String key = "ESTADOS DE LAS ATENCIONES";
			List<IDValorDto> valores = new ArrayList<IDValorDto>();
			valores.add(new IDValorDto(1L, "REGISTRADO"));
			valores.add(new IDValorDto(2L, "EN PROCESO"));
			valores.add(new IDValorDto(3L, "POR FINALIZAR"));
			valores.add(new IDValorDto(4L, "FINALIZADO"));
			valores.add(new IDValorDto(5L, "ANULADO"));

			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(valores.size());
					int contador = 0;
					for (IDValorDto valor : valores) {
						PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
						prtParametrosBkEliminado.setDescripcion(valor.getValor());
						prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
						prtParametrosBkEliminado.setOrden(valor.getId());
						prtParametrosBkEliminado.setEstado(1);
						prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado, "AdminSID-D",
								1L, 1L, null, "127.0.0.1", null);
						contador++;
						progreso.setIniProgreso(contador);
					}
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		//// PARAMETROS DEL SERVICIO DE EMAIL
		p = prtParametrosDao.getDescripcion("SERVICIO DE EMAIL");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO SERVICIO DE EMAIL");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);

			String key = "SERVICIO DE EMAIL";
			List<IDsValorDto> valores = new ArrayList<IDsValorDto>();
			valores.add(new IDsValorDto("FROMEMAIL", "mefservicios2021@gmail.com"));
			valores.add(new IDsValorDto("PSSWRD", "cde321qazS"));
			valores.add(new IDsValorDto("SMTPHOST", "smtp.gmail.com"));
			valores.add(new IDsValorDto("SSLPORT", "465"));
			valores.add(new IDsValorDto("SMTPPORT", "587"));
			valores.add(new IDsValorDto("TIPO-SSL-TLS-NA", "SSL"));

			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(valores.size());
					int contador = 0;
					for (IDsValorDto valor : valores) {
						PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
						prtParametrosBkEliminado.setDescripcion(valor.getId());
						prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
						prtParametrosBkEliminado.setDescripcioncorta(valor.getValor());
						prtParametrosBkEliminado.setEstado(1);
						prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado, "AdminSID-D",
								1L, 1L, null, "127.0.0.1", null);
						contador++;
						progreso.setIniProgreso(contador);
					}
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		//// PARAMETROS DEL SERVICIO DE TRAMITE
		p = prtParametrosDao.getDescripcion("SERVICIO DE TRAMITE");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO SERVICIO DE TRAMITE");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			String key = "SERVICIO DE TRAMITE";
			List<IDsValorDto> valores = new ArrayList<IDsValorDto>();
			valores.add(new IDsValorDto("endpointVentanilla",
					"http://sisadm.mef.gob.pe:8280/tramite/webservice/ventanillastd"));
			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(valores.size());
					int contador = 0;
					for (IDsValorDto valor : valores) {
						PrtParametrosBk prtParametrosBkEliminado = new PrtParametrosBk();
						prtParametrosBkEliminado.setDescripcion(valor.getId());
						prtParametrosBkEliminado.setIdpadre(prtParametrosBkPadre.getIdparametro());
						prtParametrosBkEliminado.setDescripcioncorta(valor.getValor());
						prtParametrosBkEliminado.setEstado(1);
						prtParametrosBkEliminado = saveorupdatePrtParametrosBk(prtParametrosBkEliminado, "AdminSID-D",
								1L, 1L, null, "127.0.0.1", null);

						contador++;
						progreso.setIniProgreso(contador);
					}
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		//// PARAMETROS DEL TIPO DE ENTIDAD
		p = prtParametrosDao.getDescripcion("TIPO DE ENTIDAD");
		if (p == null) {
			progreso.setIniLabel("INICIANLIZANDO TIPO DE ENTIDAD");
			progreso.setIniTotal(0);
			progreso.setIniProgreso(0);
			String key = "TIPO DE ENTIDAD";
			PrtParametrosBk prtParametrosBkPadre = new PrtParametrosBk();
			prtParametrosBkPadre.setDescripcion(key);
			prtParametrosBkPadre.setIdpadre(0L);
			prtParametrosBkPadre.setEstado(1);
			try {
				prtParametrosBkPadre = saveorupdatePrtParametrosBk(prtParametrosBkPadre, "AdminSID-D", 1L, 1L, null,
						"127.0.0.1", null);
				if (prtParametrosBkPadre.getIdparametro() != null
						&& prtParametrosBkPadre.getIdparametro().longValue() > 0L) {
					progreso.setIniTotal(4);

					PrtParametrosBk prtParametrosBkAct = new PrtParametrosBk();
					prtParametrosBkAct.setDescripcion("GOBIERNO NACIONAL");
					prtParametrosBkAct.setIdpadre(prtParametrosBkPadre.getIdparametro());
					prtParametrosBkAct.setDescripcioncorta("GN");
					prtParametrosBkAct.setOrden(1L);
					prtParametrosBkAct.setEstado(1);
					prtParametrosBkAct = saveorupdatePrtParametrosBk(prtParametrosBkAct, "AdminSID-D", 1L, 1L, null,
							"127.0.0.1", null);
					progreso.setIniProgreso(1);
					prtParametrosBkAct = new PrtParametrosBk();
					prtParametrosBkAct.setDescripcion("GOBIERNO REGIONAL");
					prtParametrosBkAct.setIdpadre(prtParametrosBkPadre.getIdparametro());
					prtParametrosBkAct.setDescripcioncorta("GR");
					prtParametrosBkAct.setOrden(2L);
					prtParametrosBkAct.setEstado(1);
					prtParametrosBkAct = saveorupdatePrtParametrosBk(prtParametrosBkAct, "AdminSID-D", 1L, 1L, null,
							"127.0.0.1", null);
					progreso.setIniProgreso(2);
					prtParametrosBkAct = new PrtParametrosBk();
					prtParametrosBkAct.setDescripcion("GOBIERNO LOCAL");
					prtParametrosBkAct.setIdpadre(prtParametrosBkPadre.getIdparametro());
					prtParametrosBkAct.setDescripcioncorta("GL");
					prtParametrosBkAct.setOrden(3L);
					prtParametrosBkAct.setEstado(1);
					prtParametrosBkAct = saveorupdatePrtParametrosBk(prtParametrosBkAct, "AdminSID-D", 1L, 1L, null,
							"127.0.0.1", null);
					progreso.setIniProgreso(3);
					prtParametrosBkAct = new PrtParametrosBk();
					prtParametrosBkAct.setDescripcion("OTROS");
					prtParametrosBkAct.setIdpadre(prtParametrosBkPadre.getIdparametro());
					prtParametrosBkAct.setDescripcioncorta("OT");
					prtParametrosBkAct.setOrden(4L);
					prtParametrosBkAct.setEstado(1);
					prtParametrosBkAct = saveorupdatePrtParametrosBk(prtParametrosBkAct, "AdminSID-D", 1L, 1L, null,
							"127.0.0.1", null);
					progreso.setIniProgreso(4);
				}
			} catch (Validador e) {
				e.printStackTrace();
			}
		}

		progreso.setIniLabel("--FIN--");
		progreso.setIniTotal(100);
		progreso.setIniProgreso(100);
	}

	@Override
	@Async
	public void inicializeCaches() {
		log.info("Inicilizando el servicio Cache");

		progreso = new Progreso();
		progreso.setIniLabel("--Inicilizando el servicio Cache--");
		progreso.setIniTotal(100);
		progreso.setIniProgreso(0);

		//////////////////////////
		sedesCache = null;
		uoTreeCache = null;
		uoListaCache = null;
		msUsuariosBkCache = null;
		msUsuariosBkXUseranameCache = null;

		cacheMsUsuariosBkActivos = null;

		estadosdeflujo = null;
		msUnidadesOrgBkCache = null;
		tiemposestados = null;
		diasFeriados = null;
		estadosdeatencion = null;
		usuariosquecreanatenciones = null;
		usuariosdearea = null;

		emailservice = null;

		xDefectoCodpais = null;
		xDefectoCoddpto = null;
		xDefectoCodprov = null;
		xDefectoCoddist = null;
		endpointVentanilla = null;
		/////////////////////////
		progreso.setIniProgreso(20);

		getListaSedes();
		progreso.setIniProgreso(24);
		getUOTreeCache();
		progreso.setIniProgreso(28);
		getUOListaCache();
		progreso.setIniProgreso(32);
		getEstadosdeflujo();
		progreso.setIniProgreso(36);
		getTiemposestados();
		progreso.setIniProgreso(40);
		getEndpointVentanilla();
		progreso.setIniProgreso(44);
		getxDefectoCodpais();
		progreso.setIniProgreso(48);
		getxDefectoCoddpto();
		progreso.setIniProgreso(52);
		getxDefectoCodprov();
		progreso.setIniProgreso(56);
		getxDefectoCoddist();
		progreso.setIniProgreso(60);
		getEmailservice();
		progreso.setIniProgreso(64);
		getUsuariosdearea();
		progreso.setIniProgreso(68);
		getUsuariosquecrean();
		progreso.setIniProgreso(72);
		getEstadosdeatencion();
		progreso.setIniProgreso(76);
		getDiasFeriados();
		progreso.setIniProgreso(80);
		getTiemposestados();
		progreso.setIniProgreso(82);
		getMsUnidadesOrgBkCache();
		progreso.setIniProgreso(86);
		getCacheMsUsuariosBkActivos();
		progreso.setIniProgreso(90);
		getMsUsuariosBkXUseranameCache();
		progreso.setIniProgreso(94);
		getMsUsuariosBkCache();
		progreso.setIniProgreso(98);//vbaldeon 08092023
		log.info("Inicilizando el servicio getPrtParametrosContratoTipoTarea");//vbaldeon 08092023
		getPrtParametrosContratoTipoTarea();//vbaldeon 08092023
		progreso.setIniProgreso(100);
		progreso.setIniLabel("--FIN--");
	}

	public MsUsuariosBk getMsUsuariosBkXid(Long id, String user) {
		if (id == null)
			return null;
		MsUsuarios msUsuarios = msUsuariosDao.getMsUsuarios(id);
		MsUsuariosBk msUsuariosBk = null;
		if (msUsuarios != null) {
			msUsuariosBk = new MsUsuariosBk();
			FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
			completarMsUsuarios(msUsuariosBk);
			if (user != null) {
				setACLMsUsuariosBk(msUsuariosBk, user);
			}
		}
		return msUsuariosBk;
	}

	public MsUsuariosBk getMsUsuariosBkXidCH(Long id) {
		if (id == null)
			return null;
		MsUsuariosBk msUsuariosBk = null;
		if (getMsUsuariosBkCache() != null) {
			msUsuariosBk = getMsUsuariosBkCache().get(id);
		}
		if (msUsuariosBk == null) {
			MsUsuarios msUsuarios = msUsuariosDao.getMsUsuarios(id);
			if (msUsuarios != null) {
				msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
			}
		}
		return msUsuariosBk;
	}

	public List<MsUsuariosBk> getAllMsUsuariosActivos(String user) {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getActivasMsUsuarios();
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				if (user != null) {
					setACLMsUsuariosBk(msUsuariosBk, user);
				}
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	public List<MsUsuariosBk> getAllMsUsuariosActivosCero(String user) {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getActivasMsUsuariosCero();
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				if (user != null) {
					setACLMsUsuariosBk(msUsuariosBk, user);
				}
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	private void completarMsUsuarios(MsUsuariosBk msUsuariosBk) {
		// try {
		// if (msUsuariosBk.getIduserCrea() != null &&
		// msUsuariosBk.getIduserCrea().longValue() > 0) {
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msUsuariosBk.getIduserCrea());
		// if (msUsuarios != null)
		// msUsuariosBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (msUsuariosBk.getIduserModif() != null &&
		// msUsuariosBk.getIduserModif().longValue() > 0) {
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msUsuariosBk.getIduserModif());
		// if (msUsuarios != null)
		// msUsuariosBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			if (msUsuariosBk.getIdunidad() != null && msUsuariosBk.getIdunidad().longValue() > 0) {
				MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(msUsuariosBk.getIdunidad());
				if (msUnidadesOrg != null)
					msUsuariosBk.setIdunidadTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msUsuariosBk.getUsername() != null && msUsuariosBk.getUsername().length() > 0) {
				List<String> roles = new ArrayList<String>();
				List<MsRoles> msRolesss = msRolesDao.getXFiltro(msUsuariosBk.getUsername());
				List<String> rolesDesc = new ArrayList<String>();
				for (MsRoles msRoles : msRolesss) {
					roles.add(msRoles.getRol());
					rolesDesc.add(Roles.getDescripcion(msRoles.getRol()));
				}
				msUsuariosBk.setRolesSistema(roles);
				msUsuariosBk.setRolesSistemaDes(rolesDesc);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		// MPINARES 01092023 - INICIO
		try {
			MsUsuarios msUsuarios = msUsuariosDao.getMsUsuarios(msUsuariosBk.getIdusuario());
			if (msUsuarios != null) {
				msUsuariosBk.setNombreCompleto(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		// MPINARES 01092023 - FIN
	}

	private void completarMsUsuariosMin(MsUsuariosBk msUsuariosBk) {
		try {
			if (msUsuariosBk.getIdunidad() != null && msUsuariosBk.getIdunidad().longValue() > 0) {
				MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(msUsuariosBk.getIdunidad());
				if (msUnidadesOrg != null) {
					msUsuariosBk.setIdunidadTxt(msUnidadesOrg.getDescripcion());
					msUsuariosBk.setIdunidadAcroTxt(msUnidadesOrg.getAcronimo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setACLMsUsuariosBk(MsUsuariosBk msUsuariosBk, String user) {
		MsUsuariosACL msUsuariosACL = new MsUsuariosACL();
		if (user != null) {
			MsUsuariosBk msUsuariosBkReg = getMsUsuariosBkXUsername(user);
			if (msUsuariosBkReg != null && msUsuariosBkReg.getRolesSistema() != null) {
				List<String> roles = msUsuariosBkReg.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.ADMINISTRADOR_USUARIOS)) {
					msUsuariosACL.setEsEditable(true);
					msUsuariosACL.setEliminar(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.ADMINISTRADOR_USUARIOS)) {
					msUsuariosACL.setEditopcion(1);
				} else {
					msUsuariosACL.setEditopcion(2);
				}
			} else {
				msUsuariosACL.setEditopcion(2);
			}
		} else {
			msUsuariosACL.setEditopcion(2);
		}
		msUsuariosBk.setMsUsuariosACL(msUsuariosACL);
	}

	public MsUsuariosBk saveorupdateMsUsuariosBk(MsUsuariosBk msUsuariosBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, Long kySedeMod, String rmtaddress, boolean solocontrasenia) throws Validador {

		ValidacionMsUsuariosMng.validarMsUsuariosBk(msUsuariosBk, solocontrasenia);

		if (msUsuariosBk.getContrasenia() != null && msUsuariosBk.getContrasenia().length() >= 6) {
			Encriptar enc = new Encriptar();
			String emcriptado = enc.EncriptarData(msUsuariosBk.getUsername().toLowerCase(),
					msUsuariosBk.getContrasenia());
			msUsuariosBk.setContrasenia(emcriptado);
		}

		List<String> roles = msUsuariosBk.getRolesSistema();
		if (roles == null) {
			throw new Validador(Messages.getStringToKey("msUsuariosRegistro.no_se_asigno_roles"));
		} else if (roles.size() <= 0) {
			throw new Validador(Messages.getStringToKey("msUsuariosRegistro.no_se_asigno_roles"));
		}
		// --------------------------------------------------

		MsUsuarios msUsuarios = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msUsuariosBk.getIdusuario() != null && msUsuariosBk.getIdusuario().longValue() > 0) {
				msUsuarios = msUsuariosDao.getMsUsuarios(msUsuariosBk.getIdusuario());
				boolean cambios = AuditoriaMsUsuariosMng.auditarCambiosMsUsuarios(msUsuariosBk, msUsuarios,
						kyUsuarioMod, user, rmtaddress, nivel);
				if (cambios) {
					msUsuarios.setRtmaddressmodif(rmtaddress);
					msUsuarios.setIduserModif(kyUsuarioMod);
					msUsuarios.setFechaModif(hoy);
					msUsuariosDao.updateMsUsuarios(msUsuarios);
				}
			} else {
				msUsuariosBk.setRtmaddress(rmtaddress);
				msUsuariosBk.setRtmaddressmodif(rmtaddress);
				// msUsuariosBk.setIdsede(kySedeMod);
				// msUsuariosBk.setIdunidad(kyAreaMod);

				msUsuariosBk.setFechaCrea(hoy);
				msUsuariosBk.setIduserCrea(kyUsuarioMod);
				msUsuariosBk.setIduserModif(kyUsuarioMod);
				msUsuariosBk.setFechaModif(hoy);
				msUsuariosBk.setEstado(1);

				msUsuarios = new MsUsuarios();

				FuncionesStaticas.copyPropertiesObject(msUsuarios, msUsuariosBk);
				msUsuariosDao.saveMsUsuarios(msUsuarios);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: " + "CREADO msUsuarios"
								+ " :: " + msUsuarios.getIdusuario().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		// -----------------------ROLES------------------------
		// --ELIMINAR
		List<MsRoles> msRolesss = msRolesDao.getXFiltro(msUsuarios.getUsername());
		for (MsRoles msRoles : msRolesss) {
			if (!roles.contains(msRoles.getRol())) {
				MsRolesBk msRolesBk = new MsRolesBk();
				FuncionesStaticas.copyPropertiesObject(msRolesBk, msRoles);
				msRolesBk.setEstado(0);
				saveorupdateMsRolesBk(msRolesBk, user, kyUsuarioMod, kyAreaMod, kySedeMod, rmtaddress);
			}
		}
		// --
		// --CREAR
		for (String rol : roles) {
			MsRoles msRoles = msRolesDao.getXUserRol(msUsuarios.getUsername(), rol);
			if (msRoles != null) {
				MsRolesBk msRolesBk = new MsRolesBk();
				FuncionesStaticas.copyPropertiesObject(msRolesBk, msRoles);
				if (msRolesBk.getEstado() == null || msRolesBk.getEstado().intValue() != 1) {
					msRolesBk.setEstado(1);
					saveorupdateMsRolesBk(msRolesBk, user, kyUsuarioMod, kyAreaMod, kySedeMod, rmtaddress);
				}
			} else {
				MsRolesBk msRolesBk = new MsRolesBk();
				msRolesBk.setUsername(msUsuarios.getUsername());
				msRolesBk.setRol(rol);
				msRolesBk.setEstado(1);
				saveorupdateMsRolesBk(msRolesBk, user, kyUsuarioMod, kyAreaMod, kySedeMod, rmtaddress);
			}
		}
		// --
		// -----------------------------------------------

		msUsuariosBk = getMsUsuariosBkXid(msUsuarios.getIdusuario(), user);

		msUsuariosBkCache = null;
		msUsuariosBkXUseranameCache = null;
		usuariosquecreanatenciones = null;
		usuariosdearea = null;
		cacheMsUsuariosBkActivos = null;

		return msUsuariosBk;
	}

	public void deleteMsUsuarios(MsUsuariosBk msUsuariosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador {
		try {
			MsUsuarios msUsuarios = null;
			if (msUsuariosBk.getIdusuario() != null && msUsuariosBk.getIdusuario().longValue() > 0) {

				msUsuarios = msUsuariosDao.getMsUsuarios(msUsuariosBk.getIdusuario());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msUsuarios.setIduserModif(kyUsuarioMod);
				msUsuarios.setFechaModif(hoy);
				Integer estadoanterior = msUsuarios.getEstado();
				msUsuarios.setEstado(0);

				msUsuariosDao.updateMsUsuarios(msUsuarios);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msUsuarios" + " :: " + msUsuarios.getIdusuario().toString() + " :: "
								+ estadoanterior + " :: " + "0");

				msUsuariosBkCache = null;
				msUsuariosBkXUseranameCache = null;
				usuariosquecreanatenciones = null;
				usuariosdearea = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public MsUsuariosBk getMsUsuariosBkXUsername(String username) {
		if (username == null)
			return null;
		List<MsUsuarios> msUsuariosss = msUsuariosDao.getByIdMsUsuarios(username);
		MsUsuarios msUsuarios = null;
		if (!msUsuariosss.isEmpty()) {
			if (msUsuariosss.size() > 1) {
				log.info("ERROR EXISTE MÁS DE UN USUARIO CON EL NOMBRE " + username);
			}
			msUsuarios = msUsuariosss.get(0);
		}
		MsUsuariosBk msUsuariosBk = null;
		if (msUsuarios != null) {
			msUsuariosBk = new MsUsuariosBk();
			FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
			completarMsUsuarios(msUsuariosBk);
		}
		return msUsuariosBk;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosXCrea() {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			String roles[] = { Roles.REGISTRADOR };
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getXRoles(roles);
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuariosMin(msUsuariosBk);
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosXUsuariosArea() {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			String roles[] = { Roles.REGISTRADOR, Roles.ASISTENTE };
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getXRoles(roles);
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuariosMin(msUsuariosBk);
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosXPrincipalArea(Long idunidad) {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			String roles[] = { Roles.RESPONSABLE, Roles.ASISTENTE };
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getXRoles(roles, idunidad);
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	/**
	 * MS_ROLES SERVICIO: ROLES DEL SISTEMA, ROLES
	 * 
	 * @author Carlos Aguilar
	 * @version 2.0, 26/11/2020 00:05
	 * 
	 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/
	 *          /Carlos Aguilar Chamochumbi / 26/11/2020 00:05 / Creación de la
	 *          clase /
	 * 
	 */
	public MsRolesBk getMsRolesBkXid(Long id) {
		if (id == null)
			return null;
		MsRoles msRoles = msRolesDao.getMsRoles(id);
		MsRolesBk msRolesBk = null;
		if (msRoles != null) {
			msRolesBk = new MsRolesBk();
			FuncionesStaticas.copyPropertiesObject(msRolesBk, msRoles);
			completarMsRoles(msRolesBk);
		}
		return msRolesBk;
	}

	public List<MsRolesBk> getAllMsRolesActivos() {
		List<MsRolesBk> msRolesBks = new ArrayList<MsRolesBk>();
		try {
			List<MsRoles> msRoless = msRolesDao.getActivasMsRoles();
			for (MsRoles msRoles : msRoless) {
				MsRolesBk msRolesBk = new MsRolesBk();
				FuncionesStaticas.copyPropertiesObject(msRolesBk, msRoles);
				completarMsRoles(msRolesBk);
				msRolesBks.add(msRolesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msRolesBks;
	}

	private void completarMsRoles(MsRolesBk msRolesBk) {
		try {
			if (msRolesBk.getIduserCrea() != null && msRolesBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msRolesBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msRolesBk.getIduserCrea());
				if (msUsuarios != null)
					msRolesBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// if (msUsuarios != null)
				// msRolesBk.setIduserCreaTxt(msUsuarios.getNombres());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msRolesBk.getIduserModif() != null && msRolesBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msRolesBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msRolesBk.getIduserModif());
				if (msUsuarios != null)
					msRolesBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// if (msUsuarios != null)
				// msRolesBk.setIduserModifTxt(msUsuarios.getNombres());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("COMPLETAR msRolesBk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MsRolesBk saveorupdateMsRolesBk(MsRolesBk msRolesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador {

		ValidacionMsRolesMng.validarMsRolesBk(msRolesBk);

		MsRoles msRoles = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msRolesBk.getIdrol() != null && msRolesBk.getIdrol().longValue() > 0) {

				msRoles = msRolesDao.getMsRoles(msRolesBk.getIdrol());

				boolean cambios = AuditoriaMsRolesMng.auditarCambiosMsRoles(msRolesBk, msRoles, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					msRoles.setRtmaddressmodif(rmtaddress);
					msRoles.setIduserModif(kyUsuarioMod);
					msRoles.setFechaModif(hoy);
					msRolesDao.updateMsRoles(msRoles);
				}
			} else {
				msRolesBk.setRtmaddress(rmtaddress);
				msRolesBk.setRtmaddressmodif(rmtaddress);
				// msRolesBk.setIdsede(kySedeMod);
				// msRolesBk.setIdunidad(kyAreaMod);

				msRolesBk.setFechaCrea(hoy);
				msRolesBk.setIduserCrea(kyUsuarioMod);
				msRolesBk.setIduserModif(kyUsuarioMod);
				msRolesBk.setFechaModif(hoy);
				msRolesBk.setEstado(1);

				msRoles = new MsRoles();

				FuncionesStaticas.copyPropertiesObject(msRoles, msRolesBk);
				msRolesDao.saveMsRoles(msRoles);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO msRoles" + " :: " + msRoles.getIdrol().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msRolesBk = getMsRolesBkXid(msRoles.getIdrol());
		return msRolesBk;
	}

	public void deleteMsRoles(MsRolesBk msRolesBk, String user, Long kyUsuarioMod, Long kyAreaMod, Long kySedeMod,
			String rmtaddress) throws Validador {
		try {
			MsRoles msRoles = null;
			if (msRolesBk.getIdrol() != null && msRolesBk.getIdrol().longValue() > 0) {

				msRoles = msRolesDao.getMsRoles(msRolesBk.getIdrol());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msRoles.setIduserModif(kyUsuarioMod);
				msRoles.setFechaModif(hoy);
				Integer estadoanterior = msRoles.getEstado();
				msRoles.setEstado(0);

				msRolesDao.updateMsRoles(msRoles);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: " + "ELIMINADO msRoles"
								+ " :: " + msRoles.getIdrol().toString() + " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	/**
	 * MS_UNIDADES_ORG SERVICIO: UNIDADES ORGÁNICAS
	 * 
	 * @author Carlos Aguilar
	 * @version 2.0, 26/11/2020 00:05
	 * 
	 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/
	 *          /Carlos Aguilar Chamochumbi / 26/11/2020 00:05 / Creación de la
	 *          clase /
	 * 
	 */
	public MsUnidadesOrgBk getMsUnidadesOrgBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(id);
		MsUnidadesOrgBk msUnidadesOrgBk = null;
		if (msUnidadesOrg != null) {
			msUnidadesOrgBk = new MsUnidadesOrgBk();
			FuncionesStaticas.copyPropertiesObject(msUnidadesOrgBk, msUnidadesOrg);
			completarMsUnidadesOrg(msUnidadesOrgBk);
			if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
				setACLMsUnidadesOrgBk(msUnidadesOrgBk, kyUsuarioMod);
		}
		return msUnidadesOrgBk;
	}

	public List<MsUnidadesOrgBk> getAllMsUnidadesOrgActivos(Long kyUsuarioMod) {
		List<MsUnidadesOrgBk> msUnidadesOrgBks = new ArrayList<MsUnidadesOrgBk>();
		try {
			List<MsUnidadesOrg> msUnidadesOrgs = msUnidadesOrgDao.getActivasMsUnidadesOrg();
			for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgs) {
				MsUnidadesOrgBk msUnidadesOrgBk = new MsUnidadesOrgBk();
				FuncionesStaticas.copyPropertiesObject(msUnidadesOrgBk, msUnidadesOrg);
				completarMsUnidadesOrg(msUnidadesOrgBk);
				if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
					setACLMsUnidadesOrgBk(msUnidadesOrgBk, kyUsuarioMod);
				msUnidadesOrgBks.add(msUnidadesOrgBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUnidadesOrgBks;
	}

	@Override
	public List<MsUnidadesOrgBk> getAllMsUnidadesOrgActivosCero(Long kyUsuarioMod) {
		List<MsUnidadesOrgBk> msUnidadesOrgBks = new ArrayList<MsUnidadesOrgBk>();
		try {
			List<MsUnidadesOrg> msUnidadesOrgs = msUnidadesOrgDao.getActivasMsUnidadesOrgCero();
			for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgs) {
				MsUnidadesOrgBk msUnidadesOrgBk = new MsUnidadesOrgBk();
				FuncionesStaticas.copyPropertiesObject(msUnidadesOrgBk, msUnidadesOrg);
				completarMsUnidadesOrg(msUnidadesOrgBk);
				if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
					setACLMsUnidadesOrgBk(msUnidadesOrgBk, kyUsuarioMod);
				msUnidadesOrgBks.add(msUnidadesOrgBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUnidadesOrgBks;
	}

	private void completarMsUnidadesOrg(MsUnidadesOrgBk msUnidadesOrgBk) {
		try {
			if (msUnidadesOrgBk.getIduserCrea() != null && msUnidadesOrgBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msUnidadesOrgBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msUnidadesOrgBk.getIduserCrea());
				if (msUsuarios != null)
					msUnidadesOrgBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// if (msUsuarios != null)
				// msUnidadesOrgBk.setIduserCreaTxt(msUsuarios.getNombres());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msUnidadesOrgBk.getIduserModif() != null && msUnidadesOrgBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msUnidadesOrgBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msUnidadesOrgBk.getIduserModif());
				if (msUsuarios != null)
					msUnidadesOrgBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// if (msUsuarios != null)
				// msUnidadesOrgBk.setIduserModifTxt(msUsuarios.getNombres());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msUnidadesOrgBk.getIdpadre() != null && msUnidadesOrgBk.getIdpadre().longValue() > 0) {
				MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(msUnidadesOrgBk.getIdpadre());
				if (msUnidadesOrg != null)
					msUnidadesOrgBk.setIdpadreTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msUnidadesOrgBk.getIdunidad() != null && msUnidadesOrgBk.getIdunidad().longValue() > 0) {
				MsUnidadesOrg msUnidadesOrg = getUnidadGeneral(msUnidadesOrgBk.getIdunidad());
				if (msUnidadesOrg != null) {
					msUnidadesOrgBk.setIdsppadre(msUnidadesOrg.getIdunidad());
					msUnidadesOrgBk.setIdsppadreTxt(msUnidadesOrg.getDescripcion());
					msUnidadesOrgBk.setIdsppadreTxtAcro(msUnidadesOrg.getAcronimo());// MPINARES
																						// 27042022
																						// -
																						// INICIO
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MsUnidadesOrg getUnidadGeneral(Long idunidad) {
		MsUnidadesOrg unidaSel = msUnidadesOrgDao.getMsUnidadesOrg(idunidad);
		if (unidaSel.getFlagofgeneral() != null && unidaSel.getFlagofgeneral().intValue() == 1) {
			return unidaSel;
		} else if (unidaSel.getIdpadre() != null && unidaSel.getIdpadre().longValue() > 0) {
			return getUnidadGeneral(unidaSel.getIdpadre());
		}
		return null;
	}

	public void setACLMsUnidadesOrgBk(MsUnidadesOrgBk msUnidadesOrgBk, Long kyUsuarioMod) {
		MsUnidadesOrgACL msUnidadesOrgACL = new MsUnidadesOrgACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUNIDADESORG_CREA)) {
					msUnidadesOrgACL.setEsEditable(true);
					msUnidadesOrgACL.setEliminar(true);
				} else if (roles.contains(Roles.MSUNIDADESORG_VE)) {
					msUnidadesOrgACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUNIDADESORG_CREA)) {
					msUnidadesOrgACL.setEditopcion(1);
				} else {
					msUnidadesOrgACL.setEditopcion(2);
				}
			} else {
				msUnidadesOrgACL.setEditopcion(2);
			}
		} else {
			msUnidadesOrgACL.setEditopcion(2);
		}
		msUnidadesOrgBk.setMsUnidadesOrgACL(msUnidadesOrgACL);
	}

	public MsUnidadesOrgBk saveorupdateMsUnidadesOrgBk(MsUnidadesOrgBk msUnidadesOrgBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, Long kySedeMod, String rmtaddress) throws Validador {

		ValidacionMsUnidadesOrgMng.validarMsUnidadesOrgBk(msUnidadesOrgBk);

		MsUnidadesOrg msUnidadesOrg = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msUnidadesOrgBk.getIdunidad() != null && msUnidadesOrgBk.getIdunidad().longValue() > 0) {

				msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(msUnidadesOrgBk.getIdunidad());

				boolean cambios = AuditoriaMsUnidadesOrgMng.auditarCambiosMsUnidadesOrg(msUnidadesOrgBk, msUnidadesOrg,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					msUnidadesOrg.setRmtaddressrst(rmtaddress);
					msUnidadesOrg.setIduserModif(kyUsuarioMod);
					msUnidadesOrg.setFechaModif(hoy);
					msUnidadesOrgDao.updateMsUnidadesOrg(msUnidadesOrg);
				}
			} else {
				msUnidadesOrgBk.setRmtaddress(rmtaddress);
				msUnidadesOrgBk.setRmtaddressrst(rmtaddress);
				// msUnidadesOrgBk.setIdsede(kySedeMod);
				// msUnidadesOrgBk.setIdunidad(kyAreaMod);

				msUnidadesOrgBk.setFechaCrea(hoy);
				msUnidadesOrgBk.setIduserCrea(kyUsuarioMod);
				msUnidadesOrgBk.setIduserModif(kyUsuarioMod);
				msUnidadesOrgBk.setFechaModif(hoy);
				msUnidadesOrgBk.setEstado(1);

				msUnidadesOrg = new MsUnidadesOrg();

				FuncionesStaticas.copyPropertiesObject(msUnidadesOrg, msUnidadesOrgBk);
				msUnidadesOrgDao.saveMsUnidadesOrg(msUnidadesOrg);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO msUnidadesOrg" + " :: " + msUnidadesOrg.getIdunidad().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
		msUnidadesOrgBk = getMsUnidadesOrgBkXid(msUnidadesOrg.getIdunidad(), kyUsuarioMod);

		msUnidadesOrgBkCache = null;
		uoTreeCache = null;
		uoListaCache = null;

		return msUnidadesOrgBk;
	}

	public void deleteMsUnidadesOrg(MsUnidadesOrgBk msUnidadesOrgBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador {
		try {
			MsUnidadesOrg msUnidadesOrg = null;
			if (msUnidadesOrgBk.getIdunidad() != null && msUnidadesOrgBk.getIdunidad().longValue() > 0) {

				msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(msUnidadesOrgBk.getIdunidad());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msUnidadesOrg.setIduserModif(kyUsuarioMod);
				msUnidadesOrg.setFechaModif(hoy);
				Integer estadoanterior = msUnidadesOrg.getEstado();
				msUnidadesOrg.setEstado(0);

				msUnidadesOrgDao.updateMsUnidadesOrg(msUnidadesOrg);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msUnidadesOrg" + " :: " + msUnidadesOrg.getIdunidad().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	/**
	 * PRT_PARAMETROS SERVICIO: ALMACENA LOS PARÁMETROS REGISTRADOS EN EL
	 * SISTEMA PARÁMETROS
	 * 
	 * @author Carlos Aguilar
	 * @version 2.0, 26/11/2020 00:05
	 * 
	 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/
	 *          /Carlos Aguilar Chamochumbi / 26/11/2020 00:05 / Creación de la
	 *          clase /
	 * 
	 */
	public PrtParametrosBk getPrtParametrosBkXid(Long id, Long kyUsuarioMod, String key) {
		if (id == null)
			return null;
		PrtParametros prtParametros = prtParametrosDao.getPrtParametros(id);
		PrtParametrosBk prtParametrosBk = null;
		if (prtParametros != null) {
			prtParametrosBk = new PrtParametrosBk();
			FuncionesStaticas.copyPropertiesObject(prtParametrosBk, prtParametros);
			completarPrtParametros(prtParametrosBk, kyUsuarioMod, key);
		}
		return prtParametrosBk;
	}

	public List<PrtParametrosBk> getAllPrtParametrosActivos(Long kyUsuarioMod, String key) {
		List<PrtParametrosBk> prtParametrosBks = new ArrayList<PrtParametrosBk>();
		try {
			List<PrtParametros> prtParametross = null;
			if (key != null) {
				prtParametross = prtParametrosDao.getXDescripcion(key);
			} else {
				prtParametross = prtParametrosDao.getActivasPrtParametros();
			}
			if (prtParametross != null) {
				for (PrtParametros prtParametros : prtParametross) {
					PrtParametrosBk prtParametrosBk = new PrtParametrosBk();
					FuncionesStaticas.copyPropertiesObject(prtParametrosBk, prtParametros);
					completarPrtParametros(prtParametrosBk, kyUsuarioMod, key);
					prtParametrosBks.add(prtParametrosBk);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<PrtParametrosBk> getAllPrtParametrosActivosCero(Long kyUsuarioMod, String key) {
		List<PrtParametrosBk> prtParametrosBks = new ArrayList<PrtParametrosBk>();
		try {
			List<PrtParametros> prtParametross = null;
			if (key != null) {
				prtParametross = prtParametrosDao.getXDescripcionCero(key);
			} else {
				prtParametross = prtParametrosDao.getActivasPrtParametrosCero();
			}
			if (prtParametross != null) {
				for (PrtParametros prtParametros : prtParametross) {
					PrtParametrosBk prtParametrosBk = new PrtParametrosBk();
					FuncionesStaticas.copyPropertiesObject(prtParametrosBk, prtParametros);
					completarPrtParametros(prtParametrosBk, kyUsuarioMod, key);
					prtParametrosBks.add(prtParametrosBk);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<PrtParametrosBk> getPrtParametrosHijosForKey(Long kyUsuarioMod, String key) {
		List<PrtParametrosBk> prtParametrosBks = new ArrayList<PrtParametrosBk>();
		try {
			List<PrtParametros> prtParametross = null;
			if (key != null) {
				prtParametross = prtParametrosDao.getXDescripcionHijos(key);
			} else {
				prtParametross = prtParametrosDao.getActivasPrtParametrosCero();
			}
			if (prtParametross != null) {
				for (PrtParametros prtParametros : prtParametross) {
					PrtParametrosBk prtParametrosBk = new PrtParametrosBk();
					FuncionesStaticas.copyPropertiesObject(prtParametrosBk, prtParametros);
					completarPrtParametros(prtParametrosBk, kyUsuarioMod, key);
					prtParametrosBks.add(prtParametrosBk);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	private void completarPrtParametros(PrtParametrosBk prtParametrosBk, Long kyUsuarioMod, String key) {
		try {
			if (prtParametrosBk.getIdpadre() != null && prtParametrosBk.getIdpadre().longValue() > 0) {
				PrtParametros prtParametros = prtParametrosDao.getPrtParametros(prtParametrosBk.getIdpadre());
				if (prtParametros != null)
					prtParametrosBk.setIdpadreTxt(prtParametros.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// if (prtParametrosBk.getIduserCrea() != null &&
		// prtParametrosBk.getIduserCrea().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(prtParametrosBk.getIduserCrea());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(prtParametrosBk.getIduserCrea());
		// if (msUsuarios != null)
		// prtParametrosBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// // if (msUsuarios != null)
		// // prtParametrosBk.setIduserCreaTxt(msUsuarios.getNombres());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (prtParametrosBk.getIduserModif() != null &&
		// prtParametrosBk.getIduserModif().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(prtParametrosBk.getIduserModif());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(prtParametrosBk.getIduserModif());
		// if (msUsuarios != null)
		// prtParametrosBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// // if (msUsuarios != null)
		// // prtParametrosBk.setIduserModifTxt(msUsuarios.getNombres());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLPrtParametrosBk(prtParametrosBk, kyUsuarioMod, key);
		// try {
		// System.out.println("COMPLETAR prtParametrosBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public void setACLPrtParametrosBk(PrtParametrosBk prtParametrosBk, Long kyUsuarioMod, String key) {
		PrtParametrosACL prtParametrosACL = new PrtParametrosACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.PRTPARAMETROS_CREA)) {
					prtParametrosACL.setEsEditable(true);
					prtParametrosACL.setEliminar(true);
				} else if (roles.contains(Roles.PRTPARAMETROS_VE)) {
					prtParametrosACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.PRTPARAMETROS_CREA)) {
					if (key == null)
						prtParametrosACL.setEditopcion(1);
					else
						prtParametrosACL.setEditopcion(3);
				} else {
					prtParametrosACL.setEditopcion(2);
				}
			} else {
				prtParametrosACL.setEditopcion(2);
			}
		} else {
			prtParametrosACL.setEditopcion(2);
		}

		prtParametrosBk.setPrtParametrosACL(prtParametrosACL);
	}

	public PrtParametrosBk saveorupdatePrtParametrosBk(PrtParametrosBk prtParametrosBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String kySedeMod, String rmtaddress, String key) throws Validador {

		ValidacionPrtParametrosMng.validarPrtParametrosBk(prtParametrosBk);

		PrtParametros prtParametros = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (prtParametrosBk.getIdparametro() != null && prtParametrosBk.getIdparametro().longValue() > 0) {

				prtParametros = prtParametrosDao.getPrtParametros(prtParametrosBk.getIdparametro());

				boolean cambios = AuditoriaPrtParametrosMng.auditarCambiosPrtParametros(prtParametrosBk, prtParametros,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					prtParametros.setRtmaddressmodif(rmtaddress);
					prtParametros.setIduserModif(kyUsuarioMod);
					prtParametros.setFechaModif(hoy);
					prtParametrosDao.updatePrtParametros(prtParametros);
				}
			} else {
				prtParametrosBk.setRtmaddress(rmtaddress);
				prtParametrosBk.setRtmaddressmodif(rmtaddress);
				// prtParametrosBk.setIdsede(kySedeMod);
				// prtParametrosBk.setIdunidad(kyAreaMod);

				prtParametrosBk.setFechaCrea(hoy);
				prtParametrosBk.setIduserCrea(kyUsuarioMod);
				prtParametrosBk.setIduserModif(kyUsuarioMod);
				prtParametrosBk.setFechaModif(hoy);
				prtParametrosBk.setEstado(1);

				prtParametros = new PrtParametros();

				FuncionesStaticas.copyPropertiesObject(prtParametros, prtParametrosBk);
				prtParametrosDao.savePrtParametros(prtParametros);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO prtParametros" + " :: " + prtParametros.getIdparametro().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		prtParametrosBk = getPrtParametrosBkXid(prtParametros.getIdparametro(), kyUsuarioMod, key);

		sedesCache = null;
		estadosdeflujo = null;
		tiemposestados = null;
		estadosdeatencion = null;
		endpointVentanilla = null;
		return prtParametrosBk;
	}

	public void deletePrtParametros(PrtParametrosBk prtParametrosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador {
		try {
			PrtParametros prtParametros = null;
			if (prtParametrosBk.getIdparametro() != null && prtParametrosBk.getIdparametro().longValue() > 0) {

				prtParametros = prtParametrosDao.getPrtParametros(prtParametrosBk.getIdparametro());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				prtParametros.setIduserModif(kyUsuarioMod);
				prtParametros.setFechaModif(hoy);
				Integer estadoanterior = prtParametros.getEstado();
				prtParametros.setEstado(0);

				prtParametrosDao.updatePrtParametros(prtParametros);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO prtParametros" + " :: " + prtParametros.getIdparametro().toString()
								+ " :: " + estadoanterior + " :: " + "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<String> getListaSedes() {
		if (sedesCache == null) {
			sedesCache = new ArrayList<String>();
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("SEDES");
			for (PrtParametros prtParametro : prtParametross) {
				sedesCache.add(prtParametro.getDescripcion());
			}
		}
		return sedesCache;
	}

	@Override
	public PrtParametrosBk getPrtParametrosBkXDesc(String descripcion, Long kyUsuarioMod) {
		PrtParametros prtParametros = prtParametrosDao.getDescripcion(descripcion);
		PrtParametrosBk prtParametrosBk = null;
		if (prtParametros != null) {
			prtParametrosBk = new PrtParametrosBk();
			FuncionesStaticas.copyPropertiesObject(prtParametrosBk, prtParametros);
			completarPrtParametros(prtParametrosBk, kyUsuarioMod, descripcion);
		}
		return prtParametrosBk;
	}

	@Override
	public List<NodosDto> getUOTreeCache() {
		if (uoTreeCache == null) {
			List<MsUnidadesOrg> msUnidadesOrgsss = msUnidadesOrgDao.getByPadresMsUnidadesOrg();
			uoTreeCache = new ArrayList<NodosDto>();
			for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgsss) {
				NodosDto nodosDto = new NodosDto();
				nodosDto.setName(msUnidadesOrg.getCodigo() + " " + msUnidadesOrg.getDescripcion());
				nodosDto.setId(msUnidadesOrg.getIdunidad());
				List<NodosDto> children = getUOTreeChildren(msUnidadesOrg.getIdunidad());
				nodosDto.setChildren(children);
				uoTreeCache.add(nodosDto);
			}
		}
		return uoTreeCache;
	}

	private List<NodosDto> getUOTreeChildren(Long idPadre) {
		List<MsUnidadesOrg> msUnidadesOrgsss = msUnidadesOrgDao.getByIdPadreMsUnidadesOrg(idPadre);
		List<NodosDto> nodosDtosss = new ArrayList<NodosDto>();
		for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgsss) {
			NodosDto nodosDto = new NodosDto();
			nodosDto.setName(msUnidadesOrg.getCodigo() + " " + msUnidadesOrg.getDescripcion());
			nodosDto.setId(msUnidadesOrg.getIdunidad());
			List<NodosDto> children = getUOTreeChildren(msUnidadesOrg.getIdunidad());
			nodosDto.setChildren(children);
			nodosDtosss.add(nodosDto);
		}
		return nodosDtosss;
	}

	@Override
	public List<MsUnidadesOrgDto> getUOListaCache() {
		if (uoListaCache == null) {
			List<MsUnidadesOrg> msUnidadesOrgsss = msUnidadesOrgDao.getActivasMsUnidadesOrg();
			uoListaCache = new ArrayList<MsUnidadesOrgDto>();
			for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgsss) {
				MsUnidadesOrgDto msUnidadesOrgDto = new MsUnidadesOrgDto();
				FuncionesStaticas.copyPropertiesObject(msUnidadesOrgDto, msUnidadesOrg);
				uoListaCache.add(msUnidadesOrgDto);
			}
		}
		return uoListaCache;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosXFiltro(String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, Long iduserModif, Integer estado) {

		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			List<MsUsuarios> msUsuariossss = msUsuariosDao.getXFiltro(username, nombres, apellidoPaterno,
					apellidoMaterno, iduserModif, estado);
			for (MsUsuarios msUsuarios : msUsuariossss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosXFiltro(String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, Long iduserModif, Integer estado, int inicial, int MAX) {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			List<MsUsuarios> msUsuariossss = msUsuariosDao.getXFiltro(username, nombres, apellidoPaterno,
					apellidoMaterno, iduserModif, estado, inicial, MAX);
			for (MsUsuarios msUsuarios : msUsuariossss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				msUsuariosBks.add(msUsuariosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	@Override
	public Long getMsUsuariosTotalXFiltro(String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, Long iduserModif, Integer estado) {
		try {
			Long totalMsUsuariossss = msUsuariosDao.getTotalXFiltro(username, nombres, apellidoPaterno, apellidoMaterno,
					iduserModif, estado);

			return totalMsUsuariossss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CacheMsUsuariosBk getCacheMsUsuariosBkActivos() {
		if (cacheMsUsuariosBkActivos == null) {
			List<MsUsuariosBk> msUsuariosBksss = getAllMsUsuariosActivos(null);
			cacheMsUsuariosBkActivos = new CacheMsUsuariosBk(msUsuariosBksss.size() + 10, this);
			cacheMsUsuariosBkActivos.setTotalCargar(msUsuariosBksss.size());
			for (MsUsuariosBk msUsuariosBk : msUsuariosBksss) {
				cacheMsUsuariosBkActivos.put(msUsuariosBk.getIdusuario(), msUsuariosBk, 60);
			}
		}
		return cacheMsUsuariosBkActivos;
	}

	@Override
	public void setCacheMsUsuariosBkActivos(CacheMsUsuariosBk cacheMsUsuariosBk) {
		this.cacheMsUsuariosBkActivos = cacheMsUsuariosBk;
	}

	@Override
	public List<MsUsuariosBk> getMsUsuariosBkActivos(String user) {
		List<MsUsuariosBk> msUsuariosBks = new ArrayList<MsUsuariosBk>();
		try {
			List<MsUsuariosBk> msUsuarioss = getCacheMsUsuariosBkActivos().getAll();
			for (MsUsuariosBk msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk(msUsuarios);
				setACLMsUsuariosBk(msUsuariosBk, user);
				msUsuariosBks.add(msUsuariosBk);
				// msUsuarios.setApellidoMaterno("XXXXXX");
				// System.out.println("APELLIDOS Materno
				// "+msUsuarios.getApellidoMaterno()+" ==
				// "+msUsuariosBk.getApellidoMaterno());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUsuariosBks;
	}

	public Map<Long, MsUsuariosBk> getMsUsuariosBkCache() {
		if (msUsuariosBkCache == null) {
			msUsuariosBkCache = new HashMap<Long, MsUsuariosBk>();
			List<MsUsuarios> msUsuarioss = msUsuariosDao.getActivasMsUsuarios();
			for (MsUsuarios msUsuarios : msUsuarioss) {
				MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
				FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
				completarMsUsuarios(msUsuariosBk);
				msUsuariosBkCache.put(msUsuariosBk.getIdusuario(), msUsuariosBk);
			}
		}
		return msUsuariosBkCache;
	}

	public void setMsUsuariosBkCache(Map<Long, MsUsuariosBk> msUsuariosBkCache) {
		this.msUsuariosBkCache = msUsuariosBkCache;
	}

	public Map<String, MsUsuariosBk> getMsUsuariosBkXUseranameCache() {
		if (msUsuariosBkXUseranameCache == null) {
			msUsuariosBkXUseranameCache = new HashMap<String, MsUsuariosBk>();
			List<MsUsuariosBk> msUsuariosBksss = getAllMsUsuariosActivos(null);
			for (MsUsuariosBk msUsuariosBk : msUsuariosBksss) {
				msUsuariosBkXUseranameCache.put(msUsuariosBk.getUsername(), msUsuariosBk);
			}
		}
		return msUsuariosBkXUseranameCache;
	}

	public void setMsUsuariosBkXUseranameCache(Map<String, MsUsuariosBk> msUsuariosBkXUseranameCache) {
		this.msUsuariosBkXUseranameCache = msUsuariosBkXUseranameCache;
	}

	/**
	 * MS_INSTITUCIONES SERVICIO: INSTITUCIONES Y ENTIDADES
	 */
	public MsInstitucionesBk getMsInstitucionesBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsInstituciones msInstituciones = msInstitucionesDao.getMsInstituciones(id);
		MsInstitucionesBk msInstitucionesBk = null;
		if (msInstituciones != null) {
			msInstitucionesBk = new MsInstitucionesBk();
			FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
			completarMsInstituciones(msInstitucionesBk, kyUsuarioMod);
		}
		return msInstitucionesBk;
	}

	public List<MsInstitucionesBk> getAllMsInstitucionesActivos(Long kyUsuarioMod) {
		List<MsInstitucionesBk> msInstitucionesBks = new ArrayList<MsInstitucionesBk>();
		try {
			List<MsInstituciones> msInstitucioness = msInstitucionesDao.getActivasMsInstituciones();
			for (MsInstituciones msInstituciones : msInstitucioness) {
				MsInstitucionesBk msInstitucionesBk = new MsInstitucionesBk();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
				completarMsInstituciones(msInstitucionesBk, kyUsuarioMod);
				msInstitucionesBks.add(msInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msInstitucionesBks;
	}

	@Override
	public List<MsInstitucionesBk> getAllMsInstitucionesActivosCero(Long kyUsuarioMod) {
		List<MsInstitucionesBk> msInstitucionesBks = new ArrayList<MsInstitucionesBk>();
		try {
			List<MsInstituciones> msInstitucioness = msInstitucionesDao.getActivasMsInstitucionesCero();
			for (MsInstituciones msInstituciones : msInstitucioness) {
				MsInstitucionesBk msInstitucionesBk = new MsInstitucionesBk();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
				completarMsInstituciones(msInstitucionesBk, kyUsuarioMod);
				msInstitucionesBks.add(msInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msInstitucionesBks;
	}

	private void completarMsInstituciones(MsInstitucionesBk msInstitucionesBk, Long kyUsuarioMod) {

		try {
			if (msInstitucionesBk.getCodpais() != null && msInstitucionesBk.getCodpais().longValue() > 0) {
				MsPaises msPaises = msPaisesDao.getMsPaises(msInstitucionesBk.getCodpais());
				if (msPaises != null)
					msInstitucionesBk.setCodpaisTxt(msPaises.getPaiNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = 0;
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCoddptoTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0
					&& msInstitucionesBk.getCodprov() != null && msInstitucionesBk.getCodprov().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = msInstitucionesBk.getCodprov().intValue();
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCodprovTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0
					&& msInstitucionesBk.getCodprov() != null && msInstitucionesBk.getCodprov().intValue() > 0
					&& msInstitucionesBk.getCoddist() != null && msInstitucionesBk.getCoddist().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = msInstitucionesBk.getCodprov().intValue();
				int iiCoddist = msInstitucionesBk.getCoddist().intValue();
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCoddistTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msInstitucionesBk.getIduserCrea() != null && msInstitucionesBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msInstitucionesBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msInstitucionesBk.getIduserCrea());
				if (msUsuarios != null)
					msInstitucionesBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msInstitucionesBk.getIduserModif() != null && msInstitucionesBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msInstitucionesBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msInstitucionesBk.getIduserModif());
				if (msUsuarios != null)
					msInstitucionesBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLMsInstitucionesBk(msInstitucionesBk, kyUsuarioMod);

		// try {
		// System.out.println("COMPLETAR msInstitucionesBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public MsInstitucionesBk saveorupdateMsInstitucionesBk(MsInstitucionesBk msInstitucionesBk, String user,
			Long kyUsuarioMod, Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionMsInstitucionesMng.validarMsInstitucionesBk(msInstitucionesBk, getxDefectoCodpais());

		MsInstituciones msInstituciones = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msInstitucionesBk.getIdprovee() != null && msInstitucionesBk.getIdprovee().longValue() > 0) {

				msInstituciones = msInstitucionesDao.getMsInstituciones(msInstitucionesBk.getIdprovee());

				boolean cambios = AuditoriaMsInstitucionesMng.auditarCambiosMsInstituciones(msInstitucionesBk,
						msInstituciones, kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					msInstituciones.setRmtaddressrst(rmtaddress);
					msInstituciones.setEstado(1);
					msInstituciones.setIduserModif(kyUsuarioMod);
					msInstituciones.setFechaModif(hoy);
					msInstitucionesDao.updateMsInstituciones(msInstituciones);
				}
			} else {
				msInstitucionesBk.setRmtaddress(rmtaddress);
				msInstitucionesBk.setRmtaddressrst(rmtaddress);
				// msInstitucionesBk.setSede(kySedeMod);
				// msInstitucionesBk.setIdunidad(kyAreaMod);

				msInstitucionesBk.setFechaCrea(hoy);
				msInstitucionesBk.setIduserCrea(kyUsuarioMod);
				msInstitucionesBk.setIduserModif(kyUsuarioMod);
				msInstitucionesBk.setFechaModif(hoy);
				msInstitucionesBk.setEstado(1);

				msInstituciones = new MsInstituciones();

				FuncionesStaticas.copyPropertiesObject(msInstituciones, msInstitucionesBk);
				msInstitucionesDao.saveMsInstituciones(msInstituciones);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO msInstituciones" + " :: " + msInstituciones.getIdprovee().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msInstitucionesBk = getMsInstitucionesBkXid(msInstituciones.getIdprovee(), kyUsuarioMod);
		return msInstitucionesBk;
	}

	public void deleteMsInstituciones(MsInstitucionesBk msInstitucionesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {
		try {
			MsInstituciones msInstituciones = null;
			if (msInstitucionesBk.getIdprovee() != null && msInstitucionesBk.getIdprovee().longValue() > 0) {

				msInstituciones = msInstitucionesDao.getMsInstituciones(msInstitucionesBk.getIdprovee());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msInstituciones.setIduserModif(kyUsuarioMod);
				msInstituciones.setFechaModif(hoy);
				Integer estadoanterior = msInstituciones.getEstado();
				msInstituciones.setEstado(0);

				msInstitucionesDao.updateMsInstituciones(msInstituciones);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msInstituciones" + " :: " + msInstituciones.getIdprovee().toString()
								+ " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsInstitucionesBk> getMsInstitucionesXFiltro(Long idprovee, String razonSocial, String siglas,
			String ruc, String telefono, String direccion, Integer estado, Long kyUsuarioMod) {
		List<MsInstitucionesBk> MsInstitucionesBks = new ArrayList<MsInstitucionesBk>();
		try {
			List<MsInstituciones> MsInstitucionessss = msInstitucionesDao.getXFiltro(idprovee, razonSocial, siglas, ruc,
					telefono, direccion, estado);
			for (MsInstituciones MsInstituciones : MsInstitucionessss) {
				MsInstitucionesBk MsInstitucionesBk = new MsInstitucionesBk();
				FuncionesStaticas.copyPropertiesObject(MsInstitucionesBk, MsInstituciones);
				completarMsInstituciones(MsInstitucionesBk, kyUsuarioMod);
				MsInstitucionesBks.add(MsInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsInstitucionesBks;
	}

	@Override
	public List<MsInstitucionesBk> getMsInstitucionesXFiltro(Long idprovee, String razonSocial, String siglas,
			String ruc, String telefono, String direccion, Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<MsInstitucionesBk> MsInstitucionesBks = new ArrayList<MsInstitucionesBk>();
		try {
			List<MsInstituciones> MsInstitucionessss = msInstitucionesDao.getXFiltro(idprovee, razonSocial, siglas, ruc,
					telefono, direccion, estado, inicial, MAX);
			for (MsInstituciones MsInstituciones : MsInstitucionessss) {
				MsInstitucionesBk MsInstitucionesBk = new MsInstitucionesBk();
				FuncionesStaticas.copyPropertiesObject(MsInstitucionesBk, MsInstituciones);
				completarMsInstituciones(MsInstitucionesBk, kyUsuarioMod);
				MsInstitucionesBks.add(MsInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsInstitucionesBks;
	}

	@Override
	public Long getMsInstitucionesTotalXFiltro(Long idprovee, String razonSocial, String siglas, String ruc,
			String telefono, String direccion, Integer estado) {
		try {
			Long totalMsInstitucionessss = msInstitucionesDao.getTotalXFiltro(idprovee, razonSocial, siglas, ruc,
					telefono, direccion, estado);

			return totalMsInstitucionessss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setACLMsInstitucionesBk(MsInstitucionesBk msInstitucionesBk, Long kyUsuarioMod) {
		MsInstitucionesACL msInstitucionesACL = new MsInstitucionesACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.REGISTRADOR)) {
					msInstitucionesACL.setEsEditable(true);
					msInstitucionesACL.setEliminar(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.REGISTRADOR)) {
					msInstitucionesACL.setEditopcion(1);
				} else {
					msInstitucionesACL.setEditopcion(2);
				}
			} else {
				msInstitucionesACL.setEditopcion(2);
			}
		} else {
			msInstitucionesACL.setEditopcion(2);
		}
		msInstitucionesBk.setMsInstitucionesACL(msInstitucionesACL);
	}

	/**
	 * MS_UBIGEO SERVICIO: UBIGEO DATOS OTORGADOS POR EL INEI
	 */
	public MsUbigeoBk getMsUbigeoBkXid(MsUbigeoId id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(id);
		MsUbigeoBk msUbigeoBk = null;
		if (msUbigeo != null) {
			msUbigeoBk = new MsUbigeoBk();
			FuncionesStaticas.copyPropertiesObject(msUbigeoBk, msUbigeo);
			completarMsUbigeo(msUbigeoBk, kyUsuarioMod);
		}
		return msUbigeoBk;
	}

	public List<MsUbigeoBk> getAllMsUbigeoActivos(Long kyUsuarioMod) {
		List<MsUbigeoBk> msUbigeoBks = new ArrayList<MsUbigeoBk>();
		try {
			List<MsUbigeo> msUbigeos = msUbigeoDao.getActivasMsUbigeo();
			for (MsUbigeo msUbigeo : msUbigeos) {
				MsUbigeoBk msUbigeoBk = new MsUbigeoBk();
				FuncionesStaticas.copyPropertiesObject(msUbigeoBk, msUbigeo);
				completarMsUbigeo(msUbigeoBk, kyUsuarioMod);
				msUbigeoBks.add(msUbigeoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUbigeoBks;
	}

	@Override
	public List<MsUbigeoBk> getAllMsUbigeoActivosCero(Long kyUsuarioMod) {
		List<MsUbigeoBk> msUbigeoBks = new ArrayList<MsUbigeoBk>();
		try {
			List<MsUbigeo> msUbigeos = msUbigeoDao.getActivasMsUbigeoCero();
			for (MsUbigeo msUbigeo : msUbigeos) {
				MsUbigeoBk msUbigeoBk = new MsUbigeoBk();
				FuncionesStaticas.copyPropertiesObject(msUbigeoBk, msUbigeo);
				completarMsUbigeo(msUbigeoBk, kyUsuarioMod);
				msUbigeoBks.add(msUbigeoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUbigeoBks;
	}

	private void completarMsUbigeo(MsUbigeoBk msUbigeoBk, Long kyUsuarioMod) {
		try {
			if (msUbigeoBk.getIduserCrea() != null && msUbigeoBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msUbigeoBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msUbigeoBk.getIduserCrea());
				if (msUsuarios != null)
					msUbigeoBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msUbigeoBk.getIduserModif() != null && msUbigeoBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msUbigeoBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msUbigeoBk.getIduserModif());
				if (msUsuarios != null)
					msUbigeoBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLMsUbigeoBk(msUbigeoBk, kyUsuarioMod);

		try {
			if (msUbigeoBk.getId().getCoddpto() != null && msUbigeoBk.getId().getCoddpto().intValue() > 0) {
				int iiCoddpto = msUbigeoBk.getId().getCoddpto().intValue();
				int iiCodprov = 0;
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msUbigeoBk.setCoddptoTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msUbigeoBk.getId().getCoddpto() != null && msUbigeoBk.getId().getCoddpto().intValue() > 0
					&& msUbigeoBk.getId().getCodprov() != null && msUbigeoBk.getId().getCodprov().intValue() > 0) {
				int iiCoddpto = msUbigeoBk.getId().getCoddpto().intValue();
				int iiCodprov = msUbigeoBk.getId().getCodprov().intValue();
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msUbigeoBk.setCodprovTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msUbigeoBk.getId().getCoddpto() != null && msUbigeoBk.getId().getCoddpto().intValue() > 0
					&& msUbigeoBk.getId().getCodprov() != null && msUbigeoBk.getId().getCodprov().intValue() > 0
					&& msUbigeoBk.getId().getCoddist() != null && msUbigeoBk.getId().getCoddist().intValue() > 0) {
				int iiCoddpto = msUbigeoBk.getId().getCoddpto().intValue();
				int iiCodprov = msUbigeoBk.getId().getCodprov().intValue();
				int iiCoddist = msUbigeoBk.getId().getCoddist().intValue();
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msUbigeoBk.setCoddistTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// System.out.println("COMPLETAR msUbigeoBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public MsUbigeoBk saveorupdateMsUbigeoBk(MsUbigeoBk msUbigeoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {

		ValidacionMsUbigeoMng.validarMsUbigeoBk(msUbigeoBk);

		MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoBk.getId());
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msUbigeo != null) {

				msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoBk.getId());

				boolean cambios = AuditoriaMsUbigeoMng.auditarCambiosMsUbigeo(msUbigeoBk, msUbigeo, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					// msUbigeo.setRtmaddressmodif(rmtaddress);
					msUbigeo.setIduserModif(kyUsuarioMod);
					msUbigeo.setFechaModif(hoy);
					msUbigeoDao.updateMsUbigeo(msUbigeo);
				}
			} else {
				// msUbigeoBk.setRtmaddress(rmtaddress);
				// msUbigeoBk.setRtmaddressmodif(rmtaddress);
				// msUbigeoBk.setSede(kySedeMod);
				// msUbigeoBk.setIdunidad(kyAreaMod);

				msUbigeoBk.setFechaCrea(hoy);
				msUbigeoBk.setIduserCrea(kyUsuarioMod);
				msUbigeoBk.setIduserModif(kyUsuarioMod);
				msUbigeoBk.setFechaModif(hoy);
				msUbigeoBk.setEstado(1);

				msUbigeo = new MsUbigeo();

				FuncionesStaticas.copyPropertiesObject(msUbigeo, msUbigeoBk);
				msUbigeoDao.saveMsUbigeo(msUbigeo);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO msUbigeo" + " :: " + msUbigeo.getId().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		xDefectoCodpais = null;
		xDefectoCoddpto = null;
		xDefectoCodprov = null;
		xDefectoCoddist = null;

		msUbigeoBk = getMsUbigeoBkXid(msUbigeo.getId(), kyUsuarioMod);
		return msUbigeoBk;
	}

	public void deleteMsUbigeo(MsUbigeoBk msUbigeoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			MsUbigeo msUbigeo = null;
			if (msUbigeoBk.getId() != null && msUbigeoBk.getId().getCoddpto() != null
					&& msUbigeoBk.getId().getCoddpto().intValue() > 0) {

				msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoBk.getId());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msUbigeo.setIduserModif(kyUsuarioMod);
				msUbigeo.setFechaModif(hoy);
				Integer estadoanterior = msUbigeo.getEstado();
				msUbigeo.setEstado(0);

				msUbigeoDao.updateMsUbigeo(msUbigeo);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msUbigeo" + " :: " + msUbigeo.getId().toString() + " :: " + estadoanterior
								+ " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsUbigeoBk> getMsUbigeoXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre,
			Integer estado, Long kyUsuarioMod) {
		List<MsUbigeoBk> MsUbigeoBks = new ArrayList<MsUbigeoBk>();
		try {
			List<MsUbigeo> MsUbigeosss = msUbigeoDao.getXFiltro(coddpto, codprov, coddist, nombre, estado);
			for (MsUbigeo MsUbigeo : MsUbigeosss) {
				MsUbigeoBk MsUbigeoBk = new MsUbigeoBk();
				FuncionesStaticas.copyPropertiesObject(MsUbigeoBk, MsUbigeo);
				completarMsUbigeo(MsUbigeoBk, kyUsuarioMod);
				MsUbigeoBks.add(MsUbigeoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsUbigeoBks;
	}

	@Override
	public List<MsUbigeoBk> getMsUbigeoXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre,
			Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<MsUbigeoBk> MsUbigeoBks = new ArrayList<MsUbigeoBk>();
		try {
			List<MsUbigeo> MsUbigeosss = msUbigeoDao.getXFiltro(coddpto, codprov, coddist, nombre, estado, inicial,
					MAX);
			for (MsUbigeo MsUbigeo : MsUbigeosss) {
				MsUbigeoBk MsUbigeoBk = new MsUbigeoBk();
				FuncionesStaticas.copyPropertiesObject(MsUbigeoBk, MsUbigeo);
				completarMsUbigeo(MsUbigeoBk, kyUsuarioMod);
				MsUbigeoBks.add(MsUbigeoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsUbigeoBks;
	}

	@Override
	public Long getMsUbigeoTotalXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre,
			Integer estado) {
		try {
			Long totalMsUbigeosss = msUbigeoDao.getTotalXFiltro(coddpto, codprov, coddist, nombre, estado);

			return totalMsUbigeosss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setACLMsUbigeoBk(MsUbigeoBk msUbigeoBk, Long kyUsuarioMod) {
		MsUbigeoACL msUbigeoACL = new MsUbigeoACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUBIGEO_CREA)) {
					msUbigeoACL.setEsEditable(true);
					msUbigeoACL.setEliminar(true);
				} else if (roles.contains(Roles.MSUBIGEO_VE)) {
					msUbigeoACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUBIGEO_CREA)) {
					msUbigeoACL.setEditopcion(1);
				} else {
					msUbigeoACL.setEditopcion(2);
				}
			} else {
				msUbigeoACL.setEditopcion(2);
			}
		} else {
			msUbigeoACL.setEditopcion(2);
		}
		msUbigeoBk.setMsUbigeoACL(msUbigeoACL);
	}

	@Override
	public String getNombreDepartamento(int idDpto) {
		if (idDpto > 0) {
			MsUbigeoId acMsUbigeoId = new MsUbigeoId(idDpto, 0, 0);
			MsUbigeo acMsUbigeo = msUbigeoDao.getMsUbigeo(acMsUbigeoId);
			if (acMsUbigeo != null) {
				return acMsUbigeo.getNombre();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getNombreProvincia(int idDpto, int idProv) {
		String nombre = null;
		try {
			if (idDpto > 0 && idProv > 0) {
				MsUbigeoId acMsUbigeoId = new MsUbigeoId(idDpto, idProv, 0);
				MsUbigeo acMsUbigeo = msUbigeoDao.getMsUbigeo(acMsUbigeoId);
				if (acMsUbigeo != null)
					nombre = acMsUbigeo.getNombre();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return nombre;
	}

	@Override
	public String getNombreDistrito(int idDpto, int idProv, int idDist) {
		String nombre = null;
		try {
			if (idDpto > 0 && idProv > 0 && idDist > 0) {
				MsUbigeoId acMsUbigeoId = new MsUbigeoId(idDpto, idProv, idDist);
				MsUbigeo acMsUbigeo = msUbigeoDao.getMsUbigeo(acMsUbigeoId);
				if (acMsUbigeo != null)
					nombre = acMsUbigeo.getNombre();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return nombre;
	}

	@Override
	public List<MsUbigeoBk> getDepartamentos() {
		List<MsUbigeoBk> ubigeoItemDtos = new ArrayList<MsUbigeoBk>();
		List<MsUbigeo> departamentos = msUbigeoDao.getDepartamentos();
		for (MsUbigeo acMsUbigeo : departamentos) {
			MsUbigeoBk ubigeoItemDto = new MsUbigeoBk();
			FuncionesStaticas.copyPropertiesObject(ubigeoItemDto, acMsUbigeo);
			ubigeoItemDtos.add(ubigeoItemDto);
		}
		return ubigeoItemDtos;
	}

	@Override
	public List<MsUbigeoBk> getProvincias(Integer id_dpto) {
		List<MsUbigeoBk> ubigeoItemProv = new ArrayList<MsUbigeoBk>();
		List<MsUbigeo> departamentos = msUbigeoDao.getProvincias(id_dpto);
		for (MsUbigeo acMsUbigeo : departamentos) {
			MsUbigeoBk ubigeoItemDto = new MsUbigeoBk();
			FuncionesStaticas.copyPropertiesObject(ubigeoItemDto, acMsUbigeo);
			ubigeoItemProv.add(ubigeoItemDto);
		}
		return ubigeoItemProv;
	}

	@Override
	public List<MsUbigeoBk> getDistritos(Integer id_dpto, Integer id_prov) {
		List<MsUbigeoBk> ubigeoItemDist = new ArrayList<MsUbigeoBk>();
		List<MsUbigeo> departamentos = msUbigeoDao.getDistritos(id_dpto, id_prov);
		for (MsUbigeo acMsUbigeo : departamentos) {
			MsUbigeoBk ubigeoItemDto = new MsUbigeoBk();
			FuncionesStaticas.copyPropertiesObject(ubigeoItemDto, acMsUbigeo);
			ubigeoItemDist.add(ubigeoItemDto);
		}
		return ubigeoItemDist;
	}

	/**
	 * MS_PAISES SERVICIO: PAISES SEGÚN ISO
	 */
	public MsPaisesBk getMsPaisesBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsPaises msPaises = msPaisesDao.getMsPaises(id);
		MsPaisesBk msPaisesBk = null;
		if (msPaises != null) {
			msPaisesBk = new MsPaisesBk();
			FuncionesStaticas.copyPropertiesObject(msPaisesBk, msPaises);
			completarMsPaises(msPaisesBk, kyUsuarioMod);
		}
		return msPaisesBk;
	}

	public List<MsPaisesBk> getAllMsPaisesActivos(Long kyUsuarioMod) {
		List<MsPaisesBk> msPaisesBks = new ArrayList<MsPaisesBk>();
		try {
			List<MsPaises> msPaisess = msPaisesDao.getActivasMsPaises();
			for (MsPaises msPaises : msPaisess) {
				MsPaisesBk msPaisesBk = new MsPaisesBk();
				FuncionesStaticas.copyPropertiesObject(msPaisesBk, msPaises);
				completarMsPaises(msPaisesBk, kyUsuarioMod);
				msPaisesBks.add(msPaisesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPaisesBks;
	}

	@Override
	public List<MsPaisesBk> getAllMsPaisesActivosCero(Long kyUsuarioMod) {
		List<MsPaisesBk> msPaisesBks = new ArrayList<MsPaisesBk>();
		try {
			List<MsPaises> msPaisess = msPaisesDao.getActivasMsPaisesCero();
			for (MsPaises msPaises : msPaisess) {
				MsPaisesBk msPaisesBk = new MsPaisesBk();
				FuncionesStaticas.copyPropertiesObject(msPaisesBk, msPaises);
				completarMsPaises(msPaisesBk, kyUsuarioMod);
				msPaisesBks.add(msPaisesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPaisesBks;
	}

	public List<IDValorDto> getListaMsPaisesActivos() {
		List<IDValorDto> msPaisesBks = new ArrayList<IDValorDto>();
		try {
			List<MsPaises> msPaisess = msPaisesDao.getActivasMsPaises();
			for (MsPaises msPaises : msPaisess) {
				IDValorDto iDValorDto = new IDValorDto(msPaises.getPaiPk(), msPaises.getPaiNombre());
				msPaisesBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPaisesBks;
	}

	private void completarMsPaises(MsPaisesBk msPaisesBk, Long kyUsuarioMod) {
		try {
			if (msPaisesBk.getIduserCrea() != null && msPaisesBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msPaisesBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msPaisesBk.getIduserCrea());
				if (msUsuarios != null)
					msPaisesBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPaisesBk.getIduserModif() != null && msPaisesBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msPaisesBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msPaisesBk.getIduserModif());
				if (msUsuarios != null)
					msPaisesBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLMsPaisesBk(msPaisesBk, kyUsuarioMod);

		// try {
		// System.out.println("COMPLETAR msPaisesBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public MsPaisesBk saveorupdateMsPaisesBk(MsPaisesBk msPaisesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {

		ValidacionMsPaisesMng.validarMsPaisesBk(msPaisesBk);

		MsPaises msPaises = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msPaisesBk.getPaiPk() != null && msPaisesBk.getPaiPk().longValue() > 0) {

				msPaises = msPaisesDao.getMsPaises(msPaisesBk.getPaiPk());

				boolean cambios = AuditoriaMsPaisesMng.auditarCambiosMsPaises(msPaisesBk, msPaises, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					msPaises.setRmtaddressrst(rmtaddress);
					msPaises.setIduserModif(kyUsuarioMod);
					msPaises.setFechaModif(hoy);
					msPaisesDao.updateMsPaises(msPaises);
				}
			} else {
				msPaisesBk.setRmtaddress(rmtaddress);
				msPaisesBk.setRmtaddressrst(rmtaddress);
				// msPaisesBk.setSede(kySedeMod);
				// msPaisesBk.setIdunidad(kyAreaMod);

				msPaisesBk.setFechaCrea(hoy);
				msPaisesBk.setIduserCrea(kyUsuarioMod);
				msPaisesBk.setIduserModif(kyUsuarioMod);
				msPaisesBk.setFechaModif(hoy);
				msPaisesBk.setEstado(1);

				msPaises = new MsPaises();

				FuncionesStaticas.copyPropertiesObject(msPaises, msPaisesBk);
				msPaisesDao.saveMsPaises(msPaises);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO msPaises" + " :: " + msPaises.getPaiPk().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msPaisesBk = getMsPaisesBkXid(msPaises.getPaiPk(), kyUsuarioMod);
		return msPaisesBk;
	}

	public void deleteMsPaises(MsPaisesBk msPaisesBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			MsPaises msPaises = null;
			if (msPaisesBk.getPaiPk() != null && msPaisesBk.getPaiPk().longValue() > 0) {

				msPaises = msPaisesDao.getMsPaises(msPaisesBk.getPaiPk());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msPaises.setIduserModif(kyUsuarioMod);
				msPaises.setFechaModif(hoy);
				Integer estadoanterior = msPaises.getEstado();
				msPaises.setEstado(0);

				msPaisesDao.updateMsPaises(msPaises);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msPaises" + " :: " + msPaises.getPaiPk().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsPaisesBk> getMsPaisesXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3,
			String paiNombre, Integer estado, Long kyUsuarioMod) {
		List<MsPaisesBk> MsPaisesBks = new ArrayList<MsPaisesBk>();
		try {
			List<MsPaises> MsPaisessss = msPaisesDao.getXFiltro(paiPk, paiIsonum, paiIso2, paiIso3, paiNombre, estado);
			for (MsPaises MsPaises : MsPaisessss) {
				MsPaisesBk MsPaisesBk = new MsPaisesBk();
				FuncionesStaticas.copyPropertiesObject(MsPaisesBk, MsPaises);
				completarMsPaises(MsPaisesBk, kyUsuarioMod);
				MsPaisesBks.add(MsPaisesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsPaisesBks;
	}

	@Override
	public List<MsPaisesBk> getMsPaisesXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3,
			String paiNombre, Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<MsPaisesBk> MsPaisesBks = new ArrayList<MsPaisesBk>();
		try {
			List<MsPaises> MsPaisessss = msPaisesDao.getXFiltro(paiPk, paiIsonum, paiIso2, paiIso3, paiNombre, estado,
					inicial, MAX);
			for (MsPaises MsPaises : MsPaisessss) {
				MsPaisesBk MsPaisesBk = new MsPaisesBk();
				FuncionesStaticas.copyPropertiesObject(MsPaisesBk, MsPaises);
				completarMsPaises(MsPaisesBk, kyUsuarioMod);
				MsPaisesBks.add(MsPaisesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsPaisesBks;
	}

	@Override
	public Long getMsPaisesTotalXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3, String paiNombre,
			Integer estado) {
		try {
			Long totalMsPaisessss = msPaisesDao.getTotalXFiltro(paiPk, paiIsonum, paiIso2, paiIso3, paiNombre, estado);

			return totalMsPaisessss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setACLMsPaisesBk(MsPaisesBk msPaisesBk, Long kyUsuarioMod) {
		MsPaisesACL msPaisesACL = new MsPaisesACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSPAISES_CREA)) {
					msPaisesACL.setEsEditable(true);
					msPaisesACL.setEliminar(true);
				} else if (roles.contains(Roles.MSPAISES_VE)) {
					msPaisesACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSPAISES_CREA)) {
					msPaisesACL.setEditopcion(1);
				} else {
					msPaisesACL.setEditopcion(2);
				}
			}
		} else {
			msPaisesACL.setEditopcion(2);
		}
		msPaisesBk.setMsPaisesACL(msPaisesACL);
	}

	/**
	 * MS_PERSONAS SERVICIO: PERSONAS NATURALES
	 */
	public MsPersonasBk getMsPersonasBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsPersonas msPersonas = msPersonasDao.getMsPersonas(id);
		MsPersonasBk msPersonasBk = null;
		if (msPersonas != null) {
			msPersonasBk = new MsPersonasBk();
			FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
			completarMsPersonas(msPersonasBk, kyUsuarioMod);
		}
		return msPersonasBk;
	}

	public List<MsPersonasBk> getAllMsPersonasActivos(Long kyUsuarioMod) {
		List<MsPersonasBk> msPersonasBks = new ArrayList<MsPersonasBk>();
		try {
			List<MsPersonas> msPersonass = msPersonasDao.getActivasMsPersonas();
			for (MsPersonas msPersonas : msPersonass) {
				MsPersonasBk msPersonasBk = new MsPersonasBk();
				FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
				completarMsPersonas(msPersonasBk, kyUsuarioMod);
				msPersonasBks.add(msPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPersonasBks;
	}

	@Override
	public List<MsPersonasBk> getAllMsPersonasActivosCero(Long kyUsuarioMod) {
		List<MsPersonasBk> msPersonasBks = new ArrayList<MsPersonasBk>();
		try {
			List<MsPersonas> msPersonass = msPersonasDao.getActivasMsPersonasCero();
			for (MsPersonas msPersonas : msPersonass) {
				MsPersonasBk msPersonasBk = new MsPersonasBk();
				FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
				completarMsPersonas(msPersonasBk, kyUsuarioMod);
				msPersonasBks.add(msPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPersonasBks;
	}

	private void completarMsPersonas(MsPersonasBk msPersonasBk, Long kyUsuarioMod) {
		try {
			if (msPersonasBk.getTipodocumento() != null && msPersonasBk.getTipodocumento().longValue() > 0) {
				PrtParametros prtParametros = prtParametrosDao.getPrtParametros(msPersonasBk.getTipodocumento());
				if (prtParametros != null)
					msPersonasBk.setTipodocumentoTxt(prtParametros.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = 0;
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCoddptoTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0
					&& msPersonasBk.getCodprov() != null && msPersonasBk.getCodprov().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = msPersonasBk.getCodprov().intValue();
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCodprovTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0
					&& msPersonasBk.getCodprov() != null && msPersonasBk.getCodprov().intValue() > 0
					&& msPersonasBk.getCoddist() != null && msPersonasBk.getCoddist().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = msPersonasBk.getCodprov().intValue();
				int iiCoddist = msPersonasBk.getCoddist().intValue();
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCoddistTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msPersonasBk.getIduserCrea() != null && msPersonasBk.getIduserCrea().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msPersonasBk.getIduserCrea());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msPersonasBk.getIduserCrea());
				if (msUsuarios != null)
					msPersonasBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPersonasBk.getIduserModif() != null && msPersonasBk.getIduserModif().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(msPersonasBk.getIduserModif());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(msPersonasBk.getIduserModif());
				if (msUsuarios != null)
					msPersonasBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLMsPersonasBk(msPersonasBk, kyUsuarioMod);

		// try {
		// System.out.println("COMPLETAR msPersonasBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public MsPersonasBk saveorupdateMsPersonasBk(MsPersonasBk msPersonasBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionMsPersonasMng.validarMsPersonasBk(msPersonasBk, getxDefectoCodpais());

		MsPersonas msPersonas = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msPersonasBk.getIdperson() != null && msPersonasBk.getIdperson().longValue() > 0) {

				msPersonas = msPersonasDao.getMsPersonas(msPersonasBk.getIdperson());

				boolean cambios = AuditoriaMsPersonasMng.auditarCambiosMsPersonas(msPersonasBk, msPersonas,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					msPersonas.setRmtaddressrst(rmtaddress);
					msPersonas.setEstado(1);
					msPersonas.setIduserModif(kyUsuarioMod);
					msPersonas.setFechaModif(hoy);
					msPersonasDao.updateMsPersonas(msPersonas);
				}
			} else {
				msPersonasBk.setRmtaddress(rmtaddress);
				msPersonasBk.setRmtaddressrst(rmtaddress);
				// msPersonasBk.setSede(kySedeMod);
				// msPersonasBk.setIdunidad(kyAreaMod);

				msPersonasBk.setFechaCrea(hoy);
				msPersonasBk.setIduserCrea(kyUsuarioMod);
				msPersonasBk.setIduserModif(kyUsuarioMod);
				msPersonasBk.setFechaModif(hoy);
				msPersonasBk.setEstado(1);

				msPersonas = new MsPersonas();

				FuncionesStaticas.copyPropertiesObject(msPersonas, msPersonasBk);
				msPersonasDao.saveMsPersonas(msPersonas);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: " + "CREADO msPersonas"
								+ " :: " + msPersonas.getIdperson().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msPersonasBk = getMsPersonasBkXid(msPersonas.getIdperson(), kyUsuarioMod);
		return msPersonasBk;
	}

	public void deleteMsPersonas(MsPersonasBk msPersonasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			MsPersonas msPersonas = null;
			if (msPersonasBk.getIdperson() != null && msPersonasBk.getIdperson().longValue() > 0) {

				msPersonas = msPersonasDao.getMsPersonas(msPersonasBk.getIdperson());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msPersonas.setIduserModif(kyUsuarioMod);
				msPersonas.setFechaModif(hoy);
				Integer estadoanterior = msPersonas.getEstado();
				msPersonas.setEstado(0);

				msPersonasDao.updateMsPersonas(msPersonas);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msPersonas" + " :: " + msPersonas.getIdperson().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsPersonasBk> getMsPersonasXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno,
			String nombres, String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular,
			String direccion, Integer estado, Long kyUsuarioMod) {
		List<MsPersonasBk> MsPersonasBks = new ArrayList<MsPersonasBk>();
		try {
			List<MsPersonas> MsPersonassss = msPersonasDao.getXFiltro(idperson, apellidoPaterno, apellidoMaterno,
					nombres, sexo, tipodocumento, documentoNumero, telefono, celular, direccion, estado);
			for (MsPersonas MsPersonas : MsPersonassss) {
				MsPersonasBk MsPersonasBk = new MsPersonasBk();
				FuncionesStaticas.copyPropertiesObject(MsPersonasBk, MsPersonas);
				completarMsPersonas(MsPersonasBk, kyUsuarioMod);
				MsPersonasBks.add(MsPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsPersonasBks;
	}

	@Override
	public List<MsPersonasBk> getMsPersonasXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno,
			String nombres, String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular,
			String direccion, Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<MsPersonasBk> MsPersonasBks = new ArrayList<MsPersonasBk>();
		try {
			List<MsPersonas> MsPersonassss = msPersonasDao.getXFiltro(idperson, apellidoPaterno, apellidoMaterno,
					nombres, sexo, tipodocumento, documentoNumero, telefono, celular, direccion, estado, inicial, MAX);
			for (MsPersonas MsPersonas : MsPersonassss) {
				MsPersonasBk MsPersonasBk = new MsPersonasBk();
				FuncionesStaticas.copyPropertiesObject(MsPersonasBk, MsPersonas);
				completarMsPersonas(MsPersonasBk, kyUsuarioMod);
				MsPersonasBks.add(MsPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsPersonasBks;
	}

	@Override
	public Long getMsPersonasTotalXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno, String nombres,
			String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular, String direccion,
			Integer estado) {
		try {
			Long totalMsPersonassss = msPersonasDao.getTotalXFiltro(idperson, apellidoPaterno, apellidoMaterno, nombres,
					sexo, tipodocumento, documentoNumero, telefono, celular, direccion, estado);

			return totalMsPersonassss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setACLMsPersonasBk(MsPersonasBk msPersonasBk, Long kyUsuarioMod) {
		MsPersonasACL msPersonasACL = new MsPersonasACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.REGISTRADOR)) {
					msPersonasACL.setEsEditable(true);
					msPersonasACL.setEliminar(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.REGISTRADOR)) {
					msPersonasACL.setEditopcion(1);
				} else {
					msPersonasACL.setEditopcion(2);
				}
			} else {
				msPersonasACL.setEditopcion(2);
			}
		} else {
			msPersonasACL.setEditopcion(2);
		}
		msPersonasBk.setMsPersonasACL(msPersonasACL);
	}

	/**
	 * TD_ANEXOS SERVICIO: ANEXOS DE LOS MOVIMIENTOS
	 */
	public TdAnexosBk getTdAnexosBkXid(Long id) {
		if (id == null)
			return null;
		TdAnexos tdAnexos = tdAnexosDao.getTdAnexos(id);
		TdAnexosBk tdAnexosBk = null;
		if (tdAnexos != null) {
			tdAnexosBk = new TdAnexosBk();
			FuncionesStaticas.copyPropertiesObject(tdAnexosBk, tdAnexos);
			completarTdAnexos(tdAnexosBk);
		}
		return tdAnexosBk;
	}

	public List<TdAnexosBk> getAllTdAnexosActivos() {
		List<TdAnexosBk> tdAnexosBks = new ArrayList<TdAnexosBk>();
		try {
			List<TdAnexos> tdAnexoss = tdAnexosDao.getActivasTdAnexos();
			for (TdAnexos tdAnexos : tdAnexoss) {
				TdAnexosBk tdAnexosBk = new TdAnexosBk();
				FuncionesStaticas.copyPropertiesObject(tdAnexosBk, tdAnexos);
				completarTdAnexos(tdAnexosBk);
				tdAnexosBks.add(tdAnexosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdAnexosBks;
	}

	private void completarTdAnexos(TdAnexosBk tdAnexosBk) {
		// try {
		// if (tdAnexosBk.getIdsacc() != null &&
		// tdAnexosBk.getIdsacc().longValue() > 0) {
		// TdAtenciones tdAtenciones =
		// tdAtencionesDao.getTdAtenciones(tdAnexosBk.getIdsacc());
		// if (tdAtenciones != null)
		// tdAnexosBk.setIdsaccTxt(tdAtenciones.getNumeroSacc() + "-" +
		// tdAtenciones.getNumeroAnio());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (tdAnexosBk.getIduserCrea() != null &&
		// tdAnexosBk.getIduserCrea().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdAnexosBk.getIduserCrea());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(tdAnexosBk.getIduserCrea());
		// if (msUsuarios != null)
		// tdAnexosBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (tdAnexosBk.getIduserModif() != null &&
		// tdAnexosBk.getIduserModif().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdAnexosBk.getIduserModif());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(tdAnexosBk.getIduserModif());
		// if (msUsuarios != null)
		// tdAnexosBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
		// setACLTdAnexosBk(tdAnexosBk, kyUsuarioMod);
		//
		// try {
		// System.out.println("COMPLETAR tdAnexosBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public TdAnexosBk saveorupdateTdAnexosBk(TdAnexosBk tdAnexosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String kySedeMod, String rmtaddress) throws Validador {

		ValidacionTdAnexosMng.validarTdAnexosBk(tdAnexosBk);

		TdAnexos tdAnexos = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdAnexosBk.getIdanexo() != null && tdAnexosBk.getIdanexo().longValue() > 0) {

				tdAnexos = tdAnexosDao.getTdAnexos(tdAnexosBk.getIdanexo());

				boolean cambios = AuditoriaTdAnexosMng.auditarCambiosTdAnexos(tdAnexosBk, tdAnexos, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					tdAnexos.setRmtaddressrst(rmtaddress);
					tdAnexos.setIduserModif(kyUsuarioMod);
					tdAnexos.setFechaModif(hoy);
					tdAnexosDao.updateTdAnexos(tdAnexos);
				}
			} else {
				tdAnexosBk.setRmtaddress(rmtaddress);
				tdAnexosBk.setRmtaddressrst(rmtaddress);
				// tdAnexosBk.setSede(kySedeMod);
				// tdAnexosBk.setIdunidad(kyAreaMod);

				tdAnexosBk.setFechaCrea(hoy);
				tdAnexosBk.setIduserCrea(kyUsuarioMod);
				tdAnexosBk.setIduserModif(kyUsuarioMod);
				tdAnexosBk.setFechaModif(hoy);
				tdAnexosBk.setEstado(1);

				tdAnexos = new TdAnexos();

				FuncionesStaticas.copyPropertiesObject(tdAnexos, tdAnexosBk);
				tdAnexosDao.saveTdAnexos(tdAnexos);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO tdAnexos" + " :: " + tdAnexos.getIdanexo().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdAnexosBk = getTdAnexosBkXid(tdAnexos.getIdanexo());
		return tdAnexosBk;
	}

	public void deleteTdAnexos(TdAnexosBk tdAnexosBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			TdAnexos tdAnexos = null;
			if (tdAnexosBk.getIdanexo() != null && tdAnexosBk.getIdanexo().longValue() > 0) {

				tdAnexos = tdAnexosDao.getTdAnexos(tdAnexosBk.getIdanexo());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdAnexos.setIduserModif(kyUsuarioMod);
				tdAnexos.setFechaModif(hoy);
				Integer estadoanterior = tdAnexos.getEstado();
				tdAnexos.setEstado(0);

				tdAnexosDao.updateTdAnexos(tdAnexos);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdAnexos" + " :: " + tdAnexos.getIdanexo().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdAnexosBk> getTdAnexosXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado,
			Long kyUsuarioMod) {
		List<TdAnexosBk> TdAnexosBks = new ArrayList<TdAnexosBk>();
		try {
			List<TdAnexos> TdAnexossss = tdAnexosDao.getXFiltro(idsacc, filename, filenameoriginal, estado);
			for (TdAnexos TdAnexos : TdAnexossss) {
				TdAnexosBk TdAnexosBk = new TdAnexosBk();
				FuncionesStaticas.copyPropertiesObject(TdAnexosBk, TdAnexos);
				completarTdAnexos(TdAnexosBk);
				TdAnexosBks.add(TdAnexosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdAnexosBks;
	}

	@Override
	public List<TdAnexosBk> getTdAnexosXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado,
			int inicial, int MAX, Long kyUsuarioMod) {
		List<TdAnexosBk> TdAnexosBks = new ArrayList<TdAnexosBk>();
		try {
			List<TdAnexos> TdAnexossss = tdAnexosDao.getXFiltro(idsacc, filename, filenameoriginal, estado, inicial,
					MAX);
			for (TdAnexos TdAnexos : TdAnexossss) {
				TdAnexosBk TdAnexosBk = new TdAnexosBk();
				FuncionesStaticas.copyPropertiesObject(TdAnexosBk, TdAnexos);
				completarTdAnexos(TdAnexosBk);
				TdAnexosBks.add(TdAnexosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdAnexosBks;
	}

	@Override
	public Long getTdAnexosTotalXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado) {
		try {
			Long totalTdAnexossss = tdAnexosDao.getTotalXFiltro(idsacc, filename, filenameoriginal, estado);

			return totalTdAnexossss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// public void setACLTdAnexosBk(TdAnexosBk tdAnexosBk, Long kyUsuarioMod) {
	// TdAnexosACL tdAnexosACL = new TdAnexosACL();
	// if (kyUsuarioMod != null) {
	// MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
	// if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
	// List<String> roles = msUsuariosBk.getRolesSistema();
	// if (roles.contains(Roles.ADMINISTRADOR) ||
	// roles.contains(Roles.TDANEXOS_CREA)) {
	// tdAnexosACL.setEsEditable(true);
	// tdAnexosACL.setEliminar(true);
	// }
	// }
	// }
	// tdAnexosACL.setEditopcion(2);
	// tdAnexosBk.setTdAnexosACL(tdAnexosACL);
	// }

	@Override
	public List<TdAnexosBk> getTdAnexosXAtencioYFlujo(Long idsacc, Long idflujo) {
		List<TdAnexosBk> tdAnexosBksss = new ArrayList<TdAnexosBk>();
		try {
			List<TdAnexos> TdAnexossss = tdAnexosDao.getXFiltroXFlujoYAtencio(idsacc, idflujo);
			for (TdAnexos TdAnexos : TdAnexossss) {
				TdAnexosBk TdAnexosBk = new TdAnexosBk();
				FuncionesStaticas.copyPropertiesObject(TdAnexosBk, TdAnexos);
				completarTdAnexos(TdAnexosBk);
				tdAnexosBksss.add(TdAnexosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdAnexosBksss;
	}

	@Override
	public List<TdAnexosDto> getTdAnexosDtoXAtencioYFlujo(Long idsacc, Long idflujo) {
		List<TdAnexosDto> tdAnexosBksss = new ArrayList<TdAnexosDto>();
		try {
			List<TdAnexos> TdAnexossss = tdAnexosDao.getXFiltroXFlujoYAtencio(idsacc, idflujo);
			for (TdAnexos TdAnexos : TdAnexossss) {
				TdAnexosDto tdAnexosDto = new TdAnexosDto();
				FuncionesStaticas.copyPropertiesObject(tdAnexosDto, TdAnexos);
				tdAnexosBksss.add(tdAnexosDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdAnexosBksss;
	}

	@Async
	@Override
	public void actualizarEntidad(Long idprovee, String correo, MsUsuariosBk usuariologueado, String rmtaddress) {
		if (idprovee != null && idprovee.longValue() > 0 && correo != null && correo.trim().length() > 1) {
			MsInstitucionesBk msInstitucionesBk = getMsInstitucionesBkXid(idprovee, null);
			boolean cambiar = false;
			if (msInstitucionesBk.getCorreo() == null || msInstitucionesBk.getCorreo().trim().length() <= 0
					|| !msInstitucionesBk.getCorreo().equalsIgnoreCase(correo)) {
				msInstitucionesBk.setCorreo(correo);
				cambiar = true;
			}
			if (cambiar) {
				try {
					saveorupdateMsInstitucionesBk(msInstitucionesBk, usuariologueado.getUsername(),
							usuariologueado.getIdusuario(), usuariologueado.getIdunidad(), rmtaddress);
				} catch (Validador e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * TD_FLUJO SERVICIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
	 */
	public TdFlujoBk getTdFlujoBkXid(Long id) {
		if (id == null)
			return null;
		TdFlujo tdFlujo = tdFlujoDao.getTdFlujo(id);
		TdFlujoBk tdFlujoBk = null;
		if (tdFlujo != null) {
			tdFlujoBk = new TdFlujoBk();
			FuncionesStaticas.copyPropertiesObject(tdFlujoBk, tdFlujo);
			completarTdFlujo(tdFlujoBk);
		}
		return tdFlujoBk;
	}

	public List<TdFlujoBk> getAllTdFlujoActivos() {
		List<TdFlujoBk> tdFlujoBks = new ArrayList<TdFlujoBk>();
		try {
			List<TdFlujo> tdFlujos = tdFlujoDao.getActivasTdFlujo();
			for (TdFlujo tdFlujo : tdFlujos) {
				TdFlujoBk tdFlujoBk = new TdFlujoBk();
				FuncionesStaticas.copyPropertiesObject(tdFlujoBk, tdFlujo);
				completarTdFlujo(tdFlujoBk);
				tdFlujoBks.add(tdFlujoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdFlujoBks;
	}

	private void completarTdFlujo(TdFlujoBk tdFlujoBk) {

		try {
			if (tdFlujoBk.getEstado() != null && tdFlujoBk.getEstado().intValue() > 0) {
				if (getEstadosdeflujo() != null) {
					if (getEstadosdeflujo().containsKey(tdFlujoBk.getEstado())) {
						String estado = getEstadosdeflujo().get(tdFlujoBk.getEstado());
						tdFlujoBk.setEstadoTxt(estado);
					} else {
						tdFlujoBk.setEstadoTxt("SIN ESPECIFICAR");
					}
				} else {
					tdFlujoBk.setEstadoTxt("SIN ESPECIFICAR");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdFlujoBk.getIdunidadDeriva() != null && tdFlujoBk.getIdunidadDeriva().longValue() > 0) {
				MsUnidadesOrgBk msUnidadesOrg = getMsUnidadesOrgBkXidCH(tdFlujoBk.getIdunidadDeriva());
				if (msUnidadesOrg != null)
					tdFlujoBk.setIdunidadDerivaTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdFlujoBk.getIdunidadRecepciona() != null && tdFlujoBk.getIdunidadRecepciona().longValue() > 0) {
				MsUnidadesOrgBk msUnidadesOrg = getMsUnidadesOrgBkXidCH(tdFlujoBk.getIdunidadRecepciona());
				if (msUnidadesOrg != null)
					tdFlujoBk.setIdunidadRecepcionaTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdFlujoBk.getIdunidadDestino() != null && tdFlujoBk.getIdunidadDestino().longValue() > 0) {
				MsUnidadesOrgBk msUnidadesOrg = getMsUnidadesOrgBkXidCH(tdFlujoBk.getIdunidadDestino());
				if (msUnidadesOrg != null) {
					tdFlujoBk.setIdunidadDestinoTxt(msUnidadesOrg.getDescripcion());
					tdFlujoBk.setIdunidadDestinoGTxt(msUnidadesOrg.getIdsppadreTxt());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdFlujoBk.getIdunidadAtiende() != null && tdFlujoBk.getIdunidadAtiende().longValue() > 0) {
				MsUnidadesOrgBk msUnidadesOrg = getMsUnidadesOrgBkXidCH(tdFlujoBk.getIdunidadAtiende());
				if (msUnidadesOrg != null)
					tdFlujoBk.setIdunidadAtiendeTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdFlujoBk.getIduserDeriva() != null && tdFlujoBk.getIduserDeriva().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserDeriva());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFlujoBk.getIduserDeriva());
				if (msUsuarios != null)
					tdFlujoBk.setIduserDerivaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdFlujoBk.getIduserRecepciona() != null && tdFlujoBk.getIduserRecepciona().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserRecepciona());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFlujoBk.getIduserRecepciona());
				if (msUsuarios != null)
					tdFlujoBk.setIduserRecepcionaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdFlujoBk.getIduserDestino() != null && tdFlujoBk.getIduserDestino().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserDestino());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFlujoBk.getIduserDestino());
				if (msUsuarios != null)
					tdFlujoBk.setIduserDestinoTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdFlujoBk.getIduserAtiende() != null && tdFlujoBk.getIduserAtiende().longValue() > 0) {
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserAtiende());
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFlujoBk.getIduserAtiende());
				if (msUsuarios != null)
					tdFlujoBk.setIduserAtiendeTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdFlujoBk.getIdevent() != null && tdFlujoBk.getIdevent().longValue() > 0) {
				List<TdAnexosDto> tdAnexosBksss = getTdAnexosDtoXAtencioYFlujo(tdFlujoBk.getIdevent(),
						tdFlujoBk.getIdflujo());
				tdFlujoBk.setTdAnexosBksss(tdAnexosBksss);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// vbaldeon 25092023 inicio
		try {
			if (tdFlujoBk.getIdTarea() != null && tdFlujoBk.getIdTarea().longValue() > 0) {
				MsTareas msTareas = msTareasDao.getMsTareas(tdFlujoBk.getIdTarea());
				if (msTareas != null)
					tdFlujoBk.setIdTareaTxt(msTareas.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// if (tdFlujoBk.getIduserCrea() != null &&
		// tdFlujoBk.getIduserCrea().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserCrea());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(tdFlujoBk.getIduserCrea());
		// if (msUsuarios != null)
		// tdFlujoBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// // if(msUsuarios!=null)
		// // tdFlujoBk.setIduserCreaTxt(msUsuarios.getIdusuario());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// if (tdFlujoBk.getIduserModif() != null &&
		// tdFlujoBk.getIduserModif().longValue() > 0) {
		// // MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdFlujoBk.getIduserModif());
		// MsUsuariosBk msUsuarios =
		// getMsUsuariosBkXidCH(tdFlujoBk.getIduserModif());
		// if (msUsuarios != null)
		// tdFlujoBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// // if(msUsuarios!=null)
		// // tdFlujoBk.setIduserModifTxt(msUsuarios.getIdusuario());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
		// setACLTdFlujoBk(tdFlujoBk, kyUsuarioMod);

		// try {
		// System.out.println("COMPLETAR tdFlujoBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public TdFlujoBk saveorupdateTdFlujoBk(TdFlujoBk tdFlujoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String kySedeMod, String rmtaddress) throws Validador {

		ValidacionTdFlujoMng.validarTdFlujoBk(tdFlujoBk);

		TdFlujo tdFlujo = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdFlujoBk.getIdflujo() != null && tdFlujoBk.getIdflujo().longValue() > 0) {

				tdFlujo = tdFlujoDao.getTdFlujo(tdFlujoBk.getIdflujo());

				boolean cambios = AuditoriaTdFlujoMng.auditarCambiosTdFlujo(tdFlujoBk, tdFlujo, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					tdFlujo.setRmtaddressrst(rmtaddress);
					tdFlujo.setIduserModif(kyUsuarioMod);
					tdFlujo.setFechaModif(hoy);
					tdFlujoDao.updateTdFlujo(tdFlujo);
				}
			} else {
				tdFlujoBk.setRmtaddress(rmtaddress);
				tdFlujoBk.setRmtaddressrst(rmtaddress);
				// tdFlujoBk.setSede(kySedeMod);
				// tdFlujoBk.setIdunidad(kyAreaMod);

				tdFlujoBk.setFechaCrea(hoy);
				tdFlujoBk.setIduserCrea(kyUsuarioMod);
				tdFlujoBk.setIduserModif(kyUsuarioMod);
				tdFlujoBk.setFechaModif(hoy);
				// tdFlujoBk.setEstado(1);

				tdFlujo = new TdFlujo();

				FuncionesStaticas.copyPropertiesObject(tdFlujo, tdFlujoBk);
				tdFlujoDao.saveTdFlujo(tdFlujo);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO tdFlujo" + " :: " + tdFlujo.getIdflujo().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdFlujoBk = getTdFlujoBkXid(tdFlujo.getIdflujo());
		return tdFlujoBk;
	}

	@Override
	public TdFlujoBk saveorupdateTdFlujoBk(TdFlujoBk tdFlujoBk, Long kyUsuarioMod, Long kyAreaMod, String kySedeMod,
			String rmtaddress) throws Validador {

		ValidacionTdFlujoMng.validarTdFlujoBk(tdFlujoBk);

		TdFlujo tdFlujo = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdFlujoBk.getIdflujo() != null && tdFlujoBk.getIdflujo().longValue() > 0) {

				tdFlujo = tdFlujoDao.getTdFlujo(tdFlujoBk.getIdflujo());

				boolean cambios = AuditoriaTdFlujoMng.auditarCambiosTdFlujo(tdFlujoBk, tdFlujo, kyUsuarioMod, null,
						rmtaddress, nivel);

				if (cambios) {
					tdFlujo.setRmtaddressrst(rmtaddress);
					tdFlujo.setIduserModif(kyUsuarioMod);
					tdFlujo.setFechaModif(hoy);
					tdFlujoDao.updateTdFlujo(tdFlujo);
				}
			} else {
				tdFlujoBk.setRmtaddress(rmtaddress);
				tdFlujoBk.setRmtaddressrst(rmtaddress);
				// tdFlujoBk.setSede(kySedeMod);
				// tdFlujoBk.setIdunidad(kyAreaMod);

				tdFlujoBk.setFechaCrea(hoy);
				tdFlujoBk.setIduserCrea(kyUsuarioMod);
				tdFlujoBk.setIduserModif(kyUsuarioMod);
				tdFlujoBk.setFechaModif(hoy);
				// tdFlujoBk.setEstado(1);

				tdFlujo = new TdFlujo();

				FuncionesStaticas.copyPropertiesObject(tdFlujo, tdFlujoBk);
				tdFlujoDao.saveTdFlujo(tdFlujo);

				tdFlujoBk.setIdflujo(tdFlujo.getIdflujo());

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + rmtaddress + " :: " + "CREADO tdFlujo"
						+ " :: " + tdFlujo.getIdflujo().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		return tdFlujoBk;

	}

	public void deleteTdFlujo(TdFlujoBk tdFlujoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			TdFlujo tdFlujo = null;
			if (tdFlujoBk.getIdflujo() != null && tdFlujoBk.getIdflujo().longValue() > 0) {

				tdFlujo = tdFlujoDao.getTdFlujo(tdFlujoBk.getIdflujo());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdFlujo.setIduserModif(kyUsuarioMod);
				tdFlujo.setFechaModif(hoy);
				Integer estadoanterior = tdFlujo.getEstado();
				tdFlujo.setEstado(0);

				tdFlujoDao.updateTdFlujo(tdFlujo);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: " + "ELIMINADO tdFlujo"
								+ " :: " + tdFlujo.getIdflujo().toString() + " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado,
			Long kyUsuarioMod) {
		List<TdFlujoBk> TdFlujoBks = new ArrayList<TdFlujoBk>();
		try {
			List<TdFlujo> TdFlujosss = tdFlujoDao.getXFiltro(idflujo, idflujopadre, idsacc, estado);
			for (TdFlujo TdFlujo : TdFlujosss) {
				TdFlujoBk TdFlujoBk = new TdFlujoBk();
				FuncionesStaticas.copyPropertiesObject(TdFlujoBk, TdFlujo);
				completarTdFlujo(TdFlujoBk);
				TdFlujoBks.add(TdFlujoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFlujoBks;
	}

	@Override
	public List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado, int inicial,
			int MAX, Long kyUsuarioMod) {
		List<TdFlujoBk> TdFlujoBks = new ArrayList<TdFlujoBk>();
		try {
			List<TdFlujo> TdFlujosss = tdFlujoDao.getXFiltro(idflujo, idflujopadre, idsacc, estado, inicial, MAX);
			for (TdFlujo TdFlujo : TdFlujosss) {
				TdFlujoBk TdFlujoBk = new TdFlujoBk();
				FuncionesStaticas.copyPropertiesObject(TdFlujoBk, TdFlujo);
				completarTdFlujo(TdFlujoBk);
				TdFlujoBks.add(TdFlujoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFlujoBks;
	}

	@Override
	public Long getTdFlujoTotalXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado) {
		try {
			Long totalTdFlujosss = tdFlujoDao.getTotalXFiltro(idflujo, idflujopadre, idsacc, estado);

			return totalTdFlujosss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TdFlujoBk> getTdFlujoXIdAt(Long idsacc) {
		List<TdFlujoBk> TdFlujoBks = new ArrayList<TdFlujoBk>();
		try {
			List<TdFlujo> TdFlujosss = tdFlujoDao.getXFiltro(idsacc);
			for (TdFlujo TdFlujo : TdFlujosss) {
				TdFlujoBk TdFlujoBk = new TdFlujoBk();
				FuncionesStaticas.copyPropertiesObject(TdFlujoBk, TdFlujo);
				completarTdFlujo(TdFlujoBk);
				TdFlujoBks.add(TdFlujoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFlujoBks;
	}

	// public void setACLTdFlujoBk(TdFlujoBk tdFlujoBk, Long kyUsuarioMod) {
	// TdFlujoACL tdFlujoACL = new TdFlujoACL();
	// if (kyUsuarioMod != null) {
	// MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
	// if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
	// List<String> roles = msUsuariosBk.getRolesSistema();
	// if (roles.contains(Roles.ADMINISTRADOR) ||
	// roles.contains(Roles.TDFLUJO_CREA)) {
	// tdFlujoACL.setEsEditable(true);
	// tdFlujoACL.setEliminar(true);
	// }
	// }
	// }
	// tdFlujoACL.setEditopcion(2);
	// tdFlujoBk.setTdFlujoACL(tdFlujoACL);
	// }

	// ADICIONALES
	@Override
	public List<MsInstitucionesDto> getMsInstitucionesXRuc(String ruc) {
		List<MsInstitucionesDto> msInstitucionesBks = new ArrayList<MsInstitucionesDto>();
		try {
			List<MsInstituciones> msInstitucioness = msInstitucionesDao.getXFiltro(null, null, null, ruc, null, null,
					null);
			for (MsInstituciones msInstituciones : msInstitucioness) {
				MsInstitucionesDto msInstitucionesBk = new MsInstitucionesDto();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
				completarMsInstitucionesDto(msInstitucionesBk);
				msInstitucionesBks.add(msInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msInstitucionesBks;
	}

	private void completarMsInstitucionesDto(MsInstitucionesDto msInstitucionesBk) {

		try {
			if (msInstitucionesBk.getCodpais() != null && msInstitucionesBk.getCodpais().longValue() > 0) {
				MsPaises msPaises = msPaisesDao.getMsPaises(msInstitucionesBk.getCodpais());
				if (msPaises != null)
					msInstitucionesBk.setCodpaisTxt(msPaises.getPaiNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = 0;
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCoddptoTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0
					&& msInstitucionesBk.getCodprov() != null && msInstitucionesBk.getCodprov().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = msInstitucionesBk.getCodprov().intValue();
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCodprovTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msInstitucionesBk.getCoddpto() != null && msInstitucionesBk.getCoddpto().intValue() > 0
					&& msInstitucionesBk.getCodprov() != null && msInstitucionesBk.getCodprov().intValue() > 0
					&& msInstitucionesBk.getCoddist() != null && msInstitucionesBk.getCoddist().intValue() > 0) {
				int iiCoddpto = msInstitucionesBk.getCoddpto().intValue();
				int iiCodprov = msInstitucionesBk.getCodprov().intValue();
				int iiCoddist = msInstitucionesBk.getCoddist().intValue();
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msInstitucionesBk.setCoddistTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MsInstitucionesDto> getMsInstitucionesXRazonSocial(String qryText) {
		List<MsInstitucionesDto> msInstitucionesBks = new ArrayList<MsInstitucionesDto>();
		try {
			List<MsInstituciones> msInstitucioness = msInstitucionesDao.getXFiltro(null, qryText, null, null, null,
					null, null);
			for (MsInstituciones msInstituciones : msInstitucioness) {
				MsInstitucionesDto msInstitucionesBk = new MsInstitucionesDto();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
				completarMsInstitucionesDto(msInstitucionesBk);
				msInstitucionesBks.add(msInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msInstitucionesBks;
	}

	@Override
	public List<MsInstitucionesDto> getMsInstitucionesXRazonSocial(String tipoentidad, String qryText) {
		List<MsInstitucionesDto> msInstitucionesBks = new ArrayList<MsInstitucionesDto>();
		try {
			List<MsInstituciones> msInstitucioness = msInstitucionesDao.getXFiltroTipoentidad(tipoentidad, qryText);
			for (MsInstituciones msInstituciones : msInstitucioness) {
				MsInstitucionesDto msInstitucionesBk = new MsInstitucionesDto();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesBk, msInstituciones);
				completarMsInstitucionesDto(msInstitucionesBk);
				msInstitucionesBks.add(msInstitucionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msInstitucionesBks;
	}

	////
	@Override
	public List<MsPersonasDto> getMsPersonasXDNI(String dni) {
		List<MsPersonasDto> msPersonasBks = new ArrayList<MsPersonasDto>();
		try {
			List<MsPersonas> msPersonass = msPersonasDao.getXFiltro(null, null, null, null, null, null, dni, null, null,
					null, null);
			for (MsPersonas msPersonas : msPersonass) {
				MsPersonasDto msPersonasBk = new MsPersonasDto();
				FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
				completarMsPersonas(msPersonasBk);
				msPersonasBks.add(msPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPersonasBks;
	}

	private void completarMsPersonas(MsPersonasDto msPersonasBk) {
		try {
			if (msPersonasBk.getTipodocumento() != null && msPersonasBk.getTipodocumento().longValue() > 0) {
				PrtParametros prtParametros = prtParametrosDao.getPrtParametros(msPersonasBk.getTipodocumento());
				if (prtParametros != null)
					msPersonasBk.setTipodocumentoTxt(prtParametros.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = 0;
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCoddptoTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0
					&& msPersonasBk.getCodprov() != null && msPersonasBk.getCodprov().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = msPersonasBk.getCodprov().intValue();
				int iiCoddist = 0;
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCodprovTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (msPersonasBk.getCoddpto() != null && msPersonasBk.getCoddpto().intValue() > 0
					&& msPersonasBk.getCodprov() != null && msPersonasBk.getCodprov().intValue() > 0
					&& msPersonasBk.getCoddist() != null && msPersonasBk.getCoddist().intValue() > 0) {
				int iiCoddpto = msPersonasBk.getCoddpto().intValue();
				int iiCodprov = msPersonasBk.getCodprov().intValue();
				int iiCoddist = msPersonasBk.getCoddist().intValue();
				MsUbigeoId msUbigeoId = new MsUbigeoId();
				msUbigeoId.setCoddpto(iiCoddpto);
				msUbigeoId.setCodprov(iiCodprov);
				msUbigeoId.setCoddist(iiCoddist);
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(msUbigeoId);
				if (msUbigeo != null)
					msPersonasBk.setCoddistTxt(msUbigeo.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MsPersonasDto> getMsPersonasXNombre(String nombre, String apellidoPaterno, String apellidoMaterno) {
		List<MsPersonasDto> msPersonasBks = new ArrayList<MsPersonasDto>();
		try {
			List<MsPersonas> msPersonass = msPersonasDao.getXFiltro(null, apellidoPaterno, apellidoMaterno, nombre,
					null, null, null, null, null, null, null);
			for (MsPersonas msPersonas : msPersonass) {
				MsPersonasDto msPersonasBk = new MsPersonasDto();
				FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
				completarMsPersonas(msPersonasBk);
				msPersonasBks.add(msPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPersonasBks;
	}

	@Override
	public List<MsPersonasDto> getMsPersonasXTelefono(String telefono, String celular) {
		List<MsPersonasDto> msPersonasBks = new ArrayList<MsPersonasDto>();
		try {
			List<MsPersonas> msPersonass = msPersonasDao.getXFiltro(null, null, null, null, null, null, null, telefono,
					celular, null, null);
			for (MsPersonas msPersonas : msPersonass) {
				MsPersonasDto msPersonasBk = new MsPersonasDto();
				FuncionesStaticas.copyPropertiesObject(msPersonasBk, msPersonas);
				completarMsPersonas(msPersonasBk);
				msPersonasBks.add(msPersonasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msPersonasBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosCargos() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("CARGOS");
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosCanales() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("CANALES DE ATENCIÓN");
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosTemas() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TEMAS");
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosTiposEntida() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TIPO DE ENTIDAD");
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosPadre() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXPadre(0L);
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosPadre(String skey) {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcionCero(skey);
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDValorDto> getPrtParametrosPadreNo0(String skey) {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcionCeroNo0(skey);
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	public Map<Integer, String> getEstadosdeflujo() {
		if (estadosdeflujo == null) {
			List<PrtParametros> prtParametrosss = prtParametrosDao.getXDescripcion("ESTADOS DE LOS MOVIMIENTOS");
			if (!prtParametrosss.isEmpty()) {
				estadosdeflujo = new HashMap<Integer, String>();
				for (PrtParametros prtParametros : prtParametrosss) {
					if (prtParametros.getOrden() != null)
						estadosdeflujo.put(prtParametros.getOrden().intValue(), prtParametros.getDescripcion());
				}
			}
		}
		return estadosdeflujo;
	}

	public Map<Long, MsUnidadesOrgBk> getMsUnidadesOrgBkCache() {
		if (msUnidadesOrgBkCache == null) {
			try {
				List<MsUnidadesOrg> msUnidadesOrgs = msUnidadesOrgDao.getActivasMsUnidadesOrg();
				msUnidadesOrgBkCache = new HashMap<Long, MsUnidadesOrgBk>();
				for (MsUnidadesOrg msUnidadesOrg : msUnidadesOrgs) {
					MsUnidadesOrgBk msUnidadesOrgBk = new MsUnidadesOrgBk();
					FuncionesStaticas.copyPropertiesObject(msUnidadesOrgBk, msUnidadesOrg);
					completarMsUnidadesOrg(msUnidadesOrgBk);
					msUnidadesOrgBkCache.put(msUnidadesOrgBk.getIdunidad(), msUnidadesOrgBk);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msUnidadesOrgBkCache;
	}

	public MsUnidadesOrgBk getMsUnidadesOrgBkXidCH(Long id) {
		if (id == null)
			return null;
		MsUnidadesOrgBk msUnidadesOrgBk = null;
		if (getMsUnidadesOrgBkCache() != null) {
			msUnidadesOrgBk = getMsUnidadesOrgBkCache().get(id);
		}
		if (msUnidadesOrgBk == null) {
			MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(id);
			if (msUnidadesOrg != null) {
				msUnidadesOrgBk = new MsUnidadesOrgBk();
				FuncionesStaticas.copyPropertiesObject(msUnidadesOrgBk, msUnidadesOrg);
				completarMsUnidadesOrg(msUnidadesOrgBk);
			}
		}
		return msUnidadesOrgBk;
	}

	@Override
	public Map<String, Long> getTiemposestados() {
		if (tiemposestados == null) {
			List<PrtParametros> prtParametrosss = prtParametrosDao.getXDescripcion("TIEMPO DE ESTADIA");
			if (!prtParametrosss.isEmpty()) {
				tiemposestados = new HashMap<String, Long>();
				for (PrtParametros prtParametros : prtParametrosss) {
					if (prtParametros.getOrden() != null && prtParametros.getDescripcion() != null)
						tiemposestados.put(prtParametros.getDescripcion(), prtParametros.getOrden().longValue());
				}
			}
		}
		return tiemposestados;
	}

	/**
	 * TD_FERIADOS SERVICIO: DÍAS NO LABORABLES
	 */
	public TdFeriadosBk getTdFeriadosBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdFeriados tdFeriados = tdFeriadosDao.getTdFeriados(id);
		TdFeriadosBk tdFeriadosBk = null;
		if (tdFeriados != null) {
			tdFeriadosBk = new TdFeriadosBk();
			FuncionesStaticas.copyPropertiesObject(tdFeriadosBk, tdFeriados);
			completarTdFeriados(tdFeriadosBk, kyUsuarioMod);
		}
		return tdFeriadosBk;
	}

	public List<TdFeriadosBk> getAllTdFeriadosActivos(Long kyUsuarioMod) {
		List<TdFeriadosBk> tdFeriadosBks = new ArrayList<TdFeriadosBk>();
		try {
			List<TdFeriados> tdFeriadoss = tdFeriadosDao.getActivasTdFeriados();
			for (TdFeriados tdFeriados : tdFeriadoss) {
				TdFeriadosBk tdFeriadosBk = new TdFeriadosBk();
				FuncionesStaticas.copyPropertiesObject(tdFeriadosBk, tdFeriados);
				completarTdFeriados(tdFeriadosBk, kyUsuarioMod);
				tdFeriadosBks.add(tdFeriadosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdFeriadosBks;
	}

	@Override
	public List<TdFeriadosBk> getAllTdFeriadosActivosCero(Long kyUsuarioMod) {
		List<TdFeriadosBk> tdFeriadosBks = new ArrayList<TdFeriadosBk>();
		try {
			List<TdFeriados> tdFeriadoss = tdFeriadosDao.getActivasTdFeriadosCero();
			for (TdFeriados tdFeriados : tdFeriadoss) {
				TdFeriadosBk tdFeriadosBk = new TdFeriadosBk();
				FuncionesStaticas.copyPropertiesObject(tdFeriadosBk, tdFeriados);
				completarTdFeriados(tdFeriadosBk, kyUsuarioMod);
				tdFeriadosBks.add(tdFeriadosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdFeriadosBks;
	}

	private void completarTdFeriados(TdFeriadosBk tdFeriadosBk, Long kyUsuarioMod) {
		try {
			if (tdFeriadosBk.getIduserCrea() != null && tdFeriadosBk.getIduserCrea().longValue() > 0) {
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFeriadosBk.getIduserCrea());
				if (msUsuarios != null)
					tdFeriadosBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFeriadosBk.getIduserCrea());
				// if(msUsuarios!=null)
				// tdFeriadosBk.setIduserCreaTxt(msUsuarios.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdFeriadosBk.getIduserModif() != null && tdFeriadosBk.getIduserModif().longValue() > 0) {
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFeriadosBk.getIduserModif());
				if (msUsuarios != null)
					tdFeriadosBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				// MsUsuarios msUsuarios =
				// msUsuariosDao.getMsUsuarios(tdFeriadosBk.getIduserModif());
				// if(msUsuarios!=null)
				// tdFeriadosBk.setIduserModifTxt(msUsuarios.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (kyUsuarioMod != null && kyUsuarioMod.longValue() > 0)
			setACLTdFeriadosBk(tdFeriadosBk, kyUsuarioMod);

		// try{
		// System.out.println("COMPLETAR tdFeriadosBk");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		//
	}

	public TdFeriadosBk saveorupdateTdFeriadosBk(TdFeriadosBk tdFeriadosBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String kySedeMod, String rmtaddress) throws Validador {
		ValidacionTdFeriadosMng.validarTdFeriadosBk(tdFeriadosBk);
		TdFeriados tdFeriados = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		int nivel = 1;
		try {
			if (tdFeriadosBk.getIdferiado() != null && tdFeriadosBk.getIdferiado().longValue() > 0) {
				tdFeriados = tdFeriadosDao.getTdFeriados(tdFeriadosBk.getIdferiado());
				boolean cambios = AuditoriaTdFeriadosMng.auditarCambiosTdFeriados(tdFeriadosBk, tdFeriados,
						kyUsuarioMod, user, rmtaddress, nivel);
				if (cambios) {
					tdFeriados.setEstado(1);
					tdFeriados.setRmtaddressrs(rmtaddress);
					tdFeriados.setIduserModif(kyUsuarioMod);
					tdFeriados.setFechaModif(hoy);
					tdFeriadosDao.updateTdFeriados(tdFeriados);
				}
			} else {
				tdFeriadosBk.setRmtaddress(rmtaddress);
				tdFeriadosBk.setRmtaddressrs(rmtaddress);
				// tdFeriadosBk.setSede(kySedeMod);
				// tdFeriadosBk.setIdunidad(kyAreaMod);
				tdFeriadosBk.setFechaCrea(hoy);
				tdFeriadosBk.setIduserCrea(kyUsuarioMod);
				tdFeriadosBk.setIduserModif(kyUsuarioMod);
				tdFeriadosBk.setFechaModif(hoy);
				tdFeriadosBk.setEstado(1);
				tdFeriados = new TdFeriados();
				FuncionesStaticas.copyPropertiesObject(tdFeriados, tdFeriadosBk);
				tdFeriadosDao.saveTdFeriados(tdFeriados);
				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: " + "CREADO tdFeriados"
								+ " :: " + tdFeriados.getIdferiado().toString() + " :: " + "0" + " :: " + "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
		diasFeriados = null;
		tdFeriadosBk = getTdFeriadosBkXid(tdFeriados.getIdferiado(), kyUsuarioMod);
		return tdFeriadosBk;
	}

	public void deleteTdFeriados(TdFeriadosBk tdFeriadosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			TdFeriados tdFeriados = null;
			if (tdFeriadosBk.getIdferiado() != null && tdFeriadosBk.getIdferiado().longValue() > 0) {
				tdFeriados = tdFeriadosDao.getTdFeriados(tdFeriadosBk.getIdferiado());
				Timestamp hoy = new Timestamp(System.currentTimeMillis());
				tdFeriados.setIduserModif(kyUsuarioMod);
				tdFeriados.setFechaModif(hoy);
				Integer estadoanterior = tdFeriados.getEstado();
				tdFeriados.setEstado(0);
				tdFeriadosDao.updateTdFeriados(tdFeriados);
				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdFeriados" + " :: " + tdFeriados.getIdferiado().toString() + " :: "
								+ estadoanterior + " :: " + "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdFeriadosBk> getTdFeriadosXFiltro(Timestamp fechaFeriado, String descricion, Integer estado,
			Long kyUsuarioMod) {
		List<TdFeriadosBk> TdFeriadosBks = new ArrayList<TdFeriadosBk>();
		try {
			List<TdFeriados> TdFeriadossss = tdFeriadosDao.getXFiltro(fechaFeriado, descricion, estado);
			for (TdFeriados TdFeriados : TdFeriadossss) {
				TdFeriadosBk TdFeriadosBk = new TdFeriadosBk();
				FuncionesStaticas.copyPropertiesObject(TdFeriadosBk, TdFeriados);
				completarTdFeriados(TdFeriadosBk, kyUsuarioMod);
				TdFeriadosBks.add(TdFeriadosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFeriadosBks;
	}

	@Override
	public List<TdFeriadosBk> getTdFeriadosXFiltro(Timestamp fechaFeriado, String descricion, Integer estado,
			int inicial, int MAX, Long kyUsuarioMod) {
		List<TdFeriadosBk> TdFeriadosBks = new ArrayList<TdFeriadosBk>();
		try {
			List<TdFeriados> TdFeriadossss = tdFeriadosDao.getXFiltro(fechaFeriado, descricion, estado, inicial, MAX);
			for (TdFeriados TdFeriados : TdFeriadossss) {
				TdFeriadosBk TdFeriadosBk = new TdFeriadosBk();
				FuncionesStaticas.copyPropertiesObject(TdFeriadosBk, TdFeriados);
				completarTdFeriados(TdFeriadosBk, kyUsuarioMod);
				TdFeriadosBks.add(TdFeriadosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFeriadosBks;
	}

	@Override
	public Long getTdFeriadosTotalXFiltro(Timestamp fechaFeriado, String descricion, Integer estado) {
		try {
			Long totalTdFeriadossss = tdFeriadosDao.getTotalXFiltro(fechaFeriado, descricion, estado);

			return totalTdFeriadossss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setACLTdFeriadosBk(TdFeriadosBk tdFeriadosBk, Long kyUsuarioMod) {
		TdFeriadosACL tdFeriadosACL = new TdFeriadosACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msUsuariosBk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msUsuariosBk != null && msUsuariosBk.getRolesSistema() != null) {
				List<String> roles = msUsuariosBk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDFERIADOS_CREA)) {
					tdFeriadosACL.setEsEditable(true);
					tdFeriadosACL.setEliminar(true);
				} else if (roles.contains(Roles.TDFERIADOS_VE)) {
					tdFeriadosACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDFERIADOS_CREA)) {
					tdFeriadosACL.setEditopcion(1);
				} else {
					tdFeriadosACL.setEditopcion(2);
				}
			} else {
				tdFeriadosACL.setEditopcion(2);
			}
		} else {
			tdFeriadosACL.setEditopcion(2);
		}
		tdFeriadosBk.setTdFeriadosACL(tdFeriadosACL);
	}

	@Override
	public List<Timestamp> getDiasFeriados() {
		if (diasFeriados == null) {
			List<TdFeriados> taFeriados = tdFeriadosDao.getActivasTdFeriados();
			diasFeriados = new ArrayList<Timestamp>();
			for (TdFeriados taFeriado : taFeriados) {
				diasFeriados.add(taFeriado.getFechaFeriado());
			}
		}
		return diasFeriados;
	}

	public Map<Integer, String> getEstadosdeatencion() {
		if (estadosdeatencion == null) {
			List<PrtParametros> prtParametrosss = prtParametrosDao.getXDescripcion("ESTADOS DE LAS ATENCIONES");
			if (!prtParametrosss.isEmpty()) {
				estadosdeatencion = new HashMap<Integer, String>();
				for (PrtParametros prtParametros : prtParametrosss) {
					if (prtParametros.getOrden() != null)
						estadosdeatencion.put(prtParametros.getOrden().intValue(), prtParametros.getDescripcion());
				}
			}
		}
		return estadosdeatencion;
	}

	@Override
	public List<MsUsuariosBk> getUsuariosquecrean() {
		if (usuariosquecreanatenciones == null) {
			usuariosquecreanatenciones = getMsUsuariosXCrea();
		}
		return usuariosquecreanatenciones;
	}

	@Override
	public List<MsUsuariosBk> getUsuariosdearea() {
		if (usuariosdearea == null) {
			usuariosdearea = getMsUsuariosXUsuariosArea();
		}
		return usuariosdearea;
	}

	public Map<String, String> getEmailservice() {
		if (emailservice == null) {
			List<PrtParametros> prtParametrosss = prtParametrosDao.getXDescripcion("SERVICIO DE EMAIL");
			if (!prtParametrosss.isEmpty()) {
				emailservice = new HashMap<String, String>();
				for (PrtParametros prtParametros : prtParametrosss) {
					if (prtParametros.getDescripcion() != null) {
						emailservice.put(prtParametros.getDescripcion(), prtParametros.getDescripcioncorta());
					}
				}
			}
		}
		return emailservice;
	}
	// ----

	public boolean isDebugmail() {
		return debugmail;
	}

	@Override
	public void setDebugmail(boolean debugmail) {
		this.debugmail = debugmail;
	}

	@Override
	public Long getxDefectoCodpais() {
		if (xDefectoCodpais == null) {
			MsPaises msPaises = msPaisesDao.getByNombreMsPaises("PERÚ");
			if (msPaises != null) {
				xDefectoCodpais = msPaises.getPaiPk();
			}
		}
		return xDefectoCodpais;
	}

	@Override
	public Integer getxDefectoCoddpto() {
		if (xDefectoCoddpto == null || xDefectoCoddpto.intValue() <= 0) {
			MsUbigeo msUbigeo = msUbigeoDao.getDepartamentosXNombre("LIMA");
			if (msUbigeo != null) {
				xDefectoCoddpto = msUbigeo.getId().getCoddpto();
			}

		}
		return xDefectoCoddpto;
	}

	@Override
	public Integer getxDefectoCodprov() {
		if (xDefectoCodprov == null || xDefectoCodprov.intValue() <= 0) {
			Integer coddpto = getxDefectoCoddpto();
			if (coddpto != null) {
				if (coddpto.intValue() > 0) {
					MsUbigeo msUbigeo = msUbigeoDao.getProvinciasXNombre(coddpto, "LIMA");
					if (msUbigeo != null) {
						xDefectoCodprov = msUbigeo.getId().getCodprov();
					}
				}
			}
		}
		return xDefectoCodprov;
	}

	@Override
	public Integer getxDefectoCoddist() {
		if (xDefectoCoddist == null || xDefectoCoddist.intValue() <= 0) {
			Integer coddpto = getxDefectoCoddpto();
			Integer codprov = getxDefectoCodprov();
			if (coddpto != null && codprov != null) {
				if (coddpto.intValue() > 0 && codprov.intValue() > 0) {
					MsUbigeo msUbigeo = msUbigeoDao.getDistritosXNombre(coddpto, codprov, "LIMA");
					if (msUbigeo != null) {
						xDefectoCoddist = msUbigeo.getId().getCoddist();
					}
				}
			}
		}
		return xDefectoCoddist;
	}

	@Override
	public List<IIDValorDto> getDepartamentosV() {
		List<IIDValorDto> ubigeoItemDtos = new ArrayList<IIDValorDto>();
		List<MsUbigeo> departamentos = msUbigeoDao.getDepartamentos();
		for (MsUbigeo acMsUbigeo : departamentos) {
			IIDValorDto ubigeoItemDto = new IIDValorDto(acMsUbigeo.getId().getCoddpto(), acMsUbigeo.getNombre());
			ubigeoItemDtos.add(ubigeoItemDto);
		}
		return ubigeoItemDtos;
	}

	@Override
	public List<IIDValorDto> getProvinciasV(Integer id_dpto) {
		List<IIDValorDto> ubigeoItemProv = new ArrayList<IIDValorDto>();
		List<MsUbigeo> departamentos = msUbigeoDao.getProvincias(id_dpto);
		for (MsUbigeo acMsUbigeo : departamentos) {
			IIDValorDto ubigeoItemDto = new IIDValorDto(acMsUbigeo.getId().getCodprov(), acMsUbigeo.getNombre());
			ubigeoItemProv.add(ubigeoItemDto);
		}
		return ubigeoItemProv;
	}

	@Override
	public List<IIDValorDto> getDistritosV(Integer id_dpto, Integer id_prov) {
		List<IIDValorDto> ubigeoItemDist = new ArrayList<IIDValorDto>();
		List<MsUbigeo> departamentos = msUbigeoDao.getDistritos(id_dpto, id_prov);
		for (MsUbigeo acMsUbigeo : departamentos) {
			IIDValorDto ubigeoItemDto = new IIDValorDto(acMsUbigeo.getId().getCoddist(), acMsUbigeo.getNombre());
			ubigeoItemDist.add(ubigeoItemDto);
		}
		return ubigeoItemDist;
	}

	@Override
	public String getEndpointVentanilla() {
		if (endpointVentanilla == null) {
			List<PrtParametros> prtParametrosss = prtParametrosDao.getXDescripcion("SERVICIO DE TRAMITE");
			if (!prtParametrosss.isEmpty()) {
				for (PrtParametros prtParametros : prtParametrosss) {
					if (prtParametros.getDescripcion() != null
							&& prtParametros.getDescripcion().equalsIgnoreCase("endpointVentanilla")) {
						endpointVentanilla = prtParametros.getDescripcioncorta();
					}
				}
			}
		}
		return endpointVentanilla;
	}

	@Override
	public Progreso getProgreso() {
		return progreso;
	}

	public void ejecuteinidate() {

	}

	public void ejecuteIniCache() {

	}

	@Override
	public String previwnotificarTdAtencionesBk(Long idsacc, Long kyUsuarioMod, MsUsuariosBk usuariologueado,
			String rmtaddress, String observacion, boolean esEntidad, Long idprovee, List<TdAnexosBk> tdAnexosBkss)
			throws Validador {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TD_UBICACIONES SERVICIO: UBICACIONES ASIGADOS A LOS EVENTOS
	 */
	@Override
	public TdUbicacionesBk getTdUbicacionesBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdUbicaciones tdUbicaciones = tdUbicacionesDao.getTdUbicaciones(id);
		TdUbicacionesBk tdUbicacionesBk = null;
		if (tdUbicaciones != null) {
			tdUbicacionesBk = new TdUbicacionesBk();
			FuncionesStaticas.copyPropertiesObject(tdUbicacionesBk, tdUbicaciones);
			completarTdUbicaciones(tdUbicacionesBk);
			setACLTdUbicacionesBk(tdUbicacionesBk, kyUsuarioMod);
		}
		return tdUbicacionesBk;
	}

	@Override
	public List<TdUbicacionesBk> getAllTdUbicacionesActivos(Long kyUsuarioMod) {
		List<TdUbicacionesBk> tdUbicacionesBkss = new ArrayList<TdUbicacionesBk>();
		try {
			List<TdUbicaciones> tdUbicacioness = tdUbicacionesDao.getActivasTdUbicaciones();
			for (TdUbicaciones tdUbicaciones : tdUbicacioness) {
				TdUbicacionesBk tdUbicacionesBk = new TdUbicacionesBk();
				FuncionesStaticas.copyPropertiesObject(tdUbicacionesBk, tdUbicaciones);
				completarTdUbicaciones(tdUbicacionesBk);
				setACLTdUbicacionesBk(tdUbicacionesBk, kyUsuarioMod);
				tdUbicacionesBkss.add(tdUbicacionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdUbicacionesBkss;
	}

	@Override
	public List<TdUbicacionesBk> getAllTdUbicacionesActivosCero(Long kyUsuarioMod) {
		List<TdUbicacionesBk> tdUbicacionesBkss = new ArrayList<TdUbicacionesBk>();
		try {
			List<TdUbicaciones> tdUbicacioness = tdUbicacionesDao.getActivasTdUbicacionesCero();
			for (TdUbicaciones tdUbicaciones : tdUbicacioness) {
				TdUbicacionesBk tdUbicacionesBk = new TdUbicacionesBk();
				FuncionesStaticas.copyPropertiesObject(tdUbicacionesBk, tdUbicaciones);
				completarTdUbicaciones(tdUbicacionesBk);
				setACLTdUbicacionesBk(tdUbicacionesBk, kyUsuarioMod);
				tdUbicacionesBkss.add(tdUbicacionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdUbicacionesBkss;
	}

	private void completarTdUbicaciones(TdUbicacionesBk tdUbicacionesBk) {
		// try{
		// if(tdUbicacionesBk.getIdevent()!=null &&
		// tdUbicacionesBk.getIdevent().longValue()>0){
		// TdEvento tdEvento =
		// tdEventoDao.getTdEvento(tdUbicacionesBk.getIdevent());
		// if(tdEvento!=null)
		// tdUbicacionesBk.setIdeventTxt(tdEvento.getNumeroSid()+"-"+tdEvento.getNumeroAnioSid());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		try {
			if (tdUbicacionesBk.getCodpais() != null && tdUbicacionesBk.getCodpais().longValue() > 0) {
				MsPaises msPaises = msPaisesDao.getMsPaises(tdUbicacionesBk.getCodpais());
				if (msPaises != null)
					tdUbicacionesBk.setCodpaisTxt(msPaises.getPaiNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdUbicacionesBk.getCoddpto() != null && tdUbicacionesBk.getCoddpto().longValue() > 0) {
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(new MsUbigeoId(tdUbicacionesBk.getCoddpto(), 0, 0));
				if (msUbigeo != null)
					tdUbicacionesBk.setCoddptoTxt(msUbigeo.getNombre());
				if (tdUbicacionesBk.getCodprov() != null && tdUbicacionesBk.getCodprov().longValue() > 0) {
					MsUbigeo msUbigeop = msUbigeoDao
							.getMsUbigeo(new MsUbigeoId(tdUbicacionesBk.getCoddpto(), tdUbicacionesBk.getCodprov(), 0));
					if (msUbigeop != null)
						tdUbicacionesBk.setCodprovTxt(msUbigeop.getNombre());
					if (tdUbicacionesBk.getCoddist() != null && tdUbicacionesBk.getCoddist().longValue() > 0) {
						MsUbigeo msUbigeodi = msUbigeoDao.getMsUbigeo(new MsUbigeoId(tdUbicacionesBk.getCoddpto(),
								tdUbicacionesBk.getCodprov(), tdUbicacionesBk.getCoddist()));
						if (msUbigeodi != null)
							tdUbicacionesBk.setCoddistTxt(msUbigeodi.getNombre());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try{
		// if(tdUbicacionesBk.getIduserCrea()!=null &&
		// tdUbicacionesBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdUbicacionesBk.getIduserCrea());
		// if(msUsuarios!=null)
		// tdUbicacionesBk.setIduserCreaTxt(FuncionesStaticas.geNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(tdUbicacionesBk.getIduserModif()!=null &&
		// tdUbicacionesBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdUbicacionesBk.getIduserModif());
		// if(msUsuarios!=null)
		// tdUbicacionesBk.setIduserModifTxt(FuncionesStaticas.geNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public TdUbicacionesBk saveorupdateTdUbicacionesBk(TdUbicacionesBk tdUbicacionesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionTdUbicacionesMng.validarTdUbicacionesBk(tdUbicacionesBk);

		TdUbicaciones tdUbicaciones = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdUbicacionesBk.getIdubicaciones() != null && tdUbicacionesBk.getIdubicaciones().longValue() > 0) {

				tdUbicaciones = tdUbicacionesDao.getTdUbicaciones(tdUbicacionesBk.getIdubicaciones());

				boolean cambios = AuditoriaTdUbicacionesMng.auditarCambiosTdUbicaciones(tdUbicacionesBk, tdUbicaciones,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					tdUbicaciones.setRmtaddressrst(rmtaddress);
					tdUbicaciones.setIduserModif(kyUsuarioMod);
					tdUbicaciones.setFechaModif(hoy);
					tdUbicacionesDao.updateTdUbicaciones(tdUbicaciones);
				}
			} else {
				tdUbicacionesBk.setRmtaddress(rmtaddress);
				tdUbicacionesBk.setRmtaddressrst(rmtaddress);

				tdUbicacionesBk.setFechaCrea(hoy);
				tdUbicacionesBk.setIduserCrea(kyUsuarioMod);
				tdUbicacionesBk.setIduserModif(kyUsuarioMod);
				tdUbicacionesBk.setFechaModif(hoy);
				tdUbicacionesBk.setEstado(1);

				tdUbicaciones = new TdUbicaciones();

				FuncionesStaticas.copyPropertiesObject(tdUbicaciones, tdUbicacionesBk);
				tdUbicacionesDao.saveTdUbicaciones(tdUbicaciones);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO tdUbicaciones" + " :: " + tdUbicaciones.getIdubicaciones().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdUbicacionesBk = getTdUbicacionesBkXid(tdUbicaciones.getIdubicaciones(), kyUsuarioMod);
		return tdUbicacionesBk;
	}

	@Override
	public void deleteTdUbicaciones(TdUbicacionesBk tdUbicacionesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			TdUbicaciones tdUbicaciones = null;
			if (tdUbicacionesBk.getIdubicaciones() != null && tdUbicacionesBk.getIdubicaciones().longValue() > 0) {

				tdUbicaciones = tdUbicacionesDao.getTdUbicaciones(tdUbicacionesBk.getIdubicaciones());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdUbicaciones.setIduserModif(kyUsuarioMod);
				tdUbicaciones.setFechaModif(hoy);
				Integer estadoanterior = tdUbicaciones.getEstado();
				tdUbicaciones.setEstado(0);

				tdUbicacionesDao.updateTdUbicaciones(tdUbicaciones);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdUbicaciones" + " :: " + tdUbicaciones.getIdubicaciones().toString()
								+ " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdUbicacionesBk> getTdUbicacionesXFiltro(Long idubicaciones, Long idevent, String ubicacion,
			Integer estado, Long kyUsuarioMod) {
		List<TdUbicacionesBk> tdUbicacionesBkss = new ArrayList<TdUbicacionesBk>();
		try {
			List<TdUbicaciones> tdUbicacionessss = tdUbicacionesDao.getXFiltro(idubicaciones, idevent, ubicacion,
					estado);
			for (TdUbicaciones tdUbicaciones : tdUbicacionessss) {
				TdUbicacionesBk tdUbicacionesBk = new TdUbicacionesBk();
				FuncionesStaticas.copyPropertiesObject(tdUbicacionesBk, tdUbicaciones);
				completarTdUbicaciones(tdUbicacionesBk);
				setACLTdUbicacionesBk(tdUbicacionesBk, kyUsuarioMod);
				tdUbicacionesBkss.add(tdUbicacionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdUbicacionesBkss;
	}

	@Override
	public List<TdUbicacionesBk> getTdUbicacionesXFiltro(Long idubicaciones, Long idevent, String ubicacion,
			Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<TdUbicacionesBk> tdUbicacionesBkss = new ArrayList<TdUbicacionesBk>();
		try {
			List<TdUbicaciones> tdUbicacionessss = tdUbicacionesDao.getXFiltro(idubicaciones, idevent, ubicacion,
					estado, inicial, MAX);
			for (TdUbicaciones tdUbicaciones : tdUbicacionessss) {
				TdUbicacionesBk tdUbicacionesBk = new TdUbicacionesBk();
				FuncionesStaticas.copyPropertiesObject(tdUbicacionesBk, tdUbicaciones);
				completarTdUbicaciones(tdUbicacionesBk);
				setACLTdUbicacionesBk(tdUbicacionesBk, kyUsuarioMod);
				tdUbicacionesBkss.add(tdUbicacionesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdUbicacionesBkss;
	}

	@Override
	public Long getTdUbicacionesTotalXFiltro(Long idubicaciones, Long idevent, String ubicacion, Integer estado,
			Long kyUsuarioMod) {
		try {
			Long totalTdUbicacionessss = tdUbicacionesDao.getTotalXFiltro(idubicaciones, idevent, ubicacion, estado);
			return totalTdUbicacionessss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLTdUbicacionesBk(TdUbicacionesBk tdUbicacionesBk, Long kyUsuarioMod) {
		TdUbicacionesACL tdUbicacionesACL = new TdUbicacionesACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDUBICACIONES_CREA)) {
					tdUbicacionesACL.setEsEditable(true);
					tdUbicacionesACL.setEliminar(true);
				} else if (roles.contains(Roles.TDUBICACIONES_VE)) {
					tdUbicacionesACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDUBICACIONES_CREA)) {
					tdUbicacionesACL.setEditopcion(1);
				} else {
					tdUbicacionesACL.setEditopcion(2);
				}
			} else {
				tdUbicacionesACL.setEditopcion(2);
			}
		} else {
			tdUbicacionesACL.setEditopcion(2);
		}
		tdUbicacionesBk.setTdUbicacionesACL(tdUbicacionesACL);
	}

	/// ADICIONALES

	@Override
	public List<IDValorDto> getMsPaisesPaiPk() {
		if (msPaisesPaiPkListaCache == null) {
			List<MsPaises> msPaisessss = msPaisesDao.getListaPaiPk();
			msPaisesPaiPkListaCache = new ArrayList<IDValorDto>();
			for (MsPaises msPaises : msPaisessss) {
				IDValorDto paiPkDto = new IDValorDto(msPaises.getPaiPk(), msPaises.getPaiNombre());
				msPaisesPaiPkListaCache.add(paiPkDto);
			}
		}
		return msPaisesPaiPkListaCache;
	}

	/**
	 * TD_PARTICIPANTES SERVICIO: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS
	 * EVENTOS
	 */
	@Override
	public TdParticipantesBk getTdParticipantesBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdParticipantes tdParticipantes = tdParticipantesDao.getTdParticipantes(id);
		TdParticipantesBk tdParticipantesBk = null;
		if (tdParticipantes != null) {
			tdParticipantesBk = new TdParticipantesBk();
			FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
			completarTdParticipantes(tdParticipantesBk);
			setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
		}
		return tdParticipantesBk;
	}

	@Override
	public List<TdParticipantesBk> getAllTdParticipantesActivos(Long kyUsuarioMod) {
		List<TdParticipantesBk> tdParticipantesBkss = new ArrayList<TdParticipantesBk>();
		try {
			List<TdParticipantes> tdParticipantess = tdParticipantesDao.getActivasTdParticipantes();
			for (TdParticipantes tdParticipantes : tdParticipantess) {
				TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
				FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
				completarTdParticipantes(tdParticipantesBk);
				setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
				tdParticipantesBkss.add(tdParticipantesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdParticipantesBkss;
	}

	@Override
	public List<TdParticipantesBk> getAllTdParticipantesActivosCero(Long kyUsuarioMod) {
		List<TdParticipantesBk> tdParticipantesBkss = new ArrayList<TdParticipantesBk>();
		try {
			List<TdParticipantes> tdParticipantess = tdParticipantesDao.getActivasTdParticipantesCero();
			for (TdParticipantes tdParticipantes : tdParticipantess) {
				TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
				FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
				completarTdParticipantes(tdParticipantesBk);
				setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
				tdParticipantesBkss.add(tdParticipantesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdParticipantesBkss;
	}

	private void completarTdParticipantes(TdParticipantesBk tdParticipantesBk) {
		try {
			if (tdParticipantesBk.getIdusuarioIdproveeIdperson() != null
					&& tdParticipantesBk.getIdusuarioIdproveeIdperson().longValue() > 0) {
				if (tdParticipantesBk.getTipo() == null) {
					MsUsuarios msUsuarios = msUsuariosDao
							.getMsUsuarios(tdParticipantesBk.getIdusuarioIdproveeIdperson());
					if (msUsuarios != null)
						tdParticipantesBk
								.setIdusuarioIdproveeIdpersonTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				} else if (tdParticipantesBk.getTipo().intValue() == 0) {
					MsUsuarios msUsuarios = msUsuariosDao
							.getMsUsuarios(tdParticipantesBk.getIdusuarioIdproveeIdperson());
					if (msUsuarios != null)
						tdParticipantesBk
								.setIdusuarioIdproveeIdpersonTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
				} else if (tdParticipantesBk.getTipo().intValue() == 1) {
					MsInstituciones msInstituciones = msInstitucionesDao
							.getMsInstituciones(tdParticipantesBk.getIdusuarioIdproveeIdperson());
					if (msInstituciones != null)
						tdParticipantesBk.setIdusuarioIdproveeIdpersonTxt(msInstituciones.getRazonSocial());
				} else if (tdParticipantesBk.getTipo().intValue() == 2) {
					MsPersonas msPersonas = msPersonasDao
							.getMsPersonas(tdParticipantesBk.getIdusuarioIdproveeIdperson());
					if (msPersonas != null)
						tdParticipantesBk.setIdusuarioIdproveeIdpersonTxt(
								FuncionesStaticas.getNombreCompletoPersona(msPersonas));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try{
		// if(tdParticipantesBk.getIduserCrea()!=null &&
		// tdParticipantesBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdParticipantesBk.getIduserCrea());
		// if(msUsuarios!=null)
		// tdParticipantesBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(tdParticipantesBk.getIduserModif()!=null &&
		// tdParticipantesBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdParticipantesBk.getIduserModif());
		// if(msUsuarios!=null)
		// tdParticipantesBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public TdParticipantesBk saveorupdateTdParticipantesBk(TdParticipantesBk tdParticipantesBk, String user,
			Long kyUsuarioMod, Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionTdParticipantesMng.validarTdParticipantesBk(tdParticipantesBk);

		TdParticipantes tdParticipantes = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdParticipantesBk.getIdparticipantes() != null
					&& tdParticipantesBk.getIdparticipantes().longValue() > 0) {

				tdParticipantes = tdParticipantesDao.getTdParticipantes(tdParticipantesBk.getIdparticipantes());

				boolean cambios = AuditoriaTdParticipantesMng.auditarCambiosTdParticipantes(tdParticipantesBk,
						tdParticipantes, kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					tdParticipantes.setRmtaddressrst(rmtaddress);
					tdParticipantes.setIduserModif(kyUsuarioMod);
					tdParticipantes.setFechaModif(hoy);
					tdParticipantesDao.updateTdParticipantes(tdParticipantes);
				}
			} else {
				tdParticipantesBk.setRmtaddress(rmtaddress);
				tdParticipantesBk.setRmtaddressrst(rmtaddress);

				tdParticipantesBk.setFechaCrea(hoy);
				tdParticipantesBk.setIduserCrea(kyUsuarioMod);
				tdParticipantesBk.setIduserModif(kyUsuarioMod);
				tdParticipantesBk.setFechaModif(hoy);
				tdParticipantesBk.setEstado(1);

				tdParticipantes = new TdParticipantes();

				FuncionesStaticas.copyPropertiesObject(tdParticipantes, tdParticipantesBk);
				tdParticipantesDao.saveTdParticipantes(tdParticipantes);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO tdParticipantes" + " :: " + tdParticipantes.getIdparticipantes().toString()
								+ " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdParticipantesBk = getTdParticipantesBkXid(tdParticipantes.getIdparticipantes(), kyUsuarioMod);
		return tdParticipantesBk;
	}

	@Override
	public void deleteTdParticipantes(TdParticipantesBk tdParticipantesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {
		try {
			TdParticipantes tdParticipantes = null;
			if (tdParticipantesBk.getIdparticipantes() != null
					&& tdParticipantesBk.getIdparticipantes().longValue() > 0) {

				tdParticipantes = tdParticipantesDao.getTdParticipantes(tdParticipantesBk.getIdparticipantes());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdParticipantes.setIduserModif(kyUsuarioMod);
				tdParticipantes.setFechaModif(hoy);
				Integer estadoanterior = tdParticipantes.getEstado();
				tdParticipantes.setEstado(0);

				tdParticipantesDao.updateTdParticipantes(tdParticipantes);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdParticipantes" + " :: " + tdParticipantes.getIdparticipantes().toString()
								+ " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdParticipantesBk> getTdParticipantesXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson,
			String nombresrazonsocial, Integer tipo, Integer estado, Long kyUsuarioMod) {
		List<TdParticipantesBk> tdParticipantesBkss = new ArrayList<TdParticipantesBk>();
		try {
			List<TdParticipantes> tdParticipantessss = tdParticipantesDao.getXFiltro(idparticipantes,
					idusuarioIdproveeIdperson, nombresrazonsocial, tipo, estado);
			for (TdParticipantes tdParticipantes : tdParticipantessss) {
				TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
				FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
				completarTdParticipantes(tdParticipantesBk);
				setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
				tdParticipantesBkss.add(tdParticipantesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdParticipantesBkss;
	}

	@Override
	public List<TdParticipantesBk> getTdParticipantesXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson,
			String nombresrazonsocial, Integer tipo, Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<TdParticipantesBk> tdParticipantesBkss = new ArrayList<TdParticipantesBk>();
		try {
			List<TdParticipantes> tdParticipantessss = tdParticipantesDao.getXFiltro(idparticipantes,
					idusuarioIdproveeIdperson, nombresrazonsocial, tipo, estado, inicial, MAX);
			for (TdParticipantes tdParticipantes : tdParticipantessss) {
				TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
				FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
				completarTdParticipantes(tdParticipantesBk);
				setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
				tdParticipantesBkss.add(tdParticipantesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdParticipantesBkss;
	}

	@Override
	public Long getTdParticipantesTotalXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson,
			String nombresrazonsocial, Integer tipo, Integer estado, Long kyUsuarioMod) {
		try {
			Long totalTdParticipantessss = tdParticipantesDao.getTotalXFiltro(idparticipantes,
					idusuarioIdproveeIdperson, nombresrazonsocial, tipo, estado);

			return totalTdParticipantessss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLTdParticipantesBk(TdParticipantesBk tdParticipantesBk, Long kyUsuarioMod) {
		TdParticipantesACL tdParticipantesACL = new TdParticipantesACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDPARTICIPANTES_CREA)) {
					tdParticipantesACL.setEsEditable(true);
					tdParticipantesACL.setEliminar(true);
				} else if (roles.contains(Roles.TDPARTICIPANTES_VE)) {
					tdParticipantesACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDPARTICIPANTES_CREA)) {
					tdParticipantesACL.setEditopcion(1);
				} else {
					tdParticipantesACL.setEditopcion(2);
				}
			} else {
				tdParticipantesACL.setEditopcion(2);
			}
		} else {
			tdParticipantesACL.setEditopcion(2);
		}
		tdParticipantesBk.setTdParticipantesACL(tdParticipantesACL);
	}

	/// ADICIONALES

	@Override
	public List<IDValorDto> getMsUsuariosIdusuario() {
		if (msUsuariosIdusuarioListaCache == null) {
			List<MsUsuarios> msUsuariossss = msUsuariosDao.getListaIdusuario();
			msUsuariosIdusuarioListaCache = new ArrayList<IDValorDto>();
			for (MsUsuarios msUsuarios : msUsuariossss) {
				IDValorDto idusuarioDto = new IDValorDto(msUsuarios.getIdusuario(),
						FuncionesStaticas.getNombreCompleto(msUsuarios));
				msUsuariosIdusuarioListaCache.add(idusuarioDto);
			}
		}
		return msUsuariosIdusuarioListaCache;
	}

	/**
	 * TD_ITINERARIO SERVICIO: ITINERARIO DE LOS VUELOS A REALIZARCE
	 */
	@Override
	public TdItinerarioBk getTdItinerarioBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdItinerario tdItinerario = tdItinerarioDao.getTdItinerario(id);
		TdItinerarioBk tdItinerarioBk = null;
		if (tdItinerario != null) {
			tdItinerarioBk = new TdItinerarioBk();
			FuncionesStaticas.copyPropertiesObject(tdItinerarioBk, tdItinerario);
			completarTdItinerario(tdItinerarioBk);
			setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
		}
		return tdItinerarioBk;
	}

	@Override
	public List<TdItinerarioBk> getAllTdItinerarioActivos(Long kyUsuarioMod) {
		List<TdItinerarioBk> tdItinerarioBkss = new ArrayList<TdItinerarioBk>();
		try {
			List<TdItinerario> tdItinerarios = tdItinerarioDao.getActivasTdItinerario();
			for (TdItinerario tdItinerario : tdItinerarios) {
				TdItinerarioBk tdItinerarioBk = new TdItinerarioBk();
				FuncionesStaticas.copyPropertiesObject(tdItinerarioBk, tdItinerario);
				completarTdItinerario(tdItinerarioBk);
				setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
				tdItinerarioBkss.add(tdItinerarioBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdItinerarioBkss;
	}

	@Override
	public List<TdItinerarioBk> getAllTdItinerarioActivosCero(Long kyUsuarioMod) {
		List<TdItinerarioBk> tdItinerarioBkss = new ArrayList<TdItinerarioBk>();
		try {
			List<TdItinerario> tdItinerarios = tdItinerarioDao.getActivasTdItinerarioCero();
			for (TdItinerario tdItinerario : tdItinerarios) {
				TdItinerarioBk tdItinerarioBk = new TdItinerarioBk();
				FuncionesStaticas.copyPropertiesObject(tdItinerarioBk, tdItinerario);
				completarTdItinerario(tdItinerarioBk);
				setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
				tdItinerarioBkss.add(tdItinerarioBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdItinerarioBkss;
	}

	private void completarTdItinerario(TdItinerarioBk tdItinerarioBk) {
		// try{
		// if(tdItinerarioBk.getIdevent()!=null &&
		// tdItinerarioBk.getIdevent().longValue()>0){
		// TdEvento tdEvento =
		// tdEventoDao.getTdEvento(tdItinerarioBk.getIdevent());
		// if(tdEvento!=null)
		// tdItinerarioBk.setIdeventTxt(tdEvento.getNumeroSid()+"-"+tdEvento.getNumeroAnioSid());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			if (tdItinerarioBk.getCodpais() != null && tdItinerarioBk.getCodpais().longValue() > 0) {
				MsPaises msPaises = msPaisesDao.getMsPaises(tdItinerarioBk.getCodpais());
				if (msPaises != null)
					tdItinerarioBk.setCodpaisTxt(msPaises.getPaiNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdItinerarioBk.getCoddpto() != null && tdItinerarioBk.getCoddpto().longValue() > 0) {
				MsUbigeo msUbigeo = msUbigeoDao.getMsUbigeo(new MsUbigeoId(tdItinerarioBk.getCoddpto(), 0, 0));
				if (msUbigeo != null)
					tdItinerarioBk.setCoddptoTxt(msUbigeo.getNombre());
				if (tdItinerarioBk.getCodprov() != null && tdItinerarioBk.getCodprov().longValue() > 0) {
					MsUbigeo msUbigeop = msUbigeoDao
							.getMsUbigeo(new MsUbigeoId(tdItinerarioBk.getCoddpto(), tdItinerarioBk.getCodprov(), 0));
					if (msUbigeop != null)
						tdItinerarioBk.setCodprovTxt(msUbigeop.getNombre());
					// if(tdItinerarioBk.getCoddist()!=null &&
					// tdItinerarioBk.getCoddist().longValue()>0){
					// MsUbigeo msUbigeodi = msUbigeoDao.getMsUbigeo(new
					// MsUbigeoId(tdItinerarioBk.getCoddpto(),tdItinerarioBk.getCodprov(),tdItinerarioBk.getCoddist()));
					// if(msUbigeodi!=null)
					// tdItinerarioBk.setCoddistTxt(msUbigeodi.getNombre());
					// }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try{
		// if(tdItinerarioBk.getIduserCrea()!=null &&
		// tdItinerarioBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdItinerarioBk.getIduserCrea());
		// if(msUsuarios!=null)
		// tdItinerarioBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(tdItinerarioBk.getIduserModif()!=null &&
		// tdItinerarioBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdItinerarioBk.getIduserModif());
		// if(msUsuarios!=null)
		// tdItinerarioBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public TdItinerarioBk saveorupdateTdItinerarioBk(TdItinerarioBk tdItinerarioBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionTdItinerarioMng.validarTdItinerarioBk(tdItinerarioBk);

		TdItinerario tdItinerario = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdItinerarioBk.getIditinerario() != null && tdItinerarioBk.getIditinerario().longValue() > 0) {

				tdItinerario = tdItinerarioDao.getTdItinerario(tdItinerarioBk.getIditinerario());

				boolean cambios = AuditoriaTdItinerarioMng.auditarCambiosTdItinerario(tdItinerarioBk, tdItinerario,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					tdItinerario.setRmtaddressrst(rmtaddress);
					tdItinerario.setIduserModif(kyUsuarioMod);
					tdItinerario.setFechaModif(hoy);
					tdItinerarioDao.updateTdItinerario(tdItinerario);
				}
			} else {
				tdItinerarioBk.setRmtaddress(rmtaddress);
				tdItinerarioBk.setRmtaddressrst(rmtaddress);

				tdItinerarioBk.setFechaCrea(hoy);
				tdItinerarioBk.setIduserCrea(kyUsuarioMod);
				tdItinerarioBk.setIduserModif(kyUsuarioMod);
				tdItinerarioBk.setFechaModif(hoy);
				tdItinerarioBk.setEstado(1);

				tdItinerario = new TdItinerario();

				FuncionesStaticas.copyPropertiesObject(tdItinerario, tdItinerarioBk);
				tdItinerarioDao.saveTdItinerario(tdItinerario);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO tdItinerario" + " :: " + tdItinerario.getIditinerario().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdItinerarioBk = getTdItinerarioBkXid(tdItinerario.getIditinerario(), kyUsuarioMod);
		return tdItinerarioBk;
	}

	@Override
	public void deleteTdItinerario(TdItinerarioBk tdItinerarioBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			TdItinerario tdItinerario = null;
			if (tdItinerarioBk.getIditinerario() != null && tdItinerarioBk.getIditinerario().longValue() > 0) {

				tdItinerario = tdItinerarioDao.getTdItinerario(tdItinerarioBk.getIditinerario());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdItinerario.setIduserModif(kyUsuarioMod);
				tdItinerario.setFechaModif(hoy);
				Integer estadoanterior = tdItinerario.getEstado();
				tdItinerario.setEstado(0);

				tdItinerarioDao.updateTdItinerario(tdItinerario);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdItinerario" + " :: " + tdItinerario.getIditinerario().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdItinerarioBk> getTdItinerarioXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto,
			Integer codprov, Long kyUsuarioMod) {
		List<TdItinerarioBk> tdItinerarioBkss = new ArrayList<TdItinerarioBk>();
		try {
			List<TdItinerario> tdItinerariosss = tdItinerarioDao.getXFiltro(iditinerario, idevent, codpais, coddpto,
					codprov);
			for (TdItinerario tdItinerario : tdItinerariosss) {
				TdItinerarioBk tdItinerarioBk = new TdItinerarioBk();
				FuncionesStaticas.copyPropertiesObject(tdItinerarioBk, tdItinerario);
				completarTdItinerario(tdItinerarioBk);
				setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
				tdItinerarioBkss.add(tdItinerarioBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdItinerarioBkss;
	}

	@Override
	public List<TdItinerarioBk> getTdItinerarioXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto,
			Integer codprov, int inicial, int MAX, Long kyUsuarioMod) {
		List<TdItinerarioBk> tdItinerarioBkss = new ArrayList<TdItinerarioBk>();
		try {
			List<TdItinerario> tdItinerariosss = tdItinerarioDao.getXFiltro(iditinerario, idevent, codpais, coddpto,
					codprov, inicial, MAX);
			for (TdItinerario tdItinerario : tdItinerariosss) {
				TdItinerarioBk tdItinerarioBk = new TdItinerarioBk();
				FuncionesStaticas.copyPropertiesObject(tdItinerarioBk, tdItinerario);
				completarTdItinerario(tdItinerarioBk);
				setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
				tdItinerarioBkss.add(tdItinerarioBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdItinerarioBkss;
	}

	@Override
	public Long getTdItinerarioTotalXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto,
			Integer codprov, Long kyUsuarioMod) {
		try {
			Long totalTdItinerariosss = tdItinerarioDao.getTotalXFiltro(iditinerario, idevent, codpais, coddpto,
					codprov);

			return totalTdItinerariosss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLTdItinerarioBk(TdItinerarioBk tdItinerarioBk, Long kyUsuarioMod) {
		TdItinerarioACL tdItinerarioACL = new TdItinerarioACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDITINERARIO_CREA)) {
					tdItinerarioACL.setEsEditable(true);
					tdItinerarioACL.setEliminar(true);
				} else if (roles.contains(Roles.TDITINERARIO_VE)) {
					tdItinerarioACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDITINERARIO_CREA)) {
					tdItinerarioACL.setEditopcion(1);
				} else {
					tdItinerarioACL.setEditopcion(2);
				}
			} else {
				tdItinerarioACL.setEditopcion(2);
			}
		} else {
			tdItinerarioACL.setEditopcion(2);
		}
		tdItinerarioBk.setTdItinerarioACL(tdItinerarioACL);
	}

	@Override
	public List<IDValorDto> getTdEventoIdevent() {
		if (tdEventoIdeventListaCache == null) {
			List<TdEvento> tdEventosss = tdEventoDao.getListaIdevent();
			tdEventoIdeventListaCache = new ArrayList<IDValorDto>();
			for (TdEvento tdEvento : tdEventosss) {
				IDValorDto ideventDto = new IDValorDto(tdEvento.getIdevent(),
						tdEvento.getNumeroSid() + "-" + tdEvento.getNumeroAnioSid());
				tdEventoIdeventListaCache.add(ideventDto);
			}
		}
		return tdEventoIdeventListaCache;
	}

	/**
	 * TD_GRUPOS SERVICIO: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
	 */
	@Override
	public TdGruposBk getTdGruposBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdGrupos tdGrupos = tdGruposDao.getTdGrupos(id);
		TdGruposBk tdGruposBk = null;
		if (tdGrupos != null) {
			tdGruposBk = new TdGruposBk();
			FuncionesStaticas.copyPropertiesObject(tdGruposBk, tdGrupos);
			completarTdGrupos(tdGruposBk);
			setACLTdGruposBk(tdGruposBk, kyUsuarioMod);
		}
		return tdGruposBk;
	}

	@Override
	public List<TdGruposBk> getAllTdGruposActivos(Long kyUsuarioMod) {
		List<TdGruposBk> tdGruposBkss = new ArrayList<TdGruposBk>();
		try {
			List<TdGrupos> tdGruposs = tdGruposDao.getActivasTdGrupos();
			for (TdGrupos tdGrupos : tdGruposs) {
				TdGruposBk tdGruposBk = new TdGruposBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposBk, tdGrupos);
				completarTdGrupos(tdGruposBk);
				setACLTdGruposBk(tdGruposBk, kyUsuarioMod);
				tdGruposBkss.add(tdGruposBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposBkss;
	}

	@Override
	public List<TdGruposBk> getAllTdGruposActivosCero(Long kyUsuarioMod) {
		List<TdGruposBk> tdGruposBkss = new ArrayList<TdGruposBk>();
		try {
			List<TdGrupos> tdGruposs = tdGruposDao.getActivasTdGruposCero();
			for (TdGrupos tdGrupos : tdGruposs) {
				TdGruposBk tdGruposBk = new TdGruposBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposBk, tdGrupos);
				completarTdGrupos(tdGruposBk);
				setACLTdGruposBk(tdGruposBk, kyUsuarioMod);
				tdGruposBkss.add(tdGruposBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposBkss;
	}

	private void completarTdGrupos(TdGruposBk tdGruposBk) {
		// try{
		// if(tdGruposBk.getIduserCrea()!=null &&
		// tdGruposBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdGruposBk.getIduserCrea());
		// if(msUsuarios!=null)
		// tdGruposBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(tdGruposBk.getIduserModif()!=null &&
		// tdGruposBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(tdGruposBk.getIduserModif());
		// if(msUsuarios!=null)
		// tdGruposBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public TdGruposBk saveorupdateTdGruposBk(TdGruposBk tdGruposBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {

		ValidacionTdGruposMng.validarTdGruposBk(tdGruposBk);

		TdGrupos tdGrupos = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdGruposBk.getIdgrupo() != null && tdGruposBk.getIdgrupo().longValue() > 0) {

				tdGrupos = tdGruposDao.getTdGrupos(tdGruposBk.getIdgrupo());

				boolean cambios = AuditoriaTdGruposMng.auditarCambiosTdGrupos(tdGruposBk, tdGrupos, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					tdGrupos.setRmtaddressrst(rmtaddress);
					tdGrupos.setIduserModif(kyUsuarioMod);
					tdGrupos.setFechaModif(hoy);
					tdGruposDao.updateTdGrupos(tdGrupos);
				}
			} else {
				tdGruposBk.setRmtaddress(rmtaddress);
				tdGruposBk.setRmtaddressrst(rmtaddress);

				tdGruposBk.setFechaCrea(hoy);
				tdGruposBk.setIduserCrea(kyUsuarioMod);
				tdGruposBk.setIduserModif(kyUsuarioMod);
				tdGruposBk.setFechaModif(hoy);
				tdGruposBk.setEstado(1);

				tdGrupos = new TdGrupos();

				FuncionesStaticas.copyPropertiesObject(tdGrupos, tdGruposBk);
				tdGruposDao.saveTdGrupos(tdGrupos);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO tdGrupos" + " :: " + tdGrupos.getIdgrupo().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdGruposBk = getTdGruposBkXid(tdGrupos.getIdgrupo(), kyUsuarioMod);
		return tdGruposBk;
	}

	@Override
	public void deleteTdGrupos(TdGruposBk tdGruposBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			TdGrupos tdGrupos = null;
			if (tdGruposBk.getIdgrupo() != null && tdGruposBk.getIdgrupo().longValue() > 0) {

				tdGrupos = tdGruposDao.getTdGrupos(tdGruposBk.getIdgrupo());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdGrupos.setIduserModif(kyUsuarioMod);
				tdGrupos.setFechaModif(hoy);
				Integer estadoanterior = tdGrupos.getEstado();
				tdGrupos.setEstado(0);

				tdGruposDao.updateTdGrupos(tdGrupos);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdGrupos" + " :: " + tdGrupos.getIdgrupo().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdGruposBk> getTdGruposXFiltro(Long idgrupo, String grupo, Integer estado, Long kyUsuarioMod) {
		List<TdGruposBk> tdGruposBkss = new ArrayList<TdGruposBk>();
		try {
			List<TdGrupos> tdGrupossss = tdGruposDao.getXFiltro(idgrupo, grupo, estado);
			for (TdGrupos tdGrupos : tdGrupossss) {
				TdGruposBk tdGruposBk = new TdGruposBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposBk, tdGrupos);
				completarTdGrupos(tdGruposBk);
				setACLTdGruposBk(tdGruposBk, kyUsuarioMod);
				tdGruposBkss.add(tdGruposBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposBkss;
	}

	@Override
	public List<TdGruposBk> getTdGruposXFiltro(Long idgrupo, String grupo, Integer estado, int inicial, int MAX,
			Long kyUsuarioMod) {
		List<TdGruposBk> tdGruposBkss = new ArrayList<TdGruposBk>();
		try {
			List<TdGrupos> tdGrupossss = tdGruposDao.getXFiltro(idgrupo, grupo, estado, inicial, MAX);
			for (TdGrupos tdGrupos : tdGrupossss) {
				TdGruposBk tdGruposBk = new TdGruposBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposBk, tdGrupos);
				completarTdGrupos(tdGruposBk);
				setACLTdGruposBk(tdGruposBk, kyUsuarioMod);
				tdGruposBkss.add(tdGruposBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposBkss;
	}

	@Override
	public Long getTdGruposTotalXFiltro(Long idgrupo, String grupo, Integer estado, Long kyUsuarioMod) {
		try {
			Long totalTdGrupossss = tdGruposDao.getTotalXFiltro(idgrupo, grupo, estado);

			return totalTdGrupossss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLTdGruposBk(TdGruposBk tdGruposBk, Long kyUsuarioMod) {
		TdGruposACL tdGruposACL = new TdGruposACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDGRUPOS_CREA)) {
					tdGruposACL.setEsEditable(true);
					tdGruposACL.setEliminar(true);
				} else if (roles.contains(Roles.TDGRUPOS_VE)) {
					tdGruposACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDGRUPOS_CREA)) {
					tdGruposACL.setEditopcion(1);
				} else {
					tdGruposACL.setEditopcion(2);
				}
			} else {
				tdGruposACL.setEditopcion(2);
			}
		} else {
			tdGruposACL.setEditopcion(2);
		}
		tdGruposBk.setTdGruposACL(tdGruposACL);
	}

	/**
	 * TD_GRUPOSFLUJOS SERVICIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
	 */
	@Override
	public TdGruposflujosBk getTdGruposflujosBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdGruposflujos tdGruposflujos = tdGruposflujosDao.getTdGruposflujos(id);
		TdGruposflujosBk tdGruposflujosBk = null;
		if (tdGruposflujos != null) {
			tdGruposflujosBk = new TdGruposflujosBk();
			FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
			completarTdGruposflujos(tdGruposflujosBk);
			setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
		}
		return tdGruposflujosBk;
	}

	@Override
	public List<TdGruposflujosBk> getAllTdGruposflujosActivos(Long kyUsuarioMod) {
		List<TdGruposflujosBk> tdGruposflujosBkss = new ArrayList<TdGruposflujosBk>();
		try {
			List<TdGruposflujos> tdGruposflujoss = tdGruposflujosDao.getActivasTdGruposflujos();
			for (TdGruposflujos tdGruposflujos : tdGruposflujoss) {
				TdGruposflujosBk tdGruposflujosBk = new TdGruposflujosBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
				completarTdGruposflujos(tdGruposflujosBk);
				setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
				tdGruposflujosBkss.add(tdGruposflujosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposflujosBkss;
	}

	@Override
	public List<TdGruposflujosBk> getAllTdGruposflujosActivosCero(Long kyUsuarioMod) {
		List<TdGruposflujosBk> tdGruposflujosBkss = new ArrayList<TdGruposflujosBk>();
		try {
			List<TdGruposflujos> tdGruposflujoss = tdGruposflujosDao.getActivasTdGruposflujosCero();
			for (TdGruposflujos tdGruposflujos : tdGruposflujoss) {
				TdGruposflujosBk tdGruposflujosBk = new TdGruposflujosBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
				completarTdGruposflujos(tdGruposflujosBk);
				setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
				tdGruposflujosBkss.add(tdGruposflujosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposflujosBkss;
	}

	private void completarTdGruposflujos(TdGruposflujosBk tdGruposflujosBk) {
		try {
			if (tdGruposflujosBk.getIdtareas() != null && tdGruposflujosBk.getIdtareas().longValue() > 0) {
				MsTareas msTareas = msTareasDao.getMsTareas(tdGruposflujosBk.getIdtareas());
				if (msTareas != null)
					tdGruposflujosBk.setIdtareasTxt(msTareas.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdGruposflujosBk.getIdunidadDestino() != null
					&& tdGruposflujosBk.getIdunidadDestino().longValue() > 0) {
				MsUnidadesOrg msUnidadesOrg = msUnidadesOrgDao.getMsUnidadesOrg(tdGruposflujosBk.getIdunidadDestino());
				if (msUnidadesOrg != null)
					tdGruposflujosBk.setIdunidadDestinoTxt(msUnidadesOrg.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdGruposflujosBk.getIduserDestino() != null && tdGruposflujosBk.getIduserDestino().longValue() > 0) {
				MsUsuarios msUsuarios = msUsuariosDao.getMsUsuarios(tdGruposflujosBk.getIduserDestino());
				if (msUsuarios != null)
					tdGruposflujosBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdGruposflujosBk.getIduserDestino() != null && tdGruposflujosBk.getIduserDestino().longValue() > 0) {
				MsUsuarios msUsuarios = msUsuariosDao.getMsUsuarios(tdGruposflujosBk.getIduserDestino());
				if (msUsuarios != null)
				    tdGruposflujosBk.setIduserDestinoTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));//vbaldeon 08092023
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TdGruposflujosBk saveorupdateTdGruposflujosBk(TdGruposflujosBk tdGruposflujosBk, String user,
			Long kyUsuarioMod, Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionTdGruposflujosMng.validarTdGruposflujosBk(tdGruposflujosBk);

		TdGruposflujos tdGruposflujos = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdGruposflujosBk.getIdgruposflujos() != null && tdGruposflujosBk.getIdgruposflujos().longValue() > 0) {

				tdGruposflujos = tdGruposflujosDao.getTdGruposflujos(tdGruposflujosBk.getIdgruposflujos());

				boolean cambios = AuditoriaTdGruposflujosMng.auditarCambiosTdGruposflujos(tdGruposflujosBk,
						tdGruposflujos, kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					tdGruposflujos.setRmtaddressrst(rmtaddress);
					tdGruposflujos.setIduserModif(kyUsuarioMod);
					tdGruposflujos.setFechaModif(hoy);
					tdGruposflujosDao.updateTdGruposflujos(tdGruposflujos);
				}
			} else {
				tdGruposflujosBk.setRmtaddress(rmtaddress);
				tdGruposflujosBk.setRmtaddressrst(rmtaddress);

				tdGruposflujosBk.setFechaCrea(hoy);
				tdGruposflujosBk.setIduserCrea(kyUsuarioMod);
				tdGruposflujosBk.setIduserModif(kyUsuarioMod);
				tdGruposflujosBk.setFechaModif(hoy);
				tdGruposflujosBk.setEstado(1);

				tdGruposflujos = new TdGruposflujos();

				FuncionesStaticas.copyPropertiesObject(tdGruposflujos, tdGruposflujosBk);
				tdGruposflujosDao.saveTdGruposflujos(tdGruposflujos);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO tdGruposflujos" + " :: " + tdGruposflujos.getIdgruposflujos().toString()
								+ " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdGruposflujosBk = getTdGruposflujosBkXid(tdGruposflujos.getIdgruposflujos(), kyUsuarioMod);
		return tdGruposflujosBk;
	}

	@Override
	public void deleteTdGruposflujos(TdGruposflujosBk tdGruposflujosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			TdGruposflujos tdGruposflujos = null;
			if (tdGruposflujosBk.getIdgruposflujos() != null && tdGruposflujosBk.getIdgruposflujos().longValue() > 0) {

				tdGruposflujos = tdGruposflujosDao.getTdGruposflujos(tdGruposflujosBk.getIdgruposflujos());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdGruposflujos.setIduserModif(kyUsuarioMod);
				tdGruposflujos.setFechaModif(hoy);
				Integer estadoanterior = tdGruposflujos.getEstado();
				tdGruposflujos.setEstado(0);

				tdGruposflujosDao.updateTdGruposflujos(tdGruposflujos);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdGruposflujos" + " :: " + tdGruposflujos.getIdgruposflujos().toString()
								+ " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

//	@Override
//	public List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
//			Long kyUsuarioMod) {
//		List<TdGruposflujosBk> tdGruposflujosBkss = new ArrayList<TdGruposflujosBk>();
//		try {
//			List<TdGruposflujos> tdGruposflujossss = tdGruposflujosDao.getXFiltro(idtareas, idunidadDestino,
//					iduserDestino);
//			for (TdGruposflujos tdGruposflujos : tdGruposflujossss) {
//				TdGruposflujosBk tdGruposflujosBk = new TdGruposflujosBk();
//				FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
//				completarTdGruposflujos(tdGruposflujosBk);
//				setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
//				tdGruposflujosBkss.add(tdGruposflujosBk);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return tdGruposflujosBkss;
//	}

//	@Override
//	public List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
//			int inicial, int MAX, Long kyUsuarioMod) {
//		List<TdGruposflujosBk> tdGruposflujosBkss = new ArrayList<TdGruposflujosBk>();
//		try {
//			List<TdGruposflujos> tdGruposflujossss = tdGruposflujosDao.getXFiltro(idtareas, idunidadDestino,
//					iduserDestino, inicial, MAX);
//			for (TdGruposflujos tdGruposflujos : tdGruposflujossss) {
//				TdGruposflujosBk tdGruposflujosBk = new TdGruposflujosBk();
//				FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
//				completarTdGruposflujos(tdGruposflujosBk);
//				setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
//				tdGruposflujosBkss.add(tdGruposflujosBk);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return tdGruposflujosBkss;
//	}
//
//	@Override
//	public Long getTdGruposflujosTotalXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
//			Long kyUsuarioMod) {
//		try {
//			Long totalTdGruposflujossss = tdGruposflujosDao.getTotalXFiltro(idtareas, idunidadDestino, iduserDestino);
//
//			return totalTdGruposflujossss;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	private void setACLTdGruposflujosBk(TdGruposflujosBk tdGruposflujosBk, Long kyUsuarioMod) {
		TdGruposflujosACL tdGruposflujosACL = new TdGruposflujosACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDGRUPOSFLUJOS_CREA)) {
					tdGruposflujosACL.setEsEditable(true);
					tdGruposflujosACL.setEliminar(true);
				} else if (roles.contains(Roles.TDGRUPOSFLUJOS_VE)) {
					tdGruposflujosACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDGRUPOSFLUJOS_CREA)) {
					tdGruposflujosACL.setEditopcion(1);
				} else {
					tdGruposflujosACL.setEditopcion(2);
				}
			} else {
				tdGruposflujosACL.setEditopcion(2);
			}
		} else {
			tdGruposflujosACL.setEditopcion(2);
		}
		tdGruposflujosBk.setTdGruposflujosACL(tdGruposflujosACL);
	}

	/**
	 * TD_EVENTO SERVICIO: EVENTO
	 */

	@Override
	public TdEventoBk getTdEventoBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		TdEvento tdEvento = tdEventoDao.getTdEvento(id);
		TdEventoBk tdEventoBk = null;
		if (tdEvento != null) {
			tdEventoBk = new TdEventoBk();
			FuncionesStaticas.copyPropertiesObject(tdEventoBk, tdEvento);
			completarTdEvento(tdEventoBk);
			setACLTdEventoBk(tdEventoBk, kyUsuarioMod);
		}
		return tdEventoBk;
	}

	@Override
	public List<TdEventoBk> getAllTdEventoActivos(Long kyUsuarioMod) {
		List<TdEventoBk> tdEventoBkss = new ArrayList<TdEventoBk>();
		try {
			List<TdEvento> tdEventos = tdEventoDao.getActivasTdEvento();
			for (TdEvento tdEvento : tdEventos) {
				TdEventoBk tdEventoBk = new TdEventoBk();
				FuncionesStaticas.copyPropertiesObject(tdEventoBk, tdEvento);
				completarTdEvento(tdEventoBk);
				setACLTdEventoBk(tdEventoBk, kyUsuarioMod);
				tdEventoBkss.add(tdEventoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdEventoBkss;
	}

	@Override
	public List<TdEventoBk> getAllTdEventoActivosCero(Long kyUsuarioMod) {
		List<TdEventoBk> tdEventoBkss = new ArrayList<TdEventoBk>();
		try {
			List<TdEvento> tdEventos = tdEventoDao.getActivasTdEventoCero();
			for (TdEvento tdEvento : tdEventos) {
				TdEventoBk tdEventoBk = new TdEventoBk();
				FuncionesStaticas.copyPropertiesObject(tdEventoBk, tdEvento);
				completarTdEvento(tdEventoBk);
				setACLTdEventoBk(tdEventoBk, kyUsuarioMod);
				tdEventoBkss.add(tdEventoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdEventoBkss;
	}

	private void completarTdEvento(TdEventoBk tdEventoBk) {
		try {
			if (tdEventoBk.getRemiIdprovee() != null && tdEventoBk.getRemiIdprovee().longValue() > 0) {
				MsInstituciones msInstituciones = msInstitucionesDao.getMsInstituciones(tdEventoBk.getRemiIdprovee());
				if (msInstituciones != null)
					tdEventoBk.setRemiIdproveeTxt(msInstituciones.getRazonSocial());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (tdEventoBk.getRemiIdperson() != null && tdEventoBk.getRemiIdperson().longValue() > 0) {
				MsPersonas msPersonas = msPersonasDao.getMsPersonas(tdEventoBk.getRemiIdperson());
				if (msPersonas != null)
					tdEventoBk.setRemiIdpersonTxt(FuncionesStaticas.getNombreCompletoPersona(msPersonas));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (tdEventoBk.getIdcategorias() != null && tdEventoBk.getIdcategorias().longValue() > 0) {
				MsCategorias msCategorias = msCategoriasDao.getMsCategorias(tdEventoBk.getIdcategorias());
				if (msCategorias != null)
					tdEventoBk.setIdcategoriasTxt(msCategorias.getCategoria());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// vbaldeon 08092023 inicio
		if (tdEventoBk != null && tdEventoBk.getIdevent() != null) {
			List<TdFlujoBk> lstFlujo = getTdFlujoXFiltro(null, null, null, null, tdEventoBk.getIdevent(), null);
			if (!lstFlujo.isEmpty())
				tdEventoBk.setTdFlujoBks(lstFlujo);

			// MPINARES 01092023 - INICIO
			List<TdItinerarioBk> TdItinerarioBksss = getTdItinerarioXFiltro(null, tdEventoBk.getIdevent(), null, null,
					null, null);
			if (TdItinerarioBksss != null && TdItinerarioBksss.size() > 0) {
				tdEventoBk.setTdItinerarioJss(TdItinerarioBksss);
			}

			List<TdCategEventoBk> tdCategEventoBksss = getTdCategEventoXFiltro(null, tdEventoBk.getIdevent(), null);
			List<MsCategoriasBk> msCategoriasBkaaaa = new ArrayList<MsCategoriasBk>();
			if (tdCategEventoBksss != null && tdCategEventoBksss.size() > 0) {
				for (TdCategEventoBk tdCategEventoBka : tdCategEventoBksss) {
					if (tdCategEventoBka.getIdcategorias() != null
							&& tdCategEventoBka.getIdcategorias().longValue() > 0) {
						MsCategorias msCategorias = msCategoriasDao.getMsCategorias(tdCategEventoBka.getIdcategorias());
						if (msCategorias != null) {
							MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
							FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
							msCategoriasBkaaaa.add(msCategoriasBk);
						}
					}
				}
				tdEventoBk.setMsCategoriasJss(msCategoriasBkaaaa);
			}

			////////////////////////////////////////////////////////

			List<TdParticipantesBk> tdParticipantesBksss = getTdParticipantesXFiltroXIdEvento(tdEventoBk.getIdevent());
			List<MsUsuariosBk> msUsuariosBkaaaa = new ArrayList<MsUsuariosBk>();
			if (tdParticipantesBksss != null && tdParticipantesBksss.size() > 0) {
				for (TdParticipantesBk tdParticipantesBka : tdParticipantesBksss) {
					if (tdParticipantesBka.getIdusuarioIdproveeIdperson() != null
							&& tdParticipantesBka.getIdusuarioIdproveeIdperson().longValue() > 0) {
						MsUsuarios msUsuarios = msUsuariosDao
								.getMsUsuarios(tdParticipantesBka.getIdusuarioIdproveeIdperson());
						if (msUsuarios != null) {
							MsUsuariosBk msUsuariosBk = new MsUsuariosBk();
							FuncionesStaticas.copyPropertiesObject(msUsuariosBk, msUsuarios);
							msUsuariosBkaaaa.add(msUsuariosBk);
						}
					}
				}
				tdEventoBk.setMsUsuariosJss(msUsuariosBkaaaa);
			}

			// MPINARES 01092023 - FIN
		}

		// //vbaldeon 08092023 fin
	}

	@Override
	public TdEventoBk saveorupdateTdEventoBk(TdEventoBk tdEventoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {

		ValidacionTdEventoMng.validarTdEventoBk(tdEventoBk);

		TdEvento tdEvento = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (tdEventoBk.getIdevent() != null && tdEventoBk.getIdevent().longValue() > 0) {

				tdEvento = tdEventoDao.getTdEvento(tdEventoBk.getIdevent());

				boolean cambios = AuditoriaTdEventoMng.auditarCambiosTdEvento(tdEventoBk, tdEvento, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					tdEvento.setRmtaddressrst(rmtaddress);
					tdEvento.setIduserModif(kyUsuarioMod);
					tdEvento.setFechaModif(hoy);
					tdEventoDao.updateTdEvento(tdEvento);
				}
			} else {
				tdEventoBk.setRmtaddress(rmtaddress);
				tdEventoBk.setRmtaddressrst(rmtaddress);

				tdEventoBk.setFechaCrea(hoy);
				tdEventoBk.setIduserCrea(kyUsuarioMod);
				tdEventoBk.setIduserModif(kyUsuarioMod);
				tdEventoBk.setFechaModif(hoy);
				tdEventoBk.setEstado(1);

				tdEvento = new TdEvento();

				FuncionesStaticas.copyPropertiesObject(tdEvento, tdEventoBk);
				tdEventoDao.saveTdEvento(tdEvento);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO tdEvento" + " :: " + tdEvento.getIdevent().toString() + " :: " + "0" + " :: " + "1");

			}

			// MPINARES 01092023 - INICIO
			TdItinerario tdItinerario = null;
			if (tdEventoBk.getTdItinerarioJss() != null && tdEventoBk.getTdItinerarioJss().size() > 0) {
				for (TdItinerarioBk tdItinerarioBk : tdEventoBk.getTdItinerarioJss()) {
					if (tdItinerarioBk.getIditinerario() != null && tdItinerarioBk.getIditinerario().longValue() > 0) {
						// ACTUALIZAR

					} else {
						// NUEVO
						tdItinerarioBk.setIdevent(tdEvento.getIdevent());
						tdItinerarioBk.setEstado(1);
						tdItinerarioBk.setFechaCrea(hoy);
						tdItinerarioBk.setFechaModif(hoy);
						tdItinerarioBk.setIduserCrea(kyUsuarioMod);
						tdItinerarioBk.setIduserModif(kyUsuarioMod);
						tdItinerarioBk.setRmtaddress(rmtaddress);
						tdItinerarioBk.setRmtaddressrst(rmtaddress);

						tdItinerario = new TdItinerario();

						FuncionesStaticas.copyPropertiesObject(tdItinerario, tdItinerarioBk);
						tdItinerarioDao.saveTdItinerario(tdItinerario);

						log.log(Level.INFO,
								"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
										+ "CREADO tdItinerario" + " :: " + tdItinerario.getIditinerario().toString()
										+ " :: " + "0" + " :: " + "1");
					}
				}
			}

			/////////////////////////////////////

			TdCategEvento tdCategEvento = null;
			TdCategEventoBk tdCategEventoBk = new TdCategEventoBk();
			if (tdEventoBk.getMsCategoriasJss() != null && tdEventoBk.getMsCategoriasJss().size() > 0) {
				for (MsCategoriasBk msCategoriasBk : tdEventoBk.getMsCategoriasJss()) {
					if (msCategoriasBk.getIdcategorias() != null && msCategoriasBk.getIdcategorias().longValue() > 0) {

						// NUEVO
						tdCategEventoBk.setIdevent(tdEvento.getIdevent());
						tdCategEventoBk.setIdcategorias(msCategoriasBk.getIdcategorias());
						tdCategEventoBk.setEstado(1);
						tdCategEventoBk.setFechaCrea(hoy);
						tdCategEventoBk.setFechaModif(hoy);
						tdCategEventoBk.setIduserCrea(kyUsuarioMod);
						tdCategEventoBk.setIduserModif(kyUsuarioMod);
						tdCategEventoBk.setRmtaddress(rmtaddress);
						tdCategEventoBk.setRmtaddressrst(rmtaddress);

						tdCategEvento = new TdCategEvento();

						FuncionesStaticas.copyPropertiesObject(tdCategEvento, tdCategEventoBk);
						tdCategEventoDao.saveTdCategEvento(tdCategEvento);

						log.log(Level.INFO,
								"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
										+ "CREADO tdCategEvento" + " :: " + tdCategEvento.getIdcategevento().toString()
										+ " :: " + "0" + " :: " + "1");
					}
				}
			}
			/////////////////////////////////////

			TdParticipantes tdParticipantes = null;
			TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
			if (tdEventoBk.getMsUsuariosJss() != null && tdEventoBk.getMsUsuariosJss().size() > 0) {
				for (MsUsuariosBk msUsuariosBk : tdEventoBk.getMsUsuariosJss()) {
					if (msUsuariosBk.getIdusuario() != null && msUsuariosBk.getIdusuario().longValue() > 0) {

						// NUEVO
						tdParticipantesBk.setIdevent(tdEvento.getIdevent());
						tdParticipantesBk.setIdusuarioIdproveeIdperson(msUsuariosBk.getIdusuario());
						tdParticipantesBk.setNombresrazonsocial(msUsuariosBk.getNombreCompleto());
						tdParticipantesBk.setTipo(1);// 01 ES PARA USUARIOS
						tdParticipantesBk.setFlagacompaniante(0);// 01 ES
																	// ACOMPAÑANTE,
																	// 0 ES
																	// PARTICIPANTE
						tdParticipantesBk.setEstado(1);
						tdParticipantesBk.setFechaCrea(hoy);
						tdParticipantesBk.setFechaModif(hoy);
						tdParticipantesBk.setIduserCrea(kyUsuarioMod);
						tdParticipantesBk.setIduserModif(kyUsuarioMod);
						tdParticipantesBk.setRmtaddress(rmtaddress);
						tdParticipantesBk.setRmtaddressrst(rmtaddress);

						tdParticipantes = new TdParticipantes();

						FuncionesStaticas.copyPropertiesObject(tdParticipantes, tdParticipantesBk);
						tdParticipantesDao.saveTdParticipantes(tdParticipantes);

						log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO tdParticipantes" + " :: " + tdParticipantes.getIdparticipantes().toString()
								+ " :: " + "0" + " :: " + "1");
					}
				}
			}

			// MPINARES 01092023 - FIN
			
			//vbaldeon 08092023 inicio
			
			if(tdEventoBk.getTdFlujoBks()!=null && !tdEventoBk.getTdFlujoBks().isEmpty()){				
				for(TdFlujoBk tdFlujoBk: tdEventoBk.getTdFlujoBks()){
					
					if(tdFlujoBk.getIdflujo()!=null && tdFlujoBk.getIdflujo().longValue()>0){
						
					}else{
						tdFlujoBk.setIdunidadDeriva(kyAreaMod);
						tdFlujoBk.setIduserDeriva(kyUsuarioMod);
						tdFlujoBk.setFechaDerivacion(hoy);
						tdFlujoBk.setIdevent(tdEvento.getIdevent());
						saveorupdateTdFlujoBk(tdFlujoBk, user, kyUsuarioMod, kyAreaMod, null, rmtaddress);								
					}
					
//					tdFlujoBk.setIdunidadDeriva(msUsuariosBk.getIdunidad());
//					tdFlujoBk.setIduserDeriva(msUsuariosBk.getIdusuario());
//					tdFlujoBk.setIdunidadRecepciona(msUsuariosBk.getIdunidad());//vbaldeon 25092023
//					tdFlujoBk.setIduserRecepciona(msUsuariosBk.getIdusuario());//vbaldeon 25092023
//					tdFlujoBk.setFechaDerivacion(hoy);//vbaldeon 25092023
//					tdFlujoBk.setFechaRecepcion(hoy);//vbaldeon 25092023	
//					tdFlujoBk.setIdunidadDestino(tdFlujoBk.getIdunidadAtiende());//vbaldeon 25092023	
//					tdFlujoBk.setIduserDestino(tdFlujoBk.getIduserAtiende());//vbaldeon 25092023
//					tdFlujoBk.setEstado(1);//vbaldeon 25092023
//					tdFlujoBk.setIdEvento(tdEventoC.getIdevent());//vbaldeon 25092023
//					servicio.saveorupdateTdFlujoBk(tdFlujoBk, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), msUsuariosBk.getSede(), adressRemoto);
				
				}
			}
			
			//	//vbaldeon 08092023 fin
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		tdEventoBk = getTdEventoBkXid(tdEvento.getIdevent(), kyUsuarioMod);
		return tdEventoBk;
	}

	@Override
	public void deleteTdEvento(TdEventoBk tdEventoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			TdEvento tdEvento = null;
			if (tdEventoBk.getIdevent() != null && tdEventoBk.getIdevent().longValue() > 0) {

				tdEvento = tdEventoDao.getTdEvento(tdEventoBk.getIdevent());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				tdEvento.setIduserModif(kyUsuarioMod);
				tdEvento.setFechaModif(hoy);
				Integer estadoanterior = tdEvento.getEstado();
				tdEvento.setEstado(0);

				tdEventoDao.updateTdEvento(tdEvento);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO tdEvento" + " :: " + tdEvento.getIdevent().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<TdEventoBk> getTdEventoXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, Long kyUsuarioMod) {
		List<TdEventoBk> tdEventoBkss = new ArrayList<TdEventoBk>();
		try {
			List<TdEvento> tdEventosss = tdEventoDao.getXFiltro(idevent, titulo, fechaSoliIni, idcategorias, modalidad,
					estado);
			for (TdEvento tdEvento : tdEventosss) {
				TdEventoBk tdEventoBk = new TdEventoBk();
				FuncionesStaticas.copyPropertiesObject(tdEventoBk, tdEvento);
				completarTdEvento(tdEventoBk);
				setACLTdEventoBk(tdEventoBk, kyUsuarioMod);
				tdEventoBkss.add(tdEventoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdEventoBkss;
	}

	@Override
	public List<TdEventoBk> getTdEventoXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, int inicial, int MAX, Long kyUsuarioMod) {
		List<TdEventoBk> tdEventoBkss = new ArrayList<TdEventoBk>();
		try {
			List<TdEvento> tdEventosss = tdEventoDao.getXFiltro(idevent, titulo, fechaSoliIni, idcategorias, modalidad,
					estado, inicial, MAX);
			for (TdEvento tdEvento : tdEventosss) {
				TdEventoBk tdEventoBk = new TdEventoBk();
				FuncionesStaticas.copyPropertiesObject(tdEventoBk, tdEvento);
				completarTdEvento(tdEventoBk);
				setACLTdEventoBk(tdEventoBk, kyUsuarioMod);
				tdEventoBkss.add(tdEventoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdEventoBkss;
	}

	@Override
	public Long getTdEventoTotalXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, Long kyUsuarioMod) {
		try {
			Long totalTdEventosss = tdEventoDao.getTotalXFiltro(idevent, titulo, fechaSoliIni, idcategorias, modalidad,
					estado);

			return totalTdEventosss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLTdEventoBk(TdEventoBk tdEventoBk, Long kyUsuarioMod) {
		TdEventoACL tdEventoACL = new TdEventoACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDEVENTO_CREA)) {
					tdEventoACL.setEsEditable(true);
					tdEventoACL.setEliminar(true);
				} else if (roles.contains(Roles.TDEVENTO_VE)) {
					tdEventoACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDEVENTO_CREA)) {
					tdEventoACL.setEditopcion(1);
				} else {
					tdEventoACL.setEditopcion(2);
				}
			} else {
				tdEventoACL.setEditopcion(2);
			}
		} else {
			tdEventoACL.setEditopcion(2);
		}
		tdEventoBk.setTdEventoACL(tdEventoACL);
	}

	@Override
	public List<MsInstitucionesDto> getMsInstitucionesCache() {
		if (msInstitucionesListaCache == null) {
			List<MsInstituciones> msInstitucionessss = msInstitucionesDao.getActivasMsInstituciones();
			msInstitucionesListaCache = new ArrayList<MsInstitucionesDto>();
			for (MsInstituciones msInstituciones : msInstitucionessss) {
				MsInstitucionesDto msInstitucionesDto = new MsInstitucionesDto();
				FuncionesStaticas.copyPropertiesObject(msInstitucionesDto, msInstituciones);
				msInstitucionesListaCache.add(msInstitucionesDto);
			}
		}
		return msInstitucionesListaCache;
	}

	@Override
	public List<MsPersonasDto> getMsPersonasCache() {
		if (msPersonasListaCache == null) {
			List<MsPersonas> msPersonassss = msPersonasDao.getActivasMsPersonas();
			msPersonasListaCache = new ArrayList<MsPersonasDto>();
			for (MsPersonas msPersonas : msPersonassss) {
				MsPersonasDto msPersonasDto = new MsPersonasDto();
				FuncionesStaticas.copyPropertiesObject(msPersonasDto, msPersonas);
				msPersonasListaCache.add(msPersonasDto);
			}
		}
		return msPersonasListaCache;
	}

	@Override
	public List<IDValorDto> getPrtParametrosIdparametro() {
		if (prtParametrosIdparametroListaCache == null) {
			List<PrtParametros> prtParametrossss = prtParametrosDao.getListaIdparametro();
			prtParametrosIdparametroListaCache = new ArrayList<IDValorDto>();
			for (PrtParametros prtParametros : prtParametrossss) {
				IDValorDto idparametroDto = new IDValorDto(prtParametros.getIdparametro(),
						prtParametros.getDescripcion());
				prtParametrosIdparametroListaCache.add(idparametroDto);
			}
		}
		return prtParametrosIdparametroListaCache;
	}

	@Override
	public List<MsCategoriasBk> getMsCategoriasIdcategorias() {
		if (msCategoriasIdcategoriasListaCache == null) {
			List<MsCategorias> msCategoriassss = msCategoriasDao.getListaIdcategorias();
			msCategoriasIdcategoriasListaCache = new ArrayList<MsCategoriasBk>();
			for (MsCategorias msCategorias : msCategoriassss) {
				MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
				FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
				msCategoriasIdcategoriasListaCache.add(msCategoriasBk);
			}
		}
		return msCategoriasIdcategoriasListaCache;
	}

	@Override
	public List<MsCategoriasBk> getMsCategoriasIdcategorias(String categoria) {
		List<MsCategoriasBk> msCategoriasBksss = new ArrayList<MsCategoriasBk>();
		List<MsCategoriasBk> msCategoriasBkss = getMsCategoriasIdcategorias();
		if (msCategoriasBkss != null && categoria != null) {
			for (Iterator<MsCategoriasBk> iterator = msCategoriasBkss.iterator(); iterator.hasNext();) {
				MsCategoriasBk msCategoriasBk = (MsCategoriasBk) iterator.next();
				if (msCategoriasBk.getCategoria().toLowerCase().contains(categoria.toLowerCase())) {
					msCategoriasBksss.add(msCategoriasBk);
				}
			}
		}
		return msCategoriasBksss;
	}

	/**
	 * MS_TAREAS SERVICIO: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS
	 * DERIVACIONES
	 */
	@Override
	public MsTareasBk getMsTareasBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsTareas msTareas = msTareasDao.getMsTareas(id);
		MsTareasBk msTareasBk = null;
		if (msTareas != null) {
			msTareasBk = new MsTareasBk();
			FuncionesStaticas.copyPropertiesObject(msTareasBk, msTareas);
			completarMsTareas(msTareasBk);
			setACLMsTareasBk(msTareasBk, kyUsuarioMod);
		}
		return msTareasBk;
	}

	@Override
	public List<MsTareasBk> getAllMsTareasActivos(Long kyUsuarioMod) {
		List<MsTareasBk> msTareasBkss = new ArrayList<MsTareasBk>();
		try {
			List<MsTareas> msTareass = msTareasDao.getActivasMsTareas();
			for (MsTareas msTareas : msTareass) {
				MsTareasBk msTareasBk = new MsTareasBk();
				FuncionesStaticas.copyPropertiesObject(msTareasBk, msTareas);
				completarMsTareas(msTareasBk);
				setACLMsTareasBk(msTareasBk, kyUsuarioMod);
				msTareasBkss.add(msTareasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msTareasBkss;
	}

	@Override
	public List<MsTareasBk> getAllMsTareasActivosCero(Long kyUsuarioMod) {
		List<MsTareasBk> msTareasBkss = new ArrayList<MsTareasBk>();
		try {
			List<MsTareas> msTareass = msTareasDao.getActivasMsTareasCero();
			for (MsTareas msTareas : msTareass) {
				MsTareasBk msTareasBk = new MsTareasBk();
				FuncionesStaticas.copyPropertiesObject(msTareasBk, msTareas);
				completarMsTareas(msTareasBk);
				setACLMsTareasBk(msTareasBk, kyUsuarioMod);
				msTareasBkss.add(msTareasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msTareasBkss;
	}

	private void completarMsTareas(MsTareasBk msTareasBk) {
		// try{
		// if(msTareasBk.getIduserCrea()!=null &&
		// msTareasBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msTareasBk.getIduserCrea());
		// if(msUsuarios!=null)
		// msTareasBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(msTareasBk.getIduserModif()!=null &&
		// msTareasBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msTareasBk.getIduserModif());
		// if(msUsuarios!=null)
		// msTareasBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public MsTareasBk saveorupdateMsTareasBk(MsTareasBk msTareasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {

		ValidacionMsTareasMng.validarMsTareasBk(msTareasBk);

		MsTareas msTareas = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msTareasBk.getIdtareas() != null && msTareasBk.getIdtareas().longValue() > 0) {

				msTareas = msTareasDao.getMsTareas(msTareasBk.getIdtareas());

				boolean cambios = AuditoriaMsTareasMng.auditarCambiosMsTareas(msTareasBk, msTareas, kyUsuarioMod, user,
						rmtaddress, nivel);

				if (cambios) {
					msTareas.setRtmaddressmodif(rmtaddress);
					msTareas.setIduserModif(kyUsuarioMod);
					msTareas.setFechaModif(hoy);
					msTareasDao.updateMsTareas(msTareas);
				}
			} else {
				msTareasBk.setRtmaddress(rmtaddress);
				msTareasBk.setRtmaddressmodif(rmtaddress);

				msTareasBk.setFechaCrea(hoy);
				msTareasBk.setIduserCrea(kyUsuarioMod);
				msTareasBk.setIduserModif(kyUsuarioMod);
				msTareasBk.setFechaModif(hoy);
				msTareasBk.setEstado(1);

				msTareas = new MsTareas();

				FuncionesStaticas.copyPropertiesObject(msTareas, msTareasBk);
				msTareasDao.saveMsTareas(msTareas);

				log.log(Level.INFO, "CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
						+ "CREADO msTareas" + " :: " + msTareas.getIdtareas().toString() + " :: " + "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msTareasBk = getMsTareasBkXid(msTareas.getIdtareas(), kyUsuarioMod);
		return msTareasBk;
	}

	@Override
	public void deleteMsTareas(MsTareasBk msTareasBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador {
		try {
			MsTareas msTareas = null;
			if (msTareasBk.getIdtareas() != null && msTareasBk.getIdtareas().longValue() > 0) {

				msTareas = msTareasDao.getMsTareas(msTareasBk.getIdtareas());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msTareas.setIduserModif(kyUsuarioMod);
				msTareas.setFechaModif(hoy);
				Integer estadoanterior = msTareas.getEstado();
				msTareas.setEstado(0);

				msTareasDao.updateMsTareas(msTareas);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msTareas" + " :: " + msTareas.getIdtareas().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsTareasBk> getMsTareasXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado,
			Long kyUsuarioMod) {
		List<MsTareasBk> msTareasBkss = new ArrayList<MsTareasBk>();
		try {
			List<MsTareas> msTareassss = msTareasDao.getXFiltro(idtareas, tarea, tiempo, estado);
			for (MsTareas msTareas : msTareassss) {
				MsTareasBk msTareasBk = new MsTareasBk();
				FuncionesStaticas.copyPropertiesObject(msTareasBk, msTareas);
				completarMsTareas(msTareasBk);
				setACLMsTareasBk(msTareasBk, kyUsuarioMod);
				msTareasBkss.add(msTareasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msTareasBkss;
	}

	@Override
	public List<MsTareasBk> getMsTareasXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado, int inicial,
			int MAX, Long kyUsuarioMod) {
		List<MsTareasBk> msTareasBkss = new ArrayList<MsTareasBk>();
		try {
			List<MsTareas> msTareassss = msTareasDao.getXFiltro(idtareas, tarea, tiempo, estado, inicial, MAX);
			for (MsTareas msTareas : msTareassss) {
				MsTareasBk msTareasBk = new MsTareasBk();
				FuncionesStaticas.copyPropertiesObject(msTareasBk, msTareas);
				completarMsTareas(msTareasBk);
				setACLMsTareasBk(msTareasBk, kyUsuarioMod);
				msTareasBkss.add(msTareasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msTareasBkss;
	}

	@Override
	public Long getMsTareasTotalXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado, Long kyUsuarioMod) {
		try {
			Long totalMsTareassss = msTareasDao.getTotalXFiltro(idtareas, tarea, tiempo, estado);

			return totalMsTareassss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLMsTareasBk(MsTareasBk msTareasBk, Long kyUsuarioMod) {
		MsTareasACL msTareasACL = new MsTareasACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSTAREAS_CREA)) {
					msTareasACL.setEsEditable(true);
					msTareasACL.setEliminar(true);
				} else if (roles.contains(Roles.MSTAREAS_VE)) {
					msTareasACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSTAREAS_CREA)) {
					msTareasACL.setEditopcion(1);
				} else {
					msTareasACL.setEditopcion(2);
				}
			} else {
				msTareasACL.setEditopcion(2);
			}
		} else {
			msTareasACL.setEditopcion(2);
		}
		msTareasBk.setMsTareasACL(msTareasACL);
	}

	/**
	 * MS_CATEGORIAS SERVICIO: CATEGORÍAS ASIGADOS A LOS EVENTOS
	 */
	@Override
	public MsCategoriasBk getMsCategoriasBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsCategorias msCategorias = msCategoriasDao.getMsCategorias(id);
		MsCategoriasBk msCategoriasBk = null;
		if (msCategorias != null) {
			msCategoriasBk = new MsCategoriasBk();
			FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
			completarMsCategorias(msCategoriasBk);
			setACLMsCategoriasBk(msCategoriasBk, kyUsuarioMod);
		}
		return msCategoriasBk;
	}

	@Override
	public List<MsCategoriasBk> getAllMsCategoriasActivos(Long kyUsuarioMod) {
		List<MsCategoriasBk> msCategoriasBkss = new ArrayList<MsCategoriasBk>();
		try {
			List<MsCategorias> msCategoriass = msCategoriasDao.getActivasMsCategorias();
			for (MsCategorias msCategorias : msCategoriass) {
				MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
				FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
				completarMsCategorias(msCategoriasBk);
				setACLMsCategoriasBk(msCategoriasBk, kyUsuarioMod);
				msCategoriasBkss.add(msCategoriasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msCategoriasBkss;
	}

	@Override
	public List<MsCategoriasBk> getAllMsCategoriasActivosCero(Long kyUsuarioMod) {
		List<MsCategoriasBk> msCategoriasBkss = new ArrayList<MsCategoriasBk>();
		try {
			List<MsCategorias> msCategoriass = msCategoriasDao.getActivasMsCategoriasCero();
			for (MsCategorias msCategorias : msCategoriass) {
				MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
				FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
				completarMsCategorias(msCategoriasBk);
				setACLMsCategoriasBk(msCategoriasBk, kyUsuarioMod);
				msCategoriasBkss.add(msCategoriasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msCategoriasBkss;
	}

	private void completarMsCategorias(MsCategoriasBk msCategoriasBk) {
		// try{
		// if(msCategoriasBk.getIduserCrea()!=null &&
		// msCategoriasBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msCategoriasBk.getIduserCrea());
		// if(msUsuarios!=null)
		// msCategoriasBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(msCategoriasBk.getIduserModif()!=null &&
		// msCategoriasBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msCategoriasBk.getIduserModif());
		// if(msUsuarios!=null)
		// msCategoriasBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public MsCategoriasBk saveorupdateMsCategoriasBk(MsCategoriasBk msCategoriasBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionMsCategoriasMng.validarMsCategoriasBk(msCategoriasBk);

		MsCategorias msCategorias = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msCategoriasBk.getIdcategorias() != null && msCategoriasBk.getIdcategorias().longValue() > 0) {

				msCategorias = msCategoriasDao.getMsCategorias(msCategoriasBk.getIdcategorias());

				boolean cambios = AuditoriaMsCategoriasMng.auditarCambiosMsCategorias(msCategoriasBk, msCategorias,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					msCategorias.setRmtaddressrst(rmtaddress);
					msCategorias.setIduserModif(kyUsuarioMod);
					msCategorias.setFechaModif(hoy);
					msCategoriasDao.updateMsCategorias(msCategorias);
				}
			} else {
				msCategoriasBk.setRmtaddress(rmtaddress);
				msCategoriasBk.setRmtaddressrst(rmtaddress);

				msCategoriasBk.setFechaCrea(hoy);
				msCategoriasBk.setIduserCrea(kyUsuarioMod);
				msCategoriasBk.setIduserModif(kyUsuarioMod);
				msCategoriasBk.setFechaModif(hoy);
				msCategoriasBk.setEstado(1);

				msCategorias = new MsCategorias();

				FuncionesStaticas.copyPropertiesObject(msCategorias, msCategoriasBk);
				msCategoriasDao.saveMsCategorias(msCategorias);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO msCategorias" + " :: " + msCategorias.getIdcategorias().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msCategoriasBk = getMsCategoriasBkXid(msCategorias.getIdcategorias(), kyUsuarioMod);
		return msCategoriasBk;
	}

	@Override
	public void deleteMsCategorias(MsCategoriasBk msCategoriasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			MsCategorias msCategorias = null;
			if (msCategoriasBk.getIdcategorias() != null && msCategoriasBk.getIdcategorias().longValue() > 0) {

				msCategorias = msCategoriasDao.getMsCategorias(msCategoriasBk.getIdcategorias());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msCategorias.setIduserModif(kyUsuarioMod);
				msCategorias.setFechaModif(hoy);
				Integer estadoanterior = msCategorias.getEstado();
				msCategorias.setEstado(0);

				msCategoriasDao.updateMsCategorias(msCategorias);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msCategorias" + " :: " + msCategorias.getIdcategorias().toString() + " :: "
								+ estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsCategoriasBk> getMsCategoriasXFiltro(Long idcategorias, String categoria, String arraycamposocultos,
			Long kyUsuarioMod) {
		List<MsCategoriasBk> msCategoriasBkss = new ArrayList<MsCategoriasBk>();
		try {
			List<MsCategorias> msCategoriassss = msCategoriasDao.getXFiltro(idcategorias, categoria,
					arraycamposocultos);
			for (MsCategorias msCategorias : msCategoriassss) {
				MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
				FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
				completarMsCategorias(msCategoriasBk);
				setACLMsCategoriasBk(msCategoriasBk, kyUsuarioMod);
				msCategoriasBkss.add(msCategoriasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msCategoriasBkss;
	}

	@Override
	public List<MsCategoriasBk> getMsCategoriasXFiltro(Long idcategorias, String categoria, String arraycamposocultos,
			int inicial, int MAX, Long kyUsuarioMod) {
		List<MsCategoriasBk> msCategoriasBkss = new ArrayList<MsCategoriasBk>();
		try {
			List<MsCategorias> msCategoriassss = msCategoriasDao.getXFiltro(idcategorias, categoria, arraycamposocultos,
					inicial, MAX);
			for (MsCategorias msCategorias : msCategoriassss) {
				MsCategoriasBk msCategoriasBk = new MsCategoriasBk();
				FuncionesStaticas.copyPropertiesObject(msCategoriasBk, msCategorias);
				completarMsCategorias(msCategoriasBk);
				setACLMsCategoriasBk(msCategoriasBk, kyUsuarioMod);
				msCategoriasBkss.add(msCategoriasBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msCategoriasBkss;
	}

	@Override
	public Long getMsCategoriasTotalXFiltro(Long idcategorias, String categoria, String arraycamposocultos,
			Long kyUsuarioMod) {
		try {
			Long totalMsCategoriassss = msCategoriasDao.getTotalXFiltro(idcategorias, categoria, arraycamposocultos);

			return totalMsCategoriassss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLMsCategoriasBk(MsCategoriasBk msCategoriasBk, Long kyUsuarioMod) {
		MsCategoriasACL msCategoriasACL = new MsCategoriasACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSCATEGORIAS_CREA)) {
					msCategoriasACL.setEsEditable(true);
					msCategoriasACL.setEliminar(true);
				} else if (roles.contains(Roles.MSCATEGORIAS_VE)) {
					msCategoriasACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSCATEGORIAS_CREA)) {
					msCategoriasACL.setEditopcion(1);
				} else {
					msCategoriasACL.setEditopcion(2);
				}
			} else {
				msCategoriasACL.setEditopcion(2);
			}
		} else {
			msCategoriasACL.setEditopcion(2);
		}
		msCategoriasBk.setMsCategoriasACL(msCategoriasACL);
	}

	/**
	 * MS_ACTIVIDADES SERVICIO: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS
	 * TAREAS
	 */
	@Override
	public MsActividadesBk getMsActividadesBkXid(Long id, Long kyUsuarioMod) {
		if (id == null)
			return null;
		MsActividades msActividades = msActividadesDao.getMsActividades(id);
		MsActividadesBk msActividadesBk = null;
		if (msActividades != null) {
			msActividadesBk = new MsActividadesBk();
			FuncionesStaticas.copyPropertiesObject(msActividadesBk, msActividades);
			completarMsActividades(msActividadesBk);
			setACLMsActividadesBk(msActividadesBk, kyUsuarioMod);
		}
		return msActividadesBk;
	}

	@Override
	public List<MsActividadesBk> getAllMsActividadesActivos(Long kyUsuarioMod) {
		List<MsActividadesBk> msActividadesBkss = new ArrayList<MsActividadesBk>();
		try {
			List<MsActividades> msActividadess = msActividadesDao.getActivasMsActividades();
			for (MsActividades msActividades : msActividadess) {
				MsActividadesBk msActividadesBk = new MsActividadesBk();
				FuncionesStaticas.copyPropertiesObject(msActividadesBk, msActividades);
				completarMsActividades(msActividadesBk);
				setACLMsActividadesBk(msActividadesBk, kyUsuarioMod);
				msActividadesBkss.add(msActividadesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msActividadesBkss;
	}

	@Override
	public List<MsActividadesBk> getAllMsActividadesActivosCero(Long kyUsuarioMod) {
		List<MsActividadesBk> msActividadesBkss = new ArrayList<MsActividadesBk>();
		try {
			List<MsActividades> msActividadess = msActividadesDao.getActivasMsActividadesCero();
			for (MsActividades msActividades : msActividadess) {
				MsActividadesBk msActividadesBk = new MsActividadesBk();
				FuncionesStaticas.copyPropertiesObject(msActividadesBk, msActividades);
				completarMsActividades(msActividadesBk);
				setACLMsActividadesBk(msActividadesBk, kyUsuarioMod);
				msActividadesBkss.add(msActividadesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msActividadesBkss;
	}

	private void completarMsActividades(MsActividadesBk msActividadesBk) {
		// try{
		// if(msActividadesBk.getIdtareas()!=null &&
		// msActividadesBk.getIdtareas().longValue()>0){
		// MsTareas msTareas =
		// msTareasDao.getMsTareas(msActividadesBk.getIdtareas());
		// if(msTareas!=null)
		// msActividadesBk.setIdtareasTxt(msTareas.getDescripcion());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(msActividadesBk.getIduserCrea()!=null &&
		// msActividadesBk.getIduserCrea().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msActividadesBk.getIduserCrea());
		// if(msUsuarios!=null)
		// msActividadesBk.setIduserCreaTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		// if(msActividadesBk.getIduserModif()!=null &&
		// msActividadesBk.getIduserModif().longValue()>0){
		// MsUsuarios msUsuarios =
		// msUsuariosDao.getMsUsuarios(msActividadesBk.getIduserModif());
		// if(msUsuarios!=null)
		// msActividadesBk.setIduserModifTxt(FuncionesStaticas.getNombreCompleto(msUsuarios));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public MsActividadesBk saveorupdateMsActividadesBk(MsActividadesBk msActividadesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador {

		ValidacionMsActividadesMng.validarMsActividadesBk(msActividadesBk);

		MsActividades msActividades = null;
		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		int nivel = 1;

		try {
			if (msActividadesBk.getIdactividades() != null && msActividadesBk.getIdactividades().longValue() > 0) {

				msActividades = msActividadesDao.getMsActividades(msActividadesBk.getIdactividades());

				boolean cambios = AuditoriaMsActividadesMng.auditarCambiosMsActividades(msActividadesBk, msActividades,
						kyUsuarioMod, user, rmtaddress, nivel);

				if (cambios) {
					msActividades.setRtmaddressmodif(rmtaddress);
					msActividades.setIduserModif(kyUsuarioMod);
					msActividades.setFechaModif(hoy);
					msActividadesDao.updateMsActividades(msActividades);
				}
			} else {
				msActividadesBk.setRtmaddress(rmtaddress);
				msActividadesBk.setRtmaddressmodif(rmtaddress);

				msActividadesBk.setFechaCrea(hoy);
				msActividadesBk.setIduserCrea(kyUsuarioMod);
				msActividadesBk.setIduserModif(kyUsuarioMod);
				msActividadesBk.setFechaModif(hoy);
				msActividadesBk.setEstado(1);

				msActividades = new MsActividades();

				FuncionesStaticas.copyPropertiesObject(msActividades, msActividadesBk);
				msActividadesDao.saveMsActividades(msActividades);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "CREADO msActividades" + " :: " + msActividades.getIdactividades().toString() + " :: "
								+ "0" + " :: " + "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}

		msActividadesBk = getMsActividadesBkXid(msActividades.getIdactividades(), kyUsuarioMod);
		return msActividadesBk;
	}

	@Override
	public void deleteMsActividades(MsActividadesBk msActividadesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador {
		try {
			MsActividades msActividades = null;
			if (msActividadesBk.getIdactividades() != null && msActividadesBk.getIdactividades().longValue() > 0) {

				msActividades = msActividadesDao.getMsActividades(msActividadesBk.getIdactividades());

				Timestamp hoy = new Timestamp(System.currentTimeMillis());

				msActividades.setIduserModif(kyUsuarioMod);
				msActividades.setFechaModif(hoy);
				Integer estadoanterior = msActividades.getEstado();
				msActividades.setEstado(0);

				msActividadesDao.updateMsActividades(msActividades);

				log.log(Level.INFO,
						"CAMBIO :: " + kyUsuarioMod + " :: " + user + " :: " + rmtaddress + " :: "
								+ "ELIMINADO msActividades" + " :: " + msActividades.getIdactividades().toString()
								+ " :: " + estadoanterior + " :: " + "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	@Override
	public List<MsActividadesBk> getMsActividadesXFiltro(Long idactividades, Long idtareas, String actividad,
			Long kyUsuarioMod) {
		List<MsActividadesBk> msActividadesBkss = new ArrayList<MsActividadesBk>();
		try {
			List<MsActividades> msActividadessss = msActividadesDao.getXFiltro(idactividades, idtareas, actividad);
			for (MsActividades msActividades : msActividadessss) {
				MsActividadesBk msActividadesBk = new MsActividadesBk();
				FuncionesStaticas.copyPropertiesObject(msActividadesBk, msActividades);
				completarMsActividades(msActividadesBk);
				setACLMsActividadesBk(msActividadesBk, kyUsuarioMod);
				msActividadesBkss.add(msActividadesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msActividadesBkss;
	}

	@Override
	public List<MsActividadesBk> getMsActividadesXFiltro(Long idactividades, Long idtareas, String actividad,
			int inicial, int MAX, Long kyUsuarioMod) {
		List<MsActividadesBk> msActividadesBkss = new ArrayList<MsActividadesBk>();
		try {
			List<MsActividades> msActividadessss = msActividadesDao.getXFiltro(idactividades, idtareas, actividad,
					inicial, MAX);
			for (MsActividades msActividades : msActividadessss) {
				MsActividadesBk msActividadesBk = new MsActividadesBk();
				FuncionesStaticas.copyPropertiesObject(msActividadesBk, msActividades);
				completarMsActividades(msActividadesBk);
				setACLMsActividadesBk(msActividadesBk, kyUsuarioMod);
				msActividadesBkss.add(msActividadesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msActividadesBkss;
	}

	@Override
	public Long getMsActividadesTotalXFiltro(Long idactividades, Long idtareas, String actividad, Long kyUsuarioMod) {
		try {
			Long totalMsActividadessss = msActividadesDao.getTotalXFiltro(idactividades, idtareas, actividad);
			return totalMsActividadessss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setACLMsActividadesBk(MsActividadesBk msActividadesBk, Long kyUsuarioMod) {
		MsActividadesACL msActividadesACL = new MsActividadesACL();
		if (kyUsuarioMod != null) {
			MsUsuariosBk msusuariosbk = getMsUsuariosBkXidCH(kyUsuarioMod);
			if (msusuariosbk != null && msusuariosbk.getRolesSistema() != null) {
				List<String> roles = msusuariosbk.getRolesSistema();
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSACTIVIDADES_CREA)) {
					msActividadesACL.setEsEditable(true);
					msActividadesACL.setEliminar(true);
				} else if (roles.contains(Roles.MSACTIVIDADES_VE)) {
					msActividadesACL.setEsEditable(true);
				}
				if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSACTIVIDADES_CREA)) {
					msActividadesACL.setEditopcion(1);
				} else {
					msActividadesACL.setEditopcion(2);
				}
			} else {
				msActividadesACL.setEditopcion(2);
			}
		} else {
			msActividadesACL.setEditopcion(2);
		}
		msActividadesBk.setMsActividadesACL(msActividadesACL);
	}

	// MPINARES 01092023 - INICIO
	@Override
	public List<IDValorDto> getPrtParametrosTipoEvento() {
		List<IDValorDto> prtParametrosBks = new ArrayList<IDValorDto>();
		try {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TIPO DE EVENTO");
			for (PrtParametros prtParametros : prtParametross) {
				IDValorDto iDValorDto = new IDValorDto(prtParametros.getIdparametro(), prtParametros.getDescripcion());
				prtParametrosBks.add(iDValorDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prtParametrosBks;
	}

	@Override
	public List<IDsValorDto> getPrtParametrosTipoEventoo() {
		if (prtParametrosContratoTipoEventoo == null) {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TIPO DE EVENTO");
			if (prtParametross != null) {
				prtParametrosContratoTipoEventoo = new ArrayList<IDsValorDto>();
				for (PrtParametros prtParametros : prtParametross) {
					IDsValorDto iDsValorDto = new IDsValorDto(prtParametros.getDescripcion(),
							prtParametros.getDescripcion());
					prtParametrosContratoTipoEventoo.add(iDsValorDto);
				}
			}
		}
		return prtParametrosContratoTipoEventoo;
	}

	@Override
	public List<IDsValorDto> getPrtParametrosTipoVuelo() {
		if (prtParametrosContratoTipoEventoo == null) {
			List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TIPO DE VUELO");
			if (prtParametross != null) {
				prtParametrosContratoTipoEventoo = new ArrayList<IDsValorDto>();
				for (PrtParametros prtParametros : prtParametross) {
					IDsValorDto iDsValorDto = new IDsValorDto(prtParametros.getDescripcion(),
							prtParametros.getDescripcion());
					prtParametrosContratoTipoEventoo.add(iDsValorDto);
				}
			}
		}
		return prtParametrosContratoTipoEventoo;
	}

	// MPINARES 01092023 - FIN
	// MPINARES 01092023 - INICIO
	@Override
	public List<TdCategEventoBk> getTdCategEventoXFiltro(Long idcategevento, Long idevent, Long idcategorias) {
		List<TdCategEventoBk> tdCategEventoBkss = new ArrayList<TdCategEventoBk>();
		try {
			List<TdCategEvento> tdCategEventosss = tdCategEventoDao.getXFiltro(idcategevento, idevent, idcategorias);
			for (TdCategEvento tdCategEvento : tdCategEventosss) {
				TdCategEventoBk tdCategEventoBk = new TdCategEventoBk();
				FuncionesStaticas.copyPropertiesObject(tdCategEventoBk, tdCategEvento);
				// completarTdItinerario(tdItinerarioBk);
				// setACLTdItinerarioBk(tdItinerarioBk, kyUsuarioMod);
				tdCategEventoBkss.add(tdCategEventoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdCategEventoBkss;
	}

	@Override
	public List<TdParticipantesBk> getTdParticipantesXFiltroXIdEvento(Long idevent) {
		List<TdParticipantesBk> tdParticipantesBkss = new ArrayList<TdParticipantesBk>();
		try {
			List<TdParticipantes> tdParticipantessss = tdParticipantesDao.getXFiltro(idevent);
			for (TdParticipantes tdParticipantes : tdParticipantessss) {
				TdParticipantesBk tdParticipantesBk = new TdParticipantesBk();
				FuncionesStaticas.copyPropertiesObject(tdParticipantesBk, tdParticipantes);
				completarTdParticipantes(tdParticipantesBk);
				// setACLTdParticipantesBk(tdParticipantesBk, kyUsuarioMod);
				tdParticipantesBkss.add(tdParticipantesBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdParticipantesBkss;
	}
	// MPINARES 01092023 - FIN

	@Override
	public List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado,
			Long idEvento, Long kyUsuarioMod) {// vbaldeon 08092023
		List<TdFlujoBk> TdFlujoBks = new ArrayList<TdFlujoBk>();
		try {
			List<TdFlujo> TdFlujosss = tdFlujoDao.getXFiltro(idflujo, idflujopadre, idEvento, estado);// vbaldeon
																												// 08092023
			for (TdFlujo TdFlujo : TdFlujosss) {
				TdFlujoBk TdFlujoBk = new TdFlujoBk();
				FuncionesStaticas.copyPropertiesObject(TdFlujoBk, TdFlujo);
				completarTdFlujo(TdFlujoBk);
				TdFlujoBks.add(TdFlujoBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TdFlujoBks;
	}

	// vbaldeon 25092023 inicio
	@Override
	public void notificarFlujo(TdFlujoBk tdFlujoBk, Long kyUsuarioMod, MsUsuariosBk usuariologueado, String rmtaddress)
			throws Validador {

		if (tdFlujoBk == null) {
			throw new Validador("ERROR ATENCIÓN INDEFINIDA, NO SE PUEDE REALIZAR ESTA OPERACIÓN.");
		}

		Long areadest = tdFlujoBk.getIdunidadAtiende();
		Long usuariodest = tdFlujoBk.getIduserAtiende();

		Timestamp hoy = new Timestamp(System.currentTimeMillis());

		try {
			if (usuariodest != null && usuariodest.longValue() > 0) {
				MsUsuariosBk msUsuarios = getMsUsuariosBkXidCH(tdFlujoBk.getIduserAtiende());
				if (msUsuarios != null && msUsuarios.getCorreo() != null) {
					String remite = FuncionesStaticas.getNombreCompleto(msUsuarios);

					String tipo = getEmailservice().get("TIPO-SSL-TLS-NA");
					String fromEmail = getEmailservice().get("FROMEMAIL");
					String password = getEmailservice().get("PSSWRD");
					String SMTPHost = getEmailservice().get("SMTPHOST");
					String SSLPort = getEmailservice().get("SSLPORT");
					String SMTPPort = getEmailservice().get("SMTPPORT");

					String subject = "MEF-NOTIFICACIONES-SISTEMA SISEVENT";
					String body = "Se notifica sobre la asignación de la tarea ....";

					List<String> correos = new ArrayList<String>();
					correos.add(msUsuarios.getCorreo());

					for (String toEmail : correos) {
						try {
							EMailUtil.sendEmail(tipo, fromEmail, password, toEmail, SMTPHost, SSLPort, SMTPPort,
									subject, body, tdFlujoBk, remite, msUsuarios, null, debugmail, 0);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Validador(e.getLocalizedMessage());
		}

	}
	//// vbaldeon 25092023 fin
	@Override
	public List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
			Long kyUsuarioMod,Long idGrupo ) {//vbaldeon 08092023
		List<TdGruposflujosBk> tdGruposflujosBkss = new ArrayList<TdGruposflujosBk>();
		try {
			List<TdGruposflujos> tdGruposflujossss = tdGruposflujosDao.getXFiltro(idtareas, idunidadDestino,
					iduserDestino, idGrupo); //vbaldeon 08092023
			for (TdGruposflujos tdGruposflujos : tdGruposflujossss) {
				TdGruposflujosBk tdGruposflujosBk = new TdGruposflujosBk();
				FuncionesStaticas.copyPropertiesObject(tdGruposflujosBk, tdGruposflujos);
				completarTdGruposflujos(tdGruposflujosBk);
				setACLTdGruposflujosBk(tdGruposflujosBk, kyUsuarioMod);
				tdGruposflujosBkss.add(tdGruposflujosBk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdGruposflujosBkss;
	}
	
	//vbaldeon 08092023 INICIO
			@Override
			public List<IDValorDto> getPrtParametrosContratoTipoTarea() {
				if (prtParametrosContratoTipoTarea== null) {
					List<PrtParametros> prtParametross = prtParametrosDao.getXDescripcion("TIPO DE TAREA");
					IDValorDto iDsValorDto;
					if (prtParametross != null) {
						prtParametrosContratoTipoTarea = new ArrayList<IDValorDto>();
						for (PrtParametros prtParametros : prtParametross) {
							iDsValorDto= new IDValorDto(prtParametros.getIdparametro(),
									prtParametros.getDescripcion());
							prtParametrosContratoTipoTarea.add(iDsValorDto);
						}
					}
				}
				return prtParametrosContratoTipoTarea;
			}
			
			//vbaldeon 08092023 fin
}