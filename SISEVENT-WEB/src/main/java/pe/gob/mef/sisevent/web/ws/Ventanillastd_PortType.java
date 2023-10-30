/**
 * Ventanillastd_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.ws;

public interface Ventanillastd_PortType extends java.rmi.Remote {
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaPersonaXDNI(java.lang.String dni) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] congresistasActivos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] comisionessActivas() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDtoComCongre[] comisionesCongre() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] tiposDocumentos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.UnidadesOrganicasTreeDto[] unidadesOrganicas() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.HrDto anexarAExpediente(java.lang.String NOMBRECORTO, java.lang.String NUMEROSID, int NUMEROANIO, java.lang.String NUM_OFICIO, pe.gob.mef.sisevent.web.ws.AnexoDto[] ANEXOS, java.lang.String REMOTEADDRESS, pe.gob.mef.sisevent.web.ws.TdFlujoSDto[] UNIDADES) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] instrucciones() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto estadoDeExpediente(java.lang.String NOMBRECORTO, java.lang.String NUMEROSID, int NUMEROANIO, java.lang.String REMOTEADDRESS) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.TtUsuariosSistemaDto[] listaUsuariosMesa() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.TaFeriadosDto[] listaDiasFeriados() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.HrDto crearExpediente(java.lang.String NOMBRECORTO, java.lang.String NUM_REGISTRO, long TIPO_DOCUMENTO, java.lang.String NUM_OFICIO, int NUM_FOLIOS, java.lang.String ASUNTO, java.lang.String APELLIDOPATERNO, java.lang.String APELLIDOMATERNO, java.lang.String NOMBRES, java.lang.String DNI, java.lang.String TELEFONO, java.lang.String RAZONSOCIAL, java.lang.String RUC, java.lang.String DIRECCION, java.lang.String DEPARTAMENTO, java.lang.String PROVINCIA, java.lang.String DISTRITO, java.lang.String CORREO, pe.gob.mef.sisevent.web.ws.AnexoDto[] ANEXOS, java.lang.String REMOTEADDRESS, pe.gob.mef.sisevent.web.ws.TdFlujoSDto[] UNIDADES) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] estadosExpediente() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.AcMsUbigwsDto[] ubigeos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaEntidadXRazon(java.lang.String razonSocial) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaEntidadXRuc(java.lang.String ruc) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
    public pe.gob.mef.sisevent.web.ws.ExpedienteWSDto consultaExpediente(int anio, java.lang.String numero) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo;
}
