var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listamsUnidadesOrgUrl = contexto+"/rs/ctrlmsUnidadesOrg/listamsUnidadesOrg";
var insertmsUnidadesOrgUrl = contexto+"/rs/ctrlmsUnidadesOrg/salvarmsUnidadesOrg";
var eliminarmsUnidadesOrgUrl = contexto+"/rs/ctrlmsUnidadesOrg/eliminarmsUnidadesOrg";
var editarmsUnidadesOrgUrl = contexto+"/rs/ctrlmsUnidadesOrg/editarmsUnidadesOrg/";
var listaUnidadesUrl = contexto+"/rs/ctrlmsUnidadesOrg/listauotree";
var listaUOUrl = contexto+"/rs/ctrlmsUnidadesOrg/listaUoLista";
/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idunidad", {
		templateUrl : "adminis/editarmsUnidadesOrg.html",
		controller : "ctrlListamsUnidadesOrg"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editarmsUnidadesOrg.html",
		controller : "ctrlListamsUnidadesOrg"
	})
	.otherwise({
		templateUrl : "adminis/vermsUnidadesOrg.html",
		controller : "ctrlListamsUnidadesOrg"  
	});
});

myapp.directive('tree', function() {    
	function linker(parent) {
		return function(clone) {
			parent.append(clone);
		};
	}    
	function buildTree(parNode, level, nodes, scope, ident, transclude) {

		if(typeof nodes === 'undefined')
			return;

		for (var i = 0; i < nodes.length; ++i) {
			var node = nodes[i];            
			var nodeScope = scope.$new();
			nodeScope[ident] = node;
			nodeScope.level = level;            
			transclude(nodeScope, linker(parNode));            
			nodeScope.tienechildren = (  typeof node === 'undefined' || typeof node.children=== 'undefined'?false:node.children.length > 0);            
			if (node.children.length > 0) {
				var ul = angular.element('<ul>');
				var children = parNode.children();
				var lastChild = angular.element(children[children.length - 1]);
				lastChild.find('recur').replaceWith(ul);
				buildTree(ul, level + 1, node.children, nodeScope, ident, transclude);
			}
		}
	}    
	function link(scope, element, attrs, ctrl, transclude) {
		var nodes = scope.$eval(attrs.roots);
		scope.$watch(attrs.roots, function(o,n) {
			element.children().remove();
			buildTree(element, 0, o, scope, attrs.item, transclude);
			element.find('recur').remove();
		},true);
		element.on('$destroy', function() {          
		});        
	}    
	return {
		restrict: 'A',
		transclude: true,
		terminal: true,
		priority: 2000,
		link: link
	};
});


