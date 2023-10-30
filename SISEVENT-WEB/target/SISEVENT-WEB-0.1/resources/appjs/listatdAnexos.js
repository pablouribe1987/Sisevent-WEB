var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdAnexosUrl = contexto+"/rs/ctrltdAnexos/listatdAnexos";
var inserttdAnexosUrl = contexto+"/rs/ctrltdAnexos/salvartdAnexos";
var eliminartdAnexosUrl = contexto+"/rs/ctrltdAnexos/eliminartdAnexos";
var editartdAnexosUrl = contexto+"/rs/ctrltdAnexos/editartdAnexos/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	  $routeProvider
	  .when("/editar/:idanexo", {
		  templateUrl : "adminis/editartdAnexos.html",
		  controller : "ctrlListatdAnexos"
	  })
	  .when("/nuevo", {
		  templateUrl : "adminis/editartdAnexos.html",
		  controller : "ctrlListatdAnexos"
	  })
	  .otherwise({
		  templateUrl : "adminis/vertdAnexos.html",
		  controller : "ctrlListatdAnexos"  
	  });
	});

myapp.controller('ctrlListatdAnexos', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';
	 
	 $scope.limitOptions = [100, 500, 1000, 5000];
	 $scope.query = {
			    order: 'idanexo',
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
	  $scope.loadtdAnexos();
	 };
	 
	 $scope.logItem = function (item) {
	  console.log(item.name, 'was selected');
	 };
	 
	 $scope.logPagination = function (page, limit) {
	  console.log('page: ', page);
	  console.log('limit: ', limit);
	  $scope.loadtdAnexos();
     };
     
    // ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadtdAnexoss = function () {
	    //$scope.promise = $timeout(function () {
	    	var surl = $scope.getURL();	    
	    	$scope.promise = $http.get(surl).then(function(res){
	    		 $scope.datos = res.data.data;
	    		 if(res.data.contador>0)
	    		 $scope.total = res.data.contador;
	    		 var tiempoenBD = res.data.tiempoenBD;
	    		 var tiempoenproceso = res.data.tiempoenproceso;
	    		 console.log("data " +$scope.datos.length+" DE "+ $scope.total);
	    		 console.log("Tiempo respuesta BD " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
				},
				function error(errResponse) {
		            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		            var dato = errResponse.data;
		            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
		            	$mdDialog.show(
						         $mdDialog.alert()
						        .parent(angular.element(document.body))
						        .clickOutsideToClose(true)
						        .title('Lista de  Anex')
						        .textContent(dato.message)
						        .ariaLabel('ERROR')
						        .ok('OK')
						        .targetEvent(ev)
						    );
		            }
		        });			 
	     //}, 500);
	  };
	 	  
	  $scope.getURL=function(){
		    var elprimero = true;
		    var order = "" 
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
		    var idsacc = ""
		    	if(!$scope.isNull($scope.filtro.idsacc) && $scope.isString($scope.filtro.idsacc)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      idsacc = "?idsacc="+encodeURIComponent($scope.filtro.idsacc);
			    	   }else{
			    		   idsacc = "&idsacc="+encodeURIComponent($scope.filtro.idsacc);
			    	   }
			    	}
			var filename = ""
		    	if(!$scope.isNull($scope.filtro.filename) && $scope.isString($scope.filtro.filename)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      filename = "?filename="+encodeURIComponent($scope.filtro.filename);
			    	   }else{
			    		   filename = "&filename="+encodeURIComponent($scope.filtro.filename);
			    	   }
			    	}
			var idflujo = ""
		    	if(!$scope.isNull($scope.filtro.idflujo) && $scope.isString($scope.filtro.idflujo)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      idflujo = "?idflujo="+encodeURIComponent($scope.filtro.idflujo);
			    	   }else{
			    		   idflujo = "&idflujo="+encodeURIComponent($scope.filtro.idflujo);
			    	   }
			    	}
			 
		  return listatdAnexosUrl+order+limit+page+idsacc+filename+idflujo;
	  }
	  
	  $scope.loadtdAnexos = function () {
		    $scope.nuevo = true;
		    var idanexo = $routeParams.idanexo;
		    if(idanexo){
		    	$scope.cargartdAnexos(idanexo);
		    }
		   ///CARGAR COMPLEMENTOS
		    
		  };
	  
	  $scope.filtro ={
  idsacc: null,
  filename: null,
  idflujo: null,
  
		}; 
	  
	  $scope.refrescarvista = function(ev){
			 var keyCode = ev.which || ev.keyCode;
			    if (keyCode === 13) {
			    	$scope.loadtdAnexos();
			    }
		};
	  
	  $scope.tdAnexosModelo = {
			idanexo : null,
			idsacc: null,
			
			filename: null,
			
			filenameoriginal: null,
						
			lastmodified: null,
			
			tamanio: null,
			
			tipo: null,
			
			idflujo: null,
			
			
		    editopcion: 1
		};
	  
	  $scope.cleartdAnexos = function(){
		    $scope.tdAnexosModelo.idanexo = null;
		    $scope.tdAnexosModelo.idsacc = null;
		    
			$scope.tdAnexosModelo.filename = null;
		    
			$scope.tdAnexosModelo.filenameoriginal = null;
		    
			
			
			
			
			
			
			
			$scope.tdAnexosModelo.lastmodified = null;
		    
			$scope.tdAnexosModelo.tamanio = null;
		    
			$scope.tdAnexosModelo.tipo = null;
		    
			$scope.tdAnexosModelo.idflujo = null;
		    
			
		    $scope.tdAnexosModelo.editopcion = 1;
	 } 
	  // ////////////////////////////////////////////////
	  $scope.editarTdAnexos = function(ev, tdAnexosBk) {
			$scope.tdAnexosModelo.idanexo = tdAnexosBk.idanexo;
			$scope.tdAnexosModelo.idsacc = tdAnexosBk.idsacc;
			
			$scope.tdAnexosModelo.filename = tdAnexosBk.filename;
			
			$scope.tdAnexosModelo.filenameoriginal = tdAnexosBk.filenameoriginal;
			
			
			
			
			
			
			
			
			$scope.tdAnexosModelo.lastmodified = tdAnexosBk.lastmodified;
			
			$scope.tdAnexosModelo.tamanio = tdAnexosBk.tamanio;
			
			$scope.tdAnexosModelo.tipo = tdAnexosBk.tipo;
			
			$scope.tdAnexosModelo.idflujo = tdAnexosBk.idflujo;
			
			
			$scope.tdAnexosModelo.editopcion = tdAnexosBk.editopcion;
			$location.url('/editar/' + $scope.tdAnexosModelo.idanexo);
			$scope.nuevo = false;
	  }
	  
	  $scope.nuevoTdAnexos = function() {
		    $scope.cleartdAnexos();
			$location.url('/nuevo');
			$scope.nuevo = true;
	  }
	  	  
	  $scope.cancelarTdAnexos = function() {
		    $scope.cleartdAnexos();
		    $location.url('/');
	  }
	  			  
	  $scope.salvarTdAnexos = function(ev){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson($scope.tdAnexosModelo);
		 			console.log("datainsert = "+datainsert);	
		        		$http.post(inserttdAnexosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
							var dato = res.data;

		    				$scope.datos.push(dato); 
		    				$scope.total = $scope.datos.length;
		    				
		    				$scope.tdAnexosModelo.idanexo = dato.idanexo;
		    				$scope.tdAnexosModelo.idsacc = dato.idsacc;
		    				
		    				$scope.tdAnexosModelo.filename = dato.filename;
		    				
		    				$scope.tdAnexosModelo.filenameoriginal = dato.filenameoriginal;
		    				
		    				
		    				
		    				
		    				
		    				
		    				
		    				
		    				$scope.tdAnexosModelo.lastmodified = dato.lastmodified;
		    				
		    				$scope.tdAnexosModelo.tamanio = dato.tamanio;
		    				
		    				$scope.tdAnexosModelo.tipo = dato.tipo;
		    				
		    				$scope.tdAnexosModelo.idflujo = dato.idflujo;
		    				
		    				
		    				$scope.tdAnexosModelo.editopcion = dato.editopcion;

						},
						function error(errResponse) {
				            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
				            var dato = errResponse.data;
				            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				            	$mdDialog.show(
								         $mdDialog.alert()
								        .parent(angular.element(document.body))
								        .clickOutsideToClose(true)
								        .title('Nuevo  Anex')
								        .textContent(dato.message)
								        .ariaLabel('ERROR')
								        .ok('OK')
								        .targetEvent(ev)
								    );
				            }
				        });		
		        			        	
		        	ev.target.disabled = false;
			 };
			 
	   $scope.eliminartdAnexos = function(ev,tdAnexosBk){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson(tdAnexosBk);
		 			console.log("datainsert = "+datainsert);	
	        		$http.post(eliminartdAnexosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
						var dato = res.data;
						var instrumentos = $scope.datos;
				        var index = $scope.datos.findIndex(obj => obj.idanexo === dato.idanexo);
						console.log("INDEX " + index);
				        if(instrumentos.length>index){
				        	instrumentos.splice(index, 1);
					        $$scope.datos = instrumentos;
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
					        .title('Eliminar  Anex')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }
			        });			        			        	
		        	ev.target.disabled = false;
			 };	
			
		$scope.cargartdAnexos = function(idanexo){		
			var surl = editartdAnexosUrl+idanexo;
			$http.get(surl).then(function(res){
				var dato = res.data;
				$scope.tdAnexosModelo.idanexo = dato.idanexo;
				$scope.tdAnexosModelo.idsacc = dato.idsacc;
				
				$scope.tdAnexosModelo.filename = dato.filename;
				
				$scope.tdAnexosModelo.filenameoriginal = dato.filenameoriginal;
				
				
				
				
				
				
				
				
				$scope.tdAnexosModelo.lastmodified = dato.lastmodified;
				
				$scope.tdAnexosModelo.tamanio = dato.tamanio;
				
				$scope.tdAnexosModelo.tipo = dato.tipo;
				
				$scope.tdAnexosModelo.idflujo = dato.idflujo;
				
				
				$scope.tdAnexosModelo.editopcion = dato.editopcion;
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
					        .title('Eliminar  Anex')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }			           
			        });			        			        	
			 };
			 
		$scope.showConfirm = function(ev, tdAnexosBk) {
				    var confirm = $mdDialog.confirm()
				      .title('Eliminar  Anex')
				      .textContent('Esta usted seguro de eliminar el registro?')
				      .ariaLabel('Lucky day')
				      .targetEvent(ev)
				      .ok('Si')
				      .cancel('No');

				    $mdDialog.show(confirm).then(function () {
				      $scope.status = 'SI';
				      $scope.eliminartdAnexos(ev, tdAnexosBk);
				    }, function () {
				      $scope.status = 'NO';
				    });
				  };		  
			 
	// ////////////////////////////////////////////////////////////////
	
		    $scope.cancel = function() {
				$mdDialog.cancel();
		    };
		    
		 // Returns if a value is really a number
			$scope.isNumber  = function(value) {
			  return typeof value === 'number' && isFinite(value);
			  };	
				  
		    $scope.nuevo = ($scope.isNumber($scope.tdAnexosModelo.idanexo) && $scope.tdAnexosModelo.idanexo > 0);
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
			 
			$scope.filtroTdAnexos = function(toma){				
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
}]);
