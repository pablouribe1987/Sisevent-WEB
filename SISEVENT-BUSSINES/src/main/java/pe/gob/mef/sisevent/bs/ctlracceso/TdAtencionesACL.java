package pe.gob.mef.sisevent.bs.ctlracceso;

import java.io.Serializable;

public class TdAtencionesACL implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259549284240982379L;
	private boolean esEditable = false;
	private boolean eliminar = false;
	private int editopcion = 1;
	private boolean showmovimiento = false;
	private boolean accionAsignar = false;
	private boolean accionDerivar = false;
	private boolean verNotificar = false;
	private boolean accionAnular = false;
	private boolean accionEditar = false;
	private boolean accionCrea = false;
	
	private boolean esVisible = false;
	
	public TdAtencionesACL() {
	}

	public boolean isEsEditable() {
		return esEditable;
	}

	public void setEsEditable(boolean esEditable) {
		this.esEditable = esEditable;
	}

	public boolean isEliminar() {
		return eliminar;
	}

	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}

	public int getEditopcion() {
		return editopcion;
	}

	public void setEditopcion(int editopcion) {
		this.editopcion = editopcion;
	}

	@Override
	public String toString() {
		return "TdAtencionesACL [esEditable=" + esEditable + ", eliminar=" + eliminar + ", editopcion=" + editopcion
				+ "]";
	}

	public boolean isEsVisible() {
		return esVisible;
	}

	public void setEsVisible(boolean esVisible) {
		this.esVisible = esVisible;
	}

	public boolean isShowmovimiento() {
		return showmovimiento;
	}

	public void setShowmovimiento(boolean showmovimiento) {
		this.showmovimiento = showmovimiento;
	}

	public boolean isAccionAsignar() {
		return accionAsignar;
	}

	public void setAccionAsignar(boolean accionAsignar) {
		this.accionAsignar = accionAsignar;
	}

	public boolean isAccionDerivar() {
		return accionDerivar;
	}

	public void setAccionDerivar(boolean accionDerivar) {
		this.accionDerivar = accionDerivar;
	}

	public boolean isVerNotificar() {
		return verNotificar;
	}

	public void setVerNotificar(boolean verNotificar) {
		this.verNotificar = verNotificar;
	}

	public boolean isAccionAnular() {
		return accionAnular;
	}

	public void setAccionAnular(boolean accionAnular) {
		this.accionAnular = accionAnular;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}

	public boolean isAccionCrea() {
		return accionCrea;
	}

	public void setAccionCrea(boolean accionCrea) {
		this.accionCrea = accionCrea;
	}	
}
