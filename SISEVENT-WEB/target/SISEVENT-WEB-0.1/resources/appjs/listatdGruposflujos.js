var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdGruposflujosUrl = contexto+"/rs/ctrltdGruposflujos/listatdGruposflujos";
var inserttdGruposflujosUrl = contexto+"/rs/ctrltdGruposflujos/salvartdGruposflujos";
var eliminartdGruposflujosUrl = contexto+"/rs/ctrltdGruposflujos/eliminartdGruposflujos";
var editartdGruposflujosUrl = contexto+"/rs/ctrltdGruposflujos/editartdGruposflujos/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	  $routeProvider
	  .when("/editar/:idgruposflujos", {
		  templateUrl : "adminis/editartdGruposflujos.html",
		  controller : "ctrlListatdGruposflujos"
	  })
	  .when("/nuevo", {
		  templateUrl : "adminis/editartdGruposflujos.html",
		  controller : "ctrlListatdGruposflujos"
	  })
	  .otherwise({
		  templateUrl : "adminis/vertdGruposflujos.html",
		  controller : "ctrlListatdGruposflujos"  
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

myapp.controller('ctrlListatdGruposflujos', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';
	 
	 $scope.limitOptions = [100, 500, 1000, 5000];
	 $scope.query = {
			    order: 'idgruposflujos',
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
	  $scope.loadtdGruposflujoss();
	 };
	 
	 $scope.logItem = function (item) {
	  console.log(item.name, 'was selected');
	 };
	 
	 $scope.logPagination = function (page, limit) {
	  console.log('page: ', page);
	  console.log('limit: ', limit);
	  $scope.loadtdGruposflujoss();
         };
     $scope.creatdGruposflujos = false;
    // ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadtdGruposflujoss = function () {
	    //$scope.promise = $timeout(function () {
	    	var surl = $scope.getURL();	    
	    	$scope.promise = $http.get(surl).then(function(res){
	    		 $scope.datos = res.data.data;
	    		 if(res.data.contador>0)
	    		 $scope.total = res.data.contador;
	    		 var tiempoenBD = res.data.tiempoenBD;
	    		 var tiempoenproceso = res.data.tiempoenproceso;
	    		 $scope.creatdGruposflujos = res.data.creamodifica;
	    		 console.log("data " +$scope.datos.length+" DE "+ $scope.total);
	    		 console.log("Tiempo respuesta BD tdGruposflujos " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
				},
				function error(errResponse) {
		            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		            var dato = errResponse.data;
		            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
		            	$mdDialog.show(
						         $mdDialog.alert()
						        .parent(angular.element(document.body))
						        .clickOutsideToClose(true)
						        .title('Lista de  Grupo')
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
		  return listatdGruposflujosUrl+order+limit+page+filtroparametro;
	  }
	  
	  $scope.edittdGruposflujos = function () {
		    $scope.nuevo = true;
		    var idgruposflujos = $routeParams.idgruposflujos;
		    if(idgruposflujos){
		    	$scope.cargartdGruposflujos(idgruposflujos);
		    }
		   ///CARGAR COMPLEMENTOS 
		  };
	  
	  $scope.filtro ={
          idgruposflujos : null,
  idtareas: null,
  idunidadDestino: null,
  iduserDestino: null,
  
         estado: null
		}; 
	  
	  $scope.seteestado = function(eestado) {
			if($scope.filtro.estado === eestado){
				$scope.filtro.estado = null;
			}else{
				$scope.filtro.estado = eestado;
			}
			$scope.loadtdGruposflujoss();
		};
	  
	  $scope.refrescarvista = function(ev){
			 var keyCode = ev.which || ev.keyCode;
			    if (keyCode === 13) {
			    	$scope.loadtdGruposflujoss();
			    }
		};
	  
	  $scope.tdGruposflujosModelo = {
			idgruposflujos : null,
			idtareas: null,
			idunidadDestino: null,
			iduserDestino: null,
			observacion: null,
			observacionHtml: null,
			tiempoestadia: null,
			correosnotif: null,
			idgrupo: null,
			
                        // ADICIONALES
	                idtareasTxt: null,
idunidadDestinoTxt: null,
iduserCreaTxt: null,
iduserModifTxt: null,
idgrupoTxt: null,

		    editopcion: 1
		};
	  
	  $scope.cleartdGruposflujos = function(){
		    $scope.tdGruposflujosModelo.idgruposflujos = null;
		    $scope.tdGruposflujosModelo.idtareas = null;
		    $scope.tdGruposflujosModelo.idunidadDestino = null;
		    $scope.tdGruposflujosModelo.iduserDestino = null;
		    $scope.tdGruposflujosModelo.observacion = null;
		    $scope.tdGruposflujosModelo.observacionHtml = null;
		    $scope.tdGruposflujosModelo.tiempoestadia = null;
		    $scope.tdGruposflujosModelo.correosnotif = null;
		    $scope.tdGruposflujosModelo.idgrupo = null;
		    
                    // ADICIONALES
	            $scope.tdGruposflujosModelo.idtareasTxt = null;
$scope.tdGruposflujosModelo.idunidadDestinoTxt = null;
$scope.tdGruposflujosModelo.iduserCreaTxt = null;
$scope.tdGruposflujosModelo.iduserModifTxt = null;
$scope.tdGruposflujosModelo.idgrupoTxt = null;

		    $scope.tdGruposflujosModelo.editopcion = 1;
	 } 
	 
	  $scope.setTdGruposflujosModelo = function(tdGruposflujosBk) {
		  $scope.tdGruposflujosModelo.idgruposflujos = tdGruposflujosBk.idgruposflujos;
			$scope.tdGruposflujosModelo.idtareas = tdGruposflujosBk.idtareas;
			$scope.tdGruposflujosModelo.idunidadDestino = tdGruposflujosBk.idunidadDestino;
			$scope.tdGruposflujosModelo.iduserDestino = tdGruposflujosBk.iduserDestino;
			$scope.tdGruposflujosModelo.observacion = tdGruposflujosBk.observacion;
			$scope.tdGruposflujosModelo.observacionHtml = tdGruposflujosBk.observacionHtml;
			$scope.tdGruposflujosModelo.tiempoestadia = tdGruposflujosBk.tiempoestadia;
			$scope.tdGruposflujosModelo.correosnotif = tdGruposflujosBk.correosnotif;
			$scope.tdGruposflujosModelo.idgrupo = tdGruposflujosBk.idgrupo;
			
                        // ADICIONALES
	                $scope.tdGruposflujosModelo.idtareasTxt = tdGruposflujosBk.idtareasTxt;
$scope.tdGruposflujosModelo.idunidadDestinoTxt = tdGruposflujosBk.idunidadDestinoTxt;
$scope.tdGruposflujosModelo.iduserCreaTxt = tdGruposflujosBk.iduserCreaTxt;
$scope.tdGruposflujosModelo.iduserModifTxt = tdGruposflujosBk.iduserModifTxt;
$scope.tdGruposflujosModelo.idgrupoTxt = tdGruposflujosBk.idgrupoTxt;

			$scope.tdGruposflujosModelo.editopcion = tdGruposflujosBk.tdGruposflujosACL.editopcion;
		}
	  // ////////////////////////////////////////////////
	  $scope.editarTdGruposflujos = function(ev, tdGruposflujosBk) {		  
		    $scope.setTdGruposflujosModelo(tdGruposflujosBk);		  
			$location.url('/editar/' + $scope.tdGruposflujosModelo.idgruposflujos);
			$scope.nuevo = false;
	  }
	  
	  $scope.nuevoTdGruposflujos = function() {
		    $scope.cleartdGruposflujos();
			$location.url('/nuevo');
			$scope.nuevo = true;
	  }
	  	  
	  $scope.cancelarTdGruposflujos = function() {
		    $scope.cleartdGruposflujos();
		    $location.url('/');
	  }
	  			  
	  $scope.salvarTdGruposflujos = function(ev){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson($scope.tdGruposflujosModelo);
		 			console.log("datainsert = "+datainsert);	
		        		$http.post(inserttdGruposflujosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
							var dato = res.data;

//		    				$scope.datos.push(dato); 
		    				$scope.total = $scope.datos.length;
		    				
		    				$scope.setTdGruposflujosModelo(dato);
		    				
		    				$mdDialog.show(
							         $mdDialog.alert()
							        .parent(angular.element(document.body))
							        .clickOutsideToClose(true)
							        .title('Guardar  Grupo')
							        .textContent(" EL  Grupo se guardó correctamente.")
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
								        .title('Guardar  Grupo')
								        .textContent(dato.message)
								        .ariaLabel('ERROR')
								        .ok('OK')
								        .targetEvent(ev)
								    );
				            }
				        });		
		        			        	
		        	ev.target.disabled = false;
			 };
			 
	   $scope.eliminartdGruposflujos = function(ev,tdGruposflujosBk){		
				    ev.target.disabled = true;
				    $scope.setTdGruposflujosModelo(tdGruposflujosBk);
				    var datainsert = angular.toJson($scope.tdGruposflujosModelo);
		 			console.log("datainsert = "+datainsert);	
	        		$http.post(eliminartdGruposflujosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
						var dato = res.data;
						var instrumentos = $scope.datos;
				        var index = $scope.datos.findIndex(obj => obj.idgruposflujos === dato.idgruposflujos);
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
					        .title('Eliminar  Grupo')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }
			        });			        			        	
		        	ev.target.disabled = false;
			 };	
			
		$scope.cargartdGruposflujos = function(idgruposflujos){		
			var surl = editartdGruposflujosUrl+idgruposflujos;
			$http.get(surl).then(function(res){
				var dato = res.data;
				$scope.setTdGruposflujosModelo(dato);
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
					        .title('Cargar  Grupo')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
						   );
			            }			           
			        });			        			        	
			 };
			 
		$scope.showConfirm = function(ev, tdGruposflujosBk) {
				    var confirm = $mdDialog.confirm()
				      .title('Eliminar  Grupo')
				      .textContent('Esta usted seguro de eliminar el registro?')
				      .ariaLabel('Lucky day')
				      .targetEvent(ev)
				      .ok('Si')
				      .cancel('No');

				    $mdDialog.show(confirm).then(function () {
				      $scope.status = 'SI';
				      $scope.eliminartdGruposflujos(ev, tdGruposflujosBk);
				    }, function () {
				      $scope.status = 'NO';
				    });
				  };		  
///ADICIONALES

///FIN ADICIONALES			 			 
	// ////////////////////////////////////////////////////////////////
	
		    $scope.cancel = function() {
				$mdDialog.cancel();
		    };
		    
		 // Returns if a value is really a number
			$scope.isNumber  = function(value) {
			  return typeof value === 'number' && isFinite(value);
			  };	
				  
		    $scope.nuevo = ($scope.isNumber($scope.tdGruposflujosModelo.idgruposflujos) && $scope.tdGruposflujosModelo.idgruposflujos > 0);
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
			 
			$scope.filtroTdGruposflujos = function(toma){				
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
