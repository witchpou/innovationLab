/**
 * This controller maintains a 'idea object and belongs to the view 'idea.single.html.
 */
(function() {
	'use strict';
	angular.module('innovationlabApp.idea').controller('ideaSingleCtrl', ideaSingleCtrl);

	ideaSingleCtrl.$inject = ['$scope', '$routeParams', 'ideaConnectorFactory', 'gotoIdea','textAngularManager'];
	function ideaSingleCtrl($scope, $routeParams, ideaConnectorFactory, gotoIdea, textAngularManager) {
		var ctrl = this;

		ctrl.doMaintain = doMaintain;
		ctrl.gotoIdea = gotoIdea;
		ctrl.dateformated = {};
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
			if(ctrl.idea.rating == undefined) {
				ctrl.idea.rating = 0;
			}
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
			$scope.version = textAngularManager.getVersion();
			$scope.versionNumber = $scope.version.substring(1);
			$scope.htmlContent='<h1>Test</h1>';
			ctrl.idea = {};
			$scope.$on('$routeChangeSuccess', function (scope, next, current) {
				if ($routeParams.id != undefined && $routeParams.id !== ctrl.idea.id) {
					ctrl.idea.id = $routeParams.id;
					ideaConnectorFactory.loadIdea(ctrl.idea.id).then(setIdea, function(){});
				}
				if ($routeParams.is == null) {
					ctrl.idea = {};
					ctrl.idea.created = new Date();
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
