/**
 * Siseventstd_PortType.java
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.ws.sisevent;

public interface Siseventstd_PortType extends java.rmi.Remote {
    public pe.gob.mef.sisevent.web.ws.sisevent.ExpedienteSiseventDto consultaExpediente(int anio, java.lang.String numero) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.sisevent.ErrorInfo;
}
