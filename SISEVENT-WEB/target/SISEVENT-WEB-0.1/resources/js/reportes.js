var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var reporteUrl = window.location.origin+contexto+'/reporte.html';
var principalUrl = window.location.origin+contexto+'/listadepersonas.html';
var listaSedesUrl = contexto+"/rs/tomas/listasedes";
var listaTomasUrl = contexto+"/rs/tomas/listatomasxfechaxsede";
var excelTomasUrl = contexto+"/rs/tomas/reporte";


/**
 * 
 */
var myapp = angular.module('MyAppReporte', ['ngMaterial', 'md.data.table']);

myapp.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';    
    $mdThemingProvider.theme('default')
      .primaryPalette('red'); 
    $mdThemingProvider.alwaysWatchTheme(true);
    $mdThemingProvider.theme('altTheme').primaryPalette('purple').accentPalette('green');  
    ////////////////////////////////////////////////////////////////////////////////////////////
    $mdThemingProvider.definePalette('mythemapaleta', {
      '50': 'ffebee',
      '100': 'ffcdd2',
      '200': 'ef9a9a',
      '300': 'e57373',
      '400': 'ef5350',
      '500': 'ffffff',
      '600': 'e53935',
      '700': 'd32f2f',
      '800': 'c62828',
      '900': 'b71c1c',
      'A100': 'ff8a80',
      'A200': 'ff5252',
      'A400': 'ff1744',
      'A700': 'd50000',
  'contrastDefaultColor': 'dark'
});
    $mdThemingProvider.theme('mythema').primaryPalette('mythemapaleta');
}]);

myapp.controller('reporteController', ['$scope', '$timeout', '$http', '$mdDialog', function ($scope, $timeout, $http, $mdDialog) {
	'use strict';
	
	 //LOAD SEDES
	 $scope.sedes = [];
	 $scope.loadSedes=function(){
		 $http.get(listaSedesUrl).then(function(res){
    		 $scope.sedes = res.data;
			},
			function error(errResponse) {
	            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
	        });
	 };
	 
	 $scope.selected = [];
	 $scope.limitOptions = [100, 500, 1000];
	 $scope.query = {
			    order: 'numDoc',
			    limit: 100,
			    page: 1
			  };
	 
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
	 	 
	 $scope.salir=function(){
	      window.location.href=pglogoff; 
	 };
	 
	 $scope.repote=function(){
		  window.location.href=reporteUrl; 
		 };
		 
	 $scope.principal=function(){
		  window.location.href=principalUrl; 
	 };	 
	 
	 $scope.exportarexecel=function(){
		 var excelTomasUrlFina = excelTomasUrl+"?sede="+($scope.filtroBusqueda.sede?$scope.filtroBusqueda.sede:"0")+($scope.filtroBusqueda.fechaInicio?"&fechaInicio="+$scope.filtroBusqueda.fechaInicio.getTime():"")+($scope.filtroBusqueda.fechaFin?"&fechaFin="+$scope.filtroBusqueda.fechaFin.getTime():"");
		 window.location.href=excelTomasUrlFina; 
	 };	
	 
	 $scope.tomaTemperaturaFiltro ={ //N°
					idtemp 		: null, //ID.REF. 
					temperatura : null, //MEDIDA
					//sede 		: null,
					//idtipodocum : null,
					//idpersona 	: null,
					fechaCrea   : null,//FECHA DE REGISTRO
					fechaCapturadni : null,//FECHA DE CAPTURA
					idpersona_txt : null,//NOMBRE
					iduserCrea_txt : null,//AUTOR
					sede_txt    : null,//SEDE
					idtipodocum_txt : null,//TIP.DOC.
					numDoc      : null,//NÚMERO
			};   
			
	  $scope.filtroTomaTemperatura = function(toma){
				const keys = Object.keys($scope.tomaTemperaturaFiltro);
				for (const key of keys) {
				  const valor = $scope.tomaTemperaturaFiltro[key];				  
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
					  }else if($scope.tomaTemperaturaFiltro[key]!=null && toma[key]==null){
						  return false;
					  }
				  }else if(typeof($scope.tomaTemperaturaFiltro[key])!='undefined' && typeof(toma[key])=='undefined'){
					  return false;
				  }
				  }catch(e){
					  console.log("Error = "+e);	
				  }
				}
				return true;
			};
		 
	 $scope.logOrder = function (order) {
	  console.log('order: ', order);
	 };
	 $scope.logItem = function (item) {
	  console.log(item.name, 'was selected');
	 };
	 $scope.logPagination = function (page, limit) {
	  console.log('page: ', page);
	  console.log('limit: ', limit);
     };
     
     var unday = 1000 * 60 * 60  * 24;
     $scope.filtroBusqueda = {
    		 sede : null,
    		 fechaInicio : new Date(((new Date()).getTime()-(7*unday))),
    		 fechaFin : new Date(),
     };
     
  // Number of milliseconds per day =
