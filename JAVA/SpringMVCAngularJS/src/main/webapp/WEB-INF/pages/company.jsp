<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<style>
table, th , td {
  border: 1px solid grey;
  border-collapse: collapse;
  padding: 5px;
}
table tr:nth-child(odd) {
  background-color: #f2f2f2;
}
table tr:nth-child(even) {
  background-color: #ffffff;
}
</style>
<script>
	var app = angular.module('myApp', []);
	
	function MyController($scope, $http){
				
	    $scope.getStudentsForCompany = function() {	    	
	    	$http({method: 'GET', url: 'studentsForCompanyURL'}).
	        success(function(data, status, headers, config) {	        	
	        	$scope.students = data;
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

			<button data-ng-click="getStudentsForCompany">Get Students for Company</button>
			
			<br>
			<tr>
			<td>Students:</td>			
			<td>
			<table>
			   <tr>
			      <th>First Name</th>
			      <th>Last Name</th>
			      <th>Email</th>
			   </tr>
			   <tr ng-repeat="student in students">
			      <td>{{ student.firstName }}</td>
			      <td>{{ student.lastName }}</td>
			      <td>{{ student.email }}</td>
			   </tr>
			</table>
			</td></tr>
			</table>
		</div>
	</div>
</body>
</html>