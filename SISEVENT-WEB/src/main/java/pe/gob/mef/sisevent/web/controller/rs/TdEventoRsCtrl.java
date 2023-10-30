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
import pe.gob.mef.sisevent.bs.transfer.IDsValorDto;
import pe.gob.mef.sisevent.bs.transfer.MsInstitucionesDto;
import pe.gob.mef.sisevent.bs.transfer.MsPersonasDto;
import pe.gob.mef.sisevent.bs.transfer.bk.MsCategoriasBk;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdEventoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdGruposflujosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdParticipantesBk;
import pe.gob.mef.sisevent.bs.utils.FuncionesStaticas;
import pe.gob.mef.sisevent.web.controller.TdEventoData;
import pe.gob.mef.sisevent.web.controller.rs.data.ExpedienteJS;
import pe.gob.mef.sisevent.web.controller.rs.data.MovimientoJS;
import pe.gob.mef.sisevent.web.controller.rs.data.RespuestaError;
import pe.gob.mef.sisevent.web.controller.rs.data.TdEventoJS;
import pe.gob.mef.sisevent.web.controller.rs.data.TdEventoLC;
import pe.gob.mef.sisevent.web.controller.rs.data.TdFlujoJS;
import pe.gob.mef.sisevent.web.ws.sisevent.ExpedienteSiseventDto;
import pe.gob.mef.sisevent.web.ws.sisevent.MovimientoSiseventDto;
import pe.gob.mef.sisevent.web.ws.sisevent.SiseventstdProxy;

@Path("/ctrltdEvento")
public class TdEventoRsCtrl {

	@Autowired
	private Servicio servicio = null;
	
	public TdEventoRsCtrl() {
	}

