var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var perfilMenuUrl = contexto+"/rs/ctrlmenu/perfilusuario";
var actualizarperfilUrl = contexto+"/rs/ctrlmenu/actualizarperfil";

myapp = angular.module('MyApp');

myapp.config(['$mdPanelProvider', function ($mdPanelProvider) {
	$mdPanelProvider.definePreset('menuPanel', {
		  attachTo: angular.element(document.body),
	      controller: ['mdPanelRef', function(mdPanelRef) {
	    	  this.closeMenu = function() {
	    	      mdPanelRef && mdPanelRef.close();
	    	    };
	    	  this.redireccionar = function(direccion){
	    		  var urldestino = direccion;
	    		  console.log("Menu " +urldestino);
	    		  window.location.href = urldestino;
	    	  };   
	    	  this.showMenu = function($event, menu) {
	    		  console.log("SHOW MENU "+menu);
	    		  this.$scope.$parent.$broadcast('showMenuRM', this.evento, menu);  
	    	  };
	      }],
	      controllerAs: 'ctrl',
	      template: '' +
	          '<div class="menu-panel" md-whiteframe="4" ng-mouseleave="ctrl.closeMenu()">' +	          
	          '  <div class="menu-content">' +
	          '    <div class="menu-item">' +
	          '      <md-button ng-click="ctrl.closeMenu()">' +
	          '        <span><b>{{ctrl.menuname}}</b></span>' +
	          '      </md-button>' +
	          '    </div>' +
	          '    <md-divider class="menu-divider"></md-divider>' +
	          '    <div class="menu-item" ng-repeat="item in ctrl.items">' +	          
	          '      <md-button href="{{item.refe}}" ng-disabled="{{!item.activo}}" ng-if="!item.submenu">' +
	          '        <span>{{item.name}}</span>' +
	          '      </md-button>' +
	          '      <md-button ng-click="ctrl.showMenu($event, item.submenu);" ng-disabled="{{!item.activo}}" ng-if="item.submenu">' +
	          '        <span>{{item.name}}</span>' +
	          '      </md-button>' +
	          '    </div>' +
	          '  </div>' +
	          '</div>',
	      panelClass: 'menu-panel-container',
	      focusOnOpen: false,
	      zIndex: 100,
	      propagateContainerEvents: true,
	      groupName: 'menus',
	    });
}]);

//myapp.controller('PanelMenuCtrl', ['mdPanelRef', function (mdPanelRef) {
//	this.closeMenu = function() {
//	      mdPanelRef && mdPanelRef.close();
//	    };
//}]);

