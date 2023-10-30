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
import pe.gob.mef.sisevent.bs.transfer.bk.TdFeriadosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.TdFeriadosData;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;
import pe.gob.mef.sisevent.web.controller.rs.data.TdFeriadosJS;
import pe.gob.mef.sisevent.web.controller.rs.data.TdFeriadosLC;

@Path("/ctrltdFeriados")
public class TdFeriadosRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public TdFeriadosRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listatdFeriados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTdFeriados(
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDFERIADOS_CREA) && !req.isUserInRole(Roles.TDFERIADOS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String fechaFeriado = req.getParameter("fechaFeriado");
			String descricion = req.getParameter("descricion");
            String sestado = req.getParameter("estado");
			
			Integer estado = null;
			if(sestado!=null){
				try{
					estado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}
			
			TdFeriadosFiltro tdFeriadosFiltro = new TdFeriadosFiltro(fechaFeriado,descricion,estado);		
			
			TdFeriadosData tdFeriadosData = (TdFeriadosData) req.getSession().getAttribute("TdFeriadosData");
			if(tdFeriadosData==null){
				tdFeriadosData = new TdFeriadosData();
				req.getSession().setAttribute("TdFeriadosData",tdFeriadosData);
			}
			
			TdFeriadosLC tdFeriadosLC = new TdFeriadosLC();
			long inicio = System.currentTimeMillis();
			List<TdFeriadosBk> tdFeriadossss = tdFeriadosData.getTdFeriadosActivos(servicio,msUsuariosBk.getIdusuario());
			long lfinal =System.currentTimeMillis()-inicio;
			tdFeriadosLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.TDFERIADOS_CREA)){
				tdFeriadosLC.setCreamodifica(true);
			}
							
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<TdFeriadosBk> tdFeriadossssData = new ArrayList<TdFeriadosBk> ();
			if(tdFeriadosFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(TdFeriadosBk tdFeriadosAct : tdFeriadossss){
	            boolean match = true;	            
	            Field camposdea[] = tdFeriadosFiltro.getClass().getDeclaredFields();
//	            if(tdFeriadosAct.getIdferiado.longValue()==56L){
//	            	System.out.println("AQUI....");
//	            }
				for (int i = 0; i < camposdea.length; i++) {
//					contador++;
//					System.out.println("Contador");
					String camponame = camposdea[i].getName();
					if(camponame.indexOf("serial")>-1 || camponame.indexOf("activo")>-1) continue;
					
					String filtroGetMetod = "get" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);
					String claseGetMetod = "get" + Character.toUpperCase(camponame.charAt(0)) + camponame.substring(1);					
					Class<?>[] types = new Class[] {};
					try {
						Method filtroMethod = tdFeriadosFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(tdFeriadosFiltro, new Object[0]);
						if(filtroValue==null) continue;
						else if(filtroValue.toString().length()<1) continue;
						Method claseMethod = tdFeriadosAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(tdFeriadosAct, new Object[0]);
						if(claseValue!=null){
							if(claseValue.getClass().getName().indexOf("Timestamp") > -1){
								String claseValueTxt = sdf.format(claseValue);
								String filterValueString = filtroValue.toString();
								if(filterValueString.trim().length()<1){
									continue;
								}
								if(filterValueString.contains("-")){
									filterValueString = filterValueString.replace("-","");
								}
    							if (claseValueTxt.startsWith(filterValueString)) {
    								match = true;
    							}else {
    								match = false;
    								break;
    							}
							}else{
								String claseValueTxt = String.valueOf(claseValue).toLowerCase();
								String filterValueString = filtroValue.toString().toLowerCase();
								if(filterValueString.startsWith("*")){
									filterValueString = filterValueString.substring(1);
									if(claseValueTxt.contains(filterValueString)){
										match = true;
									}else{
										match = false;
	    								break;
									}
								}else{
									if(claseValueTxt.startsWith(filterValueString)){
										match = true;
									}else{
										match = false;
	    								break;
									}
								}								
							}
						}else{
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
				if(match) {
					tdFeriadossssData.add(tdFeriadosAct);
	            }	            
	        }}else{
	        	tdFeriadossssData = tdFeriadossss;
	        }	 
	        //sort
	        if(sorder != null) {
	            Collections.sort(tdFeriadossssData, new Comparator<TdFeriadosBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(TdFeriadosBk tdFeriados1, TdFeriadosBk tdFeriados2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = TdFeriadosBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(tdFeriados1, new Object[0]);
						Object value2 = method.invoke(tdFeriados2, new Object[0]);
						if(value1==null && value2==null) return 0;
						else if(value1==null) return 1;
						else if(value2==null) return -1;
						int value = ((Comparable)value1).compareTo(value2);						
						return sortorden ? value : -1 * value;
	                	}catch(Exception e){
	                		return 0;
	                	}
	                }
	            });
	        }
	 
	        //rowCount
	        int dataSize = tdFeriadossssData.size();
	        int pageSize = 100;
	        try{
	        	if(slimit!=null && slimit.trim().length()>0){
	        		pageSize = Integer.parseInt(slimit);
	        	}
	        	if(pageSize<0)pageSize*=-1;
	        }catch(Exception e){}
	        int first = 1;
	        try{
	        	if(spage!=null && spage.trim().length()>0){
	        		first = Integer.parseInt(spage);
	        	}
	        	if(first<0)first*=-1;
	        }catch(Exception e){}
	        
	        //paginate
	        tdFeriadosLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                tdFeriadosLC.setData(tdFeriadossssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	tdFeriadosLC.setData(tdFeriadossssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	tdFeriadosLC.setData(tdFeriadossssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 tdFeriadosLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<TdFeriadosLC> registrosx = new GenericEntity<TdFeriadosLC>(tdFeriadosLC) {
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
	@Path("/salvartdFeriados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarTdFeriados(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdFeriadosJS tdFeriadosJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDFERIADOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		TdFeriadosBk tdFeriadosC = new TdFeriadosBk();
		FuncionesStaticas.copyPropertiesObject(tdFeriadosC, tdFeriadosJS);
		tdFeriadosC.setRmtaddress(adressRemoto);
		tdFeriadosC.setRmtaddressrs(adressRemoto);
		tdFeriadosC.setEstado(1);

		try {
			tdFeriadosC = servicio.saveorupdateTdFeriadosBk(tdFeriadosC, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), msUsuariosBk.getSede(), adressRemoto);
//			tdFeriadosJS = new TdFeriadosJS();
//			FuncionesStaticas.copyPropertiesObject(tdFeriadosJS, tdFeriadosC);
			TdFeriadosData tdFeriadosData = (TdFeriadosData) req.getSession().getAttribute("TdFeriadosData");
			if(tdFeriadosData==null){
				tdFeriadosData = new TdFeriadosData();
				req.getSession().setAttribute("TdFeriadosData",tdFeriadosData);
			}
			tdFeriadosData.add(servicio, msUsuariosBk.getIdusuario(), tdFeriadosC);
			
			GenericEntity<TdFeriadosBk> registrors = new GenericEntity<TdFeriadosBk>(tdFeriadosC) {
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

	@POST
	@Path("/eliminartdFeriados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarTdFeriados(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdFeriadosBk tdFeriadosE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDFERIADOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		tdFeriadosE.setEstado(0);
		try {
			servicio.deleteTdFeriados(tdFeriadosE, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), adressRemoto);
			TdFeriadosData tdFeriadosData = (TdFeriadosData) req.getSession().getAttribute("TdFeriadosData");
			if(tdFeriadosData==null){
				tdFeriadosData = new TdFeriadosData();
				req.getSession().setAttribute("TdFeriadosData",tdFeriadosData);
			}
			tdFeriadosData.refrescar(servicio, msUsuariosBk.getIdusuario());
			
			GenericEntity<TdFeriadosBk> registro = new GenericEntity<TdFeriadosBk>(tdFeriadosE) {
			};
			return Response.status(200).entity(registro).build();
		} catch (Validador e) {
			// e.printStackTrace();
			String mensaje = e.getMessage();
			System.out.println("ERROR: " + mensaje);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity(new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
					}).build();
		}
	}
	
	@GET
	@Path("/editartdFeriados/{idferiado}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarTdFeriados(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idferiado") Long idferiado) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDFERIADOS_CREA) && !req.isUserInRole(Roles.TDFERIADOS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			TdFeriadosBk tdFeriadosE = servicio.getTdFeriadosBkXid(idferiado,msUsuariosBk.getIdusuario());
			
			GenericEntity<TdFeriadosBk> registro = new GenericEntity<TdFeriadosBk>(tdFeriadosE) {
			};
			return Response.status(200).entity(registro).build();
		} catch (Exception e) {
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
	
}
