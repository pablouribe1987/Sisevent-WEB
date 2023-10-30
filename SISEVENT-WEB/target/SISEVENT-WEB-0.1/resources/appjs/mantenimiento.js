var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';

var inicialUrl = contexto + "/rs/ctrlmantenimiento/serverinfo";
var liberarMemUrl = contexto + "/rs/ctrlmantenimiento/liberarmemoria";
var salvarparametroUrl = contexto + "/rs/ctrlmantenimiento/salvarparametro";
var cargarparametroUrl = contexto + "/rs/ctrlmantenimiento/cargarparametro";

var ejecutarIniUrl = contexto + "/rs/ctrlmantenimiento/ejecutarIni";
var ejecutarIniCacheUrl = contexto + "/rs/ctrlmantenimiento/ejecutarIniCache";
var progressUrl = contexto + "/rs/ctrlmantenimiento/progress";
var serverinfoUrl = contexto + "/rs/ctrlmantenimiento/serverinfo";
var liberarMemUrl = contexto + "/rs/ctrlmantenimiento/liberarmemoria";
var salvarparametroUrl = contexto + "/rs/ctrlmantenimiento/salvarparametro";
var cargarparametroUrl = contexto + "/rs/ctrlmantenimiento/cargarparametro";
var ejecutarqueryUrl = contexto + "/rs/ctrlmantenimiento/ejecutarquery";
var ejecutarappUrl = contexto + "/rs/ctrlmantenimiento/ejecutarapp";