myapp.controller('menuCtrl', ['$scope','$mdPanel','$http', function ($scope,$mdPanel,$http) {
	  
	//-----------------------------
	$scope.msUsuariosPerfilJS = {
			idusuario: null,
            username: null,
			nombrecompleto: null,
			contrasenia: null,
			sede: null,
			idunidad: null,
			idunidadTxt: null,
			contraseniaConfir: null
	};
	$scope.setPerfil = function(dato){
		$scope.msUsuariosPerfilJS.idusuario = dato.idusuario;
		$scope.msUsuariosPerfilJS.username = dato.username;
		$scope.msUsuariosPerfilJS.nombrecompleto = dato.nombrecompleto;
		$scope.msUsuariosPerfilJS.contrasenia = dato.contrasenia;
		$scope.msUsuariosPerfilJS.sede = dato.sede;
		$scope.msUsuariosPerfilJS.idunidad = dato.idunidad;
		$scope.msUsuariosPerfilJS.idunidadTxt = dato.idunidadTxt;
		$scope.msUsuariosPerfilJS.contraseniaConfir = dato.contraseniaConfir;
	};
	$scope.showdlgCambioContrasenia = function() {		
		$mdDialog.show({
			contentElement: '#cambioClaveDialog',
			parent: angular.element(document.body),
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true
		});
	};
	$scope.actulizarMsUsuarios = function(ev){		
		ev.target.disabled = true;
		var datainsert = angular.toJson($scope.msUsuariosPerfilJS);// $httpParamSerializerJQLike($scope.nuevapersonamodel);
		console.log("datainsert = "+datainsert);	
		$http.post(actualizarperfilUrl,datainsert,{headers: {'Content-Type': 'application/json'}}).then(function(res){
			var dato = res.data;
			$scope.setPerfil(dato); 
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.body))
					.clickOutsideToClose(true)
					.title('GUARDAR CONTRASEÑA')
					.textContent('LA CONTRASEÑA SE GUARDO CORRECTAMENTE')
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
						.title('GUARDAR CONTRASEÑA')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}
		});
		ev.target.disabled = false;
	};
	//-----------------------------
		        
	        $scope.registros = {
		      name: 'Eventos',
		      items: [
		    	  {'name':'Lista de eventos','refe':'/paginas/listatdEvento.html','activo':true},
		    	  {'name':'Vista agenda','refe':'/paginas/listatdEventoAgenda.html','activo':true}
		      ]
		    };
	       
		    $scope.reportes = {
		      name: 'Reportes',
		      items: [
		    	  {'name':'Listado por filtro','refe':'paginas/reportelistatdEvento.html','activo':true}
		      ]
		    };
		    $scope.administracion = { 
		      name: 'Administración',
		      items: [
		    	  {'name':'Entidades','refe':'adminis/listamsInstituciones.html','activo':true},
		    	  {'name':'Personas','refe':'adminis/listamsPersonas.html','activo':true},
		    	  {'name':'Tareas','refe':'adminis/listamsTareas.html','activo':true},
		    	  {'name':'Actividades','refe':'adminis/listamsActividades.html','activo':true},
		    	  {'name':'Grupos','refe':'adminis/listatdGrupos.html','activo':true}
		      ]
		    };
		    $scope.configuracion = {
				      name: 'Configuración',
				      items: [
				    	  {'name':'Parámetros','refe':'adminis/listaprtParametros.html','activo':true},
				    	  {'name':'Usuarios','refe':'adminis/listamsUsuariosSistema.html','activo':true},
				    	  {'name':'Unidades','refe':'adminis/listamsUnidadesOrg.html','activo':true},
				    	  {'name':'Paises','refe':'adminis/listamsPaises.html','activo':true},
				    	  {'name':'Ubigeo','refe':'adminis/listamsUbigeo.html','activo':true},
				    	  {'name':'Feriados','refe':'adminis/listatdFeriados.html','activo':true}
				      ]
				    };
		    $scope.ayuda = {
				      name: 'Ayuda',
				      items: [
				    	  {'name':'Manual de usuario','refe':'adminis/testcolor.html','activo':false},
				    	  {'name':'Videos','refe':'adminis/testcolor.html','activo':false}
				      ]
				    };

		    $mdPanel.newPanelGroup('menus', {
		      maxOpen: 1
		    });
		    
		    $scope.cargarperfil = function(){
		    	/**
		    	 * PRINT DE CONTROL
		    	 */
		    	console.log("window.location.pathname: " + window.location.pathname + " contexto: " + contexto + "\nperfilMenuUrl: " + perfilMenuUrl + " principalUrl: " + principalUrl);
		    	
				var surl = perfilMenuUrl;
				$http.get(surl).then(function(res){
					var dato = res.data;
					
					$scope.administracion.items[0].activo = dato.entidades;
					$scope.administracion.items[1].activo = dato.personas;
					$scope.administracion.items[2].activo = dato.temas;
					$scope.administracion.items[3].activo = dato.subtemas;
					$scope.administracion.items[4].activo = dato.canales;
					
					$scope.configuracion.items[0].activo = dato.parametros;
					$scope.configuracion.items[1].activo = dato.usuarios;
					$scope.configuracion.items[2].activo = dato.unidades;
					$scope.configuracion.items[3].activo = dato.paises;
					$scope.configuracion.items[4].activo = dato.ubigeo;
					$scope.configuracion.items[5].activo = dato.feriados;
					
					$scope.setPerfil(dato);
										
				},
				function error(errResponse) {
					console.log("data " + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Cargar perfil de usurio')
								.textContent(dato.message)
								.ariaLabel('ERROR')
								.ok('OK')
								.targetEvent(ev)
						);
					}			           
				});			        			        	
			};

		    var panelRef;
		    
		    $scope.showMenu = function($event, menu) {
		      /**
		       * The request to open the panel has two arguments passed into it. The
		       * first is a preset name passed in as a string. This will request a
		       * cached preset and apply its configuration parameters. The second is an
		       * object containing parameters that can only be filled through a
		       * controller. These parameters represent configuration needs associated
		       * with user interaction, panel position, panel animation, and other
		       * miscellaneous needs.
		       */
		      var offsetwidth = ($event.srcElement.nodeName=="MD-ICON"?"16px":"1PX");
		      var offsetheight = ($event.srcElement.nodeName=="MD-ICON"?"-5px":"1PX");
		      $mdPanel.open('menuPanel', {
		        id: 'menu_' + menu.name,
		        position: $mdPanel.newPanelPosition()
		            .relativeTo($event.target)
		            .addPanelPosition(
		              $mdPanel.xPosition.OFFSET_END,
		              $mdPanel.yPosition.ALIGN_TOPS
		            ).withOffsetX(offsetwidth).withOffsetY(offsetheight),
		        locals: {
		          items: menu.items,
		          menuname: menu.name,
		          evento: $event
		        },
		        openFrom: $event
		      }).then(function(result) {
		          panelRef = result;
//		          panelRef.panelContainer.addClass('menu-panel-container-w');
//		          panelRef.panelContainer.css('width', '300px');
		          //panelRef.panelEl.css('width', '300px');
//		          panelRef.panelContainer[0].setAttribute('style', 'width:200px;');
//		          panelRef.panelEl.addClass('menu-panel-container-w');
		          var element = panelRef.panelEl;
		          setTimeout(function(){
		        	  element[0].style.transition= "width 0.4s linear";
			          element[0].style.width = "250px";
		          },200,"JavaScript");
		          
		          console.log('Print '+element[0].style.cssText+' CLASS '+ element[0].className);
		          
		        });;
		    };
		    
		    $scope.salir=function(){
		 	   window.location.href=pglogoff; 
		 	 };
		 	 
		 	$scope.$on("showMenuRM", function(obj, $event, data){ 
		 		console.log('obj '+obj.name);
		 		console.log('evt '+$event.srcElement.nodeName);
		 		console.log('data '+data.name);
		 		$scope.showMenu($event, data);
			});
		 			 
}]);

