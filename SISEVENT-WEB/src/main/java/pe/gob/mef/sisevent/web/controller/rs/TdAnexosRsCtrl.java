package pe.gob.mef.sisevent.web.controller.rs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.mef.sisevent.bs.ctlracceso.Roles;
import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.TdAnexosData;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;
import pe.gob.mef.sisevent.web.controller.rs.data.TdAnexosJS;
import pe.gob.mef.sisevent.web.controller.rs.data.TdAnexosLC;

@Path("/ctrltdAnexos")
public class TdAnexosRsCtrl {

	@Autowired
	private Servicio servicio = null;

	public TdAnexosRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listatdAnexos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTdAnexos(@Context HttpServletRequest req, @Context HttpServletResponse res,
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDANEXOS_CREA) && !req.isUserInRole(Roles.TDANEXOS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			String sorder = req.getParameter("order");
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");

			String idsacc = req.getParameter("idsacc");
			String filename = req.getParameter("filename");
			String idflujo = req.getParameter("idflujo");

			TdAnexosFiltro tdAnexosFiltro = new TdAnexosFiltro(idsacc, filename, idflujo);

			TdAnexosData tdAnexosData = (TdAnexosData) req.getSession().getAttribute("TdAnexosData");
			if (tdAnexosData == null) {
				tdAnexosData = new TdAnexosData();
				req.getSession().setAttribute("TdAnexosData", tdAnexosData);
			}

			TdAnexosLC tdAnexosLC = new TdAnexosLC();
			long inicio = System.currentTimeMillis();
			List<TdAnexosBk> tdAnexossss = tdAnexosData.getTdAnexosActivos(servicio, msUsuariosBk.getIdusuario());
			long lfinal = System.currentTimeMillis() - inicio;
			tdAnexosLC.setTiempoenBD(lfinal);

			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<TdAnexosBk> tdAnexossssData = new ArrayList<TdAnexosBk>();
			if (tdAnexosFiltro.isActivo()) {
				// filter
				// int contador = 0;
				for (TdAnexosBk tdAnexosAct : tdAnexossss) {
					boolean match = true;
					Field camposdea[] = tdAnexosFiltro.getClass().getDeclaredFields();
					// if(tdAnexosAct.getIdanexo.longValue()==56L){
					// System.out.println("AQUI....");
					// }
					for (int i = 0; i < camposdea.length; i++) {
						// contador++;
						// System.out.println("Contador");
						String camponame = camposdea[i].getName();
						if (camponame.indexOf("serial") > -1 || camponame.indexOf("activo") > -1)
							continue;

						String filtroGetMetod = "get" + Character.toUpperCase(camponame.charAt(0))
								+ camponame.substring(1);
						String claseGetMetod = "get" + Character.toUpperCase(camponame.charAt(0))
								+ camponame.substring(1);
						Class<?>[] types = new Class[] {};
						try {
							Method filtroMethod = tdAnexosFiltro.getClass().getMethod(filtroGetMetod, types);
							Object filtroValue = filtroMethod.invoke(tdAnexosFiltro, new Object[0]);
							if (filtroValue == null)
								continue;
							Method claseMethod = tdAnexosAct.getClass().getMethod(claseGetMetod, types);
							Object claseValue = claseMethod.invoke(tdAnexosAct, new Object[0]);
							if (claseValue != null) {
								if (claseValue.getClass().getName().indexOf("Timestamp") > -1) {
									String claseValueTxt = sdf.format(claseValue);
									String filterValueString = filtroValue.toString();
									if (filterValueString.trim().length() < 1) {
										continue;
									}
									if (filterValueString.contains("-")) {
										filterValueString = filterValueString.replace("-", "");
									}
									if (claseValueTxt.startsWith(filterValueString)) {
										match = true;
									} else {
										match = false;
										break;
									}
								} else {
									String claseValueTxt = String.valueOf(claseValue).toLowerCase();
									String filterValueString = filtroValue.toString().toLowerCase();
									if (filterValueString.startsWith("*")) {
										filterValueString = filterValueString.substring(1);
										if (claseValueTxt.contains(filterValueString)) {
											match = true;
										} else {
											match = false;
											break;
										}
									} else {
										if (claseValueTxt.startsWith(filterValueString)) {
											match = true;
										} else {
											match = false;
											break;
										}
									}
								}
							} else {
								match = false;
								break;
							}
						} catch (NoSuchMethodException exception) {
							System.out.println("Error Exception: " + exception.getMessage());
							continue;
						} catch (Exception exception) {
							System.out.println("Error Exception: " + exception.getMessage());
							continue;
						}
					}
					if (match) {
						tdAnexossssData.add(tdAnexosAct);
					}
				}
			} else {
				tdAnexossssData = tdAnexossss;
			}
			// sort
			if (sorder != null) {
				Collections.sort(tdAnexossssData, new Comparator<TdAnexosBk>() {
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(TdAnexosBk tdAnexos1, TdAnexosBk tdAnexos2) {
						boolean sortorden = true;
						String order = sorder;
						if (order.startsWith("-")) {
							sortorden = false;
							order = order.substring(1);
						}
						try {
							String getMetod = "get" + Character.toUpperCase(order.charAt(0)) + order.substring(1);
							Class<?>[] types = new Class[] {};
							Method method = TdAnexosBk.class.getMethod(getMetod, types);
							Object value1 = method.invoke(tdAnexos1, new Object[0]);
							Object value2 = method.invoke(tdAnexos2, new Object[0]);
							int value = ((Comparable) value1).compareTo(value2);
							return sortorden ? value : -1 * value;
						} catch (Exception e) {
							return 0;
						}
					}
				});
			}

			// rowCount
			int dataSize = tdAnexossssData.size();
			int pageSize = 100;
			try {
				if (slimit != null && slimit.trim().length() > 0) {
					pageSize = Integer.parseInt(slimit);
				}
				if (pageSize < 0)
					pageSize *= -1;
			} catch (Exception e) {
			}
			int first = 1;
			try {
				if (spage != null && spage.trim().length() > 0) {
					first = Integer.parseInt(spage);
				}
				if (first < 0)
					first *= -1;
			} catch (Exception e) {
			}

			// paginate
			tdAnexosLC.setContador(dataSize);

			if (dataSize > pageSize) {
				int iniciodelista = ((first - 1) * pageSize);
				try {
					tdAnexosLC.setData(tdAnexossssData.subList(iniciodelista, iniciodelista + pageSize));
				} catch (IndexOutOfBoundsException e) {
					tdAnexosLC.setData(tdAnexossssData.subList(iniciodelista, iniciodelista + (dataSize % pageSize)));
				}
			} else {
				tdAnexosLC.setData(tdAnexossssData);
			}
			lfinal = System.currentTimeMillis() - inicio;
			System.out.println("EJECUCIÓN EN: " + (lfinal) + " MILISEGUNDOS.");
			tdAnexosLC.setTiempoenproceso(lfinal);
			/////

			GenericEntity<TdAnexosLC> registrosx = new GenericEntity<TdAnexosLC>(tdAnexosLC) {
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

	@POST
	@Path("/salvartdAnexos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarTdAnexos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdAnexosJS tdAnexosJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDANEXOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		String adressRemoto = getRemoteAdress(req);

		TdAnexosBk tdAnexosC = new TdAnexosBk();
		FuncionesStaticas.copyPropertiesObject(tdAnexosC, tdAnexosJS);
		tdAnexosC.setRmtaddress(adressRemoto);
		tdAnexosC.setRmtaddressrst(adressRemoto);
		tdAnexosC.setEstado(1);

		try {
			tdAnexosC = servicio.saveorupdateTdAnexosBk(tdAnexosC, msUsuariosBk.getUsername(),
					msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), msUsuariosBk.getSede(), adressRemoto);
			tdAnexosJS = new TdAnexosJS();
			FuncionesStaticas.copyPropertiesObject(tdAnexosJS, tdAnexosC);
			GenericEntity<TdAnexosJS> registrors = new GenericEntity<TdAnexosJS>(tdAnexosJS) {
			};
			return Response.status(200).entity(registrors).build();
		} catch (Validador e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}

	@POST
	@Path("/eliminartdAnexos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarTdAnexos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdAnexosBk tdAnexosE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDANEXOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		String adressRemoto = getRemoteAdress(req);

		try {
			servicio.deleteTdAnexos(tdAnexosE, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(),
					msUsuariosBk.getIdunidad(), adressRemoto);
			GenericEntity<TdAnexosBk> registro = new GenericEntity<TdAnexosBk>(tdAnexosE) {
			};
			return Response.status(200).entity(registro).build();
		} catch (Validador e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}

	@GET
	@Path("/editartdAnexos/{idanexo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarTdAnexos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idanexo") Long idanexo) {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDANEXOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			TdAnexosBk tdAnexosE = servicio.getTdAnexosBkXid(idanexo);

			GenericEntity<TdAnexosBk> registro = new GenericEntity<TdAnexosBk>(tdAnexosE) {
			};
			return Response.status(200).entity(registro).build();
		} catch (Exception e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
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

	/// ADICIONALES

}
