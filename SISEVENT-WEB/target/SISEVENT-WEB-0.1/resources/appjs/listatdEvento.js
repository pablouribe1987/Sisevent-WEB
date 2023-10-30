var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdEventoUrl = contexto+"/rs/ctrltdEvento/listatdEvento";
var inserttdEventoUrl = contexto+"/rs/ctrltdEvento/salvartdEvento";
var eliminartdEventoUrl = contexto+"/rs/ctrltdEvento/eliminartdEvento";
var editartdEventoUrl = contexto+"/rs/ctrltdEvento/editartdEvento/";
//var listaMsInstitucionesUrl = contexto+"/rs/ctrltdEvento/listaMsInstituciones";//MPINARES 01092023 - INICIO - SE COMENTA
//var listaMsPersonasUrl = contexto+"/rs/ctrltdEvento/listaMsPersonas";//MPINARES 01092023 - INICIO - SE COMENTA
//var listaPrtParametrosidparametroUrl = contexto+"/rs/ctrltdEvento/listaPrtParametrosidparametro/";//MPINARES 01092023 - INICIO - SE COMENTA
//var listaMsCategoriasidcategoriasUrl = contexto+"/rs/ctrltdEvento/listaMsCategoriasidcategorias/";//MPINARES 01092023 - INICIO - SE COMENTA
var situacionSTDUrl = contexto+"/rs/ctrltdEvento/situaciontramite/";//MPINARES 01092023 - INICIO
//MPINARES 01092023 - INICIO
var listaPrtParametrosdescripcionUrlTipoEvento = contexto+"/rs/ctrltdEvento/listaPrtParametrosTipoEvento";
var listaPrtParametrosdescripcionUrlTipoVuelo = contexto+"/rs/ctrltdEvento/listaPrtParametrosTipoVuelo";
listaUrlMsCategorias= contexto+"/rs/ctrltdEvento/listaMsCategorias";
listaUrlMsUsuarios= contexto+"/rs/ctrltdEvento/listaMsUsuarios";
//MPINARES 01092023 - INICIO

