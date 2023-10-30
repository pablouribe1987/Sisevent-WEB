package pe.gob.mef.sisevent.web.controller.rs;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.IDValorDto;
import pe.gob.mef.sisevent.bs.transfer.MsInstitucionesDto;
import pe.gob.mef.sisevent.bs.transfer.MsPersonasDto;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.utils.PropertiesMg;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;

@Path("/srvcs")
public class ServiciosRsCtrl {

	private static final Logger log = Logger.getLogger(ServiciosRsCtrl.class.getName());

	@Autowired
	private Servicio servicio = null;

	public ServiciosRsCtrl() {
	}

	@GET
	@Path("/buscarruc/{user}/{ruc}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaInstitucionesXRuc(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user,@PathParam("ruc") String ruc) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /buscarruc/ = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /buscarruc/ = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		////////
		
		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		
		try {
			List<MsInstitucionesDto> msInstitucionesDtosss = servicio.getMsInstitucionesXRuc(ruc);
			GenericEntity<List<MsInstitucionesDto>> registrosx = new GenericEntity<List<MsInstitucionesDto>>(
					msInstitucionesDtosss) {
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

	@GET
	@Path("/buscarrzsocial/{user}/{qrytext}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaInstitucionesXRazonSocial(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user, @PathParam("qrytext") String qryText) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /buscarrzsocial/ = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /buscarrzsocial/ = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<MsInstitucionesDto> msInstitucionesDtosss = servicio.getMsInstitucionesXRazonSocial(qryText);
			GenericEntity<List<MsInstitucionesDto>> registrosx = new GenericEntity<List<MsInstitucionesDto>>(
					msInstitucionesDtosss) {
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

	@GET
	@Path("/buscarpersonadni/{user}/{dni}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPersonasXDNI(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user, @PathParam("dni") String dni) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /buscarpersonadni/ = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /buscarpersonadni/ = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<MsPersonasDto> msPersonasDtosss = servicio.getMsPersonasXDNI(dni);
			GenericEntity<List<MsPersonasDto>> registrosx = new GenericEntity<List<MsPersonasDto>>(msPersonasDtosss) {
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

	@GET
	@Path("/buscarpersonanombre/{user}/{nombre}/{apellidoPaterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPersonasXNombre(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user, @PathParam("nombre") String nombre,
			@PathParam("apellidoPaterno") String apellidoPaterno) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /buscarpersonadni/ = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /buscarpersonadni/ = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<MsPersonasDto> msPersonasDtosss = servicio.getMsPersonasXNombre(nombre, apellidoPaterno,null);
			GenericEntity<List<MsPersonasDto>> registrosx = new GenericEntity<List<MsPersonasDto>>(msPersonasDtosss) {
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

	@GET
	@Path("/listaCargos/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaCargos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /listaCargos = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /listaCargos = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<IDValorDto> iDValorDtosss = servicio.getPrtParametrosCargos();
			GenericEntity<List<IDValorDto>> registrosx = new GenericEntity<List<IDValorDto>>(iDValorDtosss) {
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

	@GET
	@Path("/listaCanales/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaCanales(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /listaCanales = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /listaCanales = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<IDValorDto> iDValorDtosss = servicio.getPrtParametrosCanales();
			GenericEntity<List<IDValorDto>> registrosx = new GenericEntity<List<IDValorDto>>(iDValorDtosss) {
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

	@GET
	@Path("/listaTemas/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTemas(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("user") String user) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		////////
		String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
		String clienteip = req.getRemoteAddr();

		boolean autorizado = true;
		log.info("Consulta /listaTemas = " + clienteip);
		try {
			if (ipautorizado != null && ipautorizado.trim().length() > 0) {
				autorizado = false;
				if (ipautorizado.indexOf(";") > -1) {
					StringTokenizer st = new StringTokenizer(ipautorizado, ";");
					while (st.hasMoreTokens()) {
						String ipvalido = st.nextToken();
						if (clienteip.equals(ipvalido)) {
							autorizado = true;
							break;
						}
					}
				} else {
					if (clienteip.equals(ipautorizado)) {
						autorizado = true;
					}
				}
			} else {
				if (ipautorizado == null) {
					log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
					log.info("Consulta /listaTemas = " + clienteip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autorizado) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		}
		///////

		if (user == null || user.trim().length()<=0)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		

		try {
			List<IDValorDto> iDValorDtosss = servicio.getPrtParametrosTemas();
			GenericEntity<List<IDValorDto>> registrosx = new GenericEntity<List<IDValorDto>>(iDValorDtosss) {
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
	
//	@POST
//	@Path("/salvaratencion/{user}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response salvarTdAtenciones(@Context HttpServletRequest req, @Context HttpServletResponse res,
//			@HeaderParam("authorization") String authString, @PathParam("user") String user, TdAtencionesJS tdAtencionesJS) {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//		
//	////////
//			String ipautorizado = PropertiesMg.getSistemaProperties().getProperty("AUTORIZA_SERVICIOS_IPs");
//			String clienteip = req.getRemoteAddr();
//
//			boolean autorizado = true;
//			log.info("Consulta /listaTemas = " + clienteip);
//			try {
//				if (ipautorizado != null && ipautorizado.trim().length() > 0) {
//					autorizado = false;
//					if (ipautorizado.indexOf(";") > -1) {
//						StringTokenizer st = new StringTokenizer(ipautorizado, ";");
//						while (st.hasMoreTokens()) {
//							String ipvalido = st.nextToken();
//							if (clienteip.equals(ipvalido)) {
//								autorizado = true;
//								break;
//							}
//						}
//					} else {
//						if (clienteip.equals(ipautorizado)) {
//							autorizado = true;
//						}
//					}
//				} else {
//					if (ipautorizado == null) {
//						log.info("NO SE HA DEFINIDO AUTORIZA_SERVICIOS_IPs en Sistema Properties");
//						log.info("Consulta /listaTemas = " + clienteip);
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (!autorizado) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity(new GenericEntity<RespuestaError>(
//								new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
//										HttpURLConnection.HTTP_UNAUTHORIZED)) {
//						}).build();
//			}
//			///////
//
//			if (user == null || user.trim().length()<=0)
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity(new GenericEntity<RespuestaError>(
//								new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
//										HttpURLConnection.HTTP_UNAUTHORIZED)) {
//						}).build();
//			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(user);
//
//			if (msUsuariosBk == null)
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity(new GenericEntity<RespuestaError>(
//								new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
//										HttpURLConnection.HTTP_UNAUTHORIZED)) {
//						}).build();
//
//		String adressRemoto = getRemoteAdress(req);
//
//		TdAtencionesBk tdAtencionesC = new TdAtencionesBk();
//		FuncionesStaticas.copyPropertiesObject(tdAtencionesC, tdAtencionesJS);
//
//		tdAtencionesC.setSede(msUsuariosBk.getSede());
//		tdAtencionesC.setTiempoestadia(30);
//		tdAtencionesC.setSistemaingreso("PORTAL WEB");
//
//		tdAtencionesC.setRmtaddress(adressRemoto);
//		tdAtencionesC.setRmtaddressrst(adressRemoto);
//		tdAtencionesC.setEstado(1);
//		
//		List<TdAnexosJS> tdAnexosJSsss = tdAtencionesJS.getTdAnexosJSss();
//		List<TdAnexosBk> tdAnexosBkss = null;
//		if(tdAnexosJSsss!=null && !tdAnexosJSsss.isEmpty()){
//			tdAnexosBkss = new ArrayList<TdAnexosBk>();
//			for (TdAnexosJS tdAnexosJS : tdAnexosJSsss) {
//				////
//				String sdata = null;
//				if(tdAnexosJS.getData()!=null){
//					if(tdAnexosJS.getData().contains(";base64,")){
//						sdata = tdAnexosJS.getData().substring(tdAnexosJS.getData().indexOf(";base64,")+8);
//					}else{
//						sdata = tdAnexosJS.getData();
//					}
//				}else{
//					sdata = tdAnexosJS.getData();
//				}
//				byte[] data = Base64.getDecoder().decode(sdata);
//				String filename = null;
//				String rutaFilename = null;
//				if (tdAnexosJS.getFilename() == null) {
//					if (tdAnexosJS.getIdanexo()!=null && tdAnexosJS.getIdanexo().longValue()>0 
//							&&tdAnexosJS.getIdsacc() != null && tdAnexosJS.getIdsacc().longValue() > 0
//							&& tdAnexosJS.getIdflujo() != null && tdAnexosJS.getIdflujo().longValue() > 0) {
//						filename = FuncionesStaticas.getFileNameSistema(tdAnexosJS.getIdsacc(), tdAnexosJS.getIdflujo(),
//								tdAnexosJS.getIdanexo(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad());
//					} else {
//						filename = FuncionesStaticas.getFileNameTempSistema(msUsuariosBk.getIdusuario(),
//								msUsuariosBk.getIdunidad());
//					}
//					rutaFilename = FuncionesStaticas.getFileNameRutaSistema(filename);
//				} else {
//					filename = tdAnexosJS.getFilename();
//					rutaFilename = FuncionesStaticas.getFileNameRutaSistema(filename);
//				}
//				try {
//					FileOutputStream fos = new FileOutputStream(rutaFilename, true);
//					fos.write(data);
//					fos.close();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				tdAnexosJS.setData(null);
//				tdAnexosJS.setFilename(filename);
//				///				
//				TdAnexosBk tdAnexosBk = new TdAnexosBk();
//				FuncionesStaticas.copyPropertiesObject(tdAnexosBk, tdAnexosJS);
//				tdAnexosBkss.add(tdAnexosBk);
//			}
//		}
//		
//		try {
//			tdAtencionesC = servicio.saveorupdateTdAtencionesBk(tdAtencionesC, 
//					msUsuariosBk.getIdusuario(), msUsuariosBk, adressRemoto, tdAnexosBkss);
//			
//			TdAtencionesData tdAtencionesData = (TdAtencionesData) req.getSession().getAttribute("TdAtencionesData");
//			if (tdAtencionesData == null) {
//				tdAtencionesData = new TdAtencionesData();
//				req.getSession().setAttribute("TdAtencionesData", tdAtencionesData);
//			}
//			tdAtencionesData.addTdAtencionesActivos(tdAtencionesC);
//			
//			GenericEntity<TdAtencionesBk> registrors = new GenericEntity<TdAtencionesBk>(tdAtencionesC) {
//			};
//			return Response.status(200).entity(registrors).build();
//		} catch (Validador e) {
//			// e.printStackTrace();
//			String mensaje = e.getMessage();
//			System.out.println("ERROR: " + mensaje);
//			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
//					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
//					}).build();
//		}
//	}
	
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
		String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
				"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
				"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };
		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
}
