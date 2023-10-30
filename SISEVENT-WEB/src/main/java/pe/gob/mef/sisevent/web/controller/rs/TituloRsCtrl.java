package pe.gob.mef.sisevent.web.controller.rs;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.rs.data.MsUsuariosPerfilJS;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;

@Path("/ctrltitulo")
public class TituloRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public TituloRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/perfilusuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMsUsuarios(
			@Context HttpServletRequest req, 
			@Context HttpServletResponse res,
			@HeaderParam("authorization") String authString			
			) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

//		if (!req.isUserInRole(Roles.ADMINISTRADOR) 
//			&& !req.isUserInRole(Roles.ADMINISTRADOR_USUARIOS))
//			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
//					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
//			}).build();

		try {
			
			MsUsuariosPerfilJS msUsuariosPerfilJS = (MsUsuariosPerfilJS) req.getSession().getAttribute("MsUsuariosPerfilJS");
			if(msUsuariosPerfilJS==null){
				msUsuariosPerfilJS = new MsUsuariosPerfilJS();
				msUsuariosPerfilJS.setIdunidad(msUsuariosBk.getIdunidad());
				msUsuariosPerfilJS.setIdunidadTxt(msUsuariosBk.getIdunidadTxt());
				msUsuariosPerfilJS.setIdusuario(msUsuariosBk.getIdusuario());
				msUsuariosPerfilJS.setNombrecompleto(FuncionesStaticas.getNombreCompleto(msUsuariosBk));
				msUsuariosPerfilJS.setSede(msUsuariosBk.getSede());
				msUsuariosPerfilJS.setUsername(msUsuariosBk.getUsername());
				req.getSession().setAttribute("MsUsuariosPerfilJS",msUsuariosPerfilJS);
			}

			GenericEntity<MsUsuariosPerfilJS> registrosx = new GenericEntity<MsUsuariosPerfilJS>(msUsuariosPerfilJS) {
			};
			return Response.status(200).entity(registrosx).build();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}

	@POST
	@Path("/actualizarperfil")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarMsUsuarios(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, MsUsuariosPerfilJS msUsuariosPerfilJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
//		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.ADMINISTRADOR_USUARIOS))
//			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
//					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
//			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		MsUsuariosBk msUsuariosBkC = new MsUsuariosBk();
		FuncionesStaticas.copyPropertiesObject(msUsuariosBkC, msUsuariosBk);
		msUsuariosBkC.setContrasenia(msUsuariosPerfilJS.getContrasenia());
		msUsuariosBkC.setContraseniaConfir(msUsuariosPerfilJS.getContraseniaConfir());
		
		msUsuariosBkC.setRtmaddress(adressRemoto);
		msUsuariosBkC.setRtmaddressmodif(adressRemoto);
		
		try {
			msUsuariosBkC = servicio.saveorupdateMsUsuariosBk(msUsuariosBkC, msUsuariosBk.getUsername(),
					msUsuariosBk.getIdusuario(), null, null, adressRemoto,true);
			
			msUsuariosPerfilJS = new MsUsuariosPerfilJS();
			msUsuariosPerfilJS.setIdunidad(msUsuariosBkC.getIdunidad());
			msUsuariosPerfilJS.setIdunidadTxt(msUsuariosBkC.getIdunidadTxt());
			msUsuariosPerfilJS.setIdusuario(msUsuariosBkC.getIdusuario());
			msUsuariosPerfilJS.setNombrecompleto(FuncionesStaticas.getNombreCompleto(msUsuariosBkC));
			msUsuariosPerfilJS.setSede(msUsuariosBkC.getSede());
			msUsuariosPerfilJS.setUsername(msUsuariosBkC.getUsername());
			req.getSession().setAttribute("MsUsuariosPerfilJS",msUsuariosPerfilJS);
			
			GenericEntity<MsUsuariosPerfilJS> registrors = new GenericEntity<MsUsuariosPerfilJS>(msUsuariosPerfilJS) {
			};
			return Response.status(200).entity(registrors).build();
		} catch (Validador e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}
	
	private String getRemoteAdress(HttpServletRequest request) {
		String s = "";
		try {
			String hostname = "";
			try {
				InetAddress addr = InetAddress.getByName(request.getRemoteHost());
				hostname = addr.getHostName();
			} catch (UnknownHostException e) {
			}
			s = (hostname + "_" + request.getRemoteHost() + "_" + getClientIpAddress(request)); // request.getRemoteAddr());
			s = s.substring(0, 50);
		} catch (Exception e) {
		}
		return s;
	}

	private String getClientIpAddress(HttpServletRequest request) {
		String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR",
				"HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
				"HTTP_VIA", "REMOTE_ADDR" };
		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	///ADICIONALES
//	@GET
//	@Path("/listatdPendientes")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response listarPendientesTdAtenciones(@Context HttpServletRequest req, @Context HttpServletResponse res,
//			@HeaderParam("authorization") String authString) {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//
//		Principal usuario = req.getUserPrincipal();
//		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());
//
//		if (msUsuariosBk == null)
//			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//					.entity(new GenericEntity<RespuestaError>(
//							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
//									HttpURLConnection.HTTP_UNAUTHORIZED)) {
//					}).build();
//
//		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDATENCIONES_CREA)
//				&& !req.isUserInRole(Roles.TDATENCIONES_VE) && !req.isUserInRole(Roles.CORDINADOR_AREA)
//				&& !req.isUserInRole(Roles.CORDINADOR_TITULAR_AREA)
//				)
//			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//					.entity(new GenericEntity<RespuestaError>(
//							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
//									HttpURLConnection.HTTP_UNAUTHORIZED)) {
//					}).build();
//
//		try {
//			TdAtencionesLC tdAtencionesLC = new TdAtencionesLC();
//			long inicio = System.currentTimeMillis();
//			List<TdAtencionesBk> tdAtencionessss = servicio.getTdAtencionesXNuevosEnProceso(msUsuariosBk);			
//			long lfinal = System.currentTimeMillis() - inicio;
//			tdAtencionesLC.setTiempoenBD(lfinal);
//
//			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.TDATENCIONES_CREA)) {
//				tdAtencionesLC.setCreamodifica(true);
//			}
//			// rowCount
//			int dataSize = tdAtencionessss.size();
//			// paginate
//			tdAtencionesLC.setContador(dataSize);
//			tdAtencionesLC.setData(tdAtencionessss);
//			lfinal = System.currentTimeMillis() - inicio;
//			System.out.println("EJECUCIÓN PENDIENTES EN : " + (lfinal) + " MILISEGUNDOS.");
//			tdAtencionesLC.setTiempoenproceso(lfinal);
//			/////
//			GenericEntity<TdAtencionesLC> registrosx = new GenericEntity<TdAtencionesLC>(tdAtencionesLC) {
//			};
//			return Response.status(200).entity(registrosx).build();
//		} catch (Exception e) {
//			String mensaje = e.getMessage();
//			System.out.println("ERROR: " + mensaje);
//			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
//					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
//					}).build();
//		}
//	}
}
