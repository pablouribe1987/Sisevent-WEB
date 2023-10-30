package pe.gob.mef.sisevent.web.controller.rs.data;

import java.sql.Timestamp;
import java.util.List;

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
public class TdParticipantesJS implements java.io.Serializable {

	// ID
	private Long idparticipantes;

	// PROPIEDADES
	private Long idusuarioIdproveeIdperson = null;
	private String nombresrazonsocial = null;
	private Integer tipo = null;
	private Integer flagacompaniante = null;
	
	
	// ADICIONALES
	private String idusuarioIdproveeIdpersonTxt = null;
	private String iduserCreaTxt = null;
	private String iduserModifTxt = null;
	
	private Integer editopcion = 1;

	public TdParticipantesJS() {
	}

	public Long getIdparticipantes() {
		return this.idparticipantes;
	}

	public void setIdparticipantes(Long idparticipantes) {
		this.idparticipantes = idparticipantes;
	}
	
	public Long getIdusuarioIdproveeIdperson() {
						return this.idusuarioIdproveeIdperson;
					}

	public void setIdusuarioIdproveeIdperson(Long idusuarioIdproveeIdperson) {
						this.idusuarioIdproveeIdperson = idusuarioIdproveeIdperson;
					}
	public String getNombresrazonsocial() {
						return this.nombresrazonsocial;
					}

	public void setNombresrazonsocial(String nombresrazonsocial) {
						this.nombresrazonsocial = nombresrazonsocial;
					}
	public Integer getTipo() {
						return this.tipo;
					}

	public void setTipo(Integer tipo) {
						this.tipo = tipo;
					}
	public Integer getFlagacompaniante() {
						return this.flagacompaniante;
					}

	public void setFlagacompaniante(Integer flagacompaniante) {
						this.flagacompaniante = flagacompaniante;
					}
	
	
	public String getIdusuarioIdproveeIdpersonTxt() {
		return this.idusuarioIdproveeIdpersonTxt;
	}
	public void setIdusuarioIdproveeIdpersonTxt(String idusuarioIdproveeIdpersonTxt) {
		this.idusuarioIdproveeIdpersonTxt = idusuarioIdproveeIdpersonTxt;
	}
	public String getIduserCreaTxt() {
		return this.iduserCreaTxt;
	}
	public void setIduserCreaTxt(String iduserCreaTxt) {
		this.iduserCreaTxt = iduserCreaTxt;
	}
	public String getIduserModifTxt() {
		return this.iduserModifTxt;
	}
	public void setIduserModifTxt(String iduserModifTxt) {
		this.iduserModifTxt = iduserModifTxt;
	}
		

	public Integer getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(Integer editopcion) {
		this.editopcion = editopcion;
	}			
}
