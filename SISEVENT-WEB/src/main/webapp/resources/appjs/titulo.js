var contexto = window.location.pathname.substring(0,window.location.pathname.indexOf('/',2));
var pglogoff = window.location.origin+contexto+'/logoff.htm';
var principalUrl = window.location.origin+contexto+'/index.htm';
var perfilUrl = contexto+"/rs/ctrltitulo/perfilusuario";

myapp = angular.module('MyApp');

myapp.config(['$mdPanelProvider', function ($mdPanelProvider) {
	$mdPanelProvider.definePreset('infoPanel', {
		  attachTo: angular.element(document.body),
	      controller: ['mdPanelRef', function(mdPanelRef) {
	    	  
	    	  var mdPanelRef = mdPanelRef;
	    	  
	    	  this.closeMenu = function() {
	    		  mdPanelRef && mdPanelRef.close();
	    	    };   
	    	    
	    	  this.showdlgCambioContrasenia = function(){
	    		  this.$scope.$parent.$broadcast('showdlgCambioContraseniaX', 'X');
//	    		  this.$scope.$root.$broadcast('showdlgCambioContraseniaX', 'X');
	    	  };  
	      }],
	      controllerAs: 'ctrl',
	      template: '' +
	          '<div style="margin-left: 15px;margin-top: 25px;" ng-mouseleave="ctrl.closeMenu()">' +	          
	          '  <div>' +
	          '  <table>' +
	          '  <tr>' +
	          '  <td>' +
	          '        <span style="font-size: 14px !important;color:#555753;"><b>{{ctrl.nombrecompleto}}</b></span>' +
	          '  </td>' +
	          '  <td>' +
	          '        <span style="font-size: 22px !important;color:#555753;background-color: #EEEEEC;margin-left: 15px;" ><md-icon md-font-set="material-icons">person</md-icon></span>' +
	          '  <td>' +
	          '  </tr>' +
	          '  </table>' +
	          '    </div>' +
	          '    <md-divider class="menu-divider"></md-divider>' +
	          '        <div>&nbsp;</div>' +
	          '        <div style="font-size: 14px !important;color:#555753;">{{ctrl.sede}}</div>' +
	          '        <div style="font-size: 14px !important;color:#555753;">{{ctrl.idunidadTxt}}</div>' +
	          '    <md-divider class="menu-divider"></md-divider>' +
	          '        <div layout="row" layout-align="end center" flex>' +
	          '        <md-button class="menu-button" ng-click="ctrl.showdlgCambioContrasenia()">' + 
	          '        <md-icon md-svg-icon="resources/icons/power_settings_new-24px.svg" aria-label="Salir">' +
	          '        </md-icon>' + 
	          '        </md-button>' +
	          '        </div>' +
	          '        <div>&nbsp;</div>' +
	          '</div>',
	      panelClass: 'idea',
	      focusOnOpen: false,
	      zIndex: 100,
	      propagateContainerEvents: true,
	      groupName: 'infos',
	    });
}]);

