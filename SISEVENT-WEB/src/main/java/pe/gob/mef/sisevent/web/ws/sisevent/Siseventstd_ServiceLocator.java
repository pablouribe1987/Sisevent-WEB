/**
 * Siseventstd_ServiceLocator.java
 * //MPINARES 01092023 - INICIO - NUEVA CLASE
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.mef.sisevent.web.ws.sisevent;

public class Siseventstd_ServiceLocator extends org.apache.axis.client.Service implements pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_Service {

    public Siseventstd_ServiceLocator() {
    }


    public Siseventstd_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Siseventstd_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SiseventSTDPort
    private java.lang.String SiseventSTDPort_address = "http://localhost:8380/tramite/webservice/siseventstd";

    public java.lang.String getSiseventSTDPortAddress() {
        return SiseventSTDPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SiseventSTDPortWSDDServiceName = "SiseventSTDPort";

    public java.lang.String getSiseventSTDPortWSDDServiceName() {
        return SiseventSTDPortWSDDServiceName;
    }

    public void setSiseventSTDPortWSDDServiceName(java.lang.String name) {
        SiseventSTDPortWSDDServiceName = name;
    }

    public pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType getSiseventSTDPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SiseventSTDPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSiseventSTDPort(endpoint);
    }

    public pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType getSiseventSTDPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            pe.gob.mef.sisevent.web.ws.sisevent.SiseventSTDPortBindingStub _stub = new pe.gob.mef.sisevent.web.ws.sisevent.SiseventSTDPortBindingStub(portAddress, this);
            _stub.setPortName(getSiseventSTDPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSiseventSTDPortEndpointAddress(java.lang.String address) {
        SiseventSTDPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                pe.gob.mef.sisevent.web.ws.sisevent.SiseventSTDPortBindingStub _stub = new pe.gob.mef.sisevent.web.ws.sisevent.SiseventSTDPortBindingStub(new java.net.URL(SiseventSTDPort_address), this);
                _stub.setPortName(getSiseventSTDPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SiseventSTDPort".equals(inputPortName)) {
            return getSiseventSTDPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.web.bs.std.mef.gob.pe/", "siseventstd");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.web.bs.std.mef.gob.pe/", "SiseventSTDPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SiseventSTDPort".equals(portName)) {
            setSiseventSTDPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
