package pe.gob.mef.sisevent.bs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import pe.gob.mef.sisevent.bs.cache.clases.CacheMsUsuariosBk;
import pe.gob.mef.sisevent.bs.domain.MsUbigeoId;
import pe.gob.mef.sisevent.bs.exception.Validador;
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

/**
 * MS_USUARIOS SERVICIO: ALMACENA LOS USUARIOS REGISTRADOS EN EL SISTEMA
 * USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 25/11/2020 22:19
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/
 *          /Carlos Aguilar Chamochumbi / 25/11/2020 22:19 / Creación de la
 *          clase /
 * 
 */
public interface Servicio {
	MsUsuariosBk getMsUsuariosBkXid(Long id, String user);

	List<MsUsuariosBk> getAllMsUsuariosActivos(String user);

	MsUsuariosBk saveorupdateMsUsuariosBk(MsUsuariosBk MsUsuariosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress, boolean solocontrasenia) throws Validador;

	void deleteMsUsuarios(MsUsuariosBk MsUsuariosBk, String user, Long kyUsuarioMod, Long kyAreaMod, Long kySedeMod,
			String rmtaddress) throws Validador;

	MsRolesBk getMsRolesBkXid(Long id);

	List<MsRolesBk> getAllMsRolesActivos();

	MsRolesBk saveorupdateMsRolesBk(MsRolesBk msRolesBk, String user, Long kyUsuarioMod, Long kyAreaMod, Long kySedeMod,
			String rmtaddress) throws Validador;

	void deleteMsRoles(MsRolesBk msRolesBk, String user, Long kyUsuarioMod, Long kyAreaMod, Long kySedeMod,
			String rmtaddress) throws Validador;

	MsUnidadesOrgBk getMsUnidadesOrgBkXid(Long id, Long kyUsuarioMod);

	List<MsUnidadesOrgBk> getAllMsUnidadesOrgActivos(Long kyUsuarioMod);

	MsUnidadesOrgBk saveorupdateMsUnidadesOrgBk(MsUnidadesOrgBk msUnidadesOrgBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, Long kySedeMod, String rmtaddress) throws Validador;