myapp.config(['$mdPanelProvider', function ($mdPanelProvider) {
	$mdPanelProvider.definePreset('notiPanel', {
		  attachTo: angular.element(document.body),
	      controller: ['mdPanelRef', function(mdPanelRef) {
	    	  this.closeMenu = function() {
	    		  mdPanelRef && mdPanelRef.close();
	    	    };
	    	  this.formatDate = function(dia){     
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
	      }],
	      controllerAs: 'ctrl',
	      template: '' +
	          '<div ng-mouseleave="ctrl.closeMenu()">' +	          
	          '  <div>' +
	          '        <span style="font-size: 14px !important;color:#CC0000;"><b>Notificaciones</b></span>' +
	          '  </div>' +
	          '    <md-divider class="menu-divider"></md-divider>' +
	          '<div ng-repeat="x in ctrl.pendientes">' +
	          '        <div style="font-size: 14px !important;color:#555753;">Se acaba de asignar nueva consulta <a href="'+window.location.origin+contexto+'/index.htm#!/editar/{{x.idsacc}}">{{x.numeroSacc}}-{{x.numeroAnio}}</a></div>' +
	          '        <div style="font-size: 14px !important;color:#555753;">{{ctrl.formatDate(x.miflujo.fechaDerivacion)}}</div>' +
	          '    <md-divider class="menu-divider"></md-divider>' +
	          '</div>' +
	          '        <div>&nbsp;</div>' +
	          '        <div>&nbsp;</div>' +
	          '</div>',
	      panelClass: 'noti',
	      focusOnOpen: false,
	      zIndex: 100,
	      propagateContainerEvents: true,
	      groupName: 'infos',
	    });
}]);

myapp.directive('strength', function () {
	return {
		restrict: 'A',
		scope:true,
		link: function (scope, element, attrs) {
			
			var defaults = {
		            strengthClass: 'strength',
		            strengthMeterClass: 'strength_meter',
		            strengthButtonClass: 'button_strength',
		            strengthButtonText: 'Show Password',
		            strengthButtonTextToggle: 'Hide Password'
		        };
			
			var characters = 0;
            var capitalletters = 0;
            var loweletters = 0;
            var number = 0;
            var special = 0;

            var upperCase= new RegExp('[A-Z]');
            var lowerCase= new RegExp('[a-z]');
            var numbers = new RegExp('[0-9]');
            var specialchars = new RegExp('([!,%,&,@,#,$,^,*,?,_,~])');
			
            function getPercentage(a, b) {
                return ((b / a) * 100);
            };
            
            function check_strength(thisval,thisid){
               if (thisval.length > 8) { characters = 1; } else { characters = 0; };
               if (thisval.match(upperCase)) { capitalletters = 1} else { capitalletters = 0; };
               if (thisval.match(lowerCase)) { loweletters = 1}  else { loweletters = 0; };
               if (thisval.match(numbers)) { number = 1}  else { number = 0; };
               var total = characters + capitalletters + loweletters + number + special;
               var totalpercent = getPercentage(7, total).toFixed(0);
               get_total(total,thisid);
            };
            
            function get_total(total,thisid){
              var thismeter = angular.element(document.getElementById(thisid+'meter'));
              if(total == 0){
                    thismeter.removeClass('veryweak weak medium strong').html('');
              }else if (total <= 1) {
                 thismeter.removeClass('veryweak weak medium strong');
                 thismeter.addClass('veryweak').html('<p>MuyDébil</p>');
              } else if (total == 2){
                  thismeter.removeClass('veryweak weak medium strong');
                 thismeter.addClass('weak').html('<p>Débiles</p>');
              } else if(total == 3){
                  thismeter.removeClass('veryweak weak medium strong');
                 thismeter.addClass('medium').html('<p>Medio</p>');

              } else {
                   thismeter.removeClass('veryweak weak medium strong');
                 thismeter.addClass('strong').html('<p>Fuerte</p>');
              } 
              console.log(total);
          };
            
          var $elem = angular.element(element);
          var options = defaults;
          var _defaults = defaults;
          
          var isShown = false;
          var strengthButtonText = options.strengthButtonText;
          var strengthButtonTextToggle = options.strengthButtonTextToggle;

          var thisid = $elem.attr('id');

          $elem.addClass(options.strengthClass);
          $elem.attr('data-password',thisid);
          $elem.after('<input style="display:none" id="'+thisid+'vis" class="'+options.strengthClass+'" data-password="'+thisid+'" type="text" name="" value=""><a data-password-button="'+thisid+'" href="javascript:void(0);" id="'+thisid+'link" class="'+options.strengthButtonClass+'">'+options.strengthButtonText+'</a><div class="'+options.strengthMeterClass+'"><div id="'+thisid+'meter" data-meter="'+thisid+'"><p></p></div></div>');
           
          $elem.bind('keyup', function(event) {
//        	  event.stopPropagation();
              thisval = angular.element(document.getElementById(thisid)).val();
              angular.element(document.getElementById(thisid+'vis')).val(thisval);
              check_strength(thisval,thisid);              
          });
                   
          angular.element(document.getElementById(thisid+'vis')).bind('keyup', function(event) {
//        	  event.stopPropagation();
        	  thisval = angular.element(document.getElementById(thisid+'vis')).val();
              console.log(thisval);
              angular.element(document.getElementById(thisid)).val(thisval);
              check_strength(thisval,thisid);              
          });
          
          angular.element(document.getElementById(thisid+'link')).on('click',function(e) {
//              e.preventDefault();
              e.stopPropagation();
             thisclass = 'hide_'+angular.element(this).attr('class');
              if (isShown) {
            	  document.getElementById(thisid+'vis').style.display = "none";
            	  document.getElementById(thisid).style.display = "block";
            	  angular.element(document.getElementById(thisid)).focus();
            	  angular.element(document.getElementById(thisid+'link')).removeClass(thisclass).html(strengthButtonText);
                  isShown = false;
              } else {
            	  document.getElementById(thisid+'vis').style.display = "block";
            	  document.getElementById(thisid).style.display = "none";
            	  angular.element(document.getElementById(thisid+'vis')).focus();
            	  angular.element(document.getElementById(thisid+'link')).addClass(thisclass).html(strengthButtonTextToggle);
                  isShown = true; 
              }         
          });
          
		},//FIN FUNCION LINK
	};
});

myapp.controller('tituloCtrl', ['$mdEditDialog', '$scope', '$timeout', '$http', '$mdDialog','$location','$routeParams','$mdPanel','$interval', function ($mdEditDialog, $scope, $timeout, $http, $mdDialog, $location, $routeParams, $mdPanel, $interval) {
 		
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
	
	$scope.inicializacion = function(){
		$scope.cargarperfil();
//		$scope.loadtdPendientes();
	}	
	
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
	
	$scope.cargarperfil = function(){		
		var surl = perfilUrl;
		$http.get(surl).then(function(res){
			var dato = res.data;
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
						.title('Cargar perfil')
						.textContent(dato.message)
						.ariaLabel('ERROR')
						.ok('OK')
						.targetEvent(ev)
				);
			}			           
		});			        			        	
	};
	
	$scope.getClassNotifica = function(){
		if($scope.pendientes.length>0){
			return 'red';
		}
		return '';	
	}
	
	$scope.getClassUsuario = function(){		
		return '';	
	}
	
	$scope.getTotalNotifica = function(){
		if($scope.pendientes.length>0){
			return ('00' + $scope.pendientes.length).slice(-2);
		}
		return '';	
	}
	
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
	
	$mdPanel.newPanelGroup('infos', {
	      maxOpen: 1
	    });

	$scope.panelRef=null;
	
	this.showdlgPerfil = function(ev) {
	      var offsetwidth = (ev.srcElement.nodeName=="MD-ICON"?"-255px":"-260px");
	      var offsetheight = (ev.srcElement.nodeName=="MD-ICON"?"40px":"45px");
	      $mdPanel.open('infoPanel', {
	        id: 'iddlgUsuario',
	        position: $mdPanel.newPanelPosition()
	            .relativeTo(ev.target)
	            .addPanelPosition(
	              $mdPanel.xPosition.OFFSET_END,
	              $mdPanel.yPosition.ALIGN_TOPS
	            ).withOffsetX(offsetwidth).withOffsetY(offsetheight),
	        locals: {
	        	nombrecompleto: $scope.msUsuariosPerfilJS.nombrecompleto,
	        	sede: $scope.msUsuariosPerfilJS.sede,
	        	idunidadTxt: $scope.msUsuariosPerfilJS.idunidadTxt
	        },
	        bindToController: true,
	        openFrom: ev,
	        clickOutsideToClose: true,
	        escapeToClose: true,
	        focusOnOpen: true
	      }).then(function(result) {
	    	  $scope.panelRef = result;
//	          panelRef.panelContainer.addClass('menu-panel-container-w');
//	          panelRef.panelContainer.css('width', '300px');
	          //panelRef.panelEl.css('width', '300px');
//	          panelRef.panelContainer[0].setAttribute('style', 'width:200px;');
//	          panelRef.panelEl.addClass('menu-panel-container-w');
	          var element = $scope.panelRef.panelEl;
	          setTimeout(function(){
	        	  element[0].style.transition= "width 0.4s linear";
		          element[0].style.width = "300px";
	          },200,"JavaScript");	          
	          console.log('Print '+element[0].style.cssText+' CLASS '+ element[0].className);	          
	        });;
	    };
	
	    this.showdlgNoti = function(ev) {
		      var offsetwidth = (ev.srcElement.nodeName=="MD-ICON"?"-370px":"-375px");
		      var offsetheight = (ev.srcElement.nodeName=="MD-ICON"?"40px":"45px");
		      $mdPanel.open('notiPanel', {
		        id: 'iddlgNoti',
		        position: $mdPanel.newPanelPosition()
		            .relativeTo(ev.target)
		            .addPanelPosition(
		              $mdPanel.xPosition.OFFSET_END,
		              $mdPanel.yPosition.ALIGN_TOPS
		            ).withOffsetX(offsetwidth).withOffsetY(offsetheight),
		        locals: {
                   pendientes: $scope.pendientes
		        },
		        openFrom: ev,
		        clickOutsideToClose: true,
		        escapeToClose: true,
		        focusOnOpen: true
		      }).then(function(result) {
		    	  $scope.panelRef = result;
//		          panelRef.panelContainer.addClass('menu-panel-container-w');
//		          panelRef.panelContainer.css('width', '300px');
		          //panelRef.panelEl.css('width', '300px');
//		          panelRef.panelContainer[0].setAttribute('style', 'width:200px;');
//		          panelRef.panelEl.addClass('menu-panel-container-w');
		          var element = $scope.panelRef.panelEl;
		          setTimeout(function(){
		        	  element[0].style.transition= "width 0.4s linear";
			          element[0].style.width = "372px";
		          },200,"JavaScript");	          
		          console.log('Print '+element[0].style.cssText+' CLASS '+ element[0].className);	          
		        });;
		    };
	    
///////////////////////////////////////
		    $scope.pendientes =[];
		    $scope.promise = null;
		    $scope.creatdAtenciones = false;
		    
		    $scope.loadtdPendientes = function () {
				var surl = pendientesUrl;	    
				$scope.promise = $http.get(surl).then(function(res){
					$scope.pendientes = res.data.data;
					if(res.data.contador>0)
						$scope.total = res.data.contador;
					var tiempoenBD = res.data.tiempoenBD;
					var tiempoenproceso = res.data.tiempoenproceso;
					$scope.creatdAtenciones = res.data.creamodifica;
					console.log("data " +$scope.pendientes.length+" DE "+ $scope.total);
					console.log("Tiempo respuesta pendientes BD " +tiempoenBD+" Tiempo en Paginar "+tiempoenproceso);
				},
				function error(errResponse) {
					console.log("data pendientes" + errResponse.data + " status " + errResponse.status + " headers " + errResponse.headers + "config " + errResponse.config + " statusText " + errResponse + " xhrStat " + errResponse.xhrStatus);
					var dato = errResponse.data;
					if(typeof(dato) != 'undefined' && typeof(dato.message) != 'undefined'){
						$mdDialog.show(
								$mdDialog.alert()
								.parent(angular.element(document.body))
								.clickOutsideToClose(true)
								.title('Lista de  Pendientes de Atención Y/o Consulta')
								.textContent(dato.message)
								.ariaLabel('ERROR')
								.ok('OK')
						);
					}
				});			 
			};
			
//			$interval( function(){ $scope.loadtdPendientes(); }, 300000);
////////////////////////////////////////
			$scope.$on("showdlgCambioContraseniaX", function(evt,data){ 
				   if($scope.panelRef)
					   $scope.panelRef.close();
				   $scope.showdlgCambioContrasenia();
				});
			
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
}]);

