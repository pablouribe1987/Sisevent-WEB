package pe.gob.mef.sisevent.bs.val.audi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.gob.mef.sisevent.bs.domain.MsCategorias;
import pe.gob.mef.sisevent.bs.transfer.bk.MsCategoriasBk;

/**
 * MS_CATEGORIAS SERVICIO AUDITORIA Y CAMBIO: CATEGORÍAS ASIGADOS A LOS EVENTOS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 29/04/2023 22:11
 * 
 *          /----------Nombre------------/-----fecha--------/---------Motivo-------/                                                       
 *          /Carlos Aguilar Chamochumbi /29/04/2023 22:11  / Creación de la clase /
 * 
 */
public class AuditoriaMsCategoriasMng implements Serializable{

	public static final Logger log = Logger.getLogger(AuditoriaMsCategoriasMng.class.getName());
	
	public static boolean auditarCambiosMsCategorias(MsCategoriasBk msCategoriasBk, MsCategorias msCategorias, 
	Long iduser, 
	String user, 
	String rmtaddress, 
	int nivel 
	)
	{
		boolean cambios = false;
		
		 
		            if (msCategoriasBk.getCategoria() != null
						&& msCategorias.getCategoria() != null) {
					if (!msCategoriasBk.getCategoria().equals(
						msCategorias.getCategoria())) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: " + iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Categoria"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getCategoria() + " :: "+ msCategoriasBk.getCategoria());								
						}
						cambios = true;
						msCategorias.setCategoria(msCategoriasBk.getCategoria());
					}
				} else if (msCategoriasBk.getCategoria() == null
						&& msCategorias.getCategoria() != null) {
					if (msCategorias.getCategoria().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Categoria"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getCategoria() + " :: "+ msCategoriasBk.getCategoria());
						}
						cambios = true;
						msCategorias.setCategoria(msCategoriasBk.getCategoria());
					}
				} else if (msCategoriasBk.getCategoria() != null
						&& msCategorias.getCategoria() == null) {
					if (msCategoriasBk.getCategoria().trim().length() > 0) {						
						if(nivel>0){
						log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Categoria"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getCategoria() + " :: "+ msCategoriasBk.getCategoria());
						}
						cambios = true;
						msCategorias.setCategoria(msCategoriasBk.getCategoria());
					}
				}
				if (msCategoriasBk.getArraycamposocultos() != null
							&& msCategorias.getArraycamposocultos() != null) {
						if (!msCategoriasBk.getArraycamposocultos().equals(
								msCategorias.getArraycamposocultos())) {						
								if(nivel>0){
								log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Arraycamposocultos"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getArraycamposocultos() + " :: "+ msCategoriasBk.getArraycamposocultos());
								}
							cambios = true;
							msCategorias.setArraycamposocultos(msCategoriasBk.getArraycamposocultos());
						}
					} else if (msCategoriasBk.getArraycamposocultos() == null
							&& msCategorias.getArraycamposocultos() != null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Arraycamposocultos"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getArraycamposocultos() + " :: "+ msCategoriasBk.getArraycamposocultos());
								}
							cambios = true;
							msCategorias.setArraycamposocultos(msCategoriasBk.getArraycamposocultos());
						
					} else if (msCategoriasBk.getArraycamposocultos() != null
							&& msCategorias.getArraycamposocultos() == null) {						
							if(nivel>0){
							log.log(Level.INFO,"CAMBIO :: "+ iduser + " :: "+ user + " :: "+ rmtaddress+" :: "+"msCategorias:Arraycamposocultos"+" :: "+msCategoriasBk.getIdcategorias().toString()+" :: "+ msCategorias.getArraycamposocultos() + " :: "+ msCategoriasBk.getArraycamposocultos());
								}
							cambios = true;			
							msCategorias.setArraycamposocultos(msCategoriasBk.getArraycamposocultos());
					}
				
			
			return cambios;
	}
	
}