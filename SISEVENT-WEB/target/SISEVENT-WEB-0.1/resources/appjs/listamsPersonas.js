var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listamsPersonasUrl = contexto+"/rs/ctrlmsPersonas/listamsPersonas";
var insertmsPersonasUrl = contexto+"/rs/ctrlmsPersonas/salvarmsPersonas";
var eliminarmsPersonasUrl = contexto+"/rs/ctrlmsPersonas/eliminarmsPersonas";
var editarmsPersonasUrl = contexto+"/rs/ctrlmsPersonas/editarmsPersonas/";
var listaPaisesUrl = contexto+"/rs/ctrlmsInstituciones/listaPaises";
var ubigeodefectoUrl = contexto+"/rs/ctrlmsInstituciones/ubigeodefecto";
var listaCoddptosUrl = contexto+"/rs/ctrlmsInstituciones/listaCoddptos";
var listaCodprovUrl = contexto+"/rs/ctrlmsInstituciones/listaCodprov/";
var listaCoddistUrl = contexto+"/rs/ctrlmsInstituciones/listaCoddist/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idperson", {
		templateUrl : "adminis/editarmsPersonas.html",
		controller : "ctrlListamsPersonas"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editarmsPersonas.html",
		controller : "ctrlListamsPersonas"
	})
	.otherwise({
		templateUrl : "adminis/vermsPersonas.html",
		controller : "ctrlListamsPersonas"  
	});
});

