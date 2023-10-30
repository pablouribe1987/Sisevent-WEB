package pe.gob.mef.sisevent.web.ws.sisevent;
//MPINARES 01092023 - INICIO - NUEVA CLASE
public class SiseventstdProxy implements pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType {
  private String _endpoint = null;
  private pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType siseventstd_PortType = null;
  
  public SiseventstdProxy() {
    _initSiseventstdProxy();
  }
  
  public SiseventstdProxy(String endpoint) {
    _endpoint = endpoint;
    _initSiseventstdProxy();
  }
  
  private void _initSiseventstdProxy() {
    try {
      siseventstd_PortType = (new pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_ServiceLocator()).getSiseventSTDPort();
      if (siseventstd_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)siseventstd_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)siseventstd_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (siseventstd_PortType != null)
      ((javax.xml.rpc.Stub)siseventstd_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.gob.mef.sisevent.web.ws.sisevent.Siseventstd_PortType getSiseventstd_PortType() {
    if (siseventstd_PortType == null)
      _initSiseventstdProxy();
    return siseventstd_PortType;
  }
  
  public pe.gob.mef.sisevent.web.ws.sisevent.ExpedienteSiseventDto consultaExpediente(int anio, java.lang.String numero) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.sisevent.ErrorInfo{
    if (siseventstd_PortType == null)
      _initSiseventstdProxy();
    return siseventstd_PortType.consultaExpediente(anio, numero);
  }
  
  
}