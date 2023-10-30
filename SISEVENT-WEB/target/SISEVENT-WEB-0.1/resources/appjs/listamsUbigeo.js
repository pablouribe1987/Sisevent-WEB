var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listamsUbigeoUrl = contexto+"/rs/ctrlmsUbigeo/listamsUbigeo";
var insertmsUbigeoUrl = contexto+"/rs/ctrlmsUbigeo/salvarmsUbigeo";
var eliminarmsUbigeoUrl = contexto+"/rs/ctrlmsUbigeo/eliminarmsUbigeo";
var editarmsUbigeoUrl = contexto+"/rs/ctrlmsUbigeo/editarmsUbigeo/";

var listaCoddptosUrl = contexto+"/rs/ctrlmsUbigeo/listaCoddptos";
var listaCodprovUrl = contexto+"/rs/ctrlmsUbigeo/listaCodprov/";
var listaCoddistUrl = contexto+"/rs/ctrlmsUbigeo/listaCoddist/";
/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:id", {
		templateUrl : "adminis/editarmsUbigeo.html",
		controller : "ctrlListamsUbigeo"
	})
	.when("/nuevo/:stipo", {
		templateUrl : "adminis/editarmsUbigeo.html",
		controller : "ctrlListamsUbigeo"
	})
	.otherwise({
		templateUrl : "adminis/vermsUbigeo.html",
		controller : "ctrlListamsUbigeo"  
	});
});

