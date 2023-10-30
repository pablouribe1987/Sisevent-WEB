/**
 * Siseventstd_Service.java
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.ws.sisevent;

public interface Siseventstd_Service extends javax.xml.rpc.Service {
    public java.lang.String getSiseventSTDPortAddress();

    public pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType getSiseventSTDPort() throws javax.xml.rpc.ServiceException;

    public pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType getSiseventSTDPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
