package pe.gob.mef.sisevent.web.controller.rs;

import java.net.HttpURLConnection;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.mef.sisevent.bs.ctlracceso.Roles;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.web.controller.rs.data.MenuaccesosJS;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;

@Path("/ctrlmenu")
public class MenuRsCtrl {

	@Autowired
	private Servicio servicio = null;

	public MenuRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/perfilusuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMsUsuarios(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {

			MenuaccesosJS menuaccesosJS = new MenuaccesosJS();

			List<String> roles = msUsuariosBk.getRolesSistema();

			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSINSTITUCIONES_CREA)) {
				menuaccesosJS.setEntidades(true);
			}

			if (roles.contains(Roles.ADMINISTRADOR) ||  roles.contains(Roles.MSPERSONAS_CREA)) {
				menuaccesosJS.setPersonas(true);
			}

			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.PRTPARAMETROS_CREA) || roles.contains(Roles.PRTPARAMETROS_VE)) {
				menuaccesosJS.setParametros(true);
			}
			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.ADMINISTRADOR_USUARIOS)) {
				menuaccesosJS.setUsuarios(true);
			}
			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUNIDADESORG_CREA) || roles.contains(Roles.MSUNIDADESORG_VE)) {
				menuaccesosJS.setUnidades(true);
			}
			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSPAISES_CREA) || roles.contains(Roles.MSPAISES_VE)) {
				menuaccesosJS.setPaises(true);
			}
			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.MSUBIGEO_CREA) || roles.contains(Roles.MSUBIGEO_VE)) {
				menuaccesosJS.setUbigeo(true);
			}
			if (roles.contains(Roles.ADMINISTRADOR) || roles.contains(Roles.TDFERIADOS_CREA) || roles.contains(Roles.TDFERIADOS_VE)) {
				menuaccesosJS.setFeriados(true);
			}

			GenericEntity<MenuaccesosJS> registrosx = new GenericEntity<MenuaccesosJS>(menuaccesosJS) {
			};
			return Response.status(200).entity(registrosx).build();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}

}