myapp.controller('ctrlListamsUbigeo', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'ids',
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
		$scope.loadmsUbigeo();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadmsUbigeo();
	};
	$scope.creamsUbigeo = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;
	$scope.tipo = 0;
	
	$scope.loadmsUbigeo = function () {
		//$scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creamsUbigeo = res.data.creamodifica;
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
						.title('Lista de  Ubigeo')
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

		return listamsUbigeoUrl+order+limit+page+filtroparametro;
	}

	$scope.editmsUbigeo = function () {
		$scope.nuevo = true;
		$scope.tipo = 0;
		var id = $routeParams.id;
		if($scope.isString(id)){
			$scope.cargarmsUbigeo(id);
		}
		///CARGAR COMPLEMENTOS
		$scope.loadlistaCoddptos();
		var stipo = $routeParams.stipo;
		if($scope.isString(stipo)){
			$scope.tipo = Number(stipo);
		}		
	};

	$scope.filtro ={
			coddptoTxt: null,
			codprovTxt: null,
			coddistTxt: null,
			ids: null,
			estado: null
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadmsUbigeo();
		}
	};

	$scope.msUbigeoModelo = {
			id : {				
				coddpto: null,
				codprov: null,
				coddist: null
			},
			nombre: null,
			coddptoTxt: null,
			codprovTxt: null,
			coddistTxt: null,
			estado: null,
			editopcion: 1
	};

	$scope.clearmsUbigeo = function(){		
		$scope.msUbigeoModelo.id.coddpto = null;
		$scope.msUbigeoModelo.id.codprov = null;
		$scope.msUbigeoModelo.id.coddist = null;
		$scope.msUbigeoModelo.nombre = null;
		$scope.msUbigeoModelo.coddptoTxt= null;
		$scope.msUbigeoModelo.codprovTxt= null;
		$scope.msUbigeoModelo.coddistTxt= null;
		$scope.msUbigeoModelo.estado = null;
		$scope.msUbigeoModelo.editopcion = 1;
		$scope.tipo = 0;
	}
	
	$scope.setMsUbigeoModelo = function(msUbigeoBk) {
		$scope.msUbigeoModelo.id.coddpto = msUbigeoBk.id.coddpto;
		$scope.msUbigeoModelo.id.codprov = msUbigeoBk.id.codprov;
		$scope.msUbigeoModelo.id.coddist = msUbigeoBk.id.coddist;
		$scope.msUbigeoModelo.nombre = msUbigeoBk.nombre;
		$scope.msUbigeoModelo.coddptoTxt= msUbigeoBk.coddptoTxt;
		$scope.msUbigeoModelo.codprovTxt= msUbigeoBk.codprovTxt;
		$scope.msUbigeoModelo.coddistTxt= msUbigeoBk.coddistTxt;
		$scope.msUbigeoModelo.estado = msUbigeoBk.estado;		
		$scope.msUbigeoModelo.editopcion = msUbigeoBk.msUbigeoACL.editopcion;
		$scope.tipo = msUbigeoBk.tipo;
	}
	
	// ////////////////////////////////////////////////
	$scope.editarMsUbigeo = function(ev, msUbigeoBk) {
		$scope.setMsUbigeoModelo(msUbigeoBk);
		$location.url('/editar/' + msUbigeoBk.ids);
		$scope.nuevo = false;
	}

	$scope.nuevoMsUbigeo = function(tipo) {
		$scope.clearmsUbigeo();
		$location.url('/nuevo/'+tipo);
		$scope.nuevo = true;		
	}

	$scope.cancelarMsUbigeo = function() {
		$scope.clearmsUbigeo();
		$location.url('/');
	}

	$scope.salvarMsUbigeo = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msUbigeoModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsUbigeoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.datos.push(dato); 
			$scope.total = $scope.datos.length;

			$scope.setMsUbigeoModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR UBIGEO')
					.textContent('EL UBIGEO SE GUARDO CORRECTAMENTE')
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
						.title('GUARDAR UBIGEO')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarmsUbigeo = function(ev,msUbigeoBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(msUbigeoBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarmsUbigeoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.ids === dato.ids);
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
						.title('Eliminar  Ubigeo')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarmsUbigeo = function(id){		
		var surl = editarmsUbigeoUrl+id;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setMsUbigeoModelo(dato);
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
						.title('Eliminar  Ubigeo')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, msUbigeoBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar  Ubigeo')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarmsUbigeo(ev, msUbigeoBk);
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

	$scope.nuevo = ($scope.isNumber($scope.msUbigeoModelo.id) && $scope.msUbigeoModelo.id > 0);
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

	$scope.filtroMsUbigeo = function(toma){				
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

	///ADICIONALES
	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
		}
		$scope.loadmsUbigeo();
	};
	
	$scope.listaCoddptoss=[];
	$scope.loadlistaCoddptos=function(){
		if(!$scope.isArray($scope.listaCoddptoss) || $scope.listaCoddptoss.length<=0){
			$http.get(listaCoddptosUrl).then(function(res){
				$scope.listaCoddptoss = res.data; 
			},
			function error(errResponse) {
				console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			});
		}
	};
	
	$scope.changeCoddpto=function(){
		$scope.msUbigeoModelo.id.codprov = null;
		$scope.msUbigeoModelo.id.coddist = null;
	};
	
	$scope.$watch('msUbigeoModelo.id.coddpto', function (newValue, oldValue) {
		console.log('msUbigeoModelo.id.coddpto ' + newValue+' -- '+oldValue);
		$scope.loadlistaCodprov();
	});
	
	$scope.listaCodprovs=[];
	$scope.loadlistaCodprov=function(){
		if($scope.msUbigeoModelo.id.coddpto){
			var surl = listaCodprovUrl+$scope.msUbigeoModelo.id.coddpto;
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
		if($scope.msUbigeoModelo.id.coddpto &&
				$scope.msUbigeoModelo.id.codprov){
			var surl = listaCoddistUrl+$scope.msUbigeoModelo.id.coddpto+'/'+$scope.msUbigeoModelo.id.codprov;
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
	
	$scope.$watch('msUbigeoModelo.id.codprov', function (newValue, oldValue) {
		console.log('msUbigeoModelo.id.codprov ' + newValue+' -- '+oldValue);
		$scope.loadlistaCoddist();
//		$scope.msInstitucionesModelo.coddist = null;
	});
	
	$scope.fabspeed = {
			isOpen: false,
			selectedMode: 'md-fling', //'md-fling', 'md-scale'
			selectedDirection: 'up', //'up', 'down', 'left', 'right'
			hidden: false,
			hover: false
	};
	
	$scope.stipo = function(){
		if($scope.tipo==1){
			return "Departamento";
		}else if($scope.tipo==2){
			return "Provincia";
		}else if($scope.tipo==3){
			return "Distrito";
		}
	};
	
}]);
