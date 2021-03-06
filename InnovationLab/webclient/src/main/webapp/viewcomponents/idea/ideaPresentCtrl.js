/**
 * This controller maintains a 'idea object and belongs to the view 'idea.single.html.
 */
(function() {
	'use strict';
	angular.module('innovationlabApp.idea').controller('ideaPresentCtrl', ideaPresentCtrl);

	ideaPresentCtrl.$inject = ['$scope', '$routeParams', '$document', 'ideaConnectorFactory', 'gotoIdea'];
	function ideaPresentCtrl($scope, $routeParams, $document, ideaConnectorFactory, gotoIdea) {
		var ctrl = this;

		ctrl.doRate = doRate;
		ctrl.gotoIdea = gotoIdea;
		init();

		/**
		 * Standard function to edit the project configuration.
		 */
		function doRate() {
			if (ctrl.rating != undefined) {
				doRateThenGoto();
			} else {
				gotoIdea.all();
			}
		}

		function doRateThenGoto() {
			if(ctrl.rating == undefined) {
				ctrl.rating = 0;
			}
			var saveFunction = isUpdate() ? ideaConnectorFactory.rate : function(){};
			saveFunction(ctrl.rating).then(saveSuccessCallback(), function(){});
		}

		function isUpdate() {
			return ctrl.idea != null && ctrl.idea.id != null;
		}

		/**
		 * Standard function for initialization.
		 */
		function init() {
			ctrl.idea = {};
			ctrl.rating = {};
			$scope.$on('$routeChangeSuccess', function (scope, next, current) {
				if ($routeParams.id != undefined && $routeParams.id !== ctrl.idea.id) {
					ctrl.idea.id = $routeParams.id;
					ctrl.rating.idea = ctrl.idea;
					ctrl.rating.ratingValue = 0;
					ideaConnectorFactory.loadIdea(ctrl.idea.id).then(setIdea, function(){});
				}
			});
		}

		/**
		 * Used for setting the database result to the representation-object in the controller.
		 */
		function setIdea(response) {
			ctrl.idea = response;
			ideaConnectorFactory.getImageFromBackend(ctrl.idea.id).then(getImageSuccessCallback, getImageErrorCallback);
		}

		/**
		 * Success message after saving.
		 */
		function saveSuccessCallback() {
			return function (response) {
				gotoIdea.all();
			}
		}
		
		function getImageSuccessCallback(response) {
			$document[0].getElementById('image-preview').attributes['src'].value = 'data:image/png;base64,' + response.data;
		};
		
		function getImageErrorCallback(response) {
		};
	}
})();
