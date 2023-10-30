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
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.TdGruposflujosData;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;
import pe.gob.mef.sisevent.web.controller.rs.data.TdGruposflujosJS;
import pe.gob.mef.sisevent.web.controller.rs.data.TdGruposflujosLC;

@Path("/ctrltdGruposflujos")
public class TdGruposflujosRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public TdGruposflujosRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listatdGruposflujos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTdGruposflujos(
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_CREA) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idtareas = req.getParameter("idtareas");
			String idunidadDestino = req.getParameter("idunidadDestino");
			String iduserDestino = req.getParameter("iduserDestino");
			
            String sestado = req.getParameter("estado");
			
			Integer iestado = null;
			if(sestado!=null){
				try{
					iestado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}		
			
			TdGruposflujosFiltro tdGruposflujosFiltro = new TdGruposflujosFiltro(idtareas,idunidadDestino,iduserDestino,iestado);		
			
			TdGruposflujosData tdGruposflujosData = (TdGruposflujosData) req.getSession().getAttribute("TdGruposflujosData");
			if(tdGruposflujosData==null){
				tdGruposflujosData = new TdGruposflujosData();
				req.getSession().setAttribute("TdGruposflujosData",tdGruposflujosData);
			}
			
			TdGruposflujosLC tdGruposflujosLC = new TdGruposflujosLC();
			long inicio = System.currentTimeMillis();
			List<TdGruposflujosBk> tdGruposflujossss = tdGruposflujosData.getTdGruposflujosActivos(servicio,msUsuariosBk.getIdusuario());
			long lfinal =System.currentTimeMillis()-inicio;
			tdGruposflujosLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.TDGRUPOSFLUJOS_CREA)){
				tdGruposflujosLC.setCreamodifica(true);
			}			
			
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<TdGruposflujosBk> tdGruposflujossssData = new ArrayList<TdGruposflujosBk> ();
			if(tdGruposflujosFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(TdGruposflujosBk tdGruposflujosAct : tdGruposflujossss){
	            boolean match = true;	            
	            Field camposdea[] = tdGruposflujosFiltro.getClass().getDeclaredFields();
//	            if(tdGruposflujosAct.getIdgruposflujos.longValue()==56L){
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
						Method filtroMethod = tdGruposflujosFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(tdGruposflujosFiltro, new Object[0]);
						if(filtroValue==null) continue;
						else if(filtroValue.toString().length()<1) continue;
						Method claseMethod = tdGruposflujosAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(tdGruposflujosAct, new Object[0]);
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
					tdGruposflujossssData.add(tdGruposflujosAct);
	            }	            
	        }}else{
	        	tdGruposflujossssData = tdGruposflujossss;
	        }	 
	        //sort
			//System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
	        if(sorder != null) {
	            Collections.sort(tdGruposflujossssData, new Comparator<TdGruposflujosBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(TdGruposflujosBk tdGruposflujos1, TdGruposflujosBk tdGruposflujos2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = TdGruposflujosBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(tdGruposflujos1, new Object[0]);
						Object value2 = method.invoke(tdGruposflujos2, new Object[0]);
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
	        int dataSize = tdGruposflujossssData.size();
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
	        tdGruposflujosLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                tdGruposflujosLC.setData(tdGruposflujossssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	tdGruposflujosLC.setData(tdGruposflujossssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	tdGruposflujosLC.setData(tdGruposflujossssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 tdGruposflujosLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<TdGruposflujosLC> registrosx = new GenericEntity<TdGruposflujosLC>(tdGruposflujosLC) {
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
	@Path("/salvartdGruposflujos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarTdGruposflujos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdGruposflujosJS tdGruposflujosJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		TdGruposflujosBk tdGruposflujosC = new TdGruposflujosBk();
		FuncionesStaticas.copyPropertiesObject(tdGruposflujosC, tdGruposflujosJS);
		tdGruposflujosC.setRmtaddress(adressRemoto);
		tdGruposflujosC.setRmtaddressrst(adressRemoto);
		tdGruposflujosC.setEstado(1);

		try {
			tdGruposflujosC = servicio.saveorupdateTdGruposflujosBk(tdGruposflujosC, msUsuariosBk.getUsername(),msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), adressRemoto);
//			tdGruposflujosJS = new TdGruposflujosJS();
//			FuncionesStaticas.copyPropertiesObject(tdGruposflujosJS, tdGruposflujosC);
//			tdGruposflujosJS.setEditopcion(tdGruposflujosC.gettdGruposflujosACL().getEditopcion());
			
			TdGruposflujosData tdGruposflujosData = (TdGruposflujosData) req.getSession().getAttribute("TdGruposflujosData");
			if(tdGruposflujosData==null){
				tdGruposflujosData = new TdGruposflujosData();
				req.getSession().setAttribute("TdGruposflujosData",tdGruposflujosData);
			}
			tdGruposflujosData.add(servicio, msUsuariosBk.getIdusuario(), tdGruposflujosC);
			
			GenericEntity<TdGruposflujosBk> registrors = new GenericEntity<TdGruposflujosBk>(tdGruposflujosC) {
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
	@Path("/eliminartdGruposflujos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarTdGruposflujos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdGruposflujosJS tdGruposflujosE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		TdGruposflujosBk tdGruposflujosC = new TdGruposflujosBk();
		FuncionesStaticas.copyPropertiesObject(tdGruposflujosC, tdGruposflujosE);

		try {
			servicio.deleteTdGruposflujos(tdGruposflujosC, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), adressRemoto);
			
			TdGruposflujosData tdGruposflujosData = (TdGruposflujosData) req.getSession().getAttribute("TdGruposflujosData");
			if(tdGruposflujosData==null){
				tdGruposflujosData = new TdGruposflujosData();
				req.getSession().setAttribute("TdGruposflujosData",tdGruposflujosData);
			}
			tdGruposflujosData.refrescar(servicio, msUsuariosBk.getIdusuario());
			
			GenericEntity<TdGruposflujosBk> registro = new GenericEntity<TdGruposflujosBk>(tdGruposflujosC) {
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
	@Path("/editartdGruposflujos/{idgruposflujos}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarTdGruposflujos(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idgruposflujos") Long idgruposflujos) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_CREA) && !req.isUserInRole(Roles.TDGRUPOSFLUJOS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			TdGruposflujosBk tdGruposflujosE = servicio.getTdGruposflujosBkXid(idgruposflujos,msUsuariosBk.getIdusuario());
			
			GenericEntity<TdGruposflujosBk> registro = new GenericEntity<TdGruposflujosBk>(tdGruposflujosE) {
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
