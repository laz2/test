'use strict';

angular.module('generatorAngularFullstackApp')
  .controller('MessageCtrl', function ($scope, $http) {
    $http.get('/api/message').success(function(message) {
      $scope.message = message;
    });
  });
