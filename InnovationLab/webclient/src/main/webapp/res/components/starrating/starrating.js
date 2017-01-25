angular.module('starRatings',[]).directive('starRating',
		function() {
	return {
		restrict : 'EA',
		template : '<ul class="rating" ng-class="{readonly: readonly}">'
				 + '	<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)" >'
				 + '\u2605'
				 + '</li>'
				 + '</ul>',
		scope : {
			ratingValue: '=ngModel',
			max : '=max',
			readonly: '=?'
		},
		link : function(scope, elem, attrs) {
			var updateStars = function() {
				scope.stars = [];
				if(scope.ratingValue == undefined) {
					scope.ratingValue = 0;
				}
				for ( var i = 0; i < scope.max; i++) {
					scope.stars.push({
						filled : i < scope.ratingValue
					});
				}
			};
			
			var initStars = function() {
				scope.stars = [];
				for ( var i = 0; i < scope.max; i++) {
					scope.stars.push({
						filled : false
					});
				}
			};
			
			scope.toggle = function(index) {
				if (scope.readonly) {
					return;
				}
				scope.ratingValue = index + 1;
			};
			
			scope.$watch('ratingValue',
				function(oldVal, newVal) {
					if (newVal) {
						updateStars();
					} else if ((newVal == undefined || newVal == 0) && (oldVal != null && oldVal != 0)) {
						updateStars();
					} else if (scope.stars == null) {
						initStars();
					}
				}
			);
		}
	};
}
);