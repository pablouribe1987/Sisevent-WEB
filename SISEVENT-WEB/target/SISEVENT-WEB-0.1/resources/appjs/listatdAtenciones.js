var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var listatdAtencionesUrl = contexto+"/rs/ctrltdAtenciones/listatdAtenciones/";
var inserttdAtencionesUrl = contexto+"/rs/ctrltdAtenciones/salvartdAtenciones";
var eliminartdAtencionesUrl = contexto+"/rs/ctrltdAtenciones/eliminartdAtenciones";
var editartdAtencionesUrl = contexto+"/rs/ctrltdAtenciones/editartdAtenciones/";
var buscarRucUrl = contexto+"/rs/ctrltdAtenciones/buscarruc/";
var buscarRzSocialUrl = contexto+"/rs/ctrltdAtenciones/buscarrzsocial/";
var buscarDNIUrl = contexto+"/rs/ctrltdAtenciones/buscarpersonadni/";
var buscarNombreUrl = contexto+"/rs/ctrltdAtenciones/buscarpersonanombre/";
var listaCargosUrl = contexto+"/rs/ctrltdAtenciones/listaCargos";
var listaCanalesUrl = contexto+"/rs/ctrltdAtenciones/listaCanales";
var listaTemasUrl = contexto+"/rs/ctrltdAtenciones/listaTemas";
var listaUnidadesUrl = contexto+"/rs/ctrltdAtenciones/listauotree";
var listaUOUrl = contexto+"/rs/ctrltdAtenciones/listaUoLista";
var uploadDocUrl = contexto+"/rs/ctrltdAtenciones/uploaddocumento";
var insertDocUrl = contexto+"/rs/ctrltdAtenciones/insertarchivo";
var listaCreaUrl = contexto+"/rs/ctrltdAtenciones/listaDeCreadores";
var asignarUrl = contexto+"/rs/ctrltdAtenciones/asignar";
var listaResponsableUrl = contexto+"/rs/ctrltdAtenciones/listaDeResponsables";
var derivarUrl = contexto+"/rs/ctrltdAtenciones/derivar";
var listaMovimientosUrl = contexto+"/rs/ctrltdAtenciones/listaDeMovimientos/";
var atenderUrl = contexto+"/rs/ctrltdAtenciones/atender";
var devolverUrl = contexto+"/rs/ctrltdAtenciones/devolver";
var notificarUrl = contexto+"/rs/ctrltdAtenciones/notificar";
var previewUrl = contexto+"/rs/ctrltdAtenciones/preview";
var finalizarUrl = contexto+"/rs/ctrltdAtenciones/finalizar";
var atenderYFinalizarUrl = contexto+"/rs/ctrltdAtenciones/atenderYFinalizar";
var listaSubTemasUrl = contexto+"/rs/ctrltdAtenciones/listaSubTemas/";
var derivarV2Url = contexto+"/rs/ctrltdAtenciones/derivarV2";

var listaPaisesUrl = contexto+"/rs/ctrltdAtenciones/listaPaises";
var ubigeodefectoUrl = contexto+"/rs/ctrltdAtenciones/ubigeodefecto";
var listaCoddptosUrl = contexto+"/rs/ctrltdAtenciones/listaCoddptos";
var listaCodprovUrl = contexto+"/rs/ctrltdAtenciones/listaCodprov/";
var listaCoddistUrl = contexto+"/rs/ctrltdAtenciones/listaCoddist/";
var insertmsInstitucionesUrl = contexto+"/rs/ctrltdAtenciones/salvarmsInstituciones";
var insertmsPersonasUrl = contexto+"/rs/ctrltdAtenciones/salvarmsPersonas";

var buscarxtelefonoUrl = contexto+"/rs/ctrltdAtenciones/buscarpersonatelefono/";
var situacionSTDUrl = contexto+"/rs/ctrltdAtenciones/situaciontramite/";
var buscarxnumeroUrl = contexto+"/rs/ctrltdAtenciones/buscarxnumero/";

var listaTiposEntidadUrl = contexto+"/rs/ctrltdAtenciones/listaTiposEntidad";
var anularUrl = contexto+"/rs/ctrltdAtenciones/anular";
var finalizarYDerivarUrl = contexto+"/rs/ctrltdAtenciones/finalizarYderivar";

/**
 * 
 */
myapp = angular.module('MyApp');

myapp.config(function($routeProvider) {
	$routeProvider
	.when("/movimientos/:idsacc", {
		templateUrl : "paginas/movimientosAtenciones.html",
		controller : "ctrlListatdAtenciones"
	})
	.when("/editar/:idsacc", {
		templateUrl : "paginas/editartdAtenciones.html",
		controller : "ctrlListatdAtenciones"
	})
	.when("/nuevo", {
		templateUrl : "paginas/editartdAtenciones.html",
		controller : "ctrlListatdAtenciones"
	})
	.otherwise({
		templateUrl : "paginas/vertdAtenciones.html",
		controller : "ctrlListatdAtenciones"  
	});
});

//MPINARES 27042022 - INICIO
myapp.directive("ckeditor", () => {
    return {
      require: '?ngModel',
      link: function (scope, element, attr, ngModel) {
        if (!ngModel) return;
         ClassicEditor
          .create(element[0])
          .then(editor => {
            editor.model.document.on('change:data', () => {
              ngModel.$setViewValue(editor.getData());
              // Only `$apply()` when there are changes, otherwise it will be an infinite digest cycle
              if (editor.getData() !== ngModel.$modelValue) {
                scope.$apply();
              }
            });
            ngModel.$render = () => {
              editor.setData(ngModel.$modelValue);
            };
            scope.$on('$destroy', () => {
              editor.destroy();
            });
          });
      }
    };
  });

myapp.filter("rawHtml", ['$sce', function($sce){
	  return function(val) {
	    return $sce.trustAsHtml(val);
	  };
	}]);
//MPINARES 27042022 - FIN

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

myapp.directive('ngFileModel', function () {
	return {
		scope:true,
		link: function (scope, element, attrs) {
			var letraatrib = attrs.letter==null?'':attrs.letter;
			element.bind('change', function (changeEvent) {                  		
				var files = changeEvent.target.files;
				// iterate files since 'multiple' may be specified on the
				// element
				for (var i = 0;i<files.length;i++) {
					var name = files[i].name.toLowerCase();
					if ( /\.(jpe?g|png|gif|html|pdf|msg|doc?x|xls?x)$/i.test(name) ) {
						cargar(files[i],letraatrib);               	
					}
				}                                       
			});

			function cargarBase64(archivo){
				var reader = new FileReader();
				reader.onload = function (loadEvent) {
					// emit event upward
					var ngFileModel = {
							lastModified: archivo.lastModified,
							lastModifiedDate: archivo.lastModifiedDate,
							name: archivo.name,
							tamanio: archivo.size,
							tipo: archivo.type,
							data: loadEvent.target.result
					};
					scope.$emit("fileSelected", ngFileModel);

				}
				reader.readAsDataURL(archivo);
			}

			function cargar(archivo,letraatrib){
				// emit event upward
				var ngFileModel = {
						idsacc: null,
						idflujo: null,
						lastmodified: archivo.lastModified,						
						filenameoriginal: archivo.name,
						filename: null,
						tamanio: archivo.size,
						tipo: archivo.type,
						data: archivo,
						letter:letraatrib
				};
				scope.$emit("fileSelected", ngFileModel);
			}
		},//FIN FUNCION LINK
	};
});

myapp.service('fileUploadServ', ['$http','$mdDialog', function ($http,$mdDialog) {
	this.uploadFileToUrl = function(posicion, archivo, uploadUrl){    	
		var reader = {};
		var slice_size = 1000 * 1024;
		var file = archivo.data;

		function start_upload() {
			reader = new FileReader();
			upload_file( 0 );
		}

		function upload_file( start ) {

			var next_slice = start + slice_size + 1;
			var blob = file.slice( start, next_slice );

			reader.onloadend = function(event) {
				if ( event.target.readyState !== FileReader.DONE ) {
					return;
				}
				$http( {
					url: uploadUrl,
					method: 'POST', 
					headers: { 'Content-Type': 'application/json' },
					transformRequest: angular.identity,
					data: angular.toJson({
						idsacc: archivo.idsacc,
						idflujo: archivo.idflujo,
						lastmodified: archivo.lastModified,						
						filenameoriginal: archivo.filenameoriginal,
						filename: archivo.filename,
						tamanio: archivo.tamanio,
						tipo: archivo.tipo,
						data: event.target.result 	
					})    				
				}).then(function (response){
					var data = response.data; 
					console.log( "SUCCESS: "+response);
					if(archivo.filename=== null)
						archivo.filename = data.filename;    					
					var size_done = start + slice_size;
					var percent_done = Math.floor( ( size_done / archivo.tamanio ) * 100 );    					
					if ( next_slice < archivo.tamanio ) {
						// Update upload progress
						document.getElementById('dbi-upload-progress'+archivo.letter+'_'+posicion).innerHTML = 'Uploading File - ' + percent_done + '%' ;
						// More to upload, call function recursively
						document.getElementById('progress-bar'+archivo.letter+'_'+posicion).style.width = percent_done+ '%';
						document.getElementById('progress-bar'+archivo.letter+'_'+posicion).innerHTML = percent_done+ '%';

						upload_file( next_slice );
					} else {
						// Update upload progress
						document.getElementById('dbi-upload-progress'+archivo.letter+'_'+posicion).innerHTML = 'Upload Complete!' ;
						document.getElementById('progress-bar'+archivo.letter+'_'+posicion).style.width = '100%';
						document.getElementById('progress-bar'+archivo.letter+'_'+posicion).innerHTML = '100%';
						archivo.data = null;
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
								.title('Cargar archivos')
								.textContent(dato.message)
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(event)
						);
					}
				})    			
			};
			reader.readAsDataURL(blob);
		}

		start_upload();

		function uuidv4() {
			return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
				var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
				return v.toString(16);
			});
		}

		function to2digit(n) {
			return ('00' + n).slice(-2);
		}
	}
}]);

