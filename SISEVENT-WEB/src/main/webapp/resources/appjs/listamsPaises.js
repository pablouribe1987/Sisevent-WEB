var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listamsPaisesUrl = contexto+"/rs/ctrlmsPaises/listamsPaises";
var insertmsPaisesUrl = contexto+"/rs/ctrlmsPaises/salvarmsPaises";
var eliminarmsPaisesUrl = contexto+"/rs/ctrlmsPaises/eliminarmsPaises";
var editarmsPaisesUrl = contexto+"/rs/ctrlmsPaises/editarmsPaises/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:paiPk", {
		templateUrl : "adminis/editarmsPaises.html",
		controller : "ctrlListamsPaises"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editarmsPaises.html",
		controller : "ctrlListamsPaises"
	})
	.otherwise({
		templateUrl : "adminis/vermsPaises.html",
		controller : "ctrlListamsPaises"  
	});
});

myapp.controller('ctrlListamsPaises', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'paiPk',
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
		$scope.loadmsPaises();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadmsPaises();
	};

	$scope.creamsPaises = false;

	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadmsPaises = function () {
		//$scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creamsPaises = res.data.creamodifica;
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
						.title('Lista de  País')
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

		return listamsPaisesUrl+order+limit+page+filtroparametro;
	}

	$scope.editmsPaises = function () {
		$scope.nuevo = true;
		var paiPk = $routeParams.paiPk;
		if(paiPk){
			$scope.cargarmsPaises(paiPk);
		}
		///CARGAR COMPLEMENTOS

	};

	$scope.filtro ={
			paiPk: null,
			paiNombre: null,
			estado: null
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadmsPaises();
		}
	};

	$scope.msPaisesModelo = {
			paiPk : null,
			paiNombre: null,
			estado: null,
			editopcion: 1
	};

	$scope.clearmsPaises = function(){
		$scope.msPaisesModelo.paiPk = null;
		$scope.msPaisesModelo.paiNombre = null;
		$scope.msPaisesModelo.estado = null;
		$scope.msPaisesModelo.editopcion = 1;
	} 

	$scope.setMsPaisesModelo = function(msPaisesBk) {
		$scope.msPaisesModelo.paiPk = msPaisesBk.paiPk;
		$scope.msPaisesModelo.paiNombre = msPaisesBk.paiNombre;
		$scope.msPaisesModelo.estado = msPaisesBk.estado;
		$scope.msPaisesModelo.editopcion = msPaisesBk.msPaisesACL.editopcion;			
	}

	// ////////////////////////////////////////////////
	$scope.editarMsPaises = function(ev, msPaisesBk) {
		$scope.setMsPaisesModelo(msPaisesBk);
		$location.url('/editar/' + $scope.msPaisesModelo.paiPk);
		$scope.nuevo = false;
	}

	$scope.nuevoMsPaises = function() {
		$scope.clearmsPaises();
		$location.url('/nuevo');
		$scope.nuevo = true;
	}

	$scope.cancelarMsPaises = function() {
		$scope.clearmsPaises();
		$location.url('/');
	}

	$scope.salvarMsPaises = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msPaisesModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsPaisesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.datos.push(dato); 
			$scope.total = $scope.datos.length;

			$scope.setMsPaisesModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR PAÍS')
					.textContent('EL PAÍS SE GUARDO CORRECTAMENTE')
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
						.title('GUARDAR PAÍS')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarmsPaises = function(ev,msPaisesBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(msPaisesBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarmsPaisesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.paiPk === dato.paiPk);
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
						.title('Eliminar  País')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarmsPaises = function(paiPk){		
		var surl = editarmsPaisesUrl+paiPk;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setMsPaisesModelo(dato);
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
						.title('Eliminar  País')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, msPaisesBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar  País')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarmsPaises(ev, msPaisesBk);
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

	$scope.nuevo = ($scope.isNumber($scope.msPaisesModelo.paiPk) && $scope.msPaisesModelo.paiPk > 0);
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

	$scope.filtroMsPaises = function(toma){				
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
		$scope.loadmsPaises();
	}; 
}]);