	/////////////////////////////////////////
	@GET
	@Path("/listatdEvento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTdEvento(
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

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA) && !req.isUserInRole(Roles.TDEVENTO_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {
			String sorder = req.getParameter("order"); 
			String slimit = req.getParameter("limit");
			String spage = req.getParameter("page");
			
			String idevent = req.getParameter("idevent");
			String titulo = req.getParameter("titulo");
			String fechaSoliIni = req.getParameter("fechaSoliIni");
			String idcategorias = req.getParameter("idcategorias");
			String modalidad = req.getParameter("modalidad");
			String estado = req.getParameter("estado");
			
            String sestado = req.getParameter("estado");
			
			Integer iestado = null;
			if(sestado!=null){
				try{
					iestado = Integer.parseInt(sestado);
				}catch(Exception e){}
			}		
			
			TdEventoFiltro tdEventoFiltro = new TdEventoFiltro(idevent,titulo,fechaSoliIni,idcategorias,modalidad,estado,iestado);		
			
			TdEventoData tdEventoData = (TdEventoData) req.getSession().getAttribute("TdEventoData");
			if(tdEventoData==null){
				tdEventoData = new TdEventoData();
				req.getSession().setAttribute("TdEventoData",tdEventoData);
			}
			
			TdEventoLC tdEventoLC = new TdEventoLC();
			long inicio = System.currentTimeMillis();
			List<TdEventoBk> tdEventosss = tdEventoData.getTdEventoActivos(servicio,msUsuariosBk.getIdusuario());
			long lfinal =System.currentTimeMillis()-inicio;
			tdEventoLC.setTiempoenBD(lfinal);
			
			if (req.isUserInRole(Roles.ADMINISTRADOR) || req.isUserInRole(Roles.TDEVENTO_CREA)){
				tdEventoLC.setCreamodifica(true);
			}			
			
			/////
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<TdEventoBk> tdEventosssData = new ArrayList<TdEventoBk> ();
			if(tdEventoFiltro.isActivo()){
			//filter
//				int contador = 0;
	        for(TdEventoBk tdEventoAct : tdEventosss){
	            boolean match = true;	            
	            Field camposdea[] = tdEventoFiltro.getClass().getDeclaredFields();
//	            if(tdEventoAct.getIdevent.longValue()==56L){
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
						Method filtroMethod = tdEventoFiltro.getClass().getMethod(filtroGetMetod, types);												
						Object filtroValue = filtroMethod.invoke(tdEventoFiltro, new Object[0]);
						if(filtroValue==null) continue;
						else if(filtroValue.toString().length()<1) continue;
						Method claseMethod = tdEventoAct.getClass().getMethod(claseGetMetod, types);
						Object claseValue = claseMethod.invoke(tdEventoAct, new Object[0]);
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
					tdEventosssData.add(tdEventoAct);
	            }	            
	        }}else{
	        	tdEventosssData = tdEventosss;
	        }	 
	        //sort
			//System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
	        if(sorder != null) {
	            Collections.sort(tdEventosssData, new Comparator<TdEventoBk>() {
	                @SuppressWarnings({ "unchecked", "rawtypes" })
					public int compare(TdEventoBk tdEvento1, TdEventoBk tdEvento2) {	                	
	                	boolean sortorden = true;
	                	String order = sorder;
	                	if(order.startsWith("-")){
	                		sortorden = false;
	                		order = order.substring(1);
	                	}	                	
	                	try{
	                	String getMetod = "get" + Character.toUpperCase(order.charAt(0))+order.substring(1);
	                	Class<?>[] types = new Class[] {};
						Method method = TdEventoBk.class.getMethod(getMetod, types);
						Object value1 = method.invoke(tdEvento1, new Object[0]);
						Object value2 = method.invoke(tdEvento2, new Object[0]);
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
	        int dataSize = tdEventosssData.size();
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
	        tdEventoLC.setContador(dataSize);
			
	        if(dataSize > pageSize) {
	        	int iniciodelista = ((first-1)*pageSize);
	            try {
	                tdEventoLC.setData(tdEventosssData.subList(iniciodelista, iniciodelista+pageSize));
	            }
	            catch(IndexOutOfBoundsException e) {
	            	tdEventoLC.setData(tdEventosssData.subList(iniciodelista, iniciodelista+(dataSize % pageSize)));
	            }
	        }else{
	        	tdEventoLC.setData(tdEventosssData);
	        }
	        lfinal =System.currentTimeMillis()-inicio;
			 System.out.println("EJECUCIÓN EN: "+(lfinal)+" MILISEGUNDOS.");
			 tdEventoLC.setTiempoenproceso(lfinal);
			/////			
			
			GenericEntity<TdEventoLC> registrosx = new GenericEntity<TdEventoLC>(tdEventoLC) {
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
	@Path("/salvartdEvento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarTdEvento(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdEventoJS tdEventoJS) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);

		TdEventoBk tdEventoC = new TdEventoBk();
		FuncionesStaticas.copyPropertiesObject(tdEventoC, tdEventoJS);
		tdEventoC.setRmtaddress(adressRemoto);
		tdEventoC.setRmtaddressrst(adressRemoto);
		tdEventoC.setEstado(1);

		try {
			
			tdEventoC.setTdFlujoBks(tdEventoJS.getTdFlujoBks());//vbaldeon 25092023
			
			tdEventoC = servicio.saveorupdateTdEventoBk(tdEventoC, msUsuariosBk.getUsername(),msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(),adressRemoto);
//			tdEventoJS = new TdEventoJS();
//			FuncionesStaticas.copyPropertiesObject(tdEventoJS, tdEventoC);
//			tdEventoJS.setEditopcion(tdEventoC.gettdEventoACL().getEditopcion());
			
			TdEventoData tdEventoData = (TdEventoData) req.getSession().getAttribute("TdEventoData");
			if(tdEventoData==null){
				tdEventoData = new TdEventoData();
				req.getSession().setAttribute("TdEventoData",tdEventoData);
			}
			tdEventoData.add(servicio, msUsuariosBk.getIdusuario(), tdEventoC);
			
			GenericEntity<TdEventoBk> registrors = new GenericEntity<TdEventoBk>(tdEventoC) {
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
	@Path("/eliminartdEvento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarTdEvento(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, TdEventoJS tdEventoE) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();
		
		String adressRemoto = getRemoteAdress(req);
		TdEventoBk tdEventoC = new TdEventoBk();
		FuncionesStaticas.copyPropertiesObject(tdEventoC, tdEventoE);

		try {
			servicio.deleteTdEvento(tdEventoC, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), adressRemoto);
			
			TdEventoData tdEventoData = (TdEventoData) req.getSession().getAttribute("TdEventoData");
			if(tdEventoData==null){
				tdEventoData = new TdEventoData();
				req.getSession().setAttribute("TdEventoData",tdEventoData);
			}
			tdEventoData.refrescar(servicio, msUsuariosBk.getIdusuario());
			
			GenericEntity<TdEventoBk> registro = new GenericEntity<TdEventoBk>(tdEventoC) {
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
	@Path("/editartdEvento/{idevent}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarTdEvento(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idevent") Long idevent) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if(!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA) && !req.isUserInRole(Roles.TDEVENTO_VE))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();	

		try {
			TdEventoBk tdEventoE = servicio.getTdEventoBkXid(idevent,msUsuariosBk.getIdusuario());
			
			GenericEntity<TdEventoBk> registro = new GenericEntity<TdEventoBk>(tdEventoE) {
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
	@Path("/listaMsInstituciones")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaMsInstituciones(@Context HttpServletRequest req, @Context HttpServletResponse res,
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
			List<MsInstitucionesDto> unidades = servicio.getMsInstitucionesCache();			
			   GenericEntity<List<MsInstitucionesDto>> registrosx = new GenericEntity<List<MsInstitucionesDto>>(unidades){
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
	@Path("/listaMsPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaMsPersonas(@Context HttpServletRequest req, @Context HttpServletResponse res,
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
			List<MsPersonasDto> unidades = servicio.getMsPersonasCache();			
			   GenericEntity<List<MsPersonasDto>> registrosx = new GenericEntity<List<MsPersonasDto>>(unidades){
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
	@Path("/listaPrtParametrosIdparametro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPrtParametrosIdparametro(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {			
			List<IDValorDto> datos = servicio.getPrtParametrosIdparametro();			
			   GenericEntity<List<IDValorDto>> registrosx = new GenericEntity<List<IDValorDto>>(datos){
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
	@Path("/listaMsCategoriasIdcategorias/{idcategorias}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaMsCategoriasIdcategorias(@Context HttpServletRequest req, @Context HttpServletResponse res,
			@HeaderParam("authorization") String authString, @PathParam("idcategorias") String idcategorias) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		Principal usuario = req.getUserPrincipal();
		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

		if (msUsuariosBk == null)
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
					new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
			}).build();

		try {			
			List<MsCategoriasBk> datos = servicio.getMsCategoriasIdcategorias(idcategorias);			
			   GenericEntity<List<MsCategoriasBk>> registrosx = new GenericEntity<List<MsCategoriasBk>>(datos){
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

      //MPINARES 01092023 - INICIO
    	@GET
    	@Path("/situaciontramite/{anio}/{numero}")
    	@Produces(MediaType.APPLICATION_JSON)
    	public Response situaciontramite(@Context HttpServletRequest req, @Context HttpServletResponse res,
    			@HeaderParam("authorization") String authString, @PathParam("anio") Integer anio,
    			@PathParam("numero") String numero) {
    		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    		Principal usuario = req.getUserPrincipal();
    		MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

    		if (msUsuariosBk == null)
    			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
    					.entity(new GenericEntity<RespuestaError>(
    							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.",
    									HttpURLConnection.HTTP_UNAUTHORIZED)) {
    					}).build();

    		if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA)
    				&& !req.isUserInRole(Roles.TDEVENTO_VE) )
    			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
    					.entity(new GenericEntity<RespuestaError>(
    							new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.",
    									HttpURLConnection.HTTP_UNAUTHORIZED)) {
    					}).build();

    		try {

    			String endpointstdventanilla = servicio.getEndpointVentanilla();
    			SiseventstdProxy siseventstdProxy=new SiseventstdProxy(endpointstdventanilla);
    			ExpedienteSiseventDto expedienteSiseventDto=siseventstdProxy.consultaExpediente(anio, numero);
    			MovimientoSiseventDto[] movimientos = expedienteSiseventDto.getMovimientos();
    			// "ELIMINADO", //0 "INGRESADO", //1 "POR RECIBIR", //2 "ASIGNADO", //3
    			// "RECIBIDO", //4 "ATENDIDO", //5 "FINALIZADO", //6 "REACTIVADO", //7
    			// "DELEGADO", //8 "DERIVADO", //9 "REASIGNADO",//10 "RECIBIDO", //11
    			// "SIN RESPUESTA",//12 "ACUMULADO", //13 "TRASPASO", //14 "ADICIONO HR", //15
    			// "INSERTO ANEXO",//16 "SUSPENDIDO", //17 "REINICIO DEL PROCESO", //18
    			// "SE LEVANTA LA SUSPENSION", //19 "REITERATIVO", //20 "INGRESADO POR EXTERNO", //21
    			// "DEVUELTO", //22 "REVERTIDO", //23 "RECIBIDO CON DEVOLUCIÓN", //24
    			// "EN TRÁNSITO", //25 "TRÁNSITO RECIBIDO", //26 "PRORROGA", //27 "ANEXO TRÁNSITO", //28
    			// "RECIBIDO ANEXO EN TRÁNSITO", //29 "ENCAUSAMIENTO"//30 ,"EXT. PENDIENTE" // 31
    			// "EXT. ENVIADO" // 32 ,"EXT. RECIBIDO" // 33 ,"EXT. OBSERVADO" // 34 ,"EXT. SUBSANADO" // 35 |
    			// "EXT. NO PRESENTADO" // 36 | "EXT. ANEXADO" // 37 |
    			List<MovimientoJS> movimientoss = new ArrayList<MovimientoJS>();	
    			if (movimientos != null) {
    				for (MovimientoSiseventDto movimientoSACCDto : movimientos) {
    					if (movimientoSACCDto.getEstado() != null) {
    							MovimientoJS movimientoJS = new MovimientoJS();
    							FuncionesStaticas.copyPropertiesObject(movimientoJS, movimientoSACCDto);
    							movimientoss.add(movimientoJS);
    							break;
    					}
    				}
    			}
    			ExpedienteJS expedienteJS = new ExpedienteJS();
    			FuncionesStaticas.copyPropertiesObject(expedienteJS, expedienteSiseventDto);//MPINARES 27042022 - INICIO
    			expedienteJS.setMovimientoss(movimientoss);

    			GenericEntity<ExpedienteJS> registrosx = new GenericEntity<ExpedienteJS>(expedienteJS) {
    			};
    			return Response.status(200).entity(registrosx).build();
    		} catch (Exception e) {
    			String mensaje = e.getMessage();
    			System.out.println("ERROR: " + mensaje);
    			if (mensaje != null && mensaje.startsWith("Method Parameter: expediente cannot be null"))
    				mensaje = "EXPEDIENTE NO ENCONTRADO...";
    			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(
    					new GenericEntity<RespuestaError>(new RespuestaError(mensaje, HttpURLConnection.HTTP_BAD_REQUEST)) {
    					}).build();
    		}
    	}
    	//MPINARES 01092023 - FIN
    	 //MPINARES 01092023 - INICIO 
        @GET
		@Path("/listaPrtParametrosTipoEvento")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaPrtParametrosDescripcionServicio(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<IDsValorDto> datos = servicio.getPrtParametrosTipoEventoo();			
				   GenericEntity<List<IDsValorDto>> registrosx = new GenericEntity<List<IDsValorDto>>(datos){
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
		@Path("/listaPrtParametrosTipoVuelo")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaPrtParametrosTipoVuelo(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<IDsValorDto> datos = servicio.getPrtParametrosTipoEventoo();			
				   GenericEntity<List<IDsValorDto>> registrosx = new GenericEntity<List<IDsValorDto>>(datos){
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
		@Path("/listaMsCategorias")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaMsCategorias(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<MsCategoriasBk> datos = servicio.getAllMsCategoriasActivos(msUsuariosBk.getIdusuario());		
				   GenericEntity<List<MsCategoriasBk>> registrosx = new GenericEntity<List<MsCategoriasBk>>(datos){
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
		@Path("/listaMsUsuarios")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaMsUsuarios(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<MsUsuariosBk> datos = servicio.getAllMsUsuariosActivos(msUsuariosBk.getUsername());	
				   GenericEntity<List<MsUsuariosBk>> registrosx = new GenericEntity<List<MsUsuariosBk>>(datos){
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
		@Path("/listaTdParticipantes")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaTdParticipantes(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<TdParticipantesBk> datos = servicio.getAllTdParticipantesActivos(msUsuariosBk.getIdusuario());		
				   GenericEntity<List<TdParticipantesBk>> registrosx = new GenericEntity<List<TdParticipantesBk>>(datos){
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
      //MPINARES 01092023 - FIN
        
      //vbaldeon 08092023 INICIO
    	@GET
		@Path("/listaPrtParametrosDescripcionTipoTarea")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaPrtParametrosDescripcionTipoTarea(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<IDValorDto> datos = servicio.getPrtParametrosContratoTipoTarea();			
				   GenericEntity<List<IDValorDto>> registrosx = new GenericEntity<List<IDValorDto>>(datos){
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
		@Path("/listaGrupo")
		@Produces(MediaType.APPLICATION_JSON)
		public Response listaGrupo(@Context HttpServletRequest req, @Context HttpServletResponse res,
				@HeaderParam("authorization") String authString) {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			try {			
				List<TdGruposBk> datos = servicio.getAllTdGruposActivos(msUsuariosBk.getIdusuario());			
				   GenericEntity<List<TdGruposBk>> registrosx = new GenericEntity<List<TdGruposBk>>(datos){
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
    	@Path("/listaGrupoFlujo")
    	@Produces(MediaType.APPLICATION_JSON)
    	public Response listaGrupoFlujo(@Context HttpServletRequest req, @Context HttpServletResponse res,
    			@HeaderParam("authorization") String authString) {

    		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();
			
    		try {
    			List<TdGruposflujosBk> lstGrupoFlujoTotal =new ArrayList<TdGruposflujosBk>();
    			Long idGrupo;
    			String gruposSeleccionados = req.getParameter("gruposSeleccionados"); 
    			if(gruposSeleccionados!=null && gruposSeleccionados.length()>0){
    				String[]lstGrupo=gruposSeleccionados.split("/");
    				for(String id:lstGrupo){
    					if(id==null || id.trim().length()<1)
    						break;    					
    					idGrupo=new Long(id);
    					List<TdGruposflujosBk> datos= servicio.getTdGruposflujosXFiltro(null,null,null, msUsuariosBk.getIdusuario(),idGrupo);	
    					for(TdGruposflujosBk dat: datos){    
    						if(dat!=null && dat.getIdgruposflujos()!=null)
    							lstGrupoFlujoTotal.add(dat);
    					}
    				}
    			}	
    					
				GenericEntity<List<TdGruposflujosBk>> registrosx = new GenericEntity<List<TdGruposflujosBk>>(lstGrupoFlujoTotal){
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
    	@Path("/eliminarTdFlujoBk")
    	@Produces(MediaType.APPLICATION_JSON)
    	public Response eliminarTdFlujoBk(@Context HttpServletRequest req, @Context HttpServletResponse res,
    			@HeaderParam("authorization") String authString, TdFlujoJS tdFlujoJS) {
    		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();
			
    		
			
			String adressRemoto = getRemoteAdress(req);
			TdFlujoBk tdFlujoBk = new TdFlujoBk();
    		FuncionesStaticas.copyPropertiesObject(tdFlujoBk, tdFlujoJS);

    		try {
    			servicio.deleteTdFlujo(tdFlujoBk, msUsuariosBk.getUsername(), msUsuariosBk.getIdusuario(), msUsuariosBk.getIdunidad(), adressRemoto);
    	 			
    			
    			GenericEntity<TdFlujoBk> registro = new GenericEntity<TdFlujoBk>(tdFlujoBk) {
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
    	@Path("/enviarAlerta")
    	@Produces(MediaType.APPLICATION_JSON)
    	public Response enviarAlerta(@Context HttpServletRequest req, @Context HttpServletResponse res,
    			@HeaderParam("authorization") String authString, TdFlujoJS tdFlujoJS) {
    		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

			Principal usuario = req.getUserPrincipal();
			MsUsuariosBk msUsuariosBk = servicio.getMsUsuariosBkXUsername(usuario.getName());

			if (msUsuariosBk == null)
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN A REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();

			if (!req.isUserInRole(Roles.ADMINISTRADOR) && !req.isUserInRole(Roles.TDEVENTO_CREA))
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(new GenericEntity<RespuestaError>(
						new RespuestaError("ERROR NO TIENE AUTORIZACIÓN PARA REALIZAR ESTA OPERACIÓN.", HttpURLConnection.HTTP_UNAUTHORIZED)) {
				}).build();
			
    		
			
			String adressRemoto = getRemoteAdress(req);
			TdFlujoBk tdFlujoBk = new TdFlujoBk();
    		FuncionesStaticas.copyPropertiesObject(tdFlujoBk, tdFlujoJS);

    		try {
    			servicio.notificarFlujo(tdFlujoBk, msUsuariosBk.getIdusuario(), msUsuariosBk, adressRemoto);
    			GenericEntity<TdFlujoBk> registro = new GenericEntity<TdFlujoBk>(tdFlujoBk) {
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
    	//vbaldeon 08092023 fin
}