/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idferiado", {
		templateUrl : "adminis/editartdFeriados.html",
		controller : "ctrlListatdFeriados"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editartdFeriados.html",
		controller : "ctrlListatdFeriados"
	})
	.otherwise({
		templateUrl : "adminis/vertdFeriados.html",
		controller : "ctrlListatdFeriados"  
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

myapp.controller('ctrlMantenimiento', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel', '$interval',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel, $interval) {
	'use strict';

	$scope.progreso = {
			iniLabel: null,
			iniTotal: 0,
			iniProgreso: 0,
			iniProgresoPorc: 0
	};

	var stop= undefined;
	$scope.ejecutarIniAccion = function(ev){		
		ev.target.disabled = true;
		var datainsert = null; //angular.toJson($scope.tdFeriadosModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(ejecutarIniUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.progreso = dato;
			$scope.actualizarprogress();			
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Ejecutar INI Data')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});
	};

	$scope.stopFight = function() {
		if (angular.isDefined(stop)) {
			$interval.cancel(stop);
			stop = undefined;
		}
	};

	$scope.ejecutarIniCacheAccion = function(ev){		
		ev.target.disabled = true;
		var datainsert = null; //angular.toJson($scope.tdFeriadosModelo);
		$http.post(ejecutarIniCacheUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.progreso = dato;
			$scope.actualizarprogress();			
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Ejecutar INI Cache')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});
	};  

	$scope.actualizarprogress = function() {
		stop = $interval(function() {				
			$http.get(progressUrl).then(function(res){
				var dato = res.data;
				$scope.progreso = dato;
				if(dato.iniLabel=='--FIN--'){
					$scope.stopFight();
				}
			},
			function error(errResponse) {
				console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
				$scope.stopFight();
			});
		}, 500);
	};

	$scope.query = null;
	$scope.datasource = null;
	$scope.resultado = null;
	////////////////////////////////////////////////77
	$scope.mantenimientoInicial = {};
	$scope.loadinicial = function(ev) {
		$http.get(serverinfoUrl).then(
				function(res) {
					$scope.mantenimientoInicial = res.data;
					ev.target.disabled = false;
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers "
							+ errResponse.headers + "config " + errResponse.config + " statusText " + errResponse
							+ " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if (typeof (dato) != 'undefined' && typeof (dato.message) != 'undefined') {
						$mdDialog.show($mdDialog.alert().parent(
								angular.element(document.querySelector('#popupContainerDlg'))).clickOutsideToClose(
										true).title('Mantenimiento').textContent(dato.message).ariaLabel('ERROR').ok('OK')
										.targetEvent(ev));
					}
				});
	};

	$scope.liberarMem = function(ev) {
		$http.get(liberarMemUrl).then(
				function(res) {
					$scope.mantenimientoInicial = res.data;
					ev.target.disabled = false;
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers "
							+ errResponse.headers + "config " + errResponse.config + " statusText " + errResponse
							+ " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if (typeof (dato) != 'undefined' && typeof (dato.message) != 'undefined') {
						$mdDialog.show($mdDialog.alert().parent(
								angular.element(document.querySelector('#popupContainerDlg'))).clickOutsideToClose(
										true).title('Mantenimiento').textContent(dato.message).ariaLabel('ERROR').ok('OK')
										.targetEvent(ev));
					}
				});
	};

	$scope.salvarParametroSistema = function(ev) {
		var searchParams = 'parametrosistema='
			+ ($scope.datasource ? encodeURIComponent($scope.datasource) : "") + '&valorsistema='
			+ ($scope.query ? encodeURIComponent($scope.query) : "");
		$http.get(salvarparametroUrl, searchParams).then(
				function(res) {
					var dato = res.data;
					$scope.resultado = dato.resultado;
					ev.target.disabled = false;
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers "
							+ errResponse.headers + "config " + errResponse.config + " statusText " + errResponse
							+ " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if (typeof (dato) != 'undefined' && typeof (dato.message) != 'undefined') {
						$mdDialog.show($mdDialog.alert().parent(
								angular.element(document.querySelector('#popupContainerDlg'))).clickOutsideToClose(
										true).title('Mantenimiento').textContent(dato.message).ariaLabel('ERROR').ok('OK')
										.targetEvent(ev));
					}
				});
	};

	$scope.cargarParametroSistema = function(ev) {
		var searchParams = 'parametrosistema='
			+ ($scope.datasource ? encodeURIComponent($scope.datasource) : "");
		$http.get(cargarparametroUrl, searchParams).then(
				function(res) {
					var dato = res.data;
					$scope.resultado = dato.resultado;
					ev.target.disabled = false;
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers "
							+ errResponse.headers + "config " + errResponse.config + " statusText " + errResponse
							+ " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if (typeof (dato) != 'undefined' && typeof (dato.message) != 'undefined') {
						$mdDialog.show($mdDialog.alert().parent(
								angular.element(document.querySelector('#popupContainerDlg'))).clickOutsideToClose(
										true).title('Mantenimiento').textContent(dato.message).ariaLabel('ERROR').ok('OK')
										.targetEvent(ev));
					}
				});
	};

	$scope.ejecutarQuery = function(ev){		
		ev.target.disabled = true;
		var datainsert = "";
		var searchParams = 'paramedatasource='
			+ ($scope.datasource ? encodeURIComponent($scope.datasource) : "") + '&qry='
			+ ($scope.query ? encodeURIComponent($scope.query) : "");	
		$http.post(ejecutarqueryUrl+'?'+searchParams,datainsert,{headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(function(res){
			 var dato = res.data;
			 $scope.resultado = dato.resultado;
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Ejecutar QUERY')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});
	};
	
	$scope.ejecutarApp = function(ev){		
		ev.target.disabled = true;
		var searchParams = 'parameter='+ ($scope.query ? encodeURIComponent($scope.query) : "");
		var datainsert = "";
//			$.param({
//			parameter: ($scope.query ? encodeURIComponent($scope.query) : "")
//        });		
		$http.post(ejecutarappUrl+'?'+searchParams,datainsert,{headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(function(res){
			var dato = res.data; //<p [innerHTML]="datos.bosquejo" >
			$scope.resultado = dato.resultado; //dato.resultado.split("\n").join("<br />"); 
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Ejecutar APP')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
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

}]);
