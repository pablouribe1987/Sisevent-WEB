var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listaprtParametrosUrl = contexto+"/rs/ctrlprtParametros/listaprtParametros";
var insertprtParametrosUrl = contexto+"/rs/ctrlprtParametros/salvarprtParametros";
var eliminarprtParametrosUrl = contexto+"/rs/ctrlprtParametros/eliminarprtParametros";
var editarprtParametrosUrl = contexto+"/rs/ctrlprtParametros/editarprtParametros/";
var listaParametrosXIdUrl = contexto+"/rs/ctrlprtParametros/listaParametrosXId";
var listaprtParametrosTemaUrl = contexto+"/rs/ctrlprtParametros/listaprtParametrosTema/";
var getParametrosUrl = contexto+"/rs/ctrlprtParametros/parametrospadre/"; 
var listaprtParametrosSubTemaUrl = contexto+"/rs/ctrlprtParametros/listaprtParametrosSubTema/";


/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idparametro", {
		templateUrl : "adminis/editarprtParametros.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editarprtParametros.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/clave/:keyt", {
		templateUrl : "adminis/verprtParametrosTema.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/editar/:idparametro/:keyt", {
		templateUrl : "adminis/editarprtParametros.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/nuevo/:keyt", {
		templateUrl : "adminis/editarprtParametros.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/subclave/:keyt", {
		templateUrl : "adminis/verprtParametrosSubTema.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/canal/:keyt", {
		templateUrl : "adminis/verprtParametrosCanal.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/editarsub/:idparametro/:keyt", {
		templateUrl : "adminis/editarprtParametrosSub.html",
		controller : "ctrlListaprtParametros"
	})
	.when("/nuevosub/:keyt", {
		templateUrl : "adminis/editarprtParametrosSub.html",
		controller : "ctrlListaprtParametros"
	})
	.otherwise({
		templateUrl : "adminis/verprtParametros.html",
		controller : "ctrlListaprtParametros"  
	});
});

myapp.controller('ctrlListaprtParametros', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idparametro',
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
		if($scope.keytm){
			$scope.listaprtParametrosTemaUrl();
		}else{
		 $scope.loadprtParametros();
	    }
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		if($scope.keytm){
			$scope.listaprtParametrosTemaUrl();
		}else{
		 $scope.loadprtParametros();
	    }
	};
	$scope.creaprtParametros = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;
	$scope.keytm = null;
	$scope.essub = false;
	
	$scope.loadprtParametros = function () {
		//$scope.promise = $timeout(function () {
		var surl = listaprtParametrosUrl+$scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creaprtParametros = res.data.creamodifica;
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
						.title('Lista Parámetros')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')

				);
			}
		});			 
		//}, 500);
	};
	
	$scope.listaprtParametrosTemaUrl = function () {
		//$scope.promise = $timeout(function () {
		var keyt = $routeParams.keyt
		if(keyt){
			$scope.keytm = keyt;			
		}else{
			if(!$scope.keytm)
			      return;
		}
		var surl = listaprtParametrosTemaUrl+$scope.keytm+$scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creaprtParametros = res.data.creamodifica;
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
						.title('Lista Parámetros')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')

				);
			}
		});			 
		//}, 500);
	};
	
	$scope.listaprtParametrosSubTemaUrl = function () {
		//$scope.promise = $timeout(function () {
		var keyt = $routeParams.keyt
		if(keyt){
			$scope.keytm = keyt;			
		}else{
			if(!$scope.keytm)
			      return;
		}
		$scope.essub = true;
		var surl = listaprtParametrosSubTemaUrl+$scope.keytm+$scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creaprtParametros = res.data.creamodifica;
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
						.title('Lista Parámetros')
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
		// /FILTRO
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
		return order+limit+page+filtroparametro;
	}

	$scope.editprtParametros = function () {
		$scope.nuevo = true;
		var idparametro = $routeParams.idparametro;
		var keyt = $routeParams.keyt
		if(idparametro && keyt){
			$scope.keytm = keyt;
			$scope.prtParametrosModelo.editopcion = 3;
			//-----------------------------------------
			$scope.cargarprtParametros(idparametro, keyt);
			$scope.cargarprtParametroPadre(keyt);
		}else if(idparametro){
			$scope.cargarprtParametros(idparametro,null);
			$scope.loadlistaPadres();
		}else if(keyt){
			$scope.keytm = keyt;
			$scope.prtParametrosModelo.editopcion = 3;
			//-----------------------------------------
			$scope.cargarprtParametroPadre(keyt);
		}else{
			$scope.loadlistaPadres();
		}
		///CARGAR COMPLEMENTOS		
	};
	
	$scope.editprtParametrosSub = function () {
		$scope.nuevo = true;
		$scope.essub = true;
		var idparametro = $routeParams.idparametro;
		var keyt = $routeParams.keyt
		if(idparametro && keyt){
			$scope.keytm = keyt;			
			$scope.loadlistaPadresTema(keyt);
			$scope.cargarprtParametros(idparametro, keyt);			
		}else if(idparametro){
			$scope.cargarprtParametros(idparametro,null);
			$scope.loadlistaPadres();
		}else if(keyt){
			$scope.keytm = keyt;
			$scope.loadlistaPadresTema(keyt);
		}else{
			$scope.loadlistaPadres();
		}		
	};
	
	$scope.filtro ={
			idparametro: null,
			idpadreTxt: null,
			descripcion: null,
			descripcioncorta: null,
			orden: null,
			estado: null,
	}; 

	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
	    }
		if($scope.keytm){
			$scope.listaprtParametrosTemaUrl();
		}else{
		 $scope.loadprtParametros();
	    }
	};
	
	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			if($scope.keytm){
				$scope.listaprtParametrosTemaUrl();
			}else{
			 $scope.loadprtParametros();
		    }
		}
	};

	$scope.prtParametrosModelo = {
			idparametro : null,
			idpadre: null,
			descripcion: null,
			descripcioncorta: null,
			orden: null,
			idpadreTxt: null,
			editopcion: 1
	};

	$scope.clearprtParametros = function(){
		$scope.prtParametrosModelo.idparametro = null;
		$scope.prtParametrosModelo.idpadre = null;
		$scope.prtParametrosModelo.descripcion = null;
		$scope.prtParametrosModelo.descripcioncorta = null;
		$scope.prtParametrosModelo.orden = null;
		$scope.prtParametrosModelo.editopcion = 1;
	} 
	// ////////////////////////////////////////////////
	$scope.editarPrtParametros = function(ev, prtParametrosBk) {
		$location.url('/editar/' + prtParametrosBk.idparametro);
		$scope.nuevo = false;
	}
	
	$scope.editarPrtParametrosTema = function(ev, prtParametrosBk) {
		$location.url('/editar/'+prtParametrosBk.idparametro+'/'+$scope.keytm);
		$scope.nuevo = false;
	}
	
	$scope.editarPrtParametrosSubTema = function(ev, prtParametrosBk) {
		$location.url('/editarsub/'+prtParametrosBk.idparametro+'/'+$scope.keytm);
		$scope.nuevo = false;
	}

	$scope.nuevoPrtParametros = function() {
		$scope.clearprtParametros();
		$location.url('/nuevo');
		$scope.nuevo = true;
	}
	
	$scope.nuevoPrtParametrosTema = function() {
		$scope.clearprtParametros();
		$location.url('/nuevo/'+$scope.keytm);
		$scope.nuevo = true;
	}
	
	$scope.nuevoPrtParametrosSubTema = function() {
		$scope.clearprtParametros();
		$location.url('/nuevosub/'+$scope.keytm);
		$scope.nuevo = true;
	}

	$scope.cancelarPrtParametros = function() {
		$scope.clearprtParametros();
		if($scope.keytm){
			if($scope.keytm=="temas"){
				if($scope.essub){					
					$location.url('/subclave/'+$scope.keytm);
				}else{
					$location.url('/clave/'+$scope.keytm);
				}
			}else{
				$location.url('/canal/'+$scope.keytm);
			}
		}else{
			$location.url('/');
		}
	}

	$scope.cancelarPrtParametrosTema = function() {
		$scope.clearprtParametros();
		$location.url('/clave/'+$scope.keytm);
	}
	
	$scope.salvarPrtParametros = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.prtParametrosModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertprtParametrosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.datos.push(dato); 
			$scope.total = $scope.datos.length;

			$scope.prtParametrosModelo.idparametro = dato.idparametro;
			$scope.prtParametrosModelo.idpadre = dato.idpadre;
			$scope.prtParametrosModelo.descripcion = dato.descripcion;
			$scope.prtParametrosModelo.descripcioncorta = dato.descripcioncorta;
			$scope.prtParametrosModelo.orden = dato.orden;
			$scope.prtParametrosModelo.editopcion = dato.prtParametrosACL.editopcion;

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR PARÁMETRO')
					.textContent('EL PARÁMETRO SE GUARDO CORRECTAMENTE')
					.ariaLabel('INFORMACIÓN')
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
						.title('GUARDAR PARÁMETRO')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarprtParametros = function(ev,prtParametrosBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(prtParametrosBk);
		console.log("datainsert = "+datainsert);		
		$http.post(eliminarprtParametrosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idparametro === dato.idparametro);
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
						.title('Eliminar Parámetros')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarprtParametros = function(idparametro, keyt){		
		var surl = editarprtParametrosUrl+idparametro;
		if(keyt)
			surl += '/'+keyt;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.prtParametrosModelo.idparametro = dato.idparametro;
			$scope.prtParametrosModelo.idpadre = dato.idpadre;
			$scope.prtParametrosModelo.descripcion = dato.descripcion;
			$scope.prtParametrosModelo.descripcioncorta = dato.descripcioncorta;
			$scope.prtParametrosModelo.orden = dato.orden;
			$scope.prtParametrosModelo.editopcion = dato.prtParametrosACL.editopcion;
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
						.title('Cargar Parámetros')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')

				);
			}			           
		});			        			        	
	};

	$scope.cargarprtParametroPadre = function(keyt){		
		var surl = getParametrosUrl+keyt;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.prtParametrosModelo.idpadre = dato.idparametro;
			$scope.prtParametrosModelo.idpadreTxt = dato.descripcion;
			$scope.prtParametrosModelo.editopcion = 3;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
						           
		});			        			        	
	};
	
	$scope.showConfirm = function(ev, prtParametrosBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Parámetros')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarprtParametros(ev, prtParametrosBk);
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

	$scope.nuevo = ($scope.isNumber($scope.prtParametrosModelo.idparametro) && $scope.prtParametrosModelo.idparametro > 0);
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

	$scope.filtroPrtParametros = function(toma){				
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

	//////////////////////////
	
	$scope.listaPadres=[];
	$scope.loadlistaPadres=function(){
		$http.get(listaParametrosXIdUrl).then(function(res){
			$scope.listaPadres = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.loadlistaPadresTema=function(tema){
		$http.get(listaParametrosXIdUrl+'/'+tema).then(function(res){
			$scope.listaPadres = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
}]);
