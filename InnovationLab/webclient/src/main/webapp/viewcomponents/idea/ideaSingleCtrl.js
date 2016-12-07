/**
 * This controller maintains a 'idea object and belongs to the view 'idea.single.html.
 */
(function() {
	'use strict';
	angular.module('innovationlabApp.idea').controller('ideaSingleCtrl', ideaSingleCtrl);
	
	ideaSingleCtrl.$inject = ['$scope', '$routeParams', 'ideaConnectorFactory', 'gotoIdea'];
	function ideaSingleCtrl($scope, $routeParams, ideaConnectorFactory, gotoIdea) {
		var ctrl = this;
		
		ctrl.doMaintain = doMaintain;
		ctrl.gotoIdea = gotoIdea;
		init();

		/**
		 * Standard function to edit the project configuration.
		 */
		function doMaintain() {
			if (ctrl.form.$dirty) {
				doMaintainThenGoto();
			} else {
				gotoIdea.all();
			}
		}
		
		function doMaintainThenGoto() {
			var saveFunction = isUpdate() ? ideaConnectorFactory.updateIdea : ideaConnectorFactory.createIdea;
			saveFunction(ctrl.idea).then(saveSuccessCallback(), function(){});
		}

		function isUpdate() {
			return ctrl.idea != null && ctrl.idea.id != null;
		}
		
		/** 
		 * Standard function for initialization.
		 */
		function init() {
			ctrl.idea = {};
			$scope.$on('$routeChangeSuccess', function (scope, next, current) {
				if ($routeParams.id != undefined && $routeParams.id !== ctrl.idea.id) {
					ctrl.idea.id = $routeParams.id;
					ideaConnectorFactory.loadIdea(ctrl.idea.id).then(setIdea, function(){});
				}
				if ($routeParams.is == null) {
					ctrl.idea = {};
				}
			});
		}
		
		/**
		 * Used for setting the database result to the representation-object in the controller.
		 */
		function setIdea(response) {
			ctrl.idea = response;
		}
		
		/**
		 * Success message after saving.
		 */
		function saveSuccessCallback() {
			return function (response) {
				setIdea(response);
				gotoIdea.all();
			}
		}
	
	}
})();