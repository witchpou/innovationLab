/** 
 * Navigation and routing for module 'innovationlabApp..idea.
 */
(function() {
'use strict';

	angular.module('innovationlabApp.idea').factory('gotoIdea', gotoIdea);

	function gotoIdea($location) {
		var factory = {};
		factory.all = function() {
	    	$location.path('/viewcomponents/idea-all/');
	    },
	    factory.update = function(id) {
	    	$location.path('/viewcomponents/idea-maintain/update/' + id);
	    },
	    factory.create = function() {
	    	$location.path('/viewcomponents/idea-maintain/create/');
	    },
	    factory.loaderror = function() {
	    	$location.path('/');
	    },
	    factory.present = function(id) {
	    	$location.path('/viewcomponents/idea-present/' + id);
	    }
		return factory;
    };
    
   	angular.module('innovationlabApp.idea') 
   	.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/viewcomponents/idea-all/', {
			controller : 'ideaAllCtrl',
			controllerAs : 'ctrl',
			title : "idea.all.title",
			subtitle : "",
			templateUrl : "viewcomponents/idea/idea.all.html",
	        resolve: {
	        	ideaConnectorFactory: ideaConnectorFactory
	         }
		}).when('/viewcomponents/idea-maintain/create/', {
			controller : 'ideaSingleCtrl',
			controllerAs : 'ctrl',
			title : "idea.create.title",
			subtitle : "",
			templateUrl : "viewcomponents/idea/idea.single.html",
			resolve: {
	        	ideaConnectorFactory: ideaConnectorFactory
	         }		
		}).when('/viewcomponents/idea-maintain/update/:id', {
			controller : 'ideaSingleCtrl',
			controllerAs : 'ctrl',
			title : "idea.update.title",
			subtitle : "",
			templateUrl : "viewcomponents/idea/idea.single.html",
	        resolve: {
	        	ideaConnectorFactory: ideaConnectorFactory
	         }		
		}).when('/viewcomponents/idea-present/:id', {
			controller : 'ideaPresentCtrl',
			controllerAs : 'ctrl',
			title : "idea.present.title",
			subtitle : "",
			templateUrl : "viewcomponents/idea/idea.present.html",
	        resolve: {
	        	ideaConnectorFactory: ideaConnectorFactory
	         }		
		});
	}]);
})();