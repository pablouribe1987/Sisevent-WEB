package pe.gob.mef.sisevent.web.controller.rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.mef.sisevent.bs.ctlracceso.Roles;
import pe.gob.mef.sisevent.bs.service.Servicio;
import pe.gob.mef.sisevent.bs.transfer.Progreso;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.utils.PropertiesMg;
import pe.gob.mef.sisevent.web.controller.rs.data.MantenimientoInicial;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;
import pe.gob.mef.sisevent.web.controller.rs.data.SimpleResultadoJS;

@Path("/ctrlmantenimiento")
public class MantenimientoRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	private static final int PROCES_INTERV = 1000;
	private static final int PROCES_TIMEOUT = 120000;
	
	public MantenimientoRsCtrl() {		
	}
	
	@POST
	@Path("/ejecutarIni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inicializeData(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
//		String adressRemoto = getRemoteAdress(req);
		try {
			
			new Thread() {
			      @Override
			      public void run() {
			    	  servicio.inicialiceData();
			      }
			    }.start();
			
			Progreso progreso = servicio.getProgreso();			
			GenericEntity<Progreso> registrors = new GenericEntity<Progreso>(progreso) {
			};
			return Response.status(200).entity(registrors).build();
		} catch (Exception e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}

	@POST
	@Path("/ejecutarIniCache")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inicializeCaches(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
//		String adressRemoto = getRemoteAdress(req);
		try {
			new Thread() {
			      @Override
			      public void run() {
			        servicio.inicializeCaches();
			      }
			    }.start();			
			Progreso progreso = servicio.getProgreso();
			GenericEntity<Progreso> registrors = new GenericEntity<Progreso>(progreso) {
			};
			return Response.status(200).entity(registrors).build();
		} catch (Exception e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}
	
	@GET
	@Path("/progress")
	@Produces(MediaType.APPLICATION_JSON)
	public Response progres(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
//		String adressRemoto = getRemoteAdress(req);
		try {
			Progreso progreso = servicio.getProgreso();
			GenericEntity<Progreso> registrors = new GenericEntity<Progreso>(progreso) {
			};
			return Response.status(200).entity(registrors).build();
		} catch (Exception e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}
	
	@GET
	@Path("/serverinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response serverinfo(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		
		MantenimientoInicial  mantenimientoInicial = new MantenimientoInicial();
		mantenimientoInicial.setTotalDeUduariosConectados(getTotalDeUduariosConectados());		
		mantenimientoInicial.setMaxMemory(Runtime.getRuntime().maxMemory());
		mantenimientoInicial.setTotalMemory(Runtime.getRuntime().totalMemory());
		mantenimientoInicial.setFreeMemory(Runtime.getRuntime().freeMemory());
		mantenimientoInicial.setResultadoLiberarmemoria(null);
		
		GenericEntity<MantenimientoInicial> registrosx = new GenericEntity<MantenimientoInicial>(mantenimientoInicial) {
		};
		return Response.status(200).entity(registrosx).build();
	}
	
	@GET
	@Path("/liberarmemoria")
	@Produces(MediaType.APPLICATION_JSON)
	public Response liberarmemoria(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
        String resultadoLiberarmemoria = liberarMemoria();
		
		MantenimientoInicial  mantenimientoInicial = new MantenimientoInicial();
		mantenimientoInicial.setTotalDeUduariosConectados(getTotalDeUduariosConectados());		
		mantenimientoInicial.setMaxMemory(Runtime.getRuntime().maxMemory());
		mantenimientoInicial.setTotalMemory(Runtime.getRuntime().totalMemory());
		mantenimientoInicial.setFreeMemory(Runtime.getRuntime().freeMemory());
		mantenimientoInicial.setResultadoLiberarmemoria(resultadoLiberarmemoria);
		
		GenericEntity<MantenimientoInicial> registrosx = new GenericEntity<MantenimientoInicial>(mantenimientoInicial) {
		};
		return Response.status(200).entity(registrosx).build();
	}
	
	@GET
	@Path("/cargarparametro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cargarparametro(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @QueryParam("parametrosistema") String parametrosistema){
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(parametrosistema==null || parametrosistema.trim().length()<=0){
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError("DEBE INGRESAR EL PARAMETRO QUE DESEA CONSULTAR", HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
			}
		
		String valorsistema = PropertiesMg.getSistemaProperties().getProperty(parametrosistema) == null ? "" : PropertiesMg.getSistemaProperties().getProperty(
				parametrosistema);
		
		SimpleResultadoJS simpleResultadoJS = new SimpleResultadoJS(valorsistema);		
		GenericEntity<SimpleResultadoJS> registrosx = new GenericEntity<SimpleResultadoJS>(simpleResultadoJS) {
		};
		return Response.status(200).entity(registrosx).build();
	}
	
	@GET
	@Path("/salvarparametro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cargarparametro(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, 
			@QueryParam("parametrosistema") String parametrosistema, 
			@QueryParam("valorsistema") String valorsistema){
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(parametrosistema==null || parametrosistema.trim().length()<=0){
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
				.entity(new GenericEntity<RespuestaError>(new RespuestaError("DEBE INGRESAR EL PARAMETRO QUE DESEA ACTUALIZAR", HttpURLConnection.HTTP_BAD_REQUEST)) {
				}).build();
		}
		
		Properties sistemaProperties = PropertiesMg.getSistemaProperties();
		sistemaProperties.setProperty(parametrosistema, valorsistema);
		PropertiesMg.saveSistemaProperties(sistemaProperties);
		
		SimpleResultadoJS simpleResultadoJS = new SimpleResultadoJS(valorsistema);		
		GenericEntity<SimpleResultadoJS> registrosx = new GenericEntity<SimpleResultadoJS>(simpleResultadoJS) {
		};
		return Response.status(200).entity(registrosx).build();
	}
	
	@POST
	@Path("/ejecutarquery")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ejecutarquery(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, 
			@QueryParam("paramedatasource") String paramedatasource, 
			@QueryParam("qry") String qry){
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(paramedatasource==null || paramedatasource.trim().length()<=0){
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
				.entity(new GenericEntity<RespuestaError>(new RespuestaError("DEBE INGRESAR EL DATASOURCE", HttpURLConnection.HTTP_BAD_REQUEST)) {
				}).build();
		}
		
		if(qry==null || qry.trim().length()<=0){
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError("DEBE INGRESAR EL QUERY", HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
			}
		
		try{
			StringBuffer sb = new StringBuffer();			
			DataSource dataSource = getDataSource(paramedatasource);

			Connection conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();

			long inicio = System.currentTimeMillis();
			ResultSet rset = stmt.executeQuery(qry); // Exec query
			long lfinal = System.currentTimeMillis() - inicio;
			
			sb.append("EJECUCIÓN DEL QUERY: " + qry + " EN " + (lfinal) + " MILISEGUNDOS.");

			ResultSetMetaData meta = rset.getMetaData(); // Get meta data

			int NCols = meta.getColumnCount();

			/*
			 * =================================================== Print the column names before any data ****
			 * ===================================================
			 */
				sb.append("\n");
				double length[] = new double[NCols];
				for (int i = 1; i <= NCols; i++) {
					String name;

					name = meta.getColumnLabel(i);
					// System.out.print( name ); // Print field name
					sb.append(name);

					length[i - 1] = Math.max(name.length(), meta.getColumnDisplaySize(i)) + 2; // 2 spaces between
																								// columns
					// Set the length of the field to print
					// It's 2 more than the name/display size

					/*
					 * ---------------------------------------------- Pad**** the attr name i to length[i]
					 * ----------------------------------------------
					 */
					for (int j = name.length(); j < length[i - 1]; j++)
						// System.out.print(" ");
						sb.append(" ");
				}
				// System.out.println();
				sb.append(" ");

				/*
				 * --------------------------------- Print a dividing line.... ---------------------------------
				 */
				for (int i = 1; i <= NCols; i++)
					for (int j = 0; j < length[(i - 1)]; j++)
						sb.append("=");
				// System.out.print("="); // Print a dividing line
				// System.out.println();

				/*
				 * =========================================== Fetch and print one row at a time....
				 * ===========================================
				 */
				while (rset.next()) // Advance to next row
				{
					/*
					 * =========================================== Fetch the columns (attributes) from a row
					 * ===========================================
					 */
					for (int i = 1; i <= NCols; i++) {
						String nextItem;

						nextItem = rset.getString(i);
						// System.out.print(nextItem);
						sb.append(nextItem);

						/*
						 * ---------------------------------------------- Pad** the attr value i to length[i]
						 * ----------------------------------------------
						 */
						for (int j = nextItem.length(); j < length[(i - 1)]; j++)
							// System.out.print(" ");
							sb.append(" ");
					}
					// System.out.println();

				}
			
			rset.close(); // De-allocate result set resources
			stmt.close();
			conn.close();
			SimpleResultadoJS simpleResultadoJS = new SimpleResultadoJS(sb.toString());		
			GenericEntity<SimpleResultadoJS> registrosx = new GenericEntity<SimpleResultadoJS>(simpleResultadoJS) {
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
	@Path("/ejecutarapp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ejecutarapp(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, 
			@QueryParam("parameter") String parameter){
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(parameter==null || parameter.trim().length()<=0){
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
				.entity(new GenericEntity<RespuestaError>(new RespuestaError("DEBE INGRESAR EL QUERY", HttpURLConnection.HTTP_BAD_REQUEST)) {
				}).build();
		}
		
		try{
			
			String respuesta = executeCommand(parameter, true);
			
			SimpleResultadoJS simpleResultadoJS = new SimpleResultadoJS(respuesta);		
			GenericEntity<SimpleResultadoJS> registrosx = new GenericEntity<SimpleResultadoJS>(simpleResultadoJS) {
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
	
	public String getRemoteAdress(HttpServletRequest request) {
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
	public Integer getTotalDeUduariosConectados() {
		ServletContext contexto = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
		Integer usuarioConectados= null; 
		synchronized (contexto) {
			try{
			usuarioConectados= (Integer) contexto.getAttribute("usuariosConectados");
			}catch(Exception e){
				e.printStackTrace();
			}
		} 		
		return usuarioConectados;
	}
	
	public String liberarMemoria() {
		String cmds = "/bin/sync";
		Process p;
		String s1 = "";
		String s2 = "";
		try {
			p = exec(cmds, PROCES_INTERV, PROCES_TIMEOUT);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// read the output from the command
			while ((s1 += stdInput.readLine()) != null) {
				if (s1.contains("null")) {
					s1 = s1.substring(0, (s1.length() - 4));
					break;
				}
			}
			s1 += '\n';
			// read any errors from the attempted command
			// System.out.println("Here is the standard error of the command (if any):\n");
			while ((s2 += stdError.readLine()) != null) {
				if (s2.contains("null")) {
					s2 = s2.substring(0, (s2.length() - 4));
					break;
				}
			}
			s2 += '\n';
		} catch (IOException e) {
			e.printStackTrace();
			s2 += e.getMessage();
		} catch (InterruptedException e) {

			e.printStackTrace();
			s2 += e.getMessage();
		}
		cmds = "/bin/echo 3 > /proc/sys/vm/drop_caches";
		try {
			p = exec(cmds, PROCES_INTERV, PROCES_TIMEOUT);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// read the output from the command
			while ((s1 += stdInput.readLine()) != null) {
				if (s1.contains("null")) {
					s1 = s1.substring(0, (s1.length() - 4));
					break;
				}
			}
			while ((s2 += stdError.readLine()) != null) {
				if (s2.contains("null")) {
					s2 = s2.substring(0, (s2.length() - 4));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			s2 += e.getMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
			s2 += e.getMessage();
		}
		return (s1 + "\n" + s2);
	}
	
	public static Process exec(String command, long interval, long timeout) throws IOException, InterruptedException {
		return exec(command, null, interval, timeout);
	}

	public static Process exec(String[] commands, long interval, long timeout) throws IOException, InterruptedException {
		return exec(commands, null, interval, timeout);
	}

	public static synchronized Process exec(String command, String[] envp, long interval, long timeout) throws IOException, InterruptedException {
		Process process = null;
		if (null == envp) {
			process = Runtime.getRuntime().exec(command);
		} else {
			process = Runtime.getRuntime().exec(command, envp);
		}

		return processTimeout(process, interval, timeout);
	}

	public static synchronized Process exec(String[] commands, String[] envp, long interval, long timeout) throws IOException, InterruptedException {
		Process process = null;
		if (null == envp) {
			process = Runtime.getRuntime().exec(commands);
		} else {
			process = Runtime.getRuntime().exec(commands, envp);
		}

		return processTimeout(process, interval, timeout);
	}

	private static synchronized Process processTimeout(Process process, long interval, long timeout) throws InterruptedException {
		long time_waiting = 0;
		boolean process_finished = false;

		while (time_waiting < timeout && !process_finished) {
			process_finished = true;
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.fillInStackTrace();
				throw e;
			}

			try {
				process.exitValue();
			} catch (IllegalThreadStateException e) {
				// process hasn't finished yet
				process_finished = false;
			}
			time_waiting += interval;
		}

		if (process_finished) {
			return process;
		} else {
			process.destroy();
			return null;
		}
	}
	
	private DataSource getDataSource(String dataSourceName) {
		DataSource dataSource = null;
		Properties p = new Properties();
		p.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		p.put(javax.naming.Context.PROVIDER_URL, "jnp://localhost:1099");
		p.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		try {
			String datasourcenamefinal = dataSourceName;
			if (!dataSourceName.startsWith("java:/")) {
				datasourcenamefinal = "java:/" + dataSourceName;
			}
			javax.naming.Context initContext = new InitialContext(p);
			dataSource = (DataSource) initContext.lookup(datasourcenamefinal);
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		return dataSource;
	}
	
	public String executeCommand(String command, boolean waitForResponse) {

		String response = "";

		ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
		pb.redirectErrorStream(true);

		System.out.println("Linux command: " + command);

		try {
			Process shell = pb.start();

			if (waitForResponse) {

				// To capture output from the shell
				InputStream shellIn = shell.getInputStream();

				// Wait for the shell to finish and get the return code
				int shellExitStatus = shell.waitFor();
				System.out.println("Exit status" + shellExitStatus);

				response = convertStreamToStr(shellIn);

				shellIn.close();
			}

		}
		catch (IOException e) {
			System.out.println("Error occured while executing Linux command. Error Description: " + e.getMessage());
		}
		catch (InterruptedException e) {
			System.out.println("Error occured while executing Linux command. Error Description: " + e.getMessage());
		}

		return response;
	}
	
	public String convertStreamToStr(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}
}