myapp.controller('ctrlListamsPersonas', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idperson',
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
		$scope.loadmsPersonas();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadmsPersonas();
	};

	$scope.creamsPersonas = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadmsPersonas = function () {
		//$scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creamsPersonas = res.data.creamodifica;
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
						.title('Lista de  Persona')
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

		return listamsPersonasUrl+order+limit+page+filtroparametro;
	}

	$scope.editmsPersonas = function () {
		$scope.nuevo = true;
		var idperson = $routeParams.idperson;
		if(idperson){
			$scope.cargarmsPersonas(idperson);
		}
		///CARGAR COMPLEMENTOS
		$scope.loadlistaPaises();
		$scope.loadubigeodefecto();
		$scope.loadlistaCoddptos();	
	};

	$scope.filtro ={
			idperson: null,
			apellidoPaterno: null,
			apellidoMaterno: null,
			nombres: null,
			documentoNumero: null,
			direccion: null,
			estado: null,
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadmsPersonas();
		}
	};

	$scope.msPersonasModelo = {
			idperson : null,
			apellidoPaterno: null,
			apellidoMaterno: null,
			nombres: null,
			sexo: null,
			tipodocumento: null,
			documentoNumero: null,
			correo: null,
			telefono: null,
			celular: null,
			codpais: null,
			coddpto: null,
			codprov: null,
			coddist: null,
			direccion: null,
			
			codpaisTxt: null,
			coddptoTxt: null,
			codprovTxt: null,
			coddistTxt: null,
			
			estado: null,
			editopcion: 1
	};

	$scope.clearmsPersonas = function(){
		$scope.msPersonasModelo.idperson = null;
		$scope.msPersonasModelo.apellidoPaterno = null;
		$scope.msPersonasModelo.apellidoMaterno = null;
		$scope.msPersonasModelo.nombres = null;
		$scope.msPersonasModelo.sexo = null;
		$scope.msPersonasModelo.tipodocumento = null;
		$scope.msPersonasModelo.documentoNumero = null;
		$scope.msPersonasModelo.correo = null;
		$scope.msPersonasModelo.telefono = null;
		$scope.msPersonasModelo.celular = null;
		$scope.msPersonasModelo.codpais = null;
		$scope.msPersonasModelo.coddpto = null;
		$scope.msPersonasModelo.codprov = null;
		$scope.msPersonasModelo.coddist = null;
		$scope.msPersonasModelo.direccion = null;
		$scope.msPersonasModelo.tienefoto = null;
		$scope.msPersonasModelo.estado = null;
		
		$scope.msPersonasModelo.codpaisTxt= null;
		$scope.msPersonasModelo.coddptoTxt= null;
		$scope.msPersonasModelo.codprovTxt= null;
		$scope.msPersonasModelo.coddistTxt= null;
		
		$scope.msPersonasModelo.editopcion = 1;
		
		if($scope.msPersonasModelo){
			if($scope.msPersonasModelo.codpais==null){
				$scope.msPersonasModelo.codpais=$scope.ubigeodefectos.xDefectoCodpais;
			}
			if($scope.msPersonasModelo.coddpto==null){
				$scope.msPersonasModelo.coddpto=$scope.ubigeodefectos.xDefectoCoddpto;
			}
			if($scope.msPersonasModelo.codprov==null){
				$scope.msPersonasModelo.codprov=$scope.ubigeodefectos.xDefectoCodprov;
			}
			if($scope.msPersonasModelo.coddist==null){
				$scope.msPersonasModelo.coddist=$scope.ubigeodefectos.xDefectoCoddist;
			}}
	}; 

	$scope.setMsPersonasModelo = function(msPersonasBk) {
		$scope.msPersonasModelo.idperson = msPersonasBk.idperson;
		$scope.msPersonasModelo.apellidoPaterno = msPersonasBk.apellidoPaterno;
		$scope.msPersonasModelo.apellidoMaterno = msPersonasBk.apellidoMaterno;
		$scope.msPersonasModelo.nombres = msPersonasBk.nombres;
		$scope.msPersonasModelo.sexo = msPersonasBk.sexo;
		$scope.msPersonasModelo.tipodocumento = msPersonasBk.tipodocumento;
		$scope.msPersonasModelo.documentoNumero = msPersonasBk.documentoNumero;
		$scope.msPersonasModelo.correo = msPersonasBk.correo;
		$scope.msPersonasModelo.telefono = msPersonasBk.telefono;
		$scope.msPersonasModelo.celular = msPersonasBk.celular;
		$scope.msPersonasModelo.codpais = msPersonasBk.codpais;
		$scope.msPersonasModelo.coddpto = msPersonasBk.coddpto;
		$scope.msPersonasModelo.codprov = msPersonasBk.codprov;
		$scope.msPersonasModelo.coddist = msPersonasBk.coddist;
		$scope.msPersonasModelo.direccion = msPersonasBk.direccion;
		$scope.msPersonasModelo.tienefoto = msPersonasBk.tienefoto;
		
		$scope.msPersonasModelo.codpaisTxt= msPersonasBk.codpaisTxt;
		$scope.msPersonasModelo.coddptoTxt= msPersonasBk.coddptoTxt;
		$scope.msPersonasModelo.codprovTxt= msPersonasBk.codprovTxt;
		$scope.msPersonasModelo.coddistTxt= msPersonasBk.coddistTxt;
		
		$scope.msPersonasModelo.estado = msPersonasBk.estado;
		$scope.msPersonasModelo.editopcion = msPersonasBk.msPersonasACL.editopcion;
	};

	// ////////////////////////////////////////////////
	$scope.editarMsPersonas = function(ev, msPersonasBk) {
		$scope.setMsPersonasModelo(msPersonasBk);
		$location.url('/editar/' + $scope.msPersonasModelo.idperson);
		$scope.nuevo = false;
	}

	$scope.nuevoMsPersonas = function() {
		$scope.clearmsPersonas();
		$location.url('/nuevo');
		$scope.nuevo = true;
	}

	$scope.cancelarMsPersonas = function() {
		$scope.clearmsPersonas();
		$location.url('/');
	}

	$scope.salvarMsPersonas = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msPersonasModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsPersonasUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

