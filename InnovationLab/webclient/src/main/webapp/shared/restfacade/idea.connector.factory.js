ideaConnectorFactory = ['$http', '$location', 'restConnectorFactory', function ideaConnectorFactory ($http, $location, restConnectorFactory) {
    var factory = {
    		getIdeaAll: getIdeaAll,
    		loadIdea: loadIdea,
    		createIdea: createIdea,
    		updateIdea: updateIdea,
    		deleteIdea : deleteIdea,
    		uploadImage : uploadImage,
    		getImageFromBackend : getImageFromBackend,
    		rate : rate
     };
    return factory;
	
	function getIdeaAll() {
		return $http.get('api/idea/query/all')
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
		
	function loadIdea(id) {
		return $http.get('api/idea/query/' + id)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
		
	function createIdea(idea) {
		return $http.put('api/idea/', idea)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
		
	function updateIdea(idea) {
		return $http.post('api/idea/', idea)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
	
	function rate(rating) {
		return $http.post('api/idea/rate', rating)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
		
	function deleteIdea(id) {
		return $http.delete('api/idea/' + id)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
	
	function uploadImage(id, file) {
	        var fd = new FormData();
	        fd.append('image', file);
	        return $http.post('uploadFile', fd, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined},
	            params: {'id': id }
	        });
	};
	
	function getImageFromBackend(id) {
		return $http.get('api/idea/getImage/' + id)			
	};
}];