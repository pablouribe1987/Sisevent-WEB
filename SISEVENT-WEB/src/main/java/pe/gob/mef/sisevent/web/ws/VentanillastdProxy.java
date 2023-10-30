package pe.gob.mef.sisevent.web.ws;

public class VentanillastdProxy implements pe.gob.mef.sisevent.web.ws.Ventanillastd_PortType {
  private String _endpoint = null;
  private pe.gob.mef.sisevent.web.ws.Ventanillastd_PortType ventanillastd_PortType = null;
  
  public VentanillastdProxy() {
    _initVentanillastdProxy();
  }
  
  public VentanillastdProxy(String endpoint) {
    _endpoint = endpoint;
    _initVentanillastdProxy();
  }
  
  private void _initVentanillastdProxy() {
    try {
      ventanillastd_PortType = (new pe.gob.mef.sisevent.web.ws.Ventanillastd_ServiceLocator()).getVentanillaSTDPort();
      if (ventanillastd_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ventanillastd_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ventanillastd_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ventanillastd_PortType != null)
      ((javax.xml.rpc.Stub)ventanillastd_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.gob.mef.sisevent.web.ws.Ventanillastd_PortType getVentanillastd_PortType() {
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType;
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaPersonaXDNI(java.lang.String dni) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.consultaPersonaXDNI(dni);
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] congresistasActivos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.congresistasActivos();
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] comisionessActivas() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.comisionessActivas();
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDtoComCongre[] comisionesCongre() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.comisionesCongre();
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] tiposDocumentos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.tiposDocumentos();
  }
  
  public pe.gob.mef.sisevent.web.ws.UnidadesOrganicasTreeDto[] unidadesOrganicas() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.unidadesOrganicas();
  }
  
  public pe.gob.mef.sisevent.web.ws.HrDto anexarAExpediente(java.lang.String NOMBRECORTO, java.lang.String NUMEROSID, int NUMEROANIO, java.lang.String NUM_OFICIO, pe.gob.mef.sisevent.web.ws.AnexoDto[] ANEXOS, java.lang.String REMOTEADDRESS, pe.gob.mef.sisevent.web.ws.TdFlujoSDto[] UNIDADES) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.anexarAExpediente(NOMBRECORTO, NUMEROSID, NUMEROANIO, NUM_OFICIO, ANEXOS, REMOTEADDRESS, UNIDADES);
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] instrucciones() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.instrucciones();
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto estadoDeExpediente(java.lang.String NOMBRECORTO, java.lang.String NUMEROSID, int NUMEROANIO, java.lang.String REMOTEADDRESS) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.estadoDeExpediente(NOMBRECORTO, NUMEROSID, NUMEROANIO, REMOTEADDRESS);
  }
  
  public pe.gob.mef.sisevent.web.ws.TtUsuariosSistemaDto[] listaUsuariosMesa() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.listaUsuariosMesa();
  }
  
  public pe.gob.mef.sisevent.web.ws.TaFeriadosDto[] listaDiasFeriados() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.listaDiasFeriados();
  }
  
  public pe.gob.mef.sisevent.web.ws.HrDto crearExpediente(java.lang.String NOMBRECORTO, java.lang.String NUM_REGISTRO, long TIPO_DOCUMENTO, java.lang.String NUM_OFICIO, int NUM_FOLIOS, java.lang.String ASUNTO, java.lang.String APELLIDOPATERNO, java.lang.String APELLIDOMATERNO, java.lang.String NOMBRES, java.lang.String DNI, java.lang.String TELEFONO, java.lang.String RAZONSOCIAL, java.lang.String RUC, java.lang.String DIRECCION, java.lang.String DEPARTAMENTO, java.lang.String PROVINCIA, java.lang.String DISTRITO, java.lang.String CORREO, pe.gob.mef.sisevent.web.ws.AnexoDto[] ANEXOS, java.lang.String REMOTEADDRESS, pe.gob.mef.sisevent.web.ws.TdFlujoSDto[] UNIDADES) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.crearExpediente(NOMBRECORTO, NUM_REGISTRO, TIPO_DOCUMENTO, NUM_OFICIO, NUM_FOLIOS, ASUNTO, APELLIDOPATERNO, APELLIDOMATERNO, NOMBRES, DNI, TELEFONO, RAZONSOCIAL, RUC, DIRECCION, DEPARTAMENTO, PROVINCIA, DISTRITO, CORREO, ANEXOS, REMOTEADDRESS, UNIDADES);
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] estadosExpediente() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.estadosExpediente();
  }
  
  public pe.gob.mef.sisevent.web.ws.AcMsUbigwsDto[] ubigeos() throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.ubigeos();
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaEntidadXRazon(java.lang.String razonSocial) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.consultaEntidadXRazon(razonSocial);
  }
  
  public pe.gob.mef.sisevent.web.ws.IdValorDto[] consultaEntidadXRuc(java.lang.String ruc) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.consultaEntidadXRuc(ruc);
  }
  
  public pe.gob.mef.sisevent.web.ws.ExpedienteWSDto consultaExpediente(int anio, java.lang.String numero) throws java.rmi.RemoteException, pe.gob.mef.sisevent.web.ws.ErrorInfo{
    if (ventanillastd_PortType == null)
      _initVentanillastdProxy();
    return ventanillastd_PortType.consultaExpediente(anio, numero);
  }
  
  
}