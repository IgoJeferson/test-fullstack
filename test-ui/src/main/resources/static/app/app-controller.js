'use strict';

var app = angular.module('test',[]);

app.controller('UserListController', function ($scope, $http) {
	  $http.get('/api/user').then(function (data) {
	    $scope.users = data;
	  });
	})
	.directive('users', function() {
	  return {
	    templateUrl: '/app/user-list.html',
	    controller: 'UserListController'
	  }
});

app.controller('UserCreationController', function ($scope, $http) {
	  $http.post('/api/user').then(function (data) {
	    $scope.users = data;
	  });
	})
	.directive('users', function() {
	  return {
	    templateUrl: 'user-creation.html',
	    controller: 'UserCreationController'
	  }
});