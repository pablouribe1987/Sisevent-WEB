/**
 * 
 */

var myapp = angular.module('MyApp', ['ngMaterial', 'md.data.table', 'ngRoute']);

//MPINARES 01092023 - INICIO
//myapp.config(['$mdThemingProvider', function ($mdThemingProvider) {
//    'use strict';    
//    $mdThemingProvider.theme('default')
//      .primaryPalette('indigo'); // red pink purple deep-purple indigo blue light-blue cyan teal green light-green lime yellow
//								// amber orange deep-orange brown grey blue-grey
//    $mdThemingProvider.alwaysWatchTheme(true);
//    $mdThemingProvider.theme('altTheme').primaryPalette('purple').accentPalette('green');
//    // //////////////////////////////////////////////////////////////////////////////////////////
//    $mdThemingProvider.definePalette('mythemapaleta', {
//      '50': 'ccddf8',
//      '100': '8eb5f6',
//      '200': '4475b8',
//      '300': '2d416b',
//      '400': '505bef',
//      '500': 'ffffff',
//      '600': '153f8c',
//      '700': '45358a',
//      '800': '452d6d',
//      '900': '231c4d',
//      'A100': '292254',
//      'A200': '6113f1',
//      'A400': '8274d9',
//      'A700': '3912e0',
//      // By default, text (contrast) on this palette should be white with 87% opacity.
//      'contrastDefaultColor': 'light',
//      // By default, for these lighter hues, text (contrast) should be 'dark'.
//      'contrastDarkColors': '50 100 200 300 400 500 600 A100 A200 A400',
//      // By default, for these darker hues, text (contrast) should be white with 100% opacity.
//      'contrastStrongLightColors': '700 800 900 A700'
//});
//    $mdThemingProvider.theme('mythema').primaryPalette('mythemapaleta');
////    $mdThemingProvider.generateThemesOnDemand(true);
////    $mdThemingProvider.alwaysWatchTheme(true);
//}]);

myapp.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';    
    
    var neonRedMap = $mdThemingProvider.extendPalette('cyan', {
	    '700':'#007DAF',
	    '800':'#4E8BA4'
	  });

	  // Register the new color palette map with the name <code>neonRed</code>
   $mdThemingProvider.definePalette('neonred', neonRedMap);

	  // Use that theme for the primary intentions
   $mdThemingProvider.theme('default').primaryPalette('neonred');
    
//    $mdThemingProvider.generateThemesOnDemand(true);
    $mdThemingProvider.alwaysWatchTheme(true);
}]);
//MPINARES 01092023 - FIN