	void deleteMsUnidadesOrg(MsUnidadesOrgBk msUnidadesOrgBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador;

	PrtParametrosBk getPrtParametrosBkXid(Long id, Long kyUsuarioMod, String key);

	List<PrtParametrosBk> getAllPrtParametrosActivos(Long kyUsuarioMod, String key);

	PrtParametrosBk saveorupdatePrtParametrosBk(PrtParametrosBk prtParametrosBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String kySedeMod, String rmtaddress, String key) throws Validador;

	void deletePrtParametros(PrtParametrosBk prtParametrosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			Long kySedeMod, String rmtaddress) throws Validador;

	MsUsuariosBk getMsUsuariosBkXUsername(String username);

	List<String> getListaSedes();

	List<NodosDto> getUOTreeCache();

	List<MsUnidadesOrgDto> getUOListaCache();

	List<MsUsuariosBk> getMsUsuariosXFiltro(String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, Long iduserModif, Integer estado, int inicial, int MAX);

	Long getMsUsuariosTotalXFiltro(String username, String nombres, String apellidoPaterno, String apellidoMaterno,
			Long iduserModif, Integer estado);

	List<MsUsuariosBk> getMsUsuariosXFiltro(String username, String nombres, String apellidoPaterno,
			String apellidoMaterno, Long iduserModif, Integer estado);

	void setCacheMsUsuariosBkActivos(CacheMsUsuariosBk cacheMsUsuariosBk);

	CacheMsUsuariosBk getCacheMsUsuariosBkActivos();

	List<MsUsuariosBk> getMsUsuariosBkActivos(String user);

	/**
	 * MS_INSTITUCIONES SERVICIO: INSTITUCIONES Y ENTIDADES
	 */
	MsInstitucionesBk getMsInstitucionesBkXid(Long id, Long kyUsuarioMod);

	List<MsInstitucionesBk> getAllMsInstitucionesActivos(Long kyUsuarioMod);

	MsInstitucionesBk saveorupdateMsInstitucionesBk(MsInstitucionesBk msInstitucionesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteMsInstituciones(MsInstitucionesBk msInstitucionesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<MsInstitucionesBk> getMsInstitucionesXFiltro(Long idprovee, String razonSocial, String siglas, String ruc,
			String telefono, String direccion, Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getMsInstitucionesTotalXFiltro(Long idprovee, String razonSocial, String siglas, String ruc, String telefono,
			String direccion, Integer estado);

	List<MsInstitucionesBk> getMsInstitucionesXFiltro(Long idprovee, String razonSocial, String siglas, String ruc,
			String telefono, String direccion, Integer estado, Long kyUsuarioMod);

	/**
	 * MS_UBIGEO SERVICIO: UBIGEO DATOS OTORGADOS POR EL INEI
	 */
	MsUbigeoBk getMsUbigeoBkXid(MsUbigeoId id, Long kyUsuarioMod);

	List<MsUbigeoBk> getAllMsUbigeoActivos(Long kyUsuarioMod);

	MsUbigeoBk saveorupdateMsUbigeoBk(MsUbigeoBk msUbigeoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteMsUbigeo(MsUbigeoBk msUbigeoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<MsUbigeoBk> getMsUbigeoXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre,
			Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getMsUbigeoTotalXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre, Integer estado);

	List<MsUbigeoBk> getMsUbigeoXFiltro(Integer coddpto, Integer codprov, Integer coddist, String nombre,
			Integer estado, Long kyUsuarioMod);

	/**
	 * MS_PAISES SERVICIO: PAISES SEGÚN ISO
	 */
	MsPaisesBk getMsPaisesBkXid(Long id, Long kyUsuarioMod);

	List<MsPaisesBk> getAllMsPaisesActivos(Long kyUsuarioMod);

	MsPaisesBk saveorupdateMsPaisesBk(MsPaisesBk msPaisesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteMsPaises(MsPaisesBk msPaisesBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<MsPaisesBk> getMsPaisesXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3, String paiNombre,
			Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getMsPaisesTotalXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3, String paiNombre,
			Integer estado);

	List<MsPaisesBk> getMsPaisesXFiltro(Long paiPk, Long paiIsonum, String paiIso2, String paiIso3, String paiNombre,
			Integer estado, Long kyUsuarioMod);

	/**
	 * MS_PERSONAS SERVICIO: PERSONAS NATURALES
	 */
	MsPersonasBk getMsPersonasBkXid(Long id, Long kyUsuarioMod);

	List<MsPersonasBk> getAllMsPersonasActivos(Long kyUsuarioMod);

	MsPersonasBk saveorupdateMsPersonasBk(MsPersonasBk msPersonasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteMsPersonas(MsPersonasBk msPersonasBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<MsPersonasBk> getMsPersonasXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno,
			String nombres, String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular,
			String direccion, Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getMsPersonasTotalXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno, String nombres,
			String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular, String direccion,
			Integer estado);

	List<MsPersonasBk> getMsPersonasXFiltro(Long idperson, String apellidoPaterno, String apellidoMaterno,
			String nombres, String sexo, Long tipodocumento, String documentoNumero, String telefono, String celular,
			String direccion, Integer estado, Long kyUsuarioMod);

	/**
	 * TD_ANEXOS SERVICIO: ANEXOS DE LOS MOVIMIENTOS
	 */
	TdAnexosBk getTdAnexosBkXid(Long id);

	List<TdAnexosBk> getAllTdAnexosActivos();

	TdAnexosBk saveorupdateTdAnexosBk(TdAnexosBk tdAnexosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String kySedeMod, String rmtaddress) throws Validador;

	void deleteTdAnexos(TdAnexosBk tdAnexosBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<TdAnexosBk> getTdAnexosXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado,
			int inicial, int MAX, Long kyUsuarioMod);

	Long getTdAnexosTotalXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado);

	List<TdAnexosBk> getTdAnexosXFiltro(Long idsacc, String filename, String filenameoriginal, Integer estado,
			Long kyUsuarioMod);

	/**
	 * TD_FLUJO SERVICIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DE LAS ATENCIONES
	 */
	TdFlujoBk getTdFlujoBkXid(Long id);

	List<TdFlujoBk> getAllTdFlujoActivos();

	TdFlujoBk saveorupdateTdFlujoBk(TdFlujoBk tdFlujoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String kySedeMod, String rmtaddress) throws Validador;

	void deleteTdFlujo(TdFlujoBk tdFlujoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado, int inicial,
			int MAX, Long kyUsuarioMod);

	Long getTdFlujoTotalXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado);

	List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado, Long kyUsuarioMod);

	List<MsInstitucionesDto> getMsInstitucionesXRuc(String ruc);

	List<MsInstitucionesDto> getMsInstitucionesXRazonSocial(String qryText);

	List<MsPersonasDto> getMsPersonasXDNI(String dni);

	List<MsPersonasDto> getMsPersonasXNombre(String nombre, String apellidoPaterno, String apellidoMaterno);

	List<IDValorDto> getPrtParametrosCargos();

	List<IDValorDto> getPrtParametrosCanales();

	List<IDValorDto> getPrtParametrosTemas();

	/**
	 * TD_FERIADOS SERVICIO: DÍAS NO LABORABLES
	 */

	TdFeriadosBk getTdFeriadosBkXid(Long id, Long kyUsuarioMod);

	List<TdFeriadosBk> getAllTdFeriadosActivos(Long kyUsuarioMod);

	TdFeriadosBk saveorupdateTdFeriadosBk(TdFeriadosBk tdFeriadosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String kySedeMod, String rmtaddress) throws Validador;

	void deleteTdFeriados(TdFeriadosBk tdFeriadosBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<TdFeriadosBk> getTdFeriadosXFiltro(Timestamp fechaFeriado, String descricion, Integer estado, int inicial,
			int MAX, Long kyUsuarioMod);

	Long getTdFeriadosTotalXFiltro(Timestamp fechaFeriado, String descricion, Integer estado);

	List<TdFeriadosBk> getTdFeriadosXFiltro(Timestamp fechaFeriado, String descricion, Integer estado,
			Long kyUsuarioMod);

	List<Timestamp> getDiasFeriados();

	List<TdAnexosBk> getTdAnexosXAtencioYFlujo(Long idsacc, Long idflujo);

	List<TdFlujoBk> getTdFlujoXIdAt(Long idsacc);

	List<MsUsuariosBk> getMsUsuariosXCrea();

	List<MsUsuariosBk> getUsuariosquecrean();

	List<TdAnexosDto> getTdAnexosDtoXAtencioYFlujo(Long idsacc, Long idflujo);

	List<MsUsuariosBk> getMsUsuariosXUsuariosArea();

	List<MsUsuariosBk> getUsuariosdearea();

	void inicialiceData();

	void setDebugmail(boolean debugmail);

	String previwnotificarTdAtencionesBk(Long idsacc, Long kyUsuarioMod, MsUsuariosBk usuariologueado,
			String rmtaddress, String observacion, boolean esEntidad, Long idprovee, List<TdAnexosBk> tdAnexosBkss)
			throws Validador;// MPINARES 27042022 - INICIO

	Map<String, Long> getTiemposestados();

	List<IDValorDto> getPrtParametrosPadre();

	PrtParametrosBk getPrtParametrosBkXDesc(String descripcion, Long kyUsuarioMod);

	List<PrtParametrosBk> getAllPrtParametrosActivosCero(Long kyUsuarioMod, String key);

	List<MsInstitucionesBk> getAllMsInstitucionesActivosCero(Long kyUsuarioMod);

	List<MsPaisesBk> getAllMsPaisesActivosCero(Long kyUsuarioMod);

	List<MsPersonasBk> getAllMsPersonasActivosCero(Long kyUsuarioMod);

	List<MsUbigeoBk> getAllMsUbigeoActivosCero(Long kyUsuarioMod);

	List<MsUnidadesOrgBk> getAllMsUnidadesOrgActivosCero(Long kyUsuarioMod);

	List<MsUsuariosBk> getAllMsUsuariosActivosCero(String user);

	List<TdFeriadosBk> getAllTdFeriadosActivosCero(Long kyUsuarioMod);

	List<IDValorDto> getListaMsPaisesActivos();

	String getNombreDepartamento(int idDpto);

	String getNombreProvincia(int idDpto, int idProv);

	String getNombreDistrito(int idDpto, int idProv, int idDist);

	List<MsUbigeoBk> getDepartamentos();

	List<MsUbigeoBk> getProvincias(Integer id_dpto);

	List<MsUbigeoBk> getDistritos(Integer id_dpto, Integer id_prov);

	Long getxDefectoCodpais();

	Integer getxDefectoCoddpto();

	Integer getxDefectoCodprov();

	Integer getxDefectoCoddist();

	List<IIDValorDto> getDepartamentosV();

	List<IIDValorDto> getProvinciasV(Integer id_dpto);

	List<IIDValorDto> getDistritosV(Integer id_dpto, Integer id_prov);

	String getEndpointVentanilla();

	List<MsPersonasDto> getMsPersonasXTelefono(String telefono, String celular);

	Progreso getProgreso();

	void inicializeCaches();

	List<PrtParametrosBk> getPrtParametrosHijosForKey(Long kyUsuarioMod, String key);

	List<IDValorDto> getPrtParametrosPadre(String skey);

	List<MsUsuariosBk> getMsUsuariosXPrincipalArea(Long idunidad);

	List<IDValorDto> getPrtParametrosTiposEntida();

	List<MsInstitucionesDto> getMsInstitucionesXRazonSocial(String tipoentidad, String qryText);

	void actualizarEntidad(Long idprovee, String correo, MsUsuariosBk usuariologueado, String rmtaddress);

	List<IDValorDto> getPrtParametrosPadreNo0(String skey);

	/**
	 * TD_UBICACIONES SERVICIO: UBICACIONES ASIGADOS A LOS EVENTOS
	 */
	TdUbicacionesBk getTdUbicacionesBkXid(Long id, Long kyUsuarioMod);

	List<TdUbicacionesBk> getAllTdUbicacionesActivos(Long kyUsuarioMod);

	List<TdUbicacionesBk> getAllTdUbicacionesActivosCero(Long kyUsuarioMod);

	TdUbicacionesBk saveorupdateTdUbicacionesBk(TdUbicacionesBk tdUbicacionesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteTdUbicaciones(TdUbicacionesBk tdUbicacionesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<TdUbicacionesBk> getTdUbicacionesXFiltro(Long idubicaciones, Long idevent, String ubicacion, Integer estado,
			int inicial, int MAX, Long kyUsuarioMod);

	Long getTdUbicacionesTotalXFiltro(Long idubicaciones, Long idevent, String ubicacion, Integer estado,
			Long kyUsuarioMod);

	List<TdUbicacionesBk> getTdUbicacionesXFiltro(Long idubicaciones, Long idevent, String ubicacion, Integer estado,
			Long kyUsuarioMod);
	/// ADICIONALES

	List<IDValorDto> getMsPaisesPaiPk();

	/**
	 * TD_PARTICIPANTES SERVICIO: PARTICIPANTES O ACOMPAÑANTES ASIGADOS A LOS
	 * EVENTOS
	 */
	TdParticipantesBk getTdParticipantesBkXid(Long id, Long kyUsuarioMod);

	List<TdParticipantesBk> getAllTdParticipantesActivos(Long kyUsuarioMod);

	List<TdParticipantesBk> getAllTdParticipantesActivosCero(Long kyUsuarioMod);

	TdParticipantesBk saveorupdateTdParticipantesBk(TdParticipantesBk tdParticipantesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteTdParticipantes(TdParticipantesBk tdParticipantesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<TdParticipantesBk> getTdParticipantesXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson,
			String nombresrazonsocial, Integer tipo, Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getTdParticipantesTotalXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson, String nombresrazonsocial,
			Integer tipo, Integer estado, Long kyUsuarioMod);

	List<TdParticipantesBk> getTdParticipantesXFiltro(Long idparticipantes, Long idusuarioIdproveeIdperson,
			String nombresrazonsocial, Integer tipo, Integer estado, Long kyUsuarioMod);

	/**
	 * TD_ITINERARIO SERVICIO: ITINERARIO DE LOS VUELOS A REALIZARCE
	 */
	TdItinerarioBk getTdItinerarioBkXid(Long id, Long kyUsuarioMod);

	List<TdItinerarioBk> getAllTdItinerarioActivos(Long kyUsuarioMod);

	List<TdItinerarioBk> getAllTdItinerarioActivosCero(Long kyUsuarioMod);

	TdItinerarioBk saveorupdateTdItinerarioBk(TdItinerarioBk tdItinerarioBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteTdItinerario(TdItinerarioBk tdItinerarioBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<TdItinerarioBk> getTdItinerarioXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto,
			Integer codprov, int inicial, int MAX, Long kyUsuarioMod);

	Long getTdItinerarioTotalXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto, Integer codprov,
			Long kyUsuarioMod);

	List<TdItinerarioBk> getTdItinerarioXFiltro(Long iditinerario, Long idevent, Long codpais, Integer coddpto,
			Integer codprov, Long kyUsuarioMod);
	/// ADICIONALES

	List<IDValorDto> getTdEventoIdevent();

	/**
	 * TD_GRUPOS SERVICIO: GRUPOS DE FLUJOS QUE SE ASIGNAN A LOS EVENTOS
	 */
	TdGruposBk getTdGruposBkXid(Long id, Long kyUsuarioMod);

	List<TdGruposBk> getAllTdGruposActivos(Long kyUsuarioMod);

	List<TdGruposBk> getAllTdGruposActivosCero(Long kyUsuarioMod);

	TdGruposBk saveorupdateTdGruposBk(TdGruposBk tdGruposBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteTdGrupos(TdGruposBk tdGruposBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<TdGruposBk> getTdGruposXFiltro(Long idgrupo, String grupo, Integer estado, int inicial, int MAX,
			Long kyUsuarioMod);

	Long getTdGruposTotalXFiltro(Long idgrupo, String grupo, Integer estado, Long kyUsuarioMod);

	List<TdGruposBk> getTdGruposXFiltro(Long idgrupo, String grupo, Integer estado, Long kyUsuarioMod);

	/**
	 * TD_GRUPOSFLUJOS SERVICIO: MOVIMIENTOS QUE SE VAN DANDO DENTRO DEL GRUPO
	 */
	TdGruposflujosBk getTdGruposflujosBkXid(Long id, Long kyUsuarioMod);

	List<TdGruposflujosBk> getAllTdGruposflujosActivos(Long kyUsuarioMod);

	List<TdGruposflujosBk> getAllTdGruposflujosActivosCero(Long kyUsuarioMod);

	TdGruposflujosBk saveorupdateTdGruposflujosBk(TdGruposflujosBk tdGruposflujosBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteTdGruposflujos(TdGruposflujosBk tdGruposflujosBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

//	List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
//			int inicial, int MAX, Long kyUsuarioMod);
//
//	Long getTdGruposflujosTotalXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino, Long kyUsuarioMod);
//
//	List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
//			Long kyUsuarioMod);

	/**
	 * TD_EVENTO SERVICIO: EVENTO
	 */
	TdEventoBk getTdEventoBkXid(Long id, Long kyUsuarioMod);

	List<TdEventoBk> getAllTdEventoActivos(Long kyUsuarioMod);

	List<TdEventoBk> getAllTdEventoActivosCero(Long kyUsuarioMod);

	TdEventoBk saveorupdateTdEventoBk(TdEventoBk tdEventoBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteTdEvento(TdEventoBk tdEventoBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<TdEventoBk> getTdEventoXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, int inicial, int MAX, Long kyUsuarioMod);

	Long getTdEventoTotalXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, Long kyUsuarioMod);

	List<TdEventoBk> getTdEventoXFiltro(Long idevent, String titulo, Timestamp fechaSoliIni, Long idcategorias,
			String modalidad, Integer estado, Long kyUsuarioMod);
	/// ADICIONALES

	public List<MsInstitucionesDto> getMsInstitucionesCache();

	public List<MsPersonasDto> getMsPersonasCache();

	List<IDValorDto> getPrtParametrosIdparametro();

	/**
	 * MS_TAREAS SERVICIO: ALMACENA LAS TAREAS QUE SE APLICARÁN EN LAS
	 * DERIVACIONES
	 */
	MsTareasBk getMsTareasBkXid(Long id, Long kyUsuarioMod);

	List<MsTareasBk> getAllMsTareasActivos(Long kyUsuarioMod);

	List<MsTareasBk> getAllMsTareasActivosCero(Long kyUsuarioMod);

	MsTareasBk saveorupdateMsTareasBk(MsTareasBk msTareasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	void deleteMsTareas(MsTareasBk msTareasBk, String user, Long kyUsuarioMod, Long kyAreaMod, String rmtaddress)
			throws Validador;

	List<MsTareasBk> getMsTareasXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado, int inicial, int MAX,
			Long kyUsuarioMod);

	Long getMsTareasTotalXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado, Long kyUsuarioMod);

	List<MsTareasBk> getMsTareasXFiltro(Long idtareas, String tarea, Long tiempo, Integer estado, Long kyUsuarioMod);

	/**
	 * MS_CATEGORIAS SERVICIO: CATEGORÍAS ASIGADOS A LOS EVENTOS
	 */
	MsCategoriasBk getMsCategoriasBkXid(Long id, Long kyUsuarioMod);

	List<MsCategoriasBk> getAllMsCategoriasActivos(Long kyUsuarioMod);

	List<MsCategoriasBk> getAllMsCategoriasActivosCero(Long kyUsuarioMod);

	MsCategoriasBk saveorupdateMsCategoriasBk(MsCategoriasBk msCategoriasBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteMsCategorias(MsCategoriasBk msCategoriasBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<MsCategoriasBk> getMsCategoriasXFiltro(Long idcategorias, String categoria, String arraycamposocultos,
			int inicial, int MAX, Long kyUsuarioMod);

	Long getMsCategoriasTotalXFiltro(Long idcategorias, String categoria, String arraycamposocultos, Long kyUsuarioMod);

	List<MsCategoriasBk> getMsCategoriasXFiltro(Long idcategorias, String categoria, String arraycamposocultos,
			Long kyUsuarioMod);

	/**
	 * MS_ACTIVIDADES SERVICIO: ALMACENA LAS ACTIVIDADES QUE SE APLICARÁN EN LAS
	 * TAREAS
	 */
	MsActividadesBk getMsActividadesBkXid(Long id, Long kyUsuarioMod);

	List<MsActividadesBk> getAllMsActividadesActivos(Long kyUsuarioMod);

	List<MsActividadesBk> getAllMsActividadesActivosCero(Long kyUsuarioMod);

	MsActividadesBk saveorupdateMsActividadesBk(MsActividadesBk msActividadesBk, String user, Long kyUsuarioMod,
			Long kyAreaMod, String rmtaddress) throws Validador;

	void deleteMsActividades(MsActividadesBk msActividadesBk, String user, Long kyUsuarioMod, Long kyAreaMod,
			String rmtaddress) throws Validador;

	List<MsActividadesBk> getMsActividadesXFiltro(Long idactividades, Long idtareas, String actividad, int inicial,
			int MAX, Long kyUsuarioMod);

	Long getMsActividadesTotalXFiltro(Long idactividades, Long idtareas, String actividad, Long kyUsuarioMod);

	List<MsActividadesBk> getMsActividadesXFiltro(Long idactividades, Long idtareas, String actividad,
			Long kyUsuarioMod);

	List<IDValorDto> getMsUsuariosIdusuario();

	List<MsCategoriasBk> getMsCategoriasIdcategorias();

	TdFlujoBk saveorupdateTdFlujoBk(TdFlujoBk tdFlujoBk, Long kyUsuarioMod, Long kyAreaMod, String kySedeMod,
			String rmtaddress) throws Validador;

	List<MsCategoriasBk> getMsCategoriasIdcategorias(String categoria);

	List<IDValorDto> getPrtParametrosContratoTipoTarea();// vbaldeon 08092023

	// MPINARES 01092023 - INICIO
	List<IDValorDto> getPrtParametrosTipoEvento();

	List<IDsValorDto> getPrtParametrosTipoEventoo();

	List<IDsValorDto> getPrtParametrosTipoVuelo();

	List<TdCategEventoBk> getTdCategEventoXFiltro(Long idcategevento, Long idevent, Long idcategorias);

	List<TdParticipantesBk> getTdParticipantesXFiltroXIdEvento(Long idevent);
	// MPINARES 01092023 - FIN

	// vbaldeon 25092023 inicio
	void notificarFlujo(TdFlujoBk tdFlujoBk, Long kyUsuarioMod, MsUsuariosBk usuariologueado, String rmtaddress)
			throws Validador;
	// vbaldeon 25092023 inicio

	List<TdFlujoBk> getTdFlujoXFiltro(Long idflujo, Long idflujopadre, Long idsacc, Integer estado,
			Long idEvento, Long kyUsuarioMod);//vbaldeon 08092023

	List<TdGruposflujosBk> getTdGruposflujosXFiltro(Long idtareas, Long idunidadDestino, Long iduserDestino,
			Long kyUsuarioMod, Long idGrupo);
}