//vbaldeon 08092023 INICIO
var listaPrtParametrosdescripcionUrlTipoTarea = contexto+"/rs/ctrltdEvento/listaPrtParametrosDescripcionTipoTarea";
var listaUrlGrupo= contexto+"/rs/ctrltdEvento/listaGrupo";
var listaUrlGrupoFlujo= contexto+"/rs/ctrltdEvento/listaGrupoFlujo";
var eliminarTdFlujoBkUrl = contexto+"/rs/ctrltdEvento/eliminarTdFlujoBk";
var enviarAlertaUrl = contexto+"/rs/ctrltdEvento/enviarAlerta";
//vbaldeon 08092023 fin

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/editar/:idevent", {
		templateUrl : "paginas/editartdEvento.html",
		controller : "ctrlListatdEvento"
	})
	.when("/nuevo", {
		templateUrl : "paginas/editartdEvento.html",
		controller : "ctrlListatdEvento"
	})
	.otherwise({
		templateUrl : "paginas/vertdEvento.html",
		controller : "ctrlListatdEvento"  
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

myapp.controller('ctrlListatdEvento', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel',  function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel) {
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idevent',
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
		$scope.loadtdEventos();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadtdEventos();
	};
	$scope.creatdEvento = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;

	$scope.loadtdEventos = function () {
		//$scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creatdEvento = res.data.creamodifica;
			console.log("data " +$scope.datos.length+" DE "+ $scope.total);
			console.log("Tiempo respuesta BD tdEvento " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Lista de Evento')
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
		return listatdEventoUrl+order+limit+page+filtroparametro;
	}

	$scope.edittdEvento = function () {
		$scope.nuevo = true;
		var idevent = $routeParams.idevent;
		if(idevent){
			$scope.cargartdEvento(idevent);
		}
		///CARGAR COMPLEMENTOS 
		//MPINARES 01092023 - INICIO
//		$scope.loadListaMsInstituciones();//SELECT-BUSQUEDA
//		$scope.loadListaMsPersonas();//SELECT-BUSQUEDA
		$scope.loadListaPrtParametrosTipoEventoo();//SELECT 
//		$scope.loadListaGrupo();//vbaldeon 08092023 
		$scope.loadListaMsCategorias(); 
		$scope.loadListaMsUsuarios();
		$scope.loadListaPrtParametrosTipoVuelo();
		//MPINARES 01092023 - FIN

	};

	$scope.filtro ={
			idevent: null,
			titulo: null,
			fechaSoliIni: null,
			idcategorias: null,
			modalidad: null,
			estado: null,

			estado: null
	}; 

	$scope.seteestado = function(eestado) {
		if($scope.filtro.estado === eestado){
			$scope.filtro.estado = null;
		}else{
			$scope.filtro.estado = eestado;
		}
		$scope.loadtdEventos();
	};

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadtdEventos();
		}
	};

	$scope.tdEventoModelo = {
			idevent : null,
			titulo: null,
			fechaSoliIni: null,
			fechaSoliFin: null,
			porconfirmar: null,
			iddoc: null,
			numeroSid: null,
			numeroAnioSid: null,
			asuntohr: null,
			remiRazonSocial: null,
			remiIdprovee: null,
			remiNombresapellidos: null,
			remiIdperson: null,
			tipoevento: null,
			idcategorias: null,
			modalidad: null,

			// ADICIONALES
			remiRazonSocialTxt: null,
			remiIdproveeTxt: null,
			remiNombresapellidosTxt: null,
			remiIdpersonTxt: null,
			tipoeventoTxt: null,
			idcategoriasTxt: null,
			modalidadTxt: null,
			iduserCreaTxt: null,
			iduserModifTxt: null,
//			MPINARES 01092023 - INICIO
			numeroSid: null,
			numeroAnioSid: null,
			noexpediente: null,
			tdItinerarioJss: [],
			msCategoriasJss: [],
			msUsuariosJss: [],
//			MPINARES 01092023 - FIN

			idTipoTarea:null,//vbaldeon 08092023 
			idTipoTareaTxt:null,//vbaldeon 08092023 
			idGrupo:null,//vbaldeon 08092023 
			idGrupoTxt:null,//vbaldeon 08092023 
			tdFlujoBks:[],//vbaldeon 08092023 

			editopcion: 1
	};

	$scope.cleartdEvento = function(){
		$scope.tdEventoModelo.idevent = null;
		$scope.tdEventoModelo.titulo = null;
		$scope.tdEventoModelo.fechaSoliIni = null;
		$scope.tdEventoModelo.fechaSoliFin = null;
		$scope.tdEventoModelo.porconfirmar = null;
		$scope.tdEventoModelo.iddoc = null;
		$scope.tdEventoModelo.numeroSid = null;
		$scope.tdEventoModelo.numeroAnioSid = null;
		$scope.tdEventoModelo.asuntohr = null;
		$scope.tdEventoModelo.remiRazonSocial = null;
		$scope.tdEventoModelo.remiIdprovee = null;
		$scope.tdEventoModelo.remiNombresapellidos = null;
		$scope.tdEventoModelo.remiIdperson = null;
		$scope.tdEventoModelo.tipoevento = null;
		$scope.tdEventoModelo.idcategorias = null;
		$scope.tdEventoModelo.modalidad = null;

		// ADICIONALES
		$scope.tdEventoModelo.remiRazonSocialTxt = null;
		$scope.tdEventoModelo.remiIdproveeTxt = null;
		$scope.tdEventoModelo.remiNombresapellidosTxt = null;
		$scope.tdEventoModelo.remiIdpersonTxt = null;
		$scope.tdEventoModelo.tipoeventoTxt = null;
		$scope.tdEventoModelo.idcategoriasTxt = null;
		$scope.tdEventoModelo.modalidadTxt = null;
		$scope.tdEventoModelo.iduserCreaTxt = null;
		$scope.tdEventoModelo.iduserModifTxt = null;

		$scope.tdEventoModelo.idTipoTarea=null;//vbaldeon 08092023 
		$scope.tdEventoModelo.idTipoTareaTxt=null;//vbaldeon 08092023 
		$scope.tdEventoModelo.idGrupo=null;//vbaldeon 08092023 
		$scope.tdEventoModelo.idGrupoTxt=null;//vbaldeon 08092023 
		$scope.tdEventoModelo.tdFlujoBks=[];//vbaldeon 08092023

		$scope.tdEventoModelo.editopcion = 1;
	} 

	$scope.setTdEventoModelo = function(tdEventoBk) {
		$scope.tdEventoModelo.idevent = tdEventoBk.idevent;
		$scope.tdEventoModelo.titulo = tdEventoBk.titulo;
		//MPINARES 01092023 - INICIO 
		$scope.tdEventoModelo.fechaSoliIniJUD = tdEventoBk.fechaSoliIniJUD;
		if(!$scope.isNull($scope.tdEventoModelo.fechaSoliIniJUD) && !isNaN($scope.tdEventoModelo.fechaSoliIniJUD)){
			$scope.tdEventoModelo.fechaSoliIniJUD = new Date($scope.tdEventoModelo.fechaSoliIniJUD);
		};
		$scope.tdEventoModelo.fechaSoliFinJUD = tdEventoBk.fechaSoliFinJUD;
		if(!$scope.isNull($scope.tdEventoModelo.fechaSoliFinJUD) && !isNaN($scope.tdEventoModelo.fechaSoliFinJUD)){
			$scope.tdEventoModelo.fechaSoliFinJUD = new Date($scope.tdEventoModelo.fechaSoliFinJUD);
		};
//		$scope.tdEventoModelo.fechaSoliIni = tdEventoBk.fechaSoliIni;
//		$scope.tdEventoModelo.fechaSoliFin = tdEventoBk.fechaSoliFin;
		//MPINARES 01092023 - FIN 
		$scope.tdEventoModelo.porconfirmar = tdEventoBk.porconfirmar;
		$scope.tdEventoModelo.iddoc = tdEventoBk.iddoc;
		$scope.tdEventoModelo.numeroSid = tdEventoBk.numeroSid;
		$scope.tdEventoModelo.numeroAnioSid = tdEventoBk.numeroAnioSid;
		$scope.tdEventoModelo.asuntohr = tdEventoBk.asuntohr;
		$scope.tdEventoModelo.remiRazonSocial = tdEventoBk.remiRazonSocial;
		$scope.tdEventoModelo.remiIdprovee = tdEventoBk.remiIdprovee;
		$scope.tdEventoModelo.remiNombresapellidos = tdEventoBk.remiNombresapellidos;
		$scope.tdEventoModelo.remiIdperson = tdEventoBk.remiIdperson;
		$scope.tdEventoModelo.tipoevento = tdEventoBk.tipoevento;
		$scope.tdEventoModelo.idcategorias = tdEventoBk.idcategorias;
		$scope.tdEventoModelo.modalidad = tdEventoBk.modalidad;

		// ADICIONALES
		$scope.tdEventoModelo.remiRazonSocialTxt = tdEventoBk.remiRazonSocialTxt;
		$scope.tdEventoModelo.remiIdproveeTxt = tdEventoBk.remiIdproveeTxt;
		$scope.tdEventoModelo.remiNombresapellidosTxt = tdEventoBk.remiNombresapellidosTxt;
		$scope.tdEventoModelo.remiIdpersonTxt = tdEventoBk.remiIdpersonTxt;
		$scope.tdEventoModelo.tipoeventoTxt = tdEventoBk.tipoeventoTxt;
		$scope.tdEventoModelo.idcategoriasTxt = tdEventoBk.idcategoriasTxt;
		$scope.tdEventoModelo.modalidadTxt = tdEventoBk.modalidadTxt;
		$scope.tdEventoModelo.iduserCreaTxt = tdEventoBk.iduserCreaTxt;
		$scope.tdEventoModelo.iduserModifTxt = tdEventoBk.iduserModifTxt;

//		MPINARES 01092023 - INICIO 
		$scope.tdEventoModelo.tdItinerarioJss = tdEventoBk.tdItinerarioJss;
		if(tdEventoBk.tdItinerarioJss!=null && tdEventoBk.tdItinerarioJss.length>0){
			$scope.datosVuelos = [];
			for(var i = 0; i < tdEventoBk.tdItinerarioJss.length; i++)
			{
				var vueloa = tdEventoBk.tdItinerarioJss[i];
				$scope.adddatosVuelos(vueloa, i, tdEventoBk.tdItinerarioJss.length);
			}
		}

		$scope.tdEventoModelo.msCategoriasJss = tdEventoBk.msCategoriasJss;
		if(tdEventoBk.msCategoriasJss!=null && tdEventoBk.msCategoriasJss.length>0){
			$scope.msCategoriasSeleccionados = [];
			for(var i = 0; i < tdEventoBk.msCategoriasJss.length; i++)
			{
				var categoriaa = tdEventoBk.msCategoriasJss[i];
				$scope.msCategoriasSeleccionados.push(categoriaa);
			}
		}

		$scope.tdEventoModelo.msUsuariosJss = tdEventoBk.msUsuariosJss;
		if(tdEventoBk.msUsuariosJss!=null && tdEventoBk.msUsuariosJss.length>0){
			$scope.msUsuariosSeleccionados = [];
			for(var i = 0; i < tdEventoBk.msUsuariosJss.length; i++)
			{
				var usuarioo= tdEventoBk.msUsuariosJss[i];
				$scope.msUsuariosSeleccionados.push(usuarioo);
			}
		}

//		MPINARES 01092023 - FIN

		$scope.tdEventoModelo.idTipoTarea=tdEventoBk.idTipoTarea;//vbaldeon 08092023 
		$scope.tdEventoModelo.idTipoTareaTxt=tdEventoBk.idTipoTareaTxt;//vbaldeon 08092023 
		$scope.tdEventoModelo.idGrupo=tdEventoBk.idGrupo;//vbaldeon 08092023 
		$scope.tdEventoModelo.idGrupoTxt=tdEventoBk.idGrupoTxt;//vbaldeon 08092023 
		$scope.tdEventoModelo.tdFlujoBks=tdEventoBk.tdFlujoBks;//vbaldeon 08092023 

		//vbaldeon 08092023  inicio
		if(tdEventoBk.tdFlujoBks!=null && tdEventoBk.tdFlujoBks.length>0){
			$scope.datoFlujoBk = [];


			for(var i = 0; i < tdEventoBk.tdFlujoBks.length; i++)
			{
				var flujo = tdEventoBk.tdFlujoBks[i];
				$scope.addFlujoBks(flujo, i, tdEventoBk.tdFlujoBks.length);
			}

		}		  
		//vbaldeon 08092023 fins

		$scope.tdEventoModelo.editopcion = tdEventoBk.tdEventoACL.editopcion;
	}
	// ////////////////////////////////////////////////
	$scope.editarTdEvento = function(ev, tdEventoBk) {		  
		$scope.setTdEventoModelo(tdEventoBk);		  
		$location.url('/editar/' + $scope.tdEventoModelo.idevent);
		$scope.nuevo = false;
	}

	$scope.nuevoTdEvento = function() {
		$scope.cleartdEvento();
		$location.url('/nuevo');
		$scope.nuevo = true;
	}

	$scope.cancelarTdEvento = function() {
		$scope.cleartdEvento();
		$location.url('/');
	}

	$scope.salvarTdEvento = function(ev){		
		//vbaldeon 08092023 inicio		
		if($scope.isArray($scope.datoFlujoBk)){
			if($scope.datoFlujoBk.length>0){
				$scope.tdEventoModelo.tdFlujoBks = $scope.datoFlujoBk;
			}}
		//vbaldeon 08092023 fin
		//MPINARES 01092023 - INICIO 
		if($scope.isArray($scope.datosVuelos)){
			if($scope.datosVuelos.length>0){
				$scope.tdEventoModelo.tdItinerarioJss = $scope.datosVuelos;
			}};

			if($scope.isArray($scope.msUsuariosSeleccionados)){
				if($scope.msUsuariosSeleccionados.length>0){
					$scope.tdEventoModelo.msUsuariosJss = $scope.msUsuariosSeleccionados;
				}};

				if($scope.isArray($scope.msCategoriasSeleccionados)){
					if($scope.msCategoriasSeleccionados.length>0){
						$scope.tdEventoModelo.msCategoriasJss = $scope.msCategoriasSeleccionados;
					}};


					//MPINARES 01092023 - FIN
					ev.target.disabled = true;
					var datainsert = angular.toJson($scope.tdEventoModelo);
					console.log("datainsert = "+datainsert);	
					$http.post(inserttdEventoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
						var dato = res.data;

//						$scope.datos.push(dato); 
						$scope.total = $scope.datos.length;

						$scope.setTdEventoModelo(dato);

						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Guardar Evento')
								.textContent("DEL Evento se guardó correctamente.")
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
									.title('Guardar Evento')
									.textContent(dato.message)
									.ariaLabel('ERROR')
									.ok('OK')
									.targetEvent(ev)
							);
						}
					});		

					ev.target.disabled = false;
	};

	$scope.eliminartdEvento = function(ev,tdEventoBk){		
		ev.target.disabled = true;
		$scope.setTdEventoModelo(tdEventoBk);
		var datainsert = angular.toJson($scope.tdEventoModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminartdEventoUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idevent === dato.idevent);
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
						.title('Eliminar Evento')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargartdEvento = function(idevent){		
		var surl = editartdEventoUrl+idevent;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setTdEventoModelo(dato);
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
						.title('Cargar Evento')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, tdEventoBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Evento')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminartdEvento(ev, tdEventoBk);
		}, function () {
			$scope.status = 'NO';
		});
	};		  
