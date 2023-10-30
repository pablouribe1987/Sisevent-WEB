var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdUbicacionesUrl = contexto+"/rs/ctrltdUbicaciones/listatdUbicaciones";
var inserttdUbicacionesUrl = contexto+"/rs/ctrltdUbicaciones/salvartdUbicaciones";
var eliminartdUbicacionesUrl = contexto+"/rs/ctrltdUbicaciones/eliminartdUbicaciones";
var editartdUbicacionesUrl = contexto+"/rs/ctrltdUbicaciones/editartdUbicaciones/";
var listapaiPkUrl = contexto+"/rs/ctrltdUbicaciones/listapaiPk";
var listaMsUsuariosidusuarioUrl = contexto+"/rs/ctrltdUbicaciones/listaMsUsuariosidusuario";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	  $routeProvider
	  .when("/editar/:idubicaciones", {
		  templateUrl : "adminis/editartdUbicaciones.html",
		  controller : "ctrlListatdUbicaciones"
	  })
	  .when("/nuevo", {
		  templateUrl : "adminis/editartdUbicaciones.html",
		  controller : "ctrlListatdUbicaciones"
	  })
	  .otherwise({
		  templateUrl : "adminis/vertdUbicaciones.html",
		  controller : "ctrlListatdUbicaciones"  
	  });
	});

myapp.config(['$mdDateLocaleProvider', function ($mdDateLocaleProvider) {

	$mdDateLocaleProvider.months = ['enero', 'febrero', 'marzo','abril','mayo','junio','julio','agosto','septiembre','octubre','noviembre','diciembre'];
	$mdDateLocaleProvider.shortMonths = ['ene', 'feb', 'mar','abr','may','jun','jul','ago','sep','oct','nov','dic'];;
	$mdDateLocaleProvider.days = ['domingo','lunes', 'martes', 'miercoles','jueves','viernes','sabado'];
	$mdDateLocaleProvider.shortDays = ['Do','Lu', 'Ma', 'Mi','Ju','Vi','Sa'];

	$mdDateLocaleProvider.formatDate = function (date) {
		if(date==null)
			return "";
		var day = date.getDate();
		var monthIndex = date.getMonth();
		var year = date.getFullYear();

		return (day<10?'0'+day:day) + '/' + (monthIndex<9?'0'+(monthIndex + 1):(monthIndex + 1)) + '/' + year;

//		return date ? moment(date).format('DD/MM/YYYY') : '';
	};
	$mdDateLocaleProvider.parseDate = function (dateString) {
//		var m = moment(dateString, 'DD/MM/YYYY', true);
		var parts = dateString.split("/");
		var dt = new Date(parseInt(parts[2], 10),
				parseInt(parts[1], 10) - 1,
				parseInt(parts[0], 10));
		return dt ? dt:new Date(NaN);
//		return m.isValid() ? m.toDate() : new Date(NaN);
	};
}]);

