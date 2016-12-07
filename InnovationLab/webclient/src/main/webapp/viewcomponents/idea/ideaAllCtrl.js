/**
 * This controller facilitates the idea.all.html - view to display all ideas. 
 * It provides all needed functions for this view.
 */
(function() {
	'use strict';
	angular.module('innovationlabApp.idea').controller('ideaAllCtrl', ideaAllCtrl);
	ideaAllCtrl.$inject = ['ideaConnectorFactory', 'gotoIdea'];
	function ideaAllCtrl(ideaConnectorFactory, gotoIdea) {
		
		var  ctrl = this;
		ctrl.refresh = refresh;
		ctrl.deleteIdea = deleteIdea;
		ctrl.gotoIdea = gotoIdea;
		ctrl.setSelected = setSelected;
		init();
		
		function setSelected(idSelected) { 
			ctrl.idSelected = idSelected; 
		}
		
		/** 
		 * Standard function for initialization.
		 */
		function init() {
			ctrl.ideaAll = [];
			ctrl.idea = {};
			ctrl.idSelected = null;
			ideaConnectorFactory.getIdeaAll().then(setIdeaAll, null);
		}
		
		function refresh() {
			ideaConnectorFactory.getIdeaAll().then(setIdeaAll, function() {});
		};
		
		function deleteIdea(id) {
			ideaConnectorFactory.deleteIdea(id).then(deleteSuccess, function() {})
		};
		
		/**
		 * Used for setting the database result to the representation-object in the controller.
		 */
		function setIdeaAll(response) {
			ctrl.ideaAll = response;		
		}
		
		/**
		 * Success message after deleting.
		 */
		function deleteSuccess(response) {
			refresh();
			gotoIdea.all();
		};
	};
})();