//	/ADICIONALES
	//MPINARES 01092023 - INICIO 
	$scope.listaPrtParametrosTipoEventoo=[];
	$scope.loadListaPrtParametrosTipoEventoo=function(){
		$http.get(listaPrtParametrosdescripcionUrlTipoEvento).then(function(res){
			$scope.listaPrtParametrosTipoEventoo = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	$scope.changeTipoEventoo=function(){
		// /BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}

	$scope.listaPrtParametrosTipoVuelo=[];
	$scope.loadListaPrtParametrosTipoVuelo=function(){
		$http.get(listaPrtParametrosdescripcionUrlTipoVuelo).then(function(res){
			$scope.listaPrtParametrosTipoVuelo = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	$scope.changeTipoVuelo=function(){
		// /BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}

	//SELECT INICIO MsCategorias

	$scope.listaMsCategorias=[];
	$scope.loadListaMsCategorias=function(){
		$http.get(listaUrlMsCategorias).then(function(res){
			$scope.listaMsCategorias = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.changeMsCategorias=function(){
		///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}
	$scope.$watch('tdEventoModelo.idcategorias', function (newValue, oldValue) {
		console.log('tdEventoModelo.idcategorias ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});

	//SELECT FIN
	//SELECT INICIO MsCategorias  con herramientas multiples 
	$scope.msCategoriasSeleccionados = [];
	$scope.searchTextMsCategorias = '';

	$scope.buscarMsCategorias = function (query) {
		const results = $scope.listaMsCategorias.filter(grupo =>
		grupo.categoria.toLowerCase().includes(query.toLowerCase())
		);
		return results;
	};

	$scope.removerMsCategorias = function (grupo) {
		const index = $scope.msCategoriasSeleccionados.indexOf(grupo);
		if (index !== -1) {
			$scope.msCategoriasSeleccionados.splice(index, 1);
		}
	};
	//SELECT FIN

	//SELECT INICIO MsUsuarios
	$scope.listaMsUsuarios=[];
	$scope.loadListaMsUsuarios=function(){
		$http.get(listaUrlMsUsuarios).then(function(res){
			$scope.listaMsUsuarios = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.changeMsUsuarios=function(){
		///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}
	$scope.$watch('tdEventoModelo.idcategorias', function (newValue, oldValue) {
		console.log('tdEventoModelo.idcategorias ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});

	//SELECT FIN
	//SELECT INICIO MsUsuarios  con herramientas multiples 
	$scope.msUsuariosSeleccionados = [];
	$scope.searchTextMsUsuarios = '';

	$scope.buscarMsUsuarios = function (query) {
		const results = $scope.listaMsUsuarios.filter(grupo =>
		grupo.nombreCompleto.toLowerCase().includes(query.toLowerCase())
		);
		return results;
	};

	$scope.removerMsUsuarios = function (grupo) {
		const index = $scope.msUsuariosSeleccionados.indexOf(grupo);
		if (index !== -1) {
			$scope.msUsuariosSeleccionados.splice(index, 1);
		}
	};
	//SELECT FIN
	//MPINARES 01092023 - FIN

//	SELECT-BUSQUEDA INI
	$scope.listaMsInstituciones=[];
	$scope.loadListaMsInstituciones=function(){
		$http.get(listaMsInstitucionesUrl).then(function(res){
			$scope.listaMsInstituciones = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	$scope.filterItemsMsInstituciones= function(items) {
		if($scope.searchTermMsInstituciones.search.length<=0)
			return $scope.listaMsInstituciones;
		var result = [];
		angular.forEach(items, function(item) {
			// if item is something.. add to result
			if(item.razonSocial.toLowerCase().indexOf($scope.searchTermMsInstituciones.search.toLowerCase())>=0){
				result.push(item); 
			}
		});
		return result;
	};
	$scope.searchTermMsInstituciones = { search: '', remiRazonSocial: null };
	$scope.clearSearchTermMsInstituciones = function () {
		$scope.searchTermMsInstituciones.search = '';
	};

	$scope.showMsInstitucionesDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#msInstitucionesDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.dlgMsInstitucioneslimitOptions = [50,100, 500, 1000];
	$scope.dlgMsInstitucionesquery = {
			order: 'idprovee',
			limit: 50,
			page: 1
	};
	$scope.dlgMsInstitucionesselected = [];
	$scope.dlgMsInstitucionesoptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};
	$scope.dlgMsInstitucionesfiltro ={
			idprovee: null,
			razonSocial: null,
	};
	$scope.dlgMsInstitucionesDtoss = [];
	$scope.dlgMsInstitucionestotal = 0;

	$scope.dlgMsInstitucionespromise=$timeout(function (){
		if($scope.listaMsInstituciones.length==0){
			$scope.loadListaMsInstituciones();
		}else{
			$scope.dlgMsInstitucionesDtoss = $scope.listaMsInstituciones;
			$scope.dlgMsInstitucionestotal = $scope.listaMsInstituciones.length;
		}
	}, 500);

	$scope.dlgMsInstitucionesrefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
		}
	};
	$scope.filtrodlgMsInstituciones = function(msInstitucionesAct){
		var keys = Object.keys($scope.dlgMsInstitucionesfiltro);
		for (var key of keys) {
			var valor = $scope.dlgMsInstitucionesfiltro[key];
			try{
				if(valor == null ) continue;
				if(valor == '' ) continue;
				if($scope.isString(valor) && $scope.isString(msInstitucionesAct[key])){
					if(msInstitucionesAct[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
						return false;
					}									  
				}else if($scope.isString(valor)){
					return false;
				}
			}catch(e){
				console.log("Error = "+e);	
			}
		}
		return true;
	};

	$scope.seleccionadodlgMsInstituciones = function(ev) {
		$scope.dlgMsInstitucionesselected = [];
		if($scope.isObject(ev)){
			var dato = ev;
			$scope.tdEventoModelo.remiIdprovee = dato.idprovee;
			$scope.cancel();
		}
	};

	$scope.searchMsInstitucionesByRemiRazonSocial = function(ev){
		if(!$scope.isString($scope.searchTermMsInstituciones.remiRazonSocial)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato incorrecto!')
					.textContent('Ingrese el valor del RemiRazonSocial en el cuadro de busqueda.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		var msInstitucionesDto = $scope.findMsInstitucionesByRemiRazonSocial($scope.searchTermMsInstituciones.remiRazonSocial, $scope.listaMsInstituciones);
		if(!$scope.isObject(msInstitucionesDto)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato no encontrado!')
					.textContent('No se encontró ninguna MsInstituciones para el RemiRazonSocial ingresado.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		$scope.tdEventoModelo.remiIdprovee = msInstitucionesDto.idprovee;
	};

	$scope.findMsInstitucionesByRemiRazonSocial = function(key, inputArray){
		for (var i=0; i < inputArray.length; i++) {
			if (inputArray[i].remiRazonSocial === key) {
				return inputArray[i];
			}}
	};
//	SELECT-BUSQUEDA FIN
//	SELECT-BUSQUEDA INI
	$scope.listaMsPersonas=[];
	$scope.loadListaMsPersonas=function(){
		$http.get(listaMsPersonasUrl).then(function(res){
			$scope.listaMsPersonas = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	$scope.filterItemsMsPersonas= function(items) {
		if($scope.searchTermMsPersonas.search.length<=0)
			return $scope.listaMsPersonas;
		var result = [];
		angular.forEach(items, function(item) {
			// if item is something.. add to result
			if(item.apellidoPaterno.toLowerCase().indexOf($scope.searchTermMsPersonas.search.toLowerCase())>=0){
				result.push(item); 
			}
		});
		return result;
	};
	$scope.searchTermMsPersonas = { search: '', remiNombresapellidos: null };
	$scope.clearSearchTermMsPersonas = function () {
		$scope.searchTermMsPersonas.search = '';
	};

	$scope.showMsPersonasDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#msPersonasDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.dlgMsPersonaslimitOptions = [50,100, 500, 1000];
	$scope.dlgMsPersonasquery = {
			order: 'idperson',
			limit: 50,
			page: 1
	};
	$scope.dlgMsPersonasselected = [];
	$scope.dlgMsPersonasoptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};
	$scope.dlgMsPersonasfiltro ={
			idperson: null,
			apellidoPaterno: null,
	};
	$scope.dlgMsPersonasDtoss = [];
	$scope.dlgMsPersonastotal = 0;

	$scope.dlgMsPersonaspromise=$timeout(function (){
		if($scope.listaMsPersonas.length==0){
			$scope.loadListaMsPersonas();
		}else{
			$scope.dlgMsPersonasDtoss = $scope.listaMsPersonas;
			$scope.dlgMsPersonastotal = $scope.listaMsPersonas.length;
		}
	}, 500);

	$scope.dlgMsPersonasrefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
		}
	};
	$scope.filtrodlgMsPersonas = function(msPersonasAct){
		var keys = Object.keys($scope.dlgMsPersonasfiltro);
		for (var key of keys) {
			var valor = $scope.dlgMsPersonasfiltro[key];
			try{
				if(valor == null ) continue;
				if(valor == '' ) continue;
				if($scope.isString(valor) && $scope.isString(msPersonasAct[key])){
					if(msPersonasAct[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
						return false;
					}									  
				}else if($scope.isString(valor)){
					return false;
				}
			}catch(e){
				console.log("Error = "+e);	
			}
		}
		return true;
	};

	$scope.seleccionadodlgMsPersonas = function(ev) {
		$scope.dlgMsPersonasselected = [];
		if($scope.isObject(ev)){
			var dato = ev;
			$scope.tdEventoModelo.remiIdperson = dato.idperson;
			$scope.cancel();
		}
	};

	$scope.searchMsPersonasByRemiNombresapellidos = function(ev){
		if(!$scope.isString($scope.searchTermMsPersonas.remiNombresapellidos)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato incorrecto!')
					.textContent('Ingrese el valor del RemiNombresapellidos en el cuadro de busqueda.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		var msPersonasDto = $scope.findMsPersonasByRemiNombresapellidos($scope.searchTermMsPersonas.remiNombresapellidos, $scope.listaMsPersonas);
		if(!$scope.isObject(msPersonasDto)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Dato no encontrado!')
					.textContent('No se encontró ninguna MsPersonas para el RemiNombresapellidos ingresado.')
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}

		$scope.tdEventoModelo.remiIdperson = msPersonasDto.idperson;
	};

	$scope.findMsPersonasByRemiNombresapellidos = function(key, inputArray){
		for (var i=0; i < inputArray.length; i++) {
			if (inputArray[i].remiNombresapellidos === key) {
				return inputArray[i];
			}}
	};
//	SELECT-BUSQUEDA FIN
//	SELECT INI
	$scope.listaPrtParametrosTipoevento=[];
	$scope.loadListaPrtParametrosTipoevento=function(){
		$http.get(listaPrtParametrosidparametroUrl).then(function(res){
			$scope.listaPrtParametrosTipoevento = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	$scope.changeTipoevento=function(){
		///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}
	$scope.$watch('tdEventoModelo.tipoevento', function (newValue, oldValue) {
		console.log('tdEventoModelo.tipoevento ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});
//	SELECT FIN                
//	AUTOCOMPLETE INI
	$scope.ctrlMsCategoriasIdcategorias={
			simulateQuery: false,
			isDisabled: false,
			selectedItem: null
	};

	$scope.newStateMsCategoriasIdcategorias=function(state) {
		console.log("¡Lo siento! ¡Primero tendrás que crear una Constitución para " + state+"! ");
	}

	$scope.listaMsCategoriasIdcategorias=[];
	$scope.querySearchMsCategoriasIdcategorias = function(query) {
//		var results = query ? $scope.listaMsCategoriasIdcategorias.filter($scope.createFilterForMsCategoriasIdcategorias(query)) : $scope.listaMsCategoriasIdcategorias,
//		return results;
		var sUrl = listaMsCategoriasidcategoriasUrl+query;
		return $http.get(sUrl).then(function(res){
			$scope.listaMsCategoriasIdcategorias = res.data;
			return $scope.listaMsCategoriasIdcategorias;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.createFilterForMsCategoriasIdcategorias = function(query) {
		var upperCaseQuery = query.toUpperCase();
		return function filterFn(item) {
			return (item.categoria.indexOf(upperCaseQuery) === 0);
		};
	};

	$scope.searchTextChangeMsCategoriasIdcategorias = function(text) {
		console.log('Text changed to ' + text);
	}

	$scope.selectedItemChangeMsCategoriasIdcategorias = function(item) {
		if($scope.isObject(item)){
			console.log('Item changed to ' + JSON.stringify(item));
			$scope.tdEventoModelo.idcategorias = item.idcategorias;
			$scope.tdEventoModelo.idcategoriasTxt = item.categoria;
		}
	}
//	AUTOCOMPLETE FIN
	//MPINARES 01092023 - INICIO 
	$scope.adddatosVuelos = function (vueloa, i, size) {

		$scope.datosVuelos.push({
			contador: i+ 1, 
			iditinerario: vueloa.iditinerario,
			tipovuelo: vueloa.tipovuelo, 
			idtipoVueloTxt: vueloa.idtipoVueloTxt, 
			origendestino: vueloa.origendestino, 
			fechaSaliIni: new Date(vueloa.fechaSaliIni),
			fechaLlegFin: new Date(vueloa.fechaLlegFin)
		});

	}
	//MPINARES 01092023 - FIN

	//MPINARES 01092023 - INICIO
	$scope.expedienteModel = {
			anexos: null,
			asunto: null,
			fechahoradeiniciodetramite: null,
			fechahoradeldocumento: null,
			fechahorarecepcion: null,
			movimientoss: [],
			digitales: [],
			noexpediente: null,
			numerodedocumento: null,
			numerodefolios: null,
			situacionactual: null,
			remitente: null,
			tipodedocumento: null,
			idperson: null,
			idprovee: null,
			numeroSid: null,
			numeroAnioSid: null,
			expedienteiddoc: null
	}

	$scope.vueloModel = {
			contador:null,
			iditinerario:null,
			tipovuelo:null,
			idtipoVueloTxt: null,
			origendestino: null,
			fechaSaliIni: null,
			fechaLlegFin: null
	}

	$scope.clearvueloModel = function(){
		$scope.vueloModel.tipovuelo= "";
		$scope.vueloModel.idtipoVueloTxt= "";
		$scope.vueloModel.origendestino= "";
		$scope.vueloModel.fechaSaliIni= "";
		$scope.vueloModel.fechaLlegFin= "";
	}

	$scope.showdlgHRDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#idVincularHR',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.showdlgAddVuelo = function(ev) {	
		var objVuelo = { contador:$scope.datosVuelos.length + 1,iditinerario:null,tipovuelo: null, idtipoVueloTxt: null,origendestino: null,fechaSaliIni: null,fechaLlegFin: null };
		$scope.vueloModel=objVuelo;
		$mdDialog.show({
			contentElement: '#idAddVuelo',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.situaciondemitramite = function(ev){		
		var parametro1 = null;
		var parametro2 = null;
//		$scope.cleartdEvento();
		if(!$scope.isString($scope.tdEventoModelo.numeroSid) && 
				!$scope.isString($scope.tdEventoModelo.numeroAnioSid)
		){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Buscar situación de mi trámite')
					.textContent("No se ha ingresado texto en el año y/o número de expediente...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			if(!$scope.isString($scope.tdEventoModelo.numeroSid) ||  
					$scope.tdEventoModelo.numeroSid.length!=6 || 
					isNaN($scope.tdEventoModelo.numeroSid)){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent("El número ingresado no corresponde a un expediente...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev));
				return;
			}else if((!$scope.isString($scope.tdEventoModelo.numeroAnioSid) ||  
					$scope.tdEventoModelo.numeroAnioSid.length!=4 || 
					isNaN($scope.tdEventoModelo.numeroAnioSid)) && 
					!($scope.isNumber($scope.tdEventoModelo.numeroAnioSid) && 
							($scope.tdEventoModelo.numeroAnioSid>2011 && $scope.tdEventoModelo.numeroAnioSid<9999))){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent("El año ingresado no corresponde a un expediente...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev));
				return;
			}else{
				parametro1 = encodeURIComponent($scope.tdEventoModelo.numeroAnioSid);
				parametro2 = encodeURIComponent($scope.tdEventoModelo.numeroSid);
			}
		}		
		ev.target.disabled = true;
		var surl = situacionSTDUrl+parametro1+'/'+parametro2;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			$scope.expedienteModel = dato;
			if($scope.expedienteModel.movimientoss!=null && $scope.expedienteModel.movimientoss.length>0){
				var movimiento = $scope.expedienteModel.movimientoss[0];
				if(movimiento.anexos!=null && movimiento.anexos.length>0){
					$scope.expedienteModel.digitales=movimiento.anexos;
				}
			}
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("Buscar situación de mi trámite data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
			ev.target.disabled = false;
		});		
	};

	$scope.situaciondemitramiteV2 = function(ev){		
		var parametro1 = null;
		var parametro2 = null;
//		$scope.cleartdEvento();
		if(!$scope.isString($scope.tdEventoModelo.numeroSid) && 
				!$scope.isString($scope.tdEventoModelo.numeroAnioSid)
		){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Buscar situación de mi trámite')
					.textContent("No se ha ingresado el año y/o número de expediente...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			if(!$scope.isString($scope.tdEventoModelo.numeroSid) ||  
					$scope.tdEventoModelo.numeroSid.length!=6 || 
					isNaN($scope.tdEventoModelo.numeroSid)){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent("El número ingresado no corresponde a un expediente...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev));
				return;
			}else if((!$scope.isString($scope.tdEventoModelo.numeroAnioSid) ||  
					$scope.tdEventoModelo.numeroAnioSid.length!=4 || 
					isNaN($scope.tdEventoModelo.numeroAnioSid)) && 
					!($scope.isNumber($scope.tdEventoModelo.numeroAnioSid) && 
							($scope.tdEventoModelo.numeroAnioSid>2011 && $scope.tdEventoModelo.numeroAnioSid<9999))){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent("El año ingresado no corresponde a un expediente...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev));
				return;
			}else{
				parametro1 = encodeURIComponent($scope.tdEventoModelo.numeroAnioSid);
				parametro2 = encodeURIComponent($scope.tdEventoModelo.numeroSid);
			}
		}		
		ev.target.disabled = true;
		var surl = situacionSTDUrl+parametro1+'/'+parametro2;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			$scope.expedienteModel = dato;
			if($scope.expedienteModel.movimientoss!=null && $scope.expedienteModel.movimientoss.length>0){
				var movimiento = $scope.expedienteModel.movimientoss[0];
				if(movimiento.anexos!=null && movimiento.anexos.length>0){
					$scope.expedienteModel.digitales=movimiento.anexos;
				}
			}
			ev.target.disabled = false;
		},
		function error(errResponse) {
			console.log("Buscar situación de mi trámite data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar situación de mi trámite')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
			ev.target.disabled = false;
		});	

		$mdDialog.show({
			contentElement: '#idVerHR',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.agregarHR = function(ev, expedienteModel){		
		if(!$scope.isString(expedienteModel.noexpediente)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Vincular Hoja de Ruta')
					.textContent("No se han encontrado datos para vincular la Hoja de Ruta...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}		
		ev.target.disabled = true;
		$scope.tdEventoModelo.noexpediente=expedienteModel.noexpediente;
		$scope.tdEventoModelo.iddoc=expedienteModel.expedienteiddoc; 
		$scope.tdEventoModelo.numeroSid=expedienteModel.numeroSid;
		$scope.tdEventoModelo.numeroAnioSid=expedienteModel.numeroAnioSid;
		$scope.tdEventoModelo.asuntohr=expedienteModel.asunto;
		$scope.tdEventoModelo.remiIdprovee=expedienteModel.idprovee;

		if(!$scope.isNull($scope.tdEventoModelo.remiIdprovee)){
			$scope.tdEventoModelo.remiRazonSocial=expedienteModel.remitente;
		};
		if(!$scope.isNull($scope.tdEventoModelo.remiIdperson)){
			$scope.tdEventoModelo.remiNombresapellidos=expedienteModel.remitente;
		};
		$scope.cancel();
	};

	$scope.datosVuelos = [];

	$scope.agregarVueloEvento = function(ev){		
		if(!$scope.isString($scope.vueloModel.tipovuelo)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Agregar vuelo')
					.textContent("No ha seleccionado el tipo de vuelo...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		if(!$scope.isString($scope.vueloModel.origendestino)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Agregar vuelo')
					.textContent("No ha ingresado el Origen-destino...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		if($scope.isNull($scope.vueloModel.fechaSaliIni) || isNaN($scope.vueloModel.fechaSaliIni)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Agregar vuelo')
					.textContent("No ha seleccionado la fecha de salida...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		if($scope.isNull($scope.vueloModel.fechaLlegFin) || isNaN($scope.vueloModel.fechaLlegFin)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Agregar vuelo')
					.textContent("No ha seleccionado la fecha de llegada...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		ev.target.disabled = true;
//		var objVuelo = { idtipoVuelo: vueloModel.idtipoVuelo, idtipoVueloTxt: vueloModel.idtipoVueloTxt,origenDestino: vueloModel.origenDestino,fechasalida: vueloModel.fechasalida,fechallegada: vueloModel.fechallegada };

		$scope.datosVuelos.push({
			contador:$scope.vueloModel.contador,
			iditinerario: $scope.vueloModel.iditinerario,
			tipovuelo: $scope.vueloModel.tipovuelo,
			idtipoVueloTxt: $scope.vueloModel.idtipoVueloTxt, 
			origendestino: $scope.vueloModel.origendestino, 
			fechaSaliIni: $scope.vueloModel.fechaSaliIni, 
			fechaLlegFin: $scope.vueloModel.fechaLlegFin

		});
//		$scope.datosVuelos.push(objVuelo);
		$scope.cancel();
	};

	$scope.removeDatosVuelos = function (ev,dato) {
		$scope.datosVuelos = $scope.datosVuelos.filter(val => val.contador !== dato.contador);

	}
	//MPINARES 01092023 - FIN	

	//vbaldeon 08092023 inicio
	$scope.addFlujoBks = function (flujo, i, size) {
		var addx=false;
		if(i+1==size){
			addx= true;
		}else{
			addx= false;
		}
		$scope.datoFlujoBk.push({
			contador: i+ 1, 
			idflujo: flujo.idflujo,
			fechaDerivacion: (flujo.fechaDerivacion != null ? new Date(flujo.fechaDerivacion):null),
			idunidadDeriva: flujo.idunidadDeriva, 
			iduserDeriva: flujo.iduserDeriva, 
			idunidadAtiende: flujo.idunidadAtiende, 
			iduserAtiende: flujo.iduserAtiende,
			fechaAtencion: (flujo.fechaAtencion != null ? new Date(flujo.fechaAtencion):null),
			idEvento: flujo.idEvento, 
			idEventoTxt: flujo.idEventoTxt, //vbaldeon 25092023
			idTarea: flujo.idTarea, //vbaldeon 25092023
			idTareaTxt: flujo.idTareaTxt, //vbaldeon 25092023
			idunidadDerivaAcroTxt: flujo.idunidadDerivaAcroTxt,
			idunidadAtiendeTxt: flujo.idunidadAtiendeTxt,
			iduserAtiendeTxt: flujo.iduserAtiendeTxt,
			iduserDerivaTxt: flujo.iduserDerivaTxt,	 
			tiempoestadia:flujo.tiempoestadia,
			add: addx

		})

	}
	//vbaldeon 08092023 fin
	//vbaldeon 08092023 - INICIO
	$scope.showdlgAgregarTareaDialog = function(ev) {	

		$mdDialog.show({
			contentElement: '#idAgregarTarea',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};		
	//vbaldeon 08092023  - FIN
	//vbaldeon 08092023 INICIO
	//SELECT INICIO tipo de tarea

	$scope.listaPrtParametrosTipoTarea=[];
	$scope.loadListaPrtParametrosTipoTarea=function(){
		$http.get(listaPrtParametrosdescripcionUrlTipoTarea).then(function(res){
			$scope.listaPrtParametrosTipoTarea = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.changeTipoTarea=function(){
		///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}
	$scope.$watch('tdEventoModelo.idTipoTarea', function (newValue, oldValue) {
		console.log('tdEventoModelo.idTipoTarea ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});

	//SELECT FIN
	//SELECT INICIO grupo

	$scope.listaGrupo=[];
	$scope.loadListaGrupo=function(){
		$http.get(listaUrlGrupo).then(function(res){
			$scope.listaGrupo = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.changeGrupo=function(){
		///BLANQUEAR LOS CAMPOS QUE DEPENDEN DE ESTE SELECT
	}
	$scope.$watch('tdEventoModelo.idGrupo', function (newValue, oldValue) {
		console.log('tdEventoModelo.idGrupo ' + newValue+' -- '+oldValue);
		//CARGAR DATOS DEL SIGUIENTE SELECT
	});

	//SELECT FIN
	//SELECT INICIO grupo  con herramientas multiples 
	$scope.gruposSeleccionados = [];
	$scope.lstGrupoFlujo=[];
	$scope.searchText = '';
	$scope.searchGrupo='';


	$scope.datoFlujoBk = [];//vbaldeon 25092023 
	//vbaldeon 25092023 {contador: "", idflujo: "", fechaDerivacion: "", idunidadDeriva:"", iduserDeriva:"",idunidadAtiende:"",iduserAtiende:"",fechaAtencion:"", idEvento:"",idEventoTxt:"",idTarea:"", idTareaTxt:"",idunidadDerivaAcroTxt:"",idunidadAtiendeTxt:"",iduserAtiendeTxt:"",iduserDerivaTxt:"",tiempoestadia:"",add: true}    
	//vbaldeon 25092023   ];

	$scope.settingFlagAddAndRemoveDetFlujo = function () {
		let sizeDatoFlujo = $scope.datoFlujoBk.length;
		$scope.datoFlujoBk.map(function (obj) {

			if (obj.contador == sizeDatoFlujo) {
				obj.add = true;
			} else {
				obj.add = false;
			}

		});
	}



	$scope.nuevoFlujo = function () {
		$scope.datoFlujoBk.push({
			contador:"",
			idflujo: "",
			fechaDerivacion: "",
			idunidadDeriva:"",
			iduserDeriva: "",
			idunidadAtiende: "",
			iduserAtiende: "",
			fechaAtencion: "",
			idEvento: "",
			idEventoTxt: "",
			idTarea: "",
			idTareaTxt: "",
			idunidadDerivaAcroTxt: "",
			idunidadAtiendeTxt: "",
			iduserAtiendeTxt: "",
			iduserDerivaTxt: "",
			tiempoestadia:"",
			add: false
		})

		$scope.settingFlagAddAndRemoveDetFlujo();
	}

	$scope.removeFlujo = function (ev,dato) {
		if(dato.idflujo!=null && dato.idflujo>0){
			$scope.showConfirmFlujo(ev, dato);
		}else{
			$scope.datoFlujoBk = $scope.datoFlujoBk.filter(val => val.contador !== dato.contador);
		} 
	}
	$scope.enviarAlerta = function (ev,dato) {
		$scope.showConfirmAlerta(ev, dato);
	}

	$scope.showConfirmAlerta = function(ev, tdFlujoBk) {
		var confirm = $mdDialog.confirm()
		.title('Enviar Alerta')
		.textContent('¿Esta usted seguro de de enviar la alerta?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.realizarEnvioAlerta(ev, tdFlujoBk);
		}, function () {
			$scope.status = 'NO';
		});
	};

	$scope.realizarEnvioAlerta = function(ev,tdFlujoBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(tdFlujoBk);
		console.log("datainsert = "+datainsert);	
		$http.post(enviarAlertaUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Enviar Alerta')
					.textContent("Se envió correctament la alerta.")
					.ariaLabel('ERROR')
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
						.title('Enviar Alerta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
		//$scope.edittdEvento();
	};

	$scope.showConfirmFlujo = function(ev, tdFlujoBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Registro')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminarFlujo(ev, tdFlujoBk);
		}, function () {
			$scope.status = 'NO';
		});
	};




	$scope.eliminarFlujo = function(ev,tdFlujoBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(tdFlujoBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminarTdFlujoBkUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idflujo === dato.idflujo);
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
						.title('Eliminar Registro')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
		$scope.edittdEvento();
	};




	$scope.buscarGrupos = function (query) {
		const results = $scope.listaGrupo.filter(grupo =>
		grupo.grupo.toLowerCase().includes(query.toLowerCase())
		);
		return results;
	};

	$scope.removerGrupo = function (grupo) {
		const index = $scope.gruposSeleccionados.indexOf(grupo);
		if (index !== -1) {
			$scope.gruposSeleccionados.splice(index, 1);                 
		}
		$scope.actualizarProductos();
	};

	$scope.actualizarGrupoFlujo = function () { 
		$scope.lstGrupoFlujo = [];
		$scope.searchGrupo='';
		for (var i = 0; i < $scope.gruposSeleccionados.length; i++) {                 		  
			$scope.searchGrupo=$scope.searchGrupo+$scope.gruposSeleccionados[i].idgrupo+"/"
		}  

		if($scope.searchGrupo){
			$scope.searchGrupo = $scope.searchGrupo.substring(0, $scope.searchGrupo.length - 1);
			var surl = listaUrlGrupoFlujo+"?gruposSeleccionados="+$scope.searchGrupo;
			$http.get(surl).then(function(res){
				$scope.lstGrupoFlujo = res.data; 
			},
			function error(errResponse) {
				console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			});
		}else{
			$scope.lstGrupoFlujo=[];
		}
	};


	$scope.seleccionarGrupoFlujoComoPrincipal = function (grupoFlujo) {
		$scope.lstGrupoFlujo.forEach(p => {p.flagPrincipal = false;});
		grupoFlujo.flagPrincipal = true;



	};

	$scope.eliminarGrupoFlujo = function (grupoFlujo) {
		const index = $scope.lstGrupoFlujo.indexOf(grupoFlujo);
		if (index !== -1) {
			$scope.lstGrupoFlujo.splice(index, 1);
			//$scope.actualizarGrupoFlujo();
		}
	};

	$scope.agregarGrupo = function (grupo) {
		if (grupo!=null && $scope.gruposSeleccionados.indexOf(grupo) === -1) {
			$scope.gruposSeleccionados.push(grupo);
			$scope.actualizarGrupoFlujo();
		}
	};



	$scope.agregarFlujo = function (e) {                    	         
		if($scope.lstGrupoFlujo!=null && $scope.lstGrupoFlujo.length>0){
			$scope.datoFlujoBk = [];
			for(var i = 0; i < $scope.lstGrupoFlujo.length; i++){            					
				var grupoFlujo =  $scope.lstGrupoFlujo[i];            						            						            						
				var addx=false;
				if(i+1==$scope.lstGrupoFlujo.length){
					addx= true;
				}else{
					addx= false;
				}
				$scope.datoFlujoBk.push({
					contador: i+ 1, 
					idflujo: "",
					fechaDerivacion: new Date(),////vbaldeon 25092023 
					idunidadDeriva: "", 
					iduserDeriva: "", 
					idunidadAtiende: grupoFlujo.idunidadDestino, 
					iduserAtiende: grupoFlujo.iduserDestino,
					fechaAtencion:new Date(),////vbaldeon 25092023 
					idEvento: "", 
					idEventoTxt: "", 
					idTarea: grupoFlujo.idtareas, 
					idTareaTxt: grupoFlujo.idtareasTxt,
					idunidadDerivaAcroTxt: "",
					idunidadAtiendeTxt: grupoFlujo.idunidadDestinoTxt,
					iduserAtiendeTxt: grupoFlujo.iduserDestinoTxt,
					iduserDerivaTxt: "",	
					tiempoestadia:grupoFlujo.tiempoestadia,
					add: addx })
			}
		}

		$mdDialog.cancel();
	};

	/*     $scope.eliminarFlujo = function (flujo) {
                                    const index = $scope.lstFlujo.indexOf(flujo);
                                    if (index !== -1) {
                                  	  $scope.lstFlujo.splice(index, 1);

                                    }
                                  };*/
	//SELECT FIN                    
	//vbaldeon 08092023 fin
//	/FIN ADICIONALES			 			 
	// ////////////////////////////////////////////////////////////////

	$scope.cancel = function() {
		$mdDialog.cancel();
	};

	// Returns if a value is really a number
	$scope.isNumber  = function(value) {
		return typeof value === 'number' && isFinite(value);
	};	

	$scope.nuevo = ($scope.isNumber($scope.tdEventoModelo.idevent) && $scope.tdEventoModelo.idevent > 0);
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

	$scope.filtroTdEvento = function(toma){				
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
