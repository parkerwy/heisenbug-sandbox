var app = angular.module('App');

app.controller('aboutController', function ($scope, $log, $http) {

    $scope.info = {};
    $scope.loadInfo = function () {
        $http.get('info.json')
            .then(function successCallback(response) {
                $scope.info = response.data;
                $log.info('actuator info loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };

    $scope.health = {};
    $scope.loadHealth = function () {
        $http.get('health.json')
            .then(function successCallback(response) {
                $scope.health = response.data;
                $log.info('actuator health loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };
});