package pe.gob.mef.sisevent.web.controller.rs.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * MSUSUARIOS BAKING: USUARIOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 09/05/2020 02:37
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/ 
 *          /Carlos Aguilar Chamochumbi /09/05/2020 02:37  / Creación de la clase /
 * 
 */
@XmlRootElement
public class MenuaccesosJS  implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6602064884152803272L;
	private boolean entidades = false;
	private boolean personas = false;
	
	private boolean parametros = false;
	private boolean usuarios = false;
	private boolean unidades = false;
	private boolean paises = false;
	private boolean ubigeo = false;
	private boolean feriados = false;
	
    public MenuaccesosJS() {
	}

	public boolean isEntidades() {
		return entidades;
	}

	public void setEntidades(boolean entidades) {
		this.entidades = entidades;
	}

	public boolean isPersonas() {
		return personas;
	}

	public void setPersonas(boolean personas) {
		this.personas = personas;
	}

	
	public boolean isParametros() {
		return parametros;
	}

	public void setParametros(boolean parametros) {
		this.parametros = parametros;
	}

	public boolean isUsuarios() {
		return usuarios;
	}

	public void setUsuarios(boolean usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isUnidades() {
		return unidades;
	}

	public void setUnidades(boolean unidades) {
		this.unidades = unidades;
	}

	public boolean isPaises() {
		return paises;
	}

	public void setPaises(boolean paises) {
		this.paises = paises;
	}

	public boolean isUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(boolean ubigeo) {
		this.ubigeo = ubigeo;
	}

	public boolean isFeriados() {
		return feriados;
	}

	public void setFeriados(boolean feriados) {
		this.feriados = feriados;
	}

    
}
