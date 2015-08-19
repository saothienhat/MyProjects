<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC AngularJS demo</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script>

	var app = angular.module('myApp', []);
	
	function MyController($scope, $http){
				
	    $scope.getStudentFromServer = function() {	    	
	    	$http({method: 'GET', url: 'springAngularJS'}).
	        success(function(data, status, headers, config) {
	        	$scope.student = data;
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        });
	    
	    };
	};
</script>
</head>
<body>
	<div data-ng-app="myApp">
		<div data-ng-controller="MyController">

			<button data-ng-click="getStudentFromServer()">Get Student data from server</button>
			<p>First Name : {{student.firstName}}</p>
			<p>Last Name : {{student.lastName}}</p>
		</div>
	</div>
</body>
</html>