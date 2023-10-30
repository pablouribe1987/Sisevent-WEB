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
import pe.gob.mef.sisevent.bs.transfer.IDValorDto;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.PrtParametrosBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.PrtParametrosData;
import pe.gob.mef.sisevent.web.controller.rs.data.PrtParametrosJS;
import pe.gob.mef.sisevent.web.controller.rs.data.PrtParametrosLC;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;

@Path("/ctrlprtParametros")
public class PrtParametrosRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public PrtParametrosRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listaprtParametros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPrtParametros(
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA) && !req.isUserInRole(Roles.PRTPARAMETROS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idparametro = req.getParameter("idparametro");
			String idpadreTxt = req.getParameter("idpadreTxt");
			String descripcion = req.getParameter("descripcion");
			String descripcioncorta = req.getParameter("descripcioncorta");
			String orden = req.getParameter("orden");
			String sestado = req.getParameter("estado");
			
			Integer estado = null;
			if(sestado!=null){
				try{
					estado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}
						
			PrtParametrosFiltro prtParametrosFiltro = new PrtParametrosFiltro(idparametro,idpadreTxt,descripcion,descripcioncorta,orden,estado);		
			
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosData");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData",prtParametrosData);
			}
			
			PrtParametrosLC prtParametrosLC = new PrtParametrosLC();
			long inicio = System.currentTimeMillis();
			List<PrtParametrosBk> prtParametrossss = prtParametrosData.getPrtParametrosActivos(servicio,msUsuariosBk.getIdusuario(),null);
			long lfinal =System.currentTimeMillis()-inicio;
			prtParametrosLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.PRTPARAMETROS_CREA)){
				prtParametrosLC.setCreamodifica(true);
			}
				
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<PrtParametrosBk> prtParametrossssData = new ArrayList<PrtParametrosBk> ();
			if(prtParametrosFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(PrtParametrosBk prtParametrosAct : prtParametrossss){
	            boolean match = true;	            
	            Field camposdea[] = prtParametrosFiltro.getClass().getDeclaredFields();
//	            if(prtParametrosAct.getIdparametro.longValue()==56L){
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
						Method filtroMethod = prtParametrosFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(prtParametrosFiltro, new Object[0]);
						if(filtroValue==null) continue;
						else if(filtroValue.toString().length()<1) continue;
						Method claseMethod = prtParametrosAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(prtParametrosAct, new Object[0]);						
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
					prtParametrossssData.add(prtParametrosAct);
	            }	            
	        }}else{
	        	prtParametrossssData = prtParametrossss;
	        }	 
	        //sort
			//System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
	        if(sorder != null) {
	            Collections.sort(prtParametrossssData, new Comparator<PrtParametrosBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(PrtParametrosBk prtParametros1, PrtParametrosBk prtParametros2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = PrtParametrosBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(prtParametros1, new Object[0]);
						Object value2 = method.invoke(prtParametros2, new Object[0]);
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
	        int dataSize = prtParametrossssData.size();
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
	        prtParametrosLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	prtParametrosLC.setData(prtParametrossssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 prtParametrosLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<PrtParametrosLC> registrosx = new GenericEntity<PrtParametrosLC>(prtParametrosLC) {
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
	@Path("/salvarprtParametros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarPrtParametros(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, PrtParametrosJS prtParametrosJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		PrtParametrosBk prtParametrosC = new PrtParametrosBk();
		FuncionesStaticas.copyPropertiesObject(prtParametrosC, prtParametrosJS);
		prtParametrosC.setRtmaddress(adressRemoto);
		prtParametrosC.setRtmaddressmodif(adressRemoto);
		if(prtParametrosC.getEstado()==null)
		prtParametrosC.setEstado(1);

		try {
			prtParametrosC = servicio.saveorupdatePrtParametrosBk(prtParametrosC, msUsuariosBk.getUsername(),msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), msUsuariosBk.getSede(), adressRemoto, prtParametrosJS.getIdpadreTxt());
			
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosData");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData",prtParametrosData);
			}
			prtParametrosData.add(servicio, prtParametrosC,msUsuariosBk.getIdusuario(), null);
						
//			prtParametrosJS = new PrtParametrosJS();
//			FuncionesStaticas.copyPropertiesObject(prtParametrosJS, prtParametrosC);
			GenericEntity<PrtParametrosBk> registrors = new GenericEntity<PrtParametrosBk>(prtParametrosC) {
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
	@Path("/eliminarprtParametros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPrtParametros(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, PrtParametrosBk prtParametrosE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		prtParametrosE.setEstado(0);
		try {
			servicio.deletePrtParametros(prtParametrosE, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(),null, adressRemoto);
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosData");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData",prtParametrosData);
			}
			prtParametrosData.refrescar(servicio, msUsuariosBk.getIdusuario(),null);
			
			GenericEntity<PrtParametrosBk> registro = new GenericEntity<PrtParametrosBk>(prtParametrosE) {
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
	
	@POST
	@Path("/eliminarprtParametros/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPrtParametrosDePadre(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("keyt") String keyt, PrtParametrosBk prtParametrosE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		prtParametrosE.setEstado(0);
		try {
			servicio.deletePrtParametros(prtParametrosE, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(),null, adressRemoto);
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosData");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData",prtParametrosData);
			}
			prtParametrosData.refrescar(servicio, msUsuariosBk.getIdusuario(),keyt);
			
			GenericEntity<PrtParametrosBk> registro = new GenericEntity<PrtParametrosBk>(prtParametrosE) {
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
	@Path("/editarprtParametros/{idparametro}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarPrtParametros(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idparametro") Long idparametro) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			PrtParametrosBk prtParametrosE = servicio.getPrtParametrosBkXid(idparametro,msUsuariosBk.getIdusuario(),null);
			
			GenericEntity<PrtParametrosBk> registro = new GenericEntity<PrtParametrosBk>(prtParametrosE) {
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
	
	@GET
	@Path("/editarprtParametros/{idparametro}/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarPrtParametros(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idparametro") Long idparametro, 
			@PathParam("keyt") String keyt) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			PrtParametrosBk prtParametrosE = servicio.getPrtParametrosBkXid(idparametro,msUsuariosBk.getIdusuario(),keyt);
			
			GenericEntity<PrtParametrosBk> registro = new GenericEntity<PrtParametrosBk>(prtParametrosE) {
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
	
	@GET
	@Path("/parametrospadre/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prtParametrospadre(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("keyt") String keyt) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA) && !req.isUserInRole(Roles.PRTPARAMETROS_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(keyt==null || (!keyt.equals("temas") && !keyt.equals("canales") && !keyt.equals("sedes") && !keyt.equals("cargos"))){
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		}
		
		if(keyt.equals("canales")){
			keyt = "CANALES DE ATENCIÓN";
		}else{
			keyt = keyt.toUpperCase();
		}
		
		try {
			PrtParametrosBk prtParametrosE = servicio.getPrtParametrosBkXDesc(keyt,msUsuariosBk.getIdusuario());
			
			GenericEntity<PrtParametrosBk> registro = new GenericEntity<PrtParametrosBk>(prtParametrosE) {
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
	@Path("/listaParametrosXId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTemas(@Context HttpServletRequest req, @Context HttpServletResponse res,
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		try {
			List<IDValorDto> iDValorDtosss = servicio.getPrtParametrosPadre();
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
	
	//////////////
	@GET
	@Path("/listaprtParametrosTema/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPrtParametrosTema(
			@Context HttpServletRequest req, 
			@Context HttpServletResponse res,
			@HeaderParam("authorization") String authString,
		    @PathParam("keyt") String keyt
			) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(keyt==null || (!keyt.equals("temas") && !keyt.equals("canales") && !keyt.equals("sedes") && !keyt.equals("cargos"))){
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		}
		
		if(keyt.equals("canales")){
			keyt = "CANALES DE ATENCIÓN";
		}else{
			keyt = keyt.toUpperCase();
		}
		
		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idparametro = req.getParameter("idparametro");
			String idpadreTxt = req.getParameter("idpadreTxt");
			String descripcion = req.getParameter("descripcion");
			String descripcioncorta = req.getParameter("descripcioncorta");
			String orden = req.getParameter("orden");
            String sestado = req.getParameter("estado");
			
			Integer estado = null;
			if(sestado!=null){
				try{
					estado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}
			
			PrtParametrosFiltro prtParametrosFiltro = new PrtParametrosFiltro(idparametro,idpadreTxt,descripcion,descripcioncorta,orden,estado);		
			
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosDataTema");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData"+keyt,prtParametrosData);
			}
			
			PrtParametrosLC prtParametrosLC = new PrtParametrosLC();
			long inicio = System.currentTimeMillis();
			List<PrtParametrosBk> prtParametrossss = prtParametrosData.getPrtParametrosActivos(servicio,msUsuariosBk.getIdusuario(),keyt);
			long lfinal =System.currentTimeMillis()-inicio;
			prtParametrosLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.PRTPARAMETROS_CREA)){
				prtParametrosLC.setCreamodifica(true);
			}
				
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<PrtParametrosBk> prtParametrossssData = new ArrayList<PrtParametrosBk> ();
			if(prtParametrosFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(PrtParametrosBk prtParametrosAct : prtParametrossss){
	            boolean match = true;	            
	            Field camposdea[] = prtParametrosFiltro.getClass().getDeclaredFields();
//	            if(prtParametrosAct.getIdparametro.longValue()==56L){
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
						Method filtroMethod = prtParametrosFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(prtParametrosFiltro, new Object[0]);
						if(filtroValue==null) continue;
						Method claseMethod = prtParametrosAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(prtParametrosAct, new Object[0]);
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
					prtParametrossssData.add(prtParametrosAct);
	            }	            
	        }}else{
	        	prtParametrossssData = prtParametrossss;
	        }	 
	        //sort
	        if(sorder != null) {
	            Collections.sort(prtParametrossssData, new Comparator<PrtParametrosBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(PrtParametrosBk prtParametros1, PrtParametrosBk prtParametros2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = PrtParametrosBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(prtParametros1, new Object[0]);
						Object value2 = method.invoke(prtParametros2, new Object[0]);
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
	        int dataSize = prtParametrossssData.size();
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
	        prtParametrosLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	prtParametrosLC.setData(prtParametrossssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 prtParametrosLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<PrtParametrosLC> registrosx = new GenericEntity<PrtParametrosLC>(prtParametrosLC) {
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
	@Path("/listaprtParametrosSubTema/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPrtParametrosSubTema(
			@Context HttpServletRequest req, 
			@Context HttpServletResponse res,
			@HeaderParam("authorization") String authString,
		    @PathParam("keyt") String keyt
			) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.PRTPARAMETROS_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(keyt==null || (!keyt.equals("temas") && !keyt.equals("canales") && !keyt.equals("sedes") && !keyt.equals("cargos"))){
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		}
		
		if(keyt.equals("canales")){
			keyt = "CANALES DE ATENCIÓN";
		}else{
			keyt = keyt.toUpperCase();
		}
		
		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idparametro = req.getParameter("idparametro");
			String idpadreTxt = req.getParameter("idpadreTxt");
			String descripcion = req.getParameter("descripcion");
			String descripcioncorta = req.getParameter("descripcioncorta");
			String orden = req.getParameter("orden");
            String sestado = req.getParameter("estado");
			
			Integer estado = null;
			if(sestado!=null){
				try{
					estado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}
			
			PrtParametrosFiltro prtParametrosFiltro = new PrtParametrosFiltro(idparametro,idpadreTxt,descripcion,descripcioncorta,orden,estado);		
			
			PrtParametrosData prtParametrosData = (PrtParametrosData) req.getSession().getAttribute("PrtParametrosDataTema");
			if(prtParametrosData==null){
				prtParametrosData = new PrtParametrosData();
				req.getSession().setAttribute("PrtParametrosData"+keyt,prtParametrosData);
			}
			
			PrtParametrosLC prtParametrosLC = new PrtParametrosLC();
			long inicio = System.currentTimeMillis();
			List<PrtParametrosBk> prtParametrossss = servicio.getPrtParametrosHijosForKey(msUsuariosBk.getIdusuario(),keyt);
			long lfinal =System.currentTimeMillis()-inicio;
			prtParametrosLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.PRTPARAMETROS_CREA)){
				prtParametrosLC.setCreamodifica(true);
			}
				
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<PrtParametrosBk> prtParametrossssData = new ArrayList<PrtParametrosBk> ();
			if(prtParametrosFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(PrtParametrosBk prtParametrosAct : prtParametrossss){
	            boolean match = true;	            
	            Field camposdea[] = prtParametrosFiltro.getClass().getDeclaredFields();
//	            if(prtParametrosAct.getIdparametro.longValue()==56L){
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
						Method filtroMethod = prtParametrosFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(prtParametrosFiltro, new Object[0]);
						if(filtroValue==null) continue;
						Method claseMethod = prtParametrosAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(prtParametrosAct, new Object[0]);
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
					prtParametrossssData.add(prtParametrosAct);
	            }	            
	        }}else{
	        	prtParametrossssData = prtParametrossss;
	        }	 
	        //sort
	        if(sorder != null) {
	            Collections.sort(prtParametrossssData, new Comparator<PrtParametrosBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(PrtParametrosBk prtParametros1, PrtParametrosBk prtParametros2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = PrtParametrosBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(prtParametros1, new Object[0]);
						Object value2 = method.invoke(prtParametros2, new Object[0]);
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
	        int dataSize = prtParametrossssData.size();
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
	        prtParametrosLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	prtParametrosLC.setData(prtParametrossssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	prtParametrosLC.setData(prtParametrossssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 prtParametrosLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<PrtParametrosLC> registrosx = new GenericEntity<PrtParametrosLC>(prtParametrosLC) {
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
	@Path("/listaParametrosXId/{keyt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTemasPorPadre(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString,
			@PathParam("keyt") String keyt
			) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
					.entity(new GenericEntity<RespuestaError>(
							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
									HttpURLConnection.HTTP_UNAUTHORIZED)) {
					}).build();
		if(keyt.equals("canales")){
			keyt = "CANALES DE ATENCIÓN";
		}else{
			keyt = keyt.toUpperCase();
		}
		try { 
			List<IDValorDto> iDValorDtosss = servicio.getPrtParametrosPadre(keyt);
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
	
}
