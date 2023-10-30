package pe.gob.mef.sisevent.bs.val.validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.resources.Messages;
import pe.gob.mef.sisevent.bs.transfer.bk.TdEventoBk;

/**
 * TD_EVENTO SERVICIO VALIDACIÓN: EVENTO
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:35
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi             / 29/04/2023 22:35                      / Creación de la clase             /
 * 
 */
public class ValidacionTdEventoMng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6586088974045803647L;
	public static final Logger log = Logger.getLogger(ValidacionTdEventoMng.class.getName());
	
	public static void validarTdEventoBk(TdEventoBk tdEventoBk)
	 throws Validador
	{
                //FORANEAS
        
	        if(tdEventoBk.getRemiIdprovee()!=null && tdEventoBk.getRemiIdprovee().longValue()<=0){
			tdEventoBk.setRemiIdprovee(null);
		}
	    
	        if(tdEventoBk.getRemiIdperson()!=null && tdEventoBk.getRemiIdperson().longValue()<=0){
			tdEventoBk.setRemiIdperson(null);
		}
	    
	        if(tdEventoBk.getIdcategorias()!=null && tdEventoBk.getIdcategorias().longValue()<=0){
			tdEventoBk.setIdcategorias(null);
		}
	    
	        if(tdEventoBk.getIduserCrea()!=null && tdEventoBk.getIduserCrea().longValue()<=0){
			tdEventoBk.setIduserCrea(null);
		}
	        if(tdEventoBk.getIduserModif()!=null && tdEventoBk.getIduserModif().longValue()<=0){
			tdEventoBk.setIduserModif(null);
		}
	        
		//VALIDANDO
		
		validarTitulo(tdEventoBk.getTitulo());
		if(tdEventoBk.getTitulo()!=null){
				if(tdEventoBk.getTitulo().trim().length()>600)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdEvento.noexceder"),
							Messages.getStringToKey("tdEvento.titulo"),
							Messages.getStringToKey("tdEvento.titulotabla"),
							600,
							Messages.getStringToKey("tdEvento.articuloTitulo")
							));				
				tdEventoBk.setTitulo(tdEventoBk.getTitulo().toUpperCase());
				}

		validarFechaSoliIni(tdEventoBk.getFechaSoliIni());

		validarFechaSoliFin(tdEventoBk.getFechaSoliFin());

		validarPorconfirmar(tdEventoBk.getPorconfirmar());

		//validarIddoc(tdEventoBk.getIddoc());

		
		//validarNumeroSid(tdEventoBk.getNumeroSid());
		if(tdEventoBk.getNumeroSid()!=null){
				if(tdEventoBk.getNumeroSid().trim().length()>50)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdEvento.noexceder"),
							Messages.getStringToKey("tdEvento.numeroSid"),
							Messages.getStringToKey("tdEvento.titulotabla"),
							50,
							Messages.getStringToKey("tdEvento.articuloNumeroSid")
									));				
				tdEventoBk.setNumeroSid(tdEventoBk.getNumeroSid().toUpperCase());
				}

		//validarNumeroAnioSid(tdEventoBk.getNumeroAnioSid());

		
		validarAsuntohr(tdEventoBk.getAsuntohr());
		if(tdEventoBk.getAsuntohr()!=null){
				if(tdEventoBk.getAsuntohr().trim().length()>600)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdEvento.noexceder"),
							Messages.getStringToKey("tdEvento.asuntohr"),
							Messages.getStringToKey("tdEvento.titulotabla"),
							600,
							Messages.getStringToKey("tdEvento.articuloAsuntohr")
							));				
				tdEventoBk.setAsuntohr(tdEventoBk.getAsuntohr().toUpperCase());
				}

		
		//validarRemiRazonSocial(tdEventoBk.getRemiRazonSocial());
		if(tdEventoBk.getRemiRazonSocial()!=null){
				if(tdEventoBk.getRemiRazonSocial().trim().length()>255)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdEvento.noexceder"),
							Messages.getStringToKey("tdEvento.remiRazonSocial"),
							Messages.getStringToKey("tdEvento.titulotabla"),
							255,
							Messages.getStringToKey("tdEvento.articuloRemiRazonSocial")
									));				
				tdEventoBk.setRemiRazonSocial(tdEventoBk.getRemiRazonSocial().toUpperCase());
				}

		//validarRemiIdprovee(tdEventoBk.getRemiIdprovee());

		
		//validarRemiNombresapellidos(tdEventoBk.getRemiNombresapellidos());
		if(tdEventoBk.getRemiNombresapellidos()!=null){
				if(tdEventoBk.getRemiNombresapellidos().trim().length()>350)
					throw new Validador(MessageFormat.format(
							Messages.getStringToKey("tdEvento.noexceder"),
							Messages.getStringToKey("tdEvento.remiNombresapellidos"),
							Messages.getStringToKey("tdEvento.titulotabla"),
							350,
							Messages.getStringToKey("tdEvento.articuloRemiNombresapellidos")
							));				
				tdEventoBk.setRemiNombresapellidos(tdEventoBk.getRemiNombresapellidos().toUpperCase());
				}

		//validarRemiIdperson(tdEventoBk.getRemiIdperson());

		//validarTipoevento(tdEventoBk.getTipoevento());

