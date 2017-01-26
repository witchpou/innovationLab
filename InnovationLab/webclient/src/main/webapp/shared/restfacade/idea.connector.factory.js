ideaConnectorFactory = ['$http', '$location', 'restConnectorFactory', function ideaConnectorFactory ($http, $location, restConnectorFactory) {
    var factory = {
    		getIdeaAll: getIdeaAll,
    		loadIdea: loadIdea,
    		createIdea: createIdea,
    		updateIdea: updateIdea,
    		deleteIdea : deleteIdea
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
	
	function updateRating(rating) {
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
}];