myapp.controller('ctrlListamsUnidadesOrg', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idunidad',
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
		$scope.loadmsUnidadesOrg();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadmsUnidadesOrg();
	};
	$scope.creamsUnidadesOrg = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadmsUnidadesOrg = function () {
		// $scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creamsUnidadesOrg = res.data.creamodifica;
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
						.title('Lista de Unidad Orgánica')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}
		});			 
		// }, 500);
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

		return listamsUnidadesOrgUrl+order+limit+page+filtroparametro;
	};

	$scope.editmsUnidadesOrg = function () {
		$scope.nuevo = true;
		var idunidad = $routeParams.idunidad;
		if(idunidad){
			$scope.cargarmsUnidadesOrg(idunidad);
		}
		// /CARGAR COMPLEMENTOS
		$scope.loadListaUOTree();
		$scope.loadListaUO();
	};

	$scope.filtro ={
			idunidad: null,
			idpadreTxt: null,
			codigo: null,
			descripcion: null,
			acronimo: null,  
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadmsUnidadesOrg();
		}
	};

	$scope.msUnidadesOrgModelo = {
			idunidad : null,
			idpadre: null,
			codigo: null,
			descripcion: null,
			acronimo: null,
			flagofgeneral: null,
			estado: null,
			editopcion: 1
	};

	$scope.clearmsUnidadesOrg = function(){
		$scope.msUnidadesOrgModelo.idunidad = null;
		$scope.msUnidadesOrgModelo.idpadre = null;
		$scope.msUnidadesOrgModelo.codigo = null;
		$scope.msUnidadesOrgModelo.descripcion = null;
		$scope.msUnidadesOrgModelo.acronimo = null;
		$scope.msUnidadesOrgModelo.flagofgeneral = null;
		$scope.msUnidadesOrgModelo.estado = null;
		
		$scope.msUnidadesOrgModelo.editopcion = 1;
	}; 
	
	$scope.setMsUnidadesOrgModelo = function(msUnidadesOrgBk) {
		$scope.msUnidadesOrgModelo.idunidad = msUnidadesOrgBk.idunidad;
		$scope.msUnidadesOrgModelo.idpadre = msUnidadesOrgBk.idpadre;
		$scope.msUnidadesOrgModelo.codigo = msUnidadesOrgBk.codigo;
		$scope.msUnidadesOrgModelo.descripcion = msUnidadesOrgBk.descripcion;
		$scope.msUnidadesOrgModelo.acronimo = msUnidadesOrgBk.acronimo;
		$scope.msUnidadesOrgModelo.flagofgeneral = msUnidadesOrgBk.flagofgeneral;
		$scope.msUnidadesOrgModelo.estado = msUnidadesOrgBk.estado;
		
		$scope.msUnidadesOrgModelo.editopcion = msUnidadesOrgBk.msUnidadesOrgACL.editopcion;
	};
	
	// ////////////////////////////////////////////////
	$scope.editarMsUnidadesOrg = function(ev, msUnidadesOrgBk) {
		$scope.setMsUnidadesOrgModelo(msUnidadesOrgBk);

		$location.url('/editar/' + $scope.msUnidadesOrgModelo.idunidad);
		$scope.nuevo = false;
	};

	$scope.nuevoMsUnidadesOrg = function() {
		$scope.clearmsUnidadesOrg();
		$location.url('/nuevo');
		$scope.nuevo = true;
	};

	$scope.cancelarMsUnidadesOrg = function() {
		$scope.clearmsUnidadesOrg();
		$location.url('/');
	};

	$scope.salvarMsUnidadesOrg = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msUnidadesOrgModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsUnidadesOrgUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.datos.push(dato); 
			$scope.total = $scope.datos.length;

			$scope.setMsUnidadesOrgModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR UO')
					.textContent('LA UO SE GUARDO CORRECTAMENTE')
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
						.title('GUARDAR UO')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarmsUnidadesOrg = function(ev,msUnidadesOrgBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(msUnidadesOrgBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarmsUnidadesOrgUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idunidad === dato.idunidad);
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
						.title('Eliminar Unidad Orgánica')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarmsUnidadesOrg = function(idunidad){		
		var surl = editarmsUnidadesOrgUrl+idunidad;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setMsUnidadesOrgModelo(dato);
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
						.title('Eliminar Unidad Orgánica')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, msUnidadesOrgBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Unidad Orgánica')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarmsUnidadesOrg(ev, msUnidadesOrgBk);
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

	$scope.nuevo = ($scope.isNumber($scope.msUnidadesOrgModelo.idunidad) && $scope.msUnidadesOrgModelo.idunidad > 0);
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

	$scope.filtroMsUnidadesOrg = function(toma){				
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
	};

	$scope._keys = function(obj) 
	{
		if (!$scope.isObject(obj)) return [];
		if (Object.keys) return Object.keys(obj);
		var keys = [];
		for (var key in obj) if (_.has(obj, key)) keys.push(key);
		return keys;
	};

	$scope.isObject = function(obj) 
	{
		var type = typeof obj;
		return type === 'function' || type === 'object' && !!obj;
	};

	///ADICIONALES
	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
		}
		$scope.loadmsUnidadesOrg();
	};
	
	$scope.listaUOTree=[];
	$scope.loadListaUOTree=function(){
		$http.get(listaUnidadesUrl).then(function(res){
			$scope.listaUOTree = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.listaUO=[];
	$scope.loadListaUO=function(){
		$http.get(listaUOUrl).then(function(res){
			$scope.listaUO = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.uoCodToSearch={
			buscarcodigo: null
	};
	$scope.searchUOByCod = function(key, inputArray){
		for (var i=0; i < inputArray.length; i++) {
			if (inputArray[i].codigo === key) {
				return inputArray[i];
			}}
	};

	$scope.searchUOByCodigo = function(ev){
		if(!$scope.isString($scope.uoCodToSearch.buscarcodigo)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato incorrecto!')
					.textContent('Ingrese el valor del código documental en el cuadro de busqueda.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		$scope.msUnidadesOrgDto = $scope.searchUOByCod($scope.uoCodToSearch.buscarcodigo, $scope.listaUO);
		if(!$scope.isObject($scope.msUnidadesOrgDto)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato no encontrado!')
					.textContent('No se encontró ninguna unidad orgánica para el código ingresado.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		$scope.msUnidadesOrgModelo.idpadre = $scope.msUnidadesOrgDto.idunidad;
	};

	$scope.setIdUnidad = function(idunidad) {
		$scope.msUnidadesOrgModelo.idpadre = idunidad;
		$mdDialog.hide();
	};

	$scope.showTreeDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#treeDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};
	
}]);