//		validarIdcategorias(tdEventoBk.getIdcategorias());//MPINARES 01092023 - INICIO -  SE COMENTA

		//validarModalidad(tdEventoBk.getModalidad());
	
	}

	public static void validarTitulo(String titulo)
	 throws Validador
	{					
			if(titulo==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.ingrese"),
			Messages.getStringToKey("tdEvento.titulo"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloTitulo")));
			if(titulo.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.titulo"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloTitulo")));						
			if(titulo!=null){
				if(titulo.trim().length()>600)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.noexceder"),
			Messages.getStringToKey("tdEvento.titulo"),
			Messages.getStringToKey("tdEvento.titulotabla"),600,
			Messages.getStringToKey("tdEvento.articuloTitulo")));
				}
	}
	
	public static void validarFechaSoliIni(Timestamp fechaSoliIni)
	 throws Validador
	{				
					if(fechaSoliIni==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.fechaSoliIni"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloFechaSoliIni")));
		}
	
	public static void validarFechaSoliFin(Timestamp fechaSoliFin)
	 throws Validador
	{				
					if(fechaSoliFin==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.fechaSoliFin"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloFechaSoliFin")));
		}
	
	public static void validarPorconfirmar(Integer porconfirmar)
	 throws Validador
	{				
					if(porconfirmar==null)
						throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.porconfirmar"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloPorconfirmar")));
	}	
	
	public static void validarIddoc(Long iddoc)
			 throws Validador
				{				
								if(iddoc==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.iddoc"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloIddoc")));
					}
	
	
	public static void validarNumeroSid(String numeroSid)
	 throws Validador
	{					
			if(numeroSid==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.ingrese"),
			Messages.getStringToKey("tdEvento.numeroSid"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloNumeroSid")));
			if(numeroSid.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.numeroSid"),
			Messages.getStringToKey("tdEvento.titulotabla")));						
			if(numeroSid!=null){
				if(numeroSid.trim().length()>50)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.noexceder"),
			Messages.getStringToKey("tdEvento.numeroSid"),
			Messages.getStringToKey("tdEvento.titulotabla"),50,
			Messages.getStringToKey("tdEvento.articuloNumeroSid")));
				}
	}
	
	public static void validarNumeroAnioSid(Integer numeroAnioSid)
	 throws Validador
	{				
					if(numeroAnioSid==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.seleccione"),
			Messages.getStringToKey("tdEvento.numeroAnioSid"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloNumeroAnioSid")));
			if(numeroAnioSid.longValue()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoseleccione"),
			Messages.getStringToKey("tdEvento.numeroAnioSid"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloNumeroAnioSid")));			
	}
	
	public static void validarAsuntohr(String asuntohr)
	 throws Validador
	{					
			if(asuntohr==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.ingrese"),
			Messages.getStringToKey("tdEvento.asuntohr"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloAsuntohr")));
			if(asuntohr.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.asuntohr"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloAsuntohr")));						
			if(asuntohr!=null){
				if(asuntohr.trim().length()>600)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.noexceder"),
			Messages.getStringToKey("tdEvento.asuntohr"),
			Messages.getStringToKey("tdEvento.titulotabla"),600,
			Messages.getStringToKey("tdEvento.articuloAsuntohr")));
				}
	}
	
	public static void validarRemiRazonSocial(String remiRazonSocial)
	 throws Validador
	{					
			if(remiRazonSocial==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.ingrese"),
			Messages.getStringToKey("tdEvento.remiRazonSocial"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloRemiRazonSocial")));
			if(remiRazonSocial.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.remiRazonSocial"),
			Messages.getStringToKey("tdEvento.titulotabla")));						
			if(remiRazonSocial!=null){
				if(remiRazonSocial.trim().length()>255)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.noexceder"),
			Messages.getStringToKey("tdEvento.remiRazonSocial"),
			Messages.getStringToKey("tdEvento.titulotabla"),255,
			Messages.getStringToKey("tdEvento.articuloRemiRazonSocial")));
				}
	}
	public static void validarRemiIdprovee(Long remiIdprovee)
			 throws Validador
				{				
								if(remiIdprovee==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.remiIdprovee"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloRemiIdprovee")));
					}
	
	
	public static void validarRemiNombresapellidos(String remiNombresapellidos)
	 throws Validador
	{					
			if(remiNombresapellidos==null)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.ingrese"),
			Messages.getStringToKey("tdEvento.remiNombresapellidos"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloRemiNombresapellidos")));
			if(remiNombresapellidos.trim().length()<=0)
			throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
			Messages.getStringToKey("tdEvento.remiNombresapellidos"),
			Messages.getStringToKey("tdEvento.titulotabla"),
			Messages.getStringToKey("tdEvento.articuloRemiNombresapellidos")));						
			if(remiNombresapellidos!=null){
				if(remiNombresapellidos.trim().length()>350)
					throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.noexceder"),
			Messages.getStringToKey("tdEvento.remiNombresapellidos"),
			Messages.getStringToKey("tdEvento.titulotabla"),350,
			Messages.getStringToKey("tdEvento.articuloRemiNombresapellidos")));
				}
	}
	public static void validarRemiIdperson(Long remiIdperson)
			 throws Validador
				{				
								if(remiIdperson==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.remiIdperson"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloRemiIdperson")));
					}
	
	public static void validarTipoevento(String tipoevento)
			 throws Validador
				{				
								if(tipoevento==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.tipoevento"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloTipoevento")));
					}
	
	public static void validarIdcategorias(Long idcategorias)
			 throws Validador
				{				
								if(idcategorias==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.idcategorias"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloIdcategorias")));
					}
	
	public static void validarModalidad(String modalidad)
			 throws Validador
				{				
								if(modalidad==null)
									throw new Validador(MessageFormat.format(Messages.getStringToKey("tdEvento.invalidoingrese"),
						Messages.getStringToKey("tdEvento.modalidad"),
						Messages.getStringToKey("tdEvento.titulotabla"),
						Messages.getStringToKey("tdEvento.articuloModalidad")));
					}
	
	
	
	
	
	
	
	
		
}