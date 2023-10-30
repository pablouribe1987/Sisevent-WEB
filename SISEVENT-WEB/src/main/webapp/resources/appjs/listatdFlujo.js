var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdFlujoUrl = contexto+"/rs/ctrltdFlujo/listatdFlujo";
var inserttdFlujoUrl = contexto+"/rs/ctrltdFlujo/salvartdFlujo";
var eliminartdFlujoUrl = contexto+"/rs/ctrltdFlujo/eliminartdFlujo";
var editartdFlujoUrl = contexto+"/rs/ctrltdFlujo/editartdFlujo/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	  $routeProvider
	  .when("/editar/:idflujo", {
		  templateUrl : "adminis/editartdFlujo.html",
		  controller : "ctrlListatdFlujo"
	  })
	  .when("/nuevo", {
		  templateUrl : "adminis/editartdFlujo.html",
		  controller : "ctrlListatdFlujo"
	  })
	  .otherwise({
		  templateUrl : "adminis/vertdFlujo.html",
		  controller : "ctrlListatdFlujo"  
	  });
	});

myapp.controller('ctrlListatdFlujo', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';
	 
	 $scope.limitOptions = [100, 500, 1000, 5000];
	 $scope.query = {
			    order: 'idflujo',
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
	  $scope.loadtdFlujo();
	 };
	 
	 $scope.logItem = function (item) {
	  console.log(item.name, 'was selected');
	 };
	 
	 $scope.logPagination = function (page, limit) {
	  console.log('page: ', page);
	  console.log('limit: ', limit);
	  $scope.loadtdFlujo();
     };
     
    // ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadtdFlujo = function () {
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
						        .title('Lista de  Movimiento')
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
		    var idflujo = ""
		    	if(!$scope.isNull($scope.filtro.idflujo) && $scope.isString($scope.filtro.idflujo)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      idflujo = "?idflujo="+encodeURIComponent($scope.filtro.idflujo);
			    	   }else{
			    		   idflujo = "&idflujo="+encodeURIComponent($scope.filtro.idflujo);
			    	   }
			    	}
			var idflujopadre = ""
		    	if(!$scope.isNull($scope.filtro.idflujopadre) && $scope.isString($scope.filtro.idflujopadre)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      idflujopadre = "?idflujopadre="+encodeURIComponent($scope.filtro.idflujopadre);
			    	   }else{
			    		   idflujopadre = "&idflujopadre="+encodeURIComponent($scope.filtro.idflujopadre);
			    	   }
			    	}
			var idsacc = ""
		    	if(!$scope.isNull($scope.filtro.idsacc) && $scope.isString($scope.filtro.idsacc)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      idsacc = "?idsacc="+encodeURIComponent($scope.filtro.idsacc);
			    	   }else{
			    		   idsacc = "&idsacc="+encodeURIComponent($scope.filtro.idsacc);
			    	   }
			    	}
			var estado = ""
		    	if(!$scope.isNull($scope.filtro.estado) && $scope.isString($scope.filtro.estado)){
			    	  if(elprimero){
			    	      elprimero=false;
			    	      estado = "?estado="+encodeURIComponent($scope.filtro.estado);
			    	   }else{
			    		   estado = "&estado="+encodeURIComponent($scope.filtro.estado);
			    	   }
			    	}
			 
		  return listatdFlujoUrl+order+limit+page+idflujo+idflujopadre+idsacc+estado;
	  }
	  
	  $scope.loadmsUsuario = function () {
		    $scope.nuevo = true;
		    var idflujo = $routeParams.idflujo;
		    if(idflujo){
		    	$scope.cargartdFlujo(idflujo);
		    }
		   ///CARGAR COMPLEMENTOS
		    
		  };
	  
	  $scope.filtro ={
  idflujo: null,
  idflujopadre: null,
  idsacc: null,
  estado: null,
  
		}; 
	  
	  $scope.refrescarvista = function(ev){
			 var keyCode = ev.which || ev.keyCode;
			    if (keyCode === 13) {
			    	$scope.loadtdFlujo();
			    }
		};
	  
	  $scope.tdFlujoModelo = {
			idflujo : null,
			idflujopadre: null,
			idsacc: null,
			fechaDerivacion: null,
			idunidadDeriva: null,
			iduserDeriva: null,
			fechaRecepcion: null,
			idunidadRecepciona: null,
			iduserRecepciona: null,
			idunidadDestino: null,
			iduserDestino: null,
			idunidadAtiende: null,
			iduserAtiende: null,
			fechaAtencion: null,
			observacion: null,
			tiempoestadia: null,
			fechaCrea: null,
			fechaModif: null,
			iduserCrea: null,
			iduserModif: null,
			estado: null,
			rmtaddress: null,
			rmtaddressrst: null,
			
		    editopcion: 1
		};
	  
	  $scope.cleartdFlujo = function(){
		    $scope.tdFlujoModelo.idflujo = null;
		    $scope.tdFlujoModelo.idflujopadre = null;
			$scope.tdFlujoModelo.idsacc = null;
			$scope.tdFlujoModelo.fechaDerivacion = null;
			$scope.tdFlujoModelo.idunidadDeriva = null;
			$scope.tdFlujoModelo.iduserDeriva = null;
			$scope.tdFlujoModelo.fechaRecepcion = null;
			$scope.tdFlujoModelo.idunidadRecepciona = null;
			$scope.tdFlujoModelo.iduserRecepciona = null;
			$scope.tdFlujoModelo.idunidadDestino = null;
			$scope.tdFlujoModelo.iduserDestino = null;
			$scope.tdFlujoModelo.idunidadAtiende = null;
			$scope.tdFlujoModelo.iduserAtiende = null;
			$scope.tdFlujoModelo.fechaAtencion = null;
			$scope.tdFlujoModelo.observacion = null;
			$scope.tdFlujoModelo.tiempoestadia = null;
			$scope.tdFlujoModelo.fechaCrea = null;
			$scope.tdFlujoModelo.fechaModif = null;
			$scope.tdFlujoModelo.iduserCrea = null;
			$scope.tdFlujoModelo.iduserModif = null;
			$scope.tdFlujoModelo.estado = null;
			$scope.tdFlujoModelo.rmtaddress = null;
			$scope.tdFlujoModelo.rmtaddressrst = null;
			
		    $scope.tdFlujoModelo.editopcion = 1;
	 } 
	  // ////////////////////////////////////////////////
	  $scope.editarTdFlujo = function(ev, tdFlujoBk) {
			$scope.tdFlujoModelo.idflujo = tdFlujoBk.idflujo;
			$scope.tdFlujoModelo.idflujopadre = tdFlujoBk.idflujopadre;
			$scope.tdFlujoModelo.idsacc = tdFlujoBk.idsacc;
			$scope.tdFlujoModelo.fechaDerivacion = tdFlujoBk.fechaDerivacion;
			$scope.tdFlujoModelo.idunidadDeriva = tdFlujoBk.idunidadDeriva;
			$scope.tdFlujoModelo.iduserDeriva = tdFlujoBk.iduserDeriva;
			$scope.tdFlujoModelo.fechaRecepcion = tdFlujoBk.fechaRecepcion;
			$scope.tdFlujoModelo.idunidadRecepciona = tdFlujoBk.idunidadRecepciona;
			$scope.tdFlujoModelo.iduserRecepciona = tdFlujoBk.iduserRecepciona;
			$scope.tdFlujoModelo.idunidadDestino = tdFlujoBk.idunidadDestino;
			$scope.tdFlujoModelo.iduserDestino = tdFlujoBk.iduserDestino;
			$scope.tdFlujoModelo.idunidadAtiende = tdFlujoBk.idunidadAtiende;
			$scope.tdFlujoModelo.iduserAtiende = tdFlujoBk.iduserAtiende;
			$scope.tdFlujoModelo.fechaAtencion = tdFlujoBk.fechaAtencion;
			$scope.tdFlujoModelo.observacion = tdFlujoBk.observacion;
			$scope.tdFlujoModelo.tiempoestadia = tdFlujoBk.tiempoestadia;
			$scope.tdFlujoModelo.fechaCrea = tdFlujoBk.fechaCrea;
			$scope.tdFlujoModelo.fechaModif = tdFlujoBk.fechaModif;
			$scope.tdFlujoModelo.iduserCrea = tdFlujoBk.iduserCrea;
			$scope.tdFlujoModelo.iduserModif = tdFlujoBk.iduserModif;
			$scope.tdFlujoModelo.estado = tdFlujoBk.estado;
			$scope.tdFlujoModelo.rmtaddress = tdFlujoBk.rmtaddress;
			$scope.tdFlujoModelo.rmtaddressrst = tdFlujoBk.rmtaddressrst;
			
			$scope.tdFlujoModelo.editopcion = tdFlujoBk.editopcion;
			$location.url('/editar/' + $scope.tdFlujoModelo.idflujo);
			$scope.nuevo = false;
	  }
	  
	  $scope.nuevoTdFlujo = function() {
		    $scope.cleartdFlujo();
			$location.url('/nuevo');
			$scope.nuevo = true;
	  }
	  	  
	  $scope.cancelarTdFlujo = function() {
		    $scope.cleartdFlujo();
		    $location.url('/');
	  }
	  			  
	  $scope.salvarTdFlujo = function(ev){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson($scope.tdFlujoModelo);
		 			console.log("datainsert = "+datainsert);	
		        		$http.post(inserttdFlujoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
							var dato = res.data;

		    				$scope.datos.push(dato); 
		    				$scope.total = $scope.datos.length;
		    				
		    				$scope.tdFlujoModelo.idflujo = dato.idflujo;
		    				$scope.tdFlujoModelo.idflujopadre = dato.idflujopadre;
		    				$scope.tdFlujoModelo.idsacc = dato.idsacc;
		    				$scope.tdFlujoModelo.fechaDerivacion = dato.fechaDerivacion;
		    				$scope.tdFlujoModelo.idunidadDeriva = dato.idunidadDeriva;
		    				$scope.tdFlujoModelo.iduserDeriva = dato.iduserDeriva;
		    				$scope.tdFlujoModelo.fechaRecepcion = dato.fechaRecepcion;
		    				$scope.tdFlujoModelo.idunidadRecepciona = dato.idunidadRecepciona;
		    				$scope.tdFlujoModelo.iduserRecepciona = dato.iduserRecepciona;
		    				$scope.tdFlujoModelo.idunidadDestino = dato.idunidadDestino;
		    				$scope.tdFlujoModelo.iduserDestino = dato.iduserDestino;
		    				$scope.tdFlujoModelo.idunidadAtiende = dato.idunidadAtiende;
		    				$scope.tdFlujoModelo.iduserAtiende = dato.iduserAtiende;
		    				$scope.tdFlujoModelo.fechaAtencion = dato.fechaAtencion;
		    				$scope.tdFlujoModelo.observacion = dato.observacion;
		    				$scope.tdFlujoModelo.tiempoestadia = dato.tiempoestadia;
		    				$scope.tdFlujoModelo.fechaCrea = dato.fechaCrea;
		    				$scope.tdFlujoModelo.fechaModif = dato.fechaModif;
		    				$scope.tdFlujoModelo.iduserCrea = dato.iduserCrea;
		    				$scope.tdFlujoModelo.iduserModif = dato.iduserModif;
		    				$scope.tdFlujoModelo.estado = dato.estado;
		    				$scope.tdFlujoModelo.rmtaddress = dato.rmtaddress;
		    				$scope.tdFlujoModelo.rmtaddressrst = dato.rmtaddressrst;
		    				
		    				$scope.tdFlujoModelo.editopcion = dato.editopcion;

						},
						function error(errResponse) {
				            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
				            var dato = errResponse.data;
				            if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				            	$mdDialog.show(
								         $mdDialog.alert()
								        .parent(angular.element(document.body))
								        .clickOutsideToClose(true)
								        .title('Nuevo  Movimiento')
								        .textContent(dato.message)
								        .ariaLabel('ERROR')
								        .ok('OK')
								        .targetEvent(ev)
								    );
				            }
				        });		
		        			        	
		        	ev.target.disabled = false;
			 };
			 
	   $scope.eliminartdFlujo = function(ev,tdFlujoBk){		
				    ev.target.disabled = true;
				    var datainsert = angular.toJson(tdFlujoBk);
		 			console.log("datainsert = "+datainsert);	
	        		$http.post(eliminartdFlujoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
						var dato = res.data;
						var instrumentos = $scope.datos;
				        var index = $scope.datos.findIndex(obj => obj.idflujo === dato.idflujo);
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
					        .title('Eliminar  Movimiento')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }
			        });			        			        	
		        	ev.target.disabled = false;
			 };	
			
		$scope.cargartdFlujo = function(idflujo){		
			var surl = editartdFlujoUrl+idflujo;
			$http.get(surl).then(function(res){
				var dato = res.data;
				$scope.tdFlujoModelo.idflujo = dato.idflujo;
				$scope.tdFlujoModelo.idflujopadre = dato.idflujopadre;
				$scope.tdFlujoModelo.idsacc = dato.idsacc;
				$scope.tdFlujoModelo.fechaDerivacion = dato.fechaDerivacion;
				$scope.tdFlujoModelo.idunidadDeriva = dato.idunidadDeriva;
				$scope.tdFlujoModelo.iduserDeriva = dato.iduserDeriva;
				$scope.tdFlujoModelo.fechaRecepcion = dato.fechaRecepcion;
				$scope.tdFlujoModelo.idunidadRecepciona = dato.idunidadRecepciona;
				$scope.tdFlujoModelo.iduserRecepciona = dato.iduserRecepciona;
				$scope.tdFlujoModelo.idunidadDestino = dato.idunidadDestino;
				$scope.tdFlujoModelo.iduserDestino = dato.iduserDestino;
				$scope.tdFlujoModelo.idunidadAtiende = dato.idunidadAtiende;
				$scope.tdFlujoModelo.iduserAtiende = dato.iduserAtiende;
				$scope.tdFlujoModelo.fechaAtencion = dato.fechaAtencion;
				$scope.tdFlujoModelo.observacion = dato.observacion;
				$scope.tdFlujoModelo.tiempoestadia = dato.tiempoestadia;
				$scope.tdFlujoModelo.fechaCrea = dato.fechaCrea;
				$scope.tdFlujoModelo.fechaModif = dato.fechaModif;
				$scope.tdFlujoModelo.iduserCrea = dato.iduserCrea;
				$scope.tdFlujoModelo.iduserModif = dato.iduserModif;
				$scope.tdFlujoModelo.estado = dato.estado;
				$scope.tdFlujoModelo.rmtaddress = dato.rmtaddress;
				$scope.tdFlujoModelo.rmtaddressrst = dato.rmtaddressrst;
				
				$scope.tdFlujoModelo.editopcion = dato.editopcion;
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
					        .title('Eliminar  Movimiento')
					        .textContent(dato.message)
					        .ariaLabel('ERROR')
					        .ok('OK')
					        .targetEvent(ev)
						   );
			            }			           
			        });			        			        	
			 };
			 
		$scope.showConfirm = function(ev, tdFlujoBk) {
				    var confirm = $mdDialog.confirm()
				      .title('Eliminar  Movimiento')
				      .textContent('Esta usted seguro de eliminar el registro?')
				      .ariaLabel('Lucky day')
				      .targetEvent(ev)
				      .ok('Si')
				      .cancel('No');

				    $mdDialog.show(confirm).then(function () {
				      $scope.status = 'SI';
				      $scope.eliminartdFlujo(ev, tdFlujoBk);
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
				  
		    $scope.nuevo = ($scope.isNumber($scope.tdFlujoModelo.idflujo) && $scope.tdFlujoModelo.idflujo > 0);
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
			 
			$scope.filtroTdFlujo = function(toma){				
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