myapp.controller('ctrlListatdAtenciones', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams', '$mdPanel','fileUploadServ', function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel, fileUploadSrv) { 
	'use strict';

	$scope.limitOptions = [100, 500, 1000, 5000];
	$scope.query = {
			order: 'idsacc',
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
		$scope.loadtdAtenciones();
	};

	$scope.logItem = function (item) {
		console.log(item.name, 'was selected');
	};

	$scope.logPagination = function (page, limit) {
		console.log('page: ', page);
		console.log('limit: ', limit);
		$scope.loadtdAtenciones();
	};
	$scope.creatdAtenciones = false;
	// ///////////////////////////////////////////
	$scope.datos = [];
	$scope.total = 0;
	$scope.opcionvista = null;
	$scope.cclase = null;

	$scope.loadtdAtenciones = function () {
		// $scope.promise = $timeout(function () {
		var surl = $scope.getURL();	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			if(res.data.contador>0)
				$scope.total = res.data.contador;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creatdAtenciones = res.data.creamodifica;
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
						.title('Lista de  Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}
		});			 
		// }, 500);
		$scope.currentNavItem = 'page1';
		
		$scope.rangeYearIni();
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

		var sopcionvista="";
		if(!$scope.isNull($scope.opcionvista) && !isNaN($scope.opcionvista)){
			if(elprimero){
				elprimero=false;
				sopcionvista = "?opcionvista="+$scope.opcionvista;
			}else{
				sopcionvista = "&opcionvista="+$scope.opcionvista;
			}
		}

		var scclase="";
		if(!$scope.isNull($scope.cclase) && $scope.isString($scope.cclase)){
			if(elprimero){
				elprimero=false;
				scclase = "?cclase="+$scope.cclase;
			}else{
				scclase = "&cclase="+$scope.cclase;
			}
		}
		
		//MPINARES 27042022 - INICIO
		var srazonSocial="";
		if($scope.searchEntidad == true){
			if(!$scope.isNull($scope.textoSearch) && $scope.isString($scope.textoSearch)){
				if(elprimero){
					elprimero=false;
					srazonSocial = "?razonSocial="+encodeURIComponent($scope.textoSearch);
				}else{
					srazonSocial = "&razonSocial="+encodeURIComponent($scope.textoSearch);
				}
			}
		}
		
		var snombreCompleto="";
		if($scope.searchPerson == true){
			if(!$scope.isNull($scope.textoSearch) && $scope.isString($scope.textoSearch)){
				if(elprimero){
					elprimero=false;
					snombreCompleto = "?nombreCompleto="+encodeURIComponent($scope.textoSearch);
				}else{
					snombreCompleto = "&nombreCompleto="+encodeURIComponent($scope.textoSearch);
				}
			}
		}
		//MPINARES 27042022 - FIN

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
		//MPINARES 27042022 - INICIO
//		return listatdAtencionesUrl+order+limit+page+sopcionvista+scclase+filtroparametro;
		return listatdAtencionesUrl+order+limit+page+sopcionvista+scclase+filtroparametro+srazonSocial+snombreCompleto;
		//MPINARES 27042022 - FIN
	}

	$scope.edittdAtenciones = function () {
		$scope.nuevo = true;
		$scope.currentNavItem = 'page1';
		var idsacc = $routeParams.idsacc;
		
		if(idsacc){			
			$scope.cargartdAtenciones(idsacc);
			$scope.loadListaCrea();
//			$scope.loadListaResponsable();
			$scope.situaciondemitramiteBground();
		}else{
			// /CARGAR COMPLEMENTOS
			$scope.loadListaUOTree();			
			$scope.loadlistaTemas();
			$scope.loadlistaCanales();
			$scope.loadlistaCargos();
			$scope.loadlistaTiposEntidad();
		}
		
		$scope.loadListaUO();
	};

	$scope.movimientostdAtenciones = function () {
		$scope.nuevo = true;
		$scope.currentNavItem = 'page2';
		var idsacc = $routeParams.idsacc;
		if(idsacc){
			$scope.cargartdAtenciones(idsacc);
			$scope.cargartdAtencionesMovimientos(idsacc);
			$scope.loadListaCrea();
			$scope.loadListaResponsable();
		}
	};

	$scope.filtro ={
			idsacc: null,
			numeroSacc: null,
			ruc: null,
			razonSocial: null,
			documentoNumero: null,
			nombres: null,
			numeroSid: null,
			tema: null,
			estado: null,
			sede: null,
			fcreadopor: null,
	        fderivadoauo: null,
	        fechaConsulta: null,
	        canal: null,
	        fderivadoauoG: null,
	        fderivadoa: null
	}; 

	$scope.refrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			$scope.loadtdAtenciones();
		}
	};

	$scope.tdAtencionesModelo = {
			idsacc : null,
			numeroSacc: null,
			numeroAnio: null,
			ruc: null,
			razonSocial: null,
			idperson: null,
			documentoNumero: null,
			apellidoPaterno: null,
			apellidoMaterno: null,
			nombres: null,
			correo: null,
			telefono: null,
			celular: null,
			cargo: 'OTROS',
			fechaConsultaJUD: new Date(),
			canal: null,
			iddoc: null,
			numeroSid: null,
			numeroAnioSid: null,
			tema: null,
			detalle: null,
			idprovee: null,
			tdAnexosJSss: [],
			editopcion: 1,
			idusuario: null,
			observacion: null,
			verNotificar: false,
			estadoTxt: null,
			detalleincidencia: null,
			subtema: null,
			accionAsignar: false,
			accionDerivar: false,
			tipoentidad: null,
			accionAnular: false,
	        accionEditar: false,
	        accionCrea: false
	};
	
	$scope.cleartdAtenciones = function(){
		$scope.tdAtencionesModelo.idsacc = null;
		$scope.tdAtencionesModelo.numeroSacc = null;
		$scope.tdAtencionesModelo.numeroAnio = null;
		$scope.tdAtencionesModelo.ruc = null;
		$scope.tdAtencionesModelo.razonSocial = null;
		$scope.tdAtencionesModelo.idperson = null;
		$scope.tdAtencionesModelo.documentoNumero = null;
		$scope.tdAtencionesModelo.apellidoPaterno = null;
		$scope.tdAtencionesModelo.apellidoMaterno = null;
		$scope.tdAtencionesModelo.nombres = null;
		$scope.tdAtencionesModelo.correo = null;
		$scope.tdAtencionesModelo.telefono = null;
		$scope.tdAtencionesModelo.celular = null;
		$scope.tdAtencionesModelo.cargo = 'OTROS';
		$scope.tdAtencionesModelo.fechaConsultaJUD = new Date();
		$scope.tdAtencionesModelo.canal = null;
		$scope.tdAtencionesModelo.iddoc = null;
		$scope.tdAtencionesModelo.numeroSid = null;
		$scope.tdAtencionesModelo.numeroAnioSid = null;
		$scope.tdAtencionesModelo.tema = null;
		$scope.tdAtencionesModelo.detalle = null;
		$scope.tdAtencionesModelo.idprovee = null;
		$scope.tdAtencionesModelo.tdAnexosJSss = [];
		$scope.tdAtencionesModelo.editopcion = 1;
		$scope.tdAtencionesModelo.idusuario = null;
		$scope.tdAtencionesModelo.observacion= null;
		$scope.tdAtencionesModelo.estadoTxt=null;
		$scope.tdAtencionesModelo.verNotificar= false;		
		$scope.tdAtencionesModelo.detalleincidencia = null;
	    $scope.tdAtencionesModelo.subtema = null;
	    $scope.tdAtencionesModelo.accionAsignar = false;
	    $scope.tdAtencionesModelo.accionDerivar = false;
	    $scope.tdAtencionesModelo.tipoentidad = null;
	    $scope.tdAtencionesModelo.accionAnular= false;
	    $scope.tdAtencionesModelo.accionEditar= false;
	    $scope.tdAtencionesModelo.accionCrea= false;
	    $scope.flujoAtiende = null;
	} 

	$scope.setTdAtencionesModelo = function(tdAtencionesBk) {
		$scope.tdAtencionesModelo.idsacc = tdAtencionesBk.idsacc;
		$scope.tdAtencionesModelo.numeroSacc = tdAtencionesBk.numeroSacc;
		$scope.tdAtencionesModelo.numeroAnio = tdAtencionesBk.numeroAnio;
		$scope.tdAtencionesModelo.ruc = tdAtencionesBk.ruc;
		$scope.tdAtencionesModelo.razonSocial = tdAtencionesBk.razonSocial;
		$scope.tdAtencionesModelo.idperson = tdAtencionesBk.idperson;
		$scope.tdAtencionesModelo.documentoNumero = tdAtencionesBk.documentoNumero;
		$scope.tdAtencionesModelo.apellidoPaterno = tdAtencionesBk.apellidoPaterno;
		$scope.tdAtencionesModelo.apellidoMaterno = tdAtencionesBk.apellidoMaterno;
		$scope.tdAtencionesModelo.nombres = tdAtencionesBk.nombres;
		$scope.tdAtencionesModelo.correo = tdAtencionesBk.correo;
		$scope.tdAtencionesModelo.telefono = tdAtencionesBk.telefono;
		$scope.tdAtencionesModelo.celular = tdAtencionesBk.celular;
		$scope.tdAtencionesModelo.cargo = tdAtencionesBk.cargo;		
		$scope.tdAtencionesModelo.fechaConsultaJUD = tdAtencionesBk.fechaConsultaJUD;
		if(!$scope.isNull($scope.tdAtencionesModelo.fechaConsultaJUD) && !isNaN($scope.tdAtencionesModelo.fechaConsultaJUD)){
			$scope.tdAtencionesModelo.fechaConsultaJUD = new Date($scope.tdAtencionesModelo.fechaConsultaJUD);
		}		
		$scope.tdAtencionesModelo.canal = tdAtencionesBk.canal;
		$scope.tdAtencionesModelo.iddoc = tdAtencionesBk.iddoc;
		$scope.tdAtencionesModelo.numeroSid = tdAtencionesBk.numeroSid;
		$scope.tdAtencionesModelo.numeroAnioSid = tdAtencionesBk.numeroAnioSid;
		$scope.tdAtencionesModelo.tema = tdAtencionesBk.tema;
		$scope.tdAtencionesModelo.detalle = tdAtencionesBk.detalle;
		$scope.tdAtencionesModelo.idprovee = tdAtencionesBk.idprovee;
		$scope.tdAtencionesModelo.tdAnexosJSss = tdAtencionesBk.tdAnexosBksssprimera;
		$scope.tdAtencionesModelo.editopcion = tdAtencionesBk.tdAtencionesACL.editopcion;
		$scope.tdAtencionesModelo.idusuario = null;
		$scope.tdAtencionesModelo.observacion= null;
		$scope.showmovimiento = tdAtencionesBk.tdAtencionesACL.showmovimiento;
		$scope.flujoAtiende = tdAtencionesBk.flujoAtiende;
		$scope.tdAtencionesModelo.respuesta = tdAtencionesBk.respuesta;
		$scope.tdAtencionesModelo.conincidencia = tdAtencionesBk.conincidencia;
		$scope.tdAtencionesModelo.verNotificar = tdAtencionesBk.tdAtencionesACL.verNotificar;
		$scope.tdAtencionesModelo.estadoTxt=tdAtencionesBk.estadoTxt;
		$scope.tdAtencionesModelo.detalleincidencia = tdAtencionesBk.detalleincidencia;
		$scope.tdAtencionesModelo.subtema = tdAtencionesBk.subtema;
		$scope.tdAtencionesModelo.accionAsignar = tdAtencionesBk.tdAtencionesACL.accionAsignar;
		$scope.tdAtencionesModelo.accionDerivar = tdAtencionesBk.tdAtencionesACL.accionDerivar;
		$scope.tdAtencionesModelo.tipoentidad = tdAtencionesBk.tipoentidad;
		$scope.tdAtencionesModelo.accionAnular= tdAtencionesBk.tdAtencionesACL.accionAnular;
	    $scope.tdAtencionesModelo.accionEditar= tdAtencionesBk.tdAtencionesACL.accionEditar;
	    $scope.tdAtencionesModelo.accionCrea=tdAtencionesBk.tdAtencionesACL.accionCrea;
	    $scope.tdAtencionesModelo.detalleView=tdAtencionesBk.detalleView;//MPINARES 27042022 - INICIO
	}

	// ////////////////////////////////////////////////
	$scope.editarTdAtenciones = function(ev, tdAtencionesBk) {
		$scope.setTdAtencionesModelo(tdAtencionesBk);
		$location.url('/editar/' + $scope.tdAtencionesModelo.idsacc);
		$scope.nuevo = false;
	}

	$scope.nuevoTdAtenciones = function() {
		$scope.cleartdAtenciones();
		$location.url('/nuevo');
		$scope.nuevo = true;
	}

	$scope.cancelarTdAtenciones = function() {
		$scope.cleartdAtenciones();
		$location.url('/');
	}

	$scope.modificarTdAtenciones = function() {
		$scope.loadListaUOTree();			
		$scope.loadlistaTemas();
		$scope.loadlistaCanales();
		$scope.loadlistaCargos();
		$scope.loadlistaTiposEntidad();
		$scope.searchText=$scope.tdAtencionesModelo.razonSocial;
		$scope.tdAtencionesModelo.editopcion = 1;
	}
	
	$scope.salvarTdAtenciones = function(ev){
		///VERIFICAR
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesModelo.tdAnexosJSss = $scope.archivos;
			}}
		////////////
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.tdAtencionesModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(inserttdAtencionesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
//			$scope.datos.push(dato); 
//			$scope.total = $scope.datos.length;
			$scope.setTdAtencionesModelo(dato);			
//			$scope.uploadDocFiles($scope.tdAtencionesModelo.idsacc);			
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Guardar consulta')
					.textContent('La consulta se guardó correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
					.targetEvent(ev)
			);			
			$scope.nuevo = false;
			$scope.archivos = [];
			$scope.loadListaCrea();
			$scope.loadListaResponsable();
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Guardar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.eliminartdAtenciones = function(ev,tdAtencionesBk){		
		ev.target.disabled = true;
		var datainsert = angular.toJson(tdAtencionesBk);
		console.log("datainsert = "+datainsert);	
		$http.post(eliminartdAtencionesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			var instrumentos = $scope.datos;
			var index = $scope.datos.findIndex(obj => obj.idsacc === dato.idsacc);
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
						.title('Eliminar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});			        			        	
		ev.target.disabled = false;
	};	

	$scope.cargartdAtenciones = function(idsacc){		
		var surl = editartdAtencionesUrl+idsacc;
		$http.get(surl).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);
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
						.title('Cargar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	$scope.showConfirm = function(ev, tdAtencionesBk) {
		var confirm = $mdDialog.confirm()
		.title('Eliminar Consulta')
		.textContent('Esta usted seguro de eliminar el registro?')
		.ariaLabel('Lucky day')
		.targetEvent(ev)
		.ok('Si')
		.cancel('No');

		$mdDialog.show(confirm).then(function () {
			$scope.status = 'SI';
			$scope.eliminartdAtenciones(ev, tdAtencionesBk);
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

	$scope.nuevo = ($scope.isNumber($scope.tdAtencionesModelo.idsacc) && $scope.tdAtencionesModelo.idsacc > 0);
	$scope.edit  = true;
	$scope.showmovimiento = false;

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

	$scope.filtroTdAtenciones = function(toma){				
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

	// /ADICIONALES
	$scope.dlgInstlimitOptions = [20,100, 500, 1000];
	$scope.dlgInstquery = {
			order: 'razonSocial',
			limit: 20,
			page: 1
	};
	$scope.dlgInstselected = [];
	$scope.dlgInstoptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};

	$scope.dlgInstfiltro ={
			razonSocial: null,
			siglas: null,
			ruc: null,
			telefono: null,
			direccion: null,
	};

	$scope.dlgInstmsInstitucionesDtoss = [];
	$scope.dlgInsttotal = 0;

	$scope.buscarxruc = function(ev){
		$scope.dlgInstmsInstitucionesDtoss=[];
		$scope.selectedItem = null;
		$scope.searchText = null;
		var surl = buscarRucUrl+$scope.tdAtencionesModelo.ruc;
		$scope.dlgInstpromise = $http.get(surl).then(function(res){
			var dato = res.data;
			if(dato.length==0){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('BUSCAR POR EL RUC')
						.textContent("NO SE ENCONTRARON DATOS CON "+$scope.tdAtencionesModelo.ruc)
						.ariaLabel('BUSQUEDA')
						.ok('OK')
						.targetEvent(ev)
				);
			}else
				if(dato.length==1){
					var institucion = dato[0];
					$scope.tdAtencionesModelo.ruc = institucion.ruc; 
					$scope.tdAtencionesModelo.razonSocial = institucion.razonSocial;
					$scope.tdAtencionesModelo.idprovee = institucion.idprovee;
					$scope.tdAtencionesModelo.tipoentidad = institucion.tipoentidad;
					$scope.selectedItem = institucion;
				}else{
					if(dato.length>1){
						$scope.dlgInstmsInstitucionesDtoss = dato;
						$scope.dlgInsttotal = dato.length;
						$scope.showdlgInstDialog(ev);
					}
				}					
		},
		function error(errResponse) {
			console.log("Buscar x Ruc data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar x Ruc - Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});				
	};	  

	$scope.dlgInstrefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			// $scope.loadtdAtenciones();
		}
	};	

	$scope.filtrodlgInst = function(intitucion){
		var keys = Object.keys($scope.dlgInstfiltro);
		for (var key of keys) {
			var valor = $scope.dlgInstfiltro[key];
//			console.log('Key '+key+' Valor '+valor);
			try{
				if(valor == null ) continue;
				if($scope.isString(valor) && $scope.isString(intitucion[key])){
					if(intitucion[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
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

	$scope.showdlgInstDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#institucionesDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.seleccionadodlgInstDialog = function(ev) {
		if($scope.isObject(ev)){
			var institucion = ev;
			$scope.tdAtencionesModelo.ruc = institucion.ruc; 
			$scope.tdAtencionesModelo.razonSocial = institucion.razonSocial;
			$scope.tdAtencionesModelo.idprovee = institucion.idprovee;
			$scope.tdAtencionesModelo.tipoentidad = institucion.tipoentidad;
			$scope.selectedItem = institucion;
			$scope.cancel();
		}
	};	

	$scope.selectedItem = null;
	$scope.searchText = null;
	$scope.searchTextChange = function(text){
		console.log('Text changed to ' + text);
	}

	$scope.selectedItemChange = function(ev) {
		if($scope.isObject(ev)){
			var institucion = ev;
			$scope.tdAtencionesModelo.ruc = institucion.ruc; 
			$scope.tdAtencionesModelo.razonSocial = institucion.razonSocial;
			$scope.tdAtencionesModelo.idprovee = institucion.idprovee;
			$scope.tdAtencionesModelo.tipoentidad = institucion.tipoentidad;
			$scope.selectedItem = institucion;
		}
	};	

	$scope.buscarxRzSocial = function(ev){		
		$scope.dlgInstmsInstitucionesDtoss=[];
		$scope.selectedItem = null;
		$scope.notiselectedItem = null;
		var surl = buscarRzSocialUrl+encodeURIComponent(ev);		
		if($scope.isString($scope.tdAtencionesModelo.tipoentidad) && $scope.tdAtencionesModelo.tipoentidad.length>2){
			surl = buscarRzSocialUrl+$scope.tdAtencionesModelo.tipoentidad+'/'+encodeURIComponent(ev);
		}
		$scope.dlgInstpromise = $http.get(surl).then(function(res){
			var dato = res.data;							 
//			if(dato.length==1){
//			var institucion = dato[0];
//			$scope.tdAtencionesModelo.ruc = institucion.ruc;
//			$scope.tdAtencionesModelo.razonSocial = institucion.razonSocial;
//			$scope.tdAtencionesModelo.idprovee = institucion.idprovee;
//			$scope.selectedItem = institucion;
//			}else{
			if(dato.length>0){
				$scope.dlgInstmsInstitucionesDtoss = dato;
				$scope.dlgInsttotal = dato.length;
			}
//			}
			return $scope.dlgInstmsInstitucionesDtoss; 
		},
		function error(errResponse) {
			console.log("Buscar x RazonSocial data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar x RazonSocial-Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});
		return $scope.dlgInstpromise;
	};
	
	$scope.buscarxRzSocialS = function(ev){		
		$scope.dlgInstmsInstitucionesDtoss=[];
		$scope.selectedItem = null;
		$scope.notiselectedItem = null;
		var surl = buscarRzSocialUrl+encodeURIComponent(ev);		
		$scope.dlgInstpromise = $http.get(surl).then(function(res){
			var dato = res.data;							 
			if(dato.length>0){
				$scope.dlgInstmsInstitucionesDtoss = dato;
				$scope.dlgInsttotal = dato.length;
			}
			return $scope.dlgInstmsInstitucionesDtoss; 
		},
		function error(errResponse) {
			console.log("Buscar x RazonSocial data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar x RazonSocial-Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});
		return $scope.dlgInstpromise;
	};

	$scope.newState = function(state) {
		alert("Sorry! You'll need to create a Constitution for " + state + " first!");
	}

	// ////
	$scope.dlgPersolimitOptions = [20,100, 500, 1000];
	$scope.dlgPersoquery = {
			order: 'apellidoPaterno',
			limit: 20,
			page: 1
	};
	$scope.dlgPersoselected = [];
	$scope.dlgPersooptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};

	$scope.dlgPersofiltro ={
			apellidoPaterno: null,
			apellidoMaterno: null,
			nombres: null,
			sexo: null,
			tipodocumento: null,
			documentoNumero: null,
			telefono: null,
			celular: null,
			direccion: null,
	};

	$scope.dlgPersomsmsPersonasDtoss = [];
	$scope.dlgPersototal = 0;

	$scope.buscarxdni = function(ev){
		$scope.dlgPersoselected = [];
		var surl = buscarDNIUrl+$scope.tdAtencionesModelo.documentoNumero;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			if(dato.length==0){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('BUSCAR POR EL DNI')
						.textContent("NO SE ENCONTRARON DATOS CON "+$scope.tdAtencionesModelo.documentoNumero)
						.ariaLabel('BUSQUEDA')
						.ok('OK')
						.targetEvent(ev)
				);
			}else
				if(dato.length==1){
					var institucion = dato[0];					
					$scope.tdAtencionesModelo.idperson = institucion.idperson;
					$scope.tdAtencionesModelo.documentoNumero = institucion.documentoNumero;
					$scope.tdAtencionesModelo.apellidoPaterno = institucion.apellidoPaterno;
					$scope.tdAtencionesModelo.apellidoMaterno = institucion.apellidoMaterno;
					$scope.tdAtencionesModelo.nombres = institucion.nombres;
					$scope.tdAtencionesModelo.correo = institucion.correo;
					$scope.tdAtencionesModelo.telefono = institucion.telefono;
					$scope.tdAtencionesModelo.celular = institucion.celular;					
				}else{
					if(dato.length>1){
						$scope.dlgPersomsmsPersonasDtoss = dato;
						$scope.dlgPersototal = dato.length;
						$scope.showdlgPersoDialog(ev);
					}
				}					
		},
		function error(errResponse) {
			console.log("Buscar x DNI data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar Persona - Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});				
	};

	$scope.dlgPersorefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			// $scope.loadtdAtenciones();
		}
	};	

	$scope.filtrodlgPerso = function(intitucion){
		var keys = Object.keys($scope.dlgPersofiltro);
		for (var key of keys) {
			var valor = $scope.dlgPersofiltro[key];
//			console.log('Key '+key+' Valor '+valor);
			try{
				if(valor == null ) continue;
				if($scope.isString(valor) && $scope.isString(intitucion[key])){
					if(intitucion[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
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

	$scope.showdlgPersoDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#personasDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.seleccionadodlgPersoDialog = function(ev) {
		$scope.dlgPersoselected = [];
		if($scope.isObject(ev)){
			var institucion = ev;
			$scope.tdAtencionesModelo.idperson = institucion.idperson;
			$scope.tdAtencionesModelo.documentoNumero = institucion.documentoNumero;
			$scope.tdAtencionesModelo.apellidoPaterno = institucion.apellidoPaterno;
			$scope.tdAtencionesModelo.apellidoMaterno = institucion.apellidoMaterno;
			$scope.tdAtencionesModelo.nombres = institucion.nombres;
			$scope.tdAtencionesModelo.correo = institucion.correo;
			$scope.tdAtencionesModelo.telefono = institucion.telefono;
			$scope.tdAtencionesModelo.celular = institucion.celular;
			$scope.cancel();
		}
	};

	$scope.buscarxnombre = function(ev){
		if(!$scope.isString($scope.tdAtencionesModelo.apellidoPaterno)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Buscar Persona - Consulta')
					.textContent("No se ha ingresado texto en el apellido paterno...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		var parametros = encodeURIComponent($scope.tdAtencionesModelo.apellidoPaterno);
		if($scope.isString($scope.tdAtencionesModelo.nombres) && $scope.tdAtencionesModelo.nombres.length>0){
			parametros = encodeURIComponent($scope.tdAtencionesModelo.nombres)+"/"+parametros;

			if($scope.isString($scope.tdAtencionesModelo.apellidoMaterno) && $scope.tdAtencionesModelo.apellidoMaterno.length>0){
				parametros = parametros+"/"+encodeURIComponent($scope.tdAtencionesModelo.apellidoMaterno);
			}
		}				
		var surl = buscarNombreUrl+parametros;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			if(dato.length==0){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('BUSCAR POR EL NOMBRE')
						.textContent("NO SE ENCONTRARON DATOS...")
						.ariaLabel('BUSQUEDA')
						.ok('OK')
						.targetEvent(ev)
				);
			}else
				if(dato.length==1){
					var institucion = dato[0];					
					$scope.tdAtencionesModelo.idperson = institucion.idperson;
					$scope.tdAtencionesModelo.documentoNumero = institucion.documentoNumero;
					$scope.tdAtencionesModelo.apellidoPaterno = institucion.apellidoPaterno;
					$scope.tdAtencionesModelo.apellidoMaterno = institucion.apellidoMaterno;
					$scope.tdAtencionesModelo.nombres = institucion.nombres;
					$scope.tdAtencionesModelo.correo = institucion.correo;
					$scope.tdAtencionesModelo.telefono = institucion.telefono;
					$scope.tdAtencionesModelo.celular = institucion.celular;					
				}else{
					if(dato.length>1){
						$scope.dlgPersomsmsPersonasDtoss = dato;
						$scope.dlgPersototal = dato.length;
						$scope.showdlgPersoDialog(ev);
					}
				}					
		},
		function error(errResponse) {
			console.log("Buscar x Nombre y Apellido Paterno data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar Persona - Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});				
	};

	$scope.buscarxNoTelefonico = function(ev){
		var parametro1 = null;
		var parametro2 = null;
		if(!$scope.isString($scope.tdAtencionesModelo.telefono) && 
				!$scope.isString($scope.tdAtencionesModelo.celular)
		){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Buscar Persona - Consulta')
					.textContent("No se ha ingresado texto en el teléfono y/o celular...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			if(!$scope.isString($scope.tdAtencionesModelo.telefono)){
				parametro1 = encodeURIComponent($scope.tdAtencionesModelo.celular);
				parametro2 = encodeURIComponent($scope.tdAtencionesModelo.celular);
			}else if(!$scope.isString($scope.tdAtencionesModelo.celular)){
				parametro1 = encodeURIComponent($scope.tdAtencionesModelo.telefono);
				parametro2 = encodeURIComponent($scope.tdAtencionesModelo.telefono);
			}else{
				parametro1 = encodeURIComponent($scope.tdAtencionesModelo.telefono);
				parametro2 = encodeURIComponent($scope.tdAtencionesModelo.celular);
			}
		}				
		var surl = buscarxtelefonoUrl+parametro1+'/'+parametro2;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			if(dato.length==0){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('BUSCAR POR EL TELÉFONO Y/O CELULAR')
						.textContent("NO SE ENCONTRARON DATOS...")
						.ariaLabel('BUSQUEDA')
						.ok('OK')
						.targetEvent(ev)
				);
			}else
				if(dato.length==1){
					var institucion = dato[0];					
					$scope.tdAtencionesModelo.idperson = institucion.idperson;
					$scope.tdAtencionesModelo.documentoNumero = institucion.documentoNumero;
					$scope.tdAtencionesModelo.apellidoPaterno = institucion.apellidoPaterno;
					$scope.tdAtencionesModelo.apellidoMaterno = institucion.apellidoMaterno;
					$scope.tdAtencionesModelo.nombres = institucion.nombres;
					$scope.tdAtencionesModelo.correo = institucion.correo;
					$scope.tdAtencionesModelo.telefono = institucion.telefono;
					$scope.tdAtencionesModelo.celular = institucion.celular;					
				}else{
					if(dato.length>1){
						$scope.dlgPersomsmsPersonasDtoss = dato;
						$scope.dlgPersototal = dato.length;
						$scope.showdlgPersoDialog(ev);
					}
				}					
		},
		function error(errResponse) {
			console.log("Buscar x Teléfono y/o celular data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar x Teléfono y/o celular')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});				
	};

	// //
	$scope.searchTermUO = '';
	$scope.clearSearchTerm = function () {
	      $scope.searchTermUO = '';
	    };	    
//	$element.find('input').on('keydown', function (ev) {
//	        ev.stopPropagation();
//	      });	
	$scope.filtroUO = function(msUnidadesOrgDto){
		if(!$scope.isString($scope.searchTermUO) || $scope.searchTermUO.length<=0)
			return true;
			if($scope.isString(msUnidadesOrgDto.descripcion)){
					if(msUnidadesOrgDto.descripcion.toUpperCase().indexOf($scope.searchTermUO.toUpperCase())<=-1){
						return false;
					}									  
				}else return false;
		return true;
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

	$scope.listaUOTree=[];
	$scope.loadListaUOTree=function(){
		$http.get(listaUnidadesUrl).then(function(res){
			$scope.listaUOTree = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.listaCargoss=[];
	$scope.loadlistaCargos=function(){
		$http.get(listaCargosUrl).then(function(res){
			$scope.listaCargoss = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.listaCanaless=[];
	$scope.loadlistaCanales=function(){
		$http.get(listaCanalesUrl).then(function(res){
			$scope.listaCanaless = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.listaTemass=[];
	$scope.loadlistaTemas=function(){
		$http.get(listaTemasUrl).then(function(res){
			$scope.listaTemass = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.listaSubTemass=[];
	$scope.loadlistaSubTemas=function(){
		$scope.listaSubTemass=[];
		if(!$scope.isString($scope.tdAtencionesModelo.tema))
			return;
		var surl = listaSubTemasUrl+$scope.tdAtencionesModelo.tema;
		$http.get(surl).then(function(res){
			$scope.listaSubTemass = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.$watch('tdAtencionesModelo.tema', function (newValue, oldValue) {
		console.log('tdAtencionesModelo.tema ' + newValue+' -- '+oldValue);
		$scope.loadlistaSubTemas();
	});
	
	$scope.listaTiposEntidads=[];
	$scope.loadlistaTiposEntidad=function(){
		$http.get(listaTiposEntidadUrl).then(function(res){
			$scope.listaTiposEntidads = res.data; 
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};
	
	$scope.clearTiposEntidad=function(){
		$scope.tdAtencionesModelo.tipoentidad = null;
		$scope.tdAtencionesModelo.ruc = null; 
		$scope.tdAtencionesModelo.razonSocial = null;
		$scope.tdAtencionesModelo.idprovee = null;
		$scope.selectedItem = null;
	}
	
	$scope.clearALCambiarTiposEntidad=function(){
//		$scope.tdAtencionesModelo.ruc = null; 
//		$scope.tdAtencionesModelo.razonSocial = null;
//		$scope.tdAtencionesModelo.idprovee = null;
//		$scope.selectedItem = null;
	}
	
	// FILE UPLOAD
	$scope.handlesubmit = function(){
		console.log("PROBANDO ENVIAR EL PRIMER ARCHIVO");
		console.log(JSON.stringify($scope.archivos));
		fileUploadSrv.uploadFileToUrl(0,$scope.archivos[0], insertDocUrl);
	}

	// a simple model to bind to and send to the server

	// an array of files selected
	$scope.archivos = [];

	$scope.handleremover = function(pos){
		$scope.archivos.splice(pos,1);			  
	}

	// listen for the file selected event
	$scope.$on("fileSelected", function (event, args) {
		$scope.$apply(function () {            
			// add the file object to the scope's files collection
			$scope.archivos.push(args);	            
			var pos = $scope.archivos.length-1;
			fileUploadSrv.uploadFileToUrl(pos,$scope.archivos[pos],insertDocUrl);

//			var data = arr.find( function( ele ) {
//			return ele.id === '21';
//			} );

//			if( data ) {
//			console.log( 'found' );
//			console.log(data); // This is entire object i.e. `item` not boolean
//			}

		});
	});

	// the save method
	$scope.uploadDocFiles = function(idsacc) {
		if($scope.archivos.length>0){
			for(var i = 0; i < $scope.archivos.length; i++)
			{
				var archivo = $scope.archivos[i].data;
				var archivoModel = {
						lastmodified: $scope.archivos[i].lastModified,
						filenameoriginal: $scope.archivos[i].name,
						tamanio: $scope.archivos[i].tamanio,
						tipo: $scope.archivos[i].tipo,
						idsacc: idsacc
				};
				var config = { headers: { 'Content-Type': undefined },
						transformResponse: angular.identity
				};

				var formData = new FormData();
				formData.append("modelo", angular.toJson(archivoModel));
				formData.append("archivo", archivo);

				$http.post(uploadDocUrl,formData,config).then(function(res){
					var dato = res.data;
					console.log("data " + dato );
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent(dato.message)
								.ariaLabel('ERROR')
								.ok('OK')
						);
					}
				});
			}
		}
	};

	$scope.currentNavItem = 'page1';

	$scope.setcclase = function(classe) {
		if($scope.cclase === classe){
			$scope.cclase = null;
		}else{
			$scope.cclase = classe;
		}
		$scope.loadtdAtenciones();
	};
	$scope.goto = function(page) {
		$scope.currentNavItem = page;
		if(page=='page1'){
			$scope.opcionvista = 1;
			$scope.loadtdAtenciones();
		}else if(page=='page2'){
			$scope.opcionvista = 2;
			$scope.loadtdAtenciones();
		}else if(page=='page3'){
			$scope.opcionvista = 4;
			$scope.loadtdAtenciones();
		}else if(page=='page4'){
			$scope.opcionvista = 5;
			$scope.loadtdAtenciones();
		}
		$scope.status = "Goto " + page;
	};

	/////////////Dialogo Asignar
	$scope.dlgCrealimitOptions = [20,100, 500, 1000];
	$scope.dlgCreaquery = {
			order: 'apellidoPaterno',
			limit: 20,
			page: 1
	};
	$scope.dlgCreaselected = [];
	$scope.dlgCreaoptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};

	$scope.dlgCreafiltro ={			
			nombres: null,
			apellidoPaterno: null,
			apellidoMaterno: null,
			idunidadTxt: null,
			sede: null
	};

	$scope.dlgCreatotal = 0;
	$scope.dlgCreapromise = null; 

	$scope.listaCrea=[];
	$scope.loadListaCrea=function(){
		$scope.dlgCreapromise = $http.get(listaCreaUrl).then(function(res){
			$scope.listaCrea = res.data; 
			$scope.dlgCreatotal = res.data.length;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.filtrodlgCrea = function(msUsuarios){
		var keys = Object.keys($scope.dlgCreafiltro);
		for (var key of keys) {
			var valor = $scope.dlgCreafiltro[key];
//			console.log('Key '+key+' Valor '+valor);
			try{
				if(valor == null ) continue;
				if($scope.isString(valor) && $scope.isString(msUsuarios[key])){
					if(msUsuarios[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
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

	$scope.dlgCreaDescripcion = null;
	$scope.showdlgCreaDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#asignaDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.seleccionadoCreaDialog = function(ev) {
		if($scope.isObject(ev)){
			$scope.tdAtencionesModelo.idusuario = ev.idusuario;
			$scope.tdAtencionesModelo.observacion= $scope.dlgCreaDescripcion;
			var datainsert = angular.toJson($scope.tdAtencionesModelo);
			console.log("datainsert = "+datainsert);	
			$http.post(asignarUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
				var dato = res.data;
				$scope.setTdAtencionesModelo(dato); 
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Asignar Consulta')
						.textContent('La Consulta se derivó correctamente.')
						.ariaLabel('INFORMACIÓN')
						.ok('OK')
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
							.title('Asignar Consulta')
							.textContent(dato.message)
							.ariaLabel('ERROR')
							.ok('OK')
					);
				}
			});		
			$scope.cancel();
		}
	};

	$scope.dlgCrearefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			//Refrescar si es necesario
		}
	};

	//////Responsables
	$scope.dlgResponsablelimitOptions = [10,100, 500, 1000];
	$scope.dlgResponsablequery = {
			order: 'apellidoPaterno',
			limit: 10,
			page: 1
	};
	$scope.dlgResponsableselected = [];
	$scope.dlgResponsableoptions = {
			rowSelection: true,
			multiSelect: false,
			autoSelect: true,
			decapitate: false,
			largeEditDialog: false,
			boundaryLinks: true,
			limitSelect: true,
			pageSelect: true
	};

	$scope.dlgResponsablefiltro ={			
			nombres: null,
			apellidoPaterno: null,
			apellidoMaterno: null,
			idunidadTxt: null,
			sede: null,
			idunidadAcroTxt: null
	};

	$scope.dlgResponsabletotal = 0;
	$scope.dlgResponsablepromise = null; 

	$scope.listaResponsable=[];
	$scope.loadListaResponsable=function(){
		$scope.dlgResponsablepromise = $http.get(listaResponsableUrl).then(function(res){
			$scope.listaResponsable = res.data; 
			$scope.dlgResponsabletotal = res.data.length;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		});
	};

	$scope.filtrodlgResponsable = function(msUsuarios){
		var keys = Object.keys($scope.dlgResponsablefiltro);
		for (var key of keys) {
			var valor = $scope.dlgResponsablefiltro[key];
//			console.log('Key '+key+' Valor '+valor);
			try{
				if(valor == null ) continue;
				if($scope.isString(valor) && $scope.isString(msUsuarios[key])){
					if(msUsuarios[key].toUpperCase().indexOf(valor.toUpperCase())<=-1){
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

	$scope.dlgResponsableDescripcion = null;
	$scope.showdlgResponsableDialog = function(ev) {		
		$scope.tdAtencionesModelo.idusuario=null;
		$mdDialog.show({
			contentElement: '#responsableDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};
	
	$scope.showdlgAreasDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#areasDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true
		});
	};
	$scope.seleccionadoResponsableDialog = function(ev) {
		if($scope.isObject(ev)){
			$scope.tdAtencionesModelo.idusuario = ev.idusuario;
			$scope.tdAtencionesModelo.observacion = $scope.dlgResponsableDescripcion;
			var datainsert = angular.toJson($scope.tdAtencionesModelo);
			console.log("datainsert = "+datainsert);	
			$http.post(derivarUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
				var dato = res.data;
				$scope.setTdAtencionesModelo(dato);				
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Derivar Consulta')
						.textContent('La Consulta se derivó correctamente.')
						.ariaLabel('INFORMACIÓN')
						.ok('OK')
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
							.title('Derivar Consulta')
							.textContent(dato.message)
							.ariaLabel('ERROR')
							.ok('OK')
					);
				}
			});		
			$scope.cancel();
		}
	};
	
	$scope.seleccionadoAreasDialog = function() {
		if($scope.isNumber($scope.tdAtencionesModelo.idusuario)){
			$scope.tdAtencionesModelo.observacion = $scope.dlgResponsableDescripcion;
			var datainsert = angular.toJson($scope.tdAtencionesModelo);
			console.log("datainsert = "+datainsert);	
			$http.post(derivarV2Url,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
				var dato = res.data;
				$scope.setTdAtencionesModelo(dato);				
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Derivar Consulta')
						.textContent('La Consulta se derivó correctamente.')
						.ariaLabel('INFORMACIÓN')
						.ok('OK')
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
							.title('Derivar Consulta')
							.textContent(dato.message)
							.ariaLabel('ERROR')
							.ok('OK')
					);
				}
			});		
			$scope.cancel();
		}
	};

	$scope.dlgResponsablerefrescarvista = function(ev){
		var keyCode = ev.which || ev.keyCode;
		if (keyCode === 13) {
			//Refrescar si es necesario
		}
	};	

	///////////VER MOVIMIENTOS
	$scope.gotoedit = function(page) {
		$scope.currentNavItem = page;
		if(page=='page1'){
			$location.url('/editar/' + $scope.tdAtencionesModelo.idsacc);
		}else if(page=='page2'){
			$location.url('/movimientos/' + $scope.tdAtencionesModelo.idsacc);
		}
		$scope.status = "Goto Edit " + page;
	};

	$scope.listaMovimientosR=[];
	$scope.movdatosEnvio=[];
	$scope.movdetalleAtencion=[];
	$scope.movdetalleNotificacion=[];
	$scope.cargartdAtencionesMovimientos = function(idsacc){		
		var surl = listaMovimientosUrl+idsacc;
		$http.get(surl).then(function(res){
			var dato = res.data;
			if($scope.isArray(dato) && dato.length>0){
				$scope.listaMovimientosR = dato;
				if(dato.length>1){					  
					for (var j =1; j <  dato.length; j++) {
						var tdflujo = dato[j];
						if(tdflujo.estado==2 || tdflujo.estado==3 || tdflujo.estado==4 
								|| tdflujo.estado==9 || tdflujo.estado==10){
							$scope.movdatosEnvio.push(tdflujo); 
						}else if(tdflujo.estado==6 || tdflujo.estado==8){
							$scope.movdetalleNotificacion.push(tdflujo); 
						}else{
							$scope.movdetalleAtencion.push(tdflujo);
						} 
					}}	
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
						.title('Cargar Movimientos Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}			           
		});			        			        	
	};

	///ATENDER
	$scope.tdAtencionesResptaJS = {
			idsacc : null,			
			respuesta : null,
			conincidencia : 0,
			correosnotif : null,
			cuerponotif : null,
			tdAnexosJSss: [],
			idusuario: null,
			observacion: null,
			correousuario: true,
			esEntidad: false,
			idprovee: null
	};		
	$scope.dlgAtenderSiIncidencia = "No"; 
	$scope.dlgAtenderIncidencia = null;
	$scope.dlgAtenderDescripcion = null;
	$scope.showdlgAtenderDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#AtenderDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true
		});
	};
	$scope.atender = function(ev) {			
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;
		if($scope.dlgAtenderSiIncidencia=='Si'){
			$scope.tdAtencionesResptaJS.conincidencia = 1;				
			if($scope.isNull($scope.dlgAtenderIncidencia) || 
					!$scope.isString($scope.dlgAtenderIncidencia) || 
					$scope.dlgAtenderIncidencia.length <=10){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Atender Consulta')
						.textContent("SE REQUIERE EL DETALLE DE LA INCIDENCIA...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
				return;
			}else{
				$scope.tdAtencionesResptaJS.respuesta = $scope.dlgAtenderIncidencia;
			}				
		}else{
			$scope.tdAtencionesResptaJS.conincidencia = 0;
		}
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesResptaJS.tdAnexosJSss = $scope.archivos;
			}}
		if($scope.isNull($scope.dlgAtenderDescripcion) || 
				!$scope.isString($scope.dlgAtenderDescripcion) || 
				$scope.dlgAtenderDescripcion.length <=10){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Atender Consulta')
					.textContent("SE REQUIERE LA RESPUESTA DE LA ATENCIÓN...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			$scope.tdAtencionesResptaJS.observacion = $scope.dlgAtenderDescripcion;
		}	
		////////////		
		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(atenderUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.cancel();				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Atender Consulta')
					.textContent('La consulta se atendio correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
			);
			$scope.archivos = [];
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Atender Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});	
	};
	
	///DEVOLVER
	$scope.dlgDevolverDescripcion = null;
	$scope.showdlgDevolverDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#DevolverDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.devolver = function(ev) {			
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;			
		if($scope.isNull($scope.dlgDevolverDescripcion) || 
				!$scope.isString($scope.dlgDevolverDescripcion) || 
				$scope.dlgDevolverDescripcion.length <=10){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Devolver Consulta')
					.textContent("SE REQUIERE EL MOTIVO DE LA DEVOLUCIÓN...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			$scope.tdAtencionesResptaJS.observacion = $scope.dlgDevolverDescripcion;
		}	
		////////////

		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(devolverUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.cancel();				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Derivar Consulta')
					.textContent('La Consulta se derivó correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
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
						.title('Devolver Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});	
	};

	///NOTIFICAR
	$scope.flujoAtiende = {
			observacion: null,
			tdAnexosBksss: []
	};
	$scope.showdlgNotificarDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#NotificarDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.dlgNotificarAddDigital = function(args){
		$scope.archivos.push(args);
	}			 

	$scope.dlgNotificarVerPreview = false;		
	$scope.dlgNotificarRetornar = function(){
		$scope.dlgNotificarVerPreview = false;
	}

	$scope.notiselectedItem = null;
	$scope.notiselectedItemChange = function(ev) {
		if($scope.isObject(ev)){
			var institucion = ev;
			$scope.tdAtencionesResptaJS.idprovee = institucion.idprovee;
			$scope.notiselectedItem = institucion;
			if($scope.isString(institucion.correo)){
	          if(!$scope.isString($scope.tdAtencionesResptaJS.correosnotif) || $scope.tdAtencionesResptaJS.correosnotif.length<=1){
	        	  $scope.tdAtencionesResptaJS.correosnotif = institucion.correo;
	          }
            }			
		}
	};
	
	///PREVIEW
	$scope.dlgNotificarpreviewload = function(ev){
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;
		if($scope.isNull($scope.tdAtencionesResptaJS.cuerponotif) || 
				!$scope.isString($scope.tdAtencionesResptaJS.cuerponotif) || 
				$scope.tdAtencionesResptaJS.cuerponotif <=10){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Notificar Consulta')
					.textContent("SE REQUIERE EL CUERPO DEL MENSAJE...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		//MPINARES 27042022 - INICIO
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesResptaJS.tdAnexosJSss = $scope.archivos;
			}}	
		//MPINARES 27042022 - FIN
		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(previewUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;				
			$scope.dlgNotificarVerPreview = true;
			$timeout(function () {
				var elementocuerpo = document.getElementById('iddlgNotificarpreview');
				elementocuerpo.innerHTML = dato;
			}, 500);								
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Atender Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});	

	}

	//NOTIFICAR
	$scope.notificar = function(ev) {			
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;
		if($scope.isNull($scope.tdAtencionesResptaJS.cuerponotif) || 
				!$scope.isString($scope.tdAtencionesResptaJS.cuerponotif) || 
				$scope.tdAtencionesResptaJS.cuerponotif <=10){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Notificar Consulta')
					.textContent("SE REQUIERE EL CUERPO DEL MENSAJE...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesResptaJS.tdAnexosJSss = $scope.archivos;
			}}			
		////////////
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(notificarUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.cancel();				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Notificar Consulta')
					.textContent('La Consulta se notificó correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
			);
			ev.target.disabled = false;
			$scope.archivos = [];
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Notificar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});			
	};

	//FINALIZAR
	$scope.dlgFinalizarSiIncidencia = "No"; 
	$scope.dlgFinalizarIncidencia = null;
	$scope.dlgFinalizarDescripcion = null;
	$scope.dlgFinalizarIdUnidad = null;
	
	$scope.showdlgFinalizarDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#finalizarDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true
		});
	};
	
	$scope.finalizar = function(ev) {			
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;
		if($scope.dlgFinalizarSiIncidencia=='Si'){
			$scope.tdAtencionesResptaJS.conincidencia = 1;				
			if($scope.isNull($scope.dlgFinalizarIncidencia) || 
					!$scope.isString($scope.dlgFinalizarIncidencia) || 
					$scope.dlgFinalizarIncidencia.length <=10){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Finalizar Consulta')
						.textContent("SE REQUIERE EL DETALLE DE LA INCIDENCIA...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
				return;
			}else{
				$scope.tdAtencionesResptaJS.respuesta = $scope.dlgFinalizarIncidencia;
			}				
		}else{
			$scope.tdAtencionesResptaJS.conincidencia = 0;
		}
		
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesResptaJS.tdAnexosJSss = $scope.archivos;
			}}			
		if($scope.isNull($scope.dlgFinalizarDescripcion) || 
				!$scope.isString($scope.dlgFinalizarDescripcion) || 
				$scope.dlgFinalizarDescripcion.length <=2){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Finalizar Consulta')
					.textContent("SE REQUIERE LA RESPUESTA DE LA FINALIZACIÓN...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			$scope.tdAtencionesResptaJS.observacion = $scope.dlgFinalizarDescripcion;
		}
		////////////
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(finalizarUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Finalizar Consulta')
					.textContent('La Consulta se finalizó correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
			);
			ev.target.disabled = false;
			$scope.archivos = [];
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Finalizar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});			
	};
	
	$scope.showdlgFinalizarYDerivarDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#finalizarYDerivarDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true
		});
	};
	
	$scope.finalizarYDerivar = function(ev) {
		if(!$scope.isNumber($scope.dlgFinalizarIdUnidad)){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Derivar llamada y Finalizar Consulta')
					.textContent("SE SELECCIONE LA UNIDAD ORGÁNICA DONDE SE DERIVA LA LLAMADA...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
		}else{
			$scope.tdAtencionesResptaJS.idusuario = $scope.dlgFinalizarIdUnidad;			
		}
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;
		if($scope.dlgFinalizarSiIncidencia=='Si'){
			$scope.tdAtencionesResptaJS.conincidencia = 1;				
			if($scope.isNull($scope.dlgFinalizarIncidencia) || 
					!$scope.isString($scope.dlgFinalizarIncidencia) || 
					$scope.dlgFinalizarIncidencia.length <=10){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Derivar llamada y Finalizar Consulta')
						.textContent("SE REQUIERE EL DETALLE DE LA INCIDENCIA...")
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
				return;
			}else{
				$scope.tdAtencionesResptaJS.respuesta = $scope.dlgFinalizarIncidencia;
			}				
		}else{
			$scope.tdAtencionesResptaJS.conincidencia = 0;
		}
		
		if($scope.isArray($scope.archivos)){
			if($scope.archivos.length>0){
				for(var i = 0; i < $scope.archivos.length; i++)
				{
					var archivo = $scope.archivos[i];
					if(archivo.filename!=null &&  archivo.data!=null){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}else if(archivo.filename===null && archivo.data!=null){					
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar archivos')
								.textContent("TODAVÍA SE ESTA CARGANDO EL ARCHIVO "+archivo.filenameoriginal+" ESPERE QUE CULMINE LA OPERACIÓN")
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
						return;
					}
				}
				$scope.tdAtencionesResptaJS.tdAnexosJSss = $scope.archivos;
			}}			
		if($scope.isNull($scope.dlgFinalizarDescripcion) || 
				!$scope.isString($scope.dlgFinalizarDescripcion) || 
				$scope.dlgFinalizarDescripcion.length <=2){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Derivar llamada y Finalizar Consulta')
					.textContent("SE REQUIERE LA RESPUESTA DE LA FINALIZACIÓN...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			$scope.tdAtencionesResptaJS.observacion = $scope.dlgFinalizarDescripcion;
		}
		////////////
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(finalizarYDerivarUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Derivar llamada y Finalizar Consulta')
					.textContent('La Consulta se finalizó correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
			);
			ev.target.disabled = false;
			$scope.archivos = [];
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Derivar llamada y Finalizar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
			ev.target.disabled = false;
		});			
	};

	///ANULAR
	$scope.dlgAnularDescripcion = null;
	$scope.showdlgAnularDialog = function(ev) {		
		$mdDialog.show({
			contentElement: '#AnularDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		});
	};

	$scope.anular = function(ev) {			
		$scope.tdAtencionesResptaJS.idsacc = $scope.tdAtencionesModelo.idsacc;			
		if($scope.isNull($scope.dlgAnularDescripcion) || 
				!$scope.isString($scope.dlgAnularDescripcion) || 
				$scope.dlgAnularDescripcion.length <=10){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Anular Consulta')
					.textContent("SE REQUIERE EL MOTIVO DE LA ANULACIÓN...")
					.ariaLabel('ERROR')
					.ok('OK')
					.targetEvent(ev)
			);
			return;
		}else{
			$scope.tdAtencionesResptaJS.observacion = $scope.dlgAnularDescripcion;
		}	
		////////////

		var datainsert = angular.toJson($scope.tdAtencionesResptaJS);
		console.log("datainsert = "+datainsert);	
		$http.post(anularUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setTdAtencionesModelo(dato);				
			$mdDialog.cancel();				
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Anular Consulta')
					.textContent('La Consulta se anulo correctamente.')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
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
						.title('Anular Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});	
	};

	///DIALOGOS

	$scope.isOpenxx = false;

	$scope.showdlgInstitucionDialog = function(ev) {		
		$scope.loadubigeodefecto();
		$scope.loadlistaPaises();
		$scope.loadlistaCoddptos();		
		$mdDialog.show({
			templateUrl: contexto+"/dialogos/instituciones.html",
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true,
			controller: mdDialogInstitucionCtrl,                	
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		}).then($scope.closeDialog, $scope.cancelDialod);
	};

	$scope.closeDialog = function() {
		console.log('Closed, do something...');
	}

	$scope.cancelDialod = function() {
		console.log('Canceled, do something else...');
	}

	$scope.listaPaisess=[];
	$scope.loadlistaPaises=function(){			
		if(!$scope.isArray($scope.listaPaisess) || $scope.listaPaisess.length<=0){
			$http.get(listaPaisesUrl).then(function(res){
				$scope.listaPaisess = res.data; 
			},
			function error(errResponse) {
				console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			});
		}
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

	$scope.ubigeodefectos=null;
	$scope.loadubigeodefecto=function(){
		if($scope.ubigeodefectos==null){
			$http.get(ubigeodefectoUrl).then(function(res){
				$scope.ubigeodefectos = res.data;			
			},
			function error(errResponse) {
				console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			});
		}
	};

	$scope.showdlgPersonas = function(ev) {
		$scope.loadubigeodefecto();
		$scope.loadlistaPaises();
		$scope.loadlistaCoddptos();		
		$mdDialog.show({
			templateUrl: contexto+"/dialogos/personas.html",
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true,
			controller: mdDialogPersonasCtrl,                	
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
		}).then($scope.closeDialog, $scope.cancelDialod);
	};

	///ESTADO DE LA HOJA DE RUTA
	$scope.expedienteModel = {
			anexos: null,
			asunto: null,
			fechahoradeiniciodetramite: null,
			fechahoradeldocumento: null,
			fechahorarecepcion: null,
			movimientoss: [],
			noexpediente: null,
			numerodedocumento: null,
			numerodefolios: null,
			situacionactual: null,
			tipodedocumento: null
	}
	
	$scope.clearExpedienteModel = function(){
		$scope.expedienteModel.anexos= null;
		$scope.expedienteModel.asunto= null;
		$scope.expedienteModel.fechahoradeiniciodetramite= null;
		$scope.expedienteModel.fechahoradeldocumento= null;
		$scope.expedienteModel.fechahorarecepcion= null;
		$scope.expedienteModel.movimientoss= [];
		$scope.expedienteModel.noexpediente= null;
		$scope.expedienteModel.numerodedocumento= null;
		$scope.expedienteModel.numerodefolios= null;
		$scope.expedienteModel.situacionactual= null;
		$scope.expedienteModel.tipodedocumento= null;
	}

	$scope.situaciondemitramite = function(ev){		
		var parametro1 = null;
		var parametro2 = null;
		$scope.clearExpedienteModel();
		if(!$scope.isString($scope.tdAtencionesModelo.numeroSid) && 
				!$scope.isString($scope.tdAtencionesModelo.numeroAnioSid)
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
			if(!$scope.isString($scope.tdAtencionesModelo.numeroSid) ||  
					$scope.tdAtencionesModelo.numeroSid.length!=6 || 
					isNaN($scope.tdAtencionesModelo.numeroSid)){
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
			}else if((!$scope.isString($scope.tdAtencionesModelo.numeroAnioSid) ||  
					$scope.tdAtencionesModelo.numeroAnioSid.length!=4 || 
					isNaN($scope.tdAtencionesModelo.numeroAnioSid)) && 
					!($scope.isNumber($scope.tdAtencionesModelo.numeroAnioSid) && 
							($scope.tdAtencionesModelo.numeroAnioSid>2011 && $scope.tdAtencionesModelo.numeroAnioSid<9999))){
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
				parametro1 = encodeURIComponent($scope.tdAtencionesModelo.numeroAnioSid);
				parametro2 = encodeURIComponent($scope.tdAtencionesModelo.numeroSid);
			}
		}		
		ev.target.disabled = true;
		var surl = situacionSTDUrl+parametro1+'/'+parametro2;
		$scope.dlgPersopromise = $http.get(surl).then(function(res){
			var dato = res.data;
			$scope.expedienteModel = dato;
			ev.target.disabled = false;
			$mdDialog.show({
				contentElement: '#verExpediente',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose: true
			});
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

	$scope.situaciondemitramiteBground = function(){		
		if($scope.expedienteModel.situacionactual==null){
			var parametro1 = null;
			var parametro2 = null;
			if(!$scope.isString($scope.tdAtencionesModelo.numeroSid) && 
					!$scope.isString($scope.tdAtencionesModelo.numeroAnioSid)
			){
				return;
			}else{
				if(!$scope.isString($scope.tdAtencionesModelo.numeroSid) ||  
						$scope.tdAtencionesModelo.numeroSid.length!=6 || 
						isNaN($scope.tdAtencionesModelo.numeroSid)){
					return;
				}else if((!$scope.isString($scope.tdAtencionesModelo.numeroAnioSid) ||  
						$scope.tdAtencionesModelo.numeroAnioSid.length!=4 || 
						isNaN($scope.tdAtencionesModelo.numeroAnioSid)) && 
						!($scope.isNumber($scope.tdAtencionesModelo.numeroAnioSid) && 
								($scope.tdAtencionesModelo.numeroAnioSid>2011 && $scope.tdAtencionesModelo.numeroAnioSid<9999))){
					return;
				}else{
					parametro1 = encodeURIComponent($scope.tdAtencionesModelo.numeroAnioSid);
					parametro2 = encodeURIComponent($scope.tdAtencionesModelo.numeroSid);
				}
			}		
			var surl = situacionSTDUrl+parametro1+'/'+parametro2;
			$scope.dlgPersopromise = $http.get(surl).then(function(res){
				var dato = res.data;
				$scope.expedienteModel = dato;				
			},
			function error(errResponse) {
				console.log("Buscar situación de mi trámite data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);		           
			});
		}
	};
	
	$scope.$watch('tdAtencionesModelo.numeroAnioSid', function (newValue, oldValue) {
		console.log('tdAtencionesModelo.numeroAnioSid ' + newValue+' -- '+oldValue);
		if($scope.isString(newValue) && newValue.length==4){
			$scope.situaciondemitramiteBground();
		}else if($scope.isNumber(newValue) && newValue>2011 && newValue<9999){
			$scope.situaciondemitramiteBground();
		}
	});

///// Buscar X Numero
	$scope.$watch('numerosacc', function (newValue, oldValue) {
		console.log('numerosacc ' + newValue+' -- '+oldValue);		
	});
	
	$scope.numerosacc=null;
	$scope.numeroanio=new Date().getFullYear();
	$scope.rangeYear=[];
	$scope.rangeYearIni=function(){
		if($scope.isArray($scope.rangeYear) && $scope.rangeYear.length<=0){
			var max = new Date().getFullYear();
		    var min = 2020;
		    for (var i = max; i >= min; i--) {
		      $scope.rangeYear.push(i)
		  }}
		};	
		
		//MPINARES 27042022 - INICIO
		  $scope.showdlgBusquedaDialog = function(ev) {
			  	$scope.textoSearch = null;
			  	$scope.searchEntidad = true;
			  	$scope.searchPerson = true;
			    $mdDialog.show({
			      contentElement: '#BusquedaAvanzaDialog',
			      parent: angular.element(document.body),
			      targetEvent: ev,
			      clickOutsideToClose: true
			    });
			  };
			  
			  
		 $scope.busquedatdAtenciones = function () {
			 $scope.loadtdAtenciones();
			 $scope.cancel();
//			 $scope.textoSearch="";
		 };
		//MPINARES 27042022 - FIN
		 
	$scope.buscarXAnioNumero = function (ev){
		if(!$scope.isString($scope.numerosacc) || $scope.numerosacc.length<=0){
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('Buscar Consulta')
					.textContent("DEBE INGRESAR EL NÚMERO...")
					.ariaLabel('ERROR')
					.ok('OK')
			);
			return;
		}
		ev.target.disabled = true;		
		var surl = buscarxnumeroUrl+$scope.numeroanio+'/'+$scope.numerosacc;	    
		$scope.promise = $http.get(surl).then(function(res){
			$scope.datos = res.data.data;
			var tiempoenBD = res.data.tiempoenBD;
			var tiempoenproceso = res.data.tiempoenproceso;
			$scope.creatdAtenciones = res.data.creamodifica;
			$scope.total = res.data.contador;
			console.log("data " +$scope.datos.length+" DE "+ $scope.total);
			console.log("Tiempo respuesta BD " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
			ev.target.disabled = false;
			if($scope.datos.length==0){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('Buscar Consulta')
						.textContent("NO SE ENCONTRARON DATOS EN LA BUSQUEDA...")
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}else if($scope.datos.length==1){
				$scope.editarTdAtenciones(ev, $scope.datos[0]);
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
						.title('Buscar Consulta')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
				);
			}
			ev.target.disabled = false;
		});			 
		// }, 500);
		$scope.currentNavItem = 'page1';
	};
		
	///FIN CONTROLER ATENCION
}]);

var mdDialogInstitucionCtrl = function ($scope, $http, $mdDialog) { 

	$scope.msInstitucionesModelo = {
			idprovee : null,
			razonSocial: null,
			siglas: null,
			ruc: null,
			correo: null,
			web: null,
			telefono: null,
			fax: null,
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
			tipoentidad: null,
			
			editopcion: 1
	};

	if($scope.msInstitucionesModelo.codpais==null){
		$scope.msInstitucionesModelo.codpais=$scope.ubigeodefectos.xDefectoCodpais;
	}
	if($scope.msInstitucionesModelo.coddpto==null){
		$scope.msInstitucionesModelo.coddpto=$scope.ubigeodefectos.xDefectoCoddpto;
	}
	if($scope.msInstitucionesModelo.codprov==null){
		$scope.msInstitucionesModelo.codprov=$scope.ubigeodefectos.xDefectoCodprov;
	}
	if($scope.msInstitucionesModelo.coddist==null){
		$scope.msInstitucionesModelo.coddist=$scope.ubigeodefectos.xDefectoCoddist;
	}

	$scope.setMsInstitucionesModelo = function(msInstitucionesBk) {
		$scope.msInstitucionesModelo.idprovee = msInstitucionesBk.idprovee;
		$scope.msInstitucionesModelo.razonSocial = msInstitucionesBk.razonSocial;
		$scope.msInstitucionesModelo.siglas = msInstitucionesBk.siglas;
		$scope.msInstitucionesModelo.ruc = msInstitucionesBk.ruc;
		$scope.msInstitucionesModelo.correo = msInstitucionesBk.correo;
		$scope.msInstitucionesModelo.web = msInstitucionesBk.web;
		$scope.msInstitucionesModelo.telefono = msInstitucionesBk.telefono;
		$scope.msInstitucionesModelo.fax = msInstitucionesBk.fax;
		$scope.msInstitucionesModelo.codpais = msInstitucionesBk.codpais;
		$scope.msInstitucionesModelo.coddpto = msInstitucionesBk.coddpto;
		$scope.msInstitucionesModelo.codprov = msInstitucionesBk.codprov;
		$scope.msInstitucionesModelo.coddist = msInstitucionesBk.coddist;
		$scope.msInstitucionesModelo.direccion = msInstitucionesBk.direccion;

		$scope.msInstitucionesModelo.codpaisTxt= msInstitucionesBk.codpaisTxt;
		$scope.msInstitucionesModelo.coddptoTxt= msInstitucionesBk.coddptoTxt;
		$scope.msInstitucionesModelo.codprovTxt= msInstitucionesBk.codprovTxt;
		$scope.msInstitucionesModelo.coddistTxt= msInstitucionesBk.coddistTxt;

		$scope.msInstitucionesModelo.estado = msInstitucionesBk.estado;
		$scope.msInstitucionesModelo.editopcion = msInstitucionesBk.msInstitucionesACL.editopcion;
	};

	$scope.salvarMsInstituciones = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msInstitucionesModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsInstitucionesUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.setMsInstitucionesModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR INSTITUCIÓN')
					.textContent('LA INSTITUCIÓN SE GUARDÓ CORRECTAMENTE')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
					.targetEvent(ev)
			);

			$scope.tdAtencionesModelo.ruc = dato.ruc; 
			$scope.tdAtencionesModelo.razonSocial = dato.razonSocial;
			$scope.tdAtencionesModelo.idprovee = dato.idprovee;
			$scope.tdAtencionesModelo.tipoentidad = dato.tipoentidad;
			$scope.selectedItem = dato;
		},
		function error(errResponse) {
			console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
			var dato = errResponse.data;
			if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.body))
						.clickOutsideToClose(true)
						.title('GUARDAR INSTITUCIÓN')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});		

		ev.target.disabled = false;
	};

	$scope.listaCodprovs=[];
	$scope.loadlistaCodprov=function(){
		if($scope.msInstitucionesModelo.coddpto){
			var surl = listaCodprovUrl+$scope.msInstitucionesModelo.coddpto;
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
		if($scope.msInstitucionesModelo.coddpto &&
				$scope.msInstitucionesModelo.codprov){
			var surl = listaCoddistUrl+$scope.msInstitucionesModelo.coddpto+'/'+$scope.msInstitucionesModelo.codprov;
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
	$scope.$watch('msInstitucionesModelo.codpais', function (newValue, oldValue) {
		console.log('msInstitucionesModelo.codpais ' + newValue+' -- '+oldValue);
		if($scope.ubigeodefectos!=null){
			if($scope.ubigeodefectos.xDefectoCodpais == newValue){
				$scope.visualizarUbigeo = true;
			}else{
				$scope.visualizarUbigeo = false;
			}
		}else{
			$scope.loadubigeodefecto();
		}
	});

	$scope.$watch('msInstitucionesModelo.coddpto', function (newValue, oldValue) {
		console.log('msInstitucionesModelo.coddpto ' + newValue+' -- '+oldValue);
		$scope.loadlistaCodprov();
//		$scope.msInstitucionesModelo.codprov = null;
//		$scope.msInstitucionesModelo.coddist = null;
	});

	$scope.$watch('msInstitucionesModelo.codprov', function (newValue, oldValue) {
		console.log('msInstitucionesModelo.codprov ' + newValue+' -- '+oldValue);
		$scope.loadlistaCoddist();
//		$scope.msInstitucionesModelo.coddist = null;
	});

	$scope.changeCoddpto=function(){
		$scope.msInstitucionesModelo.codprov = null;
		$scope.msInstitucionesModelo.coddist = null;
	}

	$scope.changeCodprov=function(){
		$scope.msInstitucionesModelo.coddist = null;
	}
};

var mdDialogPersonasCtrl = function ($scope, $http, $mdDialog) {

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

	$scope.salvarMsPersonas = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msPersonasModelo);
		console.log("datainsert = "+datainsert);	
		$http.post(insertmsPersonasUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;

			$scope.setMsPersonasModelo(dato);

			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR PERSONA')
					.textContent('LA PERSONA SE GUARDÓ CORRECTAMENTE')
					.ariaLabel('INFORMACIÓN')
					.ok('OK')
					.targetEvent(ev)
			);

			$scope.tdAtencionesModelo.idperson = dato.idperson;
			$scope.tdAtencionesModelo.documentoNumero = dato.documentoNumero;
			$scope.tdAtencionesModelo.apellidoPaterno = dato.apellidoPaterno;
			$scope.tdAtencionesModelo.apellidoMaterno = dato.apellidoMaterno;
			$scope.tdAtencionesModelo.nombres = dato.nombres;
			$scope.tdAtencionesModelo.correo = dato.correo;
			$scope.tdAtencionesModelo.telefono = dato.telefono;
			$scope.tdAtencionesModelo.celular = dato.celular;

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
		if($scope.ubigeodefectos!=null){
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
	});

	$scope.$watch('msPersonasModelo.codprov', function (newValue, oldValue) {
		console.log('msPersonasModelo.codprov ' + newValue+' -- '+oldValue);
		$scope.loadlistaCoddist();
	});

	$scope.changeCoddpto=function(){
		$scope.msPersonasModelo.codprov = null;
		$scope.msPersonasModelo.coddist = null;
	}

	$scope.changeCodprov=function(){
		$scope.msPersonasModelo.coddist = null;
	}
}
