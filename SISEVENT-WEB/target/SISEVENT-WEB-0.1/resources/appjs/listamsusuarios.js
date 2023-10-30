var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listamsusuariosUrl = contexto+"/rs/ctrlmsusuariosrs/listamsusuarios";
var insertmsusuariosUrl = contexto+"/rs/ctrlmsusuariosrs/salvarmsusuarios";
var eliminarmsusuariosUrl = contexto+"/rs/ctrlmsusuariosrs/eliminarmsusuarios";
var listaRolesUrl = contexto+"/rs/ctrlmsusuariosrs/listaderoles";
var listaSedesUrl = contexto+"/rs/ctrlmsusuariosrs/listadesedes";
var editarmsusuariosUrl = contexto+"/rs/ctrlmsusuariosrs/editar/";
var listaUnidadesUrl = contexto+"/rs/ctrlmsusuariosrs/listauotree";
var listaUOUrl = contexto+"/rs/ctrlmsusuariosrs/listaUoLista";
var refrescarmsusuariosUrl = contexto+"/rs/ctrlmsusuariosrs/refrescarroles/";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idusuario", {
		templateUrl : "adminis/editarmsusuarios.html",
		controller : "ctrlListamsUsuarios"
	})
	.when("/nuevo", {
		templateUrl : "adminis/editarmsusuarios.html",
		controller : "ctrlListamsUsuarios"
	})
	.otherwise({
		templateUrl : "adminis/vermsusuarios.html",
		controller : "ctrlListamsUsuarios"  
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

myapp.controller('ctrlListamsUsuarios', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idusuario',
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
		$scope.loadmsUsuarios();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadmsUsuarios();
	};
	$scope.creamsUsuarios = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadmsUsuarios = function () {
		//$scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creamsUsuarios = res.data.creamodifica;
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
						.title('Lista de usuarios')
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
		return listamsusuariosUrl+order+limit+page+filtroparametro;
	}

	$scope.editmsUsuario = function () {
		$scope.nuevo = true;
		var idusuario = $routeParams.idusuario;
		if(idusuario){
			$scope.cargarmsusuarios(idusuario);
		}
		$scope.loadListaRoles();
		$scope.loadListaSedes();
		$scope.loadListaUOTree();
		$scope.loadListaUO();
	};

	$scope.filtro ={
			idusuario : null,
			username : null,
			nombres : null,
			apellidoPaterno : null,
			apellidoMaterno : null,
			idunidadTxt: null,
			sede : null,
			roles : null,
			estado: null,
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadmsUsuarios();
		}
	};

	$scope.msUsuariosModelo = {
			idusuario : null,
			username : null,
			nombres : null,
			apellidoPaterno : null,
			apellidoMaterno : null,
			iduserModifTxt : null,
			rolesSistema : [],
			contrasenia : null,
			contraseniaConfir : null,
			sede : null,
			idunidad: null,
			anexo: null,
			celular: null,
			correo:null,
			editopcion: 1
	};

	$scope.clearmsusuarios = function(){
		$scope.msUsuariosModelo.idusuario = null;
		$scope.msUsuariosModelo.username = null;
		$scope.msUsuariosModelo.nombres = null;
		$scope.msUsuariosModelo.apellidoPaterno = null;
		$scope.msUsuariosModelo.apellidoMaterno = null;
		$scope.msUsuariosModelo.iduserModifTxt = null;
		$scope.msUsuariosModelo.rolesSistema = [];
		$scope.msUsuariosModelo.contrasenia = null;
		$scope.msUsuariosModelo.contraseniaConfir = null;	 
		$scope.msUsuariosModelo.sede = null;
		$scope.msUsuariosModelo.idunidad = null;
		$scope.msUsuariosModelo.editopcion = 1;
		$scope.msUsuariosModelo.anexo = null;
		$scope.msUsuariosModelo.celular = null;
		$scope.msUsuariosModelo.correo = null;
	}
	
	$scope.setMsUsuarios = function(msusuariosBk) {
		$scope.msUsuariosModelo.idusuario = msusuariosBk.idusuario;
		$scope.msUsuariosModelo.username = msusuariosBk.username;
		$scope.msUsuariosModelo.nombres = msusuariosBk.nombres;
		$scope.msUsuariosModelo.apellidoPaterno = msusuariosBk.apellidoPaterno;
		$scope.msUsuariosModelo.apellidoMaterno = msusuariosBk.apellidoMaterno;
		$scope.msUsuariosModelo.iduserModifTxt = msusuariosBk.iduserModifTxt;
		$scope.msUsuariosModelo.rolesSistema = msusuariosBk.rolesSistema;
		$scope.msUsuariosModelo.contrasenia = null;
		$scope.msUsuariosModelo.contraseniaConfir = null;
		$scope.msUsuariosModelo.sede = msusuariosBk.sede;
		$scope.msUsuariosModelo.idunidad = msusuariosBk.idunidad;
		$scope.msUsuariosModelo.editopcion = msusuariosBk.msUsuariosACL.editopcion;
		$scope.msUsuariosModelo.anexo = msusuariosBk.anexo;
		$scope.msUsuariosModelo.celular = msusuariosBk.celular;
		$scope.msUsuariosModelo.correo = msusuariosBk.correo;
	}
	
	// ////////////////////////////////////////////////
	$scope.editarMsUsuarios = function(ev, msusuariosBk) {
		$scope.setMsUsuarios(msusuariosBk);

		// window.location.href = '#!editar';
		$location.url('/editar/' + $scope.msUsuariosModelo.idusuario);
		$scope.nuevo = false;
	}

	$scope.nuevoMsUsuarios = function() {
		$scope.clearmsusuarios();
//		window.location.href = '#!nuevo';
		$location.url('/nuevo');
		$scope.nuevo = true;
	}

	$scope.cancelarMsUsuarios = function() {
		$scope.clearmsusuarios();
//		window.location.href = '#/!';
		$location.url('/');
	}

	$scope.salvarMsUsuarios = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msUsuariosModelo);// $httpParamSerializerJQLike($scope.nuevapersonamodel);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsusuariosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.datos.push(dato); 
			$scope.total = $scope.datos.length;

			$scope.setMsUsuarios(dato);
			
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR USUARIO.')
					.textContent('EL USUARIO SE GUARDO CORRECTAMENTE')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
					.targetEvent(ev)
			);
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Nuevo usuario')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminarmsusuarios = function(ev,msusuariosBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(msusuariosBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarmsusuariosUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idusuario === dato.idusuario);
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
						.title('Eliminar usuario')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargarmsusuarios = function(idusuario){		
		var surl = editarmsusuariosUrl+idusuario;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setMsUsuarios(dato);
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
						.title('Eliminar usuario')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};
	
	$scope.showConfirm = function(ev, msusuariosBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Usuario')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarmsusuarios(ev, msusuariosBk);
		}, function () {
			$scope.status = 'NO';
		});
	};		  

	// ////////////////////////////////////////////////////////////////
	$scope.showNuevaMsUsuariosDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#myNuevaPesona',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.showMsUsuariosDialog = function(ev, msusuariosBk) {
		$scope.setMsUsuarios(msusuariosBk);
		$mdDialog.show({
			contentElement: '#myNuevaPesona',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};		  


	// ////////////////////////////////
	$scope.listaroles=[];
	$scope.loadListaRoles=function(){
		$http.get(listaRolesUrl).then(function(res){
			$scope.listaroles = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.listasedes=[];
	$scope.loadListaSedes=function(){
		$http.get(listaSedesUrl).then(function(res){
			$scope.listasedes = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
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

	$scope.uoCodToSearch = null;
	$scope.searchUOByCod = function(key, inputArray){
		for (var i=0; i < inputArray.length; i++) {
			if (inputArray[i].codigo === key) {
				return inputArray[i];
			}}
	};

	$scope.searchUOByCodigo = function(ev){
		if(!$scope.isString($scope.uoCodToSearch)){
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

		$scope.msUnidadesOrgDto = $scope.searchUOByCod($scope.uoCodToSearch, $scope.listaUO);
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

		$scope.msUsuariosModelo.idunidad = $scope.msUnidadesOrgDto.idunidad;
	};

	$scope.setIdUnidad = function(idunidad) {
		$scope.msUsuariosModelo.idunidad = idunidad;
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

	$scope.cancel = function() {
		$mdDialog.cancel();
	};

	// Returns if a value is really a number
	$scope.isNumber  = function(value) {
		return typeof value === 'number' && isFinite(value);
	};	

	$scope.nuevo = ($scope.isNumber($scope.msUsuariosModelo.idusuario) && $scope.msUsuariosModelo.idusuario > 0);
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

	$scope.filtroMsUsuarios = function(toma){				
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

	$scope.$watch('filtro.apellidoPaterno', function (newValue, oldValue) {
		console.log('filtro.apellidoPaterno ' + newValue+' -- '+oldValue);
	});

	$scope.refrescarusuario = function(ev){		
		var surl = refrescarmsusuariosUrl+$scope.msUsuariosModelo.idusuario;
		$http.get(surl).then(function(res){
			var dato = res.data;			
			console.log("SE REFRESCO "+ dato.username);
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Refrescar usuario')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});			        			        	
	};

	// /////////////////////////////////////////////////////////////////
	$scope.estructuta = {
			caracteresespeciales : 'ñq°!"#$%&¡¿?* {}()',
			carcteresacentos: 'áéíóúÁÉÍÓÚ',
			caracteresdelInput: null,
	};

	$scope.test = function(){				
		var str = [];
		for(var p in $scope.estructuta)
			str.push(encodeURIComponent(p) + "=" + encodeURIComponent($scope.estructuta[p]));			        
		console.log(str.join("&"));
	};

	$scope.showAdvancedNuevaPersona = function(ev) {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'resources/dialogos/nuevapersona.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true
		})
		.then(function(answer) {
			console.log('You said the information was "' + answer + '".');
		}, function() {
			console.log('You cancelled the dialog.');
		});
	};

	function DialogController2($scope, $mdDialog) {
		$scope.hide2 = function() {
			$mdDialog.hide();
		};

		$scope.cancel2 = function() {
			$mdDialog.cancel();
		};

		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};
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

	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
		}
		$scope.loadmsUsuarios();
	};
}]);