//			$scope.datos.push(dato); 
//			$scope.total = $scope.datos.length;

			$scope.setMsPersonasModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR PERSONA')
					.textContent('LA PERSONA SE GUARDO CORRECTAMENTE')
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
						.title('GUARDAR PERSONA')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarmsPersonas = function(ev,msPersonasBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(msPersonasBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarmsPersonasUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idperson === dato.idperson);
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
						.title('Eliminar  Persona')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarmsPersonas = function(idperson){		
		var surl = editarmsPersonasUrl+idperson;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setMsPersonasModelo(dato);
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
						.title('Eliminar  Persona')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, msPersonasBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar  Persona')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarmsPersonas(ev, msPersonasBk);
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

	$scope.nuevo = ($scope.isNumber($scope.msPersonasModelo.idperson) && $scope.msPersonasModelo.idperson > 0);
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

	$scope.filtroMsPersonas = function(toma){				
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
	
	/////ADICIONALES
	$scope.listaPaisess=[];
	$scope.loadlistaPaises=function(){
		$http.get(listaPaisesUrl).then(function(res){
			$scope.listaPaisess = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.ubigeodefectos=null;
	$scope.loadubigeodefecto=function(){
		$http.get(ubigeodefectoUrl).then(function(res){
			$scope.ubigeodefectos = res.data; 
			if($scope.msPersonasModelo.codpais==null){
				$scope.msPersonasModelo.codpais=$scope.ubigeodefectos.xDefectoCodpais;
			}
			if($scope.msPersonasModelo.coddpto==null){
				$scope.msPersonasModelo.coddpto=$scope.ubigeodefectos.xDefectoCoddpto;
			}
			if($scope.msPersonasModelo.codprov==null){
				$scope.msPersonasModelo.codprov=$scope.ubigeodefectos.xDefectoCodprov;
			}
			if($scope.msPersonasModelo.coddist==null){
				$scope.msPersonasModelo.coddist=$scope.ubigeodefectos.xDefectoCoddist;
			}
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.listaCoddptoss=[];
	$scope.loadlistaCoddptos=function(){
		$http.get(listaCoddptosUrl).then(function(res){
			$scope.listaCoddptoss = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.listaCodprovs=[];
	$scope.loadlistaCodprov=function(){
		if($scope.msPersonasModelo.coddpto){
		var surl = listaCodprovUrl+$scope.msPersonasModelo.coddpto;
		$http.get(surl).then(function(res){
			$scope.listaCodprovs = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
		}else{
			$scope.listaCodprovs=[];
		}
	};
		
	$scope.listaCoddists=[];
	$scope.loadlistaCoddist=function(){
		if($scope.msPersonasModelo.coddpto &&
		$scope.msPersonasModelo.codprov){
			var surl = listaCoddistUrl+$scope.msPersonasModelo.coddpto+'/'+$scope.msPersonasModelo.codprov;
		$http.get(surl).then(function(res){
			$scope.listaCoddists = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
		}else{
			$scope.listaCoddists=[];
		}
	};
	
	$scope.visualizarUbigeo = true;	
	$scope.$watch('msPersonasModelo.codpais', function (newValue, oldValue) {
		console.log('msPersonasModelo.codpais ' + newValue+' -- '+oldValue);
		if($scope.ubigeodefectos){
			if($scope.ubigeodefectos.xDefectoCodpais == newValue){
				$scope.visualizarUbigeo = true;
			}else{
				$scope.visualizarUbigeo = false;
			}
		}else{
			$scope.loadubigeodefecto();
		}
	});
	
	$scope.$watch('msPersonasModelo.coddpto', function (newValue, oldValue) {
		console.log('msPersonasModelo.coddpto ' + newValue+' -- '+oldValue);
		$scope.loadlistaCodprov();
//		$scope.msPersonasModelo.codprov = null;
//		$scope.msPersonasModelo.coddist = null;
	});
	
	$scope.$watch('msPersonasModelo.codprov', function (newValue, oldValue) {
		console.log('msPersonasModelo.codprov ' + newValue+' -- '+oldValue);
		$scope.loadlistaCoddist();
//		$scope.msPersonasModelo.coddist = null;
	});
	
	$scope.changeCoddpto=function(){
		$scope.msPersonasModelo.codprov = null;
		$scope.msPersonasModelo.coddist = null;
	}
	
	$scope.changeCodprov=function(){
		$scope.msPersonasModelo.coddist = null;
	}
	
	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
		}
		$scope.loadmsPersonas();
	};
	
}]);
