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
import pe.gob.mef.sisevent.bs.transfer.MsUnidadesOrgDto;
import pe.gob.mef.sisevent.bs.transfer.NodosDto;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUnidadesOrgBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.MsUnidadesOrgData;
import pe.gob.mef.sisevent.web.controller.rs.data.MsUnidadesOrgJS;
import pe.gob.mef.sisevent.web.controller.rs.data.MsUnidadesOrgLC;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;

@Path("/ctrlmsUnidadesOrg")
public class MsUnidadesOrgRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public MsUnidadesOrgRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listamsUnidadesOrg")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMsUnidadesOrg(
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA) && !req.isUserInRole(Roles.MSUNIDADESORG_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idunidad = req.getParameter("idunidad");
			String idpadre = req.getParameter("idpadre");
			String codigo = req.getParameter("codigo");
			String descripcion = req.getParameter("descripcion");
			String acronimo = req.getParameter("acronimo");
            String sestado = req.getParameter("estado");
			
			Integer estado = null;
			if(sestado!=null){
				try{
					estado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}
			
			MsUnidadesOrgFiltro msUnidadesOrgFiltro = new MsUnidadesOrgFiltro(idunidad,idpadre,codigo,descripcion,acronimo,estado);		
			
			MsUnidadesOrgData msUnidadesOrgData = (MsUnidadesOrgData) req.getSession().getAttribute("MsUnidadesOrgData");
			if(msUnidadesOrgData==null){
				msUnidadesOrgData = new MsUnidadesOrgData();
				req.getSession().setAttribute("MsUnidadesOrgData",msUnidadesOrgData);
			}
			
			MsUnidadesOrgLC msUnidadesOrgLC = new MsUnidadesOrgLC();
			long inicio = System.currentTimeMillis();
			List<MsUnidadesOrgBk> msUnidadesOrgsss = msUnidadesOrgData.getMsUnidadesOrgActivos(servicio,msUsuariosBk.getIdusuario());
			long lfinal =System.currentTimeMillis()-inicio;
			msUnidadesOrgLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.MSUNIDADESORG_CREA)){
				msUnidadesOrgLC.setCreamodifica(true);
			}
				
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<MsUnidadesOrgBk> msUnidadesOrgsssData = new ArrayList<MsUnidadesOrgBk> ();
			if(msUnidadesOrgFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(MsUnidadesOrgBk msUnidadesOrgAct : msUnidadesOrgsss){
	            boolean match = true;	            
	            Field camposdea[] = msUnidadesOrgFiltro.getClass().getDeclaredFields();
//	            if(msUnidadesOrgAct.getIdunidad.longValue()==56L){
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
						Method filtroMethod = msUnidadesOrgFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(msUnidadesOrgFiltro, new Object[0]);
						if(filtroValue==null) continue;
						else if(filtroValue.toString().length()<1) continue;
						Method claseMethod = msUnidadesOrgAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(msUnidadesOrgAct, new Object[0]);
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
					msUnidadesOrgsssData.add(msUnidadesOrgAct);
	            }	            
	        }}else{
	        	msUnidadesOrgsssData = msUnidadesOrgsss;
	        }	 
	        //sort
	        if(sorder != null) {
	            Collections.sort(msUnidadesOrgsssData, new Comparator<MsUnidadesOrgBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(MsUnidadesOrgBk msUnidadesOrg1, MsUnidadesOrgBk msUnidadesOrg2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = MsUnidadesOrgBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(msUnidadesOrg1, new Object[0]);
						Object value2 = method.invoke(msUnidadesOrg2, new Object[0]);
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
	        int dataSize = msUnidadesOrgsssData.size();
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
	        msUnidadesOrgLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                msUnidadesOrgLC.setData(msUnidadesOrgsssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	msUnidadesOrgLC.setData(msUnidadesOrgsssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	msUnidadesOrgLC.setData(msUnidadesOrgsssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 msUnidadesOrgLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<MsUnidadesOrgLC> registrosx = new GenericEntity<MsUnidadesOrgLC>(msUnidadesOrgLC) {
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
	@Path("/salvarmsUnidadesOrg")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarMsUnidadesOrg(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, MsUnidadesOrgJS msUnidadesOrgJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		MsUnidadesOrgBk msUnidadesOrgC = new MsUnidadesOrgBk();
		FuncionesStaticas.copyPropertiesObject(msUnidadesOrgC, msUnidadesOrgJS);
		msUnidadesOrgC.setEstado(1);

		try {
			msUnidadesOrgC = servicio.saveorupdateMsUnidadesOrgBk(msUnidadesOrgC, msUsuariosBk.getUsername(),	msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), null, adressRemoto);
//			msUnidadesOrgJS = new MsUnidadesOrgJS();
//			FuncionesStaticas.copyPropertiesObject(msUnidadesOrgJS, msUnidadesOrgC);
			MsUnidadesOrgData msUnidadesOrgData = (MsUnidadesOrgData) req.getSession().getAttribute("MsUnidadesOrgData");
			if(msUnidadesOrgData==null){
				msUnidadesOrgData = new MsUnidadesOrgData();
				req.getSession().setAttribute("MsUnidadesOrgData",msUnidadesOrgData);
			}
			msUnidadesOrgData.add(servicio, msUsuariosBk.getIdusuario(), msUnidadesOrgC);
			
			GenericEntity<MsUnidadesOrgBk> registrors = new GenericEntity<MsUnidadesOrgBk>(msUnidadesOrgC) {
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
	@Path("/eliminarmsUnidadesOrg")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarMsUnidadesOrg(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, MsUnidadesOrgBk msUnidadesOrgE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		msUnidadesOrgE.setEstado(0);
		try {
			servicio.deleteMsUnidadesOrg(msUnidadesOrgE, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), null, adressRemoto);
			MsUnidadesOrgData msUnidadesOrgData = (MsUnidadesOrgData) req.getSession().getAttribute("MsUnidadesOrgData");
			if(msUnidadesOrgData==null){
				msUnidadesOrgData = new MsUnidadesOrgData();
				req.getSession().setAttribute("MsUnidadesOrgData",msUnidadesOrgData);
			}
			msUnidadesOrgData.refrescar(servicio, msUsuariosBk.getIdusuario());
			
			GenericEntity<MsUnidadesOrgBk> registro = new GenericEntity<MsUnidadesOrgBk>(msUnidadesOrgE) {
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
	@Path("/editarmsUnidadesOrg/{idunidad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarMsUnidadesOrg(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idunidad") Long idunidad) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA) && !req.isUserInRole(Roles.MSUNIDADESORG_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			MsUnidadesOrgBk msUnidadesOrgE = servicio.getMsUnidadesOrgBkXid(idunidad,msUsuariosBk.getIdusuario());
			
			GenericEntity<MsUnidadesOrgBk> registro = new GenericEntity<MsUnidadesOrgBk>(msUnidadesOrgE) {
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
	@GET
	@Path("/listauotree")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaUOTree(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {			
			List<NodosDto>  nodosDtoss = servicio.getUOTreeCache();			
			   GenericEntity<List<NodosDto>> registrosx = new GenericEntity<List<NodosDto>>(nodosDtoss){
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

	@GET
	@Path("/listaUoLista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaUOs(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.MSUNIDADESORG_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {			
			List<MsUnidadesOrgDto> unidades = servicio.getUOListaCache();			
			   GenericEntity<List<MsUnidadesOrgDto>> registrosx = new GenericEntity<List<MsUnidadesOrgDto>>(unidades){
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
}