myapp.controller('ctrlListatdUbicaciones', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';
	 
	 $scope.limitOptions = [100, 500, 1000, 5000];
	 $scope.query = {
			    order: 'idubicaciones',
			    limit: 100,
			    page: 1
			  };
	 
	 $scope.selected = [];
	 $scope.options = {
			    rowSelection: false,
			    multiSelect: true,
			    autoSelect: true,
			    decapitate: false,
			    largeEditDialog: false,
			    boundaryLinks: true,
			    limitSelect: true,
			    pageSelect: true
			  };
	 
	 $scope.principal=function(){
		window.location.href=principalUrl; 
		 };	 
	 
	 $scope.logOrder = function (order) {
	  console.log('order: ', order);
	  $scope.loadtdUbicacioness();
	 };
	 
	 $scope.logItem = function (item) {
	  console.log(item.name, 'was selected');
	 };
	 
	 $scope.logPagination = function (page, limit) {
	  console.log('page: ', page);
	  console.log('limit: ', limit);
	  $scope.loadtdUbicacioness();
         };
     $scope.creatdUbicaciones = false;
    // ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadtdUbicacioness = function () {
	    //$scope.promise = $timeout(function () {
	    	var surl = $scope.getURL();	    
	    	$scope.promise = $http.get(surl).then(function(res){
	    		 $scope.datos = res.data.data;
	    		 if(res.data.contador>0)
	    		 $scope.total = res.data.contador;
	    		 var tiempoenBD = res.data.tiempoenBD;
	    		 var tiempoenproceso = res.data.tiempoenproceso;
	    		 $scope.creatdUbicaciones = res.data.creamodifica;
	    		 console.log("data " +$scope.datos.length+" DE "+ $scope.total);
	    		 console.log("Tiempo respuesta BD tdUbicaciones " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
				},
				function error(errResponse) {
		            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		            var dato = errResponse.data;
		            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
		            	$mdDialog.show(
						         $mdDialog.alert()
						        .parent(angular.element(document.body))
						        .clickOutsideToClose(true)
						        .title('Lista de Ubicaciones Asigados A Los Eventos')
						        .textContent(dato.message)
						        .ariaLabel('ERROR')
						        .ok('OK')
						    );
		            }
		        });			 
	     //}, 500);
	  };
	 	  
	  $scope.getURL=function(){
		    var elprimero = true;
		    var order = ""; 
		    if(!$scope.isNull($scope.query.order) && $scope.isString($scope.query.order)){
		    	if(elprimero){
		    		elprimero=false;
		    		order = "?order="+$scope.query.order;
		    	}else{
		    		order = "&order="+$scope.query.order;
		    	}
		    }
		    var limit ="";
		    if(!$scope.isNull($scope.query.limit) && !isNaN($scope.query.limit)){
		    	if(elprimero){
		    		elprimero=false;
		    		limit = "?limit="+$scope.query.limit;
		    	}else{
		    		limit = "&limit="+$scope.query.limit;
		    	}
		    }
		    var page="";
		    if(!$scope.isNull($scope.query.page) && !isNaN($scope.query.page)){
		    	if(elprimero){
		    		elprimero=false;
		    		page = "?page="+$scope.query.page;
		    	}else{
		    		page = "&page="+$scope.query.page;
		    	}
		    }		    
		    ///FILTRO
		    const keys = Object.keys($scope.filtro);
			console.log('Filtro Keys '+keys);
			var filtroparametro = "";
			Object.keys($scope.filtro).forEach(key => {
				console.log(key);
				const valor = $scope.filtro[key];
				console.log('Filtro Key '+key+' Valor '+valor);
				if(!$scope.isNull(valor) && $scope.isString(valor)){
					if(elprimero){
						elprimero=false;
						filtroparametro += "?"+key+"="+encodeURIComponent(valor);
					}else{
						filtroparametro += "&"+key+"="+encodeURIComponent(valor);
					}
				}
			});	 
		  return listatdUbicacionesUrl+order+limit+page+filtroparametro;
	  }
	  
	  $scope.edittdUbicaciones = function () {
		    $scope.nuevo = true;
		    var idubicaciones = $routeParams.idubicaciones;
		    if(idubicaciones){
		    	$scope.cargartdUbicaciones(idubicaciones);
		    }
		   ///CARGAR COMPLEMENTOS 
                $scope.loadListaCodpais();//SELECT
                $scope.loadListaMsUsuariosIduserModif();//SELECT
		  };
	  
	  $scope.filtro ={
          idubicaciones : null,
  idubicaciones: null,
  idevent: null,
  ubicacion: null,
  estado: null,
  
         estado: null
		}; 
	  
	  $scope.seteestado = function(eestado) {
			if($scope.filtro.estado === eestado){
				$scope.filtro.estado = null;
			}else{
				$scope.filtro.estado = eestado;
			}
			$scope.loadtdUbicacioness();
		};
	  
	  $scope.refrescarvista = function(ev){
			 var keyCode = ev.which || ev.keyCode;
			    if (keyCode === 13) {
			    	$scope.loadtdUbicacioness();
			    }
		};
	  
	  $scope.tdUbicacionesModelo = {
			idubicaciones : null,
			idevent: null,
			codpais: null,
			coddpto: null,
			codprov: null,
			coddist: null,
			ubicacion: null,
			fechaActivIni: null,
			fechaActivFin: null,
			iduserModif: null,
			
                        // ADICIONALES
	                ideventTxt: null,
iduserCreaTxt: null,
iduserModifTxt: null,

		    editopcion: 1
		};
	  
	  $scope.cleartdUbicaciones = function(){
		    $scope.tdUbicacionesModelo.idubicaciones = null;
		    $scope.tdUbicacionesModelo.idevent = null;
		    $scope.tdUbicacionesModelo.codpais = null;
		    $scope.tdUbicacionesModelo.coddpto = null;
		    $scope.tdUbicacionesModelo.codprov = null;
		    $scope.tdUbicacionesModelo.coddist = null;
		    $scope.tdUbicacionesModelo.ubicacion = null;
		    $scope.tdUbicacionesModelo.fechaActivIni = null;
		    $scope.tdUbicacionesModelo.fechaActivFin = null;
		    $scope.tdUbicacionesModelo.iduserModif = null;
		    
                    // ADICIONALES
	            $scope.tdUbicacionesModelo.ideventTxt = null;
$scope.tdUbicacionesModelo.iduserCreaTxt = null;
$scope.tdUbicacionesModelo.iduserModifTxt = null;

		    $scope.tdUbicacionesModelo.editopcion = 1;
	 } 
	 
	  $scope.setTdUbicacionesModelo = function(tdUbicacionesBk) {
		  $scope.tdUbicacionesModelo.idubicaciones = tdUbicacionesBk.idubicaciones;
			$scope.tdUbicacionesModelo.idevent = tdUbicacionesBk.idevent;
			$scope.tdUbicacionesModelo.codpais = tdUbicacionesBk.codpais;
			$scope.tdUbicacionesModelo.coddpto = tdUbicacionesBk.coddpto;
			$scope.tdUbicacionesModelo.codprov = tdUbicacionesBk.codprov;
			$scope.tdUbicacionesModelo.coddist = tdUbicacionesBk.coddist;
			$scope.tdUbicacionesModelo.ubicacion = tdUbicacionesBk.ubicacion;
			$scope.tdUbicacionesModelo.fechaActivIni = tdUbicacionesBk.fechaActivIni;
			$scope.tdUbicacionesModelo.fechaActivFin = tdUbicacionesBk.fechaActivFin;
			$scope.tdUbicacionesModelo.iduserModif = tdUbicacionesBk.iduserModif;
			
                        // ADICIONALES
	                $scope.tdUbicacionesModelo.ideventTxt = tdUbicacionesBk.ideventTxt;
$scope.tdUbicacionesModelo.iduserCreaTxt = tdUbicacionesBk.iduserCreaTxt;
$scope.tdUbicacionesModelo.iduserModifTxt = tdUbicacionesBk.iduserModifTxt;

			$scope.tdUbicacionesModelo.editopcion = tdUbicacionesBk.tdUbicacionesACL.editopcion;
		}
	  // ////////////////////////////////////////////////
	  $scope.editarTdUbicaciones = function(ev, tdUbicacionesBk) {		  
		    $scope.setTdUbicacionesModelo(tdUbicacionesBk);		  
			$location.url('/editar/' + $scope.tdUbicacionesModelo.idubicaciones);
			$scope.nuevo = false;
	  }
	  
	  $scope.nuevoTdUbicaciones = function() {
		    $scope.cleartdUbicaciones();
			$location.url('/nuevo');
			$scope.nuevo = true;
	  }
	  	  
	  $scope.cancelarTdUbicaciones = function() {
		    $scope.cleartdUbicaciones();
		    $location.url('/');
	  }
	  			  
	  $scope.salvarTdUbicaciones = function(ev){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson($scope.tdUbicacionesModelo);
		 			console.log("datainsert = "+datainsert);	
		        		$http.post(inserttdUbicacionesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
							var dato = res.data;

//		    				$scope.datos.push(dato); 
		    				$scope.total = $scope.datos.length;
		    				
		    				$scope.setTdUbicacionesModelo(dato);
		    				
		    				$mdDialog.show(
							         $mdDialog.alert()
							        .parent(angular.element(document.body))
							        .clickOutsideToClose(true)
							        .title('Guardar Ubicaciones Asigados A Los Eventos')
							        .textContent("DEL Ubicaciones Asigados A Los Eventos se guardó correctamente.")
							        .ariaLabel('ERROR')
							        .ok('OK')
							        .targetEvent(ev)
							    );
		    				
		    				$scope.nuevo = false;
						},
						function error(errResponse) {
				            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
				            var dato = errResponse.data;
				            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				            	$mdDialog.show(
								         $mdDialog.alert()
								        .parent(angular.element(document.body))
								        .clickOutsideToClose(true)
								        .title('Guardar Ubicaciones Asigados A Los Eventos')
								        .textContent(dato.message)
								        .ariaLabel('ERROR')
								        .ok('OK')
								        .targetEvent(ev)
								    );
				            }
				        });		
		        			        	
		        	ev.target.disabled = false;
			 };
			 
	   $scope.eliminartdUbicaciones = function(ev,tdUbicacionesBk){		
				    ev.target.disabled = true;
				    $scope.setTdUbicacionesModelo(tdUbicacionesBk);
				    var datainsert = angular.toJson($scope.tdUbicacionesModelo);
		 			console.log("datainsert = "+datainsert);	
	        		$http.post(eliminartdUbicacionesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
						var dato = res.data;
						var instrumentos = $scope.datos;
				        var index = $scope.datos.findIndex(obj => obj.idubicaciones === dato.idubicaciones);
						console.log("INDEX " + index);
				        if(instrumentos.length>index){
				        	instrumentos.splice(index, 1);
					        $scope.datos = instrumentos;
					        $scope.total = $scope.datos.length;
				        }	
					},
					function error(errResponse) {
			            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			            var dato = errResponse.data;
			            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
			            	$mdDialog.show(
					         $mdDialog.alert()
					        .parent(angular.element(document.body))
					        .clickOutsideToClose(true)
					        .title('Eliminar Ubicaciones Asigados A Los Eventos')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }
			        });			        			        	
		        	ev.target.disabled = false;
			 };	
			
		$scope.cargartdUbicaciones = function(idubicaciones){		
			var surl = editartdUbicacionesUrl+idubicaciones;
			$http.get(surl).then(function(res){
				var dato = res.data;
				$scope.setTdUbicacionesModelo(dato);
				$scope.nuevo = false;
				},
					function error(errResponse) {
			            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			            var dato = errResponse.data;
			            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
			            	$mdDialog.show(
					         $mdDialog.alert()
					        .parent(angular.element(document.body))
					        .clickOutsideToClose(true)
					        .title('Cargar Ubicaciones Asigados A Los Eventos')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
						   );
			            }			           
			        });			        			        	
			 };
			 
		$scope.showConfirm = function(ev, tdUbicacionesBk) {
				    var confirm = $mdDialog.confirm()
				      .title('Eliminar Ubicaciones Asigados A Los Eventos')
				      .textContent('Esta usted seguro de eliminar el registro?')
				      .ariaLabel('Lucky day')
				      .targetEvent(ev)
				      .ok('Si')
				      .cancel('No');

				    $mdDialog.show(confirm).then(function () {
				      $scope.status = 'SI';
				      $scope.eliminartdUbicaciones(ev, tdUbicacionesBk);
				    }, function () {
				      $scope.status = 'NO';
				    });
				  };		  