//   24 hrs/day * 60 minutes/hour * 60 seconds/minute * 1000 msecs/second
//var daysDiff = Math.floor(microSecondsDiff/(1000 * 60 * 60  * 24));

//console.log(daysDiff);
    ///////////////////////////////////////////// 
	$scope.datos = [];
	$scope.total = 0;
	$scope.loadTomas = function () {
	    $scope.promise = $timeout(function () {
	    	var listaTomasUrlFina = listaTomasUrl+"?sede="+($scope.filtroBusqueda.sede?$scope.filtroBusqueda.sede:"0")+($scope.filtroBusqueda.fechaInicio?"&fechaInicio="+$scope.filtroBusqueda.fechaInicio.getTime():"")+($scope.filtroBusqueda.fechaFin?"&fechaFin="+$scope.filtroBusqueda.fechaFin.getTime():"");
	    	console.log("-->"+listaTomasUrlFina);
	    	$http.get(listaTomasUrlFina).then(function(res){
	    		 $scope.datos = res.data;
	    		 $scope.total = $scope.datos.length;
				},
				function error(errResponse) {
		            console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
		            $scope.showAlert();
		        });			 
	     }, 2000);
	  };	  
	 
	  $scope.showAlert = function(ev) {
		    $mdDialog.show(
		      $mdDialog.alert()
		        .parent(angular.element(document.querySelector('#popupContainerDlg')))
		        .clickOutsideToClose(true)
		        .title('Medición de temperatura!')
		        .textContent('No hay información que cargar.')
		        .ariaLabel('Alerta')
		        .ok('OK')
		        .targetEvent(ev)
		    );
		  };
		    
		
			 $scope.cancel = function() {
			      $mdDialog.cancel();
			    };
			    
			$scope.$watch('tomaTemperaturaFiltro.temperatura', function (newValue, oldValue) {
//				console.log('tomaTemperaturaFiltro.temperatura ' + newValue+' -- '+oldValue);
			  });
			
			$scope.$watch('tomaTemperaturaFiltro.idpersona_txt', function (newValue, oldValue) {
//				console.log('tomaTemperaturaFiltro.idpersona_txt ' + newValue+' -- '+oldValue);
			  });
			
			$scope.$watch('tomaTemperaturaFiltro.numDoc', function (newValue, oldValue) {
//				console.log('tomaTemperaturaFiltro.numDoc ' + newValue+' -- '+oldValue);
			  });
			
			
				// Returns if a value is a string
				  function isString (value) {
				  return typeof value === 'string' || value instanceof String;
				  };
				  
				// Returns if a value is really a number
				  function isNumber (value) {
				  return typeof value === 'number' && isFinite(value);
				  }
				  
				// Returns if a value is an array
				  function isArray (value) {
				  return value && typeof value === 'object' && value.constructor === Array;
				  }
				  
				// Returns if a value is a function
				  function isFunction (value) {
				  return typeof value === 'function';
				  }
				  
				// Returns if a value is an object
				  function isObject (value) {
				  return value && typeof value === 'object' && value.constructor === Object;
				  }
				  
				// Returns if a value is null
				  function isNull (value) {
				  return value === null;
				  }

				  // Returns if a value is undefined
				  function isUndefined (value) {
				  return typeof value === 'undefined';
				  }
				  
				// Returns if a value is a boolean
				  function isBoolean (value) {
				  return typeof value === 'boolean';
				  }
				  
				// Returns if a value is a regexp
				  function isRegExp (value) {
				  return value && typeof value === 'object' && value.constructor === RegExp;
				  }
				  
				// Returns if value is an error object
				  function isError (value) {
				  return value instanceof Error && typeof value.message !== 'undefined';
				  }
				  
				// Returns if value is a date object
				  function isDate (value) {
				  return value instanceof Date;
				  }
				  
				// Returns if a Symbol
				  function isSymbol (value) {
				  return typeof value === 'symbol';
				  }
}]);
