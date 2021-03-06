(function() {
	'use strict';
	/**
	 *  Declare app level module which depends on views, and components.
	 */
	angular.module('innovationlabApp', [
		'tmh.dynamicLocale', // angular-dynamic-locale
		'moment-picker',
		'dahr.ng-image-picker',
		'starRatings',
		'textAngular',
	    'pascalprecht.translate',
	    //DO NOT DELETE ###BEGIN### include generated files
	    'innovationlabApp.idea',

		//DO NOT DELETE ###END### include generated files
		'ngRoute'
	]);

	/**
	 * Language Configuration via module pascalprecht.translate.
	 */
	angular.module('innovationlabApp').config(['$translateProvider', function($translateProvider) {
		$translateProvider
		.useStaticFilesLoader({
			prefix: 'localization/translations-',
			suffix: '.json'
		})
        .registerAvailableLanguageKeys(['en-US', 'de-DE'], {
             'en*': 'en-US',
             'de*': 'de-DE',
         })
		.determinePreferredLanguage()
		.useSanitizeValueStrategy('escaped') // Security for escaping variables
		.usePostCompiling(true); // Post compiling angular filters
	}]);
	
	/**
	 * Date-picker configuration.
	 */
	angular.module('innovationlabApp').config(['momentPickerProvider', function (momentPickerProvider) {
			var locale = window.navigator.userLanguage || window.navigator.language;
			momentPickerProvider.options({
	        /* Picker properties */
	    	locale:		locale,
	        format:        'LL',
	        minView: 	'month',
	        maxView: 	'minute',
	        startView:     'month',
	        autoclose:     true,
	        today:         false,
	        keyboard:      false
		});
	}]);

	angular.module('innovationlabApp')
		.config(['tmhDynamicLocaleProvider', function(tmhDynamicLocaleProvider) {
			var locale = window.navigator.userLanguage || window.navigator.language;
			if (locale.includes('de')) {
					tmhDynamicLocaleProvider.defaultLocale('de-de');
					tmhDynamicLocaleProvider.localeLocationPattern('localization/angular-locale_de-de.js');
			} else {
					tmhDynamicLocaleProvider.defaultLocale('en-us');
					tmhDynamicLocaleProvider.localeLocationPattern('localization/angular-locale_en-us.js');
			}
	  }]);
	
	angular.module('innovationlabApp').controller('appController', appController);

	/**
	 * Controller for global behavior when changing the view (routeChange).
	 * @param $scope
	 * @returns
	 */
	function appController($scope) {
		$scope.$on('$routeChangeSuccess', function (scope, next, current) {
			$scope.title=next.title;
			$scope.subtitle=next.subtitle;
		});
	}
	

	angular.module('innovationlabApp')
	.filter('formatLocalDate', function ($filter) {
	   return function (date) {
	       if (date) {
	    	   moment.locale(window.navigator.userLanguage || window.navigator.language);
	           return moment(date).format("LL");
	       }
	       else
	           return "";
	   };
	})
	.filter('formatLocalTime', function ($filter) {
	   return function (date) {
	       if (date) {
	    	   moment.locale(window.navigator.userLanguage || window.navigator.language);
	           return moment(date).format("LLL");
	       }
	       else
	           return "";
	   };
	});


	/**
	 * Factories used globally in innovationlabApp.
	 * To keep the scope as small as possible, add factories only used by submodules in the submodule itself.
	 */
	angular.module('innovationlabApp').factory('restConnectorFactory', restConnectorFactory);
})();