///ADICIONALES
//SELECT INI
        $scope.listaCodpais=[];
	$scope.loadListaCodpais=function(){
		$http.get(listapaiPkUrl).then(function(res){
			$scope.listaCodpais = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
        $scope.changeCodpais=function(){
          ///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
        }
        $scope.$watch('tdUbicacionesModelo.codpais', function (newValue, oldValue) {
		console.log('tdUbicacionesModelo.codpais ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});
//SELECT FIN                
//SELECT INI
        $scope.listaMsUsuariosIduserModif=[];
	$scope.loadListaMsUsuariosIduserModif=function(){
		$http.get(listaMsUsuariosidusuarioUrl).then(function(res){
			$scope.listaMsUsuariosIduserModif = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
        $scope.changeIduserModif=function(){
          ///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
        }
        $scope.$watch('tdUbicacionesModelo.iduserModif', function (newValue, oldValue) {
		console.log('tdUbicacionesModelo.iduserModif ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});
//SELECT FIN                

///FIN ADICIONALES			 			 
	// ////////////////////////////////////////////////////////////////
	
		    $scope.cancel = function() {
				$mdDialog.cancel();
		    };
		    
		 // Returns if a value is really a number
			$scope.isNumber  = function(value) {
			  return typeof value === 'number' && isFinite(value);
			  };	
				  
		    $scope.nuevo = ($scope.isNumber($scope.tdUbicacionesModelo.idubicaciones) && $scope.tdUbicacionesModelo.idubicaciones > 0);
			$scope.edit  = true;
			  
		    	    
			 $scope.toggle = function (item, list) {
			        var idx = list.indexOf(item);
			        if (idx > -1) {
			          list.splice(idx, 1);
			        }
			        else {
			          list.push(item);
			        }
			      };

			 $scope.exists = function (item, list) {
			        return list.indexOf(item) > -1;
			      };
			 
			$scope.filtroTdUbicaciones = function(toma){				
				const keys = Object.keys($scope.filtro);
				console.log('Filtro Keys '+keys);
				Object.keys($scope.filtro).forEach(key => {
					   console.log(key);
					   const valor = $scope.filtro[key];
		                  console.log('Filtro Key '+key+' Valor '+valor);
						  try{
						  if(typeof(valor)!='undefined' && typeof(toma[key])!='undefined'){
							  if(valor!=null && toma[key]!=null){
								  if(isString(valor)){
									  if(toma[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
										  return false;
									  };
								  }else if(isNumber(valor)){
									  const svalor = valor.toString();
									  const stoma = toma[key].toString();
									  if(stoma.indexOf(svalor)<=-1){
										  return false;
									  };
								  }else {
									  if(toma[key]===valor){
										  	return false;
										}
								  }
							  }else if($scope.filtro[key]!=null && toma[key]==null){
								  return false;
							  }
						  }else if(typeof($scope.filtro[key])!='undefined' && typeof(toma[key])=='undefined'){
							  return false;
						  }
						  }catch(e){
							  console.log("Error = "+e);	
						  }
					});
				return true;
			};
			
			// singers.sort(compareValues('band', 'desc'));
			 $scope.compareValues = function(key, order = 'asc') {
				  return function innerSort(a, b) {
				    if (!a.hasOwnProperty(key) || !b.hasOwnProperty(key)) {
				      // property doesn't exist on either object
				      return 0;
				    }
				    const varA = (typeof a[key] === 'string')
				      ? a[key].toUpperCase() : a[key];
				    const varB = (typeof b[key] === 'string')
				      ? b[key].toUpperCase() : b[key];

				    var comparison = 0;
				    if (varA > varB) {
				      comparison = 1;
				    } else if (varA < varB) {
				      comparison = -1;
				    }
				    return (
				      (order === 'desc') ? (comparison * -1) : comparison
				    );
				  };
				};
				
			  $scope.formatDate = function(dia){     
					    if(typeof(dia)=='undefined' || dia==null)
					    	return "";
				        var d = new Date(dia);
				        var dformat = [ d.getFullYear(),
				        	("00" + (d.getMonth() + 1)).slice(-2),
				        	("00" + d.getDate()).slice(-2)				        	
				                    ].join('-');
				        var dHour = [ ("00" + d.getHours()).slice(-2),
				        	("00" + d.getMinutes()).slice(-2),
				        	("00" + d.getSeconds()).slice(-2)				        	
				                    ].join(':');				        
				     return dformat+" "+dHour;
				  };	
					
			// /////////////////////////////////////////////////////////////////
			 $scope.estructuta = {
					 caracteresespeciales : 'ñq°!"#$%&¡¿?* {}()',
			         carcteresacentos: 'áéíóúÁÉÍÓÚ',
			         caracteresdelInput: null,
			 };
			 
			 
				// Returns if a value is a string
				  $scope.isString  = function(value) {
				  return typeof value === 'string' || value instanceof String;
				  };
							  
				// Returns if a value is an array
				  $scope.isArray  = function(value) {
				  return value && typeof value === 'object' && value.constructor === Array;
				  };
				  
				// Returns if a value is a function
				  $scope.isFunction  = function(value) {
				  return typeof value === 'function';
				  };
				  
				// Returns if a value is an object
				  $scope.isObject  = function(value) {
				  return value && typeof value === 'object' && value.constructor === Object;
				  };
				  
				// Returns if a value is null
				  $scope.isNull = function(value) {
				  return value === null;
				  };

				  // Returns if a value is undefined
				  $scope.isUndefined  = function(value) {
				  return typeof value === 'undefined';
				  };
				  
				// Returns if a value is a boolean
				  $scope.isBoolean  = function(value) {
				  return typeof value === 'boolean';
				  };
				  
				// Returns if a value is a regexp
				  $scope.isRegExp  = function(value) {
				  return value && typeof value === 'object' && value.constructor === RegExp;
				  };
				  
				// Returns if value is an error object
				  $scope.isError  = function(value) {
				  return value instanceof Error && typeof value.message !== 'undefined';
				  };
				  
				// Returns if value is a date object
				  $scope.isDate  = function(value) {
				  return value instanceof Date;
				  };
				  
				// Returns if a Symbol
				  $scope.isSymbol  = function(value) {
				  return typeof value === 'symbol';
				  };
				  
				  $scope.invert_key_value = function(obj) {
					    var result = {};
					    var keys = $scope._keys(obj);
					    for (var i = 0, length = keys.length; i < length; i++) {
					      result[obj[keys[i]]] = keys[i];
					    }
					    return result;
					  }
				  
				  $scope._keys = function(obj) 
					  {
					    if (!$scope.isObject(obj)) return [];
					    if (Object.keys) return Object.keys(obj);
					    var keys = [];
					    for (var key in obj) if (_.has(obj, key)) keys.push(key);
					    return keys;
					  }
					
				  $scope.isObject = function(obj) 
					 {
					    var type = typeof obj;
					    return type === 'function' || type === 'object' && !!obj;
					  }
                           // $scope.promise = $timeout(function () {	 
		           // }, 500);
}]);
